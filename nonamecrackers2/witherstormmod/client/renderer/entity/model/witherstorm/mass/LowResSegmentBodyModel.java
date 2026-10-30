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

public class LowResSegmentBodyModel {
    public static PartDefinition createBodyModel(PartDefinition root, float texScale) {
        PartDefinition body = root.m_171599_("lowResMass", CubeListBuilder.m_171558_().m_171514_(17, 40).m_171496_(-6.0f, -0.5f, -10.5f, 3.0f, 2.0f, 1.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(17, 40).m_171496_(-3.0f, -0.5f, -10.5f, 2.0f, 1.0f, 1.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(17, 40).m_171496_(0.0f, -0.5f, -10.5f, 3.0f, 1.0f, 1.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(17, 40).m_171496_(-3.0f, 0.5f, -10.5f, 5.0f, 2.0f, 1.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171419_((float)2.0f, (float)-4.0f, (float)0.0f));
        body.m_171599_("part1", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-3.0f, -10.0f, -6.0f, 7.0f, 9.0f, 7.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)0.0f, (float)2.5f, (float)-0.5f, (float)0.0f, (float)0.2618f, (float)0.0436f));
        body.m_171599_("part2", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-7.0f, -4.3f, -9.0f, 12.0f, 5.0f, 2.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)0.0f, (float)2.5f, (float)-0.5f, (float)0.0f, (float)0.0f, (float)-0.0436f));
        body.m_171599_("part3", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-7.0f, -7.0f, -7.0f, 14.0f, 9.0f, 7.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)0.0f, (float)2.5f, (float)-0.5f, (float)0.0f, (float)0.0f, (float)-0.2182f));
        body.m_171599_("part4", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-2.1745f, -3.0038f, 4.0f, 5.0f, 7.0f, 6.0f, new CubeDeformation(0.0f), texScale, texScale).m_171514_(0, 24).m_171496_(-2.1745f, -6.0038f, -1.0f, 6.0f, 7.0f, 7.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)0.0f, (float)0.5f, (float)-0.5f, (float)0.0f, (float)0.2618f, (float)0.1309f));
        body.m_171599_("part5", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-10.0f, -5.0f, -3.2f, 5.0f, 6.0f, 7.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)0.0f, (float)4.5f, (float)-0.5f, (float)-2.4771f, (float)1.2923f, (float)-2.5016f));
        body.m_171599_("part6", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-10.0f, -5.0f, -5.0f, 11.0f, 6.0f, 7.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)0.0f, (float)4.5f, (float)-0.5f, (float)-0.2618f, (float)0.0f, (float)-0.0436f));
        body.m_171599_("part7", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-4.0f, -5.0f, 0.0f, 11.0f, 7.0f, 7.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)0.0f, (float)4.5f, (float)-0.5f, (float)0.0f, (float)0.0f, (float)-0.0436f));
        body.m_171599_("part8", CubeListBuilder.m_171558_().m_171514_(0, 24).m_171496_(-1.5f, -9.0f, -0.5f, 3.0f, 8.0f, 3.0f, new CubeDeformation(0.0f), texScale, texScale), PartPose.m_171423_((float)2.5f, (float)11.5f, (float)10.0f, (float)0.7854f, (float)0.0f, (float)0.0f));
        return body;
    }
}

