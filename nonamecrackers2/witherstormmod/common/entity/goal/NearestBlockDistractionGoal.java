/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.ForgeEventFactory
 */
package nonamecrackers2.witherstormmod.common.entity.goal;

import java.util.EnumSet;
import java.util.Objects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class NearestBlockDistractionGoal<T extends Mob>
extends Goal {
    private static final int TICKS_UNTIL_RETRY = 60;
    private static final int LOOK_AT_DISTRACTION_TIME_MIN = 120;
    private static final int LOOK_AT_DISTRACTION_TIME_MAX = 180;
    private static final double MINIMUM_DISTANCE_BETWEEN_BEAMS = 10.0;
    protected final T mob;
    protected final int headIndex;
    private BlockPos targetPos;
    private int retry;

    public NearestBlockDistractionGoal(T mob, int headIndex) {
        this.mob = mob;
        this.headIndex = headIndex;
        this.m_7021_(EnumSet.of(Goal.Flag.TARGET));
    }

    public boolean m_8036_() {
        if (!ForgeEventFactory.getMobGriefingEvent((Level)this.mob.m_9236_(), this.mob)) {
            return false;
        }
        if (this.retry > 0) {
            --this.retry;
            return false;
        }
        if (((WitherStormBase)this.mob).getTarget(this.headIndex) != null && TractorBeamHelper.isInsideTractorBeam((Entity)Objects.requireNonNull(((WitherStormBase)this.mob).getTarget(this.headIndex)), this.mob, 4.0, this.headIndex)) {
            return false;
        }
        if (((WitherStormBase)this.mob).canBeDistracted(this.headIndex, WitherStormBase.DistractionType.STRUCTURES) && !((WitherStormBase)this.mob).isDistracted(this.headIndex)) {
            BlockPos pos = this.findTargetPosition();
            if (pos != null) {
                for (int i = 0; i < ((WitherStormBase)this.mob).getTotalHeads(); ++i) {
                    Vec3 distractedPos = ((WitherStormBase)this.mob).getDistractedPos(i);
                    if (distractedPos == null || !(distractedPos.m_82531_((double)pos.m_123341_() + 0.5, (double)pos.m_123342_() + 0.5, (double)pos.m_123343_() + 0.5) < Math.pow(10.0, 2.0)) || this.mob.m_217043_().m_188503_(5) == 0) continue;
                    this.targetPos = null;
                    this.retry = 60;
                    return false;
                }
                if (((WitherStormBase)this.mob).isPosBehindBack(Vec3.m_82512_((Vec3i)pos))) {
                    return false;
                }
            }
            this.targetPos = pos;
            return this.targetPos != null;
        }
        return false;
    }

    public void m_8056_() {
        ((WitherStormBase)this.mob).makeDistracted(Vec3.m_82512_((Vec3i)this.targetPos), this.mob.m_217043_().m_188503_(60) + 120, this.headIndex);
    }

    private BlockPos findTargetPosition() {
        int scanRadius = (Integer)WitherStormModConfig.SERVER.tractorBeamBlockSearchRadius.get();
        Vec3 currentTarget = ((WitherStormBase)this.mob).getHeadPos(this.headIndex);
        Vec3 beamEnd = currentTarget.m_82549_(((WitherStormBase)this.mob).getViewVector(((WitherStormBase)this.mob).getHeadXRot(this.headIndex), ((WitherStormBase)this.mob).getHeadYRot(this.headIndex), 200.0f));
        BlockHitResult result = this.mob.m_9236_().m_45547_(new ClipContext(currentTarget, beamEnd, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null));
        BlockPos hitPos = result.m_82425_().m_7918_(this.mob.m_217043_().m_216332_(-4, 4), this.mob.m_217043_().m_216332_(-4, 4), this.mob.m_217043_().m_216332_(-4, 4));
        if (WorldUtil.isLoaded((ServerLevel)this.mob.m_9236_(), hitPos)) {
            return WorldUtil.forEachBlockSpiralOutwards(this.mob.m_9236_(), hitPos, scanRadius, p -> this.mob.m_9236_().m_8055_(p).m_204336_(WitherStormModBlockTags.TRACTOR_BEAM_DISTRACTION_BLOCKS));
        }
        return null;
    }
}

