/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.ForgeMod
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package nonamecrackers2.witherstormmod.common.packet;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import nonamecrackers2.witherstormmod.common.blockentity.inventory.AbstractSuperBeaconMenu;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
import nonamecrackers2.witherstormmod.common.packet.InjureHeadMessage;
import nonamecrackers2.witherstormmod.common.packet.SuperBeaconSetEffectMessage;
import nonamecrackers2.witherstormmod.common.packet.SuperBeaconToggleAreaMessage;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WitherStormModMessageHandlerServer {
    public static final Logger LOGGER = LogManager.getLogger();

    public static void processInjureHeadMessage(InjureHeadMessage message, ServerPlayer player) {
        ServerLevel world = player.m_284548_();
        double pickRange = player.m_21051_((Attribute)ForgeMod.BLOCK_REACH.get()).m_22135_();
        Vec3 pos = player.m_20299_(1.0f);
        Vec3 eye = player.m_20252_(1.0f);
        Vec3 reach = pos.m_82520_(eye.f_82479_ * pickRange, eye.f_82480_ * pickRange, eye.f_82481_ * pickRange);
        Entity entity = world.m_6815_(message.getEntityID());
        byte headIndex = message.getHead();
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            WitherStormHead head = storm.getHeadManager().getHead(headIndex);
            if (storm.tractorBeamActive(headIndex) && WorldUtil.checkForIntersect(head.getBoundingBox(), pos, reach)) {
                if (head.getHeadInjureAttemptCooldown() <= 0 && head.getHeadInjuryTicks() <= 0) {
                    head.setHeadInjureAttemptCooldown(20);
                    if (head.checkAndCountAttack()) {
                        head.hurt((Entity)player, storm.getHeadManager().getHeadInjuryTime());
                    }
                    player.m_6330_(SoundEvents.f_12313_, player.m_5720_(), 1.0f, 1.0f);
                } else {
                    player.m_6330_(SoundEvents.f_12318_, player.m_5720_(), 1.0f, 1.0f);
                }
            }
        } else {
            LOGGER.warn("Received entity " + entity + " that is not an instance of WitherStormEntity");
        }
    }

    public static void processSuperBeaconSetEffectMessage(SuperBeaconSetEffectMessage message, ServerPlayer player) {
        AbstractContainerMenu abstractContainerMenu = player.f_36096_;
        if (abstractContainerMenu instanceof AbstractSuperBeaconMenu) {
            AbstractSuperBeaconMenu menu = (AbstractSuperBeaconMenu)abstractContainerMenu;
            MobEffect effect = MobEffect.m_19453_((int)message.getEffectId());
            if (menu.getCooldown() == 0 || effect == null) {
                if (effect != null && effect != menu.getPrimaryEffect()) {
                    menu.doPowerUp(player);
                    menu.activateCooldown(200);
                }
                menu.updateEffects(message.getEffectId());
            }
        }
    }

    public static void processSuperBeaconToggleAreaMessage(SuperBeaconToggleAreaMessage message, ServerPlayer player) {
        AbstractContainerMenu abstractContainerMenu = player.f_36096_;
        if (abstractContainerMenu instanceof AbstractSuperBeaconMenu) {
            AbstractSuperBeaconMenu menu = (AbstractSuperBeaconMenu)abstractContainerMenu;
            menu.setShowArea(message.shouldShowArea());
        }
    }
}

