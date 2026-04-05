package com.hbm.nucleartech.block.entity;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.fluid.FluidPressure;
import com.hbm.nucleartech.fluid.IFluidPipe;
import com.hbm.nucleartech.fluid.PipeMaterial;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FluidValveBlockEntity extends BlockEntity implements IFluidPipe {

    private boolean open = false;
    private FluidPressure currentPressure = FluidPressure.LOW;
    private int distanceFromSource = 0;

    public FluidValveBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlocks.FLUID_VALVE_BE.get(), pos, state);
    }

    public void tick() {
        if (level == null || level.isClientSide) return;
        // редстоун открывает вентиль
        open = level.getBestNeighborSignal(worldPosition) > 0;
    }

    public boolean isOpen() { return open; }

    @Override public PipeMaterial getMaterial()                        { return PipeMaterial.STEEL; }
    @Override public FluidPressure getCurrentPressure()                { return currentPressure; }
    @Override public void setCurrentPressure(FluidPressure pressure)   { this.currentPressure = pressure; }
    @Override public int getDistanceFromSource()                       { return distanceFromSource; }
    @Override public void setDistanceFromSource(int distance)          { this.distanceFromSource = distance; }
    @Override public boolean canFlow()                                 { return open; }

    @Override public void load(CompoundTag tag) {
        super.load(tag);
        open = tag.getBoolean("open");
    }
    @Override protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("open", open);
    }
}

