/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.TickEvent$ClientTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.client.audio.TractorBeamLoop;
import nonamecrackers2.witherstormmod.client.audio.WitherStormTractorBeamLoop;
import nonamecrackers2.witherstormmod.client.capability.WitherStormLoopingSoundManager;
import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;

public class PlayTractorBeamLoopEvents {
    @SubscribeEvent
    public static void clientTickEvent(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            Minecraft mc = Minecraft.m_91087_();
            ClientLevel clientWorld = mc.f_91073_;
            if (clientWorld != null && !mc.m_91104_()) {
                for (Entity entity : clientWorld.m_104735_()) {
                    WitherStormEntity storm;
                    if (!(entity instanceof WitherStormEntity) || (storm = (WitherStormEntity)entity).isDeadOrPlayingDead() || storm.m_20067_()) continue;
                    for (WitherStormHead head : storm.getHeadManager().getHeads()) {
                        LocalPlayer player = mc.f_91074_;
                        if (!storm.tractorBeamActive(head.getIndex())) continue;
                        Vec3 pos = TractorBeamHelper.calculateClosestPoint(player.m_20182_(), storm, head.getIndex());
                        double distance = Math.sqrt(player.m_20238_(pos));
                        WitherStormLoopingSoundManager manager = (WitherStormLoopingSoundManager)clientWorld.getCapability(WitherStormModClientCapabilities.LOOPING_MANAGER).orElse(null);
                        if (manager == null || manager.alreadyHasLoop(storm.m_19879_(), head.getIndex()) || !(distance <= TractorBeamLoop.DISTANCE_REQUIRED)) continue;
                        WitherStormTractorBeamLoop loop = new WitherStormTractorBeamLoop(storm, head.getIndex());
                        loop.setPos(pos);
                        manager.putBeamSound(storm.m_19879_(), head.getIndex(), loop);
                    }
                }
            }
        }
    }
}

