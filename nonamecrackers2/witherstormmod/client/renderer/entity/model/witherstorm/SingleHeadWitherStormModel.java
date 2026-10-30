/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.AbstractWitherStormModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.HeadModel;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public abstract class SingleHeadWitherStormModel<T extends WitherStormEntity>
extends AbstractWitherStormModel<T> {
    protected HeadModel<T> primaryHead;

    public SingleHeadWitherStormModel(ModelPart root, float scale) {
        super(root, scale);
    }

    protected static MeshDefinition createMesh(PartPose headPos) {
        MeshDefinition mesh = AbstractWitherStormModel.createMesh();
        PartDefinition root = mesh.m_171576_();
        PartDefinition heads = root.m_171597_("heads");
        HeadModel.populateDefinition(heads.m_171599_(HEADS[0], CubeListBuilder.m_171558_(), headPos));
        return mesh;
    }

    @Override
    protected void configureHeads(ModelPart root, float scale) {
        this.primaryHead = new HeadModel(root.m_171324_(HEADS[0]), scale);
        this.primaryHead.tractorBeamDistance = 90.0f;
        this.primaryHead.tractorBeamStartSize = 0.1f;
        this.primaryHead.tractorBeamEndSize = 5.0f;
        this.primaryHead.tractorBeamXOffset = 0.0f;
        this.primaryHead.tractorBeamYOffset = 34.0f;
        this.primaryHead.tractorBeamZOffset = 0.0f;
        this.primaryHead.pivotOffsetX = -19.0f;
        this.primaryHead.pivotOffsetY = 1.85f;
        this.heads.put(0, this.primaryHead);
    }
}

