/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.BaseEntityBlock
 *  net.minecraft.world.level.block.RenderShape
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityTicker
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 */
package nonamecrackers2.witherstormmod.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import nonamecrackers2.witherstormmod.common.blockentity.AbstractSuperBeaconBlockEntity;

public abstract class AbstractSuperBeaconBlock
extends BaseEntityBlock {
    public AbstractSuperBeaconBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public RenderShape m_7514_(BlockState state) {
        return RenderShape.MODEL;
    }

    protected static <T extends BlockEntity> BlockEntityTicker<T> createBeaconTicker(Level level, BlockEntityType<T> type, BlockEntityType<? extends AbstractSuperBeaconBlockEntity> type1) {
        return AbstractSuperBeaconBlock.m_152132_(type, type1, (l, s, p, entity) -> entity.tick());
    }

    public void m_6402_(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
        BlockEntity blockEntity;
        if (stack.m_41788_() && (blockEntity = level.m_7702_(pos)) instanceof AbstractSuperBeaconBlockEntity) {
            AbstractSuperBeaconBlockEntity beacon = (AbstractSuperBeaconBlockEntity)blockEntity;
            beacon.setCustomName(stack.m_41786_());
        }
    }
}

