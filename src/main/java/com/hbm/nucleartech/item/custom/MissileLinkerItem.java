package com.hbm.nucleartech.item.custom;

import com.hbm.nucleartech.block.entity.MissileLauncherBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class MissileLinkerItem extends Item {

    public MissileLinkerItem(Properties properties) {
        super(properties);
    }

    // ПКМ по блоку — если это лаунчер, привязываем к нему
    // если это любой другой блок — устанавливаем цель
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (level.isClientSide) return InteractionResult.SUCCESS;

        if (level.getBlockEntity(pos) instanceof MissileLauncherBlockEntity launcher) {
            // кликнули по лаунчеру — привязываем линкер к нему
            CompoundTag tag = stack.getOrCreateTag();
            tag.putInt("launcherX", pos.getX());
            tag.putInt("launcherY", pos.getY());
            tag.putInt("launcherZ", pos.getZ());

            // применяем текущую цель если она уже была установлена
            if (tag.contains("targetX")) {
                launcher.setTarget(
                        tag.getDouble("targetX"),
                        tag.getDouble("targetY"),
                        tag.getDouble("targetZ")
                );
                player.sendSystemMessage(Component.literal(
                        "§aLinked to launcher and applied target: " +
                        (int)tag.getDouble("targetX") + " " +
                        (int)tag.getDouble("targetY") + " " +
                        (int)tag.getDouble("targetZ")
                ));
            } else {
                player.sendSystemMessage(Component.literal(
                        "§aLinked to launcher at " + pos.getX() + " " + pos.getY() + " " + pos.getZ() +
                        ". Now right-click anywhere to set target."
                ));
            }
        } else {
            // кликнули по обычному блоку — устанавливаем цель
            double tx = pos.getX() + 0.5;
            double ty = pos.getY();
            double tz = pos.getZ() + 0.5;

            CompoundTag tag = stack.getOrCreateTag();
            tag.putDouble("targetX", tx);
            tag.putDouble("targetY", ty);
            tag.putDouble("targetZ", tz);

            // если лаунчер привязан — сразу обновляем цель
            if (tag.contains("launcherX")) {
                BlockPos launcherPos = new BlockPos(
                        tag.getInt("launcherX"),
                        tag.getInt("launcherY"),
                        tag.getInt("launcherZ")
                );
                if (level.getBlockEntity(launcherPos) instanceof MissileLauncherBlockEntity launcher) {
                    launcher.setTarget(tx, ty, tz);
                    player.sendSystemMessage(Component.literal(
                            "§eTarget updated: " + (int)tx + " " + (int)ty + " " + (int)tz
                    ));
                } else {
                    player.sendSystemMessage(Component.literal(
                            "§cLauncher not found! Re-link to a launcher first."
                    ));
                }
            } else {
                player.sendSystemMessage(Component.literal(
                        "§eTarget set: " + (int)tx + " " + (int)ty + " " + (int)tz +
                        ". Now right-click a launcher to link."
                ));
            }
        }
        return InteractionResult.SUCCESS;
    }

    // ПКМ в воздух — показываем текущий статус
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        CompoundTag tag = stack.getOrCreateTag();

        if (!level.isClientSide) {
            StringBuilder info = new StringBuilder("§6=== Missile Linker ===\n");
            if (tag.contains("launcherX")) {
                info.append("§aLauncher: ")
                    .append(tag.getInt("launcherX")).append(" ")
                    .append(tag.getInt("launcherY")).append(" ")
                    .append(tag.getInt("launcherZ")).append("\n");
            } else {
                info.append("§cNo launcher linked\n");
            }
            if (tag.contains("targetX")) {
                info.append("§eTarget: ")
                    .append((int)tag.getDouble("targetX")).append(" ")
                    .append((int)tag.getDouble("targetY")).append(" ")
                    .append((int)tag.getDouble("targetZ"));
            } else {
                info.append("§cNo target set");
            }
            player.sendSystemMessage(Component.literal(info.toString()));
        }
        return InteractionResultHolder.success(stack);
    }
}
