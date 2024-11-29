package com.iamkaf.sunny.registry;

import com.iamkaf.sunny.Sunny;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class Items {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Sunny.MOD_ID, Registries.ITEM);

    public static RegistrySupplier<Item> LOVE =
            ITEMS.register("love", () -> new Item(new Item.Properties().arch$tab(CreativeModeTabs.SUNNY)));
    public static RegistrySupplier<Item> LONDON_FOG = ITEMS.register(
            "london_fog",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10)
                    .saturationModifier(1f)
                    .alwaysEdible()
                    .effect(new MobEffectInstance(MobEffects.REGENERATION, 8000, 1), 1f)
                    .build()).stacksTo(1).arch$tab(CreativeModeTabs.SUNNY)) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.literal("The famciest drink.").withStyle(ChatFormatting.AQUA));
                    tooltipComponents.add(Component.literal("For avid tea enjoyers only.").withStyle(ChatFormatting.YELLOW));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            }
    );

    public static void init() {
        ITEMS.register();
    }
}
