package com.hbm.nucleartech.item.custom;


import com.hbm.nucleartech.damagesource.RegisterDamageSources;
import com.hbm.nucleartech.explosion.NuclearBombType;     // ← добавь свой класс
import com.hbm.nucleartech.explosion.NuclearExplosion;   // ← добавь
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class ItemUnstable extends Item {

    private final int radius;
    private final int timer;

    private static final String TIMER_TAG = "unstable_timer";

    private final NuclearBombType bombType;

    public ItemUnstable(int radius, int timer, NuclearBombType bombType, Properties properties) {
        super(properties);
        this.radius = radius;
        this.timer = timer;
        this.bombType = bombType;
    }

    private int scaledRadiusForCount(int count) {
        if (count <= 1) return radius;
        return (int) Math.max(1, Math.round(radius * Math.cbrt(count)));
    }

    private void setTimer(ItemStack stack, int time) {
        stack.getOrCreateTag().putInt(TIMER_TAG, time);
    }

    private int getTimer(ItemStack stack) {
        return stack.getOrCreateTag().getInt(TIMER_TAG);
    }

    private void initTimerIfNeeded(ItemStack stack) {
        if (!stack.getOrCreateTag().contains(TIMER_TAG)) {
            setTimer(stack, 0);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (stack.getDamageValue() != 0) return;

        initTimerIfNeeded(stack);

        int current = getTimer(stack) + 1;
        setTimer(stack, current);

        if (current >= timer && !world.isClientSide && world instanceof net.minecraft.server.level.ServerLevel serverLevel) {
            final int count = stack.getCount();
            final int explosionRadius = scaledRadiusForCount(count);

            stack.shrink(count);

            NuclearExplosion.explode(serverLevel, entity.getX(), entity.getY(), entity.getZ(), bombType);

            entity.hurt(RegisterDamageSources.NUCLEAR_BLAST, 10000F);
        }
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity itemEntity) {
        if (stack.getDamageValue() != 0) {
            return false;
        }

        initTimerIfNeeded(stack);

        int current = getTimer(stack) + 1;
        setTimer(stack, current);

        if (current >= timer && !itemEntity.level().isClientSide
                && itemEntity.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {

            final int explosionRadius = scaledRadiusForCount(stack.getCount());

            NuclearExplosion.explode(serverLevel, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), bombType);

            if (itemEntity.getOwner() instanceof Player player) {
                player.hurt(RegisterDamageSources.NUCLEAR_BLAST, 10000F);
            }

            itemEntity.discard();
            return true;
        }

        return false;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return !ItemStack.matches(oldStack, newStack);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        initTimerIfNeeded(stack);

        int progress = getTimer(stack);
        int percent = (int) ((progress * 100f) / timer);
        int remainingSeconds = Math.max(0, (timer - progress) / 20);

        tooltip.add(Component.literal("§4Unstable Item§r"));
        tooltip.add(Component.literal("§cDecay Time: " + (timer / 20) + "s - Explosion radius: ~" + scaledRadiusForCount(stack.getCount()) + "m§r"));
        tooltip.add(Component.literal("§cDecay: " + percent + "% §7(§c" + remainingSeconds + "s left§7)§r"));
    }
}