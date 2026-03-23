package com.hbm.nucleartech.block.entity;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.energy.CableTier;
import com.hbm.nucleartech.energy.HbmEnergyNetwork;
import com.hbm.nucleartech.energy.IHbmEnergy;
import com.hbm.nucleartech.energy.TransformerTier;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TransformerBlockEntity extends BlockEntity
        implements IHbmEnergy.Consumer, IHbmEnergy.Provider {

    private final TransformerTier tier;
    private long inputBuffer = 0;
    private long outputBuffer = 0;
    private static final long BUFFER_SIZE = 10000;

    public TransformerBlockEntity(BlockPos pos, BlockState state, TransformerTier tier) {
        super(RegisterBlocks.TRANSFORMER_BE.get(), pos, state);
        this.tier = tier;
    }

    public void tick() {
        if (level == null || level.isClientSide) return;

        // конвертируем входной буфер в выходной с потерями
        if (inputBuffer > 0) {
            long toConvert = Math.min(inputBuffer, tier.maxTransfer);
            long converted = tier.calculateOutput(toConvert);
            inputBuffer -= toConvert;
            outputBuffer = Math.min(outputBuffer + converted, BUFFER_SIZE);
            setChanged();
        }

        // раздаём выходную энергию в сеть
        if (outputBuffer > 0 && level instanceof ServerLevel serverLevel) {
            HbmEnergyNetwork.distribute(serverLevel, worldPosition, this);
        }
    }

    // Consumer (входная сторона — принимает высокий тир)
    @Override
    public long receiveEnergy(long maxReceive, boolean simulate) {
        long space = BUFFER_SIZE - inputBuffer;
        long received = Math.min(space, Math.min(maxReceive, tier.maxTransfer));
        if (!simulate) { inputBuffer += received; setChanged(); }
        return received;
    }

    @Override
    public long getEnergyStored() { return outputBuffer; }

    @Override
    public long getMaxEnergyStored() { return BUFFER_SIZE; }

    @Override
    public CableTier getInputTier() { return tier.inputTier; }

    // Provider (выходная сторона — отдаёт низкий тир)
    @Override
    public long extractEnergy(long maxExtract, boolean simulate) {
        long extracted = Math.min(outputBuffer, maxExtract);
        if (!simulate) { outputBuffer -= extracted; setChanged(); }
        return extracted;
    }

    @Override
    public CableTier getOutputTier() { return tier.outputTier; }

    public TransformerTier getTransformerTier() { return tier; }
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
