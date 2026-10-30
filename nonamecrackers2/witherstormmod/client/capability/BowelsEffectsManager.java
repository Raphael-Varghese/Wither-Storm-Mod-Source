/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.client.renderer.DimensionSpecialEffects
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent
 *  net.minecraftforge.event.TickEvent$ClientTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.client.capability;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.client.capability.PlayerCameraShaker;
import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.util.BowelsSpecialEffects;

public class BowelsEffectsManager {
    private final Minecraft minecraft;
    private final Random random = new Random();
    private int nextTremble = 300 + this.random.nextInt(600);
    private int nextScream = 100 + this.random.nextInt(240);

    public BowelsEffectsManager(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public BowelsEffectsManager() {
        this.minecraft = null;
    }

    public void tick() {
        LocalPlayer player = this.minecraft.f_91074_;
        float timeReduction = 1.0f;
        CommandBlockEntity lowest = null;
        float lowestHealth = -1.0f;
        for (Entity entity : this.minecraft.f_91073_.m_104735_()) {
            if (!(entity instanceof CommandBlockEntity)) continue;
            CommandBlockEntity commandBlock = (CommandBlockEntity)entity;
            if (lowestHealth != -1.0f && !(commandBlock.m_21223_() < lowestHealth)) continue;
            lowest = commandBlock;
            lowestHealth = commandBlock.m_21223_();
        }
        if (lowest != null && lowest.m_21223_() < lowest.m_21233_()) {
            timeReduction = Math.max(0.05f, lowest.m_21223_() / lowest.m_21233_() * 0.3f);
            if (this.nextScream > 0) {
                --this.nextScream;
                if (this.nextScream == 0) {
                    player.m_6330_((SoundEvent)WitherStormModSoundEvents.BOWELS_LOUD_HURT.get(), SoundSource.AMBIENT, 1.0f, 1.0f);
                    this.nextScream = 120 + this.random.nextInt(120);
                }
            }
        }
        if (this.nextTremble > 0) {
            --this.nextTremble;
            if (this.nextTremble == 0) {
                PlayerCameraShaker shaker;
                float extraShakeStrength = 0.0f;
                if (lowest != null && lowest.m_21223_() < lowest.m_21233_()) {
                    extraShakeStrength = 4.0f;
                }
                if ((shaker = (PlayerCameraShaker)player.getCapability(WitherStormModClientCapabilities.CAMERA_SHAKER).orElse(null)) != null) {
                    shaker.shake(60.0f, 2.0f + extraShakeStrength);
                }
                player.m_6330_((SoundEvent)WitherStormModSoundEvents.BOWELS_TREMBLE.get(), SoundSource.AMBIENT, 1.0f, 1.0f);
                this.nextTremble = (int)((float)(240 + this.random.nextInt(720)) * timeReduction);
            }
        }
    }

    public static class Events {
        @SubscribeEvent
        public static void tickAmbience(TickEvent.ClientTickEvent event) {
            ClientLevel world;
            Minecraft mc = Minecraft.m_91087_();
            if (event.phase == TickEvent.Phase.END && (world = mc.f_91073_) != null && !mc.m_91104_()) {
                world.getCapability(WitherStormModClientCapabilities.BOWELS_EFFECTS_MANAGER).ifPresent(manager -> manager.tick());
            }
        }

        public static void registerSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {
            event.register(WitherStormMod.id("bowels"), (DimensionSpecialEffects)new BowelsSpecialEffects());
        }
    }
}

