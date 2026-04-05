package com.hbm.nucleartech.block;

import com.hbm.nucleartech.HBM;
import com.hbm.nucleartech.block.custom.DeconRadBlock;
import com.hbm.nucleartech.block.custom.RadResistantBlock;
import com.hbm.nucleartech.hazard.HazardBlock;
import com.hbm.nucleartech.hazard.HazardBlockItem;
import com.hbm.nucleartech.item.RegisterItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.hbm.nucleartech.block.custom.MissileLauncherBlock;
import com.hbm.nucleartech.block.entity.MissileLauncherBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.registries.DeferredRegister;

import com.hbm.nucleartech.block.custom.CableBlock;
import com.hbm.nucleartech.block.entity.CableBlockEntity;
import com.hbm.nucleartech.energy.CableTier;

import com.hbm.nucleartech.block.custom.TestGeneratorBlock;
import com.hbm.nucleartech.block.entity.TestGeneratorBlockEntity;
import com.hbm.nucleartech.block.custom.BatteryBlock;
import com.hbm.nucleartech.block.entity.BatteryBlockEntity;


import com.hbm.nucleartech.block.custom.EnergySwitchBlock;
import com.hbm.nucleartech.block.custom.LvTransformerBlock;
import com.hbm.nucleartech.block.custom.ConnectorCableBlock;
import com.hbm.nucleartech.block.custom.FluidValveBlock;
import com.hbm.nucleartech.block.entity.LvTransformerBlockEntity;
import com.hbm.nucleartech.block.entity.FluidValveBlockEntity;
import com.hbm.nucleartech.block.custom.SubstationBlock;
import com.hbm.nucleartech.block.custom.AnalogMeterBlock;
import com.hbm.nucleartech.block.custom.AnalogBarometerBlock;
import com.hbm.nucleartech.block.entity.AnalogBarometerBlockEntity;
import com.hbm.nucleartech.block.entity.EnergySwitchBlockEntity;
import com.hbm.nucleartech.block.entity.SubstationBlockEntity;
import com.hbm.nucleartech.block.entity.AnalogMeterBlockEntity;
import com.hbm.nucleartech.energy.SubstationType;

import com.hbm.nucleartech.block.custom.PipeBlock;
import com.hbm.nucleartech.block.custom.FluidCompressorBlock;
import com.hbm.nucleartech.block.custom.FluidSourceBlock;
import com.hbm.nucleartech.block.custom.FluidTankBlock;
import com.hbm.nucleartech.block.entity.PipeBlockEntity;
import com.hbm.nucleartech.block.entity.FluidCompressorBlockEntity;
import com.hbm.nucleartech.block.entity.FluidSourceBlockEntity;
import com.hbm.nucleartech.block.entity.FluidTankBlockEntity;
import com.hbm.nucleartech.fluid.PipeMaterial;

import java.util.function.Supplier;

