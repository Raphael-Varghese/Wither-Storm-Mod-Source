/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.logging.LogUtils
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  javax.annotation.Nullable
 *  net.minecraft.Util
 *  net.minecraft.core.Holder
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtOps
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.ReputationEventHandler
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.ai.village.ReputationEventType
 *  net.minecraft.world.entity.monster.ZombieVillager
 *  net.minecraft.world.entity.npc.Villager
 *  net.minecraft.world.entity.npc.VillagerData
 *  net.minecraft.world.entity.npc.VillagerDataHolder
 *  net.minecraft.world.entity.npc.VillagerProfession
 *  net.minecraft.world.entity.npc.VillagerTrades$ItemListing
 *  net.minecraft.world.entity.npc.VillagerType
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.trading.MerchantOffers
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraftforge.common.BasicItemListing
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.apache.commons.lang3.ArrayUtils
 *  org.slf4j.Logger
 */
package nonamecrackers2.witherstormmod.common.entity;

import com.google.common.collect.Maps;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ReputationEventHandler;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.village.ReputationEventType;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.common.entity.SickenedZombie;
import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.mixin.IMixinZombieVillager;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;

public class SickenedVillager
extends SickenedZombie
implements VillagerDataHolder {
    public static final Map<VillagerProfession, VillagerTrades.ItemListing[]> SICKENED_TRADES = (Map)Util.m_137469_((Object)Maps.newHashMap(), map -> {
        for (VillagerProfession profession : ForgeRegistries.VILLAGER_PROFESSIONS.getValues()) {
            if (profession == VillagerProfession.f_35585_ || profession == VillagerProfession.f_35596_) continue;
            if (profession == VillagerProfession.f_35589_) {
                map.put(profession, SickenedVillager.makeDefaultWitheredItems(new VillagerTrades.ItemListing[]{new BasicItemListing(1, new ItemStack((ItemLike)WitherStormModItems.TAINTED_DUST.get()), 12, 2)}));
                continue;
            }
            if (profession == VillagerProfession.f_35590_) {
                map.put(profession, SickenedVillager.makeDefaultWitheredItems(new VillagerTrades.ItemListing[]{new BasicItemListing(16, new ItemStack((ItemLike)WitherStormModItems.GOLDEN_APPLE_STEW.get()), 1, 30)}));
                continue;
            }
            if (profession == VillagerProfession.f_35595_) {
                map.put(profession, SickenedVillager.makeDefaultMobStatues());
                continue;
            }
            map.put(profession, SickenedVillager.makeDefaultWitheredItems());
        }
    });
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final EntityDataAccessor<VillagerData> VILLAGER_DATA = SynchedEntityData.m_135353_(SickenedVillager.class, (EntityDataSerializer)EntityDataSerializers.f_135043_);
    @Nullable
    private Tag gossips;
    private CompoundTag tradeOffers;
    private int villagerXp;
    private final SickenedData data = new SickenedData();

    public SickenedVillager(EntityType<? extends SickenedVillager> type, Level world) {
        super(type, world);
        BuiltInRegistries.f_256735_.m_213642_(this.f_19796_).ifPresent(prof -> this.m_34375_(this.m_7141_().m_35565_((VillagerProfession)prof.m_203334_())));
    }

    private static VillagerTrades.ItemListing[] makeDefaultWitheredItems() {
        return new VillagerTrades.ItemListing[]{new BasicItemListing(1, new ItemStack((ItemLike)WitherStormModItems.WITHERED_FLESH.get()), 16, 5), new BasicItemListing(1, new ItemStack((ItemLike)WitherStormModItems.WITHERED_BONE.get()), 16, 5), new BasicItemListing(1, new ItemStack((ItemLike)WitherStormModItems.WITHERED_SPIDER_EYE.get()), 16, 5)};
    }

    private static VillagerTrades.ItemListing[] makeDefaultWitheredItems(VillagerTrades.ItemListing ... and) {
        return (VillagerTrades.ItemListing[])ArrayUtils.addAll((Object[])SickenedVillager.makeDefaultWitheredItems(), (Object[])and);
    }

    private static VillagerTrades.ItemListing[] makeDefaultMobStatues() {
        return new VillagerTrades.ItemListing[]{new BasicItemListing(2, new ItemStack((ItemLike)WitherStormModItems.TAINTED_ZOMBIE_SITTING.get()), 12, 20), new BasicItemListing(2, new ItemStack((ItemLike)WitherStormModItems.TAINTED_ZOMBIE_WALL.get()), 12, 20), new BasicItemListing(2, new ItemStack((ItemLike)WitherStormModItems.TAINTED_ZOMBIE_LYING.get()), 12, 20), new BasicItemListing(2, new ItemStack((ItemLike)WitherStormModItems.TAINTED_BONE_PILE.get()), 12, 20), new BasicItemListing(2, new ItemStack((ItemLike)WitherStormModItems.TAINTED_SKELETON_WALL.get()), 12, 20), new BasicItemListing(2, new ItemStack((ItemLike)WitherStormModItems.TAINTED_SKULL_CEILING.get()), 12, 20)};
    }

    @Override
    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(VILLAGER_DATA, (Object)new VillagerData(VillagerType.f_35821_, VillagerProfession.f_35585_, 1));
    }

    @Override
    public void m_7380_(CompoundTag compound) {
        super.m_7380_(compound);
        VillagerData.f_35550_.encodeStart((DynamicOps)NbtOps.f_128958_, (Object)this.m_7141_()).resultOrPartial(arg_0 -> ((Logger)LOGGER).error(arg_0)).ifPresent(tag -> compound.m_128365_("VillagerData", tag));
        if (this.tradeOffers != null) {
            compound.m_128365_("Offers", (Tag)this.tradeOffers);
        }
        if (this.gossips != null) {
            compound.m_128365_("Gossips", this.gossips);
        }
        compound.m_128405_("Xp", this.villagerXp);
    }

    @Override
    public void m_7378_(CompoundTag compound) {
        super.m_7378_(compound);
        if (compound.m_128425_("VillagerData", 10)) {
            DataResult result = VillagerData.f_35550_.parse(new Dynamic((DynamicOps)NbtOps.f_128958_, (Object)compound.m_128423_("VillagerData")));
            result.resultOrPartial(arg_0 -> ((Logger)LOGGER).error(arg_0)).ifPresent(this::m_34375_);
        }
        if (compound.m_128425_("Offers", 10)) {
            this.tradeOffers = compound.m_128469_("Offers");
        }
        if (compound.m_128425_("Gossips", 10)) {
            this.gossips = compound.m_128437_("Gossips", 10);
        }
        if (compound.m_128425_("Xp", 3)) {
            this.villagerXp = compound.m_128451_("Xp");
        }
    }

    @Override
    public void doExtraHandling(Mob mob) {
        VillagerTrades.ItemListing[] listings;
        Villager villager = (Villager)mob;
        villager.m_34375_(this.m_7141_());
        if (this.gossips != null) {
            villager.m_35455_(this.gossips);
        }
        if (this.tradeOffers != null) {
            villager.m_35476_(new MerchantOffers(this.tradeOffers));
        }
        villager.m_35546_(this.villagerXp);
        if (this.getData().getConversionStarter() != null) {
            Player player = this.m_9236_().m_46003_(this.getData().getConversionStarter());
            ((ServerLevel)this.m_9236_()).m_8670_(ReputationEventType.f_26985_, (Entity)player, (ReputationEventHandler)villager);
        }
        if ((listings = SICKENED_TRADES.get(villager.m_7141_().m_35571_())) != null) {
            for (VillagerTrades.ItemListing listing : listings) {
                MerchantOffers offers = villager.m_6616_();
                offers.add((Object)listing.m_213663_((Entity)villager, villager.m_217043_()));
            }
        }
    }

    @Override
    public void convertFrom(Mob mob) {
        if (mob instanceof VillagerDataHolder) {
            VillagerDataHolder holder = (VillagerDataHolder)mob;
            this.m_34375_(holder.m_7141_());
        }
        if (mob instanceof Villager) {
            Villager villager = (Villager)mob;
            this.setGossips((Tag)villager.m_35517_().m_262795_((DynamicOps)NbtOps.f_128958_));
            this.setTradeOffers(villager.m_6616_().m_45388_());
            this.setVillagerXp(villager.m_7809_());
        } else if (mob instanceof ZombieVillager) {
            ZombieVillager villager = (ZombieVillager)mob;
            IMixinZombieVillager mixinVillager = (IMixinZombieVillager)villager;
            this.setGossips(mixinVillager.getGossips());
            this.setTradeOffers(mixinVillager.getTradeOffers());
            this.setVillagerXp(villager.m_149889_());
        }
    }

    public SoundEvent m_7515_() {
        return SoundEvents.f_12615_;
    }

    public SoundEvent m_7975_(DamageSource source) {
        return SoundEvents.f_12646_;
    }

    public SoundEvent m_5592_() {
        return SoundEvents.f_12645_;
    }

    public SoundEvent m_7660_() {
        return SoundEvents.f_12647_;
    }

    public void setTradeOffers(CompoundTag tag) {
        this.tradeOffers = tag;
    }

    public void setGossips(Tag tag) {
        this.gossips = tag;
    }

    @Override
    public SpawnGroupData m_6518_(ServerLevelAccessor world, DifficultyInstance instance, MobSpawnType reason, SpawnGroupData data, CompoundTag compound) {
        this.m_34375_(this.m_7141_().m_35567_(VillagerType.m_204073_((Holder)world.m_204166_(this.m_20183_()))));
        return super.m_6518_(world, instance, reason, data, compound);
    }

    public VillagerData m_7141_() {
        return (VillagerData)this.f_19804_.m_135370_(VILLAGER_DATA);
    }

    public void m_34375_(VillagerData data) {
        VillagerData villagerdata = this.m_7141_();
        if (villagerdata.m_35571_() != data.m_35571_()) {
            this.tradeOffers = null;
        }
        this.f_19804_.m_135381_(VILLAGER_DATA, (Object)data);
    }

    public int getVillagerXp() {
        return this.villagerXp;
    }

    public void setVillagerXp(int xp) {
        this.villagerXp = xp;
    }

    @Override
    public WitherSickened.Data getData() {
        return this.data;
    }

    @Override
    public boolean m_6785_(double dist) {
        return !this.isConverting() && this.villagerXp == 0;
    }

    public static class SickenedData
    extends WitherSickened.Data {
        @Override
        public EntityType<?> getStoredOriginalType() {
            return EntityType.f_20492_;
        }

        @Override
        public void setOriginal(EntityType<?> originalType, CompoundTag originalData) {
        }
    }
}

