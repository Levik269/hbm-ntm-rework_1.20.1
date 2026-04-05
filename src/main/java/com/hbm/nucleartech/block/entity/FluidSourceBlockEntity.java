package com.hbm.nucleartech.block.entity;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.fluid.FluidPipeNetwork;
import com.hbm.nucleartech.fluid.FluidPressure;
import com.hbm.nucleartech.fluid.IFluidProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FluidSourceBlockEntity extends BlockEntity implements IFluidProvider {

    private long fluid = 0;
    private static final long MAX_FLUID = 100000;
    private static final long GEN_PER_TICK = 200;
    private final String fluidType;

    public FluidSourceBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlocks.FLUID_SOURCE_BE.get(), pos, state);
        this.fluidType = "water";
    }

    public void tick() {
        if (level == null || level.isClientSide) return;
        fluid = Math.min(fluid + GEN_PER_TICK, MAX_FLUID);

        if (level.getGameTime() % 5 == 0 && fluid > 0) {
            FluidPipeNetwork.distribute((ServerLevel) level, worldPosition, this);
        }
    }

    @Override
    public long extractFluid(long maxExtract, boolean simulate) {
        long extracted = Math.min(fluid, maxExtract);
        if (!simulate) { fluid -= extracted; setChanged(); }
        return extracted;
    }

    @Override public long getFluidStored() { return fluid; }
    @Override public FluidPressure getOutputPressure() { return FluidPressure.LOW; }
    @Override public String getFluidType() { return fluidType; }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        fluid = tag.getLong("fluid");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putLong("fluid", fluid);
    }
}
