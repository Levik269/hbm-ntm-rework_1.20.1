package com.hbm.nucleartech.datagen;

import com.hbm.nucleartech.HBM;
import com.hbm.nucleartech.block.RegisterBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {

        super(output, HBM.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        blockWithItem(RegisterBlocks.TRANSFORMER_HV_MV_GILDED_COPPER);
        blockWithItem(RegisterBlocks.TRANSFORMER_HV_MV_COPPER);
        blockWithItem(RegisterBlocks.TRANSFORMER_HV_MV_RED_COPPER);
        blockWithItem(RegisterBlocks.TRANSFORMER_HV_MV_GOLD);
        blockWithItem(RegisterBlocks.TRANSFORMER_HV_MV_RED_GOLD);
        blockWithItem(RegisterBlocks.TRANSFORMER_MV_LV_GILDED_COPPER);
        blockWithItem(RegisterBlocks.TRANSFORMER_MV_LV_COPPER);
        blockWithItem(RegisterBlocks.TRANSFORMER_MV_LV_RED_COPPER);
        blockWithItem(RegisterBlocks.TRANSFORMER_MV_LV_GOLD);
        blockWithItem(RegisterBlocks.TRANSFORMER_MV_LV_RED_GOLD);

        // провода LV
        blockWithItem(RegisterBlocks.CABLE_LV_COPPER);
        blockWithItem(RegisterBlocks.CABLE_LV_RED_COPPER);
        blockWithItem(RegisterBlocks.CABLE_LV_GOLD);
        blockWithItem(RegisterBlocks.CABLE_LV_RED_GOLD);
        blockWithItem(RegisterBlocks.CABLE_LV_GILDED_COPPER);
        // провода MV
        blockWithItem(RegisterBlocks.CABLE_MV_COPPER);
        blockWithItem(RegisterBlocks.CABLE_MV_RED_COPPER);
        blockWithItem(RegisterBlocks.CABLE_MV_GOLD);
        blockWithItem(RegisterBlocks.CABLE_MV_RED_GOLD);
        blockWithItem(RegisterBlocks.CABLE_MV_GILDED_COPPER);
        // провода HV
        blockWithItem(RegisterBlocks.CABLE_HV_COPPER);
        blockWithItem(RegisterBlocks.CABLE_HV_RED_COPPER);
        blockWithItem(RegisterBlocks.CABLE_HV_GOLD);
        blockWithItem(RegisterBlocks.CABLE_HV_RED_GOLD);
        blockWithItem(RegisterBlocks.CABLE_HV_GILDED_COPPER);

        blockWithItem(RegisterBlocks.TEST_GENERATOR);
        blockWithItem(RegisterBlocks.BATTERY_BLOCK);

        blockWithItem(RegisterBlocks.MISSILE_LAUNCHER);

        blockWithItem(RegisterBlocks.M350_CONCRETE);

        blockWithItem(RegisterBlocks.BLOCK_WASTE);

        blockWithItem(RegisterBlocks.BLOCK_TITANIUM);

        blockWithItem(RegisterBlocks.BLOCK_URANIUM);

        blockWithItem(RegisterBlocks.ORE_TITANIUM);
        blockWithItem(RegisterBlocks.DEEPSLATE_ORE_TITANIUM);

        blockWithItem(RegisterBlocks.ORE_URANIUM);
        blockWithItem(RegisterBlocks.DEEPSLATE_ORE_URANIUM);

        blockWithItem(RegisterBlocks.ORE_THORIUM);
        blockWithItem(RegisterBlocks.DEEPSLATE_ORE_THORIUM);

        blockWithItem(RegisterBlocks.ORE_COPPER);
        blockWithItem(RegisterBlocks.DEEPSLATE_ORE_COPPER);

        blockWithItem(RegisterBlocks.ORE_COBALT);
        blockWithItem(RegisterBlocks.DEEPSLATE_ORE_COBALT);


        blockWithItem(RegisterBlocks.RADIATION_DECONTAMINATOR,
                new ResourceLocation("hbm:block/radiation_decontaminator_top"),
                new ResourceLocation("hbm:block/radiation_decontaminator_side"),
                new ResourceLocation("hbm:block/radiation_decontaminator_side"),
                new ResourceLocation("hbm:block/radiation_decontaminator_side"),
                new ResourceLocation("hbm:block/radiation_decontaminator_side"));

        cubeBottomTopBlockWithItem(RegisterBlocks.WASTE_GRASS,
                new ResourceLocation("hbm:block/waste_grass_top"),
                new ResourceLocation("minecraft:block/dirt"),
                new ResourceLocation("hbm:block/waste_grass_side"));

        blockWithItem(RegisterBlocks.RAD_RESISTANT_BLOCK);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {

        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject, ResourceLocation bottom, ResourceLocation side, ResourceLocation top) {

//        System.err.println(blockRegistryObject.getId().getPath());
        simpleBlockWithItem(blockRegistryObject.get(), models().cube(blockRegistryObject.getId().getPath(), bottom, top, side, side, side, side));
    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject, ResourceLocation top, ResourceLocation bottom, ResourceLocation side, ResourceLocation front, ResourceLocation particle) {
        String name = blockRegistryObject.getId().getPath();

        // Create the model using orientable_with_bottom
        ModelFile model = models()
                .withExistingParent(name, mcLoc("block/orientable_with_bottom"))
                .texture("top", top)
                .texture("bottom", bottom)
                .texture("side", side)
                .texture("front", front)
                .texture("particle", particle);

        // Register blockstate with facing direction
        horizontalBlock(blockRegistryObject.get(), model);

        // Register the item model to point to the block model
        itemModels().getBuilder(name).parent(model);
    }

    private void cubeBottomTopBlockWithItem(RegistryObject<Block> blockRegistryObject, ResourceLocation top, ResourceLocation bottom, ResourceLocation side) {
        String name = blockRegistryObject.getId().getPath();

        ModelFile model = models()
                .cubeBottomTop(name, side, bottom, top);

        simpleBlockWithItem(blockRegistryObject.get(), model);
    }
}
