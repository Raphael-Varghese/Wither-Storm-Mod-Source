/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.packs.resources.ResourceManager
 *  net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener
 *  net.minecraft.util.GsonHelper
 *  net.minecraft.util.profiling.ProfilerFiller
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package nonamecrackers2.witherstormmod.client.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.awt.Color;
import java.lang.invoke.LambdaMetafactory;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import nonamecrackers2.witherstormmod.client.resources.color.ColorSet;
import nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet;
import nonamecrackers2.witherstormmod.client.resources.texture.TextureSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WitherStormResourceConfigManager
extends SimpleJsonResourceReloadListener {
    private static final Logger LOGGER = LogManager.getLogger((String)"witherstormmod/WitherStormResourceConfigManager");
    private static final Gson GSON = new GsonBuilder().create();
    private static final int COLOR_CONFIG_FORMAT_VERSION = 2;
    private static final int TEXTURES_FORMAT_VERSION = 1;
    public static final WitherStormResourceConfigManager INSTANCE = new WitherStormResourceConfigManager();
    private Int2ObjectMap<ColorSet> colors = new Int2ObjectOpenHashMap();
    private Optional<Color> bowelsFogColor;
    private Int2ObjectMap<TextureSet> textures = new Int2ObjectOpenHashMap();

    public WitherStormResourceConfigManager() {
        super(GSON, "config");
        this.defaultColors();
        this.defaultTextures();
    }

    private void defaultColors() {
        for (int i = 0; i <= 7; ++i) {
            this.colors.put(i, (Object)ColorSet.DEFAULT);
        }
        this.bowelsFogColor = Optional.empty();
    }

    private void defaultTextures() {
        for (int i = 0; i <= 7; ++i) {
            this.textures.put(i, (Object)TextureSet.DEFAULT);
        }
    }

    /*
     * Unable to fully structure code
     */
    protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager manager, ProfilerFiller profiler) {
        colorSetsByPhase = new Int2ObjectOpenHashMap();
        textureSetsByPhase = new Int2ObjectOpenHashMap();
        try {
            for (Map.Entry<ResourceLocation, JsonElement> entry : files.entrySet()) {
                obj = entry.getValue().getAsJsonObject();
                var9_10 = entry.getKey().m_135815_();
                var10_11 = -1;
                switch (var9_10.hashCode()) {
                    case -1354842768: {
                        if (!var9_10.equals("colors")) break;
                        var10_11 = 0;
                        break;
                    }
                    case -1002647880: {
                        if (!var9_10.equals("textures")) break;
                        var10_11 = 1;
                    }
                }
                switch (var10_11) {
                    case 0: {
                        version = 1;
                        if (obj.has("format_version")) {
                            version = GsonHelper.m_13927_((JsonObject)obj, (String)"format_version");
                        }
                        if (version < 2) {
                            WitherStormResourceConfigManager.LOGGER.info("Your colors.json file is out of date. Please update in order for your colors.json file to work correctly. More info here: https://github.com/nonamecrackers2/crackers-wither-storm-mod/wiki/Advanced-Modification");
                        }
                        this.bowelsFogColor = obj.has("bowels_fog") != false ? Optional.of(WitherStormResourceConfigManager.colorFromJsonNoAlpha(GsonHelper.m_13930_((JsonObject)obj, (String)"bowels_fog"))) : Optional.empty();
                        WitherStormResourceConfigManager.LOGGER.debug("Populating default color sets for all phases");
                        defaultColors = ColorSet.builder();
                        WitherStormResourceConfigManager.populateColorSetBuilder(defaultColors, obj);
                        colorSetsByPhase.put(0, (Object)defaultColors);
                        for (i = 1; i <= 7; ++i) {
                            colorSetsByPhase.put(i, (Object)defaultColors.copy());
                        }
                        if (!obj.has("sky_colors")) ** GOTO lbl45
                        if (version < 2) {
                            WitherStormResourceConfigManager.LOGGER.debug("Doing compat stuff with older formats");
                            phases = GsonHelper.m_13930_((JsonObject)obj, (String)"sky_colors");
                            for (i = 5; i <= 7; ++i) {
                                phase = GsonHelper.m_13930_((JsonObject)phases, (String)String.valueOf(i));
                                ((ColorSet.Builder)colorSetsByPhase.get(i)).setSkyColors(WitherStormResourceConfigManager.getColorSetFromJson(phase));
                            }
                        } else {
                            WitherStormResourceConfigManager.LOGGER.info("Looks like you're trying to use an older format for the sky color set with the newer format version. Please see the updated format: https://github.com/nonamecrackers2/crackers-wither-storm-mod/wiki/Advanced-Modification");
                        }
                        ** GOTO lbl49
lbl45:
                        // 1 sources

                        if (obj.has("by_phase")) {
                            WitherStormResourceConfigManager.LOGGER.debug("Populating per-phase color configuration");
                            byPhase = GsonHelper.m_13930_((JsonObject)obj, (String)"by_phase");
                            colorSetsByPhase.int2ObjectEntrySet().forEach((Consumer<Int2ObjectMap.Entry>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, lambda$apply$0(com.google.gson.JsonObject it.unimi.dsi.fastutil.ints.Int2ObjectMap$Entry ), (Lit/unimi/dsi/fastutil/ints/Int2ObjectMap$Entry;)V)((JsonObject)byPhase));
                        }
                    }
lbl49:
                    // 6 sources

                    case 1: {
                        version = 1;
                        if (obj.has("format_version")) {
                            version = GsonHelper.m_13927_((JsonObject)obj, (String)"format_version");
                        }
                        if (version < 1) {
                            WitherStormResourceConfigManager.LOGGER.info("Your textures.json file is out of date. Please update in order for your textures.json file to work correctly. More info here: https://github.com/nonamecrackers2/crackers-wither-storm-mod/wiki/Advanced-Modification");
                        }
                        for (i = 0; i <= 7; ++i) {
                            phaseStr = String.valueOf(i);
                            if (obj.has(phaseStr)) {
                                phase = GsonHelper.m_13930_((JsonObject)obj, (String)phaseStr);
                                textureSetsByPhase.put(i, (Object)TextureSet.fromJson(phase));
                                continue;
                            }
                            textureSetsByPhase.put(i, (Object)TextureSet.DEFAULT);
                        }
                        break;
                    }
                }
            }
        }
        catch (JsonSyntaxException e) {
            WitherStormResourceConfigManager.LOGGER.warn("Failed to read 'colors.json'", (Throwable)e);
        }
        if (colorSetsByPhase.isEmpty()) {
            this.defaultColors();
        } else {
            built = new Int2ObjectOpenHashMap();
            for (Int2ObjectMap.Entry entry : colorSetsByPhase.int2ObjectEntrySet()) {
                built.put(entry.getIntKey(), (Object)((ColorSet.Builder)entry.getValue()).build());
            }
            this.colors = built;
            WitherStormResourceConfigManager.LOGGER.info("Found a colors.json file");
        }
        if (textureSetsByPhase.isEmpty()) {
            this.defaultTextures();
        } else {
            this.textures = textureSetsByPhase;
            WitherStormResourceConfigManager.LOGGER.info("Found a textures.json file");
        }
    }

    public ColorSet getColorSetByPhase(int phase) {
        WitherStormResourceConfigManager.assertIsWithinPhaseRange(phase);
        return (ColorSet)this.colors.get(phase);
    }

    public TextureSet getTextureSetByPhase(int phase) {
        WitherStormResourceConfigManager.assertIsWithinPhaseRange(phase);
        return (TextureSet)this.textures.get(phase);
    }

    public Optional<Color> getBowelsFogColor() {
        return this.bowelsFogColor;
    }

    private static void assertIsWithinPhaseRange(int phase) {
        if (phase < 0 || phase > 7) {
            throw new IllegalArgumentException("Phase outside of range: 0 ~ 7");
        }
    }

    private static void populateColorSetBuilder(ColorSet.Builder builder, JsonObject obj) throws JsonSyntaxException {
        if (obj.has("tractor_beams")) {
            builder.setTractorBeamColor(WitherStormResourceConfigManager.colorFromJsonNoAlpha(GsonHelper.m_13930_((JsonObject)obj, (String)"tractor_beams")));
            LOGGER.debug("Found tractor beam color");
        }
        if (obj.has("tractor_beams_night")) {
            builder.setTractorBeamNightColor(WitherStormResourceConfigManager.colorFromJsonNoAlpha(GsonHelper.m_13930_((JsonObject)obj, (String)"tractor_beams_night")));
            LOGGER.debug("Found night time tractor beam color");
        }
        if (obj.has("night_shine")) {
            builder.setNightShineColor(WitherStormResourceConfigManager.colorFromJsonAlpha(GsonHelper.m_13930_((JsonObject)obj, (String)"night_shine")));
            LOGGER.debug("Found night shine color");
        }
        builder.setSkyColors(WitherStormResourceConfigManager.getColorSetFromJson(obj));
    }

    private static Color colorFromJsonNoAlpha(JsonObject object) throws JsonSyntaxException {
        int r = GsonHelper.m_13927_((JsonObject)object, (String)"red");
        int g = GsonHelper.m_13927_((JsonObject)object, (String)"green");
        int b = GsonHelper.m_13927_((JsonObject)object, (String)"blue");
        return new Color(r, g, b);
    }

    private static Color colorFromJsonAlpha(JsonObject object) throws JsonSyntaxException {
        int r = GsonHelper.m_13927_((JsonObject)object, (String)"red");
        int g = GsonHelper.m_13927_((JsonObject)object, (String)"green");
        int b = GsonHelper.m_13927_((JsonObject)object, (String)"blue");
        int a = 255;
        if (object.has("alpha")) {
            a = GsonHelper.m_13927_((JsonObject)object, (String)"alpha");
        }
        return new Color(r, g, b, a);
    }

    private static SkyColorSet getColorSetFromJson(JsonObject object) throws JsonSyntaxException {
        SkyColorSet.Builder builder = SkyColorSet.builder();
        if (object.has("night") && !object.has("day") || !object.has("night") && object.has("day")) {
            throw new JsonSyntaxException("Must contain both 'day' and 'night' entries, not just one");
        }
        if (object.has("night") && object.has("day")) {
            LOGGER.debug("Found day and night sky color set");
            JsonObject night = GsonHelper.m_13930_((JsonObject)object, (String)"night");
            JsonObject day = GsonHelper.m_13930_((JsonObject)object, (String)"day");
            if (day.has("sky_darken")) {
                builder.setSkyColor(WitherStormResourceConfigManager.colorFromJsonNoAlpha(GsonHelper.m_13930_((JsonObject)day, (String)"sky_darken")));
            }
            if (day.has("sky_darken_clouds")) {
                builder.setCloudColor(WitherStormResourceConfigManager.colorFromJsonNoAlpha(GsonHelper.m_13930_((JsonObject)day, (String)"sky_darken_clouds")));
            }
            if (day.has("sky_darken_fog")) {
                builder.setFogColor(WitherStormResourceConfigManager.colorFromJsonNoAlpha(GsonHelper.m_13930_((JsonObject)day, (String)"sky_darken_fog")));
            }
            if (night.has("sky_darken")) {
                builder.setNightSkyColor(WitherStormResourceConfigManager.colorFromJsonNoAlpha(GsonHelper.m_13930_((JsonObject)night, (String)"sky_darken")));
            }
            if (night.has("sky_darken_clouds")) {
                builder.setNightCloudColor(WitherStormResourceConfigManager.colorFromJsonNoAlpha(GsonHelper.m_13930_((JsonObject)night, (String)"sky_darken_clouds")));
            }
            if (night.has("sky_darken_fog")) {
                builder.setNightFogColor(WitherStormResourceConfigManager.colorFromJsonNoAlpha(GsonHelper.m_13930_((JsonObject)night, (String)"sky_darken_fog")));
            }
        } else {
            LOGGER.debug("Found sky color set");
            if (object.has("sky_darken")) {
                builder.setSkyColor(WitherStormResourceConfigManager.colorFromJsonNoAlpha(GsonHelper.m_13930_((JsonObject)object, (String)"sky_darken")));
            }
            if (object.has("sky_darken_clouds")) {
                builder.setCloudColor(WitherStormResourceConfigManager.colorFromJsonNoAlpha(GsonHelper.m_13930_((JsonObject)object, (String)"sky_darken_clouds")));
            }
            if (object.has("sky_darken_fog")) {
                builder.setFogColor(WitherStormResourceConfigManager.colorFromJsonNoAlpha(GsonHelper.m_13930_((JsonObject)object, (String)"sky_darken_fog")));
            }
        }
        return builder.build();
    }

    private static /* synthetic */ void lambda$apply$0(JsonObject byPhase, Int2ObjectMap.Entry e) {
        String phaseStr = String.valueOf(e.getIntKey());
        if (byPhase.has(phaseStr)) {
            LOGGER.debug("Found phase: {}", (Object)e.getIntKey());
            WitherStormResourceConfigManager.populateColorSetBuilder((ColorSet.Builder)e.getValue(), GsonHelper.m_13930_((JsonObject)byPhase, (String)phaseStr));
        }
    }
}

