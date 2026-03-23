package com.hbm.nucleartech.block.entity;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.energy.CableTier;
import com.hbm.nucleartech.energy.IHbmEnergy;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BatteryBlockEntity extends BlockEntity implements IHbmEnergy.Storage {

    private long energy = 0;
    private static final long MAX_ENERGY = 500000;

    public BatteryBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlocks.BATTERY_BE.get(), pos, state);
    }

    public void tick() {
        if (level == null || level.isClientSide) return;
        // каждые 20 тиков печатаем уровень заряда в консоль для отладки
        if (level.getGameTime() % 20 == 0) {
            System.out.println("[Battery] " + worldPosition + " energy: " + energy + "/" + MAX_ENERGY);
        }
    }

    @Override
    public long receiveEnergy(long maxReceive, boolean simulate) {
        long received = Math.min(MAX_ENERGY - energy, maxReceive);
        if (!simulate) { energy += received; setChanged(); }
        return received;
    }

    @Override
    public long extractEnergy(long maxExtract, boolean simulate) {
        long extracted = Math.min(energy, maxExtract);
        if (!simulate) { energy -= extracted; setChanged(); }
        return extracted;
    }

    @Override public long getEnergyStored() { return energy; }
    @Override public long getMaxEnergyStored() { return MAX_ENERGY; }
    @Override public CableTier getInputTier() { return CableTier.HV_RED_GOLD; }
    @Override public CableTier getOutputTier() { return CableTier.HV_RED_GOLD; }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        energy = tag.getLong("energy");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putLong("energy", energy);
    }
}
