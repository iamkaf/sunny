package com.iamkaf.sunny.crops;

import com.iamkaf.sunny.mixin.VillagerMixin;
import net.minecraft.world.entity.npc.Villager;

import java.util.ArrayList;
import java.util.List;

public class Crops {
    public static final List<SunnyCropDefinition> ALL_CROPS = new ArrayList<>();

    public static final SunnyCropDefinition STRAWBERRY = add(new StrawberryCropDefinition());
    public static final SunnyCropDefinition CUCUMBER = add(new CucumberCropDefinition());
    public static final SunnyCropDefinition PINEBERRY = add(new PineberryCropDefinition());
    public static final SunnyCropDefinition PEANUT = add(new PeanutCropDefinition());
    public static final SunnyCropDefinition CABBAGE = add(new CabbageCropDefinition());

    private static SunnyCropDefinition add(SunnyCropDefinition crop) {
        ALL_CROPS.add(crop);
        return crop;
    }

    public static void init() {
    }
}
