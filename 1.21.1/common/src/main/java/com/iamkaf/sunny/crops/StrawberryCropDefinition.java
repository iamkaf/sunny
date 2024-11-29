package com.iamkaf.sunny.crops;

import com.iamkaf.sunny.block.GenericCropBlock;
import com.iamkaf.sunny.registry.Blocks;
import com.iamkaf.sunny.registry.CreativeModeTabs;
import com.iamkaf.sunny.registry.Items;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;

public final class StrawberryCropDefinition extends SunnyCropDefinition {
    private static final String ID = "strawberry";
    private static final String DISPLAY_NAME = "Strawberry";
    private static final int NUTRITION = 4;
    private static final float SATURATION = 0.2f;
    private static final CropShape SHAPE = CropShape.CROSS;

    private static final RegistrySupplier<Item> CROP_ITEM = Items.ITEMS.register(ID,
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(NUTRITION)
                    .saturationModifier(SATURATION)
                    .build()).arch$tab(CreativeModeTabs.SUNNY))
    );


    public StrawberryCropDefinition() {
        super(NUTRITION, SATURATION);
    }

    @Override
    public @NotNull RegistrySupplier<Item> getCropItem() {
        return CROP_ITEM;
    }

    @Override
    public @NotNull RegistrySupplier<Item> getCropSeedItem() {
        return CROP_SEED_ITEM;
    }

    @Override
    public @NotNull RegistrySupplier<Block> getCropBlock() {
        return CROP_BLOCK;
    }

    @Override
    public @NotNull String getId() {
        return ID;
    }

    @Override
    public @NotNull String getDisplayName() {
        return DISPLAY_NAME;
    }

    @Override
    public @NotNull RenderType getRenderType() {
        return RenderType.cutout();
    }

    @Override
    public CropShape getShape() {
        return SHAPE;
    }    private static final RegistrySupplier<Block> CROP_BLOCK = Blocks.BLOCKS.register(ID + "_crop_block",
            () -> new GenericCropBlock(BlockBehaviour.Properties.ofFullCopy(net.minecraft.world.level.block.Blocks.WHEAT),
                    SHAPE
            ) {
                @Override
                protected @NotNull ItemLike getBaseSeedId() {
                    return CROP_SEED_ITEM.get();
                }
            }
    );


    private static final RegistrySupplier<Item> CROP_SEED_ITEM = Items.ITEMS.register(ID + "_seeds",
            () -> new ItemNameBlockItem(CROP_BLOCK.get(),
                    new Item.Properties().arch$tab(CreativeModeTabs.SUNNY)
            )
    );



}
