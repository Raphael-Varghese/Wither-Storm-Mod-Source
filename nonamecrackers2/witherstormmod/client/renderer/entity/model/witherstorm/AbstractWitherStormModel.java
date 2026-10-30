/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  javax.annotation.Nullable
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.util.RandomSource
 *  nonamecrackers2.crackerslib.common.compat.CompatHelper
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.RandomSource;
import nonamecrackers2.crackerslib.common.compat.CompatHelper;
import nonamecrackers2.witherstormmod.client.instancing.RenderBufferer;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.HeadModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.TentacleModel;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.mixin.IMixinModelPart;

public abstract class AbstractWitherStormModel<T extends WitherStormEntity> {
    public static final String MASS = "mass";
    public static final String LOWRESMASS = "lowResMass";
    public static final String TENTACLES = "tentacles";
    public static final String HEADSROOT = "heads";
    public static final String[] HEADS = new String[]{"head0", "head1", "head2"};
    protected ModelPart body;
    protected ModelPart lowResBody;
    protected TentacleModel[] tentacles = new TentacleModel[0];
    public final Int2ObjectMap<HeadModel<T>> heads = new Int2ObjectOpenHashMap();
    public final float headScale;

    protected AbstractWitherStormModel(ModelPart root, float headScale) {
        this.body = root.m_171324_(MASS);
        this.lowResBody = root.m_171324_(LOWRESMASS);
        this.configureTentacles(root.m_171324_(TENTACLES));
        this.configureHeads(root.m_171324_(HEADSROOT), headScale);
        this.headScale = headScale;
    }

    protected abstract void configureTentacles(ModelPart var1);

    protected abstract void configureHeads(ModelPart var1, float var2);

