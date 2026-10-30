/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
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
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.AbstractWitherStormModel;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class WitherStormCommandBlockModel<T extends WitherStormEntity>
extends AbstractWitherStormModel<T> {
    private final ModelPart base;
    private final ModelPart centerHead;
    private final ModelPart rightHead;
    private final ModelPart leftHead;
    private final ModelPart ribcage;
    private final ModelPart tail;

    public WitherStormCommandBlockModel(ModelPart root) {
        super(root, 1.0f);
        this.base = root.m_171324_("witherBase");
        this.ribcage = this.base.m_171324_("ribcage");
        this.tail = this.base.m_171324_("tail");
        this.centerHead = this.base.m_171324_("center_head");
        this.rightHead = this.base.m_171324_("right_head");
        this.leftHead = this.base.m_171324_("left_head");
    }

    public static LayerDefinition createLayerDefinition(CubeDeformation def) {
        return LayerDefinition.m_171565_((MeshDefinition)WitherStormCommandBlockModel.createBaseMesh(AbstractWitherStormModel.createMesh(), def, true, true, true), (int)160, (int)160);
    }

    public static MeshDefinition createBaseMesh(MeshDefinition mesh, CubeDeformation def, boolean hasCenterHead, boolean hasRibcageExtension, boolean hasTail) {
        PartDefinition base = mesh.m_171576_().m_171599_("witherBase", CubeListBuilder.m_171558_(), PartPose.f_171404_);
        base.m_171599_("shoulders", CubeListBuilder.m_171558_().m_171514_(0, 16).m_171488_(-10.0f, 3.9f, -0.5f, 20.0f, 3.0f, 3.0f, def), PartPose.f_171404_);
        PartDefinition ribcage = base.m_171599_("ribcage", CubeListBuilder.m_171558_().m_171514_(0, 22).m_171488_(0.0f, 0.0f, 0.0f, 3.0f, 10.0f, 3.0f, def).m_171514_(24, 22).m_171488_(-4.0f, 1.5f, 0.5f, 11.0f, 2.0f, 2.0f, def).m_171514_(24, 22).m_171488_(-4.0f, 4.5f, 0.5f, 11.0f, 2.0f, 2.0f, def).m_171514_(24, 22).m_171488_(-4.0f, 7.5f, 0.5f, 11.0f, 2.0f, 2.0f, def), PartPose.m_171423_((float)-2.0f, (float)6.9f, (float)-0.5f, (float)0.20420352f, (float)0.0f, (float)0.0f));
        if (hasRibcageExtension) {
            PartDefinition ribcageExtension = ribcage.m_171599_("ribcageExtension", CubeListBuilder.m_171558_().m_171514_(128, 40).m_171488_(-5.5f, -2.0f, -4.0f, 2.0f, 2.0f, 8.0f, def).m_171514_(128, 40).m_171488_(-5.5f, -5.0f, -4.0f, 2.0f, 2.0f, 8.0f, def).m_171514_(128, 40).m_171488_(-5.5f, -8.0f, -4.0f, 2.0f, 2.0f, 8.0f, def).m_171514_(128, 40).m_171488_(3.5f, -8.0f, -4.0f, 2.0f, 2.0f, 8.0f, def).m_171514_(128, 40).m_171488_(3.5f, -5.0f, -4.0f, 2.0f, 2.0f, 8.0f, def).m_171514_(128, 40).m_171488_(3.5f, -2.0f, -4.0f, 2.0f, 2.0f, 8.0f, def).m_171514_(140, 44).m_171488_(2.5f, -2.0f, -4.0f, 1.0f, 2.0f, 2.0f, def).m_171514_(140, 44).m_171488_(2.5f, -5.0f, -4.0f, 1.0f, 2.0f, 2.0f, def).m_171514_(140, 44).m_171488_(2.5f, -8.0f, -4.0f, 1.0f, 2.0f, 2.0f, def).m_171514_(140, 44).m_171488_(-3.5f, -8.0f, -4.0f, 1.0f, 2.0f, 2.0f, def).m_171514_(140, 44).m_171488_(-3.5f, -5.0f, -4.0f, 1.0f, 2.0f, 2.0f, def).m_171514_(140, 44).m_171488_(-3.5f, -2.0f, -4.0f, 1.0f, 2.0f, 2.0f, def), PartPose.m_171419_((float)1.5f, (float)9.5f, (float)-3.5f));
            ribcageExtension.m_171599_("block", CubeListBuilder.m_171558_().m_171514_(48, 0).m_171496_(-4.0f, -8.0f, -3.0f, 8.0f, 8.0f, 8.0f, def.m_171469_(0.001f), 0.5f, 0.5f), PartPose.f_171404_);
        }
        if (hasTail) {
            base.m_171599_("tail", CubeListBuilder.m_171558_().m_171514_(12, 22).m_171488_(0.0f, 0.0f, 0.0f, 3.0f, 6.0f, 3.0f, def), PartPose.m_171423_((float)-2.0f, (float)(6.9f + Mth.m_14089_((float)0.20420352f) * 10.0f), (float)(-0.5f + Mth.m_14031_((float)0.20420352f) * 10.0f), (float)0.83252203f, (float)0.0f, (float)0.0f));
        }
        if (hasCenterHead) {
            base.m_171599_("center_head", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-4.0f, -4.0f, -4.0f, 8.0f, 8.0f, 8.0f, def), PartPose.f_171404_);
        }
        CubeListBuilder sideHeads = CubeListBuilder.m_171558_().m_171514_(32, 0).m_171488_(-4.0f, -4.0f, -4.0f, 6.0f, 6.0f, 6.0f, def);
        base.m_171599_("right_head", sideHeads, PartPose.m_171419_((float)-8.0f, (float)4.0f, (float)0.0f));
        base.m_171599_("left_head", sideHeads, PartPose.m_171419_((float)10.0f, (float)4.0f, (float)0.0f));
        return mesh;
    }

    @Override
    protected void configureHeads(ModelPart root, float scale) {
    }

    @Override
    protected void configureTentacles(ModelPart root) {
    }

    @Override
    public void setupAnimations(T entity, float partialTicks, float tickCount, float yRot, float xRot) {
        super.setupAnimations(entity, partialTicks, tickCount, yRot, xRot);
        float f = Mth.m_14089_((float)(tickCount * 0.1f));
        this.ribcage.f_104203_ = (0.065f + 0.05f * f) * (float)Math.PI;
        this.tail.m_104227_(-2.0f, 6.9f + Mth.m_14089_((float)this.ribcage.f_104203_) * 10.0f, -0.5f + Mth.m_14031_((float)this.ribcage.f_104203_) * 10.0f);
        this.tail.f_104203_ = (0.265f + 0.1f * f) * (float)Math.PI;
        this.centerHead.f_104204_ = yRot * ((float)Math.PI / 180);
        this.centerHead.f_104203_ = xRot * ((float)Math.PI / 180);
        WitherStormCommandBlockModel.setupHeadRotation(entity, this.leftHead, 1, partialTicks);
        WitherStormCommandBlockModel.setupHeadRotation(entity, this.rightHead, 2, partialTicks);
    }

    @Override
    protected void renderExtra(PoseStack stack, VertexConsumer consumer, int packedLight, int overlayTexture, float r, float g, float b, float a) {
        this.base.m_104306_(stack, consumer, packedLight, overlayTexture, r, g, b, a);
    }

    public static void setupHeadRotation(WitherStormEntity storm, ModelPart headModel, int head, float partialTick) {
        headModel.f_104204_ = (Mth.m_14179_((float)partialTick, (float)storm.getHeadYRotO(head), (float)storm.getHeadYRot(head)) - Mth.m_14179_((float)partialTick, (float)storm.f_20884_, (float)storm.f_20883_)) * ((float)Math.PI / 180);
        headModel.f_104203_ = Mth.m_14179_((float)partialTick, (float)storm.getHeadXRotO(head), (float)storm.getHeadXRot(head)) * ((float)Math.PI / 180);
    }
}

