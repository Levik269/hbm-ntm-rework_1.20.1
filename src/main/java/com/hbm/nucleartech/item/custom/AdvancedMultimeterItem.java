package com.hbm.nucleartech.item.custom;

import com.hbm.nucleartech.energy.CableTier;
import com.hbm.nucleartech.energy.HbmEnergyNetwork;
import com.hbm.nucleartech.energy.ICableBlockEntity;
import com.hbm.nucleartech.energy.IHbmEnergy;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.List;
import java.util.Map;

public class AdvancedMultimeterItem extends MultimeterItem {

    public enum Mode {
        LOAD("§bLoad Monitor"),
        SOURCES("§aSource Scanner"),
        CONSUMERS("§eConsumer Scanner"),
        LOSSES("§cLoss Analyzer");

        public final String displayName;
        Mode(String name) { this.displayName = name; }
        public Mode next() { return values()[(ordinal() + 1) % values().length]; }
    }

    public AdvancedMultimeterItem(Properties properties) {
        super(properties);
    }

    private Mode getMode(ItemStack stack) {
        try { return Mode.valueOf(stack.getOrCreateTag().getString("mode")); }
        catch (Exception e) { return Mode.LOAD; }
    }

    private void setMode(ItemStack stack, Mode mode) {
        stack.getOrCreateTag().putString("mode", mode.name());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(net.minecraft.world.level.Level level,
                                                  Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isCrouching()) {
            Mode next = getMode(stack).next();
            setMode(stack, next);
            if (!level.isClientSide)
                player.sendSystemMessage(Component.literal("§6Mode switched: " + next.displayName));
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().isClientSide) return InteractionResult.SUCCESS;

        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ServerLevel level = (ServerLevel) context.getLevel();
        ItemStack stack = context.getItemInHand();
        Mode mode = getMode(stack);
        BlockEntity be = level.getBlockEntity(pos);

        player.sendSystemMessage(Component.literal(
                "§6=== Advanced Multimeter [" + mode.displayName + "§6] ==="));

        switch (mode) {
            case LOAD -> {
                if (be instanceof ICableBlockEntity cable) {
                    long load = cable.getCurrentLoad();
                    long max = cable.getCableTier().maxTransfer;
                    float percent = max > 0 ? (load * 100f / max) : 0;
                    String status = percent > 150 ? "§cOVERLOAD!" : percent > 80 ? "§eWARNING" : "§aNORMAL";
                    player.sendSystemMessage(Component.literal("§eCable: §f" + cable.getCableTier().getDisplayName()));
                    player.sendSystemMessage(Component.literal("§eLoad: §f" + load + " / " + max + " HBM/t"));
                    player.sendSystemMessage(Component.literal(buildBar(percent) + "  " + String.format("%.1f", percent) + "%  " + status));
                    player.sendSystemMessage(Component.literal("§eOverload ticks: §f" +
                            (cable instanceof com.hbm.nucleartech.block.entity.CableBlockEntity cbe ? cbe.getOverloadTicks() : "N/A")));
                } else {
                    player.sendSystemMessage(Component.literal("§cClick on a cable for load info."));
                }
            }
            case SOURCES -> {
                if (!(be instanceof ICableBlockEntity cable)) {
                    player.sendSystemMessage(Component.literal("§cClick on a cable to scan sources."));
                    return InteractionResult.SUCCESS;
                }
                List<HbmEnergyNetwork.NetworkNode> nodes = HbmEnergyNetwork.findNetwork(
                        level, pos, cable.getCableTier());
                long totalAvailable = 0;
                int count = 0;
                for (HbmEnergyNetwork.NetworkNode node : nodes) {
                    if (node.provider() == null) continue;
                    long stored = node.provider().getEnergyStored();
                    totalAvailable += stored;
                    count++;
                    player.sendSystemMessage(Component.literal(
                            "§a[+] §f" + level.getBlockEntity(node.pos()).getClass().getSimpleName() +
                                    " §7at " + node.pos().toShortString() +
                                    " §f| " + stored + " HBM | " + node.provider().getOutputTier().getDisplayName()
                    ));
                }
                player.sendSystemMessage(Component.literal(
                        "§6Sources: §f" + count + " §6| Total: §f" + totalAvailable + " HBM"));
            }
            case CONSUMERS -> {
                if (!(be instanceof ICableBlockEntity cable)) {
                    player.sendSystemMessage(Component.literal("§cClick on a cable to scan consumers."));
                    return InteractionResult.SUCCESS;
                }
                List<HbmEnergyNetwork.NetworkNode> nodes = HbmEnergyNetwork.findNetwork(
                        level, pos, cable.getCableTier());
                long totalDemand = 0;
                int count = 0;
                for (HbmEnergyNetwork.NetworkNode node : nodes) {
                    if (node.consumer() == null) continue;
                    long stored = node.consumer().getEnergyStored();
                    long max = node.consumer().getMaxEnergyStored();
                    float percent = max > 0 ? (stored * 100f / max) : 0;
                    long demand = max - stored;
                    totalDemand += demand;
                    count++;
                    player.sendSystemMessage(Component.literal(
                            "§e[-] §f" + level.getBlockEntity(node.pos()).getClass().getSimpleName() +
                                    " §7at " + node.pos().toShortString() +
                                    " §f| " + String.format("%.1f", percent) + "% | needs " + demand + " HBM"
                    ));
                }
                player.sendSystemMessage(Component.literal(
                        "§6Consumers: §f" + count + " §6| Total demand: §f" + totalDemand + " HBM"));
            }
            case LOSSES -> {
                if (!(be instanceof ICableBlockEntity cable)) {
                    player.sendSystemMessage(Component.literal("§cClick on a cable to analyze losses."));
                    return InteractionResult.SUCCESS;
                }

                // получаем карту потерь от всех источников до всех потребителей
                Map<BlockPos, Map<BlockPos, Float>> lossMap =
                        HbmEnergyNetwork.findLossMap(level, pos, cable.getCableTier());

                if (lossMap.isEmpty()) {
                    player.sendSystemMessage(Component.literal("§cNo consumers found in network."));
                    return InteractionResult.SUCCESS;
                }

                // для каждого потребителя выводим инфо
                for (Map.Entry<BlockPos, Map<BlockPos, Float>> consumerEntry : lossMap.entrySet()) {
                    BlockPos consumerPos = consumerEntry.getKey();
                    Map<BlockPos, Float> sourceLosses = consumerEntry.getValue();
                    BlockEntity consumerBe = level.getBlockEntity(consumerPos);
                    if (consumerBe == null) continue;

                    player.sendSystemMessage(Component.literal(
                            "§6── " + consumerBe.getClass().getSimpleName() +
                                    " §7at " + consumerPos.toShortString() + " ──"));

                    long sigma = 0;
                    long totalAvailable = 0;

                    for (Map.Entry<BlockPos, Float> sourceEntry : sourceLosses.entrySet()) {
                        BlockPos sourcePos = sourceEntry.getKey();
                        float lossRate = sourceEntry.getValue();
                        BlockEntity sourceBe = level.getBlockEntity(sourcePos);
                        if (sourceBe == null) continue;

                        long sourceEnergy = 0;
                        if (sourceBe instanceof IHbmEnergy.Provider prov)
                            sourceEnergy = prov.getEnergyStored();

                        long lossAmount = (long)(sourceEnergy * lossRate);
                        long delivered = Math.max(0, sourceEnergy - lossAmount);
                        sigma += delivered;
                        totalAvailable += sourceEnergy;

                        String lossPercent = String.format("%.2f", lossRate * 100);
                        player.sendSystemMessage(Component.literal(
                                "§7  §a[src] §f" + sourceBe.getClass().getSimpleName() +
                                        " §7at " + sourcePos.toShortString() +
                                        " §f| loss: §c" + lossPercent + "%" +
                                        " §f| delivers: §a" + delivered + " §7/ " + sourceEnergy + " HBM"
                        ));
                    }

                    // sigma — суммарная энергия от всех источников с учётом потерь
                    player.sendSystemMessage(Component.literal(
                            "§6  Σ delivered: §a" + sigma + " §6/ available: §f" + totalAvailable + " HBM" +
                                    " §6(net loss: §c" + (totalAvailable > 0 ?
                                    String.format("%.1f", (totalAvailable - sigma) * 100f / totalAvailable) : "0") + "%§6)"
                    ));
                    player.sendSystemMessage(Component.literal("§7"));
                }
            }

        }
        return InteractionResult.SUCCESS;
    }
}
