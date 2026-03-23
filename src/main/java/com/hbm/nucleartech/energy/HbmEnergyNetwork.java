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
                    // Storage — только потребитель в сети
                    nodes.add(new NetworkNode(neighbor, null, storage, null));
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

    public static void distribute(ServerLevel level, BlockPos providerPos,
                                  IHbmEnergy.Provider provider) {
        long available = provider.extractEnergy(provider.getEnergyStored(), true);
        if (available <= 0) return;

        List<NetworkNode> nodes = findNetwork(level, providerPos, provider.getOutputTier());

        List<NetworkNode> consumerNodes = nodes.stream()
                .filter(n -> n.consumer() != null).toList();
        List<NetworkNode> cableNodes = nodes.stream()
                .filter(n -> n.cable() != null).toList();

        if (consumerNodes.isEmpty()) return;

        long perConsumer = available / consumerNodes.size();
        long totalTransferred = 0;

        for (NetworkNode node : consumerNodes) {
            if (perConsumer <= 0) break;
            long loss = (long)(perConsumer * provider.getOutputTier().lossFactor * 10);
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

