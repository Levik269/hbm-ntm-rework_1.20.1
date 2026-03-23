package com.hbm.nucleartech.energy;

public enum TransformerTier {

    // HV → MV
    HV_MV_COPPER        ("hv_mv_copper",        8000,   0.06f, CableTier.HV_COPPER,       CableTier.MV_COPPER),
    HV_MV_RED_COPPER    ("hv_mv_red_copper",     15000,  0.04f, CableTier.HV_RED_COPPER,   CableTier.MV_RED_COPPER),
    HV_MV_GOLD          ("hv_mv_gold",           25000,  0.02f, CableTier.HV_GOLD,         CableTier.MV_GOLD),
    HV_MV_RED_GOLD      ("hv_mv_red_gold",       50000,  0.01f, CableTier.HV_RED_GOLD,     CableTier.MV_RED_GOLD),
    HV_MV_GILDED_COPPER ("hv_mv_gilded_copper",  20000,  0.015f,CableTier.HV_GILDED_COPPER,CableTier.MV_GILDED_COPPER),

    // MV → LV
    MV_LV_COPPER        ("mv_lv_copper",         800,    0.06f, CableTier.MV_COPPER,       CableTier.LV_COPPER),
    MV_LV_RED_COPPER    ("mv_lv_red_copper",      1500,   0.04f, CableTier.MV_RED_COPPER,   CableTier.LV_RED_COPPER),
    MV_LV_GOLD          ("mv_lv_gold",            2500,   0.02f, CableTier.MV_GOLD,         CableTier.LV_GOLD),
    MV_LV_RED_GOLD      ("mv_lv_red_gold",        5000,   0.01f, CableTier.MV_RED_GOLD,     CableTier.LV_RED_GOLD),
    MV_LV_GILDED_COPPER ("mv_lv_gilded_copper",   2000,   0.015f,CableTier.MV_GILDED_COPPER,CableTier.LV_GILDED_COPPER);

    public final String name;
    public final long maxTransfer;
    public final float lossFactor;
    public final CableTier inputTier;
    public final CableTier outputTier;

    TransformerTier(String name, long maxTransfer, float lossFactor,
                    CableTier inputTier, CableTier outputTier) {
        this.name = name;
        this.maxTransfer = maxTransfer;
        this.lossFactor = lossFactor;
        this.inputTier = inputTier;
        this.outputTier = outputTier;
    }

    public boolean isHvToMv() { return inputTier.isHV() && outputTier.isMV(); }
    public boolean isMvToLv() { return inputTier.isMV() && outputTier.isLV(); }

    public long calculateOutput(long input) {
        return (long)(input * (1.0f - lossFactor));
    }

    public String getDisplayName() {
        return switch (this) {
            case HV_MV_COPPER        -> "HV→MV Copper";
            case HV_MV_RED_COPPER    -> "HV→MV Red Copper";
            case HV_MV_GOLD          -> "HV→MV Gold";
            case HV_MV_RED_GOLD      -> "HV→MV Red Gold";
            case HV_MV_GILDED_COPPER -> "HV→MV Gilded Copper";
            case MV_LV_COPPER        -> "MV→LV Copper";
            case MV_LV_RED_COPPER    -> "MV→LV Red Copper";
            case MV_LV_GOLD          -> "MV→LV Gold";
            case MV_LV_RED_GOLD      -> "MV→LV Red Gold";
            case MV_LV_GILDED_COPPER -> "MV→LV Gilded Copper";
        };
    }

    public static TransformerTier fromName(String name) {
        for (TransformerTier t : values())
            if (t.name.equals(name)) return t;
        return HV_MV_COPPER;
    }
}
