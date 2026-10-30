/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemBlockRenderTypes
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.inventory.InventoryMenu
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraftforge.client.model.data.ModelData
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import nonamecrackers2.witherstormmod.common.entity.SuperTNTEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;

public class SuperTNTRenderer
extends EntityRenderer<SuperTNTEntity> {
    public SuperTNTRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.f_114477_ = 0.5f;
    }

    public void render(SuperTNTEntity entity, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int p_225623_6_) {
        stack.m_85836_();
        stack.m_85837_(0.0, 0.5, 0.0);
        if ((float)entity.m_32100_() - partialTicks + 1.0f < 10.0f) {
            float f = 1.0f - ((float)entity.m_32100_() - partialTicks + 1.0f) / 10.0f;
            f = Mth.m_14036_((float)f, (float)0.0f, (float)1.0f);
            f *= f;
            f *= f;
            float f1 = 1.0f + f * 5.0f;
            stack.m_85841_(f1, f1, f1);
        }
        stack.m_252781_(Axis.f_252436_.m_252977_(-90.0f));
        stack.m_85837_(-0.5, -0.5, 0.5);
        stack.m_252781_(Axis.f_252436_.m_252977_(90.0f));
        SuperTNTRenderer.renderWhiteSolidBlock(((Block)WitherStormModBlocks.SUPER_TNT.get()).m_49966_(), stack, buffer, p_225623_6_, entity.m_32100_() / 5 % 2 == 0);
        stack.m_85849_();
        super.m_7392_((Entity)entity, p_225623_2_, partialTicks, stack, buffer, p_225623_6_);
    }

    private static void renderWhiteSolidBlock(BlockState state, PoseStack stack, MultiBufferSource buffer, int packedLight, boolean flash) {
        int i = flash ? OverlayTexture.m_118093_((int)OverlayTexture.m_118088_((float)1.0f), (int)10) : OverlayTexture.f_118083_;
        Minecraft mc = Minecraft.m_91087_();
        mc.m_91289_().renderSingleBlock(state, stack, buffer, packedLight, i, ModelData.EMPTY, ItemBlockRenderTypes.m_109284_((BlockState)state, (boolean)false));
    }

    public ResourceLocation getTextureLocation(SuperTNTEntity entity) {
        return InventoryMenu.f_39692_;
    }
}

