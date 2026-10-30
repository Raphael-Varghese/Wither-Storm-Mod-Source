/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityTicker
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.shapes.BooleanOp
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.Shapes
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package nonamecrackers2.witherstormmod.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import nonamecrackers2.witherstormmod.common.block.AbstractSuperBeaconBlock;
import nonamecrackers2.witherstormmod.common.blockentity.AbstractSuperBeaconBlockEntity;
import nonamecrackers2.witherstormmod.common.blockentity.SuperBeaconBlockEntity;
import nonamecrackers2.witherstormmod.common.blockentity.SuperSupportBeaconBlockEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;

public class SuperSupportBeaconBlock
extends AbstractSuperBeaconBlock {
    private static final VoxelShape SHAPE = Shapes.m_83113_((VoxelShape)Block.m_49796_((double)3.0, (double)4.0, (double)3.0, (double)13.0, (double)14.0, (double)13.0), (VoxelShape)Shapes.m_83113_((VoxelShape)Block.m_49796_((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)3.0, (double)16.0), (VoxelShape)Block.m_49796_((double)2.0, (double)3.0, (double)2.0, (double)14.0, (double)4.0, (double)14.0), (BooleanOp)BooleanOp.f_82695_), (BooleanOp)BooleanOp.f_82695_);

    public SuperSupportBeaconBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public AbstractSuperBeaconBlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SuperSupportBeaconBlockEntity(pos, state);
    }

    public <T extends BlockEntity> BlockEntityTicker<T> m_142354_(Level level, BlockState state, BlockEntityType<T> type) {
        return SuperSupportBeaconBlock.createBeaconTicker(level, type, (BlockEntityType<? extends AbstractSuperBeaconBlockEntity>)((BlockEntityType)WitherStormModBlockEntityTypes.SUPER_SUPPORT_BEACON.get()));
    }

    public VoxelShape m_5940_(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public InteractionResult m_6227_(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        SuperSupportBeaconBlockEntity beacon;
        SuperBeaconBlockEntity connected;
        if (level.f_46443_) {
            return InteractionResult.SUCCESS;
        }
        BlockEntity entity = level.m_7702_(pos);
        if (entity instanceof SuperSupportBeaconBlockEntity && ((connected = (beacon = (SuperSupportBeaconBlockEntity)entity).getConnectedBeaconEntity()) == null || !connected.isDoingResummonAnimation())) {
            player.m_5893_((MenuProvider)beacon);
        }
        return InteractionResult.CONSUME;
    }
}

