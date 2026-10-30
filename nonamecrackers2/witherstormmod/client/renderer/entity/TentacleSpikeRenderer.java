/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.TentacleSpikeModel;
import nonamecrackers2.witherstormmod.common.entity.TentacleSpike;

public class TentacleSpikeRenderer
extends EntityRenderer<TentacleSpike> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/tentacle_spike/tentacle_spike.png");
    private static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/tentacle_spike/tentacle_spike_emissive.png");
    private final TentacleSpikeModel<TentacleSpike> model;

    public TentacleSpikeRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new TentacleSpikeModel(context.m_174023_(WitherStormModRenderers.TENTACLE_SPIKE));
    }

    public void render(TentacleSpike fang, float p_114486_, float partialTicks, PoseStack stack, MultiBufferSource buffers, int packedLight) {
        float animProg = fang.getAnimationProgress(partialTicks);
        if (animProg != 0.0f) {
            float vertScale = 1.0f;
            float horzScale = 1.0f;
            if (animProg > 0.9f) {
                horzScale *= (1.0f - animProg) / 0.1f;
            }
            if (animProg > 0.9f) {
                vertScale *= (1.0f - animProg) / 0.1f;
            } else if (animProg < 0.08f) {
                vertScale *= animProg / 0.08f;
            }
            stack.m_85836_();
            stack.m_252781_(Axis.f_252436_.m_252977_(90.0f - fang.m_146908_()));
            stack.m_85841_(-horzScale, -vertScale, horzScale);
            this.model.setupAnim(fang, animProg, 0.0f, 0.0f, fang.m_146908_(), fang.m_146909_());
            VertexConsumer consumer = buffers.m_6299_(this.model.m_103119_(this.getTextureLocation(fang)));
            this.model.m_7695_(stack, consumer, packedLight, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
            VertexConsumer emissive = buffers.m_6299_(RenderType.m_110488_((ResourceLocation)EMISSIVE));
            this.model.m_7695_(stack, emissive, packedLight, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
            stack.m_85849_();
            super.m_7392_((Entity)fang, p_114486_, partialTicks, stack, buffers, packedLight);
        }
    }

    public ResourceLocation getTextureLocation(TentacleSpike fang) {
        return TEXTURE;
    }
}

