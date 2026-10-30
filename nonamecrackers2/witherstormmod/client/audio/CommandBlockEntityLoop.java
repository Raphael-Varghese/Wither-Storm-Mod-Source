package nonamecrackers2.witherstormmod.client.audio;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import nonamecrackers2.witherstormmod.client.audio.IForceStoppableSound;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;

public class CommandBlockEntityLoop
extends AbstractTickableSoundInstance
implements IForceStoppableSound {
    public final CommandBlockEntity entity;

    public CommandBlockEntityLoop(CommandBlockEntity entity) {
        super((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_PULSE_LOOP.get(), SoundSource.AMBIENT, SoundInstance.m_235150_());
        this.entity = entity;
        this.f_119578_ = true;
    }

    public void m_7788_() {
        if (this.shouldFadeOut()) {
            if (this.f_119573_ > 0.0f) {
                this.f_119573_ -= 0.1f;
            }
        } else if (this.f_119573_ < 0.5f) {
            this.f_119573_ += 0.1f;
        }
        this.f_119574_ = (this.entity.m_21233_() - this.entity.m_21223_()) / this.entity.m_21233_() * 0.4f + 1.0f;
        this.f_119575_ = this.entity.m_20185_();
        this.f_119576_ = this.entity.m_20186_();
        this.f_119577_ = this.entity.m_20189_();
        if (this.shouldStop()) {
            this.forceStop();
        }
    }

    private boolean shouldFadeOut() {
        return this.entity.getState() == CommandBlockEntity.State.PLAYING_DEAD;
    }

    public boolean m_7784_() {
        return true;
    }

    public boolean shouldStop() {
        return !this.entity.m_6084_();
    }

    @Override
    public void forceStop() {
        this.m_119609_();
    }
}

