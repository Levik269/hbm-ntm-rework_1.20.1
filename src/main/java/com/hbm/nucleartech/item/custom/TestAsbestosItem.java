package com.hbm.nucleartech.item.custom;

import com.hbm.nucleartech.capability.HbmCapabilities;
import com.hbm.nucleartech.interfaces.IEntityCapabilityBase;
import com.hbm.nucleartech.modules.ItemHazardModule;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import com.hbm.nucleartech.interfaces.IItemHazard;
import net.minecraft.world.level.Level;
import com.hbm.nucleartech.handler.AsbestosSystemNT;

public class TestAsbestosItem extends Item implements IItemHazard {

    private final ItemHazardModule module;

    public TestAsbestosItem(Properties props) {
        super(props);

        this.module = new ItemHazardModule();
        this.module.addAsbestos(0.2F);
    }

    @Override
    public ItemHazardModule getModule() {
        return module;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!(entity instanceof ServerPlayer player) || level.isClientSide)
            return;

        double add = module.getAsbestosLevel() * stack.getCount();

        // добавляем только за этот тик, не весь накопленный
        AsbestosSystemNT.addAsbestosToPlayer(player, (float) add);

        // вывод для дебага
        float totalAsb = HbmCapabilities.getData(player).getValue(IEntityCapabilityBase.Type.ASBESTOS);
        //System.out.println("[Debug] Player " + player.getName().getString() + " has asbestos level: " + totalAsb);
    }
}