/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.LivingEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={MobEffect.class})
public class MixinMobEffect {
    @Redirect(method={"applyInstantenousEffect"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;heal(F)V"))
    public void redirectHeal(LivingEntity entity, float amount) {
        if (!(entity instanceof CommandBlockEntity)) {
            entity.m_5634_(amount);
        }
    }

    @Redirect(method={"applyInstantenousEffect"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    public boolean redirectHurt(LivingEntity entity, DamageSource source, float amount) {
        if (entity.m_6336_() == WitherStormModMobTypes.SICKENED && !(entity instanceof WitherStormEntity)) {
            return false;
        }
        if (!(entity instanceof WitherStormEntity) || ((WitherStormEntity)entity).getPhase() < 3) {
            return entity.m_6469_(source, amount);
        }
        return false;
    }
}

