/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.TraceableEntity
 *  net.minecraft.world.level.Level
 */
package nonamecrackers2.witherstormmod.common.entity;

import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.level.Level;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;

public class TentacleSpike
extends Entity
implements TraceableEntity {
    @Nullable
    private UUID ownerUUID;
    @Nullable
    private LivingEntity owner;
    private boolean sentSpikeEvent;
    private int warmupDelayTicks;
    private int lifeTicks = 22;
    private boolean clientSideAttackStarted;
    private float damageModifier;

    public TentacleSpike(EntityType<? extends TentacleSpike> type, Level level) {
        super(type, level);
    }

    public TentacleSpike(Level level, double x, double y, double z, float angle, int warmupDelay, LivingEntity owner, float damageModifier) {
        this((EntityType<? extends TentacleSpike>)((EntityType)WitherStormModEntityTypes.TENTACLE_SPIKE.get()), level);
        this.warmupDelayTicks = warmupDelay;
        this.setOwner(owner);
        this.m_146922_(angle * 57.295776f);
        this.m_6034_(x, y, z);
        this.damageModifier = damageModifier;
    }

    protected void m_8097_() {
    }

    public void setOwner(@Nullable LivingEntity entity) {
        this.owner = entity;
        this.ownerUUID = entity == null ? null : entity.m_20148_();
    }

    @Nullable
    public LivingEntity getOwner() {
        ServerLevel level;
        Entity entity;
        Level level2;
        if (this.owner == null && this.ownerUUID != null && (level2 = this.m_9236_()) instanceof ServerLevel && (entity = (level = (ServerLevel)level2).m_8791_(this.ownerUUID)) instanceof LivingEntity) {
            this.owner = (LivingEntity)entity;
        }
        return this.owner;
    }

    protected void m_7378_(CompoundTag tag) {
        this.warmupDelayTicks = tag.m_128451_("WarmupDelay");
        if (tag.m_128403_("Owner")) {
            this.ownerUUID = tag.m_128342_("Owner");
        }
        this.damageModifier = tag.m_128457_("DamageModifier");
    }

    protected void m_7380_(CompoundTag tag) {
        tag.m_128405_("WarmupDelay", this.warmupDelayTicks);
        if (this.ownerUUID != null) {
            tag.m_128362_("Owner", this.ownerUUID);
        }
        tag.m_128350_("DamageModifier", this.damageModifier);
    }

    public void m_8119_() {
        super.m_8119_();
        if (this.m_9236_().f_46443_) {
            if (this.clientSideAttackStarted) {
                --this.lifeTicks;
                if (this.lifeTicks == 20) {
                    for (int i = 0; i < 12; ++i) {
                        double d0 = this.m_20185_() + (this.f_19796_.m_188500_() * 2.0 - 1.0) * (double)this.m_20205_() * 0.5;
                        double d1 = this.m_20186_() + 0.05 + this.f_19796_.m_188500_() * 2.0;
                        double d2 = this.m_20189_() + (this.f_19796_.m_188500_() * 2.0 - 1.0) * (double)this.m_20205_() * 0.5;
                        double d3 = (this.f_19796_.m_188500_() * 2.0 - 1.0) * 0.3;
                        double d4 = 0.3 + this.f_19796_.m_188500_() * 0.3;
                        double d5 = (this.f_19796_.m_188500_() * 2.0 - 1.0) * 0.3;
                        this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123797_, d0, d1, d2, d3, d4, d5);
                    }
                }
            }
        } else if (--this.warmupDelayTicks < 0) {
            if (this.warmupDelayTicks == -2) {
                for (LivingEntity entity : this.m_9236_().m_45976_(LivingEntity.class, this.m_20191_().m_82377_(0.6, 0.0, 0.6))) {
                    this.dealDamageTo(entity);
                }
            }
            if (!this.sentSpikeEvent) {
                this.m_9236_().m_7605_((Entity)this, (byte)4);
                this.sentSpikeEvent = true;
            }
            if (--this.lifeTicks < 0) {
                this.m_146870_();
            }
        }
    }

    private void dealDamageTo(LivingEntity entity) {
        LivingEntity owner = this.getOwner();
        if (entity.m_6084_() && !entity.m_20147_() && entity != owner) {
            if (owner == null) {
                entity.m_6469_(this.m_269291_().m_269425_(), 6.0f);
            } else {
                if (owner.m_7307_((Entity)entity)) {
                    return;
                }
                entity.m_6469_(this.m_269291_().m_269104_((Entity)this, (Entity)owner), 6.0f + this.damageModifier);
            }
        }
    }

    public void m_7822_(byte event) {
        super.m_7822_(event);
        if (event == 4) {
            this.clientSideAttackStarted = true;
            if (!this.m_20067_()) {
                this.m_9236_().m_7785_(this.m_20185_(), this.m_20186_(), this.m_20189_(), (SoundEvent)WitherStormModSoundEvents.TENTACLE_SPIKE_STAB.get(), this.m_5720_(), 1.0f, this.f_19796_.m_188501_() * 0.2f + 0.85f, false);
            }
        }
    }

    public float getAnimationProgress(float partialTick) {
        if (!this.clientSideAttackStarted) {
            return 0.0f;
        }
        int i = this.lifeTicks - 2;
        return i <= 0 ? 1.0f : 1.0f - ((float)i - partialTick) / 20.0f;
    }
}

