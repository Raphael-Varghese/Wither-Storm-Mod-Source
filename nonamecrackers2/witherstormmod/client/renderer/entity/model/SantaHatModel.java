/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.model.Model
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.resources.ResourceLocation
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import nonamecrackers2.witherstormmod.WitherStormMod;

public class SantaHatModel
extends Model {
    public static final ResourceLocation TEXTURE = WitherStormMod.id("textures/misc/santa_hat.png");
    private final ModelPart root;

    public SantaHatModel(ModelPart root) {
        super(RenderType::m_110458_);
        this.root = root;
    }

    public void m_7695_(PoseStack stack, VertexConsumer consumer, int packedLight, int overlayTexture, float r, float g, float b, float alpha) {
        this.root.m_104306_(stack, consumer, packedLight, overlayTexture, r, g, b, alpha);
    }

    public static LayerDefinition createLayerDefinition() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition base = partdefinition.m_171599_("base", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-6.0f, -3.0f, -6.0f, 12.0f, 3.0f, 12.0f, new CubeDeformation(0.0f)), PartPose.m_171419_((float)0.0f, (float)-4.0f, (float)6.1f));
        PartDefinition segment1 = base.m_171599_("segment1", CubeListBuilder.m_171558_().m_171514_(0, 15).m_171488_(-5.0f, -3.0f, -5.0f, 10.0f, 4.0f, 10.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)0.0f, (float)-3.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)-0.1222f));
        PartDefinition segment2 = segment1.m_171599_("segment2", CubeListBuilder.m_171558_().m_171514_(0, 29).m_171488_(-4.0f, -3.0f, -4.0f, 8.0f, 5.0f, 8.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)0.0f, (float)-2.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)-0.6196f));
        PartDefinition segment3 = segment2.m_171599_("segment3", CubeListBuilder.m_171558_().m_171514_(30, 15).m_171488_(-3.0f, -3.0f, -3.0f, 6.0f, 4.0f, 6.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)0.0f, (float)-3.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)-0.3229f));
        PartDefinition segment4 = segment3.m_171599_("segment4", CubeListBuilder.m_171558_().m_171514_(24, 29).m_171488_(-2.0f, -2.0f, -2.0f, 4.0f, 3.0f, 4.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)0.25f, (float)-3.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)-0.4887f));
        segment4.m_171599_("tip", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-1.0f, -2.0f, -1.0f, 2.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)0.0f, (float)-1.7f, (float)0.0f, (float)0.0f, (float)0.0f, (float)-0.5672f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)64, (int)64);
    }
}

