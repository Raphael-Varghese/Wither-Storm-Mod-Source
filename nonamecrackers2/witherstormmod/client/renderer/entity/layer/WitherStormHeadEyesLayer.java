/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import nonamecrackers2.witherstormmod.client.renderer.entity.WitherStormHeadRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.WitherStormHeadModel;
import nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity;

public class WitherStormHeadEyesLayer
extends EyesLayer<WitherStormHeadEntity, WitherStormHeadModel> {
    private static final RenderType EYES = RenderType.m_110488_((ResourceLocation)WitherStormHeadRenderer.EMISSIVE);
    private static final RenderType EYES_HURT = RenderType.m_110488_((ResourceLocation)WitherStormHeadRenderer.EMISSIVE_HURT);
    private static final RenderType ENTITY_CUTOUT = RenderType.m_110452_((ResourceLocation)WitherStormHeadRenderer.EMISSIVE);

    public WitherStormHeadEyesLayer(RenderLayerParent<WitherStormHeadEntity, WitherStormHeadModel> renderer) {
        super(renderer);
    }

    public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, WitherStormHeadEntity entity, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        VertexConsumer builder = null;
        builder = entity.isPlayingDead() ? buffer.m_6299_(ENTITY_CUTOUT) : (entity.isHurt() ? buffer.m_6299_(EYES_HURT) : buffer.m_6299_(EYES));
        ((WitherStormHeadModel)this.m_117386_()).m_7695_(stack, builder, 0xF00000, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    public RenderType m_5708_() {
        return EYES;
    }
}

