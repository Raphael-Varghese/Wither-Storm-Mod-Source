/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.advancements.critereon.EnchantmentPredicate
 *  net.minecraft.advancements.critereon.ItemPredicate$Builder
 *  net.minecraft.advancements.critereon.MinMaxBounds$Ints
 *  net.minecraft.data.loot.BlockLootSubProvider
 *  net.minecraft.world.flag.FeatureFlags
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.enchantment.Enchantments
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.storage.loot.LootPool
 *  net.minecraft.world.level.storage.loot.LootPool$Builder
 *  net.minecraft.world.level.storage.loot.LootTable
 *  net.minecraft.world.level.storage.loot.LootTable$Builder
 *  net.minecraft.world.level.storage.loot.entries.LootItem
 *  net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer$Builder
 *  net.minecraft.world.level.storage.loot.functions.LootItemFunction$Builder
 *  net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
 *  net.minecraft.world.level.storage.loot.predicates.ConditionUserBuilder
 *  net.minecraft.world.level.storage.loot.predicates.MatchTool
 *  net.minecraft.world.level.storage.loot.providers.number.ConstantValue
 *  net.minecraft.world.level.storage.loot.providers.number.NumberProvider
 *  net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
 */
package nonamecrackers2.witherstormmod.common.data.loot;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Set;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.ConditionUserBuilder;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;

