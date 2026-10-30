/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model.commandblock;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.List;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.commandblock.RibModel;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.util.StructureAnimationHelper;

public class RibcageModel
extends EntityModel<CommandBlockEntity> {
    private final Int2ObjectMap<RibModel> ribs;
    private final RibModel rib;
    private final RibModel rib2;
    private final RibModel rib3;
    private final RibModel rib4;
    private final RibModel rib5;
    private final RibModel rib6;

    public RibcageModel(ModelPart root) {
        Int2ObjectOpenHashMap ribs = new Int2ObjectOpenHashMap();
        this.rib = new RibModel(root.m_171324_("rib"));
        this.rib2 = new RibModel(root.m_171324_("rib2"));
        this.rib3 = new RibModel(root.m_171324_("rib3"));
        this.rib4 = new RibModel(root.m_171324_("rib4"));
        this.rib5 = new RibModel(root.m_171324_("rib5"));
        this.rib6 = new RibModel(root.m_171324_("rib6"));
        ribs.put(0, (Object)this.rib);
        ribs.put(1, (Object)this.rib2);
        ribs.put(2, (Object)this.rib3);
        ribs.put(3, (Object)this.rib4);
        ribs.put(4, (Object)this.rib5);
        ribs.put(5, (Object)this.rib6);
        this.ribs = ribs;
    }

    public static LayerDefinition createLayerDefinition() {
        MeshDefinition definition = new MeshDefinition();
        PartDefinition root = definition.m_171576_();
        RibModel.populateDefinition(root.m_171599_("rib", CubeListBuilder.m_171558_(), PartPose.f_171404_), PartPose.m_171419_((float)0.0f, (float)24.0f, (float)68.0f), false);
        RibModel.populateDefinition(root.m_171599_("rib2", CubeListBuilder.m_171558_(), PartPose.f_171404_), PartPose.m_171419_((float)0.0f, (float)24.0f, (float)-68.0f), false);
        RibModel.populateDefinition(root.m_171599_("rib3", CubeListBuilder.m_171558_(), PartPose.f_171404_), PartPose.m_171419_((float)42.0f, (float)24.0f, (float)-57.0f), false);
        RibModel.populateDefinition(root.m_171599_("rib4", CubeListBuilder.m_171558_(), PartPose.f_171404_), PartPose.m_171419_((float)42.0f, (float)24.0f, (float)57.0f), false);
        RibModel.populateDefinition(root.m_171599_("rib5", CubeListBuilder.m_171558_(), PartPose.f_171404_), PartPose.m_171419_((float)-42.0f, (float)24.0f, (float)57.0f), true);
        RibModel.populateDefinition(root.m_171599_("rib6", CubeListBuilder.m_171558_(), PartPose.f_171404_), PartPose.m_171419_((float)-42.0f, (float)24.0f, (float)-57.0f), true);
        return LayerDefinition.m_171565_((MeshDefinition)definition, (int)128, (int)128);
    }

    public void m_7695_(PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        stack.m_85836_();
        stack.m_85837_(0.0, -1.5, 0.0);
        for (Int2ObjectMap.Entry entry : this.ribs.int2ObjectEntrySet()) {
            ((RibModel)entry.getValue()).rib.m_104301_(stack, buffer, packedLight, packedOverlay);
        }
        stack.m_85849_();
    }

    public void setupAnim(CommandBlockEntity entity, float animation, float partialTicks, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        for (Int2ObjectMap.Entry entry : this.ribs.int2ObjectEntrySet()) {
            List<StructureAnimationHelper> structure;
            RibModel model = (RibModel)entry.getValue();
            int key = entry.getIntKey();
            if (key >= (structure = entity.getRibStructure()).size()) continue;
            StructureAnimationHelper helper = structure.get(key);
            model.rib.f_104203_ = (float)Math.toRadians(helper.getBaseXRot(partialTicks));
            model.rib.f_104204_ = (float)Math.toRadians(helper.getBaseYRot(partialTicks));
            model.segmentOne.f_104203_ = (float)Math.toRadians(helper.getXRot(partialTicks) * 0.4f);
            model.segmentOne.f_104204_ = (float)Math.toRadians(helper.getYRot(partialTicks) * 0.4f);
            model.segmentTwo.f_104203_ = (float)Math.toRadians(helper.getXRot(partialTicks) * 0.4f);
            model.segmentTwo.f_104204_ = (float)Math.toRadians(helper.getYRot(partialTicks) * 0.4f);
            model.segmentThree.f_104203_ = (float)Math.toRadians(helper.getXRot(partialTicks) * 0.8f);
            model.segmentThree.f_104204_ = (float)Math.toRadians(helper.getYRot(partialTicks) * 0.8f);
            model.segmentFour.f_104203_ = (float)Math.toRadians(helper.getXRot(partialTicks));
            model.segmentFour.f_104204_ = (float)Math.toRadians(helper.getYRot(partialTicks));
        }
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.f_104203_ = x;
        modelRenderer.f_104204_ = y;
        modelRenderer.f_104205_ = z;
    }
}

