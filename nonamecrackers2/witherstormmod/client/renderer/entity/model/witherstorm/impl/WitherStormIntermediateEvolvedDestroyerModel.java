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
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.IntermediateEvolvedDestroyerBodyModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass.LowResIntermediateEvolvedDestroyerBodyModel;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class WitherStormIntermediateEvolvedDestroyerModel<T extends WitherStormEntity>
extends ThreeHeadedWitherStormModel<T> {
    public WitherStormIntermediateEvolvedDestroyerModel(ModelPart part) {
        super(part, 3.0f);
    }

    public static LayerDefinition createLayerDefinition() {
        MeshDefinition mesh = ThreeHeadedWitherStormModel.createMesh(new PartPose[]{PartPose.m_171419_((float)-22.0f, (float)-65.0f, (float)-40.0f), PartPose.m_171419_((float)0.0f, (float)-32.0f, (float)-23.0f), PartPose.m_171419_((float)32.0f, (float)-60.0f, (float)-24.0f)});
        PartDefinition root = mesh.m_171576_();
        IntermediateEvolvedDestroyerBodyModel.createBodyModel(root, 0.2f);
        LowResIntermediateEvolvedDestroyerBodyModel.createBodyModel(root, 0.3f);
        PartDefinition tentacles = root.m_171597_("tentacles");
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle0", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{12, 18, 20, 23, 22, 25}, PartPose.m_171419_((float)-20.0f, (float)-50.0f, (float)0.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle1", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{12, 19, 22, 24, 28, 22}, PartPose.m_171419_((float)20.0f, (float)-55.0f, (float)0.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle2", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{12, 17, 23, 22, 28, 24}, PartPose.m_171419_((float)-10.0f, (float)-40.0f, (float)-10.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle3", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{12, 14, 19, 20, 20, 22}, PartPose.m_171419_((float)8.0f, (float)-45.0f, (float)-10.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle4", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{12, 14, 20, 21, 23, 24}, PartPose.m_171419_((float)-8.0f, (float)-50.0f, (float)12.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle5", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{12, 18, 22, 23, 24, 22}, PartPose.m_171419_((float)10.0f, (float)-45.0f, (float)12.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle6", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{23, 28, 28, 28, 32, 32}, PartPose.m_171419_((float)30.0f, (float)-155.0f, (float)-10.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle7", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{23, 28, 28, 28, 32, 32}, PartPose.m_171419_((float)-60.0f, (float)-120.0f, (float)0.0f));
        TentacleModel.populateDefinition(tentacles.m_171599_("tentacle8", CubeListBuilder.m_171558_(), PartPose.f_171404_), new int[]{23, 28, 28, 28, 32, 32}, PartPose.m_171419_((float)-30.0f, (float)-155.0f, (float)5.0f));
        return LayerDefinition.m_171565_((MeshDefinition)mesh, (int)160, (int)160);
    }

    @Override
    protected void configureHeads(ModelPart root, float headScale) {
        super.configureHeads(root, headScale);
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
        this.tentacles = new TentacleModel[9];
        this.tentacles[0] = new TentacleModel(root.m_171324_("tentacle0"), 3.0f);
        this.tentacles[0].animationSpeed = 0.3f;
        this.tentacles[0].yRotationalOffset = (float)Math.toRadians(90.0);
        this.tentacles[0].xRotationalOffset = (float)Math.toRadians(90.0);
        this.tentacles[0].xAngularOffset = -0.3925f;
        this.tentacles[0].yAngularOffset = 0.17444445f;
        this.tentacles[0].reach = 3.0f;
        this.tentacles[1] = new TentacleModel(root.m_171324_("tentacle1"), 3.0f);
        this.tentacles[1].animationOffset = 8.0f;
        this.tentacles[1].animationSpeed = 0.3f;
        this.tentacles[1].yRotationalOffset = (float)Math.toRadians(270.0);
        this.tentacles[1].xRotationalOffset = (float)Math.toRadians(90.0);
        this.tentacles[1].xAngularOffset = -0.3925f;
        this.tentacles[1].yAngularOffset = -0.17444445f;
        this.tentacles[1].reach = 2.0f;
        this.tentacles[2] = new TentacleModel(root.m_171324_("tentacle2"), 3.0f);
        this.tentacles[2].animationOffset = 16.0f;
        this.tentacles[2].animationSpeed = 0.3f;
        this.tentacles[2].xRotationalOffset = (float)Math.toRadians(100.0);
        this.tentacles[2].xAngularOffset = -0.3925f;
        this.tentacles[2].yAngularOffset = 0.2616667f;
        this.tentacles[2].reach = 2.5f;
        this.tentacles[3] = new TentacleModel(root.m_171324_("tentacle3"), 3.0f);
        this.tentacles[3].animationOffset = 9.0f;
        this.tentacles[3].animationSpeed = 0.3f;
        this.tentacles[3].yRotationalOffset = (float)Math.toRadians(320.0);
        this.tentacles[3].xRotationalOffset = (float)Math.toRadians(90.0);
        this.tentacles[3].xAngularOffset = -0.3925f;
        this.tentacles[3].yAngularOffset = -0.13083334f;
        this.tentacles[3].reach = 2.75f;
        this.tentacles[4] = new TentacleModel(root.m_171324_("tentacle4"), 3.0f);
        this.tentacles[4].animationOffset = 12.0f;
        this.tentacles[4].animationSpeed = 0.3f;
        this.tentacles[4].yRotationalOffset = (float)Math.toRadians(120.0);
        this.tentacles[4].xRotationalOffset = (float)Math.toRadians(70.0);
        this.tentacles[4].xAngularOffset = -0.3925f;
        this.tentacles[4].yAngularOffset = 0.2616667f;
        this.tentacles[4].reach = 3.0f;
        this.tentacles[5] = new TentacleModel(root.m_171324_("tentacle5"), 3.0f);
        this.tentacles[5].animationOffset = 20.0f;
        this.tentacles[5].animationSpeed = 0.5f;
        this.tentacles[5].yRotationalOffset = (float)Math.toRadians(220.0);
        this.tentacles[5].xRotationalOffset = (float)Math.toRadians(70.0);
        this.tentacles[5].xAngularOffset = -0.44857144f;
        this.tentacles[5].yAngularOffset = -0.2616667f;
        this.tentacles[5].reach = 2.5f;
        this.tentacles[6] = new TentacleModel(root.m_171324_("tentacle6"), 2.0f);
        this.tentacles[6].animationSpeed = 0.6f;
        this.tentacles[6].yRotationalOffset = -0.3925f;
        this.tentacles[6].xRotationalOffset = -0.628f;
        this.tentacles[6].xAngularOffset = 0.3925f;
        this.tentacles[6].yAngularOffset = -0.2616667f;
        this.tentacles[6].reach = 2.0f;
        this.tentacles[6].animationOffset = 20.0f;
        this.tentacles[7] = new TentacleModel(root.m_171324_("tentacle7"), 2.0f);
        this.tentacles[7].animationSpeed = 0.6f;
        this.tentacles[7].yRotationalOffset = 1.256f;
        this.tentacles[7].xAngularOffset = 0.19625f;
        this.tentacles[7].yAngularOffset = -0.3925f;
        this.tentacles[7].animationOffset = 30.0f;
        this.tentacles[7].reach = 2.0f;
        this.tentacles[8] = new TentacleModel(root.m_171324_("tentacle8"), 2.0f);
        this.tentacles[8].animationSpeed = 0.6f;
        this.tentacles[8].yRotationalOffset = -0.19625f;
        this.tentacles[8].xRotationalOffset = -1.0466667f;
        this.tentacles[8].xAngularOffset = 0.3925f;
        this.tentacles[8].yAngularOffset = 0.098125f;
        this.tentacles[8].animationOffset = 40.0f;
        this.tentacles[8].reach = 2.0f;
    }
}

