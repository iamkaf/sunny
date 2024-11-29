package com.iamkaf.sunny.registry;

import com.iamkaf.sunny.Sunny;
import com.iamkaf.sunny.crops.Crops;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeModeTabs {
    private static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Sunny.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> SUNNY = TABS.register(Sunny.MOD_ID,
            () -> CreativeTabRegistry.create(Component.translatable("creativetab." + Sunny.MOD_ID + "." + Sunny.MOD_ID),
                    () -> new ItemStack(Crops.STRAWBERRY.getCropItem().get())
            )
    );

    public static void init() {
        TABS.register();
    }
}
