package com.hbm.nucleartech.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.*;

public class FluidPipeNetwork {

    public static record PipeNode(
            BlockPos pos,
            IFluidPipe pipe,
            IFluidConsumer consumer,
            IFluidProvider provider,
            boolean isCompressor) {}

    public static List<PipeNode> findNetwork(ServerLevel level, BlockPos start) {
        List<PipeNode> nodes = new ArrayList<>();
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            BlockPos pos = queue.poll();
            for (Direction dir : Direction.values()) {
                BlockPos neighbor = pos.relative(dir);
                if (visited.contains(neighbor)) continue;
                visited.add(neighbor);

                BlockEntity be = level.getBlockEntity(neighbor);
                if (be == null) continue;

                if (be instanceof IFluidPipe pipe) {
                    if (!pipe.canFlow()) continue;
                    nodes.add(new PipeNode(neighbor, pipe, null, null, false));
                    queue.add(neighbor);
                } else if (be instanceof IFluidCompressor compressor) {
                    nodes.add(new PipeNode(neighbor, null, null, null, compressor.isActive()));
                    queue.add(neighbor);
                } else if (be instanceof IFluidProvider provider) {
                    nodes.add(new PipeNode(neighbor, null, null, provider, false));
                } else if (be instanceof IFluidConsumer consumer) {
                    nodes.add(new PipeNode(neighbor, null, consumer, null, false));
                }
            }
        }
        return nodes;
    }

    public static void distribute(ServerLevel level, BlockPos providerPos,
                                   IFluidProvider provider) {
        long available = provider.extractFluid(provider.getFluidStored(), true);
        if (available <= 0) return;

        // BFS с расстоянием и давлением
        Map<BlockPos, Integer>       distances = new HashMap<>();
        Map<BlockPos, FluidPressure> pressures = new HashMap<>();
        List<PipeNode> allNodes = new ArrayList<>();
        Set<BlockPos> visited   = new HashSet<>();
        Queue<BlockPos> queue   = new LinkedList<>();

        queue.add(providerPos);
        visited.add(providerPos);
        distances.put(providerPos, 0);
        pressures.put(providerPos, provider.getOutputPressure());

        while (!queue.isEmpty()) {
            BlockPos pos = queue.poll();
            int dist = distances.get(pos);
            FluidPressure pressure = pressures.get(pos);

            // давление упало до нуля — дальше не идём
            if (!pressure.canReach(dist)) continue;

            for (Direction dir : Direction.values()) {
                BlockPos neighbor = pos.relative(dir);
                if (visited.contains(neighbor)) continue;
                visited.add(neighbor);

                BlockEntity be = level.getBlockEntity(neighbor);
                if (be == null) continue;

                int newDist = dist + 1;
                FluidPressure newPressure = pressure;

                if (be instanceof IFluidCompressor compressor) {
                    if (compressor.isActive()) {
                        // активный компрессор повышает давление и сбрасывает расстояние
                        newPressure = pressure.boost();
                        newDist = 0;
                    }
                    // неактивный — пропускает жидкость без boost (как труба)
                    allNodes.add(new PipeNode(neighbor, null, null, null, compressor.isActive()));
                    distances.put(neighbor, newDist);
                    pressures.put(neighbor, newPressure);
                    queue.add(neighbor);
                } else if (be instanceof IFluidPipe pipe) {
                    // труба — проверяем достаточно ли давления и открыт ли вентиль
                    if (!pipe.canFlow()) continue;
                    if (pressure.canReach(dist)) {
                        allNodes.add(new PipeNode(neighbor, pipe, null, null, false));
                        distances.put(neighbor, newDist);
                        pressures.put(neighbor, newPressure);
                        queue.add(neighbor);
                    }
                } else if (be instanceof IFluidConsumer consumer) {
                    if (pressure.canReach(dist) &&
                            consumer.acceptsFluid(provider.getFluidType())) {
                        allNodes.add(new PipeNode(neighbor, null, consumer, null, false));
                        distances.put(neighbor, newDist);
                        pressures.put(neighbor, newPressure);
                    }
                }
            }
        }

        // раздаём жидкость потребителям
        List<PipeNode> consumers = allNodes.stream()
                .filter(n -> n.consumer() != null).toList();
        if (consumers.isEmpty()) return;

        long perConsumer = available / consumers.size();

        for (PipeNode node : consumers) {
            if (perConsumer <= 0) break;
            FluidPressure p = pressures.getOrDefault(node.pos(), FluidPressure.LOW);
            long accepted = node.consumer().receiveFluid(perConsumer, p, true);
            if (accepted > 0) {
                provider.extractFluid(accepted, false);
                node.consumer().receiveFluid(accepted, p, false);
            }
        }

        // обновляем давление в трубах
        for (PipeNode node : allNodes) {
            if (node.pipe() != null) {
                FluidPressure p = pressures.getOrDefault(node.pos(), FluidPressure.LOW);
                node.pipe().setCurrentPressure(p);
                node.pipe().setDistanceFromSource(distances.getOrDefault(node.pos(), 0));
            }
        }
    }
}
