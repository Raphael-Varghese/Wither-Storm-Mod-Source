/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.targeting.TargetingConditions
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.scores.Team
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity.goal;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Team;
import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModAttributes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class WitherStormTargetingGoal
extends Goal {
    private static final Logger LOGGER = LogManager.getLogger((String)"witherstormmod/WitherStormTargetingGoal");
    private static final boolean LOGGING = false;
    protected final WitherStormEntity storm;
    protected final int headIndex;
    protected TargetingConditions targetConditions;
    @Nullable
    protected LivingEntity target;
    private int unseenTicks;
    @Nullable
    private Vec3 lastTargetPos;

    public WitherStormTargetingGoal(WitherStormEntity entity, Predicate<LivingEntity> mobSelector, int headIndex) {
        this.storm = entity;
        this.targetConditions = TargetingConditions.m_148352_().m_26888_(mobSelector).m_148355_();
        this.headIndex = headIndex;
        this.m_7021_(EnumSet.of(Goal.Flag.TARGET));
    }

    public boolean m_8036_() {
        if (this.storm.isPlayingDead() || this.storm.isAttractingFormidibomb() || this.storm.isDistracted(this.headIndex)) {
            return false;
        }
        this.findApplicableTarget();
        return this.target != null;
    }

    public boolean m_8045_() {
        LivingEntity entity = this.storm.getTarget(this.headIndex);
        if (entity == null) {
            return false;
        }
        if (!entity.m_6084_()) {
            return false;
        }
        Team team = this.storm.m_5647_();
        Team team1 = entity.m_5647_();
        if (team != null && team1 == team) {
            return false;
        }
        double d0 = this.getFollowDistance();
        if (this.storm.m_20280_((Entity)entity) > d0 * d0) {
            return false;
        }
        if (this.storm.getHeadManager().getHead(this.headIndex).canSee((Entity)entity)) {
            this.unseenTicks = 0;
        } else if (++this.unseenTicks > (this.storm.getPhase() < 4 ? 80 : 20)) {
            return false;
        }
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if (player.m_150110_().f_35934_) {
                return false;
            }
        }
        if (this.storm.getPhase() > 3 && this.storm.isEntityBehindBack((Entity)entity)) {
            return false;
        }
        if (!entity.m_9236_().m_46472_().equals((Object)this.storm.m_9236_().m_46472_())) {
            return false;
        }
        if (this.lastTargetPos != null && entity.m_20182_().m_82554_(this.lastTargetPos) > 20.0) {
            return false;
        }
        if (this.storm.getTrackedEntities().contains((Entity)entity)) {
            return false;
        }
        this.lastTargetPos = entity.m_20182_();
        return true;
    }

    public void m_8056_() {
        this.storm.getHeadManager().getHead(this.headIndex).setTarget(this.target);
        this.unseenTicks = 0;
        this.lastTargetPos = null;
    }

    public void m_8041_() {
        this.storm.getHeadManager().getHead(this.headIndex).setTarget(null);
    }

    @NotNull
    protected AABB getTargetSearchArea(double range) {
        return this.storm.getPhase() > 3 ? this.storm.m_20191_().m_82377_(range, range + 50.0, range) : this.storm.m_20191_().m_82377_(range, range * 2.0, range);
    }

    protected double getFollowDistance() {
        return this.storm.m_21133_(Attributes.f_22277_) + 100.0;
    }

    protected void findApplicableTarget() {
        double range = this.storm.getPhase() > 3 ? this.storm.m_21133_(Attributes.f_22277_) : this.storm.m_21133_((Attribute)WitherStormModAttributes.HUNCHBACK_FOLLOW_RANGE.get());
        List<LivingEntity> nearbyEntities = WorldUtil.getPerformantEntitiesOfClass((ServerLevel)this.storm.m_9236_(), LivingEntity.class, this.getTargetSearchArea(range));
        ArrayList targetableEntities = Lists.newArrayList();
        for (LivingEntity entity : nearbyEntities) {
            if (!this.storm.targetApplicable(entity, this.headIndex, this.targetConditions)) continue;
            targetableEntities.add(entity);
        }
        double d0 = -1.0;
        LivingEntity t = null;
        for (LivingEntity t1 : targetableEntities) {
            if (t1.m_20191_().m_82309_() <= 0.5 && t1.m_217043_().m_188503_(4) > 0) continue;
            double d1 = t1.m_20275_(this.storm.m_20185_(), this.storm.m_20188_(), this.storm.m_20189_());
            if (d0 != -1.0 && !(d1 < d0)) continue;
            d0 = d1;
            t = t1;
        }
        this.target = t;
        if (this.target != null) {
            this.target.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(WitherSicknessTracker::countContact);
        }
    }
}

