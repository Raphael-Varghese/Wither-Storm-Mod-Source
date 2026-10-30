/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 */
package nonamecrackers2.witherstormmod.common.entity.section;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.section.Section;

public class CollisionActionSection
extends Section {
    private final Consumer<Entity> action;

    public CollisionActionSection(WitherStormEntity entity, float height, float width, double x, double y, double z, int phase, Consumer<Entity> action) {
        super(entity, height, width, x, y, z, phase);
        this.action = action;
    }

    public CollisionActionSection(WitherStormEntity entity, float height, float width, double x, double y, double z, Predicate<WitherStormEntity> isActive, Consumer<Entity> action) {
        super(entity, height, width, x, y, z, isActive);
        this.action = action;
    }

    @Override
    protected void collideWithNearbyEntities() {
        super.collideWithNearbyEntities();
        List list = this.owner.m_9236_().m_45976_(Entity.class, this.boundingBox);
        for (Entity entity : list) {
            this.action.accept(entity);
        }
    }
}

