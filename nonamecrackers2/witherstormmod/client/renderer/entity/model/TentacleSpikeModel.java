/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.HierarchicalModel
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import nonamecrackers2.witherstormmod.common.entity.TentacleSpike;

public class TentacleSpikeModel<T extends TentacleSpike>
extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart base;
    private final ModelPart middle;
    private final ModelPart end;

    public TentacleSpikeModel(ModelPart root) {
        this.root = root;
        this.base = root.m_171324_("base");
        this.middle = this.base.m_171324_("middle");
        this.end = this.middle.m_171324_("end");
    }

    public static LayerDefinition createLayerDefinition() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition base = partdefinition.m_171599_("base", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-3.0f, -7.0f, -3.0f, 6.0f, 7.0f, 6.0f, new CubeDeformation(0.0f)), PartPose.f_171404_);
        PartDefinition middle = base.m_171599_("middle", CubeListBuilder.m_171558_().m_171514_(0, 13).m_171488_(-2.0f, -9.0f, -2.0f, 4.0f, 9.0f, 4.0f, new CubeDeformation(0.0f)), PartPose.m_171419_((float)0.0f, (float)-7.0f, (float)0.0f));
        middle.m_171599_("end", CubeListBuilder.m_171558_().m_171514_(16, 13).m_171488_(-1.0f, -11.0f, -1.0f, 2.0f, 11.0f, 2.0f, new CubeDeformation(0.0f)), PartPose.m_171419_((float)0.0f, (float)-9.0f, (float)0.0f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)32, (int)32);
    }

    public void setupAnim(T entity, float anim, float f, float f1, float yRot, float xRot) {
        float sway = anim * (3.0f - anim) + RandomSource.m_216335_((long)entity.m_19879_()).m_188501_() * 1000.0f;
        float zSway = Mth.m_14089_((float)sway) * 0.1f;
        float xSway = Mth.m_14031_((float)sway) * 0.1f;
        this.base.f_104205_ = zSway;
        this.base.f_104203_ = xSway;
        this.middle.f_104205_ = zSway;
        this.middle.f_104203_ = xSway;
        this.end.f_104205_ = zSway;
        this.end.f_104203_ = xSway;
    }

    public ModelPart m_142109_() {
        return this.root;
    }
}

