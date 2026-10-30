/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 *  net.minecraft.util.Mth
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.SingleHeadWitherStormModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.TentacleModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormCommandBlockModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.PregnantHunchbackBodyModel;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class WitherStormPregnantHunchbackModel<T extends WitherStormEntity>
extends SingleHeadWitherStormModel<T> {
    private final ModelPart base;
    private final ModelPart rightHead;
    private final ModelPart leftHead;
    private final ModelPart ribcage;

    public WitherStormPregnantHunchbackModel(ModelPart root) {
        super(root, 0.7f);
        this.base = root.m_171324_("witherBase");
        this.ribcage = this.base.m_171324_("ribcage");
        this.rightHead = this.base.m_171324_("right_head");
        this.leftHead = this.base.m_171324_("left_head");
    }

    public static LayerDefinition createLayerDefinition(CubeDeformation def) {
        MeshDefinition mesh = WitherStormCommandBlockModel.createBaseMesh(SingleHeadWitherStormModel.createMesh(PartPose.f_171404_), def, false, false, false);
        PartDefinition root = mesh.m_171576_();
        PregnantHunchbackBodyModel.createBodyModel(root, 1.0f);
        PartDefinition tentacles = root.m_171597_("tentacles");
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle0", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{18, 24, 24, 24, 24, 32}, PartPose.m_171419_((float)0.0f, (float)0.0f, (float)20.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle1", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{18, 24, 24, 24, 24, 32}, PartPose.m_171419_((float)0.0f, (float)0.0f, (float)20.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle2", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{18, 24, 24, 24, 24, 32}, PartPose.m_171419_((float)0.0f, (float)20.0f, (float)20.0f));
        return LayerDefinition.m_171565_((MeshDefinition)mesh, (int)160, (int)160);
    }

    @Override
    protected void configureTentacles(ModelPart root) {
        this.tentacles = new TentacleModel[3];
        this.tentacles[0] = new TentacleModel(root.m_171324_("tentacle0"), 0.5f);
        this.tentacles[0].animationSpeed = 0.5f;
        this.tentacles[0].yRotationalOffset = 4.14f;
        this.tentacles[0].xAngularOffset = -0.17444445f;
        this.tentacles[0].yAngularOffset = 0.3488889f;
        this.tentacles[0].reach = 2.0f;
        this.tentacles[1] = new TentacleModel(root.m_171324_("tentacle1"), 0.5f);
        this.tentacles[1].animationSpeed = 0.5f;
        this.tentacles[1].yRotationalOffset = 11.775001f;
        this.tentacles[1].xRotationalOffset = 9.859601f;
        this.tentacles[1].xAngularOffset = 0.17444445f;
        this.tentacles[1].yAngularOffset = 0.3488889f;
        this.tentacles[1].animationOffset = 10.0f;
        this.tentacles[1].reach = 2.15f;
        this.tentacles[2] = new TentacleModel(root.m_171324_("tentacle2"), 0.5f);
        this.tentacles[2].animationSpeed = 0.5f;
        this.tentacles[2].yRotationalOffset = 3.14f;
        this.tentacles[2].xRotationalOffset = 0.785f;
        this.tentacles[2].xAngularOffset = 0.5233334f;
        this.tentacles[2].yAngularOffset = -0.17444445f;
        this.tentacles[2].animationOffset = 32.0f;
        this.tentacles[2].reach = 1.85f;
    }

    @Override
    public void setupAnimations(T entity, float partialTicks, float tickCount, float yRot, float xRot) {
        super.setupAnimations(entity, partialTicks, tickCount, yRot, xRot);
        float f = Mth.m_14089_((float)(tickCount * 0.1f));
        this.ribcage.f_104203_ = (0.065f + 0.05f * f) * (float)Math.PI;
        WitherStormCommandBlockModel.setupHeadRotation(entity, this.leftHead, 1, partialTicks);
        WitherStormCommandBlockModel.setupHeadRotation(entity, this.rightHead, 2, partialTicks);
    }

    @Override
    protected void renderExtra(PoseStack stack, VertexConsumer consumer, int packedLight, int overlayTexture, float r, float g, float b, float a) {
        this.base.m_104306_(stack, consumer, packedLight, overlayTexture, r, g, b, a);
    }

    @Override
    public void scaleMass(PoseStack stack) {
        stack.m_252781_(Axis.f_252529_.m_252977_(20.0f));
    }

    @Override
    public void scaleTentacles(PoseStack stack, TentacleModel model) {
        super.scaleTentacles(stack, model);
        stack.m_252781_(Axis.f_252529_.m_252977_(20.0f));
    }
}

