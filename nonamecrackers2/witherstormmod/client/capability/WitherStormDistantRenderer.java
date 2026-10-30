/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.PoseStack
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 *  net.minecraft.CrashReport
 *  net.minecraft.CrashReportCategory
 *  net.minecraft.ReportedException
 *  net.minecraft.client.Camera
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.renderer.FogRenderer
 *  net.minecraft.client.renderer.FogRenderer$FogMode
 *  net.minecraft.client.renderer.GameRenderer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.MultiBufferSource$BufferSource
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.entity.EntityRenderDispatcher
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.client.event.RenderLevelStageEvent
 *  net.minecraftforge.client.event.RenderLevelStageEvent$Stage
 *  net.minecraftforge.event.TickEvent$ClientTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.server.timings.TimeTracker
 *  nonamecrackers2.crackerslib.common.compat.CompatHelper
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 */
package nonamecrackers2.witherstormmod.client.capability;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.server.timings.TimeTracker;
import nonamecrackers2.crackerslib.common.compat.CompatHelper;
import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
import nonamecrackers2.witherstormmod.client.renderer.blockentity.AbstractSuperBeaconRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.AbstractWitherStormRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.AbstractWitherStormModel;
import nonamecrackers2.witherstormmod.client.util.SuperBeaconDistantInstance;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.mixin.IMixinGameRenderer;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

public class WitherStormDistantRenderer {
    private final Int2ObjectMap<WitherStormEntity> stormsToRender = new Int2ObjectOpenHashMap();
    private final Minecraft minecraft;
    private final EntityRenderDispatcher manager;
    private final Map<BlockPos, SuperBeaconDistantInstance> superBeacons = Maps.newHashMap();

    public WitherStormDistantRenderer(Minecraft minecraft) {
        this.minecraft = minecraft;
        this.manager = minecraft.m_91290_();
    }

    public WitherStormDistantRenderer() {
        this.minecraft = null;
        this.manager = null;
    }

    public void tick() {
        ClientLevel world = this.minecraft.f_91073_;
        if (world != null) {
            world.m_46473_().m_6180_("distantWitherStorms");
            this.stormsToRender.forEach((id, entity) -> {
                WitherStormEntity storm = entity;
                if (!storm.m_213877_() && !storm.m_20159_()) {
                    this.guardEntityTick(this::tickEntity, storm);
                }
            });
            this.removeAllPendingEntityRemovals();
            world.m_46473_().m_7238_();
        }
    }

    public void tickEntity(WitherStormEntity entity) {
        entity.m_146867_();
        ++entity.f_19797_;
        this.minecraft.f_91073_.m_46473_().m_6521_(() -> ForgeRegistries.ENTITY_TYPES.getKey((Object)entity.m_6095_()).toString());
        if (entity.canUpdate() && entity.m_9236_().m_6815_(entity.m_19879_()) == null) {
            entity.m_8119_();
        }
        this.minecraft.f_91073_.m_46473_().m_7238_();
    }

    public void guardEntityTick(Consumer<WitherStormEntity> consumer, WitherStormEntity entity) {
        try {
            TimeTracker.ENTITY_UPDATE.trackStart((Object)entity);
            consumer.accept(entity);
        }
        catch (Throwable t) {
            CrashReport report = CrashReport.m_127521_((Throwable)t, (String)"Distant Ticking WitherStormEntity");
            CrashReportCategory category = report.m_127514_("Trying to tick distant WitherStormEntity on client");
            entity.m_7976_(category);
            throw new ReportedException(report);
        }
        finally {
            TimeTracker.ENTITY_UPDATE.trackEnd((Object)entity);
        }
    }

    public void removeAllPendingEntityRemovals() {
        ObjectIterator iterator = this.stormsToRender.int2ObjectEntrySet().iterator();
        while (iterator.hasNext()) {
            Int2ObjectMap.Entry entry = (Int2ObjectMap.Entry)iterator.next();
            WitherStormEntity entity = (WitherStormEntity)entry.getValue();
            if (!entity.m_213877_()) continue;
            iterator.remove();
        }
    }

