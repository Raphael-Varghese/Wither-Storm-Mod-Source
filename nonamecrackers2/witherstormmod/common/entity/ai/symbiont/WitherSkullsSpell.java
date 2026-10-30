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
 *  net.minecraft.world.entity.projectile.WitherSkull
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
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;

public class WitherSkullsSpell
extends SymbiontSpell {
    public WitherSkullsSpell(WitheredSymbiontEntity symbiont, SpellType type) {
        super(symbiont, type);
    }

    @Override
    public void cast(LivingEntity target) {
    }

    @Override
    public void doCasting(LivingEntity target) {
        if (this.entity.f_19797_ % (this.entity.shouldIncreaseDifficulty() ? 10 : 20) == 0) {
            int spellCastingTime = this.type.spellTime() - this.entity.getSpellCastingTime();
            double radius = 2.0;
            float angle = (float)spellCastingTime * 0.08f;
            float angleBetweenTarget = (float)Math.atan2(target.m_20185_() - this.entity.m_20185_(), target.m_20189_() - this.entity.m_20189_());
            float offset = 1.5707964f;
            double xOffset = (double)Mth.m_14089_((float)(offset - angleBetweenTarget)) * radius;
            double zOffset = (double)Mth.m_14031_((float)(offset - angleBetweenTarget)) * radius;
            double rawX = (double)(Mth.m_14089_((float)angle) * Mth.m_14089_((float)(offset * 2.0f - angleBetweenTarget))) * radius;
            double rawY = (double)Mth.m_14031_((float)angle) * radius;
            double rawZ = (double)(Mth.m_14089_((float)angle) * Mth.m_14031_((float)(offset * 2.0f - angleBetweenTarget))) * radius;
            double x = xOffset + rawX + this.entity.m_20185_();
            double y = this.entity.m_20188_() + rawY;
            double z = zOffset + rawZ + this.entity.m_20189_();
            Vec3 skullPose = new Vec3(x, y, z);
            Vec3 delta = skullPose.m_82546_(target.m_20299_(1.0f)).m_82541_().m_82490_(-2.5);
            WitherSkull skull = new WitherSkull(this.entity.m_9236_(), (LivingEntity)this.entity, delta.m_7096_(), delta.m_7098_(), delta.m_7094_());
            skull.m_6034_(x, y, z);
            if (this.entity.m_217043_().m_188503_(11) == 1) {
                skull.m_37629_(true);
            }
            this.entity.m_9236_().m_7967_((Entity)skull);
            skull.m_5496_(SoundEvents.f_12558_, 4.0f, 1.0f);
            ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)ParticleTypes.f_123755_, x, y, z, 20, this.entity.m_217043_().m_188583_(), this.entity.m_217043_().m_188583_(), this.entity.m_217043_().m_188583_(), 0.01);
            ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)((SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get()), x, y, z, 20, this.entity.m_217043_().m_188583_(), this.entity.m_217043_().m_188583_(), this.entity.m_217043_().m_188583_(), 0.2);
        }
    }

    @Override
    public int getDelay(RandomSource random, float modifier) {
        return Math.max(440, random.m_188503_(580)) - Mth.m_14143_((float)modifier) * 10;
    }
}

