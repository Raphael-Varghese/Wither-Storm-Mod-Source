/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.item.Equipable
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.CarvedPumpkinBlock
 *  net.minecraft.world.level.block.HorizontalDirectionalBlock
 *  net.minecraft.world.level.block.state.BlockBehaviour$BlockStateBase
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.pattern.BlockInWorld
 *  net.minecraft.world.level.block.state.pattern.BlockPattern
 *  net.minecraft.world.level.block.state.pattern.BlockPattern$BlockPatternMatch
 *  net.minecraft.world.level.block.state.pattern.BlockPatternBuilder
 *  net.minecraft.world.level.block.state.predicate.BlockStatePredicate
 *  net.minecraft.world.level.block.state.properties.DirectionProperty
 *  net.minecraft.world.level.block.state.properties.Property
 */
package nonamecrackers2.witherstormmod.common.block;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import nonamecrackers2.witherstormmod.common.entity.SickenedIronGolem;
import nonamecrackers2.witherstormmod.common.entity.SickenedSnowGolem;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;

public class TaintedCarvedPumpkinBlock
extends HorizontalDirectionalBlock
implements Equipable {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.f_54117_;
    @Nullable
    private BlockPattern taintedSnowGolemBase;
    @Nullable
    private BlockPattern taintedSnowGolemFull;
    @Nullable
    private BlockPattern taintedIronGolemBase;
    @Nullable
    private BlockPattern taintedIronGolemFull;
    private static final Predicate<BlockState> PUMPKINS_PREDICATE = state -> state != null && (state.m_60713_((Block)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get()) || state.m_60713_((Block)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get()));

    public TaintedCarvedPumpkinBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.m_49959_((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)FACING, (Comparable)Direction.NORTH));
    }

    public boolean canSpawnGolem(LevelReader level, BlockPos pos) {
        return this.getOrCreateTaintedIronGolemBase().m_61184_(level, pos) != null || this.getOrCreateTaintedSnowGolemBase().m_61184_(level, pos) != null;
    }

    private void trySpawnGolem(Level level, BlockPos pos) {
        BlockPattern.BlockPatternMatch snowmatch = this.getOrCreateTaintedSnowGolemFull().m_61184_((LevelReader)level, pos);
        if (snowmatch != null) {
            SickenedSnowGolem golem = (SickenedSnowGolem)((EntityType)WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get()).m_20615_(level);
            if (golem != null) {
                BlockPos spawnPos = snowmatch.m_61229_(0, 2, 0).m_61176_();
                CarvedPumpkinBlock.m_245585_((Level)level, (BlockPattern.BlockPatternMatch)snowmatch);
                golem.m_7678_((double)spawnPos.m_123341_() + 0.5, (double)spawnPos.m_123342_() + 0.05, (double)spawnPos.m_123343_() + 0.5, 0.0f, 0.0f);
                level.m_7967_((Entity)golem);
                CarvedPumpkinBlock.m_246758_((Level)level, (BlockPattern.BlockPatternMatch)snowmatch);
            }
        } else {
            SickenedIronGolem golem;
            BlockPattern.BlockPatternMatch ironmatch = this.getOrCreateTaintedIronGolemFull().m_61184_((LevelReader)level, pos);
            if (ironmatch != null && (golem = (SickenedIronGolem)((EntityType)WitherStormModEntityTypes.SICKENED_IRON_GOLEM.get()).m_20615_(level)) != null) {
                BlockPos spawnPos = ironmatch.m_61229_(1, 2, 0).m_61176_();
                CarvedPumpkinBlock.m_245585_((Level)level, (BlockPattern.BlockPatternMatch)ironmatch);
                golem.m_7678_((double)spawnPos.m_123341_() + 0.5, (double)spawnPos.m_123342_() + 0.05, (double)spawnPos.m_123343_() + 0.5, 0.0f, 0.0f);
                level.m_7967_((Entity)golem);
                for (ServerPlayer serverplayer : level.m_45976_(ServerPlayer.class, golem.m_20191_().m_82400_(5.0))) {
                    CriteriaTriggers.f_10580_.m_68256_(serverplayer, (Entity)golem);
                }
                CarvedPumpkinBlock.m_246758_((Level)level, (BlockPattern.BlockPatternMatch)ironmatch);
            }
        }
    }

    public EquipmentSlot m_40402_() {
        return EquipmentSlot.HEAD;
    }

    public void m_6807_(BlockState state, Level level, BlockPos pos, BlockState other, boolean flag) {
        if (!state.m_60713_(other.m_60734_())) {
            this.trySpawnGolem(level, pos);
        }
    }

    public BlockState m_5573_(BlockPlaceContext context) {
        return (BlockState)this.m_49966_().m_61124_((Property)FACING, (Comparable)context.m_8125_().m_122424_());
    }

    protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
        builder.m_61104_(new Property[]{FACING});
    }

    private BlockPattern getOrCreateTaintedSnowGolemBase() {
        if (this.taintedSnowGolemBase == null) {
            this.taintedSnowGolemBase = BlockPatternBuilder.m_61243_().m_61247_(new String[]{" ", "#", "#"}).m_61244_('#', BlockInWorld.m_61169_((Predicate)BlockStatePredicate.m_61287_((Block)Blocks.f_50127_))).m_61249_();
        }
        return this.taintedSnowGolemBase;
    }

    private BlockPattern getOrCreateTaintedSnowGolemFull() {
        if (this.taintedSnowGolemFull == null) {
            this.taintedSnowGolemFull = BlockPatternBuilder.m_61243_().m_61247_(new String[]{"^", "#", "#"}).m_61244_('^', BlockInWorld.m_61169_(PUMPKINS_PREDICATE)).m_61244_('#', BlockInWorld.m_61169_((Predicate)BlockStatePredicate.m_61287_((Block)Blocks.f_50127_))).m_61249_();
        }
        return this.taintedSnowGolemFull;
    }

    private BlockPattern getOrCreateTaintedIronGolemBase() {
        if (this.taintedIronGolemBase == null) {
            this.taintedIronGolemBase = BlockPatternBuilder.m_61243_().m_61247_(new String[]{"~ ~", "###", "~#~"}).m_61244_('#', BlockInWorld.m_61169_((Predicate)BlockStatePredicate.m_61287_((Block)Blocks.f_50075_))).m_61244_('~', BlockInWorld.m_61169_(BlockBehaviour.BlockStateBase::m_60795_)).m_61249_();
        }
        return this.taintedIronGolemBase;
    }

    private BlockPattern getOrCreateTaintedIronGolemFull() {
        if (this.taintedIronGolemFull == null) {
            this.taintedIronGolemFull = BlockPatternBuilder.m_61243_().m_61247_(new String[]{"~^~", "###", "~#~"}).m_61244_('^', BlockInWorld.m_61169_(PUMPKINS_PREDICATE)).m_61244_('#', BlockInWorld.m_61169_((Predicate)BlockStatePredicate.m_61287_((Block)Blocks.f_50075_))).m_61244_('~', BlockInWorld.m_61169_(BlockBehaviour.BlockStateBase::m_60795_)).m_61249_();
        }
        return this.taintedIronGolemFull;
    }
}

