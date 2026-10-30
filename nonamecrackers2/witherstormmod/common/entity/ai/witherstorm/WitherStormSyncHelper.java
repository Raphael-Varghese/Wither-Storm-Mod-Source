/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.event.entity.player.PlayerEvent$PlayerChangedDimensionEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$PlayerLoggedInEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$PlayerRespawnEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$StartTracking
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.network.PacketDistributor
 *  net.minecraftforge.network.PacketDistributor$PacketTarget
 *  nonamecrackers2.crackerslib.common.packet.Packet
 */
package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.crackerslib.common.packet.Packet;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.packet.CreateDebrisMessage;
import nonamecrackers2.witherstormmod.common.packet.CreateLoopingSoundMessage;
import nonamecrackers2.witherstormmod.common.packet.RemoveSoundLoopMessage;
import nonamecrackers2.witherstormmod.common.packet.RemoveStormFromDistantRendererMessage;
import nonamecrackers2.witherstormmod.common.packet.WitherStormToDistantRendererMessage;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class WitherStormSyncHelper {
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (player instanceof ServerPlayer) {
            ServerPlayer player2 = (ServerPlayer)player;
            WitherStormSyncHelper.sendWitherStormsToPlayer(player2);
        }
    }

    @SubscribeEvent
    public static void onPlayerChangeDimensions(PlayerEvent.PlayerChangedDimensionEvent event) {
        Player player = event.getEntity();
        if (player instanceof ServerPlayer) {
            ServerPlayer player2 = (ServerPlayer)player;
            WitherStormSyncHelper.sendWitherStormsToPlayer(player2);
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        if (player instanceof ServerPlayer) {
            ServerPlayer player2 = (ServerPlayer)player;
            WitherStormSyncHelper.sendWitherStormsToPlayer(player2);
        }
    }

    @SubscribeEvent
    public static void onPlayerStartTracking(PlayerEvent.StartTracking event) {
        Entity target = event.getTarget();
        Player player = event.getEntity();
        if (player instanceof ServerPlayer) {
            ServerPlayer player2 = (ServerPlayer)player;
            if (target instanceof WitherStormEntity) {
                WitherStormEntity storm = (WitherStormEntity)target;
                PacketDistributor.PacketTarget packetTarget = PacketDistributor.PLAYER.with(() -> player2);
                storm.getPlayDeadManager().sendChanges(packetTarget, false);
                WitherStormModPacketHandlers.MAIN.send(packetTarget, (Object)new CreateDebrisMessage(storm, storm.isDeadOrPlayingDead()));
            }
        }
    }

    private static void sendWitherStormsToPlayer(ServerPlayer player) {
        WorldUtil.getAllStorms(player.m_284548_()).forEach(storm -> WitherStormSyncHelper.sendWitherStormToClient(PacketDistributor.PLAYER.with(() -> player), storm));
    }

    public static void sendWitherStormToClient(PacketDistributor.PacketTarget target, WitherStormEntity storm) {
        Packet message;
        WitherStormModPacketHandlers.MAIN.send(target, (Object)new WitherStormToDistantRendererMessage(WorldUtil.getStormIds(storm), storm));
        if (storm.shouldPlaySoundLoops()) {
            message = new CreateLoopingSoundMessage(storm);
            WitherStormModPacketHandlers.MAIN.send(target, (Object)message);
        }
        storm.getPlayDeadManager().sendChanges(target, false);
        message = new CreateDebrisMessage(storm, storm.isDeadOrPlayingDead());
        WitherStormModPacketHandlers.MAIN.send(target, (Object)message);
    }

    public static void sendWitherStormToClient(WitherStormEntity storm) {
        WitherStormSyncHelper.sendWitherStormToClient(PacketDistributor.DIMENSION.with(() -> ((Level)storm.m_9236_()).m_46472_()), storm);
    }

    public static void removeWitherStorm(PacketDistributor.PacketTarget target, WitherStormEntity storm) {
        WitherStormModPacketHandlers.MAIN.send(target, (Object)new RemoveStormFromDistantRendererMessage(WorldUtil.getStormIds(storm), storm));
        if (storm.shouldPlaySoundLoop) {
            RemoveSoundLoopMessage message = new RemoveSoundLoopMessage(storm);
            WitherStormModPacketHandlers.MAIN.send(target, (Object)message);
        }
    }

    public static void removeWitherStorm(WitherStormEntity storm) {
        WitherStormSyncHelper.removeWitherStorm(PacketDistributor.DIMENSION.with(() -> ((Level)storm.m_9236_()).m_46472_()), storm);
    }
}

