/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.behavior.Behavior
 *  net.minecraft.world.entity.ai.memory.MemoryModuleType
 *  net.minecraft.world.entity.npc.Villager
 */
package nonamecrackers2.witherstormmod.common.entity.ai.behavior;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;
import nonamecrackers2.witherstormmod.common.entity.ai.behavior.WitherStormPanicTrigger;

public class VillagerCalmDownFromWitherStorm
extends Behavior<Villager> {
    public VillagerCalmDownFromWitherStorm() {
        super((Map)ImmutableMap.of());
    }

    protected void start(ServerLevel level, Villager villager, long seed) {
        if (!WitherStormPanicTrigger.isNearWitherStorm((LivingEntity)villager)) {
            villager.m_6274_().m_21936_(MemoryModuleType.f_26381_);
            villager.m_6274_().m_21936_(MemoryModuleType.f_26382_);
            villager.m_6274_().m_21862_(level.m_46468_(), level.m_46467_());
        }
    }
}

