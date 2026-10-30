/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  net.minecraft.core.NonNullList
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.GsonHelper
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.RecipeSerializer
 *  net.minecraft.world.item.crafting.RecipeType
 *  net.minecraft.world.item.crafting.ShapedRecipe
 *  net.minecraft.world.level.ItemLike
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.jetbrains.annotations.Nullable
 */
package nonamecrackers2.witherstormmod.common.item.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.Collection;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeTypes;
import nonamecrackers2.witherstormmod.common.item.crafting.SuperBeaconRecipe;
import org.jetbrains.annotations.Nullable;

public class ItemCraftSuperBeaconRecipe
extends SuperBeaconRecipe {
    private final ItemStack result;

    public ItemCraftSuperBeaconRecipe(ResourceLocation id, NonNullList<Ingredient> ingredients, ItemStack result, SuperBeaconRecipe.Condition condition) {
        super(id, ingredients, condition);
        this.result = result;
    }

    public ItemStack m_8043_(RegistryAccess access) {
        return this.result;
    }

    public RecipeType<?> m_6671_() {
        return (RecipeType)WitherStormModRecipeTypes.SUPER_BEACON_ITEM.get();
    }

    @Override
    public boolean isResummonEntity() {
        return false;
    }

    public RecipeSerializer<?> m_7707_() {
        return (RecipeSerializer)WitherStormModRecipeSerializers.ITEM_CRAFT_SUPER_BEACON.get();
    }

    public static class Serializer
    implements RecipeSerializer<ItemCraftSuperBeaconRecipe> {
        public ItemCraftSuperBeaconRecipe fromJson(ResourceLocation id, JsonObject object) {
            ItemStack stack;
            JsonArray array = GsonHelper.m_13933_((JsonObject)object, (String)"ingredients");
            NonNullList ingredients = NonNullList.m_122779_();
            for (int i = 0; i < array.size(); ++i) {
                ingredients.add((Object)Ingredient.m_43917_((JsonElement)array.get(i)));
            }
            if (!object.has("result")) {
                throw new JsonSyntaxException("Missing result, expected to find a string or object");
            }
            if (object.get("result").isJsonObject()) {
                stack = ShapedRecipe.m_151274_((JsonObject)object.get("result").getAsJsonObject());
            } else {
                String rawId = GsonHelper.m_13906_((JsonObject)object, (String)"result");
                ResourceLocation itemId = new ResourceLocation(rawId);
                Item item = (Item)ForgeRegistries.ITEMS.getValue(itemId);
                if (item != null) {
                    stack = new ItemStack((ItemLike)item);
                } else {
                    throw new JsonSyntaxException("Unknown item '" + rawId + "'");
                }
            }
            return new ItemCraftSuperBeaconRecipe(id, (NonNullList<Ingredient>)ingredients, stack, SuperBeaconRecipe.Condition.fromJson(object, "condition"));
        }

        @Nullable
        public ItemCraftSuperBeaconRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            NonNullList ingredients = (NonNullList)buffer.m_236838_(NonNullList::m_182647_, b -> Ingredient.m_43940_((FriendlyByteBuf)b));
            ItemStack item = buffer.m_130267_();
            SuperBeaconRecipe.Condition condition = (SuperBeaconRecipe.Condition)buffer.m_130066_(SuperBeaconRecipe.Condition.class);
            return new ItemCraftSuperBeaconRecipe(id, (NonNullList<Ingredient>)ingredients, item, condition);
        }

        public void toNetwork(FriendlyByteBuf buffer, ItemCraftSuperBeaconRecipe recipe) {
            buffer.m_236828_((Collection)recipe.ingredients, (b, i) -> i.m_43923_(b));
            buffer.m_130055_(recipe.result);
            buffer.m_130068_((Enum)recipe.getCondition());
        }
    }
}

