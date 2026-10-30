/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.projectile.FireworkRocketEntity
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity.goal;

import java.util.function.Predicate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.goal.NearestDistractionGoal;

public class WitherStormNearestDistractionGoal
extends NearestDistractionGoal<WitherStormEntity> {
    public WitherStormNearestDistractionGoal(WitherStormEntity entity, int head, Predicate<Entity> condition, int interval) {
        super(entity, head, condition, interval);
    }

    @Override
    protected AABB getSearchArea() {
        return ((WitherStormEntity)this.entity).getSearchBox();
    }

    @Override
    protected Vec3 getTargetPos() {
        if (this.target instanceof FireworkRocketEntity) {
            return super.getTargetPos().m_82520_(0.0, 10.0, 0.0);
        }
        return super.getTargetPos();
    }
}

