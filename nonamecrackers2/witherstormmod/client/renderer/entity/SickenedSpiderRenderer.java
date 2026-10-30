/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.SpiderModel
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.SpiderRenderer
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.entity.layers.SpiderEyesLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.common.entity.SickenedSpider;

public class SickenedSpiderRenderer
extends SpiderRenderer<SickenedSpider> {
    private static final ResourceLocation SICKENED_SPIDER_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_spider.png");
    private static final ResourceLocation SICKENED_SPIDER_EMISSIVE_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_spider_emissive.png");

    public SickenedSpiderRenderer(EntityRendererProvider.Context context) {
        super(context, WitherStormModRenderers.SICKENED_SPIDER);
        this.f_115291_.removeIf(l -> l instanceof SpiderEyesLayer);
        this.m_115326_((RenderLayer)new EyesLayer<SickenedSpider, SpiderModel<SickenedSpider>>((RenderLayerParent)this){

            public RenderType m_5708_() {
                return RenderType.m_110488_((ResourceLocation)SICKENED_SPIDER_EMISSIVE_LOCATION);
            }
        });
    }

    protected void scale(SickenedSpider entity, PoseStack stack, float p_225620_3_) {
        stack.m_85841_(1.2f, 1.2f, 1.2f);
    }

    protected boolean isShaking(SickenedSpider entity) {
        return super.m_5936_((LivingEntity)entity) || entity.isConverting();
    }

    public ResourceLocation getTextureLocation(SickenedSpider entity) {
        return SICKENED_SPIDER_LOCATION;
    }
}

