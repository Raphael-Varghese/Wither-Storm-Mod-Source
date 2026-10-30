/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.AnimationUtils
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 *  net.minecraft.util.Mth
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;

public class WitheredSymbiontModel<T extends WitheredSymbiontEntity>
extends HumanoidModel<T> {
    private final ModelPart tentacle;
    private final ModelPart segment1;
    private final ModelPart segment2;
    private final ModelPart segment3;
    private final ModelPart segment4;
    private final ModelPart tentacle2;
    private final ModelPart segment5;
    private final ModelPart segment6;
    private final ModelPart segment7;
    private final ModelPart segment8;
    private final ModelPart tentacle3;
    private final ModelPart segment9;
    private final ModelPart segment10;
    private final ModelPart segment11;
    private final ModelPart segment12;
    private float crouchAnim;

    public WitheredSymbiontModel(ModelPart root) {
        super(root);
        ModelPart body = root.m_171324_("body");
        this.tentacle = body.m_171324_("tentacle");
        this.segment1 = this.tentacle.m_171324_("segment1");
        this.segment2 = this.segment1.m_171324_("segment2");
        this.segment3 = this.segment2.m_171324_("segment3");
        this.segment4 = this.segment3.m_171324_("segment4");
        this.tentacle2 = body.m_171324_("tentacle2");
        this.segment5 = this.tentacle2.m_171324_("segment1");
        this.segment6 = this.segment5.m_171324_("segment2");
        this.segment7 = this.segment6.m_171324_("segment3");
        this.segment8 = this.segment7.m_171324_("segment4");
        this.tentacle3 = body.m_171324_("tentacle3");
        this.segment9 = this.tentacle3.m_171324_("segment1");
        this.segment10 = this.segment9.m_171324_("segment2");
        this.segment11 = this.segment10.m_171324_("segment3");
        this.segment12 = this.segment11.m_171324_("segment4");
        this.f_102809_.f_104207_ = false;
    }

    public static LayerDefinition createLayerDefinition() {
        MeshDefinition mesh = HumanoidModel.m_170681_((CubeDeformation)CubeDeformation.f_171458_, (float)0.0f);
        PartDefinition root = mesh.m_171576_();
        root.m_171599_("left_leg", CubeListBuilder.m_171558_().m_171514_(16, 48).m_171481_(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f), PartPose.m_171419_((float)1.9f, (float)12.0f, (float)0.0f));
        PartDefinition body = root.m_171597_("body");
        body.m_171599_("mass1", CubeListBuilder.m_171558_().m_171514_(36, 32).m_171506_(-4.5f, -4.5f, -1.5f, 6.0f, 9.0f, 3.0f, false), PartPose.m_171423_((float)1.2478f, (float)5.7655f, (float)2.1684f, (float)-0.0783f, (float)-0.1909f, (float)-0.2221f));
        body.m_171599_("mass2", CubeListBuilder.m_171558_().m_171514_(32, 4).m_171506_(-3.5f, -2.5f, -2.5f, 7.0f, 9.0f, 3.0f, false), PartPose.m_171423_((float)-0.6213f, (float)2.8595f, (float)-1.0036f, (float)0.2661f, (float)-0.1641f, (float)0.244f));
        body.m_171599_("mass3", CubeListBuilder.m_171558_().m_171514_(0, 32).m_171506_(-2.0f, -2.0f, -2.0f, 4.0f, 4.0f, 4.0f, false), PartPose.m_171423_((float)-2.0355f, (float)9.8385f, (float)1.8323f, (float)-2.5517f, (float)-0.5708f, (float)1.7651f));
        body.m_171599_("mass4", CubeListBuilder.m_171558_().m_171514_(16, 32).m_171506_(-2.5f, -0.5f, -4.5f, 5.0f, 5.0f, 5.0f, false), PartPose.m_171423_((float)1.5f, (float)6.5f, (float)-1.5f, (float)0.6346f, (float)-0.678f, (float)-0.4326f));
        PartDefinition rightLeg = root.m_171597_("right_leg");
        rightLeg.m_171599_("mass5", CubeListBuilder.m_171558_().m_171514_(16, 32).m_171506_(-2.5f, -2.5f, 1.5f, 5.0f, 5.0f, 5.0f, false), PartPose.m_171423_((float)-1.5f, (float)7.5f, (float)-1.5f, (float)0.0406f, (float)0.4854f, (float)-1.2322f));
        rightLeg.m_171599_("mass6", CubeListBuilder.m_171558_().m_171514_(16, 32).m_171506_(-2.5f, -2.5f, -2.5f, 5.0f, 5.0f, 5.0f, false), PartPose.m_171423_((float)-1.5f, (float)7.5f, (float)-1.5f, (float)0.6929f, (float)0.4557f, (float)0.3503f));
        rightLeg.m_171599_("mass7", CubeListBuilder.m_171558_().m_171514_(16, 32).m_171506_(-1.5f, -3.5f, -2.5f, 5.0f, 5.0f, 5.0f, false), PartPose.m_171423_((float)-1.5f, (float)3.5f, (float)-1.5f, (float)0.432f, (float)-0.8648f, (float)-0.6805f));
        PartDefinition rightArm = root.m_171597_("right_arm");
        rightArm.m_171599_("mass8", CubeListBuilder.m_171558_().m_171514_(0, 32).m_171506_(-2.5f, -1.5f, -1.5f, 4.0f, 4.0f, 4.0f, false), PartPose.m_171423_((float)-1.2211f, (float)4.1554f, (float)1.9027f, (float)1.6619f, (float)-0.8156f, (float)-1.5077f));
        rightArm.m_171599_("mass9", CubeListBuilder.m_171558_().m_171514_(16, 32).m_171506_(-2.0f, 2.0f, 21.0f, 5.0f, 5.0f, 5.0f, false), PartPose.m_171423_((float)5.0f, (float)22.0f, (float)0.0f, (float)1.7759f, (float)0.1628f, (float)-0.3449f));
        rightArm.m_171599_("mass10", CubeListBuilder.m_171558_().m_171514_(16, 32).m_171506_(-2.0f, -15.0f, 12.0f, 5.0f, 5.0f, 5.0f, false), PartPose.m_171423_((float)5.0f, (float)22.0f, (float)0.0f, (float)0.9318f, (float)-0.52f, (float)-0.4963f));
        PartDefinition head = root.m_171597_("head");
        head.m_171599_("mass11", CubeListBuilder.m_171558_().m_171514_(0, 32).m_171506_(-2.0f, -2.0f, -2.0f, 4.0f, 4.0f, 4.0f, false), PartPose.m_171423_((float)-5.2146f, (float)-1.96f, (float)2.8784f, (float)3.1231f, (float)-0.7686f, (float)-1.9387f));
        head.m_171599_("mass12", CubeListBuilder.m_171558_().m_171514_(16, 32).m_171506_(-1.5f, -2.5f, -2.5f, 5.0f, 5.0f, 5.0f, false), PartPose.m_171423_((float)-4.7943f, (float)-4.4645f, (float)-3.6255f, (float)-2.4784f, (float)-0.4058f, (float)-2.4209f));
        head.m_171599_("mass13", CubeListBuilder.m_171558_().m_171514_(16, 32).m_171506_(-1.5f, -2.5f, -2.5f, 5.0f, 5.0f, 5.0f, false), PartPose.m_171423_((float)-2.5f, (float)-7.5f, (float)2.5f, (float)-2.4343f, (float)-0.4891f, (float)2.7597f));
        PartDefinition tentacle = body.m_171599_("tentacle", CubeListBuilder.m_171558_(), PartPose.m_171419_((float)-3.5f, (float)9.5f, (float)0.0f));
        PartDefinition segment1 = tentacle.m_171599_("segment1", CubeListBuilder.m_171558_().m_171514_(54, 30).m_171506_(-1.5f, -1.5f, 1.0f, 3.0f, 3.0f, 8.0f, false), PartPose.f_171404_);
        PartDefinition segment2 = segment1.m_171599_("segment2", CubeListBuilder.m_171558_().m_171514_(72, 33).m_171506_(-1.3f, -1.3f, 0.0f, 2.6f, 2.6f, 9.0f, false), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)9.0f));
        PartDefinition segment3 = segment2.m_171599_("segment3", CubeListBuilder.m_171558_().m_171514_(68, 19).m_171506_(-0.9f, -0.9f, 0.0f, 1.8f, 1.8f, 11.0f, false), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)9.0f));
        segment3.m_171599_("segment4", CubeListBuilder.m_171558_().m_171514_(58, 0).m_171506_(-0.5f, -0.5f, 0.0f, 1.0f, 1.0f, 16.0f, false), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)11.0f));
        PartDefinition tentacle2 = body.m_171599_("tentacle2", CubeListBuilder.m_171558_(), PartPose.m_171419_((float)2.5f, (float)10.5f, (float)0.0f));
        PartDefinition segment5 = tentacle2.m_171599_("segment1", CubeListBuilder.m_171558_().m_171514_(54, 30).m_171506_(-1.5f, -1.5f, 1.0f, 3.0f, 3.0f, 8.0f, false), PartPose.f_171404_);
        PartDefinition segment6 = segment5.m_171599_("segment2", CubeListBuilder.m_171558_().m_171514_(72, 33).m_171506_(-1.3f, -1.3f, 0.0f, 2.6f, 2.6f, 9.0f, false), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)9.0f));
        PartDefinition segment7 = segment6.m_171599_("segment3", CubeListBuilder.m_171558_().m_171514_(68, 19).m_171506_(-0.9f, -0.9f, 0.0f, 1.8f, 1.8f, 11.0f, false), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)9.0f));
        segment7.m_171599_("segment4", CubeListBuilder.m_171558_().m_171514_(58, 0).m_171506_(-0.5f, -0.5f, 0.0f, 1.0f, 1.0f, 16.0f, false), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)11.0f));
        PartDefinition tentacle3 = body.m_171599_("tentacle3", CubeListBuilder.m_171558_(), PartPose.m_171419_((float)-0.5f, (float)0.5f, (float)0.0f));
        PartDefinition segment9 = tentacle3.m_171599_("segment1", CubeListBuilder.m_171558_().m_171514_(54, 30).m_171506_(-1.5f, -1.5f, 1.0f, 3.0f, 3.0f, 8.0f, false), PartPose.f_171404_);
        PartDefinition segment10 = segment9.m_171599_("segment2", CubeListBuilder.m_171558_().m_171514_(72, 33).m_171506_(-1.3f, -1.3f, 0.0f, 2.6f, 2.6f, 9.0f, false), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)9.0f));
        PartDefinition segment11 = segment10.m_171599_("segment3", CubeListBuilder.m_171558_().m_171514_(68, 19).m_171506_(-0.9f, -0.9f, 0.0f, 1.8f, 1.8f, 11.0f, false), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)9.0f));
        segment11.m_171599_("segment4", CubeListBuilder.m_171558_().m_171514_(58, 0).m_171506_(-0.5f, -0.5f, 0.0f, 1.0f, 1.0f, 16.0f, false), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)11.0f));
        return LayerDefinition.m_171565_((MeshDefinition)mesh, (int)96, (int)96);
    }

    public void prepareMobModel(T entity, float p_212843_2_, float p_212843_3_, float partialTicks) {
        super.m_6839_(entity, p_212843_2_, p_212843_3_, partialTicks);
        float tickCount = ((WitheredSymbiontEntity)entity).isVulnerable() ? 0.0f : (float)((WitheredSymbiontEntity)entity).f_19797_ + partialTicks;
        float additionalAnim = ((WitheredSymbiontEntity)entity).f_267362_.m_267590_(partialTicks);
        this.animateTentacle(tickCount, additionalAnim, this.tentacle, this.segment1, this.segment2, this.segment3, this.segment4, 0.0f, 0.4f, 1.5f, 0.0f, 0.0f, -30.0f, 10.0f);
        this.animateTentacle(tickCount, additionalAnim, this.tentacle2, this.segment5, this.segment6, this.segment7, this.segment8, 20.0f, 0.4f, 1.5f, 0.0f, 0.0f, 40.0f, 15.0f);
        this.animateTentacle(tickCount, additionalAnim, this.tentacle3, this.segment9, this.segment10, this.segment11, this.segment12, 40.0f, 0.4f, 1.5f, 0.0f, 0.0f, -10.0f, 50.0f);
        this.crouchAnim = ((WitheredSymbiontEntity)entity).getVulnerableAnim(partialTicks);
    }

    public void setupAnim(T entity, float p_225597_2_, float p_225597_3_, float tickCount, float p_225597_5_, float p_225597_6_) {
        float f2;
        super.m_6973_(entity, p_225597_2_, p_225597_3_, tickCount, p_225597_5_, p_225597_6_);
        float tick = ((WitheredSymbiontEntity)entity).isVulnerable() ? 0.0f : tickCount;
        float f = Mth.m_14031_((float)(this.f_102608_ * (float)Math.PI));
        float f1 = Mth.m_14031_((float)((1.0f - (1.0f - this.f_102608_) * (1.0f - this.f_102608_)) * (float)Math.PI));
        this.f_102811_.f_104205_ = 0.0f;
        this.f_102812_.f_104205_ = 0.0f;
        this.f_102811_.f_104204_ = -(0.1f - f * 0.6f);
        this.f_102812_.f_104204_ = 0.1f - f * 0.6f;
        this.f_102811_.f_104203_ = f2 = (float)(-Math.PI) / (entity.m_5912_() ? 1.5f : 2.25f);
        this.f_102812_.f_104203_ = f2;
        this.f_102811_.f_104203_ += f * 1.2f - f1 * 0.4f;
        this.f_102812_.f_104203_ += f * 1.2f - f1 * 0.4f;
        AnimationUtils.m_102082_((ModelPart)this.f_102811_, (ModelPart)this.f_102812_, (float)tick);
        this.f_102810_.f_104203_ += this.crouchAnim;
        this.f_102811_.f_104203_ += this.crouchAnim;
        this.f_102812_.f_104203_ += this.crouchAnim;
        this.f_102813_.f_104202_ += this.crouchAnim * 10.5f;
        this.f_102814_.f_104202_ += this.crouchAnim * 10.5f;
        this.f_102813_.f_104201_ += this.crouchAnim * 0.4f;
        this.f_102814_.f_104201_ += this.crouchAnim * 0.4f;
        this.f_102808_.f_104201_ += this.crouchAnim * 2.0f;
        this.f_102810_.f_104201_ += this.crouchAnim * 3.0f;
        this.f_102812_.f_104201_ += this.crouchAnim * 3.0f;
        this.f_102811_.f_104201_ += this.crouchAnim * 3.0f;
        this.f_102812_.f_104202_ += this.crouchAnim * 2.0f;
        this.f_102811_.f_104202_ += this.crouchAnim * 2.0f;
        if (((WitheredSymbiontEntity)entity).isCastingSpell() || ((WitheredSymbiontEntity)entity).isSummoningMobs() || entity.m_21224_() || ((WitheredSymbiontEntity)entity).hasAttackDelay()) {
            this.f_102812_.f_104204_ = -0.4f - 0.8f * Mth.m_14031_((float)(tickCount * 0.5f));
            this.f_102811_.f_104204_ = -0.4f - 0.8f * -Mth.m_14031_((float)(tickCount * 0.5f));
            this.f_102812_.f_104203_ = -2.5f;
            this.f_102811_.f_104203_ = -2.5f;
        }
    }

    protected void animateTentacle(float animation, float additionalAnim, ModelPart base, ModelPart segment1, ModelPart segment2, ModelPart segment3, ModelPart segment4, float offset, float speed, float reach, float xRotOffset, float yRotOffset, float xAngleOffset, float yAngleOffset) {
        float f = Mth.m_14089_((float)((animation + offset * 10.0f) * speed * 0.1f)) * reach + Mth.m_14089_((float)additionalAnim);
        float s = Mth.m_14031_((float)((animation + offset * 10.0f) * speed / 2.0f * 0.1f)) * reach + Mth.m_14031_((float)additionalAnim);
        float yRotOffRadians = (float)Math.toRadians(yRotOffset);
        float xRotOffRadians = (float)Math.toRadians(xRotOffset);
        float xAngleOffRadians = (float)Math.toRadians(yAngleOffset);
        float yAngleOffRadians = (float)Math.toRadians(xAngleOffset);
        base.f_104204_ = s * f * 0.05f + yRotOffRadians;
        base.f_104203_ = f * s * 0.05f + xRotOffRadians;
        segment1.f_104203_ = f * -0.1f;
        segment2.f_104203_ = f * 0.1f + xAngleOffRadians;
        segment3.f_104203_ = f * 0.075f + xAngleOffRadians;
        segment4.f_104203_ = f * 0.05f + xAngleOffRadians;
        segment1.f_104204_ = s * 0.1f + yAngleOffRadians;
        segment2.f_104204_ = s * 0.075f + yAngleOffRadians;
        segment3.f_104204_ = s * 0.05f + yAngleOffRadians;
        segment4.f_104204_ = s * 0.01f + yAngleOffRadians;
    }

    protected void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.f_104203_ = x;
        modelRenderer.f_104204_ = y;
        modelRenderer.f_104205_ = z;
    }
}

