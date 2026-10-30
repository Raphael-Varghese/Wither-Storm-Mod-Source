/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.ForgeEventFactory
 */
package nonamecrackers2.witherstormmod.common.util;

import java.util.List;
import java.util.Optional;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.util.ClusterBuilderHelper;
import nonamecrackers2.witherstormmod.common.util.ItemPreservationCondition;
import nonamecrackers2.witherstormmod.mixin.MixinLivingEntityAccessor;

public class PhlegmGravestoneHelper {
    public static Optional<Vec3> findPotentialPhlegmClusterPos(LivingEntity entity, DamageSource damageSource) {
        if (((Boolean)WitherStormModConfig.SERVER.preserveDropsForAllMobs.get()).booleanValue() || entity instanceof Player) {
            WitherStormEntity storm;
            Entity source;
            ItemPreservationCondition method = (ItemPreservationCondition)((Object)WitherStormModConfig.SERVER.itemPreservation.get());
            Entity entity2 = source = method.useDirectEntity() ? damageSource.m_7640_() : damageSource.m_7639_();
            if (source instanceof WitherStormEntity && (storm = (WitherStormEntity)source).m_6084_()) {
                return Optional.ofNullable(method.getPos(storm, entity));
            }
        }
        return Optional.empty();
    }

    public static void spawnForEntity(LivingEntity entity, Vec3 pos, List<ItemStack> items) {
        int reward = ForgeEventFactory.getExperienceDrop((LivingEntity)entity, (Player)((MixinLivingEntityAccessor)entity).witherstormmod$getLastHurtByPlayer(), (int)entity.m_213860_());
        entity.m_217045_();
        BlockClusterEntity cluster = ClusterBuilderHelper.buildPhlegmClusterWithItems(entity.m_9236_(), entity.m_217043_(), items, entity.m_5446_(), reward);
        if (cluster.getSize() > 0) {
            cluster.m_146884_(pos);
            cluster.m_20334_(entity.m_217043_().m_188583_() * 0.3, 0.0, entity.m_217043_().m_188583_() * 0.3);
            cluster.setRotationDelta(new Vec2((float)entity.m_217043_().m_188503_(20) * 0.3f / 2.0f, (float)entity.m_217043_().m_188503_(20) * 0.3f / 2.0f));
            entity.m_9236_().m_7967_((Entity)cluster);
            cluster.setSink(1);
            cluster.setAntiStacking(true);
        }
    }
}

