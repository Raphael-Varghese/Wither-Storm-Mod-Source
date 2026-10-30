/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Direction
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.BlockStateProperties
 *  net.minecraft.world.level.block.state.properties.DirectionProperty
 *  net.minecraft.world.level.block.state.properties.Property
 */
package nonamecrackers2.witherstormmod.common.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class TaintedStatue
extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.f_61374_;

    public TaintedStatue(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }

    public BlockState m_5573_(BlockPlaceContext pContext) {
        return (BlockState)this.m_49966_().m_61124_((Property)FACING, (Comparable)pContext.m_8125_().m_122424_());
    }

    public BlockState m_6843_(BlockState pState, Rotation pRotation) {
        return (BlockState)pState.m_61124_((Property)FACING, (Comparable)pRotation.m_55954_((Direction)pState.m_61143_((Property)FACING)));
    }

    public BlockState m_6943_(BlockState pState, Mirror pMirror) {
        return pState.m_60717_(pMirror.m_54846_((Direction)pState.m_61143_((Property)FACING)));
    }

    protected void m_7926_(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.m_61104_(new Property[]{FACING});
    }
}

