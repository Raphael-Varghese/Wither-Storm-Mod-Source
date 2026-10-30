/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Explosion
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.network.PacketDistributor
 *  net.minecraftforge.network.PacketDistributor$TargetPoint
 */
package nonamecrackers2.witherstormmod.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.FlamingWitherSkullEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;

public class BlueFlamingWitherSkullEntity
extends FlamingWitherSkullEntity {
    public BlueFlamingWitherSkullEntity(EntityType<? extends BlueFlamingWitherSkullEntity> type, Level world) {
        super(type, world);
    }

    public BlueFlamingWitherSkullEntity(EntityType<? extends BlueFlamingWitherSkullEntity> type, Level world, LivingEntity owner, double x, double y, double z) {
        super(type, world, owner, x, y, z);
    }

    public BlueFlamingWitherSkullEntity(Level world, LivingEntity owner, double x, double y, double z) {
        this((EntityType<? extends BlueFlamingWitherSkullEntity>)((EntityType)WitherStormModEntityTypes.BLUE_FLAMING_WITHER_SKULL.get()), world, owner, x, y, z);
    }

    @Override
    protected float m_6884_() {
        return 0.85f;
    }

    @Override
    protected ParticleOptions getParticle() {
        return ParticleTypes.f_123745_;
    }

    @Override
    protected void explodeAndDiscard() {
        boolean flag = ForgeEventFactory.getMobGriefingEvent((Level)this.m_9236_(), (Entity)this.m_19749_());
        this.m_5496_((SoundEvent)WitherStormModSoundEvents.FLAMING_SKULL_IMPACT.get(), 6.0f, (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * -0.2f + 0.8f);
        WitherStormModPacketHandlers.MAIN.send(PacketDistributor.NEAR.with(PacketDistributor.TargetPoint.p((double)this.m_20182_().f_82479_, (double)this.m_20182_().f_82480_, (double)this.m_20182_().f_82481_, (double)60.0, (ResourceKey)this.m_9236_().m_46472_())), (Object)new ShakeScreenMessage(20.0f, 6.0f));
        this.m_9236_().m_255391_((Entity)this, this.m_20185_(), this.m_20186_(), this.m_20189_(), (float)((Double)WitherStormModConfig.SERVER.flamingSkullExplosionSize.get() + 4.0), flag, Level.ExplosionInteraction.MOB);
        this.m_146870_();
    }

    public float m_7077_(Explosion explosion, BlockGetter blockGetter, BlockPos pos, BlockState state, FluidState fluidState, float resistance) {
        float blastResistance = state.getExplosionResistance(blockGetter, pos, explosion);
        if (blastResistance <= 1200.0f) {
            return state.canEntityDestroy(blockGetter, pos, (Entity)this) ? Math.min(0.8f, resistance) : resistance;
        }
        return resistance;
    }
}

