package com.hbm.nucleartech.item.custom;

import com.hbm.nucleartech.explosion.NuclearBombType;
import net.minecraft.world.item.Item;

public class MissileItem extends Item {

    private final NuclearBombType bombType;

    public MissileItem(NuclearBombType bombType, Properties properties) {
        super(properties);
        this.bombType = bombType;
    }

    public NuclearBombType getBombType() {
        return bombType;
    }
}