    public void renderTick(PoseStack stack, MultiBufferSource.BufferSource buffer, float partialTicks, Frustum clippinghelper) {
        if (this.minecraft.f_91073_ != null) {
            GameRenderer renderer = this.minecraft.f_91063_;
            Camera camera = renderer.m_109153_();
            boolean distantFog = (Boolean)WitherStormModConfig.CLIENT.distantFog.get();
            Vec3 pos = camera.m_90583_();
            if (distantFog) {
                boolean flag = this.minecraft.f_91073_.m_104583_().m_5781_(Mth.m_14107_((double)pos.f_82479_), Mth.m_14107_((double)pos.f_82480_)) || this.minecraft.f_91065_.m_93090_().m_93715_();
                float f = renderer.m_109152_();
                FogRenderer.m_109018_((Camera)camera, (float)partialTicks, (ClientLevel)this.minecraft.f_91073_, (int)((Integer)this.minecraft.f_91066_.m_231984_().m_231551_()), (float)1.0f);
                FogRenderer.m_109036_();
                FogRenderer.m_234172_((Camera)camera, (FogRenderer.FogMode)FogRenderer.FogMode.FOG_TERRAIN, (float)Math.max(f - 16.0f, 32.0f), (boolean)flag, (float)partialTicks);
            }
            this.minecraft.f_91073_.m_46473_().m_6182_("distant_wither_storms");
            for (Int2ObjectMap.Entry entry : this.stormsToRender.int2ObjectEntrySet()) {
                WitherStormEntity entity = (WitherStormEntity)entry.getValue();
                if (!this.manager.m_114397_((Entity)entity, clippinghelper, pos.f_82479_, pos.f_82480_, pos.f_82481_) || this.minecraft.f_91073_.m_6815_(entity.m_19879_()) != null) continue;
                float f1 = Mth.m_14179_((float)partialTicks, (float)entity.f_19859_, (float)entity.m_146908_());
                double x = Mth.m_14139_((double)partialTicks, (double)entity.f_19790_, (double)entity.m_20185_());
                double y = Mth.m_14139_((double)partialTicks, (double)entity.f_19791_, (double)entity.m_20186_());
                double z = Mth.m_14139_((double)partialTicks, (double)entity.f_19792_, (double)entity.m_20189_());
                int packedLight = this.manager.m_114394_((Entity)entity, partialTicks);
                this.manager.m_114384_((Entity)entity, x - pos.f_82479_, y - pos.f_82480_, z - pos.f_82481_, f1, partialTicks, stack, (MultiBufferSource)buffer, packedLight);
            }
            this.renderStorms(clippinghelper, pos, partialTicks, stack, (MultiBufferSource)buffer, (s, r) -> {
                if (((Boolean)WitherStormModConfig.CLIENT.renderDebrisRings.get()).booleanValue() && (!((Boolean)WitherStormModConfig.CLIENT.hideDebrisRingsUntilSplit.get()).booleanValue() || s.getPhase() > 5)) {
                    r.renderDebrisRings((WitherStormEntity)s, stack, (MultiBufferSource)buffer, partialTicks, this.manager.m_114394_((Entity)s, partialTicks));
                }
            });
            buffer.m_109911_();
            if (distantFog) {
                FogRenderer.m_109017_();
            }
            this.renderStorms(clippinghelper, pos, partialTicks, stack, (MultiBufferSource)buffer, (s, r) -> {
                if (!(!((Boolean)WitherStormModConfig.CLIENT.renderTractorBeams.get()).booleanValue() || s.m_20096_() && s.isPlayingDead())) {
                    int packedLight = this.manager.m_114394_((Entity)s, partialTicks);
                    r.prepareHeadAnimsForTractorBeams(s, partialTicks);
                    r.renderTractorBeams(s, stack, (MultiBufferSource)buffer, partialTicks, packedLight);
                }
            });
            this.renderStorms(clippinghelper, pos, partialTicks, stack, (MultiBufferSource)buffer, (s, r) -> {
                if (((Boolean)WitherStormModConfig.CLIENT.renderShine.get()).booleanValue() && s.shouldShine()) {
                    AbstractWitherStormRenderer.renderShine(s, stack, partialTicks, this.minecraft.f_91063_.m_109153_(), (MultiBufferSource)buffer);
                }
            });
            for (Map.Entry entry : this.superBeacons.entrySet()) {
                SuperBeaconDistantInstance instance = (SuperBeaconDistantInstance)entry.getValue();
                stack.m_85836_();
                stack.m_85837_((double)instance.pos.m_123341_() - pos.f_82479_, (double)instance.pos.m_123342_() - pos.f_82480_, (double)instance.pos.m_123343_() - pos.f_82481_);
                float camDist = (float)(pos.m_82554_(Vec3.m_82512_((Vec3i)instance.pos)) - (double)((float)((Integer)this.minecraft.f_91066_.m_231984_().m_231551_()).intValue() * 16.0f));
                float widthMul = (float)Math.max(1.0, (double)camDist * 0.01);
                AbstractSuperBeaconRenderer.renderBeam(instance.active, stack, instance.color, (MultiBufferSource)buffer, camDist > 0.0f ? 0.0f : partialTicks, camDist > 0.0f ? 0L : this.minecraft.f_91073_.m_46467_(), instance.beaconHeight, instance.beamWidth * widthMul, instance.outerBeamWidth * widthMul);
                stack.m_85849_();
            }
        }
    }

