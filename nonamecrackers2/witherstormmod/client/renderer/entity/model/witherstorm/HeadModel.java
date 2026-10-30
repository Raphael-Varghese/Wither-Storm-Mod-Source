/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.PoseStack$Pose
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.PartDefinition
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import java.util.Random;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class HeadModel<T extends LivingEntity> {
    protected final ModelPart head;
    protected final ModelPart upper;
    protected final ModelPart lower;
    private float jawHinge;
    public float tractorBeamXOffset;
    public float tractorBeamYOffset;
    public float tractorBeamZOffset;
    public float pivotOffsetX;
    public float pivotOffsetY;
    public float pivotOffsetZ;
    public float scale;
    public float tractorBeamStartSize;
    public float tractorBeamDistance;
    public float tractorBeamEndSize;
    public float animationOffset;

    public HeadModel(ModelPart root, float scale) {
        this.head = root;
        this.upper = root.m_171324_("upperJaw");
        this.lower = root.m_171324_("lowerJaw");
        this.scale = scale;
    }

    public static void populateDefinition(PartDefinition root) {
        PartDefinition upper = root.m_171599_("upperJaw", CubeListBuilder.m_171558_().m_171514_(0, 65).m_171506_(-4.0f, -6.5f, 12.0f, 8.0f, 6.0f, 2.0f, false).m_171514_(0, 47).m_171506_(-2.0f, -8.5f, 10.0f, 4.0f, 2.0f, 2.0f, false).m_171514_(0, 35).m_171506_(-4.0f, -8.5f, 0.0f, 8.0f, 2.0f, 10.0f, false).m_171514_(0, 47).m_171506_(-6.0f, -6.5f, 0.0f, 12.0f, 6.0f, 12.0f, false).m_171514_(4, 13).m_171496_(-1.0f, -4.5f, 13.1f, 2.0f, 2.0f, 1.0f, CubeDeformation.f_171458_, 0.2f, 0.2f), PartPose.m_171419_((float)0.0f, (float)2.5f, (float)0.0f));
        upper.m_171599_("upperTeeth", CubeListBuilder.m_171558_().m_171514_(0, 54).m_171506_(-1.0f, -1.0f, 13.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(-3.0f, -1.0f, 12.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(-5.0f, -1.0f, 11.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(-6.0f, -1.0f, 9.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(-6.0f, -1.0f, 7.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(-6.0f, -1.0f, 5.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(-6.0f, -1.0f, 3.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(-6.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(1.0f, -1.0f, 13.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(3.0f, -1.0f, 12.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(4.0f, -1.0f, 10.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(5.0f, -1.0f, 8.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(5.0f, -1.0f, 6.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(5.0f, -1.0f, 4.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(5.0f, -1.0f, 2.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(5.0f, -1.0f, 0.0f, 1.0f, 1.0f, 1.0f, false), PartPose.m_171419_((float)0.0f, (float)0.5f, (float)0.0f));
        PartDefinition lower = root.m_171599_("lowerJaw", CubeListBuilder.m_171558_().m_171514_(0, 73).m_171506_(-4.0f, 0.5f, 12.0f, 8.0f, 2.0f, 2.0f, false).m_171514_(48, 0).m_171506_(-6.0f, 0.5f, 0.0f, 12.0f, 2.0f, 12.0f, false), PartPose.m_171419_((float)0.0f, (float)2.5f, (float)0.0f));
        lower.m_171599_("lowerTeeth", CubeListBuilder.m_171558_().m_171514_(0, 54).m_171506_(0.0f, -3.0f, 5.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(2.0f, -3.0f, 4.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(4.0f, -3.0f, 3.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(5.0f, -3.0f, 1.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(5.0f, -3.0f, -1.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(5.0f, -3.0f, -3.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(5.0f, -3.0f, -5.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(5.0f, -3.0f, -7.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(-2.0f, -3.0f, 5.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(-4.0f, -3.0f, 4.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(-5.0f, -3.0f, 2.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(-6.0f, -3.0f, 0.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(-6.0f, -3.0f, -2.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(-6.0f, -3.0f, -4.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(-6.0f, -3.0f, -6.0f, 1.0f, 1.0f, 1.0f, false).m_171514_(0, 54).m_171506_(-6.0f, -3.0f, -8.0f, 1.0f, 1.0f, 1.0f, false), PartPose.m_171419_((float)0.0f, (float)2.5f, (float)8.0f));
    }

    public ModelPart root() {
        return this.head;
    }

    public void scale(PoseStack stack) {
        stack.m_85841_(this.scale, this.scale, this.scale);
    }

    public void setupAnimations(T entity, float partialTicks, float tickCount, float yRot, float xRot, int head) {
        if (head > 0) {
            this.head.f_104204_ = (Mth.m_14179_((float)partialTicks, (float)((WitherStormBase)entity).getHeadYRotO(head), (float)((WitherStormBase)entity).getHeadYRot(head)) - Mth.m_14179_((float)partialTicks, (float)((LivingEntity)entity).f_20884_, (float)((LivingEntity)entity).f_20883_)) * ((float)Math.PI / 180) + 3.1416f;
            this.head.f_104203_ = -(Mth.m_14179_((float)partialTicks, (float)((WitherStormBase)entity).getHeadXRotO(head), (float)((WitherStormBase)entity).getHeadXRot(head)) * ((float)Math.PI / 180));
        } else {
            this.head.f_104204_ = 3.1416f + yRot * ((float)Math.PI / 180);
            this.head.f_104203_ = -xRot * ((float)Math.PI / 180);
        }
        float hinge = ((WitherStormBase)entity).getMouthAnimation(head, partialTicks);
        this.jawHinge = hinge * 0.3f;
        float ticks = (float)((LivingEntity)entity).f_19797_ + partialTicks;
        if (((WitherStormBase)entity).isDeadOrPlayingDead()) {
            ticks = 0.0f;
        }
        float f = Mth.m_14089_((float)((ticks + this.animationOffset) * 0.1f));
        float d = Mth.m_14089_((float)this.jawHinge);
        this.lower.f_104203_ = d * 10.0f - 10.0f + (0.065f + 0.02f * f) * (float)Math.PI - 0.5f;
        Random random = new Random(entity.m_19879_());
        boolean mirror = false;
        for (int i = 0; i < head; ++i) {
            mirror = random.nextBoolean();
            if (i == head) break;
        }
        float s = Mth.m_14089_((float)(((WitherStormBase)entity).getBrokenJawAnimation(head, partialTicks) * 0.3f));
        this.lower.f_104205_ = (s * 10.0f - 10.0f) * (mirror ? -1.0f : 1.0f);
        this.head.f_104205_ = ((WitherStormBase)entity).getHeadShakeAnim(head, partialTicks);
    }

    public boolean shouldRenderTractorBeam(T entity, int head) {
        boolean flag = false;
        flag = ((WitherStormBase)entity).areOtherHeadsDisabled() ? head == 0 : true;
        return flag && !((WitherStormBase)entity).isHeadInjured(head);
    }

    public void renderTractorBeam(T entity, PoseStack stack, MultiBufferSource buffer, int packedLightIn, float r, float g, float b, float a, float partialTicks, double tractorBeamCutoff, float endFadeAlpha) {
        stack.m_85836_();
        stack.m_252781_(Axis.f_252436_.m_252977_(-Mth.m_14189_((float)partialTicks, (float)((LivingEntity)entity).f_20884_, (float)((LivingEntity)entity).f_20883_)));
        Pose pose = entity.m_20089_();
        if (pose != Pose.SLEEPING) {
            float xBodyRot = Mth.m_14189_((float)partialTicks, (float)((WitherStormBase)entity).getXBodyRotO(), (float)((WitherStormBase)entity).getXBodyRot());
            stack.m_252781_(Axis.f_252529_.m_252977_(-xBodyRot));
        }
        stack.m_252880_((this.head.f_104200_ + this.tractorBeamXOffset) / 8.0f * this.scale, -((this.head.f_104201_ - this.tractorBeamYOffset) / 8.0f) * this.scale, -((this.head.f_104202_ - this.tractorBeamZOffset) / 8.0f) * this.scale);
        stack.m_85841_(this.scale, this.scale, this.scale);
        stack.m_252781_(Axis.f_252403_.m_252961_(-this.head.f_104205_));
        stack.m_252781_(Axis.f_252436_.m_252961_(-this.head.f_104204_ - (float)Math.toRadians(90.0)));
        stack.m_252781_(Axis.f_252403_.m_252961_(-this.head.f_104203_));
        stack.m_252880_(this.pivotOffsetX / 8.0f * this.scale, this.pivotOffsetY / 8.0f * this.scale, this.pivotOffsetZ / 8.0f * this.scale);
        PoseStack.Pose entry = stack.m_85850_();
        Matrix4f matrix4f = entry.m_252922_();
        Matrix3f matrix3f = entry.m_252943_();
        VertexConsumer builder = buffer.m_6299_(RenderType.m_110502_());
        float distance = this.tractorBeamDistance;
        if (tractorBeamCutoff != -1.0) {
            distance = (float)tractorBeamCutoff;
        }
        float distanceScale = this.tractorBeamEndSize / this.tractorBeamDistance;
        float endSize = distanceScale * distance;
        builder.m_252986_(matrix4f, 0.0f, 0.0f + this.tractorBeamStartSize, 0.0f + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f - this.tractorBeamStartSize, 0.0f + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -distance, -endSize, endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -distance, endSize, endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f + this.tractorBeamStartSize, 0.0f - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f - this.tractorBeamStartSize, 0.0f - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f - this.tractorBeamStartSize, 0.0f + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f + this.tractorBeamStartSize, 0.0f + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -distance, endSize, -endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -distance, -endSize, -endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f - this.tractorBeamStartSize, 0.0f - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f + this.tractorBeamStartSize, 0.0f - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -distance, endSize, -endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f + this.tractorBeamStartSize, 0.0f - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f + this.tractorBeamStartSize, 0.0f + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -distance, endSize, endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -distance, -endSize, endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f - this.tractorBeamStartSize, 0.0f + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f - this.tractorBeamStartSize, 0.0f - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -distance, -endSize, -endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f + this.tractorBeamStartSize, 0.0f - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f - this.tractorBeamStartSize, 0.0f - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -distance, -endSize, -endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -distance, endSize, -endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f + this.tractorBeamStartSize, 0.0f + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f - this.tractorBeamStartSize, 0.0f + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f - this.tractorBeamStartSize, 0.0f - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f + this.tractorBeamStartSize, 0.0f - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -distance, endSize, endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -distance, -endSize, endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f - this.tractorBeamStartSize, 0.0f + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f + this.tractorBeamStartSize, 0.0f + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -distance, endSize, endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f + this.tractorBeamStartSize, 0.0f + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f + this.tractorBeamStartSize, 0.0f - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -distance, endSize, -endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -distance, -endSize, -endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f - this.tractorBeamStartSize, 0.0f - this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, 0.0f, 0.0f - this.tractorBeamStartSize, 0.0f + this.tractorBeamStartSize).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -distance, -endSize, endSize).m_85950_(r, g, b, endFadeAlpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLightIn).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        stack.m_85849_();
    }
}

