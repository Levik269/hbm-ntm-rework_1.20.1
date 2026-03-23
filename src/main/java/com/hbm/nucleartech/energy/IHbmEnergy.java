package com.hbm.nucleartech.energy;

public interface IHbmEnergy {

    interface Provider {
        long extractEnergy(long maxExtract, boolean simulate);
        long getEnergyStored();
        CableTier getOutputTier();
    }

    interface Consumer {
        long receiveEnergy(long maxReceive, boolean simulate);
        long getEnergyStored();
        long getMaxEnergyStored();
        CableTier getInputTier();
    }

    interface Storage extends Provider, Consumer {}
}

