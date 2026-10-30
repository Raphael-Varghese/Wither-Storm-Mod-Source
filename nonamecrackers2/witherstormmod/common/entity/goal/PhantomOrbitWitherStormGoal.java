/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.Vec3i
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.monster.Phantom
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity.goal;

import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;
import nonamecrackers2.witherstormmod.mixin.IMixinPhantom;

public class PhantomOrbitWitherStormGoal
extends Goal {
    private final Phantom entity;
    @Nullable
    private WitherStormEntity storm;
    private float angle;
    private float distance;
    private float height;
    private float clockwise;
    private final int chance;

    public PhantomOrbitWitherStormGoal(Phantom entity) {
        this(entity, 100);
    }

    public PhantomOrbitWitherStormGoal(Phantom entity, int chance) {
        this.entity = entity;
        this.chance = chance;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    protected boolean touchingTarget() {
        return ((IMixinPhantom)this.entity).getMoveTargetPoint().m_82531_(this.entity.m_20185_(), this.entity.m_20186_(), this.entity.m_20189_()) < 4.0;
    }

    public boolean m_8036_() {
        if (this.entity.m_217043_().m_188503_(this.chance) == 0) {
            this.storm = this.findStorm();
        }
        return this.storm != null && this.storm.getPhase() > 2;
    }

    public void m_8056_() {
        this.distance = 25.0f + this.entity.m_217043_().m_188501_() * 10.0f;
        this.height = -4.0f + this.entity.m_217043_().m_188501_() * 9.0f;
        this.clockwise = this.entity.m_217043_().m_188499_() ? 1.0f : -1.0f;
        this.selectNext();
    }

    public void m_8037_() {
        if (this.entity.m_217043_().m_188503_(350) == 0) {
            this.height = -4.0f + this.entity.m_217043_().m_188501_() * 9.0f;
        }
        if (this.entity.m_217043_().m_188503_(250) == 0) {
            this.distance += 1.0f;
            if (this.distance > 75.0f) {
                this.distance = 25.0f;
                this.clockwise = -this.clockwise;
            }
        }
        if (this.entity.m_217043_().m_188503_(450) == 0) {
            this.angle = this.entity.m_217043_().m_188501_() * 2.0f * (float)Math.PI;
            this.selectNext();
        }
        if (this.touchingTarget()) {
            this.selectNext();
        }
        if (((IMixinPhantom)this.entity).getMoveTargetPoint().f_82480_ < this.entity.m_20186_() && !this.entity.m_9236_().m_46859_(this.entity.m_20183_().m_6625_(1))) {
            this.height = Math.max(1.0f, this.height);
            this.selectNext();
        }
        if (((IMixinPhantom)this.entity).getMoveTargetPoint().f_82480_ > this.entity.m_20186_() && !this.entity.m_9236_().m_46859_(this.entity.m_20183_().m_6625_(1))) {
            this.height = Math.min(-1.0f, this.height);
            this.selectNext();
        }
    }

    private void selectNext() {
        this.angle += this.clockwise * 15.0f * ((float)Math.PI / 180);
        if (this.storm != null) {
            ((IMixinPhantom)this.entity).setAnchorPoint(this.storm.m_20183_().m_6630_((int)this.storm.m_20206_() + 20));
        }
        ((IMixinPhantom)this.entity).setMoveTargetPoint(Vec3.m_82528_((Vec3i)((IMixinPhantom)this.entity).getAnchorPoint()).m_82520_((double)(this.distance * Mth.m_14089_((float)this.angle)), (double)(-4.0f + this.height), (double)(this.distance * Mth.m_14031_((float)this.angle))));
    }

    private WitherStormEntity findStorm() {
        List<WitherStormEntity> entities = WorldUtil.getPerformantEntitiesOfClass((ServerLevel)this.entity.m_9236_(), WitherStormEntity.class, this.entity.m_20191_().m_82400_(100.0));
        double d0 = -1.0;
        WitherStormEntity closest = null;
        for (WitherStormEntity storm : entities) {
            if (!this.entity.m_142582_((Entity)storm)) continue;
            double d1 = storm.m_20275_(this.entity.m_20185_(), this.entity.m_20186_(), this.entity.m_20189_());
            if (d0 != -1.0 && !(d1 < d0)) continue;
            d0 = d1;
            closest = storm;
        }
        return closest;
    }
}

