/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LeapAtTargetGoal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.MeleeAttackGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.Spider
 *  net.minecraft.world.entity.monster.Spider$SpiderEffectsGroupData
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.ServerLevelAccessor
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nonamecrackers2.witherstormmod.common.entity.SickenedSkeleton;
import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
import org.jetbrains.annotations.NotNull;

public class SickenedSpider
extends Spider
implements WitherSickened {
    private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.m_135353_(SickenedSpider.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private final WitherSickened.Data sickenedData = new WitherSickened.Data();

    public SickenedSpider(EntityType<? extends SickenedSpider> type, Level world) {
        super(type, world);
    }

    @NotNull
    public MobType m_6336_() {
        return WitherStormModMobTypes.SICKENED;
    }

    public static AttributeSupplier.Builder m_33815_() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 20.0).m_22268_(Attributes.f_22279_, (double)0.34f).m_22268_(Attributes.f_22281_, 3.0).m_22268_(Attributes.f_22277_, 32.0);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(1, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(3, (Goal)new LeapAtTargetGoal((Mob)this, 0.45f));
        this.f_21345_.m_25352_(4, (Goal)new AttackGoal(this));
        this.f_21345_.m_25352_(5, (Goal)new WaterAvoidingRandomStrollGoal((PathfinderMob)this, 0.8));
        this.f_21345_.m_25352_(6, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 8.0f));
        this.f_21345_.m_25352_(6, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]));
        this.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
        this.f_21346_.m_25352_(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
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
        return super.m_6071_(player, hand);
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

    public boolean m_7327_(Entity target) {
        boolean flag = super.m_7327_(target);
        if (flag) {
            this.addWitherToTarget(target);
        }
        return flag;
    }

    public SpawnGroupData m_6518_(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, SpawnGroupData groupData, CompoundTag tag) {
        SickenedSkeleton sickenedSkeleton;
        RandomSource random = level.m_213780_();
        if (random.m_188503_(100) == 0 && (sickenedSkeleton = (SickenedSkeleton)((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get()).m_20615_(this.m_9236_())) != null) {
            sickenedSkeleton.m_7678_(this.m_20185_(), this.m_20186_(), this.m_20189_(), this.m_146908_(), 0.0f);
            sickenedSkeleton.m_6518_(level, difficulty, MobSpawnType.JOCKEY, null, null);
            sickenedSkeleton.m_20329_((Entity)this);
        }
        if (groupData == null) {
            groupData = new Spider.SpiderEffectsGroupData();
            if (level.m_46791_() == Difficulty.HARD && random.m_188501_() < 0.1f * difficulty.m_19057_()) {
                ((Spider.SpiderEffectsGroupData)groupData).m_219118_(random);
            }
        }
        if (groupData instanceof Spider.SpiderEffectsGroupData) {
            Spider.SpiderEffectsGroupData sickenedSpider$spidereffectsgroupdata = (Spider.SpiderEffectsGroupData)groupData;
            MobEffect mobEffect = sickenedSpider$spidereffectsgroupdata.f_33827_;
            if (mobEffect != null) {
                this.m_7292_(new MobEffectInstance(mobEffect, -1));
            }
        }
        return groupData;
    }

    public boolean m_6469_(DamageSource source, float amount) {
        if (!this.sickenedCanBeHurt(source, amount)) {
            return false;
        }
        return super.m_6469_(source, amount);
    }

    private static class AttackGoal
    extends MeleeAttackGoal {
        public AttackGoal(SickenedSpider entity) {
            super((PathfinderMob)entity, 1.0, true);
        }

        public boolean m_8036_() {
            return super.m_8036_() && !this.f_25540_.m_20160_();
        }

        protected double m_6639_(LivingEntity target) {
            return 4.0f + target.m_20205_();
        }
    }
}

