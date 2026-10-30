/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.targeting.TargetingConditions
 */
package nonamecrackers2.witherstormmod.common.entity.goal;

import java.util.function.Predicate;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

public class WitherStormHurtByTargetGoal
extends HurtByTargetGoal {
    private final Predicate<LivingEntity> entitySelector;

    public WitherStormHurtByTargetGoal(PathfinderMob mob, Predicate<LivingEntity> entitySelector) {
        super(mob, new Class[0]);
        this.entitySelector = entitySelector;
    }

    protected boolean m_26150_(LivingEntity entity, TargetingConditions conditions) {
        return this.entitySelector.test(entity) && super.m_26150_(entity, conditions);
    }
}

