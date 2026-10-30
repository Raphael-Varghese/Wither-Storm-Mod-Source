/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.client.renderer.entity.layer.SickenedIronGolemCrackinessLayer;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.sickenedentity.SickenedIronGolemModel;
import nonamecrackers2.witherstormmod.common.entity.SickenedIronGolem;

public class SickenedIronGolemRenderer
extends MobRenderer<SickenedIronGolem, SickenedIronGolemModel<SickenedIronGolem>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_iron_golem.png");
    public static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_iron_golem_emissive.png");

    public SickenedIronGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new SickenedIronGolemModel(context.m_174023_(WitherStormModRenderers.SICKENED_IRON_GOLEM)), 0.7f);
        this.m_115326_((RenderLayer)new EyesLayer<SickenedIronGolem, SickenedIronGolemModel<SickenedIronGolem>>((RenderLayerParent)this){

            public RenderType m_5708_() {
                return RenderType.m_110488_((ResourceLocation)EMISSIVE);
            }
        });
        this.m_115326_(new SickenedIronGolemCrackinessLayer((RenderLayerParent<SickenedIronGolem, SickenedIronGolemModel<SickenedIronGolem>>)this));
    }

    public ResourceLocation getTextureLocation(SickenedIronGolem golem) {
        return TEXTURE;
    }

    protected void setupRotations(SickenedIronGolem entity, PoseStack stack, float p_115016_, float p_115017_, float p_115018_) {
        super.m_7523_((LivingEntity)entity, stack, p_115016_, p_115017_, p_115018_);
        if (!((double)entity.f_267362_.m_267731_() < 0.01)) {
            float f1 = entity.f_267362_.m_267590_(p_115018_) + 6.0f;
            float f2 = (Math.abs(f1 % 13.0f - 6.5f) - 3.25f) / 3.25f;
            stack.m_252781_(Axis.f_252403_.m_252977_(6.5f * f2));
        }
    }
}

