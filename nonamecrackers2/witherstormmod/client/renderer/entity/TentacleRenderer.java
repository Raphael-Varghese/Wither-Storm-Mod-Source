/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.TentacleModel;
import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;

public class TentacleRenderer
extends EntityRenderer<TentacleEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/tentacle/tentacle.png");
    private final TentacleModel model;

    public TentacleRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new TentacleModel(context.m_174023_(WitherStormModRenderers.TENTACLE));
        this.f_114477_ = 0.5f;
    }

    public void render(TentacleEntity entity, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
        super.m_7392_((Entity)entity, p_225623_2_, partialTicks, stack, buffer, packedLight);
        stack.m_85836_();
        stack.m_85841_(-1.0f, -1.0f, 1.0f);
        stack.m_85841_(2.0f, 2.0f, 2.0f);
        VertexConsumer builder = buffer.m_6299_(this.model.m_103119_(this.getTextureLocation(entity)));
        this.model.setupAnim(entity, partialTicks, 0.0f, 0.0f, entity.m_146908_(), entity.m_146909_());
        int i = LivingEntityRenderer.m_115338_((LivingEntity)entity, (float)0.0f);
        this.model.m_7695_(stack, builder, packedLight, i, 1.0f, 1.0f, 1.0f, 1.0f);
        stack.m_85849_();
    }

    public ResourceLocation getTextureLocation(TentacleEntity entity) {
        return TEXTURE;
    }
}

