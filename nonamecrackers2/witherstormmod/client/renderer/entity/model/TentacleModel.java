/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 *  net.minecraft.util.Mth
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
import nonamecrackers2.witherstormmod.common.entity.part.TentaclePartEntity;

public class TentacleModel
extends EntityModel<TentacleEntity> {
    private final List<ModelPart> segments;
    private final ModelPart base;
    private final ModelPart segment;
    private final ModelPart segment2;
    private final ModelPart segment3;
    private final ModelPart segment4;
    private final ModelPart segment5;

    public TentacleModel(ModelPart root) {
        this.base = root;
        this.segment = this.base.m_171324_("segment");
        this.segment2 = this.segment.m_171324_("segment2");
        this.segment3 = this.segment2.m_171324_("segment3");
        this.segment4 = this.segment3.m_171324_("segment4");
        this.segment5 = this.segment4.m_171324_("segment5");
        this.segments = Lists.newArrayList((Object[])new ModelPart[]{this.segment, this.segment2, this.segment3, this.segment4, this.segment5});
    }

    public static LayerDefinition createLayerDefinition() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition base = mesh.m_171576_();
        PartDefinition segment = base.m_171599_("segment", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171506_(-6.0f, -12.0f, -6.0f, 12.0f, 12.0f, 12.0f, false), PartPose.f_171404_);
        PartDefinition segment2 = segment.m_171599_("segment2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171506_(-5.0f, -16.0f, -5.0f, 10.0f, 16.0f, 10.0f, false), PartPose.m_171419_((float)0.0f, (float)-12.0f, (float)0.0f));
        PartDefinition segment3 = segment2.m_171599_("segment3", CubeListBuilder.m_171558_().m_171514_(40, 16).m_171506_(-4.0f, -20.0f, -4.0f, 8.0f, 20.0f, 8.0f, false), PartPose.m_171419_((float)0.0f, (float)-16.0f, (float)0.0f));
        PartDefinition segment4 = segment3.m_171599_("segment4", CubeListBuilder.m_171558_().m_171514_(34, 44).m_171506_(-3.0f, -24.0f, -3.0f, 6.0f, 24.0f, 6.0f, false), PartPose.m_171419_((float)0.0f, (float)-20.0f, (float)0.0f));
        segment4.m_171599_("segment5", CubeListBuilder.m_171558_().m_171514_(0, 50).m_171506_(-2.0f, -24.0f, -2.0f, 4.0f, 24.0f, 4.0f, false), PartPose.m_171419_((float)0.0f, (float)-24.0f, (float)0.0f));
        return LayerDefinition.m_171565_((MeshDefinition)mesh, (int)128, (int)128);
    }

    public void setupAnim(TentacleEntity entity, float partialTicks, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        float offset = 90.0f;
        TentaclePartEntity<TentacleEntity> part = entity.getTentacle();
        ArrayList chained = Lists.newArrayList((Object[])new TentaclePartEntity[]{part});
        chained.addAll(part.getChained());
        TentaclePartEntity previous = null;
        for (int j = 0; j < this.segments.size(); ++j) {
            TentaclePartEntity chain = (TentaclePartEntity)((Object)chained.get(j));
            float prevXRot = 0.0f;
            float prevYRot = 0.0f;
            if (previous != null) {
                prevXRot = Mth.m_14179_((float)partialTicks, (float)previous.f_19860_, (float)previous.m_146909_()) + offset;
                prevYRot = Mth.m_14179_((float)partialTicks, (float)previous.f_19859_, (float)previous.m_146908_());
            }
            ModelPart segment = this.segments.get(j);
            segment.f_104203_ = -(Mth.m_14179_((float)partialTicks, (float)chain.f_19860_, (float)chain.m_146909_()) - prevXRot + offset) * ((float)Math.PI / 180);
            segment.f_104204_ = (Mth.m_14179_((float)partialTicks, (float)chain.f_19859_, (float)chain.m_146908_()) - prevYRot) * ((float)Math.PI / 180);
            previous = chain;
        }
    }

    public void m_7695_(PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.base.m_104301_(stack, buffer, packedLight, packedOverlay);
    }
}

