/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.stats.Stats
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraftforge.common.ToolActions
 */
package nonamecrackers2.witherstormmod.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ToolActions;
import nonamecrackers2.witherstormmod.common.block.TaintedCarvedPumpkinBlock;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;

public class TaintedPumpkinBlock
extends Block {
    public TaintedPumpkinBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public InteractionResult m_6227_(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack item = player.m_21120_(hand);
        if (item.canPerformAction(ToolActions.SHEARS_CARVE)) {
            if (!level.f_46443_) {
                Direction hitSide = hitResult.m_82434_();
                Direction direction = hitSide.m_122434_() == Direction.Axis.Y ? player.m_6350_().m_122424_() : hitSide;
                level.m_5594_(null, pos, SoundEvents.f_12296_, SoundSource.BLOCKS, 1.0f, 1.0f);
                level.m_7731_(pos, (BlockState)((Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get()).m_49966_().m_61124_((Property)TaintedCarvedPumpkinBlock.FACING, (Comparable)direction), 11);
                item.m_41622_(1, (LivingEntity)player, p -> p.m_21190_(hand));
                level.m_142346_((Entity)player, GameEvent.f_157781_, pos);
                player.m_36246_(Stats.f_12982_.m_12902_((Object)Items.f_42574_));
            }
            return InteractionResult.m_19078_((boolean)level.f_46443_);
        }
        return InteractionResult.PASS;
    }
}

