/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.core.BlockPos
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate$StructureBlockInfo
 */
package nonamecrackers2.witherstormmod.common.world.gen.feature.template;

import com.google.common.collect.ImmutableList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class BlockIgnoreIrreplacableProcessor
extends BlockIgnoreProcessor {
    public static final BlockIgnoreIrreplacableProcessor AIR = new BlockIgnoreIrreplacableProcessor((List<Block>)ImmutableList.of((Object)Blocks.f_50016_), false);
    public static final BlockIgnoreIrreplacableProcessor AIR_REMOVE = new BlockIgnoreIrreplacableProcessor((List<Block>)ImmutableList.of((Object)Blocks.f_50016_), true);
    private final boolean removeMode;

    public BlockIgnoreIrreplacableProcessor(List<Block> ignore, boolean remove) {
        super(ignore);
        this.removeMode = remove;
    }

    public StructureTemplate.StructureBlockInfo m_7382_(LevelReader world, BlockPos pos, BlockPos pos1, StructureTemplate.StructureBlockInfo original, StructureTemplate.StructureBlockInfo copy, StructurePlaceSettings settings) {
        BlockState previous = world.m_8055_(copy.f_74675_());
        StructureTemplate.StructureBlockInfo info = copy;
        if (previous.m_204336_(BlockTags.f_144287_)) {
            info = null;
        }
        if (super.m_7382_(world, pos, pos1, original, copy, settings) == null) {
            info = null;
        }
        if (this.removeMode && info != null) {
            info = new StructureTemplate.StructureBlockInfo(info.f_74675_(), Blocks.f_50016_.m_49966_(), info.f_74677_());
        }
        return info;
    }
}

