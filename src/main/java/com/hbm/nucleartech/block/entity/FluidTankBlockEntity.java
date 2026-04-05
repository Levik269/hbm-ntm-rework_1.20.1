package com.hbm.nucleartech.block.entity;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.fluid.FluidPipeNetwork;
import com.hbm.nucleartech.fluid.FluidPressure;
import com.hbm.nucleartech.fluid.IFluidConsumer;
import com.hbm.nucleartech.fluid.IFluidProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FluidTankBlockEntity extends BlockEntity
        implements IFluidConsumer, IFluidProvider {

    private long fluid = 0;
    private String fluidType = "";
    private static final long MAX_FLUID = 500000;
    // максимальный расход за тик при активной перекачке
    private static final long MAX_PUMP_RATE = 1000;

    public FluidTankBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlocks.FLUID_TANK_BE.get(), pos, state);
    }

    public void tick() {
        if (level == null || level.isClientSide) return;
        // перекачиваем жидкость в сеть каждые 5 тиков
        if (fluid > 0 && level.getGameTime() % 5 == 0 && level instanceof ServerLevel sl) {
            FluidPipeNetwork.distribute(sl, worldPosition, this);
        }
        if (level.getGameTime() % 20 == 0) {
            System.out.println("[Tank] " + worldPosition.toShortString() +
                    " fluid=" + fluid + "/" + MAX_FLUID +
                    " type=" + fluidType);
        }
    }

    // принимает любое давление
    @Override
    public long receiveFluid(long maxReceive, FluidPressure pressure, boolean simulate) {
        long space = MAX_FLUID - fluid;
        long received = Math.min(space, maxReceive);
        if (!simulate) { fluid += received; setChanged(); }
        return received;
    }

    @Override public long getFluidStored() { return fluid; }
    @Override public long getMaxFluidStored() { return MAX_FLUID; }
    @Override public boolean acceptsFluid(String type) { return true; }

    // выдаёт всегда LOW давление, расход ограничен MAX_PUMP_RATE за вызов
    @Override
    public long extractFluid(long maxExtract, boolean simulate) {
        long extracted = Math.min(Math.min(fluid, maxExtract), MAX_PUMP_RATE);
        if (!simulate) { fluid -= extracted; setChanged(); }
        return extracted;
    }

    @Override public FluidPressure getOutputPressure() { return FluidPressure.LOW; }
    @Override public String getFluidType() { return fluidType.isEmpty() ? "water" : fluidType; }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        fluid = tag.getLong("fluid");
        fluidType = tag.getString("fluidType");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putLong("fluid", fluid);
        tag.putString("fluidType", fluidType);
    }
}
