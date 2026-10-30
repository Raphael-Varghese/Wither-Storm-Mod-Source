/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.control.BodyRotationControl
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.RangedAttackMob
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.WitherSkull
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.network.PacketDistributor
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity;

import com.mojang.datafixers.util.Pair;
import java.util.EnumSet;
import java.util.Objects;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.goal.LookAtDistractionGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.LookAtTargetGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.NearestDistractionGoal;
import nonamecrackers2.witherstormmod.common.entity.goal.YAffectedLookRandomlyGoal;
import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.packet.PlayerMotionMessage;
import nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions;
import nonamecrackers2.witherstormmod.common.util.ConditionalLookController;
import nonamecrackers2.witherstormmod.common.util.EmptyBodyController;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
import nonamecrackers2.witherstormmod.common.util.WitherStormModNBTUtil;
import org.jetbrains.annotations.NotNull;

public class WitherStormHeadEntity
extends Monster
implements WitherStormBase,
RangedAttackMob {
    private static final EntityDataAccessor<Boolean> IS_ACTIVE = SynchedEntityData.m_135353_(WitherStormHeadEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> IS_ROARING = SynchedEntityData.m_135353_(WitherStormHeadEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> IS_BITING = SynchedEntityData.m_135353_(WitherStormHeadEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> IS_HURT = SynchedEntityData.m_135353_(WitherStormHeadEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    @Nullable
    private Vec3 distractedPos;
    private int distractedTime;
    private int nextRoar;
    private int roarTime;
    private int shootTime;
    private int biteTime;
    private float mouthAnim;
    private float mouthAnimO;
    private float fadeAnimation;
    private float fadeAnimationO;
    private boolean isShaking;
    private float shakeAnim;
    private float shakeAnimO;
    private LookAtTargetGoal<WitherStormHeadEntity> lookGoal;
    private int specialDeathTime;

    public WitherStormHeadEntity(EntityType<? extends WitherStormHeadEntity> type, Level world) {
        super(type, world);
        this.nextRoar = 400 + this.f_19796_.m_188503_(600);
        this.shootTime = 100;
        this.m_21573_().m_7008_(true);
        this.f_19811_ = true;
        this.m_20242_(true);
        this.f_21365_ = new ConditionalLookController<WitherStormHeadEntity>(this, entity -> false);
    }

    @NotNull
    protected BodyRotationControl m_7560_() {
        return new EmptyBodyController((Mob)this);
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(IS_ACTIVE, (Object)true);
        this.f_19804_.m_135372_(IS_ROARING, (Object)false);
        this.f_19804_.m_135372_(IS_BITING, (Object)false);
        this.f_19804_.m_135372_(IS_HURT, (Object)false);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new DoNothingGoal(this));
        this.f_21345_.m_25352_(1, new LookAtDistractionGoal<WitherStormHeadEntity>(this, 0));
        this.lookGoal = new LookAtTargetGoal<WitherStormHeadEntity>(this, 0, s -> 3);
        this.f_21345_.m_25352_(2, this.lookGoal);
        this.f_21345_.m_25352_(3, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 12.0f));
        this.f_21345_.m_25352_(4, (Goal)new YAffectedLookRandomlyGoal((Mob)this));
        this.f_21346_.m_25352_(0, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
        this.f_21346_.m_25352_(1, new NearestDistractionGoal<WitherStormHeadEntity>(this, 0, WitherStormEntity.DISTRACTION_SELECTOR, 8));
        this.f_21346_.m_25352_(2, new AttackGoal<LivingEntity>(this, LivingEntity.class, 100, true, false, WitherStormEntity.DESTROYER_LIVING_ENTITY_SELECTOR.and(entity -> !this.isATarget((LivingEntity)entity))));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 60.0).m_22268_(Attributes.f_22277_, 40.0).m_22268_(Attributes.f_22279_, 0.0).m_22268_(Attributes.f_22284_, 8.0);
    }

    public void m_7380_(CompoundTag compound) {
        compound.m_128379_("IsRoaring", this.isRoaring());
        compound.m_128405_("RoarTime", this.roarTime);
        super.m_7380_(compound);
        compound.m_128379_("IsActive", ((Boolean)this.f_19804_.m_135370_(IS_ACTIVE)).booleanValue());
        if (this.getDistractedPos(0) != null) {
            compound.m_128365_("DistractedPos", (Tag)WitherStormModNBTUtil.writeVector3d(Objects.requireNonNull(this.getDistractedPos(0))));
        }
        compound.m_128405_("DistractedTime", this.distractedTime);
        compound.m_128350_("YBodyRot", this.f_20883_);
        compound.m_128379_("IsHurt", this.isHurt());
    }

    public void m_7378_(CompoundTag compound) {
        this.f_19804_.m_135381_(IS_ROARING, (Object)compound.m_128471_("IsRoaring"));
        this.roarTime = compound.m_128451_("RoarTime");
        super.m_7378_(compound);
        if (compound.m_128441_("IsActive")) {
            this.f_19804_.m_135381_(IS_ACTIVE, (Object)compound.m_128471_("IsActive"));
        }
        if (compound.m_128441_("DistractedPos")) {
            this.setDistractedPos(0, WitherStormModNBTUtil.readVector3d(compound.m_128469_("DistractedPos")));
        }
        this.distractedTime = compound.m_128451_("DistractedTime");
        if (compound.m_128441_("YBodyRot")) {
            this.f_20884_ = this.f_20883_ = compound.m_128457_("YBodyRot");
        }
        if (compound.m_128441_("IsHurt")) {
            this.setHurt(compound.m_128471_("IsHurt"));
        }
    }

    public void m_8107_() {
        super.m_8107_();
        if (this.distractedTime > 0) {
            --this.distractedTime;
            if (this.distractedTime == 0) {
                this.setDistractedPos(0, null);
            }
        }
        if (!this.isDeadOrPlayingDead()) {
            if (!this.m_9236_().f_46443_) {
                if (this.nextRoar > 0) {
                    --this.nextRoar;
                    if (this.nextRoar == 0) {
                        this.setRoar(false);
                        this.nextRoar = 400 + this.f_19796_.m_188503_(600);
                        this.roarTime = 40;
                    }
                }
                if (this.roarTime > 0) {
                    --this.roarTime;
                    if (this.roarTime == 0) {
                        this.disableRoar();
                    }
                }
                if (this.biteTime > 0) {
                    --this.biteTime;
                    if (this.biteTime == 0) {
                        this.setBite(false);
                        this.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_BITE.get(), this.m_6121_(), 1.0f);
                    }
                }
            }
            if (this.isHurt() && this.f_19797_ % 20 == 0 && this.shootTime > 60) {
                this.isShaking = true;
            }
            if (this.isHurt() && this.shootTime > 0) {
                LivingEntity entity;
                --this.shootTime;
                if (this.shootTime < 60 && (entity = this.m_5448_()) != null) {
                    this.setLookAt(0, entity.m_20182_());
                    this.isShaking = false;
                }
                if (this.shootTime == 0) {
                    this.shootSkullAtTarget();
                    this.shootTime = 60 + this.f_19796_.m_188503_(40);
                    this.isShaking = false;
                }
            }
        }
    }

    public void m_8119_() {
        super.m_8119_();
        this.mouthAnimO = this.mouthAnim;
        if (!this.isBiting() && this.isRoaring()) {
            this.mouthAnim += (1.0f - this.mouthAnim) * 0.15f + 0.04f;
            if (this.mouthAnim > 2.0f) {
                this.mouthAnim = 2.0f;
            }
        } else if (this.isBiting()) {
            this.mouthAnim += (1.0f - this.mouthAnim) * 0.16f + 0.1f;
            if (this.mouthAnim > 1.4f) {
                this.mouthAnim = 1.4f;
            }
        } else {
            this.mouthAnim += -this.mouthAnim * 0.16f - 0.02f;
            if (this.mouthAnim < 0.0f) {
                this.mouthAnim = 0.0f;
            }
        }
        this.fadeAnimationO = this.fadeAnimation;
        if (this.isPlayingDead()) {
            this.fadeAnimation += 1.0f + this.f_19796_.m_188501_() * 2.0f;
            if (this.fadeAnimation > 300.0f) {
                this.fadeAnimation = 300.0f;
            }
        } else {
            this.fadeAnimation -= 1.0f + this.f_19796_.m_188501_() * 2.0f;
            if (this.fadeAnimation < 0.0f) {
                this.fadeAnimation = 0.0f;
            }
        }
        this.shakeAnimO = this.shakeAnim;
        if (this.isShaking) {
            this.shakeAnim += 0.02f + this.f_19796_.m_188501_() * 0.05f;
            if (this.shakeAnimO >= 2.0f) {
                this.shakeAnimO = 0.0f;
                this.shakeAnim = 0.0f;
                this.isShaking = false;
            }
        }
        if (this.m_9236_().f_46443_ && ((Boolean)WitherStormModConfig.CLIENT.tractorBeamParticles.get()).booleanValue() && this.tractorBeamActive(0)) {
            for (int amount = 0; amount < 5; ++amount) {
                double randZ;
                double randY;
                float x = this.m_146909_();
                float y = this.f_20885_;
                Vec3 lookVec = this.getViewVector(x, y, this.f_19796_.m_188501_() * 200.0f);
                Vec3 headPos = this.getHeadPos(0);
                Vec3 pos = headPos.m_82549_(lookVec).m_82520_(0.0, 1.5, 0.0);
                double distanceFromHead = Math.sqrt(pos.m_82557_(headPos));
                double distanceAllowed = distanceFromHead * 2.0 * 0.02;
                double randX = this.f_19796_.m_188583_() * distanceAllowed;
                Pair<Boolean, Integer> result = TractorBeamHelper.isInsideTractorBeam(pos = pos.m_82520_(randX, randY = this.f_19796_.m_188583_() * distanceAllowed, randZ = this.f_19796_.m_188583_() * distanceAllowed), this, 4.0);
                if (!((Boolean)result.getFirst()).booleanValue()) continue;
                Vec3 delta = pos.m_82546_(headPos).m_82541_().m_82490_(-0.8);
                if (WitherStormMod.isAprilFools() && ((Boolean)WitherStormModConfig.CLIENT.aprilFools.get()).booleanValue()) {
                    this.m_9236_().m_6493_((ParticleOptions)ParticleTypes.f_123750_, true, pos.f_82479_, pos.f_82480_, pos.f_82481_, delta.f_82479_, delta.f_82480_, delta.f_82481_);
                    continue;
                }
                this.m_9236_().m_6493_((ParticleOptions)new TractorBeamParticleOptions(this.m_19879_(), (Integer)result.getSecond()), true, pos.f_82479_, pos.f_82480_, pos.f_82481_, delta.f_82479_, delta.f_82480_, delta.f_82481_);
            }
        }
    }

    public void m_6667_(@NotNull DamageSource source) {
        super.m_6667_(source);
        this.setRoar(true);
    }

    protected void m_6153_() {
        ++this.specialDeathTime;
        if (!this.m_9236_().f_46443_) {
            this.m_146926_(this.m_146909_() - 1.0f);
            if (this.m_146909_() < -50.0f) {
                this.m_146926_(-50.0f);
            }
        }
        if (this.specialDeathTime > 120) {
            this.m_142687_(Entity.RemovalReason.KILLED);
        }
    }

    public void m_5997_(double deltaX, double deltaY, double deltaZ) {
    }

    protected SoundEvent m_7515_() {
        return !this.isPlayingDead() ? (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_GROWL.get() : null;
    }

    protected SoundEvent m_7975_(@NotNull DamageSource source) {
        return (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_HURT.get();
    }

    public int m_8100_() {
        return 80 + this.f_19796_.m_188503_(40);
    }

    protected float m_6121_() {
        return 8.0f;
    }

    protected void m_8024_() {
        if (!this.isPlayingDead() && !this.isHurt()) {
            if (this.m_5448_() != null) {
                LivingEntity living;
                boolean flag;
                double speed = 0.2;
                LivingEntity target = this.m_5448_();
                Entity vehicle = target.m_20202_();
                Vec3 delta = this.m_20182_().m_82546_(target.m_20182_()).m_82541_().m_82490_(speed);
                boolean bl = flag = !(vehicle instanceof LivingEntity) || WitherStormEntity.DESTROYER_LIVING_ENTITY_SELECTOR.test(living = (LivingEntity)vehicle);
                if (target.m_20159_() && ((Boolean)WitherStormModConfig.COMMON.shouldPickUpVehicles.get()).booleanValue() && flag) {
                    vehicle.m_20256_(delta);
                } else {
                    target.m_20256_(delta);
                }
                if (target instanceof Player) {
                    WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> WitherStormHeadEntity.lambda$customServerAiStep$3((Entity)target)), (Object)new PlayerMotionMessage(delta));
                }
                if (this.m_20191_().m_82381_(target.m_20191_())) {
                    if (target instanceof Player) {
                        Player player = (Player)target;
                        if (!player.m_21224_() && player.m_6084_() && !player.m_150110_().f_35934_) {
                            player.m_6469_(WitherStormModDamageTypes.witherStormAttack((LivingEntity)this), 3.5f);
                        }
                        this.setBite(true);
                    } else {
                        target.m_6469_(WitherStormModDamageTypes.witherStormAttackMob((LivingEntity)this), Float.MAX_VALUE);
                        this.setBite(true);
                    }
                }
            }
            if (this.f_19797_ % 80 == 0) {
                this.m_5634_(10.0f);
            }
        }
    }

    @Override
    public void setDistractedPos(int head, @Nullable Vec3 pos) {
        this.distractedPos = pos;
    }

    @Override
    @Nullable
    public Vec3 getDistractedPos(int head) {
        return this.distractedPos;
    }

    @Override
    public void makeDistracted(Vec3 pos, int time, int head) {
        this.distractedTime = time;
        this.setDistractedPos(head, pos);
    }

    public void setRoar(boolean screaming) {
        this.f_19804_.m_135381_(IS_ROARING, (Object)true);
        SoundEvent event = (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_ROAR.get();
        if (screaming) {
            event = (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_HURT.get();
        }
        this.m_5496_(event, this.m_6121_(), 1.0f);
    }

    public void setRoarTime(int time) {
        this.roarTime = time;
    }

    public void disableRoar() {
        this.f_19804_.m_135381_(IS_ROARING, (Object)false);
    }

    public boolean isRoaring() {
        return (Boolean)this.f_19804_.m_135370_(IS_ROARING);
    }

    public void setBite(boolean biting) {
        if (biting) {
            this.biteTime = 10;
        }
        this.f_19804_.m_135381_(IS_BITING, (Object)biting);
    }

    public boolean isBiting() {
        return (Boolean)this.f_19804_.m_135370_(IS_BITING);
    }

    public boolean m_6469_(DamageSource source, float amount) {
        if (source.m_269533_(DamageTypeTags.f_268738_)) {
            return super.m_6469_(source, amount);
        }
        if (!this.isPlayingDead() && !this.isHurt()) {
            if (!this.isRoaring()) {
                this.setRoar(true);
                this.roarTime = 20;
            }
            boolean flag = super.m_6469_(source, amount);
            if (this.m_21223_() < this.m_21233_() / 1.5f) {
                this.setHurt(true);
            }
            return flag;
        }
        return false;
    }

    public boolean m_5830_() {
        return false;
    }

    public void m_6043_() {
        this.f_20891_ = 0;
    }

    public boolean m_147207_(@NotNull MobEffectInstance effect, @Nullable Entity entity) {
        return false;
    }

    @NotNull
    public MobType m_6336_() {
        return WitherStormModMobTypes.SICKENED;
    }

    public boolean m_6072_() {
        return false;
    }

    public boolean m_7301_(@NotNull MobEffectInstance effect) {
        return false;
    }

    public boolean m_5789_() {
        return !this.isPlayingDead();
    }

    protected float m_6431_(@NotNull Pose pose, EntityDimensions size) {
        return size.f_20378_ / 1.5f;
    }

    public void m_5802_(@NotNull BlockPos pos) {
    }

    public boolean m_6573_(@NotNull Player player) {
        return false;
    }

    public float getMouthAnimation(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.mouthAnimO, (float)this.mouthAnim);
    }

    public boolean m_6094_() {
        return false;
    }

    public boolean m_6063_() {
        return false;
    }

    @Override
    public boolean isDeadOrPlayingDead() {
        return this.isPlayingDead() || this.m_21224_();
    }

    @Override
    public boolean isPlayingDead() {
        return (Boolean)this.f_19804_.m_135370_(IS_ACTIVE) == false;
    }

    public void setActive(boolean active) {
        this.f_19804_.m_135381_(IS_ACTIVE, (Object)active);
    }

    @Override
    public float getMouthAnimation(int head, float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.mouthAnimO, (float)this.mouthAnim);
    }

    @Override
    public float getBrokenJawAnimation(int head, float partialTicks) {
        return 0.0f;
    }

    @Override
    public float getFadeAnimation(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.fadeAnimationO, (float)this.fadeAnimation);
    }

    @Override
    public float getFadeAnimation() {
        return this.fadeAnimation;
    }

    @Override
    public float getTentacleAnimation(float partialTicks) {
        return 0.0f;
    }

    @Override
    public float getHeadYRot(int head) {
        return this.f_20885_;
    }

    @Override
    public float getHeadYRotO(int head) {
        return this.f_20886_;
    }

    @Override
    public float getHeadXRot(int head) {
        return this.m_146909_();
    }

    @Override
    public float getHeadXRotO(int head) {
        return this.f_19860_;
    }

    @Override
    public float getXBodyRot() {
        return 0.0f;
    }

    @Override
    public float getXBodyRotO() {
        return 0.0f;
    }

    @Override
    public boolean isPosBehindBack(Vec3 pos) {
        return false;
    }

    public void m_147240_(double strength, double x, double z) {
    }

    public boolean m_20068_() {
        return true;
    }

    public boolean isATarget(LivingEntity entity) {
        for (WitherStormHeadEntity entity1 : this.m_9236_().m_45976_(WitherStormHeadEntity.class, this.m_20191_().m_82400_(this.m_21133_(Attributes.f_22277_)))) {
            if (entity1.isHurt() || entity1.m_5448_() != entity) continue;
            return true;
        }
        return false;
    }

    public boolean isHurt() {
        return this.isHeadInjured(0);
    }

    @Override
    public boolean isHeadInjured(int head) {
        return (Boolean)this.f_19804_.m_135370_(IS_HURT);
    }

    public void setHurt(boolean hurt) {
        this.f_19804_.m_135381_(IS_HURT, (Object)hurt);
        if (hurt) {
            this.f_21345_.m_25363_(this.lookGoal);
        } else {
            this.f_21345_.m_25363_(this.lookGoal);
            this.f_21345_.m_25352_(2, this.lookGoal);
        }
    }

    @Override
    public float getHeadShakeAnim(int head, float partialTicks) {
        float lerp = Mth.m_14036_((float)Mth.m_14179_((float)partialTicks, (float)this.shakeAnimO, (float)this.shakeAnim), (float)0.0f, (float)1.0f);
        return Mth.m_14031_((float)(lerp * (float)Math.PI)) * Mth.m_14031_((float)(lerp * (float)Math.PI * 12.0f)) * 0.05f * (float)Math.PI;
    }

    @Override
    public boolean areOtherHeadsDisabled() {
        return false;
    }

    @Override
    public Vec3 getHeadPos(int head) {
        return this.m_20191_().m_82399_();
    }

    @Override
    public int getTotalHeads() {
        return 1;
    }

    @Override
    public void setLookAt(int head, Vec3 pos, int steps) {
        if (pos != null) {
            this.f_21365_.m_24964_(pos);
        }
    }

    @Override
    public LivingEntity getTarget(int head) {
        return this.m_5448_();
    }

    @Override
    public void setTarget(int head, LivingEntity entity) {
        this.m_6710_(entity);
    }

    @Override
    public boolean tractorBeamActive(int head) {
        return WitherStormBase.super.tractorBeamActive(head) && !this.isPlayingDead();
    }

    @Override
    public boolean canSee(int head, Entity entity) {
        return this.m_142582_(entity);
    }

    public void m_6504_(@NotNull LivingEntity head, float p_33318_) {
    }

    private void shootSkullAtTarget() {
        LivingEntity entity = this.m_5448_();
        if (entity != null) {
            Vec3 direction = entity.m_20182_().m_82546_(this.m_20182_()).m_82541_();
            WitherSkull witherSkull = new WitherSkull(this.m_9236_(), (LivingEntity)this, direction.f_82479_, direction.f_82480_, direction.f_82481_);
            this.m_9236_().m_7967_((Entity)witherSkull);
            if (this.m_217043_().m_188503_(16) == 1) {
                witherSkull.m_37629_(true);
            }
            witherSkull.m_5496_(SoundEvents.f_12558_, 2.0f, 1.0f);
        }
    }

    private static /* synthetic */ ServerPlayer lambda$customServerAiStep$3(Entity target) {
        return (ServerPlayer)target;
    }

    private static class DoNothingGoal
    extends Goal {
        private final WitherStormHeadEntity entity;

        public DoNothingGoal(WitherStormHeadEntity entity) {
            this.m_7021_(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
            this.entity = entity;
        }

        public boolean m_8036_() {
            return this.entity.isPlayingDead();
        }
    }

    private static class AttackGoal<T extends LivingEntity>
    extends NearestAttackableTargetGoal<T> {
        public AttackGoal(WitherStormHeadEntity entity, Class<T> clazz, int randomInterval, boolean mustSee, boolean mustReach, Predicate<LivingEntity> predicate) {
            super((Mob)entity, clazz, randomInterval, mustSee, mustReach, predicate);
        }

        @NotNull
        protected AABB m_7255_(double followRange) {
            return this.f_26135_.m_20191_().m_82400_(followRange);
        }
    }
}

