/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.BonemealableBlock
 *  net.minecraft.world.level.block.MultifaceBlock
 *  net.minecraft.world.level.block.MultifaceSpreader
 *  net.minecraft.world.level.block.SimpleWaterloggedBlock
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.BlockStateProperties
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.level.material.Fluids
 */
package nonamecrackers2.witherstormmod.common.block;

import java.util.function.ToIntFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.MultifaceSpreader;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class TaintedVeinBlock
extends MultifaceBlock
implements BonemealableBlock,
SimpleWaterloggedBlock {
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.f_61362_;
    private final MultifaceSpreader spreader = new MultifaceSpreader((MultifaceBlock)this);

    public TaintedVeinBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.m_49959_((BlockState)this.m_49966_().m_61124_((Property)WATERLOGGED, (Comparable)Boolean.valueOf(false)));
    }

    public static ToIntFunction<BlockState> emission(int lightLevel) {
        return state -> MultifaceBlock.m_153960_((BlockState)state) ? lightLevel : 0;
    }

    protected void m_7926_(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.m_7926_(stateBuilder);
        stateBuilder.m_61104_(new Property[]{WATERLOGGED});
    }

    public BlockState m_7417_(BlockState state, Direction direction, BlockState state2, LevelAccessor levelAccessor, BlockPos pos, BlockPos pos2) {
        if (((Boolean)state.m_61143_((Property)WATERLOGGED)).booleanValue()) {
            levelAccessor.m_186469_(pos, (Fluid)Fluids.f_76193_, Fluids.f_76193_.m_6718_((LevelReader)levelAccessor));
        }
        return super.m_7417_(state, direction, state2, levelAccessor, pos, pos2);
    }

    public boolean m_6864_(BlockState state, BlockPlaceContext placeContext) {
        return !placeContext.m_43722_().m_150930_(Items.f_151025_) || super.m_6864_(state, placeContext);
    }

    public boolean m_7370_(LevelReader levelreader, BlockPos pos, BlockState state, boolean yeah) {
        return false;
    }

    public boolean m_214167_(Level level, RandomSource randomSource, BlockPos pos, BlockState state) {
        return false;
    }

    public void m_214148_(ServerLevel level, RandomSource randomSource, BlockPos pos, BlockState state) {
        this.spreader.m_221619_(state, (LevelAccessor)level, pos, randomSource);
    }

    public FluidState m_5888_(BlockState state) {
        return (Boolean)state.m_61143_((Property)WATERLOGGED) != false ? Fluids.f_76193_.m_76068_(false) : super.m_5888_(state);
    }

    public boolean m_7420_(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.m_60819_().m_76178_();
    }

    public MultifaceSpreader m_213612_() {
        return this.spreader;
    }
}

