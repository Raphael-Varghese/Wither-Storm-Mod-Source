/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 */
package nonamecrackers2.witherstormmod.common.entity.goal;

import java.util.EnumSet;
import net.minecraft.world.entity.ai.goal.Goal;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class DoNothingGoal
extends Goal {
    private final WitherStormEntity storm;
    private final int head;

    public DoNothingGoal(WitherStormEntity entity, int head) {
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
        this.storm = entity;
        this.head = head;
    }

    public boolean m_8036_() {
        return this.storm.shouldDoNothing() || this.head > 0 && this.storm.areOtherHeadsDisabled();
    }
}

