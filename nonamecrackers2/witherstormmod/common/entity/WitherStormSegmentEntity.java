/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerBossEvent
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.BodyRotationControl
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.BowelsInstanceManager;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.SegmentsManager;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.SymbiontSummoningManager;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller.WitherStormBodyController;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.HeadManager;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager;
import nonamecrackers2.witherstormmod.common.init.WitherStormModAttributes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.util.ClusterBuilderHelper;
import nonamecrackers2.witherstormmod.common.util.StormHeadOffsets;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
import org.jetbrains.annotations.NotNull;

public class WitherStormSegmentEntity
extends WitherStormEntity {
    private static final EntityDataAccessor<Optional<UUID>> PARENT_UUID = SynchedEntityData.m_135353_(WitherStormSegmentEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135041_);
    private static final EntityDimensions STARTING_SIZE = EntityDimensions.m_20395_((float)15.0f, (float)17.5f);
    private static final EntityDimensions EVOLVED_SIZE = EntityDimensions.m_20395_((float)15.0f, (float)17.5f);
    @Nullable
    private WitherStormEntity parent;
    private final int tillFreeFall;
    private int dropTime;
    private int nextDropTime;
    private int timeWithParent;
    @Nullable
    private Vec3 wantedSegmentPos;
    @Nullable
    private Vec3 randomStrollPos;
    private int tillNextRandomStroll;
    private float randomBodyRotAngleOffset;

    public WitherStormSegmentEntity(EntityType<? extends WitherStormEntity> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.nextDropTime = 120 + this.f_19796_.m_188503_(160);
        this.f_21364_ = 0;
        this.partsEnabled = false;
        this.shouldFollowUltimateTarget = false;
        this.chunkloads = false;
        this.shouldPlaySoundLoop = false;
        this.shouldPlayGlobalSounds = false;
        this.shouldIgnoreFormidibomb = true;
        this.tillFreeFall = Math.max(220, this.f_19796_.m_188503_(260));
    }

    @Override
    @NotNull
    protected BodyRotationControl m_7560_() {
        return new BodyController();
    }

    @Override
    protected HeadManager makeHeadManager() {
        return new HeadManager(this, StormHeadOffsets.SEGMENT);
    }

    @Override
    protected Optional<SegmentsManager> makeSegmentsManager() {
        return Optional.empty();
    }

    @Override
    protected Optional<SymbiontSummoningManager> makeSummoningManager() {
        return Optional.empty();
    }

    @Override
    protected Optional<BowelsInstanceManager> makeBowelsInstanceManager() {
        return Optional.empty();
    }

    @Override
    protected Optional<UltimateTargetManager> makeUltimateTargetManager() {
        return Optional.empty();
    }

    @Override
    protected Optional<ServerBossEvent> makeBossEvent() {
        return Optional.empty();
    }

    public WitherStormSegmentEntity(WitherStormEntity parent) {
        this((EntityType<? extends WitherStormEntity>)((EntityType)WitherStormModEntityTypes.WITHER_STORM_SEGMENT.get()), parent.m_9236_());
        this.setParent(parent);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.m_33035_().m_22268_((Attribute)WitherStormModAttributes.TARGET_STATIONARY_FLYING_SPEED.get(), 0.4).m_22268_((Attribute)WitherStormModAttributes.SLOW_FLYING_SPEED.get(), 0.05).m_22268_((Attribute)WitherStormModAttributes.EVOLUTION_SPEED.get(), 1.0).m_22268_(Attributes.f_22280_, 0.0).m_22268_(Attributes.f_22276_, 4000.0).m_22268_(Attributes.f_22279_, 0.6).m_22268_(Attributes.f_22277_, 160.0).m_22268_((Attribute)WitherStormModAttributes.HUNCHBACK_FOLLOW_RANGE.get(), 40.0).m_22268_(Attributes.f_22284_, 6.0);
    }

    @Override
    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(PARENT_UUID, Optional.empty());
    }

    @Override
    public void m_7380_(@NotNull CompoundTag compound) {
        super.m_7380_(compound);
        if (this.getParentUUID() != null) {
            compound.m_128362_("Parent", this.getParentUUID());
        }
        compound.m_128405_("TimeWithParent", this.timeWithParent);
    }

    @Override
    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        if (compound.m_128441_("Parent")) {
            this.setParentUUID(compound.m_128342_("Parent"));
        }
        this.timeWithParent = compound.m_128451_("TimeWithParent");
    }

    @Override
    public void m_8119_() {
        super.m_8119_();
        WitherStormEntity parent = this.getParent();
        if (parent == null && this.getParentUUID() != null) {
            List entities = this.m_9236_().m_45976_(WitherStormEntity.class, this.m_20191_().m_82400_(1000.0));
            if (!this.m_9236_().f_46443_) {
                entities = Lists.newArrayList((Iterable)((ServerLevel)this.m_9236_()).m_8583_());
            }
            for (Entity entity : entities) {
                if (!this.getParentUUID().equals(entity.m_20148_())) continue;
                WitherStormEntity storm = (WitherStormEntity)entity;
                this.setParent(storm);
            }
            if (this.isOnDistantRenderer()) {
                this.m_9236_().getCapability(WitherStormModClientCapabilities.DISTANT_RENDERER).ifPresent(renderer -> {
                    for (WitherStormEntity storm : renderer.getKnown()) {
                        if (!this.getParentUUID().equals(storm.m_20148_())) continue;
                        this.setParent(storm);
                    }
                });
            }
        }
        if (parent != null) {
            ++this.timeWithParent;
        }
    }

    @Override
    protected void m_8024_() {
        CommandBlockEntity commandBlock;
        super.m_8024_();
        WitherStormEntity parent = this.getParent();
        if (parent != null) {
            this.calculateDesiredPos();
            Vec3 desiredPos = this.getDesiredPos();
            Vec3 parentPos = new Vec3(parent.m_20185_() - this.m_20185_(), parent.m_20186_() - this.m_20186_(), parent.m_20189_() - this.m_20189_());
            if (parentPos.m_165924_() > 200.0) {
                assert (desiredPos != null);
                this.m_20219_(desiredPos);
            }
            if (parent.f_267362_.m_267731_() < 0.3f && this.m_20182_().m_82554_(Objects.requireNonNull(desiredPos)) < 50.0) {
                if (this.tillNextRandomStroll == 0) {
                    double x = this.f_19796_.m_188500_() * 20.0 - 10.0 + desiredPos.f_82479_;
                    double y = this.f_19796_.m_188500_() * 40.0 - 20.0 + desiredPos.f_82480_;
                    double z = this.f_19796_.m_188500_() * 20.0 - 10.0 + desiredPos.f_82481_;
                    this.randomStrollPos = new Vec3(x, y, z);
                    this.tillNextRandomStroll = 200 + this.f_19796_.m_188503_(100);
                    this.randomBodyRotAngleOffset = (this.f_19796_.m_188501_() * 20.0f + 20.0f) * (this.isMirrored() ? -1.0f : 1.0f);
                }
                if (this.tillNextRandomStroll > 0) {
                    --this.tillNextRandomStroll;
                }
                if (this.randomStrollPos != null && this.m_20182_().m_82554_(this.randomStrollPos) < 5.0) {
                    this.tillNextRandomStroll = 0;
                }
            } else {
                this.tillNextRandomStroll = 0;
                this.randomStrollPos = null;
                this.randomBodyRotAngleOffset = 0.0f;
            }
        }
        if ((commandBlock = this.getBowelsCommandBlock()) != null && commandBlock.m_21223_() < commandBlock.m_21233_()) {
            --this.nextDropTime;
            if (this.nextDropTime == 0) {
                this.dropTime = 10 + this.f_19796_.m_188503_(5);
                this.nextDropTime = (int)((double)(360 + this.f_19796_.m_188503_(160)) * Math.max(0.2, (double)(commandBlock.m_21223_() / commandBlock.m_21233_())));
            }
        }
        if (this.dropTime > 0) {
            --this.dropTime;
        }
    }

    @Override
    public double getHeightToAscendTo(Vec3 vector3d, double height, double ascendSpeed) {
        return this.getParent() != null ? this.getParent().getDesiredSegmentY(this.getSegmentIndex()) : super.getHeightToAscendTo(vector3d, height, ascendSpeed);
    }

    @Override
    public double getFlyingSpeed(Vec3 ultimateTarget) {
        WitherStormEntity parent = this.getParent();
        if (parent != null) {
            if (this.randomStrollPos == null) {
                double speed = parent.m_21133_(Attributes.f_22280_);
                speed = parent.shouldSpeedUp() ? (speed += parent.getDefaultChasingSpeed() + 0.05) : (speed += parent.getDefaultNormalSpeed() + 0.08);
                return speed;
            }
            return 0.01;
        }
        return super.getFlyingSpeed(ultimateTarget);
    }

    @Override
    protected Vec3 doFlying(Vec3 vector3d) {
        if (this.getParent() != null && !this.isDeadOrPlayingDead()) {
            double ascendSpeed = this.randomStrollPos == null ? 0.02 : 0.01;
            Vec3 wanted = this.randomStrollPos != null ? this.randomStrollPos : this.getDesiredPos();
            assert (wanted != null);
            double height = wanted.f_82480_;
            double d0 = vector3d.f_82480_;
            if (this.m_20186_() < height || !this.m_7090_() && this.m_20186_() < height + 5.0) {
                d0 = (height - this.m_20186_()) * ascendSpeed;
            }
            vector3d = new Vec3(vector3d.f_82479_, d0, vector3d.f_82481_);
            Vec3 vector3d1 = new Vec3(wanted.f_82479_ - this.m_20185_(), 0.0, wanted.f_82481_ - this.m_20189_());
            double speed = this.getFlyingSpeed(vector3d1);
            speed = Math.min(speed, Math.sqrt(this.m_20238_(wanted)) * 0.01);
            if (vector3d1.m_165924_() > 1.0) {
                Vec3 vector3d2 = vector3d1.m_82541_();
                vector3d = vector3d.m_82520_(vector3d2.f_82479_ * speed - vector3d.f_82479_ * 0.6, 0.0, vector3d2.f_82481_ * speed - vector3d.f_82481_ * 0.6);
            }
            return vector3d;
        }
        return super.doFlying(vector3d);
    }

    @Override
    protected void m_6153_() {
        super.m_6153_();
        if (this.m_9236_().f_46443_ && !this.m_20096_()) {
            float xOffset = (this.f_19796_.m_188501_() - 0.5f) * ((float)this.m_20191_().m_82362_() + 5.0f);
            float yOffset = (this.f_19796_.m_188501_() - 0.5f) * ((float)this.m_20191_().m_82376_() + 5.0f);
            float zOffset = (this.f_19796_.m_188501_() - 0.5f) * ((float)this.m_20191_().m_82385_() + 5.0f);
            this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123813_, this.m_20185_() + (double)xOffset, this.m_20188_() + (double)yOffset, this.m_20189_() + (double)zOffset, -5.0, 0.0, 0.0);
        }
        if (this.m_20096_()) {
            for (WitherStormHead head : this.headManager.getHeads()) {
                head.lerpHeadXTo(90.0f, 10.0f);
            }
        }
    }

    @Override
    @NotNull
    public EntityDimensions m_6972_(@NotNull Pose pose) {
        EntityDimensions size = this.getUnmodifiedDimensions(pose);
        if (((Boolean)WitherStormModConfig.SERVER.squashHitbox.get()).booleanValue() && this.getPhase() > 3) {
            size = EntityDimensions.m_20395_((float)size.f_20377_, (float)1.0f);
        }
        return size;
    }

    @Override
    public EntityDimensions getUnmodifiedDimensions(Pose pose) {
        EntityDimensions size = STARTING_SIZE;
        if (this.getPhase() == 7) {
            size = EVOLVED_SIZE;
        }
        return size;
    }

    @Override
    protected float m_6431_(@NotNull Pose pose, @NotNull EntityDimensions size) {
        return 10.0f;
    }

    @Override
    public boolean setPhase(int phase, int consumedEntities) {
        boolean result = false;
        if (this.getParent() != null) {
            result = false;
            this.f_19804_.m_135381_(PHASE, (Object)this.getParent().getPhase());
        } else if (phase <= 7) {
            this.f_19804_.m_135381_(PHASE, (Object)Math.max(phase, 6));
            result = phase >= 6;
        } else {
            result = false;
        }
        this.clusterRadius = (int)Math.max(1.0, (double)((float)this.getPhase() * 0.75f));
        this.entityConsumptionRadius = this.getPhase() > 3 ? 64 : 16;
        this.setConsumedEntities(consumedEntities);
        this.m_20090_();
        this.m_6210_();
        return result;
    }

    public void setParent(WitherStormEntity parent) {
        this.parent = parent;
        if (parent != null) {
            this.f_19804_.m_135381_(PARENT_UUID, Optional.of(parent.m_20148_()));
            assert (this.getParent() != null);
            this.getPlayDeadManager().setStateRaw(this.getParent().getPlayDeadManager().getState());
            this.getPlayDeadManager().setTickAmount(this.getParent().getPlayDeadManager().getTicks());
        } else {
            this.f_19804_.m_135381_(PARENT_UUID, Optional.empty());
        }
    }

    protected void setParentUUID(UUID uuid) {
        this.f_19804_.m_135381_(PARENT_UUID, Optional.of(uuid));
    }

    @Nullable
    public UUID getParentUUID() {
        return ((Optional)this.f_19804_.m_135370_(PARENT_UUID)).orElse(null);
    }

    @Nullable
    public WitherStormEntity getParent() {
        return this.parent;
    }

    @Override
    public LivingEntity getUltimateTarget() {
        return this.getParent() != null ? this.getParent().getUltimateTarget() : null;
    }

    @Override
    public int getConsumedEntities() {
        return this.getParent() != null ? this.getParent().getConsumedEntities() : 0;
    }

    @Override
    public void setConsumedEntities(int newAmount) {
    }

    @Override
    public void addToConsumedEntities(int amount) {
    }

    @Override
    public int getInvulnerableTicks() {
        return this.getParent() != null ? this.getParent().getInvulnerableTicks() : 0;
    }

    @Override
    public void setInvulnerableTicks(int ticks) {
    }

    @Override
    public boolean targetInUseBySegment(Entity entity) {
        WitherStormEntity parent = this.getParent();
        if (parent != null) {
            if (parent.alreadyATarget(entity, true)) {
                return true;
            }
            Optional<SegmentsManager> manager = this.getParent().getSegmentsManager();
            if (manager.isPresent()) {
                WitherStormSegmentEntity[] segments;
                for (WitherStormSegmentEntity segment : segments = manager.get().getSegments()) {
                    if (segment == null || this.equals(segment) || !segment.alreadyATarget(entity, true)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void createDebrisRings(boolean hidden) {
    }

    @Override
    public void createDebrisClusters(boolean hidden) {
    }

    public int getTimeTillFreeFall() {
        return this.tillFreeFall;
    }

    @Override
    public void onBigFall() {
        super.onBigFall();
        for (int i = 0; i < 6; ++i) {
            this.m_9236_().m_255391_((Entity)this, this.m_20185_(), this.m_20188_() - (double)i, this.m_20189_(), 16.0f, false, Level.ExplosionInteraction.MOB);
        }
    }

    public void regatherCapabilities() {
        this.gatherCapabilities();
    }

    @Override
    public boolean shouldDoCustomMovement() {
        PlayDeadManager.State state = this.getPlayDeadManager().getState();
        return this.dropTime <= 0 && this.getDeathTime() < this.getTimeTillFreeFall() && (state != PlayDeadManager.State.FALLING || this.getPlayDeadManager().getTicks() <= 200) && this.shouldDoCustomMovement;
    }

    @Override
    public boolean canFallOnBack() {
        return false;
    }

    @Override
    public Vec3 getUltimateTargetPos() {
        return this.getParent() != null ? this.getParent().getUltimateTargetPos() : super.getUltimateTargetPos();
    }

    @Override
    public CommandBlockEntity getBowelsCommandBlock() {
        return this.getParent() != null ? this.getParent().getBowelsCommandBlock() : super.getBowelsCommandBlock();
    }

    @Override
    public void dropDropsAt(Entity player) {
    }

    @Override
    protected boolean canTrackEntity(Entity entity) {
        if (!super.canTrackEntity(entity)) {
            return false;
        }
        return this.getParent() == null || !this.getParent().getTrackedEntities().contains(entity);
    }

    @Override
    public boolean isBeingTornApart() {
        return this.getParent() != null ? this.getParent().isBeingTornApart() : super.isBeingTornApart();
    }

    public int getTimeWithParent() {
        return this.timeWithParent;
    }

    @Override
    protected boolean isInsideOtherTractorBeam(LivingEntity entity, int head) {
        if (this.getParent() != null) {
            ArrayList storms = Lists.newArrayList((Object[])new WitherStormEntity[]{this.getParent()});
            this.getParent().getSegmentsManager().ifPresent(manager -> {
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
        return super.isInsideOtherTractorBeam(entity, head);
    }

    @Override
    public void dropMassCluster(int radius) {
    }

    @Override
    public boolean shouldShine() {
        return false;
    }

    private void calculateDesiredPos() {
        WitherStormEntity parent = this.getParent();
        int index = this.getSegmentIndex();
        assert (parent != null);
        this.wantedSegmentPos = new Vec3(parent.getDesiredSegmentX(index), parent.getDesiredSegmentY(index), parent.getDesiredSegmentZ(index));
    }

    @Nullable
    private Vec3 getDesiredPos() {
        if (this.wantedSegmentPos == null && this.getParent() != null) {
            this.calculateDesiredPos();
        }
        return this.wantedSegmentPos;
    }

    private int getSegmentIndex() {
        return this.isMirrored() ? 1 : 2;
    }

    @Override
    public boolean m_6469_(DamageSource source, float floatIn) {
        if (source.m_269533_(DamageTypeTags.f_268738_)) {
            return super.m_6469_(source, floatIn);
        }
        if (this.isCompletelyInvulnerable()) {
            return false;
        }
        return super.m_6469_(source, 0.0f);
    }

    @Override
    protected void splitCluster(BlockClusterEntity cluster, List<Entity> toAdd) {
        BlockClusterEntity split = cluster.splitAt(Direction.Axis.m_235688_((RandomSource)this.f_19796_));
        if (split != null) {
            this.m_9236_().m_7967_((Entity)split);
            if (this.f_19796_.m_188499_() && this.getParent() != null) {
                this.getParent().getTrackedEntities().trackEntityToConsume(split);
            } else {
                toAdd.add(split);
            }
        }
    }

    @Override
    public int loadRadius() {
        return 6;
    }

    @Override
    public void dropSmallMassCluster(int radius) {
        BlockClusterEntity cluster = ClusterBuilderHelper.buildSmallRandomDeathCluster(this.m_9236_(), this.f_19796_, radius);
        cluster.setSink(-1);
        cluster.m_146884_(this.m_20182_().m_82520_(this.f_19796_.m_188583_() * 5.0, (double)this.getUnmodifiedHeight() / 2.0 + this.f_19796_.m_188583_() * 5.0, this.f_19796_.m_188583_() * 5.0));
        cluster.m_20334_(this.f_19796_.m_188583_() * 0.4, this.f_19796_.m_188583_() * 0.3, this.f_19796_.m_188583_() * 0.4);
        cluster.setRotationDelta(new Vec2((float)this.f_19796_.m_188503_(90) * 0.3f / 2.0f, (float)this.f_19796_.m_188503_(90) * 0.3f / 2.0f));
        this.m_9236_().m_7967_((Entity)cluster);
    }

    @Override
    protected void dropDeathClusters() {
        if (this.getDeathTime() > 10 && this.getDeathTime() % 10 == 0) {
            this.dropSmallMassCluster(1);
        }
    }

    @Override
    protected void searchForPlayingJukeboxes() {
        WitherStormEntity parent = this.getParent();
        this.getPlayingJukeboxes().clear();
        if (parent != null) {
            for (BlockPos pos : parent.getPlayingJukeboxes()) {
                this.getPlayingJukeboxes().add(pos);
            }
        }
    }

    @Override
    public void storePet(Entity entity) {
        WitherStormEntity parent = this.getParent();
        if (parent != null) {
            parent.storePet(entity);
        } else {
            super.storePet(entity);
        }
    }

    private class BodyController
    extends WitherStormBodyController {
        public BodyController() {
            super(WitherStormSegmentEntity.this);
        }

        @Override
        protected float getYRotD() {
            return Mth.m_14177_((float)(super.getYRotD() + WitherStormSegmentEntity.this.randomBodyRotAngleOffset));
        }
    }
}

