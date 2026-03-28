package com.hbm.nucleartech.energy;

public enum SubstationType {

    HV_MV("hv_mv", 100000, 0.03f, CableTier.HV_RED_GOLD,  CableTier.MV_RED_GOLD),
    MV_LV("mv_lv", 20000,  0.03f, CableTier.MV_RED_GOLD,  CableTier.LV_RED_GOLD);

    public final String name;
    public final long maxTransfer;
    public final float lossFactor;
    public final CableTier inputTier;
    public final CableTier outputTier;

    SubstationType(String name, long maxTransfer, float lossFactor,
                   CableTier inputTier, CableTier outputTier) {
        this.name = name;
        this.maxTransfer = maxTransfer;
        this.lossFactor = lossFactor;
        this.inputTier = inputTier;
        this.outputTier = outputTier;
    }
}

