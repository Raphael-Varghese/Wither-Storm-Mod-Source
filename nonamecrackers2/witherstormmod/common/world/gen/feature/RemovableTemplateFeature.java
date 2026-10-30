/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.WorldGenLevel
 *  net.minecraft.world.level.chunk.ChunkGenerator
 *  net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager
 */
package nonamecrackers2.witherstormmod.common.world.gen.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import nonamecrackers2.witherstormmod.common.world.gen.feature.RemovableFeature;
import nonamecrackers2.witherstormmod.common.world.gen.feature.TemplateFeature;

public interface RemovableTemplateFeature<C extends FeatureConfiguration>
extends RemovableFeature<C> {
    @Override
    default public boolean remove(WorldGenLevel reader, ChunkGenerator generator, RandomSource random, BlockPos pos, C config) {
        if (reader instanceof ServerLevel) {
            ServerLevel world = (ServerLevel)reader;
            StructureTemplateManager manager = world.m_215082_();
            TemplateFeature.TemplateFeaturePiece piece = this.setupRemovalTemplate(pos, random, manager);
            return this.placeWithTemplate(world, generator, random, pos, config, piece);
        }
        return false;
    }

    public TemplateFeature.TemplateFeaturePiece setupRemovalTemplate(BlockPos var1, RandomSource var2, StructureTemplateManager var3);

    public boolean placeWithTemplate(ServerLevel var1, ChunkGenerator var2, RandomSource var3, BlockPos var4, C var5, TemplateFeature.TemplateFeaturePiece var6);
}

