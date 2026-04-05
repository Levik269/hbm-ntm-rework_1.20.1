package com.hbm.nucleartech.block.entity;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.fluid.FluidPressure;
import com.hbm.nucleartech.fluid.IFluidPipe;
import com.hbm.nucleartech.fluid.PipeMaterial;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PipeBlockEntity extends BlockEntity implements IFluidPipe {

    private final PipeMaterial material;
    private FluidPressure currentPressure = FluidPressure.LOW;
    private int distanceFromSource = 0;

    public PipeBlockEntity(BlockPos pos, BlockState state, PipeMaterial material) {
        super(RegisterBlocks.PIPE_BE.get(), pos, state);
        this.material = material;
    }

    @Override public PipeMaterial getMaterial() { return material; }
    @Override public FluidPressure getCurrentPressure() { return currentPressure; }
    @Override public void setCurrentPressure(FluidPressure pressure) { this.currentPressure = pressure; }
    @Override public int getDistanceFromSource() { return distanceFromSource; }
    @Override public void setDistanceFromSource(int distance) { this.distanceFromSource = distance; }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        currentPressure = FluidPressure.fromName(tag.getString("pressure"));
        distanceFromSource = tag.getInt("distance");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putString("pressure", currentPressure.name);
        tag.putInt("distance", distanceFromSource);
    }
}
