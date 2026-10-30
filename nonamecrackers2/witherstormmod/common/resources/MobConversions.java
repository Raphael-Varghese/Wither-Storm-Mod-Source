/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  javax.annotation.Nullable
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.packs.resources.ResourceManager
 *  net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener
 *  net.minecraft.util.GsonHelper
 *  net.minecraft.util.profiling.ProfilerFiller
 *  net.minecraft.world.entity.EntityType
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package nonamecrackers2.witherstormmod.common.resources;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.common.resources.taint.MobConversion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MobConversions
extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().create();
    private static final Logger LOGGER = LogManager.getLogger();
    @Nullable
    private Map<ResourceLocation, MobConversion> mobConversions;

    public MobConversions() {
        super(GSON, "tainting/entity");
    }

    protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager, ProfilerFiller profiler) {
        HashMap conversions = Maps.newHashMap();
        for (Map.Entry<ResourceLocation, JsonElement> entry : files.entrySet()) {
            ResourceLocation id = entry.getKey();
            try {
                JsonObject obj = entry.getValue().getAsJsonObject();
                EntityType<?> from = MobConversions.parseEntityType(GsonHelper.m_13906_((JsonObject)obj, (String)"from"));
                if (conversions.values().stream().anyMatch(c -> c.from().equals((Object)from))) {
                    LOGGER.warn("Duplicate mob conversion detected: {} has been registered multiple times", from);
                }
                EntityType<?> to = MobConversions.parseEntityType(GsonHelper.m_13906_((JsonObject)obj, (String)"to"));
                boolean canBeConvertedFromWitherSickness = GsonHelper.m_13912_((JsonObject)obj, (String)"convert_from_sickness");
                conversions.put(id, new MobConversion(from, to, canBeConvertedFromWitherSickness));
            }
            catch (JsonSyntaxException | IllegalStateException e) {
                LOGGER.warn("Failed to read '" + id.toString() + "'", e);
            }
        }
        this.mobConversions = ImmutableMap.copyOf((Map)conversions);
    }

    private static EntityType<?> parseEntityType(String rawId) throws JsonSyntaxException {
        ResourceLocation id = ResourceLocation.m_135820_((String)rawId);
        if (id == null) {
            throw new JsonSyntaxException("Not a valid id: '" + rawId + "'");
        }
        if (!ForgeRegistries.ENTITY_TYPES.containsKey(id)) {
            throw new JsonSyntaxException("Unknown entity with id '" + rawId + "'");
        }
        return (EntityType)ForgeRegistries.ENTITY_TYPES.getValue(id);
    }

    public Map<ResourceLocation, MobConversion> getConversions() {
        return Objects.requireNonNull(this.mobConversions, "Mob conversions are not loaded");
    }
}

