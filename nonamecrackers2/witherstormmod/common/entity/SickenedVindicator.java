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
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.Vindicator
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
import nonamecrackers2.witherstormmod.common.entity.goal.SickenedMobsAttackGoal;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMobTypes;
import org.jetbrains.annotations.NotNull;

public class SickenedVindicator
extends Vindicator
implements WitherSickened {
    private static final EntityDataAccessor<Boolean> CONVERTING = SynchedEntityData.m_135353_(SickenedVindicator.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private final WitherSickened.Data sickenedData = new WitherSickened.Data();

    public SickenedVindicator(EntityType<? extends SickenedVindicator> type, Level level) {
        super(type, level);
    }

    @NotNull
    public MobType m_6336_() {
        return WitherStormModMobTypes.SICKENED;
    }

    public static AttributeSupplier.Builder m_34104_() {
        return Monster.m_33035_().m_22268_(Attributes.f_22279_, 0.35).m_22268_(Attributes.f_22276_, 30.0).m_22268_(Attributes.f_22281_, 6.0).m_22268_(Attributes.f_22277_, 48.0);
    }

    protected void m_8099_() {
        super.m_8099_();
        this.f_21346_.m_25352_(2, (Goal)new NearestAttackableTargetGoal((Mob)this, Player.class, true));
        this.f_21346_.m_25352_(3, (Goal)new SickenedMobsAttackGoal((Mob)this));
    }

    public boolean m_7307_(Entity entity) {
        LivingEntity living;
        if (entity instanceof LivingEntity && (living = (LivingEntity)entity).m_6336_() == MobType.f_21643_) {
            return false;
        }
        return super.m_7307_(entity);
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

    public boolean m_147207_(@NotNull MobEffectInstance effect, Entity entity) {
        return this.sickenedAddEffect(effect, entity) && super.m_147207_(effect, entity);
    }

    public boolean m_214076_(ServerLevel level, LivingEntity entity) {
        return this.sickenedInfect(entity);
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

    public boolean m_6779_(LivingEntity entity) {
        return super.m_6779_(entity) && this.sickenedCanAttack(entity);
    }

    public boolean m_37882_() {
        return false;
    }

    public void m_37897_(boolean flag) {
    }

    public boolean m_7492_() {
        return false;
    }

    public boolean m_33067_() {
        return false;
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

