/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.PartDefinition
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.mass;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class LowResDestroyerBodyModel {
    public static PartDefinition createBodyModel(PartDefinition root, float texScale) {
        PartDefinition body = root.m_171599_("lowResMass", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.0f, -6.0f, -3.0f, 5.0f, 18.0f, 6.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(0, 24).m_171496_(-3.0f, 12.0f, -3.0f, 5.0f, 3.0f, 6.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(0, 24).m_171496_(-2.0f, 16.0f, 0.0f, 1.0f, 5.0f, 1.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(0, 24).m_171496_(0.0f, 15.0f, 0.0f, 1.0f, 7.0f, 1.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(0, 24).m_171496_(-1.0f, 16.0f, -2.0f, 1.0f, 3.0f, 1.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(0, 24).m_171496_(-1.0f, 16.0f, -3.0f, 1.0f, 1.0f, 1.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(0, 24).m_171496_(-1.0f, 15.0f, -3.0f, 1.0f, 1.0f, 3.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(0, 24).m_171496_(-2.0f, 15.0f, -3.0f, 1.0f, 1.0f, 1.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(0, 24).m_171496_(-3.0f, 15.0f, 0.0f, 1.0f, 3.0f, 1.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(0, 24).m_171496_(-2.0f, 15.0f, 0.0f, 1.0f, 1.0f, 3.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(0, 24).m_171496_(1.0f, 15.0f, -2.0f, 1.0f, 1.0f, 2.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(0, 24).m_171496_(0.0f, 15.0f, -2.0f, 1.0f, 1.0f, 1.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(0, 24).m_171496_(1.0f, 16.0f, -2.0f, 1.0f, 1.0f, 1.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(0, 24).m_171496_(-1.0f, 15.0f, 2.0f, 1.0f, 3.0f, 1.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(0, 24).m_171496_(0.0f, 15.0f, 1.0f, 2.0f, 1.0f, 1.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171419_((float)0.0f, (float)-15.0f, (float)0.0f));
        body.m_171599_("part1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5f, -3.0f, -1.0f, 3.0f, 5.0f, 2.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)-0.5f, (float)4.0f, (float)-6.0f, (float)0.1309f, (float)0.0f, (float)0.0f));
        body.m_171599_("part2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5f, -14.0f, -2.0f, 5.0f, 14.0f, 6.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)-0.5f, (float)9.0f, (float)-1.0f, (float)0.0f, (float)0.0f, (float)0.3927f));
        body.m_171599_("part3", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-2.4965f, -8.2953f, -1.0f, 5.0f, 8.0f, 5.0f, new CubeDeformation(0.0f), texScale, texScale).m_171555_(false), PartPose.m_171423_((float)4.5f, (float)-2.0f, (float)-1.0f, (float)1.2519f, (float)-0.2368f, (float)0.8109f));
        body.m_171599_("part4", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.4965f, -4.2953f, -2.0f, 11.0f, 4.0f, 6.0f, new CubeDeformation(0.0f), texScale, texScale).m_171555_(false), PartPose.m_171423_((float)4.5f, (float)-2.0f, (float)-1.0f, (float)0.0f, (float)0.0f, (float)-1.1345f));
        body.m_171599_("part5", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-4.4965f, -7.2953f, -2.0f, 7.0f, 8.0f, 6.0f, new CubeDeformation(0.0f), texScale, texScale).m_171555_(false), PartPose.m_171423_((float)4.5f, (float)-2.0f, (float)-1.0f, (float)0.0f, (float)0.0f, (float)0.6981f));
        body.m_171599_("part6", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-2.0341f, -3.1877f, -4.5034f, 5.0f, 7.0f, 13.0f, new CubeDeformation(0.0f), texScale, texScale).m_171555_(false), PartPose.m_171423_((float)0.5f, (float)0.0f, (float)-3.0f, (float)1.9222f, (float)-0.318f, (float)0.8876f));
        body.m_171599_("part7", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.4217f, -10.2414f, -9.7983f, 6.0f, 7.0f, 13.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)-7.5f, (float)-5.0f, (float)-1.0f, (float)2.2689f, (float)0.0f, (float)-1.0036f));
        body.m_171599_("part8", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.4217f, -10.2414f, -5.7983f, 6.0f, 9.0f, 6.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)-7.5f, (float)-5.0f, (float)-1.0f, (float)1.4399f, (float)0.0f, (float)-1.0036f));
        body.m_171599_("part9", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035f, -8.2953f, -2.0f, 6.0f, 10.0f, 7.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)-4.5f, (float)-2.0f, (float)-1.0f, (float)1.0036f, (float)0.0f, (float)-1.0036f));
        body.m_171599_("part10", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5035f, -7.2953f, -2.0f, 5.0f, 9.0f, 6.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)-4.5f, (float)-2.0f, (float)-1.0f, (float)0.0f, (float)0.0f, (float)-0.5672f));
        body.m_171599_("part11", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171480_().m_171496_(-3.4965f, -6.2953f, -2.0f, 5.0f, 7.0f, 9.0f, new CubeDeformation(0.0f), texScale, texScale).m_171555_(false), PartPose.m_171423_((float)4.5f, (float)-2.0f, (float)-1.0f, (float)-1.0943f, (float)0.0603f, (float)0.8873f));
        body.m_171599_("part12", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035f, -6.2953f, -2.0f, 6.0f, 8.0f, 9.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)-4.5f, (float)-2.0f, (float)-1.0f, (float)-1.0908f, (float)0.0f, (float)-1.0036f));
        body.m_171599_("part13", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5035f, -7.2953f, -2.0f, 6.0f, 9.0f, 6.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)-4.5f, (float)-2.0f, (float)-1.0f, (float)0.0f, (float)0.0f, (float)-1.0036f));
        body.m_171599_("part14", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5f, -14.0f, -2.0f, 5.0f, 14.0f, 6.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)-0.5f, (float)9.0f, (float)-1.0f, (float)0.0f, (float)0.0f, (float)-0.4363f));
        body.m_171599_("part15", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-0.5f, -8.8659f, -2.4041f, 3.0f, 9.0f, 6.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)-2.5f, (float)6.5f, (float)-3.0f, (float)0.3295f, (float)0.1172f, (float)-0.3295f));
        body.m_171599_("part16", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5f, -14.0f, -3.0f, 5.0f, 13.0f, 6.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)-0.5f, (float)13.0f, (float)0.0f, (float)0.3491f, (float)0.0f, (float)0.0f));
        body.m_171599_("part17", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.5f, -2.0f, -2.0f, 5.0f, 2.0f, 5.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(0, 24).m_171496_(-2.5f, -21.0f, -2.0f, 5.0f, 19.0f, 5.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)-0.5f, (float)15.0f, (float)0.0f, (float)-0.2182f, (float)0.0f, (float)0.0f));
        return body;
    }
}

