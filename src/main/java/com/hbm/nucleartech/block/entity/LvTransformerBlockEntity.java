package com.hbm.nucleartech.block.entity;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.energy.CableTier;
import com.hbm.nucleartech.energy.HbmEnergyNetwork;
import com.hbm.nucleartech.energy.IHbmEnergy;
import com.hbm.nucleartech.energy.LvTransformerTier;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LvTransformerBlockEntity extends BlockEntity
        implements IHbmEnergy.Consumer, IHbmEnergy.Provider {

    private long inputBuffer  = 0;
    private long outputBuffer = 0;
    private static final long BUFFER_SIZE = 20000;

    public LvTransformerBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlocks.LV_TRANSFORMER_BE.get(), pos, state);
    }

    public void tick() {
        if (level == null || level.isClientSide) return;

        if (inputBuffer > 0) {
            long toConvert = Math.min(inputBuffer, LvTransformerTier.MAX_TRANSFER);
            long converted = (long)(toConvert * (1.0f - LvTransformerTier.LOSS_FACTOR));
            inputBuffer  -= toConvert;
            outputBuffer  = Math.min(outputBuffer + converted, BUFFER_SIZE);
            setChanged();
        }

        if (outputBuffer > 0 && level instanceof ServerLevel sl)
            HbmEnergyNetwork.distribute(sl, worldPosition, this);
    }

    @Override public long receiveEnergy(long max, boolean sim) {
        // принимаем только LV
        long received = Math.min(BUFFER_SIZE - inputBuffer, Math.min(max, LvTransformerTier.MAX_TRANSFER));
        if (!sim) { inputBuffer += received; setChanged(); }
        return received;
    }
    @Override public long extractEnergy(long max, boolean sim) {
        long extracted = Math.min(outputBuffer, max);
        if (!sim) { outputBuffer -= extracted; setChanged(); }
        return extracted;
    }
    @Override public long getEnergyStored()    { return outputBuffer; }
    @Override public long getMaxEnergyStored() { return BUFFER_SIZE; }
    @Override public CableTier getInputTier()  { return LvTransformerTier.INPUT_TIER; }
    @Override public CableTier getOutputTier() { return LvTransformerTier.OUTPUT_TIER; }

    public long getInputBuffer()  { return inputBuffer; }
    public long getOutputBuffer() { return outputBuffer; }

    @Override public void load(CompoundTag tag) {
        super.load(tag);
        inputBuffer  = tag.getLong("inputBuffer");
        outputBuffer = tag.getLong("outputBuffer");
    }
    @Override protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putLong("inputBuffer",  inputBuffer);
        tag.putLong("outputBuffer", outputBuffer);
    }
}
