/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.projectile.AbstractHurtingProjectile
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.level.block.BedBlock
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.registries.ForgeRegistries
 */
package nonamecrackers2.witherstormmod.common.entity;

import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
import nonamecrackers2.witherstormmod.common.entity.SickenedPillager;
import nonamecrackers2.witherstormmod.common.entity.SickenedSkeleton;
import nonamecrackers2.witherstormmod.common.entity.SickenedVillager;
import nonamecrackers2.witherstormmod.common.entity.SickenedZombie;
import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCriteriaTriggers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEffects;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;

public interface WitherSickened {
    public static final Predicate<Mob> CAN_WEAR_ARMOR;

    default public int getConversionProgress() {
        Mob mob = this.witherstormmod$cast();
        int i = 1;
        if (mob.m_217043_().m_188501_() < 0.01f) {
            int j = 0;
            BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
            for (int k = (int)mob.m_20185_() - 4; k < (int)mob.m_20185_() + 4 && j < 14; ++k) {
                for (int l = (int)mob.m_20186_() - 4; l < (int)mob.m_20186_() + 4 && j < 14; ++l) {
                    for (int i1 = (int)mob.m_20189_() - 4; i1 < (int)mob.m_20189_() + 4 && j < 14; ++i1) {
                        Block block = mob.m_9236_().m_8055_((BlockPos)pos.m_122178_(k, l, i1)).m_60734_();
                        if (block != Blocks.f_50183_ && !(block instanceof BedBlock)) continue;
                        if (mob.m_217043_().m_188501_() < 0.3f) {
                            ++i;
                        }
                        ++j;
                    }
                }
            }
        }
        return i;
    }

    default public void sickenedTick() {
        Mob cast = this.witherstormmod$cast();
        Data data = this.getData();
        if (!cast.m_9236_().f_46443_ && cast.m_6084_() && this.isConverting()) {
            int i = this.getConversionProgress();
            data.conversionTime -= i;
            if (data.getConversionTime() <= 0 && ForgeEventFactory.canLivingConvert((LivingEntity)cast, (EntityType)cast.m_6095_(), timer -> {
                data.conversionTime = timer;
            })) {
                this.cure((ServerLevel)cast.m_9236_());
            }
        }
    }

