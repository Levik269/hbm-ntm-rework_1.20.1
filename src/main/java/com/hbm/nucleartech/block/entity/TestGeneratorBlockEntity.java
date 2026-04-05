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

        // мощность зависит от редстоун сигнала рядом
        int redstonePower = level.getBestNeighborSignal(worldPosition);
        long genPerTick = 500L * (redstonePower + 1); // от 500 до 8000 HBM/t

        energy = Math.min(energy + genPerTick, MAX_ENERGY);

        if (level.getGameTime() % 20 == 0) {
            System.out.println("[Generator] " + worldPosition.toShortString() +
                    " energy=" + energy + " redstonePower=" + redstonePower);
        }

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
    public CableTier getOutputTier() { return CableTier.HV_RED_GOLD; }

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
