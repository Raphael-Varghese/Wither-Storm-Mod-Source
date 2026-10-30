/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.projectile.Arrow
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;

public class ArrowsSpell
extends SymbiontSpell {
    public ArrowsSpell(WitheredSymbiontEntity entity, SpellType type) {
        super(entity, type);
    }

    @Override
    public void start(LivingEntity target) {
        for (Entity projectile : this.projectiles) {
            projectile.m_146870_();
        }
        this.projectiles.clear();
    }

    @Override
    public void doCasting(LivingEntity target) {
        int timer;
        int count = this.entity.shouldIncreaseDifficulty() ? 6 : 3;
        int n = timer = this.entity.shouldIncreaseDifficulty() ? 10 : 20;
        if (this.entity.f_19797_ % 4 == 0) {
            for (int i = 0; i < count; ++i) {
                Arrow arrow = new Arrow(this.entity.m_9236_(), (LivingEntity)this.entity);
                arrow.m_20242_(true);
                double deltaX = this.entity.m_217043_().m_188583_() * 0.55;
                double deltaY = this.entity.m_217043_().m_188500_() * 0.75;
                double deltaZ = this.entity.m_217043_().m_188583_() * 0.55;
                arrow.m_20334_(deltaX, deltaY, deltaZ);
                Vec2 rot = this.getRot(new Vec3(deltaX, deltaY, deltaZ));
                arrow.m_146926_(rot.f_82470_);
                arrow.m_146922_(rot.f_82471_);
                this.entity.m_9236_().m_7967_((Entity)arrow);
                this.projectiles.add(arrow);
            }
            this.entity.m_5496_(SoundEvents.f_11796_, 4.0f, 0.5f + (this.entity.m_217043_().m_188501_() - 0.5f) * 0.1f);
        }
        for (Entity projectile : this.projectiles) {
            LivingEntity nearestTarget;
            AbstractArrow arrow = (AbstractArrow)projectile;
            if (projectile.m_6084_() && projectile.f_19797_ == timer && (nearestTarget = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE)) != null) {
                double x = nearestTarget.m_20185_() + this.entity.m_217043_().m_188583_() * 2.0 - arrow.m_20185_();
                double y = nearestTarget.m_20227_(0.3333333333333333) + this.entity.m_217043_().m_188583_() * 0.5 - arrow.m_20186_();
                double z = nearestTarget.m_20189_() + this.entity.m_217043_().m_188583_() * 2.0 - arrow.m_20189_();
                double d0 = Math.sqrt(x * x + z * z);
                arrow.m_6686_(x, y + d0 * 0.2, z, 2.5f, (float)(14 - this.entity.m_9236_().m_46791_().m_19028_() * 4));
                arrow.m_20242_(false);
                arrow.m_5496_(SoundEvents.f_11687_, 2.0f, 1.0f);
            }
            if (!projectile.m_6084_() || projectile.f_19797_ >= timer) continue;
            double x = projectile.m_20185_() + this.entity.m_217043_().m_188583_() * 0.5;
            double y = projectile.m_20188_() + this.entity.m_217043_().m_188583_() * 0.5;
            double z = projectile.m_20189_() + this.entity.m_217043_().m_188583_() * 0.5;
            Vec3 delta = projectile.m_20299_(1.0f).m_82492_(x, y, z).m_82541_().m_82542_(0.1, 0.1, 0.1);
            ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)((SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get()), x, y, z, 0, delta.m_7096_(), delta.m_7098_(), delta.m_7094_(), 1.0);
        }
    }

    @Override
    public void cast(LivingEntity target) {
    }

    @Override
    public void finish() {
        for (Entity projectile : this.projectiles) {
            double x = projectile.m_20185_() + this.entity.m_217043_().m_188583_() * 0.5;
            double y = projectile.m_20188_() + this.entity.m_217043_().m_188583_() * 0.5;
            double z = projectile.m_20189_() + this.entity.m_217043_().m_188583_() * 0.5;
            Vec3 delta = projectile.m_20299_(1.0f).m_82492_(x, y, z).m_82541_().m_82542_(0.1, 0.1, 0.1);
            ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)ParticleTypes.f_123755_, x, y, z, 0, delta.m_7096_(), delta.m_7098_(), delta.m_7094_(), 1.0);
            projectile.m_146870_();
        }
    }

    @Override
    public int getDelay(RandomSource random, float modifier) {
        return Math.max(160, random.m_188503_(200)) - Mth.m_14143_((float)modifier) * 10;
    }

    private Vec2 getRot(Vec3 delta) {
        float f = Mth.m_14116_((float)((float)delta.m_165925_()));
        float xRot = (float)(Mth.m_14136_((double)delta.f_82480_, (double)f) * 57.2957763671875);
        float yRot = (float)(Mth.m_14136_((double)delta.f_82479_, (double)delta.f_82481_) * 57.2957763671875);
        return new Vec2(xRot, yRot);
    }
}

