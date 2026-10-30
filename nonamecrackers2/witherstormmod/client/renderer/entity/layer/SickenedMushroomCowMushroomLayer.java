/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.CowModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.block.BlockRenderDispatcher
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.resources.model.BakedModel
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.inventory.InventoryMenu
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraftforge.client.model.data.ModelData
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import nonamecrackers2.witherstormmod.common.entity.SickenedMushroomCow;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;

public class SickenedMushroomCowMushroomLayer<T extends SickenedMushroomCow>
extends RenderLayer<T, CowModel<T>> {
    private final BlockRenderDispatcher blockRenderer;

    public SickenedMushroomCowMushroomLayer(RenderLayerParent<T, CowModel<T>> parent, BlockRenderDispatcher blockRenderer) {
        super(parent);
        this.blockRenderer = blockRenderer;
    }

    public void render(PoseStack stack, MultiBufferSource buffer, int p_117351_, T entity, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
        if (!entity.m_6162_()) {
            boolean flag;
            Minecraft minecraft = Minecraft.m_91087_();
            boolean bl = flag = minecraft.m_91314_(entity) && entity.m_20145_();
            if (!entity.m_20145_() || flag) {
                BlockState state = ((Block)WitherStormModBlocks.TAINTED_MUSHROOM.get()).m_49966_();
                int i = LivingEntityRenderer.m_115338_(entity, (float)0.0f);
                BakedModel bakedmodel = this.blockRenderer.m_110910_(state);
                stack.m_85836_();
                stack.m_252880_(0.2f, -0.35f, 0.5f);
                stack.m_252781_(Axis.f_252436_.m_252977_(-48.0f));
                stack.m_85841_(-1.0f, -1.0f, 1.0f);
                stack.m_252880_(-0.5f, -0.5f, -0.5f);
                this.renderMushroomBlock(stack, buffer, 0xF000F0, flag, state, i, bakedmodel);
                stack.m_85849_();
                stack.m_85836_();
                stack.m_252880_(0.2f, -0.35f, 0.5f);
                stack.m_252781_(Axis.f_252436_.m_252977_(42.0f));
                stack.m_252880_(0.1f, 0.0f, -0.6f);
                stack.m_252781_(Axis.f_252436_.m_252977_(-48.0f));
                stack.m_85841_(-1.0f, -1.0f, 1.0f);
                stack.m_252880_(-0.5f, -0.5f, -0.5f);
                this.renderMushroomBlock(stack, buffer, 0xF000F0, flag, state, i, bakedmodel);
                stack.m_85849_();
                stack.m_85836_();
                ((CowModel)this.m_117386_()).m_102450_().m_104299_(stack);
                stack.m_252880_(0.0f, -0.7f, -0.2f);
                stack.m_252781_(Axis.f_252436_.m_252977_(-78.0f));
                stack.m_85841_(-1.0f, -1.0f, 1.0f);
                stack.m_252880_(-0.5f, -0.5f, -0.5f);
                this.renderMushroomBlock(stack, buffer, 0xF000F0, flag, state, i, bakedmodel);
                stack.m_85849_();
            }
        }
    }

    private void renderMushroomBlock(PoseStack stack, MultiBufferSource buffer, int p_234855_, boolean p_234856_, BlockState state, int p_234858_, BakedModel model) {
        if (p_234856_) {
            this.blockRenderer.m_110937_().renderModel(stack.m_85850_(), buffer.m_6299_(RenderType.m_110491_((ResourceLocation)InventoryMenu.f_39692_)), state, model, 0.0f, 0.0f, 0.0f, p_234855_, p_234858_, ModelData.EMPTY, null);
        } else {
            this.blockRenderer.renderSingleBlock(state, stack, buffer, p_234855_, p_234858_, ModelData.EMPTY, null);
        }
    }
}

