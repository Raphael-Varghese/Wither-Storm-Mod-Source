package nonamecrackers2.witherstormmod.client.audio.bosstheme;

import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.resources.sounds.TickableSoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.client.audio.ISoundManager;
import nonamecrackers2.witherstormmod.client.audio.bosstheme.BossThemeLoop;
import nonamecrackers2.witherstormmod.common.entity.BossThemeEntity;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;
import nonamecrackers2.witherstormmod.mixin.MixinSoundEngineAccessor;
import nonamecrackers2.witherstormmod.mixin.MixinSoundManagerAccessor;

public class BossThemeManager
implements ISoundManager {
    public static final int WATERMARK_TIME = 160;
    protected final Minecraft minecraft;
    @Nullable
    protected BossThemeLoop theme;
    protected Component watermark;
    protected int watermarkTime;
    protected int startTime;

    public BossThemeManager(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public BossThemeManager() {
        this(null);
    }

    @Override
    public void tick() {
        ClientLevel world = this.minecraft.f_91073_;
        SoundManager manager = this.minecraft.m_91106_();
        for (Entity entity : world.m_104735_()) {
            BossThemeEntity bossEntity;
            if (!(entity instanceof BossThemeEntity) || !(this.minecraft.f_91066_.m_92147_((bossEntity = (BossThemeEntity)entity).getCategory()) > 0.0f) || !(this.minecraft.f_91066_.m_92147_(SoundSource.MASTER) > 0.0f) || !bossEntity.shouldPlayBossTheme() || !BossThemeManager.inRange(bossEntity, this.minecraft.f_91074_) || !BossThemeManager.hasGeneralAccessTo(bossEntity, this.minecraft.f_91074_)) continue;
            BossThemeEntity current = this.getEntity();
            if (current != null) {
                if (current == bossEntity) {
                    this.theme.continueSound();
                    continue;
                }
                if (!bossEntity.hasPriority(current)) continue;
                if (this.theme != null) {
                    this.theme.stopSound();
                }
                this.playLoop(bossEntity);
                continue;
            }
            this.playLoop(bossEntity);
        }
        SoundEngine engine = ((MixinSoundManagerAccessor)manager).witherstormmod$getSoundEngine();
        if (this.theme != null) {
            if (this.theme.m_7801_()) {
                this.theme = null;
            } else if (!manager.m_120403_((SoundInstance)this.theme) && !((MixinSoundEngineAccessor)engine).witherstormmod$getQueuedTickableSounds().contains(this.theme)) {
                this.theme = null;
            } else if (this.minecraft.f_91066_.m_92147_(this.theme.m_8070_()) <= 0.0f || this.minecraft.f_91066_.m_92147_(SoundSource.MASTER) <= 0.0f) {
                this.theme.forceStop();
                this.theme = null;
            }
        }
        if (this.theme != null && !this.theme.event.equals(this.theme.getEntity().getBossTheme())) {
            this.theme.stopSound();
            this.playLoop(this.theme.getEntity());
        }
        if (this.watermarkTime > 0) {
            --this.watermarkTime;
        }
    }

    @Nullable
    public BossThemeEntity getEntity() {
        if (this.theme != null) {
            return this.theme.getEntity();
        }
        return null;
    }

    private void playLoop(BossThemeEntity entity) {
        this.theme = new BossThemeLoop(entity);
        this.minecraft.m_91106_().m_120372_((TickableSoundInstance)this.theme);
        this.watermark = entity.getWatermark();
        this.watermarkTime = 160;
        this.startTime = 160;
    }

    public void forceStop() {
        if (this.theme != null) {
            this.theme.forceStop();
        }
    }

    @Override
    public void refresh() {
        if (this.theme != null && !this.theme.m_7801_() && !this.theme.isStopping()) {
            this.theme.forceStop();
            this.playLoop(this.theme.getEntity());
        }
    }

    public boolean isPlaying() {
        return this.theme != null;
    }

    protected static boolean inRange(BossThemeEntity entity, LocalPlayer player) {
        boolean flag = true;
        if (entity.distanceToPlay() > 0.0) {
            flag = Math.sqrt(player.m_20275_(entity.getPosition().m_7096_(), entity.getPosition().m_7098_(), entity.getPosition().m_7094_())) < entity.distanceToPlay();
        }
        return flag;
    }

    protected static boolean hasGeneralAccessTo(BossThemeEntity theme, LocalPlayer player) {
        if (theme.smartBossMusic()) {
            return WorldUtil.canSeeOrIsNotInASmallArea((Entity)theme, (Entity)player);
        }
        return true;
    }

    @Nullable
    public Component getWatermark() {
        return this.watermark;
    }

    public int getWatermarkTime() {
        return this.watermarkTime;
    }

    public int getWatermarkStartTime() {
        return this.startTime;
    }
}

