/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.FlamingWitherSkullModel;
import nonamecrackers2.witherstormmod.client.rendertype.UtilRenderTypes;
import nonamecrackers2.witherstormmod.common.entity.FlamingWitherSkullEntity;

public class FlamingWitherSkullRenderer<T extends FlamingWitherSkullEntity>
extends EntityRenderer<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/flaming_wither_skull/flaming_wither_skull.png");
    private static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/flaming_wither_skull/flaming_wither_skull_emissive.png");
    private final FlamingWitherSkullModel model;

    public FlamingWitherSkullRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new FlamingWitherSkullModel(context.m_174023_(WitherStormModRenderers.FLAMING_WITHER_SKULL));
    }

    protected int getBlockLightLevel(FlamingWitherSkullEntity p_225624_1_, BlockPos p_225624_2_) {
        return 15;
    }

    public void render(T entity, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
        stack.m_85836_();
        stack.m_85841_(-1.0f, -1.0f, 1.0f);
        stack.m_85841_(1.8f, 1.8f, 1.8f);
        float f = Mth.m_14189_((float)partialTicks, (float)((FlamingWitherSkullEntity)((Object)entity)).f_19859_, (float)entity.m_146908_());
        float f1 = Mth.m_14179_((float)partialTicks, (float)((FlamingWitherSkullEntity)((Object)entity)).f_19860_, (float)entity.m_146909_());
        VertexConsumer builder = buffer.m_6299_(this.model.m_103119_(this.getTextureLocation(entity)));
        this.model.m_6251_(partialTicks, f, f1);
        this.model.m_7695_(stack, builder, packedLight, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        VertexConsumer emissive = buffer.m_6299_(UtilRenderTypes.emissiveNoCull(this.getEmissiveTextureLocation(entity)));
        this.model.m_7695_(stack, emissive, packedLight, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        stack.m_85849_();
        super.m_7392_(entity, p_225623_2_, partialTicks, stack, buffer, packedLight);
    }

    public ResourceLocation getTextureLocation(T entity) {
        return TEXTURE;
    }

    public ResourceLocation getEmissiveTextureLocation(T entity) {
        return EMISSIVE;
    }
}

