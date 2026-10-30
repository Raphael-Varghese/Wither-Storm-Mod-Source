/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.WorldGenLevel
 *  net.minecraft.world.level.chunk.ChunkGenerator
 *  net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
 */
package nonamecrackers2.witherstormmod.common.world.gen.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public interface RemovableFeature<C extends FeatureConfiguration> {
    public boolean remove(WorldGenLevel var1, ChunkGenerator var2, RandomSource var3, BlockPos var4, C var5);
}

