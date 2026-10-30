/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 */
package nonamecrackers2.witherstormmod.client.capability;

import net.minecraft.util.Mth;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;

public class PlayerScreenBlinder {
    private int duration;
    private int fadeInDuration;
    private int fadeOutDuration;
    private float fade;
    private float fadeO;

    public void tick() {
        this.fadeO = this.fade;
        if (((Boolean)WitherStormModConfig.CLIENT.blindingEffects.get()).booleanValue()) {
            if (this.fadeInDuration > 0) {
                this.fade += (1.0f - this.fade) / (float)this.fadeInDuration;
                --this.fadeInDuration;
            } else if (this.duration > 0) {
                --this.duration;
            } else if (this.fadeOutDuration > 0) {
                this.fade += (0.0f - this.fade) / (float)this.fadeOutDuration;
                --this.fadeOutDuration;
            }
        }
    }

    public float getFade(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.fadeO, (float)this.fade);
    }

    public void blind(int duration, int fadeInDuration, int fadeOutDuration) {
        if (((Boolean)WitherStormModConfig.CLIENT.cameraShakeEffects.get()).booleanValue()) {
            this.duration = duration;
            this.fadeInDuration = fadeInDuration;
            this.fadeOutDuration = fadeOutDuration;
        }
    }
}

