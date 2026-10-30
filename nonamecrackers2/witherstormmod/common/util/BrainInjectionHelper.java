/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.util.Pair
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.Brain
 *  net.minecraft.world.entity.ai.behavior.Behavior
 *  net.minecraft.world.entity.ai.behavior.DoNothing
 *  net.minecraft.world.entity.ai.behavior.RandomStroll
 *  net.minecraft.world.entity.ai.behavior.RunOne
 *  net.minecraft.world.entity.ai.behavior.SetEntityLookTarget
 *  net.minecraft.world.entity.ai.behavior.SetWalkTargetAwayFrom
 *  net.minecraft.world.entity.ai.memory.MemoryModuleType
 *  net.minecraft.world.entity.ai.memory.MemoryStatus
 *  net.minecraft.world.entity.ai.sensing.Sensor
 *  net.minecraft.world.entity.ai.sensing.SensorType
 *  net.minecraft.world.entity.npc.Villager
 *  net.minecraft.world.entity.schedule.Activity
 */
package nonamecrackers2.witherstormmod.common.util;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.DoNothing;
import net.minecraft.world.entity.ai.behavior.RandomStroll;
import net.minecraft.world.entity.ai.behavior.RunOne;
import net.minecraft.world.entity.ai.behavior.SetEntityLookTarget;
import net.minecraft.world.entity.ai.behavior.SetWalkTargetAwayFrom;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.schedule.Activity;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.ai.behavior.VillagerCalmDownFromWitherStorm;
import nonamecrackers2.witherstormmod.common.entity.ai.behavior.WitherStormPanicTrigger;
import nonamecrackers2.witherstormmod.common.init.WitherStormModActivities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMemoryTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSensorTypes;
import nonamecrackers2.witherstormmod.mixin.IMixinBrain;

public class BrainInjectionHelper {
    public static void inject(LivingEntity entity) {
        Villager villager;
        Brain brain;
        if (((Boolean)WitherStormModConfig.COMMON.injectCustomAiBehavior.get()).booleanValue() && entity.m_6095_().equals(EntityType.f_20492_) && (brain = (villager = (Villager)entity).m_6274_()) != null) {
            BrainInjectionHelper.addMemoryType(villager, (MemoryModuleType)WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get());
            BrainInjectionHelper.addSensorType(villager, (SensorType)WitherStormModSensorTypes.WITHER_STORM_SENSOR.get());
            BrainInjectionHelper.addToActivity(villager, Activity.f_37978_, ImmutableList.of((Object)Pair.of((Object)-1, (Object)((Object)new WitherStormPanicTrigger()))));
            brain.m_21895_((Activity)WitherStormModActivities.WITHER_STORM_PANIC.get(), 0, ImmutableList.of((Object)((Object)new VillagerCalmDownFromWitherStorm()), (Object)SetWalkTargetAwayFrom.m_257370_((MemoryModuleType)((MemoryModuleType)WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get()), (float)0.75f, (int)300, (boolean)true), (Object)RandomStroll.m_257965_((float)1.0f), (Object)new RunOne((List)ImmutableList.of((Object)Pair.of((Object)SetEntityLookTarget.m_258096_((EntityType)EntityType.f_20492_, (float)8.0f), (Object)2), (Object)Pair.of((Object)SetEntityLookTarget.m_258096_((EntityType)EntityType.f_20532_, (float)8.0f), (Object)2), (Object)Pair.of((Object)new DoNothing(30, 60), (Object)8)))), (MemoryModuleType)WitherStormModMemoryTypes.NEAREST_WITHER_STORM.get());
        }
    }

    private static <E extends LivingEntity> void addToActivity(E entity, Activity activity, ImmutableList<? extends Pair<Integer, ? extends Behavior<? super E>>> behaviors) {
        Brain brain = entity.m_6274_();
        IMixinBrain mixinBrain = (IMixinBrain)brain;
        Set<Pair<MemoryModuleType<?>, MemoryStatus>> previousRequirements = mixinBrain.getActivityRequirements().get(activity);
        Set previousMemoriesToErase = mixinBrain.getActivityMemoriesToEraseWhenStopped().getOrDefault(activity, Sets.newHashSet());
        brain.m_21907_(activity, behaviors, previousRequirements, previousMemoriesToErase);
    }

    private static <E extends LivingEntity> IMixinBrain<E> toMixinBrain(E entity) {
        return (IMixinBrain)entity.m_6274_();
    }

    private static <E extends LivingEntity> void addMemoryType(E entity, MemoryModuleType<?> type) {
        IMixinBrain<E> brain = BrainInjectionHelper.toMixinBrain(entity);
        brain.getMemories().put(type, Optional.empty());
    }

    private static <E extends LivingEntity> void addSensorType(E entity, SensorType<? extends Sensor<? super E>> type) {
        IMixinBrain<E> brain = BrainInjectionHelper.toMixinBrain(entity);
        Sensor sensor = type.m_26827_();
        brain.getSensors().put(type, sensor);
        for (MemoryModuleType memory : sensor.m_7163_()) {
            brain.getMemories().put(memory, Optional.empty());
        }
    }
}

