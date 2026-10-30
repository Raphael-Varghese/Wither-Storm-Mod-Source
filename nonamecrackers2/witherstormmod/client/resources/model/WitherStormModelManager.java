/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  javax.annotation.Nullable
 *  net.minecraft.client.model.geom.ModelLayerLocation
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.packs.resources.ResourceManager
 *  net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener
 *  net.minecraft.util.profiling.ProfilerFiller
 *  net.minecraftforge.client.model.obj.ObjModel
 */
package nonamecrackers2.witherstormmod.client.resources.model;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.client.model.obj.ObjModel;

public class WitherStormModelManager
extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().create();
    public static final WitherStormModelManager INSTANCE = new WitherStormModelManager();
    private Map<ModelLayerLocation, ObjModel> models = Maps.newHashMap();

    public WitherStormModelManager() {
        super(GSON, "models/wither_storm_mass");
    }

    protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager, ProfilerFiller profiler) {
        this.models.clear();
    }

    public boolean hasObjModel(ModelLayerLocation location) {
        return this.models.containsKey(location);
    }

    @Nullable
    public ObjModel getModel(ModelLayerLocation location) {
        return this.models.get(location);
    }

    public Map<ModelLayerLocation, ObjModel> getModels() {
        return ImmutableMap.copyOf(this.models);
    }
}

