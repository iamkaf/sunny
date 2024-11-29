package com.iamkaf.sunny.neoforge.datagen;

import com.iamkaf.sunny.Sunny;
import com.iamkaf.sunny.crops.Crops;
import com.iamkaf.sunny.registry.Items;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, Sunny.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("creativetab.sunny.sunny", "Sunny");

        add(Items.LOVE.get(), "Love");
        add(Items.LONDON_FOG.get(), "London Fog");

        for (var def : Crops.ALL_CROPS) {
            add(def.getCropItem().get(), def.getDisplayName());
            add(def.getCropSeedItem().get(), def.getDisplayName() + " Seeds");
            add(def.getCropBlock().get(), def.getDisplayName() + "Crop");
        }
    }
}
