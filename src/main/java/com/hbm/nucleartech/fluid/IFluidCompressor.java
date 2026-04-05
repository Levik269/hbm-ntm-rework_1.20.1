package com.hbm.nucleartech.fluid;

public interface IFluidCompressor {
    FluidPressure getInputPressure();
    FluidPressure getOutputPressure();
    boolean isActive(); // работает только если есть энергия
}
