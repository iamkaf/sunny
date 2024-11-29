package com.iamkaf.sunny.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class SunnyConfig {
    public final ModConfigSpec.ConfigValue<String> welcomeMessage;

    public SunnyConfig(ModConfigSpec.Builder builder) {
        welcomeMessage = builder.define("welcome_message", "Hello from the config!");
    }
}
