/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.WallTorchBlock
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 */
package nonamecrackers2.witherstormmod.common.block;

import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class TaintedWallTorchBlock
extends WallTorchBlock {
    protected final Supplier<? extends ParticleOptions> flameParticle;

    public TaintedWallTorchBlock(BlockBehaviour.Properties properties, Supplier<? extends ParticleOptions> flameParticle) {
        super(properties, null);
        this.flameParticle = flameParticle;
    }

    public void m_214162_(BlockState state, Level level, BlockPos pos, RandomSource random) {
        Direction direction = (Direction)state.m_61143_((Property)f_58119_);
        double x = (double)pos.m_123341_() + 0.5 + random.m_188583_() * 0.1;
        double y = (double)pos.m_123342_() + 0.7 + random.m_188583_() * 0.1;
        double z = (double)pos.m_123343_() + 0.5 + random.m_188583_() * 0.1;
        Direction opposite = direction.m_122424_();
        level.m_7106_((ParticleOptions)ParticleTypes.f_123762_, x + 0.27 * (double)opposite.m_122429_(), y + 0.22, z + 0.27 * (double)opposite.m_122431_(), 0.0, 0.0, 0.0);
        level.m_7106_(this.flameParticle.get(), x + 0.27 * (double)opposite.m_122429_(), y + 0.22, z + 0.27 * (double)opposite.m_122431_(), 0.0, 0.01, 0.0);
    }
}

