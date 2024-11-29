package com.iamkaf.sunny.registry.fabric;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

public class CompostablesImpl {
    public static <T extends ItemLike> void compostable(Supplier<T> item, float chance) {
        CompostingChanceRegistry.INSTANCE.add(item.get(), chance);
    }
}
