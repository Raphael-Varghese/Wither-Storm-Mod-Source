/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.control.LookControl
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller;

import java.util.Optional;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class WitherStormLookController
extends LookControl {
    private final WitherStormEntity storm;

    public WitherStormLookController(WitherStormEntity storm) {
        super((Mob)storm);
        this.storm = storm;
    }

    public void m_8128_() {
        if (this.f_186068_ > 0) {
            --this.f_186068_;
            this.m_180896_().ifPresent(rot -> {
                this.f_24937_.f_20885_ = this.m_24956_(this.f_24937_.f_20885_, rot.floatValue(), this.f_24938_);
            });
            this.m_180897_().ifPresent(rot -> this.f_24937_.m_146926_(this.m_24956_(this.f_24937_.m_146909_(), rot.floatValue(), this.f_24938_)));
        } else {
            this.f_24937_.f_20885_ = this.m_24956_(this.f_24937_.f_20885_, this.f_24937_.f_20883_, 10.0f);
        }
        this.m_142586_();
    }

    public void m_24950_(double wantedX, double wantedY, double wantedZ, float yMaxSpeed, float xMaxRotAngle) {
        this.f_24941_ = wantedX;
        this.f_24942_ = wantedY;
        this.f_24943_ = wantedZ;
        this.f_24938_ = yMaxSpeed;
        this.f_186068_ = 2;
    }

    protected Optional<Float> m_180897_() {
        if (this.storm.getPhase() > 3) {
            Vec3 pos = this.storm.getHeadPos(0);
            double d0 = this.f_24941_ - pos.f_82479_;
            double d1 = this.f_24942_ - pos.f_82480_;
            double d2 = this.f_24943_ - pos.f_82481_;
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            return Optional.of(Float.valueOf((float)(-(Mth.m_14136_((double)d1, (double)d3) * 57.2957763671875))));
        }
        return super.m_180897_();
    }

    protected Optional<Float> m_180896_() {
        if (this.storm.getPhase() > 3) {
            Vec3 pos = this.storm.getHeadPos(0);
            double d0 = this.f_24941_ - pos.f_82479_;
            double d1 = this.f_24943_ - pos.f_82481_;
            return Optional.of(Float.valueOf((float)(Mth.m_14136_((double)d1, (double)d0) * 57.2957763671875) - 90.0f));
        }
        return super.m_180896_();
    }

    protected boolean m_8106_() {
        return false;
    }
}

