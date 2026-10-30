/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.core.Holder
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraftforge.event.PlayLevelSoundEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.PlayLevelSoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class PlaySoundEvents {
    @SubscribeEvent
    public static void onPlaySound(PlayLevelSoundEvent event) {
        if (event.getLevel().f_46443_ && ((Boolean)WitherStormModConfig.SERVER.occludeSoundsUnderground.get()).booleanValue()) {
            Holder sound = event.getSound();
            LocalPlayer player = Minecraft.m_91087_().f_91074_;
            if (!WorldUtil.isInAnOpenArea((Entity)player)) {
                float original = event.getOriginalVolume();
                float volume = original * ((20.0f - Mth.m_14036_((float)((float)(-player.m_20186_()) + 40.0f), (float)0.0f, (float)20.0f)) / 20.0f) * 0.5f;
                if (WitherStormEntity.isOccludedSound((SoundEvent)sound.get())) {
                    event.setNewVolume(volume);
                }
            }
        }
    }
}

