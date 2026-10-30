/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.Bee
 *  net.minecraft.world.entity.animal.Wolf
 *  net.minecraft.world.entity.animal.horse.Llama
 *  net.minecraft.world.entity.monster.AbstractSkeleton
 *  net.minecraft.world.entity.monster.Blaze
 *  net.minecraft.world.entity.monster.Creeper
 *  net.minecraft.world.entity.monster.Evoker
 *  net.minecraft.world.entity.monster.Phantom
 *  net.minecraft.world.entity.monster.Pillager
 *  net.minecraft.world.entity.monster.Ravager
 *  net.minecraft.world.entity.monster.Shulker
 *  net.minecraft.world.entity.monster.Silverfish
 *  net.minecraft.world.entity.monster.Spider
 *  net.minecraft.world.entity.monster.Vex
 *  net.minecraft.world.entity.monster.Vindicator
 *  net.minecraft.world.entity.monster.Witch
 *  net.minecraft.world.entity.monster.WitherSkeleton
 *  net.minecraft.world.entity.monster.Zombie
 *  net.minecraft.world.entity.monster.ZombifiedPiglin
 *  net.minecraft.world.entity.monster.hoglin.Hoglin
 *  net.minecraft.world.entity.monster.piglin.AbstractPiglin
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.event.entity.EntityJoinLevelEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.common.event;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
import nonamecrackers2.witherstormmod.common.entity.goal.AvoidWitherStormGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.FightSickenedMobsGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.NearestAttackingWitherStormGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.PhantomOrbitWitherStormGoal;
import nonamecrackers2.witherstormmod.common.predicate.EntityPredicateBuilder;
import nonamecrackers2.witherstormmod.mixin.IMixinBrain;

public class InjectCustomGoalsEvents {
    public static final Predicate<LivingEntity> CAN_RUN_AWAY_FROM_WITHER_STORM = ((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.and().isNotInstanceOf(WitherSickened.class)).isNotInstanceOf(WitherSkeleton.class)).addTest(((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.or().isInstanceOf(Zombie.class)).isInstanceOf(Spider.class)).isInstanceOf(AbstractSkeleton.class)).isInstanceOf(Creeper.class)).isInstanceOf(AbstractPiglin.class)).isInstanceOf(Pillager.class)).isInstanceOf(Animal.class)).build())).addTest(e -> ((IMixinBrain)e.m_6274_()).getCoreActivities().isEmpty())).build();
    public static final Predicate<LivingEntity> CAN_ATTACK_WITHER_STORM_BACK = ((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.and().isNotInstanceOf(WitherSickened.class)).isNotInstanceOf(WitherSkeleton.class)).addTest(((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.or().isInstanceOf(Creeper.class)).isInstanceOf(AbstractSkeleton.class)).build())).build();
    public static final Predicate<LivingEntity> CAN_ATTACK_SICKENED_MOBS = ((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.and().isNotInstanceOf(WitherSickened.class)).isNotInstanceOf(WitherSkeleton.class)).addTest(((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.or().isInstanceOf(Shulker.class)).isInstanceOf(Evoker.class)).isInstanceOf(Vex.class)).isInstanceOf(Witch.class)).isInstanceOf(Ravager.class)).isInstanceOf(Llama.class)).isInstanceOf(Vindicator.class)).isInstanceOf(ZombifiedPiglin.class)).isInstanceOf(AbstractPiglin.class)).isInstanceOf(Hoglin.class)).isInstanceOf(Silverfish.class)).isInstanceOf(Blaze.class)).isInstanceOf(Wolf.class)).isInstanceOf(Bee.class)).build())).build();

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        Level world = event.getLevel();
        if (world.f_46443_ || !((Boolean)WitherStormModConfig.COMMON.injectCustomAiBehavior.get()).booleanValue()) {
            return;
        }
        Entity entity = event.getEntity();
        if (((List)WitherStormModConfig.COMMON.injectAiMobBlacklist.get()).contains(entity.m_20078_())) {
            return;
        }
        if (entity.m_6095_().equals(EntityType.f_20509_) && entity instanceof Phantom) {
            Phantom phantom = (Phantom)entity;
            if (((Boolean)WitherStormModConfig.COMMON.phantomsOrbitWitherStorm.get()).booleanValue()) {
                phantom.f_21345_.m_262460_(g -> g instanceof PhantomOrbitWitherStormGoal);
                phantom.f_21345_.m_25352_(1, (Goal)new PhantomOrbitWitherStormGoal(phantom));
            }
        }
        if (entity instanceof PathfinderMob) {
            PathfinderMob mob = (PathfinderMob)entity;
            if (CAN_RUN_AWAY_FROM_WITHER_STORM.test((LivingEntity)mob)) {
                mob.f_21345_.m_262460_(g -> g instanceof AvoidWitherStormGoal);
                mob.f_21345_.m_25352_(0, (Goal)new AvoidWitherStormGoal(mob, 300.0f, 1.55, 1.55));
            }
            if (CAN_ATTACK_WITHER_STORM_BACK.test((LivingEntity)mob)) {
                mob.f_21346_.m_262460_(g -> g instanceof NearestAttackingWitherStormGoal);
                mob.f_21346_.m_25352_(0, (Goal)new NearestAttackingWitherStormGoal((Mob)mob, 10));
            }
            if (CAN_ATTACK_SICKENED_MOBS.test((LivingEntity)mob)) {
                mob.f_21346_.m_262460_(g -> g instanceof FightSickenedMobsGoal);
                mob.f_21346_.m_25352_(-1, (Goal)new FightSickenedMobsGoal((Mob)mob));
            }
        }
    }
}

