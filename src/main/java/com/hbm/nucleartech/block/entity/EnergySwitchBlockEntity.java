package com.hbm.nucleartech.block.entity;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.energy.CableTier;
import com.hbm.nucleartech.energy.IHbmEnergy;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import com.hbm.nucleartech.energy.HbmEnergyNetwork;
import net.minecraft.server.level.ServerLevel;

public class EnergySwitchBlockEntity extends BlockEntity
        implements IHbmEnergy.Consumer, IHbmEnergy.Provider {

    private long buffer = 0;
    private boolean powered = false;
    private static final long BUFFER_SIZE = 500000;

    public EnergySwitchBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlocks.ENERGY_SWITCH_BE.get(), pos, state);
    }

    public void tick() {
        if (level == null || level.isClientSide) return;
        powered = level.getBestNeighborSignal(worldPosition) > 0;
        if (!powered) { buffer = 0; return; }
        if (buffer > 0 && level instanceof ServerLevel sl)
            HbmEnergyNetwork.distribute(sl, worldPosition, this);
    }

    public boolean isPowered() { return powered; }

    @Override
    public long receiveEnergy(long maxReceive, boolean simulate) {
        if (!powered) return 0; // закрыт
        long space = BUFFER_SIZE - buffer;
        long received = Math.min(space, maxReceive);
        if (!simulate) { buffer += received; setChanged(); }
        return received;
    }

    @Override
    public long extractEnergy(long maxExtract, boolean simulate) {
        if (!powered) return 0; // закрыт
        long extracted = Math.min(buffer, maxExtract);
        if (!simulate) { buffer -= extracted; setChanged(); }
        return extracted;
    }

    @Override public long getEnergyStored() { return buffer; }
    @Override public long getMaxEnergyStored() { return BUFFER_SIZE; }
    // универсальный — принимает любой тир
    @Override public CableTier getInputTier()  { return CableTier.UNIVERSAL; }
    @Override public CableTier getOutputTier() { return CableTier.UNIVERSAL; }


    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        buffer = tag.getLong("buffer");
        powered = tag.getBoolean("powered");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putLong("buffer", buffer);
        tag.putBoolean("powered", powered);
    }
}
