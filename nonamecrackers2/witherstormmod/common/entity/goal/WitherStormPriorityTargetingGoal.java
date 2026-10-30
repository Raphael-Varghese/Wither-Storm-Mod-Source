/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.Attributes
 */
package nonamecrackers2.witherstormmod.common.entity.goal;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.goal.WitherStormTargetingGoal;
import nonamecrackers2.witherstormmod.common.init.WitherStormModAttributes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModEntityTags;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class WitherStormPriorityTargetingGoal
extends WitherStormTargetingGoal {
    public WitherStormPriorityTargetingGoal(WitherStormEntity entity, Predicate<LivingEntity> mobSelector, int headIndex) {
        super(entity, mobSelector.and(e -> e.m_6095_().m_204039_(WitherStormModEntityTags.FAVOURABLE_MOBS)), headIndex);
    }

    @Override
    public boolean m_8036_() {
        return (Boolean)WitherStormModConfig.SERVER.specialTargetingBias.get() != false && this.storm.getPlayingJukeboxes().isEmpty() && this.storm.getTarget(this.headIndex) == null && this.storm.m_217043_().m_188503_(100) <= (Integer)WitherStormModConfig.SERVER.specialTargetingBiasChance.get() && super.m_8036_();
    }

    @Override
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

