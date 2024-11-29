package com.iamkaf.sunny.fabric.client;

import com.iamkaf.sunny.Sunny;
import com.iamkaf.sunny.crops.Crops;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.client.ConfigScreenFactoryRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

public final class SunnyFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (var def : Crops.ALL_CROPS) {
            BlockRenderLayerMap.INSTANCE.putBlock(def.getCropBlock().get(), def.getRenderType());
        }

        ConfigScreenFactoryRegistry.INSTANCE.register(Sunny.MOD_ID, ConfigurationScreen::new);
    }
}
