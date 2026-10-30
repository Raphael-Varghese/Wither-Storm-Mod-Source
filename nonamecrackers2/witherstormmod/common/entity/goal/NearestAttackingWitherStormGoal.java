/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.phys.AABB
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity.goal;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.phys.AABB;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
import org.jetbrains.annotations.NotNull;

public class NearestAttackingWitherStormGoal
extends NearestAttackableTargetGoal<WitherStormEntity> {
    public NearestAttackingWitherStormGoal(Mob mob, int randomInterval) {
        super(mob, WitherStormEntity.class, randomInterval, true, false, null);
    }

    @NotNull
    protected AABB m_7255_(double range) {
        return this.f_26135_.m_20191_().m_82377_(range, range * 2.0, range);
    }

    protected void m_26073_() {
        super.m_26073_();
        LivingEntity livingEntity = this.f_26050_;
        if (livingEntity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)livingEntity;
            boolean flag = true;
            if (storm.getPhase() > 3 && (double)storm.m_20270_((Entity)this.f_26135_) > 30.0 && ((Boolean)TractorBeamHelper.isInsideTractorBeam((Entity)this.f_26135_, storm, 4.0).getFirst()).booleanValue() && !this.f_26135_.m_20096_()) {
                flag = false;
            }
            if (flag) {
                this.f_26050_ = null;
            }
        }
    }

    protected double m_7623_() {
        return 100.0;
    }
}

