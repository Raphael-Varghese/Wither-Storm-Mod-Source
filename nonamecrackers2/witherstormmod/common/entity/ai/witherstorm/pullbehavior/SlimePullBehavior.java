/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.monster.Slime
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.pullbehavior;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.pullbehavior.WitherStormPullBehavior;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class SlimePullBehavior
extends WitherStormPullBehavior<Slime> {
    private static final double MAX_SLIME_SPEED = 5.0;
    private static final double SLIME_ROTATION_SPEED = 0.25;

    @Override
    public Vec3 pullEntity(Slime slime, WitherStormEntity storm, Vec3 absorptionPoint, Vec3 defaultVelocity, double defaultSpeed) {
        if (slime.m_20182_().m_82554_(absorptionPoint) > (double)storm.getUnmodifiedWidth() * 1.5) {
            Vec3 rotationVector = absorptionPoint.m_82546_(slime.m_20182_()).m_82541_().m_82537_(new Vec3(0.0, -1.0, 0.0)).m_82541_().m_82490_(0.25);
            Vec3 delta = absorptionPoint.m_82546_(slime.m_20182_()).m_82541_();
            Vec3 itemVelocity = delta.m_82490_(defaultSpeed).m_82549_(rotationVector);
            if (itemVelocity.m_82553_() > 5.0) {
                itemVelocity = itemVelocity.m_82541_().m_82490_(5.0);
            }
            return itemVelocity;
        }
        return defaultVelocity;
    }

    @Override
    public double getSpeed(Slime entity, WitherStormEntity storm, Vec3 absorptionPoint) {
        double speed = 0.375;
        double configSpeedModifier = (Double)WitherStormModConfig.SERVER.blockClusterPullSpeedModifier.get();
        speed *= configSpeedModifier;
        return speed *= Mth.m_14008_((double)(entity.m_20182_().m_82554_(absorptionPoint) / configSpeedModifier), (double)0.1, (double)1.0);
    }

    @Override
    public boolean canPullIn(Slime entity, WitherStormEntity storm) {
        return true;
    }

    @Override
    public boolean doClientsideVelocityUpdates(Slime entity, WitherStormEntity storm) {
        return false;
    }
}

