/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemBlockRenderTypes
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.inventory.InventoryMenu
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraftforge.client.model.data.ModelData
 *  org.joml.Matrix4f
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import org.joml.Matrix4f;

public class FormidibombRenderer
extends EntityRenderer<FormidibombEntity> {
    public FormidibombRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.f_114477_ = 0.5f;
    }

    public void render(FormidibombEntity entity, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int p_225623_6_) {
        float ticks;
        stack.m_85836_();
        stack.m_85837_(0.0, 0.5, 0.0);
        if ((float)entity.getFuseLife() - partialTicks + 1.0f < 20.0f) {
            float f = 1.0f - ((float)entity.getFuseLife() - partialTicks + 1.0f) / 20.0f;
            f = Mth.m_14036_((float)f, (float)0.0f, (float)1.0f);
            f *= f;
            f *= f;
            float f1 = 1.0f + f * 20.0f;
            stack.m_85841_(f1, f1, f1);
        }
        if ((float)entity.getFuseLife() - partialTicks < 240.0f) {
            ticks = (240.0f - (float)entity.getFuseLife() + partialTicks) / 150.0f;
            Random random = new Random(289L);
            VertexConsumer builder = buffer.m_6299_(RenderType.m_110502_());
            stack.m_85836_();
            int i = 0;
            while ((float)i < (ticks + ticks * ticks) / 2.0f * 10.0f) {
                stack.m_252781_(Axis.f_252529_.m_252977_(random.nextFloat() * 360.0f));
                stack.m_252781_(Axis.f_252436_.m_252977_(random.nextFloat() * 360.0f));
                stack.m_252781_(Axis.f_252403_.m_252977_(random.nextFloat() * 360.0f));
                stack.m_252781_(Axis.f_252529_.m_252977_(random.nextFloat() * 360.0f));
                stack.m_252781_(Axis.f_252436_.m_252977_(random.nextFloat() * 360.0f));
                stack.m_252781_(Axis.f_252403_.m_252977_(random.nextFloat() * 360.0f + ticks * 90.0f));
                float f3 = random.nextFloat() * (float)entity.m_20191_().m_82309_() * 0.2f + ticks * (float)Math.max(1.0, (double)((40.0f - (float)entity.getFuseLife() + partialTicks) / 6.0f));
                float f4 = random.nextFloat() * 0.025f + ticks;
                Matrix4f matrix4f = stack.m_85850_().m_252922_();
                int k = 255;
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_6122_(255, 255, 255, k).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_6122_(255, 255, 255, k).m_5752_();
                builder.m_252986_(matrix4f, -((float)(Math.sqrt(3.0) / 2.0)) * f4, f3, -0.5f * f4).m_6122_(255, 0, 255, 0).m_5752_();
                builder.m_252986_(matrix4f, (float)(Math.sqrt(3.0) / 2.0) * f4, f3, -0.5f * f4).m_6122_(255, 0, 255, 0).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_6122_(255, 255, 255, k).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_6122_(255, 255, 255, k).m_5752_();
                builder.m_252986_(matrix4f, (float)(Math.sqrt(3.0) / 2.0) * f4, f3, -0.5f * f4).m_6122_(255, 0, 255, 0).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, f3, 1.0f * f4).m_6122_(255, 0, 255, 0).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_6122_(255, 255, 255, k).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_6122_(255, 255, 255, k).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, f3, 1.0f * f4).m_6122_(255, 0, 255, 0).m_5752_();
                builder.m_252986_(matrix4f, -((float)(Math.sqrt(3.0) / 2.0)) * f4, f3, -0.5f * f4).m_6122_(255, 0, 255, 0).m_5752_();
                ++i;
            }
            stack.m_85849_();
        }
        stack.m_252781_(Axis.f_252436_.m_252977_(-90.0f));
        stack.m_85837_(-0.5, -0.5, 0.5);
        stack.m_252781_(Axis.f_252436_.m_252977_(90.0f));
        ticks = (float)entity.getAirTime() + partialTicks;
        if (!entity.m_20096_()) {
            stack.m_85837_(0.5, 0.5, 0.5);
            stack.m_252781_(Axis.f_252436_.m_252977_(ticks));
            stack.m_252781_(Axis.f_252529_.m_252977_(ticks * 0.5f));
            stack.m_85837_(-0.5, -0.5, -0.5);
        }
        BlockState state = entity.getBlockState();
        int fuse = entity.getStartFuse() > 0 && entity.getFuseLife() > 0 ? entity.getStartFuse() / entity.getFuseLife() : 0;
        FormidibombRenderer.renderWhiteSolidBlock(state, stack, buffer, p_225623_6_, fuse % 2 == 0);
        stack.m_85849_();
        super.m_7392_((Entity)entity, p_225623_2_, partialTicks, stack, buffer, p_225623_6_);
    }

    private static void renderWhiteSolidBlock(BlockState state, PoseStack stack, MultiBufferSource buffer, int packedLight, boolean flash) {
        int i = flash ? OverlayTexture.m_118093_((int)OverlayTexture.m_118088_((float)1.0f), (int)10) : OverlayTexture.f_118083_;
        Minecraft mc = Minecraft.m_91087_();
        mc.m_91289_().renderSingleBlock(state, stack, buffer, packedLight, i, ModelData.EMPTY, ItemBlockRenderTypes.m_109284_((BlockState)state, (boolean)false));
    }

    public ResourceLocation getTextureLocation(FormidibombEntity entity) {
        return InventoryMenu.f_39692_;
    }
}

