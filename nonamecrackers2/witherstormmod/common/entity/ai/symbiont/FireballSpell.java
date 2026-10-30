/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket
 *  net.minecraft.server.level.ServerChunkCache
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.AbstractHurtingProjectile
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.network.PacketDistributor
 */
package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
import nonamecrackers2.witherstormmod.common.entity.FlamingWitherSkullEntity;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.packet.UpdateDamagingProjectileMessage;

public class FireballSpell
extends SymbiontSpell {
    private final ProjectileFactory factory;
    private final int count;
    private final int throwInterval;

    public FireballSpell(WitheredSymbiontEntity symbiont, SpellType type, ProjectileFactory factory, int count) {
        super(symbiont, type);
        this.factory = factory;
        this.count = count;
        this.throwInterval = type.spellTime() / count;
    }

    @Override
    public void start(LivingEntity target) {
        this.projectiles.clear();
        double y = this.entity.m_20188_() + 4.0;
        float theta = (float)(Math.PI * 2 / (double)this.count);
        for (int i = 0; i < this.count; ++i) {
            float angle = theta * (float)i;
            double x = 7.0 * (double)Mth.m_14089_((float)angle) + this.entity.m_20185_();
            double z = 7.0 * (double)Mth.m_14031_((float)angle) + this.entity.m_20189_();
            AbstractHurtingProjectile projectile = this.factory.make(this.entity.m_9236_(), (LivingEntity)this.entity, 0.0, 0.0, 0.0);
            projectile.m_6034_(x, y, z);
            projectile.m_20242_(true);
            this.entity.m_9236_().m_7967_((Entity)projectile);
            this.projectiles.add(projectile);
        }
    }

    @Override
    public void cast(LivingEntity target) {
    }

    @Override
    public void doCasting(LivingEntity target) {
        int size = this.projectiles.size();
        int spellCastingTime = this.type.spellTime() - this.entity.getSpellCastingTime();
        float theta = (float)Math.PI * 2 / (float)size;
        double radius = 7.0;
        for (int i = this.projectiles.size() - 1; i >= 0; --i) {
            Entity projectile = (Entity)this.projectiles.get(i);
            if (projectile instanceof AbstractHurtingProjectile) {
                AbstractHurtingProjectile damaging = (AbstractHurtingProjectile)projectile;
                if (projectile.m_6084_()) {
                    float angle = theta * (float)i + (float)spellCastingTime * 0.08f;
                    double x = radius * (double)Mth.m_14089_((float)angle) + this.entity.m_20185_();
                    double y = this.entity.m_20188_() + 4.0;
                    double z = radius * (double)Mth.m_14031_((float)angle) + this.entity.m_20189_();
                    Vec3 wanted = new Vec3(x, y, z);
                    double distance = damaging.m_20182_().m_82554_(wanted);
                    double multiplier = Math.min(1.0, distance);
                    Vec3 delta = wanted.m_82546_(damaging.m_20182_()).m_82541_().m_82542_(multiplier, multiplier, multiplier);
                    if (spellCastingTime % this.throwInterval == 0 && i == 0) {
                        Vec3 targetDelta = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE).m_20299_(1.0f).m_82546_(damaging.m_20182_()).m_82541_().m_82542_(0.1, 0.1, 0.1);
                        if (projectile instanceof FlamingWitherSkullEntity) {
                            damaging.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_SHOOT.get(), 4.0f, 1.0f);
                        } else {
                            damaging.m_5496_(SoundEvents.f_11705_, 4.0f, 1.0f);
                        }
                        this.projectiles.remove(i);
                        damaging.m_20256_(Vec3.f_82478_);
                        damaging.f_36813_ = targetDelta.m_7096_();
                        damaging.f_36814_ = targetDelta.m_7098_();
                        damaging.f_36815_ = targetDelta.m_7094_();
                        UpdateDamagingProjectileMessage message = new UpdateDamagingProjectileMessage(damaging);
                        WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> damaging), (Object)message);
                        continue;
                    }
                    damaging.m_20256_(delta);
                    ((ServerChunkCache)this.entity.m_20193_().m_7726_()).m_8445_((Entity)damaging, (Packet)new ClientboundSetEntityMotionPacket((Entity)damaging));
                    continue;
                }
            }
            this.projectiles.remove(i);
        }
    }

    @Override
    public void finish() {
        for (Entity projectile : this.projectiles) {
            projectile.m_146870_();
        }
    }

    @Override
    public int getDelay(RandomSource random, float modifier) {
        return Math.max(400, random.m_188503_(520)) - Mth.m_14143_((float)modifier) * 10;
    }

    @FunctionalInterface
    public static interface ProjectileFactory {
        public AbstractHurtingProjectile make(Level var1, LivingEntity var2, double var3, double var5, double var7);
    }
}

