/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 *  net.minecraft.advancements.CriterionTriggerInstance
 *  net.minecraft.data.recipes.FinishedRecipe
 *  net.minecraft.data.recipes.RecipeBuilder
 *  net.minecraft.data.recipes.RecipeCategory
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 */
package nonamecrackers2.witherstormmod.common.item.crafting.builder;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import nonamecrackers2.witherstormmod.common.item.crafting.SuperBeaconRecipe;
import nonamecrackers2.witherstormmod.common.item.crafting.builder.ItemCraftSuperBeaconRecipeBuilder;
import nonamecrackers2.witherstormmod.common.item.crafting.builder.ResummonSuperBeaconRecipeBuilder;

public abstract class SuperBeaconRecipeBuilder
implements RecipeBuilder {
    protected final List<Ingredient> ingredients = Lists.newArrayList();
    protected final SuperBeaconRecipe.Condition condition;
    @Nullable
    protected String group;

    public SuperBeaconRecipeBuilder(SuperBeaconRecipe.Condition condition) {
        this.condition = condition;
    }

    public static ItemCraftSuperBeaconRecipeBuilder item(SuperBeaconRecipe.Condition condition, RecipeCategory category, ItemLike result, int count) {
        return new ItemCraftSuperBeaconRecipeBuilder(condition, result, count);
    }

    public static ItemCraftSuperBeaconRecipeBuilder item(SuperBeaconRecipe.Condition condition, RecipeCategory category, ItemLike result) {
        return new ItemCraftSuperBeaconRecipeBuilder(condition, result, 1);
    }

    public static ResummonSuperBeaconRecipeBuilder entity(SuperBeaconRecipe.Condition condition, RecipeCategory category, EntityType<?> type, CompoundTag tag) {
        return new ResummonSuperBeaconRecipeBuilder(condition, type, tag);
    }

    public static ResummonSuperBeaconRecipeBuilder entity(SuperBeaconRecipe.Condition condition, RecipeCategory category, EntityType<?> type) {
        return new ResummonSuperBeaconRecipeBuilder(condition, type, new CompoundTag());
    }

    public SuperBeaconRecipeBuilder requires(ItemLike item) {
        return this.requires(item, 1);
    }

    public SuperBeaconRecipeBuilder requires(ItemLike item, int count) {
        for (int i = 0; i < count; ++i) {
            this.requires(Ingredient.m_43929_((ItemLike[])new ItemLike[]{item}));
        }
        return this;
    }

    public SuperBeaconRecipeBuilder requires(Ingredient ingredient) {
        return this.requires(ingredient, 1);
    }

    public SuperBeaconRecipeBuilder requires(Ingredient ingredient, int count) {
        for (int i = 0; i < count; ++i) {
            this.ingredients.add(ingredient);
        }
        return this;
    }

    public SuperBeaconRecipeBuilder unlockedBy(String name, CriterionTriggerInstance instance) {
        return this;
    }

    public RecipeBuilder m_126145_(String group) {
        this.group = group;
        return null;
    }

    public static abstract class Result
    implements FinishedRecipe {
        private final ResourceLocation id;
        private final SuperBeaconRecipe.Condition condition;
        private final String group;
        private final List<Ingredient> ingredients;

        public Result(ResourceLocation id, SuperBeaconRecipe.Condition condition, String group, List<Ingredient> ingredients) {
            this.id = id;
            this.condition = condition;
            this.group = group;
            this.ingredients = ingredients;
        }

        public void m_7917_(JsonObject object) {
            object.addProperty("condition", this.condition.m_7912_());
            if (!this.group.isEmpty()) {
                object.addProperty("group", this.group);
            }
            JsonArray array = new JsonArray();
            for (Ingredient ingredient : this.ingredients) {
                array.add(ingredient.m_43942_());
            }
            object.add("ingredients", (JsonElement)array);
        }

        public ResourceLocation m_6445_() {
            return this.id;
        }

        public JsonObject m_5860_() {
            return null;
        }

        public ResourceLocation m_6448_() {
            return null;
        }
    }
}

