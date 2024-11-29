package com.iamkaf.sunny.neoforge.client;

import com.iamkaf.sunny.Sunny;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = Sunny.MOD_ID, dist = Dist.CLIENT)
public final class SunnyNeoForgeClient {
    public SunnyNeoForgeClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
