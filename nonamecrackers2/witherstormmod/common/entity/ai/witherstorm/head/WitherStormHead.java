/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.network.PacketDistributor
 */
package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head;

import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.HeadManager;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCriteriaTriggers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.packet.NotifyHeadInjuryMessage;
import nonamecrackers2.witherstormmod.common.packet.OnHeadAttackedMessage;
import nonamecrackers2.witherstormmod.common.util.HeadConfiguration;
import nonamecrackers2.witherstormmod.common.util.WitherStormModNBTUtil;

public abstract class WitherStormHead {
    protected final WitherStormEntity storm;
    protected final int headIndex;
    private final boolean syncHeadRotations;
    protected float lerpXRot;
    protected float lerpYRot;
    protected float lerpXSteps;
    protected float lerpYSteps;
    protected float mouthAnim;
    protected float mouthAnimO;
    protected float jawBrokenAnimation;
    protected float jawBrokenAnimationO;
    protected boolean isHeadShaking;
    protected float headShakeAnim;
    protected float headShakeAnimO;
    @Nullable
    protected Vec3 headPos = Vec3.f_82478_;
    @Nullable
    protected Vec3 headPosO = Vec3.f_82478_;
    @Nullable
    protected Vec3 distractionPos;
    protected int distractedTime;
    @Nullable
    protected AABB box;
    protected int nextClusterPickup;
    protected int idleClusterPickup;
    protected int nextRoarTick;
    protected int roarTick;
    protected int biteTick;
    protected int nextShake;
    protected double tractorBeamCutoffDistance = -1.0;
    public int nextHeadUpdate;
    public int idleHeadUpdates;
    protected int headHits;
    protected int requiredHits;
    protected int headHurtDuration;

    public WitherStormHead(WitherStormEntity storm, int headIndex, boolean syncHeadRotations) {
        if (headIndex >= storm.getTotalHeads()) {
            throw new IllegalArgumentException("Head index too large! Maximum amount of heads allowed is " + storm.getTotalHeads());
        }
        this.storm = storm;
        this.headIndex = headIndex;
        this.syncHeadRotations = syncHeadRotations;
        this.requiredHits = this.getRandomHitCount();
    }

    public void update(int phase) {
        this.requiredHits = this.getRandomHitCount();
    }

    public boolean syncHeadRotations() {
        return this.syncHeadRotations;
    }

    public int getIndex() {
        return this.headIndex;
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.m_128379_("IsRoaring", this.isRoaring());
        tag.m_128405_("RoaringTime", this.getRoarTicks());
        if (this.getDistractedPos() != null) {
            tag.m_128365_("DistractedPos", (Tag)WitherStormModNBTUtil.writeVector3d(this.getDistractedPos()));
        }
        tag.m_128405_("DistractedTime", this.getDistractedTime());
        tag.m_128405_("AttackCooldown", this.getHeadInjureAttemptCooldown());
        tag.m_128405_("InjuryTime", this.getHeadInjuryTicks());
        tag.m_128405_("Hits", this.getHeadHits());
        return tag;
    }

    public void read(CompoundTag tag) {
        if (tag.m_128441_("IsRoaring")) {
            this.setRoar(tag.m_128471_("IsRoaring"));
        }
        if (tag.m_128441_("RoaringTime")) {
            this.setRoarTicks(tag.m_128451_("RoaringTime"));
        }
        if (tag.m_128441_("DistractedPos")) {
            this.setDistractedPos(WitherStormModNBTUtil.readVector3d(tag.m_128469_("DistractedPos")));
        }
        if (tag.m_128441_("DistractedTime")) {
            this.distractedTime = tag.m_128451_("DistractedTime");
        }
        if (tag.m_128441_("AttackCooldown")) {
            this.setHeadInjureAttemptCooldown(tag.m_128451_("AttackCooldown"));
        }
        if (tag.m_128441_("InjuryTime")) {
            this.setHeadInjuryTicks(tag.m_128451_("InjuryTime"));
        }
        if (tag.m_128441_("Hits")) {
            this.setHeadHitCount(tag.m_128451_("Hits"));
        }
    }

    public abstract float getHeadXRot();

    public abstract float getHeadYRot();

    public abstract float getHeadXRotO();

