/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;

public class MainHead
extends WitherStormHead {
    public MainHead(WitherStormEntity storm, int headIndex) {
        super(storm, headIndex, false);
    }

    @Override
    public void setLookPos(Vec3 pos, int steps) {
        if (pos != null) {
            this.storm.m_21563_().m_24964_(pos);
        }
    }

    @Override
    public float getHeadXRot() {
        return this.storm.m_146909_();
    }

    @Override
    public float getHeadXRotO() {
        return this.storm.f_19860_;
    }

    @Override
    public float getHeadYRot() {
        return this.storm.f_20885_;
    }

    @Override
    public float getHeadYRotO() {
        return this.storm.f_20886_;
    }

    @Override
    public void setHeadXRot(float rot) {
        this.storm.m_146926_(rot);
    }

    @Override
    public void setHeadYRot(float rot) {
        this.storm.m_146922_(rot);
    }

    @Override
    public LivingEntity getTarget() {
        return this.storm.m_5448_();
    }

    @Override
    public void setTarget(LivingEntity target) {
        this.storm.m_6710_(target);
    }

    @Override
    protected boolean canShootFlamingSkull() {
        return super.canShootFlamingSkull() || this.storm.getPhase() < 4 && this.getTarget() != null;
    }

    @Override
    public void doHeadLookLogic() {
    }
}

