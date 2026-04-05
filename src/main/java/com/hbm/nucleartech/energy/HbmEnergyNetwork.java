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

        boolean junctionCheckEnabled = sourceTier.isLV() || sourceTier.isMV() || sourceTier.isHV();

        while (!queue.isEmpty()) {
            BlockPos pos = queue.poll();
            boolean comingFromCable = level.getBlockEntity(pos) instanceof ICableBlockEntity;
            for (Direction dir : Direction.values()) {
                BlockPos neighbor = pos.relative(dir);
                if (visited.contains(neighbor)) continue;
                visited.add(neighbor);

                BlockEntity be = level.getBlockEntity(neighbor);

                if (be == null) continue;

                if (be instanceof ICableBlockEntity cable) {
                    if (sourceTier.canConnectTo(cable.getCableTier())) {
                        nodes.add(new NetworkNode(neighbor, cable, null, null));
                        CableTier ct = cable.getCableTier();
                        boolean freeBranch = (ct == CableTier.CONNECTOR_COPPER || ct == CableTier.UNIVERSAL);
                        boolean blocked = junctionCheckEnabled && !freeBranch
                                && comingFromCable && isCableJunction(level, neighbor, visited, ct);
                        if (!blocked) {
                            queue.add(neighbor);
                        }
                    }
                } else if (be instanceof IHbmEnergy.Storage storage) {
                    nodes.add(new NetworkNode(neighbor, null, storage, null));
                } else if (be instanceof IHbmEnergy.Consumer && be instanceof IHbmEnergy.Provider) {
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

        List<BlockPos> providerPositions = new ArrayList<>();
        BlockEntity startBe = level.getBlockEntity(startPos);
        if (startBe instanceof IHbmEnergy.Provider)
            providerPositions.add(startPos);
        for (NetworkNode n : providers)
            providerPositions.add(n.pos());

        for (BlockPos provPos : providerPositions) {
            BlockEntity provBe = level.getBlockEntity(provPos);
            long provPower = provBe instanceof IHbmEnergy.Provider p ? p.getEnergyStored() : 0;

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
                            // I²R потери — реалистичная физика
                            float segLoss = lossHere + cable.getCableTier().lossFactorAt(provPower);
                            accumulated.put(neighbor, segLoss);
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

        long available = Math.min(
                provider.extractEnergy(provider.getEnergyStored(), true),
                provider.getOutputTier().maxTransfer
        );
        if (available <= 0) return;
        if (level.getGameTime() % 20 == 0)
            System.out.println("[D] from=" + providerPos + " avail=" + available + " tier=" + provider.getOutputTier().name);


        Map<BlockPos, Float> accumulatedLoss = new java.util.HashMap<>();
        List<NetworkNode> allNodes = new ArrayList<>();
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new LinkedList<>();

        queue.add(providerPos);
        visited.add(providerPos);
        accumulatedLoss.put(providerPos, 0f);

        // junction check only applies when provider outputs LV/MV/HV (substation networks)
        CableTier outTier = provider.getOutputTier();
        boolean junctionCheckEnabled = outTier.isLV() || outTier.isMV() || outTier.isHV();

        while (!queue.isEmpty()) {
            BlockPos pos = queue.poll();
            float lossHere = accumulatedLoss.get(pos);
            boolean comingFromCable = level.getBlockEntity(pos) instanceof ICableBlockEntity;

            for (Direction dir : Direction.values()) {
                BlockPos neighbor = pos.relative(dir);
                if (visited.contains(neighbor)) continue;
                visited.add(neighbor);

                BlockEntity be = level.getBlockEntity(neighbor);
                if (be == null) continue;

                if (be instanceof ICableBlockEntity cable) {
                    if (outTier.canConnectTo(cable.getCableTier())) {
                        float segLoss = lossHere + cable.getCableTier().lossFactorAt(available);
                        accumulatedLoss.put(neighbor, segLoss);
                        allNodes.add(new NetworkNode(neighbor, cable, null, null));
                        CableTier ct = cable.getCableTier();
                        boolean freeBranch = (ct == CableTier.CONNECTOR_COPPER || ct == CableTier.UNIVERSAL);
                        boolean blocked = junctionCheckEnabled && !freeBranch
                                && comingFromCable && isCableJunction(level, neighbor, visited, ct);
                        if (!blocked) queue.add(neighbor);
                    }
                } else if (be instanceof IHbmEnergy.Storage storage) {
                    accumulatedLoss.put(neighbor, lossHere);
                    allNodes.add(new NetworkNode(neighbor, null, storage, null));
                } else if (be instanceof IHbmEnergy.Consumer && be instanceof IHbmEnergy.Provider) {
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
                    accumulatedLoss.put(neighbor, lossHere);
                    allNodes.add(new NetworkNode(neighbor, null, consumer, null));
                }
            }
        }

        List<NetworkNode> consumerNodes = allNodes.stream()
                .filter(n -> n.consumer() != null).toList();
        List<NetworkNode> cableNodes = allNodes.stream()
                .filter(n -> n.cable() != null).toList();

        if (consumerNodes.isEmpty()) {
            if (level.getGameTime() % 20 == 0)
                System.out.println("[D2-EMPTY] " + providerPos + " tier=" + provider.getOutputTier().name
                        + " cables=" + cableNodes.size() + " total_nodes=" + allNodes.size());
            return;
        }
        if (level.getGameTime() % 20 == 0)
            System.out.println("[D2] " + providerPos + " consumers=" + consumerNodes.size() + " cables=" + cableNodes.size() + " tier=" + provider.getOutputTier().name);


        long perConsumer = available / consumerNodes.size();
        long totalTransferred = 0;

        for (NetworkNode node : consumerNodes) {
            if (perConsumer <= 0) break;
            float totalLoss = Math.min(accumulatedLoss.getOrDefault(node.pos(), 0f), 0.95f);
            long loss = (long)(perConsumer * totalLoss);
            long delivered = perConsumer - loss;
            long accepted = node.consumer().receiveEnergy(delivered, true);
            if (accepted > 0) {
                provider.extractEnergy(accepted + loss, false);
                node.consumer().receiveEnergy(accepted, false);
                totalTransferred += accepted + loss;
            } else if (level.getGameTime() % 20 == 0) {
                System.out.println("[D-REJECT] " + node.pos() + " delivered=" + delivered + " accepted=0 (buffer full?)");
            }
        }

        for (NetworkNode node : cableNodes) {
            node.cable().setCurrentLoad(totalTransferred);
            // проверяем перегрузку только для кабелей основного тира провайдера
            CableTier ct = node.cable().getCableTier();
            if (ct == outTier || ct == CableTier.UNIVERSAL) {
                checkOverload(level, node.pos(), node.cable(), totalTransferred);
            }
        }
    }

    /** Returns true if cablePos has 2+ unvisited compatible cable neighbors (T/X junction). */
    private static boolean isCableJunction(ServerLevel level, BlockPos cablePos,
                                            Set<BlockPos> visited, CableTier sourceTier) {
        int count = 0;
        for (Direction d : Direction.values()) {
            BlockPos adj = cablePos.relative(d);
            if (visited.contains(adj)) continue;
            BlockEntity be = level.getBlockEntity(adj);
            if (be instanceof ICableBlockEntity adjCable
                    && sourceTier.canConnectTo(adjCable.getCableTier())) {
                if (++count >= 2) return true;
            }
        }
        return false;
    }

    private static void checkOverload(ServerLevel level, BlockPos pos,
                                      ICableBlockEntity cable, long load) {
        if (!cable.getCableTier().isOverloaded(load)) return;
        if (!(cable instanceof CableBlockEntity cbe)) return;

        System.out.println("[Overload] " + pos + " load=" + load +
                " max=" + cable.getCableTier().maxTransfer +
                " current=" + String.format("%.1f", cable.getCableTier().currentAt(load)) +
                "/" + cable.getCableTier().maxCurrent + "A" +
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
