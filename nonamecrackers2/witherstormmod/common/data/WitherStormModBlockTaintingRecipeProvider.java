/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.data.PackOutput
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraftforge.common.Tags$Blocks
 */
package nonamecrackers2.witherstormmod.common.data;

import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import nonamecrackers2.witherstormmod.api.common.data.BlockTaintingRecipeProvider;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;

public class WitherStormModBlockTaintingRecipeProvider
extends BlockTaintingRecipeProvider {
    public WitherStormModBlockTaintingRecipeProvider(PackOutput output) {
        super(output, "witherstormmod");
    }

    @Override
    protected void addRecipes() {
        this.addAndCopyAllProperties(Blocks.f_50143_, MobEffects.f_19615_, (Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get());
        this.addAndCopyAllProperties(Blocks.f_50144_, MobEffects.f_19615_, (Block)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get());
        this.add(Blocks.f_50133_, MobEffects.f_19615_, ((Block)WitherStormModBlocks.TAINTED_PUMPKIN.get()).m_49966_());
        this.addAndCopyAllProperties(Blocks.f_50072_, (Block)WitherStormModBlocks.TAINTED_MUSHROOM.get());
        this.addAndCopyAllProperties(Blocks.f_50073_, (Block)WitherStormModBlocks.TAINTED_MUSHROOM.get());
        this.addAndCopyAllProperties(Blocks.f_50088_, (Block)WitherStormModBlocks.TAINTED_DUST.get());
        this.addAndCopyAllProperties((TagKey<Block>)BlockTags.f_13037_, Blocks.f_50070_);
        this.addAndCopyAllProperties((TagKey<Block>)BlockTags.f_13061_, (Block)WitherStormModBlocks.TAINTED_STONE.get());
        this.addAndCopyAllProperties(Blocks.f_50635_, (Block)WitherStormModBlocks.TAINTED_STONE_STAIRS.get());
        this.addAndCopyAllProperties(Blocks.f_50404_, (Block)WitherStormModBlocks.TAINTED_STONE_SLAB.get());
        this.addAndCopyAllProperties(Blocks.f_50124_, (Block)WitherStormModBlocks.TAINTED_STONE_BUTTON.get());
        this.addAndCopyAllProperties(Blocks.f_50165_, (Block)WitherStormModBlocks.TAINTED_STONE_PRESSURE_PLATE.get());
        this.addAndCopyAllProperties((TagKey<Block>)Tags.Blocks.COBBLESTONE, (Block)WitherStormModBlocks.TAINTED_COBBLESTONE.get());
        this.addAndCopyAllProperties(Blocks.f_50157_, (Block)WitherStormModBlocks.TAINTED_COBBLESTONE_STAIRS.get());
        this.addAndCopyAllProperties(Blocks.f_50409_, (Block)WitherStormModBlocks.TAINTED_COBBLESTONE_SLAB.get());
        this.addAndCopyAllProperties((TagKey<Block>)BlockTags.f_13032_, (Block)WitherStormModBlocks.TAINTED_COBBLESTONE_WALL.get());
        this.addAndCopyAllProperties((TagKey<Block>)BlockTags.f_144274_, (Block)WitherStormModBlocks.TAINTED_DIRT.get());
        this.addAndCopyAllProperties((TagKey<Block>)Tags.Blocks.SAND, (Block)WitherStormModBlocks.TAINTED_SAND.get());
        this.addAndCopyAllProperties((TagKey<Block>)Tags.Blocks.GLASS, (Block)WitherStormModBlocks.TAINTED_GLASS.get());
        this.addAndCopyAllProperties((TagKey<Block>)Tags.Blocks.GLASS_PANES, (Block)WitherStormModBlocks.TAINTED_GLASS_PANE.get());
        this.addAndCopyAllProperties(Blocks.f_50330_, (Block)WitherStormModBlocks.TAINTED_DUST_BLOCK.get());
        this.addAndCopyAllProperties((TagKey<Block>)BlockTags.f_13090_, (Block)WitherStormModBlocks.TAINTED_PLANKS.get());
        this.addAndCopyAllProperties((TagKey<Block>)BlockTags.f_215839_, (Block)WitherStormModBlocks.TAINTED_LOG.get());
        this.addAndCopyAllProperties((TagKey<Block>)BlockTags.f_13035_, (Block)WitherStormModBlocks.TAINTED_LEAVES.get());
        this.addAndCopyAllProperties((TagKey<Block>)BlockTags.f_13095_, (Block)WitherStormModBlocks.TAINTED_DOOR.get());
        this.addAndCopyAllProperties((TagKey<Block>)BlockTags.f_13102_, (Block)WitherStormModBlocks.TAINTED_TRAPDOOR.get());
        this.addAndCopyAllProperties((TagKey<Block>)BlockTags.f_13092_, (Block)WitherStormModBlocks.TAINTED_BUTTON.get());
        this.addAndCopyAllProperties((TagKey<Block>)BlockTags.f_13100_, (Block)WitherStormModBlocks.TAINTED_PRESSURE_PLATE.get());
        this.addAndCopyAllProperties((TagKey<Block>)BlockTags.f_13096_, (Block)WitherStormModBlocks.TAINTED_STAIRS.get());
        this.addAndCopyAllProperties((TagKey<Block>)BlockTags.f_13097_, (Block)WitherStormModBlocks.TAINTED_SLAB.get());
        this.addAndCopyAllProperties((TagKey<Block>)BlockTags.f_13098_, (Block)WitherStormModBlocks.TAINTED_FENCE.get());
        this.addAndCopyAllProperties((TagKey<Block>)BlockTags.f_13055_, (Block)WitherStormModBlocks.TAINTED_FENCE_GATE.get());
        this.addAndCopyAllProperties((TagKey<Block>)Tags.Blocks.SANDSTONE, (Block)WitherStormModBlocks.TAINTED_SANDSTONE.get());
        this.addAndCopyAllProperties(Blocks.f_50406_, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_SLAB.get());
        this.addAndCopyAllProperties(Blocks.f_50263_, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_STAIRS.get());
        this.addAndCopyAllProperties(Blocks.f_50613_, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_WALL.get());
        this.addAndCopyAllProperties(Blocks.f_50064_, (Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE.get());
        this.addAndCopyAllProperties(Blocks.f_50407_, (Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE_SLAB.get());
        this.addAndCopyAllProperties(Blocks.f_50471_, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.get());
        this.addAndCopyAllProperties(Blocks.f_50649_, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_SLAB.get());
        this.addAndCopyAllProperties(Blocks.f_50636_, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_STAIRS.get());
        this.addAndCopyAllProperties(Blocks.f_50394_, (Block)WitherStormModBlocks.TAINTED_SANDSTONE.get());
        this.addAndCopyAllProperties(Blocks.f_50467_, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_SLAB.get());
        this.addAndCopyAllProperties(Blocks.f_50397_, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_STAIRS.get());
        this.addAndCopyAllProperties(Blocks.f_50606_, (Block)WitherStormModBlocks.TAINTED_SANDSTONE_WALL.get());
        this.addAndCopyAllProperties(Blocks.f_50396_, (Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE.get());
        this.addAndCopyAllProperties(Blocks.f_50468_, (Block)WitherStormModBlocks.TAINTED_CUT_SANDSTONE_SLAB.get());
        this.addAndCopyAllProperties(Blocks.f_50473_, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE.get());
        this.addAndCopyAllProperties(Blocks.f_50644_, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_SLAB.get());
        this.addAndCopyAllProperties(Blocks.f_50630_, (Block)WitherStormModBlocks.TAINTED_SMOOTH_SANDSTONE_STAIRS.get());
        this.addAndCopyAllProperties(Blocks.f_50081_, (Block)WitherStormModBlocks.TAINTED_TORCH.get());
        this.addAndCopyAllProperties(Blocks.f_50082_, (Block)WitherStormModBlocks.TAINTED_WALL_TORCH.get());
    }
}

