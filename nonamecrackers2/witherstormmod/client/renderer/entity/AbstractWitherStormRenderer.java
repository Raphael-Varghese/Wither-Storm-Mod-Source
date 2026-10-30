/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.PoseStack$Pose
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap$Entry
 *  javax.annotation.Nullable
 *  net.minecraft.client.Camera
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.ModelPart$Cube
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.renderer.LevelRenderer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.entity.EntityRenderDispatcher
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.animal.Sheep
 *  net.minecraft.world.item.DyeColor
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  nonamecrackers2.crackerslib.common.compat.CompatHelper
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 *  org.joml.Vector3f
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import java.awt.Color;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.crackerslib.common.compat.CompatHelper;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.client.instancing.RenderBufferer;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.SantaHatModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.AbstractWitherStormModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.HeadModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormCommandBlockModel;
import nonamecrackers2.witherstormmod.client.rendertype.UtilRenderTypes;
import nonamecrackers2.witherstormmod.client.resources.WitherStormResourceConfigManager;
import nonamecrackers2.witherstormmod.client.resources.color.ColorSet;
import nonamecrackers2.witherstormmod.client.resources.texture.TextureSet;
import nonamecrackers2.witherstormmod.client.util.SpecialDay;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.MainHead;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
import nonamecrackers2.witherstormmod.common.entity.section.Section;
import nonamecrackers2.witherstormmod.common.util.DebrisCluster;
import nonamecrackers2.witherstormmod.common.util.DebrisRingSettings;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public abstract class AbstractWitherStormRenderer<T extends WitherStormEntity, M extends AbstractWitherStormModel<T>>
extends EntityRenderer<T> {
    private static final Color MAIN_HEAD_COLOR = new Color(1.0f, 0.6f, 0.0f);
    private static final Color EXTRA_HEAD_COLOR = new Color(1.0f, 1.0f, 0.0f);
    public static final ResourceLocation WITHER_STORM_INVULNERABLE_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/wither_storm/wither_storm_invulnerable.png");
    public static final ResourceLocation WITHER_STORM_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/wither_storm/wither_storm.png");
    public static final ResourceLocation WITHER_STORM_EXPLODING_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/wither_storm/wither_storm_exploding.png");
    public static final ResourceLocation EMISSIVE_DECAL = new ResourceLocation("witherstormmod", "textures/entity/wither_storm/wither_storm_emissive_decal.png");
    public static final ResourceLocation HURT_OVERLAY = new ResourceLocation("witherstormmod", "textures/entity/wither_storm/wither_storm_hurt_overlay.png");
    public static final ResourceLocation PULSE = new ResourceLocation("witherstormmod", "textures/entity/wither_storm/wither_storm_pulse.png");
    public static final ResourceLocation DEBRIS_RING = new ResourceLocation("witherstormmod", "textures/entity/wither_storm/debris.png");
    public static final ResourceLocation SHINE = new ResourceLocation("witherstormmod", "textures/entity/wither_storm/shine.png");
    public static final ResourceLocation PINK_WITHER_STORM_LOCATION = new ResourceLocation("witherstormmod", "textures/misc/pink_wither_storm.png");
    public static final ResourceLocation WITHER_ARMOR_LOCATION = new ResourceLocation("textures/entity/wither/wither_armor.png");
    protected final SantaHatModel santaHat;
    @Nullable
    protected final SpecialDay specialDay;
    protected M model;
    protected WitherStormCommandBlockModel<T> swirlModel;

    public AbstractWitherStormRenderer(EntityRendererProvider.Context context, M model) {
        super(context);
        this.model = model;
        this.swirlModel = new WitherStormCommandBlockModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_ARMOR));
        this.santaHat = new SantaHatModel(context.m_174023_(WitherStormModRenderers.SANTA_HAT));
        this.specialDay = SpecialDay.getForCurrentDate();
    }

    public void updateModel(T entity) {
        this.model = this.fetchModel(entity);
    }

    public M getModel() {
        return this.model;
    }

    public abstract M fetchModel(T var1);

    protected int getBlockLightLevel(T entity, BlockPos pos) {
        return Math.max(0, (int)((100.0f - ((WitherStormEntity)entity).getFadeAnimation()) / 4.0f - 10.0f));
    }

    public void render(T entity, float yRot, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
        stack.m_85836_();
        this.updateModel(entity);
        AbstractWitherStormRenderer.renderDebrisClusters(this.getTextureLocation(entity), entity, stack, buffer, partialTicks, packedLight);
        float yBodyRot = Mth.m_14189_((float)partialTicks, (float)((WitherStormEntity)entity).f_20884_, (float)((WitherStormEntity)entity).f_20883_);
        float xBodyRot = Mth.m_14189_((float)partialTicks, (float)((WitherStormEntity)entity).xBodyRotO, (float)((WitherStormEntity)entity).xBodyRot);
        float yHeadRot = Mth.m_14189_((float)partialTicks, (float)((WitherStormEntity)entity).f_20886_, (float)((WitherStormEntity)entity).f_20885_);
        float netRot = yHeadRot - yBodyRot;
        float xRot = Mth.m_14179_((float)partialTicks, (float)((WitherStormEntity)entity).f_19860_, (float)entity.m_146909_());
        float bob = (float)((WitherStormEntity)entity).f_19797_ + partialTicks;
        this.setupRotations(entity, stack, xBodyRot, yBodyRot, partialTicks);
        stack.m_85841_(-1.0f, -1.0f, 1.0f);
        this.scale(entity, stack, partialTicks);
        stack.m_252880_(0.0f, -1.501f, 0.0f);
        ((AbstractWitherStormModel)this.model).setupAnimations(entity, partialTicks, bob, netRot, xRot);
        int i = LivingEntityRenderer.m_115338_(entity, (float)0.0f);
        boolean shadersEnabled = CompatHelper.areShadersRunning();
        RenderType emissive = (Boolean)WitherStormModConfig.CLIENT.renderEmissiveDecalForHeads.get() != false ? UtilRenderTypes.emissiveTranslucent(this.getEmissiveDecalLocation(entity)) : RenderType.m_110452_((ResourceLocation)this.getEmissiveDecalLocation(entity));
        RenderType massEmissive = RenderType.m_110488_((ResourceLocation)this.getEmissiveDecalLocation(entity));
        RenderType hurtOverlay = RenderType.m_110473_((ResourceLocation)HURT_OVERLAY);
        if (((WitherStormEntity)entity).getDeathTime() > 0) {
            float fade = Math.min((float)((WitherStormEntity)entity).getDeathTime(), 400.0f) / 400.0f;
            RenderType explodingType = shadersEnabled ? RenderType.m_173235_((ResourceLocation)this.getExplodingTextureLocation(entity)) : UtilRenderTypes.witherStormDissolve(this.getExplodingTextureLocation(entity));
            ((AbstractWitherStormModel)this.model).render(entity, stack, buffer, explodingType, null, null, hurtOverlay, packedLight, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, fade);
            RenderType decalType = shadersEnabled ? RenderType.m_110479_((ResourceLocation)this.getTextureLocation(entity)) : UtilRenderTypes.witherStormDecal(this.getTextureLocation(entity));
            ((AbstractWitherStormModel)this.model).render(entity, stack, buffer, decalType, emissive, massEmissive, hurtOverlay, packedLight, i, 1.0f, 1.0f, 1.0f, 1.0f);
        } else {
            ((AbstractWitherStormModel)this.model).render(entity, stack, buffer, shadersEnabled ? RenderType.m_110458_((ResourceLocation)this.getTextureLocation(entity)) : UtilRenderTypes.witherStorm(this.getTextureLocation(entity)), emissive, massEmissive, hurtOverlay, packedLight, i, 1.0f, 1.0f, 1.0f, 1.0f);
        }
        if (((WitherStormEntity)entity).m_7090_()) {
            this.renderEnergyShield(entity, stack, buffer, partialTicks, bob, netRot, xRot, packedLight, i);
        }
        if (this.specialDay == SpecialDay.CHRISTMAS) {
            this.renderChristmasFestivities(entity, stack, buffer, packedLight, i);
        }
        if (((Boolean)WitherStormModConfig.CLIENT.renderPulse.get()).booleanValue() && ((WitherStormEntity)entity).isBeingTornApart() && !entity.m_21224_()) {
            this.renderPulsing(entity, stack, buffer, partialTicks, packedLight, i);
        }
        stack.m_85849_();
        super.m_7392_(entity, yRot, partialTicks, stack, buffer, packedLight);
    }

    protected void renderEnergyShield(T entity, PoseStack stack, MultiBufferSource bufferSource, float partialTicks, float bob, float yRot, float xRot, int packedLight, int overlayTexture) {
        this.swirlModel.setupAnimations(entity, partialTicks, bob, yRot, xRot);
        float xOffset = Mth.m_14089_((float)(bob * 0.02f)) * 3.0f;
        RenderType type = RenderType.m_110436_((ResourceLocation)WITHER_ARMOR_LOCATION, (float)(xOffset % 1.0f), (float)(bob * 0.01f % 1.0f));
        this.swirlModel.render(entity, stack, bufferSource, type, null, null, null, packedLight, overlayTexture, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    protected void renderChristmasFestivities(T entity, PoseStack stack, MultiBufferSource bufferSource, int packedLight, int overlayTexture) {
        for (Int2ObjectMap.Entry entry : ((AbstractWitherStormModel)this.getModel()).heads.int2ObjectEntrySet()) {
            if (((WitherStormEntity)entity).areOtherHeadsDisabled() && entry.getIntKey() != 0) continue;
            stack.m_85836_();
            HeadModel model = (HeadModel)entry.getValue();
            model.scale(stack);
            model.root().m_104299_(stack);
            this.santaHat.m_7695_(stack, bufferSource.m_6299_(this.santaHat.m_103119_(SantaHatModel.TEXTURE)), packedLight, overlayTexture, 1.0f, 1.0f, 1.0f, 1.0f);
            stack.m_85849_();
        }
    }

    protected void renderPulsing(T entity, PoseStack stack, MultiBufferSource bufferSource, float partialTicks, int packedLight, int overlayTexture) {
        if (((AbstractWitherStormModel)this.model).getMassModel(entity) != null) {
            VertexConsumer builder = bufferSource.m_6299_(RenderType.m_110473_((ResourceLocation)this.getPulseTextureLocation(entity)));
            for (int k = 0; k < this.getPulseAmount(entity); ++k) {
                float tick = (float)(((WitherStormEntity)entity).f_19797_ + k) + partialTicks;
                long seed = (long)(tick / 20.0f);
                RandomSource random = RandomSource.m_216335_((long)(seed + (long)((double)k * Math.PI)));
                ModelPart part = ((AbstractWitherStormModel)this.model).getRandomPart(entity, random);
                Vector3f pos = AbstractWitherStormRenderer.getRandomPointOnCubeSurface(part.m_233558_(random), random);
                ModelPart mirror = new ModelPart(CubeListBuilder.m_171558_().m_171514_(random.m_188503_(28), Math.max(16, random.m_188503_(30))).m_171488_(pos.x(), pos.y(), pos.z(), 1.0f, 1.0f, 1.0f, new CubeDeformation(0.01f)).m_171557_().stream().map(def -> def.m_171455_(32, 32)).collect(Collectors.toList()), (Map)Maps.newHashMap());
                ModelPart mass = ((AbstractWitherStormModel)this.model).getMassModel(entity);
                mirror.f_104200_ = mass.f_104200_;
                mirror.f_104201_ = mass.f_104201_;
                mirror.f_104202_ = mass.f_104202_;
                if (part != mass) {
                    mirror.f_104200_ += part.f_104200_;
                    mirror.f_104201_ += part.f_104201_;
                    mirror.f_104202_ += part.f_104202_;
                }
                mirror.f_104203_ = part.f_104203_;
                mirror.f_104204_ = part.f_104204_;
                mirror.f_104205_ = part.f_104205_;
                stack.m_85836_();
                stack.m_85841_(((WitherStormEntity)entity).isMirrored() ? -1.0f : 1.0f, 1.0f, 1.0f);
                ((AbstractWitherStormModel)this.model).scaleMass(stack);
                float fade = 1.0f - tick % 20.0f * 0.1f / 2.0f;
                int overlayCoords = LivingEntityRenderer.m_115338_(entity, (float)0.0f);
                mirror.m_104306_(stack, builder, packedLight, overlayCoords, 1.0f, 1.0f, 1.0f, fade);
                stack.m_85849_();
            }
        }
    }

    public void renderTractorBeams(T entity, PoseStack stack, MultiBufferSource buffer, float partialTicks, int packedLight) {
        for (Int2ObjectMap.Entry entry : ((AbstractWitherStormModel)this.getModel()).heads.int2ObjectEntrySet()) {
            HeadModel head = (HeadModel)entry.getValue();
            stack.m_85836_();
            if (head.shouldRenderTractorBeam(entity, entry.getIntKey())) {
                double cutoff;
                float b;
                float g;
                float r;
                WitherStormSegmentEntity segment;
                WitherStormEntity parent;
                boolean rainbow;
                Minecraft mc = Minecraft.m_91087_();
                Vec3 pos = mc.f_91063_.m_109153_().m_90583_();
                double distance = pos.m_82554_(entity.m_20182_());
                float renderDistance = (float)((Integer)mc.f_91066_.m_231984_().m_231551_()).intValue() / 16.0f;
                float distLerp = AbstractWitherStormRenderer.getDistanceLerp(distance, renderDistance);
                float nightLerp = AbstractWitherStormRenderer.getNightTimeLerp(entity.m_9236_(), partialTicks);
                float alpha = distLerp * (1.0f - nightLerp);
                boolean bl = rainbow = entity.m_8077_() && entity.m_7755_().getString().equals("jeb_");
                if (!rainbow && entity instanceof WitherStormSegmentEntity && (parent = (segment = (WitherStormSegmentEntity)entity).getParent()) != null && parent.m_8077_() && parent.m_7755_().getString().equals("jeb_")) {
                    rainbow = true;
                }
                if (rainbow) {
                    float[] rainbowColor = AbstractWitherStormRenderer.getRainbowColor(entity, entry.getIntKey(), partialTicks);
                    r = rainbowColor[0];
                    g = rainbowColor[1];
                    b = rainbowColor[2];
                } else if (this.specialDay != null) {
                    Color color = this.specialDay.getColor((WitherStormEntity)entity, partialTicks, entry.getIntKey());
                    r = (float)color.getRed() / 255.0f;
                    g = (float)color.getGreen() / 255.0f;
                    b = (float)color.getBlue() / 255.0f;
                } else {
                    ColorSet set = WitherStormResourceConfigManager.INSTANCE.getColorSetByPhase(((WitherStormEntity)entity).getPhase());
                    Color color = set.tractorBeamColor();
                    Color night = set.tractorBeamNightColor();
                    r = Mth.m_14179_((float)nightLerp, (float)color.getRed(), (float)night.getRed()) / 255.0f;
                    g = Mth.m_14179_((float)nightLerp, (float)color.getGreen(), (float)night.getGreen()) / 255.0f;
                    b = Mth.m_14179_((float)nightLerp, (float)color.getBlue(), (float)night.getBlue()) / 255.0f;
                }
                if (((Boolean)WitherStormModConfig.CLIENT.distantFog.get()).booleanValue()) {
                    float rDelta = 0.3f - r;
                    float gDelta = 0.3f - g;
                    float bDelta = 0.3f - b;
                    r += rDelta * alpha;
                    g += gDelta * alpha;
                    b += bDelta * alpha;
                }
                if ((cutoff = ((WitherStormEntity)entity).getTractorBeamCutoffDistance(entry.getIntKey())) != -1.0) {
                    cutoff = cutoff / (double)((AbstractWitherStormModel)this.model).headScale + 10.0;
                }
                head.renderTractorBeam(entity, stack, buffer, packedLight, r, g, b, 0.5f, partialTicks, cutoff, 0.5f * (1.0f - distLerp));
            }
            stack.m_85849_();
        }
    }

    private static float[] getRainbowColor(WitherStormEntity entity, int offset, float partialTicks) {
        int tickCount = entity.f_19797_ / 25 + entity.m_19879_() + offset;
        int allDyeColors = DyeColor.values().length;
        int k = tickCount % allDyeColors;
        int l = (tickCount + 1) % allDyeColors;
        float f3 = ((float)(entity.f_19797_ % 25) + partialTicks) / 25.0f;
        float[] afloat1 = Sheep.m_29829_((DyeColor)DyeColor.m_41053_((int)k));
        float[] afloat2 = Sheep.m_29829_((DyeColor)DyeColor.m_41053_((int)l));
        float r = afloat1[0] * (1.0f - f3) + afloat2[0] * f3;
        float g = afloat1[1] * (1.0f - f3) + afloat2[1] * f3;
        float b = afloat1[2] * (1.0f - f3) + afloat2[2] * f3;
        return new float[]{r, g, b};
    }

    public void prepareHeadAnimsForTractorBeams(T entity, float partialTicks) {
        for (Int2ObjectMap.Entry entry : ((AbstractWitherStormModel)this.model).heads.int2ObjectEntrySet()) {
            HeadModel head = (HeadModel)entry.getValue();
            float yBodyRot = Mth.m_14189_((float)partialTicks, (float)((WitherStormEntity)entity).f_20884_, (float)((WitherStormEntity)entity).f_20883_);
            float yHeadRot = Mth.m_14189_((float)partialTicks, (float)((WitherStormEntity)entity).f_20886_, (float)((WitherStormEntity)entity).f_20885_);
            float yRot = yHeadRot - yBodyRot;
            float xRot = Mth.m_14179_((float)partialTicks, (float)((WitherStormEntity)entity).f_19860_, (float)entity.m_146909_());
            float bob = (float)((WitherStormEntity)entity).f_19797_ + partialTicks;
            head.setupAnimations(entity, partialTicks, bob, yRot, xRot, entry.getIntKey());
        }
    }

    public boolean shouldRender(T entity, Frustum clipping, double x, double y, double z) {
        if (!entity.m_6000_(x, y, z)) {
            return false;
        }
        if (!((WitherStormEntity)entity).isOnDistantRenderer() && ((WitherStormEntity)entity).f_19811_) {
            return true;
        }
        AABB box = ((WitherStormEntity)entity).m_6921_().m_82400_(0.5);
        if (box.m_82392_() || box.m_82309_() == 0.0) {
            box = new AABB(entity.m_20185_() - 2.0, entity.m_20186_() - 2.0, entity.m_20189_() - 2.0, entity.m_20185_() + 2.0, entity.m_20186_() + 2.0, entity.m_20189_() + 2.0);
        }
        return clipping.m_113029_(box);
    }

    protected boolean shouldShowName(T entity) {
        return super.m_6512_(entity) && (entity.m_6052_() || entity.m_8077_() && entity == this.f_114476_.f_114359_);
    }

    public void renderDebrisRings(WitherStormEntity entity, PoseStack stack, MultiBufferSource buffer, float partialTicks, int packedLightIn) {
        Minecraft mc = Minecraft.m_91087_();
        Vec3 pos = mc.f_91063_.m_109153_().m_90583_();
        double distance = pos.m_82554_(entity.m_20182_());
        float baseAlpha = Mth.m_14036_((float)((float)(400.0 - distance) * 0.005f), (float)0.2f, (float)0.5f);
        ResourceLocation tex = WitherStormResourceConfigManager.INSTANCE.getTextureSetByPhase(entity.getPhase()).debrisRing();
        VertexConsumer debrisBuilder = buffer.m_6299_(RenderType.m_110470_((ResourceLocation)tex));
        for (DebrisRingSettings settings : entity.getDebrisRings()) {
            if (!(settings.alpha() > 0.0f) || entity.getPhase() < settings.getPhaseRequirement()) continue;
            stack.m_85836_();
            int segments = settings.getSegments();
            float bottomRadius = settings.getBottomRadius();
            float topRadius = settings.getTopRadius();
            float y = settings.getY();
            float height = settings.getHeight();
            float alpha = settings.alpha() * baseAlpha;
            PoseStack.Pose entry = stack.m_85850_();
            Matrix4f matrix4f = entry.m_252922_();
            Matrix3f matrix3f = entry.m_252943_();
            float u = 0.0f;
            float v = 0.0f;
            float uMax = 1.0f;
            float vMax = 1.0f;
            float tickCount = ((float)entity.f_19797_ + partialTicks) * (settings.getSpeedModifier() * (settings.clockwise() ? 1.0f : -1.0f));
            for (int i = 0; i < segments; ++i) {
                float theta = (float)(Math.PI * 2 / (double)segments);
                float angle = theta * (float)i + tickCount;
                float x = Mth.m_14089_((float)angle);
                float z = Mth.m_14031_((float)angle);
                float angle2 = theta * (float)(i + 1) + tickCount;
                float x2 = Mth.m_14089_((float)angle2);
                float z2 = Mth.m_14031_((float)angle2);
                debrisBuilder.m_252986_(matrix4f, x * bottomRadius, y, z * bottomRadius).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(u, v).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
                debrisBuilder.m_252986_(matrix4f, x * topRadius, height, z * topRadius).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(u, vMax).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
                debrisBuilder.m_252986_(matrix4f, x2 * topRadius, height, z2 * topRadius).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMax, vMax).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
                debrisBuilder.m_252986_(matrix4f, x2 * bottomRadius, y, z2 * bottomRadius).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMax, v).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
                debrisBuilder.m_252986_(matrix4f, x2 * bottomRadius, y, z2 * bottomRadius).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMax, vMax).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
                debrisBuilder.m_252986_(matrix4f, x2 * topRadius, height, z2 * topRadius).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMax, v).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
                debrisBuilder.m_252986_(matrix4f, x * topRadius, height, z * topRadius).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(u, v).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
                debrisBuilder.m_252986_(matrix4f, x * bottomRadius, y, z * bottomRadius).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(u, vMax).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            }
            stack.m_85849_();
        }
    }

    public static void renderDebrisClusters(ResourceLocation tex, WitherStormEntity entity, PoseStack stack, MultiBufferSource bufferSource, float partialTicks, int packedLight) {
        if (((Boolean)WitherStormModConfig.CLIENT.renderDebrisCloud.get()).booleanValue() && (!((Boolean)WitherStormModConfig.CLIENT.renderDistantDebris.get()).booleanValue() && !entity.isOnDistantRenderer() || ((Boolean)WitherStormModConfig.CLIENT.renderDistantDebris.get()).booleanValue())) {
            for (DebrisCluster cluster : entity.getDebrisClusters()) {
                float vMax;
                float uMax;
                float v;
                float u;
                boolean flag;
                if (cluster.isDisabled() || cluster.getRenderPhase() > entity.getPhase()) continue;
                float orbitalAngle = cluster.getOrbitalAngle(partialTicks);
                stack.m_85836_();
                stack.m_85837_(0.0, (double)cluster.getVerticalOffset(), 0.0);
                stack.m_252781_(Axis.f_252436_.m_252977_(orbitalAngle));
                stack.m_85837_((double)cluster.getRadiusFromCenter(), 0.0, 0.0);
                stack.m_252781_(Axis.f_252529_.m_252977_(cluster.getXRot(partialTicks)));
                stack.m_252781_(Axis.f_252436_.m_252977_(cluster.getYRot(partialTicks)));
                boolean bl = flag = cluster.isGlowing() && entity.getPhase() > 5 || cluster.isForcedGlowing();
                if (flag) {
                    u = 0.3125f;
                    v = 0.3125f;
                    uMax = 0.375f;
                    vMax = 0.375f;
                } else {
                    u = 0.9f;
                    v = 0.8f;
                    uMax = 1.0f;
                    vMax = 0.9f;
                }
                RenderBufferer.pushCullFaces();
                if (flag) {
                    RenderBufferer.pushNoFog();
                }
                RenderBufferer.buildAndOrRender(cluster.toString() + flag + tex, RenderType.m_110452_((ResourceLocation)tex), (bstack, consumer, bpackedLight, boverlayTexture, br, bg, bb, ba) -> AbstractWitherStormRenderer.renderPieces(cluster, bstack, consumer, br, bg, bb, ba, boverlayTexture, bpackedLight, u, v, uMax, vMax), stack, packedLight, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
                if (flag) {
                    RenderBufferer.pushNoFog();
                    RenderBufferer.buildAndOrRender(cluster.toString() + ", " + flag + ", emissive" + tex, RenderType.m_110488_((ResourceLocation)tex), (bstack, consumer, bpackedLight, boverlayTexture, br, bg, bb, ba) -> AbstractWitherStormRenderer.renderPieces(cluster, bstack, consumer, br, bg, bb, ba, boverlayTexture, bpackedLight, u, v, uMax, vMax), stack, packedLight, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
                }
                RenderBufferer.popCullFaces();
                stack.m_85849_();
            }
        }
    }

    private static void renderPieces(DebrisCluster cluster, PoseStack bstack, VertexConsumer consumer, float br, float bg, float bb, float ba, int boverlayTexture, int bpackedLight, float u, float v, float uMax, float vMax) {
        for (DebrisCluster.Piece piece : cluster.getPieces()) {
            bstack.m_85836_();
            bstack.m_252880_(piece.x(), piece.y(), piece.z());
            Matrix4f matrix4f = bstack.m_85850_().m_252922_();
            Matrix3f matrix3f = bstack.m_85850_().m_252943_();
            float startSize = piece.size();
            float endSize = -piece.size();
            consumer.m_252986_(matrix4f, startSize, startSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(u, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, startSize, endSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(u, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, endSize, endSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, endSize, startSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, endSize, startSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(u, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, endSize, endSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(u, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, endSize, endSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, endSize, startSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, startSize, startSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(u, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, startSize, endSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(u, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, startSize, endSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, startSize, startSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, endSize, startSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(u, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, endSize, endSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(u, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, startSize, endSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, startSize, startSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, startSize, startSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(u, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, startSize, startSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(u, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, endSize, startSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, endSize, startSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, startSize, endSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(u, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, endSize, endSize, startSize).m_85950_(br, bg, bb, ba).m_7421_(u, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, endSize, endSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, vMax).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix4f, startSize, endSize, endSize).m_85950_(br, bg, bb, ba).m_7421_(uMax, v).m_86008_(boverlayTexture).m_85969_(bpackedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
            bstack.m_85849_();
        }
    }

    public ResourceLocation getTextureLocation(T entity) {
        TextureSet set = WitherStormResourceConfigManager.INSTANCE.getTextureSetByPhase(((WitherStormEntity)entity).getPhase());
        boolean flag = WitherStormMod.isAprilFools() && (Boolean)WitherStormModConfig.CLIENT.aprilFools.get() != false;
        int i = ((WitherStormEntity)entity).getInvulnerableTicks();
        return i > 0 && (i > 80 || i / 5 % 2 != 1) ? set.invulnerable() : (flag ? PINK_WITHER_STORM_LOCATION : set.main());
    }

    public ResourceLocation getExplodingTextureLocation(T entity) {
        return WITHER_STORM_EXPLODING_LOCATION;
    }

    public ResourceLocation getPulseTextureLocation(T entity) {
        return PULSE;
    }

    public ResourceLocation getEmissiveDecalLocation(T entity) {
        return WitherStormResourceConfigManager.INSTANCE.getTextureSetByPhase(((WitherStormEntity)entity).getPhase()).emissiveDecal();
    }

    protected void scale(T entity, PoseStack stack, float partialTicks) {
        float f = 2.0f;
        int i = ((WitherStormEntity)entity).getInvulnerableTicks();
        int j = Math.max(0, i - 750);
        if (j > 0) {
            f -= ((float)j - partialTicks) / (float)Math.max(0, ((WitherStormEntity)entity).getStartingInvulnerableTicks() - 750) * 0.5f;
        }
        stack.m_85841_(f, f, f);
    }

    protected void setupRotations(T entity, PoseStack stack, float xBodyRot, float yBodyRot, float partialTicks) {
        stack.m_252781_(Axis.f_252436_.m_252977_(180.0f - yBodyRot));
        stack.m_252781_(Axis.f_252529_.m_252977_(xBodyRot));
    }

    public int getPulseAmount(T entity) {
        return (int)((float)((WitherStormEntity)entity).getPhase() * 15.0f / 2.0f * ((Boolean)WitherStormModConfig.CLIENT.lowResModels.get() != false ? 3.0f : 1.0f));
    }

    @Nullable
    public static <T extends WitherStormEntity, M extends AbstractWitherStormModel<T>> AbstractWitherStormRenderer<T, M> getRenderer(T storm, EntityRenderDispatcher manager) {
        EntityRenderer raw = manager.m_114382_(storm);
        if (raw instanceof AbstractWitherStormRenderer) {
            return (AbstractWitherStormRenderer)raw;
        }
        return null;
    }

    public static void renderShine(WitherStormEntity storm, PoseStack stack, float partialTicks, Camera camera, MultiBufferSource buffer) {
        int[] color;
        WitherStormSegmentEntity segment;
        WitherStormEntity parent;
        boolean rainbow;
        Minecraft mc = Minecraft.m_91087_();
        Color configColor = WitherStormResourceConfigManager.INSTANCE.getColorSetByPhase(storm.getPhase()).nightShineColor();
        boolean bl = rainbow = storm.m_8077_() && storm.m_7755_().getString().equals("jeb_");
        if (!rainbow && storm instanceof WitherStormSegmentEntity && (parent = (segment = (WitherStormSegmentEntity)storm).getParent()) != null && parent.m_8077_() && parent.m_7755_().getString().equals("jeb_")) {
            rainbow = true;
        }
        if (!rainbow) {
            color = new int[]{configColor.getRed(), configColor.getGreen(), configColor.getBlue(), configColor.getAlpha()};
        } else {
            float[] rainbowColor = AbstractWitherStormRenderer.getRainbowColor(storm, 0, partialTicks);
            color = new int[]{(int)(rainbowColor[0] * 255.0f), (int)(rainbowColor[1] * 255.0f), (int)(rainbowColor[2] * 255.0f), 75};
        }
        float distanceLerp = 1.0f;
        if (storm.getPhase() > 5 || storm.getConsumptionAmountForPhase(5) <= storm.getConsumedEntities()) {
            distanceLerp = AbstractWitherStormRenderer.getDistanceLerp(storm.m_146892_().m_82554_(camera.m_90583_()), (float)((Integer)mc.f_91066_.m_231984_().m_231551_()).intValue() / 8.0f);
        }
        float f1 = AbstractWitherStormRenderer.getNightTimeLerp(storm.m_9236_(), partialTicks) * distanceLerp * storm.getShineAlpha(partialTicks);
        color[3] = Mth.m_14143_((float)((float)color[3] * f1));
        if (color[3] > 0) {
            VertexConsumer consumer = buffer.m_6299_(RenderType.m_110473_((ResourceLocation)SHINE));
            Matrix4f matrix = stack.m_85850_().m_252922_();
            Vec3 dir = camera.m_90583_().m_82546_(storm.m_20299_(partialTicks)).m_82541_();
            float pitch = (float)Math.asin(dir.f_82480_);
            float yaw = (float)Math.atan2(dir.f_82479_, dir.f_82481_);
            stack.m_252781_(Axis.f_252392_.m_252977_(180.0f));
            stack.m_252781_(Axis.f_252436_.m_252961_(yaw));
            stack.m_252781_(Axis.f_252529_.m_252961_(pitch));
            float scale = storm.getShineScale();
            float xStretch = scale * (storm.getPhase() > 5 ? 1.5f : 1.0f);
            stack.m_252880_(-xStretch / 2.0f, storm.getUnmodifiedHeight() / 2.0f - scale / 2.0f, scale / 2.0f);
            stack.m_85841_(xStretch, scale, 1.0f);
            consumer.m_252986_(matrix, 0.0f, 0.0f, 0.0f).m_6122_(color[0], color[1], color[2], color[3]).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_5601_(0.0f, 1.0f, 1.0f).m_5752_();
            consumer.m_252986_(matrix, 0.0f, 1.0f, 0.0f).m_6122_(color[0], color[1], color[2], color[3]).m_7421_(0.0f, 1.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_5601_(0.0f, 1.0f, 1.0f).m_5752_();
            consumer.m_252986_(matrix, 1.0f, 1.0f, 0.0f).m_6122_(color[0], color[1], color[2], color[3]).m_7421_(1.0f, 1.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_5601_(0.0f, 1.0f, 1.0f).m_5752_();
            consumer.m_252986_(matrix, 1.0f, 0.0f, 0.0f).m_6122_(color[0], color[1], color[2], color[3]).m_7421_(1.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_5601_(0.0f, 1.0f, 1.0f).m_5752_();
        }
    }

    public static float getNightTimeLerp(Level level, float partialTicks) {
        float f1 = Mth.m_14089_((float)(level.m_46942_(partialTicks) * ((float)Math.PI * 2))) * 2.0f + 0.5f;
        return 1.0f - Mth.m_14036_((float)(f1 + 0.5f), (float)0.0f, (float)1.0f);
    }

    public static float getDistanceLerp(double distance, float renderDistance) {
        return Mth.m_14036_((float)((float)(distance - (double)(200.0f * renderDistance)) * 0.005f), (float)0.0f, (float)1.0f);
    }

    public static Vector3f getRandomPointOnCubeSurface(ModelPart.Cube box, RandomSource random) {
        boolean flag;
        Vector3f pos = new Vector3f(box.f_104335_, box.f_104336_, box.f_104337_);
        float xDif = box.f_104338_ - pos.x();
        float yDif = box.f_104339_ - pos.y();
        float zDif = box.f_104340_ - pos.z();
        boolean bl = flag = xDif > 0.0f && yDif > 0.0f && zDif > 0.0f;
        if (flag) {
            Direction direction = Direction.m_235672_((RandomSource)random);
            if (direction.m_122434_().equals((Object)Direction.Axis.X)) {
                pos.set(direction.equals((Object)Direction.WEST) ? box.f_104335_ : box.f_104338_ - 1.0f, pos.y, pos.z);
                pos.set(pos.x, pos.y() + (float)random.m_188503_((int)yDif), pos.z);
                pos.set(pos.x, pos.y, pos.z() + (float)random.m_188503_((int)zDif));
            } else if (direction.m_122434_().equals((Object)Direction.Axis.Y)) {
                pos.set(pos.x() + (float)random.m_188503_((int)xDif), pos.y, pos.z);
                pos.set(pos.x, direction.equals((Object)Direction.DOWN) ? box.f_104336_ : box.f_104339_ - 1.0f, pos.z);
                pos.set(pos.x, pos.y, pos.z() + (float)random.m_188503_((int)zDif));
            } else if (direction.m_122434_().equals((Object)Direction.Axis.Z)) {
                pos.set(pos.x() + (float)random.m_188503_((int)xDif), pos.y, pos.z);
                pos.set(pos.x, pos.y() + (float)random.m_188503_((int)yDif), pos.z);
                pos.set(pos.x, pos.y, direction.equals((Object)Direction.NORTH) ? box.f_104337_ : box.f_104340_ - 1.0f);
            }
        }
        return pos;
    }

    public static void renderExtraHitboxes(PoseStack stack, VertexConsumer consumer, WitherStormEntity storm, float partialTick) {
        for (WitherStormHead head : storm.getHeadManager().getHeads()) {
            stack.m_85836_();
            stack.m_85837_(-storm.m_20185_(), -storm.m_20186_(), -storm.m_20189_());
            if (head.getBoundingBox() != null) {
                AABB box = head.getBoundingBox();
                Color col = EXTRA_HEAD_COLOR;
                if (head instanceof MainHead) {
                    col = MAIN_HEAD_COLOR;
                }
                LevelRenderer.m_109646_((PoseStack)stack, (VertexConsumer)consumer, (AABB)box, (float)((float)col.getRed() / 255.0f), (float)((float)col.getGreen() / 255.0f), (float)((float)col.getBlue() / 255.0f), (float)1.0f);
                Vec3 viewVector = storm.getViewVector(head.getHeadXRot(partialTick), head.getHeadYRot(partialTick), (float)box.m_82309_());
                Vec3 eyePos = head.getHeadPos();
                Matrix4f matrix4f = stack.m_85850_().m_252922_();
                Matrix3f matrix3f = stack.m_85850_().m_252943_();
                consumer.m_252986_(matrix4f, (float)eyePos.f_82479_, (float)eyePos.f_82480_, (float)eyePos.f_82481_).m_6122_(0, 0, 255, 255).m_252939_(matrix3f, (float)viewVector.f_82479_, (float)viewVector.f_82480_, (float)viewVector.f_82481_).m_5752_();
                consumer.m_252986_(matrix4f, (float)eyePos.f_82479_ + (float)viewVector.f_82479_, (float)eyePos.f_82480_ + (float)viewVector.f_82480_, (float)eyePos.f_82481_ + (float)viewVector.f_82481_).m_6122_(0, 0, 255, 255).m_252939_(matrix3f, (float)viewVector.f_82479_, (float)viewVector.f_82480_, (float)viewVector.f_82481_).m_5752_();
            }
            if (storm.getPhase() > 4 && storm.partsEnabled) {
                for (Section part : storm.getSections()) {
                    if (!part.isActive()) continue;
                    LevelRenderer.m_109646_((PoseStack)stack, (VertexConsumer)consumer, (AABB)part.getBoundingBox(), (float)part.getColor()[0], (float)part.getColor()[1], (float)part.getColor()[2], (float)1.0f);
                }
            }
            stack.m_85849_();
        }
    }
}

