/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.animal.IronGolem$Crackiness
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.layer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.Map;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.sickenedentity.SickenedIronGolemModel;
import nonamecrackers2.witherstormmod.common.entity.SickenedIronGolem;

public class SickenedIronGolemCrackinessLayer
extends RenderLayer<SickenedIronGolem, SickenedIronGolemModel<SickenedIronGolem>> {
    private static final Map<IronGolem.Crackiness, ResourceLocation> LAYERS = ImmutableMap.of((Object)IronGolem.Crackiness.LOW, (Object)new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_iron_golem_crackiness_low.png"), (Object)IronGolem.Crackiness.MEDIUM, (Object)new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_iron_golem_crackiness_medium.png"), (Object)IronGolem.Crackiness.HIGH, (Object)new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_iron_golem_crackiness_high.png"));

    public SickenedIronGolemCrackinessLayer(RenderLayerParent<SickenedIronGolem, SickenedIronGolemModel<SickenedIronGolem>> parent) {
        super(parent);
    }

    public void render(PoseStack stack, MultiBufferSource buffer, int p_117351_, SickenedIronGolem entity, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
        IronGolem.Crackiness irongolem$crackiness;
        if (!entity.m_20145_() && (irongolem$crackiness = entity.getCrackiness()) != IronGolem.Crackiness.NONE) {
            ResourceLocation resourcelocation = LAYERS.get(irongolem$crackiness);
            SickenedIronGolemCrackinessLayer.m_117376_((EntityModel)this.m_117386_(), (ResourceLocation)resourcelocation, (PoseStack)stack, (MultiBufferSource)buffer, (int)p_117351_, (LivingEntity)entity, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }
}

