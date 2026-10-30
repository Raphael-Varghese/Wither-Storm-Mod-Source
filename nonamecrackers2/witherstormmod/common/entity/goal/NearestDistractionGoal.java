/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity.goal;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class NearestDistractionGoal<T extends Mob>
extends Goal {
    protected final T entity;
    protected int head;
    protected int unseenTime = 180;
    @Nullable
    protected Entity target;
    protected int unseenTicks;
    protected Predicate<Entity> condition;
    protected int randomInterval;

    public NearestDistractionGoal(T entity, int head, Predicate<Entity> condition, int interval) {
        this.entity = entity;
        this.head = head;
        this.condition = condition;
        this.randomInterval = interval;
        this.m_7021_(EnumSet.of(Goal.Flag.TARGET));
    }

    public boolean m_8036_() {
        if (((WitherStormBase)this.entity).canBeDistracted(this.head, WitherStormBase.DistractionType.ENTITY_BASED)) {
            if (!((WitherStormBase)this.entity).isDistracted(this.head)) {
                if (this.randomInterval > 0 && this.entity.m_217043_().m_188503_(this.randomInterval) != 0) {
                    return false;
                }
                this.findTarget();
                return this.target != null;
            }
            return false;
        }
        return false;
    }

    public boolean m_8045_() {
        Entity entity = this.target;
        if (((WitherStormBase)this.entity).isDistracted(this.head)) {
            if (entity == null || !entity.m_6084_()) {
                if (this.entity.m_217043_().m_188503_(8) == 0) {
                    Vec3 vector = ((WitherStormBase)this.entity).getDistractedPos(this.head);
                    assert (vector != null);
                    ((WitherStormBase)this.entity).setDistractedPos(this.head, vector.m_82520_(this.entity.m_217043_().m_188583_(), this.entity.m_217043_().m_188583_(), this.entity.m_217043_().m_188583_()));
                }
                this.findTarget();
                if (this.target != null && entity != this.target) {
                    ((WitherStormBase)this.entity).makeDistracted(this.getTargetPos(), this.entity.m_217043_().m_188503_(80) + 80, this.head);
                }
            }
            return true;
        }
        if (entity == null) {
            return false;
        }
        if (!entity.m_6084_()) {
            return false;
        }
        if ((double)this.entity.m_20270_(entity) > this.getFollowDistance()) {
            return false;
        }
        if (((WitherStormBase)this.entity).canSee(this.head, entity)) {
            this.unseenTicks = 0;
        } else if (this.unseenTicks++ > this.unseenTime) {
            return false;
        }
        if (!((WitherStormBase)this.entity).isDistracted(this.head)) {
            ((WitherStormBase)this.entity).makeDistracted(this.getTargetPos(), this.entity.m_217043_().m_188503_(80) + 80, this.head);
        } else {
            ((WitherStormBase)this.entity).setDistractedPos(this.head, this.getTargetPos());
        }
        return true;
    }

    public void findTarget() {
        List<Entity> entities = WorldUtil.getPerformantEntitiesOfClass((ServerLevel)this.entity.m_9236_(), Entity.class, this.getSearchArea());
        double d0 = -1.0;
        Entity e = null;
        for (Entity e1 : entities) {
            if (!this.canBeDistractedBy(e1) || ((WitherStormBase)this.entity).isEntityBehindBack(e1)) continue;
            double d1 = e1.m_20238_(((WitherStormBase)this.entity).getHeadPos(this.head));
            if (d0 != -1.0 && !(d1 < d0)) continue;
            d0 = d1;
            e = e1;
        }
        this.target = e;
    }

    public void m_8056_() {
        this.unseenTicks = 0;
        ((WitherStormBase)this.entity).setTarget(this.head, null);
    }

    public void m_8041_() {
        ((WitherStormBase)this.entity).setDistractedPos(this.head, null);
        this.target = null;
    }

    protected double getFollowDistance() {
        return this.entity.m_21133_(Attributes.f_22277_);
    }

    protected AABB getSearchArea() {
        return this.entity.m_20191_().m_82400_(this.getFollowDistance());
    }

    protected boolean canBeDistractedBy(Entity entity) {
        return this.condition.test(entity);
    }

    protected Vec3 getTargetPos() {
        assert (this.target != null);
        return this.target.m_20182_();
    }
}

