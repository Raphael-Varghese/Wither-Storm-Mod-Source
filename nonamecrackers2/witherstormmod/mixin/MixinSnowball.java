/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.Snowball
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.util.SnowballAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Snowball.class})
public class MixinSnowball
implements SnowballAccessor {
    @Unique
    private boolean givesWitherEffect;

    @Override
    public void setWitherEffect(boolean flag) {
        this.givesWitherEffect = flag;
    }

    @Override
    public boolean hasWitherEffect() {
        return this.givesWitherEffect;
    }

    @Inject(method={"onHitEntity"}, at={@At(value="HEAD")})
    public void onHitEntity(EntityHitResult result, CallbackInfo callbackInfo) {
        Level level;
        Entity entity = result.m_82443_();
        if (this.givesWitherEffect && entity instanceof LivingEntity) {
            ((LivingEntity)entity).m_7292_(new MobEffectInstance(MobEffects.f_19615_, 150, 1));
        }
        if (!entity.m_9236_().m_5776_() && (level = entity.m_9236_()) instanceof ServerLevel) {
            ServerLevel level2 = (ServerLevel)level;
            if (this.givesWitherEffect) {
                for (int i = 0; i < 5; ++i) {
                    double x = entity.m_20185_() + level2.m_213780_().m_188583_() * 0.5;
                    double y = entity.m_20188_() + level2.m_213780_().m_188583_() * 0.5;
                    double z = entity.m_20189_() + level2.m_213780_().m_188583_() * 0.5;
                    Vec3 delta = entity.m_20299_(1.0f).m_82492_(x, y, z).m_82541_().m_82542_(0.1, 0.1, 0.1);
                    level2.m_8767_((ParticleOptions)ParticleTypes.f_123755_, x, y, z, 0, delta.m_7096_(), delta.m_7098_(), delta.m_7094_(), 1.0);
                }
            }
        }
    }
}

