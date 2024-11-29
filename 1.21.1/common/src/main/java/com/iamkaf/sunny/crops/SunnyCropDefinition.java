package com.iamkaf.sunny.crops;

import com.iamkaf.sunny.block.GenericCropBlock;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

public abstract class SunnyCropDefinition {
    final int nutrition;
    final float saturation;
    final boolean isVillagerFarmable;

    protected SunnyCropDefinition(int nutrition, float saturation, boolean isVillagerFarmable) {
        this.nutrition = nutrition;
        this.saturation = saturation;
        this.isVillagerFarmable = isVillagerFarmable;
    }

    protected SunnyCropDefinition(int nutrition, float saturation) {
        this.nutrition = nutrition;
        this.saturation = saturation;
        this.isVillagerFarmable = true;
    }

    public abstract @NotNull RegistrySupplier<Item> getCropItem();

    public abstract @NotNull RegistrySupplier<Item> getCropSeedItem();

    public abstract @NotNull RegistrySupplier<Block> getCropBlock();

    public abstract @NotNull String getId();

    public abstract @NotNull String getDisplayName();

    public abstract @NotNull RenderType getRenderType();


    public IntegerProperty getAgeProperty() {
        return GenericCropBlock.AGE;
    }

    public int getMaxAge() {
        return GenericCropBlock.MAX_AGE;
    }

    public int getNutrition() {
        return nutrition;
    }

    public float getSaturation() {
        return saturation;
    }

    public boolean isVillagerFarmable() {
        return isVillagerFarmable;
    }

    abstract public CropShape getShape();

    public enum CropShape {
        CROP,
        CROSS
    }
}
