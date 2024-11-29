package com.iamkaf.sunny.registry.neoforge;

import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

public class CompostablesImpl {
    public static <T extends ItemLike> void compostable(Supplier<T> item, float chance) {
        // NO-OP, done in datagen
    }
}
