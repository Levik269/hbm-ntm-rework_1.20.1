package com.hbm.nucleartech.fluid;

public interface IFluidPipe {
    PipeMaterial getMaterial();
    FluidPressure getCurrentPressure();
    void setCurrentPressure(FluidPressure pressure);
    int getDistanceFromSource(); // сколько блоков прошла жидкость
    void setDistanceFromSource(int distance);
    /** false = вентиль закрыт, BFS не проходит */
    default boolean canFlow() { return true; }
}
