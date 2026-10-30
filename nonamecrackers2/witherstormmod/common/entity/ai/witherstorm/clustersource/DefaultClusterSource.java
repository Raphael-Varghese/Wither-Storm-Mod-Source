/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.phys.Vec2
 */
package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.clustersource;

import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec2;
import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.clustersource.BlockClusterSource;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class DefaultClusterSource
extends BlockClusterSource {
    public DefaultClusterSource() {
        super(256);
    }

    @Override
    protected float getClusterSizeRadius(WitherStormEntity storm) {
        return storm.getClusterRadius() + (float)((Integer)WitherStormModConfig.SERVER.clusterSizeModifier.get()).intValue();
    }

    @Override
    protected int calculateShakeTime(WitherStormEntity storm, RandomSource random) {
        return random.m_188503_(10) + 20;
    }

    @Override
    protected Vec2 calculateRotationDelta(WitherStormEntity storm, RandomSource random) {
        return new Vec2((float)random.m_188503_(20) * 0.1f / 2.0f, (float)random.m_188503_(20) * 0.1f / 2.0f);
    }

    @Override
    protected int getPickupInterval(WitherStormEntity storm) {
        if (storm.getPhase() > 3 && storm.shouldSpeedUp()) {
            return (Integer)WitherStormModConfig.SERVER.devourerClusterPickupInterval.get() * 4;
        }
        if (storm.getPhase() < 6) {
            return (Integer)WitherStormModConfig.SERVER.clusterPickupInterval.get();
        }
        return (Integer)WitherStormModConfig.SERVER.devourerClusterPickupInterval.get();
    }

    @Override
    protected boolean canUse(WitherStormEntity storm) {
        return storm.getPhase() >= 4;
    }
}

