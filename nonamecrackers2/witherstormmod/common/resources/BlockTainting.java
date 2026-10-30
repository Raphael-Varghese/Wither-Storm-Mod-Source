/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.JsonOps
 *  javax.annotation.Nullable
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.packs.resources.ResourceManager
 *  net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener
 *  net.minecraft.tags.TagKey
 *  net.minecraft.util.GsonHelper
 *  net.minecraft.util.profiling.ProfilerFiller
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package nonamecrackers2.witherstormmod.common.resources;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.common.resources.taint.SingleBlockTaintRecipe;
import nonamecrackers2.witherstormmod.common.resources.taint.TagBasedTaintRecipe;
import nonamecrackers2.witherstormmod.common.resources.taint.TaintRecipe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlockTainting
extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().create();
    private static final Logger LOGGER = LogManager.getLogger();
    @Nullable
    private Map<ResourceLocation, TaintRecipe> recipes;

    public BlockTainting() {
        super(GSON, "tainting/block");
    }

    protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager, ProfilerFiller profiler) {
        HashMap recipes = Maps.newHashMap();
        for (Map.Entry<ResourceLocation, JsonElement> entry : files.entrySet()) {
            ResourceLocation id = entry.getKey();
            try {
                String blockEntry;
                ResourceLocation rawEffectId;
                JsonObject object = entry.getValue().getAsJsonObject();
                MobEffect effect = null;
                if (object.has("potion_effect") && (effect = (MobEffect)ForgeRegistries.MOB_EFFECTS.getValue(rawEffectId = (ResourceLocation)ResourceLocation.m_135837_((String)GsonHelper.m_13906_((JsonObject)object, (String)"potion_effect")).resultOrPartial(m -> {
                    throw new JsonSyntaxException(m);
                }).get())) == null) {
                    throw new JsonSyntaxException("Unknown effect with id '" + rawEffectId + "'");
                }
                BlockState replacement = (BlockState)BlockState.f_61039_.parse((DynamicOps)JsonOps.INSTANCE, (Object)GsonHelper.m_13930_((JsonObject)object, (String)"replacement")).resultOrPartial(m -> {
                    throw new JsonSyntaxException(m);
                }).get();
                ArrayList properties = Lists.newArrayList();
                if (object.has("properties_to_copy")) {
                    JsonArray propertiesToCopy = GsonHelper.m_13933_((JsonObject)object, (String)"properties_to_copy");
                    for (JsonElement element : propertiesToCopy) {
                        String propertyName = GsonHelper.m_13805_((JsonElement)element, (String)"property_name");
                        boolean flag = true;
                        for (Property property : replacement.m_61147_()) {
                            if (!property.m_61708_().equals(propertyName)) continue;
                            if (properties.contains(property)) {
                                throw new IllegalStateException("Property is already specified: '" + propertyName + "'");
                            }
                            properties.add(property);
                            flag = false;
                            break;
                        }
                        if (!flag) continue;
                        throw new JsonSyntaxException("Unknown property with name '" + propertyName + "'");
                    }
                }
                if ((blockEntry = GsonHelper.m_13906_((JsonObject)object, (String)"block")).startsWith("#")) {
                    TagKey tag = TagKey.m_203882_((ResourceKey)Registries.f_256747_, (ResourceLocation)((ResourceLocation)ResourceLocation.m_135837_((String)blockEntry.replace("#", "")).resultOrPartial(e -> {
                        throw new JsonSyntaxException(e);
                    }).get()));
                    recipes.put(id, new TagBasedTaintRecipe((TagKey<Block>)tag, effect, replacement, properties));
                    continue;
                }
                ResourceLocation blockId = (ResourceLocation)ResourceLocation.m_135837_((String)blockEntry).resultOrPartial(e -> {
                    throw new JsonSyntaxException(e);
                }).get();
                if (!ForgeRegistries.BLOCKS.containsKey(blockId)) {
                    throw new JsonSyntaxException("Unknown block with id '" + blockEntry + "'");
                }
                Block block = (Block)ForgeRegistries.BLOCKS.getValue(blockId);
                recipes.put(id, new SingleBlockTaintRecipe(block, effect, replacement, properties));
            }
            catch (JsonSyntaxException | IllegalStateException e2) {
                LOGGER.warn("Failed to read '" + id.toString() + "'", e2);
            }
        }
        this.recipes = (Map)recipes.entrySet().stream().sorted(Map.Entry.comparingByValue().reversed()).collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<ResourceLocation, TaintRecipe> getRecipes() {
        return Objects.requireNonNull(this.recipes, "Recipes are not loaded");
    }
}

