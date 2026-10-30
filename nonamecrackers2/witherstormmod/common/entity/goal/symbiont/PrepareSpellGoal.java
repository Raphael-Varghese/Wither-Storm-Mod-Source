/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.Util
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 */
package nonamecrackers2.witherstormmod.common.entity.goal.symbiont;

import java.util.List;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
import nonamecrackers2.witherstormmod.api.common.registry.WitherStormModRegistries;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSymbiontSpellTypes;

public class PrepareSpellGoal
extends Goal {
    protected final WitheredSymbiontEntity entity;

    public PrepareSpellGoal(WitheredSymbiontEntity entity) {
        this.entity = entity;
    }

    public boolean m_8036_() {
        LivingEntity target = this.entity.m_5448_();
        if (target != null && target.m_6084_()) {
            if (this.entity.isCastingSpell()) {
                return false;
            }
            return this.entity.canPickSpell();
        }
        return false;
    }

    public boolean m_8045_() {
        LivingEntity target = this.entity.m_5448_();
        return target != null && target.m_6084_() && this.entity.canPickSpell();
    }

    public void m_8056_() {
        this.entity.setSpell(PrepareSpellGoal.getRandomSpell(this.entity.m_217043_(), this.entity.getSpell()));
        this.entity.getUseSpellGoal().nextAttackTickCount = this.entity.f_19797_ + 40 + this.entity.m_217043_().m_188503_(20);
        this.entity.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_PREPARE_SPELL.get(), 4.0f, 1.0f);
        this.entity.setNextSpellPickCount(400 + this.entity.m_217043_().m_188503_(400) - (this.entity.shouldIncreaseDifficulty() ? 320 : 0));
        if (this.entity.shouldNotGoOverHalfHealth() && this.entity.m_21223_() / this.entity.m_21233_() <= 0.5f) {
            this.entity.setHalfHealthLimit(false);
        }
    }

    public static SpellType getRandomSpell(RandomSource random, SpellType toRemove) {
        return (SpellType)Util.m_214621_((List)WitherStormModRegistries.SPELL_TYPES.get().getValues().stream().filter(s -> s != WitherStormModSymbiontSpellTypes.EMPTY.get() && s != toRemove).toList(), (RandomSource)random);
    }
}

