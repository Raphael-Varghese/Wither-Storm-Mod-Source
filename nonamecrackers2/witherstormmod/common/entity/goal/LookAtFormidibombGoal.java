/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity.goal;

import java.util.EnumSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class LookAtFormidibombGoal
extends Goal {
    protected final WitherStormEntity storm;
    private FormidibombEntity target;

    public LookAtFormidibombGoal(WitherStormEntity storm) {
        this.storm = storm;
        this.m_7021_(EnumSet.of(Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        if (this.storm.canBeFormidibombed(false)) {
            this.target = this.storm.getFormidibomb();
            return true;
        }
        return false;
    }

    public boolean m_8045_() {
        return this.m_8036_();
    }

    public void m_8056_() {
        if (!this.storm.shouldIgnoreFormidibomb) {
            this.storm.getHeadManager().getHead(0).doRoar(false);
        }
        super.m_8056_();
    }

    public void m_8041_() {
        if (this.target != null) {
            this.target.m_20242_(false);
        }
        this.target = null;
        super.m_8041_();
    }

    public void m_8037_() {
        this.storm.m_21563_().m_24960_((Entity)this.target, 30.0f, 30.0f);
        Vec3 entityPos = this.target.m_20182_();
        Vec3 headPos = this.storm.getHeadPos(0);
        Vec3 motion = headPos.m_82546_(entityPos).m_82541_();
        this.target.m_20242_(true);
        this.target.m_20256_(motion.m_82542_(0.1, 0.1, 0.1));
    }

    public boolean hasTarget() {
        return this.target != null;
    }
}

