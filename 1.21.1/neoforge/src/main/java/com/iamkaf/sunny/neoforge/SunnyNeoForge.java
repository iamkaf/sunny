package com.iamkaf.sunny.neoforge;

import com.iamkaf.sunny.Sunny;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(Sunny.MOD_ID)
public final class SunnyNeoForge {
    public SunnyNeoForge(IEventBus eBussy, ModContainer container) {
        // Run our common setup.
        Sunny.init();

        RegisterImpl.ITEMS.register(eBussy);
        RegisterImpl.BLOCKS.register(eBussy);
        RegisterImpl.TABS.register(eBussy);
        RegisterImpl.DATA_COMPONENT_TYPES.register(eBussy);
        RegisterImpl.ARMOR_MATERIAL.register(eBussy);
        // will be added later:
//        RegisterImpl.MOB_EFFECT.register(eBussy);
//        RegisterImpl.POTION.register(eBussy);

//        NeoForgeConfigRegistry.INSTANCE.register(Sunny.MOD_ID, ModConfig.Type.COMMON, Sunny.CONFIG_SPEC);
        container.registerConfig(ModConfig.Type.COMMON, Sunny.CONFIG_SPEC);
    }

//    @SubscribeEvent
//    public static void onFMLConstructModEvent(FMLConstructModEvent event) {
//
//    }
}
