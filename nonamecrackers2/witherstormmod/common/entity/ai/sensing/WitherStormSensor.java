/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.memory.MemoryModuleType
 *  net.minecraft.world.entity.ai.sensing.Sensor
 *  net.minecraft.world.phys.AABB
 */
package nonamecrackers2.witherstormmod.common.entity.ai.sensing;

import com.google.common.collect.ImmutableSet;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.phys.AABB;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMemoryTypes;

public class WitherStormSensor
extends Sensor<LivingEntity> {
    protected void m_5578_(ServerLevel level, LivingEntity entity) {
        AABB box = entity.m_20191_().m_82400_(300.0);
        List storms = level.m_6443_(WitherStormEntity.class, box, storm -> storm != entity && storm.m_6084_());
        storms.sort(Comparator.comparingDouble(arg_0 -> ((LivingEntity)entity).m_20280_(arg_0)));
        Optional storm2 = storms.stream().findFirst();
        entity.m_6274_().m_21886_((MemoryModuleType)WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get(), storm2);
    }

    public Set<MemoryModuleType<?>> m_7163_() {
        return ImmutableSet.of((Object)((MemoryModuleType)WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get()));
    }
}

