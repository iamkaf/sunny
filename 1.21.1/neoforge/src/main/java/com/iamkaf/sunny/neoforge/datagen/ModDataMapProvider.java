package com.iamkaf.sunny.neoforge.datagen;

import com.iamkaf.sunny.Sunny;
import com.iamkaf.sunny.crops.Crops;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput,
            CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        var compostableItemBuilder = this.builder(NeoForgeDataMaps.COMPOSTABLES);

        for (var def : Crops.ALL_CROPS) {
            compostableItemBuilder.add(Sunny.id(def.getId()), new Compostable(0.5f, true), false);
            compostableItemBuilder.add(Sunny.id(def.getId() + "_seeds"), new Compostable(0.3f, true), false);
        }
    }
}
