package nonamecrackers2.witherstormmod.client.audio.bosstheme;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.client.audio.FadingSoundLoop;
import nonamecrackers2.witherstormmod.client.audio.IForceStoppableSound;
import nonamecrackers2.witherstormmod.client.audio.bosstheme.BossThemeManager;
import nonamecrackers2.witherstormmod.common.entity.BossThemeEntity;

public class BossThemeLoop
extends FadingSoundLoop
implements IForceStoppableSound {
    private final Minecraft minecraft;
    protected BossThemeEntity entity;
    protected final SoundEvent event;

    public BossThemeLoop(BossThemeEntity entity) {
        super(entity.getBossTheme(), entity.getCategory());
        this.f_119582_ = true;
        this.f_119580_ = SoundInstance.Attenuation.NONE;
        this.minecraft = Minecraft.m_91087_();
        this.entity = entity;
        this.event = entity.getBossTheme();
    }

    protected BossThemeLoop(BossThemeEntity entity, SoundEvent event) {
        super(event, entity.getCategory());
        this.minecraft = Minecraft.m_91087_();
        this.entity = entity;
        this.event = event;
    }

    @Override
    public void m_7788_() {
        super.m_7788_();
        this.f_119575_ = this.entity.getPosition().m_7096_();
        this.f_119576_ = this.entity.getPosition().m_7098_();
        this.f_119577_ = this.entity.getPosition().m_7094_();
        if (!(this.entity.shouldPlayBossTheme() && BossThemeManager.inRange(this.entity, this.minecraft.f_91074_) && BossThemeManager.hasGeneralAccessTo(this.entity, this.minecraft.f_91074_))) {
            ClientLevel world = this.minecraft.f_91073_;
            boolean flag = false;
            for (Entity entity : world.m_104735_()) {
                BossThemeEntity bossEntity;
                if (!(entity instanceof BossThemeEntity) || !(bossEntity = (BossThemeEntity)entity).shouldPlayBossTheme() || !this.entity.matches(bossEntity) || !BossThemeManager.inRange(bossEntity, this.minecraft.f_91074_) || !BossThemeManager.hasGeneralAccessTo(bossEntity, this.minecraft.f_91074_)) continue;
                this.entity = bossEntity;
                flag = true;
                break;
            }
            if (!flag) {
                this.stopSound();
            }
        }
    }

    @Override
    protected int getFadeTime() {
        return this.entity.getFadeTime();
    }

    @Override
    public void forceStop() {
        this.m_119609_();
    }

    public BossThemeEntity getEntity() {
        return this.entity;
    }
}

