/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.PartDefinition
 *  net.minecraft.util.Mth
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class TentacleModel {
    public final ModelPart tentacle;
    protected final ModelPart segmentOne;
    protected final ModelPart segmentTwo;
    protected final ModelPart segmentThree;
    protected final ModelPart segmentFour;
    protected final ModelPart segmentFive;
    protected final ModelPart segmentSix;
    public float scale;
    public float xRotationalOffset;
    public float yRotationalOffset;
    public float animationSpeed = 1.0f;
    public float yAngularOffset;
    public float xAngularOffset;
    public float animationOffset;
    public float reach = 1.0f;

    public TentacleModel(ModelPart root, float scale) {
        this.tentacle = root.m_171324_("base");
        this.segmentOne = this.tentacle.m_171324_("segment1");
        this.segmentTwo = this.segmentOne.m_171324_("segment2");
        this.segmentThree = this.segmentTwo.m_171324_("segment3");
        this.segmentFour = this.segmentThree.m_171324_("segment4");
        this.segmentFive = this.segmentFour.m_171324_("segment5");
        this.segmentSix = this.segmentFive.m_171324_("segment6");
        this.scale = scale;
    }

    public static void populateDefinition(PartDefinition root, int[] length, PartPose offset) {
        PartDefinition tentacle = root.m_171599_("base", CubeListBuilder.m_171558_(), offset);
        PartDefinition segment1 = tentacle.m_171599_("segment1", CubeListBuilder.m_171558_().m_171514_(36 + (24 - length[0]), 14 + (24 - length[0])).m_171506_(-6.0f, -6.0f, (float)(-length[0]), 12.0f, 12.0f, (float)length[0], false), PartPose.f_171404_);
        PartDefinition segment2 = segment1.m_171599_("segment2", CubeListBuilder.m_171558_().m_171514_(76 + (32 - length[1]), 32 + (32 - length[1])).m_171506_(-5.0f, -5.0f, (float)(-length[1]), 10.0f, 10.0f, (float)length[1], false), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)(-length[0])));
        PartDefinition segment3 = segment2.m_171599_("segment3", CubeListBuilder.m_171558_().m_171514_(76 + (32 - length[2]), 33 + (32 - length[2])).m_171506_(-4.0f, -4.0f, (float)(-length[2]), 8.0f, 8.0f, (float)length[2], false), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)(-length[1])));
        PartDefinition segment4 = segment3.m_171599_("segment4", CubeListBuilder.m_171558_().m_171514_(77 + (32 - length[3]), 34 + (32 - length[3])).m_171506_(-3.0f, -3.0f, (float)(-length[3]), 6.0f, 6.0f, (float)length[3], false), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)(-length[2])));
        PartDefinition segment5 = segment4.m_171599_("segment5", CubeListBuilder.m_171558_().m_171514_(76 + (34 - length[4]), 36 + (34 - length[4])).m_171506_(-2.0f, -2.0f, (float)(-length[4]), 4.0f, 4.0f, (float)length[4], false), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)(-length[3])));
        segment5.m_171599_("segment6", CubeListBuilder.m_171558_().m_171514_(77 + (34 - length[5]), 38 + (34 - length[5])).m_171506_(-1.0f, -1.0f, (float)(-length[5]), 2.0f, 2.0f, (float)length[5], false), PartPose.m_171419_((float)0.0f, (float)0.0f, (float)(-length[4])));
    }

    public void setupAnimations(float animation, float partialTicks) {
        float speed = this.animationSpeed;
        float f = Mth.m_14089_((float)((animation + this.animationOffset * 10.0f) * speed * 0.1f)) * this.reach;
        float s = Mth.m_14031_((float)((animation + this.animationOffset * 10.0f) * speed / 2.0f * 0.1f)) * this.reach;
        this.tentacle.f_104204_ = s * f * 0.05f + this.yRotationalOffset;
        this.tentacle.f_104203_ = f * s * 0.05f + this.xRotationalOffset;
        this.segmentOne.f_104203_ = f * -0.1f;
        this.segmentTwo.f_104203_ = f * 0.1f + this.xAngularOffset;
        this.segmentThree.f_104203_ = f * 0.075f + this.xAngularOffset;
        this.segmentFour.f_104203_ = f * 0.05f + this.xAngularOffset;
        this.segmentFive.f_104203_ = f * 0.1f + this.xAngularOffset;
        this.segmentSix.f_104203_ = f * 0.1f + this.xAngularOffset;
        this.segmentTwo.f_104204_ = this.yAngularOffset;
        this.segmentThree.f_104204_ = this.yAngularOffset;
        this.segmentFour.f_104204_ = this.yAngularOffset;
        this.segmentFive.f_104204_ = this.yAngularOffset;
        this.segmentSix.f_104204_ = this.yAngularOffset;
    }
}

