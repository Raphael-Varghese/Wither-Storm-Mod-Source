/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.entity.Entity
 */
package nonamecrackers2.witherstormmod.common.entity.bossfight;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.common.entity.bossfight.BossfightPhase;

public class BossfightManager<T extends Entity> {
    private final T entity;
    private final Int2ObjectMap<AdvanceableBossfightPhase<T>> phases = new Int2ObjectOpenHashMap();
    @Nullable
    private AdvanceableBossfightPhase<T> currentPhase;
    private int phaseIndex;
    private int ticksSincePhaseInit;

    private BossfightManager(BossfightPhase<T> defaultPhase, AdvanceableBossfightPhase.PhaseAdvancement advancement, int next, T entity) {
        this.entity = entity;
        this.addPhase(0, defaultPhase, advancement, next);
        this.setCurrentPhase(0);
    }

    public BossfightManager(BossfightPhase<T> defaultPhase, AdvanceableBossfightPhase.PhaseAdvancement advancement, T entity) {
        this(defaultPhase, advancement, 0, entity);
        if (advancement == AdvanceableBossfightPhase.PhaseAdvancement.SPECIFIED) {
            throw new IllegalArgumentException("Use BossFightManager(defaultPhase, next, entity) instead");
        }
    }

    public BossfightManager(BossfightPhase<T> defaultPhase, int next, T entity) {
        this(defaultPhase, AdvanceableBossfightPhase.PhaseAdvancement.SPECIFIED, next, entity);
    }

    public BossfightManager(BossfightPhase<T> defaultPhase, T entity) {
        this(defaultPhase, AdvanceableBossfightPhase.PhaseAdvancement.NEXT, 0, entity);
    }

    private BossfightManager<T> addPhase(int key, BossfightPhase<T> phase, AdvanceableBossfightPhase.PhaseAdvancement advancement, int next) {
        this.phases.put(key, new AdvanceableBossfightPhase<T>(phase, advancement, key));
        return this;
    }

    public BossfightManager<T> addPhase(int key, BossfightPhase<T> phase, AdvanceableBossfightPhase.PhaseAdvancement advancement) {
        if (advancement == AdvanceableBossfightPhase.PhaseAdvancement.SPECIFIED) {
            throw new IllegalArgumentException("Use addPhase(key, phase, next) instead");
        }
        return this.addPhase(key, phase, advancement, 0);
    }

    public BossfightManager<T> addPhase(int key, BossfightPhase<T> phase, int next) {
        return this.addPhase(key, phase, AdvanceableBossfightPhase.PhaseAdvancement.SPECIFIED, next);
    }

    public BossfightManager<T> addPhase(int key, BossfightPhase<T> phase) {
        return this.addPhase(key, phase, AdvanceableBossfightPhase.PhaseAdvancement.NEXT, 0);
    }

    public void tick() {
        ++this.ticksSincePhaseInit;
        this.currentPhase.getPhase().tick(this, this.entity);
        if (this.currentPhase.getPhase().shouldAdvance(this, this.entity)) {
            this.currentPhase.advance(this);
        }
    }

    public void goToNextPhase() {
        int nextPhase = this.phaseIndex + 1;
        if (nextPhase > this.phases.size() - 1) {
            nextPhase = 0;
        }
        this.setCurrentPhase(nextPhase);
    }

    public void goToPreviousPhase() {
        int nextPhase = this.phaseIndex - 1;
        if (nextPhase < 0) {
            nextPhase = this.phases.size() - 1;
        }
        this.setCurrentPhase(nextPhase);
    }

    public void setCurrentPhase(int key) {
        if (this.currentPhase != null) {
            this.currentPhase.getPhase().finish(this, this.entity);
        }
        this.phaseIndex = key;
        this.currentPhase = (AdvanceableBossfightPhase)this.phases.get(key);
        this.currentPhase.getPhase().init(this, this.entity);
        this.ticksSincePhaseInit = 0;
    }

    public void setCurrentFromNextInOrder(BossfightPhase<T> phase) {
        for (Int2ObjectMap.Entry entry : this.phases.int2ObjectEntrySet()) {
            AdvanceableBossfightPhase value = (AdvanceableBossfightPhase)entry.getValue();
            if (value.getPhase() != phase) continue;
            this.setCurrentPhase(entry.getIntKey());
            break;
        }
    }

    public BossfightPhase<T> getPhase(int key) {
        return ((AdvanceableBossfightPhase)this.phases.get(key)).getPhase();
    }

    public int getTicksSincePhaseInit() {
        return this.ticksSincePhaseInit;
    }

    public BossfightPhase<T> getCurrentPhase() {
        return this.currentPhase.getPhase();
    }

    public int getCurrentPhaseIndex() {
        return this.phaseIndex;
    }

    public CompoundTag write() {
        CompoundTag compound = new CompoundTag();
        compound.m_128405_("CurrentPhase", this.phaseIndex);
        compound.m_128405_("PhaseTicks", this.ticksSincePhaseInit);
        return compound;
    }

    public void read(CompoundTag compound) {
        this.setCurrentPhase(compound.m_128451_("CurrentPhase"));
        this.ticksSincePhaseInit = compound.m_128451_("PhaseTicks");
    }

    public static class AdvanceableBossfightPhase<T extends Entity> {
        private final BossfightPhase<T> phase;
        private final PhaseAdvancement phaseAdvancement;
        private final int nextPhase;

        public AdvanceableBossfightPhase(BossfightPhase<T> phase, PhaseAdvancement advancement, int next) {
            this.phase = phase;
            this.phaseAdvancement = advancement;
            this.nextPhase = next;
        }

        public void advance(BossfightManager<T> manager) {
            this.phaseAdvancement.toNextPhase(manager, this);
        }

        public int getPhaseToGoTo() {
            return this.nextPhase;
        }

        public BossfightPhase<T> getPhase() {
            return this.phase;
        }

        /*
         * Uses 'sealed' constructs - enablewith --sealed true
         */
        public static enum PhaseAdvancement {
            NEXT{

                @Override
                public <T extends Entity> void toNextPhase(BossfightManager<T> manager, AdvanceableBossfightPhase<T> phase) {
                    manager.goToNextPhase();
                }
            }
            ,
            PREVIOUS{

                @Override
                public <T extends Entity> void toNextPhase(BossfightManager<T> manager, AdvanceableBossfightPhase<T> phase) {
                    manager.goToPreviousPhase();
                }
            }
            ,
            SPECIFIED{

                @Override
                public <T extends Entity> void toNextPhase(BossfightManager<T> manager, AdvanceableBossfightPhase<T> phase) {
                    manager.setCurrentPhase(phase.getPhaseToGoTo());
                }
            };


            public abstract <T extends Entity> void toNextPhase(BossfightManager<T> var1, AdvanceableBossfightPhase<T> var2);
        }
    }
}

