/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtUtils
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.registries.ForgeRegistries
 */
package nonamecrackers2.witherstormmod.common.item;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCriteriaTriggers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class AmuletItem
extends Item {
    private static final Predicate<Entity> BINDABLE = entity -> entity instanceof Mob || entity instanceof Player;
    public static final String TRACKING_BLUE = "TrackingBlue";
    public static final String TRACKING_AQUA = "TrackingAqua";
    public static final String TRACKING_GREEN = "TrackingGreen";
    public static final String TRACKING_GRAY = "TrackingGray";
    public static final String TRACKING_RED = "TrackingRed";
    public static final String[] TRACKING = new String[]{"TrackingBlue", "TrackingAqua", "TrackingGreen", "TrackingGray", "TrackingRed"};
    public static final String SELECTED_INDEX = "SelectedIndex";
    public static final String LOCKED = "Locked";
    public static final String TRACK_ENTITY_TYPES = "TrackEntityTypes";
    public static final int DEFAULT_SCAN_DISTANCE = 1000;

    public AmuletItem(Item.Properties properties) {
        super(properties);
    }

    public void m_6883_(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            if (entity instanceof Player) {
                Player player = (Player)entity;
                CompoundTag tag = stack.m_41784_();
                if (tag.m_128451_(SELECTED_INDEX) <= 0) {
                    tag.m_128405_(SELECTED_INDEX, 1);
                }
                for (String id : TRACKING) {
                    if (id.equals(TRACKING_BLUE)) {
                        ArrayList storms = Lists.newArrayList();
                        for (Entity e : serverLevel.m_8583_()) {
                            if (!(e instanceof WitherStormEntity)) continue;
                            WitherStormEntity storm = (WitherStormEntity)e;
                            if (e instanceof WitherStormSegmentEntity) continue;
                            storms.add(storm);
                        }
                        WitherStormEntity nearest = WorldUtil.getNearest(storms, player.m_20182_(), Entity::m_20182_);
                        if (nearest != null) {
                            tag.m_128359_(id + "Type", ForgeRegistries.ENTITY_TYPES.getKey((Object)nearest.m_6095_()).toString());
                            tag.m_128362_(id, nearest.m_20148_());
                            tag.m_128405_(id + "Dist", (int)player.m_20270_((Entity)nearest));
                            tag.m_128359_(id + "Name", nearest.m_5446_().getString());
                            tag.m_128365_(id + "Pos", (Tag)NbtUtils.m_129224_((BlockPos)nearest.m_20183_()));
                            continue;
                        }
                        tag.m_128405_(id + "Dist", -1);
                        continue;
                    }
                    if (tag.m_128441_(id)) {
                        this.saveDistFor(serverLevel, tag, player, tag.m_128342_(id), id);
                        continue;
                    }
                    tag.m_128405_(id + "Dist", -1);
                }
            }
        }
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.m_21120_(hand);
        if (!level.f_46443_ && player.m_6144_() && stack.m_41720_() instanceof AmuletItem) {
            CompoundTag tag = stack.m_41784_();
            int index = tag.m_128451_(SELECTED_INDEX);
            if (++index < TRACKING.length) {
                tag.m_128405_(SELECTED_INDEX, index);
            } else {
                tag.m_128405_(SELECTED_INDEX, 1);
            }
            player.m_6330_((SoundEvent)WitherStormModSoundEvents.AMULET_SWAPS.get(), SoundSource.PLAYERS, 1.0f, 2.0f);
        }
        return InteractionResultHolder.m_19098_((Object)stack);
    }

    public InteractionResult m_6880_(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        int index;
        CompoundTag tag;
        ItemStack item;
        if (BINDABLE.test((Entity)entity) && !(entity instanceof WitherStormEntity) && !player.m_6144_() && (item = player.m_21120_(hand)).m_41720_() instanceof AmuletItem && !(tag = item.m_41784_()).m_128471_(LOCKED) && (index = tag.m_128451_(SELECTED_INDEX)) >= 1 && index < TRACKING.length) {
            if (!player.m_9236_().f_46443_) {
                String id = TRACKING[index];
                if (!tag.m_128441_(id) || !tag.m_128342_(id).equals(entity.m_20148_())) {
                    tag.m_128359_(id + "Type", ForgeRegistries.ENTITY_TYPES.getKey((Object)entity.m_6095_()).toString());
                    tag.m_128362_(id, entity.m_20148_());
                    player.m_6330_((SoundEvent)WitherStormModSoundEvents.AMULET_BIND.get(), SoundSource.PLAYERS, 1.0f, 0.0f);
                    if (player instanceof ServerPlayer) {
                        ServerPlayer serverPlayer = (ServerPlayer)player;
                        WitherStormModCriteriaTriggers.LINK_AMULET.trigger(serverPlayer, (Entity)entity, this.getTotalUniqueLinked(stack));
                    }
                } else {
                    tag.m_128473_(id);
                    tag.m_128473_(id + "Type");
                    tag.m_128473_(id + "Name");
                    tag.m_128473_(id + "Pos");
                    tag.m_128405_(id + "Dist", -1);
                    player.m_6330_((SoundEvent)WitherStormModSoundEvents.AMULET_UNBIND.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
                }
            }
            return InteractionResult.m_19078_((boolean)player.m_9236_().f_46443_);
        }
        return InteractionResult.PASS;
    }

    private void saveDistFor(ServerLevel level, CompoundTag tag, Player player, UUID uuid, String id) {
        Entity tracking = null;
        if (tag.m_128471_(TRACK_ENTITY_TYPES)) {
            EntityType type = (EntityType)ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(tag.m_128461_(id + "Type")));
            List entities = level.m_6443_(Entity.class, player.m_20191_().m_82400_(500.0), e -> e.m_6095_().equals(type) && e != player);
            tracking = WorldUtil.getNearest(entities, player.m_20182_(), Entity::m_20182_);
        } else if (tag.m_128441_(id)) {
            tracking = level.m_8791_(tag.m_128342_(id));
        }
        if (tracking != null) {
            tag.m_128405_(id + "Dist", (int)player.m_20270_(tracking));
            tag.m_128359_(id + "Name", tracking.m_5446_().getString());
            tag.m_128365_(id + "Pos", (Tag)NbtUtils.m_129224_((BlockPos)tracking.m_20183_()));
        } else {
            tag.m_128405_(id + "Dist", -1);
        }
    }

    public void m_7373_(ItemStack stack, Level level, List<Component> text, TooltipFlag flag) {
        super.m_7373_(stack, level, text, flag);
        CompoundTag tag = stack.m_41784_();
        boolean locked = tag.m_128471_(LOCKED);
        if (((Boolean)WitherStormModConfig.SERVER.amuletOverride.get()).booleanValue()) {
            text.add((Component)Component.m_237115_((String)"description.amulet.mainUse").m_130940_(ChatFormatting.DARK_GRAY));
        }
        text.add((Component)Component.m_237115_((String)"description.amulet.trackingDesc").m_130940_(ChatFormatting.DARK_GRAY));
        text.add((Component)Component.m_237115_((String)"description.amulet.swap").m_130940_(ChatFormatting.DARK_GRAY));
        if (!locked) {
            text.add((Component)Component.m_237115_((String)"description.amulet.bind").m_130940_(ChatFormatting.DARK_GRAY));
        }
        text.add((Component)Component.m_237110_((String)"description.amulet.tracking", (Object[])new Object[]{AmuletItem.getTrackingName(TRACKING_BLUE, tag), AmuletItem.getDistString(TRACKING_BLUE, tag)}).m_130940_(ChatFormatting.BLUE));
        text.add((Component)Component.m_237110_((String)"description.amulet.tracking", (Object[])new Object[]{AmuletItem.getTrackingName(TRACKING_AQUA, tag), AmuletItem.getDistString(TRACKING_AQUA, tag)}).m_130940_(ChatFormatting.AQUA));
        text.add((Component)Component.m_237110_((String)"description.amulet.tracking", (Object[])new Object[]{AmuletItem.getTrackingName(TRACKING_GREEN, tag), AmuletItem.getDistString(TRACKING_GREEN, tag)}).m_130940_(ChatFormatting.GREEN));
        text.add((Component)Component.m_237110_((String)"description.amulet.tracking", (Object[])new Object[]{AmuletItem.getTrackingName(TRACKING_GRAY, tag), AmuletItem.getDistString(TRACKING_GRAY, tag)}).m_130940_(ChatFormatting.GRAY));
        text.add((Component)Component.m_237110_((String)"description.amulet.tracking", (Object[])new Object[]{AmuletItem.getTrackingName(TRACKING_RED, tag), AmuletItem.getDistString(TRACKING_RED, tag)}).m_130940_(ChatFormatting.RED));
        if (locked) {
            text.add((Component)Component.m_237115_((String)"description.amulet.locked").m_130940_(ChatFormatting.YELLOW));
        }
        if (tag.m_128471_(TRACK_ENTITY_TYPES)) {
            text.add((Component)Component.m_237115_((String)"description.amulet.tracksEntityTypes").m_130940_(ChatFormatting.GOLD));
        }
    }

    private static String getDistString(String id, CompoundTag tag) {
        int dist = tag.m_128451_(id + "Dist");
        if (dist >= 0) {
            return String.valueOf(dist);
        }
        if (tag.m_128441_(id + "Name")) {
            return "Could not find nearby";
        }
        return "";
    }

    private static String getTrackingName(String id, CompoundTag tag) {
        if (!tag.m_128441_(id + "Name")) {
            if (id == TRACKING_BLUE) {
                return "No Nearby Wither Storm";
            }
            return "Empty";
        }
        return tag.m_128461_(id + "Name");
    }

    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged ? super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) : false;
    }

    public int getTotalUniqueLinked(ItemStack stack) {
        CompoundTag tag = stack.m_41784_();
        ArrayList ids = Lists.newArrayList();
        for (int i = 0; i < TRACKING.length; ++i) {
            UUID uuid;
            String id = TRACKING[i];
            if (id.equals(TRACKING_BLUE) || !tag.m_128441_(id) || ids.contains(uuid = tag.m_128342_(id))) continue;
            ids.add(uuid);
        }
        return ids.size();
    }

    public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {
        return !newStack.m_150930_(oldStack.m_41720_());
    }
}

