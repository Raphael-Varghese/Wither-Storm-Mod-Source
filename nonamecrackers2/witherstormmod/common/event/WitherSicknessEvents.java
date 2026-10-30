/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.monster.Phantom
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.common.util.LazyOptional
 *  net.minecraftforge.event.TickEvent$LevelTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.event.entity.living.MobEffectEvent$Remove
 *  net.minecraftforge.event.entity.living.MobSpawnEvent$AllowDespawn
 *  net.minecraftforge.event.entity.player.PlayerEvent$Clone
 *  net.minecraftforge.event.entity.player.PlayerEvent$PlayerLoggedInEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$PlayerRespawnEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$StartTracking
 *  net.minecraftforge.eventbus.api.Event$Result
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.network.PacketDistributor
 */
package nonamecrackers2.witherstormmod.common.event;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.accessor.LivingEntityAccessor;
import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEffects;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.packet.UpdateWitherSicknessTrackerMessage;

public class WitherSicknessEvents {
    @SubscribeEvent
    public static void onWorldTick(TickEvent.LevelTickEvent event) {
        Level level = event.level;
        if (level instanceof ServerLevel) {
            ServerLevel world = (ServerLevel)level;
            if (event.phase == TickEvent.Phase.START) {
                ArrayList storms = Lists.newArrayList();
                for (Entity entity : world.m_8583_()) {
                    WitherStormEntity storm;
                    if (!(entity instanceof WitherStormEntity) || (storm = (WitherStormEntity)entity).getPhase() <= 1) continue;
                    storms.add(storm);
                }
                for (Entity entity : world.m_8583_()) {
                    if (!(entity instanceof LivingEntity)) continue;
                    LivingEntity living = (LivingEntity)entity;
                    living.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> {
                        if (!tracker.isActuallyImmune()) {
                            WitherStormEntity storm;
                            boolean nearby = false;
                            Iterator iterator = storms.iterator();
                            while (iterator.hasNext() && !(nearby = (storm = (WitherStormEntity)iterator.next()).isEntityNearby((Entity)living))) {
                            }
                            if (entity.m_9236_().m_46472_().m_135782_().equals((Object)WitherStormMod.bowelsLocation())) {
                                nearby = true;
                            }
                            tracker.setNearStorm(nearby);
                        }
                        tracker.tick();
                    });
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            Player original = event.getOriginal();
            Player player = event.getEntity();
            original.reviveCaps();
            LazyOptional optional = original.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER);
            if (optional.isPresent()) {
                WitherSicknessTracker oldTracker = (WitherSicknessTracker)optional.resolve().get();
                player.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> {
                    tracker.copyFrom(oldTracker);
                    if (((Boolean)WitherStormModConfig.SERVER.keepSicknessAfterRespawn.get()).booleanValue()) {
                        MobEffectInstance effect = original.m_21124_((MobEffect)WitherStormModEffects.WITHER_SICKNESS.get());
                        if (effect != null) {
                            player.m_7292_(effect);
                        }
                    } else {
                        tracker.setInfected(false);
                        tracker.setProximityTicks(0);
                        tracker.setContacts(0);
                        tracker.setContactDecreaseTicks(0);
                    }
                });
            }
            original.invalidateCaps();
        }
    }

    @SubscribeEvent
    public static void onCheckDespawn(MobSpawnEvent.AllowDespawn event) {
        Mob entity = event.getEntity();
        entity.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> {
            if (!tracker.isActuallyImmune() && (tracker.isInfected() || tracker.isBeingCured())) {
                event.setResult(Event.Result.DENY);
            }
        });
        if (entity.m_6095_().equals(EntityType.f_20509_) && ((Boolean)WitherStormModConfig.COMMON.phantomsOrbitWitherStorm.get()).booleanValue()) {
            List phantoms = entity.m_9236_().m_45976_(Phantom.class, entity.m_20191_().m_82400_(100.0));
            List storms = entity.m_9236_().m_45976_(WitherStormEntity.class, entity.m_20191_().m_82400_(100.0));
            if (phantoms.size() < 12 && storms.size() >= 1) {
                event.setResult(Event.Result.DENY);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        UpdateWitherSicknessTrackerMessage message = new UpdateWitherSicknessTrackerMessage((Entity)event.getEntity());
        WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)event.getEntity()), (Object)message);
    }

    @SubscribeEvent
    public static void onPlayerChangedDimensions(PlayerEvent.PlayerLoggedInEvent event) {
        UpdateWitherSicknessTrackerMessage message = new UpdateWitherSicknessTrackerMessage((Entity)event.getEntity());
        WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)event.getEntity()), (Object)message);
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        UpdateWitherSicknessTrackerMessage message = new UpdateWitherSicknessTrackerMessage((Entity)event.getEntity());
        WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)event.getEntity()), (Object)message);
    }

    @SubscribeEvent
    public static void onPlayerStartTracking(PlayerEvent.StartTracking event) {
        UpdateWitherSicknessTrackerMessage message = new UpdateWitherSicknessTrackerMessage(event.getTarget());
        WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)event.getEntity()), (Object)message);
    }

    @SubscribeEvent
    public static void onMobEffectRemove(MobEffectEvent.Remove event) {
        LivingEntity entity = event.getEntity();
        if (((LivingEntityAccessor)entity).hasDeathProtection() && event.getEffect() == WitherStormModEffects.WITHER_SICKNESS.get()) {
            event.setCanceled(true);
        }
    }
}

