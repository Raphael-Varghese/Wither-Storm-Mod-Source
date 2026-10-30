/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.model.GoatModel
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
import net.minecraft.client.model.GoatModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.common.entity.SickenedGoat;

public class SickenedGoatRenderer
extends MobRenderer<SickenedGoat, GoatModel<SickenedGoat>> {
    private static final ResourceLocation GOAT_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_snow_golem.png");
    private static final ResourceLocation GOAT_EMISSIVE_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_snow_golem_emissive.png");

    public SickenedGoatRenderer(EntityRendererProvider.Context context) {
        super(context, (EntityModel)new GoatModel(context.m_174023_(WitherStormModRenderers.SICKENED_GOAT)), 0.5f);
        this.m_115326_((RenderLayer)new EyesLayer<SickenedGoat, GoatModel<SickenedGoat>>((RenderLayerParent)this){

            public RenderType m_5708_() {
                return RenderType.m_110488_((ResourceLocation)GOAT_EMISSIVE_LOCATION);
            }
        });
    }

    public ResourceLocation getTextureLocation(SickenedGoat entity) {
        return GOAT_LOCATION;
    }
}

