package com.hbm.nucleartech.block.entity;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.energy.CableTier;
import com.hbm.nucleartech.energy.IHbmEnergy;
import com.hbm.nucleartech.fluid.FluidPipeNetwork;
import com.hbm.nucleartech.fluid.FluidPressure;
import com.hbm.nucleartech.fluid.IFluidCompressor;
import com.hbm.nucleartech.fluid.IFluidProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FluidCompressorBlockEntity extends BlockEntity
        implements IFluidCompressor, IHbmEnergy.Consumer, IFluidProvider {

    private long energyBuffer = 0;
    private static final long MAX_ENERGY = 10000;
    private static final long ENERGY_PER_TICK = 100;
    private boolean active = false;

    private long fluidBuffer = 0;
    private static final long MAX_FLUID = 2000;
    private static final long FLUID_PER_TICK = 100; // объём сжатого воздуха за тик

    public FluidCompressorBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlocks.FLUID_COMPRESSOR_BE.get(), pos, state);
    }

    public void tick() {
        if (level == null || level.isClientSide) return;

        // работает только если есть энергия
        if (energyBuffer >= ENERGY_PER_TICK) {
            energyBuffer -= ENERGY_PER_TICK;
            active = true;
            fluidBuffer = Math.min(fluidBuffer + FLUID_PER_TICK, MAX_FLUID);
            setChanged();
        } else {
            active = false;
        }

        if (fluidBuffer > 0 && active && level instanceof ServerLevel sl) {
            FluidPipeNetwork.distribute(sl, worldPosition, this);
        }

        if (level.getGameTime() % 20 == 0) {
            System.out.println("[Compressor] " + worldPosition.toShortString() +
                    " active=" + active + " energy=" + energyBuffer + " fluid=" + fluidBuffer);
        }
    }

    @Override public boolean isActive() { return active; }
    @Override public FluidPressure getInputPressure()  { return FluidPressure.LOW; }
    @Override public FluidPressure getOutputPressure() { return FluidPressure.HIGH; }

    // IFluidProvider — компрессор сам является источником сжатого воздуха
    @Override public long extractFluid(long maxExtract, boolean simulate) {
        long extracted = Math.min(fluidBuffer, maxExtract);
        if (!simulate) { fluidBuffer -= extracted; setChanged(); }
        return extracted;
    }
    @Override public long getFluidStored() { return fluidBuffer; }
    @Override public String getFluidType()  { return "compressed_air"; }

    // принимает энергию от сети
    @Override
    public long receiveEnergy(long maxReceive, boolean simulate) {
        long space = MAX_ENERGY - energyBuffer;
        long received = Math.min(space, maxReceive);
        if (!simulate) { energyBuffer += received; setChanged(); }
        return received;
    }

    @Override public long getEnergyStored() { return energyBuffer; }
    @Override public long getMaxEnergyStored() { return MAX_ENERGY; }
    @Override public CableTier getInputTier() { return CableTier.LV_COPPER; }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        energyBuffer = tag.getLong("energy");
        active = tag.getBoolean("active");
        fluidBuffer = tag.getLong("fluid");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putLong("energy", energyBuffer);
        tag.putBoolean("active", active);
        tag.putLong("fluid", fluidBuffer);
    }
}
