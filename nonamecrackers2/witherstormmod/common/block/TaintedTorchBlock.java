/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.TorchBlock
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 */
package nonamecrackers2.witherstormmod.common.block;

import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class TaintedTorchBlock
extends TorchBlock {
    protected final Supplier<? extends ParticleOptions> flameParticle;

    public TaintedTorchBlock(BlockBehaviour.Properties properties, Supplier<? extends ParticleOptions> flameParticle) {
        super(properties, null);
        this.flameParticle = flameParticle;
    }

    public void m_214162_(BlockState state, Level level, BlockPos pos, RandomSource random) {
        double x = (double)pos.m_123341_() + 0.5 + random.m_188583_() * 0.1;
        double y = (double)pos.m_123342_() + 0.7 + random.m_188583_() * 0.1;
        double z = (double)pos.m_123343_() + 0.5 + random.m_188583_() * 0.1;
        level.m_7106_((ParticleOptions)ParticleTypes.f_123762_, x, y, z, 0.0, 0.0, 0.0);
        level.m_7106_(this.flameParticle.get(), x, y, z, 0.0, 0.01, 0.0);
    }
}

