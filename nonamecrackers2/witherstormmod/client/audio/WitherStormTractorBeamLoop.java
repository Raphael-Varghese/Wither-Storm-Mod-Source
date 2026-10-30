package nonamecrackers2.witherstormmod.client.audio;

import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.client.audio.TractorBeamLoop;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;

public class WitherStormTractorBeamLoop
extends TractorBeamLoop<WitherStormEntity> {
    private final int head;

    public WitherStormTractorBeamLoop(WitherStormEntity entity, int head) {
        super(entity);
        this.head = head;
    }

    public int getHead() {
        return this.head;
    }

    @Override
    public boolean shouldStop(double distance) {
        return ((WitherStormEntity)this.entity).isDeadOrPlayingDead() || !((WitherStormEntity)this.entity).m_6084_() || distance > TractorBeamLoop.DISTANCE_REQUIRED || !((WitherStormEntity)this.entity).tractorBeamActive(this.head);
    }

    @Override
    public void calculateVolume() {
        this.f_119573_ = this.head == 0 || this.head > 0 && !((WitherStormEntity)this.entity).areOtherHeadsDisabled() ? Math.max(0.0f, 0.3f - this.getDistance(new Vec3(this.f_119575_, this.f_119576_, this.f_119577_)) / 60.0f) : 0.0f;
    }

    @Override
    protected Vec3 calculateClosestPoint() {
        return TractorBeamHelper.calculateClosestPoint(this.player.m_20182_(), (WitherStormEntity)this.entity, this.head);
    }
}

