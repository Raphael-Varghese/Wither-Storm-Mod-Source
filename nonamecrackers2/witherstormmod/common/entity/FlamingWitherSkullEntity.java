/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.AbstractHurtingProjectile
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.SwordItem
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.entity.IEntityAdditionalSpawnData
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.network.NetworkHooks
 *  net.minecraftforge.network.PacketDistributor
 *  net.minecraftforge.network.PacketDistributor$TargetPoint
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
import nonamecrackers2.witherstormmod.common.packet.UpdateDamagingProjectileMessage;
import org.jetbrains.annotations.NotNull;

public class FlamingWitherSkullEntity
extends AbstractHurtingProjectile
implements IEntityAdditionalSpawnData {
    public FlamingWitherSkullEntity(EntityType<? extends FlamingWitherSkullEntity> type, Level world) {
        super(type, world);
    }

    public FlamingWitherSkullEntity(EntityType<? extends FlamingWitherSkullEntity> type, Level world, LivingEntity owner, double x, double y, double z) {
        super(type, owner, x, y, z, world);
        this.f_36813_ = x * 0.1;
        this.f_36814_ = y * 0.1;
        this.f_36815_ = z * 0.1;
    }

    public FlamingWitherSkullEntity(Level world, LivingEntity owner, double x, double y, double z) {
        this((EntityType<? extends FlamingWitherSkullEntity>)((EntityType)WitherStormModEntityTypes.FLAMING_WITHER_SKULL.get()), world, owner, x, y, z);
    }

    protected float m_6884_() {
        return 0.9f;
    }

    public boolean m_6060_() {
        return true;
    }

    public boolean m_6051_() {
        return false;
    }

    @NotNull
    protected ParticleOptions m_5967_() {
        return WitherStormMod.isAprilFools() && (Boolean)WitherStormModConfig.CLIENT.aprilFools.get() != false ? ParticleTypes.f_123750_ : super.m_5967_();
    }

    protected ParticleOptions getParticle() {
        return ParticleTypes.f_123744_;
    }

    public void m_8119_() {
        super.m_8119_();
        Vec3 vec3 = this.m_20184_();
        double d0 = this.m_20185_() + vec3.f_82479_ + (double)this.f_19796_.m_188501_();
        double d1 = this.m_20186_() + vec3.f_82480_ + (double)this.f_19796_.m_188501_();
        double d2 = this.m_20189_() + vec3.f_82481_ + (double)this.f_19796_.m_188501_();
        this.m_9236_().m_7106_(this.getParticle(), d0 - 0.5, d1, d2 - 0.5, 0.0, 0.0, 0.0);
    }

    protected void m_5790_(@NotNull EntityHitResult ray) {
        super.m_5790_(ray);
        if (!this.m_9236_().f_46443_) {
            boolean flag;
            LivingEntity living;
            ItemStack use;
            Entity victim = ray.m_82443_();
            if (victim instanceof LivingEntity && (use = (living = (LivingEntity)victim).m_21211_()).m_150930_(Items.f_42740_)) {
                this.explodeAndDiscard();
                return;
            }
            Entity owner = this.m_19749_();
            if (owner instanceof LivingEntity) {
                LivingEntity livingOwner = (LivingEntity)owner;
                flag = victim.m_6469_(FlamingWitherSkullEntity.damageSource(this, (Entity)livingOwner), 10.0f);
                if (flag) {
                    if (victim.m_6084_()) {
                        this.m_19970_(livingOwner, victim);
                    } else {
                        livingOwner.m_5634_(10.0f);
                    }
                }
            } else {
                flag = victim.m_6469_(this.m_269291_().m_269425_(), 8.0f);
            }
            if (flag && victim instanceof LivingEntity) {
                int i = 0;
                if (this.m_9236_().m_46791_() == Difficulty.NORMAL) {
                    i = 10;
                } else if (this.m_9236_().m_46791_() == Difficulty.HARD) {
                    i = 40;
                }
                if (i > 0) {
                    ((LivingEntity)victim).m_7292_(new MobEffectInstance(MobEffects.f_19615_, 180, 1));
                }
            }
        }
    }

    protected void m_6532_(@NotNull HitResult ray) {
        super.m_6532_(ray);
        if (!this.m_9236_().f_46443_ && ray.m_6662_() != HitResult.Type.ENTITY) {
            this.explodeAndDiscard();
        }
    }

    protected void explodeAndDiscard() {
        boolean flag = ForgeEventFactory.getMobGriefingEvent((Level)this.m_9236_(), (Entity)this.m_19749_());
        this.m_5496_((SoundEvent)WitherStormModSoundEvents.FLAMING_SKULL_IMPACT.get(), 6.0f, (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * -0.2f + 1.0f);
        WitherStormModPacketHandlers.MAIN.send(PacketDistributor.NEAR.with(PacketDistributor.TargetPoint.p((double)this.m_20182_().f_82479_, (double)this.m_20182_().f_82480_, (double)this.m_20182_().f_82481_, (double)45.0, (ResourceKey)this.m_9236_().m_46472_())), (Object)new ShakeScreenMessage(20.0f, 4.0f));
        this.m_9236_().m_255391_((Entity)this, this.m_20185_(), this.m_20186_(), this.m_20189_(), (float)((Double)WitherStormModConfig.SERVER.flamingSkullExplosionSize.get() + (double)this.f_19796_.m_188503_(2)), flag, Level.ExplosionInteraction.MOB);
        this.m_146870_();
    }

    public boolean m_6469_(@NotNull DamageSource source, float amount) {
        LivingEntity entity;
        ItemStack item;
        Entity entity2 = source.m_7639_();
        if (entity2 instanceof LivingEntity && !(item = (entity = (LivingEntity)entity2).m_21205_()).m_41619_() && item.m_41720_() instanceof SwordItem) {
            boolean flag = super.m_6469_(source, amount);
            if (flag && !this.m_9236_().m_5776_()) {
                item.m_41622_(120 + this.f_19796_.m_188503_(140), entity, e -> e.m_21190_(InteractionHand.MAIN_HAND));
                WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), (Object)new UpdateDamagingProjectileMessage(this));
            }
            return flag;
        }
        return false;
    }

    protected boolean m_5931_() {
        return false;
    }

    public static DamageSource damageSource(FlamingWitherSkullEntity cause, Entity entity) {
        return WitherStormModDamageTypes.source(cause.m_9236_().m_9598_(), WitherStormModDamageTypes.FLAMING_WITHER_SKULL, (Entity)cause, entity);
    }

    @NotNull
    public Packet<ClientGamePacketListener> m_5654_() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    public void writeSpawnData(FriendlyByteBuf buffer) {
        buffer.writeDouble(this.f_36813_);
        buffer.writeDouble(this.f_36814_);
        buffer.writeDouble(this.f_36815_);
    }

    public void readSpawnData(FriendlyByteBuf additionalData) {
        this.f_36813_ = additionalData.readDouble();
        this.f_36814_ = additionalData.readDouble();
        this.f_36815_ = additionalData.readDouble();
    }
}

