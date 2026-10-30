/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;

public class SmashSpell
extends SymbiontSpell {
    private int stompCount = 0;
    private int stompCooldown = 0;

    public SmashSpell(WitheredSymbiontEntity symbiont, SpellType type) {
        super(symbiont, type);
    }

    @Override
    public void start(LivingEntity target) {
        RandomSource random = this.entity.m_217043_();
        this.stompCount = random.m_188503_(3) + 1;
    }

    @Override
    public void cast(LivingEntity target) {
        if (this.entity.m_20075_().m_60734_() == Blocks.f_50016_) {
            return;
        }
        this.stomp(target);
    }

    private void stomp(LivingEntity target) {
        if (this.entity.m_20075_().m_60734_() == Blocks.f_50016_) {
            return;
        }
        float f = this.entity.m_6118_() * 5.0f;
        if (this.entity.m_21023_(MobEffects.f_19603_)) {
            f += 0.1f * (float)(this.entity.m_21124_(MobEffects.f_19603_).m_19564_() + 1);
        }
        Vec3 prevDelta = this.entity.m_20184_();
        double x = this.entity.m_20185_() - target.m_20185_();
        double z = this.entity.m_20189_() - target.m_20189_();
        double multiplier = Math.min(0.2, (double)this.entity.m_20270_((Entity)target) * 0.05);
        Vec3 delta = new Vec3(x, (double)f, z).m_82542_(-multiplier, 1.0, -multiplier);
        delta.m_82520_(prevDelta.f_82479_, 0.0, prevDelta.f_82481_);
        this.entity.m_20256_(delta);
        this.entity.setSmashing(true);
        this.entity.m_5496_(this.entity.m_20075_().m_60827_().m_56775_(), 4.0f, 1.0f);
    }

    @Override
    public void doCasting(LivingEntity target) {
        if (this.entity.shouldIncreaseDifficulty()) {
            ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)ParticleTypes.f_123744_, this.entity.m_20185_() + (double)this.entity.m_217043_().m_188501_(), this.entity.m_20186_(), this.entity.m_20189_() + (double)this.entity.m_217043_().m_188501_(), 1, 0.0, 0.0, 0.0, 0.0);
        }
        if (this.stompCount > 0 && this.stompCooldown <= 0) {
            this.stomp(target);
            --this.stompCount;
            this.stompCooldown = 5;
            if (this.stompCount > 0) {
                LivingEntity newTarget = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE);
                this.cast(newTarget);
            }
        } else {
            --this.stompCooldown;
        }
    }

    @Override
    public int getDelay(RandomSource random, float modifier) {
        return Math.max(160, random.m_188503_(200)) - Mth.m_14143_((float)modifier) * 10;
    }
}

