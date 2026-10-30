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
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class HunchbackClusterSource
extends BlockClusterSource {
    public HunchbackClusterSource() {
        super(256);
    }

    @Override
    protected boolean shouldScanUpwards(WitherStormEntity storm) {
        if (storm.m_217043_().m_188503_(2) == 0) {
            int x = storm.m_146903_();
            int z = storm.m_146907_();
            int height = WorldUtil.getCeilingStartingAt(storm.m_9236_(), storm.m_146904_(), x, z);
            BlockPos pos = new BlockPos(x, height, z);
            return !storm.m_9236_().m_8055_(pos).m_60795_();
        }
        return false;
    }

    @Override
    protected void createCluster(WitherStormEntity storm) {
        int multiClusters = switch (storm.getPhase()) {
            case 1 -> 3;
            case 2 -> 9;
            case 3 -> 18;
            default -> 1;
        };
        for (int i = 0; i < multiClusters; ++i) {
            super.createCluster(storm);
        }
    }

    @Override
    protected int calculateShakeTime(WitherStormEntity storm, RandomSource random) {
        if (storm.getConsumedEntities() >= storm.adjustAmountForEvolutionSpeed(15000)) {
            return 0;
        }
        if (storm.getConsumedEntities() >= storm.adjustAmountForEvolutionSpeed(10000)) {
            return random.m_188503_(10);
        }
        return random.m_188503_(40);
    }

    @Override
    protected float getClusterSizeRadius(WitherStormEntity storm) {
        return 1.0f;
    }

    @Override
    protected boolean isInvalidInitialStartBlock(WitherStormEntity storm, BlockState state) {
        return state.m_204336_(WitherStormModBlockTags.LESS_FAVORABLE_BLOCKS_HUNCH) && storm.m_217043_().m_188500_() <= 0.995 && storm.getPhase() == 3;
    }

    @Override
    protected int getClusterSearchRadius(WitherStormEntity storm) {
        return storm.entityConsumptionRadiusHunch();
    }

    @Override
    protected int getPickupInterval(WitherStormEntity storm) {
        if (((Boolean)WitherStormModConfig.SERVER.constantBlackhole.get()).booleanValue()) {
            return 1;
        }
        return Math.max(1, 60 - Math.round((float)storm.getConsumedEntities() * 0.00375f));
    }

    @Override
    protected boolean canUse(WitherStormEntity storm) {
        return storm.getPhase() <= 3;
    }

    @Override
    protected void onClusterAddedToWorld(WitherStormEntity storm, BlockClusterEntity cluster, BlockPos startPos, BlockState startState) {
        SoundType sound = startState.getSoundType((LevelReader)storm.m_9236_(), startPos, null);
        storm.m_9236_().m_5594_(null, startPos, sound.m_56775_(), SoundSource.BLOCKS, (sound.m_56773_() + 1.0f) / 2.0f, sound.m_56774_() * 0.8f);
    }
}

