/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.PoseStack$Pose
 *  com.mojang.blaze3d.vertex.SheetedDecalTextureGenerator
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.ItemBlockRenderTypes
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.block.BlockRenderDispatcher
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.client.resources.model.ModelBakery
 *  net.minecraft.core.BlockPos
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
import com.mojang.blaze3d.vertex.SheetedDecalTextureGenerator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.commandblock.RibcageModel;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import org.joml.Matrix4f;

public class CommandBlockRenderer
extends EntityRenderer<CommandBlockEntity> {
    private static final ResourceLocation RIBCAGE_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/command_block/ribcage.png");
    private final RibcageModel ribcage;
    @Nullable
    private EntityModel<CommandBlockEntity> model;

    public CommandBlockRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.ribcage = new RibcageModel(context.m_174023_(WitherStormModRenderers.RIBCAGE));
    }

    protected int getBlockLightLevel(CommandBlockEntity entity, BlockPos pos) {
        return entity.getMode() == CommandBlockEntity.Mode.TENTACLES ? 15 : super.m_6086_((Entity)entity, pos);
    }

    public void render(CommandBlockEntity entity, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
        this.model = this.getModel(entity.getMode());
        if (this.model != null) {
            stack.m_85836_();
            stack.m_85841_(-1.0f, -1.0f, 1.0f);
            stack.m_252781_(Axis.f_252392_.m_252977_(-Mth.m_14179_((float)partialTicks, (float)entity.f_20884_, (float)entity.f_20883_) + 90.0f));
            VertexConsumer builder = buffer.m_6299_(this.model.m_103119_(this.getTextureLocation(entity)));
            this.model.m_6973_((Entity)entity, entity.getModeAnim(partialTicks), partialTicks, 0.0f, entity.m_146908_(), entity.m_146909_());
            this.model.m_7695_(stack, builder, packedLight, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
            stack.m_85849_();
        }
        stack.m_85836_();
        stack.m_85837_(-0.5, (double)entity.lerpProtectionYOffset(partialTicks), -0.5);
        Minecraft mc = Minecraft.m_91087_();
        BlockRenderDispatcher blockRenderer = mc.m_91289_();
        BlockState state = entity.getBlockState();
        blockRenderer.renderSingleBlock(state, stack, buffer, packedLight, OverlayTexture.f_118083_, ModelData.EMPTY, ItemBlockRenderTypes.m_109284_((BlockState)entity.getBlockState(), (boolean)false));
        int health = Mth.m_14143_((float)((entity.m_21233_() - entity.m_21223_()) / (entity.m_21233_() / 4.0f)));
        PoseStack.Pose entry = stack.m_85850_();
        if (health < 5 && health > 0) {
            RenderType type = (RenderType)ModelBakery.f_119229_.get(health * 2);
            SheetedDecalTextureGenerator blockBreakingBuilder = new SheetedDecalTextureGenerator(buffer.m_6299_(type), entry.m_252922_(), entry.m_252943_(), 1.0f);
            blockRenderer.m_110937_().renderModel(entry, (VertexConsumer)blockBreakingBuilder, state, blockRenderer.m_110910_(state), 1.0f, 1.0f, 1.0f, packedLight, OverlayTexture.f_118083_, ModelData.EMPTY, type);
        }
        stack.m_85849_();
        if (entity.getSpecialDeathTime() > 0 || entity.getHitGlareTime() > 0) {
            float f1 = 0.0f;
            float k = 1.0f;
            if (entity.getSpecialDeathTime() > 0) {
                f1 = ((float)entity.getSpecialDeathTime() + partialTicks) / 20.0f;
                k = 1.0f;
            } else if (entity.getHitGlareTime() > 0) {
                f1 = 2.0f + ((float)entity.getHitGlareTime() - partialTicks) / 30.0f;
                k = ((float)entity.getHitGlareTime() - partialTicks) / 60.0f;
            }
            Random random = new Random(122L);
            VertexConsumer builder = buffer.m_6299_(RenderType.m_110502_());
            stack.m_85836_();
            stack.m_85837_(0.0, entity.m_20191_().m_82376_() / 2.0, 0.0);
            int i = 0;
            while ((float)i < (f1 + f1 * f1) / 2.0f) {
                stack.m_252781_(Axis.f_252529_.m_252977_(random.nextFloat() * 360.0f));
                stack.m_252781_(Axis.f_252436_.m_252977_(random.nextFloat() * 360.0f));
                stack.m_252781_(Axis.f_252403_.m_252977_(random.nextFloat() * 360.0f));
                stack.m_252781_(Axis.f_252529_.m_252977_(random.nextFloat() * 360.0f));
                stack.m_252781_(Axis.f_252436_.m_252977_(random.nextFloat() * 360.0f));
                stack.m_252781_(Axis.f_252403_.m_252977_(random.nextFloat() * 360.0f + f1 * 90.0f));
                float f3 = random.nextFloat() * 0.2f + f1;
                float f4 = (random.nextFloat() + 0.2f) * 0.05f * f1;
                Matrix4f matrix4f = stack.m_85850_().m_252922_();
                float sqrt = (float)(Math.sqrt(3.0) / 2.0);
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_85950_(1.0f, 1.0f, 1.0f, k).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_85950_(1.0f, 1.0f, 1.0f, k).m_5752_();
                builder.m_252986_(matrix4f, -sqrt * f4, f3, -0.5f * f4).m_6122_(255, 123, 0, 0).m_5752_();
                builder.m_252986_(matrix4f, sqrt * f4, f3, -0.5f * f4).m_6122_(255, 123, 0, 0).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_85950_(1.0f, 1.0f, 1.0f, k).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_85950_(1.0f, 1.0f, 1.0f, k).m_5752_();
                builder.m_252986_(matrix4f, sqrt * f4, f3, -0.5f * f4).m_6122_(255, 123, 0, 0).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, f3, 1.0f * f4).m_6122_(255, 123, 0, 0).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_85950_(1.0f, 1.0f, 1.0f, k).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_85950_(1.0f, 1.0f, 1.0f, k).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, f3, 1.0f * f4).m_6122_(255, 123, 0, 0).m_5752_();
                builder.m_252986_(matrix4f, -sqrt * f4, f3, -0.5f * f4).m_6122_(255, 123, 0, 0).m_5752_();
                ++i;
            }
            stack.m_85849_();
        }
        super.m_7392_((Entity)entity, p_225623_2_, partialTicks, stack, buffer, packedLight);
    }

    private EntityModel<CommandBlockEntity> getModel(CommandBlockEntity.Mode mode) {
        return mode == CommandBlockEntity.Mode.RIBS ? this.ribcage : null;
    }

    public ResourceLocation getTextureLocation(CommandBlockEntity entity) {
        return entity.getMode() == CommandBlockEntity.Mode.RIBS ? RIBCAGE_LOCATION : InventoryMenu.f_39692_;
    }
}

