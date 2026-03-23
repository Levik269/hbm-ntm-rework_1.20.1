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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.hbm.nucleartech.block.custom.MissileLauncherBlock;
import com.hbm.nucleartech.block.entity.MissileLauncherBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;

import com.hbm.nucleartech.block.custom.CableBlock;
import com.hbm.nucleartech.block.entity.CableBlockEntity;
import com.hbm.nucleartech.energy.CableTier;

import com.hbm.nucleartech.block.custom.TestGeneratorBlock;
import com.hbm.nucleartech.block.entity.TestGeneratorBlockEntity;
import com.hbm.nucleartech.block.custom.BatteryBlock;
import com.hbm.nucleartech.block.entity.BatteryBlockEntity;

import com.hbm.nucleartech.block.custom.TransformerBlock;
import com.hbm.nucleartech.block.entity.TransformerBlockEntity;
import com.hbm.nucleartech.energy.TransformerTier;

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

    public static final RegistryObject<Block> CONCRETE_SMOOTH = registerBlock("concrete_smooth",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(15.0f, 84.0f)
            ));

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

    public static final RegistryObject<Block> CONCRETE_LIGHT_GRAY = registerBlock("concrete_light_gray",
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

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
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

    public static final RegistryObject<Block> TRANSFORMER_HV_MV_COPPER =
            registerBlock("transformer_hv_mv_copper", () -> new TransformerBlock(TransformerTier.HV_MV_COPPER,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(3.0f).noOcclusion()));
    public static final RegistryObject<Block> TRANSFORMER_HV_MV_RED_COPPER =
            registerBlock("transformer_hv_mv_red_copper", () -> new TransformerBlock(TransformerTier.HV_MV_RED_COPPER,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(3.0f).noOcclusion()));
    public static final RegistryObject<Block> TRANSFORMER_HV_MV_GOLD =
            registerBlock("transformer_hv_mv_gold", () -> new TransformerBlock(TransformerTier.HV_MV_GOLD,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(3.0f).noOcclusion()));
    public static final RegistryObject<Block> TRANSFORMER_HV_MV_RED_GOLD =
            registerBlock("transformer_hv_mv_red_gold", () -> new TransformerBlock(TransformerTier.HV_MV_RED_GOLD,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(3.0f).noOcclusion()));
    public static final RegistryObject<Block> TRANSFORMER_HV_MV_GILDED_COPPER =
            registerBlock("transformer_hv_mv_gilded_copper", () -> new TransformerBlock(TransformerTier.HV_MV_GILDED_COPPER,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(3.0f).noOcclusion()));

    public static final RegistryObject<Block> TRANSFORMER_MV_LV_COPPER =
            registerBlock("transformer_mv_lv_copper", () -> new TransformerBlock(TransformerTier.MV_LV_COPPER,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(3.0f).noOcclusion()));
    public static final RegistryObject<Block> TRANSFORMER_MV_LV_RED_COPPER =
            registerBlock("transformer_mv_lv_red_copper", () -> new TransformerBlock(TransformerTier.MV_LV_RED_COPPER,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(3.0f).noOcclusion()));
    public static final RegistryObject<Block> TRANSFORMER_MV_LV_GOLD =
            registerBlock("transformer_mv_lv_gold", () -> new TransformerBlock(TransformerTier.MV_LV_GOLD,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(3.0f).noOcclusion()));
    public static final RegistryObject<Block> TRANSFORMER_MV_LV_RED_GOLD =
            registerBlock("transformer_mv_lv_red_gold", () -> new TransformerBlock(TransformerTier.MV_LV_RED_GOLD,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(3.0f).noOcclusion()));
    public static final RegistryObject<Block> TRANSFORMER_MV_LV_GILDED_COPPER =
            registerBlock("transformer_mv_lv_gilded_copper", () -> new TransformerBlock(TransformerTier.MV_LV_GILDED_COPPER,
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(3.0f).noOcclusion()));

    public static final RegistryObject<BlockEntityType<TransformerBlockEntity>> TRANSFORMER_BE =
            BLOCK_ENTITIES.register("transformer", () -> BlockEntityType.Builder.of(
                    (pos, state) -> {
                        Block block = state.getBlock();
                        if (block instanceof TransformerBlock tb)
                            return new TransformerBlockEntity(pos, state, tb.getTier());
                        return new TransformerBlockEntity(pos, state, TransformerTier.HV_MV_COPPER);
                    },
                    TRANSFORMER_HV_MV_COPPER.get(), TRANSFORMER_HV_MV_RED_COPPER.get(),
                    TRANSFORMER_HV_MV_GOLD.get(), TRANSFORMER_HV_MV_RED_GOLD.get(),
                    TRANSFORMER_HV_MV_GILDED_COPPER.get(),
                    TRANSFORMER_MV_LV_COPPER.get(), TRANSFORMER_MV_LV_RED_COPPER.get(),
                    TRANSFORMER_MV_LV_GOLD.get(), TRANSFORMER_MV_LV_RED_GOLD.get(),
                    TRANSFORMER_MV_LV_GILDED_COPPER.get()
            ).build(null));


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
