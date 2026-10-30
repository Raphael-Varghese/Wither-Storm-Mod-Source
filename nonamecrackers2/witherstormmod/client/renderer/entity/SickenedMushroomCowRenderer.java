/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.CowModel
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.client.renderer.entity.layer.SickenedMushroomCowMushroomLayer;
import nonamecrackers2.witherstormmod.common.entity.SickenedMushroomCow;

public class SickenedMushroomCowRenderer
extends MobRenderer<SickenedMushroomCow, CowModel<SickenedMushroomCow>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_mushroom_cow.png");
    private static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_mushroom_cow_emissive.png");

    public SickenedMushroomCowRenderer(EntityRendererProvider.Context context) {
        super(context, (EntityModel)new CowModel(context.m_174023_(WitherStormModRenderers.SICKENED_MUSHROOM_COW)), 0.7f);
        this.m_115326_((RenderLayer)new EyesLayer<SickenedMushroomCow, CowModel<SickenedMushroomCow>>((RenderLayerParent)this){

            public RenderType m_5708_() {
                return RenderType.m_110488_((ResourceLocation)EMISSIVE);
            }
        });
        this.m_115326_(new SickenedMushroomCowMushroomLayer(this, context.m_234597_()));
    }

    public ResourceLocation getTextureLocation(SickenedMushroomCow cow) {
        return TEXTURE;
    }
}

