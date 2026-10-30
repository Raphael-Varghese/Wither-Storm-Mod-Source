/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  com.mojang.serialization.codecs.RecordCodecBuilder$Instance
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.QuartPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtUtils
 *  net.minecraft.nbt.Tag
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.level.levelgen.structure.BoundingBox
 *  net.minecraft.world.level.levelgen.structure.Structure
 *  net.minecraft.world.level.levelgen.structure.Structure$GenerationContext
 *  net.minecraft.world.level.levelgen.structure.Structure$GenerationStub
 *  net.minecraft.world.level.levelgen.structure.Structure$StructureSettings
 *  net.minecraft.world.level.levelgen.structure.StructurePiece
 *  net.minecraft.world.level.levelgen.structure.StructureType
 *  net.minecraft.world.level.levelgen.structure.TemplateStructurePiece
 *  net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext
 *  net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager
 */
package nonamecrackers2.witherstormmod.common.world.gen.feature.structure;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.init.WitherStormModStructures;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBiomeTags;

public class StormSpawnPlatformStructure
extends Structure {
    public static final Codec<StormSpawnPlatformStructure> CODEC = RecordCodecBuilder.create(instance -> instance.group((App)StormSpawnPlatformStructure.m_226567_((RecordCodecBuilder.Instance)instance), (App)BlockPos.f_121852_.fieldOf("position").forGetter(structure -> structure.spawnPos)).apply((Applicative)instance, StormSpawnPlatformStructure::new));
    private static final ResourceLocation DESERT = new ResourceLocation("witherstormmod", "desert_storm_spawn_platform");
    private static final ResourceLocation JUNGLE = new ResourceLocation("witherstormmod", "jungle_storm_spawn_platform");
    private static final ResourceLocation SAVANNA = new ResourceLocation("witherstormmod", "savanna_storm_spawn_platform");
    private static final ResourceLocation TAIGA = new ResourceLocation("witherstormmod", "taiga_storm_spawn_platform");
    private static final ResourceLocation SNOWY = new ResourceLocation("witherstormmod", "snowy_storm_spawn_platform");
    private static final ResourceLocation RUINS = new ResourceLocation("witherstormmod", "ruins_storm_spawn_platform");
    private static final ResourceLocation ORDER_TEMPLE = new ResourceLocation("witherstormmod", "order_temple_storm_spawn_platform");
    private static final ResourceLocation FOREST = new ResourceLocation("witherstormmod", "forest_storm_spawn_platform");
    private static final ResourceLocation AUTO_SPAWN = WitherStormMod.id("auto_spawn_platform");
    private BlockPos spawnPos;

    public StormSpawnPlatformStructure(Structure.StructureSettings settings, BlockPos pos) {
        super(settings);
        this.spawnPos = pos;
    }

    private boolean isInChunk(ChunkPos pos) {
        return this.spawnPos.m_123341_() >= pos.m_45604_() && this.spawnPos.m_123341_() <= pos.m_45608_() && this.spawnPos.m_123343_() >= pos.m_45605_() && this.spawnPos.m_123343_() <= pos.m_45609_();
    }

    private static boolean isFeatureChunk(Structure.GenerationContext context, StormSpawnPlatformStructure structure) {
        return structure.isInChunk(context.f_226628_());
    }

    public Optional<Structure.GenerationStub> m_214086_(Structure.GenerationContext context) {
        if (StormSpawnPlatformStructure.isFeatureChunk(context, this)) {
            Rotation rotation = Rotation.m_221990_((RandomSource)context.f_226626_());
            ChunkPos chunk = context.f_226628_();
            BlockPos prePos = new BlockPos(chunk.m_45604_(), 0, chunk.m_45605_());
            BlockPos pos = prePos.m_6630_(context.f_226622_().m_214096_(prePos.m_123341_(), prePos.m_123343_(), Heightmap.Types.WORLD_SURFACE_WG, context.f_226629_(), context.f_226624_()));
            Holder biome = context.f_226623_().m_203407_(QuartPos.m_175400_((int)pos.m_123341_()), QuartPos.m_175400_((int)pos.m_123342_()), QuartPos.m_175400_((int)pos.m_123343_()), context.f_226624_().m_224579_());
            ResourceLocation structure = StormSpawnPlatformStructure.getStructureForBiome((Holder<Biome>)biome, (RandomSource)context.f_226626_());
            return Optional.of(new Structure.GenerationStub(pos, builder -> builder.m_142679_((StructurePiece)new Piece(context.f_226625_(), structure, pos, rotation, 0))));
        }
        return Optional.empty();
    }

    private static ResourceLocation getStructureForBiome(Holder<Biome> biome, RandomSource random) {
        if (((Boolean)WitherStormModConfig.COMMON.autoSpawnWitherStorm.get()).booleanValue()) {
            return AUTO_SPAWN;
        }
        if (biome.m_203656_(WitherStormModBiomeTags.HAS_RUINS_STORM_SPAWN_PLATFORM) && random.m_188501_() <= 0.2f) {
            return RUINS;
        }
        if (biome.m_203656_(WitherStormModBiomeTags.HAS_ORDER_TEMPLE_STORM_SPAWN_PLATFORM) && random.m_188501_() <= 0.25f) {
            return ORDER_TEMPLE;
        }
        if (biome.m_203656_(WitherStormModBiomeTags.HAS_FOREST_STORM_SPAWN_PLATFORM) && random.m_188501_() <= 0.2f) {
            return FOREST;
        }
        if (biome.m_203656_(WitherStormModBiomeTags.HAS_DESERT_STORM_SPAWN_PLATFORM)) {
            return DESERT;
        }
        if (biome.m_203656_(WitherStormModBiomeTags.HAS_JUNGLE_STORM_SPAWN_PLATFORM)) {
            return JUNGLE;
        }
        if (biome.m_203656_(WitherStormModBiomeTags.HAS_SAVANNA_STORM_SPAWN_PLATFORM)) {
            return SAVANNA;
        }
        if (biome.m_203656_(WitherStormModBiomeTags.HAS_SNOWY_STORM_SPAWN_PLATFORM)) {
            return SNOWY;
        }
        if (biome.m_203656_(WitherStormModBiomeTags.HAS_TAIGA_STORM_SPAWN_PLATFORM)) {
            return TAIGA;
        }
        return WitherStormModStructures.STORM_SPAWN_PLATFORM.getId();
    }

    public StructureType<?> m_213658_() {
        return (StructureType)WitherStormModStructures.STORM_SPAWN_PLATFORM.get();
    }

    public static class Piece
    extends TemplateStructurePiece {
        @Nullable
        private BlockPos spawnPos;

        public Piece(StructureTemplateManager manager, ResourceLocation location, BlockPos pos, Rotation rotation, int offsetY) {
            super(WitherStormModStructures.PLATFORM, 0, manager, location, location.toString(), Piece.makeSettings(rotation, pos), pos);
        }

        public Piece(StructureTemplateManager manager, CompoundTag nbt) {
            super(WitherStormModStructures.PLATFORM, nbt, manager, id -> {
                StructureTemplate template = manager.m_230359_(id);
                return Piece.makeSettings(Rotation.valueOf((String)nbt.m_128461_("Rot")), new BlockPos(template.m_163801_().m_123341_() / 2, 0, template.m_163801_().m_123343_() / 2));
            });
            if (nbt.m_128425_("SpawnPos", 10)) {
                this.spawnPos = NbtUtils.m_129239_((CompoundTag)nbt.m_128469_("SpawnPos"));
            }
        }

        protected void m_183620_(StructurePieceSerializationContext context, CompoundTag tag) {
            super.m_183620_(context, tag);
            tag.m_128359_("Rot", this.f_73657_.m_74404_().name());
            if (this.spawnPos != null) {
                tag.m_128365_("SpawnPos", (Tag)NbtUtils.m_129224_((BlockPos)this.spawnPos));
            }
        }

        private static StructurePlaceSettings makeSettings(Rotation rotation, BlockPos pos) {
            return new StructurePlaceSettings().m_74379_(rotation).m_74377_(Mirror.NONE).m_74383_((StructureProcessor)BlockIgnoreProcessor.f_74046_);
        }

        protected void m_213704_(String metadata, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
            if (metadata.equals("spawn_position")) {
                this.spawnPos = pos;
                level.m_7731_(pos, Blocks.f_50016_.m_49966_(), 3);
            }
        }

        @Nullable
        public BlockPos getSpawnPos() {
            return this.spawnPos;
        }
    }
}

