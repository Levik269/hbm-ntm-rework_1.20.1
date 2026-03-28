package com.hbm.nucleartech.item;

import com.hbm.nucleartech.HBM;
import com.hbm.nucleartech.handler.ArmorModHandler;
import com.hbm.nucleartech.hazard.HazardItem;
import com.hbm.nucleartech.item.custom.*;
import com.hbm.nucleartech.explosion.NuclearBombType;
import com.hbm.nucleartech.item.special.CustomLoreItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegisterItems {



    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HBM.MOD_ID);

    public static final RegistryObject<Item> MISSILE_LINKER = ITEMS.register("missile_linker",
            () -> new MissileLinkerItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MULTIMETER = ITEMS.register("multimeter",
            () -> new MultimeterItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> ADVANCED_MULTIMETER = ITEMS.register("advanced_multimeter",
            () -> new AdvancedMultimeterItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> NUKE_TACTICAL = ITEMS.register("nuke_tactical",
            () -> new NukeItem(NuclearBombType.TACTICAL, new Item.Properties()));

    public static final RegistryObject<Item> NUKE_STANDARD = ITEMS.register("nuke_standard",
            () -> new NukeItem(NuclearBombType.STANDARD, new Item.Properties()));

    public static final RegistryObject<Item> NUKE_THERMONUCLEAR = ITEMS.register("nuke_thermonuclear",
            () -> new NukeItem(NuclearBombType.THERMONUCLEAR, new Item.Properties()));

    public static final RegistryObject<Item> NUKE_BUNKER_BUSTER = ITEMS.register("nuke_bunker_buster",
            () -> new NukeItem(NuclearBombType.BUNKER_BUSTER, new Item.Properties()));


    public static final RegistryObject<Item> PLATE_IRON = ITEMS.register("plate_iron",
            () -> new Item(new Item.Properties()));
// ------------- Ingots --------------

    // ------------- Uranium Ingots --------------
    public static final RegistryObject<Item> INGOT_URANIUM = ITEMS.register("ingot_uranium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(0.35)
                    .build());
    public static final RegistryObject<Item> INGOT_U233 = ITEMS.register("ingot_u233",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(5)
                    .build());
    public static final RegistryObject<Item> INGOT_U235 = ITEMS.register("ingot_u235",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(1)
                    .build());
    public static final RegistryObject<Item> INGOT_U238 = ITEMS.register("ingot_u238",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(0.25)
                    .build());
    public static final RegistryObject<Item> INGOT_URANIUM_FUEL = ITEMS.register("ingot_uranium_fuel",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(0.5)
                    .build());
    public static final RegistryObject<Item> INGOT_U238m2 = ITEMS.register("ingot_u238m2",
            () -> new ItemUnstable(350, 200, NuclearBombType.UNSTABLE_BIG, new Item.Properties()));
    // ------------- Plutonium Ingots --------------
    public static final RegistryObject<Item> INGOT_PLUTONIUM = ITEMS.register("ingot_plutonium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(7.5)
                    .build());
    public static final RegistryObject<Item> INGOT_PU239 = ITEMS.register("ingot_pu239",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(5)
                    .build());
    public static final RegistryObject<Item> INGOT_PU240 = ITEMS.register("ingot_pu240",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(7.5)
                    .build());
    public static final RegistryObject<Item> INGOT_PU241 = ITEMS.register("ingot_pu241",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(25)
                    .build());
    public static final RegistryObject<Item> INGOT_PLUTONIUM_PU_MIX = ITEMS.register("ingot_pu_mix",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(6.25)
                    .build());
    public static final RegistryObject<Item> INGOT_PLUTONIUM_FUEL = ITEMS.register("ingot_plutonium_fuel",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(4.25)
                    .build());
    public static final RegistryObject<Item> INGOT_PU238 = ITEMS.register("ingot_pu238",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(10)
                    .fire(1)
                    .build());
    // ------------- Americium Ingots --------------
    public static final RegistryObject<Item> INGOT_AM241 = ITEMS.register("ingot_am241",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(8.5)
                    .build());
    public static final RegistryObject<Item> INGOT_AM242 = ITEMS.register("ingot_am242",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(9.5)
                    .build());
    public static final RegistryObject<Item> INGOT_AM_MIX = ITEMS.register("ingot_am_mix",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(9)
                    .build());
    public static final RegistryObject<Item> INGOT_AMERICIUM_FUEL = ITEMS.register("ingot_americium_fuel",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(4.75)
                    .build());
    // ------------- Thorium Ingots --------------
    public static final RegistryObject<Item> INGOT_THORIUM = ITEMS.register("ingot_thorium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(0.1)
                    .build());
    public static final RegistryObject<Item> INGOT_THORIUM_FUEL = ITEMS.register("ingot_thorium_fuel",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(1.75)
                    .build());
    // ------------- Neptunium Ingots --------------
    public static final RegistryObject<Item> INGOT_NEPTUNIUM = ITEMS.register("ingot_neptunium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(2.5)
                    .build());
    public static final RegistryObject<Item> INGOT_NEPTUNIUM_FUEL = ITEMS.register("ingot_neptunium_fuel",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(1.5)
                    .build());
    // ------------- Schrabidium Ingots --------------
    public static final RegistryObject<Item> INGOT_SCHRARANIUM = ITEMS.register("ingot_schraranium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(1.5)
                    .blinding()
                    .build());
    public static final RegistryObject<Item> INGOT_SCHRABIDIUM = ITEMS.register("ingot_schrabidium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(15)
                    .blinding()
                    .build());
    public static final RegistryObject<Item> INGOT_SCHRABIDATE = ITEMS.register("ingot_schrabidate",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(1.5)
                    .blinding()
                    .build());
    public static final RegistryObject<Item> INGOT_SCHRABIDIUM_FUEL = ITEMS.register("ingot_schrabidium_fuel",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(5.85)
                    .blinding()
                    .build());
    public static final RegistryObject<Item> INGOT_HES = ITEMS.register("ingot_hes",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(5.85)
                    .build());
    public static final RegistryObject<Item> INGOT_LES = ITEMS.register("ingot_les",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(5.85)
                    .build());
    // ------------- Other Hazard Ingots --------------
    public static final RegistryObject<Item> INGOT_TECHNETIUM = ITEMS.register("ingot_technetium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(1.5)
                    .build());
    public static final RegistryObject<Item> INGOT_MOX_FUEL = ITEMS.register("ingot_mox_fuel",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(1.5)
                    .build());
    public static final RegistryObject<Item> INGOT_RA226 = ITEMS.register("ingot_ra226",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(1.5)
                    .build());
    public static final RegistryObject<Item> INGOT_ACTINIUM = ITEMS.register("ingot_actinium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(1.5)
                    .build());
    public static final RegistryObject<Item> INGOT_GH336 = ITEMS.register("ingot_gh336",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(1.5)
                    .build());
    public static final RegistryObject<Item> INGOT_MUD = ITEMS.register("ingot_mud",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(1.5)
                    .build());
    public static final RegistryObject<Item> INGOT_POLONIUM = ITEMS.register("ingot_polonium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(75)
                    .fire(1)
                    .build());
    public static final RegistryObject<Item> INGOT_PHOSPHORUS = ITEMS.register("ingot_phosphorus",
            () -> new HazardItem.Builder(new Item.Properties())
                    .fire(1)
                    .build());
    public static final RegistryObject<Item> INGOT_CO60 = ITEMS.register("ingot_co60",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(30)
                    .fire(1)
                    .build());
    public static final RegistryObject<Item> INGOT_SR90 = ITEMS.register("ingot_sr90",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(15)
                    .hydroReactive()
                    .fire(1)
                    .build());
    public static final RegistryObject<Item> INGOT_I131 = ITEMS.register("ingot_i131",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(150)
                    .fire(1)
                    .build());

    public static final RegistryObject<Item> INGOT_AU198 = ITEMS.register("ingot_au198",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(500)
                    .fire(1)
                    .build());

    public static final RegistryObject<Item> INGOT_PB209 = ITEMS.register("ingot_pb209",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(10000)
                    .blinding()
                    .fire(1)
                    .build());
    public static final RegistryObject<Item> INGOT_ELECTRONIUM = ITEMS.register("ingot_electronium",
            () -> new ItemUnstable(30, 6000, NuclearBombType.UNSTABLE_SMALL, new Item.Properties()));
    public static final RegistryObject<Item> INGOT_ASBESTOS = ITEMS.register("ingot_asbestos",
            () -> new HazardItem.Builder(new Item.Properties())
                    .asbestos(10)
                    .build());


    // ------------- Other Ingots --------------
    public static final RegistryObject<Item> INGOT_STEEL = ITEMS.register("ingot_steel",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_TITANIUM = ITEMS.register("ingot_titanium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_COPPER = ITEMS.register("ingot_copper",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_RED_COPPER = ITEMS.register("ingot_red_copper",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_ADVANCED_ALLOY = ITEMS.register("ingot_advanced_alloy",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_TUNGSTEN = ITEMS.register("ingot_tungsten",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_ALUMINIUM = ITEMS.register("ingot_aluminium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_BERYLLIUM = ITEMS.register("ingot_beryllium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_LEAD = ITEMS.register("ingot_lead",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_MAGNETIZED_TUNGSTEN = ITEMS.register("ingot_magnetized_tungsten",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_COMBINE_STEEL = ITEMS.register("ingot_combine_steel",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_DURA_STEEL = ITEMS.register("ingot_dura_steel",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_TCALLOY = ITEMS.register("ingot_tcalloy",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_CDALLOY = ITEMS.register("ingot_cdalloy",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_DESH = ITEMS.register("ingot_desh",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_SATURNITE = ITEMS.register("ingot_saturnite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_FERROURANIUM = ITEMS.register("ingot_ferrouranium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_STARMETAL = ITEMS.register("ingot_starmetal",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_OSMIRIDIUM = ITEMS.register("ingot_osmiridium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_EUPHEMIUM = ITEMS.register("ingot_euphemium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_DINEUTRONIUM = ITEMS.register("ingot_dineutronium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_CADMIUM = ITEMS.register("ingot_cadmium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_BISMUTH = ITEMS.register("ingot_bismuth",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_ARSENIC = ITEMS.register("ingot_arsenic",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_ZIRCONIUM = ITEMS.register("ingot_zirconium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_BISMUTH_BRONZE = ITEMS.register("ingot_bismuth_bronze",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_ARSENIC_BRONZE = ITEMS.register("ingot_arsenic_bronze",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_BSCCO = ITEMS.register("ingot_bscco",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_CALCIUM = ITEMS.register("ingot_calcium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_GUNMETAL = ITEMS.register("ingot_gunmetal",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_WEAPONSTEEL = ITEMS.register("ingot_weaponsteel",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_CFT = ITEMS.register("ingot_cft",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_TENNESSINE = ITEMS.register("ingot_tennessine",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_BORON = ITEMS.register("ingot_boron",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_GRAPHITE = ITEMS.register("ingot_graphite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_FIBERGLASS = ITEMS.register("ingot_fiberglass",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_NIOBIUM = ITEMS.register("ingot_niobium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_BROMINE = ITEMS.register("ingot_bromine",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_CAESIUM = ITEMS.register("ingot_caesium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_CERIUM = ITEMS.register("ingot_cerium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_LANTHANIUM = ITEMS.register("ingot_lanthanium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_TANTALIUM = ITEMS.register("ingot_tantalium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_ASTATINE = ITEMS.register("ingot_astatine",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_FIREBRICK = ITEMS.register("ingot_firebrick",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_COBALT = ITEMS.register("ingot_cobalt",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_IODINE = ITEMS.register("ingot_iodine",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_REIIUM = ITEMS.register("ingot_reiium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_WEIDANIUM = ITEMS.register("ingot_weidanium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_AUSTRALIUM = ITEMS.register("ingot_australium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_VERTICIUM = ITEMS.register("ingot_verticium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_UNOBTAINIUM = ITEMS.register("ingot_unobtainium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_DAFFERGON = ITEMS.register("ingot_daffergon",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_STEEL_DUSTED = ITEMS.register("ingot_steel_dusted",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_CHAINSTEEL = ITEMS.register("ingot_chainsteel",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_METEORITE = ITEMS.register("ingot_meteorite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_METEORITE_FORGED = ITEMS.register("ingot_meteorite_forged",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_POLYMER = ITEMS.register("ingot_polymer",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_BAKELITE = ITEMS.register("ingot_bakelite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_RUBBER = ITEMS.register("ingot_rubber",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_BIORUBBER = ITEMS.register("ingot_biorubber",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_PC = ITEMS.register("ingot_pc",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_PVC = ITEMS.register("ingot_pvc",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INGOT_SILICON = ITEMS.register("ingot_silicon",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_RED_GOLD = ITEMS.register("ingot_red_gold",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INGOT_GILDED_COPPER = ITEMS.register("ingot_gilded_copper",
            () -> new Item(new Item.Properties()));




// ------------- Raw Ores --------------
    public static final RegistryObject<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_URANIUM = ITEMS.register("raw_uranium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(0.035)
                    .build());

    public static final RegistryObject<Item> RAW_COPPER_HBM = ITEMS.register("raw_copper_hbm",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_THORIUM = ITEMS.register("raw_thorium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(0.015)
                    .build());

    public static final RegistryObject<Item> RAW_COBALT = ITEMS.register("raw_cobalt",
            () -> new Item(new Item.Properties()));
// ------------- Hazardous Items --------------

    public static final RegistryObject<Item> TEST_ASBESTOS_ITEM =
            ITEMS.register("test_asbestos_item",
                    () -> new HazardItem.Builder(new Item.Properties())
                            .asbestos(1)
                            .build()
            );
    public static final RegistryObject<Item> DEBUG_HAZARD_REMOVER = ITEMS.register("debug_hazard_remover",
            () -> new DebugHazardRemover(new Item.Properties()));

    public static final RegistryObject<Item> POWDER_URANIUM = ITEMS.register("powder_uranium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(0.175)
                    .build());



    public static final RegistryObject<Item> CRYSTAL_URANIUM = ITEMS.register("crystal_uranium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(1.75)
                    .build());
    // ------------- Nuggets --------------
    public static final RegistryObject<Item> NUGGET_URANIUM = ITEMS.register("nugget_uranium",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.035f).build());

    public static final RegistryObject<Item> NUGGET_U233 = ITEMS.register("nugget_u233",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.5f).build());

    public static final RegistryObject<Item> NUGGET_U235 = ITEMS.register("nugget_u235",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.1f).build());

    public static final RegistryObject<Item> NUGGET_U238 = ITEMS.register("nugget_u238",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.025f).build());

    public static final RegistryObject<Item> NUGGET_PLUTONIUM = ITEMS.register("nugget_plutonium",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.75f).build());

    public static final RegistryObject<Item> NUGGET_PU238 = ITEMS.register("nugget_pu238",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(1.0f).fire(1).build());

    public static final RegistryObject<Item> NUGGET_PU239 = ITEMS.register("nugget_pu239",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.5f).build());

    public static final RegistryObject<Item> NUGGET_PU240 = ITEMS.register("nugget_pu240",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.75f).build());

    public static final RegistryObject<Item> NUGGET_TH232 = ITEMS.register("nugget_th232",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.01f).build());

    public static final RegistryObject<Item> NUGGET_PU241 = ITEMS.register("nugget_pu241",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.6f).build());

    public static final RegistryObject<Item> NUGGET_PU_MIX = ITEMS.register("nugget_pu_mix",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.7f).build());

    public static final RegistryObject<Item> NUGGET_AM241 = ITEMS.register("nugget_am241",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(1.2f).build());

    public static final RegistryObject<Item> NUGGET_AM242 = ITEMS.register("nugget_am242",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(1.5f).build());

    public static final RegistryObject<Item> NUGGET_AM_MIX = ITEMS.register("nugget_am_mix",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(1.3f).build());

    public static final RegistryObject<Item> NUGGET_TECHNETIUM = ITEMS.register("nugget_technetium",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.8f).build());

    public static final RegistryObject<Item> NUGGET_NEPTUNIUM = ITEMS.register("nugget_neptunium",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.25f).build());

    public static final RegistryObject<Item> NUGGET_POLONIUM = ITEMS.register("nugget_polonium",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(2.5f).build());

    public static final RegistryObject<Item> NUGGET_THORIUM_FUEL = ITEMS.register("nugget_thorium_fuel",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.175f).build());

    public static final RegistryObject<Item> NUGGET_URANIUM_FUEL = ITEMS.register("nugget_uranium_fuel",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.05f).build());

    public static final RegistryObject<Item> NUGGET_MOX_FUEL = ITEMS.register("nugget_mox_fuel",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.25f).build());

    public static final RegistryObject<Item> NUGGET_PLUTONIUM_FUEL = ITEMS.register("nugget_plutonium_fuel",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.425f).build());

    public static final RegistryObject<Item> NUGGET_NEPTUNIUM_FUEL = ITEMS.register("nugget_neptunium_fuel",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.4f).build());

    public static final RegistryObject<Item> NUGGET_AMERICIUM_FUEL = ITEMS.register("nugget_americium_fuel",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(1.0f).build());

    public static final RegistryObject<Item> NUGGET_LES = ITEMS.register("nugget_les",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.585f).build());

    public static final RegistryObject<Item> NUGGET_SCHRABIDIUM_FUEL = ITEMS.register("nugget_schrabidium_fuel",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.585f).blinding().build());

    public static final RegistryObject<Item> NUGGET_HES = ITEMS.register("nugget_hes",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.585f).build());

    public static final RegistryObject<Item> NUGGET_LEAD = ITEMS.register("nugget_lead",
            () -> new HazardItem.Builder(new Item.Properties()).build()); // почти 0

    public static final RegistryObject<Item> NUGGET_BERYLLIUM = ITEMS.register("nugget_beryllium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_CADMIUM = ITEMS.register("nugget_cadmium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_BISMUTH = ITEMS.register("nugget_bismuth",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_ARSENIC = ITEMS.register("nugget_arsenic",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.3f).build());

    public static final RegistryObject<Item> NUGGET_ZIRCONIUM = ITEMS.register("nugget_zirconium",
            () -> new HazardItem.Builder(new Item.Properties()).build());

    public static final RegistryObject<Item> NUGGET_TANTALIUM = ITEMS.register("nugget_tantalium",
            () -> new HazardItem.Builder(new Item.Properties()).build());

    public static final RegistryObject<Item> NUGGET_DESH = ITEMS.register("nugget_desh",
            () -> new HazardItem.Builder(new Item.Properties()).build());

    public static final RegistryObject<Item> NUGGET_OSMIRIDIUM = ITEMS.register("nugget_osmiridium",
            () -> new HazardItem.Builder(new Item.Properties()).build());

    public static final RegistryObject<Item> NUGGET_SCHRABIDIUM = ITEMS.register("nugget_schrabidium",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(1.5f).blinding().build());

    public static final RegistryObject<Item> NUGGET_SOLINIUM = ITEMS.register("nugget_solinium",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(1.75f).blinding().build());

    public static final RegistryObject<Item> NUGGET_EUPHEMIUM = ITEMS.register("nugget_euphemium",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(2.0f).build());

    public static final RegistryObject<Item> NUGGET_DINEUTRONIUM = ITEMS.register("nugget_dineutronium",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(3.0f).build());

    public static final RegistryObject<Item> NUGGET_NIOBIUM = ITEMS.register("nugget_niobium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_SILICON = ITEMS.register("nugget_silicon",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_ACTINIUM = ITEMS.register("nugget_actinium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NUGGET_COBALT = ITEMS.register("nugget_cobalt",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(0.4f).build());

    public static final RegistryObject<Item> NUGGET_CO60 = ITEMS.register("nugget_co60",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(4.5f).build());

    public static final RegistryObject<Item> NUGGET_STRONTIUM = ITEMS.register("nugget_strontium",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(1.2f).build());

    public static final RegistryObject<Item> NUGGET_SR90 = ITEMS.register("nugget_sr90",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(5.0f).hydroReactive().build());

    public static final RegistryObject<Item> NUGGET_PB209 = ITEMS.register("nugget_pb209",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(3.5f).blinding().build());

    public static final RegistryObject<Item> NUGGET_GH336 = ITEMS.register("nugget_gh336",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(2.8f).build());

    public static final RegistryObject<Item> NUGGET_AU198 = ITEMS.register("nugget_au198",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(2.2f).build());

    public static final RegistryObject<Item> NUGGET_RA226 = ITEMS.register("nugget_ra226",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(8.0f).build());

    public static final RegistryObject<Item> NUGGET_REIIUM = ITEMS.register("nugget_reiium",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(4.0f).build());

    public static final RegistryObject<Item> NUGGET_WEIDANIUM = ITEMS.register("nugget_weidanium",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(5.5f).build());

    public static final RegistryObject<Item> NUGGET_AUSTRALIUM = ITEMS.register("nugget_australium",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(6.0f).build());

    public static final RegistryObject<Item> NUGGET_AUSTRALIUM_LESSER = ITEMS.register("nugget_australium_lesser",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(3.0f).build());

    public static final RegistryObject<Item> NUGGET_AUSTRALIUM_GREATER = ITEMS.register("nugget_australium_greater",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(9.0f).build());

    public static final RegistryObject<Item> NUGGET_VERTICIUM = ITEMS.register("nugget_verticium",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(7.0f).build());

    public static final RegistryObject<Item> NUGGET_UNOBTAINIUM = ITEMS.register("nugget_unobtainium",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(10.0f).build());

    public static final RegistryObject<Item> NUGGET_UNOBTAINIUM_LESSER = ITEMS.register("nugget_unobtainium_lesser",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(5.0f).build());

    public static final RegistryObject<Item> NUGGET_UNOBTAINIUM_GREATER = ITEMS.register("nugget_unobtainium_greater",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(15.0f).build());

    public static final RegistryObject<Item> NUGGET_DAFFERGON = ITEMS.register("nugget_daffergon",
            () -> new HazardItem.Builder(new Item.Properties()).radiation(8.5f).build());

// ======================= UNSTABLE НУГГЕТ =======================

    public static final RegistryObject<Item> NUGGET_U238M2 = ITEMS.register("nugget_u238m2",
            () -> new ItemUnstable(60, 2000, NuclearBombType.UNSTABLE_SMALL,
                    new Item.Properties().fireResistant()));
    // ------------- Billets Items  --------------
    public static final RegistryObject<Item> BILLET_COBALT = ITEMS.register("billet_cobalt",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BILLET_SILICON = ITEMS.register("billet_silicon",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BILLET_TH232 = ITEMS.register("billet_th232",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(0.05)
                    .build());
    public static final RegistryObject<Item> BILLET_URANIUM = ITEMS.register("billet_uranium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(0.175)
                    .build());
    public static final RegistryObject<Item> BILLET_U233 = ITEMS.register("billet_u233",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(2.5)
                    .build());
    public static final RegistryObject<Item> BILLET_U235 = ITEMS.register("billet_u235",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(0.5)
                    .build());
    public static final RegistryObject<Item> BILLET_U238 = ITEMS.register("billet_u238",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(0.125)
                    .build());
    public static final RegistryObject<Item> BILLET_PLUTONIUM = ITEMS.register("billet_plutonium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(3.75)
                    .build());
    public static final RegistryObject<Item> BILLET_PU238 = ITEMS.register("billet_pu238",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(5)
                    .fire(1)
                    .build());


    public static final RegistryObject<Item> BILLET_PU239 = ITEMS.register("billet_pu239",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(2.5)
                    .build());
    public static final RegistryObject<Item> BILLET_PU240 = ITEMS.register("billet_pu240",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(3.75)
                    .build());
    public static final RegistryObject<Item> BILLET_PU241 = ITEMS.register("billet_pu241",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(12.5)
                    .build());
    public static final RegistryObject<Item> BILLET_PU_MIX = ITEMS.register("billet_pu_mix",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(3.125)
                    .build());
    public static final RegistryObject<Item> BILLET_AM241 = ITEMS.register("billet_am241",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(4.25)
                    .build());
    public static final RegistryObject<Item> BILLET_AM242 = ITEMS.register("billet_am242",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(4.75)
                    .build());
    public static final RegistryObject<Item> BILLET_AM_MIX = ITEMS.register("billet_am_mix",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(4.5)
                    .build());
    public static final RegistryObject<Item> BILLET_NEPTUNIUM = ITEMS.register("billet_neptunium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(1.25)
                    .build());
    public static final RegistryObject<Item> BILLET_POLONIUM = ITEMS.register("billet_polonium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(37.5)
                    .fire(1)
                    .build());

    public static final RegistryObject<Item> BILLET_TECHNETIUM = ITEMS.register("billet_technetium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(1.375)
                    .build());
    public static final RegistryObject<Item> BILLET_CO60 = ITEMS.register("billet_co60",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(15)
                    .fire(1)
                    .build());
    public static final RegistryObject<Item> BILLET_SR90 = ITEMS.register("billet_sr90",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(7.5)
                    .hydroReactive()
                    .fire(1)
                    .build());
    public static final RegistryObject<Item> BILLET_AU198 = ITEMS.register("billet_au198",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(250)
                    .fire(1)
                    .build());
    public static final RegistryObject<Item> BILLET_PB209 = ITEMS.register("billet_pb209",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(5000)
                    .blinding()
                    .fire(1)
                    .build());
    public static final RegistryObject<Item> BILLET_RA226 = ITEMS.register("billet_ra226",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(3.75)
                    .build());
    public static final RegistryObject<Item> BILLET_ACTINIUM = ITEMS.register("billet_actinium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(15)
                    .build());
    public static final RegistryObject<Item> BILLET_GH336 = ITEMS.register("billet_gh336",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(2.5)
                    .build());
    public static final RegistryObject<Item> BILLET_BERYLLIUM = ITEMS.register("billet_beryllium",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> BILLET_BISMUTH = ITEMS.register("billet_bismuth",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BILLET_ZIRCONIUM = ITEMS.register("billet_zirconium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BILLET_ZFB_BISMUTH = ITEMS.register("billet_zfb_bismuth",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BILLET_ZFB_PU241 = ITEMS.register("billet_zfb_pu241",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BILLET_ZFB_AM_MIX = ITEMS.register("billet_zfb_am_mix",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BILLET_SCHRABIDIUM = ITEMS.register("billet_schrabidium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(7.5)
                    .blinding()
                    .build());
    public static final RegistryObject<Item> BILLET_SOLINIUM = ITEMS.register("billet_solinium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(8.75)
                    .blinding()
                    .build());
    public static final RegistryObject<Item> BILLET_THORIUM_FUEL = ITEMS.register("billet_thorium_fuel",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(0.875)
                    .build());
    public static final RegistryObject<Item> BILLET_URANIUM_FUEL = ITEMS.register("billet_uranium_fuel",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(0.25)
                    .build());


    public static final RegistryObject<Item> BILLET_MOX_FUEL = ITEMS.register("billet_mox_fuel",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(1.25)
                    .build());
    public static final RegistryObject<Item> BILLET_PLUTONIUM_FUEL = ITEMS.register("billet_plutonium_fuel",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(2.125)
                    .build());
    public static final RegistryObject<Item> BILLET_NEPTUNIUM_FUEL = ITEMS.register("billet_neptunium_fuel",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(0.75)
                    .build());
    public static final RegistryObject<Item> BILLET_AMERICIUM_FUEL = ITEMS.register("billet_americium_fuel",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(2.375)
                    .build());
    public static final RegistryObject<Item> BILLET_LES = ITEMS.register("billet_les",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(2.925)
                    .build());
    public static final RegistryObject<Item> BILLET_SCHRABIDIUM_FUEL = ITEMS.register("billet_schrabidium_fuel",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(2.925)
                    .blinding()
                    .build());
    public static final RegistryObject<Item> BILLET_HES = ITEMS.register("billet_hes",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(2.925)
                    .build());
    public static final RegistryObject<Item> BILLET_PO210BE = ITEMS.register("billet_po210be",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(112.5)
                    .build());
    public static final RegistryObject<Item> BILLET_RA226BE = ITEMS.register("billet_ra226be",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(11.25)
                    .build());


    public static final RegistryObject<Item> BILLET_PU238BE = ITEMS.register("billet_pu238be",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(15)
                    .build());
    public static final RegistryObject<Item> BILLET_AUSTRALIUM = ITEMS.register("billet_australium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BILLET_AUSTRALIUM_LESSER = ITEMS.register("billet_australium_lesser",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BILLET_AUSTRALIUM_GREATER = ITEMS.register("billet_australium_greater",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BILLET_UNOBTAINIUM = ITEMS.register("billet_unobtainium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BILLET_YHARONITE = ITEMS.register("billet_yharonite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BILLET_BALEFIRE_GOLD = ITEMS.register("billet_balefire_gold",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(250)
                    .build());
    public static final RegistryObject<Item> BILLET_FLASHLEAD = ITEMS.register("billet_flashlead",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(6250)
                    .fire(1)
                    .build());
    public static final RegistryObject<Item> BILLET_NUCLEAR_WASTE = ITEMS.register("billet_nuclear_waste",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(7.5)
                    .build());

    public static final RegistryObject<Item> PILE_ROD_URANIUM = ITEMS.register("pile_rod_uranium",
            () -> new HazardItem.Builder(new Item.Properties())
                    .radiation(0.525)
                    .build());

// ------------- Tools --------------
    public static final RegistryObject<Item> REACHER = ITEMS.register("reacher",
            () -> new CustomLoreItem(new Item.Properties()));

    public static final RegistryObject<Item> GEIGER_COUNTER = ITEMS.register("geiger_counter",
            () -> new GeigerCounterItem(new Item.Properties()));

    public static final RegistryObject<Item> PLACEHOLDER = ITEMS.register("placeholder",
            () -> new Item(new Item.Properties()));
// ------------- Gas Mask --------------
    public static final RegistryObject<Item> M65_MASK = ITEMS.register("m65_mask",
            () -> new M65Item(new Item.Properties(), ArmorMaterials.IRON));

    public static final RegistryObject<Item> GAS_MASK_FILTER_MONO = ITEMS.register("gas_mask_filter_mono",
            () -> new FilterItem(new Item.Properties(), 12000));

    public static final RegistryObject<Item> GAS_MASK_FILTER = ITEMS.register("gas_mask_filter",
            () -> new FilterItem(new Item.Properties(), 18000));

    public static final RegistryObject<Item> GAS_MASK_FILTER_COMBO = ITEMS.register("gas_mask_filter_combo",
            () -> new FilterItem(new Item.Properties(), 24000));

    public static final RegistryObject<Item> GAS_MASK_FILTER_RADON = ITEMS.register("gas_mask_filter_radon",
            () -> new FilterItem(new Item.Properties(), 32000));
// ------------- Hazmat Suit --------------
    public static final RegistryObject<Item> HAZMAT_HELMET = ITEMS.register("hazmat_helmet",
            () -> new HazmatHeadItem(new Item.Properties(), HbmArmorMaterials.HAZMAT));
    public static final RegistryObject<Item> HAZMAT_CHESTPLATE = ITEMS.register("hazmat_chestplate",
            () -> new HazmatItem(new Item.Properties(), HbmArmorMaterials.HAZMAT, ArmorModHandler.plate_only));
    public static final RegistryObject<Item> HAZMAT_LEGGINGS = ITEMS.register("hazmat_leggings",
            () -> new HazmatItem(new Item.Properties(), HbmArmorMaterials.HAZMAT, ArmorModHandler.legs_only));
    public static final RegistryObject<Item> HAZMAT_BOOTS = ITEMS.register("hazmat_boots",
            () -> new HazmatItem(new Item.Properties(), HbmArmorMaterials.HAZMAT, ArmorModHandler.boots_only));

    public static final RegistryObject<Item> HAZMAT_HELMET_RED = ITEMS.register("hazmat_helmet_red",
            () -> new HazmatHeadRedItem(new Item.Properties(), HbmArmorMaterials.HAZMAT));
    public static final RegistryObject<Item> HAZMAT_CHESTPLATE_RED = ITEMS.register("hazmat_chestplate_red",
            () -> new HazmatRedItem(new Item.Properties(), HbmArmorMaterials.HAZMAT, ArmorModHandler.plate_only));
    public static final RegistryObject<Item> HAZMAT_LEGGINGS_RED = ITEMS.register("hazmat_leggings_red",
            () -> new HazmatRedItem(new Item.Properties(), HbmArmorMaterials.HAZMAT, ArmorModHandler.legs_only));
    public static final RegistryObject<Item> HAZMAT_BOOTS_RED = ITEMS.register("hazmat_boots_red",
            () -> new HazmatRedItem(new Item.Properties(), HbmArmorMaterials.HAZMAT, ArmorModHandler.boots_only));

    public static final RegistryObject<Item> HAZMAT_HELMET_GREY = ITEMS.register("hazmat_helmet_grey",
            () -> new HazmatHeadGreyItem(new Item.Properties(), HbmArmorMaterials.HAZMAT));
    public static final RegistryObject<Item> HAZMAT_CHESTPLATE_GREY = ITEMS.register("hazmat_chestplate_grey",
            () -> new HazmatGreyItem(new Item.Properties(), HbmArmorMaterials.HAZMAT, ArmorModHandler.plate_only));
    public static final RegistryObject<Item> HAZMAT_LEGGINGS_GREY = ITEMS.register("hazmat_leggings_grey",
            () -> new HazmatGreyItem(new Item.Properties(), HbmArmorMaterials.HAZMAT, ArmorModHandler.legs_only));
    public static final RegistryObject<Item> HAZMAT_BOOTS_GREY = ITEMS.register("hazmat_boots_grey",
            () -> new HazmatGreyItem(new Item.Properties(), HbmArmorMaterials.HAZMAT, ArmorModHandler.boots_only));

    public static void register(IEventBus eventBus) {

        ITEMS.register(eventBus);
    }
}
