/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.BlockParticleOption
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.NbtUtils
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerBossEvent
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.tags.StructureTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.BossEvent$BossBarColor
 *  net.minecraft.world.BossEvent$BossBarOverlay
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageTypes
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$MovementEmission
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.FlyingMob
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.PowerableMob
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeInstance
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.BodyRotationControl
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.GoalSelector
 *  net.minecraft.world.entity.ai.goal.WrappedGoal
 *  net.minecraft.world.entity.ai.targeting.TargetingConditions
 *  net.minecraft.world.entity.ambient.AmbientCreature
 *  net.minecraft.world.entity.animal.AbstractSchoolingFish
 *  net.minecraft.world.entity.animal.Squid
 *  net.minecraft.world.entity.boss.enderdragon.EnderDragon
 *  net.minecraft.world.entity.boss.wither.WitherBoss
 *  net.minecraft.world.entity.decoration.ArmorStand
 *  net.minecraft.world.entity.item.FallingBlockEntity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.item.PrimedTnt
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.Phantom
 *  net.minecraft.world.entity.monster.Slime
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.projectile.FireworkRocketEntity
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.entity.projectile.ThrownEnderpearl
 *  net.minecraft.world.entity.projectile.ThrownTrident
 *  net.minecraft.world.entity.projectile.WitherSkull
 *  net.minecraft.world.entity.vehicle.AbstractMinecart
 *  net.minecraft.world.entity.vehicle.Boat
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.ShieldItem
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.JukeboxBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.level.material.Fluids
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.ForgeConfigSpec$ConfigValue
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.Tags$Blocks
 *  net.minecraftforge.common.util.LazyOptional
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.network.PacketDistributor
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.StructureTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.WitherStormWorldInteractions;
import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.clustersource.BlockClusterSource;
import nonamecrackers2.witherstormmod.api.common.ai.witherstorm.pullbehavior.WitherStormPullBehavior;
import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
import nonamecrackers2.witherstormmod.api.common.event.CanWitherStormTargetMobEvent;
import nonamecrackers2.witherstormmod.api.common.event.WitherStormChangePhaseEvent;
import nonamecrackers2.witherstormmod.api.common.event.WitherStormConsumeEvent;
import nonamecrackers2.witherstormmod.api.common.event.WitherStormEvolveEvent;
import nonamecrackers2.witherstormmod.api.common.event.WitherStormModifyEvolutionSpeedEvent;
import nonamecrackers2.witherstormmod.api.common.event.WitherStormModifyFlyingSpeedEvent;
import nonamecrackers2.witherstormmod.client.capability.WitherStormLoopingSoundManager;
import nonamecrackers2.witherstormmod.common.capability.PlayerWitherStormData;
import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
import nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
import nonamecrackers2.witherstormmod.common.entity.BlueFlamingWitherSkullEntity;
import nonamecrackers2.witherstormmod.common.entity.BossThemeEntity;
import nonamecrackers2.witherstormmod.common.entity.ChunkLoader;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.entity.FlamingWitherSkullEntity;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
import nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.BowelsInstanceManager;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.IgnoredTargetsManager;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PersistentTrackedEntities;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.SegmentsManager;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.SymbiontSummoningManager;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.WitherStormSyncHelper;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller.WitherStormBodyController;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller.WitherStormLookController;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.AdditionalHead;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.HeadManager;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.MainHead;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager;
import nonamecrackers2.witherstormmod.common.entity.goal.DoNothingGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.FindNearestFormidibombGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.LookAtDistractionGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.LookAtFormidibombGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.LookAtTargetGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.NearestBlockDistractionGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.RemovableGoals;
import nonamecrackers2.witherstormmod.common.entity.goal.RemovableGoalsManager;
import nonamecrackers2.witherstormmod.common.entity.goal.WitherStormHurtByTargetGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.WitherStormLookRandomlyGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.WitherStormNearestDistractionGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.WitherStormPriorityTargetingGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.WitherStormTargetingGoal;
import nonamecrackers2.witherstormmod.common.entity.section.CollisionActionSection;
import nonamecrackers2.witherstormmod.common.entity.section.FallingSection;
import nonamecrackers2.witherstormmod.common.entity.section.Section;
import nonamecrackers2.witherstormmod.common.init.WitherStormModAttributes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.packet.CreateLoopingSoundMessage;
import nonamecrackers2.witherstormmod.common.packet.GlobalSoundMessage;
import nonamecrackers2.witherstormmod.common.packet.PlayerMotionMessage;
import nonamecrackers2.witherstormmod.common.packet.RemoveSoundLoopMessage;
import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
import nonamecrackers2.witherstormmod.common.packet.StormSoundPositionMessage;
import nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions;
import nonamecrackers2.witherstormmod.common.predicate.EntityPredicateBuilder;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModEntityTags;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModItemTags;
import nonamecrackers2.witherstormmod.common.util.ClusterBuilderHelper;
import nonamecrackers2.witherstormmod.common.util.DebrisCluster;
import nonamecrackers2.witherstormmod.common.util.DebrisRingSettings;
import nonamecrackers2.witherstormmod.common.util.EntitySyncableData;
import nonamecrackers2.witherstormmod.common.util.EvolutionProfiler;
import nonamecrackers2.witherstormmod.common.util.StormHeadOffsets;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;
import org.jetbrains.annotations.NotNull;

