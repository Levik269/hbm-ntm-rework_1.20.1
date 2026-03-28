package com.hbm.nucleartech.block.entity;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.energy.CableTier;
import com.hbm.nucleartech.energy.HbmEnergyNetwork;
import com.hbm.nucleartech.energy.ICableBlockEntity;
import com.hbm.nucleartech.energy.IHbmEnergy;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class AnalogMeterBlockEntity extends BlockEntity implements ICableBlockEntity {

    private long currentLoad = 0;
    private int overloadTicks = 0;

    public AnalogMeterBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlocks.ANALOG_METER_BE.get(), pos, state);
    }

    public void tick() {
        if (level == null || level.isClientSide) return;
        // логируем каждые 20 тиков (1 секунда)
        if (level.getGameTime() % 20 == 0 && level instanceof ServerLevel serverLevel) {
            List<HbmEnergyNetwork.NetworkNode> nodes =
                    HbmEnergyNetwork.findNetwork(serverLevel, worldPosition, CableTier.HV_RED_GOLD);

            long totalProviders = nodes.stream().filter(n -> n.provider() != null).count();
            long totalConsumers = nodes.stream().filter(n -> n.consumer() != null).count();
            long totalCables   = nodes.stream().filter(n -> n.cable() != null).count();

            long totalEnergy = nodes.stream()
                    .filter(n -> n.provider() != null)
                    .mapToLong(n -> n.provider().getEnergyStored())
                    .sum();

            System.out.println("[AnalogMeter] " + worldPosition.toShortString() +
                    " | load=" + currentLoad + " HBM/t" +
                    " | providers=" + totalProviders +
                    " | consumers=" + totalConsumers +
                    " | cables=" + totalCables +
                    " | totalEnergy=" + totalEnergy + " HBM");
        }
    }

    @Override public CableTier getCableTier() { return CableTier.HV_RED_GOLD; }
    @Override public long getCurrentLoad() { return currentLoad; }
    @Override public void setCurrentLoad(long load) { this.currentLoad = load; }
    public int getOverloadTicks() { return overloadTicks; }
}
