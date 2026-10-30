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
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.phys.Vec3
 *  org.jetbrains.annotations.NotNull
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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
import nonamecrackers2.witherstormmod.common.entity.SickenedCreeper;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import org.jetbrains.annotations.NotNull;

public class BombingSpell
extends SymbiontSpell {
    public BombingSpell(WitheredSymbiontEntity symbiont, SpellType type) {
        super(symbiont, type);
    }

    @Override
    public void start(LivingEntity target) {
        double x = this.entity.m_20185_() + this.entity.m_217043_().m_188583_() * 1.0;
        double y = this.entity.m_20188_() + 1.0;
        double z = this.entity.m_20189_() + this.entity.m_217043_().m_188583_() * 1.0;
        SickenedCreeper livingProjectile = new SickenedCreeper((EntityType<? extends SickenedCreeper>)((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get()), this.entity.m_9236_());
        livingProjectile.m_20242_(true);
        livingProjectile.m_6034_(x, y, z);
        livingProjectile.m_20334_(0.0, this.entity.m_217043_().m_188500_() * 0.07, 0.0);
        this.entity.m_9236_().m_7967_((Entity)livingProjectile);
        this.projectiles.add(livingProjectile);
        livingProjectile.m_6710_(target);
        this.entity.m_5496_(SoundEvents.f_11836_, 4.0f, 0.75f);
    }

    @Override
    public void cast(@NotNull LivingEntity target) {
        for (Entity livingProjectile : this.projectiles) {
            SickenedCreeper projectile = (SickenedCreeper)livingProjectile;
            LivingEntity targetEntity = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE);
            double x = targetEntity.m_20185_() - projectile.m_20185_();
            double y = this.entity.m_217043_().m_188583_() * 4.0 + targetEntity.m_20227_(0.34) - projectile.m_20186_();
            double z = targetEntity.m_20189_() - projectile.m_20189_();
            double distance = Math.sqrt(x * x + y * y + z * z);
            projectile.m_20334_(x / (distance * 0.3), y / (distance * 0.25) + 1.0, z / (distance * 0.3));
            projectile.m_20242_(false);
            projectile.m_6710_(targetEntity);
            projectile.m_32312_();
        }
        this.entity.m_5496_(SoundEvents.f_11837_, 4.0f, 1.0f);
    }

    @Override
    public void doCasting(@NotNull LivingEntity target) {
        for (Entity livingProjectile : this.projectiles) {
            if (!livingProjectile.m_6084_()) continue;
            double x = livingProjectile.m_20185_() + this.entity.m_217043_().m_188583_() * 1.0;
            double y = livingProjectile.m_20188_() + this.entity.m_217043_().m_188583_() * 1.0;
            double z = livingProjectile.m_20189_() + this.entity.m_217043_().m_188583_() * 1.0;
            Vec3 delta = livingProjectile.m_20299_(1.0f).m_82492_(x, y, z).m_82541_().m_82542_(0.1, 0.1, 0.1);
            ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)((SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get()), x, y, z, 0, delta.m_7096_(), delta.m_7098_(), delta.m_7094_(), 1.0);
            ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)ParticleTypes.f_123755_, livingProjectile.m_20185_(), livingProjectile.m_20186_(), livingProjectile.m_20189_(), 0, delta.m_7096_(), delta.m_7098_(), delta.m_7094_(), 0.125);
        }
    }

    @Override
    public void finish() {
        if (this.entity.m_5448_() == null) {
            for (Entity livingProjectile : this.projectiles) {
                livingProjectile.m_146870_();
            }
        }
    }

    @Override
    public int getDelay(RandomSource random, float modifier) {
        return Math.max(360, random.m_188503_(600)) - Mth.m_14143_((float)modifier) * 10;
    }
}

