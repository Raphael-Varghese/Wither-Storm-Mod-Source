package nonamecrackers2.witherstormmod.client.audio;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import nonamecrackers2.witherstormmod.client.audio.IForceStoppableSound;
import nonamecrackers2.witherstormmod.common.blockentity.FormidibombBlockEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.util.IFormidibomb;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class FormidibombFuseLoop
extends AbstractTickableSoundInstance
implements IForceStoppableSound {
    public final IFormidibomb formidibomb;

    public FormidibombFuseLoop(IFormidibomb formidibomb) {
        super((SoundEvent)WitherStormModSoundEvents.FORMIDIBOMB_PULSE_LOOP.get(), SoundSource.BLOCKS, SoundInstance.m_235150_());
        this.formidibomb = formidibomb;
        this.f_119578_ = true;
    }

    public void m_7788_() {
        this.f_119573_ = 1.0f + this.getPercentage() * 2.0f;
        float additionalPitch = this.formidibomb.getStartFuse() > 0 ? (float)Math.max(0.0, (120.0 - (double)this.formidibomb.getFuseLife()) / 120.0) : 0.0f;
        this.f_119574_ = 1.0f + this.getPercentage() * 0.1f + additionalPitch;
        this.f_119575_ = this.formidibomb.getPosition().m_7096_();
        this.f_119576_ = this.formidibomb.getPosition().m_7098_();
        this.f_119577_ = this.formidibomb.getPosition().m_7094_();
        if (this.shouldStop()) {
            this.forceStop();
        }
    }

    private float getPercentage() {
        if (this.formidibomb.getStartFuse() > 0) {
            return ((float)this.formidibomb.getStartFuse() - (float)this.formidibomb.getFuseLife()) / (float)this.formidibomb.getStartFuse();
        }
        return 0.0f;
    }

    public boolean shouldStop() {
        boolean flag = false;
        if (this.formidibomb instanceof FormidibombBlockEntity) {
            FormidibombBlockEntity tile = (FormidibombBlockEntity)this.formidibomb;
            Minecraft mc = Minecraft.m_91087_();
            ClientLevel world = mc.f_91073_;
            List<BlockEntity> blocks = WorldUtil.getBlockEntitiesInAABB((Level)world, mc.f_91074_.m_20191_().m_82400_(50.0));
            flag = !blocks.contains(tile);
        }
        return !this.formidibomb.isStillAlive() || flag;
    }

    @Override
    public void forceStop() {
        this.m_119609_();
    }

    public boolean m_7784_() {
        return true;
    }
}

