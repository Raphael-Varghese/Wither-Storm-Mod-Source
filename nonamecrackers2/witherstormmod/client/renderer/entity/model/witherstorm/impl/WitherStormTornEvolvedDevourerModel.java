/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.PoseStack$Pose
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.TentacleModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.ThreeHeadedWitherStormModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormEvolvedDevourerModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.LowResTornEvolvedDevourerBodyModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.TornEvolvedDevourerBodyModel;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class WitherStormTornEvolvedDevourerModel<T extends WitherStormEntity>
extends WitherStormEvolvedDevourerModel<T> {
    public WitherStormTornEvolvedDevourerModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createLayerDefinition() {
        MeshDefinition mesh = ThreeHeadedWitherStormModel.createMesh(new PartPose[]{PartPose.m_171419_((float)-22.0f, (float)-65.0f, (float)-40.0f), PartPose.m_171419_((float)0.0f, (float)-32.0f, (float)-23.0f), PartPose.m_171419_((float)32.0f, (float)-60.0f, (float)-24.0f)});
        PartDefinition root = mesh.m_171576_();
        TornEvolvedDevourerBodyModel.createBodyModel(root, 0.2f);
        LowResTornEvolvedDevourerBodyModel.createBodyModel(root, 0.3f);
        PartDefinition tentacles = root.m_171597_("tentacles");
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle0", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{18, 24, 24, 28, 28, 32}, PartPose.m_171419_((float)-20.0f, (float)-25.0f, (float)5.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle1", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{18, 24, 24, 24, 28, 38}, PartPose.m_171419_((float)20.0f, (float)-27.5f, (float)7.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle2", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{18, 24, 24, 28, 32, 28}, PartPose.m_171419_((float)-10.0f, (float)-30.0f, (float)-10.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle3", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{18, 18, 24, 24, 24, 28}, PartPose.m_171419_((float)8.0f, (float)-34.0f, (float)-6.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle4", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{18, 18, 24, 28, 32, 32}, PartPose.m_171419_((float)-8.0f, (float)-25.0f, (float)16.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle5", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{18, 20, 26, 28, 32, 28}, PartPose.m_171419_((float)10.0f, (float)-23.0f, (float)19.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle6", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{18, 20, 26, 28, 28, 24}, PartPose.m_171419_((float)-2.0f, (float)0.0f, (float)0.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacleLarge0", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{16, 20, 24, 28, 32, 28}, PartPose.m_171419_((float)-24.0f, (float)-28.0f, (float)0.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacleLarge1", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{20, 20, 24, 24, 28, 32}, PartPose.m_171419_((float)28.0f, (float)-28.0f, (float)2.0f));
        return LayerDefinition.m_171565_((MeshDefinition)mesh, (int)160, (int)160);
    }

    @Override
    public void renderMassDecal(T entity, PoseStack stack, MultiBufferSource buffer, int packedLight, int overlayCoords, float r, float g, float b, float a) {
        stack.m_85836_();
        VertexConsumer builder = buffer.m_6299_(RenderType.m_110502_());
        PoseStack.Pose entry = stack.m_85850_();
        Matrix4f matrix4f = entry.m_252922_();
        Matrix3f matrix3f = entry.m_252943_();
        float aR = 0.5f;
        float aG = 0.3f;
        float aB = 0.8f;
        float aA = a * 0.2f;
        float size = 0.35f;
        float topZOffset = 0.4f;
        float stretch = 1.1f;
        if (this.lowResModelsEnabled(entity)) {
            topZOffset = 0.45f;
            stretch = 1.4f;
            stack.m_85837_(-0.12, -2.0, -0.8);
        } else {
            stack.m_85837_(-0.12, -2.0, -0.9);
        }
        builder.m_252986_(matrix4f, size * stretch, size, 0.0f).m_85950_(aR, aG, aB, aA).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, size * stretch, -size, -topZOffset).m_85950_(aR, aG, aB, aA).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -size * stretch, -size, -topZOffset).m_85950_(aR, aG, aB, aA).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -size * stretch, size, 0.0f).m_85950_(aR, aG, aB, aA).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -size * stretch, size, 0.0f).m_85950_(aR, aG, aB, aA).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, -size * stretch, -size, -topZOffset).m_85950_(aR, aG, aB, aA).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, size * stretch, -size, -topZOffset).m_85950_(aR, aG, aB, aA).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        builder.m_252986_(matrix4f, size * stretch, size, 0.0f).m_85950_(aR, aG, aB, aA).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(packedLight).m_252939_(matrix3f, 0.0f, -1.0f, 0.0f).m_5752_();
        stack.m_85849_();
    }
}

