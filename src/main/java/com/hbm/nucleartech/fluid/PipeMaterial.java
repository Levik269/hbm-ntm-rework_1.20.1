package com.hbm.nucleartech.fluid;

public enum PipeMaterial {

    TITANIUM("titanium", 0.8f),  // дешевле, чуть хуже сохраняет давление
    STEEL   ("steel",    1.0f);  // дороже, лучше

    public final String name;
    // коэффициент эффективности — у стали потери меньше
    public final float efficiency;

    PipeMaterial(String name, float efficiency) {
        this.name = name;
        this.efficiency = efficiency;
    }

    // у стали давление падает медленнее
    public int effectiveRange(FluidPressure pressure) {
        return (int)(pressure.maxRange * efficiency);
    }
}
