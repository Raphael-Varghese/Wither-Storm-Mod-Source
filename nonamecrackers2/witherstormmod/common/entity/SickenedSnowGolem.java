/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
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
 *  net.minecraft.world.entity.ai.goal.FloatGoal
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
 *  net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
 *  net.minecraft.world.entity.ai.goal.RangedAttackGoal
 *  net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.animal.SnowGolem
 *  net.minecraft.world.entity.monster.Enemy
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.RangedAttackMob
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.Snowball
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.gameevent.GameEvent
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
import nonamecrackers2.witherstormmod.common.util.SnowballAccessor;
import org.jetbrains.annotations.NotNull;

public class SickenedSnowGolem
extends SnowGolem
implements WitherSickened,
Enemy {
    private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.m_135353_(SickenedSnowGolem.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private final WitherSickened.Data sickenedData = new WitherSickened.Data();

    public SickenedSnowGolem(EntityType<? extends SickenedSnowGolem> type, Level world) {
        super(type, world);
    }

    protected void m_8099_() {
        this.f_21345_.m_25352_(0, (Goal)new FloatGoal((Mob)this));
        this.f_21345_.m_25352_(1, (Goal)new RangedAttackGoal((RangedAttackMob)this, 1.25, 12, 10.0f));
        this.f_21345_.m_25352_(2, (Goal)new WaterAvoidingRandomStrollGoal((PathfinderMob)this, 1.0, 1.0000001E-5f));
        this.f_21345_.m_25352_(3, (Goal)new LookAtPlayerGoal((Mob)this, Player.class, 6.0f));
        this.f_21345_.m_25352_(4, (Goal)new RandomLookAroundGoal((Mob)this));
        this.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
        this.f_21346_.m_25352_(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
    }

    @NotNull
    public MobType m_6336_() {
        return WitherStormModMobTypes.SICKENED;
    }

    public static AttributeSupplier.Builder m_29934_() {
        return Monster.m_33035_().m_22268_(Attributes.f_22276_, 8.0).m_22268_(Attributes.f_22279_, 0.24);
    }

    protected int m_7302_(int supply) {
        return supply;
    }

    public boolean m_6126_() {
        return false;
    }

    public void m_8119_() {
        super.m_8119_();
        this.sickenedTick();
    }

    public void m_5851_(SoundSource source) {
        this.m_9236_().m_6269_((Player)null, (Entity)this, SoundEvents.f_12480_, source, 1.0f, 1.0f);
        if (!this.m_9236_().m_5776_()) {
            this.m_29936_(false);
            this.m_5552_(new ItemStack((ItemLike)WitherStormModItems.TAINTED_CARVED_PUMPKIN.get()), 1.7f);
        }
    }

    public void m_6504_(LivingEntity entity, float p_29913_) {
        Snowball snowball = new Snowball(this.m_9236_(), (LivingEntity)this);
        if ((double)this.f_19796_.m_188501_() < 0.1) {
            ((SnowballAccessor)snowball).setWitherEffect(true);
        }
        double d0 = entity.m_20188_() - (double)1.1f;
        double d1 = entity.m_20185_() - this.m_20185_();
        double d2 = d0 - snowball.m_20186_();
        double d3 = entity.m_20189_() - this.m_20189_();
        double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double)0.2f;
        snowball.m_6686_(d1, d2 + d4, d3, 1.6f, 12.0f);
        this.m_5496_(SoundEvents.f_12479_, 1.0f, 0.4f / (this.m_217043_().m_188501_() * 0.4f + 0.8f));
        this.m_9236_().m_7967_((Entity)snowball);
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

    public List<ItemStack> onSheared(@Nullable Player player, @NotNull ItemStack item, Level world, BlockPos pos, int fortune) {
        world.m_6269_(null, (Entity)this, SoundEvents.f_12480_, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0f, 1.0f);
        this.m_146852_(GameEvent.f_157781_, (Entity)player);
        if (!world.m_5776_()) {
            this.m_29936_(false);
            return Collections.singletonList(new ItemStack((ItemLike)WitherStormModItems.TAINTED_CARVED_PUMPKIN.get()));
        }
        return Collections.emptyList();
    }

    public boolean m_6785_(double dist) {
        return false;
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

    protected SoundEvent m_7515_() {
        return SoundEvents.f_12476_;
    }

    protected SoundEvent m_7975_(DamageSource source) {
        return SoundEvents.f_12478_;
    }

    protected SoundEvent m_5592_() {
        return SoundEvents.f_12477_;
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

    public boolean m_6469_(DamageSource source, float amount) {
        if (!this.sickenedCanBeHurt(source, amount)) {
            return false;
        }
        return super.m_6469_(source, amount);
    }
}

