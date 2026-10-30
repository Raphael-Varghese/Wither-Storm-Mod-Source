/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.util.Mth
 */
package nonamecrackers2.witherstormmod.client.capability;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;

public class PlayerCameraShaker {
    private final LocalPlayer player;
    private float xShake;
    private float yShake;
    private float xShakeO;
    private float yShakeO;
    private float duration;
    private float initialDuration;
    private float power;

    public PlayerCameraShaker(LocalPlayer player) {
        this.player = player;
    }

    public void tick() {
        this.xShakeO = this.xShake;
        this.yShakeO = this.yShake;
        if (((Boolean)WitherStormModConfig.CLIENT.cameraShakeEffects.get()).booleanValue() && this.duration > 0.0f) {
            float power = this.player.m_20096_() ? this.power : 0.0f;
            this.xShake = power * (this.player.m_217043_().m_188501_() * 2.0f - 1.0f) * this.getDurationPercentage();
            this.yShake = power * (this.player.m_217043_().m_188501_() * 2.0f - 1.0f) * this.getDurationPercentage();
            this.duration -= 1.0f;
        }
    }

    public void shake(float duration, float power) {
        if (((Boolean)WitherStormModConfig.CLIENT.cameraShakeEffects.get()).booleanValue()) {
            this.initialDuration = duration;
            this.duration = duration;
            this.power = power;
        }
    }

    public float getXShake(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.xShakeO, (float)this.xShake);
    }

    public float getYShake(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.yShakeO, (float)this.yShake);
    }

    private float getDurationPercentage() {
        return this.duration / this.initialDuration;
    }
}

