/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.GsonHelper
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.RecipeSerializer
 *  net.minecraft.world.item.crafting.RecipeType
 *  net.minecraft.world.item.crafting.ShapedRecipe
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.jetbrains.annotations.Nullable
 */
package nonamecrackers2.witherstormmod.common.item.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeTypes;
import org.jetbrains.annotations.Nullable;

public class AnvilRecipe
implements Recipe<AnvilContents> {
    private final ResourceLocation id;
    private final Ingredient left;
    private final Ingredient right;
    private final ItemStack result;
    private final int cost;

    public AnvilRecipe(ResourceLocation id, Ingredient left, Ingredient right, ItemStack result, int cost) {
        this.id = id;
        this.left = left;
        this.right = right;
        this.result = result;
        this.cost = cost;
    }

    public boolean matches(AnvilContents contents, Level level) {
        return this.left.test(contents.left) && this.right.test(contents.right);
    }

    public ItemStack assemble(AnvilContents contents, RegistryAccess access) {
        return this.m_8043_(access).m_41777_();
    }

    public boolean m_8004_(int width, int height) {
        return width == 1 && width == 2;
    }

    public ResourceLocation m_6423_() {
        return this.id;
    }

    public ItemStack m_8043_(RegistryAccess access) {
        return this.result;
    }

    public RecipeSerializer<?> m_7707_() {
        return (RecipeSerializer)WitherStormModRecipeSerializers.ANVIL_RECIPE.get();
    }

    public RecipeType<?> m_6671_() {
        return (RecipeType)WitherStormModRecipeTypes.ANVIL.get();
    }

    public int getCost() {
        return this.cost;
    }

    public Ingredient getLeft() {
        return this.left;
    }

    public Ingredient getRight() {
        return this.right;
    }

    public ItemStack getOutputRaw() {
        return this.result;
    }

    public static class AnvilContents
    implements Container {
        private final ItemStack left;
        private final ItemStack right;

        public AnvilContents(ItemStack left, ItemStack right) {
            this.left = left;
            this.right = right;
        }

        public void m_6211_() {
        }

        public int m_6643_() {
            return 2;
        }

        public boolean m_7983_() {
            return this.left.m_41619_() && this.right.m_41619_();
        }

        public ItemStack m_8020_(int slot) {
            switch (slot) {
                case 0: {
                    return this.left;
                }
                case 1: {
                    return this.right;
                }
            }
            return ItemStack.f_41583_;
        }

        public ItemStack m_7407_(int slot, int amount) {
            return ItemStack.f_41583_;
        }

        public ItemStack m_8016_(int slot) {
            return ItemStack.f_41583_;
        }

        public void m_6836_(int slot, ItemStack stack) {
        }

        public void m_6596_() {
        }

        public boolean m_6542_(Player player) {
            return false;
        }
    }

    public static class Serializer
    implements RecipeSerializer<AnvilRecipe> {
        public AnvilRecipe fromJson(ResourceLocation id, JsonObject object) {
            ItemStack stack;
            Ingredient left = Ingredient.m_43917_((JsonElement)object.get("left"));
            Ingredient right = Ingredient.m_43917_((JsonElement)object.get("right"));
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
            int cost = GsonHelper.m_13927_((JsonObject)object, (String)"cost");
            return new AnvilRecipe(id, left, right, stack, cost);
        }

        @Nullable
        public AnvilRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            Ingredient left = Ingredient.m_43940_((FriendlyByteBuf)buffer);
            Ingredient right = Ingredient.m_43940_((FriendlyByteBuf)buffer);
            ItemStack item = buffer.m_130267_();
            int cost = buffer.m_130242_();
            return new AnvilRecipe(id, left, right, item, cost);
        }

        public void toNetwork(FriendlyByteBuf buffer, AnvilRecipe recipe) {
            recipe.left.m_43923_(buffer);
            recipe.right.m_43923_(buffer);
            buffer.m_130055_(recipe.result);
            buffer.m_130130_(recipe.cost);
        }
    }
}

