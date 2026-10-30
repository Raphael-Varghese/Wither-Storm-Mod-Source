/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.model.SnowGolemModel
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
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.client.renderer.entity.layer.SickenedSnowGolemHeadLayer;
import nonamecrackers2.witherstormmod.common.entity.SickenedSnowGolem;

public class SickenedSnowGolemRenderer
extends MobRenderer<SickenedSnowGolem, SnowGolemModel<SickenedSnowGolem>> {
    private static final ResourceLocation SNOW_GOLEM_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_snow_golem.png");
    private static final ResourceLocation SNOW_GOLEM_EMISSIVE_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_snow_golem_emissive.png");

    public SickenedSnowGolemRenderer(EntityRendererProvider.Context context) {
        super(context, (EntityModel)new SnowGolemModel(context.m_174023_(WitherStormModRenderers.SICKENED_SNOW_GOLEM)), 0.5f);
        this.m_115326_(new SickenedSnowGolemHeadLayer((RenderLayerParent<SickenedSnowGolem, SnowGolemModel<SickenedSnowGolem>>)this, context.m_234597_(), context.m_174025_()));
        this.m_115326_((RenderLayer)new EyesLayer<SickenedSnowGolem, SnowGolemModel<SickenedSnowGolem>>((RenderLayerParent)this){

            public RenderType m_5708_() {
                return RenderType.m_110488_((ResourceLocation)SNOW_GOLEM_EMISSIVE_LOCATION);
            }
        });
    }

    public ResourceLocation getTextureLocation(SickenedSnowGolem entity) {
        return SNOW_GOLEM_LOCATION;
    }
}

