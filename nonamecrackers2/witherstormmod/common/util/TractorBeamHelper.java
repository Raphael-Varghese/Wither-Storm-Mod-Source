/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;

public class TractorBeamHelper {
    public static <T extends LivingEntity> Pair<Boolean, Integer> isInsideTractorBeam(Entity target, T entity, double radius) {
        return TractorBeamHelper.isInsideTractorBeam(target.m_20182_(), entity, radius);
    }

    public static <T extends LivingEntity> Pair<Boolean, Integer> isInsideTractorBeam(Vec3 target, T entity, double radius) {
        for (int i = 0; i < ((WitherStormBase)entity).getTotalHeads(); ++i) {
            if (!TractorBeamHelper.isInsideTractorBeam(target, entity, radius, i)) continue;
            return Pair.of((Object)true, (Object)i);
        }
        return Pair.of((Object)false, (Object)-1);
    }

    public static <T extends LivingEntity> boolean isInsideTractorBeam(Entity target, T entity, double radius, int head) {
        return TractorBeamHelper.isInsideTractorBeam(target.m_20182_(), entity, radius, head);
    }

    public static <T extends LivingEntity> boolean isInsideTractorBeam(Vec3 target, T entity, double radius, int head) {
        double distanceFromHead;
        Vec3 pos;
        double distance;
        return ((WitherStormBase)entity).tractorBeamActive(head) && (distance = Math.sqrt(target.m_82557_(pos = TractorBeamHelper.calculateClosestPoint(target, entity, head)))) <= radius * (((distanceFromHead = Math.sqrt(target.m_82557_(((WitherStormBase)entity).getHeadPos(head)))) + 30.0) * 0.014);
    }

    public static <T extends LivingEntity> Vec3 calculateClosestPoint(Vec3 target, T entity, int head) {
        return TractorBeamHelper.calculateClosestPoint(target, entity, head, 0.0);
    }

    public static <T extends LivingEntity> Vec3 calculateClosestPoint(Vec3 target, T entity, int head, double distanceOffset) {
        float x = ((WitherStormBase)entity).getHeadXRot(head);
        float y = ((WitherStormBase)entity).getHeadYRot(head);
        Vec3 headPos = ((WitherStormBase)entity).getHeadPos(head);
        double cutoff = ((WitherStormBase)entity).getTractorBeamCutoffDistance(head);
        float distanceToHead = (float)(headPos.m_82554_(target) + distanceOffset);
        if (cutoff != -1.0) {
            distanceToHead = Mth.m_14036_((float)distanceToHead, (float)0.0f, (float)((float)cutoff));
        }
        return headPos.m_82549_(((WitherStormBase)entity).getViewVector(x, y, distanceToHead));
    }

    public static Vec3 calculateClosestPoint(Entity target, LivingEntity entity) {
        Vec3 headPos = entity.m_146892_();
        float distanceToHead = (float)headPos.m_82554_(target.m_20182_());
        Vec3 closest = headPos.m_82549_(TractorBeamHelper.getViewVector(entity).m_82490_((double)distanceToHead));
        BlockHitResult ray = target.m_9236_().m_45547_(new ClipContext(headPos, closest, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null));
        return ray.m_82450_();
    }

    private static Vec3 getViewVector(LivingEntity entity) {
        float xRot = entity.m_146909_() * ((float)Math.PI / 180);
        float yRot = -entity.f_20885_ * ((float)Math.PI / 180);
        float cosY = Mth.m_14089_((float)yRot);
        float sinY = Mth.m_14031_((float)yRot);
        float cosX = Mth.m_14089_((float)xRot);
        float sinX = Mth.m_14031_((float)xRot);
        return new Vec3((double)(sinY * cosX), (double)(-sinX), (double)(cosY * cosX));
    }
}

