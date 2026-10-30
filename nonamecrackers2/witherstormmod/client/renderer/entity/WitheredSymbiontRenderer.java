/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer
 *  net.minecraft.client.renderer.entity.layers.ItemInHandLayer
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.client.renderer.entity.layer.WitheredSymbiontEyesLayer;
import nonamecrackers2.witherstormmod.client.renderer.entity.layer.WitheredSymbiontTearLayer;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.WitheredSymbiontModel;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;

public class WitheredSymbiontRenderer
extends MobRenderer<WitheredSymbiontEntity, WitheredSymbiontModel<WitheredSymbiontEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/withered_symbiont/withered_symbiont.png");
    private static final ResourceLocation EGG_TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/misc/crackers.png");

    public WitheredSymbiontRenderer(EntityRendererProvider.Context context) {
        super(context, new WitheredSymbiontModel(context.m_174023_(WitherStormModRenderers.WITHERED_SYMBIONT)), 0.8f);
        this.m_115326_((RenderLayer)new WitheredSymbiontEyesLayer((RenderLayerParent<WitheredSymbiontEntity, WitheredSymbiontModel<WitheredSymbiontEntity>>)this));
        this.m_115326_(new WitheredSymbiontTearLayer((RenderLayerParent<WitheredSymbiontEntity, WitheredSymbiontModel<WitheredSymbiontEntity>>)this));
        this.m_115326_((RenderLayer)new ItemInHandLayer((RenderLayerParent)this, context.m_234598_()));
        this.m_115326_((RenderLayer)new HumanoidArmorLayer((RenderLayerParent)this, new HumanoidModel(context.m_174023_(WitherStormModRenderers.SYMBIONT_INNER_ARMOR)), new HumanoidModel(context.m_174023_(WitherStormModRenderers.SYMBIONT_OUTER_ARMOR)), context.m_266367_()));
    }

    public ResourceLocation getTextureLocation(WitheredSymbiontEntity entity) {
        if (entity.m_8077_() && entity.m_7755_().getString().equals("nonamecrackers2")) {
            return EGG_TEXTURE;
        }
        return TEXTURE;
    }

    protected void setupRotations(WitheredSymbiontEntity entity, PoseStack stack, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        super.m_7523_((LivingEntity)entity, stack, p_225621_3_, p_225621_4_, p_225621_5_);
        if (!((double)entity.f_267362_.m_267731_() < 0.01)) {
            float f1 = entity.f_267362_.m_267590_(p_225621_5_) + 6.0f;
            float f2 = (Math.abs(f1 % 13.0f - 6.5f) - 3.25f) / 3.25f;
            stack.m_252781_(Axis.f_252403_.m_252977_(6.5f * f2));
        }
    }

    protected void scale(WitheredSymbiontEntity entity, PoseStack stack, float partialTicks) {
        stack.m_85841_(1.8f, 1.8f, 1.8f);
    }
}