    private <T extends WitherStormEntity, M extends AbstractWitherStormModel<T>> void renderStorms(Frustum frustum, Vec3 cameraPos, float partialTicks, PoseStack stack, MultiBufferSource buffer, BiConsumer<T, AbstractWitherStormRenderer<T, M>> action) {
        for (Int2ObjectMap.Entry entry : this.stormsToRender.int2ObjectEntrySet()) {
            AbstractWitherStormRenderer renderer;
            WitherStormEntity entity = (WitherStormEntity)entry.getValue();
            if (!this.manager.m_114397_((Entity)entity, frustum, cameraPos.f_82479_, cameraPos.f_82480_, cameraPos.f_82481_) || this.minecraft.f_91073_.m_6815_(entity.m_19879_()) != null || (renderer = AbstractWitherStormRenderer.getRenderer(entity, this.manager)) == null) continue;
            double x = Mth.m_14139_((double)partialTicks, (double)entity.f_19790_, (double)entity.m_20185_());
            double y = Mth.m_14139_((double)partialTicks, (double)entity.f_19791_, (double)entity.m_20186_());
            double z = Mth.m_14139_((double)partialTicks, (double)entity.f_19792_, (double)entity.m_20189_());
            stack.m_85836_();
            stack.m_85837_(x - cameraPos.f_82479_, y - cameraPos.f_82480_, z - cameraPos.f_82481_);
            renderer.updateModel(entity);
            action.accept(entity, renderer);
            stack.m_85849_();
        }
    }

    public void addWitherStorm(int id, WitherStormEntity entity) {
        this.stormsToRender.put(id, (Object)entity);
    }

    public WitherStormEntity get(int id) {
        return (WitherStormEntity)this.stormsToRender.get(id);
    }

    public boolean contains(int id) {
        return this.stormsToRender.containsKey(id);
    }

    public Iterable<WitherStormEntity> getKnown() {
        return Iterables.unmodifiableIterable((Iterable)this.stormsToRender.values());
    }

    public void addAndOrUpdateSuperBeacon(BlockPos pos, int[] color, boolean active, int beaconHeight, float beamWidth, float outerBeamWidth) {
        SuperBeaconDistantInstance instance = this.superBeacons.computeIfAbsent(pos, p -> new SuperBeaconDistantInstance((BlockPos)p, color));
        instance.color = color;
        instance.active = active;
        instance.beaconHeight = beaconHeight;
        instance.beamWidth = beamWidth;
        instance.outerBeamWidth = outerBeamWidth;
    }

    public void removeSuperBeacon(BlockPos pos) {
        this.superBeacons.remove(pos);
    }

