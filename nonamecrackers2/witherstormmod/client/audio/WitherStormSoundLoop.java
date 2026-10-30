
package nonamecrackers2.witherstormmod.client.audio;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.client.audio.FadingSoundLoop;
import nonamecrackers2.witherstormmod.client.audio.IForceStoppableSound;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class WitherStormSoundLoop
extends FadingSoundLoop
implements IForceStoppableSound {
    public Vec3 pos;
    public Vec3 prevPos;
    public final SoundEvent soundevent;
    protected Optional<WitherStormEntity> entity;

    public WitherStormSoundLoop(@Nullable WitherStormEntity entity, Vec3 pos, SoundEvent event) {
        super(event, SoundSource.AMBIENT);
        this.pos = pos;
        this.prevPos = pos;
        this.f_119575_ = pos.f_82479_;
        this.f_119576_ = pos.f_82480_;
        this.f_119577_ = pos.f_82481_;
        this.soundevent = event;
        this.entity = Optional.ofNullable(entity);
    }

    public WitherStormSoundLoop(@Nonnull WitherStormEntity entity, SoundEvent event) {
        this(entity, entity.m_20182_(), event);
    }

    public WitherStormSoundLoop(Vec3 pos, SoundEvent event) {
        this(null, pos, event);
    }

    public Vec3 getPos() {
        return this.pos;
    }

    public SoundEvent getSoundEvent() {
        return this.soundevent;
    }

    @Override
    public void m_7788_() {
        LocalPlayer player = Minecraft.m_91087_().f_91074_;
        double distance = Math.sqrt(player.m_20238_(this.pos));
        if (distance < 1000.0) {
            float dampenAmount;
            if (((Boolean)WitherStormModConfig.SERVER.occludeSoundsUnderground.get()).booleanValue()) {
                boolean cave = !WorldUtil.isInAnOpenArea((Entity)player);
                dampenAmount = cave ? 30.0f + Mth.m_14036_((float)((float)(-player.m_20186_()) + 40.0f), (float)0.0f, (float)20.0f) : 15.0f;
            } else {
                dampenAmount = 15.0f;
            }
            BlockHitResult ray = player.m_9236_().m_45547_(new ClipContext(this.pos, player.m_20182_(), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, null));
            if (ray.m_6662_() == HitResult.Type.BLOCK && this.dampen < dampenAmount) {
                this.dampen += 1.0f;
            } else if (ray.m_6662_() == HitResult.Type.BLOCK && this.dampen > dampenAmount) {
                this.dampen -= 1.0f;
            } else if (ray.m_6662_() == HitResult.Type.MISS && this.dampen > 0.0f) {
                this.dampen -= 1.0f;
            }
        } else {
            this.dampen = 0.0f;
        }
        this.f_119575_ = this.pos.f_82479_;
        this.f_119576_ = this.pos.f_82480_;
        this.f_119577_ = this.pos.f_82481_;
        this.entity.ifPresent(entity -> {
            if (entity.m_21224_() || !entity.isAddedToWorld()) {
                this.stopSound();
            }
        });
        super.m_7788_();
    }

    @Override
    public void forceStop() {
        this.m_119609_();
    }

    @Override
    protected int getFadeTime() {
        return 40;
    }
}

