/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.chunk.ChunkGenerator
 *  net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
 *  net.minecraft.world.level.levelgen.structure.BoundingBox
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager
 */
package nonamecrackers2.witherstormmod.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import nonamecrackers2.witherstormmod.common.world.gen.feature.TemplateFeature;
import nonamecrackers2.witherstormmod.common.world.gen.feature.template.BlockIgnoreIrreplacableProcessor;

public class BowelsPodiumFeature
extends TemplateFeature<NoneFeatureConfiguration> {
    private final ResourceLocation templateId;

    public BowelsPodiumFeature(Codec<NoneFeatureConfiguration> codec, ResourceLocation templateId) {
        super(codec);
        this.templateId = templateId;
    }

    @Override
    protected TemplateFeature.TemplateFeaturePiece setupTemplate(BlockPos pos, RandomSource random, StructureTemplateManager manager) {
        return new TemplateFeature.TemplateFeaturePiece(manager.m_230359_(this.templateId), new StructurePlaceSettings().m_74379_(Rotation.m_221990_((RandomSource)RandomSource.m_216335_((long)pos.m_121878_()))).m_74377_(Mirror.NONE).m_74385_(BlockPos.f_121853_).m_74383_((StructureProcessor)BlockIgnoreIrreplacableProcessor.AIR));
    }

    @Override
    public boolean placeWithTemplate(ServerLevel world, ChunkGenerator generator, RandomSource random, BlockPos pos, NoneFeatureConfiguration config, TemplateFeature.TemplateFeaturePiece piece) {
        StructureTemplate template = piece.template();
        BoundingBox box = template.m_74633_(piece.settings(), pos);
        BlockPos center = box.m_162394_();
        int xOffset = pos.m_123341_() - center.m_123341_();
        int zOffset = pos.m_123343_() - center.m_123343_();
        BlockPos newPos = pos.m_7918_(xOffset, -box.m_71057_(), zOffset);
        template.m_230328_((ServerLevelAccessor)world, newPos, newPos, piece.settings(), random, 2);
        return true;
    }
}

