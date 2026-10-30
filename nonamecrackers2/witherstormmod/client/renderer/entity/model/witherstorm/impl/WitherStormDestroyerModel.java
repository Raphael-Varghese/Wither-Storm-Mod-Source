/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.TentacleModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.ThreeHeadedWitherStormModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.DestroyerBodyModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.LowResDestroyerBodyModel;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class WitherStormDestroyerModel<T extends WitherStormEntity>
extends ThreeHeadedWitherStormModel<T> {
    public WitherStormDestroyerModel(ModelPart root) {
        super(root, 3.0f);
    }

    public static LayerDefinition createLayerDefinition() {
        MeshDefinition mesh = ThreeHeadedWitherStormModel.createMesh(new PartPose[]{PartPose.m_171419_((float)-22.0f, (float)-65.0f, (float)-40.0f), PartPose.m_171419_((float)0.0f, (float)-32.0f, (float)-23.0f), PartPose.m_171419_((float)32.0f, (float)-60.0f, (float)-24.0f)});
        PartDefinition root = mesh.m_171576_();
        DestroyerBodyModel.createBodyModel(root, 0.2f);
        LowResDestroyerBodyModel.createBodyModel(root, 0.3f);
        PartDefinition tentacles = root.m_171597_("tentacles");
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle0", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{23, 28, 28, 28, 32, 32}, PartPose.m_171419_((float)-10.0f, (float)-30.0f, (float)0.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle1", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{23, 28, 28, 28, 32, 32}, PartPose.m_171419_((float)30.0f, (float)-115.0f, (float)-10.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle2", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{23, 28, 28, 28, 32, 32}, PartPose.m_171419_((float)10.0f, (float)-40.0f, (float)10.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle3", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{23, 28, 28, 28, 32, 32}, PartPose.m_171419_((float)-50.0f, (float)-100.0f, (float)0.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle4", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{23, 28, 28, 28, 32, 32}, PartPose.m_171419_((float)-10.0f, (float)-95.0f, (float)15.0f));
        return LayerDefinition.m_171565_((MeshDefinition)mesh, (int)160, (int)160);
    }

    @Override
    protected void configureHeads(ModelPart part, float headScale) {
        super.configureHeads(part, headScale);
        this.rightHead.tractorBeamDistance = 90.0f;
        this.rightHead.tractorBeamStartSize = 0.1f;
        this.rightHead.tractorBeamEndSize = 5.0f;
        this.rightHead.tractorBeamXOffset = 0.0f;
        this.rightHead.tractorBeamYOffset = 8.0f;
        this.rightHead.tractorBeamZOffset = 0.0f;
        this.rightHead.animationOffset = 100.0f;
        this.rightHead.pivotOffsetX = -4.0f;
        this.rightHead.pivotOffsetY = 0.325f;
        this.rightHead.pivotOffsetZ = 0.0f;
        this.middleHead.tractorBeamDistance = 90.0f;
        this.middleHead.tractorBeamStartSize = 0.1f;
        this.middleHead.tractorBeamEndSize = 5.0f;
        this.middleHead.tractorBeamXOffset = 0.0f;
        this.middleHead.tractorBeamYOffset = 8.0f;
        this.middleHead.tractorBeamZOffset = 0.0f;
        this.middleHead.pivotOffsetX = -4.0f;
        this.middleHead.pivotOffsetY = 0.325f;
        this.middleHead.pivotOffsetZ = 0.0f;
        this.leftHead.tractorBeamDistance = 90.0f;
        this.leftHead.tractorBeamStartSize = 0.1f;
        this.leftHead.tractorBeamEndSize = 5.0f;
        this.leftHead.tractorBeamXOffset = 0.0f;
        this.leftHead.tractorBeamYOffset = 8.0f;
        this.leftHead.tractorBeamZOffset = 0.0f;
        this.leftHead.animationOffset = 175.0f;
        this.leftHead.pivotOffsetX = -4.0f;
        this.leftHead.pivotOffsetY = 0.325f;
        this.leftHead.pivotOffsetZ = 0.0f;
    }

    @Override
    protected void configureTentacles(ModelPart root) {
        this.tentacles = new TentacleModel[5];
        this.tentacles[0] = new TentacleModel(root.m_171324_("tentacle0"), 2.0f);
        this.tentacles[0].animationSpeed = 0.6f;
        this.tentacles[0].yRotationalOffset = 1.57f;
        this.tentacles[0].xRotationalOffset = 0.3925f;
        this.tentacles[0].xAngularOffset = -0.19625f;
        this.tentacles[0].yAngularOffset = -0.19625f;
        this.tentacles[0].reach = 2.0f;
        this.tentacles[1] = new TentacleModel(root.m_171324_("tentacle1"), 2.0f);
        this.tentacles[1].animationSpeed = 0.6f;
        this.tentacles[1].yRotationalOffset = -0.3925f;
        this.tentacles[1].xRotationalOffset = -0.628f;
        this.tentacles[1].xAngularOffset = 0.3925f;
        this.tentacles[1].yAngularOffset = -0.2616667f;
        this.tentacles[1].reach = 2.0f;
        this.tentacles[1].animationOffset = 20.0f;
        this.tentacles[2] = new TentacleModel(root.m_171324_("tentacle2"), 2.0f);
        this.tentacles[2].animationSpeed = 0.6f;
        this.tentacles[2].yRotationalOffset = -1.7444445f;
        this.tentacles[2].xAngularOffset = 0.3925f;
        this.tentacles[2].yAngularOffset = -0.19625f;
        this.tentacles[2].reach = 2.0f;
        this.tentacles[2].animationOffset = 10.0f;
        this.tentacles[3] = new TentacleModel(root.m_171324_("tentacle3"), 2.0f);
        this.tentacles[3].animationSpeed = 0.6f;
        this.tentacles[3].yRotationalOffset = 1.256f;
        this.tentacles[3].xAngularOffset = -0.5233334f;
        this.tentacles[3].yAngularOffset = 0.3925f;
        this.tentacles[3].animationOffset = 30.0f;
        this.tentacles[3].reach = 2.0f;
        this.tentacles[4] = new TentacleModel(root.m_171324_("tentacle4"), 2.0f);
        this.tentacles[4].animationSpeed = 0.6f;
        this.tentacles[4].yRotationalOffset = -0.19625f;
        this.tentacles[4].xRotationalOffset = -1.0466667f;
        this.tentacles[4].xAngularOffset = 0.2616667f;
        this.tentacles[4].yAngularOffset = 0.098125f;
        this.tentacles[4].animationOffset = 40.0f;
        this.tentacles[4].reach = 2.0f;
    }
}

