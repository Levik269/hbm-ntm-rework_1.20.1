package com.hbm.nucleartech.datagen.loot;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.item.RegisterItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.SlabBlock;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {


    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }



    @Override
    protected void generate() {
        for (RegistryObject<Block> entry : RegisterBlocks.BLOCKS.getEntries()) {
            Block block = entry.get();
            dropSelf(block);
        }


        this.dropSelf(RegisterBlocks.TRANSFORMER_HV_MV_GILDED_COPPER.get());
        this.dropSelf(RegisterBlocks.TRANSFORMER_HV_MV_COPPER.get());
        this.dropSelf(RegisterBlocks.TRANSFORMER_HV_MV_RED_COPPER.get());
        this.dropSelf(RegisterBlocks.TRANSFORMER_HV_MV_GOLD.get());
        this.dropSelf(RegisterBlocks.TRANSFORMER_HV_MV_RED_GOLD.get());
        this.dropSelf(RegisterBlocks.TRANSFORMER_MV_LV_GILDED_COPPER.get());
        this.dropSelf(RegisterBlocks.TRANSFORMER_MV_LV_COPPER.get());
        this.dropSelf(RegisterBlocks.TRANSFORMER_MV_LV_RED_COPPER.get());
        this.dropSelf(RegisterBlocks.TRANSFORMER_MV_LV_GOLD.get());
        this.dropSelf(RegisterBlocks.TRANSFORMER_MV_LV_RED_GOLD.get());

        this.dropSelf(RegisterBlocks.ENERGY_SWITCH.get());
        this.dropSelf(RegisterBlocks.SUBSTATION_HV_MV.get());
        this.dropSelf(RegisterBlocks.SUBSTATION_MV_LV.get());
        this.dropSelf(RegisterBlocks.ANALOG_METER.get());

        this.dropSelf(RegisterBlocks.CABLE_LV_COPPER.get());
        this.dropSelf(RegisterBlocks.CABLE_LV_RED_COPPER.get());
        this.dropSelf(RegisterBlocks.CABLE_LV_GOLD.get());
        this.dropSelf(RegisterBlocks.CABLE_LV_RED_GOLD.get());
        this.dropSelf(RegisterBlocks.CABLE_LV_GILDED_COPPER.get());
        this.dropSelf(RegisterBlocks.CABLE_MV_COPPER.get());
        this.dropSelf(RegisterBlocks.CABLE_MV_RED_COPPER.get());
        this.dropSelf(RegisterBlocks.CABLE_MV_GOLD.get());
        this.dropSelf(RegisterBlocks.CABLE_MV_RED_GOLD.get());
        this.dropSelf(RegisterBlocks.CABLE_MV_GILDED_COPPER.get());
        this.dropSelf(RegisterBlocks.CABLE_HV_COPPER.get());
        this.dropSelf(RegisterBlocks.CABLE_HV_RED_COPPER.get());
        this.dropSelf(RegisterBlocks.CABLE_HV_GOLD.get());
        this.dropSelf(RegisterBlocks.CABLE_HV_RED_GOLD.get());
        this.dropSelf(RegisterBlocks.CABLE_HV_GILDED_COPPER.get());

        this.dropSelf(RegisterBlocks.TEST_GENERATOR.get());
        this.dropSelf(RegisterBlocks.BATTERY_BLOCK.get());

        this.dropSelf(RegisterBlocks.MISSILE_LAUNCHER.get());

        // M350 Concrete variants
        this.dropSelf(RegisterBlocks.CONCRETE.get());
        this.dropSelf(RegisterBlocks.BRICK_CONCRETE.get());
        this.dropSelf(RegisterBlocks.BRICK_CONCRETE_MOSSY.get());
        this.dropSelf(RegisterBlocks.BRICK_CONCRETE_CRACKED.get());
        this.dropSelf(RegisterBlocks.BRICK_CONCRETE_BROKEN.get());
        this.dropSelf(RegisterBlocks.BRICK_CONCRETE_MARKED.get());
        this.dropSelf(RegisterBlocks.CONCRETE_STAIRS.get());


        // M350 Colored Concrete variants
        this.dropSelf(RegisterBlocks.CONCRETE_SMOOTH.get());
        this.dropSelf(RegisterBlocks.CONCRETE_WHITE.get());
        this.dropSelf(RegisterBlocks.CONCRETE_ORANGE.get());
        this.dropSelf(RegisterBlocks.CONCRETE_MAGENTA.get());
        this.dropSelf(RegisterBlocks.CONCRETE_LIGHT_BLUE.get());
        this.dropSelf(RegisterBlocks.CONCRETE_YELLOW.get());
        this.dropSelf(RegisterBlocks.CONCRETE_LIME.get());
        this.dropSelf(RegisterBlocks.CONCRETE_PINK.get());
        this.dropSelf(RegisterBlocks.CONCRETE_GRAY.get());
        this.dropSelf(RegisterBlocks.CONCRETE_LIGHT_GRAY.get());
        this.dropSelf(RegisterBlocks.CONCRETE_CYAN.get());
        this.dropSelf(RegisterBlocks.CONCRETE_PURPLE.get());
        this.dropSelf(RegisterBlocks.CONCRETE_BLUE.get());
        this.dropSelf(RegisterBlocks.CONCRETE_BROWN.get());
        this.dropSelf(RegisterBlocks.CONCRETE_GREEN.get());
        this.dropSelf(RegisterBlocks.CONCRETE_RED.get());
        this.dropSelf(RegisterBlocks.CONCRETE_BLACK.get());

        // M350 Reinforced blocks
        this.dropSelf(RegisterBlocks.REINFORCED_STONE.get());
        this.dropSelf(RegisterBlocks.BRICK_COMPOUND.get());
        this.dropSelf(RegisterBlocks.BRICK_OBSIDIAN.get());

        // CMB Brick variants
        this.dropSelf(RegisterBlocks.CMB_BRICK.get());
        this.dropSelf(RegisterBlocks.CMB_BRICK_REINFORCED.get());

        this.dropSelf(RegisterBlocks.BLOCK_WASTE.get());
        this.dropSelf(RegisterBlocks.BLOCK_TITANIUM.get());
        this.dropSelf(RegisterBlocks.BLOCK_URANIUM.get());
        this.dropSelf(RegisterBlocks.RADIATION_DECONTAMINATOR.get());


        this.add(RegisterBlocks.ORE_TITANIUM.get(),
                block -> createOreDrop(RegisterBlocks.ORE_TITANIUM.get(), RegisterItems.RAW_TITANIUM.get()));
        this.add(RegisterBlocks.DEEPSLATE_ORE_TITANIUM.get(),
                block -> createOreDrop(RegisterBlocks.DEEPSLATE_ORE_TITANIUM.get(), RegisterItems.RAW_TITANIUM.get()));

        this.add(RegisterBlocks.ORE_URANIUM.get(),
                block -> createOreDrop(RegisterBlocks.ORE_URANIUM.get(), RegisterItems.RAW_URANIUM.get()));
        this.add(RegisterBlocks.DEEPSLATE_ORE_URANIUM.get(),
                block -> createOreDrop(RegisterBlocks.DEEPSLATE_ORE_URANIUM.get(), RegisterItems.RAW_URANIUM.get()));

        this.dropSelf(RegisterBlocks.RAD_RESISTANT_BLOCK.get());

        this.dropSelf(RegisterBlocks.WASTE_GRASS.get());

        this.add(RegisterBlocks.ORE_THORIUM.get(),
                block -> createOreDrop(RegisterBlocks.ORE_THORIUM.get(), RegisterItems.RAW_THORIUM.get()));
        this.add(RegisterBlocks.DEEPSLATE_ORE_THORIUM.get(),
                block -> createOreDrop(RegisterBlocks.DEEPSLATE_ORE_THORIUM.get(), RegisterItems.RAW_THORIUM.get()));

        this.add(RegisterBlocks.ORE_COPPER.get(),
                block -> createOreDrop(RegisterBlocks.ORE_COPPER.get(), RegisterItems.RAW_COPPER_HBM.get()));
        this.add(RegisterBlocks.DEEPSLATE_ORE_COPPER.get(),
                block -> createOreDrop(RegisterBlocks.DEEPSLATE_ORE_COPPER.get(), RegisterItems.RAW_COPPER_HBM.get()));

        this.add(RegisterBlocks.ORE_COBALT.get(),
                block -> createOreDrop(RegisterBlocks.ORE_COBALT.get(), RegisterItems.RAW_COBALT.get()));
        this.add(RegisterBlocks.DEEPSLATE_ORE_COBALT.get(),
                block -> createOreDrop(RegisterBlocks.DEEPSLATE_ORE_COBALT.get(), RegisterItems.RAW_COBALT.get()));

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {

        return RegisterBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
