package com.hbm.nucleartech.block.entity;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.fluid.FluidPressure;
import com.hbm.nucleartech.fluid.IFluidPipe;
import com.hbm.nucleartech.fluid.PipeMaterial;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AnalogBarometerBlockEntity extends BlockEntity implements IFluidPipe {

    private FluidPressure currentPressure = FluidPressure.LOW;
    private int distanceFromSource = 0;

    public AnalogBarometerBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlocks.ANALOG_BAROMETER_BE.get(), pos, state);
    }

    @Override public PipeMaterial getMaterial()                        { return PipeMaterial.STEEL; }
    @Override public FluidPressure getCurrentPressure()                { return currentPressure; }
    @Override public void setCurrentPressure(FluidPressure pressure)   { this.currentPressure = pressure; }
    @Override public int getDistanceFromSource()                       { return distanceFromSource; }
    @Override public void setDistanceFromSource(int distance)          { this.distanceFromSource = distance; }

    public void tick() {
        if (level == null || level.isClientSide) return;
        if (level.getGameTime() % 20 != 0) return;

        int pipes = 0, valves = 0, compressors = 0, tanks = 0, sources = 0;

        for (Direction dir : Direction.values()) {
            BlockEntity be = level.getBlockEntity(worldPosition.relative(dir));
            if (be instanceof PipeBlockEntity) {
                pipes++;
            } else if (be instanceof FluidValveBlockEntity) {
                valves++;
            } else if (be instanceof FluidCompressorBlockEntity) {
                compressors++;
            } else if (be instanceof FluidTankBlockEntity) {
                tanks++;
            } else if (be instanceof FluidSourceBlockEntity) {
                sources++;
            }
        }

        System.out.println("[Barometer] " + worldPosition.toShortString() +
                " | pressure=" + currentPressure.getDisplayName() +
                " | pipes=" + pipes +
                " | valves=" + valves +
                " | compressors=" + compressors +
                " | tanks=" + tanks +
                " | sources=" + sources);
    }
}
