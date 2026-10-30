/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraftforge.event.TickEvent$ClientTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;

public class PlayerCameraShakerTicker {
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            Minecraft mc = Minecraft.m_91087_();
            ClientLevel world = mc.f_91073_;
            if (world != null && !mc.m_91104_()) {
                for (Entity entity : world.m_104735_()) {
                    if (!(entity instanceof LocalPlayer)) continue;
                    LocalPlayer player = (LocalPlayer)entity;
                    player.getCapability(WitherStormModClientCapabilities.CAMERA_SHAKER).ifPresent(shaker -> shaker.tick());
                }
            }
        }
    }
}

