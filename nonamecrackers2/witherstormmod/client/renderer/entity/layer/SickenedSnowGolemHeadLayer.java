/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.SnowGolemModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.block.BlockRenderDispatcher
 *  net.minecraft.client.renderer.entity.ItemRenderer
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.resources.model.BakedModel
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.inventory.InventoryMenu
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraftforge.client.model.data.ModelData
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import nonamecrackers2.witherstormmod.common.entity.SickenedSnowGolem;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;

public class SickenedSnowGolemHeadLayer
extends RenderLayer<SickenedSnowGolem, SnowGolemModel<SickenedSnowGolem>> {
    private final BlockRenderDispatcher blockRenderer;
    private final ItemRenderer itemRenderer;

    public SickenedSnowGolemHeadLayer(RenderLayerParent<SickenedSnowGolem, SnowGolemModel<SickenedSnowGolem>> parent, BlockRenderDispatcher blockRenderDispatcher, ItemRenderer itemRenderer) {
        super(parent);
        this.blockRenderer = blockRenderDispatcher;
        this.itemRenderer = itemRenderer;
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int p_117351_, SickenedSnowGolem entity, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
        if (entity.m_29930_()) {
            boolean flag;
            boolean bl = flag = Minecraft.m_91087_().m_91314_((Entity)entity) && entity.m_20145_();
            if (!entity.m_20145_() || flag) {
                poseStack.m_85836_();
                ((SnowGolemModel)this.m_117386_()).m_103851_().m_104299_(poseStack);
                poseStack.m_252880_(0.0f, -0.34375f, 0.0f);
                poseStack.m_252781_(Axis.f_252436_.m_252977_(180.0f));
                poseStack.m_85841_(0.625f, -0.625f, -0.625f);
                ItemStack itemstack = new ItemStack((ItemLike)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get());
                if (flag) {
                    BlockState blockstate = ((Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get()).m_49966_();
                    BakedModel bakedmodel = this.blockRenderer.m_110910_(blockstate);
                    int i = LivingEntityRenderer.m_115338_((LivingEntity)entity, (float)0.0f);
                    poseStack.m_252880_(-0.5f, -0.5f, -0.5f);
                    this.blockRenderer.m_110937_().renderModel(poseStack.m_85850_(), buffer.m_6299_(RenderType.m_110491_((ResourceLocation)InventoryMenu.f_39692_)), blockstate, bakedmodel, 0.0f, 0.0f, 0.0f, p_117351_, i, ModelData.EMPTY, null);
                } else {
                    this.itemRenderer.m_269491_((LivingEntity)entity, itemstack, ItemDisplayContext.HEAD, false, poseStack, buffer, entity.m_9236_(), p_117351_, LivingEntityRenderer.m_115338_((LivingEntity)entity, (float)0.0f), entity.m_19879_());
                }
                poseStack.m_85849_();
            }
        }
    }
}

