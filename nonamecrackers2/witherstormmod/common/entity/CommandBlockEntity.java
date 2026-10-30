/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerBossEvent
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.random.SimpleWeightedRandomList
 *  net.minecraft.world.BossEvent$BossBarColor
 *  net.minecraft.world.BossEvent$BossBarOverlay
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.SpawnPlacements$Type
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.targeting.TargetingConditions
 *  net.minecraft.world.entity.animal.AbstractGolem
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.NaturalSpawner
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BlockStateProperties
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.material.PushReaction
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.common.util.LogicalSidedProvider
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.fml.DistExecutor
 *  net.minecraftforge.network.NetworkEvent$Context
 *  net.minecraftforge.network.PacketDistributor
 *  nonamecrackers2.crackerslib.common.packet.Packet
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.crackerslib.common.packet.Packet;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
import nonamecrackers2.witherstormmod.common.entity.BossThemeEntity;
import nonamecrackers2.witherstormmod.common.entity.SickenedIronGolem;
import nonamecrackers2.witherstormmod.common.entity.SickenedVindicator;
import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.commandblock.BowelsBossFightStages;
import nonamecrackers2.witherstormmod.common.entity.bossfight.BossfightManager;
import nonamecrackers2.witherstormmod.common.entity.bossfight.BossfightPhase;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.init.WitherStormModToolActions;
import nonamecrackers2.witherstormmod.common.packet.PlayerMotionMessage;
import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
import nonamecrackers2.witherstormmod.common.serializer.WitherStormModDataSerializers;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModItemTags;
import nonamecrackers2.witherstormmod.common.util.EntitySyncableData;
import nonamecrackers2.witherstormmod.common.util.StructureAnimationHelper;
import nonamecrackers2.witherstormmod.common.util.TentacleOffsets;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;
import org.jetbrains.annotations.NotNull;

