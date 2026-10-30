package nonamecrackers2.witherstormmod.client.audio;

import java.util.function.Predicate;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.client.audio.FadingSoundLoop;
import nonamecrackers2.witherstormmod.client.audio.IForceStoppableSound;

public class EntitySoundLoop<T extends Entity>
extends FadingSoundLoop
implements IForceStoppableSound {
    public final T entity;
    protected int fadeTime;
    protected float maxVolume;
    protected final Predicate<T> shouldStop;

    public EntitySoundLoop(T entity, SoundEvent event, SoundSource category, int fadeTime, float maxVolume, Predicate<T> shouldStop) {
        super(event, category);
        this.entity = entity;
        this.fadeTime = fadeTime;
        this.maxVolume = maxVolume;
        this.shouldStop = shouldStop;
    }

    @Override
    public void m_7788_() {
        super.m_7788_();
        this.f_119575_ = this.entity.m_20185_();
        this.f_119576_ = this.entity.m_20186_();
        this.f_119577_ = this.entity.m_20189_();
        if (!this.entity.m_6084_() || this.shouldStop.test(this.entity)) {
            this.stopSound();
        }
    }

    @Override
    protected int getFadeTime() {
        return this.fadeTime;
    }

    @Override
    protected float maximumVolume() {
        return this.maxVolume;
    }

    @Override
    public void forceStop() {
        this.m_119609_();
    }
}

