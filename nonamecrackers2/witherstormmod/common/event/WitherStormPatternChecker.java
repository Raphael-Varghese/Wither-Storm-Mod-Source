/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockBehaviour$BlockStateBase
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.pattern.BlockInWorld
 *  net.minecraft.world.level.block.state.pattern.BlockPattern
 *  net.minecraft.world.level.block.state.pattern.BlockPattern$BlockPatternMatch
 *  net.minecraft.world.level.block.state.pattern.BlockPatternBuilder
 *  net.minecraft.world.level.block.state.predicate.BlockStatePredicate
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.level.BlockEvent$EntityPlaceEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.common.event;

import java.util.function.Predicate;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.AdditionalHead;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;

public class WitherStormPatternChecker {
    private static BlockPattern WITHER_STORM_PATTERN;

    @SubscribeEvent
    public static void checkForWitherStormPattern(BlockEvent.EntityPlaceEvent event) {
        if (event.getPlacedBlock().m_60713_(Blocks.f_50312_) || event.getPlacedBlock().m_60713_(Blocks.f_50313_)) {
            BlockPattern blockPattern;
            BlockPattern.BlockPatternMatch patternHelper;
            Level world = (Level)event.getLevel();
            if (event.getPos().m_123342_() >= world.m_141937_() && world.m_46791_() != Difficulty.PEACEFUL && (patternHelper = (blockPattern = WitherStormPatternChecker.getOrCreateWitherStorm()).m_61184_((LevelReader)world, event.getPos())) != null) {
                for (int i = 0; i < blockPattern.m_61203_(); ++i) {
                    for (int j = 0; j < blockPattern.m_61202_(); ++j) {
                        BlockInWorld cachedBlockInfo = patternHelper.m_61229_(i, j, 0);
                        world.m_7731_(cachedBlockInfo.m_61176_(), Blocks.f_50016_.m_49966_(), 2);
                        world.m_46796_(2001, cachedBlockInfo.m_61176_(), Block.m_49956_((BlockState)cachedBlockInfo.m_61168_()));
                    }
                }
                WitherStormEntity witherStorm = (WitherStormEntity)((EntityType)WitherStormModEntityTypes.WITHER_STORM.get()).m_20615_(world);
                BlockPos blockPos = patternHelper.m_61229_(1, 2, 0).m_61176_();
                Vec3 vector = new Vec3((double)blockPos.m_123341_() + 0.5, (double)blockPos.m_123342_() + 0.55, (double)blockPos.m_123343_() + 0.5);
                float rotation = patternHelper.m_61233_().m_122435_();
                witherStorm.m_7678_(vector.f_82479_, vector.f_82480_, vector.f_82481_, rotation, 0.0f);
                witherStorm.f_20883_ = rotation;
                for (AdditionalHead head : witherStorm.getHeadManager().getOtherHeads()) {
                    head.setHeadYRot(rotation);
                }
                witherStorm.makeInvulnerable();
                witherStorm.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), 4.0f, 1.0f);
                for (ServerPlayer player : world.m_45976_(ServerPlayer.class, witherStorm.m_20191_().m_82400_(50.0))) {
                    CriteriaTriggers.f_10580_.m_68256_(player, (Entity)witherStorm);
                }
                for (int k = 0; k < blockPattern.m_61203_(); ++k) {
                    for (int l = 0; l < blockPattern.m_61202_(); ++l) {
                        world.m_6289_(patternHelper.m_61229_(k, l, 0).m_61176_(), Blocks.f_50016_);
                    }
                }
                world.m_7967_((Entity)witherStorm);
            }
        }
    }

    private static BlockPattern getOrCreateWitherStorm() {
        if (WITHER_STORM_PATTERN == null) {
            WITHER_STORM_PATTERN = BlockPatternBuilder.m_61243_().m_61247_(new String[]{"^^^", "#$#", "~#~"}).m_61244_('#', block -> block.m_61168_().m_204336_(WitherStormModBlockTags.WITHER_STORM_SUMMON_BASE_BLOCKS)).m_61244_('^', BlockInWorld.m_61169_((Predicate)BlockStatePredicate.m_61287_((Block)Blocks.f_50312_).or((Predicate)BlockStatePredicate.m_61287_((Block)Blocks.f_50313_)))).m_61244_('~', BlockInWorld.m_61169_(BlockBehaviour.BlockStateBase::m_60795_)).m_61244_('$', block -> block.m_61168_().m_204336_(WitherStormModBlockTags.WITHER_STORM_SUMMON_COMMAND_BLOCKS)).m_61249_();
        }
        return WITHER_STORM_PATTERN;
    }
}