public class WitherStormEntity
extends Monster
implements PowerableMob,
EntitySyncableData,
BossThemeEntity,
WitherStormBase,
ChunkLoader {
    private static final List<DataAccessorHolder<?>> DATA_ACCESSORS = Lists.newArrayList();
    protected static final EntityDataAccessor<Integer> INVULNERABLE = SynchedEntityData.m_135353_(WitherStormEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    protected static final EntityDataAccessor<Integer> STARTING_INVULNERABLE = SynchedEntityData.m_135353_(WitherStormEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    protected static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.m_135353_(WitherStormEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Integer> CONSUMED_ENTITIES = SynchedEntityData.m_135353_(WitherStormEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final EntityDataAccessor<Boolean> MIRRORED = SynchedEntityData.m_135353_(WitherStormEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> SHOULD_SHOW_HOLE = SynchedEntityData.m_135353_(WitherStormEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final UUID HEALTH_MODIFIER_UUID = UUID.fromString("9B8DA22B-138B-4B68-879D-3FD329FAF903");
    private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("C806DBFA-2B10-4BEA-B16C-C3233707399C");
    public static final Predicate<LivingEntity> DESTROYER_LIVING_ENTITY_SELECTOR = ((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.and().addTest(Entity::m_6097_)).isNotTag(WitherStormModEntityTags.WITHER_STORM_TARGETING_BLACKLIST).isNotInstanceOf(WitherBoss.class)).isNotInstanceOf(FlyingMob.class)).isNotInstanceOf(EnderDragon.class)).isNotInstanceOf(WitherStormEntity.class)).isNotInstanceOf(WitherSickened.class)).isNotInstanceOf(CommandBlockEntity.class)).isNotInstanceOf(WitheredSymbiontEntity.class)).isNotInstanceOf(WitherStormHeadEntity.class)).isNotInstanceOf(TentacleEntity.class)).isNotInstanceOf(AmbientCreature.class)).isNotInstanceOf(ArmorStand.class)).build();
    public static final Predicate<LivingEntity> HUNCHBACK_LIVING_ENTITY_SELECTOR = ((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.and().addTest(DESTROYER_LIVING_ENTITY_SELECTOR)).isNotInstanceOf(AbstractSchoolingFish.class)).isNotInstanceOf(Squid.class)).build();
    public static final Predicate<Entity> DISTRACTION_SELECTOR = ((EntityPredicateBuilder)EntityPredicateBuilder.or().isInstanceOf(FireworkRocketEntity.class)).build();
    public static final Predicate<Entity> TRACTOR_BEAM_PULLABLE = ((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.or().isExactly(EntityType.f_20515_).isInstanceOf(Boat.class)).isInstanceOf(AbstractMinecart.class)).build();
    public static final Predicate<Entity> ABSORBABLE = ((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.and().isNotInstanceOf(Player.class)).isNotInstanceOf(PrimedTnt.class)).build();
    public static final Predicate<Entity> CAN_TRAVEL_TO_BOWELS = ((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.and().isNotInstanceOf(BlockClusterEntity.class)).isNotInstanceOf(Projectile.class)).isNotInstanceOf(ItemEntity.class)).isNotInstanceOf(Phantom.class)).build();
    public static final Predicate<Entity> PICKABLE = ((EntityPredicateBuilder)((EntityPredicateBuilder)EntityPredicateBuilder.or().isInstanceOf(ItemEntity.class)).addTest(e -> {
        Slime slime;
        return e instanceof Slime && (slime = (Slime)e).m_33633_();
    })).build();
    private static final EntityDimensions STARTING_SIZE = EntityDimensions.m_20395_((float)0.9f, (float)3.5f);
    private static final EntityDimensions DESTROYER_SIZE = EntityDimensions.m_20395_((float)10.0f, (float)30.0f);
    private static final EntityDimensions EVOLVED_DESTROYER_SIZE = EntityDimensions.m_20395_((float)10.0f, (float)60.0f);
    private static final EntityDimensions DEVOURER_SIZE = EntityDimensions.m_20395_((float)15.0f, (float)90.0f);
    private static final EntityDimensions EVOLVED_DEVOURER_SIZE = EntityDimensions.m_20395_((float)15.0f, (float)120.0f);
    private static final double[] SEGMENT_DESIRED_X = new double[]{75.0, 75.0};
    private static final double[] SEGMENT_DESIRED_Z = new double[]{50.0, -50.0};
    public static final String[] REMOVABLE_LOOK_GOALS = new String[]{"lookGoals0", "lookGoals1", "lookGoals2"};
    public static final String[] REMOVABLE_TARGET_GOALS = new String[]{"targetGoals0", "targetGoals1", "targetGoals2"};
    public static final int MAX_PHASE = 7;
    protected final HeadManager headManager;
    protected final PlayDeadManager playDeadManager;
    protected final Optional<UltimateTargetManager> targetManager;
    protected final Optional<SegmentsManager> segments;
    protected final Optional<SymbiontSummoningManager> summoningManager;
    protected final Optional<BowelsInstanceManager> bowelsInstance;
    protected RemovableGoalsManager removableGoals;
    protected final Optional<ServerBossEvent> bossEvent;
    private int lastConsumedEntities;
    private int tentacleTickCount;
    private int tentacleTickCountO;
    private int destroyBlocksTick;
    protected int entityConsumptionRadius = 16;
    protected float clusterRadius = 1.0f;
    public int idleTargetTicks;
    public boolean chunkloads = true;
    protected Section[] sections = new Section[3];
    private FallingSection fallingSection;
    public boolean partsEnabled = true;
    public boolean shouldFollowUltimateTarget = true;
    public boolean shouldPlaySoundLoop = true;
    public boolean shouldPlayGlobalSounds = true;
    public int witherStormDeathTime;
    public boolean shouldDoCustomMovement = true;
    protected List<DebrisCluster> debrisClusters = ImmutableList.of();
    protected List<DebrisCluster> hunchbackDebrisClusters = ImmutableList.of();
    protected List<DebrisRingSettings> debrisRings = ImmutableList.of();
    private boolean isOnDistantRenderer;
    protected float onGroundAnimation;
    protected float onGroundAnimationO;
    public float xBodyRot;
    public float xBodyRotO;
    public boolean shouldIgnoreFormidibomb;
    private List<GoalSelector> headGoalSelectors;
    private List<GoalSelector> headTargetSelectors;
    private int flickerTime;
    private int nextFlicker = 40;
    private boolean shouldFlicker;
    private final List<ServerPlayer> playersTracking = Lists.newArrayList();
    private float shineAlpha;
    private float shineAlphaO;
    private boolean resummoned;
    private final EvolutionProfiler evolutionProfiler = new EvolutionProfiler();
    private final Map<UUID, CompoundTag> consumedPets = Maps.newHashMap();
    private final PersistentTrackedEntities trackedEntities = new PersistentTrackedEntities();
    private final IgnoredTargetsManager ignoredTargets;
    private final List<BlockPos> playingJukeboxes = Lists.newArrayList();
    private int nextUndergroundRumble = 1200 + this.f_19796_.m_188503_(1200);
    private float shineScale;
    private float phaseProgress;
    private boolean isLocked;
    private int lastFlyingSwitchTime;
    private double currentFlyingHeight = 10.0;
    private float lerpBodyXRot;
    private float lerpBodyYRot;
    private int bodyLerpSteps;
    private Predicate<LivingEntity> entitySelector;

    public WitherStormEntity(EntityType<? extends WitherStormEntity> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.m_21153_(this.m_21233_());
        this.m_21573_().m_7008_(true);
        this.f_21364_ = 10000;
        this.f_19811_ = true;
        this.f_21365_ = new WitherStormLookController(this);
        this.buildSections();
        this.segments = this.makeSegmentsManager();
        this.bowelsInstance = this.makeBowelsInstanceManager();
        this.headManager = this.makeHeadManager();
        this.targetManager = this.makeUltimateTargetManager();
        this.summoningManager = this.makeSummoningManager();
        this.playDeadManager = new PlayDeadManager(this);
        this.bossEvent = this.makeBossEvent();
        this.ignoredTargets = new IgnoredTargetsManager(this);
    }

    protected HeadManager makeHeadManager() {
        return new HeadManager(this, StormHeadOffsets.MAIN);
    }

    protected Optional<SegmentsManager> makeSegmentsManager() {
        return Optional.of(new SegmentsManager(this));
    }

    protected Optional<SymbiontSummoningManager> makeSummoningManager() {
        return Optional.of(new SymbiontSummoningManager(this));
    }

    protected Optional<BowelsInstanceManager> makeBowelsInstanceManager() {
        return Optional.of(new BowelsInstanceManager(this));
    }

    protected Optional<UltimateTargetManager> makeUltimateTargetManager() {
        return Optional.of(new UltimateTargetManager(this));
    }

    protected Optional<ServerBossEvent> makeBossEvent() {
        return Optional.of((ServerBossEvent)new ServerBossEvent(this.m_5446_(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS).m_7003_(true));
    }

    protected void buildSections() {
        this.sections[0] = new Section(this, 30.0f, 15.0f, 13.0, 28.0, 0.0, 4);
        this.sections[1] = new Section(this, 30.0f, 15.0f, -13.0, 28.0, 0.0, 4);
        this.fallingSection = new FallingSection(this, 50.0f, 70.0f, 0.0, 60.0, 30.0, 4);
        this.sections[2] = new CollisionActionSection(this, 16.0f, 16.0f, -3.0, 34.0, -24.0, storm -> storm.isBeingTornApart() && storm.getPhase() >= 7, entity -> {
            if (!this.m_9236_().f_46443_ && (CAN_TRAVEL_TO_BOWELS.test((Entity)entity) || entity instanceof ThrownEnderpearl) && !this.alreadyATarget((Entity)entity, true)) {
                if (entity instanceof ThrownEnderpearl) {
                    ThrownEnderpearl pearl = (ThrownEnderpearl)entity;
                    Entity owner = pearl.m_19749_();
                    if (owner instanceof ServerPlayer) {
                        ServerPlayer player = (ServerPlayer)owner;
                        if (player.f_8906_.f_9742_.m_129536_() && player.m_9236_() == pearl.m_9236_() && !player.m_5803_()) {
                            if (player.m_20159_()) {
                                player.m_8127_();
                            }
                            player.m_6021_(pearl.m_20185_(), pearl.m_20186_(), pearl.m_20189_());
                            player.f_19789_ = 0.0f;
                            player.m_6469_(this.m_269291_().m_268989_(), 5.0f);
                            this.sendToBowels(owner);
                        }
                    } else if (owner != null) {
                        owner.m_6021_(pearl.m_20185_(), pearl.m_20186_(), pearl.m_20189_());
                        owner.f_19789_ = 0.0f;
                        this.sendToBowels(owner);
                    }
                    pearl.m_146870_();
                } else {
                    this.sendToBowels((Entity)entity);
                }
            }
        }).setColor(0.0f, 1.0f, 1.0f);
    }

    @NotNull
    protected BodyRotationControl m_7560_() {
        return new WitherStormBodyController(this);
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(INVULNERABLE, (Object)0);
        this.f_19804_.m_135372_(STARTING_INVULNERABLE, (Object)((Integer)WitherStormModConfig.SERVER.invulnerabilityTime.get() * 20));
        this.f_19804_.m_135372_(PHASE, (Object)0);
        this.f_19804_.m_135372_(CONSUMED_ENTITIES, (Object)0);
        this.f_19804_.m_135372_(MIRRORED, (Object)false);
        this.f_19804_.m_135372_(SHOULD_SHOW_HOLE, (Object)false);
        for (DataAccessorHolder<?> holder : DATA_ACCESSORS) {
            holder.defineTo(this.f_19804_);
        }
    }

    protected void m_8099_() {
        this.entitySelector = entity -> this.getPhase() <= 3 ? HUNCHBACK_LIVING_ENTITY_SELECTOR.test((LivingEntity)entity) : DESTROYER_LIVING_ENTITY_SELECTOR.test((LivingEntity)entity);
        this.headGoalSelectors = ImmutableList.builder().add((Object)new GoalSelector(this.m_9236_().m_46658_())).add((Object)new GoalSelector(this.m_9236_().m_46658_())).build();
        this.headTargetSelectors = ImmutableList.builder().add((Object)new GoalSelector(this.m_9236_().m_46658_())).add((Object)new GoalSelector(this.m_9236_().m_46658_())).build();
        this.removableGoals = new RemovableGoalsManager();
        this.f_21345_.m_25352_(0, (Goal)new DoNothingGoal(this, 0));
        this.removableGoals.put(REMOVABLE_LOOK_GOALS[0], RemovableGoals.Builder.builder().put(1, new LookAtFormidibombGoal(this)).put(2, new LookAtDistractionGoal<WitherStormEntity>(this, 0, () -> 300.0)).put(4, new LookAtTargetGoal<WitherStormEntity>(this, 0, s -> s.getPhase() > 3 ? 50 : 3)).build(this.f_21345_));
        this.f_21345_.m_25352_(7, (Goal)new WitherStormLookRandomlyGoal(this, 0, 120));
        this.removableGoals.put(REMOVABLE_TARGET_GOALS[0], RemovableGoals.Builder.builder().put(0, (Goal)new FindNearestFormidibombGoal(this, 0)).put(1, (Goal)new WitherStormHurtByTargetGoal((PathfinderMob)this, this.entitySelector)).put(2, new WitherStormNearestDistractionGoal(this, 0, DISTRACTION_SELECTOR, 2)).put(3, new WitherStormPriorityTargetingGoal(this, this.entitySelector, 0)).put(4, new NearestBlockDistractionGoal<WitherStormEntity>(this, 0)).put(5, new WitherStormTargetingGoal(this, this.entitySelector, 0)).build(this.f_21346_));
        this.addHeadGoals((selector, head) -> {
            selector.m_25352_(0, (Goal)new DoNothingGoal(this, (int)head));
            this.removableGoals.put(REMOVABLE_LOOK_GOALS[head], RemovableGoals.Builder.builder().put(1, new LookAtDistractionGoal<WitherStormEntity>(this, (int)head, () -> 300.0)).put(2, new LookAtTargetGoal<WitherStormEntity>(this, (int)head, s -> s.getPhase() > 3 ? 50 : 3)).build((GoalSelector)selector));
            selector.m_25352_(3, (Goal)new WitherStormLookRandomlyGoal(this, (int)head, 120));
        });
        this.addHeadTargetGoals((selector, head) -> this.removableGoals.put(REMOVABLE_TARGET_GOALS[head], RemovableGoals.Builder.builder().put(0, new WitherStormNearestDistractionGoal(this, (int)head, DISTRACTION_SELECTOR, 2)).put(1, new WitherStormPriorityTargetingGoal(this, this.entitySelector, (int)head)).put(3, new NearestBlockDistractionGoal<WitherStormEntity>(this, (int)head)).put(4, new WitherStormTargetingGoal(this, this.entitySelector, (int)head)).build((GoalSelector)selector)));
    }

    public Predicate<LivingEntity> getEntitySelector() {
        return this.entitySelector;
    }

    public void m_262441_(Predicate<Goal> predicate) {
        super.m_262441_(predicate);
        this.headGoalSelectors.forEach(sel -> sel.m_262460_(predicate));
    }

    protected void addHeadGoals(BiConsumer<GoalSelector, Integer> goals) {
        for (int i = 0; i < this.headGoalSelectors.size(); ++i) {
            goals.accept(this.headGoalSelectors.get(i), i + 1);
        }
    }

    protected void addHeadTargetGoals(BiConsumer<GoalSelector, Integer> goals) {
        for (int i = 0; i < this.headTargetSelectors.size(); ++i) {
            goals.accept(this.headTargetSelectors.get(i), i + 1);
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.m_33035_().m_22266_((Attribute)WitherStormModAttributes.TARGET_STATIONARY_FLYING_SPEED.get()).m_22266_((Attribute)WitherStormModAttributes.SLOW_FLYING_SPEED.get()).m_22268_((Attribute)WitherStormModAttributes.EVOLUTION_SPEED.get(), 1.0).m_22268_(Attributes.f_22280_, 0.0).m_22268_(Attributes.f_22276_, 400.0).m_22268_(Attributes.f_22279_, 0.6).m_22268_(Attributes.f_22277_, 120.0).m_22268_((Attribute)WitherStormModAttributes.HUNCHBACK_FOLLOW_RANGE.get(), 40.0).m_22268_(Attributes.f_22284_, 8.0).m_22268_(Attributes.f_22281_, 3.5);
    }

    public boolean shouldSpeedUp() {
        UltimateTargetManager manager = this.targetManager.orElse(null);
        if (manager != null) {
            Vec3 pos = manager.getUltimateTargetPos();
            if (this.getPhase() >= 4 && ((Boolean)WitherStormModConfig.SERVER.shouldChaseWhenTargetStopped.get()).booleanValue() && pos != null) {
                return manager.isTargetStationary() && this.m_20182_().m_82554_(pos) > 122.0 && !manager.isDistracted();
            }
            return false;
        }
        return false;
    }

    public double getFlyingSpeed(Vec3 ultimateTargetDist) {
        if (this.getPhase() > 3) {
            double flyingSpeed = this.m_21133_(Attributes.f_22280_);
            flyingSpeed = this.shouldSpeedUp() ? (flyingSpeed += Math.min(this.getDefaultChasingSpeed(), this.m_20182_().m_82554_(Objects.requireNonNull(this.getUltimateTargetPos())) * 0.001)) : (ultimateTargetDist.m_165924_() > 205.0 ? (flyingSpeed += this.getDefaultNormalSpeed() + 0.03) : (flyingSpeed += this.getDefaultNormalSpeed()));
            return flyingSpeed;
        }
        return this.m_21133_((Attribute)WitherStormModAttributes.SLOW_FLYING_SPEED.get());
    }

    protected double getDefaultChasingSpeed() {
        return this.attributeOrConfigValue((Attribute)WitherStormModAttributes.TARGET_STATIONARY_FLYING_SPEED.get(), WitherStormModConfig.SERVER.chasingFlyingSpeed);
    }

    protected double getDefaultNormalSpeed() {
        return this.attributeOrConfigValue((Attribute)WitherStormModAttributes.SLOW_FLYING_SPEED.get(), WitherStormModConfig.SERVER.normalFlyingSpeed);
    }

    public void m_8107_() {
        if (this.bodyLerpSteps > 0) {
            this.xBodyRot += (this.lerpBodyXRot - this.xBodyRot) / (float)this.bodyLerpSteps;
            this.f_20883_ += Mth.m_14177_((float)(this.lerpBodyYRot - this.f_20883_)) / (float)this.bodyLerpSteps;
            --this.bodyLerpSteps;
        }
        Vec3 vector3d = this.m_20184_().m_82542_(1.0, 0.6, 1.0);
        if (!this.m_9236_().f_46443_ && !this.shouldDoNothing()) {
            this.targetManager.ifPresent(UltimateTargetManager::tick);
            this.summoningManager.ifPresent(SymbiontSummoningManager::tick);
            if (!this.isDeadOrPlayingDead()) {
                vector3d = this.doFlying(vector3d);
            }
        }
        if (this.shouldDoCustomMovement()) {
            this.m_20256_(vector3d);
        }
        if (vector3d.m_165925_() > 0.05) {
            this.m_146922_((float)Mth.m_14136_((double)vector3d.f_82481_, (double)vector3d.f_82479_) * 57.295776f - 90.0f);
        }
        super.m_8107_();
        if (!this.isDeadOrPlayingDead() && !this.shouldDoNothing()) {
            this.headManager.aiStep();
        }
        boolean flag = this.m_7090_();
        if (this.getPhase() < 4) {
            for (WitherStormHead head : this.headManager.getHeads()) {
                Vec3 pos = head.getHeadPos();
                double d8 = pos.f_82479_;
                double d10 = pos.f_82480_;
                double d2 = pos.f_82481_;
                if (head instanceof MainHead) {
                    d8 = this.m_20185_();
                    d10 = this.m_20188_();
                    d2 = this.m_20189_();
                }
                this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123762_, d8 + this.f_19796_.m_188583_() * (double)0.3f, d10 + this.f_19796_.m_188583_() * (double)0.3f, d2 + this.f_19796_.m_188583_() * (double)0.3f, 0.0, 0.0, 0.0);
                if (!flag || this.m_9236_().f_46441_.m_188503_(4) != 0) continue;
                this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123811_, d8 + this.f_19796_.m_188583_() * (double)0.3f, d10 + this.f_19796_.m_188583_() * (double)0.3f, d2 + this.f_19796_.m_188583_() * (double)0.3f, (double)0.7f, (double)0.7f, 0.5);
            }
        }
        if (this.getPhase() < 3) {
            for (int i = 0; i < 5; ++i) {
                float angle = (this.f_20883_ + 90.0f) * ((float)Math.PI / 180);
                double x = (double)Mth.m_14089_((float)angle) * 0.3 + this.m_20185_();
                double z = (double)Mth.m_14031_((float)angle) * 0.3 + this.m_20189_();
                double y = this.m_20186_() + 1.4;
                double startX = x + this.f_19796_.m_188583_();
                double startY = y + this.f_19796_.m_188583_();
                double startZ = z + this.f_19796_.m_188583_();
                Vec3 delta = new Vec3(x, y, z).m_82492_(startX, startY, startZ).m_82541_().m_82490_(0.1);
                this.m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), startX, startY, startZ, delta.f_82479_, delta.f_82480_, delta.f_82481_);
            }
        }
        if (this.getInvulnerableTicks() > 0 && this.getPhase() < 4) {
            for (int i1 = 0; i1 < 3; ++i1) {
                this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123811_, this.m_20185_() + this.f_19796_.m_188583_(), this.m_20186_() + (double)(this.f_19796_.m_188501_() * 3.3f), this.m_20189_() + this.f_19796_.m_188583_(), (double)0.7f, (double)0.7f, (double)0.9f);
            }
        }
        if (!this.m_21224_() && this.m_6084_()) {
            this.getPlayDeadManager().tick();
        }
    }

    protected Vec3 doFlying(Vec3 vector3d) {
        double ascendSpeed = 0.02;
        if (this.getPhase() > 3) {
            if (!((Boolean)WitherStormModConfig.SERVER.dynamicFlyingHeight.get()).booleanValue()) {
                this.currentFlyingHeight = ((Integer)WitherStormModConfig.SERVER.flyingHeight.get()).intValue();
                ascendSpeed = 0.005;
            } else {
                int timeDiff = this.f_19797_ - this.lastFlyingSwitchTime;
                if (timeDiff >= (Integer)WitherStormModConfig.SERVER.dynamicFlyingHeightTime.get() * 20) {
                    this.lastFlyingSwitchTime = this.f_19797_;
                    this.currentFlyingHeight = 40 + this.m_217043_().m_188503_(41);
                }
            }
        }
        double finalHeight = this.getHeightToAscendTo(vector3d, this.currentFlyingHeight, ascendSpeed);
        vector3d = new Vec3(vector3d.f_82479_, finalHeight, vector3d.f_82481_);
        if (this.m_5448_() != null && this.getPhase() < 4) {
            LivingEntity entity = this.m_5448_();
            Vec3 vector3d1 = new Vec3(entity.m_20185_() - this.m_20185_(), 0.0, entity.m_20189_() - this.m_20189_());
            if (vector3d1.m_165924_() > 20.0) {
                Vec3 vector3d2 = vector3d1.m_82541_();
                vector3d = vector3d.m_82520_(vector3d2.f_82479_ * 0.3 - vector3d.f_82479_ * 0.6, 0.0, vector3d2.f_82481_ * 0.3 - vector3d.f_82481_ * 0.6);
            }
        } else if (!this.isNearbyTickingFormidibomb() && this.shouldTrackUltimateTarget() && (this.m_5448_() == null || this.getPhase() > 3)) {
            Vec3 pos;
            double minDistance = 12000.0;
            if (this.getPhase() > 3) {
                minDistance = 6000.0;
            }
            if ((pos = this.getUltimateTargetPos()) != null) {
                Vec3 vector3d1 = new Vec3(pos.m_7096_() - this.m_20185_(), 0.0, pos.m_7094_() - this.m_20189_());
                double speed = this.getFlyingSpeed(vector3d1);
                WitherStormModifyFlyingSpeedEvent event = new WitherStormModifyFlyingSpeedEvent(this, speed);
                MinecraftForge.EVENT_BUS.post((Event)event);
                speed = event.getOriginalSpeed();
                if (vector3d1.m_165925_() > minDistance) {
                    Vec3 vector3d2 = vector3d1.m_82541_();
                    vector3d = vector3d.m_82520_(vector3d2.f_82479_ * speed - vector3d.f_82479_ * 0.6, 0.0, vector3d2.f_82481_ * speed - vector3d.f_82481_ * 0.6);
                }
            }
        }
        return vector3d;
    }

    public double getHeightToAscendTo(Vec3 vector3d, double height, double ascendSpeed) {
        double finalHeight = vector3d.f_82480_;
        double yToStart = Math.min((double)(this.m_9236_().m_151558_() - 1), this.m_20191_().m_82399_().m_7098_());
        int radius = (int)(this.m_6972_((Pose)this.m_20089_()).f_20377_ * 1.5f);
        double highest = -1.0;
        for (int x = -radius; x < radius; ++x) {
            for (int z = -radius; z < radius; ++z) {
                int currentHeight;
                Heightmap.Types type = Heightmap.Types.MOTION_BLOCKING_NO_LEAVES;
                if (this.getPhase() < 4) {
                    type = Heightmap.Types.WORLD_SURFACE;
                }
                if ((currentHeight = this.getPhase() > 3 ? this.m_9236_().m_6924_(type, x + Mth.m_14107_((double)this.m_20185_()), z + Mth.m_14107_((double)this.m_20189_())) : WorldUtil.getHeightStartingAt(this.m_9236_(), Mth.m_14107_((double)yToStart), x + this.m_146903_(), z + this.m_146907_())) > (int)yToStart) {
                    currentHeight = (int)yToStart;
                }
                if (highest != -1.0 && !((double)currentHeight > highest)) continue;
                highest = currentHeight;
            }
        }
        double heightToAscendTo = highest + height;
        if (this.m_20186_() < heightToAscendTo || !this.m_7090_() && this.m_20186_() < heightToAscendTo + 5.0) {
            finalHeight = (heightToAscendTo - this.m_20186_()) * ascendSpeed;
        }
        return finalHeight;
    }

    public double distanceTo(BlockPos pos) {
        float x = (float)(this.m_20185_() - (double)pos.m_123341_());
        float y = (float)(this.m_20186_() - (double)pos.m_123342_());
        float z = (float)(this.m_20189_() - (double)pos.m_123343_());
        return Mth.m_14116_((float)(x * x + y * y + z * z));
    }

    public boolean targetInUseBySegment(Entity entity) {
        if (this.getSegmentsManager().isPresent()) {
            WitherStormSegmentEntity[] segments;
            for (WitherStormSegmentEntity segment : segments = this.getSegmentsManager().get().getSegments()) {
                if (segment == null || !segment.alreadyATarget(entity, true)) continue;
                return true;
            }
        }
        return false;
    }

    protected SoundEvent m_7515_() {
        if (!this.isDeadOrPlayingDead()) {
            if (this.getPhase() < 4) {
                return SoundEvents.f_12554_;
            }
            return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_GROWL.get();
        }
        return null;
    }

    protected SoundEvent m_7975_(@NotNull DamageSource source) {
        if (this.getPhase() < 4) {
            return SoundEvents.f_12557_;
        }
        return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_HURT.get();
    }

    protected SoundEvent m_5592_() {
        if (this.getPhase() < 4) {
            return SoundEvents.f_12556_;
        }
        return null;
    }

    public int m_8100_() {
        if (this.getPhase() > 3) {
            return Math.max(80, this.f_19796_.m_188503_(120));
        }
        return super.m_8100_();
    }

    public float m_6121_() {
        if (this.getPhase() > 3) {
            return 25.0f;
        }
        return super.m_6121_();
    }

    public static float m_21376_(float p_82204_1_, float p_82204_2_, float p_82204_3_) {
        float f = Mth.m_14177_((float)(p_82204_2_ - p_82204_1_));
        if (f > p_82204_3_) {
            f = p_82204_3_;
        }
        if (f < -p_82204_3_) {
            f = -p_82204_3_;
        }
        return p_82204_1_ + f;
    }

    @Override
    public float getHeadYRot(int head) {
        return this.headManager.getHead(head).getHeadYRot();
    }

    @Override
    public float getHeadXRot(int head) {
        return this.headManager.getHead(head).getHeadXRot();
    }

    @Override
    public float getHeadYRotO(int head) {
        return this.headManager.getHead(head).getHeadYRotO();
    }

    @Override
    public float getHeadXRotO(int head) {
        return this.headManager.getHead(head).getHeadXRotO();
    }

    private void tickExtraHeadGoals() {
        int i = this.m_9236_().m_7654_().m_129921_() + this.m_19879_();
        if (i % 2 != 0 && this.f_19797_ > 1) {
            this.m_9236_().m_46473_().m_6180_("targetSelectorHeads");
            this.headTargetSelectors.forEach(sel -> sel.m_186081_(false));
            this.m_9236_().m_46473_().m_7238_();
            this.m_9236_().m_46473_().m_6180_("goalSelectorHeads");
            this.headGoalSelectors.forEach(sel -> sel.m_186081_(false));
            this.m_9236_().m_46473_().m_7238_();
        } else {
            this.m_9236_().m_46473_().m_6180_("targetSelectorHeads");
            this.headTargetSelectors.forEach(GoalSelector::m_25373_);
            this.m_9236_().m_46473_().m_7238_();
            this.m_9236_().m_46473_().m_6180_("goalSelectorHeads");
            this.headGoalSelectors.forEach(GoalSelector::m_25373_);
            this.m_9236_().m_46473_().m_7238_();
        }
    }

    protected void searchForPlayingJukeboxes() {
        for (BlockEntity entity : WorldUtil.getBlockEntitiesInAABB(this.m_9236_(), this.getSearchBox())) {
            JukeboxBlockEntity jukebox;
            if (!(entity instanceof JukeboxBlockEntity) || !(jukebox = (JukeboxBlockEntity)entity).m_272025_() || this.playingJukeboxes.contains(jukebox.m_58899_())) continue;
            this.playingJukeboxes.add(jukebox.m_58899_());
        }
    }

    protected void m_8024_() {
        this.tickExtraHeadGoals();
        if (this.getInvulnerableTicks() > 0) {
            int ticks = this.getInvulnerableTicks() - 1;
            if (ticks <= 0) {
                this.m_9236_().m_255391_((Entity)this, this.m_20185_(), this.m_20188_(), this.m_20189_(), 7.0f, false, Level.ExplosionInteraction.MOB);
                if (!this.m_20067_()) {
                    this.m_9236_().m_6798_(1023, this.m_20183_(), 0);
                }
            }
            this.setInvulnerableTicks(ticks);
            float healthPerTick = (this.m_21233_() - 1.0f) / (float)this.getStartingInvulnerableTicks() * 10.0f;
            if (this.f_19797_ % 10 == 0) {
                this.m_5634_(healthPerTick);
            }
            this.bossEvent.ifPresent(bossEvent -> bossEvent.m_142711_(this.m_21223_() / this.m_21233_()));
        } else {
            super.m_8024_();
            if (!this.isDeadOrPlayingDead()) {
                CommandBlockEntity commandBlock;
                this.headManager.customServerAiStep();
                if (this.destroyBlocksTick > 0) {
                    --this.destroyBlocksTick;
                    if (this.destroyBlocksTick == 0 && ForgeEventFactory.getMobGriefingEvent((Level)this.m_9236_(), (Entity)this)) {
                        int i1 = Mth.m_14107_((double)this.m_20186_());
                        int l1 = Mth.m_14107_((double)this.m_20185_());
                        int i2 = Mth.m_14107_((double)this.m_20189_());
                        boolean flag = false;
                        for (int k2 = -1; k2 <= 1; ++k2) {
                            for (int l2 = -1; l2 <= 1; ++l2) {
                                for (int j = 0; j <= 3; ++j) {
                                    int i3 = l1 + k2;
                                    int k = i1 + j;
                                    int l = i2 + l2;
                                    BlockPos blockpos = new BlockPos(i3, k, l);
                                    BlockState blockstate = this.m_9236_().m_8055_(blockpos);
                                    if (!blockstate.canEntityDestroy((BlockGetter)this.m_9236_(), blockpos, (Entity)this) || blockstate.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST) || !ForgeEventFactory.onEntityDestroyBlock((LivingEntity)this, (BlockPos)blockpos, (BlockState)blockstate)) continue;
                                    flag = this.m_9236_().m_46953_(blockpos, true, (Entity)this) || flag;
                                }
                            }
                        }
                        if (flag) {
                            this.m_9236_().m_5898_(null, 1022, this.m_20183_(), 0);
                        }
                    }
                }
                for (BlockClusterSource source : WitherStormWorldInteractions.getInstance().getClusterSources()) {
                    source.tick(this);
                }
                if (((Boolean)WitherStormModConfig.SERVER.convertFallingBlocks.get()).booleanValue()) {
                    List fallingBlocks = this.m_9236_().m_45976_(FallingBlockEntity.class, this.getSearchBox());
                    for (FallingBlockEntity fallingBlock : fallingBlocks) {
                        if (!WorldUtil.isLoaded((ServerLevel)this.m_9236_(), fallingBlock.m_20183_()) || !WorldUtil.canSeeOrIsNotInASmallArea((Entity)this, (Entity)fallingBlock)) continue;
                        BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(this.m_9236_());
                        HashMap map = Maps.newHashMap();
                        map.put(BlockPos.f_121853_, fallingBlock.m_31980_());
                        cluster.populate(map);
                        cluster.m_20219_(fallingBlock.m_20182_());
                        cluster.setRotationDelta(new Vec2((float)this.f_19796_.m_188503_(20) * 0.1f / 2.0f, (float)this.f_19796_.m_188503_(20) * 0.1f / 2.0f));
                        cluster.m_20242_(true);
                        cluster.setPhysics(false);
                        cluster.setCreatedFromFallingBlock(true);
                        fallingBlock.m_146870_();
                        this.m_9236_().m_7967_((Entity)cluster);
                        this.trackedEntities.trackEntityToConsume(cluster);
                    }
                }
                if (ForgeEventFactory.getMobGriefingEvent((Level)this.m_9236_(), (Entity)this)) {
                    double consumptionRadius;
                    double addRad;
                    if (this.getPhase() >= 6) {
                        addRad = (double)this.entityConsumptionRadius + 50.0 + 100.0;
                        consumptionRadius = this.entityConsumptionRadius;
                    } else if (this.getPhase() <= 3) {
                        addRad = (double)this.entityConsumptionRadiusHunch() + 50.0 + 0.0;
                        consumptionRadius = this.entityConsumptionRadiusHunch();
                    } else {
                        addRad = (double)this.entityConsumptionRadius + 50.0 + 0.0;
                        consumptionRadius = this.entityConsumptionRadius;
                    }
                    AABB searchBB = this.m_20191_().m_82400_(addRad);
                    List playerBoundingBoxes = this.m_9236_().m_45976_(ServerPlayer.class, searchBB).stream().map(p -> p.m_20191_().m_82400_(8.0)).toList();
                    List entities = this.m_9236_().m_6443_(Entity.class, searchBB, PICKABLE);
                    for (Entity entity : entities) {
                        if (this.trackedEntities.contains(entity) || !(this.f_19796_.m_188501_() >= 0.9f)) continue;
                        if (entity instanceof ItemEntity) {
                            ItemEntity itemEntity = (ItemEntity)entity;
                            ItemStack item = itemEntity.m_32055_();
                            if (this.getPhase() > 3 && item.m_204117_(WitherStormModItemTags.JUNK) && ((Boolean)WitherStormModConfig.SERVER.removeNearbyJunk.get()).booleanValue()) {
                                if (!playerBoundingBoxes.stream().allMatch(box -> !box.m_82390_(entity.m_20182_()))) continue;
                                entity.m_146870_();
                                continue;
                            }
                            if (!((double)entity.m_20270_((Entity)this) <= consumptionRadius) || !(item.m_204117_(WitherStormModItemTags.UNAPPETIZING) && entity.m_20270_((Entity)this) < (float)(this.getPhase() > 3 ? 35 : 2)) && item.m_204117_(WitherStormModItemTags.UNAPPETIZING) || item.m_150930_((Item)WitherStormModItems.COMMAND_BLOCK_BOOK.get()) || item.m_204117_(WitherStormModItemTags.COMMAND_BLOCK_TOOLS) || item.m_150930_((Item)WitherStormModItems.WITHERED_NETHER_STAR.get()) || !this.canTrackEntity(entity)) continue;
                            this.trackedEntities.trackEntityToConsume(entity);
                            entity.m_20242_(true);
                            continue;
                        }
                        if (!(entity instanceof Slime) || !((double)entity.m_20270_((Entity)this) <= consumptionRadius) || !this.canTrackEntity(entity)) continue;
                        this.trackedEntities.trackEntityToConsume(entity);
                        entity.m_20242_(true);
                    }
                }
                double defaultSpeed = (Double)WitherStormModConfig.SERVER.tractorPullSpeedModifier.get();
                if (this.getPhase() < 4) {
                    for (WitherStormHead head : this.headManager.getHeads()) {
                        this.pullInTarget((Entity)head.getTarget(), defaultSpeed, head);
                    }
                }
                for (Entity entity : this.m_9236_().m_45976_(Entity.class, this.getSearchBox())) {
                    if (this.getIgnoredTargets().shouldIgnoreEntity(entity)) continue;
                    for (WitherStormHead head : this.headManager.getHeads()) {
                        LivingEntity living;
                        if (!TractorBeamHelper.isInsideTractorBeam(entity, this, 4.0, head.getIndex())) continue;
                        if (this.getPhase() > 3 && head.getTarget() == entity) {
                            this.pullInTarget(entity, defaultSpeed, head);
                            continue;
                        }
                        if (!((Boolean)WitherStormModConfig.SERVER.canPickupMobClusters.get()).booleanValue() || this.isDistracted(head.getIndex()) || !(entity instanceof LivingEntity ? this.entitySelector.test(living = (LivingEntity)entity) : TRACTOR_BEAM_PULLABLE.test(entity)) || !EntitySelector.f_20406_.test(entity) || this.alreadyATarget(entity, true) || this.targetInUseBySegment(entity) || !head.canSee(entity) || this.trackedEntities.contains(entity)) continue;
                        double speed = TRACTOR_BEAM_PULLABLE.test(entity) ? 0.4 : (!(entity instanceof Player) ? defaultSpeed - 0.05 + new Random(entity.m_19879_()).nextDouble() * 0.1 : defaultSpeed);
                        this.pullInTarget(entity, speed, head);
                        if (!ABSORBABLE.test(entity) || !(entity.m_20182_().m_82554_(head.getHeadPos()) < 20.0)) continue;
                        this.trackedEntities.trackEntityToConsume(entity);
                    }
                }
                if (this.isAddedToWorld() && this.f_19797_ % 20 == 0 && this.isCompletelyInvulnerable()) {
                    this.m_5634_(10.0f);
                }
                this.bossEvent.ifPresent(bossEvent -> bossEvent.m_142711_(this.m_21223_() / this.m_21233_()));
                if (this.m_5448_() != null) {
                    ++this.idleTargetTicks;
                }
                if (this.idleTargetTicks > 1800 || this.m_5448_() == null) {
                    this.m_6710_(null);
                    this.idleTargetTicks = 0;
                }
                if (this.getPhase() > 3) {
                    for (WitherStormHead head : this.headManager.getHeads()) {
                        if (!this.tractorBeamActive(head.getIndex())) continue;
                        AABB headBox = head.getBoundingBox();
                        List headBoxEntities = this.m_9236_().m_45976_(Projectile.class, headBox);
                        for (Projectile projectile : headBoxEntities) {
                            if (projectile.m_19749_() == this) continue;
                            if (head.getHeadInjureAttemptCooldown() <= 0 && head.getHeadInjuryTicks() <= 0) {
                                head.setHeadInjureAttemptCooldown(40);
                                if (!this.isDeadOrPlayingDead() && head.checkAndCountAttack()) {
                                    head.hurt(projectile.m_19749_(), this.headManager.getHeadInjuryTime());
                                    Entity itemEntity = projectile.m_19749_();
                                    if (itemEntity instanceof Player) {
                                        Player owner = (Player)itemEntity;
                                        owner.m_6330_(SoundEvents.f_11686_, SoundSource.PLAYERS, 1.0f, 1.0f);
                                    }
                                }
                            }
                            if (projectile instanceof ThrownTrident) continue;
                            projectile.m_146870_();
                        }
                    }
                }
                if ((commandBlock = this.getBowelsCommandBlock()) != null && commandBlock.m_21223_() < commandBlock.m_21233_() && this.nextFlicker > 0) {
                    --this.nextFlicker;
                    if (this.nextFlicker == 0) {
                        this.doFlicker();
                        this.nextFlicker = (int)((double)this.f_19796_.m_188503_(40) + 60.0 * Math.max(0.2, (double)(commandBlock.m_21223_() / commandBlock.m_21233_())));
                    }
                }
                if ((this.f_19862_ || this.f_19863_) && ForgeEventFactory.getMobGriefingEvent((Level)this.m_9236_(), (Entity)this) && this.getPhase() > 3) {
                    for (int i = 0; i < 10; ++i) {
                        Direction direction = Direction.m_235672_((RandomSource)this.f_19796_);
                        AABB box2 = this.m_20191_();
                        int scale = 2;
                        BlockPos pos = switch (direction.m_122434_()) {
                            case Direction.Axis.Y -> {
                                int x = Mth.m_14107_((double)(box2.f_82288_ + (box2.f_82291_ - box2.f_82288_) * this.f_19796_.m_188500_()));
                                int y = Mth.m_14107_((double)(direction == Direction.DOWN ? box2.f_82289_ - (double)scale : box2.f_82292_ + (double)scale));
                                int z = Mth.m_14107_((double)(box2.f_82290_ + (box2.f_82293_ - box2.f_82290_) * this.f_19796_.m_188500_()));
                                yield new BlockPos(x, y, z);
                            }
                            case Direction.Axis.X -> {
                                int x = Mth.m_14107_((double)(direction == Direction.WEST ? box2.f_82288_ - (double)scale : box2.f_82291_ + (double)scale));
                                int y = Mth.m_14107_((double)(box2.f_82289_ + (box2.f_82292_ - box2.f_82289_) * this.f_19796_.m_188500_()));
                                int z = Mth.m_14107_((double)(box2.f_82290_ + (box2.f_82293_ - box2.f_82290_) * this.f_19796_.m_188500_()));
                                yield new BlockPos(x, y, z);
                            }
                            case Direction.Axis.Z -> {
                                int x = Mth.m_14107_((double)(box2.f_82288_ + (box2.f_82291_ - box2.f_82288_) * this.f_19796_.m_188500_()));
                                int y = Mth.m_14107_((double)(box2.f_82289_ + (box2.f_82292_ - box2.f_82289_) * this.f_19796_.m_188500_()));
                                int z = Mth.m_14107_((double)(direction == Direction.NORTH ? box2.f_82290_ - (double)scale : box2.f_82293_ + (double)scale));
                                yield new BlockPos(x, y, z);
                            }
                            default -> throw new IllegalArgumentException("Unexpected value: " + direction.m_122434_());
                        };
                        BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(this.m_9236_());
                        assert (cluster != null);
                        cluster.populateWithRadius(pos, 3.0f + this.f_19796_.m_188501_() * 2.0f, state -> !state.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST));
                        if (cluster.getSize() <= 0) continue;
                        this.trackedEntities.trackEntityToConsume(cluster);
                        cluster.setRotationDelta(new Vec2((float)this.f_19796_.m_188503_(20) * 0.1f / 2.0f, (float)this.f_19796_.m_188503_(20) * 0.1f / 2.0f));
                        cluster.m_20242_(true);
                        cluster.setPhysics(false);
                        cluster.setShakeTime(5);
                        this.m_9236_().m_7967_((Entity)cluster);
                    }
                }
                if (((Boolean)WitherStormModConfig.SERVER.caveRumbles.get()).booleanValue() && this.nextUndergroundRumble > 0) {
                    --this.nextUndergroundRumble;
                    if (this.nextUndergroundRumble == 0) {
                        if (this.getPhase() > 3) {
                            for (ServerPlayer tracking : this.m_9236_().m_45976_(ServerPlayer.class, this.getSearchBox().m_82400_(50.0))) {
                                if (WorldUtil.canSeeOrIsNotInASmallArea((Entity)this, (Entity)tracking)) continue;
                                WorldUtil.doCaveRumble((ServerLevel)this.m_9236_(), tracking, (Double)WitherStormModConfig.SERVER.caveRumbleIntensity.get(), this.f_19796_);
                            }
                        }
                        if (((Boolean)WitherStormModConfig.SERVER.chanceForExtendedRumbles.get()).booleanValue() && this.f_19796_.m_188503_(3) != 0) {
                            this.nextUndergroundRumble = 100 + this.f_19796_.m_188503_(60);
                        } else {
                            int min = (Integer)WitherStormModConfig.SERVER.caveRumbleIntervalMin.get() * 20;
                            int diff = (Integer)WitherStormModConfig.SERVER.caveRumbleIntervalMax.get() * 20 - min;
                            this.nextUndergroundRumble = diff > 0 ? min + this.f_19796_.m_188503_(diff) : min;
                        }
                    }
                }
                this.searchForPlayingJukeboxes();
                Iterator<BlockPos> iterator = this.playingJukeboxes.iterator();
                while (iterator.hasNext()) {
                    BlockPos pos = iterator.next();
                    if (!this.getSearchBox().m_82390_(Vec3.m_82512_((Vec3i)pos))) {
                        iterator.remove();
                        continue;
                    }
                    BlockEntity entity = this.m_9236_().m_7702_(pos);
                    if (entity instanceof JukeboxBlockEntity) {
                        JukeboxBlockEntity jukebox = (JukeboxBlockEntity)entity;
                        if (jukebox.m_272025_()) continue;
                        iterator.remove();
                        continue;
                    }
                    iterator.remove();
                }
            }
        }
    }

    protected boolean canTrackEntity(Entity entity) {
        SegmentsManager manager = this.getSegmentsManager().orElse(null);
        if (manager != null) {
            for (WitherStormSegmentEntity segment : manager.getSegments()) {
                if (segment == null || !segment.m_6084_() || !segment.getTrackedEntities().contains(entity)) continue;
                return false;
            }
        }
        return WorldUtil.canSeeOrIsNotInASmallArea((Entity)this, entity);
    }

    protected void m_6153_() {
        if (this.getPhase() > 3) {
            ++this.witherStormDeathTime;
        } else {
            ++this.f_20919_;
        }
        if (!this.m_20096_()) {
            for (WitherStormHead head : this.headManager.getHeads()) {
                head.lerpHeadXTo(-50.0f, 64.0f);
            }
        }
        if (!this.m_9236_().f_46443_) {
            if (this.getPhase() > 5 && this.getDeathTime() < 240 && ForgeEventFactory.getMobGriefingEvent((Level)this.m_9236_(), (Entity)this)) {
                this.dropDeathClusters();
            }
            if (this.getPhase() > 3 && this.getDeathTime() == 360) {
                this.m_142687_(Entity.RemovalReason.KILLED);
                Player nearest = this.m_9236_().m_45924_(this.m_20185_(), this.m_20186_(), this.m_20189_(), this.m_21133_(Attributes.f_22277_) + 50.0, false);
                if (nearest != null) {
                    this.dropDropsAt((Entity)nearest);
                }
            } else if (this.getPhase() < 4 && this.f_20919_ == 20) {
                this.m_142687_(Entity.RemovalReason.KILLED);
            }
            this.bossEvent.ifPresent(event -> event.m_142711_(1.0f - (float)this.getDeathTime() / 360.0f));
        } else {
            if (this.f_19797_ % 20 == 0) {
                int size = Math.max(10, this.f_19796_.m_188503_(15));
                for (int i = 0; i < size; ++i) {
                    for (DebrisCluster cluster : this.getDebrisClusters()) {
                        if (cluster.isDisabled()) continue;
                        cluster.setDisabled(this.f_19796_.m_188503_(this.getDebrisClusters().size()) == 0);
                    }
                }
            }
            float percentage = Math.max(0.0f, (360.0f - (float)this.getDeathTime()) / 360.0f);
            for (DebrisRingSettings settings : this.getDebrisRings()) {
                settings.setAlpha(percentage);
            }
            this.setShineAlpha(percentage);
        }
    }

    protected void dropDeathClusters() {
        int interval = 240 / this.getPhase();
        if (this.getDeathTime() % interval == 0) {
            this.dropMassCluster(this.getPhase() - 2);
        }
        if (this.getDeathTime() % 5 == 0) {
            this.dropMassCluster(2);
        }
        if (this.getDeathTime() > 5) {
            for (int i = 0; i < 3; ++i) {
                this.dropSmallMassCluster(1);
            }
        }
    }

    public boolean alreadyATarget(Entity entity, boolean countMain) {
        for (WitherStormHead head : this.headManager.getHeads()) {
            if (!countMain && head instanceof MainHead || !entity.equals((Object)head.getTarget())) continue;
            return true;
        }
        return false;
    }

    public void removeFluidFromLook(float x, float y, int head) {
        Vec3 vecPos = this.getHeadPos(head);
        Vec3 end = vecPos.m_82549_(this.getViewVector(x, y, 200.0f));
        if (ForgeEventFactory.getMobGriefingEvent((Level)this.m_9236_(), (Entity)this) && this.getPhase() > 3) {
            BlockHitResult result = this.m_9236_().m_45547_(new ClipContext(vecPos, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, null));
            BlockPos hitPos = result.m_82425_();
            if (WorldUtil.isLoaded((ServerLevel)this.m_9236_(), hitPos) && hitPos.m_123342_() > (Integer)WitherStormModConfig.SERVER.tractorBeamFluidRemovalHeight.get()) {
                for (int hitX = -6; hitX <= 6; ++hitX) {
                    for (int hitY = -6; hitY <= 6; ++hitY) {
                        for (int hitZ = -6; hitZ <= 6; ++hitZ) {
                            BlockPos pos = hitPos.m_7918_(hitX, hitY, hitZ);
                            BlockState state = this.m_9236_().m_8055_(pos);
                            if (state.m_60819_().m_192917_((Fluid)Fluids.f_76193_)) {
                                this.m_9236_().m_7731_(pos, Fluids.f_76192_.m_76145_().m_76188_(), 3);
                            }
                            if (!state.m_60819_().m_192917_((Fluid)Fluids.f_76195_)) continue;
                            this.m_9236_().m_7731_(pos, Fluids.f_76194_.m_76145_().m_76188_(), 3);
                        }
                    }
                }
            }
        }
    }

    public void createClusterFromLook(float x, float y, int time, int head) {
        Vec3 vecPos = this.getHeadPos(head);
        Vec3 end = vecPos.m_82549_(this.getViewVector(x, y, 200.0f));
        if (ForgeEventFactory.getMobGriefingEvent((Level)this.m_9236_(), (Entity)this)) {
            BlockHitResult result = this.m_9236_().m_45547_(new ClipContext(vecPos, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null));
            BlockPos hitPos = result.m_82425_();
            if (WorldUtil.isLoaded((ServerLevel)this.m_9236_(), hitPos)) {
                for (int i = 0; i < 512; ++i) {
                    BlockState state;
                    int offsetZ;
                    int offsetY;
                    int offsetX;
                    if (this.getPhase() <= 3) {
                        offsetX = (int)Math.round(this.f_19796_.m_188583_() * 1.0);
                        offsetY = (int)Math.round(this.f_19796_.m_188583_() * 1.0);
                        offsetZ = (int)Math.round(this.f_19796_.m_188583_() * 1.0);
                    } else {
                        offsetX = (int)Math.round(this.f_19796_.m_188583_() * 2.25);
                        offsetY = (int)Math.round(this.f_19796_.m_188583_() * 2.25);
                        offsetZ = (int)Math.round(this.f_19796_.m_188583_() * 2.25);
                    }
                    BlockPos pos = hitPos.m_7918_(offsetX, offsetY, offsetZ);
                    if (!WorldUtil.isBlockExposed(this.m_9236_(), pos) || (state = this.m_9236_().m_8055_(pos)).m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST) || this.f_19796_.m_188500_() > 0.999 && (state.m_204336_(Tags.Blocks.STONE) || state.m_204336_(BlockTags.f_144274_) || state.m_204336_(BlockTags.f_13029_)) || ((Boolean)WitherStormModConfig.SERVER.onlyTryPickingUpTractorTagged.get()).booleanValue() && !state.m_204336_(WitherStormModBlockTags.TRACTOR_BEAM_DISTRACTION_BLOCKS)) continue;
                    double radius = 1.0;
                    if (this.getPhase() == 4) {
                        radius = Math.max(1.0, Math.min(1.5, 1.0 + 0.125 * this.f_19796_.m_188583_()));
                    } else if (this.getPhase() == 5) {
                        radius = Math.max(1.0, Math.min(3.0, 1.0 + 0.5 * this.f_19796_.m_188583_()));
                    } else if (this.getPhase() == 6) {
                        radius = Math.max(1.0, Math.min(4.5, 1.0 + 0.75 * this.f_19796_.m_188583_()));
                    } else if (this.getPhase() == 7) {
                        radius = Math.max(1.0, Math.min(8.0, 1.5 + 1.25 * this.f_19796_.m_188583_()));
                    }
                    BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(this.m_9236_());
                    assert (cluster != null);
                    cluster.populateWithRadius(pos, (float)radius, blockstate -> !blockstate.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST));
                    cluster.setTime(time);
                    if (this.f_19796_.m_188503_(3) == 0) {
                        cluster.m_5496_((SoundEvent)WitherStormModSoundEvents.BLOCK_CLUSTER_SHAKE.get(), 2.0f, (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.2f + 1.0f);
                    }
                    cluster.setCreatedFromTractorBeam(true);
                    cluster.setHeadCreatedFrom(head);
                    cluster.setTractorBeamDistanceThreshold(this.f_19796_.m_188500_() * 5.0);
                    this.trackedEntities.trackEntityToConsume(cluster);
                    cluster.setRotationDelta(new Vec2((float)(this.f_19796_.m_188503_(120) - 60) * 0.05f / ((float)radius * 3.0f), (float)(this.f_19796_.m_188503_(120) - 60) * 0.05f / ((float)radius * 3.0f)));
                    cluster.m_20242_(true);
                    cluster.setPhysics(false);
                    this.m_9236_().m_7967_((Entity)cluster);
                    break;
                }
            }
        }
    }

    private void setPlayerDeltaMovement(ServerPlayer player, Vec3 motion) {
        PlayerMotionMessage message = new PlayerMotionMessage(motion);
        WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> player), (Object)message);
    }

    @NotNull
    public AABB m_6921_() {
        return this.m_20191_().m_82400_(50.0);
    }

    @NotNull
    public EntityDimensions m_6972_(@NotNull Pose pose) {
        EntityDimensions size = this.getUnmodifiedDimensions(pose);
        if (((Boolean)WitherStormModConfig.SERVER.squashHitbox.get()).booleanValue() && this.getPhase() > 3) {
            size = EntityDimensions.m_20395_((float)size.f_20377_, (float)1.0f);
        }
        if (this.getPlayDeadManager().getState() == PlayDeadManager.State.PLAYING_DEAD) {
            size = EntityDimensions.m_20395_((float)size.f_20377_, (float)0.1f);
        }
        return size;
    }

    public EntityDimensions getUnmodifiedDimensions(Pose pos) {
        EntityDimensions size = STARTING_SIZE;
        if (this.getPhase() == 4) {
            size = DESTROYER_SIZE;
        } else if (this.getPhase() == 5) {
            size = EVOLVED_DESTROYER_SIZE;
        } else if (this.getPhase() == 6) {
            size = DEVOURER_SIZE;
        } else if (this.getPhase() == 7) {
            size = EVOLVED_DEVOURER_SIZE;
        }
        return size;
    }

    public float getUnmodifiedHeight() {
        return this.getUnmodifiedDimensions((Pose)this.m_20089_()).f_20378_;
    }

    public float getUnmodifiedWidth() {
        return this.getUnmodifiedDimensions((Pose)this.m_20089_()).f_20377_;
    }

    public float getUnmodifiedSize() {
        float width = this.getUnmodifiedWidth();
        float height = this.getUnmodifiedHeight();
        return (width + height + width) / 3.0f;
    }

    protected float m_6431_(@NotNull Pose pose, @NotNull EntityDimensions size) {
        if (this.getPhase() > 3) {
            return 17.5f;
        }
        return super.m_6431_(pose, size);
    }

    public void m_6075_() {
        this.headManager.baseTick();
        super.m_6075_();
        this.xBodyRotO = this.xBodyRot;
        this.shineAlphaO = this.shineAlpha;
    }

    public void m_8119_() {
        super.m_8119_();
        if (!this.m_9236_().f_46443_ && this.evolutionProfiler.isProfiling()) {
            this.evolutionProfiler.tick(this);
        }
        this.tentacleTickCountO = this.tentacleTickCount;
        if (!this.isDeadOrPlayingDead()) {
            this.tentacleTickCount += (int)(1.0f + this.f_267362_.m_267731_());
        }
        this.lastConsumedEntities = this.getConsumedEntities();
        if (!this.m_9236_().f_46443_) {
            this.trackedEntities.tick((ServerLevel)this.m_9236_());
            this.ignoredTargets.tick();
            this.bowelsInstance.ifPresent(BowelsInstanceManager::tick);
            Vec3 absorptionPoint = this.m_20191_().m_82399_();
            List<Entity> currentTracked = this.trackedEntities.getCurrentTrackedEntities();
            ArrayList toAdd = Lists.newArrayList();
            AABB absorptionBox = this.m_20191_();
            if (this.getPhase() > 3) {
                absorptionBox = this.m_20191_().m_82406_((double)this.getUnmodifiedWidth() / 1.5);
            }
            for (Entity entity : currentTracked) {
                if (entity.m_6084_()) {
                    this.doEntityPulling(entity, absorptionPoint, absorptionBox);
                    if (!(entity instanceof BlockClusterEntity)) continue;
                    BlockClusterEntity cluster = (BlockClusterEntity)entity;
                    if (!WorldUtil.isLoaded((ServerLevel)this.m_9236_(), cluster.m_20183_()) || cluster.getShakeTime() > 0 || !cluster.shouldCrumble() || this.f_19797_ % 20 != 0 || this.f_19796_.m_188503_(3) != 0) continue;
                    this.splitCluster(cluster, toAdd);
                    continue;
                }
                this.trackedEntities.stopTrackingEntity(entity);
            }
            for (Entity entity : toAdd) {
                this.trackedEntities.trackEntityToConsume(entity);
            }
            if (this.getPhase() > 5) {
                this.findSegments();
                this.createSegments();
                this.addSegments();
            }
            if (this.getPhase() < 6) {
                this.removeSegments();
            } else {
                this.readdSegments();
            }
            this.bossEvent.ifPresent(event -> {
                for (ServerPlayer tracking : this.playersTracking) {
                    if (!this.smartBossMusic() || WorldUtil.canSeeOrIsNotInASmallArea((Entity)this, (Entity)tracking)) {
                        event.m_6543_(tracking);
                        continue;
                    }
                    event.m_6539_(tracking);
                }
            });
        }
        int phase = this.getPhase();
        float prevConsumption = this.getConsumptionAmountForPhase(phase - 1);
        float consumptionForPhase = this.getConsumptionAmountForPhase(phase);
        float consumption = this.getConsumedEntities();
        this.phaseProgress = Mth.m_14036_((float)((consumption - prevConsumption) / (consumptionForPhase - prevConsumption)), (float)0.0f, (float)1.0f);
        if (this.m_9236_().m_5776_()) {
            for (DebrisCluster debrisCluster : this.getDebrisClusters()) {
                if (debrisCluster.isDisabled()) continue;
                debrisCluster.tick();
            }
            if (this.shouldShine()) {
                if (phase > 0) {
                    float factor = (float)phase - this.phaseProgress;
                    this.shineScale = this.getUnmodifiedHeight() * (10.0f / factor);
                } else {
                    this.shineScale = this.getUnmodifiedHeight();
                }
            }
        }
        this.checkConsumptionAmount();
        if (this.partsEnabled) {
            Section[] sections;
            for (Section section : sections = this.getSections()) {
                if (!section.isActive()) continue;
                section.tick();
            }
        }
        if (!this.m_9236_().f_46443_ && this.shouldPlaySoundLoops()) {
            StormSoundPositionMessage message = new StormSoundPositionMessage(this.m_19879_(), this.m_20185_(), this.m_20188_(), this.m_20189_(), (byte)this.getPhase());
            WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> this.m_9236_().m_46472_()), (Object)message);
        }
        this.headManager.tick();
        this.onGroundAnimationO = this.onGroundAnimation;
        if (this.m_20096_() && this.isDeadOrPlayingDead()) {
            this.onGroundAnimation += 1.0f + this.f_19796_.m_188501_() * 2.0f;
            if (this.onGroundAnimation > 300.0f) {
                this.onGroundAnimation = 300.0f;
            }
        } else {
            this.onGroundAnimation -= 1.0f + this.f_19796_.m_188501_() * 2.0f;
            if (this.onGroundAnimation < 0.0f) {
                this.onGroundAnimation = 0.0f;
            }
        }
        if (this.flickerTime > 0) {
            --this.flickerTime;
            boolean bl = this.shouldFlicker = Mth.m_14089_((float)(this.flickerTime + this.f_19796_.m_188503_(20))) * Mth.m_14031_((float)(this.flickerTime + 30 + this.f_19796_.m_188503_(20))) < -0.5f;
            if (this.flickerTime == 0) {
                this.shouldFlicker = false;
            }
        }
        if (this.m_9236_().f_46443_ && ((Boolean)WitherStormModConfig.CLIENT.tractorBeamParticles.get()).booleanValue() && !this.isOnDistantRenderer()) {
            for (int i = 0; i < this.getTotalHeads(); ++i) {
                if (!this.tractorBeamActive(i) || this.isDeadOrPlayingDead() || this.getXBodyRot() != 0.0f) continue;
                Vec3 vec3 = this.getHeadPos(i);
                float x = this.getHeadXRot(i);
                float y = this.getHeadYRot(i);
                for (int amount = 0; amount < 5; ++amount) {
                    double randZ;
                    double randY;
                    Vec3 lookVec = this.getViewVector(x, y, this.f_19796_.m_188501_() * 200.0f);
                    Vec3 pos = vec3.m_82549_(lookVec).m_82520_(0.0, 5.5, 0.0);
                    double distanceFromHead = Math.sqrt(pos.m_82557_(vec3));
                    double distanceAllowed = distanceFromHead * 2.0 * 0.02;
                    double randX = this.f_19796_.m_188583_() * distanceAllowed;
                    Pair<Boolean, Integer> result = TractorBeamHelper.isInsideTractorBeam(pos = pos.m_82520_(randX, randY = this.f_19796_.m_188583_() * distanceAllowed, randZ = this.f_19796_.m_188583_() * distanceAllowed), this, 4.0);
                    if (!((Boolean)result.getFirst()).booleanValue()) continue;
                    Vec3 delta = pos.m_82546_(vec3).m_82541_().m_82490_(-0.8);
                    if (WitherStormMod.isAprilFools() && ((Boolean)WitherStormModConfig.CLIENT.aprilFools.get()).booleanValue()) {
                        this.m_9236_().m_6493_((ParticleOptions)ParticleTypes.f_123750_, true, pos.f_82479_, pos.f_82480_, pos.f_82481_, delta.f_82479_, delta.f_82480_, delta.f_82481_);
                        continue;
                    }
                    this.m_9236_().m_6493_((ParticleOptions)new TractorBeamParticleOptions(this.m_19879_(), (Integer)result.getSecond()), true, pos.f_82479_, pos.f_82480_, pos.f_82481_, delta.f_82479_, delta.f_82480_, delta.f_82481_);
                }
                if (this.getPhase() < 4) continue;
                float spread = 8.0f;
                for (int p = 0; p < 10; ++p) {
                    double cutoff = this.getTractorBeamCutoffDistance(i);
                    Vec3 end = vec3.m_82549_(this.getViewVector(x + this.f_19796_.m_188501_() * spread - spread / 2.0f, y + this.f_19796_.m_188501_() * spread - spread / 2.0f, (float)(cutoff == -1.0 ? 200.0 : cutoff + 30.0))).m_82520_(0.0, 5.5, 0.0);
                    BlockHitResult blockHitResult = this.m_9236_().m_45547_(new ClipContext(vec3, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null));
                    BlockPos hitPos = blockHitResult.m_82425_();
                    if (!WorldUtil.isBlockExposed(this.m_9236_(), hitPos)) continue;
                    BlockState hitState = this.m_9236_().m_8055_(hitPos);
                    Vec3 delta = Vec3.m_82512_((Vec3i)hitPos).m_82546_(vec3).m_82541_();
                    this.m_9236_().m_6493_((ParticleOptions)new BlockParticleOption(ParticleTypes.f_123794_, hitState).setPos(hitPos), true, (double)hitPos.m_123341_() + 0.5, (double)hitPos.m_123342_() + 1.0, (double)hitPos.m_123343_(), -delta.f_82479_, -delta.f_82480_, -delta.f_82481_);
                }
            }
        }
    }

    protected <T extends Entity> void doEntityPulling(T entity, Vec3 absorptionPoint, AABB absorptionBox) {
        WitherStormPullBehavior<T> behavior = null;
        if (WitherStormWorldInteractions.getInstance().hasPullBehavior(entity.m_6095_())) {
            behavior = WitherStormWorldInteractions.getInstance().getPullBehavior(entity.m_6095_());
        }
        if (behavior == null || behavior.canPullIn(entity, this)) {
            double speed = 0.5;
            if (behavior != null) {
                speed = behavior.getSpeed(entity, this, absorptionPoint);
            }
            Vec3 delta = absorptionPoint.m_82546_(entity.m_20182_());
            double distanceToStorm = delta.m_82553_();
            Vec3 defaultVelocity = delta.m_82541_().m_82542_(speed, speed, speed);
            if (distanceToStorm >= 320.0 || !WorldUtil.isLoaded((ServerLevel)this.m_9236_(), entity.m_20183_())) {
                entity.m_6021_(absorptionPoint.f_82479_, absorptionPoint.f_82480_, absorptionPoint.f_82481_);
            }
            if (behavior != null) {
                entity.m_20256_(behavior.pullEntity(entity, this, absorptionPoint, defaultVelocity, speed));
            } else {
                entity.m_20256_(defaultVelocity);
            }
            if (entity instanceof ItemEntity || behavior != null && behavior.doClientsideVelocityUpdates(entity, this)) {
                ServerLevel world = (ServerLevel)this.m_9236_();
                for (ServerPlayer player : world.m_6907_()) {
                    player.f_8906_.m_9829_((Packet)new ClientboundSetEntityMotionPacket(entity));
                }
            }
            if (absorptionBox.m_82390_(entity.m_20182_())) {
                BlockClusterEntity cluster;
                this.trackedEntities.stopTrackingEntity(entity);
                if (entity instanceof BlockClusterEntity && !(cluster = (BlockClusterEntity)entity).shouldntCountToConsumedEntities()) {
                    this.consumeEntity(cluster, cluster.getSize());
                }
                if (entity instanceof ItemEntity) {
                    ItemEntity itemEntity = (ItemEntity)entity;
                    this.consumeEntity((Entity)itemEntity, itemEntity.m_32055_().m_41613_());
                }
                if (!(entity instanceof BlockClusterEntity) && !(entity instanceof ItemEntity)) {
                    this.consumeEntity(entity, 1);
                }
                if (entity instanceof LivingEntity) {
                    TamableAnimal tamable;
                    LivingEntity living = (LivingEntity)entity;
                    if (living instanceof TamableAnimal && (tamable = (TamableAnimal)living).m_21805_() != null) {
                        this.storePet((Entity)tamable);
                    }
                    living.m_6469_(WitherStormModDamageTypes.witherStormAttackMob((LivingEntity)this), Float.MAX_VALUE);
                } else {
                    entity.m_146870_();
                }
            }
        }
    }

    public Optional<UltimateTargetManager> getUltimateTargetManager() {
        return this.targetManager;
    }

    public void checkConsumptionAmount() {
        if (!this.isDeadOrPlayingDead() && this.getConsumedEntities() > this.getConsumptionAmountForPhase(this.getPhase()) && this.getConsumedEntities() != this.lastConsumedEntities) {
            this.evolve(false);
        }
    }

    public boolean evolve(boolean force) {
        int nextPhase = this.getPhase() + 1;
        if (this.canEvolve(force) && !MinecraftForge.EVENT_BUS.post((Event)new WitherStormEvolveEvent(this, nextPhase))) {
            this.evolveToPhase(nextPhase);
            return true;
        }
        return false;
    }

    public void evolveToPhase(int phase) {
        this.setPhase(phase);
        if (this.evolutionProfiler.isProfiling()) {
            this.evolutionProfiler.onEvolve(this);
        }
        this.targetManager.ifPresent(manager -> {
            if (((Boolean)WitherStormModConfig.SERVER.chaseOnPhaseChange.get()).booleanValue() && this.getPhase() > 3) {
                manager.accelerate();
            }
        });
        if (this.shouldPlayGlobalSounds && this.getPhase() == 4) {
            this.playSoundToEveryone((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_EVOLVES.get(), 1.0f, 1.0f);
        }
    }

    public void m_6210_() {
        double x = this.m_20185_();
        double y = this.m_20186_();
        double z = this.m_20189_();
        super.m_6210_();
        this.m_6034_(x, y, z);
    }

    public void playSoundToEveryone(SoundEvent event, float volume, float pitch) {
        GlobalSoundMessage message = new GlobalSoundMessage(event, volume, pitch);
        if (((Boolean)WitherStormModConfig.SERVER.shouldPlayGlobalSoundsCrossDimensionally.get()).booleanValue()) {
            WitherStormModPacketHandlers.MAIN.send(PacketDistributor.ALL.noArg(), (Object)message);
        } else {
            WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> this.m_9236_().m_46472_()), (Object)message);
        }
    }

    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        if (!this.m_9236_().f_46443_) {
            WitherStormSyncHelper.removeWitherStorm(this);
        }
        if (!this.m_6084_()) {
            this.getPlayDeadManager().removePodium();
            this.trackedEntities.destroyAllClusters();
            this.trackedEntities.clearAndMakeAllFall();
        }
    }

    public void m_6667_(@NotNull DamageSource source) {
        super.m_6667_(source);
        if (!this.m_9236_().f_46443_) {
            this.spawnConsumedPets(Vec3.m_82539_((Vec3i)this.m_9236_().m_5452_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, this.m_20183_())));
            if (this.getPhase() > 3) {
                if (this.shouldPlaySoundLoop) {
                    this.m_9236_().m_6263_(null, this.m_20185_(), this.m_20186_(), this.m_20189_(), (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_DEATH.get(), SoundSource.HOSTILE, 20.0f, 1.0f);
                    Object message = new RemoveSoundLoopMessage(this);
                    WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> this.m_9236_().m_46472_()), message);
                }
                for (WitherStormHead head : this.headManager.getHeads()) {
                    head.doRoar(false);
                }
                this.trackedEntities.clearAndMakeAllFall();
            }
            List entities = this.m_9236_().m_45976_(LivingEntity.class, this.getSearchBox());
            for (LivingEntity living : entities) {
                living.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(WitherSicknessTracker::cure);
                if (!(living instanceof WitherSickened)) continue;
                WitherSickened sickened = (WitherSickened)living;
                sickened.cure((ServerLevel)this.m_9236_());
            }
            this.getSegmentsManager().ifPresent(SegmentsManager::killSegments);
        }
    }

    public void performRangedAttack(int head, LivingEntity entity) {
        this.performRangedAttack(head, entity.m_20185_(), entity.m_20186_() + (double)entity.m_20192_() * 0.5, entity.m_20189_(), head == 0 && this.f_19796_.m_188501_() < 0.001f);
    }

    public void performRangedAttack(int head, double x, double y, double z, boolean dangerous) {
        if (!this.m_20067_()) {
            this.m_9236_().m_5898_(null, 1024, this.m_20183_(), 0);
        }
        Vec3 pos = this.getHeadPos(head);
        double d3 = x - pos.f_82479_;
        double d4 = y - pos.f_82480_;
        double d5 = z - pos.f_82481_;
        WitherSkull witherskullentity = new WitherSkull(this.m_9236_(), (LivingEntity)this, d3, d4, d5);
        witherskullentity.m_5602_((Entity)this);
        if (dangerous) {
            witherskullentity.m_37629_(true);
        }
        witherskullentity.m_20343_(pos.f_82479_, pos.f_82480_, pos.f_82481_);
        this.m_9236_().m_7967_((Entity)witherskullentity);
    }

    public void spawnFlamingWitherSkull(int head, double x, double y, double z) {
        this.playSound((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_SHOOT.get(), head, Math.max(5.0f, this.m_6121_() - 5.0f), 1.0f);
        Vec3 pos = this.getHeadPos(head);
        double speed = (Double)WitherStormModConfig.SERVER.flamingSkullSpeedModifier.get();
        double d3 = (x - pos.f_82479_) * speed;
        double d4 = (y - pos.f_82480_) * speed;
        double d5 = (z - pos.f_82481_) * speed;
        FlamingWitherSkullEntity skull = new FlamingWitherSkullEntity(this.m_9236_(), (LivingEntity)this, d3, d4, d5);
        skull.m_5602_((Entity)this);
        skull.m_20343_(pos.f_82479_, pos.f_82480_, pos.f_82481_);
        this.m_9236_().m_7967_((Entity)skull);
    }

    public void spawnBlueFlamingWitherSkull(int head, double x, double y, double z) {
        this.playSound((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_SHOOT.get(), head, Math.max(5.0f, this.m_6121_() - 5.0f), 1.0f);
        Vec3 pos = this.getHeadPos(head);
        double speed = (Double)WitherStormModConfig.SERVER.flamingSkullSpeedModifier.get();
        double d3 = (x - pos.f_82479_) * speed;
        double d4 = (y - pos.f_82480_) * speed;
        double d5 = (z - pos.f_82481_) * speed;
        BlueFlamingWitherSkullEntity skull = new BlueFlamingWitherSkullEntity(this.m_9236_(), (LivingEntity)this, d3, d4, d5);
        skull.m_5602_((Entity)this);
        skull.m_20343_(pos.f_82479_, pos.f_82480_, pos.f_82481_);
        this.m_9236_().m_7967_((Entity)skull);
    }

    public double getDesiredSegmentX(int segment) {
        if (segment <= 0) {
            return this.m_20185_();
        }
        double staticX = SEGMENT_DESIRED_X[segment - 1];
        double staticZ = SEGMENT_DESIRED_Z[segment - 1];
        if (this.isPlayingDead()) {
            staticX = 45.0;
            staticZ = 0.0;
        }
        float f = (this.f_20883_ + (float)(180 * (segment - 1))) * ((float)Math.PI / 180);
        float offset = (float)Mth.m_14136_((double)staticZ, (double)staticX);
        float f1 = Mth.m_14089_((float)(f + offset));
        return this.m_20185_() + (double)f1 * Math.sqrt(staticX * staticX + staticZ * staticZ);
    }

    public double getDesiredSegmentY(int ignoredSegment) {
        double pos = this.m_20188_();
        if (this.isPlayingDead()) {
            pos = this.m_20191_().m_82399_().m_7098_() + 10.0;
        }
        return pos;
    }

    public double getDesiredSegmentZ(int segment) {
        if (segment <= 0) {
            return this.m_20189_();
        }
        double staticX = SEGMENT_DESIRED_X[segment - 1];
        double staticZ = SEGMENT_DESIRED_Z[segment - 1];
        if (this.isPlayingDead()) {
            staticX = 45.0;
            staticZ = 0.0;
        }
        float f = (this.f_20883_ + (float)(180 * (segment - 1))) * ((float)Math.PI / 180);
        float offset = (float)Mth.m_14136_((double)staticZ, (double)staticX);
        float f1 = Mth.m_14031_((float)(f + offset));
        return this.m_20189_() + (double)f1 * Math.sqrt(staticX * staticX + staticZ * staticZ);
    }

    public void m_5997_(double deltaX, double deltaY, double deltaZ) {
    }

    public void m_147240_(double strength, double x, double z) {
    }

    @Override
    public Vec3 getHeadPos(int head) {
        return this.headManager.getHead(head).getHeadPos();
    }

    public void m_7380_(@NotNull CompoundTag compound) {
        CompoundTag headsTag = new CompoundTag();
        for (WitherStormHead head : this.headManager.getHeads()) {
            headsTag.m_128365_(String.valueOf(head.getIndex()), (Tag)head.save());
        }
        compound.m_128365_("Heads", (Tag)headsTag);
        CompoundTag playDeadManagerNBT = new CompoundTag();
        PlayDeadManager playDeadManager = this.getPlayDeadManager();
        playDeadManagerNBT.m_128379_("PodiumPlaced", playDeadManager.isPodiumPlaced());
        if (playDeadManager.getPodiumPos() != null) {
            playDeadManagerNBT.m_128365_("PodiumPos", (Tag)NbtUtils.m_129224_((BlockPos)playDeadManager.getPodiumPos()));
        }
        playDeadManagerNBT.m_128405_("StateTicks", playDeadManager.getTicks());
        playDeadManagerNBT.m_128405_("State", playDeadManager.getState().ordinal());
        playDeadManagerNBT.m_128405_("RevivalTime", playDeadManager.getTicksSinceRevival());
        playDeadManagerNBT.m_128379_("RecentlyRevived", playDeadManager.hasRecentlyBeenRevived());
        playDeadManagerNBT.m_128405_("CommandBlockMissingTicks", playDeadManager.getTicksSinceCommandBlockMissing());
        compound.m_128365_("PlayDeadManager", (Tag)playDeadManagerNBT);
        compound.m_128405_("Phase", this.getPhase());
        super.m_7380_(compound);
        compound.m_128405_("Invul", this.getInvulnerableTicks());
        compound.m_128405_("StartingInvul", this.getStartingInvulnerableTicks());
        compound.m_128405_("ConsumedEntities", this.getConsumedEntities());
        compound.m_128379_("OtherHeadsDisabled", this.areOtherHeadsDisabled());
        this.targetManager.ifPresent(manager -> manager.save(compound));
        compound.m_128350_("YBodyRot", this.f_20883_);
        compound.m_128350_("XBodyRot", this.xBodyRot);
        compound.m_128379_("Mirrored", this.isMirrored());
        this.summoningManager.ifPresent(manager -> compound.m_128405_("SymbiontSummoningCooldown", manager.getSummoningDelay()));
        compound.m_128379_("ShouldShowHole", ((Boolean)this.f_19804_.m_135370_(SHOULD_SHOW_HOLE)).booleanValue());
        compound.m_128379_("Resummoned", this.resummoned);
        CompoundTag tag = new CompoundTag();
        this.evolutionProfiler.save(tag);
        compound.m_128365_("EvolutionProfiler", (Tag)tag);
        ListTag consumedPets = new ListTag();
        for (Map.Entry<UUID, CompoundTag> entry : this.consumedPets.entrySet()) {
            UUID id = entry.getKey();
            CompoundTag entity = entry.getValue();
            CompoundTag entityTag = new CompoundTag();
            entityTag.m_128362_("id", id);
            entityTag.m_128365_("Entity", (Tag)entity);
            consumedPets.add((Object)entityTag);
        }
        compound.m_128365_("ConsumedPets", (Tag)consumedPets);
        compound.m_128365_("TrackedEntities", (Tag)this.getTrackedEntities().save());
        compound.m_128365_("IgnoredTargets", (Tag)this.getIgnoredTargets().save());
        ListTag playingJukeboxes = new ListTag();
        for (BlockPos pos : this.playingJukeboxes) {
            CompoundTag entry = new CompoundTag();
            entry.m_128365_("Pos", (Tag)NbtUtils.m_129224_((BlockPos)pos));
            playingJukeboxes.add((Object)entry);
        }
        compound.m_128365_("PlayingJukeboxes", (Tag)playingJukeboxes);
        compound.m_128379_("IsConsumptionLocked", this.isLocked);
    }

    public void m_7378_(CompoundTag compound) {
        CompoundTag headsTag = compound.m_128469_("Heads");
        for (WitherStormHead head : this.headManager.getHeads()) {
            head.read(headsTag.m_128469_(String.valueOf(head.getIndex())));
        }
        CompoundTag playDeadManagerNBT = compound.m_128469_("PlayDeadManager");
        PlayDeadManager playDeadManager = this.getPlayDeadManager();
        playDeadManager.setPodiumPlaced(playDeadManagerNBT.m_128471_("PodiumPlaced"));
        if (playDeadManagerNBT.m_128441_("PodiumPos")) {
            playDeadManager.setPodiumPos(NbtUtils.m_129239_((CompoundTag)playDeadManagerNBT.m_128469_("PodiumPos")));
        }
        playDeadManager.setTickAmountAndO(playDeadManagerNBT.m_128451_("StateTicks"));
        int state = playDeadManagerNBT.m_128451_("State");
        if (state >= 0 && state < PlayDeadManager.State.values().length) {
            playDeadManager.setState(PlayDeadManager.State.values()[state]);
        }
        playDeadManager.setTicksSinceRevival(playDeadManagerNBT.m_128451_("RevivalTime"));
        playDeadManager.setRecentlyRevived(playDeadManagerNBT.m_128471_("RecentlyRevived"));
        playDeadManager.setTicksSinceCommandBlockMissing(playDeadManagerNBT.m_128451_("CommandBlockMissingTicks"));
        if (compound.m_128441_("ConsumedEntities")) {
            this.setPhase(compound.m_128451_("Phase"), compound.m_128451_("ConsumedEntities"));
        } else {
            this.setPhase(compound.m_128451_("Phase"));
        }
        super.m_7378_(compound);
        this.setInvulnerableTicks(compound.m_128451_("Invul"));
        this.setStartingInvulnerableTicks(compound.m_128451_("StartingInvul"));
        if (this.m_8077_()) {
            this.bossEvent.ifPresent(bossEvent -> bossEvent.m_6456_(this.m_5446_()));
        }
        this.setOtherHeadsDisabled(compound.m_128471_("OtherHeadsDisabled"));
        this.targetManager.ifPresent(manager -> manager.read(compound));
        this.m_5618_(compound.m_128457_("YBodyRot"));
        this.setXBodyRot(compound.m_128457_("XBodyRot"));
        this.setMirrored(compound.m_128471_("Mirrored"));
        this.getBossInfo().ifPresent(info -> info.m_8321_(!this.isPlayingDead()));
        this.summoningManager.ifPresent(manager -> manager.setSummoningDelay(compound.m_128451_("SymbiontSummoningCooldown")));
        this.f_19804_.m_135381_(SHOULD_SHOW_HOLE, (Object)compound.m_128471_("ShouldShowHole"));
        this.resummoned = compound.m_128471_("Resummoned");
        CompoundTag tag = compound.m_128469_("EvolutionProfiler");
        this.evolutionProfiler.read(tag);
        this.consumedPets.clear();
        ListTag consumedPets = compound.m_128437_("ConsumedPets", 10);
        for (int i = 0; i < consumedPets.size(); ++i) {
            CompoundTag entityTag = consumedPets.m_128728_(i);
            this.consumedPets.put(entityTag.m_128342_("id"), entityTag.m_128469_("Entity"));
        }
        this.getTrackedEntities().read(compound.m_128469_("TrackedEntities"));
        this.getIgnoredTargets().read(compound.m_128469_("IgnoredEntities"));
        this.playingJukeboxes.clear();
        ListTag playingJukeboxes = compound.m_128437_("PlayingJukeboxes", 10);
        for (int i = 0; i < playingJukeboxes.size(); ++i) {
            CompoundTag entry = playingJukeboxes.m_128728_(i);
            this.playingJukeboxes.add(NbtUtils.m_129239_((CompoundTag)entry.m_128469_("Pos")));
        }
        this.isLocked = compound.m_128471_("IsConsumptionLocked");
    }

    public void m_6593_(Component component) {
        super.m_6593_(component);
        this.bossEvent.ifPresent(bossEvent -> bossEvent.m_6456_(this.m_5446_()));
    }

    public void setInvulnerableTicks(int ticks) {
        this.f_19804_.m_135381_(INVULNERABLE, (Object)ticks);
    }

    public int phaseRadiusMultiplier(int phase) {
        double multiplier = switch (phase) {
            case 0 -> 1.0;
            case 1, 2 -> 1.25;
            case 3 -> 1.5;
            case 4 -> 1.75;
            case 5 -> 2.0;
            case 6 -> 2.25;
            case 7 -> 2.5;
            default -> throw new IllegalStateException("Unexpected Wither Storm Phase value: " + phase);
        };
        return (int)multiplier;
    }

    public int phaseRadiusMultiplierNature(int phase) {
        double multiplier = switch (phase) {
            case 0 -> 1.0;
            case 1, 2 -> 1.125;
            case 3 -> 1.25;
            case 4 -> 1.5;
            case 5, 6 -> 1.75;
            case 7 -> 2.25;
            default -> throw new IllegalStateException("Erm... Unexpected Wither Storm Phase value: " + phase);
        };
        return (int)multiplier;
    }

    public int getInvulnerableTicks() {
        return (Integer)this.f_19804_.m_135370_(INVULNERABLE);
    }

    public void setStartingInvulnerableTicks(int ticks) {
        this.f_19804_.m_135381_(STARTING_INVULNERABLE, (Object)ticks);
    }

    public int getStartingInvulnerableTicks() {
        return (Integer)this.f_19804_.m_135370_(STARTING_INVULNERABLE);
    }

    public int getPhase() {
        return (Integer)this.f_19804_.m_135370_(PHASE);
    }

    public int getConsumptionAmountForPhase(int phase) {
        int consumptionAmount = switch (phase) {
            case 0 -> 100;
            case 1 -> 400;
            case 2 -> 1200;
            case 3 -> 18800;
            case 4 -> 195000;
            case 5 -> 351400;
            case 6 -> 580800;
            case 7 -> 2125000;
            default -> 0;
        };
        return this.adjustAmountForEvolutionSpeed(consumptionAmount);
    }

    public int adjustAmountForEvolutionSpeed(int consumptionAmount) {
        return (int)((double)consumptionAmount * this.getEvolutionSpeedModifier());
    }

    public double getEvolutionSpeedModifier() {
        double modifier = this.attributeOrConfigValue((Attribute)WitherStormModAttributes.EVOLUTION_SPEED.get(), WitherStormModConfig.SERVER.evolutionAttributeModifier);
        WitherStormModifyEvolutionSpeedEvent event = new WitherStormModifyEvolutionSpeedEvent(this, modifier);
        MinecraftForge.EVENT_BUS.post((Event)event);
        return event.getOriginalEvolutionSpeedModifier();
    }

    public boolean setPhase(int phase) {
        return this.setPhase(phase, this.getConsumptionAmountForPhase(phase - 1));
    }

    public boolean setPhase(int phase, int consumedEntities) {
        if (phase >= 0 && phase <= 7) {
            MinecraftForge.EVENT_BUS.post((Event)new WitherStormChangePhaseEvent(this, phase));
            this.f_19804_.m_135381_(PHASE, (Object)phase);
            this.clusterRadius = (int)Math.max(1.0f, (float)this.getPhase() * 0.75f);
            this.entityConsumptionRadius = this.getPhase() > 3 ? 80 : 12 + Math.round((float)this.getConsumedEntities() * 0.00445f);
            this.setConsumedEntities(consumedEntities);
            this.m_20090_();
            this.m_6210_();
            if (this.isAddedToWorld()) {
                if (phase < 6) {
                    this.removeSegments();
                } else {
                    this.readdSegments();
                }
            }
            if (phase == 6) {
                if (this.getConsumedEntities() < this.getSubPhaseRequirement(phase)) {
                    this.setOtherHeadsDisabled(true);
                }
            } else {
                this.setOtherHeadsDisabled(false);
            }
            this.updateSections();
            if (!this.m_9236_().f_46443_) {
                if (this.shouldPlaySoundLoops()) {
                    message = new CreateLoopingSoundMessage(this);
                    WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> this.m_9236_().m_46472_()), (Object)message);
                } else {
                    message = new RemoveSoundLoopMessage(this);
                    WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> this.m_9236_().m_46472_()), (Object)message);
                }
            }
            this.headManager.update(phase);
            AttributeInstance health = this.m_21051_(Attributes.f_22276_);
            AttributeInstance armor = this.m_21051_(Attributes.f_22284_);
            if (!this.m_9236_().f_46443_) {
                if (phase < 4) {
                    health.m_22127_(HEALTH_MODIFIER_UUID);
                    armor.m_22127_(ARMOR_MODIFIER_UUID);
                    this.currentFlyingHeight = 10.0;
                } else {
                    AttributeModifier healthModifier = new AttributeModifier(HEALTH_MODIFIER_UUID, "Phase health modifier", 624.0, AttributeModifier.Operation.ADDITION);
                    assert (health != null);
                    if (!health.m_22109_(healthModifier)) {
                        health.m_22125_(healthModifier);
                    }
                    AttributeModifier armorModifier = new AttributeModifier(ARMOR_MODIFIER_UUID, "Phase armor modifier", (double)((phase + 1) * 2), AttributeModifier.Operation.ADDITION);
                    assert (armor != null);
                    if (!armor.m_22109_(armorModifier)) {
                        armor.m_22125_(armorModifier);
                    }
                    this.currentFlyingHeight = ((Integer)WitherStormModConfig.SERVER.flyingHeight.get()).intValue();
                }
            }
            this.segments.ifPresent(manager -> {
                for (WitherStormSegmentEntity entity : manager.getSegments()) {
                    if (entity == null) continue;
                    entity.setPhase(this.getPhase());
                }
            });
            return true;
        }
        return false;
    }

    protected void updateSections() {
        if (this.getPhase() == 5) {
            if (this.getConsumptionAmountForPhase(this.getPhase()) <= this.getConsumedEntities()) {
                this.sections[0].setSize(60.0f, 35.0f);
                this.sections[1].setSize(60.0f, 35.0f);
                this.sections[0].setOffset(24.0, 28.0, 0.0);
                this.sections[1].setOffset(-24.0, 28.0, 0.0);
                this.fallingSection.setSize(50.0f, 70.0f);
                this.fallingSection.setOffset(0.0, 60.0, 30.0);
            } else {
                this.sections[0].setSize(30.0f, 15.0f);
                this.sections[1].setSize(30.0f, 15.0f);
                this.sections[0].setOffset(13.0, 28.0, 0.0);
                this.sections[1].setOffset(-13.0, 28.0, 0.0);
                this.fallingSection.setSize(30.0f, 35.0f);
                this.fallingSection.setOffset(0.0, 35.0, 15.0);
            }
        } else if (this.getPhase() == 6) {
            this.sections[0].setSize(60.0f, 35.0f);
            this.sections[1].setSize(60.0f, 35.0f);
            this.sections[0].setOffset(24.0, 28.0, 0.0);
            this.sections[1].setOffset(-24.0, 28.0, 0.0);
            this.fallingSection.setSize(50.0f, 70.0f);
            this.fallingSection.setOffset(0.0, 60.0, 30.0);
        } else if (this.getPhase() == 7) {
            this.sections[0].setSize(95.0f, 45.0f);
            this.sections[1].setSize(95.0f, 45.0f);
            this.sections[0].setOffset(28.0, 28.0, 0.0);
            this.sections[1].setOffset(-28.0, 28.0, 0.0);
            this.fallingSection.setSize(60.0f, 85.0f);
            this.fallingSection.setOffset(0.0, 80.0, 30.0);
        }
    }

    public void m_7350_(@NotNull EntityDataAccessor<?> parameter) {
        super.m_7350_(parameter);
        if (parameter.equals(PHASE)) {
            this.updateSections();
            this.m_6210_();
            this.headManager.update(this.getPhase());
        } else if (parameter.equals(CONSUMED_ENTITIES)) {
            this.updateSections();
        }
    }

    public void makeInvulnerable() {
        this.setInvulnerableTicks((Integer)WitherStormModConfig.SERVER.invulnerabilityTime.get() * 20);
        this.bossEvent.ifPresent(event -> event.m_142711_(0.0f));
        this.m_21153_(1.0f);
    }

    public void m_7601_(@NotNull BlockState blockState, @NotNull Vec3 vector3d) {
    }

    public void m_6457_(@NotNull ServerPlayer player) {
        super.m_6457_(player);
        this.playersTracking.add(player);
        CommandBlockEntity commandBlock = this.getBowelsCommandBlock();
        if (commandBlock != null) {
            commandBlock.getOutsideBossBarViewers().add(player);
        }
    }

    @Override
    public SoundEvent getBossTheme() {
        if (this.hasRecentlyBeenRevived()) {
            return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_REVIVAL_THEME.get();
        }
        if (this.getPhase() == 5 && this.getConsumptionAmountForPhase(5) < this.getConsumedEntities()) {
            return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_FORMIDIBOMB_THEME.get();
        }
        if (this.isBeingTornApart()) {
            return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_BOWELS_EXPOSED_THEME.get();
        }
        return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_BOSS_THEME.get();
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public boolean shouldPlayBossTheme() {
        boolean commandBlockOverride = false;
        CommandBlockEntity commandBlock = this.getPlayDeadManager().getCommandBlock();
        if (commandBlock != null && commandBlock.m_6084_()) {
            commandBlockOverride = commandBlock.getState().shouldShowOwnerBossBar();
        }
        return BossThemeEntity.super.shouldPlayBossTheme() && this.shouldPlaySoundLoop && !this.m_21525_() && !this.m_20067_() && (!this.isDeadOrPlayingDead() || commandBlockOverride);
    }

    @Override
    public boolean checkConfig() {
        return (Boolean)WitherStormModConfig.CLIENT.playWitherStormTheme.get();
    }

    @Override
    public boolean smartBossMusic() {
        return (Boolean)WitherStormModConfig.SERVER.smartBossbar.get();
    }

    @Override
    public boolean isStillAlive() {
        return this.m_6084_();
    }

    @Override
    public Vec3 getPosition() {
        return this.m_20182_();
    }

    public void m_6452_(@NotNull ServerPlayer player) {
        super.m_6452_(player);
        this.playersTracking.remove(player);
        this.bossEvent.ifPresent(bossEvent -> bossEvent.m_6539_(player));
        CommandBlockEntity commandBlock = this.getBowelsCommandBlock();
        if (commandBlock != null) {
            commandBlock.removeOutsideBossBarViewer(player);
        }
    }

    public void m_6043_() {
        this.f_20891_ = 0;
    }

    public boolean m_142535_(float p_225503_1_, float p_225503_2_, @NotNull DamageSource source) {
        int damage = this.m_5639_(p_225503_1_, p_225503_2_);
        if (damage > 15) {
            this.onBigFall();
        }
        return false;
    }

    public void onBigFall() {
        if (this.getPhase() > 3) {
            this.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_THUMP.get(), this.m_6121_() + 3.0f, 1.0f);
            this.shake(30.0f, 12.0f);
        }
    }

    public boolean m_147207_(@NotNull MobEffectInstance effect, @Nullable Entity entity) {
        return false;
    }

    @NotNull
    public MobType m_6336_() {
        return WitherStormModMobTypes.SICKENED;
    }

    protected boolean m_7341_(@NotNull Entity entity) {
        return false;
    }

    public boolean m_6072_() {
        return false;
    }

    public boolean m_7301_(@NotNull MobEffectInstance effect) {
        return false;
    }

    public boolean m_7090_() {
        return this.getInvulnerableTicks() > 900 && this.getPhase() < 4;
    }

    public boolean m_5789_() {
        return this.shouldDoNothing() && this.getPhase() < 4;
    }

    public boolean m_6469_(DamageSource source, float floatIn) {
        if (source.m_269533_(DamageTypeTags.f_268738_)) {
            return super.m_6469_(source, floatIn);
        }
        if (this.m_6673_(source)) {
            return false;
        }
        if (!source.m_276093_(DamageTypes.f_268722_) && !(source.m_7639_() instanceof WitherStormEntity)) {
            Entity entity;
            if (this.shouldDoNothing() && !source.m_276093_(DamageTypes.f_268724_)) {
                return false;
            }
            if (this.getPhase() > 3 && this.isCompletelyInvulnerable()) {
                return false;
            }
            if (this.m_7090_() && (entity = source.m_7640_()) instanceof AbstractArrow) {
                return false;
            }
            Entity entity1 = source.m_7639_();
            if (!(entity1 instanceof Player) && entity1 instanceof LivingEntity && ((LivingEntity)entity1).m_6336_() == this.m_6336_()) {
                return false;
            }
            if (this.destroyBlocksTick <= 0) {
                this.destroyBlocksTick = 20;
            }
            for (AdditionalHead head : this.headManager.getOtherHeads()) {
                head.idleHeadUpdates += 3;
            }
            return super.m_6469_(source, floatIn);
        }
        return false;
    }

    public int getConsumedEntities() {
        return (Integer)this.f_19804_.m_135370_(CONSUMED_ENTITIES);
    }

    public void setConsumedEntities(int newAmount) {
        int amount;
        this.f_19804_.m_135381_(CONSUMED_ENTITIES, (Object)newAmount);
        this.updateSections();
        if (this.getPhase() == 6 && newAmount > (amount = this.getSubPhaseRequirement(this.getPhase())) && this.areOtherHeadsDisabled()) {
            this.setOtherHeadsDisabled(false);
            for (AdditionalHead head : this.headManager.getOtherHeads()) {
                head.setNextRoarTick(this.f_19797_ + this.f_19796_.m_188503_(30));
            }
            this.getSegmentsManager().ifPresent(manager -> {
                for (WitherStormSegmentEntity entity : manager.getSegments()) {
                    if (entity == null) continue;
                    for (AdditionalHead head : entity.headManager.getOtherHeads()) {
                        head.setNextRoarTick(entity.f_19797_ + entity.f_19796_.m_188503_(30));
                    }
                }
            });
        }
    }

    public void consumeEntity(@Nullable Entity entity, int amount) {
        WitherStormConsumeEvent event = new WitherStormConsumeEvent(this, entity, amount);
        if (!MinecraftForge.EVENT_BUS.post((Event)event)) {
            amount = event.getConsumedAmount();
            this.addToConsumedEntities(amount);
        }
    }

    public void addToConsumedEntities(int amount) {
        if (!this.isConsumptionLocked()) {
            this.setConsumedEntities(this.getConsumedEntities() + amount);
        }
    }

    public int m_8132_() {
        return this.getPhase() > 3 ? 180 : super.m_8132_();
    }

    @Nullable
    public LivingEntity getUltimateTarget() {
        return this.targetManager.map(UltimateTargetManager::getUltimateTarget).orElse(null);
    }

    @Nullable
    public Vec3 getUltimateTargetPos() {
        return this.targetManager.map(manager -> {
            if (manager.isDistracted()) {
                assert (manager.getDistractedPos() != null);
                return Vec3.m_82512_((Vec3i)manager.getDistractedPos());
            }
            if (manager.isRandomStrolling()) {
                assert (manager.getRandomStrollPos() != null);
                return Vec3.m_82512_((Vec3i)manager.getRandomStrollPos());
            }
            return manager.getUltimateTargetPos();
        }).orElse(null);
    }

    public Section[] getSections() {
        if (this.xBodyRot != 0.0f) {
            return new Section[]{this.fallingSection, this.sections[2]};
        }
        return this.sections;
    }

    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (!this.m_9236_().f_46443_) {
            WitherStormSyncHelper.sendWitherStormToClient(this);
            this.bowelsInstance.ifPresent(BowelsInstanceManager::loadInstance);
        }
    }

    public void findSegments() {
        if (!this.m_9236_().f_46443_) {
            this.getSegmentsManager().ifPresent(manager -> {
                ServerLevel world = (ServerLevel)this.m_9236_();
                manager.findSegments(world);
            });
        }
    }

    public void createSegments() {
        this.getSegmentsManager().ifPresent(SegmentsManager::createSegments);
    }

    public void removeSegments() {
        this.getSegmentsManager().ifPresent(SegmentsManager::removeSegments);
    }

    public void readdSegments() {
        this.getSegmentsManager().ifPresent(SegmentsManager::readdSegments);
    }

    public Optional<SegmentsManager> getSegmentsManager() {
        return this.segments;
    }

    public void addSegments() {
        this.getSegmentsManager().ifPresent(SegmentsManager::addSegments);
    }

    @Override
    public boolean areOtherHeadsDisabled() {
        return this.headManager.areOtherHeadsDisabled();
    }

    public void setOtherHeadsDisabled(boolean value) {
        this.headManager.setOtherHeadsDisabled(value);
    }

    public void setMirrored(boolean mirrored) {
        this.f_19804_.m_135381_(MIRRORED, (Object)mirrored);
    }

    public boolean isMirrored() {
        return (Boolean)this.f_19804_.m_135370_(MIRRORED);
    }

    public void createDebrisClusters(boolean hidden) {
        DebrisCluster cluster;
        int i;
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i2 = 0; i2 < 100; ++i2) {
            DebrisCluster cluster2 = new DebrisCluster(this.f_19796_.m_188501_() * 360.0f, this.f_19796_.m_188501_() * 100.0f, 40.0f + this.f_19796_.m_188501_() * 160.0f, this.f_19796_.m_188501_() * 2.0f - 1.0f, 1.0f);
            cluster2.randomize(this.f_19796_, 15, 18.0f);
            cluster2.setDisabled(hidden);
            cluster2.determineRenderPhase();
            builder.add((Object)cluster2);
        }
        ImmutableList.Builder hunchbackClustersBuilder = ImmutableList.builder();
        for (i = 0; i < 30; ++i) {
            cluster = new DebrisCluster(this.f_19796_.m_188501_() * 360.0f, this.f_19796_.m_188501_() * 3.0f, this.f_19796_.m_188501_() * 0.75f + 1.25f, this.f_19796_.m_188501_() * 8.0f - 4.0f, 0.125f);
            cluster.randomize(this.f_19796_, 1, 0.1f);
            cluster.setDisabled(hidden);
            cluster.setGlowing(false);
            cluster.setRenderPhase(1);
            hunchbackClustersBuilder.add((Object)cluster);
        }
        for (i = 0; i < 20; ++i) {
            cluster = new DebrisCluster(this.f_19796_.m_188501_() * 360.0f, this.f_19796_.m_188501_() * 4.0f, this.f_19796_.m_188501_() * 5.0f + 2.0f, this.f_19796_.m_188501_() * 12.0f - 6.0f, 0.25f);
            cluster.randomize(this.f_19796_, 1, 0.5f);
            cluster.setDisabled(hidden);
            cluster.setGlowing(false);
            cluster.setRenderPhase(2);
            hunchbackClustersBuilder.add((Object)cluster);
        }
        for (i = 0; i < 5; ++i) {
            cluster = new DebrisCluster(this.f_19796_.m_188501_() * 360.0f, this.f_19796_.m_188501_() * 4.0f, this.f_19796_.m_188501_() * 1.25f + 3.75f, this.f_19796_.m_188501_() * 6.0f - 3.0f, 0.125f);
            cluster.randomize(this.f_19796_, 3, 0.5f);
            cluster.setGlowing(true);
            cluster.setDisabled(hidden);
            cluster.setRenderPhase(2);
            hunchbackClustersBuilder.add((Object)cluster);
        }
        for (i = 0; i < 25; ++i) {
            cluster = new DebrisCluster(this.f_19796_.m_188501_() * 360.0f, this.f_19796_.m_188501_() * 20.0f - 10.0f, this.f_19796_.m_188501_() * 20.0f + 5.0f, this.f_19796_.m_188501_() * 6.0f - 3.0f, 0.25f);
            cluster.randomize(this.f_19796_, 4, 2.5f);
            cluster.setGlowing(false);
            cluster.setDisabled(hidden);
            cluster.setRenderPhase(3);
            hunchbackClustersBuilder.add((Object)cluster);
        }
        for (i = 0; i < 5; ++i) {
            cluster = new DebrisCluster(this.f_19796_.m_188501_() * 360.0f, this.f_19796_.m_188501_() * 15.0f - 5.0f, this.f_19796_.m_188501_() * 7.5f + 20.0f, this.f_19796_.m_188501_() * 2.0f - 1.0f, 0.125f);
            cluster.randomize(this.f_19796_, 8, 1.0f);
            cluster.setGlowing(true);
            cluster.setDisabled(hidden);
            cluster.setRenderPhase(3);
            hunchbackClustersBuilder.add((Object)cluster);
        }
        for (i = 0; i < 15; ++i) {
            cluster = new DebrisCluster(this.f_19796_.m_188501_() * 360.0f, this.f_19796_.m_188501_() * 10.0f - 5.0f, this.f_19796_.m_188501_() * 24.0f + 6.0f, this.f_19796_.m_188501_() * 5.0f - 2.5f, 1.0f);
            cluster.randomize(this.f_19796_, 1, 0.5f);
            cluster.setDisabled(hidden);
            cluster.setGlowing(false);
            cluster.setRenderPhase(3);
            hunchbackClustersBuilder.add((Object)cluster);
        }
        this.hunchbackDebrisClusters = hunchbackClustersBuilder.build();
        this.debrisClusters = builder.build();
    }

    public void createDebrisRings(boolean hidden) {
        this.debrisRings = ImmutableList.of((Object)new DebrisRingSettings(16, 100.0f, 60.0f, 30.0f, 25.0f, 0.02f, true, 4, hidden), (Object)new DebrisRingSettings(24, 160.0f, 120.0f, 10.0f, 50.0f, 0.005f, false, 4, hidden), (Object)new DebrisRingSettings(24, 180.0f, 100.0f, 30.0f, 60.0f, 0.001f, true, 4, hidden), (Object)new DebrisRingSettings(24, 130.0f, 50.0f, 80.0f, 10.0f, 0.008f, false, 4, hidden), (Object)new DebrisRingSettings(36, 240.0f, 200.0f, 0.0f, 40.0f, 0.002f, true, 6, hidden), (Object)new DebrisRingSettings(36, 250.0f, 210.0f, -30.0f, 10.0f, 0.001f, true, 6, hidden));
    }

    public List<DebrisRingSettings> getDebrisRings() {
        return this.debrisRings;
    }

    public List<DebrisCluster> getDebrisClusters() {
        return this.getPhase() > 3 ? this.debrisClusters : this.hunchbackDebrisClusters;
    }

    public int getDeathTime() {
        return this.witherStormDeathTime;
    }

    public void m_5802_(@NotNull BlockPos pos) {
    }

    public boolean m_6573_(@NotNull Player player) {
        return false;
    }

    public final boolean isOnDistantRenderer() {
        return this.isOnDistantRenderer;
    }

    public final void setOnDistantRenderer() {
        this.isOnDistantRenderer = true;
    }

    public boolean isEntityNearby(Entity entity) {
        return this.getSearchBox().m_82390_(entity.m_20182_());
    }

    public AABB getSearchBox() {
        double range = this.getPhase() > 3 ? this.m_21133_(Attributes.f_22277_) : this.m_21133_((Attribute)WitherStormModAttributes.HUNCHBACK_FOLLOW_RANGE.get());
        return this.getPhase() > 3 ? this.m_20191_().m_82377_(range, range + 255.0, range) : this.m_20191_().m_82377_(range, range * 2.0, range);
    }

    public void playSound(SoundEvent event, int head, float volume, float pitch) {
        Vec3 pos = this.getHeadPos(head);
        if (!this.m_20067_()) {
            this.m_9236_().m_6263_(null, pos.f_82479_, pos.f_82480_, pos.f_82481_, event, this.m_5720_(), volume, pitch);
        }
    }

    @Override
    public float getTentacleAnimation(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.tentacleTickCountO, (float)this.tentacleTickCount);
    }

    @Override
    public float getFadeAnimation(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.onGroundAnimationO, (float)this.onGroundAnimation);
    }

    @Override
    public float getMouthAnimation(int head, float partialTicks) {
        return this.headManager.getHead(head).getMouthAnimation(partialTicks);
    }

    @Override
    public float getBrokenJawAnimation(int head, float partialTicks) {
        return this.headManager.getHead(head).getBrokenJawAnimation(partialTicks);
    }

    @Override
    public float getFadeAnimation() {
        return this.onGroundAnimation;
    }

    public boolean isBeingTornApart() {
        if (((Boolean)WitherStormModConfig.SERVER.shouldShowHole.get()).booleanValue() && this.getPhase() >= 7) {
            if (this.getConsumedEntities() >= this.getConsumptionAmountForPhase(7)) {
                return true;
            }
            return (Boolean)this.f_19804_.m_135370_(SHOULD_SHOW_HOLE);
        }
        return false;
    }

    public void setShouldShowHole(boolean flag) {
        this.f_19804_.m_135381_(SHOULD_SHOW_HOLE, (Object)flag);
    }

    public PlayDeadManager getPlayDeadManager() {
        return this.playDeadManager;
    }

    public boolean shouldDoNothing() {
        if (this.getInvulnerableTicks() > 0) {
            return true;
        }
        return this.getPlayDeadManager().getState().disablesAi();
    }

    @Override
    public boolean isDeadOrPlayingDead() {
        if (this.m_21224_()) {
            return true;
        }
        return this.getPlayDeadManager().getState().disablesAi();
    }

    @Override
    public boolean isPlayingDead() {
        return this.getPlayDeadManager().getState().disablesAi();
    }

    public boolean canEvolve(boolean force) {
        if (!force) {
            return this.evolutionProfiler.isProfiling() || this.getPhase() < 5 || this.getPhase() > 5 && this.getPhase() < 7;
        }
        return this.getPhase() < 7;
    }

    public int getSubPhaseRequirement(int phase) {
        int amount = this.getConsumptionAmountForPhase(phase - 1);
        return amount + (this.getConsumptionAmountForPhase(phase) - amount) / 2;
    }

    public boolean shouldDoCustomMovement() {
        PlayDeadManager.State state = this.getPlayDeadManager().getState();
        if (state == PlayDeadManager.State.FALLING && this.getPlayDeadManager().getTicks() > 300) {
            return false;
        }
        return this.shouldDoCustomMovement;
    }

    public PersistentTrackedEntities getTrackedEntities() {
        return this.trackedEntities;
    }

    public Optional<ServerBossEvent> getBossInfo() {
        return this.bossEvent;
    }

    public boolean canFallOnBack() {
        return true;
    }

    @Override
    public void writeData(FriendlyByteBuf buffer) {
        this.headManager.packHeadRotations().toPacket(buffer);
        buffer.writeBoolean(this.m_20096_());
        buffer.writeByte((int)((byte)Mth.m_14143_((float)(this.f_20883_ * 256.0f / 360.0f))));
        buffer.writeByte((int)((byte)Mth.m_14143_((float)(this.xBodyRot * 256.0f / 360.0f))));
    }

    @Override
    public void readData(FriendlyByteBuf buffer) {
        this.headManager.updateHeadsFromPacked(HeadManager.PackedHeadRots.fromPacket(buffer));
        this.m_6853_(buffer.readBoolean());
        this.f_20884_ = this.f_20883_ = (float)(buffer.readByte() * 360) / 256.0f;
        this.xBodyRotO = this.xBodyRot = (float)(buffer.readByte() * 360) / 256.0f;
    }

    public boolean m_6094_() {
        return false;
    }

    public boolean m_6063_() {
        return false;
    }

    public void setXBodyRot(float rot) {
        this.xBodyRot = rot;
    }

    protected void m_6138_() {
        if (this.canFallOnBack() && this.getPlayDeadManager().getState() == PlayDeadManager.State.PLAYING_DEAD) {
            return;
        }
        super.m_6138_();
    }

    public void onFallOnBack() {
        this.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_THUMP.get(), this.m_6121_() + 3.0f, 1.0f);
        this.shake(30.0f, 12.0f);
    }

    public boolean isOnBack() {
        return this.xBodyRot >= 90.0f;
    }

    public void shake(float duration, float power) {
        if (!this.m_9236_().f_46443_) {
            ShakeScreenMessage message = new ShakeScreenMessage(duration, power);
            WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), (Object)message);
        }
    }

    public void setFormidibomb(FormidibombEntity entity) {
        this.getPlayDeadManager().setFormidibomb(entity);
    }

    @Nullable
    public FormidibombEntity getFormidibomb() {
        return this.getPlayDeadManager().getFormidibomb();
    }

    public boolean canBeFormidibombed(boolean isExplosion) {
        if (this.shouldIgnoreFormidibomb) {
            return false;
        }
        if (this.getPhase() < 5) {
            return false;
        }
        if (this.getPhase() > 6 && this.isBeingTornApart()) {
            return false;
        }
        if (this.getFormidibomb() == null) {
            return false;
        }
        if (((Boolean)WitherStormModConfig.SERVER.endOfPhaseFiveBombableExclusively.get()).booleanValue() && this.getPhaseProgress() < 1.0f) {
            return false;
        }
        FormidibombEntity formidibomb = this.getFormidibomb();
        if (!isExplosion && !formidibomb.m_6084_()) {
            return false;
        }
        return !((float)formidibomb.getFuseLife() > 600.0f + this.m_20270_((Entity)formidibomb));
    }

    public void explode() {
        if (this.getPhase() > 3 && !this.isDeadOrPlayingDead()) {
            this.getPlayDeadManager().explode();
        }
    }

    public void reviveFromPlayingDead() {
        if (this.isPlayingDead()) {
            this.getPlayDeadManager().revive();
        }
    }

    public boolean isReviving() {
        return this.getPlayDeadManager().getState() == PlayDeadManager.State.REVIVING;
    }

    public boolean m_6128_() {
        return true;
    }

    public boolean isAttractingFormidibomb() {
        boolean flag = false;
        Stream goals = this.f_21345_.m_25386_();
        for (WrappedGoal prioritizedGoal : (WrappedGoal[])goals.toArray(WrappedGoal[]::new)) {
            LookAtFormidibombGoal goal;
            Goal goal2 = prioritizedGoal.m_26015_();
            if (!(goal2 instanceof LookAtFormidibombGoal) || !(goal = (LookAtFormidibombGoal)goal2).hasTarget()) continue;
            flag = true;
        }
        return flag;
    }

    public boolean isNearbyTickingFormidibomb() {
        FormidibombEntity formidibomb = this.getFormidibomb();
        return formidibomb != null && formidibomb.m_6084_() && formidibomb.getStartFuse() > 0 && formidibomb.getFuseLife() <= 800;
    }

    public boolean targetApplicable(LivingEntity entity, int head, TargetingConditions conditions) {
        if (entity != this) {
            PlayerWitherStormData data;
            if (!conditions.m_26885_((LivingEntity)this, entity)) {
                return false;
            }
            if (!this.headManager.getHead(head).canSee((Entity)entity)) {
                return false;
            }
            if (this.ignoredTargets.shouldIgnoreEntity((Entity)entity)) {
                return false;
            }
            if (this.trackedEntities.contains((Entity)entity)) {
                return false;
            }
            if (entity instanceof Player) {
                Player player = (Player)entity;
                if (player.m_21205_().m_41720_() instanceof ShieldItem ? player.m_6117_() : player.m_21206_().m_41720_() instanceof ShieldItem && player.m_6117_()) {
                    return false;
                }
                if (this.hasRecentlyBeenRevived()) {
                    return false;
                }
            }
            if (this.getPhase() > 3 && entity.m_20145_()) {
                return false;
            }
            if (this.getPhase() > 3 && (this.targetInUseBySegment((Entity)entity) || this.alreadyATarget((Entity)entity, head != 0))) {
                return false;
            }
            if (this.isInsideOtherTractorBeam(entity, head)) {
                return false;
            }
            List entities = this.m_9236_().m_45976_(LivingEntity.class, this.m_20191_().m_82377_(10.0, 255.0, 10.0));
            for (LivingEntity entityBelow : entities) {
                if (!entityBelow.m_7306_((Entity)entity)) continue;
                return false;
            }
            if (this.getPhase() > 3 && this.isEntityBehindBack((Entity)entity)) {
                return false;
            }
            LazyOptional optional = entity.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA);
            if (optional.isPresent() && (data = (PlayerWitherStormData)optional.resolve().get()).hasKilledSymbiontRecently()) {
                return false;
            }
            if (MinecraftForge.EVENT_BUS.post((Event)new CanWitherStormTargetMobEvent(this, entity))) {
                return false;
            }
            return !MinecraftForge.EVENT_BUS.post((Event)new CanWitherStormTargetMobEvent(this, entity));
        }
        return false;
    }

    protected boolean isInsideOtherTractorBeam(LivingEntity entity, int head) {
        ArrayList storms = Lists.newArrayList((Object[])new WitherStormEntity[]{this});
        this.getSegmentsManager().ifPresent(manager -> {
            for (WitherStormSegmentEntity segment : manager.getSegments()) {
                if (segment == null || !segment.m_6084_()) continue;
                storms.add(segment);
            }
        });
        for (WitherStormEntity storm : storms) {
            Pair<Boolean, Integer> flag = TractorBeamHelper.isInsideTractorBeam((Entity)entity, storm, 5.0);
            if (!((Boolean)flag.getFirst()).booleanValue() || (Integer)flag.getSecond() == head) continue;
            return true;
        }
        return false;
    }

    public boolean m_6783_(double sqrDistance) {
        double d0 = this.m_20191_().m_82309_();
        if (Double.isNaN(d0)) {
            d0 = 1.0;
        }
        return sqrDistance < (d0 = d0 * 248.0 * Entity.m_20150_()) * d0;
    }

    public boolean hasRecentlyBeenRevived() {
        return this.getPlayDeadManager().hasRecentlyBeenRevived();
    }

    @Override
    @Nullable
    public Vec3 getDistractedPos(int head) {
        return this.headManager.getHead(head).getDistractedPos();
    }

    @Override
    public void setDistractedPos(int head, @Nullable Vec3 pos) {
        this.headManager.getHead(head).setDistractedPos(pos);
    }

    @Override
    public void makeDistracted(Vec3 pos, int time, int head) {
        this.headManager.getHead(head).makeDistracted(pos, time);
    }

    @Override
    public boolean isPosBehindBack(Vec3 pos) {
        float angle = (float)(Mth.m_14136_((double)(pos.m_7096_() - this.m_20185_()), (double)(pos.m_7094_() - this.m_20189_())) * 57.29577951308232);
        float angleDiff = (Mth.m_14177_((float)(-this.f_20883_)) - angle + 180.0f + 360.0f) % 360.0f - 180.0f;
        return !(angleDiff <= 80.0f) || !(angleDiff >= -80.0f);
    }

    @Override
    public boolean canBeDistracted(int head, WitherStormBase.DistractionType type) {
        return WitherStormBase.super.canBeDistracted(head, type) && this.getPhase() > 3;
    }

    public void sendToBowels(Entity entity) {
        if (entity instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer)entity;
            WitherStormBowelsManager.queueEnter(player, this);
        } else {
            WitherStormBowelsManager.enter((ServerLevel)entity.m_9236_(), this, entity);
        }
    }

    @Override
    public float getXBodyRot() {
        return this.xBodyRot;
    }

    @Override
    public float getXBodyRotO() {
        return this.xBodyRotO;
    }

    public void doFlicker() {
        this.flickerTime = 60;
        this.m_9236_().m_7605_((Entity)this, (byte)11);
    }

    public void m_7822_(byte event) {
        if (event == 11) {
            this.doFlicker();
        } else {
            super.m_7822_(event);
        }
    }

    public boolean shouldFlicker() {
        return this.shouldFlicker;
    }

    @Nullable
    public CommandBlockEntity getBowelsCommandBlock() {
        BowelsInstanceManager manager = this.bowelsInstance.orElse(null);
        if (manager != null) {
            return manager.getCommandBlock();
        }
        return null;
    }

    @Nullable
    public WitherStormBowelsManager.BowelsInstance getBowelsInstance() {
        BowelsInstanceManager manager = this.bowelsInstance.orElse(null);
        if (manager != null) {
            return manager.getBowelsInstance();
        }
        return null;
    }

    public void dropDropsAt(Entity player) {
        ItemStack stack = new ItemStack((ItemLike)WitherStormModItems.WITHERED_NETHER_STAR.get());
        ItemEntity item = new ItemEntity(this.m_9236_(), player.m_20185_(), player.m_20188_() + 2.0, player.m_20189_(), stack);
        item.m_20334_(0.0, -0.08, 0.0);
        item.m_20242_(true);
        this.m_9236_().m_7967_((Entity)item);
        ServerLevel level = (ServerLevel)this.m_9236_();
        ExperienceOrb.m_147082_((ServerLevel)level, (Vec3)player.m_20182_().m_82520_(0.0, 10.0, 0.0), (int)ForgeEventFactory.getExperienceDrop((LivingEntity)this, (Player)this.f_20888_, (int)this.m_213860_()));
        for (Player nearby : this.m_9236_().m_45976_(Player.class, player.m_20191_().m_82400_(15.0))) {
            BlockPos nearestVillage = level.m_215011_(StructureTags.f_215889_, player.m_20183_(), 50, false);
            if (nearestVillage == null || !(Math.sqrt(nearestVillage.m_123331_((Vec3i)player.m_20183_())) < 200.0)) continue;
            nearby.m_7292_(new MobEffectInstance(MobEffects.f_19595_, 48000, 4, false, false, true));
        }
    }

    public boolean shouldPlaySoundLoops() {
        return this.shouldPlaySoundLoop && !this.m_20067_() && !this.isDeadOrPlayingDead();
    }

    public static SoundEvent getSoundForLoop(int phase, float fade) {
        SoundEvent event = (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_LOOP.get();
        if (phase > 3) {
            event = WitherStormLoopingSoundManager.getSoundBasedOnDistance(fade);
        } else if (phase < 3) {
            event = (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_PULSE_LOOP.get();
        }
        return event;
    }

    public boolean shouldTrackUltimateTarget() {
        CommandBlockEntity entity = this.getBowelsCommandBlock();
        if (entity != null && entity.m_21223_() < entity.m_21233_()) {
            return false;
        }
        return this.shouldFollowUltimateTarget;
    }

    public boolean shouldRotateTowardsUltimateTarget() {
        CommandBlockEntity entity = this.getBowelsCommandBlock();
        return entity == null || !(entity.m_21223_() / entity.m_21233_() <= 0.25f);
    }

    @Override
    public boolean isHeadInjured(int head) {
        return this.headManager.getHead(head).isHeadInjured();
    }

    public GoalSelector getGoalSelectorForHead(int head) {
        if (head > 0) {
            return this.headGoalSelectors.get(head - 1);
        }
        return this.f_21345_;
    }

    public GoalSelector getTargetSelectorForHead(int head) {
        if (head > 0) {
            return this.headTargetSelectors.get(head - 1);
        }
        return this.f_21346_;
    }

    @Override
    public float getHeadShakeAnim(int head, float partialTicks) {
        return this.headManager.getHead(head).getRollAngle(partialTicks);
    }

    @Override
    public boolean tractorBeamActive(int head) {
        boolean flag = false;
        if (this.getPhase() < 4) {
            flag = head == 0 && this.getPhase() > 1;
        } else if (this.getPhase() > 1) {
            flag = true;
        }
        return WitherStormBase.super.tractorBeamActive(head) && flag && !this.isDeadOrPlayingDead();
    }

    public void pullInTarget(Entity target, double speed, WitherStormHead head) {
        if (target != null && !(target instanceof WitherStormEntity) && this.tractorBeamActive(head.getIndex())) {
            LivingEntity living;
            LivingEntity living2;
            boolean flag;
            Entity vehicle = target.m_20202_();
            Vec3 targetPullPos = null;
            Vec3 headPos = head.getHeadPos();
            targetPullPos = target instanceof Player || target.m_20182_().m_82554_(headPos) < 25.0 ? headPos : TractorBeamHelper.calculateClosestPoint(target.m_20182_(), this, head.getIndex(), -5.0);
            Vec3 delta = targetPullPos.m_82546_(target.m_20182_()).m_82541_().m_82490_(speed *= Mth.m_14008_((double)target.m_20182_().m_82554_(targetPullPos), (double)0.1, (double)1.0));
            boolean bl = flag = !(vehicle instanceof LivingEntity) || this.entitySelector.test(living2 = (LivingEntity)vehicle);
            if (target.m_20159_() && ((Boolean)WitherStormModConfig.COMMON.shouldPickUpVehicles.get()).booleanValue() && flag) {
                vehicle.m_20256_(delta);
            } else {
                target.m_20256_(delta);
            }
            AABB headBB = new AABB(headPos.f_82479_ - 2.0, headPos.f_82480_ - 4.0, headPos.f_82481_ - 2.0, headPos.f_82479_ + 2.0, headPos.f_82480_ + 2.0, headPos.f_82481_ + 2.0);
            if (target instanceof ServerPlayer) {
                ServerPlayer player = (ServerPlayer)target;
                this.setPlayerDeltaMovement(player, delta);
            }
            if (headBB.m_82381_(target.m_20191_()) && target instanceof LivingEntity && !(living = (LivingEntity)target).m_21224_()) {
                if (target instanceof Player) {
                    Player player = (Player)target;
                    if (!player.m_21224_()) {
                        float damage = (float)this.m_21133_(Attributes.f_22281_);
                        if (((Boolean)WitherStormModConfig.SERVER.instantChomp.get()).booleanValue()) {
                            damage = Float.MAX_VALUE;
                        }
                        player.m_6469_(WitherStormModDamageTypes.witherStormAttack((LivingEntity)this), damage);
                        if (player.m_21224_()) {
                            this.consumeEntity((Entity)player, 1);
                        }
                    }
                    head.startBiting();
                } else {
                    TamableAnimal tamable;
                    this.consumeEntity((Entity)living, 1);
                    if (((Boolean)WitherStormModConfig.SERVER.healFromChomp.get()).booleanValue()) {
                        this.m_5634_(living.m_21233_() / 2.0f);
                    }
                    if (living instanceof TamableAnimal && (tamable = (TamableAnimal)living).m_21805_() != null) {
                        this.storePet((Entity)tamable);
                    }
                    target.m_6469_(WitherStormModDamageTypes.witherStormAttackMob((LivingEntity)this), Float.MAX_VALUE);
                    head.startBiting();
                    if (head instanceof AdditionalHead) {
                        AdditionalHead additionalHead = (AdditionalHead)head;
                        additionalHead.nextHeadUpdate = this.f_19797_ + this.f_19796_.m_188503_(20) + this.f_19796_.m_188503_(60);
                    }
                }
            }
        }
    }

    protected void m_21226_() {
    }

    public void dropMassCluster(int radius) {
        BlockClusterEntity cluster = ClusterBuilderHelper.buildRandomDeathCluster(this.m_9236_(), this.f_19796_, radius);
        cluster.setSink(radius / 2 + 1);
        cluster.m_146884_(this.m_20182_().m_82520_(0.0, (double)this.getUnmodifiedHeight() / 2.0, 0.0));
        cluster.m_20334_(this.f_19796_.m_188583_() * 0.3, 0.0, this.f_19796_.m_188583_() * 0.3);
        cluster.setRotationDelta(new Vec2((float)this.f_19796_.m_188503_(20) * 0.3f / 2.0f, (float)this.f_19796_.m_188503_(20) * 0.3f / 2.0f));
        cluster.setAntiStacking(true);
        this.m_9236_().m_7967_((Entity)cluster);
    }

    public void dropSmallMassCluster(int radius) {
        BlockClusterEntity cluster = ClusterBuilderHelper.buildSmallRandomDeathCluster(this.m_9236_(), this.f_19796_, radius);
        cluster.setSink(-1);
        cluster.m_20334_(this.f_19796_.m_188583_() * 0.6, this.f_19796_.m_188583_() * 0.3, this.f_19796_.m_188583_() * 0.6);
        cluster.m_146884_(this.m_20182_().m_82520_(this.f_19796_.m_188583_() * 20.0, (double)this.getUnmodifiedHeight() / 2.0 + this.f_19796_.m_188583_() * 40.0, this.f_19796_.m_188583_() * 20.0));
        cluster.setRotationDelta(new Vec2((float)this.f_19796_.m_188503_(90) * 0.3f / 2.0f, (float)this.f_19796_.m_188503_(90) * 0.3f / 2.0f));
        this.m_9236_().m_7967_((Entity)cluster);
    }

    public boolean isCompletelyInvulnerable() {
        return (Boolean)WitherStormModConfig.SERVER.witherStormInvulnerability.get();
    }

    public boolean shouldShine() {
        return this.getPhase() > 3;
    }

    public float getShineAlpha(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.shineAlphaO, (float)this.shineAlpha);
    }

    public void setShineAlpha(float alpha) {
        this.shineAlpha = alpha;
    }

    public void setResummoned(boolean flag) {
        this.resummoned = flag;
    }

    public boolean m_142079_() {
        return false;
    }

    public float getClusterRadius() {
        return this.clusterRadius;
    }

    public HeadManager getHeadManager() {
        return this.headManager;
    }

    public RemovableGoalsManager getRemovableGoalsManager() {
        return this.removableGoals;
    }

    public int m_21529_() {
        if (this.getPhase() > 3 && !this.isHeadInjured(0) && !this.isDistracted(0)) {
            return 1;
        }
        if (this.isDistracted(0)) {
            return 2;
        }
        return super.m_21529_();
    }

    public void m_6074_() {
        this.m_142687_(Entity.RemovalReason.KILLED);
        this.m_146850_(GameEvent.f_223707_);
        this.getSegmentsManager().ifPresent(manager -> {
            for (WitherStormSegmentEntity entity : manager.getSegments()) {
                if (entity == null) continue;
                entity.m_6074_();
            }
        });
    }

    @Override
    public double getTractorBeamCutoffDistance(int head) {
        return this.headManager.getHead(head).getTractorBeamCutoff();
    }

    public EvolutionProfiler getEvolutionProfiler() {
        return this.evolutionProfiler;
    }

    public void spawnConsumedPets(Vec3 pos) {
        for (Map.Entry<UUID, CompoundTag> entry : this.consumedPets.entrySet()) {
            CompoundTag tag = entry.getValue();
            if (tag.m_128456_()) continue;
            EntityType.m_20642_((CompoundTag)tag, (Level)this.m_9236_()).ifPresent(entity -> {
                entity.m_146884_(pos);
                if (entity instanceof LivingEntity) {
                    LivingEntity living = (LivingEntity)entity;
                    living.m_21153_(living.m_21233_());
                    living.m_21219_();
                    living.m_7292_(new MobEffectInstance(MobEffects.f_19604_, 200, 0));
                }
                ((ServerLevel)this.m_9236_()).m_8847_(entity);
            });
        }
        this.consumedPets.clear();
    }

    public IgnoredTargetsManager getIgnoredTargets() {
        return this.ignoredTargets;
    }

    public List<BlockPos> getPlayingJukeboxes() {
        return this.playingJukeboxes;
    }

    public float getShineScale() {
        return this.shineScale;
    }

    @Override
    public void setLookAt(int head, Vec3 pos, int time) {
        this.headManager.getHead(head).setLookPos(pos, time);
    }

    @Override
    public LivingEntity getTarget(int head) {
        return this.headManager.getHead(head).getTarget();
    }

    @Override
    public void setTarget(int head, LivingEntity entity) {
        this.headManager.getHead(head).setTarget(entity);
    }

    protected void splitCluster(BlockClusterEntity cluster, List<Entity> toAdd) {
        BlockClusterEntity split = cluster.splitAt(Direction.Axis.m_235688_((RandomSource)this.f_19796_));
        if (split != null) {
            this.m_9236_().m_7967_((Entity)split);
            this.segments.ifPresentOrElse(manager -> {
                if (this.f_19796_.m_188499_()) {
                    toAdd.add(split);
                } else {
                    int size = manager.getSegments().length;
                    int index = this.f_19796_.m_188503_(size);
                    WitherStormSegmentEntity segment = manager.getSegments()[index];
                    if (segment != null && !segment.m_213877_()) {
                        segment.getTrackedEntities().trackEntityToConsume(split);
                    } else {
                        toAdd.add(split);
                    }
                }
            }, () -> toAdd.add(split));
        }
    }

    @Override
    public int loadRadius() {
        return (Integer)WitherStormModConfig.SERVER.chunkLoadingRadius.get();
    }

    @Override
    public boolean isStillValidForChunkLoading() {
        return this.m_146911_() == null || !this.m_146911_().m_146965_();
    }

    public float getPhaseProgress() {
        return this.phaseProgress;
    }

    public int entityConsumptionRadiusHunch() {
        int radiusValue = 12;
        if ((radiusValue = (int)((long)radiusValue + Math.round((double)this.getConsumedEntities() * 0.00445))) > 48) {
            radiusValue = 48;
        }
        return radiusValue;
    }

    public int getEntityConsumptionRadius() {
        return this.entityConsumptionRadius;
    }

    public void makeConsumptionLocked(boolean flag) {
        this.isLocked = flag;
    }

    public boolean isConsumptionLocked() {
        return this.isLocked;
    }

    public Vec3 m_21074_(Vec3 vec, float f) {
        this.m_19920_(this.m_6113_(), vec);
        this.m_6478_(MoverType.SELF, this.m_20184_());
        return this.m_20184_();
    }

    private Vec3 wsCollide(Vec3 delta) {
        AABB aabb = this.m_20191_();
        List list = this.m_9236_().m_183134_((Entity)this, aabb.m_82369_(delta));
        return delta.m_82556_() == 0.0 ? delta : WitherStormEntity.m_198894_((Entity)this, (Vec3)delta, (AABB)aabb, (Level)this.m_9236_(), (List)list);
    }

    public float getStepHeight() {
        return 0.0f;
    }

    public void m_6478_(MoverType moverType, Vec3 delta) {
        if (this.f_19794_) {
            this.m_6034_(this.m_20185_() + delta.f_82479_, this.m_20186_() + delta.f_82480_, this.m_20189_() + delta.f_82481_);
        } else {
            Vec3 vec3;
            double d0;
            this.m_9236_().m_46473_().m_6180_("move");
            if (this.f_19865_.m_82556_() > 1.0E-7) {
                delta = delta.m_82559_(this.f_19865_);
                this.f_19865_ = Vec3.f_82478_;
                this.m_20256_(Vec3.f_82478_);
            }
            if ((d0 = (vec3 = this.wsCollide(delta)).m_82556_()) > 1.0E-7) {
                BlockHitResult blockhitresult;
                if (this.f_19789_ != 0.0f && d0 >= 1.0 && (blockhitresult = this.m_9236_().m_45547_(new ClipContext(this.m_20182_(), this.m_20182_().m_82549_(vec3), ClipContext.Block.FALLDAMAGE_RESETTING, ClipContext.Fluid.WATER, (Entity)this))).m_6662_() != HitResult.Type.MISS) {
                    this.m_183634_();
                }
                this.m_6034_(this.m_20185_() + vec3.f_82479_, this.m_20186_() + vec3.f_82480_, this.m_20189_() + vec3.f_82481_);
            }
            this.m_9236_().m_46473_().m_7238_();
            this.m_9236_().m_46473_().m_6180_("rest");
            boolean noXMovement = !Mth.m_14082_((double)delta.f_82479_, (double)vec3.f_82479_);
            boolean noZMovement = !Mth.m_14082_((double)delta.f_82481_, (double)vec3.f_82481_);
            this.f_19862_ = noXMovement || noZMovement;
            this.f_19863_ = delta.f_82480_ != vec3.f_82480_;
            this.f_201939_ = this.f_19863_ && delta.f_82480_ < 0.0;
            this.m_289603_(this.f_201939_, vec3);
            BlockPos pos = this.m_216999_();
            BlockState state = this.m_9236_().m_8055_(pos);
            if (!this.m_213877_()) {
                if (this.f_19862_) {
                    Vec3 vec31 = this.m_20184_();
                    this.m_20334_(noXMovement ? 0.0 : vec31.f_82479_, vec31.f_82480_, noZMovement ? 0.0 : vec31.f_82481_);
                }
                Block block = state.m_60734_();
                if (delta.f_82480_ != vec3.f_82480_) {
                    block.m_5548_((BlockGetter)this.m_9236_(), (Entity)this);
                }
                if (this.m_20096_()) {
                    block.m_141947_(this.m_9236_(), pos, state, (Entity)this);
                }
            }
            this.m_9236_().m_46473_().m_7238_();
        }
    }

    protected void m_20101_() {
    }

    protected void m_146872_() {
    }

    protected Entity.MovementEmission m_142319_() {
        return Entity.MovementEmission.NONE;
    }

    public void lerpBodyRotationTo(float xBodyRot, float yBodyRot, int steps) {
        this.lerpBodyXRot = xBodyRot;
        this.lerpBodyYRot = yBodyRot;
        this.bodyLerpSteps = steps;
    }

    private double attributeOrConfigValue(Attribute attribute, ForgeConfigSpec.ConfigValue<Double> config) {
        AttributeInstance instance = this.m_21051_(attribute);
        if (instance.m_22135_() != attribute.m_22082_()) {
            return instance.m_22135_();
        }
        return (Double)config.get();
    }

    @Override
    public boolean canSee(int head, Entity entity) {
        return this.headManager.getHead(head).canSee(entity);
    }

    public void storePet(Entity entity) {
        this.consumedPets.computeIfAbsent(entity.m_20148_(), u -> {
            CompoundTag tag = new CompoundTag();
            tag.m_128359_("id", Objects.requireNonNull(entity.m_20078_()));
            entity.m_20240_(tag);
            return tag;
        });
    }

    public boolean equals(Object obj) {
        WitherStormEntity storm;
        return super.equals(obj) && obj instanceof WitherStormEntity && (storm = (WitherStormEntity)obj).isOnDistantRenderer() == this.isOnDistantRenderer();
    }

    public static boolean isOccludedSound(SoundEvent event) {
        if (event == WitherStormModSoundEvents.WITHER_STORM_GROWL.get()) {
            return true;
        }
        if (event == WitherStormModSoundEvents.WITHER_STORM_HURT.get()) {
            return true;
        }
        if (event == WitherStormModSoundEvents.WITHER_STORM_SHOOT.get()) {
            return true;
        }
        if (event == WitherStormModSoundEvents.WITHER_STORM_BITE.get()) {
            return true;
        }
        if (event == WitherStormModSoundEvents.WITHER_STORM_ROAR.get()) {
            return true;
        }
        return event == WitherStormModSoundEvents.WITHER_STORM_TRACTOR_BEAM_ACTIVATES.get();
    }

    public static <T> EntityDataAccessor<T> registerDataAccessor(EntityDataSerializer<T> serializer, Supplier<T> defaultValue) {
        try {
            Class<?> oclass = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());
            if (oclass.getPackageName().startsWith("nonamecrackers2.witherstormmod.common.entity.ai.witherstorm")) {
                EntityDataAccessor accessor = SynchedEntityData.m_135353_(WitherStormEntity.class, serializer);
                DATA_ACCESSORS.add(new DataAccessorHolder<T>(accessor, defaultValue));
                return accessor;
            }
            throw new RuntimeException("This method is for internal use only!");
        }
        catch (ClassNotFoundException classNotFoundException) {
            return null;
        }
    }

    private record DataAccessorHolder<T>(EntityDataAccessor<T> accessor, Supplier<T> defaultValue) {
        private void defineTo(SynchedEntityData data) {
            data.m_135372_(this.accessor, this.defaultValue.get());
        }
    }
}