    public static List<WitherStormEntity> getAllStorms(ClientLevel level) {
        ArrayList storms = Lists.newArrayList();
        level.m_104735_().forEach(entity -> {
            if (entity instanceof WitherStormEntity) {
                WitherStormEntity storm = (WitherStormEntity)entity;
                storms.add(storm);
            }
        });
        level.getCapability(WitherStormModClientCapabilities.DISTANT_RENDERER).ifPresent(renderer -> {
            List ids = storms.stream().collect(Collectors.mapping(Entity::m_19879_, Collectors.toList()));
            renderer.getKnown().forEach(storm -> {
                if (!ids.contains(storm.m_19879_())) {
                    storms.add(storm);
                }
            });
        });
        return storms;
    }

    private static void setClipPlanes(Matrix4f mat, float near, float far) {
        mat.set(2, 2, -((far + near) / (far - near))).set(3, 2, -(2.0f * far * near / (far - near)));
    }

    public static class Events {
        @SubscribeEvent
        public static void renderTickDistantRenderer(RenderLevelStageEvent event) {
            if (event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_PARTICLES)) {
                Events.render(event.getPoseStack(), event.getPartialTick());
            }
        }

        @SubscribeEvent
        public static void clientTickDistantRenderer(TickEvent.ClientTickEvent event) {
            ClientLevel world;
            Minecraft mc = Minecraft.m_91087_();
            if (event.phase == TickEvent.Phase.START && (world = mc.f_91073_) != null) {
                world.getCapability(WitherStormModClientCapabilities.DISTANT_RENDERER).ifPresent(distantRenderer -> {
                    if (!(mc.m_91104_() && mc.m_91091_() || !((Boolean)WitherStormModConfig.CLIENT.distantRenderer.get()).booleanValue())) {
                        distantRenderer.tick();
                    }
                });
            }
        }

        public static void render(PoseStack stack, float partialTicks) {
            if (((Boolean)WitherStormModConfig.CLIENT.distantRenderer.get()).booleanValue()) {
                Minecraft mc = Minecraft.m_91087_();
                ClientLevel world = mc.f_91073_;
                world.getCapability(WitherStormModClientCapabilities.DISTANT_RENDERER).ifPresent(distantRenderer -> {
                    Matrix4f originalMatrix = RenderSystem.getProjectionMatrix();
                    GameRenderer renderer = mc.f_91063_;
                    Camera renderInfo = renderer.m_109153_();
                    Matrix4f projection = null;
                    if (!CompatHelper.isVrActive()) {
                        double fov = ((IMixinGameRenderer)renderer).callGetFov(renderInfo, partialTicks, true);
                        projection = new Matrix4f().perspective((float)(fov * 0.01745329238474369), (float)mc.m_91268_().m_85441_() / (float)mc.m_91268_().m_85442_(), 0.05f, renderer.m_109152_() * 180.0f);
                        Matrix4f defaultProjection = renderer.m_253088_(fov);
                        Matrix4f invertedDefaultProjection = new Matrix4f((Matrix4fc)defaultProjection);
                        invertedDefaultProjection.invert();
                        Matrix4f distortionMatrix = new Matrix4f((Matrix4fc)invertedDefaultProjection);
                        distortionMatrix.mul((Matrix4fc)originalMatrix);
                        projection.mul((Matrix4fc)distortionMatrix);
                    } else {
                        projection = new Matrix4f((Matrix4fc)originalMatrix);
                        WitherStormDistantRenderer.setClipPlanes(projection, 0.05f, renderer.m_109152_() * 180.0f);
                    }
                    renderer.m_252879_(projection);
                    Vec3 pos = renderInfo.m_90583_();
                    MultiBufferSource.BufferSource buffer = mc.m_91269_().m_110104_();
                    Frustum clippinghelper = new Frustum(stack.m_85850_().m_252922_(), projection);
                    clippinghelper.m_113002_(pos.m_7096_(), pos.m_7098_(), pos.m_7094_());
                    distantRenderer.renderTick(stack, buffer, partialTicks, clippinghelper);
                    buffer.m_109911_();
                    renderer.m_252879_(originalMatrix);
                });
            }
        }
    }
}

