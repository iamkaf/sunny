package com.iamkaf.sunny.neoforge.datagen;

import com.iamkaf.sunny.Sunny;
import com.iamkaf.sunny.crops.Crops;
import com.iamkaf.sunny.crops.SunnyCropDefinition;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Sunny.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (var def : Crops.ALL_CROPS) {
            addCropBlock(
                    def.getId() + "_crop",
                    def.getCropBlock().get(),
                    def.getAgeProperty(),
                    def.getRenderType().name,
                    def.getShape()
            );
        }
    }

    private void blockWithItem(Supplier<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }

    private <T extends Block> void blockItem(String id, Supplier<T> block) {
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(modLoc("block/" + id)));
    }

    public void addCropBlock(String id, Block block, IntegerProperty AGE, String renderType,
            SunnyCropDefinition.CropShape shape) {
        this.getVariantBuilder(block).forAllStates(state -> {
            var path = id + "_stage" + state.getValue(AGE);
            if (shape == SunnyCropDefinition.CropShape.CROSS) {
                return ConfiguredModel.builder()
                        .modelFile(models().cross(path, modLoc("block/" + path)).renderType(renderType))
                        .build();
            } else {
                return ConfiguredModel.builder()
                        .modelFile(models().crop(path, modLoc("block/" + path)).renderType(renderType))
                        .build();
            }
        });
    }
}
