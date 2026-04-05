package com.hbm.nucleartech.energy;

public class LvTransformerTier {
    public static final CableTier INPUT_TIER  = CableTier.LV_RED_GOLD;  // любой LV
    public static final CableTier OUTPUT_TIER = CableTier.CONNECTOR_COPPER;    // коннекторные провода и LV кабели к машинам
    public static final float LOSS_FACTOR     = 0.05f;
    public static final long MAX_TRANSFER     = 5000;
}
