/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package nonamecrackers2.witherstormmod.common.entity.goal;

import java.util.EnumSet;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;

public class LookAtTargetGoal<T extends LivingEntity>
extends Goal {
    protected final T mob;
    protected final int headIndex;
    protected final Function<T, Integer> lookStepsGetter;
    protected LivingEntity target;

    public LookAtTargetGoal(T mob, int headIndex, Function<T, Integer> lookStepsGetter) {
        this.mob = mob;
        this.headIndex = headIndex;
        this.lookStepsGetter = lookStepsGetter;
        this.m_7021_(EnumSet.of(Goal.Flag.LOOK));
    }

    public boolean m_8036_() {
        LivingEntity target = this.getTarget();
        if (target != null && target.m_6084_()) {
            this.target = target;
            return true;
        }
        return false;
    }

    @Nullable
    protected LivingEntity getTarget() {
        return ((WitherStormBase)this.mob).getTarget(this.headIndex);
    }

    public void m_8041_() {
        this.target = null;
        ((WitherStormBase)this.mob).setLookAt(this.headIndex, null);
        super.m_8041_();
    }

    public void m_8037_() {
        ((WitherStormBase)this.mob).setLookAt(this.headIndex, this.target.m_146892_(), this.lookStepsGetter.apply(this.mob));
    }
}

