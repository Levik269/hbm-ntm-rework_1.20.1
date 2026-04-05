package com.hbm.nucleartech.block.custom;

import net.minecraft.world.item.ItemStack;
import com.hbm.nucleartech.block.entity.MissileLauncherBlockEntity;
import com.hbm.nucleartech.block.RegisterBlocks;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkHooks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class MissileLauncherBlock extends BaseEntityBlock {

    public MissileLauncherBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MissileLauncherBlockEntity(pos, state);
    }

    // редстоун сигнал — запуск
    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos,
                                net.minecraft.world.level.block.Block block,
                                BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide && level.hasNeighborSignal(pos)) {
            if (level.getBlockEntity(pos) instanceof MissileLauncherBlockEntity launcher) {
                launcher.launch();
            }
        }
    }

    // правый клик — показываем статус
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS;

        ItemStack heldItem = player.getItemInHand(hand);

        // если держит линкер — пропускаем, пусть линкер сам обработает
        if (heldItem.getItem() instanceof com.hbm.nucleartech.item.custom.MissileLinkerItem) {
            return InteractionResult.PASS;
        }

        if (level.getBlockEntity(pos) instanceof MissileLauncherBlockEntity launcher) {
            if (player instanceof ServerPlayer serverPlayer) {
                NetworkHooks.openScreen(serverPlayer, launcher, buf -> {
                    buf.writeBlockPos(pos);
                    buf.writeDouble(launcher.getTargetX());
                    buf.writeDouble(launcher.getTargetY());
                    buf.writeDouble(launcher.getTargetZ());
                });
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                  BlockEntityType<T> type) {
        return createTickerHelper(type, RegisterBlocks.MISSILE_LAUNCHER_BE.get(),
                (lvl, pos, st, be) -> be.tick());
    }
}