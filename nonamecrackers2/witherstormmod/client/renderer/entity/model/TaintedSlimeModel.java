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
import nonamecrackers2.witherstormmod.client.renderer.entity.animation.TaintedSlimeAnimations;
import nonamecrackers2.witherstormmod.common.entity.TaintedSlime;

public class TaintedSlimeModel<T extends TaintedSlime>
extends HierarchicalModel<T> {
    private final ModelPart root;

    public TaintedSlimeModel(ModelPart root) {
        this.root = root;
    }

    public static LayerDefinition createLayerDefinition() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition bone = partdefinition.m_171599_("bone", CubeListBuilder.m_171558_(), PartPose.m_171419_((float)0.0f, (float)19.0f, (float)0.0f));
        bone.m_171599_("cube_r1", CubeListBuilder.m_171558_().m_171514_(0, 20).m_171488_(-3.0f, -3.0f, -3.0f, 6.0f, 6.0f, 6.0f, new CubeDeformation(-0.1f)), PartPose.m_171423_((float)0.0f, (float)0.0f, (float)0.0f, (float)-0.7854f, (float)0.0f, (float)0.0f));
        partdefinition.m_171599_("bb_main", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-5.0f, -10.0f, -5.0f, 10.0f, 10.0f, 10.0f, new CubeDeformation(0.0f)), PartPose.m_171419_((float)0.0f, (float)24.0f, (float)0.0f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)48, (int)48);
    }

    public ModelPart m_142109_() {
        return this.root;
    }

    public void setupAnim(T entity, float walkAnimation, float animationSpeed, float bob, float yRot, float xRot) {
        this.m_142109_().m_171331_().forEach(ModelPart::m_233569_);
        this.m_233381_(((TaintedSlime)((Object)entity)).idle, TaintedSlimeAnimations.MODEL_IDLE, bob);
    }
}

