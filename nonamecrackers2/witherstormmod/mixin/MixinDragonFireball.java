/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.AreaEffectCloud
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.AbstractHurtingProjectile
 *  net.minecraft.world.entity.projectile.DragonFireball
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.HitResult
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package nonamecrackers2.witherstormmod.mixin;

import java.util.List;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import nonamecrackers2.witherstormmod.common.util.DragonFireballAccessor;
import nonamecrackers2.witherstormmod.mixin.MixinAreaEffectCloud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={DragonFireball.class})
public class MixinDragonFireball
extends AbstractHurtingProjectile
implements DragonFireballAccessor {
    @Unique
    private static final EntityDataAccessor<Boolean> CREATED_FROM_SYMBIONT = SynchedEntityData.m_135353_(MixinDragonFireball.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);

    private MixinDragonFireball(EntityType<? extends AbstractHurtingProjectile> type, Level level) {
        super(type, level);
        throw new UnsupportedOperationException();
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(CREATED_FROM_SYMBIONT, (Object)false);
    }

    @Inject(method={"onHit"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/AreaEffectCloud;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)V", shift=At.Shift.AFTER)}, locals=LocalCapture.CAPTURE_FAILHARD)
    public void witherstormmod$addMobEffects_onHit(HitResult result, CallbackInfo ci, List<LivingEntity> list, AreaEffectCloud areaEffectCloud, Entity entity) {
        if (this.createdBySymbiont()) {
            ((MixinAreaEffectCloud)areaEffectCloud).witherstormmod$getEffects().clear();
            areaEffectCloud.m_19724_((ParticleOptions)ParticleTypes.f_123762_);
            areaEffectCloud.m_19712_(2.5f);
            areaEffectCloud.m_19734_(120);
            areaEffectCloud.m_19716_(new MobEffectInstance(MobEffects.f_19615_, 60, 2));
            areaEffectCloud.m_19738_((10.0f - areaEffectCloud.m_19743_()) / (float)areaEffectCloud.m_19748_());
        }
    }

    @Inject(method={"getTrailParticle"}, at={@At(value="HEAD")}, cancellable=true)
    public void witherstormmod$useCustomTrailParticle_getTrailParticle(CallbackInfoReturnable<ParticleOptions> ci) {
        if (this.createdBySymbiont()) {
            ci.setReturnValue((Object)((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get()));
        }
    }

    @Override
    public boolean createdBySymbiont() {
        return (Boolean)this.f_19804_.m_135370_(CREATED_FROM_SYMBIONT);
    }

    @Override
    public void setCreatedBySymbiont(boolean flag) {
        this.f_19804_.m_135381_(CREATED_FROM_SYMBIONT, (Object)flag);
    }

    public void m_7380_(CompoundTag tag) {
        super.m_7380_(tag);
        if (this.createdBySymbiont()) {
            tag.m_128379_("CreatedBySymbiont", this.createdBySymbiont());
        }
    }

    public void m_7378_(CompoundTag tag) {
        super.m_7378_(tag);
        if (tag.m_128425_("CreatedBySymbiont", 1)) {
            this.setCreatedBySymbiont(tag.m_128471_("CreatedBySymbiont"));
        }
    }
}

