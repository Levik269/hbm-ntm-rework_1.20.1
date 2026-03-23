package com.hbm.nucleartech.item.custom;

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
            long max = cable.getCableTier().maxTransfer;
            float percent = max > 0 ? (load * 100f / max) : 0;
            String status = percent > 150 ? "§cOVERLOAD!" : percent > 80 ? "§eWARNING" : "§aNORMAL";
            String bar = buildBar(percent);

            player.sendSystemMessage(Component.literal("§eCable: §f" + cable.getCableTier().getDisplayName()));
            player.sendSystemMessage(Component.literal("§eLoad: §f" + load + " / " + max + " HBM/t"));
            player.sendSystemMessage(Component.literal(bar + "  " + String.format("%.1f", percent) + "%"));
            player.sendSystemMessage(Component.literal("§eStatus: " + status));

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
