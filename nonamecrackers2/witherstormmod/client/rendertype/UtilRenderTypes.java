/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$DestFactor
 *  com.mojang.blaze3d.platform.GlStateManager$SourceFactor
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.DefaultVertexFormat
 *  com.mojang.blaze3d.vertex.VertexFormat
 *  com.mojang.blaze3d.vertex.VertexFormat$Mode
 *  net.minecraft.Util
 *  net.minecraft.client.renderer.GameRenderer
 *  net.minecraft.client.renderer.RenderStateShard$CullStateShard
 *  net.minecraft.client.renderer.RenderStateShard$DepthTestStateShard
 *  net.minecraft.client.renderer.RenderStateShard$EmptyTextureStateShard
 *  net.minecraft.client.renderer.RenderStateShard$LightmapStateShard
 *  net.minecraft.client.renderer.RenderStateShard$OverlayStateShard
 *  net.minecraft.client.renderer.RenderStateShard$ShaderStateShard
 *  net.minecraft.client.renderer.RenderStateShard$TextureStateShard
 *  net.minecraft.client.renderer.RenderStateShard$TransparencyStateShard
 *  net.minecraft.client.renderer.RenderStateShard$WriteMaskStateShard
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.RenderType$CompositeState
 *  net.minecraft.resources.ResourceLocation
 */
package nonamecrackers2.witherstormmod.client.rendertype;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.util.function.Function;
import net.minecraft.Util;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import nonamecrackers2.witherstormmod.client.init.WitherStormModShaders;

