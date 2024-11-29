package com.iamkaf.sunny.mixin;

import com.iamkaf.sunny.Sunny;
import com.iamkaf.sunny.crops.Crops;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This mixin adds mod crops to villager behavior.
 * They will plant, replant and eat the Sunny crops.
 */
@Mixin(Villager.class)
public abstract class VillagerMixin extends AbstractVillager {
    @Mutable
    private static Map<Item, Integer> FOOD_POINTS =
            new HashMap<>(Map.of(Items.BREAD, 4, Items.POTATO, 1, Items.CARROT, 1, Items.BEETROOT, 1));

    @Mutable
    private static Set<Item> WANTED_ITEMS = new HashSet<>(Set.of(
            Items.BREAD,
            Items.POTATO,
            Items.CARROT,
            Items.WHEAT,
            Items.WHEAT_SEEDS,
            Items.BEETROOT,
            Items.BEETROOT_SEEDS,
            Items.TORCHFLOWER_SEEDS,
            Items.PITCHER_POD
    ));

    static {
        Sunny.addInitCallback((n) -> {
            sunny$init();
        });
    }

    public VillagerMixin(EntityType<? extends AbstractVillager> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    private static void sunny$init() {
        for (var def : Crops.ALL_CROPS) {
            if (def.isVillagerFarmable()) {
                WANTED_ITEMS.add(def.getCropItem().get());
                WANTED_ITEMS.add(def.getCropSeedItem().get());
                FOOD_POINTS.put(def.getCropItem().get(), 1);
            }
        }
    }

    @Shadow
    public abstract VillagerData getVillagerData();

    @Shadow
    public abstract void setVillagerData(VillagerData data);
}
