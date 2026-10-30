/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.Container
 *  net.minecraft.world.Containers
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityTicker
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.BlockHitResult
 */
package nonamecrackers2.witherstormmod.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import nonamecrackers2.witherstormmod.common.block.AbstractSuperBeaconBlock;
import nonamecrackers2.witherstormmod.common.blockentity.AbstractSuperBeaconBlockEntity;
import nonamecrackers2.witherstormmod.common.blockentity.SuperBeaconBlockEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModStats;

public class SuperBeaconBlock
extends AbstractSuperBeaconBlock {
    public SuperBeaconBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public BlockEntity m_142194_(BlockPos pos, BlockState state) {
        return new SuperBeaconBlockEntity(pos, state);
    }

    public <T extends BlockEntity> BlockEntityTicker<T> m_142354_(Level level, BlockState state, BlockEntityType<T> type) {
        return SuperBeaconBlock.createBeaconTicker(level, type, (BlockEntityType<? extends AbstractSuperBeaconBlockEntity>)((BlockEntityType)WitherStormModBlockEntityTypes.SUPER_BEACON.get()));
    }

    public InteractionResult m_6227_(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        SuperBeaconBlockEntity beacon;
        if (level.f_46443_) {
            return InteractionResult.SUCCESS;
        }
        BlockEntity entity = level.m_7702_(pos);
        if (entity instanceof SuperBeaconBlockEntity && !(beacon = (SuperBeaconBlockEntity)entity).isDoingResummonAnimation() && beacon.canPlayerUseItems(player)) {
            ItemStack item = player.m_21120_(hand);
            if (!player.m_6144_() && beacon.m_6643_() > 0 && item.m_41619_()) {
                ItemStack stack = beacon.takeItem();
                if (!stack.m_41619_()) {
                    player.m_36356_(stack);
                }
            } else if (!item.m_41619_()) {
                if (beacon.m_6643_() < 16) {
                    ItemStack stack = item.m_41777_();
                    stack.m_41764_(1);
                    if (!player.m_150110_().f_35937_) {
                        item.m_41774_(1);
                    }
                    beacon.addItem(stack);
                    level.m_5594_(null, pos, SoundEvents.f_12019_, SoundSource.BLOCKS, 1.0f, 1.0f);
                }
            } else {
                player.m_5893_((MenuProvider)beacon);
                player.m_36220_(WitherStormModStats.INTERACT_WITH_SUPER_BEACON);
            }
        }
        return InteractionResult.CONSUME;
    }

    public void m_6810_(BlockState state, Level level, BlockPos pos, BlockState state2, boolean flag) {
        if (!state.m_60713_(state2.m_60734_())) {
            BlockEntity entity = level.m_7702_(pos);
            if (entity instanceof Container) {
                Container container = (Container)entity;
                Containers.m_19002_((Level)level, (BlockPos)pos, (Container)container);
            }
            super.m_6810_(state, level, pos, state2, flag);
        }
    }
}

