/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.PartDefinition
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model.commandblock;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class RibModel {
    protected final ModelPart rib;
    protected final ModelPart segmentOne;
    protected final ModelPart segmentTwo;
    protected final ModelPart segmentThree;
    protected final ModelPart segmentFour;

    public RibModel(ModelPart root) {
        this.rib = root.m_171324_("rib");
        this.segmentOne = this.rib.m_171324_("segmentOne");
        this.segmentTwo = this.segmentOne.m_171324_("segmentTwo");
        this.segmentThree = this.segmentTwo.m_171324_("segmentThree");
        this.segmentFour = this.segmentThree.m_171324_("segmentFour");
    }

    public static void populateDefinition(PartDefinition root, PartPose offset, boolean mirror) {
        PartDefinition base = root.m_171599_("rib", CubeListBuilder.m_171558_(), offset);
        PartDefinition segmentOne = base.m_171599_("segmentOne", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171506_(-6.5f, -21.0f, -4.0f, 13.0f, 21.0f, 13.0f, mirror), PartPose.f_171404_);
        PartDefinition segmentTwo = segmentOne.m_171599_("segmentTwo", CubeListBuilder.m_171558_().m_171514_(0, 34).m_171506_(-5.5f, -29.0f, -4.0f, 11.0f, 29.0f, 10.0f, mirror), PartPose.m_171419_((float)0.0f, (float)-21.0f, (float)0.0f));
        PartDefinition segmentThree = segmentTwo.m_171599_("segmentThree", CubeListBuilder.m_171558_().m_171514_(0, 73).m_171506_(-4.5f, -29.0f, -3.0f, 9.0f, 29.0f, 8.0f, mirror), PartPose.m_171419_((float)0.0f, (float)-29.0f, (float)-1.0f));
        segmentThree.m_171599_("segmentFour", CubeListBuilder.m_171558_().m_171514_(52, 0).m_171506_(-3.5f, -32.0f, -3.0f, 7.0f, 32.0f, 6.0f, mirror), PartPose.m_171419_((float)0.0f, (float)-29.0f, (float)0.0f));
    }
}

