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
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntitySelector
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
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.RangedAttackGoal
 *  net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.RangedAttackMob
 *  net.minecraft.world.entity.monster.Zombie
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.ThrownTrident
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.ServerLevelAccessor
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity;

import java.util.List;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
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
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nonamecrackers2.witherstormmod.common.entity.SickenedChicken;
import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
import org.jetbrains.annotations.NotNull;

public class SickenedZombie
extends Zombie
implements WitherSickened,
RangedAttackMob {
    private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.m_135353_(SickenedZombie.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private final WitherSickened.Data sickenedData = new WitherSickened.Data();

    public SickenedZombie(EntityType<? extends SickenedZombie> type, Level world) {
        super(type, world);
    }

    @NotNull
    public MobType m_6336_() {
        return WitherStormModMobTypes.SICKENED;
    }

    public static AttributeSupplier.Builder m_34328_() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 24.0).m_22268_(Attributes.f_22277_, 48.0).m_22268_(Attributes.f_22279_, (double)0.28f).m_22268_(Attributes.f_22281_, 3.5).m_22268_(Attributes.f_22284_, 2.2).m_22266_(Attributes.f_22287_);
    }

    protected void m_6878_() {
        super.m_6878_();
        this.f_21345_.m_25352_(2, (Goal)new SickenedZombieTridentAttackGoal(this, 1.0, 40, 10.0f));
        this.f_21346_.m_25352_(1, (Goal)new HurtByTargetGoal((PathfinderMob)this, new Class[0]).m_26044_(new Class[0]));
        this.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
        this.f_21346_.m_25352_(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
    }

    public void m_8119_() {
        super.m_8119_();
        this.sickenedTick();
    }

    protected int m_7302_(int supply) {
        return supply;
    }

    protected boolean m_5884_() {
        return false;
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

    public boolean m_147207_(@NotNull MobEffectInstance effect, Entity entity) {
        return this.sickenedAddEffect(effect, entity) && super.m_147207_(effect, entity);
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

    protected ItemStack m_5728_() {
        return ItemStack.f_41583_;
    }

    protected void m_7595_() {
    }

    public boolean m_34329_() {
        return false;
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

    protected void m_213945_(RandomSource random, DifficultyInstance instance) {
        super.m_213945_(random, instance);
        float f = this.f_19796_.m_188501_();
        float f2 = this.m_9236_().m_46791_() == Difficulty.HARD ? 0.05f : 0.01f;
        if (f < f2) {
            int i = this.f_19796_.m_188503_(4);
            if (i == 0) {
                this.m_8061_(EquipmentSlot.MAINHAND, new ItemStack((ItemLike)Items.f_42383_));
            } else if (i < 4) {
                this.m_8061_(EquipmentSlot.MAINHAND, new ItemStack((ItemLike)Items.f_42384_));
            } else if (i == 4) {
                this.m_8061_(EquipmentSlot.MAINHAND, new ItemStack((ItemLike)Items.f_42430_));
            }
        }
    }

    public boolean m_7327_(Entity target) {
        boolean flag = super.m_7327_(target);
        if (flag) {
            this.addWitherToTarget(target);
        }
        return flag;
    }

    public boolean m_214076_(ServerLevel level, LivingEntity entity) {
        return this.sickenedInfect(entity);
    }

    public SpawnGroupData m_6518_(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, SpawnGroupData groupData, CompoundTag tag) {
        ItemStack head = this.m_6844_(EquipmentSlot.HEAD);
        this.m_34336_(this.m_7586_());
        this.m_213945_(this.f_19796_, difficulty);
        this.m_213946_(this.f_19796_, difficulty);
        if (head.m_150930_(Items.f_42055_)) {
            this.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get()));
        } else if (head.m_150930_(Items.f_42047_)) {
            this.m_8061_(EquipmentSlot.HEAD, new ItemStack((ItemLike)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get()));
        }
        if (this.m_6162_()) {
            SickenedChicken sickenedChicken;
            RandomSource random = level.m_213780_();
            if ((double)random.m_188501_() < 0.05) {
                List list = level.m_6443_(SickenedChicken.class, this.m_20191_().m_82377_(5.0, 3.0, 5.0), EntitySelector.f_20404_);
                if (!list.isEmpty()) {
                    SickenedChicken sickenedChicken2 = (SickenedChicken)list.get(0);
                    sickenedChicken2.m_28273_(true);
                    this.m_20329_((Entity)sickenedChicken2);
                }
            } else if ((double)random.m_188501_() < 0.05 && (sickenedChicken = (SickenedChicken)((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get()).m_20615_(this.m_9236_())) != null) {
                sickenedChicken.m_7678_(this.m_20185_(), this.m_20186_(), this.m_20189_(), this.m_146908_(), 0.0f);
                sickenedChicken.m_6518_(level, difficulty, MobSpawnType.JOCKEY, null, null);
                sickenedChicken.m_28273_(true);
                this.m_20329_((Entity)sickenedChicken);
                level.m_7967_((Entity)sickenedChicken);
            }
        }
        return groupData;
    }

    public void m_6504_(LivingEntity zombie, float p_32357_) {
        ThrownTrident throwntrident = new ThrownTrident(this.m_9236_(), (LivingEntity)this, new ItemStack((ItemLike)Items.f_42713_));
        double d0 = zombie.m_20185_() - this.m_20185_();
        double d1 = zombie.m_20227_(0.3333333333333333) - throwntrident.m_20186_();
        double d2 = zombie.m_20189_() - this.m_20189_();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        throwntrident.m_6686_(d0, d1 + d3 * (double)0.2f, d2, 1.6f, (float)(14 - this.m_9236_().m_46791_().m_19028_() * 4));
        this.m_5496_(SoundEvents.f_11821_, 1.0f, 1.0f / (this.m_217043_().m_188501_() * 0.4f + 0.8f));
        this.m_9236_().m_7967_((Entity)throwntrident);
    }

    public boolean m_6469_(DamageSource source, float amount) {
        if (!this.sickenedCanBeHurt(source, amount)) {
            return false;
        }
        return super.m_6469_(source, amount);
    }

    static class SickenedZombieTridentAttackGoal
    extends RangedAttackGoal {
        private final SickenedZombie zombie;

        public SickenedZombieTridentAttackGoal(RangedAttackMob attackMob, double p_25769_, int p_25770_, float p_25771_) {
            super(attackMob, p_25769_, p_25770_, p_25771_);
            this.zombie = (SickenedZombie)attackMob;
        }

        public boolean m_8036_() {
            return super.m_8036_() && this.zombie.m_21205_().m_150930_(Items.f_42713_);
        }

        public void m_8056_() {
            super.m_8056_();
            this.zombie.m_21561_(true);
            this.zombie.m_6672_(InteractionHand.MAIN_HAND);
        }

        public void m_8041_() {
            super.m_8041_();
            this.zombie.m_5810_();
            this.zombie.m_21561_(false);
        }
    }
}

