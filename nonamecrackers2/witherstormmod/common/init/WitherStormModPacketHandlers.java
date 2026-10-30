/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraftforge.network.NetworkRegistry
 *  net.minecraftforge.network.simple.SimpleChannel
 *  nonamecrackers2.crackerslib.common.packet.PacketUtil
 */
package nonamecrackers2.witherstormmod.common.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import nonamecrackers2.crackerslib.common.packet.PacketUtil;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller.WitherStormBodyController;
import nonamecrackers2.witherstormmod.common.packet.BlindScreenMessage;
import nonamecrackers2.witherstormmod.common.packet.CreateDebrisMessage;
import nonamecrackers2.witherstormmod.common.packet.CreateLoopingSoundMessage;
import nonamecrackers2.witherstormmod.common.packet.EntitySyncableDataMessage;
import nonamecrackers2.witherstormmod.common.packet.FormidibombExplosionMessage;
import nonamecrackers2.witherstormmod.common.packet.GlobalSoundMessage;
import nonamecrackers2.witherstormmod.common.packet.InjureHeadMessage;
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
import nonamecrackers2.witherstormmod.common.packet.SuperBeaconSetEffectMessage;
import nonamecrackers2.witherstormmod.common.packet.SuperBeaconToggleAreaMessage;
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

public class WitherStormModPacketHandlers {
    private static final String PROTOCOL_VERSION = "4.0";
    public static final SimpleChannel MAIN = NetworkRegistry.newSimpleChannel((ResourceLocation)new ResourceLocation("witherstormmod", "main"), () -> "4.0", "4.0"::equals, "4.0"::equals);

    public static void registerPackets() {
        PacketUtil.registerToClient((SimpleChannel)MAIN, PlayerMotionMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, GlobalSoundMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, WitherStormBodyController.UpdateBodyRotMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, WitherStormToDistantRendererMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, RemoveStormFromDistantRendererMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, UpdateStormPositionMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, StormTeleportMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, UpdateStormVelocityMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, UpdateStormHeadLookMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, StormMetadataMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, StormAttributesMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, CreateLoopingSoundMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, StormSoundPositionMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, RemoveSoundLoopMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, NotifyHeadInjuryMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, UpdateEffectInstanceMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, UpdateWitherSicknessTrackerMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, UpdatePlayDeadManagerMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, CreateDebrisMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, EntitySyncableDataMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, PlayAdditionalLoopingSoundMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, RemoveAdditionalLoopingSoundMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, ShakeScreenMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, FormidibombExplosionMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, UpdateDamagingProjectileMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, WitheredSymbiontEntity.SetSpellTimeMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, CommandBlockEntity.ModeAnimationMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, TentacleEntity.UpdateAnimationMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, BlindScreenMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, SuperBeaconValidEffectsMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, UpdateDistantSuperBeaconMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, RemoveDistantSuperBeaconMessage.class);
        PacketUtil.registerToClient((SimpleChannel)MAIN, OnHeadAttackedMessage.class);
        PacketUtil.registerToServer((SimpleChannel)MAIN, InjureHeadMessage.class);
        PacketUtil.registerToServer((SimpleChannel)MAIN, SuperBeaconSetEffectMessage.class);
        PacketUtil.registerToServer((SimpleChannel)MAIN, SuperBeaconToggleAreaMessage.class);
    }
}

