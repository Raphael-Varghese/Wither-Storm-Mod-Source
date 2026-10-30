/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ChickenModel
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.common.entity.SickenedChicken;

public class SickenedChickenRenderer
extends MobRenderer<SickenedChicken, ChickenModel<SickenedChicken>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_chicken.png");
    public static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_chicken_emissive.png");

    public SickenedChickenRenderer(EntityRendererProvider.Context context) {
        super(context, (EntityModel)new ChickenModel(context.m_174023_(WitherStormModRenderers.SICKENED_CHICKEN)), 0.3f);
        this.m_115326_((RenderLayer)new EyesLayer<SickenedChicken, ChickenModel<SickenedChicken>>((RenderLayerParent)this){

            public RenderType m_5708_() {
                return RenderType.m_110488_((ResourceLocation)EMISSIVE);
            }
        });
    }

    public ResourceLocation getTextureLocation(SickenedChicken chicken) {
        return TEXTURE;
    }

    protected float getBob(SickenedChicken chicken, float partialTicks) {
        float f = Mth.m_14179_((float)partialTicks, (float)chicken.f_28229_, (float)chicken.f_28226_);
        float f1 = Mth.m_14179_((float)partialTicks, (float)chicken.f_28228_, (float)chicken.f_28227_);
        return (Mth.m_14031_((float)f) + 1.0f) * f1;
    }
}

