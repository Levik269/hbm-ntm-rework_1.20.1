package com.hbm.nucleartech.block.entity;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.energy.CableTier;
import com.hbm.nucleartech.energy.HbmEnergyNetwork;
import com.hbm.nucleartech.energy.IHbmEnergy;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TestGeneratorBlockEntity extends BlockEntity implements IHbmEnergy.Provider {

    private long energy = 0;
    private static final long MAX_ENERGY = 100000;
    private static final long GEN_PER_TICK = 500;

    public TestGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlocks.TEST_GENERATOR_BE.get(), pos, state);
    }

    public void tick() {
        if (level == null || level.isClientSide) return;

        // генерируем энергию каждый тик
        energy = Math.min(energy + GEN_PER_TICK, MAX_ENERGY);

        // раздаём энергию по сети каждые 5 тиков
        if (level.getGameTime() % 5 == 0 && energy > 0) {
            HbmEnergyNetwork.distribute((ServerLevel) level, worldPosition, this);
        }
    }

    @Override
    public long extractEnergy(long maxExtract, boolean simulate) {
        long extracted = Math.min(energy, maxExtract);
        if (!simulate) energy -= extracted;
        return extracted;
    }

    @Override
    public long getEnergyStored() { return energy; }

    @Override
    public CableTier getOutputTier() { return CableTier.LV_COPPER; }

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
