/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.ibm.icu.impl.locale.XCldrStub$ImmutableSet
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket$AttributeSnapshot
 *  net.minecraft.network.protocol.game.VecDeltaCodec
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.AttributeInstance
 *  net.minecraft.world.entity.ai.attributes.AttributeMap
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.projectile.AbstractHurtingProjectile
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.util.LazyOptional
 *  net.minecraftforge.entity.IEntityAdditionalSpawnData
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package nonamecrackers2.witherstormmod.client.packet;

import com.ibm.icu.impl.locale.XCldrStub;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.network.protocol.game.VecDeltaCodec;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.client.audio.WitherStormSoundLoop;
import nonamecrackers2.witherstormmod.client.audio.bosstheme.BossThemeManager;
import nonamecrackers2.witherstormmod.client.capability.WitherStormDistantRenderer;
import nonamecrackers2.witherstormmod.client.capability.WitherStormLoopingSoundManager;
import nonamecrackers2.witherstormmod.client.gui.menu.SuperBeaconScreen;
import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.HeadManager;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEffects;
import nonamecrackers2.witherstormmod.common.packet.BlindScreenMessage;
import nonamecrackers2.witherstormmod.common.packet.CreateDebrisMessage;
import nonamecrackers2.witherstormmod.common.packet.CreateLoopingSoundMessage;
import nonamecrackers2.witherstormmod.common.packet.DistantRendererMessage;
import nonamecrackers2.witherstormmod.common.packet.EntitySyncableDataMessage;
import nonamecrackers2.witherstormmod.common.packet.FormidibombExplosionMessage;
import nonamecrackers2.witherstormmod.common.packet.GlobalSoundMessage;
import nonamecrackers2.witherstormmod.common.packet.NotifyHeadInjuryMessage;
import nonamecrackers2.witherstormmod.common.packet.OnHeadAttackedMessage;
import nonamecrackers2.witherstormmod.common.packet.PlayAdditionalLoopingSoundMessage;
import nonamecrackers2.witherstormmod.common.packet.PlayerMotionMessage;
import nonamecrackers2.witherstormmod.common.packet.RemoveAdditionalLoopingSoundMessage;
import nonamecrackers2.witherstormmod.common.packet.RemoveDistantSuperBeaconMessage;
import nonamecrackers2.witherstormmod.common.packet.RemoveSoundLoopMessage;
import nonamecrackers2.witherstormmod.common.packet.RemoveStormFromDistantRendererMessage;
import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
import nonamecrackers2.witherstormmod.common.packet.StormAttributesMessage;
import nonamecrackers2.witherstormmod.common.packet.StormMetadataMessage;
import nonamecrackers2.witherstormmod.common.packet.StormSoundPositionMessage;
import nonamecrackers2.witherstormmod.common.packet.StormTeleportMessage;
import nonamecrackers2.witherstormmod.common.packet.SuperBeaconValidEffectsMessage;
import nonamecrackers2.witherstormmod.common.packet.UpdateDamagingProjectileMessage;
import nonamecrackers2.witherstormmod.common.packet.UpdateDistantSuperBeaconMessage;
import nonamecrackers2.witherstormmod.common.packet.UpdateEffectInstanceMessage;
import nonamecrackers2.witherstormmod.common.packet.UpdatePlayDeadManagerMessage;
import nonamecrackers2.witherstormmod.common.packet.UpdateStormHeadLookMessage;
import nonamecrackers2.witherstormmod.common.packet.UpdateStormPositionMessage;
import nonamecrackers2.witherstormmod.common.packet.UpdateStormVelocityMessage;
import nonamecrackers2.witherstormmod.common.packet.UpdateWitherSicknessTrackerMessage;
import nonamecrackers2.witherstormmod.common.packet.WitherStormToDistantRendererMessage;
import nonamecrackers2.witherstormmod.common.util.EntitySyncableData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WitherStormModMessageHandlerClient {
    public static final Logger LOGGER = LogManager.getLogger();

    public static void processGlobalSoundMessage(GlobalSoundMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        LocalPlayer player = mc.f_91074_;
        if (player != null) {
            player.m_6330_(message.getSoundEvent(), SoundSource.HOSTILE, message.getVolume(), message.getPitch());
        }
    }

    public static void processPlayerMotionMessage(PlayerMotionMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        Entity vehicle = mc.f_91074_.m_20202_();
        if (mc.f_91074_.m_20159_()) {
            vehicle.m_20256_(message.getMotion());
        } else {
            mc.f_91074_.m_20256_(message.getMotion());
        }
    }

    public static void processWitherStormToDistantRendererMessage(WitherStormToDistantRendererMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        WitherStormModMessageHandlerClient.purgeNonApplicable(world, message);
        WitherStormModMessageHandlerClient.getDistantRenderer(world).ifPresent(distantRenderer -> {
            WitherStormEntity entity = (WitherStormEntity)((EntityType)ForgeRegistries.ENTITY_TYPES.getValue(message.getType())).m_20615_((Level)mc.f_91073_);
            double x = message.getPos().f_82479_;
            double y = message.getPos().f_82480_;
            double z = message.getPos().f_82481_;
            float yRot = (float)(message.getYRot() * 360) / 256.0f;
            float xRot = (float)(message.getXRot() * 360) / 256.0f;
            entity.m_217006_(message.getPos().f_82479_, message.getPos().f_82480_, message.getPos().f_82481_);
            entity.f_20883_ = (float)(message.getHeadYRot() * 360) / 256.0f;
            entity.f_20885_ = (float)(message.getHeadYRot() * 360) / 256.0f;
            entity.getHeadManager().updateHeadsFromPacked(message.getRots());
            entity.m_20234_(message.getId());
            entity.m_20084_(message.getUUID());
            entity.m_19890_(x, y, z, yRot, xRot);
            entity.m_20256_(new Vec3((double)message.getDeltaMovement().m_123341_() / 8000.0, (double)message.getDeltaMovement().m_123342_() / 8000.0, (double)message.getDeltaMovement().m_123343_() / 8000.0));
            entity.m_20088_().m_135356_(message.getUnpackedData());
            entity.setOnDistantRenderer();
            if (!distantRenderer.contains(message.getId())) {
                distantRenderer.addWitherStorm(message.getId(), entity);
            }
            if (entity instanceof IEntityAdditionalSpawnData) {
                ((IEntityAdditionalSpawnData)entity).readSpawnData(message.getBuffer());
            }
            if (entity instanceof EntitySyncableData) {
                entity.readData(message.getBuffer());
            }
        });
    }

    public static void processRemoveStormFromDistantRendererMessage(RemoveStormFromDistantRendererMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        WitherStormModMessageHandlerClient.purgeNonApplicable(world, message);
        WitherStormModMessageHandlerClient.getDistantRenderer(world).ifPresent(distantRenderer -> {
            WitherStormEntity entity = distantRenderer.get(message.getId());
            if (entity != null) {
                entity.m_146870_();
            }
        });
    }

    public static void processUpdateStormPositionMessage(UpdateStormPositionMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        WitherStormModMessageHandlerClient.purgeNonApplicable(world, message);
        WitherStormModMessageHandlerClient.getDistantRenderer(world).ifPresent(distantRenderer -> {
            WitherStormEntity entity = distantRenderer.get(message.getEntityID());
            if (entity != null && !entity.m_6109_()) {
                if (message.hasPosition()) {
                    VecDeltaCodec codec = entity.m_217001_();
                    Vec3 vector3d = codec.m_238021_((long)message.getX(), (long)message.getY(), (long)message.getZ());
                    codec.m_238033_(vector3d);
                    float yRot = message.hasRotation() ? (float)(message.getYRot() * 360) / 256.0f : entity.m_146908_();
                    float xRot = message.hasRotation() ? (float)(message.getXRot() * 360) / 256.0f : entity.m_146909_();
                    entity.m_6453_(vector3d.f_82479_, vector3d.f_82480_, vector3d.f_82481_, yRot, xRot, 3, false);
                } else if (message.hasRotation()) {
                    float yRot = (float)(message.getYRot() * 360) / 256.0f;
                    float xRot = (float)(message.getXRot() * 360) / 256.0f;
                    entity.m_6453_(entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), yRot, xRot, 3, false);
                }
                entity.m_6853_(message.onGround());
            }
        });
    }

    public static void processStormTeleportMessage(StormTeleportMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        WitherStormModMessageHandlerClient.purgeNonApplicable(world, message);
        WitherStormModMessageHandlerClient.getDistantRenderer(world).ifPresent(distantRenderer -> {
            WitherStormEntity entity = distantRenderer.get(message.getEntityID());
            if (entity != null) {
                double x = message.getX();
                double y = message.getY();
                double z = message.getZ();
                entity.m_217006_(x, y, z);
                if (!entity.m_6109_()) {
                    float yRot = (float)(message.getYRot() * 360) / 256.0f;
                    float xRot = (float)(message.getXRot() * 360) / 256.0f;
                    if (world.m_6815_(entity.m_19879_()) == null) {
                        entity.m_6453_(x, y, z, yRot, xRot, 3, false);
                    } else {
                        entity.f_19854_ = x;
                        entity.f_19855_ = y;
                        entity.f_19856_ = z;
                        entity.f_19790_ = x;
                        entity.f_19791_ = y;
                        entity.f_19792_ = z;
                        entity.f_19859_ = yRot;
                        entity.f_19860_ = xRot;
                        entity.m_146922_(yRot);
                        entity.m_146926_(xRot);
                    }
                    entity.m_6853_(message.onGround());
                }
            }
        });
    }

    public static void processUpdateStormVelocityMessage(UpdateStormVelocityMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        WitherStormModMessageHandlerClient.purgeNonApplicable(world, message);
        WitherStormModMessageHandlerClient.getDistantRenderer(world).ifPresent(distantRenderer -> {
            WitherStormEntity entity = distantRenderer.get(message.getEntityID());
            if (entity != null) {
                entity.m_6001_(message.getX() / 8000.0, message.getY() / 8000.0, message.getZ() / 8000.0);
            }
        });
    }

    public static void processUpdateStormHeadLookMessage(UpdateStormHeadLookMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        WitherStormModMessageHandlerClient.purgeNonApplicable(world, message);
        WitherStormModMessageHandlerClient.getDistantRenderer(world).ifPresent(distantRenderer -> {
            WitherStormEntity entity = distantRenderer.get(message.getEntityID());
            if (entity != null) {
                float yHeadRot = (float)(message.getYRot() * 360) / 256.0f;
                entity.m_6541_(yHeadRot, 3);
            }
        });
    }

    public static void processStormMetadataMessage(StormMetadataMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        WitherStormModMessageHandlerClient.purgeNonApplicable(world, message);
        WitherStormModMessageHandlerClient.getDistantRenderer(world).ifPresent(distantRenderer -> {
            WitherStormEntity entity = distantRenderer.get(message.getEntityID());
            if (entity != null && message.getUnpackedItems() != null) {
                entity.m_20088_().m_135356_(message.getUnpackedItems());
            }
        });
    }

    public static void processStormAttributesMessage(StormAttributesMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        WitherStormModMessageHandlerClient.purgeNonApplicable(world, message);
        WitherStormModMessageHandlerClient.getDistantRenderer(world).ifPresent(distantRenderer -> {
            WitherStormEntity entity = distantRenderer.get(message.getEntityID());
            if (entity != null) {
                AttributeMap manager = entity.m_21204_();
                for (ClientboundUpdateAttributesPacket.AttributeSnapshot snapshot : message.getAttributes()) {
                    AttributeInstance attribute = manager.m_22146_(snapshot.m_133601_());
                    if (attribute == null) {
                        LOGGER.warn("WitherStormEntity {} does not have attribute {}", (Object)entity, (Object)ForgeRegistries.ATTRIBUTES.getKey((Object)snapshot.m_133601_()));
                        continue;
                    }
                    attribute.m_22100_(snapshot.m_133602_());
                    attribute.m_22132_();
                    for (AttributeModifier modifier : snapshot.m_133603_()) {
                        attribute.m_22118_(modifier);
                    }
                }
            }
        });
    }

    public static void processCreateLoopingSoundMessage(CreateLoopingSoundMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        WitherStormModMessageHandlerClient.getLoopingSoundManager(world).ifPresent(loopingManager -> {
            double distance = mc.f_91074_.m_20275_(message.getX(), message.getY(), message.getZ());
            float fade = Math.max(1.0f, (float)(distance / 1000.0) / 32.0f);
            loopingManager.putSound(message.getEntityID(), new WitherStormSoundLoop(new Vec3(message.getX(), message.getY(), message.getZ()), WitherStormEntity.getSoundForLoop(message.getPhase(), fade)));
        });
    }

    public static void processStormSoundPositionMessage(StormSoundPositionMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        WitherStormModMessageHandlerClient.getLoopingSoundManager(world).ifPresent(loopingManager -> {
            WitherStormSoundLoop loop = loopingManager.getSound(message.getEntityID());
            if (loop != null) {
                loop.prevPos = loop.pos;
                loop.pos = new Vec3(message.getX(), message.getY(), message.getZ());
                distance = mc.f_91074_.m_20275_(message.getX(), message.getY(), message.getZ());
                float fade = Math.max(1.0f, (float)(distance / 1000.0) / 32.0f);
                SoundEvent event = WitherStormEntity.getSoundForLoop(message.getPhase(), fade);
                if (!loop.soundevent.m_11660_().equals((Object)event.m_11660_()) && !loopingManager.alreadyHasReplacement(message.getEntityID())) {
                    loopingManager.replace(message.getEntityID(), new WitherStormSoundLoop(new Vec3(message.getX(), message.getY(), message.getZ()), event));
                }
            } else {
                distance = mc.f_91074_.m_20275_(message.getX(), message.getY(), message.getZ());
                float fade = Math.max(1.0f, (float)(distance / 1000.0) / 32.0f);
                loopingManager.putSound(message.getEntityID(), new WitherStormSoundLoop(new Vec3(message.getX(), message.getY(), message.getZ()), WitherStormEntity.getSoundForLoop(message.getPhase(), fade)));
            }
            WitherStormSoundLoop additional = loopingManager.getAdditional(message.getEntityID());
            if (additional != null) {
                additional.prevPos = additional.pos;
                additional.pos = new Vec3(message.getX(), message.getY(), message.getZ());
            }
        });
    }

    public static void processRemoveSoundLoopMessage(RemoveSoundLoopMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        WitherStormModMessageHandlerClient.getLoopingSoundManager(world).ifPresent(loopingManager -> loopingManager.stopSound(message.getId()));
    }

    public static void processNotifyHeadInjuryMessage(NotifyHeadInjuryMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        WitherStormEntity entity = (WitherStormEntity)mc.f_91073_.m_6815_(message.getEntityID());
        if (entity != null) {
            HeadManager manager = entity.getHeadManager();
            manager.getHead(message.getHead()).hurt(null, manager.getHeadInjuryTime());
        }
    }

    public static void processUpdateEffectInstanceMessage(UpdateEffectInstanceMessage message) {
        LivingEntity living;
        MobEffectInstance effect;
        Minecraft mc = Minecraft.m_91087_();
        Entity entity = mc.f_91073_.m_6815_(message.getEntityID());
        if (entity instanceof LivingEntity && (effect = (living = (LivingEntity)entity).m_21124_((MobEffect)WitherStormModEffects.WITHER_SICKNESS.get())) != null) {
            MobEffectInstance newEffect = new MobEffectInstance((MobEffect)WitherStormModEffects.WITHER_SICKNESS.get(), message.getDuration(), (int)message.getAmplifier());
            effect.m_19558_(newEffect);
        }
    }

    public static void processUpdateWitherSicknessTrackerMessage(UpdateWitherSicknessTrackerMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        Entity entity = world.m_6815_(message.getId());
        if (entity != null) {
            entity.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> tracker.copyFromMessage(message));
        }
    }

    public static void processUpdatePlayDeadManagerMessage(UpdatePlayDeadManagerMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        Entity entity = world.m_6815_(message.getEntityID());
        if (entity != null && entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            WitherStormModMessageHandlerClient.updatePlayDeadManager(storm.getPlayDeadManager(), message);
        }
        WitherStormModMessageHandlerClient.getDistantRenderer(world).ifPresent(manager -> {
            WitherStormEntity storm = manager.get(message.getEntityID());
            if (storm != null) {
                WitherStormModMessageHandlerClient.updatePlayDeadManager(storm.getPlayDeadManager(), message);
            }
        });
    }

    private static void updatePlayDeadManager(PlayDeadManager manager, UpdatePlayDeadManagerMessage message) {
        if (!message.shouldUpdateTick()) {
            manager.setState(message.getState());
            manager.setTicksSinceRevival(message.getTicksSinceRevival());
        }
        manager.setRecentlyRevived(message.hasRecentlyBeenRevived());
        manager.setTickAmount(message.getTicks());
        manager.setRevivalPlayerProtectionTime(message.getRevivalPlayerProtectionTime());
    }

    public static void processCreateDebrisMessage(CreateDebrisMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        Entity entity = world.m_6815_(message.getEntityID());
        if (entity != null && entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            storm.createDebrisClusters(message.isDebrisHidden());
            storm.createDebrisRings(message.isDebrisHidden());
        }
        WitherStormModMessageHandlerClient.getDistantRenderer(world).ifPresent(manager -> {
            WitherStormEntity storm = manager.get(message.getEntityID());
            if (storm != null) {
                storm.createDebrisClusters(message.isDebrisHidden());
                storm.createDebrisRings(message.isDebrisHidden());
            }
        });
    }

    public static void processEntitySyncableDataMessage(EntitySyncableDataMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        Entity entity = world.m_6815_(message.getId());
        if (entity != null && entity instanceof EntitySyncableData) {
            EntitySyncableData syncable = (EntitySyncableData)entity;
            syncable.readData(message.getBuffer());
        }
    }

    public static void processPlayAdditionalLoopingSoundMessage(PlayAdditionalLoopingSoundMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        WitherStormModMessageHandlerClient.getLoopingSoundManager(world).ifPresent(loopingManager -> {
            SoundEvent event = message.getSound();
            Entity entity = world.m_6815_(message.getEntityID());
            if (entity instanceof WitherStormEntity) {
                loopingManager.putAdditionalSound(message.getEntityID(), new WitherStormSoundLoop((WitherStormEntity)entity, new Vec3(message.getX(), message.getY(), message.getZ()), event));
            }
        });
    }

    public static void processRemoveAdditionalLoopingSoundMessage(RemoveAdditionalLoopingSoundMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        WitherStormModMessageHandlerClient.getLoopingSoundManager(world).ifPresent(loopingManager -> loopingManager.stopAdditional(message.getId()));
    }

    public static void processShakeScreenMessage(ShakeScreenMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        mc.f_91074_.getCapability(WitherStormModClientCapabilities.CAMERA_SHAKER).ifPresent(shaker -> shaker.shake(message.getDuration(), message.getPower()));
    }

    public static void processFormidibombExplosionMessage(FormidibombExplosionMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        Entity entity = world.m_6815_(message.getId());
        FormidibombEntity.explode((Level)world, entity, message.getRadius(), message.getSquish(), message.getX(), message.getY(), message.getZ());
        mc.f_91074_.getCapability(WitherStormModClientCapabilities.CAMERA_SHAKER).ifPresent(shaker -> shaker.shake(100.0f, 7.5f));
        if (Math.sqrt(mc.f_91074_.m_20275_(message.getX(), message.getY(), message.getZ())) <= 250.0) {
            mc.f_91074_.getCapability(WitherStormModClientCapabilities.SCREEN_BLINDER).ifPresent(blinder -> blinder.blind(260, 40, 240));
        }
        WitherStormModMessageHandlerClient.getBossThemeManager(world).ifPresent(manager -> manager.forceStop());
    }

    public static void processUpdateDamagingProjectileMessage(UpdateDamagingProjectileMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        ClientLevel world = mc.f_91073_;
        Entity entity = world.m_6815_(message.getEntityId());
        if (entity instanceof AbstractHurtingProjectile) {
            AbstractHurtingProjectile projectile = (AbstractHurtingProjectile)entity;
            projectile.f_36813_ = message.getXPower();
            projectile.f_36814_ = message.getYPower();
            projectile.f_36815_ = message.getZPower();
        }
    }

    public static void processBlindScreenMessage(BlindScreenMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        mc.f_91074_.getCapability(WitherStormModClientCapabilities.SCREEN_BLINDER).ifPresent(blinder -> blinder.blind(message.getDuration(), message.getFadeInDuration(), message.getFadeOutDuration()));
    }

    public static void processSuperBeaconValidEffectsMessage(SuperBeaconValidEffectsMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        Screen screen = mc.f_91080_;
        if (screen instanceof SuperBeaconScreen) {
            SuperBeaconScreen screen2 = (SuperBeaconScreen)screen;
            screen2.setValidEffects(XCldrStub.ImmutableSet.copyOf(message.getEffects()));
        }
    }

    public static void processUpdateDistantSuperBeaconMessage(UpdateDistantSuperBeaconMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        WitherStormModMessageHandlerClient.getDistantRenderer(mc.f_91073_).ifPresent(renderer -> renderer.addAndOrUpdateSuperBeacon(message.getPos(), message.getColor(), message.isActive(), message.getBeaconHeight(), message.getBeamWidth(), message.getOuterBeamWidth()));
    }

    public static void processRemoveDistantSuperBeaconMessage(RemoveDistantSuperBeaconMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        WitherStormModMessageHandlerClient.getDistantRenderer(mc.f_91073_).ifPresent(renderer -> renderer.removeSuperBeacon(message.getPos()));
    }

    public static void processOnHeadAttackedMessage(OnHeadAttackedMessage message) {
        Minecraft mc = Minecraft.m_91087_();
        Entity entity = mc.f_91073_.m_6815_(message.getEntityId());
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            storm.getHeadManager().getHead(message.getHeadIndex()).handleHeadAttackedOnClient();
        }
    }

    private static LazyOptional<WitherStormDistantRenderer> getDistantRenderer(ClientLevel world) {
        return world.getCapability(WitherStormModClientCapabilities.DISTANT_RENDERER);
    }

    private static LazyOptional<WitherStormLoopingSoundManager> getLoopingSoundManager(ClientLevel world) {
        return world.getCapability(WitherStormModClientCapabilities.LOOPING_MANAGER);
    }

    private static LazyOptional<BossThemeManager> getBossThemeManager(ClientLevel world) {
        return world.getCapability(WitherStormModClientCapabilities.BOSS_THEME_MANAGER);
    }

    private static void purgeNonApplicable(ClientLevel level, DistantRendererMessage message) {
        WitherStormModMessageHandlerClient.getDistantRenderer(level).ifPresent(renderer -> {
            for (WitherStormEntity known : renderer.getKnown()) {
                if (message.getApplicable().contains(known.m_19879_())) continue;
                known.m_146870_();
            }
        });
    }
}

