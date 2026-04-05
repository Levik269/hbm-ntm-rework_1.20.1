package com.hbm.nucleartech.energy;

public enum CableTier {

    //                  name              voltage  maxCurrent  resistance(Ом/блок)
    // LV — 32V
    LV_COPPER          ("lv_copper",          32,    31,    0.08f),
    LV_RED_COPPER      ("lv_red_copper",       32,    37,    0.06f),
    LV_GOLD            ("lv_gold",             32,    46,    0.04f),
    LV_RED_GOLD        ("lv_red_gold",         32,    56,    0.03f),
    LV_GILDED_COPPER   ("lv_gilded_copper",    32,    43,    0.035f),

    // MV — 128V
    MV_COPPER          ("mv_copper",          128,    39,    0.08f),
    MV_RED_COPPER      ("mv_red_copper",      128,    62,    0.06f),
    MV_GOLD            ("mv_gold",            128,    93,    0.04f),
    MV_RED_GOLD        ("mv_red_gold",        128,   117,    0.03f),
    MV_GILDED_COPPER   ("mv_gilded_copper",   128,    85,    0.035f),

    // HV — 512V
    HV_COPPER          ("hv_copper",          512,    39,    0.08f),
    HV_RED_COPPER      ("hv_red_copper",      512,    78,    0.06f),
    HV_GOLD            ("hv_gold",            512,   136,    0.04f),
    HV_RED_GOLD        ("hv_red_gold",        512,   195,    0.03f),
    HV_GILDED_COPPER   ("hv_gilded_copper",   512,   117,    0.035f),

    CONNECTOR_COPPER   ("connector_copper",    32,    62,    0.05f),
    UNIVERSAL          ("universal",          512,   195,    0.0f);

    public final String name;
    public final int voltage;        // напряжение тира (В)
    public final int maxCurrent;     // максимальный ток (А)
    public final float resistance;   // сопротивление на блок (Ом/блок)
    public final long maxTransfer;   // макс мощность = U × I_max (Вт)

    CableTier(String name, int voltage, int maxCurrent, float resistance) {
        this.name = name;
        this.voltage = voltage;
        this.maxCurrent = maxCurrent;
        this.resistance = resistance;
        this.maxTransfer = (long) voltage * maxCurrent;
    }

    // ток при данной мощности: I = P / U
    public float currentAt(long power) {
        return voltage > 0 ? (float) power / voltage : 0;
    }

    // потери на длину: P_loss = I² × R × length
    public long calculateLoss(long power, int length) {
        float current = currentAt(power);
        return (long)(current * current * resistance * length);
    }

    // lossFactor для совместимости с HbmEnergyNetwork
    // на 1 блок: loss/power = I²R / (U×I) = I×R/U
    public float lossFactorAt(long power) {
        float current = currentAt(power);
        return voltage > 0 ? (current * resistance) / voltage : 0;
    }

    public boolean isOverloaded(long power) {
        return currentAt(power) > maxCurrent;
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

    public boolean canConnectTo(CableTier other) {
        if (this == UNIVERSAL || other == UNIVERSAL) return true;
        // connector свободно соединяется с другими connector и с LV-кабелями/машинами
        if (this == CONNECTOR_COPPER) return other == CONNECTOR_COPPER || other.isLV();
        // LV-сеть подстанции НЕ видит connector (однонаправленная совместимость)
        if (other == CONNECTOR_COPPER) return false;
        if (this.isHV()) return other.isHV();
        if (this.isMV()) return other.isMV();
        return other.isLV();
    }

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
            case CONNECTOR_COPPER -> "Connector Copper";
            case UNIVERSAL        -> "Universal";
        };
    }

    public static CableTier fromName(String name) {
        for (CableTier t : values())
            if (t.name.equals(name)) return t;
        return LV_COPPER;
    }
}