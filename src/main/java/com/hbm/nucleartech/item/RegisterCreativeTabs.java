package com.hbm.nucleartech.item;

import com.hbm.nucleartech.HBM;
import com.hbm.nucleartech.block.RegisterBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class RegisterCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HBM.MOD_ID);

    public static final RegistryObject<CreativeModeTab> NTM_RESOURCES_AND_PARTS = CREATIVE_TABS.register("resources_and_parts",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(RegisterItems.INGOT_URANIUM.get()))
                    .title(Component.translatable("creativetab.resources_and_parts"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(RegisterItems.TEST_ASBESTOS_ITEM.get());
                        output.accept(RegisterBlocks.TEST_GENERATOR.get());
                        output.accept(RegisterItems.DEBUG_HAZARD_REMOVER.get());

                        output.accept(RegisterBlocks.MISSILE_LAUNCHER.get());

                        output.accept(RegisterItems.MULTIMETER.get());
                        output.accept(RegisterItems.ADVANCED_MULTIMETER.get());
                        // Uranium
                        output.accept(RegisterItems.INGOT_URANIUM.get());
                        output.accept(RegisterItems.INGOT_U233.get());
                        output.accept(RegisterItems.INGOT_U235.get());
                        output.accept(RegisterItems.INGOT_U238.get());
                        output.accept(RegisterItems.INGOT_URANIUM_FUEL.get());
                        output.accept(RegisterItems.INGOT_U238m2.get());

                        // Plutonium
                        output.accept(RegisterItems.INGOT_PLUTONIUM.get());
                        output.accept(RegisterItems.INGOT_PU239.get());
                        output.accept(RegisterItems.INGOT_PU240.get());
                        output.accept(RegisterItems.INGOT_PU241.get());
                        output.accept(RegisterItems.INGOT_PLUTONIUM_PU_MIX.get());
                        output.accept(RegisterItems.INGOT_PLUTONIUM_FUEL.get());
                        output.accept(RegisterItems.INGOT_PU238.get());

                        // Americium
                        output.accept(RegisterItems.INGOT_AM241.get());
                        output.accept(RegisterItems.INGOT_AM242.get());
                        output.accept(RegisterItems.INGOT_AM_MIX.get());
                        output.accept(RegisterItems.INGOT_AMERICIUM_FUEL.get());

                        // Thorium / Neptunium
                        output.accept(RegisterItems.INGOT_THORIUM.get());
                        output.accept(RegisterItems.INGOT_THORIUM_FUEL.get());
                        output.accept(RegisterItems.INGOT_NEPTUNIUM.get());
                        output.accept(RegisterItems.INGOT_NEPTUNIUM_FUEL.get());

                        // Schrabidium
                        output.accept(RegisterItems.INGOT_SCHRARANIUM.get());
                        output.accept(RegisterItems.INGOT_SCHRABIDIUM.get());
                        output.accept(RegisterItems.INGOT_SCHRABIDATE.get());
                        output.accept(RegisterItems.INGOT_SCHRABIDIUM_FUEL.get());
                        output.accept(RegisterItems.INGOT_HES.get());
                        output.accept(RegisterItems.INGOT_LES.get());

                        // Hazard misc
                        output.accept(RegisterItems.INGOT_TECHNETIUM.get());
                        output.accept(RegisterItems.INGOT_MOX_FUEL.get());
                        output.accept(RegisterItems.INGOT_RA226.get());
                        output.accept(RegisterItems.INGOT_ACTINIUM.get());
                        output.accept(RegisterItems.INGOT_GH336.get());
                        output.accept(RegisterItems.INGOT_MUD.get());
                        output.accept(RegisterItems.INGOT_POLONIUM.get());
                        output.accept(RegisterItems.INGOT_PHOSPHORUS.get());
                        output.accept(RegisterItems.INGOT_CO60.get());
                        output.accept(RegisterItems.INGOT_SR90.get());
                        output.accept(RegisterItems.INGOT_I131.get());
                        output.accept(RegisterItems.INGOT_AU198.get());
                        output.accept(RegisterItems.INGOT_PB209.get());
                        output.accept(RegisterItems.INGOT_ELECTRONIUM.get());
                        output.accept(RegisterItems.INGOT_ASBESTOS.get());

                        // Normal ingots
                        output.accept(RegisterItems.INGOT_STEEL.get());
                        output.accept(RegisterItems.INGOT_TITANIUM.get());
                        output.accept(RegisterItems.INGOT_COPPER.get());
                        output.accept(RegisterItems.INGOT_RED_COPPER.get());
                        output.accept(RegisterItems.INGOT_ADVANCED_ALLOY.get());
                        output.accept(RegisterItems.INGOT_TUNGSTEN.get());
                        output.accept(RegisterItems.INGOT_ALUMINIUM.get());
                        output.accept(RegisterItems.INGOT_BERYLLIUM.get());
                        output.accept(RegisterItems.INGOT_LEAD.get());
                        output.accept(RegisterItems.INGOT_MAGNETIZED_TUNGSTEN.get());
                        output.accept(RegisterItems.INGOT_COMBINE_STEEL.get());
                        output.accept(RegisterItems.INGOT_DURA_STEEL.get());
                        output.accept(RegisterItems.INGOT_TCALLOY.get());
                        output.accept(RegisterItems.INGOT_CDALLOY.get());
                        output.accept(RegisterItems.INGOT_DESH.get());
                        output.accept(RegisterItems.INGOT_SATURNITE.get());
                        output.accept(RegisterItems.INGOT_FERROURANIUM.get());
                        output.accept(RegisterItems.INGOT_STARMETAL.get());
                        output.accept(RegisterItems.INGOT_OSMIRIDIUM.get());
                        output.accept(RegisterItems.INGOT_EUPHEMIUM.get());
                        output.accept(RegisterItems.INGOT_DINEUTRONIUM.get());
                        output.accept(RegisterItems.INGOT_CADMIUM.get());
                        output.accept(RegisterItems.INGOT_BISMUTH.get());
                        output.accept(RegisterItems.INGOT_ARSENIC.get());
                        output.accept(RegisterItems.INGOT_ZIRCONIUM.get());
                        output.accept(RegisterItems.INGOT_BISMUTH_BRONZE.get());
                        output.accept(RegisterItems.INGOT_ARSENIC_BRONZE.get());
                        output.accept(RegisterItems.INGOT_BSCCO.get());
                        output.accept(RegisterItems.INGOT_CALCIUM.get());
                        output.accept(RegisterItems.INGOT_GUNMETAL.get());
                        output.accept(RegisterItems.INGOT_WEAPONSTEEL.get());
                        output.accept(RegisterItems.INGOT_CFT.get());
                        output.accept(RegisterItems.INGOT_TENNESSINE.get());
                        output.accept(RegisterItems.INGOT_BORON.get());
                        output.accept(RegisterItems.INGOT_GRAPHITE.get());
                        output.accept(RegisterItems.INGOT_FIBERGLASS.get());
                        output.accept(RegisterItems.INGOT_NIOBIUM.get());
                        output.accept(RegisterItems.INGOT_BROMINE.get());
                        output.accept(RegisterItems.INGOT_CAESIUM.get());
                        output.accept(RegisterItems.INGOT_CERIUM.get());
                        output.accept(RegisterItems.INGOT_LANTHANIUM.get());
                        output.accept(RegisterItems.INGOT_TANTALIUM.get());
                        output.accept(RegisterItems.INGOT_ASTATINE.get());
                        output.accept(RegisterItems.INGOT_FIREBRICK.get());
                        output.accept(RegisterItems.INGOT_COBALT.get());
                        output.accept(RegisterItems.INGOT_IODINE.get());
                        output.accept(RegisterItems.INGOT_REIIUM.get());
                        output.accept(RegisterItems.INGOT_WEIDANIUM.get());
                        output.accept(RegisterItems.INGOT_AUSTRALIUM.get());
                        output.accept(RegisterItems.INGOT_VERTICIUM.get());
                        output.accept(RegisterItems.INGOT_UNOBTAINIUM.get());
                        output.accept(RegisterItems.INGOT_DAFFERGON.get());
                        output.accept(RegisterItems.INGOT_STEEL_DUSTED.get());
                        output.accept(RegisterItems.INGOT_CHAINSTEEL.get());
                        output.accept(RegisterItems.INGOT_METEORITE.get());
                        output.accept(RegisterItems.INGOT_METEORITE_FORGED.get());
                        output.accept(RegisterItems.INGOT_POLYMER.get());
                        output.accept(RegisterItems.INGOT_BAKELITE.get());
                        output.accept(RegisterItems.INGOT_RUBBER.get());
                        output.accept(RegisterItems.INGOT_BIORUBBER.get());
                        output.accept(RegisterItems.INGOT_PC.get());
                        output.accept(RegisterItems.INGOT_PVC.get());
                        output.accept(RegisterItems.INGOT_SILICON.get());
                        output.accept(RegisterItems.INGOT_RED_GOLD.get());
                        output.accept(RegisterItems.INGOT_GILDED_COPPER.get());
//---------- Billets -----------
                        output.accept(RegisterItems.BILLET_COBALT.get());
                        output.accept(RegisterItems.BILLET_SILICON.get());
                        output.accept(RegisterItems.BILLET_TH232.get());
                        output.accept(RegisterItems.BILLET_URANIUM.get());
                        output.accept(RegisterItems.BILLET_U233.get());
                        output.accept(RegisterItems.BILLET_U235.get());
                        output.accept(RegisterItems.BILLET_U238.get());
                        output.accept(RegisterItems.BILLET_PLUTONIUM.get());
                        output.accept(RegisterItems.BILLET_PU238.get());
                        output.accept(RegisterItems.BILLET_PU239.get());
                        output.accept(RegisterItems.BILLET_PU240.get());
                        output.accept(RegisterItems.BILLET_PU241.get());
                        output.accept(RegisterItems.BILLET_PU_MIX.get());
                        output.accept(RegisterItems.BILLET_AM241.get());
                        output.accept(RegisterItems.BILLET_AM242.get());
                        output.accept(RegisterItems.BILLET_AM_MIX.get());
                        output.accept(RegisterItems.BILLET_NEPTUNIUM.get());
                        output.accept(RegisterItems.BILLET_POLONIUM.get());
                        output.accept(RegisterItems.BILLET_TECHNETIUM.get());
                        output.accept(RegisterItems.BILLET_CO60.get());
                        output.accept(RegisterItems.BILLET_SR90.get());
                        output.accept(RegisterItems.BILLET_AU198.get());
                        output.accept(RegisterItems.BILLET_PB209.get());
                        output.accept(RegisterItems.BILLET_RA226.get());
                        output.accept(RegisterItems.BILLET_ACTINIUM.get());
                        output.accept(RegisterItems.BILLET_GH336.get());
                        output.accept(RegisterItems.BILLET_BERYLLIUM.get());
                        output.accept(RegisterItems.BILLET_BISMUTH.get());
                        output.accept(RegisterItems.BILLET_ZIRCONIUM.get());
                        output.accept(RegisterItems.BILLET_ZFB_BISMUTH.get());
                        output.accept(RegisterItems.BILLET_ZFB_PU241.get());
                        output.accept(RegisterItems.BILLET_ZFB_AM_MIX.get());
                        output.accept(RegisterItems.BILLET_SCHRABIDIUM.get());
                        output.accept(RegisterItems.BILLET_SOLINIUM.get());
                        output.accept(RegisterItems.BILLET_THORIUM_FUEL.get());
                        output.accept(RegisterItems.BILLET_URANIUM_FUEL.get());
                        output.accept(RegisterItems.BILLET_MOX_FUEL.get());
                        output.accept(RegisterItems.BILLET_PLUTONIUM_FUEL.get());
                        output.accept(RegisterItems.BILLET_NEPTUNIUM_FUEL.get());
                        output.accept(RegisterItems.BILLET_AMERICIUM_FUEL.get());
                        output.accept(RegisterItems.BILLET_LES.get());
                        output.accept(RegisterItems.BILLET_SCHRABIDIUM_FUEL.get());
                        output.accept(RegisterItems.BILLET_HES.get());
                        output.accept(RegisterItems.BILLET_PO210BE.get());
                        output.accept(RegisterItems.BILLET_RA226BE.get());
                        output.accept(RegisterItems.BILLET_PU238BE.get());
                        output.accept(RegisterItems.BILLET_AUSTRALIUM.get());
                        output.accept(RegisterItems.BILLET_AUSTRALIUM_LESSER.get());
                        output.accept(RegisterItems.BILLET_AUSTRALIUM_GREATER.get());
                        output.accept(RegisterItems.BILLET_UNOBTAINIUM.get());
                        output.accept(RegisterItems.BILLET_YHARONITE.get());
                        output.accept(RegisterItems.BILLET_BALEFIRE_GOLD.get());
                        output.accept(RegisterItems.BILLET_FLASHLEAD.get());
                        output.accept(RegisterItems.BILLET_NUCLEAR_WASTE.get());
                        // Nuggets
                        output.accept(RegisterItems.NUGGET_URANIUM.get());
                        output.accept(RegisterItems.NUGGET_U233.get());
                        output.accept(RegisterItems.NUGGET_U235.get());
                        output.accept(RegisterItems.NUGGET_U238.get());
                        output.accept(RegisterItems.NUGGET_PLUTONIUM.get());
                        output.accept(RegisterItems.NUGGET_PU238.get());
                        output.accept(RegisterItems.NUGGET_PU239.get());
                        output.accept(RegisterItems.NUGGET_PU240.get());
                        output.accept(RegisterItems.NUGGET_TH232.get());
                        output.accept(RegisterItems.NUGGET_PU241.get());
                        output.accept(RegisterItems.NUGGET_PU_MIX.get());
                        output.accept(RegisterItems.NUGGET_AM241.get());
                        output.accept(RegisterItems.NUGGET_AM242.get());
                        output.accept(RegisterItems.NUGGET_AM_MIX.get());
                        output.accept(RegisterItems.NUGGET_TECHNETIUM.get());
                        output.accept(RegisterItems.NUGGET_NEPTUNIUM.get());
                        output.accept(RegisterItems.NUGGET_POLONIUM.get());
                        output.accept(RegisterItems.NUGGET_THORIUM_FUEL.get());
                        output.accept(RegisterItems.NUGGET_URANIUM_FUEL.get());
                        output.accept(RegisterItems.NUGGET_MOX_FUEL.get());
                        output.accept(RegisterItems.NUGGET_PLUTONIUM_FUEL.get());
                        output.accept(RegisterItems.NUGGET_NEPTUNIUM_FUEL.get());
                        output.accept(RegisterItems.NUGGET_AMERICIUM_FUEL.get());
                        output.accept(RegisterItems.NUGGET_LES.get());
                        output.accept(RegisterItems.NUGGET_SCHRABIDIUM_FUEL.get());
                        output.accept(RegisterItems.NUGGET_HES.get());
                        output.accept(RegisterItems.NUGGET_LEAD.get());
                        output.accept(RegisterItems.NUGGET_BERYLLIUM.get());
                        output.accept(RegisterItems.NUGGET_CADMIUM.get());
                        output.accept(RegisterItems.NUGGET_BISMUTH.get());
                        output.accept(RegisterItems.NUGGET_ARSENIC.get());
                        output.accept(RegisterItems.NUGGET_ZIRCONIUM.get());
                        output.accept(RegisterItems.NUGGET_TANTALIUM.get());
                        output.accept(RegisterItems.NUGGET_DESH.get());
                        output.accept(RegisterItems.NUGGET_OSMIRIDIUM.get());
                        output.accept(RegisterItems.NUGGET_SCHRABIDIUM.get());
                        output.accept(RegisterItems.NUGGET_SOLINIUM.get());
                        output.accept(RegisterItems.NUGGET_EUPHEMIUM.get());
                        output.accept(RegisterItems.NUGGET_DINEUTRONIUM.get());
                        output.accept(RegisterItems.NUGGET_NIOBIUM.get());
                        output.accept(RegisterItems.NUGGET_SILICON.get());
                        output.accept(RegisterItems.NUGGET_ACTINIUM.get());
                        output.accept(RegisterItems.NUGGET_COBALT.get());
                        output.accept(RegisterItems.NUGGET_CO60.get());
                        output.accept(RegisterItems.NUGGET_STRONTIUM.get());
                        output.accept(RegisterItems.NUGGET_SR90.get());
                        output.accept(RegisterItems.NUGGET_PB209.get());
                        output.accept(RegisterItems.NUGGET_GH336.get());
                        output.accept(RegisterItems.NUGGET_AU198.get());
                        output.accept(RegisterItems.NUGGET_RA226.get());
                        output.accept(RegisterItems.NUGGET_REIIUM.get());
                        output.accept(RegisterItems.NUGGET_WEIDANIUM.get());
                        output.accept(RegisterItems.NUGGET_AUSTRALIUM.get());
                        output.accept(RegisterItems.NUGGET_AUSTRALIUM_LESSER.get());
                        output.accept(RegisterItems.NUGGET_AUSTRALIUM_GREATER.get());
                        output.accept(RegisterItems.NUGGET_VERTICIUM.get());
                        output.accept(RegisterItems.NUGGET_UNOBTAINIUM.get());
                        output.accept(RegisterItems.NUGGET_UNOBTAINIUM_LESSER.get());
                        output.accept(RegisterItems.NUGGET_UNOBTAINIUM_GREATER.get());
                        output.accept(RegisterItems.NUGGET_DAFFERGON.get());
                        output.accept(RegisterItems.NUGGET_U238M2.get());

                        output.accept(RegisterBlocks.BATTERY_BLOCK.get());

                        output.accept(RegisterItems.POWDER_URANIUM.get());
                        output.accept(RegisterItems.NUGGET_URANIUM.get());
                        output.accept(RegisterItems.CRYSTAL_URANIUM.get());

                        output.accept(RegisterItems.PILE_ROD_URANIUM.get());

                        output.accept(RegisterItems.RAW_TITANIUM.get());
                        output.accept(RegisterItems.RAW_URANIUM.get());
                        output.accept(RegisterItems.RAW_THORIUM.get());
                        output.accept(RegisterItems.RAW_COPPER_HBM.get());
                        output.accept(RegisterItems.RAW_COBALT.get());

                        output.accept(RegisterItems.GEIGER_COUNTER.get());

                        output.accept(RegisterItems.M65_MASK.get());

                        output.accept(RegisterItems.GAS_MASK_FILTER_MONO.get());
                        output.accept(RegisterItems.GAS_MASK_FILTER.get());
                        output.accept(RegisterItems.GAS_MASK_FILTER_COMBO.get());
                        output.accept(RegisterItems.GAS_MASK_FILTER_RADON.get());

                        output.accept(RegisterItems.HAZMAT_HELMET.get());
                        output.accept(RegisterItems.HAZMAT_CHESTPLATE.get());
                        output.accept(RegisterItems.HAZMAT_LEGGINGS.get());
                        output.accept(RegisterItems.HAZMAT_BOOTS.get());

                        output.accept(RegisterItems.HAZMAT_HELMET_RED.get());
                        output.accept(RegisterItems.HAZMAT_CHESTPLATE_RED.get());
                        output.accept(RegisterItems.HAZMAT_LEGGINGS_RED.get());
                        output.accept(RegisterItems.HAZMAT_BOOTS_RED.get());

                        output.accept(RegisterItems.HAZMAT_HELMET_GREY.get());
                        output.accept(RegisterItems.HAZMAT_CHESTPLATE_GREY.get());
                        output.accept(RegisterItems.HAZMAT_LEGGINGS_GREY.get());
                        output.accept(RegisterItems.HAZMAT_BOOTS_GREY.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> NTM_BLOCKS = CREATIVE_TABS.register("ntm_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(RegisterBlocks.BLOCK_TITANIUM.get()))
                    .title(Component.translatable("creativetab.ntm_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        


                        // провода LV
			output.accept(RegisterBlocks.CABLE_LV_COPPER.get());
			output.accept(RegisterBlocks.CABLE_LV_RED_COPPER.get());
			output.accept(RegisterBlocks.CABLE_LV_GOLD.get());
			output.accept(RegisterBlocks.CABLE_LV_RED_GOLD.get());
			output.accept(RegisterBlocks.CABLE_LV_GILDED_COPPER.get());
			// провода MV
			output.accept(RegisterBlocks.CABLE_MV_COPPER.get());
			output.accept(RegisterBlocks.CABLE_MV_RED_COPPER.get());
			output.accept(RegisterBlocks.CABLE_MV_GOLD.get());
			output.accept(RegisterBlocks.CABLE_MV_RED_GOLD.get());
			output.accept(RegisterBlocks.CABLE_MV_GILDED_COPPER.get());
			// провода HV
			output.accept(RegisterBlocks.CABLE_HV_COPPER.get());
			output.accept(RegisterBlocks.CABLE_HV_RED_COPPER.get());
			output.accept(RegisterBlocks.CABLE_HV_GOLD.get());
			output.accept(RegisterBlocks.CABLE_HV_RED_GOLD.get());
			output.accept(RegisterBlocks.CABLE_HV_GILDED_COPPER.get());


                          
                        output.accept(RegisterBlocks.CONCRETE.get());


                        // M350 Concrete variants
                        output.accept(RegisterBlocks.BRICK_CONCRETE.get());
                        output.accept(RegisterBlocks.BRICK_CONCRETE_MOSSY.get());
                        output.accept(RegisterBlocks.BRICK_CONCRETE_CRACKED.get());
                        output.accept(RegisterBlocks.BRICK_CONCRETE_BROKEN.get());
                        output.accept(RegisterBlocks.BRICK_CONCRETE_MARKED.get());

                        // M350 Colored Concrete variants
                        output.accept(RegisterBlocks.CONCRETE_WHITE.get());
                        output.accept(RegisterBlocks.CONCRETE_ORANGE.get());
                        output.accept(RegisterBlocks.CONCRETE_MAGENTA.get());
                        output.accept(RegisterBlocks.CONCRETE_LIGHT_BLUE.get());
                        output.accept(RegisterBlocks.CONCRETE_YELLOW.get());
                        output.accept(RegisterBlocks.CONCRETE_LIME.get());
                        output.accept(RegisterBlocks.CONCRETE_PINK.get());
                        output.accept(RegisterBlocks.CONCRETE_GRAY.get());
                        output.accept(RegisterBlocks.CONCRETE_CYAN.get());
                        output.accept(RegisterBlocks.CONCRETE_PURPLE.get());
                        output.accept(RegisterBlocks.CONCRETE_BLUE.get());
                        output.accept(RegisterBlocks.CONCRETE_BROWN.get());
                        output.accept(RegisterBlocks.CONCRETE_GREEN.get());
                        output.accept(RegisterBlocks.CONCRETE_RED.get());
                        output.accept(RegisterBlocks.CONCRETE_BLACK.get());

                        // M350 Reinforced blocks
                        output.accept(RegisterBlocks.REINFORCED_STONE.get());
                        output.accept(RegisterBlocks.BRICK_COMPOUND.get());
                        output.accept(RegisterBlocks.BRICK_OBSIDIAN.get());

                        // CMB Brick variants
                        output.accept(RegisterBlocks.CMB_BRICK.get());
                        output.accept(RegisterBlocks.CMB_BRICK_REINFORCED.get());

                        output.accept(RegisterBlocks.BLOCK_WASTE.get());
                        output.accept(RegisterBlocks.BLOCK_URANIUM.get());
                        output.accept(RegisterBlocks.BLOCK_TITANIUM.get());

                        output.accept(RegisterBlocks.ORE_URANIUM.get());
                        output.accept(RegisterBlocks.DEEPSLATE_ORE_URANIUM.get());

                        output.accept(RegisterBlocks.ORE_TITANIUM.get());
                        output.accept(RegisterBlocks.DEEPSLATE_ORE_TITANIUM.get());
                        output.accept(RegisterBlocks.ORE_THORIUM.get());
                        output.accept(RegisterBlocks.DEEPSLATE_ORE_THORIUM.get());
                        output.accept(RegisterBlocks.ORE_COPPER.get());
                        output.accept(RegisterBlocks.DEEPSLATE_ORE_COPPER.get());
                        output.accept(RegisterBlocks.ORE_COBALT.get());
                        output.accept(RegisterBlocks.DEEPSLATE_ORE_COBALT.get());


                        output.accept(RegisterBlocks.RAD_RESISTANT_BLOCK.get());

                        output.accept(RegisterBlocks.WASTE_GRASS.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> NTM_MACHINES = CREATIVE_TABS.register("ntm_machines",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(RegisterBlocks.RADIATION_DECONTAMINATOR.get()))
                    .title(Component.translatable("creativetab.ntm_machines"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(RegisterBlocks.TRANSFORMER_HV_MV_GILDED_COPPER.get());
                        output.accept(RegisterBlocks.TRANSFORMER_HV_MV_COPPER.get());
                        output.accept(RegisterBlocks.TRANSFORMER_HV_MV_RED_COPPER.get());
                        output.accept(RegisterBlocks.TRANSFORMER_HV_MV_GOLD.get());
                        output.accept(RegisterBlocks.TRANSFORMER_HV_MV_RED_GOLD.get());
                        output.accept(RegisterBlocks.TRANSFORMER_MV_LV_GILDED_COPPER.get());
                        output.accept(RegisterBlocks.TRANSFORMER_MV_LV_COPPER.get());
                        output.accept(RegisterBlocks.TRANSFORMER_MV_LV_RED_COPPER.get());
                        output.accept(RegisterBlocks.TRANSFORMER_MV_LV_GOLD.get());
                        output.accept(RegisterBlocks.TRANSFORMER_MV_LV_RED_GOLD.get());

                        output.accept(RegisterItems.MISSILE_LINKER.get());
                        output.accept(RegisterItems.NUKE_TACTICAL.get());
                        output.accept(RegisterItems.NUKE_STANDARD.get());
                        output.accept(RegisterItems.NUKE_THERMONUCLEAR.get());
                        output.accept(RegisterItems.NUKE_BUNKER_BUSTER.get());

                        output.accept(RegisterBlocks.RADIATION_DECONTAMINATOR.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {

        CREATIVE_TABS.register(eventBus);
    }
}
