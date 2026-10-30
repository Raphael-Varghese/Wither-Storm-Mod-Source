/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.player.Player
 */
package nonamecrackers2.witherstormmod.common.entity.goal.symbiont;

import java.util.EnumSet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;

public class UseSpellGoal
extends Goal {
    protected final WitheredSymbiontEntity entity;
    public int nextAttackTickCount;

    public UseSpellGoal(WitheredSymbiontEntity entity) {
        this.entity = entity;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        LivingEntity target = this.entity.m_5448_();
        if (target != null && target.m_6084_()) {
            if (this.entity.isCastingSpell()) {
                return false;
            }
            if (!this.entity.hasSpell()) {
                return false;
            }
            if (this.entity.getSpellInstance() == null) {
                return false;
            }
            return this.entity.f_19797_ > this.nextAttackTickCount;
        }
        return false;
    }

    public boolean m_8045_() {
        LivingEntity target = this.entity.m_5448_();
        return target != null && target.m_6084_() && this.entity.hasSpell() && this.entity.f_19797_ >= this.nextAttackTickCount || this.entity.isCastingSpell();
    }

    public void m_8056_() {
        float effectiveDifficulty = this.entity.m_9236_().m_6436_(this.entity.m_20183_()).m_19056_();
        int spellDelay = this.entity.getSpellInstance().getDelay(this.entity.m_217043_(), effectiveDifficulty + (float)(this.entity.shouldIncreaseDifficulty() ? 60 : 0));
        if (spellDelay < this.entity.getSpell().spellTime() + 10) {
            spellDelay = this.entity.getSpell().spellTime() + 10;
        }
        this.nextAttackTickCount = this.entity.f_19797_ + spellDelay;
        this.entity.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_CAST_SPELL.get(), 4.0f, 1.0f);
        this.entity.beginSpellCasting();
        if (this.entity.m_5448_() instanceof Player) {
            this.entity.spellUsed();
        }
    }
}

