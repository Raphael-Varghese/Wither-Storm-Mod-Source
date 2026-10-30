package nonamecrackers2.witherstormmod.api.common.ai.witherstorm.clustersource;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.event.ForgeEventFactory;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public abstract class BlockClusterSource {
    protected final int maximumCreationAttempts;

    public BlockClusterSource(int maximumCreationAttempts) {
        this.maximumCreationAttempts = maximumCreationAttempts;
    }

    public void tick(WitherStormEntity storm) {
        if (ForgeEventFactory.getMobGriefingEvent((Level)storm.m_9236_(), (Entity)storm) && this.canUse(storm) && storm.f_19797_ % this.getPickupInterval(storm) == 0) {
            this.createCluster(storm);
        }
    }

    protected void createCluster(WitherStormEntity storm) {
        this.createClusterNearby(storm, this.searchCenter(storm), this.getClusterSizeRadius(storm), this.getClusterSearchRadius(storm), this.calculateShakeTime(storm, storm.m_217043_()), this.maximumCreationAttempts, this.calculateRotationDelta(storm, storm.m_217043_()), this.shouldScanUpwards(storm), this.shouldntCountToConsumedEntities(storm));
    }

    protected BlockPos searchCenter(WitherStormEntity storm) {
        int flooredX = Mth.m_14107_((double)storm.m_20185_());
        int flooredY = Math.min(storm.m_9236_().m_151558_() - 1, Mth.m_14107_((double)(storm.m_20188_() + 1.0)));
        int flooredZ = Mth.m_14107_((double)storm.m_20189_());
        return new BlockPos(flooredX, flooredY, flooredZ);
    }

    protected abstract float getClusterSizeRadius(WitherStormEntity var1);

    protected int getClusterSearchRadius(WitherStormEntity storm) {
        return storm.getEntityConsumptionRadius();
    }

    protected abstract int calculateShakeTime(WitherStormEntity var1, RandomSource var2);

    protected Vec2 calculateRotationDelta(WitherStormEntity storm, RandomSource random) {
        if (storm.getConsumedEntities() < storm.adjustAmountForEvolutionSpeed(10000)) {
            return new Vec2((float)(random.m_188503_(20) - 10) * 0.125f, (float)(random.m_188503_(20) - 10) * 0.125f);
        }
        return new Vec2((float)(random.m_188503_(20) - 10) * 0.75f, (float)(random.m_188503_(20) - 10) * 0.75f);
    }

    protected boolean isInvalidInitialStartBlock(WitherStormEntity storm, BlockState state) {
        return false;
    }

    protected boolean isValidClusterBlock(WitherStormEntity storm, BlockState state) {
        return !state.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST);
    }

    protected abstract int getPickupInterval(WitherStormEntity var1);

    protected abstract boolean canUse(WitherStormEntity var1);

    protected boolean shouldScanUpwards(WitherStormEntity storm) {
        return false;
    }

    protected boolean shouldntCountToConsumedEntities(WitherStormEntity storm) {
        return false;
    }

    protected boolean shouldPullBlocksWithRandomYOffset(WitherStormEntity storm, float clusterSizeRadius) {
        return clusterSizeRadius <= 1.0f;
    }

    protected void onClusterAddedToWorld(WitherStormEntity storm, BlockClusterEntity cluster, BlockPos startPos, BlockState startState) {
    }

    protected void createClusterNearby(WitherStormEntity storm, BlockPos searchCenter, float clusterSizeRadius, int radius, int shakeTime, int maxAttempts, Vec2 rotationDelta, boolean scanUpwards, boolean countToConsumedEntities) {
        for (int i = 0; i < maxAttempts; ++i) {
            int randomZ;
            int randomX = storm.m_217043_().m_188503_(radius * 2) - radius;
            double distance = Math.sqrt(randomX * randomX + (randomZ = storm.m_217043_().m_188503_(radius * 2) - radius) * randomZ);
            if (!(distance < (double)radius)) continue;
            BlockPos blockToCollect = new BlockPos(searchCenter.m_123341_() + randomX, searchCenter.m_123342_(), searchCenter.m_123343_() + randomZ);
            if (!WorldUtil.isLoaded((ServerLevel)storm.m_9236_(), blockToCollect)) break;
            BlockState blockState = storm.m_9236_().m_8055_(blockToCollect);
            while (blockToCollect.m_123342_() > storm.m_9236_().m_141937_() && blockToCollect.m_123342_() < storm.m_9236_().m_151558_() && (blockState.m_60713_(Blocks.f_50016_) || blockState.m_60713_(Blocks.f_49990_))) {
                blockToCollect = scanUpwards ? blockToCollect.m_7494_() : blockToCollect.m_7495_();
                blockState = storm.m_9236_().m_8055_(blockToCollect);
            }
            if (!scanUpwards && this.shouldPullBlocksWithRandomYOffset(storm, clusterSizeRadius)) {
                BlockPos originalBlockToCollect = blockToCollect;
                BlockState originalBlockState = blockState;
                int minY = blockToCollect.m_123342_();
                int maxY = Math.max(storm.m_9236_().m_141937_(), minY - 10);
                int randomY = storm.m_217043_().m_188503_(minY - maxY + 1) + maxY;
                blockToCollect = new BlockPos(blockToCollect.m_123341_(), randomY, blockToCollect.m_123343_());
                blockState = storm.m_9236_().m_8055_(blockToCollect);
                if (blockState.m_60795_() || blockState.m_60713_(Blocks.f_49990_) || blockState.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST)) {
                    blockToCollect = originalBlockToCollect;
                    blockState = originalBlockState;
                }
            }
            if (this.isInvalidInitialStartBlock(storm, blockState) || !WorldUtil.isBlockExposed(storm.m_9236_(), blockToCollect) || !storm.m_9236_().m_45976_(WitheredSymbiontEntity.class, new AABB(blockToCollect).m_82400_(15.0)).stream().filter(LivingEntity::m_6084_).findFirst().isEmpty()) continue;
            BlockClusterEntity clusterEntity = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(storm.m_9236_());
            assert (clusterEntity != null);
            clusterEntity.populateWithRadius(blockToCollect, clusterSizeRadius, state -> this.isValidClusterBlock(storm, (BlockState)state));
            if (clusterEntity.getSize() <= 0) continue;
            if (clusterEntity.getSize() >= 55 && storm.m_217043_().m_188503_(3) == 0) {
                clusterEntity.setShouldCrumble(true);
            }
            clusterEntity.setTime(50);
            clusterEntity.setShakeTime(shakeTime);
            if (clusterEntity.getSize() >= 2) {
                clusterEntity.m_5496_((SoundEvent)WitherStormModSoundEvents.BLOCK_CLUSTER_SHAKE.get(), 2.0f, (storm.m_217043_().m_188501_() - storm.m_217043_().m_188501_()) * 0.2f + 1.0f);
            }
            storm.getTrackedEntities().trackEntityToConsume(clusterEntity);
            clusterEntity.setRotationDelta(rotationDelta);
            if (storm.m_217043_().m_188499_()) {
                clusterEntity.m_20049_("RotateClockwise");
            }
            clusterEntity.m_20242_(true);
            clusterEntity.setPhysics(false);
            clusterEntity.setShouldntCountToConsumedEntities(countToConsumedEntities);
            this.onClusterAddedToWorld(storm, clusterEntity, blockToCollect, blockState);
            storm.m_9236_().m_7967_((Entity)clusterEntity);
            break;
        }
    }
}

