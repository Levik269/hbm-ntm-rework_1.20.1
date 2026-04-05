package com.hbm.nucleartech.block.custom;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.block.entity.FluidTankBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class FluidTankBlock extends BaseEntityBlock {
    public FluidTankBlock(BlockBehaviour.Properties props) { super(props); }

    @Nullable @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FluidTankBlockEntity(pos, state);
    }
    @Override public RenderShape getRenderShape(BlockState s) { return RenderShape.MODEL; }

    @Nullable @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                    BlockEntityType<T> type) {
        if (level.isClientSide) return null;
        return createTickerHelper(type, RegisterBlocks.FLUID_TANK_BE.get(),
                (lvl, pos, st, be) -> be.tick());
    }
}
