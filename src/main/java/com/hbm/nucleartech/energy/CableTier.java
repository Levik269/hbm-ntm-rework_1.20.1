package com.hbm.nucleartech.energy;

public enum CableTier {

    //                 название              макс ток               потери
    // === LV (низкий вольтаж) ===
    LV_COPPER ("lv_copper",  1000,       0.0015f),  // ~15% на 100 блоков
    LV_RED_COPPER("lv_red_copper", 1200, 0.0012f),
    LV_GOLD   ("lv_gold",    1500,       0.0009f),   // ~9% на 100 блоков
    LV_RED_GOLD("lv_red_gold", 1800,     0.0010f),
    LV_GILDED_COPPER("lv_gilded_copper", 1400, 0.0011f),

    // === MV ===
    MV_COPPER ("mv_copper",  5000,       0.0012f),
    MV_RED_COPPER("mv_red_copper", 8000, 0.0008f),
    MV_GOLD   ("mv_gold",    12000,      0.0004f),
    MV_RED_GOLD("mv_red_gold", 15000,    0.0006f),
    MV_GILDED_COPPER("mv_gilded_copper", 11000, 0.0007f),

    // === HV ===
    HV_COPPER ("hv_copper",  20000,      0.0010f),
    HV_RED_COPPER("hv_red_copper", 40000, 0.008f),
    HV_GOLD   ("hv_gold",    70000,      0.0001f),
    HV_RED_GOLD("hv_red_gold", 100000,   0.0003f), // ~2.5% на 100 блоков
    HV_GILDED_COPPER("hv_gilded_copper", 60000, 0.0004f);

    public final String name;
    public final long maxTransfer;
    public final float lossFactor;

    CableTier(String name, long maxTransfer, float lossFactor) {
        this.name = name;
        this.maxTransfer = maxTransfer;
        this.lossFactor = lossFactor;
    }

    public boolean isLV() {
        return this == LV_COPPER || this == LV_RED_COPPER || this == LV_GOLD
            || this == LV_RED_GOLD || this == LV_GILDED_COPPER;
    }

    public boolean isMV() {
        return this == MV_COPPER || this == MV_RED_COPPER || this == MV_GOLD
            || this == MV_RED_GOLD || this == MV_GILDED_COPPER;
    }

    public boolean isHV() {
        return this == HV_COPPER || this == HV_RED_COPPER || this == HV_GOLD
            || this == HV_RED_GOLD || this == HV_GILDED_COPPER;
    }

    // провод ИСТОЧНИКА подключается к проводу ЦЕЛИ
    // HV источник может идти через любой провод
    // LV источник может идти только через LV провода
    public boolean canConnectTo(CableTier other) {
        if (this.isHV()) return true;           // HV проходит везде
        if (this.isMV()) return !other.isHV();  // MV идёт через LV и MV
        return other.isLV();                    // LV только через LV
    }

    public long calculateLoss(long power, int cableLength) {
        return (long)(power * lossFactor * cableLength);
    }

    public boolean isOverloaded(long power) {
        return power > maxTransfer;
    }

    // человекочитаемое название для GUI
    public String getDisplayName() {
        return switch (this) {
            case LV_COPPER        -> "LV Copper";
            case LV_RED_COPPER    -> "LV Red Copper";
            case LV_GOLD          -> "LV Gold";
            case LV_RED_GOLD      -> "LV Red Gold";
            case LV_GILDED_COPPER -> "LV Gilded Copper";
            case MV_COPPER        -> "MV Copper";
            case MV_RED_COPPER    -> "MV Red Copper";
            case MV_GOLD          -> "MV Gold";
            case MV_RED_GOLD      -> "MV Red Gold";
            case MV_GILDED_COPPER -> "MV Gilded Copper";
            case HV_COPPER        -> "HV Copper";
            case HV_RED_COPPER    -> "HV Red Copper";
            case HV_GOLD          -> "HV Gold";
            case HV_RED_GOLD      -> "HV Red Gold";
            case HV_GILDED_COPPER -> "HV Gilded Copper";
        };
    }

    // тир как строка для сохранения в NBT
    public static CableTier fromName(String name) {
        for (CableTier tier : values())
            if (tier.name.equals(name)) return tier;
        return LV_COPPER;
    }
}