public class RegisterBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, HBM.MOD_ID);



    public static final RegistryObject<Block> BLOCK_WASTE = registerHazardBlock(4500, "block_waste",
            () -> new HazardBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5.0f, 10.0f),
                    4500
            ));

    public static final RegistryObject<Block> BLOCK_TITANIUM = registerBlock("block_titanium",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5.0f, 10.0f)
            ));

    public static final RegistryObject<Block> BLOCK_URANIUM = registerHazardBlock(3.5, "block_uranium",
            () -> new HazardBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)
                    .strength(5.0f, 10.0f),
                    3.5
            ));

    public static final RegistryObject<Block> ORE_TITANIUM = registerBlock("ore_titanium",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)
                    .strength(5.0f, 10.0f),
                    UniformInt.of(3, 6)
            ));

    public static final RegistryObject<Block> DEEPSLATE_ORE_TITANIUM = registerBlock("deepslate_ore_titanium",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)
                    .strength(8.0f, 15.0f),
                    UniformInt.of(3, 7)
            ));

    public static final RegistryObject<Block> ORE_COPPER = registerBlock("ore_copper",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)
                    .strength(4.0f, 8.0f),
                    UniformInt.of(2, 5)
            ));

    public static final RegistryObject<Block> DEEPSLATE_ORE_COPPER = registerBlock("deepslate_ore_copper",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)
                    .strength(6.0f, 12.0f),
                    UniformInt.of(2, 6)
            ));

    public static final RegistryObject<Block> ORE_URANIUM = registerHazardBlock(0.003, "ore_uranium",
            () -> new HazardBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)
                    .strength(5.0f, 10.0f),
                    UniformInt.of(2, 4),
                    0.003
            ));

    public static final RegistryObject<Block> DEEPSLATE_ORE_URANIUM = registerHazardBlock(0.0035, "deepslate_ore_uranium",
            () -> new HazardBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE)
                    .strength(8.0f, 15.0f),
                    UniformInt.of(2, 5),
                    0.0035
            ));

    public static final RegistryObject<Block> ORE_THORIUM = registerHazardBlock(0.002, "ore_thorium",
            () -> new HazardBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)
                    .strength(4.0f, 8.0f),
                    UniformInt.of(2, 5),
                    0.002
            ));

    public static final RegistryObject<Block> DEEPSLATE_ORE_THORIUM = registerHazardBlock(0.0025, "deepslate_ore_thorium",
            () -> new HazardBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)
                    .strength(6.0f, 12.0f),
                    UniformInt.of(2, 6),
                    0.0025
            ));

    public static final RegistryObject<Block> ORE_COBALT = registerBlock("ore_cobalt",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)
                    .strength(4.0f, 8.0f),
                    UniformInt.of(2, 5)  // опыт при добыче: от 2 до 5
            ));

    public static final RegistryObject<Block> DEEPSLATE_ORE_COBALT = registerBlock("deepslate_ore_cobalt",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)
                    .strength(6.0f, 12.0f),
                    UniformInt.of(2, 6)
            ));
// ------------------------------------- Concrete variants (M350) -------------------------------------
    public static final RegistryObject<Block> CONCRETE = registerBlock("concrete",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                    .strength(15.0f, 160.0f)
            ));

    public static final RegistryObject<Block> BRICK_CONCRETE = registerBlock("brick_concrete",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                    .strength(15.0f, 160.0f)
            ));

    public static final RegistryObject<Block> BRICK_CONCRETE_MOSSY = registerBlock("brick_concrete_mossy",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                    .strength(15.0f, 160.0f)
            ));

    public static final RegistryObject<Block> BRICK_CONCRETE_CRACKED = registerBlock("brick_concrete_cracked",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                    .strength(15.0f, 60.0f)
            ));

    public static final RegistryObject<Block> BRICK_CONCRETE_BROKEN = registerBlock("brick_concrete_broken",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                    .strength(15.0f, 45.0f)
            ));

    public static final RegistryObject<Block> BRICK_CONCRETE_MARKED = registerBlock("brick_concrete_marked",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                    .strength(15.0f, 160.0f)
            ));

