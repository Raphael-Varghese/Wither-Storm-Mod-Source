/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.WitheredSymbiontModel;
import nonamecrackers2.witherstormmod.client.rendertype.UtilRenderTypes;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;

public class WitheredSymbiontTearLayer
extends RenderLayer<WitheredSymbiontEntity, WitheredSymbiontModel<WitheredSymbiontEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/withered_symbiont/withered_symbiont_tear.png");

    public WitheredSymbiontTearLayer(RenderLayerParent<WitheredSymbiontEntity, WitheredSymbiontModel<WitheredSymbiontEntity>> parent) {
        super(parent);
    }

    public void render(PoseStack stack, MultiBufferSource buffer, int p_117351_, WitheredSymbiontEntity entity, float p_117353_, float p_117354_, float partialTicks, float p_117356_, float p_117357_, float p_117358_) {
        VertexConsumer consumer = buffer.m_6299_(UtilRenderTypes.emissiveTranslucent(TEXTURE));
        ((WitheredSymbiontModel)this.m_117386_()).m_7695_(stack, consumer, 0xF00000, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, entity.getTearAlpha(partialTicks));
    }
}

