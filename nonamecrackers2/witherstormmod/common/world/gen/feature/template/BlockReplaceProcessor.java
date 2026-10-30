/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.serialization.Codec
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockBehaviour$BlockStateBase
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate$StructureBlockInfo
 */
package nonamecrackers2.witherstormmod.common.world.gen.feature.template;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class BlockReplaceProcessor
extends StructureProcessor {
    public static final Codec<BlockReplaceProcessor> CODEC = BlockState.f_61039_.xmap(BlockBehaviour.BlockStateBase::m_60734_, Block::m_49966_).listOf().fieldOf("blocks").xmap(BlockReplaceProcessor::new, proc -> proc.toReplace).codec();
    private final ImmutableList<Block> toReplace;

    public BlockReplaceProcessor(List<Block> blocks) {
        this.toReplace = ImmutableList.copyOf(blocks);
    }

    public StructureTemplate.StructureBlockInfo m_7382_(LevelReader reader, BlockPos pos, BlockPos pos1, StructureTemplate.StructureBlockInfo original, StructureTemplate.StructureBlockInfo copy, StructurePlaceSettings settings) {
        BlockState prev = reader.m_8055_(copy.f_74675_());
        return this.toReplace.contains((Object)prev.m_60734_()) ? copy : null;
    }

    protected StructureProcessorType<?> m_6953_() {
        return null;
    }
}

