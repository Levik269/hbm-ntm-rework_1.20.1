package com.hbm.nucleartech.item.custom;

import com.hbm.nucleartech.energy.CableTier;
import com.hbm.nucleartech.energy.ICableBlockEntity;
import com.hbm.nucleartech.energy.IHbmEnergy;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.entity.BlockEntity;

public class MultimeterItem extends Item {

    public MultimeterItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().isClientSide) return InteractionResult.SUCCESS;

        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ServerLevel level = (ServerLevel) context.getLevel();
        BlockEntity be = level.getBlockEntity(pos);

        player.sendSystemMessage(Component.literal("§6=== Multimeter ==="));

        if (be instanceof ICableBlockEntity cable) {
            long load = cable.getCurrentLoad();
            CableTier tier = cable.getCableTier();
            float current = tier.currentAt(load);
            float percent = tier.maxTransfer > 0 ? (load * 100f / tier.maxTransfer) : 0;
            String status = current > tier.maxCurrent ? "§cOVERLOAD!" : percent > 80 ? "§eWARNING" : "§aNORMAL";

            player.sendSystemMessage(Component.literal("§eCable: §f" + tier.getDisplayName()));
            player.sendSystemMessage(Component.literal(
                    "§eU=§f" + tier.voltage + "V  §eI=§f" + String.format("%.1f", current) +
                            "/" + tier.maxCurrent + "A  §eP=§f" + load + "/" + tier.maxTransfer + "W"));
            player.sendSystemMessage(Component.literal(
                    "§eR=§f" + tier.resistance + "Ohm/блок  §eP_loss/блок=§f" +
                            String.format("%.2f", current * current * tier.resistance) + "W"));
            player.sendSystemMessage(Component.literal(buildBar(percent) + "  " +
                    String.format("%.1f", percent) + "%  " + status));
        } else if (be instanceof IHbmEnergy.Storage storage) {
            long stored = storage.getEnergyStored();
            long max = storage.getMaxEnergyStored();
            float percent = max > 0 ? (stored * 100f / max) : 0;

            player.sendSystemMessage(Component.literal("§eType: §fStorage"));
            player.sendSystemMessage(Component.literal("§eCharge: §f" + stored + " / " + max + " HBM"));
            player.sendSystemMessage(Component.literal(buildBar(percent) + "  " + String.format("%.1f", percent) + "%"));
            player.sendSystemMessage(Component.literal("§eTier in: §f" + storage.getInputTier().getDisplayName()));

        } else if (be instanceof IHbmEnergy.Provider provider) {
            player.sendSystemMessage(Component.literal("§eType: §fProvider"));
            player.sendSystemMessage(Component.literal("§eStored: §f" + provider.getEnergyStored() + " HBM"));
            player.sendSystemMessage(Component.literal("§eTier out: §f" + provider.getOutputTier().getDisplayName()));

        } else if (be instanceof IHbmEnergy.Consumer consumer) {
            long stored = consumer.getEnergyStored();
            long max = consumer.getMaxEnergyStored();
            float percent = max > 0 ? (stored * 100f / max) : 0;
            player.sendSystemMessage(Component.literal("§eType: §fConsumer"));
            player.sendSystemMessage(Component.literal("§eCharge: §f" + stored + " / " + max + " HBM"));
            player.sendSystemMessage(Component.literal(buildBar(percent) + "  " + String.format("%.1f", percent) + "%"));
            player.sendSystemMessage(Component.literal("§eTier in: §f" + consumer.getInputTier().getDisplayName()));

        } else {
            player.sendSystemMessage(Component.literal("§cNo energy component here."));
        }

        return InteractionResult.SUCCESS;
    }

    protected String buildBar(float percent) {
        int filled = Math.min((int)(percent / 10), 10);
        String color = percent > 150 ? "§c" : percent > 80 ? "§e" : "§a";
        return color + "█".repeat(filled) + "§7" + "░".repeat(10 - filled);
    }
}
