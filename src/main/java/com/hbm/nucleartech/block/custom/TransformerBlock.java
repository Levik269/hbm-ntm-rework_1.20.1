package com.hbm.nucleartech.block.custom;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.block.entity.TransformerBlockEntity;
import com.hbm.nucleartech.energy.TransformerTier;
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

public class TransformerBlock extends BaseEntityBlock {

    private final TransformerTier tier;

    public TransformerBlock(TransformerTier tier, BlockBehaviour.Properties props) {
        super(props);
        this.tier = tier;
    }

    public TransformerTier getTier() { return tier; }

    @Nullable @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TransformerBlockEntity(pos, state, tier);
    }

    @Override public RenderShape getRenderShape(BlockState state) { return RenderShape.MODEL; }

    @Nullable @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                    BlockEntityType<T> type) {
        if (level.isClientSide) return null;
        return createTickerHelper(type, RegisterBlocks.TRANSFORMER_BE.get(),
                (lvl, pos, st, be) -> be.tick());
    }
}