    protected static MeshDefinition createMesh() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.m_171576_();
        root.m_171599_(MASS, CubeListBuilder.m_171558_(), PartPose.f_171404_);
        root.m_171599_(LOWRESMASS, CubeListBuilder.m_171558_(), PartPose.f_171404_);
        root.m_171599_(TENTACLES, CubeListBuilder.m_171558_(), PartPose.f_171404_);
        root.m_171599_(HEADSROOT, CubeListBuilder.m_171558_(), PartPose.f_171404_);
        return mesh;
    }

    public void setupAnimations(T entity, float partialTicks, float tickCount, float yRot, float xRot) {
        for (Int2ObjectMap.Entry entry : this.heads.int2ObjectEntrySet()) {
            ((HeadModel)entry.getValue()).setupAnimations(entity, partialTicks, tickCount, yRot, xRot, entry.getIntKey());
        }
        for (int i = 0; i < this.tentacles.length; ++i) {
            this.tentacles[i].setupAnimations(((WitherStormEntity)entity).getTentacleAnimation(partialTicks), partialTicks);
        }
    }

    public void renderHeads(T entity, PoseStack stack, VertexConsumer consumer, int overlayTexture, int packedLight, float r, float g, float b, float a) {
        this.renderHeads(i -> true, entity, stack, consumer, overlayTexture, packedLight, r, g, b, a);
    }

    public void renderHeads(Predicate<Integer> canRender, T entity, PoseStack stack, VertexConsumer consumer, int overlayTexture, int packedLight, float r, float g, float b, float a) {
        for (Int2ObjectMap.Entry entry : this.heads.int2ObjectEntrySet()) {
            if (!canRender.test(entry.getIntKey()) || ((WitherStormEntity)entity).areOtherHeadsDisabled() && (!((WitherStormEntity)entity).areOtherHeadsDisabled() || entry.getIntKey() != 0)) continue;
            this.renderHead(entry.getIntKey(), entity, stack, consumer, overlayTexture, packedLight, r, g, b, a);
        }
    }

    public void renderHead(int head, T entity, PoseStack stack, VertexConsumer consumer, int overlayTexture, int packedLight, float r, float g, float b, float a) {
        int overlay;
        HeadModel headModel = (HeadModel)this.heads.get(head);
        stack.m_85836_();
        headModel.scale(stack);
        int hurtDir = ((WitherStormEntity)entity).getHeadManager().getHead(head).getHeadHurtDuration();
        int n = overlay = hurtDir > 0 ? 3 : overlayTexture;
        if (!((WitherStormEntity)entity).areOtherHeadsDisabled() || head == 0) {
            headModel.root().m_104306_(stack, consumer, packedLight, overlay, r, g, b, a);
        }
        stack.m_85849_();
    }

    public void render(T entity, PoseStack stack, MultiBufferSource source, RenderType type, @Nullable RenderType emissiveType, @Nullable RenderType massEmissiveType, @Nullable RenderType hurtOverlayType, int packedLight, int overlayTexture, float r, float g, float b, float alpha) {
        boolean flag;
        stack.m_85836_();
        VertexConsumer consumer = source.m_6299_(type);
        this.renderHeads(entity, stack, consumer, overlayTexture, packedLight, r, g, b, alpha);
        stack.m_85836_();
        this.transformForMirrored(stack, ((WitherStormEntity)entity).isMirrored());
        for (int i2 = 0; i2 < this.tentacles.length; ++i2) {
            stack.m_85836_();
            this.scaleTentacles(stack, this.tentacles[i2]);
            this.tentacles[i2].tentacle.m_104306_(stack, consumer, packedLight, overlayTexture, 1.0f, 1.0f, 1.0f, alpha);
            stack.m_85849_();
        }
        this.renderExtra(stack, consumer, packedLight, overlayTexture, r, g, b, alpha);
        stack.m_85849_();
        if (hurtOverlayType != null) {
            this.renderHeads(i -> entity.isHeadInjured((int)i), entity, stack, source.m_6299_(hurtOverlayType), overlayTexture, packedLight, r, g, b, alpha);
        }
        if (!(entity.m_20096_() && ((WitherStormEntity)entity).isDeadOrPlayingDead() || ((WitherStormEntity)entity).shouldFlicker() || emissiveType == null)) {
            VertexConsumer emissive = source.m_6299_(emissiveType);
            this.renderHeads(i -> !entity.isHeadInjured((int)i), entity, stack, emissive, overlayTexture, packedLight, r, g, b, alpha);
        }
        stack.m_85836_();
        this.transformForMirrored(stack, ((WitherStormEntity)entity).isMirrored());
        this.scaleMass(stack);
        RenderBufferer.pushCullFaces();
        if (((WitherStormEntity)entity).isMirrored()) {
            RenderBufferer.pushFlipFaces();
        }
        if ((flag = RenderBufferer.shouldUse()) && entity.m_21224_() && CompatHelper.areShadersRunning()) {
            flag = false;
        }
        RenderBufferer.buildAndOrRender(this + ", " + type + ", " + this.lowResModelsEnabled(entity), type, () -> false, (arg_0, arg_1, arg_2, arg_3, arg_4, arg_5, arg_6, arg_7) -> ((ModelPart)this.getMassModel(entity)).m_104306_(arg_0, arg_1, arg_2, arg_3, arg_4, arg_5, arg_6, arg_7), stack, packedLight, overlayTexture, 1.0f, 1.0f, 1.0f, alpha, flag);
        if (flag && emissiveType != null) {
            RenderBufferer.pushNoFog();
            RenderBufferer.buildAndOrRender(this + ", " + massEmissiveType + ", " + this.lowResModelsEnabled(entity) + ", emissive", massEmissiveType, () -> false, (arg_0, arg_1, arg_2, arg_3, arg_4, arg_5, arg_6, arg_7) -> ((ModelPart)this.getMassModel(entity)).m_104306_(arg_0, arg_1, arg_2, arg_3, arg_4, arg_5, arg_6, arg_7), stack, packedLight, overlayTexture, 1.0f, 1.0f, 1.0f, alpha, true);
        }
        RenderBufferer.popFlipFaces();
        RenderBufferer.popCullFaces();
        if (!entity.m_21224_()) {
            this.renderMassDecal(entity, stack, source, packedLight, overlayTexture, r, g, b, alpha);
        }
        stack.m_85849_();
        stack.m_85849_();
    }

    protected void transformForMirrored(PoseStack stack, boolean mirrored) {
        stack.m_85841_(mirrored ? -1.0f : 1.0f, 1.0f, 1.0f);
    }

    protected void renderExtra(PoseStack stack, VertexConsumer consumer, int packedLight, int overlayTexture, float r, float g, float b, float a) {
    }

    public ModelPart getMassModel(T entity) {
        if (this.lowResMassPresent()) {
            return this.lowResModelsEnabled(entity) ? this.lowResBody : this.body;
        }
        return this.body;
    }

    public boolean lowResModelsEnabled(T entity) {
        return this.lowResMassPresent() && ((Boolean)WitherStormModConfig.CLIENT.lowResModels.get() != false || (Boolean)WitherStormModConfig.CLIENT.witherStormLOD.get() != false && ((WitherStormEntity)entity).isOnDistantRenderer());
    }

    public void scaleMass(PoseStack stack) {
        stack.m_85841_(10.0f, 10.0f, 10.0f);
    }

    public void scaleTentacles(PoseStack stack, TentacleModel model) {
        stack.m_85841_(model.scale, model.scale, model.scale);
    }

    public boolean massPresent() {
        return this.body != null;
    }

    public boolean lowResMassPresent() {
        return !this.lowResBody.m_171326_();
    }

    public ModelPart getRandomPart(T entity, RandomSource random) {
        ModelPart potential;
        ModelPart model = this.getMassModel(entity);
        ArrayList<ModelPart> children = new ArrayList<ModelPart>(((IMixinModelPart)model).getChildren().values());
        if (!children.isEmpty() && !(potential = (ModelPart)children.get(random.m_188503_(children.size()))).m_171326_()) {
            model = potential;
        }
        return model;
    }

    public void renderMassDecal(T entity, PoseStack stack, MultiBufferSource buffer, int packedLight, int overlayCoords, float r, float g, float b, float a) {
    }
}

