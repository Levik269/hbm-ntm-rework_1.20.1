package com.hbm.nucleartech.energy;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.*;

public class HbmEnergyNetwork {

    public static List<IHbmEnergy.Consumer> findConsumers(ServerLevel level,
                                                            BlockPos start,
                                                            CableTier sourceTier) {
        List<IHbmEnergy.Consumer> consumers = new ArrayList<>();
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
                    if (sourceTier.canConnectTo(cable.getCableTier()))
                        queue.add(neighbor);
                } else if (be instanceof IHbmEnergy.Storage storage) {
                    consumers.add(storage);
                } else if (be instanceof IHbmEnergy.Consumer consumer) {
                    if (sourceTier.canConnectTo(consumer.getInputTier()))
                        consumers.add(consumer);
                }
            }
        }
        return consumers;
    }

    public static void distribute(ServerLevel level, BlockPos providerPos,
                                   IHbmEnergy.Provider provider) {
        long available = provider.extractEnergy(provider.getEnergyStored(), true);
        if (available <= 0) return;

        List<IHbmEnergy.Consumer> consumers = findConsumers(level, providerPos, provider.getOutputTier());
        if (consumers.isEmpty()) return;

        long perConsumer = available / consumers.size();

        for (IHbmEnergy.Consumer consumer : consumers) {
            if (perConsumer <= 0) break;
            long loss = (long)(perConsumer * provider.getOutputTier().lossFactor * 10);
            long delivered = Math.max(0, perConsumer - loss);
            long accepted = consumer.receiveEnergy(delivered, true);
            if (accepted > 0) {
                provider.extractEnergy(accepted + loss, false);
                consumer.receiveEnergy(accepted, false);
            }
        }
    }
}
