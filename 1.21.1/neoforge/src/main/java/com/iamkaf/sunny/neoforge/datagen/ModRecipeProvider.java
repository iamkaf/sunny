package com.iamkaf.sunny.neoforge.datagen;

import com.iamkaf.sunny.Sunny;
import com.iamkaf.sunny.crops.Crops;
import com.iamkaf.sunny.registry.Items;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients,
            RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(
                recipeOutput,
                RecipeSerializer.SMELTING_RECIPE,
                SmeltingRecipe::new,
                pIngredients,
                pCategory,
                pResult,
                pExperience,
                pCookingTIme,
                pGroup,
                "_from_smelting"
        );
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients,
            RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(
                recipeOutput,
                RecipeSerializer.BLASTING_RECIPE,
                BlastingRecipe::new,
                pIngredients,
                pCategory,
                pResult,
                pExperience,
                pCookingTime,
                pGroup,
                "_from_blasting"
        );
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput,
            RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
            List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience,
            int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(
                    Ingredient.of(itemlike),
                    pCategory,
                    pResult,
                    pExperience,
                    pCookingTime,
                    pCookingSerializer,
                    factory
            ).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(
                    recipeOutput,
                    Sunny.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike)
            );
        }
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        for (var def : Crops.ALL_CROPS) {
            ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, def.getCropSeedItem().get(), 1)
                    .requires(def.getCropItem().get())
                    .unlockedBy("has_" + def.getId(), has(def.getCropItem().get()))
                    .save(recipeOutput);
        }

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.LONDON_FOG.get(), 1)
                .requires(Items.LOVE.get(), 1)
                .requires(Items.LOVE.get(), 1)
                .requires(Items.LOVE.get(), 1)
                .requires(Items.LOVE.get(), 1)
                .requires(net.minecraft.world.item.Items.SUGAR)
                .requires(net.minecraft.world.item.Items.WATER_BUCKET)
                .requires(net.minecraft.world.item.Items.MILK_BUCKET)
                .requires(net.minecraft.world.item.Items.COCOA_BEANS)
                .requires(net.minecraft.world.item.Items.HONEY_BOTTLE)
                .unlockedBy("has_love", has(Items.LOVE.get()))
                .save(recipeOutput);
    }
}
