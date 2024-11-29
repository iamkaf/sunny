package com.iamkaf.sunny.neoforge.datagen;

import com.iamkaf.sunny.Sunny;
import com.iamkaf.sunny.crops.Crops;
import com.iamkaf.sunny.registry.Blocks;
import com.iamkaf.sunny.registry.Items;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
//        dropSelf(Blocks.STRAWBERRY_BLOCK.get());

//        var strawberryLootBlockStateCondition =
//                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.STRAWBERRY_CROP.get())
//                        .setProperties(StatePropertiesPredicate.Builder.properties()
//                                .hasProperty(StrawberryCropBlock.AGE, StrawberryCropBlock.MAX_AGE));
//
//        add(Blocks.STRAWBERRY_CROP.get(), createCropDrops(Blocks.STRAWBERRY_CROP.get(),
//                Items.STRAWBERRY.get(),
//                Items.STRAWBERRY_SEEDS.get(),
//                strawberryLootBlockStateCondition
//        ));

//        Items.ITEMS.getRegistrar().getIds().forEach(resourceLocation -> {
//            Sunny.LOGGER.info("Registered Item: {}", resourceLocation.getPath());
//        });
        Items.ITEMS.iterator().forEachRemaining((ac) -> {
            Sunny.LOGGER.info("Registered Item: {}", ac.getId().getPath());
        });

        for (var def : Crops.ALL_CROPS) {
            add(def.getCropBlock().get(), createCropDrops(
                    def.getCropBlock().get(),
                    def.getCropItem().get(),
                    def.getCropSeedItem().get(),
                    LootItemBlockStatePropertyCondition.hasBlockStateProperties(def.getCropBlock().get())
                            .setProperties(StatePropertiesPredicate.Builder.properties()
                                    .hasProperty(def.getAgeProperty(), def.getMaxAge()))
            ));
        }


//        dropSelf(AmberDreams.Blocks.BISMUTH_BLOCK.get());
//        // dropSelf(ModBlocks.MAGIC_BLOCK.get());
//        dropSelf(AmberDreams.Blocks.BISMUTH_STAIRS.get());
//        add(AmberDreams.Blocks.BISMUTH_SLAB.get(), block -> createSlabItemTable(AmberDreams.Blocks
//        .BISMUTH_SLAB.get()));
//        dropSelf(AmberDreams.Blocks.BISMUTH_PRESSURE_PLATE.get());
//        dropSelf(AmberDreams.Blocks.BISMUTH_BUTTON.get());
//        dropSelf(AmberDreams.Blocks.BISMUTH_FENCE.get());
//        dropSelf(AmberDreams.Blocks.BISMUTH_FENCE_GATE.get());
//        dropSelf(AmberDreams.Blocks.BISMUTH_WALL.get());
//        dropSelf(AmberDreams.Blocks.BISMUTH_TRAPDOOR.get());
//        add(AmberDreams.Blocks.BISMUTH_DOOR.get(), block -> createDoorTable(AmberDreams.Blocks
//        .BISMUTH_DOOR.get()));
//        dropSelf(AmberDreams.Blocks.BISMUTH_LAMP.get());
//
//        add(AmberDreams.Blocks.BISMUTH_ORE.get(), block -> createOreDrop(AmberDreams.Blocks.BISMUTH_ORE
//        .get(), AmberDreams.Items.RAW_BISMUTH.get()));
//        add(AmberDreams.Blocks.BISMUTH_DEEPSLATE_ORE.get(), block -> createMultipleOreDrops(AmberDreams
//        .Blocks.BISMUTH_DEEPSLATE_ORE.get(), AmberDreams.Items.RAW_BISMUTH.get(), 2, 5));
//
//        dropSelf(AmberDreams.Blocks.TOOL_BENCH.get());
//        dropSelf(AmberDreams.Blocks.REPAIR_BENCH.get());
//        dropOther(AmberDreams.Blocks.BRITTLEY_BLOCK.get(), Items.SUGAR);
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops,
            float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup =
                this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(
                pBlock,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
        ));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        List<Block> blocks = new ArrayList<>();
        for (RegistrySupplier<Block> b : Blocks.BLOCKS) {
            blocks.add(b.get());
        }

        return blocks;
    }
}