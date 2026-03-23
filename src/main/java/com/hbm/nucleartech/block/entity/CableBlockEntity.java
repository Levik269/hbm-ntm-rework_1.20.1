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
    private int overloadTicks = 0;
    private final CableTier tier;
    private long currentLoad = 0;

    public CableBlockEntity(BlockPos pos, BlockState state, CableTier tier) {
        super(RegisterBlocks.CABLE_BE.get(), pos, state);
        this.tier = tier;
    }

    public void tick() {
        if (level == null || level.isClientSide) return;

        // перегрев — проверяем после обновления нагрузки
        if (currentLoad > tier.maxTransfer * 1.5f && overloadTicks > 20) {
            if (tier.isHV()) {
                level.explode(null, worldPosition.getX() + 0.5,
                        worldPosition.getY() + 0.5, worldPosition.getZ() + 0.5,
                        3.0f, net.minecraft.world.level.Level.ExplosionInteraction.BLOCK);
            } else {
                level.setBlock(worldPosition,
                        net.minecraft.world.level.block.Blocks.FIRE.defaultBlockState(), 3);
            }
        }
    }

    @Override
    public CableTier getCableTier() { return tier; }

    @Override
    public long getCurrentLoad() { return currentLoad; }

    @Override
    public void setCurrentLoad(long load) {
        this.currentLoad = load;
        // увеличиваем тики только при перегрузке, не сбрасываем если load=0
        if (tier.isOverloaded(load)) {
            overloadTicks++;
        }
        // сбрасываем только если нагрузка явно в норме
        else if (load > 0) {
            overloadTicks = 0;
        }
        // если load=0 — не трогаем overloadTicks
    }

    public int getOverloadTicks() { return overloadTicks; }

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