    public abstract float getHeadYRotO();

    public abstract void setHeadXRot(float var1);

    public abstract void setHeadYRot(float var1);

    @Nullable
    public abstract LivingEntity getTarget();

    public abstract void setTarget(@Nullable LivingEntity var1);

    public abstract void setLookPos(@Nullable Vec3 var1, int var2);

    public abstract void doHeadLookLogic();

    protected boolean canShootNormalWitherSkulls() {
        return !this.storm.tractorBeamActive(this.headIndex);
    }

    protected boolean canShootFlamingSkull() {
        return this.storm.tractorBeamActive(this.headIndex);
    }

    public float getHeadXRot(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.getHeadXRotO(), (float)this.getHeadXRot());
    }

    public float getHeadYRot(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.getHeadYRotO(), (float)this.getHeadYRot());
    }

    public Vec3 getHeadPos() {
        return this.headPos;
    }

    public Vec3 getHeadPosO() {
        return this.headPosO;
    }

    public void baseTick(HeadConfiguration config) {
        this.headPosO = this.headPos;
        this.headPos = this.calculateHeadPosition(config);
        float size = this.storm.getPhase() > 3 ? 3.0f : 0.5f;
        this.box = new AABB(this.headPos.f_82479_ - (double)size, this.headPos.f_82480_ - (double)size, this.headPos.f_82481_ - (double)size, this.headPos.f_82479_ + (double)size, this.headPos.f_82480_ + (double)size, this.headPos.f_82481_ + (double)size);
    }

    public void tick() {
        float rotNew;
        float rot;
        this.handleAnimations();
        if (this.lerpXSteps > 0.0f) {
            rot = this.getHeadXRot();
            rotNew = (float)((double)rot + Mth.m_14175_((double)((double)this.lerpXRot - (double)rot)) / (double)this.lerpXSteps);
            this.setHeadXRot(rotNew);
            this.lerpXSteps -= 1.0f;
        }
        if (this.lerpYSteps > 0.0f) {
            rot = this.getHeadYRot();
            rotNew = (float)((double)rot + Mth.m_14175_((double)((double)this.lerpYRot - (double)rot)) / (double)this.lerpYSteps);
            this.setHeadYRot(rotNew);
            this.lerpYSteps -= 1.0f;
        }
        if (!this.storm.isOnDistantRenderer()) {
            float x = this.getHeadXRot();
            float y = this.getHeadYRot();
            Vec3 end = this.headPos.m_82549_(this.storm.getViewVector(x, y, 250.0f));
            BlockHitResult hitResult = this.storm.m_9236_().m_45547_(new ClipContext(this.headPos, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null));
            this.tractorBeamCutoffDistance = hitResult.m_6662_() == HitResult.Type.BLOCK ? this.headPos.m_82554_(hitResult.m_82450_()) : -1.0;
        }
        if (this.headHurtDuration > 0) {
            --this.headHurtDuration;
        }
    }

    public void doAi() {
        this.doHeadLookLogic();
        if (this.getHeadInjuryTicks() > 0) {
            this.decreaseHeadInjuryTicks();
            if (this.getHeadInjuryTicks() == 0 && !this.storm.isDeadOrPlayingDead()) {
                this.storm.playSound((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_TRACTOR_BEAM_ACTIVATES.get(), this.headIndex, this.storm.m_6121_() + 2.5f, 1.0f);
            }
        }
        if (this.getHeadInjureAttemptCooldown() > 0) {
            this.decreaseInjureAttemptCooldown();
        }
        if (this.isHeadInjured() && !this.storm.isDeadOrPlayingDead() && this.nextShake > 0) {
            --this.nextShake;
            if (this.nextShake == 0) {
                this.isHeadShaking = true;
            }
        }
    }

    public void doServerAi() {
        if (this.storm.tractorBeamActive(this.headIndex) && this.storm.f_19797_ >= this.nextClusterPickup) {
            this.nextClusterPickup = this.storm instanceof WitherStormSegmentEntity ? this.storm.f_19797_ + 12 : (this.storm.getPhase() <= 2 ? this.storm.f_19797_ + 24 : (this.storm.getPhase() == 3 ? this.storm.f_19797_ + 15 : (this.storm.getPhase() == 4 ? this.storm.f_19797_ + 5 + this.storm.m_217043_().m_188503_(20) : (this.storm.getPhase() == 5 ? this.storm.f_19797_ + 5 + this.storm.m_217043_().m_188503_(15) : (this.storm.getPhase() >= 6 ? this.storm.f_19797_ + this.storm.m_217043_().m_188503_(15) : this.storm.f_19797_ + 45)))));
            ++this.idleClusterPickup;
            float x = this.getHeadXRot();
            float y = this.getHeadYRot();
            if (((Boolean)WitherStormModConfig.SERVER.tractorBeamClusterPickUp.get()).booleanValue()) {
                if (this.storm instanceof WitherStormSegmentEntity) {
                    this.storm.createClusterFromLook(x, y, (int)Math.max(1.0, Math.min(1.25, 1.0 + 0.125 * this.storm.m_217043_().m_188583_())), this.headIndex);
                } else {
                    this.storm.createClusterFromLook(x, y, (int)this.storm.getClusterRadius(), this.headIndex);
                }
            }
            if (((Boolean)WitherStormModConfig.SERVER.tractorBeamsRemoveFluids.get()).booleanValue()) {
                this.storm.removeFluidFromLook(x, y, this.headIndex);
            }
            this.idleClusterPickup = 0;
        }
        if (this.storm.f_19797_ >= this.nextHeadUpdate) {
            int k3;
            this.nextHeadUpdate = this.storm.getPhase() < 4 ? this.storm.f_19797_ + 10 + this.storm.m_217043_().m_188503_(10) : this.storm.f_19797_ + 1200 + this.storm.m_217043_().m_188503_(120);
            if ((k3 = this.idleHeadUpdates++) > 15) {
                if (this.canShootNormalWitherSkulls()) {
                    Vec3 pos = this.getHeadPos();
                    double d0 = Mth.m_216263_((RandomSource)this.storm.m_217043_(), (double)(pos.f_82479_ - 10.0), (double)(pos.f_82481_ + 10.0));
                    double d1 = Mth.m_216263_((RandomSource)this.storm.m_217043_(), (double)(pos.f_82480_ - 5.0), (double)(pos.f_82480_ + 5.0));
                    double d2 = Mth.m_216263_((RandomSource)this.storm.m_217043_(), (double)(pos.f_82481_ - 10.0), (double)(pos.f_82481_ + 10.0));
                    this.storm.performRangedAttack(this.headIndex, d0, d1, d2, true);
                }
                this.idleHeadUpdates = 0;
            }
            if (this.getTarget() != null) {
                if (this.canShootNormalWitherSkulls()) {
                    this.storm.performRangedAttack(this.headIndex, this.getTarget());
                }
                this.nextHeadUpdate = this.storm.getPhase() < 4 ? this.storm.f_19797_ + 40 + this.storm.m_217043_().m_188503_(20) : this.storm.f_19797_ + 1800 + this.storm.m_217043_().m_188503_(160);
                this.idleHeadUpdates = 0;
            } else {
                this.nextHeadUpdate = this.storm.f_19797_ + 40 + this.storm.m_217043_().m_188503_(20);
            }
        }
        if (this.nextRoarTick == 0) {
            this.nextRoarTick = this.storm.f_19797_ + 200 + this.storm.m_217043_().m_188503_(200);
        }
        if (this.storm.f_19797_ > this.nextRoarTick) {
            if (this.canShootFlamingSkull() && !this.storm.isAttractingFormidibomb()) {
                Vec3 view = this.storm.getViewVector(this.getHeadXRot(), this.getHeadYRot(), 1.0f);
                Vec3 headPos = this.getHeadPos();
                this.storm.spawnFlamingWitherSkull(this.headIndex, view.f_82479_ + headPos.f_82479_, view.f_82480_ + headPos.f_82480_, view.f_82481_ + headPos.f_82481_);
            }
            this.doRoar(this.storm.isHeadInjured(this.headIndex));
            int min = (Integer)WitherStormModConfig.SERVER.minimumRoarInterval.get() * 20;
            int max = (Integer)WitherStormModConfig.SERVER.maximumRoarInterval.get() * 20;
            int randomMax = max - min;
            int random = 0;
            if (randomMax > 0) {
                random = this.storm.m_217043_().m_188503_(randomMax);
            }
            this.nextRoarTick = this.storm.f_19797_ + min + random;
        }
        if (this.isRoaring()) {
            ++this.roarTick;
            if (this.roarTick > 40) {
                this.setRoar(false);
                this.roarTick = 0;
            }
        }
        if (this.isBiting()) {
            ++this.biteTick;
            if (this.biteTick > 10) {
                this.setBiting(false);
                this.biteTick = 0;
                this.storm.playSound((SoundEvent)WitherStormModSoundEvents.WITHER_STORM_BITE.get(), this.headIndex, Math.max(2.0f, this.storm.m_6121_()), 1.0f);
            }
        }
        if (this.distractedTime > 0) {
            --this.distractedTime;
            if (this.distractedTime <= 0) {
                this.setDistractedPos(null);
            }
        }
        if (this.isHeadInjured()) {
            this.storm.getRemovableGoalsManager().removeGoals(WitherStormEntity.REMOVABLE_LOOK_GOALS[this.headIndex], this.storm.getGoalSelectorForHead(this.headIndex));
            this.storm.getRemovableGoalsManager().removeGoals(WitherStormEntity.REMOVABLE_TARGET_GOALS[this.headIndex], this.storm.getTargetSelectorForHead(this.headIndex));
        } else {
            this.storm.getRemovableGoalsManager().putGoals(WitherStormEntity.REMOVABLE_LOOK_GOALS[this.headIndex], this.storm.getGoalSelectorForHead(this.headIndex));
            this.storm.getRemovableGoalsManager().putGoals(WitherStormEntity.REMOVABLE_TARGET_GOALS[this.headIndex], this.storm.getTargetSelectorForHead(this.headIndex));
        }
    }

    public Vec3 calculateHeadPosition(HeadConfiguration config) {
        float yBodyRot = (this.storm.f_20883_ + 180.0f) * ((float)Math.PI / 180);
        float yBodyRot90 = (this.storm.f_20883_ + 270.0f) * ((float)Math.PI / 180);
        float xBodyRot = -(this.storm.xBodyRot + 270.0f) * ((float)Math.PI / 180);
        Vec3 headOffset = config.getOffsetForHead(this.headIndex);
        double staticX = headOffset.f_82479_;
        double staticY = headOffset.f_82480_;
        double staticZ = headOffset.f_82481_;
        double xOffset = (double)Mth.m_14089_((float)yBodyRot) * staticX;
        double zOffset = (double)Mth.m_14031_((float)yBodyRot) * staticX;
        float offset = (float)Mth.m_14136_((double)staticZ, (double)staticY);
        double rawX = Mth.m_14089_((float)(xBodyRot + offset)) * Mth.m_14089_((float)yBodyRot90);
        double rawY = Mth.m_14031_((float)(xBodyRot + offset));
        double rawZ = Mth.m_14089_((float)(xBodyRot + offset)) * Mth.m_14031_((float)yBodyRot90);
        double sqrt = Math.sqrt(staticZ * staticZ + staticY * staticY);
        double x = xOffset + this.storm.m_20185_() + rawX * sqrt;
        double y = this.storm.m_20186_() + rawY * sqrt;
        double z = zOffset + this.storm.m_20189_() + rawZ * sqrt;
        return new Vec3(x, y, z);
    }

    protected void handleAnimations() {
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
        this.jawBrokenAnimationO = this.jawBrokenAnimation;
        if (this.storm.m_20096_() && this.storm.isDeadOrPlayingDead()) {
            this.jawBrokenAnimation += (1.0f - this.jawBrokenAnimation) * 0.2f + 0.05f;
            if (this.jawBrokenAnimation > 1.5f) {
                this.jawBrokenAnimation = 1.5f;
            }
        } else {
            this.jawBrokenAnimation += -this.jawBrokenAnimation * 0.2f - 0.05f;
            if (this.jawBrokenAnimation < 0.0f) {
                this.jawBrokenAnimation = 0.0f;
            }
        }
        this.headShakeAnimO = this.headShakeAnim;
        if (this.isHeadShaking) {
            this.headShakeAnim += 0.02f + this.storm.m_217043_().m_188501_() * 0.05f;
            if (this.headShakeAnimO >= 2.0f) {
                this.headShakeAnimO = 0.0f;
                this.headShakeAnim = 0.0f;
                this.isHeadShaking = false;
                this.nextShake = 20 + this.storm.m_217043_().m_188503_(20);
            }
        }
    }

    public boolean isRoaring() {
        return (Boolean)this.storm.m_20088_().m_135370_(HeadManager.HEAD_ROARS.get(this.headIndex));
    }

    public void setRoarTicks(int ticks) {
        this.roarTick = ticks;
    }

    public int getRoarTicks() {
        return this.roarTick;
    }

    public boolean isBiting() {
        return (Boolean)this.storm.m_20088_().m_135370_(HeadManager.HEADS_BITING.get(this.headIndex));
    }

    public void setRoar(boolean flag) {
        this.storm.m_20088_().m_135381_(HeadManager.HEAD_ROARS.get(this.headIndex), (Object)flag);
    }

    public void setBiting(boolean flag) {
        this.storm.m_20088_().m_135381_(HeadManager.HEADS_BITING.get(this.headIndex), (Object)flag);
    }

    public void doRoar(boolean screaming) {
        this.setRoar(true);
        SoundEvent event = (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_ROAR.get();
        if (screaming) {
            event = (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_HURT.get();
        }
        if (this.storm.areOtherHeadsDisabled() || this.storm.getPhase() < 4 && this.storm.getPhase() > 1) {
            if (this.headIndex == 0) {
                this.storm.playSound(event, this.headIndex, Math.max(6.0f, this.storm.m_6121_() + 2.5f), 1.0f);
            }
        } else if (this.storm.getPhase() > 3 && !this.storm.areOtherHeadsDisabled()) {
            this.storm.playSound(event, this.headIndex, Math.max(6.0f, this.storm.m_6121_() + 2.5f), 1.0f);
        }
    }

    public void startBiting() {
        this.biteTick = 0;
        this.storm.m_20088_().m_135381_(HeadManager.HEADS_BITING.get(this.headIndex), (Object)true);
    }

    @Nullable
    public Vec3 getDistractedPos() {
        return this.distractionPos;
    }

    public void setDistractedPos(@Nullable Vec3 pos) {
        this.distractionPos = pos;
    }

    public void makeDistracted(Vec3 pos, int time) {
        this.distractedTime = time;
        this.setDistractedPos(pos);
    }

    public void lerpHeadTo(float lerpToX, float lerpToY, float steps) {
        this.lerpXRot = lerpToX;
        this.lerpYRot = lerpToY;
        this.lerpXSteps = steps;
        this.lerpYSteps = steps;
    }

    public void lerpHeadXTo(float lerpToX, float steps) {
        this.lerpXRot = lerpToX;
        this.lerpXSteps = steps;
    }

    public void lerpHeadYTo(float lerpToY, float steps) {
        this.lerpYRot = lerpToY;
        this.lerpYSteps = steps;
    }

    public int getDistractedTime() {
        return this.distractedTime;
    }

    public int getNextRoarTick() {
        return this.nextRoarTick;
    }

    public void setNextRoarTick(int ticks) {
        this.nextRoarTick = ticks;
    }

    public float getMouthAnimation(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.mouthAnimO, (float)this.mouthAnim);
    }

    public float getBrokenJawAnimation(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.jawBrokenAnimationO, (float)this.jawBrokenAnimation);
    }

    public float getRollAngle(float partialTicks) {
        float lerp = Mth.m_14036_((float)Mth.m_14179_((float)partialTicks, (float)this.headShakeAnimO, (float)this.headShakeAnim), (float)0.0f, (float)1.0f);
        return Mth.m_14031_((float)(lerp * (float)Math.PI)) * Mth.m_14031_((float)(lerp * (float)Math.PI * 12.0f)) * 0.05f * (float)Math.PI;
    }

    public double getTractorBeamCutoff() {
        return this.tractorBeamCutoffDistance;
    }

    public boolean canSee(Entity entity) {
        Vec3 pos = this.getHeadPos();
        Vec3 entityPos = entity.m_20299_(1.0f);
        if (entity.m_9236_() != this.storm.m_9236_()) {
            return false;
        }
        return this.storm.m_9236_().m_45547_(new ClipContext(pos, entityPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, (Entity)this.storm)).m_6662_() == HitResult.Type.MISS;
    }

    protected int getRandomHitCount() {
        if (this.storm.getPhase() > 3) {
            return 3 + this.storm.m_217043_().m_188503_(3);
        }
        return 1 + this.storm.m_217043_().m_188503_(2);
    }

    public int getHeadInjureAttemptCooldown() {
        return (Integer)this.storm.m_20088_().m_135370_(HeadManager.INJURE_ATTEMPT_COOLDOWN.get(this.headIndex));
    }

    public void setHeadInjureAttemptCooldown(int amount) {
        this.storm.m_20088_().m_135381_(HeadManager.INJURE_ATTEMPT_COOLDOWN.get(this.headIndex), (Object)amount);
    }

    public int getHeadInjuryTicks() {
        return (Integer)this.storm.m_20088_().m_135370_(HeadManager.HURT_HEAD_TIME.get(this.headIndex));
    }

    public void setHeadInjuryTicks(int amount) {
        this.storm.m_20088_().m_135381_(HeadManager.HURT_HEAD_TIME.get(this.headIndex), (Object)amount);
    }

    public int getHeadHits() {
        return this.headHits;
    }

    public void setHeadHitCount(int amount) {
        this.headHits = amount;
    }

    public void decreaseHeadInjuryTicks() {
        this.setHeadInjuryTicks(this.getHeadInjuryTicks() - 1);
    }

    public void decreaseInjureAttemptCooldown() {
        this.setHeadInjureAttemptCooldown(this.getHeadInjureAttemptCooldown() - 1);
    }

    public boolean isHeadInjured() {
        return this.getHeadInjuryTicks() > 0;
    }

    public int getHeadHurtDuration() {
        return this.headHurtDuration;
    }

    public void hurt(@Nullable Entity entity, int injuryTime) {
        this.setHeadInjuryTicks(injuryTime);
        this.doRoar(true);
        Vec3 view = this.storm.getViewVector(this.getHeadXRot(), this.getHeadYRot(), 1.0f);
        Vec3 headPos = this.getHeadPos();
        this.storm.spawnBlueFlamingWitherSkull(this.headIndex, view.f_82479_ + headPos.f_82479_, view.f_82480_ + headPos.f_82480_, view.f_82481_ + headPos.f_82481_);
        this.setRoarTicks(20);
        this.setHeadInjureAttemptCooldown(40);
        if (!this.storm.m_9236_().m_5776_()) {
            this.requiredHits = this.getRandomHitCount();
            this.headHits = 0;
            NotifyHeadInjuryMessage message = new NotifyHeadInjuryMessage(this.storm, this.headIndex);
            ResourceKey dimension = this.storm.m_9236_().m_46472_();
            WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> dimension), (Object)message);
            if (entity instanceof ServerPlayer) {
                ServerPlayer player = (ServerPlayer)entity;
                if (this.storm.alreadyATarget(entity, true)) {
                    entity.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> data.makeInvulnerable((Integer)WitherStormModConfig.SERVER.headEscapeTime.get() * 20 + player.m_217043_().m_188503_(80)));
                    WitherStormModCriteriaTriggers.ESCAPE_STORM.trigger(player, this.storm);
                }
            }
        }
    }

    public boolean checkAndCountAttack() {
        if (((Boolean)WitherStormModConfig.SERVER.canAttackHeads.get()).booleanValue()) {
            WitherStormModPacketHandlers.MAIN.send(PacketDistributor.TRACKING_ENTITY.with(() -> this.storm), (Object)new OnHeadAttackedMessage(this.storm.m_19879_(), this.headIndex));
            ++this.headHits;
            if (this.headHits >= this.requiredHits) {
                return true;
            }
            this.doRoar(true);
            this.setRoarTicks(20);
            return false;
        }
        return false;
    }

    public void handleHeadAttackedOnClient() {
        this.headHurtDuration = 10;
        this.isHeadShaking = true;
    }

    @Nullable
    public AABB getBoundingBox() {
        return this.box;
    }
}

