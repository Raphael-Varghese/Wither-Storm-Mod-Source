/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraftforge.event.entity.player.PlayerEvent$PlayerChangedDimensionEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$PlayerLoggedInEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$PlayerRespawnEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$StartTracking
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.network.PacketDistributor
 */
package nonamecrackers2.witherstormmod.common.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.packet.EntitySyncableDataMessage;
import nonamecrackers2.witherstormmod.common.util.EntitySyncableData;

public class EntitySyncableDataEvents {
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        EntitySyncableDataEvents.sendChanges((ServerPlayer)event.getEntity());
    }

    @SubscribeEvent
    public static void onChangedDimensions(PlayerEvent.PlayerChangedDimensionEvent event) {
        EntitySyncableDataEvents.sendChanges((ServerPlayer)event.getEntity());
    }

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        EntitySyncableDataEvents.sendChanges((ServerPlayer)event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerStartTracking(PlayerEvent.StartTracking event) {
        EntitySyncableDataEvents.sendChanges((ServerPlayer)event.getEntity(), event.getTarget());
    }

    public static void sendChanges(ServerPlayer player) {
        ServerLevel world = (ServerLevel)player.m_9236_();
        for (Entity entity : world.m_8583_()) {
            EntitySyncableDataEvents.sendChanges(player, entity);
        }
    }

    public static void sendChanges(ServerPlayer player, Entity entity) {
        if (entity instanceof EntitySyncableData) {
            EntitySyncableDataMessage message = new EntitySyncableDataMessage(entity.m_19879_(), (EntitySyncableData)entity);
            WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> player), (Object)message);
        }
    }
}

