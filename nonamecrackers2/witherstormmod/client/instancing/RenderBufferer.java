/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.blaze3d.shaders.FogShape
 *  com.mojang.blaze3d.shaders.Uniform
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexBuffer
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraftforge.event.TickEvent$ClientTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.event.TickEvent$RenderTickEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  nonamecrackers2.crackerslib.common.compat.CompatHelper
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.joml.Matrix3fc
 *  org.joml.Vector3f
 *  org.lwjgl.opengl.GL11
 */
package nonamecrackers2.witherstormmod.client.instancing;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.crackerslib.common.compat.CompatHelper;
import nonamecrackers2.witherstormmod.client.instancing.AsyncBufferedInstance;
import nonamecrackers2.witherstormmod.client.instancing.BufferedInstance;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.mixin.IMixinLightTexture;
import nonamecrackers2.witherstormmod.mixin.IMixinOverlayTexture;
import nonamecrackers2.witherstormmod.mixin.MixinRenderSystemAccessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joml.Matrix3fc;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

public class RenderBufferer {
    public static final RenderBufferer INSTANCE = new RenderBufferer(Minecraft.m_91087_());
    private static final Logger LOGGER = LogManager.getLogger((String)"witherstormmod/RenderBufferer");
    private final Minecraft mc;
    private final Map<Object, BufferedInstance> instances = Maps.newHashMap();
    private final ExecutorService asyncBufferBuilderPool = new ThreadPoolExecutor(0, 3, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    private boolean noFog;
    private boolean tempDisabled;
    private boolean useAsyncBuilder;
    private boolean flipFaces;
    private boolean cullFaces;

    private RenderBufferer(Minecraft mc) {
        this.mc = mc;
    }

    public int getTotalInstances() {
        return this.instances.size();
    }

    public void tick() {
    }

    public void renderTick() {
        Iterator<BufferedInstance> iterator = this.instances.values().iterator();
        while (iterator.hasNext()) {
            BufferedInstance instance = iterator.next();
            if (!(instance.getBuffer() == null || instance.hasRemoveSupplier() && !instance.shouldRemove() || instance.wasRenderedLastFrame())) {
                instance.close();
                iterator.remove();
            }
            instance.setRenderedLastFrame(false);
        }
    }

    private void render(Object key, RenderType type, @Nullable Supplier<Boolean> shouldRemove, BufferedInstance.Bufferable bufferer, PoseStack stack, int packedLight, int overlayTexture, float r, float g, float b, float a, boolean renderBuffered) {
        if (renderBuffered && !this.tempDisabled) {
            BufferedInstance instance = this.instances.computeIfAbsent(key, e -> {
                if (this.useAsyncBuilder) {
                    return new AsyncBufferedInstance(type, bufferer, shouldRemove);
                }
                return new BufferedInstance(type, bufferer, shouldRemove);
            });
            if (instance != null) {
                if (instance.requiresComputing()) {
                    PoseStack s = new PoseStack();
                    s.m_85841_(-1.0f, -1.0f, 1.0f);
                    instance.buildBuffer(s, this.asyncBufferBuilderPool);
                }
                if (instance instanceof AsyncBufferedInstance) {
                    AsyncBufferedInstance async = (AsyncBufferedInstance)instance;
                    async.checkBufferBuilderStatus();
                }
                instance.setBufferer(bufferer);
                VertexBuffer buffer = instance.getBuffer();
                if (buffer != null) {
                    int i;
                    buffer.m_85921_();
                    stack.m_85836_();
                    stack.m_85841_(-1.0f, -1.0f, 1.0f);
                    RenderType renderType = instance.getRenderType();
                    renderType.m_110185_();
                    this.applyOverlays(packedLight, overlayTexture, r, g, b, a);
                    float prevStart = RenderSystem.getShaderFogStart();
                    if (this.noFog) {
                        RenderSystem.setShaderFogStart((float)Float.MAX_VALUE);
                    }
                    FogShape prev = RenderSystem.getShaderFogShape();
                    RenderSystem.setShaderFogShape((FogShape)FogShape.SPHERE);
                    if (this.cullFaces) {
                        RenderSystem.enableCull();
                    }
                    if (this.flipFaces) {
                        GL11.glFrontFace((int)2304);
                    }
                    Vector3f[] shaderLights = MixinRenderSystemAccessor.witherstormmod$getShaderLightDirections();
                    Vector3f[] storedOriginalShaderLights = Arrays.copyOf(shaderLights, shaderLights.length);
                    for (i = 0; i < shaderLights.length; ++i) {
                        shaderLights[i] = shaderLights[i].mul((Matrix3fc)RenderSystem.getInverseViewRotationMatrix(), new Vector3f());
                    }
                    buffer.m_253207_(stack.m_85850_().m_252922_(), RenderSystem.getProjectionMatrix(), RenderSystem.getShader());
                    instance.setRenderedLastFrame(true);
                    for (i = 0; i < shaderLights.length; ++i) {
                        shaderLights[i] = storedOriginalShaderLights[i];
                    }
                    if (this.cullFaces) {
                        RenderSystem.disableCull();
                    }
                    if (this.flipFaces) {
                        GL11.glFrontFace((int)2305);
                    }
                    this.resetOverlays();
                    RenderSystem.setShaderFogShape((FogShape)prev);
                    if (this.noFog) {
                        RenderSystem.setShaderFogStart((float)prevStart);
                    }
                    renderType.m_110188_();
                    RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    stack.m_85849_();
                    VertexBuffer.m_85931_();
                }
            }
        } else {
            bufferer.bufferInto(stack, Minecraft.m_91087_().m_91269_().m_110104_().m_6299_(type), packedLight, overlayTexture, r, g, b, a);
        }
        this.noFog = false;
        this.tempDisabled = false;
        this.useAsyncBuilder = false;
    }

    private void applyOverlays(int packedLight, int overlayTexture, float r, float g, float b, float a) {
        IMixinLightTexture lightTexture = (IMixinLightTexture)this.mc.f_91063_.m_109154_();
        IMixinOverlayTexture overlay = (IMixinOverlayTexture)this.mc.f_91063_.m_109155_();
        float[] packedLightCol = RenderBufferer.getRGB(lightTexture.getLightTexture(), (packedLight & 0xFFFF) / 16, (packedLight >> 16 & 0xFFFF) / 16);
        float[] overlayCol = RenderBufferer.getRGB(overlay.getTexture(), overlayTexture & 0xFFFF, overlayTexture >> 16 & 0xFFFF);
        Uniform uniform = RenderSystem.getShader().m_173348_("OverlayTextureColor");
        if (uniform != null) {
            uniform.m_5805_(overlayCol[0], overlayCol[1], overlayCol[2], overlayCol[3]);
        }
        RenderSystem.setShaderColor((float)(r * packedLightCol[0]), (float)(g * packedLightCol[1]), (float)(b * packedLightCol[2]), (float)(a * packedLightCol[3]));
    }

    private void resetOverlays() {
        Uniform uniform = RenderSystem.getShader().m_173348_("OverlayTextureColor");
        if (uniform != null) {
            uniform.m_5805_(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    private static float[] getRGB(DynamicTexture texture, int u, int v) {
        int color = texture.m_117991_().m_84985_(u, v);
        float r = (float)(color >> 0 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color >> 16 & 0xFF) / 255.0f;
        float a = (float)(color >> 24 & 0xFF) / 255.0f;
        return new float[]{r, g, b, a};
    }

    public static boolean shouldUse() {
        return (Boolean)WitherStormModConfig.CLIENT.vertexBufferRendering.get() != false && (!CompatHelper.isOptifineLoaded() || !CompatHelper.areShadersRunning());
    }

    public static void buildAndOrRender(Object key, RenderType type, BufferedInstance.Bufferable bufferer, PoseStack stack, int packedLight, int overlayTexture, float r, float g, float b, float a, boolean renderBuffered) {
        INSTANCE.render(key, type, null, bufferer, stack, packedLight, overlayTexture, r, g, b, a, renderBuffered);
    }

    public static void buildAndOrRender(Object key, RenderType type, Supplier<Boolean> shouldRemove, BufferedInstance.Bufferable bufferer, PoseStack stack, int packedLight, int overlayTexture, float r, float g, float b, float a, boolean renderBuffered) {
        INSTANCE.render(key, type, shouldRemove, bufferer, stack, packedLight, overlayTexture, r, g, b, a, renderBuffered);
    }

    public static void buildAndOrRender(Object key, RenderType type, BufferedInstance.Bufferable bufferer, PoseStack stack, int packedLight, int overlayTexture, float r, float g, float b, float a) {
        INSTANCE.render(key, type, null, bufferer, stack, packedLight, overlayTexture, r, g, b, a, RenderBufferer.shouldUse());
    }

    public static void buildAndOrRender(Object key, RenderType type, Supplier<Boolean> shouldRemove, BufferedInstance.Bufferable bufferer, PoseStack stack, int packedLight, int overlayTexture, float r, float g, float b, float a) {
        INSTANCE.render(key, type, shouldRemove, bufferer, stack, packedLight, overlayTexture, r, g, b, a, RenderBufferer.shouldUse());
    }

    public static void pushNoFog() {
        RenderBufferer.INSTANCE.noFog = true;
    }

    public static void pushTempDisabled() {
        RenderBufferer.INSTANCE.tempDisabled = true;
    }

    public static void pushUseAsyncBuilder() {
        if (((Boolean)WitherStormModConfig.CLIENT.asyncBufferBuilders.get()).booleanValue()) {
            RenderBufferer.INSTANCE.useAsyncBuilder = true;
        }
    }

    public static void pushFlipFaces() {
        RenderBufferer.INSTANCE.flipFaces = true;
    }

    public static void popFlipFaces() {
        RenderBufferer.INSTANCE.flipFaces = false;
    }

    public static void pushCullFaces() {
        RenderBufferer.INSTANCE.cullFaces = true;
    }

    public static void popCullFaces() {
        RenderBufferer.INSTANCE.cullFaces = false;
    }

    private void purgeInstances() {
        LOGGER.debug("Clearing {} instances", (Object)this.instances.size());
        Iterator<Map.Entry<Object, BufferedInstance>> iterator = this.instances.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Object, BufferedInstance> instance = iterator.next();
            instance.getValue().close();
            iterator.remove();
        }
    }

    public void levelReload() {
        LOGGER.debug("Refreshing...");
        this.purgeInstances();
    }

    public void shutdown() {
        LOGGER.debug("Shutting down...");
        this.purgeInstances();
        this.asyncBufferBuilderPool.shutdown();
    }

    public static class Events {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            Minecraft mc = Minecraft.m_91087_();
            if (event.phase == TickEvent.Phase.START && !mc.m_91104_() && mc.f_91073_ != null) {
                INSTANCE.tick();
            }
        }

        @SubscribeEvent
        public static void onRender(TickEvent.RenderTickEvent event) {
            if (event.phase == TickEvent.Phase.START) {
                INSTANCE.renderTick();
            }
        }
    }
}

