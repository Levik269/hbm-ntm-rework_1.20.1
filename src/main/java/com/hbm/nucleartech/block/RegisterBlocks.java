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

    public static final RegistryObject<Block> M350_CONCRETE = registerBlock("m350_concrete",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
                    .strength(1000.0f, 1200.0f)  // hardness=1000, resistance=1200
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
