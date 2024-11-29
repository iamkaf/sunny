package com.iamkaf.sunny.fabric;

import com.iamkaf.sunny.Sunny;
import com.iamkaf.sunny.registry.CreativeModeTabs;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class RegisterImpl {

    public static <T extends Block> Supplier<T> block(String id, Supplier<T> supplier) {
        var obj = Registry.register(BuiltInRegistries.BLOCK, Sunny.id(id), supplier.get());
        item(id, () -> new BlockItem(obj, new Item.Properties()));
        return () -> obj;
    }

    public static <T extends Item> Supplier<T> item(String id, Supplier<T> supplier) {
        var obj = Registry.register(BuiltInRegistries.ITEM, Sunny.id(id), supplier.get());
        return () -> obj;
    }

    public static <T extends Item> void fuelItem(Supplier<T> supplier, int burnTime) {
        FuelRegistry.INSTANCE.add(supplier.get(), burnTime);
    }

//    public static Supplier<CreativeModeTab> creativeModeTab(Supplier<Item> icon, String tabName) {
//        var obj = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
//                Sunny.id(tabName),
//                FabricItemGroup.builder().icon(() -> new ItemStack(icon.get()))
//                        .title(Component.translatable("creativetab." + Sunny.MOD_ID + "." + tabName))
//                        .displayItems((itemDisplayParameters, output) -> {
//                            for (var item : CreativeModeTabs.getCreativeModeTabItems()) {
//                                output.accept(item);
//                            }
//                        }).build()
//        );
//        return () -> obj;
//    }

    public static <T> Supplier<DataComponentType<T>> dataComponentType(String name,
            UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        var obj = Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE,
                Sunny.id(name),
                builderOperator.apply(DataComponentType.builder()).build()
        );
        return () -> obj;
    }

    public static Holder<ArmorMaterial> armorMaterial(String name, ArmorMaterial material) {
        var obj = Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL,
                Sunny.id(name),
                material
        );
        return obj;
    }
}
