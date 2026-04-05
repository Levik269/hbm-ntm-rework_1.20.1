package com.hbm.nucleartech.fluid;

public interface IFluidConsumer {
    long receiveFluid(long maxReceive, FluidPressure pressure, boolean simulate);
    long getFluidStored();
    long getMaxFluidStored();
    boolean acceptsFluid(String fluidType);
}
