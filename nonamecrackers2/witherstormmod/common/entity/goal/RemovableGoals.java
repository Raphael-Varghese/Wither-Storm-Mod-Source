/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.util.Pair
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.GoalSelector
 *  net.minecraft.world.entity.ai.goal.WrappedGoal
 */
package nonamecrackers2.witherstormmod.common.entity.goal;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.WrappedGoal;

public class RemovableGoals {
    private final List<Pair<Integer, Goal>> goals;

    private RemovableGoals(List<Pair<Integer, Goal>> goals) {
        this.goals = goals;
    }

    public void putGoals(GoalSelector selector) {
        this.goals.forEach(pair -> {
            if (!selector.m_148105_().stream().map(WrappedGoal::m_26015_).collect(Collectors.toSet()).contains(pair.getSecond())) {
                selector.m_25352_(((Integer)pair.getFirst()).intValue(), (Goal)pair.getSecond());
            }
        });
    }

    public void removeGoals(GoalSelector selector) {
        this.goals.forEach(pair -> selector.m_25363_((Goal)pair.getSecond()));
    }

    public static class Builder {
        private final List<Pair<Integer, Goal>> goals = Lists.newArrayList();

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder put(int priority, Goal goal) {
            this.goals.add((Pair<Integer, Goal>)Pair.of((Object)priority, (Object)goal));
            return this;
        }

        public RemovableGoals build(GoalSelector selector) {
            if (this.goals.size() > 0) {
                RemovableGoals goals = new RemovableGoals(this.goals);
                goals.putGoals(selector);
                return goals;
            }
            throw new IllegalStateException("No goals present");
        }
    }
}

