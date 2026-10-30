/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.util;

import java.util.function.BiFunction;
import javax.annotation.Nullable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public enum ItemPreservationCondition {
    ANY_WITHER_STORM_DEATH((w, e) -> ItemPreservationCondition.pos(w, e, true), false),
    CHOMPED_OR_KILLED_NEAR_HEAD((w, e) -> ItemPreservationCondition.pos(w, e, false), false),
    CHOMPED((w, e) -> ItemPreservationCondition.pos(w, e, false), true),
    DISABLED((w, e) -> null, true);

    private final BiFunction<WitherStormEntity, LivingEntity, Vec3> posGetter;
    private boolean useDirectEntity;

    private ItemPreservationCondition(BiFunction<WitherStormEntity, LivingEntity, Vec3> posGetter, boolean useDirectEntity) {
        this.posGetter = posGetter;
        this.useDirectEntity = useDirectEntity;
    }

    @Nullable
    public Vec3 getPos(WitherStormEntity storm, LivingEntity entity) {
        return this.posGetter.apply(storm, entity);
    }

    public boolean useDirectEntity() {
        return this.useDirectEntity;
    }

    @Nullable
    private static Vec3 pos(WitherStormEntity storm, LivingEntity entity, boolean entityPosIfTooFarAway) {
        Vec3 closest = null;
        double distance = -1.0;
        Vec3 eyePos = entity.m_146892_();
        for (int i = 0; i < storm.getTotalHeads(); ++i) {
            Vec3 pos = storm.getHeadPos(i);
            double d = pos.m_82554_(eyePos);
            if (distance != -1.0 && !(distance > d) || !(d < 30.0)) continue;
            distance = d;
            closest = pos;
        }
        if (entityPosIfTooFarAway && closest == null) {
            return eyePos;
        }
        return closest;
    }
}

