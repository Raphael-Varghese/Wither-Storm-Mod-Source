/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.state.BlockState
 */
package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.clustersource;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.clustersource.BlockClusterSource;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;

public class SmallClusterSource
extends BlockClusterSource {
    public SmallClusterSource() {
        super(1024);
    }

    @Override
    protected void createCluster(WitherStormEntity storm) {
        int phase = storm.getPhase();
        int multiClusters = switch (phase) {
            case 4 -> 4;
            case 5 -> 5;
            case 6 -> 6;
            case 7 -> 8;
            default -> 1;
        };
        for (int i = 0; i < multiClusters; ++i) {
            super.createCluster(storm);
        }
    }

    @Override
    protected float getClusterSizeRadius(WitherStormEntity storm) {
        return 1.0f;
    }

    @Override
    protected int getClusterSearchRadius(WitherStormEntity storm) {
        return storm.getEntityConsumptionRadius() * storm.phaseRadiusMultiplier(storm.getPhase());
    }

    @Override
    protected int calculateShakeTime(WitherStormEntity storm, RandomSource random) {
        return 0;
    }

    @Override
    protected boolean isValidClusterBlock(WitherStormEntity storm, BlockState state) {
        return !state.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST) && !state.m_204336_(WitherStormModBlockTags.SMALL_CLUSTER_BLACKLIST);
    }

    @Override
    protected boolean isInvalidInitialStartBlock(WitherStormEntity storm, BlockState state) {
        return state.m_204336_(WitherStormModBlockTags.LESS_FAVORABLE_BLOCKS) && storm.m_217043_().m_188500_() <= 0.9;
    }

    @Override
    protected int getPickupInterval(WitherStormEntity storm) {
        int phase = storm.getPhase();
        if (((Boolean)WitherStormModConfig.SERVER.constantBlackhole.get()).booleanValue()) {
            return 6;
        }
        return switch (phase) {
            case 0, 1, 2, 3 -> 64;
            case 4 -> 30;
            case 5 -> 25;
            case 6 -> 20;
            case 7 -> 15;
            default -> 100;
        };
    }

    @Override
    protected void onClusterAddedToWorld(WitherStormEntity storm, BlockClusterEntity cluster, BlockPos startPos, BlockState startState) {
        SoundType sound = startState.getSoundType((LevelReader)storm.m_9236_(), startPos, null);
        storm.m_9236_().m_5594_(null, startPos, sound.m_56775_(), SoundSource.BLOCKS, (sound.m_56773_() + 1.0f) / 2.0f, sound.m_56774_() * 0.8f);
    }

    @Override
    protected boolean canUse(WitherStormEntity storm) {
        return !(storm instanceof WitherStormSegmentEntity);
    }
}

