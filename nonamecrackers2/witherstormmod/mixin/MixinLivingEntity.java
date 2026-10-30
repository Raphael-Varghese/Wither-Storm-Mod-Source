/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.boss.wither.WitherBoss
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.level.Level
 *  org.apache.commons.lang3.mutable.MutableBoolean
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package nonamecrackers2.witherstormmod.mixin;

import java.util.List;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import nonamecrackers2.witherstormmod.common.accessor.LivingEntityAccessor;
import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCriteriaTriggers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModEntityTags;
import nonamecrackers2.witherstormmod.common.util.BrainInjectionHelper;
import nonamecrackers2.witherstormmod.common.util.PhlegmGravestoneHelper;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={LivingEntity.class})
public abstract class MixinLivingEntity
extends Entity
implements LivingEntityAccessor {
    @Unique
    private boolean hasDeathProtection;

    private MixinLivingEntity() {
        super(null, null);
        throw new UnsupportedOperationException();
    }

    @Inject(method={"<init>"}, at={@At(value="TAIL")})
    public void constructorTail(EntityType<? extends LivingEntity> type, Level level, CallbackInfo ci) {
        BrainInjectionHelper.inject((LivingEntity)this);
    }

    @Inject(method={"dropAllDeathLoot"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;dropExperience()V")}, cancellable=true)
    public void witherstormmod$preventDrops_dropAllDeathLoot(DamageSource source, CallbackInfo ci) {
        MutableBoolean flag = new MutableBoolean();
        PhlegmGravestoneHelper.findPotentialPhlegmClusterPos((LivingEntity)this, source).ifPresent(pos -> {
            List items = this.captureDrops().stream().map(ItemEntity::m_32055_).toList();
            if (!items.isEmpty()) {
                PhlegmGravestoneHelper.spawnForEntity((LivingEntity)this, pos, items);
                this.captureDrops(null);
                flag.setTrue();
            }
        });
        if (flag.getValue().booleanValue()) {
            ci.cancel();
        }
    }

    @Inject(method={"checkTotemDeathProtection"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;setHealth(F)V")})
    public void witherstormmod$setHasDeathProtection_checkTotemDeathProtection(DamageSource damageSource, CallbackInfoReturnable<Boolean> ci) {
        this.hasDeathProtection = true;
    }

    @Inject(method={"checkTotemDeathProtection"}, at={@At(value="TAIL")}, cancellable=true)
    public void witherstormmod$evolveWitherStormIfDying_checkTotemDeathProtection(DamageSource damageSource, CallbackInfoReturnable<Boolean> ci) {
        float health;
        WitherStormEntity storm;
        MixinLivingEntity mixinLivingEntity = this;
        if (mixinLivingEntity instanceof WitherStormEntity && (storm = (WitherStormEntity)((Object)mixinLivingEntity)).isCompletelyInvulnerable() && storm.getPhase() < 4 && (health = storm.m_21223_() / storm.m_21233_()) <= 0.1f) {
            storm.evolveToPhase(4);
            storm.m_21153_(storm.m_21233_());
            ci.setReturnValue((Object)true);
            Entity entity = damageSource.m_7639_();
            if (entity instanceof ServerPlayer) {
                ServerPlayer player = (ServerPlayer)entity;
                WitherStormModCriteriaTriggers.NEARLY_KILL_WITHER_STORM.trigger(player, storm);
            }
        }
    }

    @Inject(method={"removeAllEffects"}, at={@At(value="RETURN")})
    public void witherstormmod$resetHasDeathProtection_removeAllEffects(CallbackInfoReturnable<Boolean> ci) {
        if (!this.m_9236_().f_46443_) {
            this.hasDeathProtection = false;
        }
    }

    @Inject(method={"canAttack"}, at={@At(value="HEAD")}, cancellable=true)
    public void witherstormmod$preventCertainMobsFromAttackingSickenedMobs_canAttack(LivingEntity entity, CallbackInfoReturnable<Boolean> ci) {
        if (this instanceof WitherBoss && (entity.m_6095_().m_204039_(WitherStormModEntityTags.SICKENED_MOBS) || entity instanceof WitherSickened || entity.m_6336_() == WitherStormModMobTypes.SICKENED)) {
            ci.setReturnValue((Object)false);
        }
    }

    @Override
    public void setHasDeathProtection(boolean flag) {
        this.hasDeathProtection = flag;
    }

    @Override
    public boolean hasDeathProtection() {
        return this.hasDeathProtection;
    }
}

