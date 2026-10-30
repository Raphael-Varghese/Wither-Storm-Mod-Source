/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.pullbehavior;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.pullbehavior.WitherStormPullBehavior;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;

public class BlockClusterPullBehavior
extends WitherStormPullBehavior<BlockClusterEntity> {
    private static final double HUNCH_MAX_CLUSTER_SPEED = 2.0;
    private static final double MAX_CLUSTER_SPEED = 5.0;
    private static final double CLUSTER_TIME = 15.0;

    @Override
    public Vec3 pullEntity(BlockClusterEntity cluster, WitherStormEntity storm, Vec3 absorptionPoint, Vec3 defaultVelocity, double defaultSpeed) {
        cluster.setFadePos(storm.m_20183_());
        if (storm.getPhase() > 3) {
            cluster.setFadeStrength(75.0f);
            cluster.setFadeDistanceOffset(20);
        }
        if (cluster.m_20182_().m_82554_(absorptionPoint) > (double)storm.getUnmodifiedWidth() * 1.5) {
            double clusterRotationSpeed = this.getClusterRotationSpeed(cluster, storm);
            if (storm.getPhase() <= 3) {
                clusterRotationSpeed = 0.2;
            }
            boolean rotateCounterClockwise = (Boolean)WitherStormModConfig.SERVER.canClustersSpiralCounterClockwise.get() != false && cluster.m_19880_().contains("RotateClockwise");
            Vec3 rotationVector = absorptionPoint.m_82546_(cluster.m_20182_()).m_82541_().m_82537_(new Vec3(0.0, rotateCounterClockwise ? 1.0 : -1.0, 0.0)).m_82541_().m_82490_(clusterRotationSpeed);
            if (cluster.createdFromTractorBeam() && cluster.m_20182_().m_82554_(storm.m_146892_()) > 25.0) {
                int head = cluster.getHeadCreatedFrom();
                if (storm.tractorBeamActive(head)) {
                    Vec3 pos = TractorBeamHelper.calculateClosestPoint(cluster.m_20182_(), storm, head);
                    double distance = Math.sqrt(cluster.m_20182_().m_82557_(pos));
                    double distanceFromHead = Math.sqrt(cluster.m_20182_().m_82557_(storm.getHeadPos(head)));
                    double tractDistance = 4.0 * ((distanceFromHead + 20.0) * 0.015);
                    double threshold = cluster.getTractorBeamDistanceThreshold() * Mth.m_14008_((double)((distanceFromHead - 60.0) * 0.1), (double)0.0, (double)1.0);
                    double tractorAlignSpeed = Mth.m_14008_((double)(distance + threshold - tractDistance), (double)0.0, (double)4.0);
                    Vec3 delta = pos.m_82546_(cluster.m_20182_()).m_82541_().m_82490_(tractorAlignSpeed);
                    Vec3 toHeadDelta = storm.getHeadPos(cluster.getHeadCreatedFrom()).m_82546_(cluster.m_20182_()).m_82541_().m_82490_(defaultSpeed);
                    return toHeadDelta.m_82549_(delta);
                }
                return defaultVelocity.m_82549_(rotationVector);
            }
            return defaultVelocity.m_82549_(rotationVector);
        }
        return defaultVelocity;
    }

    private double getClusterRotationSpeed(BlockClusterEntity cluster, WitherStormEntity storm) {
        double rotationSpeed = 2.0E-4 + (double)cluster.time * 15.0 * 4.0;
        int size = cluster.getSize();
        double clusterSize = Math.pow(size, -0.25);
        double clusterRotationSpeed = storm.getPhase() <= 3 ? Math.min(rotationSpeed * 10000.0 + (double)cluster.time * 15.0 * clusterSize, clusterSize) : Math.min(rotationSpeed + (double)cluster.time * 15.0 * clusterSize, clusterSize);
        if (clusterRotationSpeed > 5.0 && storm.getPhase() >= 4) {
            clusterRotationSpeed = 5.0;
        } else if (clusterRotationSpeed > 2.0 && storm.getPhase() <= 3) {
            clusterRotationSpeed = 2.0;
        }
        return clusterRotationSpeed;
    }

    @Override
    public double getSpeed(BlockClusterEntity entity, WitherStormEntity storm, Vec3 absorptionPoint) {
        Vec3 delta = absorptionPoint.m_82546_(entity.m_20182_());
        double distanceToStorm = delta.m_82553_();
        double speed = storm.getPhase() <= 3 ? 0.125 : (distanceToStorm >= 240.0 && !entity.createdFromTractorBeam() ? 0.375 : 0.0625);
        speed += (double)entity.time * 0.005;
        double configSpeedModifier = entity.createdFromTractorBeam() ? ((Double)WitherStormModConfig.SERVER.tractorBeamClusterSpeedModifier.get()).doubleValue() : ((Double)WitherStormModConfig.SERVER.blockClusterPullSpeedModifier.get()).doubleValue();
        speed *= configSpeedModifier;
        return speed *= Mth.m_14008_((double)(entity.m_20182_().m_82554_(absorptionPoint) / configSpeedModifier), (double)0.1, (double)1.0);
    }

    @Override
    public boolean canPullIn(BlockClusterEntity entity, WitherStormEntity storm) {
        return entity.getShakeTime() <= 0;
    }

    @Override
    public boolean doClientsideVelocityUpdates(BlockClusterEntity entity, WitherStormEntity storm) {
        return true;
    }
}

