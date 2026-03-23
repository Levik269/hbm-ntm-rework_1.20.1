package com.hbm.nucleartech.energy;

import com.hbm.nucleartech.block.entity.CableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.*;

public class HbmEnergyNetwork {

    public static List<NetworkNode> findNetwork(ServerLevel level,
                                                BlockPos start,
                                                CableTier sourceTier) {
        List<NetworkNode> nodes = new ArrayList<>();
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

                if (be instanceof ICableBlockEntity cable) {
                    if (sourceTier.canConnectTo(cable.getCableTier())) {
                        nodes.add(new NetworkNode(neighbor, cable, null, null));
                        queue.add(neighbor);
                    }
                } else if (be instanceof IHbmEnergy.Storage storage) {
                    nodes.add(new NetworkNode(neighbor, null, storage, null));
                } else if (be instanceof IHbmEnergy.Consumer && be instanceof IHbmEnergy.Provider) {
                    // трансформатор — и потребитель и провайдер
                    IHbmEnergy.Consumer consumer = (IHbmEnergy.Consumer) be;
                    IHbmEnergy.Provider provider = (IHbmEnergy.Provider) be;
                    if (sourceTier.canConnectTo(consumer.getInputTier()))
                        nodes.add(new NetworkNode(neighbor, null, consumer, provider));
                } else if (be instanceof IHbmEnergy.Provider provider) {
                    nodes.add(new NetworkNode(neighbor, null, null, provider));
                } else if (be instanceof IHbmEnergy.Consumer consumer) {
                    if (sourceTier.canConnectTo(consumer.getInputTier()))
                        nodes.add(new NetworkNode(neighbor, null, consumer, null));
                }
            }
        }
        return nodes;
    }

    public static Map<BlockPos, Map<BlockPos, Float>> findLossMap(
            ServerLevel level, BlockPos startPos, CableTier sourceTier) {

        Map<BlockPos, Map<BlockPos, Float>> result = new java.util.HashMap<>();

        List<NetworkNode> network = findNetwork(level, startPos, sourceTier);
        List<NetworkNode> providers = network.stream()
                .filter(n -> n.provider() != null).toList();

        // только настоящие провайдеры, не кабели
        List<BlockPos> providerPositions = new ArrayList<>();
        BlockEntity startBe = level.getBlockEntity(startPos);
        if (startBe instanceof IHbmEnergy.Provider)
            providerPositions.add(startPos);
        for (NetworkNode n : providers)
            providerPositions.add(n.pos());

        for (BlockPos provPos : providerPositions) {
            Map<BlockPos, Float> accumulated = new java.util.HashMap<>();
            Set<BlockPos> visited = new HashSet<>();
            Queue<BlockPos> queue = new LinkedList<>();

            queue.add(provPos);
            visited.add(provPos);
            accumulated.put(provPos, 0f);

            while (!queue.isEmpty()) {
                BlockPos pos = queue.poll();
                float lossHere = accumulated.get(pos);

                for (Direction dir : Direction.values()) {
                    BlockPos neighbor = pos.relative(dir);
                    if (visited.contains(neighbor)) continue;
                    visited.add(neighbor);

                    BlockEntity be = level.getBlockEntity(neighbor);
                    if (be == null) continue;

                    if (be instanceof ICableBlockEntity cable) {
                        if (sourceTier.canConnectTo(cable.getCableTier())) {
                            accumulated.put(neighbor, lossHere + cable.getCableTier().lossFactor);
                            queue.add(neighbor);
                        }
                    } else if (be instanceof IHbmEnergy.Consumer
                            || be instanceof IHbmEnergy.Storage) {
                        accumulated.put(neighbor, lossHere);
                    }
                }
            }

            for (Map.Entry<BlockPos, Float> entry : accumulated.entrySet()) {
                BlockEntity be = level.getBlockEntity(entry.getKey());
                if (be instanceof IHbmEnergy.Consumer || be instanceof IHbmEnergy.Storage) {
                    result.computeIfAbsent(entry.getKey(), k -> new java.util.LinkedHashMap<>())
                            .put(provPos, entry.getValue());
                }
            }
        }
        return result;
    }

    public static void distribute(ServerLevel level, BlockPos providerPos,
                                  IHbmEnergy.Provider provider) {
        long available = provider.extractEnergy(provider.getEnergyStored(), true);
        if (available <= 0) return;

        Map<BlockPos, Float> accumulatedLoss = new java.util.HashMap<>();
        List<NetworkNode> allNodes = new ArrayList<>();
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new LinkedList<>();

        queue.add(providerPos);
        visited.add(providerPos);
        accumulatedLoss.put(providerPos, 0f);

        while (!queue.isEmpty()) {
            BlockPos pos = queue.poll();
            float lossHere = accumulatedLoss.get(pos);

            for (Direction dir : Direction.values()) {
                BlockPos neighbor = pos.relative(dir);
                if (visited.contains(neighbor)) continue;
                visited.add(neighbor);

                BlockEntity be = level.getBlockEntity(neighbor);
                if (be == null) continue;

                if (be instanceof ICableBlockEntity cable) {
                    if (provider.getOutputTier().canConnectTo(cable.getCableTier())) {
                        accumulatedLoss.put(neighbor, lossHere + cable.getCableTier().lossFactor);
                        allNodes.add(new NetworkNode(neighbor, cable, null, null));
                        queue.add(neighbor);
                    }
                } else if (be instanceof IHbmEnergy.Storage storage) {
                    accumulatedLoss.put(neighbor, lossHere);
                    allNodes.add(new NetworkNode(neighbor, null, storage, null));
                } else if (be instanceof IHbmEnergy.Consumer && be instanceof IHbmEnergy.Provider) {
                    // трансформатор
                    IHbmEnergy.Consumer consumer = (IHbmEnergy.Consumer) be;
                    IHbmEnergy.Provider prov = (IHbmEnergy.Provider) be;
                    if (provider.getOutputTier().canConnectTo(consumer.getInputTier())) {
                        accumulatedLoss.put(neighbor, lossHere);
                        allNodes.add(new NetworkNode(neighbor, null, consumer, prov));
                    }
                } else if (be instanceof IHbmEnergy.Provider prov) {
                    accumulatedLoss.put(neighbor, lossHere);
                    allNodes.add(new NetworkNode(neighbor, null, null, prov));
                } else if (be instanceof IHbmEnergy.Consumer consumer) {
                    if (provider.getOutputTier().canConnectTo(consumer.getInputTier())) {
                        accumulatedLoss.put(neighbor, lossHere);
                        allNodes.add(new NetworkNode(neighbor, null, consumer, null));
                    }
                }
            }
        }

        List<NetworkNode> consumerNodes = allNodes.stream()
                .filter(n -> n.consumer() != null).toList();
        List<NetworkNode> cableNodes = allNodes.stream()
                .filter(n -> n.cable() != null).toList();

        if (consumerNodes.isEmpty()) return;

        long perConsumer = available / consumerNodes.size();
        long totalTransferred = 0;

        for (NetworkNode node : consumerNodes) {
            if (perConsumer <= 0) break;
            float totalLoss = accumulatedLoss.getOrDefault(node.pos(), 0f);
            long loss = (long)(perConsumer * totalLoss);
            long delivered = Math.max(0, perConsumer - loss);
            long accepted = node.consumer().receiveEnergy(delivered, true);
            if (accepted > 0) {
                provider.extractEnergy(accepted + loss, false);
                node.consumer().receiveEnergy(accepted, false);
                totalTransferred += accepted + loss;
            }
        }

        for (NetworkNode node : cableNodes) {
            node.cable().setCurrentLoad(totalTransferred);
            checkOverload(level, node.pos(), node.cable(), totalTransferred);
        }
    }

    private static void checkOverload(ServerLevel level, BlockPos pos,
                                      ICableBlockEntity cable, long load) {
        if (load <= cable.getCableTier().maxTransfer * 1.5f) return;
        if (!cable.getCableTier().isOverloaded(load)) return;
        if (!(cable instanceof CableBlockEntity cbe)) return;

        System.out.println("[Overload] " + pos + " load=" + load +
                " max=" + cable.getCableTier().maxTransfer +
                " ticks=" + cbe.getOverloadTicks());

        if (cbe.getOverloadTicks() < 60) return;

        CableTier tier = cable.getCableTier();
        if (tier.isHV()) {
            level.explode(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    3.0f, net.minecraft.world.level.Level.ExplosionInteraction.BLOCK);
        } else if (tier.isMV()) {
            level.setBlock(pos, Blocks.FIRE.defaultBlockState(), 3);
            if (load > tier.maxTransfer * 2) {
                level.explode(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        1.5f, net.minecraft.world.level.Level.ExplosionInteraction.BLOCK);
            }
        } else {
            level.setBlock(pos, Blocks.FIRE.defaultBlockState(), 3);
        }
    }

    public static record NetworkNode(
            BlockPos pos,
            ICableBlockEntity cable,
            IHbmEnergy.Consumer consumer,
            IHbmEnergy.Provider provider) {}

    public static List<IHbmEnergy.Consumer> findConsumers(ServerLevel level,
                                                           BlockPos start,
                                                           CableTier sourceTier) {
        return findNetwork(level, start, sourceTier).stream()
                .filter(n -> n.consumer() != null)
                .map(NetworkNode::consumer)
                .toList();
    }
}