// ------------------------------------- Concrete variants (pDT=15, pER=84) on Stone base -------------------------------------



    // Colored concrete blocks
    public static final RegistryObject<Block> CONCRETE_WHITE = registerBlock("concrete_white",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 84.0f)
            ));

    public static final RegistryObject<Block> CONCRETE_ORANGE = registerBlock("concrete_orange",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 84.0f)
            ));

    public static final RegistryObject<Block> CONCRETE_MAGENTA = registerBlock("concrete_magenta",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 84.0f)
            ));

    public static final RegistryObject<Block> CONCRETE_LIGHT_BLUE = registerBlock("concrete_light_blue",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 84.0f)
            ));

    public static final RegistryObject<Block> CONCRETE_YELLOW = registerBlock("concrete_yellow",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 84.0f)
            ));

    public static final RegistryObject<Block> CONCRETE_LIME = registerBlock("concrete_lime",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 84.0f)
            ));

    public static final RegistryObject<Block> CONCRETE_PINK = registerBlock("concrete_pink",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 84.0f)
            ));

    public static final RegistryObject<Block> CONCRETE_GRAY = registerBlock("concrete_gray",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 84.0f)
            ));



    public static final RegistryObject<Block> CONCRETE_CYAN = registerBlock("concrete_cyan",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 84.0f)
            ));

    public static final RegistryObject<Block> CONCRETE_PURPLE = registerBlock("concrete_purple",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 84.0f)
            ));

    public static final RegistryObject<Block> CONCRETE_BLUE = registerBlock("concrete_blue",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 84.0f)
            ));

    public static final RegistryObject<Block> CONCRETE_BROWN = registerBlock("concrete_brown",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 84.0f)
            ));

    public static final RegistryObject<Block> CONCRETE_GREEN = registerBlock("concrete_green",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 84.0f)
            ));

    public static final RegistryObject<Block> CONCRETE_RED = registerBlock("concrete_red",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 84.0f)
            ));

    public static final RegistryObject<Block> CONCRETE_BLACK = registerBlock("concrete_black",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 84.0f)
            ));

    /*
    // ------------------------------------- Concrete Stairs ----------------------------------
    public static final RegistryObject<StairBlock> CONCRETE_STAIRS = registerBlock("concrete_stairs",
            () -> new StairBlock(() -> CONCRETE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                            .strength(15.0f, 160.0f)
            ));
    
    public static final RegistryObject<StairBlock> BRICK_CONCRETE_STAIRS = registerBlock("brick_concrete_stairs",
            () -> new StairBlock(() -> BRICK_CONCRETE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                            .strength(15.0f, 160.0f)
            ));
    
    public static final RegistryObject<StairBlock> BRICK_CONCRETE_MOSSY_STAIRS = registerBlock("brick_concrete_mossy_stairs",
            () -> new StairBlock(() -> BRICK_CONCRETE_MOSSY.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                            .strength(15.0f, 160.0f)
            ));
    
    public static final RegistryObject<StairBlock> BRICK_CONCRETE_CRACKED_STAIRS = registerBlock("brick_concrete_cracked_stairs",
            () -> new StairBlock(() -> BRICK_CONCRETE_CRACKED.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                            .strength(15.0f, 60.0f)
            ));
    
    public static final RegistryObject<StairBlock> BRICK_CONCRETE_BROKEN_STAIRS = registerBlock("brick_concrete_broken_stairs",
            () -> new StairBlock(() -> BRICK_CONCRETE_BROKEN.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                            .strength(15.0f, 45.0f)
            ));
    
    public static final RegistryObject<StairBlock> BRICK_CONCRETE_MARKED_STAIRS = registerBlock("brick_concrete_marked_stairs",
            () -> new StairBlock(() -> BRICK_CONCRETE_MARKED.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                            .strength(15.0f, 160.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_SMOOTH_STAIRS = registerBlock("concrete_smooth_stairs",
            () -> new StairBlock(() -> CONCRETE_SMOOTH.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_WHITE_STAIRS = registerBlock("concrete_white_stairs",
            () -> new StairBlock(() -> CONCRETE_WHITE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_ORANGE_STAIRS = registerBlock("concrete_orange_stairs",
            () -> new StairBlock(() -> CONCRETE_ORANGE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_MAGENTA_STAIRS = registerBlock("concrete_magenta_stairs",
            () -> new StairBlock(() -> CONCRETE_MAGENTA.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_LIGHT_BLUE_STAIRS = registerBlock("concrete_light_blue_stairs",
            () -> new StairBlock(() -> CONCRETE_LIGHT_BLUE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_YELLOW_STAIRS = registerBlock("concrete_yellow_stairs",
            () -> new StairBlock(() -> CONCRETE_YELLOW.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_LIME_STAIRS = registerBlock("concrete_lime_stairs",
            () -> new StairBlock(() -> CONCRETE_LIME.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_PINK_STAIRS = registerBlock("concrete_pink_stairs",
            () -> new StairBlock(() -> CONCRETE_PINK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_GRAY_STAIRS = registerBlock("concrete_gray_stairs",
            () -> new StairBlock(() -> CONCRETE_GRAY.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_LIGHT_GRAY_STAIRS = registerBlock("concrete_light_gray_stairs",
            () -> new StairBlock(() -> CONCRETE_LIGHT_GRAY.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_CYAN_STAIRS = registerBlock("concrete_cyan_stairs",
            () -> new StairBlock(() -> CONCRETE_CYAN.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_PURPLE_STAIRS = registerBlock("concrete_purple_stairs",
            () -> new StairBlock(() -> CONCRETE_PURPLE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_BLUE_STAIRS = registerBlock("concrete_blue_stairs",
            () -> new StairBlock(() -> CONCRETE_BLUE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_BROWN_STAIRS = registerBlock("concrete_brown_stairs",
            () -> new StairBlock(() -> CONCRETE_BROWN.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_GREEN_STAIRS = registerBlock("concrete_green_stairs",
            () -> new StairBlock(() -> CONCRETE_GREEN.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_RED_STAIRS = registerBlock("concrete_red_stairs",
            () -> new StairBlock(() -> CONCRETE_RED.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    
    public static final RegistryObject<StairBlock> CONCRETE_BLACK_STAIRS = registerBlock("concrete_black_stairs",
            () -> new StairBlock(() -> CONCRETE_BLACK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)
                            .strength(15.0f, 84.0f)
            ));
    */

    // ------------------------------------- Other blocks -------------------------------------

    public static final RegistryObject<Block> REINFORCED_STONE = registerBlock("reinforced_stone",
            () -> new RadResistantBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 180.0f)
                    , 11.34f, 0.07f, 1f  // same as lead
            ));

    public static final RegistryObject<Block> BRICK_COMPOUND = registerBlock("brick_compound",
            () -> new RadResistantBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 240.0f)
                    , 15.0f, 0.05f, 1f  // stronger than lead
            ));

    public static final RegistryObject<Block> BRICK_OBSIDIAN = registerBlock("brick_obsidian",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 72.0f)
            ));

    public static final RegistryObject<Block> CMB_BRICK = registerBlock("cmb_brick",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                    .strength(50.0f, 3000.0f)
            ));

    public static final RegistryObject<Block> CMB_BRICK_REINFORCED = registerBlock("cmb_brick_reinforced",
            () -> new RadResistantBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                    .strength(50.0f, 30000.0f)
                    , 50.0f, 0.01f, 1f  // extremely strong radiation resistance
            ));

    public static final RegistryObject<Block> RADIATION_DECONTAMINATOR = registerBlock("radiation_decontaminator",
            () -> new DeconRadBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5.0f, 10.0f)
            ));

    public static final RegistryObject<Block> WASTE_GRASS = registerBlock("waste_grass",
            () -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));

    public static final RegistryObject<Block> RAD_RESISTANT_BLOCK = registerBlock("rad_resistant_block",
            () -> new RadResistantBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5.0f, 10.0f)
                    , 1f, 0.085f, 1f
            )); // has values of lead ^^ ~100% resistance (water is 1f, 0.085f. Lead is 11.34f, 0.07f)

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {

        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {

        return RegisterItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block>RegistryObject<T> registerHazardBlock(double radiation, String name, Supplier<T> block) {

        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerHazardBlockItem(radiation, name, toReturn);
        return toReturn;
    }

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = // ENTITY HERE
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HBM.MOD_ID);

    public static final RegistryObject<Block> MISSILE_LAUNCHER = registerBlock("missile_launcher",
            () -> new MissileLauncherBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5.0f, 10.0f)));

    public static final RegistryObject<Block> CABLE_LV_COPPER =
            registerBlock("cable_lv_copper", () -> new CableBlock(CableTier.LV_COPPER,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).noOcclusion()));
    public static final RegistryObject<Block> CABLE_LV_RED_COPPER =
            registerBlock("cable_lv_red_copper", () -> new CableBlock(CableTier.LV_RED_COPPER,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).noOcclusion()));
    public static final RegistryObject<Block> CABLE_LV_GOLD =
            registerBlock("cable_lv_gold", () -> new CableBlock(CableTier.LV_GOLD,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).noOcclusion()));
    public static final RegistryObject<Block> CABLE_LV_RED_GOLD =
            registerBlock("cable_lv_red_gold", () -> new CableBlock(CableTier.LV_RED_GOLD,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).noOcclusion()));
    public static final RegistryObject<Block> CABLE_LV_GILDED_COPPER =
            registerBlock("cable_lv_gilded_copper", () -> new CableBlock(CableTier.LV_GILDED_COPPER,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).noOcclusion()));

    // провода MV
    public static final RegistryObject<Block> CABLE_MV_COPPER =
            registerBlock("cable_mv_copper", () -> new CableBlock(CableTier.MV_COPPER,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.5f).noOcclusion()));
    public static final RegistryObject<Block> CABLE_MV_RED_COPPER =
            registerBlock("cable_mv_red_copper", () -> new CableBlock(CableTier.MV_RED_COPPER,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.5f).noOcclusion()));
    public static final RegistryObject<Block> CABLE_MV_GOLD =
            registerBlock("cable_mv_gold", () -> new CableBlock(CableTier.MV_GOLD,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.5f).noOcclusion()));
    public static final RegistryObject<Block> CABLE_MV_RED_GOLD =
            registerBlock("cable_mv_red_gold", () -> new CableBlock(CableTier.MV_RED_GOLD,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.5f).noOcclusion()));
    public static final RegistryObject<Block> CABLE_MV_GILDED_COPPER =
            registerBlock("cable_mv_gilded_copper", () -> new CableBlock(CableTier.MV_GILDED_COPPER,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.5f).noOcclusion()));

    // провода HV
    public static final RegistryObject<Block> CABLE_HV_COPPER =
            registerBlock("cable_hv_copper", () -> new CableBlock(CableTier.HV_COPPER,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(2.0f).noOcclusion()));
    public static final RegistryObject<Block> CABLE_HV_RED_COPPER =
            registerBlock("cable_hv_red_copper", () -> new CableBlock(CableTier.HV_RED_COPPER,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(2.0f).noOcclusion()));
    public static final RegistryObject<Block> CABLE_HV_GOLD =
            registerBlock("cable_hv_gold", () -> new CableBlock(CableTier.HV_GOLD,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(2.0f).noOcclusion()));
    public static final RegistryObject<Block> CABLE_HV_RED_GOLD =
            registerBlock("cable_hv_red_gold", () -> new CableBlock(CableTier.HV_RED_GOLD,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(2.0f).noOcclusion()));
    public static final RegistryObject<Block> CABLE_HV_GILDED_COPPER =
            registerBlock("cable_hv_gilded_copper", () -> new CableBlock(CableTier.HV_GILDED_COPPER,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(2.0f).noOcclusion()));
    // тестовый генератор
    public static final RegistryObject<Block> TEST_GENERATOR = registerBlock("test_generator",
            () -> new TestGeneratorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(3.0f)));

    public static final RegistryObject<BlockEntityType<TestGeneratorBlockEntity>> TEST_GENERATOR_BE =
            BLOCK_ENTITIES.register("test_generator", () -> BlockEntityType.Builder
                    .of(TestGeneratorBlockEntity::new, TEST_GENERATOR.get()).build(null));

    // батарея
    public static final RegistryObject<Block> BATTERY_BLOCK = registerBlock("battery_block",
            () -> new BatteryBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(3.0f)));

    public static final RegistryObject<BlockEntityType<BatteryBlockEntity>> BATTERY_BE =
            BLOCK_ENTITIES.register("battery_block", () -> BlockEntityType.Builder
                    .of(BatteryBlockEntity::new, BATTERY_BLOCK.get()).build(null));

    // общий блок-энтити для всех проводов
    public static final RegistryObject<BlockEntityType<CableBlockEntity>> CABLE_BE =
            BLOCK_ENTITIES.register("cable", () -> BlockEntityType.Builder.of(
                    (pos, state) -> {
                        // определяем тир по блоку
                        Block block = state.getBlock();
                        if (block instanceof CableBlock cable)
                            return new CableBlockEntity(pos, state, cable.getTier());
                        return new CableBlockEntity(pos, state, CableTier.LV_COPPER);
                    },
                    CABLE_LV_COPPER.get(), CABLE_LV_RED_COPPER.get(), CABLE_LV_GOLD.get(),
                    CABLE_LV_RED_GOLD.get(), CABLE_LV_GILDED_COPPER.get(),
                    CABLE_MV_COPPER.get(), CABLE_MV_RED_COPPER.get(), CABLE_MV_GOLD.get(),
                    CABLE_MV_RED_GOLD.get(), CABLE_MV_GILDED_COPPER.get(),
                    CABLE_HV_COPPER.get(), CABLE_HV_RED_COPPER.get(), CABLE_HV_GOLD.get(),
                    CABLE_HV_RED_GOLD.get(), CABLE_HV_GILDED_COPPER.get()
            ).build(null));

    // выключатель
    public static final RegistryObject<Block> ENERGY_SWITCH = registerBlock("energy_switch",
            () -> new EnergySwitchBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(2.0f)));
    public static final RegistryObject<BlockEntityType<EnergySwitchBlockEntity>> ENERGY_SWITCH_BE =
            BLOCK_ENTITIES.register("energy_switch", () -> BlockEntityType.Builder
                    .of(EnergySwitchBlockEntity::new, ENERGY_SWITCH.get()).build(null));

    // подстанции
    public static final RegistryObject<Block> SUBSTATION_HV_MV = registerBlock("substation_hv_mv",
            () -> new SubstationBlock(SubstationType.HV_MV,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(4.0f)));
    public static final RegistryObject<Block> SUBSTATION_MV_LV = registerBlock("substation_mv_lv",
            () -> new SubstationBlock(SubstationType.MV_LV,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(4.0f)));
    public static final RegistryObject<BlockEntityType<SubstationBlockEntity>> SUBSTATION_BE =
            BLOCK_ENTITIES.register("substation", () -> BlockEntityType.Builder.of(
                    (pos, state) -> {
                        Block block = state.getBlock();
                        if (block instanceof SubstationBlock sb)
                            return new SubstationBlockEntity(pos, state, sb.getSubstationType());
                        return new SubstationBlockEntity(pos, state, SubstationType.HV_MV);
                    },
                    SUBSTATION_HV_MV.get(), SUBSTATION_MV_LV.get()
            ).build(null));

    // аналоговый мультиметр
    public static final RegistryObject<Block> ANALOG_METER = registerBlock("analog_meter",
            () -> new AnalogMeterBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(2.0f)));
    public static final RegistryObject<BlockEntityType<AnalogMeterBlockEntity>> ANALOG_METER_BE =
            BLOCK_ENTITIES.register("analog_meter", () -> BlockEntityType.Builder
                    .of(AnalogMeterBlockEntity::new, ANALOG_METER.get()).build(null));

    public static final RegistryObject<Block> ANALOG_BAROMETER = registerBlock("analog_barometer",
            () -> new AnalogBarometerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(2.0f)));
    public static final RegistryObject<BlockEntityType<AnalogBarometerBlockEntity>> ANALOG_BAROMETER_BE =
            BLOCK_ENTITIES.register("analog_barometer", () -> BlockEntityType.Builder
                    .of(AnalogBarometerBlockEntity::new, ANALOG_BAROMETER.get()).build(null));

    // трубы
    public static final RegistryObject<Block> PIPE_TITANIUM = registerBlock("pipe_titanium",
            () -> new PipeBlock(PipeMaterial.TITANIUM,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.5f).noOcclusion()));
    public static final RegistryObject<Block> PIPE_STEEL = registerBlock("pipe_steel",
            () -> new PipeBlock(PipeMaterial.STEEL,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(2.0f).noOcclusion()));
    public static final RegistryObject<BlockEntityType<PipeBlockEntity>> PIPE_BE =
            BLOCK_ENTITIES.register("pipe", () -> BlockEntityType.Builder.of(
                    (pos, state) -> {
                        Block block = state.getBlock();
                        if (block instanceof PipeBlock pb)
                            return new PipeBlockEntity(pos, state, pb.getMaterial());
                        return new PipeBlockEntity(pos, state, PipeMaterial.TITANIUM);
                    },
                    PIPE_TITANIUM.get(), PIPE_STEEL.get()
            ).build(null));

    // компрессор
    public static final RegistryObject<Block> FLUID_COMPRESSOR = registerBlock("fluid_compressor",
            () -> new FluidCompressorBlock(
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(3.0f)));
    public static final RegistryObject<BlockEntityType<FluidCompressorBlockEntity>> FLUID_COMPRESSOR_BE =
            BLOCK_ENTITIES.register("fluid_compressor", () -> BlockEntityType.Builder
                    .of(FluidCompressorBlockEntity::new, FLUID_COMPRESSOR.get()).build(null));

    // источник жидкости
    public static final RegistryObject<Block> FLUID_SOURCE = registerBlock("fluid_source",
            () -> new FluidSourceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(2.0f)));
    public static final RegistryObject<BlockEntityType<FluidSourceBlockEntity>> FLUID_SOURCE_BE =
            BLOCK_ENTITIES.register("fluid_source", () -> BlockEntityType.Builder
                    .of(FluidSourceBlockEntity::new, FLUID_SOURCE.get()).build(null));
    // бак
    public static final RegistryObject<Block> FLUID_TANK = registerBlock("fluid_tank",
            () -> new FluidTankBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(2.0f)));
    public static final RegistryObject<BlockEntityType<FluidTankBlockEntity>> FLUID_TANK_BE =
            BLOCK_ENTITIES.register("fluid_tank", () -> BlockEntityType.Builder
                    .of(FluidTankBlockEntity::new, FLUID_TANK.get()).build(null));

    // LV трансформатор
    public static final RegistryObject<Block> LV_TRANSFORMER = registerBlock("lv_transformer",
            () -> new LvTransformerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(3.0f)));
    public static final RegistryObject<BlockEntityType<LvTransformerBlockEntity>> LV_TRANSFORMER_BE =
            BLOCK_ENTITIES.register("lv_transformer", () -> BlockEntityType.Builder
                    .of(LvTransformerBlockEntity::new, LV_TRANSFORMER.get()).build(null));

    // соединительный провод
    // соединительный провод
    public static final RegistryObject<Block> CONNECTOR_CABLE = registerBlock("connector_cable",
            () -> new ConnectorCableBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(1.0f).noOcclusion()));
    public static final RegistryObject<BlockEntityType<CableBlockEntity>> CONNECTOR_CABLE_BE =
            BLOCK_ENTITIES.register("connector_cable", () -> BlockEntityType.Builder
                    .of((pos, state) -> new CableBlockEntity(pos, state, CableTier.CONNECTOR_COPPER),
                            CONNECTOR_CABLE.get()).build(null));

    // вентиль
    public static final RegistryObject<Block> FLUID_VALVE = registerBlock("fluid_valve",
            () -> new FluidValveBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(2.0f)));
    public static final RegistryObject<BlockEntityType<FluidValveBlockEntity>> FLUID_VALVE_BE =
            BLOCK_ENTITIES.register("fluid_valve", () -> BlockEntityType.Builder
                    .of(FluidValveBlockEntity::new, FLUID_VALVE.get()).build(null));


    public static final RegistryObject<BlockEntityType<MissileLauncherBlockEntity>> MISSILE_LAUNCHER_BE =
            BLOCK_ENTITIES.register("missile_launcher", () ->
                    BlockEntityType.Builder.of(MissileLauncherBlockEntity::new,
                            MISSILE_LAUNCHER.get()).build(null));



    private static <T extends Block>RegistryObject<Item> registerHazardBlockItem(double radiation, String name, RegistryObject<T> block) {

        return RegisterItems.ITEMS.register(name, () -> new HazardBlockItem(radiation, block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {

        BLOCKS.register(eventBus);
        BLOCK_ENTITIES.register(eventBus);
    }
}