    default public <E extends Mob> E sickenedConvertTo(EntityType<E> type, boolean loot) {
        Mob cast = this.witherstormmod$cast();
        Data data = this.getData();
        if (!cast.m_6084_()) {
            return null;
        }
        Mob t = (Mob)type.m_20615_(cast.m_9236_());
        if (data.getOriginalData() != null) {
            if (!1.$assertionsDisabled && t == null) {
                throw new AssertionError();
            }
            t.m_20258_(data.getOriginalData());
        }
        if (!1.$assertionsDisabled && t == null) {
            throw new AssertionError();
        }
        t.m_21153_(cast.m_21223_());
        t.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(WitherSicknessTracker::cure);
        t.m_21219_();
        t.m_20359_((Entity)cast);
        t.m_6863_(cast.m_6162_());
        t.m_21557_(cast.m_21525_());
        if (cast.m_8077_()) {
            t.m_6593_(cast.m_7770_());
            t.m_20340_(cast.m_20151_());
        }
        if (cast.m_21532_()) {
            t.m_21530_();
        }
        t.m_20331_(cast.m_20147_());
        if (loot) {
            t.m_21553_(cast.m_21531_());
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                ItemStack stack = cast.m_6844_(slot);
                if (stack.m_41619_()) continue;
                t.m_8061_(slot, stack.m_41777_());
                t.m_21409_(slot, this.getSickenedEquipmentDropChance(slot));
                stack.m_41764_(0);
            }
        }
        cast.m_9236_().m_7967_((Entity)t);
        if (cast.m_20159_()) {
            Entity entity = cast.m_20202_();
            cast.m_8127_();
            if (!1.$assertionsDisabled && entity == null) {
                throw new AssertionError();
            }
            t.m_7998_(entity, true);
        }
        cast.m_146870_();
        return (E)t;
    }

    @Nullable
    default public InteractionResult sickenedMobInteract(Player player, InteractionHand hand) {
        Mob cast = this.witherstormmod$cast();
        ItemStack stack = player.m_21120_(hand);
        if (stack.m_41720_() == WitherStormModItems.GOLDEN_APPLE_STEW.get()) {
            if (!this.isConverting() && this.getOriginalType() != null) {
                if (!player.m_150110_().f_35937_) {
                    stack.m_41774_(1);
                }
                if (!cast.m_9236_().f_46443_) {
                    this.startConverting(player.m_20148_(), cast.m_217043_().m_188503_(2401) + 3600);
                }
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        }
        return null;
    }

    default public boolean sickenedRemoveWhenFarAway(double dist) {
        return !this.isConverting();
    }

    default public boolean sickenedAddEffect(MobEffectInstance effect, @Nullable Entity entity) {
        return effect.m_19544_() != WitherStormModEffects.WITHER_SICKNESS.get() && effect.m_19544_() != MobEffects.f_19615_;
    }

    default public float sickenedGetVoicePitch() {
        return this.sickenedGetVoicePitch(1.35f, 0.85f);
    }

    default public float sickenedGetVoicePitch(float babyBasePitch, float adultBasePitch) {
        Mob cast = this.witherstormmod$cast();
        return cast.m_6162_() ? (cast.m_217043_().m_188501_() - cast.m_217043_().m_188501_()) * 0.2f + babyBasePitch : (cast.m_217043_().m_188501_() - cast.m_217043_().m_188501_()) * 0.2f + adultBasePitch;
    }

    default public void sickenedSave(CompoundTag compound) {
        Data data = this.getData();
        String id = data.encodeOriginalId();
        if (id != null) {
            compound.m_128359_("OriginalType", id);
        }
        if (data.getOriginalData() != null) {
            compound.m_128365_("OriginalData", (Tag)data.getOriginalData());
        }
        compound.m_128405_("ConversionTime", this.isConverting() ? data.getConversionTime() : -1);
        if (data.getConversionStarter() != null) {
            compound.m_128362_("ConversionPlayer", data.getConversionStarter());
        }
    }

    default public void sickenedRead(CompoundTag compound) {
        Data data = this.getData();
        if (compound.m_128441_("OriginalType")) {
            ResourceLocation location = ResourceLocation.m_135820_((String)compound.m_128461_("OriginalType"));
            EntityType type = (EntityType)ForgeRegistries.ENTITY_TYPES.getValue(location);
            data.setOriginal(type, compound.m_128441_("OriginalData") ? compound.m_128469_("OriginalData") : null);
        }
        if (compound.m_128441_("ConversionTime") && compound.m_128451_("ConversionTime") > -1) {
            this.startConverting(compound.m_128403_("ConversionPlayer") ? compound.m_128342_("ConversionPlayer") : null, compound.m_128451_("ConversionTime"));
        }
    }

    default public boolean cure(ServerLevel world) {
        Mob entity;
        Mob cast = this.witherstormmod$cast();
        Data data = this.getData();
        EntityType<?> original = this.getOriginalType();
        if (original != null && (entity = cast.m_21406_(original, false)) != null) {
            Player player;
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                ItemStack stack = cast.m_6844_(slot);
                if (stack.m_41619_()) continue;
                if (EnchantmentHelper.m_44920_((ItemStack)stack)) {
                    entity.m_141942_(slot.m_20749_() + 300).m_142104_(stack);
                    continue;
                }
                double d0 = this.getSickenedEquipmentDropChance(slot);
                if (!(d0 > 1.0)) continue;
                cast.m_19983_(stack);
            }
            WorldTainting.copyExtraData(this.witherstormmod$cast(), entity);
            this.doExtraHandling(entity);
            if (data.conversionStarter != null && (player = world.m_46003_(data.conversionStarter)) instanceof ServerPlayer) {
                WitherStormModCriteriaTriggers.CURED_SICKENED_MOB.trigger((ServerPlayer)player, cast, entity);
            }
            entity.m_7292_(new MobEffectInstance(MobEffects.f_19604_, 200, 0));
            cast.m_216990_((SoundEvent)WitherStormModSoundEvents.MOB_CURED.get());
            ForgeEventFactory.onLivingConvert((LivingEntity)cast, (LivingEntity)entity);
            return true;
        }
        return false;
    }

    default public void startConverting(@Nullable UUID uuid, int duration) {
        Mob cast = this.witherstormmod$cast();
        Data data = this.getData();
        if (this.getOriginalType() != null) {
            data.conversionStarter = uuid;
            data.conversionTime = duration;
            this.setConverting(true);
            cast.m_7292_(new MobEffectInstance(MobEffects.f_19600_, duration, Math.min(cast.m_9236_().m_46791_().m_19028_() - 1, 0)));
            this.playCureSound();
        }
    }

    default public void playCureSound() {
        Mob cast = this.witherstormmod$cast();
        cast.m_5496_(SoundEvents.f_12644_, 1.0f + cast.m_217043_().m_188501_(), cast.m_217043_().m_188501_() * 0.7f + 0.3f);
    }

    default public Mob witherstormmod$cast() {
        return (Mob)this;
    }

    default public boolean sickenedCanAttack(LivingEntity entity) {
        return !(entity instanceof WitherSickened) && !(entity instanceof TentacleEntity) && !(entity instanceof WitherStormEntity) && !(entity instanceof WitheredSymbiontEntity) && !(entity instanceof WitherStormHeadEntity);
    }

    default public void addWitherToTarget(Entity entity) {
        if (this.witherstormmod$cast().m_21205_().m_41619_() && entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)entity;
            float f = this.witherstormmod$cast().m_9236_().m_6436_(this.witherstormmod$cast().m_20183_()).m_19056_();
            living.m_147207_(new MobEffectInstance(MobEffects.f_19615_, 120 * (int)f), (Entity)this.witherstormmod$cast());
        }
    }

    default public void addPotentWitherToTarget(Entity entity) {
        if (this.witherstormmod$cast().m_21205_().m_41619_() && entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)entity;
            float f = this.witherstormmod$cast().m_9236_().m_6436_(this.witherstormmod$cast().m_20183_()).m_19056_();
            living.m_147207_(new MobEffectInstance(MobEffects.f_19615_, 75 * (int)f, 1), (Entity)this.witherstormmod$cast());
        }
    }

    default public void doExtraHandling(Mob mob) {
    }

    default public void convertFrom(Mob mob) {
    }

    public Data getData();

    public boolean isConverting();

    public void setConverting(boolean var1);

    public float getSickenedEquipmentDropChance(EquipmentSlot var1);

    default public int getInfectedHealAmount() {
        return 8;
    }

    default public boolean sickenedInfect(LivingEntity entity) {
        if (entity instanceof Mob) {
            Mob mob = (Mob)entity;
            if (WorldTainting.getInstance().convertMob(mob, false)) {
                int healAmount = this.getInfectedHealAmount();
                if (healAmount > 0) {
                    this.witherstormmod$cast().m_5634_((float)healAmount);
                }
                return false;
            }
        }
        return true;
    }

    default public boolean sickenedCanBeHurt(DamageSource source, float amount) {
        Projectile projectile;
        return !(source.m_7640_() instanceof AbstractHurtingProjectile) && !(source.m_7640_() instanceof AbstractArrow) || !((projectile = (Projectile)source.m_7640_()).m_19749_() instanceof WitherSickened);
    }

    @Nullable
    default public EntityType<?> getOriginalType() {
        EntityType<?> storedOriginal = this.getData().getStoredOriginalType();
        return storedOriginal == null ? WorldTainting.getInstance().getOriginalTypeFromConvertedType(this.witherstormmod$cast().m_6095_()) : storedOriginal;
    }

    static {
        if (1.$assertionsDisabled) {
            // empty if block
        }
        CAN_WEAR_ARMOR = mob -> mob instanceof SickenedZombie || mob instanceof SickenedSkeleton || mob instanceof SickenedVillager || mob instanceof SickenedPillager;
    }

    public static class Data {
        protected int conversionTime;
        protected UUID conversionStarter;
        @Nullable
        protected EntityType<?> original;
        @Nullable
        protected CompoundTag originalData;

        @Nullable
        public final String encodeOriginalId() {
            if (this.original != null) {
                EntityType<?> type = this.original;
                ResourceLocation location = ForgeRegistries.ENTITY_TYPES.getKey(type);
                return type.m_20584_() && location != null ? location.toString() : null;
            }
            return null;
        }

        public UUID getConversionStarter() {
            return this.conversionStarter;
        }

        public int getConversionTime() {
            return this.conversionTime;
        }

        public EntityType<?> getStoredOriginalType() {
            return this.original;
        }

        @Nullable
        protected CompoundTag getOriginalData() {
            return this.originalData;
        }

        public void setOriginal(EntityType<?> originalType, CompoundTag originalData) {
            this.original = originalType;
            this.originalData = originalData;
        }
    }
}

