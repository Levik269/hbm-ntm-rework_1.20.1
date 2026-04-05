package com.hbm.nucleartech.datagen;

import com.hbm.nucleartech.HBM;
import com.hbm.nucleartech.item.RegisterItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {

        super(output, HBM.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        simpleItem(RegisterItems.MISSILE_LINKER);
        simpleItem(RegisterItems.MULTIMETER);
        simpleItem(RegisterItems.ADVANCED_MULTIMETER);
        // ingots
        // Uranium
        simpleItem(RegisterItems.INGOT_URANIUM);
        simpleItem(RegisterItems.INGOT_U233);
        simpleItem(RegisterItems.INGOT_U235);
        simpleItem(RegisterItems.INGOT_U238);
        simpleItem(RegisterItems.INGOT_URANIUM_FUEL);
        simpleItem(RegisterItems.INGOT_U238m2);

        // Plutonium
        simpleItem(RegisterItems.INGOT_PLUTONIUM);
        simpleItem(RegisterItems.INGOT_PU239);
        simpleItem(RegisterItems.INGOT_PU240);
        simpleItem(RegisterItems.INGOT_PU241);
        simpleItem(RegisterItems.INGOT_PLUTONIUM_PU_MIX);
        simpleItem(RegisterItems.INGOT_PLUTONIUM_FUEL);
        simpleItem(RegisterItems.INGOT_PU238);

        // Americium
        simpleItem(RegisterItems.INGOT_AM241);
        simpleItem(RegisterItems.INGOT_AM242);
        simpleItem(RegisterItems.INGOT_AM_MIX);
        simpleItem(RegisterItems.INGOT_AMERICIUM_FUEL);

        // Thorium / Neptunium
        simpleItem(RegisterItems.INGOT_THORIUM);
        simpleItem(RegisterItems.INGOT_THORIUM_FUEL);
        simpleItem(RegisterItems.INGOT_NEPTUNIUM);
        simpleItem(RegisterItems.INGOT_NEPTUNIUM_FUEL);

        // Schrabidium
        simpleItem(RegisterItems.INGOT_SCHRARANIUM);
        simpleItem(RegisterItems.INGOT_SCHRABIDIUM);
        simpleItem(RegisterItems.INGOT_SCHRABIDATE);
        simpleItem(RegisterItems.INGOT_SCHRABIDIUM_FUEL);
        simpleItem(RegisterItems.INGOT_HES);
        simpleItem(RegisterItems.INGOT_LES);

        // Hazard misc
        simpleItem(RegisterItems.INGOT_TECHNETIUM);
        simpleItem(RegisterItems.INGOT_MOX_FUEL);
        simpleItem(RegisterItems.INGOT_RA226);
        simpleItem(RegisterItems.INGOT_ACTINIUM);
        simpleItem(RegisterItems.INGOT_GH336);
        simpleItem(RegisterItems.INGOT_MUD);
        simpleItem(RegisterItems.INGOT_POLONIUM);
        simpleItem(RegisterItems.INGOT_PHOSPHORUS);
        simpleItem(RegisterItems.INGOT_CO60);
        simpleItem(RegisterItems.INGOT_SR90);
        simpleItem(RegisterItems.INGOT_I131);
        simpleItem(RegisterItems.INGOT_AU198);
        simpleItem(RegisterItems.INGOT_PB209);
        simpleItem(RegisterItems.INGOT_ELECTRONIUM);
        simpleItem(RegisterItems.INGOT_ASBESTOS);

        // Normal ingots
        simpleItem(RegisterItems.INGOT_STEEL);
        simpleItem(RegisterItems.INGOT_TITANIUM);
        simpleItem(RegisterItems.INGOT_COPPER);
        simpleItem(RegisterItems.INGOT_RED_COPPER);
        simpleItem(RegisterItems.INGOT_ADVANCED_ALLOY);
        simpleItem(RegisterItems.INGOT_TUNGSTEN);
        simpleItem(RegisterItems.INGOT_ALUMINIUM);
        simpleItem(RegisterItems.INGOT_BERYLLIUM);
        simpleItem(RegisterItems.INGOT_LEAD);
        simpleItem(RegisterItems.INGOT_MAGNETIZED_TUNGSTEN);
        simpleItem(RegisterItems.INGOT_COMBINE_STEEL);
        simpleItem(RegisterItems.INGOT_DURA_STEEL);
        simpleItem(RegisterItems.INGOT_TCALLOY);
        simpleItem(RegisterItems.INGOT_CDALLOY);
        simpleItem(RegisterItems.INGOT_DESH);
        simpleItem(RegisterItems.INGOT_SATURNITE);
        simpleItem(RegisterItems.INGOT_FERROURANIUM);
        simpleItem(RegisterItems.INGOT_STARMETAL);
        simpleItem(RegisterItems.INGOT_OSMIRIDIUM);
        simpleItem(RegisterItems.INGOT_EUPHEMIUM);
        simpleItem(RegisterItems.INGOT_DINEUTRONIUM);
        simpleItem(RegisterItems.INGOT_CADMIUM);
        simpleItem(RegisterItems.INGOT_BISMUTH);
        simpleItem(RegisterItems.INGOT_ARSENIC);
        simpleItem(RegisterItems.INGOT_ZIRCONIUM);
        simpleItem(RegisterItems.INGOT_BISMUTH_BRONZE);
        simpleItem(RegisterItems.INGOT_ARSENIC_BRONZE);
        simpleItem(RegisterItems.INGOT_BSCCO);
        simpleItem(RegisterItems.INGOT_CALCIUM);
        simpleItem(RegisterItems.INGOT_GUNMETAL);
        simpleItem(RegisterItems.INGOT_WEAPONSTEEL);
        simpleItem(RegisterItems.INGOT_CFT);
        simpleItem(RegisterItems.INGOT_TENNESSINE);
        simpleItem(RegisterItems.INGOT_BORON);
        simpleItem(RegisterItems.INGOT_GRAPHITE);
        simpleItem(RegisterItems.INGOT_FIBERGLASS);
        simpleItem(RegisterItems.INGOT_NIOBIUM);
        simpleItem(RegisterItems.INGOT_BROMINE);
        simpleItem(RegisterItems.INGOT_CAESIUM);
        simpleItem(RegisterItems.INGOT_CERIUM);
        simpleItem(RegisterItems.INGOT_LANTHANIUM);
        simpleItem(RegisterItems.INGOT_TANTALIUM);
        simpleItem(RegisterItems.INGOT_ASTATINE);
        simpleItem(RegisterItems.INGOT_FIREBRICK);
        simpleItem(RegisterItems.INGOT_COBALT);
        simpleItem(RegisterItems.INGOT_IODINE);
        simpleItem(RegisterItems.INGOT_REIIUM);
        simpleItem(RegisterItems.INGOT_WEIDANIUM);
        simpleItem(RegisterItems.INGOT_AUSTRALIUM);
        simpleItem(RegisterItems.INGOT_VERTICIUM);
        simpleItem(RegisterItems.INGOT_UNOBTAINIUM);
        simpleItem(RegisterItems.INGOT_DAFFERGON);
        simpleItem(RegisterItems.INGOT_STEEL_DUSTED);
        simpleItem(RegisterItems.INGOT_CHAINSTEEL);
        simpleItem(RegisterItems.INGOT_METEORITE);
        simpleItem(RegisterItems.INGOT_METEORITE_FORGED);
        simpleItem(RegisterItems.INGOT_POLYMER);
        simpleItem(RegisterItems.INGOT_BAKELITE);
        simpleItem(RegisterItems.INGOT_RUBBER);
        simpleItem(RegisterItems.INGOT_BIORUBBER);
        simpleItem(RegisterItems.INGOT_PC);
        simpleItem(RegisterItems.INGOT_PVC);
        simpleItem(RegisterItems.INGOT_SILICON);
        simpleItem(RegisterItems.INGOT_RED_GOLD);
        simpleItem(RegisterItems.INGOT_GILDED_COPPER);

//--------- Billets ---------
        simpleItem(RegisterItems.BILLET_COBALT);
        simpleItem(RegisterItems.BILLET_SILICON);
        simpleItem(RegisterItems.BILLET_TH232);
        simpleItem(RegisterItems.BILLET_URANIUM);
        simpleItem(RegisterItems.BILLET_U233);
        simpleItem(RegisterItems.BILLET_U235);
        simpleItem(RegisterItems.BILLET_U238);
        simpleItem(RegisterItems.BILLET_PLUTONIUM);
        simpleItem(RegisterItems.BILLET_PU238);
        simpleItem(RegisterItems.BILLET_PU239);
        simpleItem(RegisterItems.BILLET_PU240);
        simpleItem(RegisterItems.BILLET_PU241);
        simpleItem(RegisterItems.BILLET_PU_MIX);
        simpleItem(RegisterItems.BILLET_AM241);
        simpleItem(RegisterItems.BILLET_AM242);
        simpleItem(RegisterItems.BILLET_AM_MIX);
        simpleItem(RegisterItems.BILLET_NEPTUNIUM);
        simpleItem(RegisterItems.BILLET_POLONIUM);
        simpleItem(RegisterItems.BILLET_TECHNETIUM);
        simpleItem(RegisterItems.BILLET_CO60);
        simpleItem(RegisterItems.BILLET_SR90);
        simpleItem(RegisterItems.BILLET_AU198);
        simpleItem(RegisterItems.BILLET_PB209);
        simpleItem(RegisterItems.BILLET_RA226);
        simpleItem(RegisterItems.BILLET_ACTINIUM);
        simpleItem(RegisterItems.BILLET_GH336);
        simpleItem(RegisterItems.BILLET_BERYLLIUM);
        simpleItem(RegisterItems.BILLET_BISMUTH);
        simpleItem(RegisterItems.BILLET_ZIRCONIUM);
        simpleItem(RegisterItems.BILLET_ZFB_BISMUTH);
        simpleItem(RegisterItems.BILLET_ZFB_PU241);
        simpleItem(RegisterItems.BILLET_ZFB_AM_MIX);
        simpleItem(RegisterItems.BILLET_SCHRABIDIUM);
        simpleItem(RegisterItems.BILLET_SOLINIUM);
        simpleItem(RegisterItems.BILLET_THORIUM_FUEL);
        simpleItem(RegisterItems.BILLET_URANIUM_FUEL);
        simpleItem(RegisterItems.BILLET_MOX_FUEL);
        simpleItem(RegisterItems.BILLET_PLUTONIUM_FUEL);
        simpleItem(RegisterItems.BILLET_NEPTUNIUM_FUEL);
        simpleItem(RegisterItems.BILLET_AMERICIUM_FUEL);
        simpleItem(RegisterItems.BILLET_LES);
        simpleItem(RegisterItems.BILLET_SCHRABIDIUM_FUEL);
        simpleItem(RegisterItems.BILLET_HES);
        simpleItem(RegisterItems.BILLET_PO210BE);
        simpleItem(RegisterItems.BILLET_RA226BE);
        simpleItem(RegisterItems.BILLET_PU238BE);
        simpleItem(RegisterItems.BILLET_AUSTRALIUM);
        simpleItem(RegisterItems.BILLET_AUSTRALIUM_LESSER);
        simpleItem(RegisterItems.BILLET_AUSTRALIUM_GREATER);
        simpleItem(RegisterItems.BILLET_UNOBTAINIUM);
        simpleItem(RegisterItems.BILLET_YHARONITE);
        simpleItem(RegisterItems.BILLET_BALEFIRE_GOLD);
        simpleItem(RegisterItems.BILLET_FLASHLEAD);
        simpleItem(RegisterItems.BILLET_NUCLEAR_WASTE);
        // ------ Nuggets ------
        simpleItem(RegisterItems.NUGGET_URANIUM);
        simpleItem(RegisterItems.NUGGET_U233);
        simpleItem(RegisterItems.NUGGET_U235);
        simpleItem(RegisterItems.NUGGET_U238);
        simpleItem(RegisterItems.NUGGET_PLUTONIUM);
        simpleItem(RegisterItems.NUGGET_PU238);
        simpleItem(RegisterItems.NUGGET_PU239);
        simpleItem(RegisterItems.NUGGET_PU240);
        simpleItem(RegisterItems.NUGGET_TH232);
        simpleItem(RegisterItems.NUGGET_PU241);
        simpleItem(RegisterItems.NUGGET_PU_MIX);
        simpleItem(RegisterItems.NUGGET_AM241);
        simpleItem(RegisterItems.NUGGET_AM242);
        simpleItem(RegisterItems.NUGGET_AM_MIX);
        simpleItem(RegisterItems.NUGGET_TECHNETIUM);
        simpleItem(RegisterItems.NUGGET_NEPTUNIUM);
        simpleItem(RegisterItems.NUGGET_POLONIUM);
        simpleItem(RegisterItems.NUGGET_THORIUM_FUEL);
        simpleItem(RegisterItems.NUGGET_URANIUM_FUEL);
        simpleItem(RegisterItems.NUGGET_MOX_FUEL);
        simpleItem(RegisterItems.NUGGET_PLUTONIUM_FUEL);
        simpleItem(RegisterItems.NUGGET_NEPTUNIUM_FUEL);
        simpleItem(RegisterItems.NUGGET_AMERICIUM_FUEL);
        simpleItem(RegisterItems.NUGGET_LES);
        simpleItem(RegisterItems.NUGGET_SCHRABIDIUM_FUEL);
        simpleItem(RegisterItems.NUGGET_HES);
        simpleItem(RegisterItems.NUGGET_LEAD);
        simpleItem(RegisterItems.NUGGET_BERYLLIUM);
        simpleItem(RegisterItems.NUGGET_CADMIUM);
        simpleItem(RegisterItems.NUGGET_BISMUTH);
        simpleItem(RegisterItems.NUGGET_ARSENIC);
        simpleItem(RegisterItems.NUGGET_ZIRCONIUM);
        simpleItem(RegisterItems.NUGGET_TANTALIUM);
        simpleItem(RegisterItems.NUGGET_DESH);
        simpleItem(RegisterItems.NUGGET_OSMIRIDIUM);
        simpleItem(RegisterItems.NUGGET_SCHRABIDIUM);
        simpleItem(RegisterItems.NUGGET_SOLINIUM);
        simpleItem(RegisterItems.NUGGET_EUPHEMIUM);
        simpleItem(RegisterItems.NUGGET_DINEUTRONIUM);
        simpleItem(RegisterItems.NUGGET_NIOBIUM);
        simpleItem(RegisterItems.NUGGET_SILICON);
        simpleItem(RegisterItems.NUGGET_ACTINIUM);
        simpleItem(RegisterItems.NUGGET_COBALT);
        simpleItem(RegisterItems.NUGGET_CO60);
        simpleItem(RegisterItems.NUGGET_STRONTIUM);
        simpleItem(RegisterItems.NUGGET_SR90);
        simpleItem(RegisterItems.NUGGET_PB209);
        simpleItem(RegisterItems.NUGGET_GH336);
        simpleItem(RegisterItems.NUGGET_AU198);
        simpleItem(RegisterItems.NUGGET_RA226);
        simpleItem(RegisterItems.NUGGET_REIIUM);
        simpleItem(RegisterItems.NUGGET_WEIDANIUM);
        simpleItem(RegisterItems.NUGGET_AUSTRALIUM);
        simpleItem(RegisterItems.NUGGET_AUSTRALIUM_LESSER);
        simpleItem(RegisterItems.NUGGET_AUSTRALIUM_GREATER);
        simpleItem(RegisterItems.NUGGET_VERTICIUM);
        simpleItem(RegisterItems.NUGGET_UNOBTAINIUM);
        simpleItem(RegisterItems.NUGGET_UNOBTAINIUM_LESSER);
        simpleItem(RegisterItems.NUGGET_UNOBTAINIUM_GREATER);
        simpleItem(RegisterItems.NUGGET_DAFFERGON);
        simpleItem(RegisterItems.NUGGET_U238M2);

        simpleItem(RegisterItems.RAW_TITANIUM);
        simpleItem(RegisterItems.RAW_URANIUM);
        simpleItem(RegisterItems.RAW_COBALT);

        simpleItem(RegisterItems.PLATE_IRON);

        simpleItem(RegisterItems.PILE_ROD_URANIUM);

        simpleItem(RegisterItems.CRYSTAL_URANIUM);
        simpleItem(RegisterItems.NUGGET_URANIUM);
        simpleItem(RegisterItems.POWDER_URANIUM);

        simpleItem(RegisterItems.REACHER);
        simpleItem(RegisterItems.GEIGER_COUNTER);

        simpleItem(RegisterItems.PLACEHOLDER);

        simpleItem(RegisterItems.M65_MASK);

        simpleItem(RegisterItems.GAS_MASK_FILTER_MONO);
        simpleItem(RegisterItems.GAS_MASK_FILTER);
        simpleItem(RegisterItems.GAS_MASK_FILTER_COMBO);
        simpleItem(RegisterItems.GAS_MASK_FILTER_RADON);

        simpleItem(RegisterItems.HAZMAT_HELMET);
        simpleItem(RegisterItems.HAZMAT_CHESTPLATE);
        simpleItem(RegisterItems.HAZMAT_LEGGINGS);
        simpleItem(RegisterItems.HAZMAT_BOOTS);

        simpleItem(RegisterItems.HAZMAT_HELMET_RED);
        simpleItem(RegisterItems.HAZMAT_CHESTPLATE_RED);
        simpleItem(RegisterItems.HAZMAT_LEGGINGS_RED);
        simpleItem(RegisterItems.HAZMAT_BOOTS_RED);

        simpleItem(RegisterItems.HAZMAT_HELMET_GREY);
        simpleItem(RegisterItems.HAZMAT_CHESTPLATE_GREY);
        simpleItem(RegisterItems.HAZMAT_LEGGINGS_GREY);
        simpleItem(RegisterItems.HAZMAT_BOOTS_GREY);

        simpleItem(RegisterItems.NUKE_TACTICAL);
        simpleItem(RegisterItems.NUKE_STANDARD);
        simpleItem(RegisterItems.NUKE_THERMONUCLEAR);
        simpleItem(RegisterItems.NUKE_BUNKER_BUSTER);

        simpleItem(RegisterItems.MISSILE_TACTICAL);
        simpleItem(RegisterItems.MISSILE_STANDARD);
        simpleItem(RegisterItems.MISSILE_THERMONUCLEAR);
        simpleItem(RegisterItems.MISSILE_BUNKER_BUSTER);
        simpleItem(RegisterItems.MISSILE_COBALT);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {

        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(HBM.MOD_ID, "item/" + item.getId().getPath()));
    }
}
