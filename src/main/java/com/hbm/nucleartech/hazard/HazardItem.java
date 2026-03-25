package com.hbm.nucleartech.hazard;

import com.hbm.nucleartech.interfaces.IItemHazard;
import com.hbm.nucleartech.modules.ItemHazardModule;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HazardItem extends Item implements IItemHazard {
/*Example code
* public static final RegistryObject<Item> EXAMPLE_ITEM =
    ITEMS.register("example_item",
        () -> new HazardItem.Builder(new Item.Properties())
                .asbestos(1)
                .build()
    );
* */
    private final ItemHazardModule module;

    private HazardItem(Builder builder) {
        super(builder.properties);
        this.module = builder.module;
    }

    @Override
    public ItemHazardModule getModule() {
        return this.module;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

        if (!pLevel.isClientSide && pEntity instanceof LivingEntity living) {
            this.module.applyEffects(
                    living,
                    pStack.getCount(),
                    pSlotId,
                    pIsSelected,
                    living.getMainHandItem() == pStack ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND
            );
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        this.module.addInformation(pStack, pTooltipComponents, pIsAdvanced);
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        boolean m = this.module.onEntityItemUpdate(entity);
        boolean i = super.onEntityItemUpdate(stack, entity);
        return m || i;
    }

    public static class Builder {

        private final Item.Properties properties;
        private final ItemHazardModule module = new ItemHazardModule();

        public Builder(Item.Properties properties) {
            this.properties = properties;
        }

        public Builder radiation(double value) {
            module.addRadiation(value);
            return this;
        }

        public Builder digamma(double value) {
            module.addDigamma(value);
            return this;
        }

        public Builder fire(int value) {
            module.addFire(value);
            return this;
        }

        public Builder cryogenic(int value) {
            module.addCryogenic(value);
            return this;
        }

        public Builder toxic(int value) {
            module.addToxic(value);
            return this;
        }

        public Builder asbestos(int value) {
            module.addAsbestos(value);
            return this;
        }

        public Builder coal(int value) {
            module.addCoal(value);
            return this;
        }

        public Builder blinding() {
            module.addBlinding();
            return this;
        }

        public Builder hydroReactive() {
            module.addHydroReactivity();
            return this;
        }

        public Builder explosive(float value) {
            module.addExplosive(value);
            return this;
        }

        public HazardItem build() {
            return new HazardItem(this);
        }
    }
}