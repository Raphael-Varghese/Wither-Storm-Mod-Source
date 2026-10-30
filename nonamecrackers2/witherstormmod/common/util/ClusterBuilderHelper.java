/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.Component$Serializer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.util.random.SimpleWeightedRandomList
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockState
 */
package nonamecrackers2.witherstormmod.common.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;

public class ClusterBuilderHelper {
    private static final ResourceLocation BOWELS_LOOT = new ResourceLocation("witherstormmod", "chests/bowels_general");
    private static final SimpleWeightedRandomList<Block> OUTSIDE = SimpleWeightedRandomList.m_146263_().m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_FLESH_BLOCK.get()), 20).m_146271_((Object)((Block)WitherStormModBlocks.INFECTED_FLESH_BLOCK.get()), 3).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_PLANKS.get()), 1).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_COBBLESTONE.get()), 1).m_146270_();
    private static final SimpleWeightedRandomList<Block> BLOCKS = SimpleWeightedRandomList.m_146263_().m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_FLESH_BLOCK.get()), 20).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_SAND.get()), 15).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_DIRT.get()), 10).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_COBBLESTONE.get()), 10).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_STONE.get()), 10).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_PLANKS.get()), 8).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_LOG.get()), 5).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_LEAVES.get()), 2).m_146271_((Object)((Block)WitherStormModBlocks.INFECTED_FLESH_BLOCK.get()), 5).m_146270_();
    private static final SimpleWeightedRandomList<Block> JUNK = SimpleWeightedRandomList.m_146263_().m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_FLESH_BLOCK.get()), 8).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_SAND.get()), 5).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_SANDSTONE.get()), 3).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_DIRT.get()), 4).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_COBBLESTONE.get()), 6).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_STONE.get()), 6).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_DUST_BLOCK.get()), 2).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_GLASS.get()), 2).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_PUMPKIN.get()), 1).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_ZOMBIE_LYING.get()), 1).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_BONE_PILE.get()), 1).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_WOOD.get()), 4).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_PLANKS.get()), 6).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_LOG.get()), 6).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_LEAVES.get()), 4).m_146270_();
    private static final SimpleWeightedRandomList<Block> DECORATION = SimpleWeightedRandomList.m_146263_().m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_DUST.get()), 10).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_MUSHROOM.get()), 5).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_BONE_PILE.get()), 1).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_ZOMBIE_SITTING.get()), 1).m_146270_();
    private static final SimpleWeightedRandomList<Block> CHEWED = SimpleWeightedRandomList.m_146263_().m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_FLESH_BLOCK.get()), 10).m_146271_((Object)((Block)WitherStormModBlocks.INFECTED_FLESH_BLOCK.get()), 10).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_ZOMBIE_LYING.get()), 2).m_146271_((Object)((Block)WitherStormModBlocks.TAINTED_BONE_PILE.get()), 2).m_146270_();

    public static BlockClusterEntity buildSmallRandomDeathCluster(Level level, RandomSource random, int radius) {
        LinkedHashMap states = Maps.newLinkedHashMap();
        for (int i = 0; i <= 3; ++i) {
            BlockPos offset = new BlockPos(random.m_188503_(radius) - radius, random.m_188503_(radius) - radius, random.m_188503_(radius) - radius);
            for (int x = -radius; x < radius; ++x) {
                for (int y = -radius; y < radius; ++y) {
                    for (int z = -radius; z < radius; ++z) {
                        BlockPos pos;
                        int sqrt = Mth.m_14143_((float)Mth.m_14116_((float)((float)(x * x) + 1.0f + ((float)(y * y) + 1.0f) + ((float)(z * z) + 1.0f))));
                        if (sqrt > radius || states.containsKey(pos = new BlockPos(x, y, z).m_121955_((Vec3i)offset))) continue;
                        BlockState state = random.m_188501_() < 0.975f ? JUNK.m_216820_(random).orElse(Blocks.f_50016_).m_49966_() : ((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get()).m_49966_();
                        states.put(pos, state);
                    }
                }
            }
        }
        BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(level);
        assert (cluster != null);
        cluster.populate(states);
        for (Map.Entry entry : states.entrySet()) {
            BlockState state = (BlockState)entry.getValue();
            if (!state.m_60713_((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get())) continue;
            BlockPos pos = ((BlockPos)entry.getKey()).m_121955_((Vec3i)cluster.getStartPos());
            CompoundTag tileData = new CompoundTag();
            BlockEntity.m_187468_((CompoundTag)tileData, (BlockEntityType)((BlockEntityType)WitherStormModBlockEntityTypes.WITHERED_PHLEGM.get()));
            tileData.m_128405_("x", pos.m_123341_());
            tileData.m_128405_("y", pos.m_123342_());
            tileData.m_128405_("z", pos.m_123343_());
            tileData.m_128359_("LootTable", BOWELS_LOOT.toString());
            cluster.addTileData(tileData);
        }
        return cluster;
    }

    public static BlockClusterEntity buildRandomDeathCluster(Level level, RandomSource random, int radius) {
        LinkedHashMap states = Maps.newLinkedHashMap();
        radius += random.m_188503_(2);
        float stretch = 3.0f;
        for (int i = 0; i <= 3; ++i) {
            float xStretch = stretch * Math.max(0.5f, random.m_188501_());
            float yStretch = stretch;
            float zStretch = stretch * Math.max(0.5f, random.m_188501_());
            int offsetR = radius - 1;
            BlockPos offset = new BlockPos(random.m_188503_(offsetR * 2) - offsetR, random.m_188503_(radius * 2) - radius, random.m_188503_(radius * 2) - radius);
            for (int x = -radius; x < radius; ++x) {
                for (int y = -radius; y < radius; ++y) {
                    for (int z = -radius; z < radius; ++z) {
                        int sqrt = Mth.m_14143_((float)Mth.m_14116_((float)((float)(x * x) * xStretch + 1.0f + ((float)(y * y) * yStretch + 1.0f) + ((float)(z * z) * zStretch + 1.0f))));
                        if (sqrt > radius) continue;
                        SimpleWeightedRandomList<Block> list = OUTSIDE;
                        if (sqrt < radius - 1) {
                            list = BLOCKS;
                        }
                        int randomY = random.m_188503_(2);
                        for (int l = -randomY; l <= randomY; ++l) {
                            BlockPos pos = new BlockPos(x, y - l, z).m_121955_((Vec3i)offset);
                            if (states.containsKey(pos)) continue;
                            BlockState state = random.m_188501_() < 0.999f ? list.m_216820_(random).orElse(Blocks.f_50016_).m_49966_() : ((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get()).m_49966_();
                            states.put(pos, state);
                        }
                    }
                }
            }
        }
        HashMap toAdd = Maps.newHashMap();
        for (Map.Entry entry : states.entrySet()) {
            BlockPos above = ((BlockPos)entry.getKey()).m_7494_();
            if (states.containsKey(above) || random.m_188503_(3) != 0) continue;
            BlockState decoration = DECORATION.m_216820_(random).orElse(Blocks.f_50016_).m_49966_();
            toAdd.put(above, decoration);
        }
        BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(level);
        toAdd.forEach(states::put);
        cluster.populate(states);
        for (Map.Entry entry : states.entrySet()) {
            BlockState state = (BlockState)entry.getValue();
            if (!state.m_60713_((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get())) continue;
            BlockPos pos = ((BlockPos)entry.getKey()).m_121955_((Vec3i)cluster.getStartPos());
            CompoundTag tileData = new CompoundTag();
            BlockEntity.m_187468_((CompoundTag)tileData, (BlockEntityType)((BlockEntityType)WitherStormModBlockEntityTypes.WITHERED_PHLEGM.get()));
            tileData.m_128405_("x", pos.m_123341_());
            tileData.m_128405_("y", pos.m_123342_());
            tileData.m_128405_("z", pos.m_123343_());
            tileData.m_128359_("LootTable", BOWELS_LOOT.toString());
            cluster.addTileData(tileData);
        }
        return cluster;
    }

    public static BlockClusterEntity buildPhlegmClusterWithItems(Level level, RandomSource random, List<ItemStack> items, @Nullable Component name, int experience) {
        CompoundTag tag;
        int size = items.size();
        ArrayList seperatedItemTags = Lists.newArrayList();
        int currentSlot = 0;
        ListTag currentListTag = new ListTag();
        for (int i = 0; i < size; ++i) {
            ItemStack stack = items.get(i);
            if (stack.m_41619_()) continue;
            if (currentSlot >= 25) {
                currentSlot -= 25;
                seperatedItemTags.add(currentListTag);
                currentListTag = new ListTag();
            }
            CompoundTag tag2 = new CompoundTag();
            tag2.m_128344_("Slot", (byte)currentSlot);
            stack.m_41777_().m_41739_(tag2);
            currentListTag.add((Object)tag2);
            currentSlot += random.m_216332_(1, 3);
        }
        seperatedItemTags.add(currentListTag);
        int experiencePerPhlegm = experience / seperatedItemTags.size();
        List tagsPerPhlegmBlock = seperatedItemTags.stream().filter(Predicate.not(ListTag::isEmpty)).map(l -> {
            CompoundTag tag = new CompoundTag();
            tag.m_128365_("Items", (Tag)l);
            tag.m_128405_("StoredXp", experiencePerPhlegm);
            return tag;
        }).toList();
        BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(level);
        ArrayList availablePositions = Lists.newArrayList();
        int radius = 1;
        for (int x = -radius; x <= radius; ++x) {
            for (int y = -radius; y <= radius; ++y) {
                for (int z = -radius; z <= radius; ++z) {
                    availablePositions.add(new BlockPos(x, y, z));
                }
            }
        }
        HashMap phlegmBlocks = Maps.newHashMap();
        for (int i = 0; i < tagsPerPhlegmBlock.size(); ++i) {
            if (i >= availablePositions.size()) continue;
            int index = 0;
            if (availablePositions.size() > 0) {
                index = random.m_188503_(availablePositions.size());
            }
            BlockPos blockPos = (BlockPos)availablePositions.get(index);
            availablePositions.remove(index);
            tag = (CompoundTag)tagsPerPhlegmBlock.get(i);
            phlegmBlocks.put(blockPos, tag);
        }
        Map<BlockPos, BlockState> blocks = phlegmBlocks.entrySet().stream().map(e -> Map.entry((BlockPos)e.getKey(), ((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get()).m_49966_())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        for (BlockPos blockPos : availablePositions) {
            if (!(random.m_188501_() > 0.6f)) continue;
            blocks.put(blockPos, ((Block)CHEWED.m_216820_(random).get()).m_49966_());
        }
        cluster.populate(blocks);
        for (Map.Entry entry : phlegmBlocks.entrySet()) {
            tag = (CompoundTag)entry.getValue();
            BlockPos pos = ((BlockPos)entry.getKey()).m_121955_((Vec3i)cluster.getStartPos());
            BlockEntity.m_187468_((CompoundTag)tag, (BlockEntityType)((BlockEntityType)WitherStormModBlockEntityTypes.WITHERED_PHLEGM.get()));
            tag.m_128405_("x", pos.m_123341_());
            tag.m_128405_("y", pos.m_123342_());
            tag.m_128405_("z", pos.m_123343_());
            if (name != null) {
                tag.m_128359_("CustomName", Component.Serializer.m_130703_((Component)name));
            }
            cluster.addTileData(tag);
        }
        return cluster;
    }
}

