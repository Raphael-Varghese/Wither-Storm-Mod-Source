/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.IllagerModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.IllagerRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.client.renderer.entity.layers.ItemInHandLayer
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.common.entity.SickenedVindicator;
import org.jetbrains.annotations.NotNull;

public class SickenedVindicatorRenderer
extends IllagerRenderer<SickenedVindicator> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_vindicator.png");
    public static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_vindicator_emissive.png");

    public SickenedVindicatorRenderer(EntityRendererProvider.Context context) {
        super(context, new IllagerModel(context.m_174023_(WitherStormModRenderers.SICKENED_VINDICATOR)), 0.5f);
        this.m_115326_((RenderLayer)new ItemInHandLayer<SickenedVindicator, IllagerModel<SickenedVindicator>>((RenderLayerParent)this, context.m_234598_()){

            public void render(@NotNull PoseStack stack, @NotNull MultiBufferSource source, int i, @NotNull SickenedVindicator sickenedVindicator, float f1, float f2, float f3, float f4, float f5, float f6) {
                if (sickenedVindicator.m_5912_()) {
                    super.m_6494_(stack, source, i, (LivingEntity)sickenedVindicator, f1, f2, f3, f4, f5, f6);
                }
            }
        });
        this.m_115326_((RenderLayer)new EyesLayer<SickenedVindicator, IllagerModel<SickenedVindicator>>((RenderLayerParent)this){

            @NotNull
            public RenderType m_5708_() {
                return RenderType.m_110488_((ResourceLocation)EMISSIVE);
            }
        });
    }

    @NotNull
    public ResourceLocation getTextureLocation(@NotNull SickenedVindicator sickenedVindicator) {
        return TEXTURE;
    }

    protected boolean isShaking(SickenedVindicator entity) {
        return super.m_5936_((LivingEntity)entity) || entity.isConverting();
    }
}

