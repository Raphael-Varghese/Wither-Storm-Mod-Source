/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.CreeperModel
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.model.geom.EntityModelSet
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.EnergySwirlLayer
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.common.entity.SickenedCreeper;

public class SickenedCreeperRenderer
extends MobRenderer<SickenedCreeper, CreeperModel<SickenedCreeper>> {
    private static final ResourceLocation SICKENED_CREEPER_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_creeper.png");
    private static final ResourceLocation SICKENED_CREEPER_EMISSIVE_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_creeper_emissive.png");

    public SickenedCreeperRenderer(EntityRendererProvider.Context context) {
        super(context, (EntityModel)new CreeperModel(context.m_174023_(WitherStormModRenderers.SICKENED_CREEPER)), 0.5f);
        this.m_115326_((RenderLayer)new ChargeLayer((RenderLayerParent<SickenedCreeper, CreeperModel<SickenedCreeper>>)this, context.m_174027_()));
        this.m_115326_((RenderLayer)new EyesLayer<SickenedCreeper, CreeperModel<SickenedCreeper>>((RenderLayerParent)this){

            public RenderType m_5708_() {
                return RenderType.m_110488_((ResourceLocation)SICKENED_CREEPER_EMISSIVE_LOCATION);
            }
        });
    }

    protected void scale(SickenedCreeper entity, PoseStack stack, float partialTicks) {
        float f = entity.m_32320_(partialTicks);
        float f1 = 1.0f + Mth.m_14031_((float)(f * 100.0f)) * f * 0.01f;
        f *= f;
        f *= f;
        float f2 = (1.0f + f * 0.4f) * f1;
        float f3 = (1.0f + f * 0.1f) / f1;
        stack.m_85841_(f2, f3, f2);
    }

    protected float getWhiteOverlayProgress(SickenedCreeper entity, float partialTicks) {
        float f = entity.m_32320_(partialTicks);
        return (int)(f * 10.0f) % 2 == 0 ? 0.0f : Mth.m_14036_((float)f, (float)0.5f, (float)1.0f);
    }

    public ResourceLocation getTextureLocation(SickenedCreeper creeper) {
        return SICKENED_CREEPER_LOCATION;
    }

    protected boolean isShaking(SickenedCreeper entity) {
        return super.m_5936_((LivingEntity)entity) || entity.isConverting();
    }

    private static class ChargeLayer
    extends EnergySwirlLayer<SickenedCreeper, CreeperModel<SickenedCreeper>> {
        private static final ResourceLocation POWER_LOCATION = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
        private final CreeperModel<SickenedCreeper> model;

        public ChargeLayer(RenderLayerParent<SickenedCreeper, CreeperModel<SickenedCreeper>> renderer, EntityModelSet set) {
            super(renderer);
            this.model = new CreeperModel(set.m_171103_(WitherStormModRenderers.SICKENED_CREEPER_ARMOR));
        }

        protected float m_7631_(float p_225634_1_) {
            return p_225634_1_ * 0.01f;
        }

        protected ResourceLocation m_7029_() {
            return POWER_LOCATION;
        }

        protected EntityModel<SickenedCreeper> m_7193_() {
            return this.model;
        }
    }
}

