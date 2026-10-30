/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.model.PhantomModel
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
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PhantomModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.common.entity.SickenedPhantom;

public class SickenedPhantomRenderer
extends MobRenderer<SickenedPhantom, PhantomModel<SickenedPhantom>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_phantom.png");
    private static final ResourceLocation TEXTURE_EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_phantom_emissive.png");

    public SickenedPhantomRenderer(EntityRendererProvider.Context context) {
        super(context, (EntityModel)new PhantomModel(context.m_174023_(WitherStormModRenderers.SICKENED_PHANTOM)), 0.75f);
        this.m_115326_((RenderLayer)new EyesLayer<SickenedPhantom, PhantomModel<SickenedPhantom>>((RenderLayerParent)this){

            public RenderType m_5708_() {
                return RenderType.m_110488_((ResourceLocation)TEXTURE_EMISSIVE);
            }
        });
    }

    public ResourceLocation getTextureLocation(SickenedPhantom phantom) {
        return TEXTURE;
    }

    protected void scale(SickenedPhantom phantom, PoseStack stack, float partialTicks) {
        int i = phantom.m_33172_();
        float f = 1.0f + 0.15f * (float)i;
        stack.m_85841_(f, f, f);
        stack.m_252880_(0.0f, 1.3125f, 0.1875f);
    }

    protected void setupRotations(SickenedPhantom phantom, PoseStack stack, float p_115687_, float p_115688_, float p_115689_) {
        super.m_7523_((LivingEntity)phantom, stack, p_115687_, p_115688_, p_115689_);
        stack.m_252781_(Axis.f_252529_.m_252977_(phantom.m_146909_()));
    }
}

