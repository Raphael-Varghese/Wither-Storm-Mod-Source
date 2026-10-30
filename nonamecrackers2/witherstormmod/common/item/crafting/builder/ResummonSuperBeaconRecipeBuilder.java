/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.minecraft.data.recipes.FinishedRecipe
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.RecipeSerializer
 *  net.minecraftforge.registries.ForgeRegistries
 */
package nonamecrackers2.witherstormmod.common.item.crafting.builder;

import com.google.gson.JsonObject;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;
import nonamecrackers2.witherstormmod.common.item.crafting.SuperBeaconRecipe;
import nonamecrackers2.witherstormmod.common.item.crafting.builder.SuperBeaconRecipeBuilder;

public class ResummonSuperBeaconRecipeBuilder
extends SuperBeaconRecipeBuilder {
    private final EntityType<?> entity;
    private final CompoundTag nbt;

    public ResummonSuperBeaconRecipeBuilder(SuperBeaconRecipe.Condition condition, EntityType<?> entity, CompoundTag tag) {
        super(condition);
        this.entity = entity;
        this.nbt = tag;
    }

    public Item m_142372_() {
        return null;
    }

    public void m_126140_(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        consumer.accept(new Result(id, this.condition, this.entity, this.nbt, this.group == null ? "" : this.group, this.ingredients));
    }

    public void m_176498_(Consumer<FinishedRecipe> consumer) {
        this.m_126140_(consumer, ResummonSuperBeaconRecipeBuilder.defaultRecipeId(this.entity));
    }

    public void m_176500_(Consumer<FinishedRecipe> consumer, String string) {
        ResourceLocation newId;
        ResourceLocation id = ResummonSuperBeaconRecipeBuilder.defaultRecipeId(this.entity);
        if (id.equals((Object)(newId = new ResourceLocation(id.m_135827_(), string)))) {
            throw new IllegalStateException("Recipe " + string + " should remove its 'save' argument as it is equal to the default one");
        }
        this.m_126140_(consumer, newId);
    }

    private static ResourceLocation defaultRecipeId(EntityType<?> type) {
        ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(type);
        return new ResourceLocation(id.m_135827_(), "summon_" + id.m_135815_());
    }

    public static class Result
    extends SuperBeaconRecipeBuilder.Result {
        private final EntityType<?> entity;
        private final CompoundTag nbt;

        public Result(ResourceLocation id, SuperBeaconRecipe.Condition condition, EntityType<?> entity, CompoundTag nbt, String group, List<Ingredient> ingredients) {
            super(id, condition, group, ingredients);
            this.entity = entity;
            this.nbt = nbt;
        }

        @Override
        public void m_7917_(JsonObject object) {
            super.m_7917_(object);
            object.addProperty("entity", ForgeRegistries.ENTITY_TYPES.getKey(this.entity).toString());
            if (!this.nbt.m_128456_()) {
                object.addProperty("nbt", this.nbt.toString());
            }
        }

        public RecipeSerializer<?> m_6637_() {
            return (RecipeSerializer)WitherStormModRecipeSerializers.RESUMMON_SUPER_BEACON.get();
        }
    }
}

