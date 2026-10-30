/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  com.mojang.serialization.codecs.RecordCodecBuilder$Instance
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.Registry
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.LevelHeightAccessor
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.chunk.ChunkGenerator
 *  net.minecraft.world.level.levelgen.WorldGenerationContext
 *  net.minecraft.world.level.levelgen.WorldgenRandom
 *  net.minecraft.world.level.levelgen.heightproviders.HeightProvider
 *  net.minecraft.world.level.levelgen.structure.BoundingBox
 *  net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece
 *  net.minecraft.world.level.levelgen.structure.Structure
 *  net.minecraft.world.level.levelgen.structure.Structure$GenerationContext
 *  net.minecraft.world.level.levelgen.structure.Structure$GenerationStub
 *  net.minecraft.world.level.levelgen.structure.Structure$StructureSettings
 *  net.minecraft.world.level.levelgen.structure.StructurePiece
 *  net.minecraft.world.level.levelgen.structure.StructureType
 *  net.minecraft.world.level.levelgen.structure.pools.EmptyPoolElement
 *  net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement
 *  net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.shapes.BooleanOp
 *  net.minecraft.world.phys.shapes.Shapes
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package nonamecrackers2.witherstormmod.common.world.gen.feature.structure;

import com.google.common.collect.Lists;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.EmptyPoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import nonamecrackers2.witherstormmod.common.init.WitherStormModStructures;
import nonamecrackers2.witherstormmod.mixin.IMixinJigsawPlacement;

public class BowelsStructure
extends Structure {
    public static final Codec<BowelsStructure> CODEC = RecordCodecBuilder.create(builder -> builder.group((App)BowelsStructure.m_226567_((RecordCodecBuilder.Instance)builder), (App)StructureTemplatePool.f_210555_.fieldOf("start_pool").forGetter(structure -> structure.startPool), (App)HeightProvider.f_161970_.fieldOf("start_height").forGetter(structure -> structure.startHeight)).apply((Applicative)builder, BowelsStructure::new));
    private final Holder<StructureTemplatePool> startPool;
    private final HeightProvider startHeight;

    public BowelsStructure(Structure.StructureSettings settings, Holder<StructureTemplatePool> startPool, HeightProvider heightProvider) {
        super(settings);
        this.startPool = startPool;
        this.startHeight = heightProvider;
    }

    public Optional<Structure.GenerationStub> m_214086_(Structure.GenerationContext context) {
        RegistryAccess access = context.f_226621_();
        ChunkGenerator generator = context.f_226622_();
        StructureTemplateManager manager = context.f_226625_();
        LevelHeightAccessor heightAccessor = context.f_226629_();
        WorldgenRandom random = context.f_226626_();
        Registry registry = access.m_175515_(Registries.f_256948_);
        Rotation rotation = Rotation.NONE;
        StructureTemplatePool pool = (StructureTemplatePool)this.startPool.m_203334_();
        StructurePoolElement start = pool.m_227355_((RandomSource)random);
        if (start == EmptyPoolElement.f_210175_) {
            return Optional.empty();
        }
        ChunkPos chunk = context.f_226628_();
        int i = this.startHeight.m_213859_((RandomSource)random, new WorldGenerationContext(generator, heightAccessor));
        BlockPos pos = new BlockPos(chunk.m_45604_(), i, chunk.m_45605_());
        PoolElementStructurePiece startPiece = new PoolElementStructurePiece(manager, start, pos, start.m_210540_(), rotation, start.m_214015_(manager, pos, rotation));
        BoundingBox box = startPiece.m_73547_();
        int x = pos.m_123341_() + box.m_71056_() / 2;
        int z = pos.m_123343_() + box.m_71058_() / 2;
        int y = pos.m_123342_();
        int l = box.m_162396_() + startPiece.m_72647_();
        startPiece.m_6324_(0, y - l, 0);
        return Optional.of(new Structure.GenerationStub(new BlockPos(x, y, z), builder -> {
            ArrayList pieces = Lists.newArrayList();
            pieces.add(startPiece);
            AABB maxDistBox = new AABB((double)(x - 128), (double)(y - 128), (double)(z - 128), (double)(x + 128 + 1), (double)(y + 128 + 1), (double)(z + 128 + 1));
            VoxelShape shape = Shapes.m_83113_((VoxelShape)Shapes.m_83064_((AABB)maxDistBox), (VoxelShape)Shapes.m_83064_((AABB)AABB.m_82321_((BoundingBox)box)), (BooleanOp)BooleanOp.f_82685_);
            IMixinJigsawPlacement.invokeAddPieces(context.f_226624_(), 8, false, generator, manager, heightAccessor, (RandomSource)random, (Registry<StructureTemplatePool>)registry, startPiece, pieces, shape);
            pieces.forEach(piece -> {
                piece.m_6324_(box.m_71056_() / -2, 0, box.m_71058_() / -2);
                builder.m_142679_((StructurePiece)piece);
            });
        }));
    }

    public StructureType<?> m_213658_() {
        return (StructureType)WitherStormModStructures.BOWELS.get();
    }
}

