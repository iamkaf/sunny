package com.iamkaf.sunny.neoforge;

import com.iamkaf.sunny.Sunny;
import com.iamkaf.sunny.registry.CreativeModeTabs;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class RegisterImpl {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(Sunny.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(Sunny.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Sunny.MOD_ID);
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Sunny.MOD_ID);
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIAL =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, Sunny.MOD_ID);

    public static <T extends Block> Supplier<T> block(String id, Supplier<T> supplier) {
        var block = BLOCKS.register(id, supplier);
        item(id, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    public static <T extends Item> Supplier<T> item(String id, Supplier<T> supplier) {
        return ITEMS.register(id, supplier);
    }

    public static <T extends Item> void fuelItem(Supplier<T> supplier, int burnTime) {
        // NO-OP, done in datapack
    }

//    public static Supplier<CreativeModeTab> creativeModeTab(Supplier<Item> icon, String tabName) {
//        var tab = TABS.register(tabName,
//                () -> CreativeModeTab.builder()
//                        .icon(() -> new ItemStack(icon.get()))
//                        .title(Component.translatable("creativetab." + Sunny.MOD_ID + "." + tabName))
//                        .displayItems((itemDisplayParameters, output) -> {
//                            for (var item : CreativeModeTabs.getCreativeModeTabItems()) {
//                                output.accept(item);
//                            }
//                        })
//                        .build()
//        );
//
//        return () -> tab.get();
//    }

    public static <T> Supplier<DataComponentType<T>> dataComponentType(String name,
            UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name,
                () -> builderOperator.apply(DataComponentType.builder()).build()
        );
    }

    public static Holder<ArmorMaterial> armorMaterial(String name, ArmorMaterial material) {
        var obj =
                Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, Sunny.id(name), material);
        return obj;
    }
}
