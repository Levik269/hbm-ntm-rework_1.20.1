package com.hbm.nucleartech.block.entity;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.energy.CableTier;
import com.hbm.nucleartech.energy.HbmEnergyNetwork;
import com.hbm.nucleartech.energy.IHbmEnergy;
import com.hbm.nucleartech.energy.SubstationType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SubstationBlockEntity extends BlockEntity
        implements IHbmEnergy.Consumer, IHbmEnergy.Provider {

    private final SubstationType type;
    private long inputBuffer = 0;
    private long outputBuffer = 0;
    private static final long BUFFER_SIZE = 100000;
    private int overloadTicks = 0;

    public SubstationBlockEntity(BlockPos pos, BlockState state, SubstationType type) {
        super(RegisterBlocks.SUBSTATION_BE.get(), pos, state);
        this.type = type;
    }

    public void tick() {
        if (level == null || level.isClientSide) return;

        if (inputBuffer > type.maxTransfer) {
            overloadTicks++;
            System.out.println("[Substation] OVERLOAD at " + worldPosition.toShortString() +
                    " | buffer=" + inputBuffer + " max=" + type.maxTransfer +
                    " | ticks=" + overloadTicks);
        } else {
            overloadTicks = Math.max(0, overloadTicks - 1);
        }

        // взрыв после 40 тиков перегрузки (2 секунды)
        if (overloadTicks >= 40) {
            level.explode(null,
                    worldPosition.getX() + 0.5,
                    worldPosition.getY() + 0.5,
                    worldPosition.getZ() + 0.5,
                    5.0f, net.minecraft.world.level.Level.ExplosionInteraction.BLOCK);
            return;
        }

        if (inputBuffer > 0) {
            long toConvert = Math.min(inputBuffer, type.maxTransfer);
            long converted = (long)(toConvert * (1.0f - type.lossFactor));
            inputBuffer -= toConvert;
            outputBuffer = Math.min(outputBuffer + converted, BUFFER_SIZE);
            setChanged();
        }

        if (outputBuffer > 0 && level instanceof ServerLevel serverLevel) {
            HbmEnergyNetwork.distribute(serverLevel, worldPosition, this);
        }
    }

    @Override
    public long receiveEnergy(long maxReceive, boolean simulate) {
        // критическое нарушение тира — мгновенный взрыв
        if (maxReceive > type.maxTransfer * 5 && level != null && !level.isClientSide) {
            level.explode(null,
                    worldPosition.getX() + 0.5,
                    worldPosition.getY() + 0.5,
                    worldPosition.getZ() + 0.5,
                    6.0f, net.minecraft.world.level.Level.ExplosionInteraction.BLOCK);
            return 0;
        }
        long space = BUFFER_SIZE - inputBuffer;
        long received = Math.min(space, Math.min(maxReceive, type.maxTransfer));
        if (!simulate) { inputBuffer += received; setChanged(); }
        return received;
    }



    @Override
    public long extractEnergy(long maxExtract, boolean simulate) {
        long extracted = Math.min(outputBuffer, maxExtract);
        if (!simulate) { outputBuffer -= extracted; setChanged(); }
        return extracted;
    }

    @Override public long getEnergyStored() { return outputBuffer; }
    @Override public long getMaxEnergyStored() { return BUFFER_SIZE; }
    @Override public CableTier getInputTier() { return type.inputTier; }
    @Override public CableTier getOutputTier() { return type.outputTier; }

    public SubstationType getSubstationType() { return type; }
    public long getInputBuffer() { return inputBuffer; }
    public long getOutputBuffer() { return outputBuffer; }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        inputBuffer = tag.getLong("inputBuffer");
        outputBuffer = tag.getLong("outputBuffer");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putLong("inputBuffer", inputBuffer);
        tag.putLong("outputBuffer", outputBuffer);
    }
}
