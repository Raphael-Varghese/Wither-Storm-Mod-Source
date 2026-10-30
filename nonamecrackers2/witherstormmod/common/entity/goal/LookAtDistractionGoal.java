/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity.goal;

import java.util.EnumSet;
import java.util.function.Supplier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;

public class LookAtDistractionGoal<T extends Mob>
extends Goal {
    protected final int headIndex;
    protected final T entity;
    protected final Supplier<Double> allowedTargetingRadius;
    protected Vec3 target;

    public LookAtDistractionGoal(T entity, int headIndex, Supplier<Double> allowedTargetingRadius) {
        this.entity = entity;
        this.allowedTargetingRadius = allowedTargetingRadius;
        this.m_7021_(EnumSet.of(Goal.Flag.LOOK));
        this.headIndex = headIndex;
    }

    public LookAtDistractionGoal(T entity, int headIndex) {
        this(entity, headIndex, () -> Double.POSITIVE_INFINITY);
    }

    public boolean m_8036_() {
        Vec3 target = ((WitherStormBase)this.entity).getDistractedPos(this.headIndex);
        if (target != null && target.m_82546_(this.entity.m_20182_()).m_165924_() <= this.allowedTargetingRadius.get() && !((WitherStormBase)this.entity).isPosBehindBack(target)) {
            this.target = target;
            return true;
        }
        return false;
    }

    public void m_8041_() {
        this.target = null;
        ((WitherStormBase)this.entity).setDistractedPos(this.headIndex, null);
        super.m_8041_();
    }

    public void m_8037_() {
        ((WitherStormBase)this.entity).setLookAt(this.headIndex, this.target, 10);
    }
}