public class WitherStormModBlockLootProvider
extends BlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = Set.of((Item)WitherStormModItems.FORMIDIBOMB.get(), (Item)WitherStormModItems.INFECTED_FLESH_BLOCK.get(), (Item)WitherStormModItems.SUPER_BEACON.get(), (Item)WitherStormModItems.SUPER_SUPPORT_BEACON.get(), (Item)WitherStormModItems.SUPER_TNT.get());

    public WitherStormModBlockLootProvider() {
        super(EXPLOSION_RESISTANT, FeatureFlags.f_244280_.m_247355_());
    }

    protected void m_245660_() {
        this.m_245724_((Block)WitherStormModBlocks.FIREWORK_BUNDLE.get());
        this.m_245724_((Block)WitherStormModBlocks.FORMIDIBOMB.get());
        this.m_245724_((Block)WitherStormModBlocks.INFECTED_FLESH_BLOCK.get());
        this.m_246535_((Block)WitherStormModBlocks.POTTED_TAINTED_MUSHROOM.get());
        this.m_245724_((Block)WitherStormModBlocks.SUPER_BEACON.get());
        this.m_245724_((Block)WitherStormModBlocks.SUPER_SUPPORT_BEACON.get());
        this.m_245724_((Block)WitherStormModBlocks.SUPER_TNT.get());
        this.m_246481_((Block)WitherStormModBlocks.TAINTED_BONE_PILE.get(), this::createTaintedSkullRemainsTable);
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_BUTTON.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get());
        this.m_246481_((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_SLAB.get(), x$0 -> this.m_247233_((Block)x$0));
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_STAIRS.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_WALL.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_COBBLESTONE.get());
        this.m_246481_((Block)WitherStormModBlocks.TAINTED_DOOR.get(), x$0 -> this.m_247398_((Block)x$0));
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_DUST_BLOCK.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_DUST.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_FENCE_GATE.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_FENCE.get());
        this.m_246481_((Block)WitherStormModBlocks.TAINTED_FLESH_VEINS.get(), b -> this.m_246235_((Block)b, MatchTool.m_81997_((ItemPredicate.Builder)ItemPredicate.Builder.m_45068_().m_45071_(new EnchantmentPredicate(Enchantments.f_44985_, MinMaxBounds.Ints.m_55386_((int)1))))));
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_FLESH_BLOCK.get());
        this.m_245644_((Block)WitherStormModBlocks.TAINTED_GLASS_PANE.get());
        this.m_245644_((Block)WitherStormModBlocks.TAINTED_GLASS.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get());
        this.m_246481_((Block)WitherStormModBlocks.TAINTED_LEAVES.get(), x$0 -> this.m_245170_((Block)x$0));
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_LOG.get());
        this.m_245724_((Block)WitherStormModBlocks.STRIPPED_TAINTED_LOG.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_TORCH.get());
        this.m_247577_((Block)WitherStormModBlocks.TAINTED_WALL_TORCH.get(), this.m_247033_((ItemLike)WitherStormModBlocks.TAINTED_TORCH.get()));
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_MUSHROOM.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_PLANKS.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_PRESSURE_PLATE.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_PUMPKIN.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_SAND.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_SANDSTONE.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_SANDSTONE_STAIRS.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_SANDSTONE_SLAB.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_SANDSTONE_WALL.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE_SLAB.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_CHISELED_SANDSTONE.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_STAIRS.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_SLAB.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_WALL.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_DIRT.get());
        this.m_246481_((Block)WitherStormModBlocks.TAINTED_SKELETON_WALL.get(), this::createTaintedSkullRemainsTable);
        this.m_246481_((Block)WitherStormModBlocks.TAINTED_SKULL_CEILING.get(), this::createTaintedSkullRemainsTable);
        this.m_246481_((Block)WitherStormModBlocks.TAINTED_SLAB.get(), x$0 -> this.m_247233_((Block)x$0));
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_STAIRS.get());
        this.m_246481_((Block)WitherStormModBlocks.TAINTED_STONE.get(), b -> this.m_245514_((Block)b, (ItemLike)WitherStormModBlocks.TAINTED_COBBLESTONE.get()));
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_STONE_BUTTON.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_STONE_PRESSURE_PLATE.get());
        this.m_246481_((Block)WitherStormModBlocks.TAINTED_STONE_SLAB.get(), x$0 -> this.m_247233_((Block)x$0));
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_STONE_STAIRS.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_TRAPDOOR.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_WOOD.get());
        this.m_245724_((Block)WitherStormModBlocks.STRIPPED_TAINTED_WOOD.get());
        this.m_246481_((Block)WitherStormModBlocks.TAINTED_ZOMBIE_LYING.get(), this::createTaintedZombieRemainsTable);
        this.m_246481_((Block)WitherStormModBlocks.TAINTED_ZOMBIE_SITTING.get(), this::createTaintedZombieRemainsTable);
        this.m_246481_((Block)WitherStormModBlocks.TAINTED_ZOMBIE_WALL.get(), this::createTaintedZombieRemainsTable);
        this.m_245724_((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get());
        this.m_245724_((Block)WitherStormModBlocks.TAINTED_SIGN.get());
    }

    protected Iterable<Block> getKnownBlocks() {
        ArrayList list = Lists.newArrayList();
        list.add((Block)WitherStormModBlocks.FIREWORK_BUNDLE.get());
        list.add((Block)WitherStormModBlocks.FORMIDIBOMB.get());
        list.add((Block)WitherStormModBlocks.INFECTED_FLESH_BLOCK.get());
        list.add((Block)WitherStormModBlocks.POTTED_TAINTED_MUSHROOM.get());
        list.add((Block)WitherStormModBlocks.SUPER_BEACON.get());
        list.add((Block)WitherStormModBlocks.SUPER_SUPPORT_BEACON.get());
        list.add((Block)WitherStormModBlocks.SUPER_TNT.get());
        list.add((Block)WitherStormModBlocks.TAINTED_BONE_PILE.get());
        list.add((Block)WitherStormModBlocks.TAINTED_BUTTON.get());
        list.add((Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get());
        list.add((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_SLAB.get());
        list.add((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_STAIRS.get());
        list.add((Block)WitherStormModBlocks.TAINTED_COBBLESTONE_WALL.get());
        list.add((Block)WitherStormModBlocks.TAINTED_COBBLESTONE.get());
        list.add((Block)WitherStormModBlocks.TAINTED_DOOR.get());
        list.add((Block)WitherStormModBlocks.TAINTED_DUST_BLOCK.get());
        list.add((Block)WitherStormModBlocks.TAINTED_DUST.get());
        list.add((Block)WitherStormModBlocks.TAINTED_FENCE_GATE.get());
        list.add((Block)WitherStormModBlocks.TAINTED_FENCE.get());
        list.add((Block)WitherStormModBlocks.TAINTED_FLESH_VEINS.get());
        list.add((Block)WitherStormModBlocks.TAINTED_FLESH_BLOCK.get());
        list.add((Block)WitherStormModBlocks.TAINTED_GLASS_PANE.get());
        list.add((Block)WitherStormModBlocks.TAINTED_GLASS.get());
        list.add((Block)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get());
        list.add((Block)WitherStormModBlocks.TAINTED_LEAVES.get());
        list.add((Block)WitherStormModBlocks.TAINTED_LOG.get());
        list.add((Block)WitherStormModBlocks.STRIPPED_TAINTED_LOG.get());
        list.add((Block)WitherStormModBlocks.TAINTED_MUSHROOM.get());
        list.add((Block)WitherStormModBlocks.TAINTED_TORCH.get());
        list.add((Block)WitherStormModBlocks.TAINTED_WALL_TORCH.get());
        list.add((Block)WitherStormModBlocks.TAINTED_PLANKS.get());
        list.add((Block)WitherStormModBlocks.TAINTED_PRESSURE_PLATE.get());
        list.add((Block)WitherStormModBlocks.TAINTED_PUMPKIN.get());
        list.add((Block)WitherStormModBlocks.TAINTED_SAND.get());
        list.add((Block)WitherStormModBlocks.TAINTED_SANDSTONE.get());
        list.add((Block)WitherStormModBlocks.TAINTED_SANDSTONE_STAIRS.get());
        list.add((Block)WitherStormModBlocks.TAINTED_SANDSTONE_SLAB.get());
        list.add((Block)WitherStormModBlocks.TAINTED_SANDSTONE_WALL.get());
        list.add((Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE.get());
        list.add((Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE_SLAB.get());
        list.add((Block)WitherStormModBlocks.TAINTED_CHISELED_SANDSTONE.get());
        list.add((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.get());
        list.add((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_STAIRS.get());
        list.add((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_SLAB.get());
        list.add((Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_WALL.get());
        list.add((Block)WitherStormModBlocks.TAINTED_DIRT.get());
        list.add((Block)WitherStormModBlocks.TAINTED_SKELETON_WALL.get());
        list.add((Block)WitherStormModBlocks.TAINTED_SKULL_CEILING.get());
        list.add((Block)WitherStormModBlocks.TAINTED_SLAB.get());
        list.add((Block)WitherStormModBlocks.TAINTED_STAIRS.get());
        list.add((Block)WitherStormModBlocks.TAINTED_STONE_BUTTON.get());
        list.add((Block)WitherStormModBlocks.TAINTED_STONE_PRESSURE_PLATE.get());
        list.add((Block)WitherStormModBlocks.TAINTED_STONE_SLAB.get());
        list.add((Block)WitherStormModBlocks.TAINTED_STONE_STAIRS.get());
        list.add((Block)WitherStormModBlocks.TAINTED_STONE.get());
        list.add((Block)WitherStormModBlocks.TAINTED_TRAPDOOR.get());
        list.add((Block)WitherStormModBlocks.TAINTED_WOOD.get());
        list.add((Block)WitherStormModBlocks.STRIPPED_TAINTED_WOOD.get());
        list.add((Block)WitherStormModBlocks.TAINTED_ZOMBIE_LYING.get());
        list.add((Block)WitherStormModBlocks.TAINTED_ZOMBIE_SITTING.get());
        list.add((Block)WitherStormModBlocks.TAINTED_ZOMBIE_WALL.get());
        list.add((Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get());
        list.add((Block)WitherStormModBlocks.TAINTED_SIGN.get());
        return list;
    }

    protected LootTable.Builder createTaintedSkullRemainsTable(Block block) {
        return LootTable.m_79147_().m_79161_((LootPool.Builder)this.m_247733_((ItemLike)block, (ConditionUserBuilder)LootPool.m_79043_().m_165133_((NumberProvider)ConstantValue.m_165692_((float)2.0f)).m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)((ItemLike)WitherStormModItems.TAINTED_DUST.get())).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)ConstantValue.m_165692_((float)1.0f)))).m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)((ItemLike)WitherStormModItems.WITHERED_BONE.get())).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)1.0f, (float)2.0f))))));
    }

    protected LootTable.Builder createTaintedZombieRemainsTable(Block block) {
        return LootTable.m_79147_().m_79161_((LootPool.Builder)this.m_247733_((ItemLike)block, (ConditionUserBuilder)LootPool.m_79043_().m_165133_((NumberProvider)ConstantValue.m_165692_((float)2.0f)).m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)((ItemLike)WitherStormModItems.WITHERED_FLESH.get())).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)1.0f, (float)2.0f)))).m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)((ItemLike)WitherStormModItems.WITHERED_BONE.get())).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)ConstantValue.m_165692_((float)1.0f))))));
    }
}

