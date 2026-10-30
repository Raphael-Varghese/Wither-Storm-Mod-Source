
package nonamecrackers2.witherstormmod.client.audio;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
import nonamecrackers2.witherstormmod.client.audio.FadingSoundLoop;
import nonamecrackers2.witherstormmod.client.audio.IForceStoppableSound;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;

public class WitheredSymbiontSpellLoop
extends FadingSoundLoop
implements IForceStoppableSound {
    public final WitheredSymbiontEntity entity;
    public final SpellType spell;

    public WitheredSymbiontSpellLoop(WitheredSymbiontEntity entity, SoundEvent event) {
        super(event, SoundSource.AMBIENT);
        this.entity = entity;
        this.spell = entity.getSpell();
    }

    @Override
    public void m_7788_() {
        super.m_7788_();
        this.f_119575_ = this.entity.m_20185_();
        this.f_119576_ = this.entity.m_20186_();
        this.f_119577_ = this.entity.m_20189_();
        if (!this.entity.m_6084_() || !this.entity.isCastingSpell() || this.entity.getSpell() != this.spell) {
            this.stopSound();
        }
    }

    @Override
    protected int getFadeTime() {
        return 20;
    }

    @Override
    public void forceStop() {
        this.m_119609_();
    }
}

