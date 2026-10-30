/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.AgeableMob
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
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.animal.Bee
 *  net.minecraft.world.entity.animal.Bee$BeeGoToHiveGoal
 *  net.minecraft.world.entity.monster.Enemy
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity;

import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AgeableMob;
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
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;
import org.jetbrains.annotations.NotNull;

public class SickenedBee
extends Bee
implements WitherSickened,
Enemy {
    private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.m_135353_(SickenedBee.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    public static final Predicate<BlockState> TAINTABLE = state -> state.m_204336_(WitherStormModBlockTags.SICKENED_BEE_CAN_CONVERT) && !state.m_204336_(WitherStormModBlockTags.TAINTED_BLOCKS) && !state.m_60713_(Blocks.f_50070_);
    private final WitherSickened.Data sickenedData = new WitherSickened.Data();
    private int underWaterTicks;

    public SickenedBee(EntityType<? extends SickenedBee> type, Level level) {
        super(type, level);
    }

    @NotNull
    public MobType m_6336_() {
        return WitherStormModMobTypes.SICKENED;
    }

    protected void m_8099_() {
        super.m_8099_();
        this.f_21345_.m_262460_(g -> {
            String className = g.getClass().getName();
            return className.equals("net.minecraft.world.entity.animal.Bee$BeeGrowCropGoal") || className.equals("net.minecraft.world.entity.animal.Bee$BeeLocateHiveGoal") || g instanceof Bee.BeeGoToHiveGoal;
        });
        this.f_21345_.m_25352_(7, (Goal)new TaintGoal());
        this.f_21346_.m_262460_(g -> true);
        this.f_21346_.m_25352_(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]).m_26044_(new Class[0]));
        this.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
        this.f_21346_.m_25352_(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
    }

    protected void m_8024_() {
        this.underWaterTicks = this.m_20072_() ? ++this.underWaterTicks : 0;
        if (this.underWaterTicks > 200) {
            this.m_6469_(this.m_269291_().m_269063_(), 1.0f);
        }
        if (!this.m_9236_().f_46443_) {
            this.m_21666_((ServerLevel)this.m_9236_(), false);
        }
    }

    public static AttributeSupplier.Builder m_27858_() {
        return Monster.m_33035_().m_22268_(Attributes.f_22279_, 0.3).m_22268_(Attributes.f_22276_, 15.0).m_22268_(Attributes.f_22280_, 1.2).m_22268_(Attributes.f_22281_, 2.0).m_22268_(Attributes.f_22277_, 48.0);
    }

    public boolean m_27854_() {
        return false;
    }

    public boolean m_27856_() {
        return false;
    }

    public void m_8107_() {
        super.m_8107_();
    }

    public void m_8119_() {
        super.m_8119_();
        this.sickenedTick();
        if (this.f_19797_ % 4 == 0) {
            double d0 = this.m_20185_() + (double)this.f_19796_.m_188501_();
            double d1 = this.m_20186_() + (double)this.f_19796_.m_188501_();
            double d2 = this.m_20189_() + (double)this.f_19796_.m_188501_();
            this.m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.PHLEGM.get(), d0 - 0.5, d1, d2 - 0.5, 0.0, 0.0, 0.0);
        }
    }

    public <T extends Mob> T m_21406_(EntityType<T> type, boolean loot) {
        return (T)this.sickenedConvertTo(type, loot);
    }

    public boolean m_214076_(ServerLevel level, LivingEntity entity) {
        return this.sickenedInfect(entity);
    }

    @NotNull
    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        InteractionResult result = this.sickenedMobInteract(player, hand);
        if (result != null) {
            return result;
        }
        return super.m_6071_(player, hand);
    }

    public boolean m_147207_(MobEffectInstance effect, Entity entity) {
        return this.sickenedAddEffect(effect, entity) && super.m_147207_(effect, entity);
    }

    public boolean m_6785_(double dist) {
        return false;
    }

    public float m_6100_() {
        return this.sickenedGetVoicePitch(1.15f, 0.65f);
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

    public Bee m_142606_(ServerLevel level, AgeableMob mob) {
        return null;
    }

    public boolean m_6898_(ItemStack stack) {
        return false;
    }

    public boolean m_6779_(LivingEntity entity) {
        return super.m_6779_(entity) && this.sickenedCanAttack(entity);
    }

    public boolean m_7327_(Entity target) {
        boolean flag = target.m_6469_(this.m_269291_().m_269396_((LivingEntity)this), (float)((int)this.m_21133_(Attributes.f_22281_)));
        if (flag) {
            this.addPotentWitherToTarget(target);
            this.m_5496_(SoundEvents.f_11692_, 1.0f, 0.8f);
        }
        return flag;
    }

    public boolean m_6469_(DamageSource source, float amount) {
        if (!this.sickenedCanBeHurt(source, amount)) {
            return false;
        }
        return super.m_6469_(source, amount);
    }

    class TaintGoal
    extends Goal {
        private static final int MAX_USE_TICKS = 120;
        private int useTicks;

        TaintGoal() {
        }

        public boolean m_8036_() {
            if (SickenedBee.this.m_21660_()) {
                return false;
            }
            return !(SickenedBee.this.f_19796_.m_188501_() < 0.3f);
        }

        public boolean m_8045_() {
            return !SickenedBee.this.m_21660_() && this.useTicks > 0;
        }

        public void m_8056_() {
            this.useTicks = 120;
        }

        public void m_8037_() {
            --this.useTicks;
            if (SickenedBee.this.f_19796_.m_188503_(this.m_183277_(30)) == 0) {
                BlockPos pos = SickenedBee.this.m_20183_();
                BlockPos saved = SickenedBee.this.m_27851_();
                if (TAINTABLE.test(SickenedBee.this.m_9236_().m_8055_(pos))) {
                    WorldTainting.getInstance().convertBlock(pos, SickenedBee.this.m_9236_());
                } else if (saved != null && pos.m_123333_((Vec3i)saved) <= 1) {
                    WorldTainting.getInstance().convertBlock(saved, SickenedBee.this.m_9236_());
                }
            }
        }
    }
}

