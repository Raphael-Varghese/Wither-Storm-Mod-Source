/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.random.SimpleWeightedRandomList
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package nonamecrackers2.witherstormmod.common.entity.goal.symbiont;

import java.util.EnumSet;
import java.util.Objects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class SummonMobsGoal
extends Goal {
    protected final WitheredSymbiontEntity entity;
    protected final SimpleWeightedRandomList<EntityType<? extends Mob>> spawnTypes;
    protected final SimpleWeightedRandomList<EntityType<? extends Mob>> difficultSpawnTypes;
    protected int time;

    public SummonMobsGoal(WitheredSymbiontEntity entity, SimpleWeightedRandomList<EntityType<? extends Mob>> types, SimpleWeightedRandomList<EntityType<? extends Mob>> difficultTypes) {
        this.entity = entity;
        this.spawnTypes = types;
        this.difficultSpawnTypes = difficultTypes;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.TARGET, Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        LivingEntity target = this.entity.m_5448_();
        if (target != null && target.m_6084_()) {
            return !this.spawnTypes.m_146337_();
        }
        return false;
    }

    public boolean m_8045_() {
        return this.m_8036_() && this.time > 0;
    }

    public void m_8056_() {
        this.time = 60 + this.entity.m_217043_().m_188503_(60) + (this.entity.shouldIncreaseDifficulty() ? 40 : 0);
        this.entity.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_SUMMON.get(), 4.0f, 1.0f);
    }

    public void m_8037_() {
        if (this.time > 0) {
            Mob mob;
            --this.time;
            if (this.time % 10 == 0 && (mob = WorldUtil.summonRandomMob((ServerLevel)this.entity.m_9236_(), this.entity.m_20183_(), this.entity.m_217043_(), 16, this.entity.shouldIncreaseDifficulty() ? this.difficultSpawnTypes : this.spawnTypes, this.entity.shouldIncreaseDifficulty())) != null) {
                SummonMobsGoal.addAttributes(mob);
            }
        }
    }

    public void m_8041_() {
        this.entity.nextStage();
    }

    private static void addAttributes(Mob mob) {
        Objects.requireNonNull(mob.m_21051_(Attributes.f_22276_)).m_22125_(new AttributeModifier("194fec31-b36e-41fc-ad72-02a5cb891def", -((mob.m_217043_().m_188500_() + 0.5) * 2.0), AttributeModifier.Operation.ADDITION));
        Objects.requireNonNull(mob.m_21051_(Attributes.f_22279_)).m_22125_(new AttributeModifier("5965c24d-8ac1-4f04-92ee-3d2724f976e8", -0.08, AttributeModifier.Operation.ADDITION));
    }
}

