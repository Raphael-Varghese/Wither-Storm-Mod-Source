/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.util;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public record HeadConfiguration(Predicate<WitherStormEntity> predicate, Int2ObjectMap<Vec3> offsetsByHead) {
    public static Builder builder(Predicate<WitherStormEntity> predicate) {
        return new Builder(predicate);
    }

    public static Builder forPhase(int phase) {
        return HeadConfiguration.builder(storm -> storm.getPhase() == phase);
    }

    public Vec3 getOffsetForHead(int head) {
        return (Vec3)this.offsetsByHead().getOrDefault(head, (Object)Vec3.f_82478_);
    }

    public static List<HeadConfiguration> makeSameFor(Consumer<Builder> consumer, int ... phases) {
        ImmutableList.Builder configurations = ImmutableList.builder();
        for (int phase : phases) {
            Builder builder = HeadConfiguration.forPhase(phase);
            consumer.accept(builder);
            configurations.add((Object)builder.build());
        }
        return configurations.build();
    }

    public static class Builder {
        private final Predicate<WitherStormEntity> predicate;
        private final Int2ObjectMap<Vec3> offsetsByHead = new Int2ObjectOpenHashMap();

        private Builder(Predicate<WitherStormEntity> predicate) {
            this.predicate = predicate;
        }

        public Builder addOffset(int head, double x, double y, double z) {
            this.offsetsByHead.put(head, (Object)new Vec3(x, y, z));
            return this;
        }

        public HeadConfiguration build() {
            return new HeadConfiguration(this.predicate, this.offsetsByHead);
        }
    }
}

