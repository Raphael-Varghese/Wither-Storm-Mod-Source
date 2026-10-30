/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.model.SkullModelBase
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class FlamingWitherSkullModel
extends SkullModelBase {
    private final ModelPart root;
    private final ModelPart base;

    public FlamingWitherSkullModel(ModelPart root) {
        this.root = root;
        this.base = root.m_171324_("head");
    }

    public static LayerDefinition createLayerDefinition() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.m_171576_();
        PartDefinition base = root.m_171599_("head", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171506_(-4.0f, -4.0f, -4.0f, 8.0f, 8.0f, 8.0f, false), PartPose.m_171419_((float)0.0f, (float)-3.5f, (float)0.0f));
        PartDefinition flame = base.m_171599_("flame", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.0f, -8.0f, 4.0f, 8.0f, 0.0f, 8.0f, CubeDeformation.f_171458_, 0.5f, 0.5f).m_171514_(0, 24).m_171496_(-4.0f, 0.0f, 4.0f, 8.0f, 0.0f, 8.0f, CubeDeformation.f_171458_, 0.5f, 0.5f), PartPose.m_171419_((float)0.0f, (float)4.0f, (float)0.0f));
        flame.m_171599_("flameSide", CubeListBuilder.m_171558_().m_171514_(0, 16).m_171496_(0.0f, -4.0f, -4.0f, 0.0f, 8.0f, 8.0f, CubeDeformation.f_171458_, 0.5f, 0.5f).m_171514_(0, 16).m_171496_(8.0f, -4.0f, -4.0f, 0.0f, 8.0f, 8.0f, CubeDeformation.f_171458_, 0.5f, 0.5f), PartPose.m_171423_((float)-4.0f, (float)-4.0f, (float)8.0f, (float)-1.5708f, (float)0.0f, (float)0.0f));
        return LayerDefinition.m_171565_((MeshDefinition)mesh, (int)32, (int)32);
    }

    public void m_7695_(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.root.m_104301_(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void m_6251_(float f, float yRot, float xRot) {
        this.base.f_104204_ = yRot * ((float)Math.PI / 180);
        this.base.f_104203_ = xRot * ((float)Math.PI / 180);
    }
}

