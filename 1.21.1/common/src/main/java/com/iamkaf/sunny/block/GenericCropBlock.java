package com.iamkaf.sunny.block;

import com.iamkaf.sunny.crops.SunnyCropDefinition;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

public abstract class GenericCropBlock extends CropBlock {
    public static final int MAX_AGE = 5;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    private final SunnyCropDefinition.CropShape cropShape;

    public GenericCropBlock(Properties settings, SunnyCropDefinition.CropShape cropShape) {
        super(settings);
        this.cropShape = cropShape;
    }

    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public SunnyCropDefinition.CropShape getCropShape() {
        return cropShape;
    }
}
