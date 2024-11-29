package com.iamkaf.sunny.loot;

import com.iamkaf.sunny.crops.Crops;
import dev.architectury.event.events.common.LootEvent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class LootModifications {
    private static final ResourceKey<LootTable> GRASS = Blocks.SHORT_GRASS.getLootTable();
    private static final ResourceKey<LootTable> TALL_GRASS = Blocks.TALL_GRASS.getLootTable();

    static {
        LootEvent.MODIFY_LOOT_TABLE.register((key, context, builtin) -> {
            // Overworld grass
            if (builtin && (GRASS.equals(key) || TALL_GRASS.equals(key))) {
                modifyGrassDrops(context);
            }
            // todo: support for nether grass and other seed sources
        });
    }

    private static void modifyGrassDrops(LootEvent.LootTableModificationContext context) {
        for (var def : Crops.ALL_CROPS) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.05f))
                    .add(LootItem.lootTableItem(def.getCropSeedItem().get()))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)));
            context.addPool(pool);
        }
    }

    public static void init() {

    }
}
