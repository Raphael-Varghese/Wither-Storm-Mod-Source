/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.block.BlockRenderDispatcher
 *  net.minecraft.client.resources.model.BakedModel
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.level.BlockAndTintGetter
 *  net.minecraft.world.level.ColorResolver
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LightLayer
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.RenderShape
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.lighting.LevelLightEngine
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.client.ChunkRenderTypeSet
 *  net.minecraftforge.client.model.data.ModelData
 *  nonamecrackers2.crackerslib.common.compat.CompatHelper
 */
package nonamecrackers2.witherstormmod.client.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.ChunkRenderTypeSet;
import net.minecraftforge.client.model.data.ModelData;
import nonamecrackers2.crackerslib.common.compat.CompatHelper;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;

public class ClientBlockClusterEntity
extends BlockClusterEntity {
    private final BlockClusterWorld blockGetter;
    private Map<RenderType, Map<BlockPos, BlockState>> toRender = new LinkedHashMap<RenderType, Map<BlockPos, BlockState>>();
    private Map<BlockPos, BlockState> tilesToRender = new LinkedHashMap<BlockPos, BlockState>();
    @Nullable
    private String toRenderUniqueId;
    public float fadeAmount = 1.0f;
    private float fadeAmountO = 1.0f;

    public ClientBlockClusterEntity(EntityType<? extends BlockClusterEntity> entityType, Level world) {
        super(entityType, world);
        this.blockGetter = new BlockClusterWorld(world, this);
    }

    @Override
    public void m_7350_(EntityDataAccessor<?> parameter) {
        super.m_7350_(parameter);
        if (parameter.equals(BlockClusterEntity.BLOCKS)) {
            this.toRender.clear();
            this.tilesToRender.clear();
            for (Map.Entry<BlockPos, BlockState> entry : this.getBlocks().entrySet()) {
                BlockPos pos = entry.getKey();
                BlockState state = entry.getValue();
                if (state.m_60799_() != RenderShape.ENTITYBLOCK_ANIMATED) {
                    if (state.m_60799_() != RenderShape.MODEL) continue;
                    BlockRenderDispatcher dispatcher = Minecraft.m_91087_().m_91289_();
                    BakedModel model = dispatcher.m_110910_(state);
                    ChunkRenderTypeSet blockRenderTypes = model.getRenderTypes(state, RandomSource.m_216335_((long)state.m_60726_(this.getStartPos())), ModelData.EMPTY);
                    for (RenderType type : RenderType.m_110506_()) {
                        if (!blockRenderTypes.contains(type)) continue;
                        Map map = this.toRender.computeIfAbsent(type, t -> new LinkedHashMap());
                        map.put(pos, state);
                    }
                    continue;
                }
                this.tilesToRender.put(pos, state);
            }
            this.toRenderUniqueId = this.toRender.toString();
        } else if (parameter.equals(BlockClusterEntity.FADE_POINT)) {
            this.calculateFade();
            this.fadeAmountO = this.fadeAmount;
        }
    }

    public Map<RenderType, Map<BlockPos, BlockState>> toRender() {
        return this.toRender;
    }

    public Map<BlockPos, BlockState> tilesToRender() {
        return this.tilesToRender;
    }

    @Nullable
    public String getToRenderUniqueId() {
        return this.toRenderUniqueId;
    }

    public BlockAndTintGetter getBlockGetter() {
        return this.blockGetter;
    }

    @Override
    public void m_8119_() {
        super.m_8119_();
        this.calculateFade();
    }

    private void calculateFade() {
        if (((Boolean)WitherStormModConfig.CLIENT.blockClusterRendering.get()).booleanValue() && this.getShakeTime() <= 0) {
            this.fadeAmountO = this.fadeAmount;
            BlockPos point = this.getFadePos();
            if (point != null) {
                double distanceFromCreationToFade = Math.sqrt(this.getStartPos().m_123331_((Vec3i)point)) - (double)this.getFadeDistanceOffset();
                double distance = Math.max(0.0, Vec3.m_82512_((Vec3i)point).m_82554_(this.m_20182_()) - (double)this.getFadeDistanceOffset());
                this.fadeAmount = Math.min(1.0f, (float)distance / Math.min((float)distanceFromCreationToFade, this.getFadeStrength()));
            }
        }
    }

    public float lerpFadeAmount(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.fadeAmountO, (float)this.fadeAmount);
    }

    public static class BlockClusterWorld
    implements BlockAndTintGetter {
        private final Level wrapped;
        private final BlockClusterEntity cluster;

        public BlockClusterWorld(Level wrapped, BlockClusterEntity cluster) {
            this.wrapped = wrapped;
            this.cluster = cluster;
        }

        public BlockEntity m_7702_(BlockPos pos) {
            return null;
        }

        public BlockState m_8055_(BlockPos pos) {
            BlockState state = this.cluster.getBlocks().get(pos.m_121996_((Vec3i)this.cluster.getStartPos()));
            if (state == null) {
                state = Blocks.f_50016_.m_49966_();
            }
            return state;
        }

        public FluidState m_6425_(BlockPos pos) {
            return this.m_8055_(pos).m_60819_();
        }

        public int m_141928_() {
            return this.wrapped.m_141928_();
        }

        public int m_141937_() {
            return this.wrapped.m_141937_();
        }

        public float m_7717_(Direction direction, boolean p_45523_) {
            return this.wrapped.m_7717_(direction, p_45523_);
        }

        public LevelLightEngine m_5518_() {
            return this.wrapped.m_5518_();
        }

        public int m_45524_(BlockPos pos, int skyOffset) {
            return CompatHelper.areShadersRunning() ? super.m_45524_(pos, skyOffset) : 15;
        }

        public int m_45517_(LightLayer layer, BlockPos pos) {
            return CompatHelper.areShadersRunning() ? super.m_45517_(layer, pos) : 15;
        }

        public int m_6171_(BlockPos pos, ColorResolver resolver) {
            return this.wrapped.m_6171_(pos, resolver);
        }
    }
}

