package nonamecrackers2.witherstormmod.client.audio;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

public abstract class FadingSoundLoop
extends AbstractTickableSoundInstance {
    protected float fade;
    protected boolean stopping;
    protected boolean starting = true;
    protected float dampen;

    protected FadingSoundLoop(SoundEvent event, SoundSource category) {
        super(event, category, SoundInstance.m_235150_());
        this.f_119573_ = 0.0f;
        this.f_119579_ = 0;
        this.f_119578_ = true;
    }

    public void m_7788_() {
        if (this.stopping) {
            if (this.fade > 0.0f) {
                this.fade -= 1.0f;
            } else {
                this.m_119609_();
            }
        } else if (this.starting) {
            if (this.fade < (float)this.getFadeTime()) {
                this.fade += 1.0f;
            } else {
                this.starting = false;
            }
        }
        float volume = Math.max(0.0f, Math.min(this.fade / (float)this.getFadeTime(), 1.0f)) * this.maximumVolume();
        this.f_119573_ = volume - Math.max(0.0f, this.dampen * 0.02f);
    }

    protected boolean shouldFadeOut() {
        return this.stopping;
    }

    public boolean isStopping() {
        return this.stopping;
    }

    public boolean isStarting() {
        return this.starting;
    }

    public void stopSound() {
        if (!this.stopping) {
            this.stopping = true;
            this.fade = this.getFadeTime();
        }
    }

    public void continueSound() {
        if (this.stopping) {
            this.stopping = false;
            this.starting = true;
        }
    }

    protected abstract int getFadeTime();

    protected float maximumVolume() {
        return 1.0f;
    }

    public boolean m_7784_() {
        return true;
    }
}

