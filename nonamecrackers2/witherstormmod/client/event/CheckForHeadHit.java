/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.GameType
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.entity.player.AttackEntityEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$LeftClickEmpty
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.client.event;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.packet.InjureHeadMessage;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class CheckForHeadHit {
    @SubscribeEvent
    public static void blockLeftHit(PlayerInteractEvent.LeftClickEmpty event) {
        CheckForHeadHit.checkForHeadHit(event.getLevel(), event.getEntity(), event.getHand());
    }

    public static void playerAttack(AttackEntityEvent event) {
        CheckForHeadHit.checkForHeadHit(event.getEntity().m_9236_(), event.getEntity(), InteractionHand.MAIN_HAND);
    }

    private static void checkForHeadHit(Level level, Player player, InteractionHand hand) {
        if (level.f_46443_) {
            ClientLevel clientWorld = (ClientLevel)level;
            LocalPlayer localPlayer = (LocalPlayer)player;
            Minecraft mc = Minecraft.m_91087_();
            if (mc.f_91072_.m_105295_() != GameType.SPECTATOR) {
                List entities = clientWorld.m_45976_(WitherStormEntity.class, localPlayer.m_20191_().m_82400_(50.0));
                for (WitherStormEntity storm : entities) {
                    for (WitherStormHead head : storm.getHeadManager().getHeads()) {
                        if (!storm.tractorBeamActive(head.getIndex())) continue;
                        Vec3 pos = localPlayer.m_20299_(1.0f);
                        Vec3 eye = localPlayer.m_20252_(1.0f);
                        float pickRange = mc.f_91072_.m_105286_();
                        Vec3 reach = pos.m_82520_(eye.f_82479_ * (double)pickRange, eye.f_82480_ * (double)pickRange, eye.f_82481_ * (double)pickRange);
                        if (!WorldUtil.checkForIntersect(head.getBoundingBox(), pos, reach)) continue;
                        if (head.getHeadInjureAttemptCooldown() <= 0 && head.getHeadInjuryTicks() <= 0 && !storm.isDeadOrPlayingDead()) {
                            InjureHeadMessage message = new InjureHeadMessage(storm, head.getIndex(), hand);
                            WitherStormModPacketHandlers.MAIN.sendToServer((Object)message);
                            continue;
                        }
                        localPlayer.m_6330_(SoundEvents.f_12318_, localPlayer.m_5720_(), 1.0f, 1.0f);
                    }
                }
            }
        }
    }
}

