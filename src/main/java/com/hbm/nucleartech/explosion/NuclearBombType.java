package com.hbm.nucleartech.explosion;

public class NuclearBombType {

    public final String name;
    public final float radius;        // радиус воронки
    public final float scaleH;        // горизонтальный масштаб лучей
    public final float scaleV;        // вертикальный масштаб лучей
    public final int rayCount;        // количество лучей
    public final float hardThreshold; // порог прочного блока
    public final float decayRate;     // скорость затухания энергии (0.5=далеко, 1.5=близко)
    public final float smoothRadius;

    public NuclearBombType(String name, float radius, float scaleH, float scaleV,
                           int rayCount, float hardThreshold, float decayRate, float smoothRadius) {
        this.name = name;
        this.radius = radius;
        this.scaleH = scaleH;
        this.scaleV = scaleV;
        this.rayCount = rayCount;
        this.hardThreshold = hardThreshold;
        this.decayRate = decayRate;
        this.smoothRadius = smoothRadius;
    }

    // --- Готовые типы бомб ---

    // Маленькая тактическая — глубокая воронка, небольшой радиус
    public static final NuclearBombType TACTICAL = new NuclearBombType(
            "tactical",
            40f,   // радиус
            0.6f,  // горизонталь
            1.2f,  // вертикаль — вытянута вниз
            4096,  // лучей
            15.0f, // порог
            0.8f,   // затухание
            1.3f
    );

    // Стандартная ядерная бомба — широкий эллипсоид
    public static final NuclearBombType STANDARD = new NuclearBombType(
            "standard",
            80f,
            1.0f,
            0.3f,
            16384,
            15.0f,
            0.75f,
            1.3f
    );

    // Термоядерная — огромная, почти плоская
    public static final NuclearBombType THERMONUCLEAR = new NuclearBombType(
            "thermonuclear",
            200f,
            1.0f,
            0.15f,
            32768,
            15.0f,
            0.6f,
            1.3f
    );

    // Бункерная бомба — узкая и глубокая, пробивает землю
    public static final NuclearBombType BUNKER_BUSTER = new NuclearBombType(
            "bunker_buster",
            30f,
            0.2f,  // узкая по горизонтали
            2.0f,  // глубокая по вертикали
            8192,
            50.0f, // ломает даже прочные блоки
            0.5f,
            1.3f
    );

    // Кобальтовая — широкая, слабая разрушительная сила но огромный радиус
    public static final NuclearBombType COBALT = new NuclearBombType(
            "cobalt",
            60f,
            1.5f,
            0.4f,
            16384,
            10.0f,
            0.6f,   // медленное затухание = далеко летят лучи
            1.3f
    );
}
