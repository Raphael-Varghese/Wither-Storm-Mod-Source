/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Explosion
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.TntBlock
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.PushReaction
 */
package nonamecrackers2.witherstormmod.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import nonamecrackers2.witherstormmod.common.entity.SuperTNTEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;

public class SuperTNTBlock
extends TntBlock {
    public SuperTNTBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public void m_7592_(Level world, BlockPos pos, Explosion explosion) {
        if (!world.f_46443_) {
            SuperTNTEntity entity = new SuperTNTEntity(world, (double)pos.m_123341_() + 0.5, pos.m_123342_(), (double)pos.m_123343_() + 0.5, explosion.m_252906_());
            entity.m_32085_(world.f_46441_.m_188503_(entity.m_32100_() / 4) + entity.m_32100_() / 8);
            world.m_7967_((Entity)entity);
        }
    }

    public void onCaughtFire(BlockState state, Level world, BlockPos pos, Direction face, LivingEntity igniter) {
        if (!world.f_46443_) {
            SuperTNTEntity entity = new SuperTNTEntity(world, (double)pos.m_123341_() + 0.5, pos.m_123342_(), (double)pos.m_123343_() + 0.5, igniter);
            world.m_7967_((Entity)entity);
            world.m_6263_(null, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), (SoundEvent)WitherStormModSoundEvents.SUPER_TNT_PRIMED.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
        }
    }

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.BLOCK;
    }
}

