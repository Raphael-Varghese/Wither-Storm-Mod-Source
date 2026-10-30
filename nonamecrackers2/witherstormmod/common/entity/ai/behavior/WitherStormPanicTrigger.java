/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.Brain
 *  net.minecraft.world.entity.ai.behavior.Behavior
 *  net.minecraft.world.entity.ai.memory.MemoryModuleType
 *  net.minecraft.world.entity.schedule.Activity
 */
package nonamecrackers2.witherstormmod.common.entity.ai.behavior;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.schedule.Activity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModActivities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMemoryTypes;

public class WitherStormPanicTrigger
extends Behavior<LivingEntity> {
    public WitherStormPanicTrigger() {
        super((Map)ImmutableMap.of());
    }

    protected boolean m_6737_(ServerLevel level, LivingEntity entity, long seed) {
        return entity.m_6274_().m_21874_((MemoryModuleType)WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get());
    }

    protected void m_6735_(ServerLevel level, LivingEntity entity, long seed) {
        if (WitherStormPanicTrigger.isNearWitherStorm(entity)) {
            Brain brain = entity.m_6274_();
            if (!brain.m_21954_(Activity.f_37984_)) {
                brain.m_21936_(MemoryModuleType.f_26377_);
                brain.m_21936_(MemoryModuleType.f_26370_);
                brain.m_21936_(MemoryModuleType.f_26371_);
                brain.m_21936_(MemoryModuleType.f_26375_);
                brain.m_21936_(MemoryModuleType.f_26374_);
                brain.m_21936_(MemoryModuleType.f_26379_);
                brain.m_21936_(MemoryModuleType.f_26378_);
                brain.m_21936_(MemoryModuleType.f_26362_);
                brain.m_21936_(MemoryModuleType.f_26324_);
                brain.m_21936_(MemoryModuleType.f_26374_);
                brain.m_21936_(MemoryModuleType.f_26359_);
            }
            brain.m_21889_((Activity)WitherStormModActivities.WITHER_STORM_PANIC.get());
        }
    }

    public static boolean isNearWitherStorm(LivingEntity entity) {
        WitherStormEntity storm = entity.m_6274_().m_21952_((MemoryModuleType)WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get()).orElse(null);
        if (storm != null && !storm.isDeadOrPlayingDead()) {
            if (storm.getPhase() < 4) {
                return entity.m_20191_().m_82400_(100.0).m_82390_(storm.m_20182_());
            }
            return true;
        }
        return false;
    }
}

