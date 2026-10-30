/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.BushBlock
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.VoxelShape
 *  net.minecraftforge.common.IPlantable
 */
package nonamecrackers2.witherstormmod.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;

public class NonGrowableMushroomBlock
extends BushBlock {
    protected static final VoxelShape SHAPE = Block.m_49796_((double)5.0, (double)0.0, (double)5.0, (double)11.0, (double)6.0, (double)11.0);

    public NonGrowableMushroomBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    protected boolean m_6266_(BlockState state, BlockGetter reader, BlockPos pos) {
        return state.m_60804_(reader, pos);
    }

    public boolean m_7898_(BlockState state, LevelReader reader, BlockPos pos) {
        BlockPos blockpos = pos.m_7495_();
        BlockState blockstate = reader.m_8055_(blockpos);
        if (blockstate.m_204336_(BlockTags.f_13057_)) {
            return true;
        }
        return reader.m_45524_(pos, 0) < 13 && blockstate.canSustainPlant((BlockGetter)reader, blockpos, Direction.UP, (IPlantable)this);
    }

    public VoxelShape m_5940_(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}

