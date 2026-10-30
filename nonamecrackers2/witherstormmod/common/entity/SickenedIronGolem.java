/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.BlockParticleOption
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.MeleeAttackGoal
 *  net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.animal.AbstractGolem
 *  net.minecraft.world.entity.animal.IronGolem$Crackiness
 *  net.minecraft.world.entity.monster.Enemy
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
import org.jetbrains.annotations.NotNull;

public class SickenedIronGolem
extends AbstractGolem
implements WitherSickened,
Enemy {
    private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.m_135353_(SickenedIronGolem.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private final WitherSickened.Data sickenedData = new WitherSickened.Data();
    private int attackAnimationTick;

    public SickenedIronGolem(EntityType<? extends SickenedIronGolem> type, Level level) {
        super(type, level);
        this.m_274367_(1.0f);
    }

    @NotNull
    public MobType m_6336_() {
        return WitherStormModMobTypes.SICKENED;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 60.0).m_22268_(Attributes.f_22277_, 48.0).m_22268_(Attributes.f_22279_, 0.25).m_22268_(Attributes.f_22281_, 10.0).m_22268_(Attributes.f_22278_, 1.0);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new MeleeAttackGoal((PathfinderMob)this, 1.1, true));
        this.f_21345_.m_25352_(1, (Goal)new MoveTowardsTargetGoal((PathfinderMob)this, 1.0, 32.0f));
        this.f_21345_.m_25352_(2, (Goal)new WaterAvoidingRandomStrollGoal((PathfinderMob)this, 1.0));
        this.f_21345_.m_25352_(3, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0f));
        this.f_21345_.m_25352_(4, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(0, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
        this.f_21346_.m_25352_(1, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
        this.f_21346_.m_25352_(2, (Goal)new SickenedMobsAttackGoal((Mob)this));
    }

    public void m_8119_() {
        super.m_8119_();
        this.sickenedTick();
    }

    public <T extends Mob> T m_21406_(EntityType<T> type, boolean loot) {
        return (T)this.sickenedConvertTo(type, loot);
    }

    protected InteractionResult m_6071_(Player player, InteractionHand hand) {
        InteractionResult result = this.sickenedMobInteract(player, hand);
        if (result != null) {
            return result;
        }
        ItemStack itemstack = player.m_21120_(hand);
        if (!itemstack.m_150930_(Items.f_42416_)) {
            return InteractionResult.PASS;
        }
        float f = this.m_21223_();
        this.m_5634_(this.m_21233_() / 4.0f);
        if (this.m_21223_() == f) {
            return InteractionResult.PASS;
        }
        float f1 = 1.0f + (this.f_19796_.m_188501_() - this.f_19796_.m_188501_()) * 0.2f;
        this.m_5496_(SoundEvents.f_12009_, 1.0f, f1);
        if (!player.m_150110_().f_35937_) {
            itemstack.m_41774_(1);
        }
        return InteractionResult.m_19078_((boolean)this.m_9236_().f_46443_);
    }

    public boolean m_6785_(double dist) {
        return this.sickenedRemoveWhenFarAway(dist);
    }

    public boolean m_147207_(MobEffectInstance effect, Entity entity) {
        return this.sickenedAddEffect(effect, entity) ? super.m_147207_(effect, entity) : false;
    }

    public boolean m_214076_(ServerLevel level, LivingEntity entity) {
        return this.sickenedInfect(entity);
    }

    @Override
    public int getInfectedHealAmount() {
        return 6;
    }

    public float m_6100_() {
        return this.sickenedGetVoicePitch();
    }

    public void m_7380_(CompoundTag tag) {
        super.m_7380_(tag);
        this.sickenedSave(tag);
    }

    public void m_7378_(CompoundTag tag) {
        super.m_7378_(tag);
        this.sickenedRead(tag);
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(CONVERTING, (Object)false);
    }

    @Override
    public WitherSickened.Data getData() {
        return this.sickenedData;
    }

    @Override
    public boolean isConverting() {
        return (Boolean)this.f_19804_.m_135370_(CONVERTING);
    }

    @Override
    public void setConverting(boolean flag) {
        this.f_19804_.m_135381_(CONVERTING, (Object)flag);
    }

    @Override
    public float getSickenedEquipmentDropChance(EquipmentSlot slot) {
        return this.m_21519_(slot);
    }

    public boolean m_6549_(EntityType<?> type) {
        return type != WitherStormModEntityTypes.WITHERED_SYMBIONT.get() && super.m_6549_(type);
    }

    public boolean m_6779_(LivingEntity entity) {
        return super.m_6779_(entity) && this.sickenedCanAttack(entity);
    }

    protected int m_7302_(int supply) {
        return supply;
    }

    public void m_8107_() {
        super.m_8107_();
        if (this.attackAnimationTick > 0) {
            --this.attackAnimationTick;
        }
        if (this.m_20184_().m_165925_() > 2.500000277905201E-7 && this.f_19796_.m_188503_(5) == 0) {
            int i = Mth.m_14107_((double)this.m_20185_());
            int j = Mth.m_14107_((double)(this.m_20186_() - 0.2));
            int k = Mth.m_14107_((double)this.m_20189_());
            BlockPos pos = new BlockPos(i, j, k);
            BlockState blockstate = this.m_9236_().m_8055_(pos);
            if (!blockstate.m_60795_()) {
                this.m_9236_().m_7106_((ParticleOptions)new BlockParticleOption(ParticleTypes.f_123794_, blockstate).setPos(pos), this.m_20185_() + ((double)this.f_19796_.m_188501_() - 0.5) * (double)this.m_20205_(), this.m_20186_() + 0.1, this.m_20189_() + ((double)this.f_19796_.m_188501_() - 0.5) * (double)this.m_20205_(), 4.0 * ((double)this.f_19796_.m_188501_() - 0.5), 0.5, ((double)this.f_19796_.m_188501_() - 0.5) * 4.0);
            }
        }
    }

    public boolean m_7327_(Entity target) {
        this.attackAnimationTick = 10;
        this.m_9236_().m_7605_((Entity)this, (byte)4);
        float damage = (float)this.m_21133_(Attributes.f_22281_);
        float damageModified = (int)damage > 0 ? damage / 2.0f + (float)this.f_19796_.m_188503_((int)damage) : damage;
        boolean flag = target.m_6469_(this.m_269291_().m_269333_((LivingEntity)this), damageModified);
        if (flag) {
            double d2;
            if (target instanceof LivingEntity) {
                LivingEntity living = (LivingEntity)target;
                d2 = living.m_21133_(Attributes.f_22278_);
            } else {
                d2 = 0.0;
            }
            double d0 = d2;
            double d1 = Math.max(0.0, 1.0 - d0);
            target.m_20256_(target.m_20184_().m_82520_(0.0, 0.4 * d1, 0.0));
            this.m_19970_((LivingEntity)this, target);
            this.addWitherToTarget(target);
        }
        this.m_5496_(SoundEvents.f_12057_, 1.0f, 1.0f);
        return flag;
    }

    public boolean m_6469_(DamageSource source, float amount) {
        if (!this.sickenedCanBeHurt(source, amount)) {
            return false;
        }
        IronGolem.Crackiness crackiness = this.getCrackiness();
        boolean flag = super.m_6469_(source, amount);
        if (flag && this.getCrackiness() != crackiness) {
            this.m_216990_(SoundEvents.f_12058_);
        }
        return flag;
    }

    public IronGolem.Crackiness getCrackiness() {
        return IronGolem.Crackiness.m_28901_((float)(this.m_21223_() / this.m_21233_()));
    }

    public void m_7822_(byte event) {
        if (event == 4) {
            this.attackAnimationTick = 10;
            this.m_5496_(SoundEvents.f_12057_, 1.0f, 1.0f);
        } else {
            super.m_7822_(event);
        }
    }

    public int getAttackAnimationTick() {
        return this.attackAnimationTick;
    }

    protected SoundEvent m_7975_(DamageSource source) {
        return SoundEvents.f_12008_;
    }

    protected SoundEvent m_5592_() {
        return SoundEvents.f_12059_;
    }

    protected void m_7355_(BlockPos pos, BlockState state) {
        this.m_5496_(SoundEvents.f_12010_, 1.0f, 1.0f);
    }

    protected Vec3 m_7939_() {
        return new Vec3(0.0, (double)(0.875f * this.m_20192_()), (double)(this.m_20205_() * 0.4f));
    }
}