public class UtilRenderTypes {
    private static final RenderStateShard.ShaderStateShard ENTITY_DECAL_SHADER = new RenderStateShard.ShaderStateShard(GameRenderer::m_172688_);
    private static final RenderStateShard.ShaderStateShard EYES_SHADER = new RenderStateShard.ShaderStateShard(GameRenderer::m_172700_);
    private static final RenderStateShard.TransparencyStateShard TRANSLUCENT_TRANSPARENCY = new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });
    private static final RenderStateShard.TransparencyStateShard ADDITIVE_TRANSPARENCY = new RenderStateShard.TransparencyStateShard("additive_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });
    protected static final RenderStateShard.TransparencyStateShard NO_TRANSPARENCY = new RenderStateShard.TransparencyStateShard("no_transparency", () -> RenderSystem.disableBlend(), () -> {});
    private static final RenderStateShard.CullStateShard NO_CULL = new RenderStateShard.CullStateShard(false);
    private static final RenderStateShard.CullStateShard CULL = new RenderStateShard.CullStateShard(false);
    private static final RenderStateShard.LightmapStateShard LIGHTMAP = new RenderStateShard.LightmapStateShard(true);
    private static final RenderStateShard.OverlayStateShard OVERLAY = new RenderStateShard.OverlayStateShard(true);
    private static final RenderStateShard.DepthTestStateShard EQUAL_DEPTH_TEST = new RenderStateShard.DepthTestStateShard("==", 514);
    private static final RenderStateShard.WriteMaskStateShard COLOR_WRITE = new RenderStateShard.WriteMaskStateShard(true, false);
    private static final RenderStateShard.ShaderStateShard WITHER_STORM_SHADER = new RenderStateShard.ShaderStateShard(WitherStormModShaders::getWitherStormShader);
    private static final RenderStateShard.ShaderStateShard WITHER_STORM_DISSOLVE_SHADER = new RenderStateShard.ShaderStateShard(WitherStormModShaders::getWitherStormDissolveShader);
    private static final Function<ResourceLocation, RenderType> ENTITY_DECAL_TRANSLUCENT = Util.m_143827_(loc -> {
        RenderType.CompositeState state = RenderType.CompositeState.m_110628_().m_173292_(ENTITY_DECAL_SHADER).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(loc, false, false)).m_110663_(EQUAL_DEPTH_TEST).m_110685_(TRANSLUCENT_TRANSPARENCY).m_110661_(NO_CULL).m_110671_(LIGHTMAP).m_110677_(OVERLAY).m_110691_(false);
        return RenderType.m_173215_((String)"entity_decal_translucent", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)false, (RenderType.CompositeState)state);
    });
    private static final Function<ResourceLocation, RenderType> EMISSIVE_NO_CULL = Util.m_143827_(tex -> {
        RenderStateShard.TextureStateShard state = new RenderStateShard.TextureStateShard(tex, false, false);
        return RenderType.m_173215_((String)"emissive_no_cull", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)true, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(EYES_SHADER).m_173290_((RenderStateShard.EmptyTextureStateShard)state).m_110685_(ADDITIVE_TRANSPARENCY).m_110661_(NO_CULL).m_110687_(COLOR_WRITE).m_110691_(false));
    });
    private static final Function<ResourceLocation, RenderType> EMISSIVE_TRANSLUCENT = Util.m_143827_(tex -> {
        RenderStateShard.TextureStateShard state = new RenderStateShard.TextureStateShard(tex, false, false);
        return RenderType.m_173215_((String)"emissive_translucent", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)true, (RenderType.CompositeState)RenderType.CompositeState.m_110628_().m_173292_(EYES_SHADER).m_173290_((RenderStateShard.EmptyTextureStateShard)state).m_110685_(TRANSLUCENT_TRANSPARENCY).m_110661_(CULL).m_110687_(COLOR_WRITE).m_110691_(false));
    });
    private static final Function<ResourceLocation, RenderType> WITHER_STORM = Util.m_143827_(p_269670_ -> {
        RenderType.CompositeState compositeState = RenderType.CompositeState.m_110628_().m_173292_(WITHER_STORM_SHADER).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(p_269670_, false, false)).m_110685_(NO_TRANSPARENCY).m_110661_(NO_CULL).m_110671_(LIGHTMAP).m_110677_(OVERLAY).m_110691_(true);
        return RenderType.m_173215_((String)"wither_storm", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)true, (boolean)false, (RenderType.CompositeState)compositeState);
    });
    private static final Function<ResourceLocation, RenderType> WITHER_STORM_DISSOLVE = Util.m_143827_(tex -> {
        RenderType.CompositeState compositeState = RenderType.CompositeState.m_110628_().m_173292_(WITHER_STORM_DISSOLVE_SHADER).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(tex, false, false)).m_110691_(true);
        return RenderType.m_173215_((String)"wither_storm_dissolve", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)false, (RenderType.CompositeState)compositeState);
    });
    private static final Function<ResourceLocation, RenderType> WITHER_STORM_DECAL = Util.m_143827_(tex -> {
        RenderType.CompositeState compositeState = RenderType.CompositeState.m_110628_().m_173292_(WITHER_STORM_SHADER).m_173290_((RenderStateShard.EmptyTextureStateShard)new RenderStateShard.TextureStateShard(tex, false, false)).m_110663_(EQUAL_DEPTH_TEST).m_110671_(LIGHTMAP).m_110677_(OVERLAY).m_110691_(false);
        return RenderType.m_173215_((String)"entity_decal", (VertexFormat)DefaultVertexFormat.f_85812_, (VertexFormat.Mode)VertexFormat.Mode.QUADS, (int)256, (boolean)false, (boolean)false, (RenderType.CompositeState)compositeState);
    });

    public static RenderType entityDecalTranslucent(ResourceLocation tex) {
        return ENTITY_DECAL_TRANSLUCENT.apply(tex);
    }

    public static RenderType emissiveNoCull(ResourceLocation tex) {
        return EMISSIVE_NO_CULL.apply(tex);
    }

    public static RenderType emissiveTranslucent(ResourceLocation tex) {
        return EMISSIVE_TRANSLUCENT.apply(tex);
    }

    public static RenderType witherStorm(ResourceLocation tex) {
        return WITHER_STORM.apply(tex);
    }

    public static RenderType witherStormDissolve(ResourceLocation tex) {
        return WITHER_STORM_DISSOLVE.apply(tex);
    }

    public static RenderType witherStormDecal(ResourceLocation tex) {
        return WITHER_STORM_DECAL.apply(tex);
    }
}

