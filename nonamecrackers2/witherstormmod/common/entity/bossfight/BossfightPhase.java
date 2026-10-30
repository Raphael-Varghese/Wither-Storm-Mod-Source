/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 */
package nonamecrackers2.witherstormmod.common.entity.bossfight;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.common.entity.bossfight.BossfightManager;

public class BossfightPhase<T extends Entity> {
    @Nullable
    private BossfightPhase<T> parent;
    private boolean hasFixedTime;
    private int time;
    private Predicate<T> shouldMoveToNextPhase;
    private Consumer<T> initAction;
    private Consumer<T> finishAction = entity -> {};
    private BiConsumer<Integer, T> action = (time, entity) -> {};

    public BossfightPhase(Consumer<T> initAction, Predicate<T> shouldMoveToNextPhase) {
        this.initAction = initAction;
        this.shouldMoveToNextPhase = shouldMoveToNextPhase;
    }

    public BossfightPhase(Predicate<T> shouldMoveToNextPhase) {
        this((T entity) -> {}, shouldMoveToNextPhase);
    }

    public BossfightPhase(Consumer<T> initAction, int time) {
        this(initAction, entity -> false);
        this.setFixedTime(time);
    }

    public static <T extends Entity> BossfightPhase<T> blank() {
        return new BossfightPhase<Entity>(entity -> false);
    }

    public static <T extends Entity> BossfightPhase<T> copyOf(BossfightPhase<T> parent, Consumer<T> initAction, Predicate<T> shouldMoveToNextPhase) {
        BossfightPhase<T> phase = new BossfightPhase<T>(parent.initAction.andThen(initAction), parent.shouldMoveToNextPhase.or(shouldMoveToNextPhase)).setTickAction(parent.action).setFinishAction(parent.finishAction);
        if (parent.hasFixedTime) {
            phase.setFixedTime(parent.time);
        }
        phase.parent = parent;
        return phase;
    }

    public BossfightPhase<T> setTickAction(BiConsumer<Integer, T> action) {
        this.action = action;
        return this;
    }

    public BossfightPhase<T> setFinishAction(Consumer<T> action) {
        this.finishAction = action;
        return this;
    }

    public BossfightPhase<T> setFixedTime(int time) {
        this.hasFixedTime = true;
        this.time = time;
        return this;
    }

    public void init(BossfightManager<T> manager, T entity) {
        this.initAction.accept(entity);
    }

    public void tick(BossfightManager<T> manager, T entity) {
        this.action.accept(manager.getTicksSincePhaseInit(), (Integer)entity);
    }

    public void finish(BossfightManager<T> manager, T entity) {
        this.finishAction.accept(entity);
    }

    public boolean shouldAdvance(BossfightManager<T> manager, T entity) {
        if (this.hasFixedTime) {
            return manager.getTicksSincePhaseInit() > this.time;
        }
        return this.shouldMoveToNextPhase.test(entity);
    }

    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }
        if (this.parent != null) {
            BossfightPhase<T> current = this.parent;
            while (current != null) {
                if (current == obj) {
                    return true;
                }
                current = current.parent;
            }
            return false;
        }
        return false;
    }
}

