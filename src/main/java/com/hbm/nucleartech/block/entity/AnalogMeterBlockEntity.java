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

    long currentLoad = 0;
    private long flowAccumulator = 0;
    long avgFlow = 0;

    public AnalogMeterBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlocks.ANALOG_METER_BE.get(), pos, state);
    }

    @Override public CableTier getCableTier()          { return CableTier.UNIVERSAL; }
    @Override public long getCurrentLoad()              { return currentLoad; }
    @Override public void setCurrentLoad(long load)    { currentLoad = load; }

    public void tick() {
        if (level == null || level.isClientSide) return;

        // накапливаем нагрузку
        flowAccumulator += currentLoad;

        if (level.getGameTime() % 20 == 0 && level instanceof ServerLevel serverLevel) {
            avgFlow = flowAccumulator / 20;
            flowAccumulator = 0;

            List<HbmEnergyNetwork.NetworkNode> nodes =
                    HbmEnergyNetwork.findNetwork(serverLevel, worldPosition, CableTier.HV_RED_GOLD);

            long totalProviders = nodes.stream().filter(n -> n.provider() != null).count();
            long totalConsumers = nodes.stream().filter(n -> n.consumer() != null).count();
            long totalCables   = nodes.stream().filter(n -> n.cable() != null).count();

            // считаем суммарную мощность провайдеров за последний тик
            long totalGenerated = nodes.stream()
                    .filter(n -> n.provider() != null)
                    .mapToLong(n -> {
                        BlockEntity be = serverLevel.getBlockEntity(n.pos());
                        if (be instanceof IHbmEnergy.Provider p)
                            return p.getEnergyStored();
                        return 0;
                    }).sum();

            float current = CableTier.HV_RED_GOLD.currentAt(avgFlow);

            System.out.println("[AnalogMeter] " + worldPosition.toShortString() +
                    " | avgFlow=" + avgFlow + " HBM/t" +
                    " | U=" + CableTier.HV_RED_GOLD.voltage + "V" +
                    " | I=" + String.format("%.2f", current) + "/" + CableTier.HV_RED_GOLD.maxCurrent + "A" +
                    " | providers=" + totalProviders +
                    " | consumers=" + totalConsumers +
                    " | cables=" + totalCables +
                    " | storedEnergy=" + totalGenerated + " HBM");
        }
    }
}
