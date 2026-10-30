/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.LevelRenderer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.block.BlockRenderDispatcher
 *  net.minecraft.client.renderer.blockentity.BlockEntityRenderer
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.BlockAndTintGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.RenderShape
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraftforge.client.model.data.ModelData
 *  net.minecraftforge.registries.ForgeRegistries
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.client.entity.ClientBlockClusterEntity;
import nonamecrackers2.witherstormmod.client.instancing.RenderBufferer;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;

public class BlockClusterRenderer
extends EntityRenderer<BlockClusterEntity> {
    public BlockClusterRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public void render(BlockClusterEntity cluster, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int p_225623_6_) {
        if (!(cluster instanceof ClientBlockClusterEntity)) {
            throw new ClassCastException("Can only render instances of ClientBlockClusterEntity! Is a mod doing something it shouldn't be doing?");
        }
        ClientBlockClusterEntity entity = (ClientBlockClusterEntity)cluster;
        stack.m_85836_();
        stack.m_85837_(0.0, 0.5, 0.0);
        float x = Mth.m_14179_((float)partialTicks, (float)entity.shakeO.f_82470_, (float)entity.shake.f_82470_);
        float z = Mth.m_14179_((float)partialTicks, (float)entity.shakeO.f_82471_, (float)entity.shake.f_82471_);
        stack.m_252781_(Axis.f_252436_.m_252977_(-entity.getClusterYRot(partialTicks) - x * 50.0f));
        stack.m_252781_(Axis.f_252529_.m_252977_(entity.getClusterXRot(partialTicks) - z * 30.0f));
        stack.m_85837_(0.0, -0.5, 0.0);
        double xOffset = -0.5 + (Math.round(entity.m_20191_().m_82362_()) % 2L == 0L ? -0.5 : 0.0);
        double yOffset = -0.5 + (Math.round(entity.m_20191_().m_82376_()) % 2L == 0L ? -0.5 : 0.0);
        double zOffset = -0.5 + (Math.round(entity.m_20191_().m_82385_()) % 2L == 0L ? -0.5 : 0.0);
        stack.m_85837_(xOffset, yOffset, zOffset);
        Minecraft minecraft = Minecraft.m_91087_();
        BlockRenderDispatcher dispatcher = minecraft.m_91289_();
        Level world = entity.m_9236_();
        BlockAndTintGetter getter = entity.getBlockGetter();
        float fade = entity.lerpFadeAmount(partialTicks);
        float scale = Math.max(0.8f, fade * 0.5f + 0.5f);
        stack.m_85841_(scale, scale, scale);
        stack.m_85837_((double)x, 0.0, (double)z);
        float r = Math.min(1.0f, fade + 0.1f);
        float g = fade;
        float b = Math.min(1.0f, fade + 0.2f);
        for (Map.Entry<RenderType, Map<BlockPos, BlockState>> entry : entity.toRender().entrySet()) {
            RenderType type = entry.getKey();
            RenderBufferer.pushUseAsyncBuilder();
            RenderBufferer.buildAndOrRender((Object)(entity.getToRenderUniqueId() + ", " + type.hashCode()), type, () -> ((ClientBlockClusterEntity)entity).m_213877_(), (s, c, p, o, unusedR, unusedG, unusedB, a) -> {
                for (Map.Entry blockEntry : ((Map)entry.getValue()).entrySet()) {
                    BlockState state = (BlockState)blockEntry.getValue();
                    BlockPos relativePos = (BlockPos)blockEntry.getKey();
                    s.m_85836_();
                    s.m_85837_((double)relativePos.m_123341_(), (double)relativePos.m_123342_() + entity.m_20191_().m_82376_() / 2.0, (double)relativePos.m_123343_());
                    dispatcher.renderBatched(state, relativePos.m_121955_((Vec3i)entity.getStartPos()), getter, s, c, true, RandomSource.m_216335_((long)relativePos.m_121878_()), ModelData.EMPTY, type);
                    s.m_85849_();
                }
            }, stack, p_225623_6_, OverlayTexture.f_118083_, r, g, b, 1.0f);
        }
        for (Map.Entry<RenderType, Map<BlockPos, BlockState>> entry : entity.tilesToRender().entrySet()) {
            BlockState state = (BlockState)entry.getValue();
            if (state.m_60799_() != RenderShape.ENTITYBLOCK_ANIMATED) continue;
            BlockPos relativePos = (BlockPos)entry.getKey();
            BlockPos pos = BlockPos.m_274561_((double)(entity.m_20185_() + (double)relativePos.m_123341_()), (double)(entity.m_20186_() + (double)relativePos.m_123342_() + entity.m_20191_().m_82376_() / 2.0 - 0.5), (double)(entity.m_20189_() + (double)relativePos.m_123343_()));
            stack.m_85836_();
            stack.m_85837_((double)relativePos.m_123341_(), (double)relativePos.m_123342_() + entity.m_20191_().m_82376_() / 2.0, (double)relativePos.m_123343_());
            CompoundTag data = entity.getTileDataFromOffsetPos(relativePos);
            if (data != null) {
                String id = data.m_128461_("id");
                BlockEntity tile = ((BlockEntityType)ForgeRegistries.BLOCK_ENTITY_TYPES.getValue(new ResourceLocation(id))).m_155264_(pos, state);
                BlockEntityRenderer tileRenderer = minecraft.m_167982_().m_112265_(tile);
                tile.m_142339_(world);
                tile.m_142466_(data);
                if (tileRenderer != null) {
                    tileRenderer.m_6922_(tile, partialTicks, stack, buffer, LevelRenderer.m_109541_((BlockAndTintGetter)getter, (BlockPos)pos), OverlayTexture.f_118083_);
                }
            }
            stack.m_85849_();
        }
        stack.m_85849_();
        super.m_7392_((Entity)entity, p_225623_2_, partialTicks, stack, buffer, p_225623_6_);
    }

    public boolean shouldRender(BlockClusterEntity entity, Frustum p_225626_2_, double p_225626_3_, double p_225626_5_, double p_225626_7_) {
        if (((Boolean)WitherStormModConfig.CLIENT.blockClusterRendering.get()).booleanValue() || entity.forceRender()) {
            return super.m_5523_((Entity)entity, p_225626_2_, p_225626_3_, p_225626_5_, p_225626_7_);
        }
        return false;
    }

    public ResourceLocation getTextureLocation(BlockClusterEntity entity) {
        return null;
    }
}

