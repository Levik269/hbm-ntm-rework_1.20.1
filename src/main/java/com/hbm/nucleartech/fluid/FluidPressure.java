package com.hbm.nucleartech.fluid;

public enum FluidPressure {

    LOW   ("low",    100, 0),
    MEDIUM("medium", 200, 1),
    HIGH  ("high",   300, 2);

    public final String name;
    public final int maxRange;   // максимальная дальность в блоках
    public final int tier;       // числовой тир для сравнения

    FluidPressure(String name, int maxRange, int tier) {
        this.name = name;
        this.maxRange = maxRange;
        this.tier = tier;
    }

    // понижаем давление на 1 блок
    public int remainingRange(int distanceTraveled) {
        return maxRange - distanceTraveled;
    }

    public boolean canReach(int distance) {
        return distance <= maxRange;
    }

    // повышаем тир
    public FluidPressure boost() {
        return switch (this) {
            case LOW    -> MEDIUM;
            case MEDIUM -> HIGH;
            case HIGH   -> HIGH; // уже макс — просто продляем жизнь
        };
    }

    public String getDisplayName() {
        return switch (this) {
            case LOW    -> "Low Pressure";
            case MEDIUM -> "Medium Pressure";
            case HIGH   -> "High Pressure";
        };
    }

    public static FluidPressure fromName(String name) {
        for (FluidPressure p : values())
            if (p.name.equals(name)) return p;
        return LOW;
    }
}

