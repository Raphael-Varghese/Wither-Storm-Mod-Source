/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.resources.sounds.SoundInstance
 *  net.minecraft.client.sounds.MusicManager
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package nonamecrackers2.witherstormmod.mixin;

import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import nonamecrackers2.witherstormmod.client.audio.bosstheme.BossThemeManager;
import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={MusicManager.class})
public abstract class MixinMusicManager {
    @Final
    @Shadow
    private Minecraft f_120178_;
    @Shadow
    @Nullable
    private SoundInstance f_120179_;

    @Inject(method={"tick"}, at={@At(value="HEAD")}, cancellable=true)
    public void witherstormmod$stopCurrentMusicIfBossMusicPlaying_tick(CallbackInfo ci) {
        BossThemeManager manager;
        ClientLevel level;
        if (WitherStormModConfig.CLIENT_SPEC.isLoaded() && !((Boolean)WitherStormModConfig.CLIENT.playMinecraftMusic.get()).booleanValue()) {
            if (this.f_120179_ != null) {
                this.m_120186_();
            }
            ci.cancel();
        }
        if ((level = this.f_120178_.f_91073_) != null && (manager = (BossThemeManager)level.getCapability(WitherStormModClientCapabilities.BOSS_THEME_MANAGER).orElse(null)) != null && manager.isPlaying()) {
            if (this.f_120179_ != null) {
                this.m_120186_();
            }
            ci.cancel();
        }
    }

    @Shadow
    public abstract void m_120186_();
}

