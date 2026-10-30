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
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import nonamecrackers2.witherstormmod.common.world.gen.feature.RemovableTemplateFeature;
import nonamecrackers2.witherstormmod.common.world.gen.feature.TemplateFeature;
import nonamecrackers2.witherstormmod.common.world.gen.feature.template.BlockIgnoreIrreplacableProcessor;

public class CommandBlockPodiumFeature
extends TemplateFeature<NoneFeatureConfiguration>
implements RemovableTemplateFeature<NoneFeatureConfiguration> {
    private final ResourceLocation templateId;

    public CommandBlockPodiumFeature(Codec<NoneFeatureConfiguration> codec, ResourceLocation templateId) {
        super(codec);
        this.templateId = templateId;
    }

    @Override
    protected TemplateFeature.TemplateFeaturePiece setupTemplate(BlockPos pos, RandomSource random, StructureTemplateManager manager) {
        StructureTemplate template = manager.m_230359_(this.templateId);
        RandomSource newRandom = RandomSource.m_216335_((long)pos.m_121878_());
        StructurePlaceSettings settings = new StructurePlaceSettings().m_74379_(Rotation.m_221990_((RandomSource)newRandom)).m_74377_(Mirror.NONE).m_74385_(BlockPos.f_121853_).m_74383_((StructureProcessor)BlockIgnoreIrreplacableProcessor.AIR);
        return new TemplateFeature.TemplateFeaturePiece(template, settings);
    }

    @Override
    public TemplateFeature.TemplateFeaturePiece setupRemovalTemplate(BlockPos pos, RandomSource random, StructureTemplateManager manager) {
        StructureTemplate template = manager.m_230359_(this.templateId);
        RandomSource newRandom = RandomSource.m_216335_((long)pos.m_121878_());
        StructurePlaceSettings settings = new StructurePlaceSettings().m_74379_(Rotation.m_221990_((RandomSource)newRandom)).m_74377_(Mirror.NONE).m_74385_(BlockPos.f_121853_).m_74383_((StructureProcessor)BlockIgnoreIrreplacableProcessor.AIR_REMOVE);
        return new TemplateFeature.TemplateFeaturePiece(template, settings);
    }

    @Override
    public boolean placeWithTemplate(ServerLevel world, ChunkGenerator generator, RandomSource random, BlockPos pos, NoneFeatureConfiguration config, TemplateFeature.TemplateFeaturePiece piece) {
        StructureTemplate template = piece.template();
        BlockPos center = template.m_74633_(piece.settings(), pos).m_162394_();
        int xOffset = pos.m_123341_() - center.m_123341_();
        int yOffset = pos.m_123342_() - center.m_123342_();
        int zOffset = pos.m_123343_() - center.m_123343_();
        BlockPos newPos = pos.m_7918_(xOffset, yOffset, zOffset);
        template.m_230328_((ServerLevelAccessor)world, newPos, newPos, piece.settings(), random, 2);
        return true;
    }
}

