/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.BeeModel
 *  net.minecraft.client.model.EntityModel
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

import net.minecraft.client.model.BeeModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.common.entity.SickenedBee;

public class SickenedBeeRenderer
extends MobRenderer<SickenedBee, BeeModel<SickenedBee>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_bee.png");
    private static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_bee_emissive.png");

    public SickenedBeeRenderer(EntityRendererProvider.Context context) {
        super(context, (EntityModel)new BeeModel(context.m_174023_(WitherStormModRenderers.SICKENED_BEE)), 0.4f);
        this.m_115326_((RenderLayer)new EyesLayer<SickenedBee, BeeModel<SickenedBee>>((RenderLayerParent)this){

            public RenderType m_5708_() {
                return RenderType.m_110488_((ResourceLocation)EMISSIVE);
            }
        });
    }

    public ResourceLocation getTextureLocation(SickenedBee bee) {
        return TEXTURE;
    }

    protected boolean isShaking(SickenedBee entity) {
        return super.m_5936_((LivingEntity)entity) || entity.isConverting();
    }
}

