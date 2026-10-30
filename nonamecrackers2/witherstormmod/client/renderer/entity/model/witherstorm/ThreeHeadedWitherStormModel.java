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

public abstract class ThreeHeadedWitherStormModel<T extends WitherStormEntity>
extends AbstractWitherStormModel<T> {
    protected HeadModel<T> rightHead;
    protected HeadModel<T> middleHead;
    protected HeadModel<T> leftHead;

    public ThreeHeadedWitherStormModel(ModelPart root, float headScale) {
        super(root, headScale);
    }

    protected static MeshDefinition createMesh(PartPose[] headPositions) {
        MeshDefinition mesh = AbstractWitherStormModel.createMesh();
        PartDefinition root = mesh.m_171576_();
        PartDefinition heads = root.m_171597_("heads");
        HeadModel.populateDefinition(heads.m_171599_(HEADS[0], CubeListBuilder.m_171558_(), headPositions[0]));
        HeadModel.populateDefinition(heads.m_171599_(HEADS[1], CubeListBuilder.m_171558_(), headPositions[1]));
        HeadModel.populateDefinition(heads.m_171599_(HEADS[2], CubeListBuilder.m_171558_(), headPositions[2]));
        return mesh;
    }

    @Override
    protected void configureHeads(ModelPart root, float scale) {
        this.rightHead = new HeadModel(root.m_171324_(HEADS[0]), scale);
        this.middleHead = new HeadModel(root.m_171324_(HEADS[1]), scale);
        this.leftHead = new HeadModel(root.m_171324_(HEADS[2]), scale);
        this.heads.put(2, this.rightHead);
        this.heads.put(0, this.middleHead);
        this.heads.put(1, this.leftHead);
    }
}

