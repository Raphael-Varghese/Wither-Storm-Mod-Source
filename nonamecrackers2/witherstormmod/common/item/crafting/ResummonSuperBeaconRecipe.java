/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.core.NonNullList
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.TagParser
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.GsonHelper
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.RecipeSerializer
 *  net.minecraft.world.item.crafting.RecipeType
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.jetbrains.annotations.Nullable
 */
package nonamecrackers2.witherstormmod.common.item.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Collection;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeTypes;
import nonamecrackers2.witherstormmod.common.item.crafting.SuperBeaconRecipe;
import org.jetbrains.annotations.Nullable;

public class ResummonSuperBeaconRecipe
extends SuperBeaconRecipe {
    private final EntityType<?> entity;
    private final CompoundTag nbt;
    @Nullable
    public LivingEntity toRender;

    public ResummonSuperBeaconRecipe(ResourceLocation id, NonNullList<Ingredient> ingredients, EntityType<?> entity, CompoundTag nbt, SuperBeaconRecipe.Condition condition) {
        super(id, ingredients, condition);
        this.entity = entity;
        this.nbt = nbt;
    }

    public ItemStack m_8043_(RegistryAccess access) {
        return ItemStack.f_41583_;
    }

    public RecipeType<?> m_6671_() {
        return (RecipeType)WitherStormModRecipeTypes.SUPER_BEACON_RESUMMON.get();
    }

    @Override
    public boolean isResummonEntity() {
        return true;
    }

    @Override
    public EntityType<?> getResummonEntity() {
        return this.entity;
    }

    @Override
    public CompoundTag getResummonEntityNBT() {
        return this.nbt;
    }

    public RecipeSerializer<?> m_7707_() {
        return (RecipeSerializer)WitherStormModRecipeSerializers.RESUMMON_SUPER_BEACON.get();
    }

    public static class Serializer
    implements RecipeSerializer<ResummonSuperBeaconRecipe> {
        public ResummonSuperBeaconRecipe fromJson(ResourceLocation id, JsonObject object) {
            JsonArray array = GsonHelper.m_13933_((JsonObject)object, (String)"ingredients");
            NonNullList ingredients = NonNullList.m_122779_();
            for (int i = 0; i < array.size(); ++i) {
                ingredients.add((Object)Ingredient.m_43917_((JsonElement)array.get(i)));
            }
            String rawEntityId = GsonHelper.m_13906_((JsonObject)object, (String)"entity");
            ResourceLocation entityId = new ResourceLocation(rawEntityId);
            EntityType type = (EntityType)ForgeRegistries.ENTITY_TYPES.getValue(entityId);
            if (type == null) {
                throw new JsonSyntaxException("Unknown entity of id '" + rawEntityId + "'");
            }
            CompoundTag tag = new CompoundTag();
            if (object.has("nbt")) {
                try {
                    tag = TagParser.m_129359_((String)GsonHelper.m_13906_((JsonObject)object, (String)"nbt"));
                }
                catch (CommandSyntaxException exception) {
                    throw new JsonSyntaxException("Invalid nbt tag: " + exception.getMessage());
                }
            }
            return new ResummonSuperBeaconRecipe(id, (NonNullList<Ingredient>)ingredients, type, tag, SuperBeaconRecipe.Condition.fromJson(object, "condition"));
        }

        @Nullable
        public ResummonSuperBeaconRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            NonNullList ingredients = (NonNullList)buffer.m_236838_(NonNullList::m_182647_, b -> Ingredient.m_43940_((FriendlyByteBuf)b));
            EntityType type = (EntityType)buffer.readRegistryId();
            CompoundTag tag = buffer.m_130260_();
            SuperBeaconRecipe.Condition condition = (SuperBeaconRecipe.Condition)buffer.m_130066_(SuperBeaconRecipe.Condition.class);
            return new ResummonSuperBeaconRecipe(id, (NonNullList<Ingredient>)ingredients, type, tag, condition);
        }

        public void toNetwork(FriendlyByteBuf buffer, ResummonSuperBeaconRecipe recipe) {
            buffer.m_236828_((Collection)recipe.ingredients, (b, i) -> i.m_43923_(b));
            buffer.writeRegistryId(ForgeRegistries.ENTITY_TYPES, recipe.entity);
            buffer.m_130079_(recipe.nbt);
            buffer.m_130068_((Enum)recipe.getCondition());
        }
    }
}

