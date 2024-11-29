package com.iamkaf.sunny.registry;

import com.iamkaf.sunny.Sunny;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class Blocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Sunny.MOD_ID, Registries.BLOCK);

//    public static final RegistrySupplier<Block> STRAWBERRY_CROP = BLOCKS.register("strawberry_crop",
//            () -> new StrawberryCropBlock(BlockBehaviour.Properties.ofFullCopy(net.minecraft.world.level
//            .block.Blocks.WHEAT))
//    );

//    public static RegistrySupplier<Block> STRAWBERRY_BLOCK =
//            register("strawberry_block", () -> new Block(BlockBehaviour.Properties.of()));

    public static void init() {
        BLOCKS.register();
    }

    private static RegistrySupplier<Block> register(String id, Supplier<Block> block) {
        var bl = BLOCKS.register(id, block);
        Items.ITEMS.register(id,
                () -> new BlockItem(bl.get(), new Item.Properties().arch$tab(CreativeModeTabs.SUNNY))
        );
        return bl;
    }
}
