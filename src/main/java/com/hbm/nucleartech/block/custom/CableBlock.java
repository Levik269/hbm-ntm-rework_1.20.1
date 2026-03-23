package com.hbm.nucleartech.block.custom;

import com.hbm.nucleartech.block.entity.CableBlockEntity;
import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.energy.CableTier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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

public class CableBlock extends BaseEntityBlock {

    private static final VoxelShape SHAPE = Block.box(4, 4, 4, 12, 12, 12);
    private final CableTier tier;

    public CableBlock(CableTier tier, BlockBehaviour.Properties properties) {
        super(properties);
        this.tier = tier;
    }

    public CableTier getTier() { return tier; }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level,
                                BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CableBlockEntity(pos, state, tier);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                    BlockEntityType<T> type) {
        if (level.isClientSide) return null;
        return createTickerHelper(type, RegisterBlocks.CABLE_BE.get(),
                (lvl, pos, st, be) -> be.tick());
    }
}
