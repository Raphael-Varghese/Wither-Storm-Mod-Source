/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.AnimationUtils
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.model.VillagerHeadModel
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model.sickenedentity;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.VillagerHeadModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import nonamecrackers2.witherstormmod.common.entity.SickenedVillager;

public class SickenedVillagerModel<T extends SickenedVillager>
extends HumanoidModel<T>
implements VillagerHeadModel {
    private final ModelPart hatRim;

    public SickenedVillagerModel(ModelPart part) {
        super(part);
        this.hatRim = this.f_102809_.m_171324_("hat_rim");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.m_170681_((CubeDeformation)CubeDeformation.f_171458_, (float)0.0f);
        PartDefinition partdefinition = meshdefinition.m_171576_();
        partdefinition.m_171599_("head", new CubeListBuilder().m_171514_(0, 0).m_171481_(-4.0f, -10.0f, -4.0f, 8.0f, 10.0f, 8.0f).m_171514_(24, 0).m_171481_(-1.0f, -3.0f, -6.0f, 2.0f, 4.0f, 2.0f), PartPose.f_171404_);
        PartDefinition partdefinition1 = partdefinition.m_171599_("hat", CubeListBuilder.m_171558_().m_171514_(32, 0).m_171488_(-4.0f, -10.0f, -4.0f, 8.0f, 10.0f, 8.0f, new CubeDeformation(0.5f)), PartPose.f_171404_);
        partdefinition1.m_171599_("hat_rim", CubeListBuilder.m_171558_().m_171514_(30, 47).m_171481_(-8.0f, -8.0f, -6.0f, 16.0f, 16.0f, 1.0f), PartPose.m_171430_((float)-1.5707964f, (float)0.0f, (float)0.0f));
        partdefinition.m_171599_("body", CubeListBuilder.m_171558_().m_171514_(16, 20).m_171481_(-4.0f, 0.0f, -3.0f, 8.0f, 12.0f, 6.0f).m_171514_(0, 38).m_171488_(-4.0f, 0.0f, -3.0f, 8.0f, 18.0f, 6.0f, new CubeDeformation(0.05f)), PartPose.f_171404_);
        partdefinition.m_171599_("right_arm", CubeListBuilder.m_171558_().m_171514_(44, 22).m_171481_(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f), PartPose.m_171419_((float)-5.0f, (float)2.0f, (float)0.0f));
        partdefinition.m_171599_("left_arm", CubeListBuilder.m_171558_().m_171514_(44, 22).m_171480_().m_171481_(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f), PartPose.m_171419_((float)5.0f, (float)2.0f, (float)0.0f));
        partdefinition.m_171599_("right_leg", CubeListBuilder.m_171558_().m_171514_(0, 22).m_171481_(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f), PartPose.m_171419_((float)-2.0f, (float)12.0f, (float)0.0f));
        partdefinition.m_171599_("left_leg", CubeListBuilder.m_171558_().m_171514_(0, 22).m_171480_().m_171481_(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f), PartPose.m_171419_((float)2.0f, (float)12.0f, (float)0.0f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)64, (int)64);
    }

    public static LayerDefinition createArmorLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.m_170681_((CubeDeformation)deformation, (float)0.0f);
        PartDefinition partdefinition = meshdefinition.m_171576_();
        partdefinition.m_171599_("head", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-4.0f, -10.0f, -4.0f, 8.0f, 8.0f, 8.0f, deformation), PartPose.f_171404_);
        partdefinition.m_171599_("body", CubeListBuilder.m_171558_().m_171514_(16, 16).m_171488_(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, deformation.m_171469_(0.1f)), PartPose.f_171404_);
        partdefinition.m_171599_("right_leg", CubeListBuilder.m_171558_().m_171514_(0, 16).m_171488_(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, deformation.m_171469_(0.1f)), PartPose.m_171419_((float)-2.0f, (float)12.0f, (float)0.0f));
        partdefinition.m_171599_("left_leg", CubeListBuilder.m_171558_().m_171514_(0, 16).m_171480_().m_171488_(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, deformation.m_171469_(0.1f)), PartPose.m_171419_((float)2.0f, (float)12.0f, (float)0.0f));
        partdefinition.m_171597_("hat").m_171599_("hat_rim", CubeListBuilder.m_171558_(), PartPose.f_171404_);
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)64, (int)32);
    }

    public void setupAnim(T entity, float p_104176_, float p_104177_, float p_104178_, float p_104179_, float p_104180_) {
        super.m_6973_(entity, p_104176_, p_104177_, p_104178_, p_104179_, p_104180_);
        AnimationUtils.m_102102_((ModelPart)this.f_102812_, (ModelPart)this.f_102811_, (boolean)entity.m_5912_(), (float)this.f_102608_, (float)p_104178_);
    }

    public void m_7491_(boolean flag) {
        this.f_102808_.f_104207_ = flag;
        this.f_102809_.f_104207_ = flag;
        this.hatRim.f_104207_ = flag;
    }
}

