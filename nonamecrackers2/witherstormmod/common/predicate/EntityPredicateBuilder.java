/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 */
package nonamecrackers2.witherstormmod.common.predicate;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import nonamecrackers2.witherstormmod.common.predicate.PredicateBuilder;

public class EntityPredicateBuilder<T extends Entity>
extends PredicateBuilder<T, EntityPredicateBuilder<T>> {
    private EntityPredicateBuilder(PredicateBuilder.Comparison comparisonMethod) {
        super(comparisonMethod);
    }

    public static <T extends Entity> EntityPredicateBuilder<T> or() {
        return new EntityPredicateBuilder<T>(PredicateBuilder.Comparison.OR);
    }

    public static <T extends Entity> EntityPredicateBuilder<T> and() {
        return new EntityPredicateBuilder<T>(PredicateBuilder.Comparison.AND);
    }

    @Override
    public EntityPredicateBuilder<T> isExactly(EntityType<? extends T> type) {
        return (EntityPredicateBuilder)this.addTest(e -> e.m_6095_().equals(type));
    }

    @Override
    public EntityPredicateBuilder<T> isNotExactly(EntityType<? extends T> type) {
        return (EntityPredicateBuilder)this.addTest(e -> !e.m_6095_().equals(type));
    }

    public EntityPredicateBuilder<T> isTag(TagKey<EntityType<?>> tag) {
        return (EntityPredicateBuilder)this.addTest(e -> e.m_6095_().m_204039_(tag));
    }

    public EntityPredicateBuilder<T> isNotTag(TagKey<EntityType<?>> tag) {
        return (EntityPredicateBuilder)this.addTest(e -> !e.m_6095_().m_204039_(tag));
    }
}

