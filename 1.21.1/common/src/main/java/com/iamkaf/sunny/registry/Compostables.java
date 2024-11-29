package com.iamkaf.sunny.registry;

import com.iamkaf.sunny.crops.Crops;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

public class Compostables {
    static {
        for (var def : Crops.ALL_CROPS) {
            compostable(def.getCropItem(), 0.5f);
            compostable(def.getCropSeedItem(), 0.3f);
        }
    }

    @ExpectPlatform
    private static <T extends ItemLike> void compostable(Supplier<T> item, float chance) {
        throw new AssertionError("");
    }

    public static void init() {

    }
}
