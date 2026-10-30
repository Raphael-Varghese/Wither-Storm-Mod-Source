/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
 */
package nonamecrackers2.witherstormmod.common.world.gen.feature.structure;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class SpecificPosConfig
implements FeatureConfiguration {
    public static final Codec<SpecificPosConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group((App)BlockPos.f_121852_.fieldOf("position").forGetter(config -> config.pos)).apply((Applicative)instance, SpecificPosConfig::new));
    private final BlockPos pos;

    public SpecificPosConfig(BlockPos pos) {
        this.pos = pos;
    }

    public boolean isInChunk(ChunkPos pos) {
        return this.pos.m_123341_() >= pos.m_45604_() && this.pos.m_123341_() <= pos.m_45608_() && this.pos.m_123343_() >= pos.m_45605_() && this.pos.m_123343_() <= pos.m_45609_();
    }

    public BlockPos getPosition() {
        return this.pos;
    }
}

