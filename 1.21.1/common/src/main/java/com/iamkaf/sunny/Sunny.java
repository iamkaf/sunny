package com.iamkaf.sunny;

import com.iamkaf.sunny.config.SunnyConfig;
import com.iamkaf.sunny.crops.Crops;
import com.iamkaf.sunny.events.OnTrampleCrop;
import com.iamkaf.sunny.loot.LootModifications;
import com.iamkaf.sunny.registry.Blocks;
import com.iamkaf.sunny.registry.Compostables;
import com.iamkaf.sunny.registry.CreativeModeTabs;
import com.iamkaf.sunny.registry.Items;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public final class Sunny {
    public static final String MOD_ID = "sunny";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static final Set<Consumer<Integer>> initCallbacks = new HashSet<>();
    private static boolean initCompleted = false;

    public static final SunnyConfig CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    static {
        Pair<SunnyConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(SunnyConfig::new);
        CONFIG = pair.getLeft();
        CONFIG_SPEC = pair.getRight();
    }

    public static void init() {
        LOGGER.info("Bright Sunny Days.");

        // Registry
        Crops.init();
        Blocks.init();
        Items.init();
        CreativeModeTabs.init();
        LootModifications.init();
        Compostables.init();

        // Events
        OnTrampleCrop.init();

        for (var listener : initCallbacks) {
            listener.accept(1);
        }

        initCompleted = true;
    }

    /**
     * Creates resource location in the mod namespace with the given path.
     */
    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void addInitCallback(Consumer<Integer> listener) {
        if (initCompleted) {
            listener.accept(2);
            return;
        }
        Sunny.initCallbacks.add(listener);
    }
}
