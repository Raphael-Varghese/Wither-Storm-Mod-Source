/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head;

import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.HeadManager;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
import nonamecrackers2.witherstormmod.common.util.HeadConfiguration;

public class AdditionalHead
extends WitherStormHead {
    @Nullable
    protected LivingEntity target;
    public float xRot;
    public float yRot;
    public float xRotO;
    public float yRotO;

    public AdditionalHead(WitherStormEntity storm, int headIndex) {
        super(storm, headIndex, true);
    }

    @Override
    public CompoundTag save() {
        CompoundTag tag = super.save();
        tag.m_128350_("xRot", this.xRot);
        tag.m_128350_("yRot", this.yRot);
        return tag;
    }

    @Override
    public void read(CompoundTag tag) {
        super.read(tag);
        if (tag.m_128441_("xRot")) {
            this.xRot = tag.m_128457_("xRot");
        }
        if (tag.m_128441_("yRot")) {
            this.yRot = tag.m_128457_("yRot");
        }
    }

    @Override
    public void setLookPos(@Nullable Vec3 pos, int steps) {
        this.setLookSteps(steps);
        this.storm.m_20088_().m_135381_(HeadManager.TARGETS.get(this.headIndex), Optional.ofNullable(pos));
    }

    @Nullable
    public Vec3 getLookPos() {
        return ((Optional)this.storm.m_20088_().m_135370_(HeadManager.TARGETS.get(this.headIndex))).orElse(null);
    }

    protected void setLookSteps(int steps) {
        this.storm.m_20088_().m_135381_(HeadManager.LOOK_STEPS.get(this.headIndex), (Object)steps);
    }

    protected int getLookSteps() {
        return (Integer)this.storm.m_20088_().m_135370_(HeadManager.LOOK_STEPS.get(this.headIndex));
    }

    @Override
    public void baseTick(HeadConfiguration config) {
        super.baseTick(config);
        this.xRotO = this.xRot;
        this.yRotO = this.yRot;
    }

    @Override
    public void doHeadLookLogic() {
        Vec3 pos = this.getLookPos();
        if (pos != null) {
            Vec3 headPos = this.getHeadPos();
            double deltaX = pos.m_7096_() - headPos.f_82479_;
            double deltaY = pos.m_7098_() - headPos.f_82480_;
            double deltaZ = pos.m_7094_() - headPos.f_82481_;
            double d7 = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
            float f = (float)(Mth.m_14136_((double)deltaZ, (double)deltaX) * 57.2957763671875) - 90.0f;
            float f1 = (float)(-(Mth.m_14136_((double)deltaY, (double)d7) * 57.2957763671875));
            this.lerpHeadTo(f1, f, this.getLookSteps());
        } else if (!this.storm.isOnDistantRenderer() && !this.storm.isDeadOrPlayingDead()) {
            this.yRot = WitherStormEntity.m_21376_(this.yRot, this.storm.f_20883_, 10.0f);
        }
    }

    @Override
    public float getHeadXRot() {
        return this.xRot;
    }

    @Override
    public float getHeadXRotO() {
        return this.xRotO;
    }

    @Override
    public float getHeadYRot() {
        return this.yRot;
    }

    @Override
    public float getHeadYRotO() {
        return this.yRotO;
    }

    @Override
    public void setHeadXRot(float rot) {
        this.xRot = rot;
    }

    @Override
    public void setHeadYRot(float rot) {
        this.yRot = rot;
    }

    @Override
    public LivingEntity getTarget() {
        return this.target;
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        this.target = target;
    }
}

