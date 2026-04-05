package com.hbm.nucleartech.fluid;

public interface IFluidProvider {
    long extractFluid(long maxExtract, boolean simulate);
    long getFluidStored();
    FluidPressure getOutputPressure();
    String getFluidType(); // тип жидкости (water, oil, etc.)
}
