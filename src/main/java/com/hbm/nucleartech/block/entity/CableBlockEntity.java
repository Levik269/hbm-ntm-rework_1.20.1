package com.hbm.nucleartech.block.entity;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.energy.CableTier;
import com.hbm.nucleartech.energy.ICableBlockEntity;
import com.hbm.nucleartech.energy.IHbmEnergy;
import com.hbm.nucleartech.energy.HbmEnergyNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CableBlockEntity extends BlockEntity implements ICableBlockEntity {

    private final CableTier tier;
    private long currentLoad = 0;

    public CableBlockEntity(BlockPos pos, BlockState state, CableTier tier) {
        super(RegisterBlocks.CABLE_BE.get(), pos, state);
        this.tier = tier;
    }

    public void tick() {
        if (level == null || level.isClientSide) return;

        // провод сам по себе не тикает логику —
        // энергия проходит через HbmEnergyNetwork
        // но мы сбрасываем нагрузку каждый тик
        currentLoad = 0;

        // перегрев — если нагрузка была слишком высокой
        if (currentLoad > tier.maxTransfer) {
            // провод горит
            level.setBlock(worldPosition,
                    net.minecraft.world.level.block.Blocks.FIRE.defaultBlockState(), 3);
        }
    }

    @Override
    public CableTier getCableTier() { return tier; }

    @Override
    public long getCurrentLoad() { return currentLoad; }

    @Override
    public void setCurrentLoad(long load) { this.currentLoad = load; }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        currentLoad = tag.getLong("currentLoad");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putLong("currentLoad", currentLoad);
    }
}
