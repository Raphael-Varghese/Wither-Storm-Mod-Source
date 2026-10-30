/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.model.PigModel
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.common.entity.SickenedPig;

public class SickenedPigRenderer
extends MobRenderer<SickenedPig, PigModel<SickenedPig>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_pig.png");
    private static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_pig_emissive.png");
    private static final ResourceLocation EGG_TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/misc/sickened_reuben.png");

    public SickenedPigRenderer(EntityRendererProvider.Context context) {
        super(context, (EntityModel)new PigModel(context.m_174023_(WitherStormModRenderers.SICKENED_PIG)), 0.7f);
        this.m_115326_((RenderLayer)new EyesLayer<SickenedPig, PigModel<SickenedPig>>((RenderLayerParent)this){

            public RenderType m_5708_() {
                return RenderType.m_110488_((ResourceLocation)EMISSIVE);
            }
        });
    }

    public ResourceLocation getTextureLocation(SickenedPig pig) {
        if (pig.m_8077_() && pig.m_7755_().getString().equals("reuben")) {
            return EGG_TEXTURE;
        }
        return TEXTURE;
    }
}