public class CommandBlockEntity
extends LivingEntity
implements EntitySyncableData,
BossThemeEntity {
    private static final EntityDataAccessor<State> STATE = SynchedEntityData.m_135353_(CommandBlockEntity.class, WitherStormModDataSerializers.STATE_ENUM);
    private static final EntityDataAccessor<Mode> MODE = SynchedEntityData.m_135353_(CommandBlockEntity.class, WitherStormModDataSerializers.MODE_ENUM);
    private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.m_135353_(CommandBlockEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135041_);
    private static final EntityDataAccessor<Integer> PHASE_KEY = SynchedEntityData.m_135353_(CommandBlockEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    private static final Predicate<LivingEntity> SEARCHABLE_PLAYER_SELECTOR = living -> {
        Player player;
        return living instanceof Player && (player = (Player)living).m_6084_() && !player.m_150110_().f_35934_ && !player.m_7500_() && !player.m_5833_() && player.m_6097_();
    };
    private static final SimpleWeightedRandomList<EntityType<? extends Mob>> IDLE_BOWELS_MOBS = SimpleWeightedRandomList.m_146263_().m_146271_((Object)((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get()), 10).m_146271_((Object)((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get()), 10).m_146271_((Object)((EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get()), 6).m_146271_((Object)((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get()), 1).m_146271_((Object)((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get()), 4).m_146271_((Object)((EntityType)WitherStormModEntityTypes.SICKENED_COW.get()), 4).m_146271_((Object)((EntityType)WitherStormModEntityTypes.SICKENED_PIG.get()), 4).m_146271_((Object)((EntityType)WitherStormModEntityTypes.SICKENED_PARROT.get()), 2).m_146271_((Object)((EntityType)WitherStormModEntityTypes.SICKENED_WOLF.get()), 1).m_146271_((Object)((EntityType)WitherStormModEntityTypes.SICKENED_CAT.get()), 1).m_146271_((Object)((EntityType)WitherStormModEntityTypes.SICKENED_BEE.get()), 1).m_146271_((Object)((EntityType)WitherStormModEntityTypes.SICKENED_PILLAGER.get()), 3).m_146271_((Object)((EntityType)WitherStormModEntityTypes.SICKENED_VINDICATOR.get()), 1).m_146271_((Object)((EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get()), 3).m_146270_();
    public static final int HIT_GLARE_TIME = 60;
    private int modeAnim;
    private int modeAnimO;
    private int stateTicks;
    @Nullable
    private Player toLure;
    private final List<StructureAnimationHelper> ribStructure = new ArrayList<StructureAnimationHelper>();
    private final TentacleManager tentacleStructure;
    private float protectionYOffset;
    private float protectionYOffsetO;
    @Nullable
    private WitherStormEntity owner;
    private final BossfightManager<CommandBlockEntity> bossfightManager;
    private final ServerBossEvent bossInfo = new ServerBossEvent(this.m_5446_(), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.PROGRESS);
    private final List<ServerPlayer> outsideBossBarViewers = Lists.newArrayList();
    private final List<ServerPlayer> tracking = Lists.newArrayList();
    @Nullable
    public BlockClusterEntity podiumCluster;
    @Nullable
    public UUID podiumClusterUUID;
    private int specialDeathTime;
    @Nullable
    public LivingEntity killer;
    private int hitGlareTime;

    public CommandBlockEntity(EntityType<? extends CommandBlockEntity> type, Level world) {
        super(type, world);
        this.createStructureHelpers();
        this.tentacleStructure = new TentacleManager(this, 6, new TentacleOffsets[]{new TentacleOffsets(2.0, -2.0, 3.0, 1.45f, 1.0f, 40.0f, -70.0f), new TentacleOffsets(0.0, -2.0, 4.0, 1.4f, 1.0f, 35.0f, -90.0f), new TentacleOffsets(-2.0, -2.0, 3.0, 1.45f, 1.0f, 40.0f, -110.0f), new TentacleOffsets(2.0, -2.0, -3.0, 1.45f, 1.0f, 40.0f, 70.0f), new TentacleOffsets(0.0, -2.0, -4.0, 1.4f, 1.0f, 35.0f, 90.0f), new TentacleOffsets(-2.0, -2.0, -3.0, 1.45f, 1.0f, 40.0f, 110.0f)});
        this.bossfightManager = new BossfightManager<CommandBlockEntity>(BowelsBossFightStages.IDLE, this).addPhase(1, BowelsBossFightStages.HIT).addPhase(2, BowelsBossFightStages.MOVE_PODIUM).addPhase(3, BowelsBossFightStages.WAIT).addPhase(4, BowelsBossFightStages.MOB_WAVE_1).addPhase(5, BowelsBossFightStages.IDLE).addPhase(6, BowelsBossFightStages.HIT).addPhase(7, BowelsBossFightStages.MOVE_PODIUM).addPhase(8, BowelsBossFightStages.WAIT).addPhase(9, BowelsBossFightStages.MOB_WAVE_2).addPhase(10, BowelsBossFightStages.PROTECT_IDLE).addPhase(11, BowelsBossFightStages.IDLE).addPhase(12, BowelsBossFightStages.HIT).addPhase(13, BowelsBossFightStages.MOVE_PODIUM).addPhase(14, BowelsBossFightStages.MOB_WAVE_3).addPhase(15, BowelsBossFightStages.PROTECT_IDLE).addPhase(16, BowelsBossFightStages.IDLE).addPhase(17, BowelsBossFightStages.DEATH).addPhase(18, BowelsBossFightStages.IDLE);
    }

    public CommandBlockEntity(Level world, WitherStormEntity owner, double x, double y, double z) {
        this((EntityType<? extends CommandBlockEntity>)((EntityType)WitherStormModEntityTypes.COMMAND_BLOCK.get()), world);
        this.m_6034_(x, y, z);
        this.setState(State.PLAYING_DEAD);
        this.setMode(Mode.RIBS);
        this.setOwner(owner);
        this.setOwnerUUID(owner.m_20148_());
        this.m_146922_(owner.m_146908_());
        this.m_5618_(owner.f_20883_);
        this.m_5616_(owner.m_6080_());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.m_21183_().m_22268_(Attributes.f_22276_, 64.0).m_22268_(Attributes.f_22284_, 32.0).m_22268_(Attributes.f_22285_, 32.0).m_22268_(Attributes.f_22278_, 1024.0).m_22268_(Attributes.f_22277_, 32.0);
    }

    private void createStructureHelpers() {
        this.ribStructure.add(new StructureAnimationHelper());
        this.ribStructure.add(new StructureAnimationHelper().setBaseRotationAngle(0.0f, 180.0f));
        this.ribStructure.add(new StructureAnimationHelper().setBaseRotationAngle(0.0f, 145.0f));
        this.ribStructure.add(new StructureAnimationHelper().setBaseRotationAngle(0.0f, 35.0f));
        this.ribStructure.add(new StructureAnimationHelper().setBaseRotationAngle(0.0f, -35.0f));
        this.ribStructure.add(new StructureAnimationHelper().setBaseRotationAngle(0.0f, 215.0f));
    }

    public void m_8119_() {
        if (!this.m_9236_().f_46443_) {
            this.f_19804_.m_135381_(PHASE_KEY, (Object)this.getBossfightManager().getCurrentPhaseIndex());
            int interval = (int)(this.m_21223_() / this.m_21233_() * 80.0f);
            int secondaryInterval = (int)(this.m_21223_() / this.m_21233_() * 16.0f);
            if (!this.tracking.isEmpty() && this.m_21223_() < this.m_21233_() && interval > 0 && this.f_19797_ % interval == 0) {
                this.dropClusterFromCeiling();
            }
            if (!this.tracking.isEmpty() && this.m_21223_() < this.m_21233_() && secondaryInterval > 0 && this.f_19797_ % secondaryInterval == 0) {
                this.dropAdditionalClustersFromCeiling();
            }
            int mobSpawnerTimer = 100;
            if (WorldUtil.areaLoaded(this.m_9236_(), this.m_20183_(), 8) && this.getState().equals((Object)State.BOSSFIGHT) && this.m_9236_().m_46472_().m_135782_().equals((Object)WitherStormMod.bowelsLocation()) && this.f_19797_ % mobSpawnerTimer == 0 && this.f_19796_.m_188500_() <= 0.25) {
                this.summonRandomBowelsMobNearPlayer(8, 16, IDLE_BOWELS_MOBS);
            }
        }
        super.m_8119_();
        this.findOwner();
        this.getState().tick(this);
        this.modeAnimO = this.modeAnim;
        this.protectionYOffsetO = this.protectionYOffset;
        this.getStructures().forEach(StructureAnimationHelper::tick);
        if (!this.m_9236_().f_46443_) {
            this.tentacleStructure.findTentacles((ServerLevel)this.m_9236_());
            this.bossInfo.m_142711_(this.m_21223_() / this.m_21233_());
            for (ServerPlayer player : this.outsideBossBarViewers) {
                if (!this.m_21224_() && this.m_21223_() < this.m_21233_()) {
                    this.bossInfo.m_6543_(player);
                    continue;
                }
                this.bossInfo.m_6539_(player);
            }
            if (this.getOwner() == null || this.getOwner().getBowelsCommandBlock() != this) {
                for (ServerPlayer player : this.outsideBossBarViewers) {
                    this.bossInfo.m_6539_(player);
                }
                this.outsideBossBarViewers.clear();
            }
        }
        if (this.hitGlareTime > 0) {
            --this.hitGlareTime;
        }
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(STATE, (Object)State.IDLE);
        this.f_19804_.m_135372_(MODE, (Object)Mode.NONE);
        this.f_19804_.m_135372_(OWNER_UUID, Optional.empty());
        this.f_19804_.m_135372_(PHASE_KEY, (Object)0);
    }

    public void m_7380_(@NotNull CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128405_("Mode", this.getMode().ordinal());
        compound.m_128405_("State", this.getState().ordinal());
        compound.m_128405_("StateTicks", this.stateTicks);
        compound.m_128405_("ModeAnim", this.modeAnim);
        compound.m_128350_("YOffset", this.protectionYOffset);
        compound.m_128350_("YBodyRot", this.f_20883_);
        compound.m_128365_("Structures", this.writeStructures());
        if (this.getOwnerUUID() != null) {
            compound.m_128362_("OwnerUUID", this.getOwnerUUID());
        }
        this.tentacleStructure.addSaveData(compound);
        if (this.podiumCluster != null && this.podiumCluster.m_6084_()) {
            compound.m_128362_("PodiumCluster", this.podiumCluster.m_20148_());
        }
        compound.m_128365_("BossfightManager", (Tag)this.bossfightManager.write());
    }

    public void m_7378_(@NotNull CompoundTag compound) {
        int state;
        super.m_7378_(compound);
        this.protectionYOffsetO = this.protectionYOffset;
        int mode = compound.m_128451_("Mode");
        if (mode >= 0 && mode < Mode.values().length) {
            this.setMode(Mode.values()[mode]);
        }
        if ((state = compound.m_128451_("State")) >= 0 && state < State.values().length) {
            this.setState(State.values()[state]);
        }
        this.stateTicks = compound.m_128451_("StateTicks");
        this.modeAnimO = this.modeAnim = compound.m_128451_("ModeAnim");
        this.protectionYOffset = compound.m_128457_("YOffset");
        this.m_5618_(compound.m_128457_("YBodyRot"));
        this.readStructures(compound.m_128437_("Structures", 10));
        if (compound.m_128441_("OwnerUUID")) {
            this.setOwnerUUID(compound.m_128342_("OwnerUUID"));
        }
        this.tentacleStructure.readSaveData(compound);
        if (compound.m_128441_("PodiumCluster")) {
            this.podiumClusterUUID = compound.m_128342_("PodiumCluster");
        }
        if (compound.m_128441_("BossfightManager")) {
            this.bossfightManager.read(compound.m_128469_("BossfightManager"));
        }
    }

    public Mode getMode() {
        return (Mode)((Object)this.f_19804_.m_135370_(MODE));
    }

    public void setMode(Mode mode) {
        this.f_19804_.m_135381_(MODE, (Object)mode);
    }

    public State getState() {
        return (State)((Object)this.f_19804_.m_135370_(STATE));
    }

    public void setState(State state) {
        this.f_19804_.m_135381_(STATE, (Object)state);
        this.initState();
    }

    private void initState() {
        this.getState().init(this);
    }

    public void nextState() {
        if (this.getState().ordinal() + 1 < State.values().length) {
            this.setState(State.values()[this.getState().ordinal() + 1]);
        }
    }

    public void setLuringPlayer(@Nullable Player player) {
        this.toLure = player;
    }

    @Nullable
    public Player getLuringPlayer() {
        return this.toLure;
    }

    public void m_7350_(@NotNull EntityDataAccessor<?> parameter) {
        super.m_7350_(parameter);
        if (parameter.equals(STATE)) {
            this.initState();
        }
        if (parameter.equals(MODE)) {
            this.getMode().init(this, this.getState());
        }
    }

    public BlockState getBlockState() {
        return (BlockState)Blocks.f_50272_.m_49966_().m_61124_((Property)BlockStateProperties.f_61372_, (Comparable)this.m_6350_());
    }

    public int m_213860_() {
        return 10;
    }

    @NotNull
    public AABB m_6921_() {
        return this.m_20191_().m_82400_(20.0);
    }

    public void m_147240_(double strength, double x, double z) {
    }

    public boolean m_6128_() {
        return true;
    }

    public boolean m_6469_(DamageSource source, float amount) {
        LivingEntity entity;
        ItemStack stack;
        if (source.m_269533_(DamageTypeTags.f_268738_)) {
            return super.m_6469_(source, amount);
        }
        boolean flag = false;
        Entity entity2 = source.m_7640_();
        if (entity2 instanceof LivingEntity && ((stack = (entity = (LivingEntity)entity2).m_21205_()).m_204117_(WitherStormModItemTags.COMMAND_BLOCK_TOOLS) || stack.canPerformAction(WitherStormModToolActions.COMMAND_BLOCK_DAMAGE))) {
            this.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_HIT.get(), 4.0f, 1.0f);
            if (!this.isVulnerable(source)) {
                if (this.getState() != State.BOSSFIGHT) {
                    entity.m_147240_(1.0, this.m_20185_() - entity.m_20185_(), this.m_20189_() - entity.m_20189_());
                }
                flag = true;
            } else {
                boolean hurt = false;
                float health = this.m_21223_() - this.m_21233_() / 4.0f;
                if (health <= 0.0f) {
                    hurt = super.m_6469_(source, Float.MAX_VALUE);
                } else {
                    this.m_21153_(health);
                    hurt = true;
                }
                if (!this.m_9236_().f_46443_ && hurt && !this.m_21224_()) {
                    this.getBossfightManager().goToNextPhase();
                    WitherStormEntity owner = this.getOwner();
                    if (owner != null && owner.m_6084_()) {
                        owner.getSegmentsManager().ifPresent(manager -> {
                            for (WitherStormSegmentEntity segment : manager.getSegments()) {
                                if (segment == null || !segment.m_6084_()) continue;
                                segment.getTrackedEntities().clearAndMakeAllFall();
                                for (int i = 0; i < segment.getTotalHeads(); ++i) {
                                    if (!(this.f_19796_.m_188501_() > 0.6f)) continue;
                                    segment.getHeadManager().getHead(i).hurt(null, segment.getHeadManager().getHeadInjuryTime());
                                }
                            }
                        });
                        owner.getTrackedEntities().clearAndMakeAllFall();
                        for (int i = 0; i < owner.getTotalHeads(); ++i) {
                            if (!(this.f_19796_.m_188501_() > 0.6f)) continue;
                            owner.getHeadManager().getHead(i).hurt(null, owner.getHeadManager().getHeadInjuryTime());
                        }
                        this.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_DAMAGE.get(), 16.0f, 1.0f);
                        this.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_CRACKS.get(), 16.0f, 1.0f);
                    }
                }
                if (this.m_9236_().f_46443_) {
                    for (int i = 0; i < 100; ++i) {
                        double x = this.f_19796_.m_188501_() - 0.5f;
                        double y = this.f_19796_.m_188501_() - 0.5f;
                        double z = this.f_19796_.m_188501_() - 0.5f;
                        Vec3 pos = this.m_20191_().m_82399_();
                        this.m_9236_().m_7107_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), pos.f_82479_, pos.f_82480_, pos.f_82481_, x, y, z);
                    }
                    if (health > 0.0f) {
                        this.hitGlareTime = 60;
                    }
                }
                return hurt;
            }
        }
        if ((source.m_269533_(DamageTypeTags.f_268415_) || source.m_269533_(DamageTypeTags.f_268415_) || flag) && this.getState().equals((Object)State.PLAYING_DEAD)) {
            this.setState(State.REACTIVATING);
            return false;
        }
        return false;
    }

    public boolean isVulnerable(DamageSource source) {
        BossfightPhase<CommandBlockEntity> currentPhase = this.getCurrentPhase();
        return this.getState() == State.BOSSFIGHT && !this.m_6673_(source) && currentPhase.equals(BowelsBossFightStages.IDLE) && !currentPhase.equals(BowelsBossFightStages.PROTECT_IDLE);
    }

    @NotNull
    public Iterable<ItemStack> m_6168_() {
        return Collections.emptyList();
    }

    @NotNull
    public ItemStack m_6844_(@NotNull EquipmentSlot slot) {
        return ItemStack.f_41583_;
    }

    public void m_8061_(@NotNull EquipmentSlot slot, @NotNull ItemStack stack) {
    }

    @NotNull
    public HumanoidArm m_5737_() {
        return HumanoidArm.RIGHT;
    }

    public boolean m_20068_() {
        return true;
    }

    public boolean m_6094_() {
        return false;
    }

    public boolean m_5829_() {
        return true;
    }

    public void m_7334_(@NotNull Entity entity) {
    }

    public void m_5997_(double x, double y, double z) {
    }

    protected float m_6431_(@NotNull Pose pose, @NotNull EntityDimensions size) {
        return 0.5f;
    }

    public float getModeAnim(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.modeAnimO, (float)this.modeAnim);
    }

    public List<StructureAnimationHelper> getRibStructure() {
        return this.ribStructure;
    }

    public TentacleManager getTentacleStructure() {
        return this.tentacleStructure;
    }

    public List<StructureAnimationHelper> getStructures() {
        return new ArrayList<StructureAnimationHelper>(this.getRibStructure());
    }

    public TargetingConditions searchablePlayersPredicate() {
        return TargetingConditions.m_148353_().m_26888_(SEARCHABLE_PLAYER_SELECTOR).m_26883_(6.0);
    }

    public void lerpStructureBaseRotTo(List<StructureAnimationHelper> structure, float xRot, float yRot, int steps) {
        for (StructureAnimationHelper helper : structure) {
            helper.lerpBaseTo(this, xRot, yRot, steps);
        }
    }

    public void lerpStructureRotTo(List<StructureAnimationHelper> structure, float xRot, float yRot, int steps) {
        for (StructureAnimationHelper helper : structure) {
            helper.lerpTo(this, xRot, yRot, steps);
        }
    }

    public void setProtectionYOffsetAndO(float offset) {
        this.protectionYOffset = offset;
        this.protectionYOffsetO = offset;
    }

    public void setProtectionYOffset(float offset) {
        this.protectionYOffset = offset;
    }

    public float getProtectionYOffset() {
        return this.protectionYOffset;
    }

    public float lerpProtectionYOffset(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.protectionYOffsetO, (float)this.protectionYOffset);
    }

    public int getStateTicks() {
        return this.stateTicks;
    }

    public Tag writeStructures() {
        ListTag structures = new ListTag();
        for (StructureAnimationHelper helper : this.getRibStructure()) {
            CompoundTag ribStructure = new CompoundTag();
            helper.write(ribStructure);
            structures.add((Object)ribStructure);
        }
        return structures;
    }

    public void readStructures(ListTag list) {
        for (int i = 0; i < list.size(); ++i) {
            CompoundTag ribStructure = list.m_128728_(i);
            if (i >= this.getRibStructure().size()) continue;
            this.getRibStructure().get(i).read(ribStructure);
        }
    }

    @Override
    public void writeData(FriendlyByteBuf buffer) {
        buffer.writeInt(this.stateTicks);
        buffer.writeInt(this.modeAnim);
        buffer.writeFloat(this.protectionYOffset);
        for (StructureAnimationHelper helper : this.getStructures()) {
            helper.writeBuffer(buffer);
        }
    }

    @Override
    public void readData(FriendlyByteBuf buffer) {
        this.stateTicks = buffer.readInt();
        this.modeAnimO = this.modeAnim = buffer.readInt();
        this.protectionYOffsetO = this.protectionYOffset = buffer.readFloat();
        for (StructureAnimationHelper helper : this.getStructures()) {
            helper.readBuffer(buffer);
        }
    }

    public void findOwner() {
        block2: {
            block3: {
                if (this.getOwnerUUID() == null || this.getOwner() != null) break block2;
                if (this.getState() == State.BOSSFIGHT) break block3;
                for (WitherStormEntity entity : this.m_9236_().m_45976_(WitherStormEntity.class, this.m_20191_().m_82400_(100.0))) {
                    if (!entity.m_20148_().equals(this.getOwnerUUID())) continue;
                    this.setOwner(entity);
                    entity.getPlayDeadManager().setCommandBlock(this);
                }
                break block2;
            }
            if (this.m_9236_().f_46443_) break block2;
            MinecraftServer server = this.m_9236_().m_7654_();
            for (Level level : server.m_129785_()) {
                Entity entity = ((ServerLevel)level).m_8791_(this.getOwnerUUID());
                if (!(entity instanceof WitherStormEntity)) continue;
                this.setOwner((WitherStormEntity)entity);
                break;
            }
        }
    }

    public void setOwner(@Nullable WitherStormEntity entity) {
        this.owner = entity;
        if (entity != null) {
            this.setOwnerUUID(entity.m_20148_());
            this.getState().initWithOwner(entity, this);
        } else {
            this.setOwnerUUID(null);
        }
    }

    @Nullable
    public WitherStormEntity getOwner() {
        return this.owner;
    }

    @Nullable
    public UUID getOwnerUUID() {
        return ((Optional)this.f_19804_.m_135370_(OWNER_UUID)).orElse(null);
    }

    public void setOwnerUUID(@Nullable UUID uuid) {
        this.f_19804_.m_135381_(OWNER_UUID, Optional.ofNullable(uuid));
    }

    @NotNull
    public net.minecraft.network.protocol.Packet<ClientGamePacketListener> m_5654_() {
        WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), (Object)new ModeAnimationMessage(this.m_19879_(), this.modeAnim));
        return super.m_5654_();
    }

    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (!this.m_9236_().f_46443_) {
            this.tentacleStructure.createTentacles();
        }
    }

    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
    }

    public void m_6667_(@NotNull DamageSource source) {
        if (!this.m_9236_().f_46443_ && this.getState() == State.BOSSFIGHT && this.getBossfightManager().getCurrentPhase() != BowelsBossFightStages.DEATH) {
            this.getBossfightManager().setCurrentFromNextInOrder(BowelsBossFightStages.DEATH);
        }
        this.killer = this.m_21232_();
        super.m_6667_(source);
    }

    protected void m_6153_() {
        if (this.getCurrentPhase().equals(BowelsBossFightStages.DEATH)) {
            ++this.specialDeathTime;
            if (!this.m_9236_().f_46443_) {
                if (this.specialDeathTime > 160) {
                    this.getBossfightManager().goToNextPhase();
                } else {
                    this.dropClusterFromCeiling();
                }
                if (this.specialDeathTime > 240) {
                    this.tentacleStructure.killTentacles();
                    this.m_142687_(Entity.RemovalReason.KILLED);
                }
            }
        } else {
            super.m_6153_();
            if (!this.m_9236_().f_46443_ && this.f_20919_ >= 20) {
                this.tentacleStructure.removeTentacles();
            }
        }
    }

    private void dropClusterFromCeiling() {
        if (WorldUtil.areaLoaded(this.m_9236_(), this.m_20183_(), 2)) {
            float angle = (float)Math.PI * 2 * this.f_19796_.m_188501_();
            float dist = 8.0f + this.f_19796_.m_188501_() * 24.0f;
            int x = Mth.m_14143_((float)(Mth.m_14089_((float)angle) * dist)) + this.m_146903_();
            int z = Mth.m_14143_((float)(Mth.m_14031_((float)angle) * dist)) + this.m_146907_();
            int y = WorldUtil.getCeilingStartingAt(this.m_9236_(), this.m_146904_() + 10, x, z);
            BlockPos pos = new BlockPos(x, y, z);
            if (this.m_9236_().m_46859_(pos.m_7495_())) {
                BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(this.m_9236_());
                assert (cluster != null);
                cluster.populateWithRadius(pos, 1.0f, blockstate -> !blockstate.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST));
                cluster.setRotationDelta(new Vec2(10.0f * (this.f_19796_.m_188501_() - 0.5f), 10.0f * (this.f_19796_.m_188501_() - 0.5f)));
                cluster.setAntiStacking(true);
                this.m_9236_().m_7967_((Entity)cluster);
            }
        }
    }

    private void dropAdditionalClustersFromCeiling() {
        if (WorldUtil.areaLoaded(this.m_9236_(), this.m_20183_(), 8)) {
            for (int i = 0; i < 128; ++i) {
                float angle = (float)Math.PI * 2 * this.f_19796_.m_188501_();
                float dist = 32.0f + this.f_19796_.m_188501_() * 80.0f;
                int x = Mth.m_14143_((float)(Mth.m_14089_((float)angle) * dist)) + this.m_146903_();
                int z = Mth.m_14143_((float)(Mth.m_14031_((float)angle) * dist)) + this.m_146907_();
                int y = WorldUtil.getCeilingStartingAt(this.m_9236_(), this.m_146904_() + (this.f_19796_.m_188503_(49) - 24), x, z);
                BlockPos pos = new BlockPos(x, y, z);
                if (!this.m_9236_().m_46859_(pos.m_7495_())) continue;
                BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(this.m_9236_());
                assert (cluster != null);
                cluster.populateWithRadius(pos, 1.0f, blockstate -> !blockstate.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST));
                cluster.setRotationDelta(new Vec2(10.0f * (this.f_19796_.m_188501_() - 0.5f), 10.0f * (this.f_19796_.m_188501_() - 0.5f)));
                cluster.setAntiStacking(true);
                this.m_9236_().m_7967_((Entity)cluster);
                break;
            }
        }
    }

    public BossfightManager<CommandBlockEntity> getBossfightManager() {
        return this.bossfightManager;
    }

    public void m_6457_(@NotNull ServerPlayer player) {
        super.m_6457_(player);
        this.bossInfo.m_6543_(player);
        this.tracking.add(player);
    }

    public void m_6452_(@NotNull ServerPlayer player) {
        super.m_6452_(player);
        this.bossInfo.m_6539_(player);
        this.tracking.remove(player);
    }

    @Override
    public SoundEvent getBossTheme() {
        return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_FINAL_BOSS_THEME.get();
    }

    @Override
    public boolean shouldPlayBossTheme() {
        return BossThemeEntity.super.shouldPlayBossTheme() && this.m_21223_() < this.m_21233_() && this.getState() == State.BOSSFIGHT;
    }

    @Override
    public Vec3 getPosition() {
        return this.m_20182_();
    }

    @Override
    public boolean isStillAlive() {
        return this.m_6084_();
    }

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public int getFadeTime() {
        return 40;
    }

    @Override
    public boolean checkConfig() {
        return (Boolean)WitherStormModConfig.CLIENT.playWitherStormTheme.get();
    }

    public void createPodiumCluster() {
        if (this.podiumCluster == null && this.podiumClusterUUID == null) {
            BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(this.m_9236_());
            BlockPos bottomCorner = new BlockPos(-5, -13, -5).m_121955_((Vec3i)this.m_20183_());
            assert (cluster != null);
            cluster.populate(bottomCorner, bottomCorner.m_7918_(10, 19, 10), blockstate -> true);
            cluster.setResetGravityOnLoad(false);
            cluster.setPhysics(false);
            cluster.m_20242_(true);
            cluster.setForceRender(true);
            this.m_9236_().m_7967_((Entity)cluster);
            this.podiumCluster = cluster;
        }
    }

    public void movePodiumCluster(Vec3 delta) {
        if (this.podiumCluster != null) {
            this.podiumCluster.m_20256_(delta);
        }
    }

    public void findPodiumCluster() {
        ServerLevel serverWorld;
        Entity entity;
        if (!this.m_9236_().f_46443_ && this.podiumClusterUUID != null && this.podiumCluster == null && (entity = (serverWorld = (ServerLevel)this.m_9236_()).m_8791_(this.podiumClusterUUID)) instanceof BlockClusterEntity) {
            this.podiumCluster = (BlockClusterEntity)entity;
        }
    }

    @Nullable
    public BlockClusterEntity getPodiumCluster() {
        return this.podiumCluster;
    }

    @Nullable
    public Mob summonRandomBowelsMobNearPlayer(int minRadius, int maxRadius, SimpleWeightedRandomList<EntityType<? extends Mob>> types) {
        ServerLevel world = (ServerLevel)this.m_9236_();
        List players = world.m_45976_(ServerPlayer.class, this.m_20191_().m_82400_(128.0));
        for (ServerPlayer player : players) {
            Mob entity = (Mob)Objects.requireNonNull(types.m_216820_(this.f_19796_).orElse(null)).m_20615_((Level)world);
            assert (entity != null);
            BlockPos playerPos = player.m_20183_();
            BlockPos randomPos = this.getRandomPosAroundPlayer(entity.m_6095_(), playerPos, minRadius, maxRadius, 10);
            if (randomPos == null || !this.hasEnoughSpace((Entity)entity, randomPos)) continue;
            entity.m_6034_((double)randomPos.m_123341_() + 0.5, (double)(randomPos.m_123342_() + 1), (double)randomPos.m_123343_() + 0.5);
            ForgeEventFactory.onFinalizeSpawn((Mob)entity, (ServerLevelAccessor)world, (DifficultyInstance)world.m_6436_(randomPos), (MobSpawnType)MobSpawnType.EVENT, null, null);
            if (entity instanceof Monster || entity instanceof AbstractGolem) {
                this.addSpeedAttribute(entity);
            }
            this.addHealthAttribute(entity);
            entity.m_8032_();
            entity.m_21373_();
            world.m_8767_((ParticleOptions)((SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get()), entity.m_20185_(), entity.m_20188_(), entity.m_20189_(), 20, this.f_19796_.m_188583_(), this.f_19796_.m_188583_(), this.f_19796_.m_188583_(), 0.2);
            world.m_8767_((ParticleOptions)ParticleTypes.f_123755_, entity.m_20185_(), entity.m_20188_(), entity.m_20189_(), 20, this.f_19796_.m_188583_(), this.f_19796_.m_188583_(), this.f_19796_.m_188583_(), 0.01);
            world.m_47205_((Entity)entity);
            return entity;
        }
        return null;
    }

    @Nullable
    public BlockPos getRandomPosAroundPlayer(EntityType<?> type, BlockPos playerPos, int minDistance, int maxDistance, int attempts) {
        for (int a = 0; a < attempts; ++a) {
            int z;
            int y;
            int x = playerPos.m_123341_() + this.f_19796_.m_188503_(maxDistance * 2 + 1) - maxDistance;
            BlockPos pos = new BlockPos(x, y = playerPos.m_123342_() + this.f_19796_.m_188503_(maxDistance), z = playerPos.m_123343_() + this.f_19796_.m_188503_(maxDistance * 2 + 1) - maxDistance);
            double distanceSquared = playerPos.m_123331_((Vec3i)pos);
            if (!(distanceSquared >= (double)(minDistance * minDistance)) || !(distanceSquared <= (double)(maxDistance * maxDistance))) continue;
            for (int i = 0; i < 30 && this.m_9236_().m_8055_(pos.m_7495_()).m_60713_(Blocks.f_50016_); ++i) {
                pos = pos.m_7495_();
            }
            if (!NaturalSpawner.m_47051_((SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (LevelReader)this.m_9236_(), (BlockPos)pos, type) || !(Math.sqrt(playerPos.m_123331_((Vec3i)pos)) > 6.0)) continue;
            return pos;
        }
        return null;
    }

    @Nullable
    public Mob summonRandomMob(int diameter, SimpleWeightedRandomList<EntityType<? extends Mob>> types) {
        ServerLevel world = (ServerLevel)this.m_9236_();
        Mob entity = (Mob)((EntityType)types.m_216820_(this.f_19796_).orElse(null)).m_20615_((Level)world);
        BlockPos pos = this.getRandomNearbyPos(entity.m_6095_(), diameter, 5);
        if (pos != null && this.hasEnoughSpace((Entity)entity, pos)) {
            entity.m_6034_((double)pos.m_123341_() + 0.5, (double)(pos.m_123342_() + 1), (double)pos.m_123343_() + 0.5);
            ForgeEventFactory.onFinalizeSpawn((Mob)entity, (ServerLevelAccessor)world, (DifficultyInstance)world.m_6436_(pos), (MobSpawnType)MobSpawnType.EVENT, null, null);
            if (entity instanceof Monster || entity instanceof AbstractGolem) {
                this.addSpeedAttribute(entity);
            }
            this.addHealthAttribute(entity);
            entity.m_8032_();
            entity.m_21373_();
            entity.m_21530_();
            world.m_8767_((ParticleOptions)((SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get()), entity.m_20185_(), entity.m_20188_(), entity.m_20189_(), 20, this.f_19796_.m_188583_(), this.f_19796_.m_188583_(), this.f_19796_.m_188583_(), 0.2);
            world.m_8767_((ParticleOptions)ParticleTypes.f_123755_, entity.m_20185_(), entity.m_20188_(), entity.m_20189_(), 20, this.f_19796_.m_188583_(), this.f_19796_.m_188583_(), this.f_19796_.m_188583_(), 0.01);
            world.m_47205_((Entity)entity);
            return entity;
        }
        return null;
    }

    @Nullable
    public BlockPos getRandomNearbyPos(EntityType<?> type, int diameter, int attempts) {
        BlockPos pos = null;
        BlockPos start = this.m_20183_();
        for (int i = 0; i < attempts; ++i) {
            int x = start.m_123341_() + this.f_19796_.m_188503_(diameter) - diameter / 2;
            int z = start.m_123343_() + this.f_19796_.m_188503_(diameter) - diameter / 2;
            int y = start.m_123342_();
            BlockPos currentPos = new BlockPos(x, y, z);
            for (int j = 0; j < 30 && this.m_9236_().m_8055_(currentPos.m_7495_()).m_60713_(Blocks.f_50016_); ++j) {
                currentPos = currentPos.m_7495_();
            }
            if (!NaturalSpawner.m_47051_((SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (LevelReader)this.m_9236_(), (BlockPos)currentPos, type) || !(Math.sqrt(this.m_20183_().m_123331_((Vec3i)currentPos)) > 6.0)) continue;
            pos = currentPos;
            break;
        }
        return pos;
    }

    private void addHealthAttribute(Mob mob) {
        Objects.requireNonNull(mob.m_21051_(Attributes.f_22276_)).m_22125_(new AttributeModifier("194fec31-b36e-41fc-ad72-02a5cb891def", -((mob.m_217043_().m_188500_() + 0.5) * 2.0), AttributeModifier.Operation.ADDITION));
    }

    private void addSpeedAttribute(Mob mob) {
        if (mob instanceof SickenedVindicator || mob instanceof SickenedIronGolem) {
            Objects.requireNonNull(mob.m_21051_(Attributes.f_22279_)).m_22125_(new AttributeModifier("5965c24d-8ac1-4f04-92ee-3d2724f976e8", -0.08, AttributeModifier.Operation.ADDITION));
        } else {
            Objects.requireNonNull(mob.m_21051_(Attributes.f_22279_)).m_22125_(new AttributeModifier("5965c24d-8ac1-4f04-92ee-3d2724f976e8", -0.06, AttributeModifier.Operation.ADDITION));
        }
    }

    private boolean hasEnoughSpace(Entity entity, BlockPos spawnPos) {
        for (BlockPos pos : BlockPos.m_121940_((BlockPos)spawnPos, (BlockPos)spawnPos.m_121955_((Vec3i)BlockPos.m_274561_((double)entity.m_20205_(), (double)entity.m_20206_(), (double)entity.m_20205_())))) {
            if (this.m_9236_().m_8055_(pos).m_60812_((BlockGetter)this.m_9236_(), pos).m_83281_()) continue;
            return false;
        }
        return true;
    }

    public BossfightPhase<CommandBlockEntity> getCurrentPhase() {
        if (this.m_9236_().f_46443_) {
            return this.getBossfightManager().getPhase((Integer)this.f_19804_.m_135370_(PHASE_KEY));
        }
        return this.getBossfightManager().getCurrentPhase();
    }

    public int getSpecialDeathTime() {
        return this.specialDeathTime;
    }

    protected float m_6121_() {
        return 4.0f;
    }

    protected SoundEvent m_7975_(@NotNull DamageSource source) {
        return (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_DAMAGE.get();
    }

    protected SoundEvent m_5592_() {
        return (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_DEATH.get();
    }

    public float m_6100_() {
        return 1.0f;
    }

    public boolean m_6149_() {
        return false;
    }

    protected boolean m_20073_() {
        return false;
    }

    public int getHitGlareTime() {
        return this.hitGlareTime;
    }

    public List<ServerPlayer> getOutsideBossBarViewers() {
        return this.outsideBossBarViewers;
    }

    public void removeOutsideBossBarViewer(ServerPlayer player) {
        this.outsideBossBarViewers.remove(player);
        this.bossInfo.m_6539_(player);
    }

    public boolean m_7301_(@NotNull MobEffectInstance instance) {
        return false;
    }

    @NotNull
    public PushReaction m_7752_() {
        return PushReaction.IGNORE;
    }

    public static class TentacleManager {
        private static final UUID KNOCKBACK_MODIFIER = UUID.fromString("72aeccbe-cdfe-41c9-9d21-261f29f6da60");
        private final CommandBlockEntity entity;
        public final int tentacles;
        public TentacleEntity[] tentacleStructure;
        private final UUID[] savedTentacleStructure;
        private final TentacleOffsets[] offsets;

        public TentacleManager(CommandBlockEntity entity, int amount, TentacleOffsets[] offsets) {
            this.entity = entity;
            this.tentacles = amount;
            this.tentacleStructure = new TentacleEntity[this.tentacles];
            this.savedTentacleStructure = new UUID[this.tentacles];
            this.offsets = offsets;
        }

        public void createTentacles() {
            for (int i = 0; i < this.tentacleStructure.length; ++i) {
                this.createTentacle(i);
            }
        }

        private void createTentacle(int index) {
            if (this.tentacleStructure[index] == null || !this.tentacleStructure[index].m_6084_()) {
                TentacleEntity tentacle = (TentacleEntity)((EntityType)WitherStormModEntityTypes.TENTACLE.get()).m_20615_(this.entity.m_9236_());
                this.getOffsetsForTentacle(index).apply(this.entity, tentacle);
                assert (tentacle != null);
                tentacle.setDormant(true);
                tentacle.lerpCurlTo(0.0f, 0.0f, 1);
                tentacle.m_20331_(true);
                tentacle.m_20242_(true);
                tentacle.setAnimationOffset(this.entity.f_19796_.m_188503_(35) * 10000);
                tentacle.setCanStrangle(false);
                Objects.requireNonNull(tentacle.m_21051_(Attributes.f_22282_)).m_22125_(new AttributeModifier(KNOCKBACK_MODIFIER, "Command block's tentacles knockback modifier", 5.0, AttributeModifier.Operation.ADDITION));
                this.tentacleStructure[index] = tentacle;
            }
        }

        public void removeTentacles() {
            for (TentacleEntity tentacle : this.tentacleStructure) {
                if (tentacle == null || !tentacle.m_6084_()) continue;
                tentacle.m_146870_();
            }
        }

        public void readdTentacles() {
            this.createTentacles();
            this.addTentacles();
        }

        public void addTentacles() {
            if (this.entity.isAddedToWorld() && this.entity.m_6084_()) {
                for (int i = 0; i < this.tentacleStructure.length; ++i) {
                    this.addTentacle(i);
                }
            }
        }

        private void addTentacle(int index) {
            TentacleEntity tentacle = this.tentacleStructure[index];
            if (tentacle != null && !tentacle.isAddedToWorld() && tentacle.m_6084_()) {
                this.getOffsetsForTentacle(index).apply(this.entity, tentacle);
                this.entity.m_9236_().m_7967_((Entity)tentacle);
            }
        }

        public void updateTentacles() {
            for (int i = 0; i < this.tentacleStructure.length; ++i) {
                TentacleEntity tentacle = this.tentacleStructure[i];
                if (tentacle == null || !tentacle.m_6084_()) continue;
                this.getOffsetsForTentacle(i).apply(this.entity, tentacle);
            }
        }

        public void killTentacles() {
            for (TentacleEntity tentacle : this.tentacleStructure) {
                if (tentacle == null) continue;
                tentacle.m_6074_();
            }
        }

        public void findTentacles(ServerLevel world) {
            for (int i = 0; i < this.savedTentacleStructure.length; ++i) {
                UUID uuid = this.savedTentacleStructure[i];
                TentacleEntity preexisting = this.tentacleStructure[i];
                if ((uuid == null || preexisting == null || preexisting.m_20148_().equals(uuid)) && preexisting != null) continue;
                assert (uuid != null);
                Entity entity = world.m_8791_(uuid);
                if (!(entity instanceof TentacleEntity)) continue;
                TentacleEntity found = (TentacleEntity)entity;
                if (preexisting != null) {
                    preexisting.m_146870_();
                }
                this.tentacleStructure[i] = found;
                this.savedTentacleStructure[i] = null;
            }
        }

        public TentacleOffsets getOffsetsForTentacle(int index) {
            return this.offsets[index];
        }

        public void addSaveData(CompoundTag compound) {
            ListTag list = new ListTag();
            for (TentacleEntity tentacle : this.tentacleStructure) {
                CompoundTag tentacleCompound = new CompoundTag();
                if (tentacle != null) {
                    tentacleCompound.m_128362_("UUID", tentacle.m_20148_());
                }
                list.add((Object)tentacleCompound);
            }
            compound.m_128365_("Tentacles", (Tag)list);
        }

        public void readSaveData(CompoundTag compound) {
            ListTag list = compound.m_128437_("Tentacles", 10);
            for (int i = 0; i < this.tentacleStructure.length; ++i) {
                CompoundTag tentacleCompound = list.m_128728_(i);
                if (!tentacleCompound.m_128441_("UUID") || this.savedTentacleStructure[i] != null) continue;
                this.savedTentacleStructure[i] = tentacleCompound.m_128342_("UUID");
            }
        }
    }

    /*
     * Uses 'sealed' constructs - enablewith --sealed true
     */
    public static enum State {
        IDLE{

            @Override
            public void tick(CommandBlockEntity entity) {
                super.tick(entity);
                if (this.modeTickDelay() < entity.getStateTicks()) {
                    entity.getMode().idleTick(entity);
                }
            }
        }
        ,
        PLAYING_DEAD{

            @Override
            public void tick(CommandBlockEntity entity) {
                super.tick(entity);
                if (this.modeTickDelay() < entity.getStateTicks()) {
                    entity.getMode().playingDeadTick(entity);
                }
                if (!entity.m_9236_().f_46443_) {
                    Level world = entity.m_9236_();
                    Player player = world.m_45946_(entity.searchablePlayersPredicate(), (LivingEntity)entity);
                    if (player != null) {
                        entity.setLuringPlayer(player);
                        entity.nextState();
                    }
                    int time = (Integer)WitherStormModConfig.SERVER.revivalTimeMinutes.get();
                    if (((Boolean)WitherStormModConfig.SERVER.revivalTimer.get()).booleanValue() && time > 0 && entity.getStateTicks() > time * 1200) {
                        entity.setState(REACTIVATING);
                    }
                }
            }
        }
        ,
        LURING{

            @Override
            public void tick(CommandBlockEntity entity) {
                super.tick(entity);
                if (this.modeTickDelay() < entity.getStateTicks()) {
                    entity.getMode().idleTick(entity);
                }
                if (!entity.m_9236_().f_46443_) {
                    Player player = entity.getLuringPlayer();
                    if (player != null && entity.searchablePlayersPredicate().m_26883_(12.0).m_26885_((LivingEntity)entity, (LivingEntity)player) && entity.getStateTicks() < 240) {
                        double speed = 0.025;
                        Vec3 motion = entity.m_20182_().m_82546_(player.m_20182_()).m_82541_().m_82542_(speed, speed, speed);
                        player.m_20334_(motion.f_82479_, player.m_20184_().f_82480_, motion.f_82481_);
                        WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)player), (Object)new PlayerMotionMessage(new Vec3(motion.f_82479_, player.m_20184_().f_82480_, motion.f_82481_)));
                        if ((double)player.m_20270_((Entity)entity) < 3.0) {
                            entity.setLuringPlayer(null);
                            entity.nextState();
                        }
                        for (int i = 0; i < 4; ++i) {
                            double x = player.m_20185_() + entity.f_19796_.m_188583_() * player.m_20191_().m_82362_() * 0.4;
                            double y = player.m_20191_().m_82399_().m_7098_() + entity.f_19796_.m_188583_() * player.m_20191_().m_82376_() * 0.4;
                            double z = player.m_20189_() + entity.f_19796_.m_188583_() * player.m_20191_().m_82385_() * 0.4;
                            Vec3 delta = entity.m_20299_(1.0f).m_82492_(x, y, z).m_82541_().m_82490_(0.1);
                            entity.m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, delta.m_7096_(), delta.m_7098_(), delta.m_7094_());
                        }
                    } else {
                        entity.nextState();
                    }
                }
            }

            @Override
            public void init(CommandBlockEntity entity) {
                super.init(entity);
                entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), SoundSource.HOSTILE, 5.0f, 1.0f);
                entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.AMBIENT, 10.0f, 1.0f);
                if (!entity.m_9236_().f_46443_) {
                    ShakeScreenMessage message = new ShakeScreenMessage(40.0f, 5.0f);
                    WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), (Object)message);
                }
                for (int i = 0; i < 10; ++i) {
                    entity.m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), entity.m_20185_(), entity.m_20188_(), entity.m_20189_(), entity.f_19796_.m_188583_() * 0.5, entity.f_19796_.m_188583_() * 0.5, entity.f_19796_.m_188583_() * 0.5);
                }
            }

            @Override
            public boolean shouldShowOwnerBossBar() {
                return true;
            }

            @Override
            public int modeTickDelay() {
                return 40;
            }
        }
        ,
        REACTIVATING{

            @Override
            public void tick(CommandBlockEntity entity) {
                WitherStormEntity owner;
                super.tick(entity);
                if (this.modeTickDelay() < entity.getStateTicks()) {
                    entity.getMode().protectTick(entity);
                }
                if (!entity.m_9236_().f_46443_ && entity.getOwner() != null && entity.getStateTicks() > 60 && !(owner = entity.getOwner()).isReviving()) {
                    owner.reviveFromPlayingDead();
                }
            }

            @Override
            public void init(CommandBlockEntity entity) {
                super.init(entity);
                if (!entity.m_9236_().f_46443_) {
                    ShakeScreenMessage message = new ShakeScreenMessage(120.0f, 5.0f);
                    WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), (Object)message);
                }
                entity.m_9236_().m_5594_(null, entity.m_20183_(), (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.AMBIENT, 10.0f, 1.0f);
            }

            @Override
            public boolean shouldShowOwnerBossBar() {
                return true;
            }

            @Override
            public int modeTickDelay() {
                return 20;
            }
        }
        ,
        BOSSFIGHT{

            @Override
            public void tick(CommandBlockEntity entity) {
                super.tick(entity);
                if (!entity.m_9236_().f_46443_) {
                    entity.getBossfightManager().tick();
                    if (!entity.getCurrentPhase().equals(BowelsBossFightStages.IDLE) || entity.m_21223_() < entity.m_21233_()) {
                        entity.bossInfo.m_8321_(true);
                    }
                }
                entity.getMode().idleTick(entity);
            }

            @Override
            public void init(CommandBlockEntity entity) {
                super.init(entity);
            }
        };


        public void tick(CommandBlockEntity entity) {
            entity.getMode().tick(entity, entity.getState());
            ++entity.stateTicks;
            if (this != PLAYING_DEAD) {
                for (int i = 0; i < 5; ++i) {
                    double x = entity.m_20185_() + entity.f_19796_.m_188583_();
                    double y = entity.m_20188_() + entity.f_19796_.m_188583_();
                    double z = entity.m_20189_() + entity.f_19796_.m_188583_();
                    float particleSpeed = (entity.m_21233_() - entity.m_21223_()) / entity.m_21233_() + 1.0f;
                    Vec3 delta = entity.m_20299_(1.0f).m_82492_(x, y, z).m_82541_().m_82490_(0.1 * (double)particleSpeed);
                    entity.m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, delta.m_7096_(), delta.m_7098_(), delta.m_7094_());
                }
            }
        }

        public void init(CommandBlockEntity entity) {
            entity.stateTicks = 0;
            entity.getMode().init(entity, entity.getState());
            entity.setProtectionYOffset(0.0f);
            if (entity.getOwner() != null) {
                this.initWithOwner(entity.getOwner(), entity);
            }
            entity.bossInfo.m_8321_(false);
        }

        public int modeTickDelay() {
            return 0;
        }

        public boolean shouldShowOwnerBossBar() {
            return false;
        }

        public void initWithOwner(WitherStormEntity owner, CommandBlockEntity entity) {
            if (owner.m_6084_()) {
                if (this.shouldShowOwnerBossBar()) {
                    owner.getBossInfo().ifPresent(info -> info.m_8321_(true));
                } else {
                    owner.getBossInfo().ifPresent(info -> info.m_8321_(!owner.isPlayingDead()));
                }
            }
        }
    }

    /*
     * Uses 'sealed' constructs - enablewith --sealed true
     */
    public static enum Mode {
        NONE,
        RIBS{

            @Override
            public void playingDeadTick(CommandBlockEntity entity) {
                super.playingDeadTick(entity);
                Random random = new Random(entity.m_20148_().getLeastSignificantBits());
                for (StructureAnimationHelper helper : this.getStructure(entity)) {
                    helper.lerpBaseTo(entity, -50.0f, 0.0f, Math.max(4, random.nextInt(11)));
                    helper.lerpTo(entity, Math.max(30.0f, (float)random.nextInt(131)), (float)random.nextInt(21) - 10.0f, Math.max(4, random.nextInt(11)));
                }
            }

            @Override
            public void idleTick(CommandBlockEntity entity) {
                super.idleTick(entity);
                entity.lerpStructureBaseRotTo(this.getStructure(entity), -50.0f, 0.0f, 10);
                entity.lerpStructureRotTo(this.getStructure(entity), 60.0f, 0.0f, 20);
            }

            @Override
            public void protectTick(CommandBlockEntity entity) {
                super.protectTick(entity);
                entity.lerpStructureBaseRotTo(this.getStructure(entity), 0.0f, 0.0f, 40);
                entity.lerpStructureRotTo(this.getStructure(entity), 70.0f, 0.0f, 20);
                if (entity.getProtectionYOffset() > -0.8f && entity.getStateTicks() > 20 + entity.getState().modeTickDelay()) {
                    entity.protectionYOffset -= 0.05f;
                }
            }

            @Override
            public void playMovementSound(CommandBlockEntity entity) {
                for (int i = 0; i < this.getStructure(entity).size(); ++i) {
                    double x = entity.f_19796_.m_188583_() * 3.0 + entity.m_20185_();
                    double y = entity.m_20188_();
                    double z = entity.f_19796_.m_188583_() * 3.0 + entity.m_20189_();
                    entity.m_9236_().m_6263_(null, x, y, z, (SoundEvent)WitherStormModSoundEvents.RIB_BONE_CRACK.get(), SoundSource.AMBIENT, 0.2f, 0.8f);
                }
            }

            @Override
            public List<StructureAnimationHelper> getStructure(CommandBlockEntity entity) {
                return entity.getRibStructure();
            }
        }
        ,
        TENTACLES{

            @Override
            public void tick(CommandBlockEntity entity, State state) {
                super.tick(entity, state);
                if (!entity.m_9236_().f_46443_) {
                    entity.tentacleStructure.addTentacles();
                    entity.tentacleStructure.updateTentacles();
                }
            }
        };


        public void tick(CommandBlockEntity entity, State state) {
            ++entity.modeAnim;
            if (!entity.m_9236_().f_46443_ && entity.f_19797_ % 120 == 0) {
                WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), (Object)new ModeAnimationMessage(entity.m_19879_(), entity.modeAnim));
            }
        }

        public void idleTick(CommandBlockEntity entity) {
        }

        public void playingDeadTick(CommandBlockEntity entity) {
        }

        public void protectTick(CommandBlockEntity entity) {
        }

        public void init(CommandBlockEntity entity, State state) {
            entity.modeAnim = 0;
            if (!entity.m_9236_().f_46443_ && entity.isAddedToWorld()) {
                if (this == TENTACLES) {
                    entity.tentacleStructure.readdTentacles();
                } else {
                    entity.tentacleStructure.removeTentacles();
                }
            }
        }

        public List<StructureAnimationHelper> getStructure(CommandBlockEntity entity) {
            return Lists.newArrayList();
        }

        public void playMovementSound(CommandBlockEntity entity) {
        }
    }

    public static class ModeAnimationMessage
    extends Packet {
        private int id;
        private int anim;

        public ModeAnimationMessage(int entityId, int anim) {
            super(true);
            this.id = entityId;
            this.anim = anim;
        }

        public ModeAnimationMessage() {
            super(false);
        }

        public void encode(FriendlyByteBuf buffer) {
            buffer.m_130130_(this.id);
            buffer.writeInt(this.anim);
        }

        public void decode(FriendlyByteBuf buffer) {
            this.id = buffer.m_130242_();
            this.anim = buffer.readInt();
        }

        public Runnable getProcessor(NetworkEvent.Context context) {
            return () -> DistExecutor.unsafeRunWhenOn((Dist)Dist.CLIENT, () -> () -> {
                Optional optional = (Optional)LogicalSidedProvider.CLIENTWORLD.get(context.getDirection().getReceptionSide());
                optional.ifPresent(world -> {
                    Entity entity = world.m_6815_(this.id);
                    if (entity instanceof CommandBlockEntity) {
                        CommandBlockEntity commandBlock = (CommandBlockEntity)entity;
                        commandBlock.modeAnim = this.anim;
                    }
                });
            });
        }
    }
}

