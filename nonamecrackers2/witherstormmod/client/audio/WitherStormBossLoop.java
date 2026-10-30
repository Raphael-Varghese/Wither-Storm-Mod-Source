
package nonamecrackers2.witherstormmod.client.audio;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.client.audio.FadingSoundLoop;
import nonamecrackers2.witherstormmod.client.audio.IForceStoppableSound;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;

public class WitherStormBossLoop
extends FadingSoundLoop
implements IForceStoppableSound {
    private WitherStormEntity entity;

    public WitherStormBossLoop(WitherStormEntity entity, SoundEvent event) {
        super(event, SoundSource.RECORDS);
        this.entity = entity;
    }

    public WitherStormBossLoop(WitherStormEntity entity) {
        this(entity, (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_BOSS_THEME.get());
    }

    public WitherStormEntity getEntity() {
        return this.entity;
    }

    @Override
    public void m_7788_() {
        if (this.entity == null) {
            this.m_119609_();
            return;
        }
        this.f_119575_ = this.entity.m_20185_();
        this.f_119576_ = this.entity.m_20186_();
        this.f_119577_ = this.entity.m_20189_();
        if (!this.entity.shouldPlayBossTheme()) {
            ClientLevel world = (ClientLevel)this.entity.m_9236_();
            boolean flag = false;
            for (Entity entity : world.m_104735_()) {
                WitherStormEntity storm;
                if (!(entity instanceof WitherStormEntity) || !(storm = (WitherStormEntity)entity).shouldPlayBossTheme()) continue;
                this.entity = storm;
                flag = true;
                break;
            }
            if (!flag) {
                this.stopSound();
            }
        }
    }

    @Override
    public void forceStop() {
        this.m_119609_();
    }

    @Override
    protected int getFadeTime() {
        return 240;
    }
}

