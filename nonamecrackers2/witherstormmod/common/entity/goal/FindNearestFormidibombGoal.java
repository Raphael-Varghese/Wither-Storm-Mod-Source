/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.target.TargetGoal
 *  net.minecraft.world.phys.AABB
 */
package nonamecrackers2.witherstormmod.common.entity.goal;

import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.phys.AABB;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class FindNearestFormidibombGoal
extends TargetGoal {
    private final WitherStormEntity storm;
    private FormidibombEntity target;
    private final int randomInterval;

    public FindNearestFormidibombGoal(WitherStormEntity storm, int randomInterval) {
        super((Mob)storm, true);
        this.storm = storm;
        this.randomInterval = randomInterval;
        this.m_7021_(EnumSet.of(Goal.Flag.TARGET));
    }

    public boolean m_8036_() {
        if (this.storm.shouldIgnoreFormidibomb) {
            return false;
        }
        if (this.randomInterval > 0 && this.storm.m_217043_().m_188503_(this.randomInterval) != 0) {
            return false;
        }
        this.findTarget();
        return this.target != null;
    }

    private void findTarget() {
        this.target = this.getNearestLoadedFormidibomb(this.storm.m_20185_(), this.storm.m_20188_(), this.storm.m_20189_(), this.getTargetSearchArea(this.m_7623_()));
    }

    public void m_8056_() {
        this.storm.setFormidibomb(this.target);
        super.m_8056_();
    }

    protected double m_7623_() {
        return this.f_26135_.m_21172_(Attributes.f_22277_) + 50.0;
    }

    private AABB getTargetSearchArea(double inflation) {
        return this.storm.getPhase() > 3 ? this.f_26135_.m_20191_().m_82377_(inflation, inflation + 255.0, inflation) : this.f_26135_.m_20191_().m_82377_(40.0, 20.0, 40.0);
    }

    @Nullable
    private FormidibombEntity getNearestLoadedFormidibomb(double x, double y, double z, AABB bounding) {
        return this.getNearestFormidibomb(WorldUtil.getPerformantEntitiesOfClass((ServerLevel)this.storm.m_9236_(), FormidibombEntity.class, bounding, EntitySelector.f_20408_), this.storm, x, y, z);
    }

    @Nullable
    private FormidibombEntity getNearestFormidibomb(List<FormidibombEntity> entities, WitherStormEntity targeter, double x, double y, double z) {
        double d0 = -1.0;
        FormidibombEntity formidibomb = null;
        for (FormidibombEntity entity : entities) {
            if (!this.test(targeter, (Entity)entity)) continue;
            double d1 = entity.m_20275_(x, y, z);
            if (d0 != -1.0 && !(d1 < d0)) continue;
            d0 = d1;
            formidibomb = entity;
        }
        return formidibomb;
    }

    private boolean test(WitherStormEntity entity, Entity target) {
        if (entity == target) {
            return false;
        }
        if (!(target instanceof FormidibombEntity)) {
            return false;
        }
        if (target.m_5833_()) {
            return false;
        }
        if (!target.m_6084_()) {
            return false;
        }
        if (entity != null) {
            if (this.m_7623_() > 0.0) {
                double d0 = Math.max(this.m_7623_(), 2.0);
                double d1 = entity.m_20275_(target.m_20185_(), target.m_20186_(), target.m_20189_());
                if (d1 > d0 * d0) {
                    return false;
                }
            }
            return entity.getHeadManager().getHead(0).canSee(target);
        }
        return true;
    }
}

