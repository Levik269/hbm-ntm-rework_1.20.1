package com.hbm.nucleartech.item.custom;

import com.hbm.nucleartech.capability.HbmCapabilities;
import com.hbm.nucleartech.interfaces.IEntityCapabilityBase;
import com.hbm.nucleartech.modules.ItemHazardModule;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import com.hbm.nucleartech.interfaces.IItemHazard;
import net.minecraft.world.level.Level;

public class DebugHazardRemover extends Item implements IItemHazard {

    private final ItemHazardModule module;

    public DebugHazardRemover(Properties props) {
        super(props);

        this.module = new ItemHazardModule();
        this.module.addAsbestos(0.9F);
    }

    @Override
    public ItemHazardModule getModule() {
        return module;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            for (IEntityCapabilityBase.Type type : IEntityCapabilityBase.Type.values()) {
                HbmCapabilities.getData(player).setValue(type, 0);
            }

        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

}