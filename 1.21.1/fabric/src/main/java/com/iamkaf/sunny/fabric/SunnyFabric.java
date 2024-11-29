package com.iamkaf.sunny.fabric;

import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;

import com.iamkaf.sunny.Sunny;
import net.neoforged.fml.config.ModConfig;

public final class SunnyFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        Sunny.init();

        NeoForgeConfigRegistry.INSTANCE.register(Sunny.MOD_ID, ModConfig.Type.COMMON, Sunny.CONFIG_SPEC);
    }
}
