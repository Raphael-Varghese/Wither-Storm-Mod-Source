/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.projectile.Arrow
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.entity.projectile.Snowball
 *  net.minecraft.world.entity.projectile.SpectralArrow
 *  net.minecraft.world.entity.projectile.ThrownPotion
 *  net.minecraft.world.entity.projectile.ThrownTrident
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraft.world.item.alchemy.PotionUtils
 *  net.minecraft.world.item.alchemy.Potions
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.phys.Vec3
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;

import com.google.common.collect.Lists;
import java.util.Collection;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import org.jetbrains.annotations.NotNull;

public class ThrowingSpell
extends SymbiontSpell {
    public ThrowingSpell(WitheredSymbiontEntity symbiont, SpellType type) {
        super(symbiont, type);
    }

    @Override
    public void start(LivingEntity target) {
        this.projectiles.clear();
    }

    @Override
    public void doCasting(LivingEntity target) {
        if (this.entity.f_19797_ % Math.max(2, this.entity.m_217043_().m_188503_(24 / this.entity.m_9236_().m_46791_().m_19028_())) == 0) {
            int randomProjectile = this.entity.m_217043_().m_188503_(5);
            int randomPotion = this.entity.m_217043_().m_188503_(8);
            int randomArrow = this.entity.m_217043_().m_188503_(12);
            Projectile projectile = this.getRandomProjectile(randomProjectile);
            if (projectile instanceof ThrownPotion) {
                ThrownPotion thrownPotion = (ThrownPotion)projectile;
                projectile.m_146926_(projectile.m_146909_() - -20.0f);
                MobEffectInstance potion = ThrowingSpell.getPotion(randomPotion);
                ItemStack stack = new ItemStack((ItemLike)Items.f_42736_);
                PotionUtils.m_43549_((ItemStack)stack, (Potion)Potions.f_43599_);
                if (potion != null) {
                    PotionUtils.m_43552_((ItemStack)stack, (Collection)Lists.newArrayList((Object[])new MobEffectInstance[]{potion}));
                }
                thrownPotion.m_37446_(stack);
            }
            if (projectile instanceof Arrow) {
                Arrow arrow = (Arrow)projectile;
                if (randomArrow < 6) {
                    arrow.m_36870_(ThrowingSpell.getArrowTip(randomPotion));
                }
            }
            projectile.m_20242_(true);
            double offsetX = this.entity.m_217043_().m_188583_() * 5.0;
            double offsetY = this.entity.m_217043_().m_188500_() * 10.0;
            double offsetZ = this.entity.m_217043_().m_188583_() * 5.0;
            projectile.m_6034_(this.entity.m_20185_() + offsetX, this.entity.m_20188_() + offsetY + 2.0, this.entity.m_20189_() + offsetZ);
            this.entity.m_9236_().m_7967_((Entity)projectile);
            this.projectiles.add(projectile);
        }
        for (Entity entity : this.projectiles) {
            if (!(entity instanceof Projectile)) continue;
            Projectile projectile = (Projectile)entity;
            LivingEntity nearestTarget = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE);
            double x = projectile.m_20185_() + this.entity.m_217043_().m_188583_() * 0.5;
            double y = projectile.m_20188_() + this.entity.m_217043_().m_188583_() * 0.5;
            double z = projectile.m_20189_() + this.entity.m_217043_().m_188583_() * 0.5;
            Vec3 delta = projectile.m_20299_(1.0f).m_82492_(x, y, z).m_82541_().m_82542_(0.1, 0.1, 0.1);
            ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)((SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get()), x, y, z, 0, delta.m_7096_(), delta.m_7098_(), delta.m_7094_(), 1.0);
            if (projectile.m_6084_() && projectile.m_20068_() && projectile instanceof AbstractArrow) {
                Vec3 motion = nearestTarget.m_20182_().m_82546_(projectile.m_20182_()).m_82541_().m_82542_(0.005, 0.005, 0.005);
                projectile.m_20256_(motion);
                projectile.m_8119_();
            }
            if (projectile.f_19797_ != 40 || nearestTarget == null) continue;
            projectile.m_20256_(Vec3.f_82478_);
            double x1 = nearestTarget.m_20185_() - projectile.m_20185_();
            double y1 = nearestTarget.m_20227_(0.3333333333333333) - projectile.m_20186_();
            double z1 = nearestTarget.m_20189_() - projectile.m_20189_();
            double distance = Math.sqrt(x1 * x1 + z1 * z1);
            projectile.m_20242_(false);
            projectile.m_5496_(SoundEvents.f_11687_, 4.0f, 1.0f);
            if (projectile instanceof ThrownPotion) {
                projectile.m_6686_(x1, y1 + distance * 0.2, z1, (float)(0.75 + distance * 0.02), 8.0f);
                continue;
            }
            projectile.m_6686_(x1, y1 + distance * 0.2, z1, (float)((double)1.6f + distance * 0.02), (float)(14 - this.entity.m_9236_().m_46791_().m_19028_() * 4));
        }
    }

    @Nullable
    private static MobEffectInstance getPotion(int randomPotion) {
        switch (randomPotion) {
            case 0: {
                return new MobEffectInstance(MobEffects.f_19615_, 60, 2);
            }
            case 1: {
                return new MobEffectInstance(MobEffects.f_19599_, 400, 2);
            }
            case 2: {
                return new MobEffectInstance(MobEffects.f_19612_, 100, 1);
            }
            case 3: {
                return new MobEffectInstance(MobEffects.f_19590_, 800, 2);
            }
            case 4: {
                return new MobEffectInstance(MobEffects.f_19613_, 100, 1);
            }
            case 5: {
                return new MobEffectInstance(MobEffects.f_19597_, 40, 1);
            }
            case 6: {
                return new MobEffectInstance(MobEffects.f_19610_, 60);
            }
        }
        return null;
    }

    @NotNull
    private Projectile getRandomProjectile(int randomProjectile) {
        return switch (randomProjectile) {
            case 0 -> new ThrownPotion(this.entity.m_9236_(), (LivingEntity)this.entity);
            case 1 -> new Snowball(this.entity.m_9236_(), (LivingEntity)this.entity);
            case 2 -> new Arrow(this.entity.m_9236_(), (LivingEntity)this.entity);
            case 3 -> new SpectralArrow(this.entity.m_9236_(), (LivingEntity)this.entity);
            default -> new ThrownTrident(this.entity.m_9236_(), (LivingEntity)this.entity, new ItemStack((ItemLike)Items.f_42713_));
        };
    }

    @NotNull
    private static MobEffectInstance getArrowTip(int randomArrow) {
        return switch (randomArrow) {
            case 0 -> new MobEffectInstance(MobEffects.f_19610_, 10, 5);
            case 1 -> new MobEffectInstance(MobEffects.f_19597_, 5, 8);
            case 2 -> new MobEffectInstance(MobEffects.f_19613_, 10, 1);
            case 3 -> new MobEffectInstance(MobEffects.f_19612_, 10, 2);
            case 4 -> new MobEffectInstance(MobEffects.f_19599_, 10, 3);
            default -> new MobEffectInstance(MobEffects.f_19604_, 10);
        };
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
        return Math.max(360, random.m_188503_(480)) - Mth.m_14143_((float)modifier) * 10;
    }
}

