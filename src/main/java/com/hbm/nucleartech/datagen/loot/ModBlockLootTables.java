package com.hbm.nucleartech.datagen.loot;

import com.hbm.nucleartech.block.RegisterBlocks;
import com.hbm.nucleartech.item.RegisterItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {


    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

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

        this.dropSelf(RegisterBlocks.M350_CONCRETE.get());

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
