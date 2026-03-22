package com.hbm.nucleartech.item.custom;

import com.hbm.nucleartech.explosion.NuclearExplosion;
import com.hbm.nucleartech.explosion.NuclearBombType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class NukeItem extends Item {

    private final NuclearBombType bombType;

    public NukeItem(NuclearBombType bombType, Properties properties) {
        super(properties);
        this.bombType = bombType;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getLevel().isClientSide && context.getLevel() instanceof ServerLevel serverLevel) {
            double x = context.getClickedPos().getX() + 0.5;
            double y = context.getClickedPos().getY() + 1;
            double z = context.getClickedPos().getZ() + 0.5;
            NuclearExplosion.explode(serverLevel, x, y, z, bombType);
            context.getItemInHand().shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
}
