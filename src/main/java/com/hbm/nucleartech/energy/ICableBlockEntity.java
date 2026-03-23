package com.hbm.nucleartech.energy;

public interface ICableBlockEntity {
    CableTier getCableTier();
    long getCurrentLoad();
    void setCurrentLoad(long load);
}

