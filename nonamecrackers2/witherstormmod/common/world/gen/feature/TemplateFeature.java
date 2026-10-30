/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.WorldGenLevel
 *  net.minecraft.world.level.chunk.ChunkGenerator
 *  net.minecraft.world.level.levelgen.feature.Feature
 *  net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
 *  net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager
 */
package nonamecrackers2.witherstormmod.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public abstract class TemplateFeature<C extends FeatureConfiguration>
extends Feature<C> {
    public TemplateFeature(Codec<C> codec) {
        super(codec);
    }

    public boolean m_142674_(FeaturePlaceContext<C> context) {
        WorldGenLevel level = context.m_159774_();
        if (level instanceof ServerLevel) {
            ServerLevel world = (ServerLevel)level;
            StructureTemplateManager manager = world.m_215082_();
            TemplateFeaturePiece piece = this.setupTemplate(context.m_159777_(), context.m_225041_(), manager);
            return this.placeWithTemplate(world, context.m_159775_(), context.m_225041_(), context.m_159777_(), context.m_159778_(), piece);
        }
        return false;
    }

    protected abstract TemplateFeaturePiece setupTemplate(BlockPos var1, RandomSource var2, StructureTemplateManager var3);

    protected abstract boolean placeWithTemplate(ServerLevel var1, ChunkGenerator var2, RandomSource var3, BlockPos var4, C var5, TemplateFeaturePiece var6);

    public record TemplateFeaturePiece(StructureTemplate template, StructurePlaceSettings settings) {
    }
}

