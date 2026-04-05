package com.hbm.nucleartech.block.custom;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.block.entity.CableBlockEntity;
import com.hbm.nucleartech.energy.CableTier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ConnectorCableBlock extends BaseEntityBlock {

    public ConnectorCableBlock(BlockBehaviour.Properties props) { super(props); }

    @Override public RenderShape getRenderShape(BlockState state) { return RenderShape.MODEL; }

    @Override public VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return Block.box(0, 0, 0, 16, 16, 16);
    }

    @Nullable @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CableBlockEntity(pos, state, CableTier.CONNECTOR_COPPER);
    }

    @Nullable @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                  BlockEntityType<T> type) {
        if (level.isClientSide) return null;
        return createTickerHelper(type, RegisterBlocks.CONNECTOR_CABLE_BE.get(),
                (lvl, pos, st, be) -> be.tick());
    }
}
