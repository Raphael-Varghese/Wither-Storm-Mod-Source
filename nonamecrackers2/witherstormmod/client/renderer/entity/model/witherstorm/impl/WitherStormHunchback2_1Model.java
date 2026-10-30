/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 *  net.minecraft.util.Mth
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.SingleHeadWitherStormModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormCommandBlockModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.WSHunchback2_1;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class WitherStormHunchback2_1Model<T extends WitherStormEntity>
extends SingleHeadWitherStormModel<T> {
    private final ModelPart base;
    private final ModelPart rightHead;
    private final ModelPart leftHead;
    private final ModelPart ribcage;

    public WitherStormHunchback2_1Model(ModelPart root) {
        super(root, 0.7f);
        this.base = root.m_171324_("witherBase");
        this.ribcage = this.base.m_171324_("ribcage");
        this.rightHead = this.base.m_171324_("right_head");
        this.leftHead = this.base.m_171324_("left_head");
    }

    public static LayerDefinition createLayerDefinition(CubeDeformation def) {
        MeshDefinition mesh = WitherStormCommandBlockModel.createBaseMesh(SingleHeadWitherStormModel.createMesh(PartPose.f_171404_), def, false, true, false);
        PartDefinition root = mesh.m_171576_();
        WSHunchback2_1.createBodyModel(root, 1.0f);
        return LayerDefinition.m_171565_((MeshDefinition)mesh, (int)160, (int)160);
    }

    @Override
    protected void configureTentacles(ModelPart root) {
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
        stack.m_85841_(1.001f, 1.001f, 1.001f);
    }
}

