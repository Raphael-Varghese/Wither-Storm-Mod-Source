/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap
 *  it.unimi.dsi.fastutil.objects.ObjectArraySet
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Holder
 *  net.minecraft.core.HolderSet
 *  net.minecraft.core.SectionPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.BlockParticleOption
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.server.level.FullChunkStatus
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.tags.TagKey
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.util.random.SimpleWeightedRandomList
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.SpawnPlacements$Type
 *  net.minecraft.world.entity.item.FallingBlockEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.NaturalSpawner
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.StructureManager
 *  net.minecraft.world.level.block.BellBlock
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.ButtonBlock
 *  net.minecraft.world.level.block.CaveVines
 *  net.minecraft.world.level.block.FallingBlock
 *  net.minecraft.world.level.block.LeverBlock
 *  net.minecraft.world.level.block.NoteBlock
 *  net.minecraft.world.level.block.PressurePlateBlock
 *  net.minecraft.world.level.block.RedStoneOreBlock
 *  net.minecraft.world.level.block.RedstoneLampBlock
 *  net.minecraft.world.level.block.SculkSensorBlock
 *  net.minecraft.world.level.block.TrapDoorBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BlockStateProperties
 *  net.minecraft.world.level.block.state.properties.Half
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.chunk.ChunkAccess
 *  net.minecraft.world.level.chunk.ChunkGeneratorStructureState
 *  net.minecraft.world.level.chunk.ChunkSource
 *  net.minecraft.world.level.chunk.ChunkStatus
 *  net.minecraft.world.level.chunk.LevelChunk
 *  net.minecraft.world.level.chunk.StructureAccess
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.level.levelgen.structure.Structure
 *  net.minecraft.world.level.levelgen.structure.StructureCheckResult
 *  net.minecraft.world.level.levelgen.structure.StructureStart
 *  net.minecraft.world.level.levelgen.structure.placement.ConcentricRingsStructurePlacement
 *  net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement
 *  net.minecraft.world.level.levelgen.structure.placement.StructurePlacement
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.network.PacketDistributor
 */
package nonamecrackers2.witherstormmod.common.util;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.SectionPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.FullChunkStatus;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CaveVines;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.SculkSensorBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.StructureAccess;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureCheckResult;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.placement.ConcentricRingsStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.common.capability.PlayerWitherStormData;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
import nonamecrackers2.witherstormmod.common.util.EquipmentHelper;
import nonamecrackers2.witherstormmod.mixin.MixinFallingBlock;
import nonamecrackers2.witherstormmod.mixin.MixinPointedDripstoneBlock;

public class WorldUtil {
    public static <T extends Entity> List<T> getPerformantEntitiesOfClass(ServerLevel world, Class<T> clazz, AABB box, @Nullable Predicate<? super T> predicate) {
        return world.m_6443_(clazz, box, predicate);
    }

    public static <T extends Entity> List<T> getPerformantEntitiesOfClass(ServerLevel world, Class<T> clazz, AABB box) {
        return WorldUtil.getPerformantEntitiesOfClass(world, clazz, box, EntitySelector.f_20408_);
    }

    public static List<BlockEntity> getBlockEntitiesInAABB(Level world, AABB box) {
        ArrayList<BlockEntity> blockEntities = new ArrayList<BlockEntity>();
        ChunkSource source = world.m_7726_();
        int minChunkX = Mth.m_14107_((double)(box.f_82288_ / 16.0));
        int minChunkZ = Mth.m_14107_((double)(box.f_82290_ / 16.0));
        int maxChunkX = Mth.m_14165_((double)(box.f_82291_ / 16.0));
        int maxChunkZ = Mth.m_14165_((double)(box.f_82293_ / 16.0));
        for (int x = minChunkX; x < maxChunkX; ++x) {
            for (int z = minChunkZ; z < maxChunkZ; ++z) {
                LevelChunk chunk = source.m_62227_(x, z, false);
                if (chunk == null) continue;
                for (BlockPos pos : chunk.m_5928_()) {
                    if (!box.m_82390_(Vec3.m_82539_((Vec3i)pos))) continue;
                    blockEntities.add(chunk.m_7702_(pos));
                }
            }
        }
        return blockEntities;
    }

    @Nullable
    public static <T> T getNearest(List<T> objects, Vec3 center, Function<T, Vec3> toVec) {
        double distance = -1.0;
        T toReturn = null;
        for (T t : objects) {
            Vec3 vec = toVec.apply(t);
            double d0 = vec.m_82557_(center);
            if (distance != -1.0 && !(d0 < distance)) continue;
            distance = d0;
            toReturn = t;
        }
        return toReturn;
    }

    public static List<WitherStormEntity> getAllStorms(ServerLevel level) {
        return Lists.newArrayList((Iterable)level.m_8583_()).stream().filter(e -> e instanceof WitherStormEntity).collect(Collectors.mapping(e -> (WitherStormEntity)e, Collectors.toList()));
    }

    public static boolean checkForIntersect(AABB box, Vec3 start, Vec3 end) {
        double d1;
        double d0 = Double.MAX_VALUE;
        boolean flag = false;
        Optional optional = box.m_82371_(start, end);
        if (optional.isPresent() && (d1 = start.m_82557_((Vec3)optional.get())) < d0) {
            flag = true;
            d0 = d1;
        }
        if (box.m_82390_(start)) {
            flag = true;
        }
        return flag;
    }

    public static boolean areaLoaded(Level level, BlockPos center, int radius) {
        for (int x = -radius; x <= radius; ++x) {
            for (int z = -radius; z <= radius; ++z) {
                ChunkPos chunkPos = new ChunkPos(center);
                ChunkAccess chunk = level.m_6522_(chunkPos.f_45578_ + x, chunkPos.f_45579_ + z, ChunkStatus.f_62326_, false);
                if (!(chunk instanceof LevelChunk)) {
                    return false;
                }
                FullChunkStatus type = ((LevelChunk)chunk).m_287138_();
                if (type.m_287205_(FullChunkStatus.BLOCK_TICKING)) continue;
                return false;
            }
        }
        return true;
    }

    public static boolean isLoaded(ServerLevel level, BlockPos pos) {
        ChunkAccess chunk = level.m_6522_(SectionPos.m_123171_((int)pos.m_123341_()), SectionPos.m_123171_((int)pos.m_123343_()), ChunkStatus.f_62326_, false);
        if (!(chunk instanceof LevelChunk)) {
            return false;
        }
        FullChunkStatus type = ((LevelChunk)chunk).m_287138_();
        if (!type.m_287205_(FullChunkStatus.ENTITY_TICKING)) {
            return false;
        }
        return level.m_7726_().f_8325_.m_143145_().m_183913_(ChunkPos.m_151388_((BlockPos)pos));
    }

    public static boolean isInAnOpenArea(Entity entity) {
        int radius = 5;
        Integer lowest = null;
        for (int x = -radius; x < radius; ++x) {
            for (int z = -radius; z < radius; ++z) {
                int height = entity.m_9236_().m_6924_(Heightmap.Types.WORLD_SURFACE, entity.m_146903_() + x, entity.m_146907_() + z);
                if (lowest != null && lowest <= height) continue;
                lowest = height;
            }
        }
        return entity.m_20186_() >= (double)lowest.intValue() - 10.0;
    }

    public static boolean hasLineOfSight(Entity caster, Entity target) {
        return WorldUtil.raycast(caster, target, 300.0).m_6662_() == HitResult.Type.MISS;
    }

    public static boolean canSeeOrIsNotInASmallArea(Entity entity, Entity target) {
        PlayerWitherStormData data = (PlayerWitherStormData)target.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).orElse(null);
        if (data != null) {
            return data.isInAnOpenArea() || WorldUtil.hasLineOfSight(entity, target);
        }
        return WorldUtil.isInAnOpenArea(target) || WorldUtil.hasLineOfSight(entity, target);
    }

    public static List<BlockState> getBlockStatesBetweenClosed(Level level, BlockPos min, BlockPos max) {
        ArrayList states = Lists.newArrayList();
        for (BlockPos pos : BlockPos.m_121940_((BlockPos)min, (BlockPos)max)) {
            states.add(level.m_8055_(pos));
        }
        return states;
    }

    public static int getHeightStartingAt(Level level, int height, int x, int z) {
        BlockPos pos = new BlockPos(x, height, z);
        LevelChunk chunk = level.m_46745_(pos);
        while (pos.m_123342_() > level.m_141937_() && chunk.m_8055_(pos).m_60795_()) {
            pos = pos.m_7495_();
        }
        return pos.m_123342_();
    }

    public static int getCeilingStartingAt(Level level, int height, int x, int z) {
        BlockPos pos = new BlockPos(x, height, z);
        while (pos.m_123342_() < level.m_151558_() && level.m_8055_(pos).m_60795_()) {
            pos = pos.m_7494_();
        }
        return pos.m_123342_();
    }

    public static BlockHitResult raycast(Entity caster, Entity entity, double maxDist) {
        Vec3 pos = caster.m_146892_();
        Vec3 entityPos = entity.m_146892_();
        Vec3 delta = entityPos.m_82546_(pos);
        double dist = Math.sqrt(delta.f_82479_ * delta.f_82479_ + delta.f_82480_ * delta.f_82480_ + delta.f_82481_ * delta.f_82481_);
        delta = delta.m_82490_(dist > maxDist ? maxDist / dist : 1.0);
        Vec3 end = pos.m_82549_(delta);
        return caster.m_9236_().m_45547_(new ClipContext(pos, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, caster));
    }

    @Nullable
    public static BlockPos forEachBlockSpiralOutwards(Level level, BlockPos starting, int radius, Predicate<BlockPos> consumer) {
        if (consumer.test(starting)) {
            return starting;
        }
        for (int i = 0; i <= radius; ++i) {
            int y;
            BlockPos second;
            BlockPos first;
            int z;
            for (int x = -i; x <= i; ++x) {
                for (z = -i; z <= i; ++z) {
                    first = new BlockPos(x, i, z).m_121955_((Vec3i)starting);
                    second = new BlockPos(x, -i, z).m_121955_((Vec3i)starting);
                    if (consumer.test(first)) {
                        return first;
                    }
                    if (!consumer.test(second)) continue;
                    return second;
                }
            }
            for (y = -(i - 1); y <= i - 1; ++y) {
                for (int x = -i; x <= i; ++x) {
                    first = new BlockPos(x, y, i).m_121955_((Vec3i)starting);
                    second = new BlockPos(x, y, -i).m_121955_((Vec3i)starting);
                    if (consumer.test(first)) {
                        return first;
                    }
                    if (!consumer.test(second)) continue;
                    return second;
                }
            }
            for (y = -(i - 1); y <= i - 1; ++y) {
                for (z = -(i - 1); z <= i - 1; ++z) {
                    first = new BlockPos(i, y, z).m_121955_((Vec3i)starting);
                    second = new BlockPos(-i, -y, z).m_121955_((Vec3i)starting);
                    if (consumer.test(first)) {
                        return first;
                    }
                    if (!consumer.test(second)) continue;
                    return second;
                }
            }
        }
        return null;
    }

    @Nullable
    public static Pair<BlockPos, StructureStart> findNearestMapStructure(ServerLevel level, TagKey<Structure> tag, BlockPos pos, int radius, boolean addReference) {
        if (!level.m_7654_().m_129910_().m_246337_().m_247749_()) {
            return null;
        }
        Optional optional = level.m_9598_().m_175515_(Registries.f_256944_).m_203431_(tag);
        if (optional.isEmpty()) {
            return null;
        }
        return WorldUtil.findNearestMapStructure(level, (HolderSet<Structure>)((HolderSet)optional.get()), pos, radius, addReference);
    }

    @Nullable
    private static Pair<BlockPos, StructureStart> findNearestMapStructure(ServerLevel level, HolderSet<Structure> set, BlockPos pos, int radius, boolean addReference) {
        ChunkGeneratorStructureState chunkgeneratorstructurestate = level.m_7726_().m_255415_();
        Object2ObjectArrayMap map = new Object2ObjectArrayMap();
        for (Holder holder : set) {
            for (StructurePlacement structureplacement : chunkgeneratorstructurestate.m_255260_(holder)) {
                map.computeIfAbsent(structureplacement, p -> new ObjectArraySet()).add(holder);
            }
        }
        if (map.isEmpty()) {
            return null;
        }
        Pair<BlockPos, StructureStart> pair2 = null;
        double d2 = Double.MAX_VALUE;
        StructureManager structuremanager = level.m_215010_();
        ArrayList list = new ArrayList(map.size());
        for (Map.Entry entry : map.entrySet()) {
            StructurePlacement structureplacement1 = (StructurePlacement)entry.getKey();
            if (structureplacement1 instanceof ConcentricRingsStructurePlacement) {
                BlockPos blockpos;
                double d0;
                ConcentricRingsStructurePlacement concentricringsstructureplacement = (ConcentricRingsStructurePlacement)structureplacement1;
                Pair<BlockPos, StructureStart> pair = WorldUtil.getNearestGeneratedConcentricRingStructure((Set)entry.getValue(), level, structuremanager, pos, addReference, concentricringsstructureplacement);
                if (pair == null || !((d0 = pos.m_123331_((Vec3i)(blockpos = (BlockPos)pair.getFirst()))) < d2)) continue;
                d2 = d0;
                pair2 = pair;
                continue;
            }
            if (!(structureplacement1 instanceof RandomSpreadStructurePlacement)) continue;
            list.add(entry);
        }
        if (!list.isEmpty()) {
            int i = SectionPos.m_123171_((int)pos.m_123341_());
            int j = SectionPos.m_123171_((int)pos.m_123343_());
            for (int k = 0; k <= radius; ++k) {
                boolean flag = false;
                for (Map.Entry entry1 : list) {
                    RandomSpreadStructurePlacement randomspreadstructureplacement = (RandomSpreadStructurePlacement)entry1.getKey();
                    Pair<BlockPos, StructureStart> pair1 = WorldUtil.getNearestGeneratedRandomPlacementStructure((Set)entry1.getValue(), (LevelReader)level, structuremanager, i, j, k, addReference, chunkgeneratorstructurestate.m_254887_(), randomspreadstructureplacement);
                    if (pair1 == null) continue;
                    flag = true;
                    double d1 = pos.m_123331_((Vec3i)pair1.getFirst());
                    if (!(d1 < d2)) continue;
                    d2 = d1;
                    pair2 = pair1;
                }
                if (!flag) continue;
                return pair2;
            }
        }
        return pair2;
    }

    @Nullable
    private static Pair<BlockPos, StructureStart> getNearestGeneratedConcentricRingStructure(Set<Holder<Structure>> set, ServerLevel level, StructureManager manager, BlockPos pos, boolean addReference, ConcentricRingsStructurePlacement placement) {
        List list = level.m_7726_().m_255415_().m_255182_(placement);
        if (list == null) {
            throw new IllegalStateException("Somehow tried to find structures for a placement that doesn't exist");
        }
        Pair<BlockPos, StructureStart> pair = null;
        double d0 = Double.MAX_VALUE;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        for (ChunkPos chunkpos : list) {
            Pair<BlockPos, StructureStart> pair1;
            blockpos$mutableblockpos.m_122178_(SectionPos.m_175554_((int)chunkpos.f_45578_, (int)8), 32, SectionPos.m_175554_((int)chunkpos.f_45579_, (int)8));
            double d1 = blockpos$mutableblockpos.m_123331_((Vec3i)pos);
            boolean flag = pair == null || d1 < d0;
            if (!flag || (pair1 = WorldUtil.getStructureGeneratingAt(set, (LevelReader)level, manager, addReference, (StructurePlacement)placement, chunkpos)) == null) continue;
            pair = pair1;
            d0 = d1;
        }
        return pair;
    }

    @Nullable
    private static Pair<BlockPos, StructureStart> getNearestGeneratedRandomPlacementStructure(Set<Holder<Structure>> set, LevelReader level, StructureManager manager, int xSectionCoord, int zSectionCoord, int radius, boolean addReference, long seed, RandomSpreadStructurePlacement placement) {
        int i = placement.m_205003_();
        for (int j = -radius; j <= radius; ++j) {
            boolean flag = j == -radius || j == radius;
            for (int k = -radius; k <= radius; ++k) {
                int i1;
                int l;
                ChunkPos chunkpos;
                Pair<BlockPos, StructureStart> pair;
                boolean flag1;
                boolean bl = flag1 = k == -radius || k == radius;
                if (!flag && !flag1 || (pair = WorldUtil.getStructureGeneratingAt(set, level, manager, addReference, (StructurePlacement)placement, chunkpos = placement.m_227008_(seed, l = xSectionCoord + i * j, i1 = zSectionCoord + i * k))) == null) continue;
                return pair;
            }
        }
        return null;
    }

    @Nullable
    private static Pair<BlockPos, StructureStart> getStructureGeneratingAt(Set<Holder<Structure>> set, LevelReader level, StructureManager manager, boolean addReference, StructurePlacement placement, ChunkPos pos) {
        for (Holder<Structure> holder : set) {
            ChunkAccess chunkaccess;
            StructureStart structurestart;
            StructureCheckResult structurecheckresult = manager.m_220473_(pos, (Structure)holder.m_203334_(), addReference);
            if (structurecheckresult == StructureCheckResult.START_NOT_PRESENT || (structurestart = manager.m_220512_(SectionPos.m_175562_((ChunkAccess)(chunkaccess = level.m_46819_(pos.f_45578_, pos.f_45579_, ChunkStatus.f_62315_))), (Structure)holder.m_203334_(), (StructureAccess)chunkaccess)) == null || !structurestart.m_73603_() || addReference && !WorldUtil.tryAddReference(manager, structurestart)) continue;
            return Pair.of((Object)placement.m_227039_(structurestart.m_163625_()), (Object)structurestart);
        }
        return null;
    }

    private static boolean tryAddReference(StructureManager manager, StructureStart start) {
        if (start.m_73606_()) {
            manager.m_220484_(start);
            return true;
        }
        return false;
    }

    public static boolean isBlockExposed(Level level, BlockPos pos) {
        BlockState posState = level.m_8055_(pos);
        if (posState.m_61138_((Property)BlockStateProperties.f_61362_)) {
            return true;
        }
        if (level.m_46859_(pos) || level.m_46801_(pos)) {
            return false;
        }
        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = pos.m_121945_(direction);
            if (!level.m_46859_(neighborPos) && !level.m_46801_(neighborPos)) continue;
            return true;
        }
        return false;
    }

    public static void doCaveRumble(ServerLevel level, ServerPlayer player, double rumbleIntensity, RandomSource random) {
        int x;
        int radius;
        int ceiling = WorldUtil.getCeilingStartingAt((Level)level, player.m_146904_(), player.m_146903_(), player.m_146907_());
        int chance = (int)(10.0 * (1.0 - Math.sqrt(rumbleIntensity)));
        int caveVinesBerryDropChance = (int)(4.0 * (1.0 - Math.sqrt(rumbleIntensity)));
        int caveInChance = (int)(12.0 * (1.0 - Math.sqrt(rumbleIntensity)));
        int dripStoneLengthChance = (int)(6.0 * (1.0 - Math.sqrt(rumbleIntensity)));
        WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> player), (Object)new ShakeScreenMessage(180.0f, (float)(12.0 * Math.sqrt((Double)WitherStormModConfig.SERVER.caveRumbleIntensity.get()))));
        player.m_6330_((SoundEvent)WitherStormModSoundEvents.EARTH_RUMBLE.get(), SoundSource.AMBIENT, 1.0f, 1.0f);
        if (ceiling < level.m_151558_()) {
            radius = 64;
            for (x = -radius; x < radius; ++x) {
                for (int z = -radius; z < radius; ++z) {
                    if (chance >= 1 && random.m_188503_(chance) != 0) continue;
                    int finalX = player.m_146903_() + x;
                    int finalZ = player.m_146907_() + z;
                    int y = WorldUtil.getCeilingStartingAt((Level)level, player.m_146904_() + 4, finalX, finalZ);
                    BlockPos pos = new BlockPos(finalX, y, finalZ);
                    BlockState state = level.m_8055_(pos);
                    boolean flag = true;
                    for (int i = 0; i < 20; ++i) {
                        BlockState current = level.m_8055_(pos);
                        if (current.m_60713_(Blocks.f_152588_) && (dripStoneLengthChance < 1 || random.m_188503_(dripStoneLengthChance) == 0)) {
                            if (!MixinPointedDripstoneBlock.callIsStalagmite(current)) {
                                MixinPointedDripstoneBlock.callSpawnFallingStalactite(current, level, pos);
                            }
                            flag = true;
                            break;
                        }
                        if (current.m_60734_() instanceof CaveVines && (caveVinesBerryDropChance < 1 || random.m_188503_(caveVinesBerryDropChance) == 0) && CaveVines.m_152951_((BlockState)current)) {
                            CaveVines.m_269473_(null, (BlockState)current, (Level)level, (BlockPos)pos);
                            flag = true;
                        }
                        pos = pos.m_7494_();
                    }
                    if (!flag) continue;
                    BlockState below = level.m_8055_((pos = pos.m_175288_(y)).m_7495_());
                    boolean canDropBlock = FallingBlock.m_53241_((BlockState)below) && pos.m_123342_() >= level.m_141937_();
                    Block block = state.m_60734_();
                    if (block instanceof FallingBlock) {
                        FallingBlock block2 = (FallingBlock)block;
                        if (!state.m_60713_(Blocks.f_152588_) && canDropBlock) {
                            FallingBlockEntity entity = FallingBlockEntity.m_201971_((Level)level, (BlockPos)pos, (BlockState)state);
                            ((MixinFallingBlock)block2).callFalling(entity);
                        }
                    }
                    if (!state.m_204336_(WitherStormModBlockTags.CAVE_IN_BLACKLIST) && canDropBlock && (caveInChance < 1 || random.m_188503_(caveInChance) == 0)) {
                        double randomMomentum = 0.25 * random.m_188500_();
                        int rotationDelta = random.m_188503_(257) - 128;
                        BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_((Level)level);
                        cluster.populateWithRadius(pos, 1.0f, blockstate -> !blockstate.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST));
                        cluster.setTime(50);
                        cluster.setShouldCrumble(false);
                        cluster.setRotationDelta(new Vec2((float)rotationDelta * 0.0625f, (float)rotationDelta * 0.0625f));
                        cluster.m_20242_(false);
                        cluster.m_20334_(0.0, randomMomentum, 0.0);
                        cluster.setPhysics(true);
                        level.m_7967_((Entity)cluster);
                    }
                    if (!WorldUtil.isBlockExposed((Level)level, pos)) continue;
                    level.m_8767_((ParticleOptions)new BlockParticleOption(ParticleTypes.f_123814_, state), (double)finalX + 0.5, (double)y, (double)finalZ + 0.5, 2, 0.5, 0.5, 0.5, 0.0);
                    level.m_8767_((ParticleOptions)new BlockParticleOption(ParticleTypes.f_123794_, state), (double)finalX + 0.5, (double)y, (double)finalZ + 0.5, 4, 0.5, 0.5, 0.5, 1.0);
                }
            }
        }
        radius = 16;
        for (x = -radius; x < radius; ++x) {
            for (int y = -radius; y < radius; ++y) {
                for (int z = -radius; z < radius; ++z) {
                    int randomized;
                    int finalZ;
                    int finalY;
                    if (chance >= 1 && random.m_188503_(chance) != 0) continue;
                    int finalX = player.m_146903_() + x;
                    BlockPos pos = new BlockPos(finalX, finalY = player.m_146904_() + y, finalZ = player.m_146907_() + z);
                    BlockState state = level.m_8055_(pos);
                    Block block = state.m_60734_();
                    if (block instanceof RedstoneLampBlock) {
                        if (!((Boolean)WitherStormModConfig.SERVER.caveRumblesMessWithRedstone.get()).booleanValue() || ((Boolean)state.m_61143_((Property)RedstoneLampBlock.f_55654_)).booleanValue()) continue;
                        level.m_6933_(pos, (BlockState)state.m_61124_((Property)RedstoneLampBlock.f_55654_, (Comparable)Boolean.TRUE), 2, 0);
                        level.m_186460_(pos, block, 20 + random.m_188503_(10));
                        continue;
                    }
                    if (block instanceof TrapDoorBlock) {
                        boolean isBottom;
                        boolean isOpen = (Boolean)state.m_61143_((Property)TrapDoorBlock.f_57514_);
                        boolean bl = isBottom = state.m_61143_((Property)TrapDoorBlock.f_57515_) == Half.BOTTOM;
                        if (isOpen && isBottom) {
                            level.m_46597_(pos, (BlockState)state.m_61124_((Property)TrapDoorBlock.f_57514_, (Comparable)Boolean.FALSE));
                        }
                        if (isOpen || isBottom) continue;
                        level.m_46597_(pos, (BlockState)state.m_61124_((Property)TrapDoorBlock.f_57514_, (Comparable)Boolean.TRUE));
                        continue;
                    }
                    if (block instanceof RedStoneOreBlock) {
                        randomized = random.m_188503_(70);
                        level.m_7731_(pos, (BlockState)state.m_61124_((Property)RedStoneOreBlock.f_55450_, (Comparable)Boolean.TRUE), 2);
                        level.m_186460_(pos, block, 10 + randomized);
                        continue;
                    }
                    if (block instanceof LeverBlock) {
                        if (!((Boolean)WitherStormModConfig.SERVER.caveRumblesMessWithRedstone.get()).booleanValue()) continue;
                        level.m_46597_(pos, (BlockState)state.m_61122_((Property)LeverBlock.f_54622_));
                        continue;
                    }
                    if (block instanceof ButtonBlock) {
                        ButtonBlock buttonBlock = (ButtonBlock)block;
                        if (!((Boolean)WitherStormModConfig.SERVER.caveRumblesMessWithRedstone.get()).booleanValue()) continue;
                        buttonBlock.m_51116_(state, (Level)level, pos);
                        continue;
                    }
                    if (block instanceof PressurePlateBlock) {
                        if (!((Boolean)WitherStormModConfig.SERVER.caveRumblesMessWithRedstone.get()).booleanValue()) continue;
                        randomized = random.m_188503_(60);
                        level.m_7731_(pos, (BlockState)state.m_61124_((Property)PressurePlateBlock.f_55249_, (Comparable)Boolean.TRUE), 2);
                        level.m_186460_(pos, block, randomized);
                        continue;
                    }
                    if (block instanceof SculkSensorBlock) {
                        SculkSensorBlock sculkSensor = (SculkSensorBlock)block;
                        sculkSensor.m_141947_((Level)level, pos, state, (Entity)player);
                        continue;
                    }
                    if (block instanceof NoteBlock) {
                        if (!((Boolean)WitherStormModConfig.SERVER.caveRumblesMessWithRedstone.get()).booleanValue()) continue;
                        level.m_46597_(pos, (BlockState)state.m_61122_((Property)NoteBlock.f_55013_));
                        continue;
                    }
                    if (!(block instanceof BellBlock)) continue;
                    BellBlock bellBlock = (BellBlock)block;
                    bellBlock.m_49712_((Level)level, pos, null);
                }
            }
        }
    }

    public static List<Integer> getStormIds(WitherStormEntity entity) {
        return WorldUtil.getAllStorms((ServerLevel)entity.m_9236_()).stream().collect(Collectors.mapping(Entity::m_19879_, Collectors.toList()));
    }

    @Nullable
    public static Mob summonRandomMob(ServerLevel world, BlockPos start, RandomSource random, int diameter, SimpleWeightedRandomList<EntityType<? extends Mob>> types, boolean advancedMobs) {
        EntityType type = (EntityType)types.m_216820_(random).get();
        BlockPos pos = WorldUtil.getRandomNearbyPos((Level)world, start, random, type, diameter, 5);
        if (pos != null && WorldUtil.hasEnoughSpace((Level)world, type.m_20680_(), pos)) {
            Mob entity = (Mob)type.m_20615_((Level)world);
            DifficultyInstance difficulty = world.m_6436_(entity.m_20183_());
            ForgeEventFactory.onFinalizeSpawn((Mob)entity, (ServerLevelAccessor)world, (DifficultyInstance)difficulty, (MobSpawnType)MobSpawnType.TRIGGERED, null, null);
            if (WitherSickened.CAN_WEAR_ARMOR.test(entity) && entity instanceof Monster) {
                Monster monster = (Monster)entity;
                EquipmentHelper.applyEquipment(monster, difficulty, advancedMobs);
            }
            entity.m_6034_((double)pos.m_123341_() + 0.5, (double)(pos.m_123342_() + 1), (double)pos.m_123343_() + 0.5);
            entity.m_8032_();
            entity.m_21373_();
            entity.m_21530_();
            world.m_8767_((ParticleOptions)((SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get()), entity.m_20185_(), entity.m_20188_(), entity.m_20189_(), 20, random.m_188583_(), random.m_188583_(), random.m_188583_(), 0.2);
            world.m_8767_((ParticleOptions)ParticleTypes.f_123755_, entity.m_20185_(), entity.m_20188_(), entity.m_20189_(), 20, random.m_188583_(), random.m_188583_(), random.m_188583_(), 0.01);
            world.m_47205_((Entity)entity);
            return entity;
        }
        return null;
    }

    @Nullable
    public static BlockPos getRandomNearbyPos(Level level, BlockPos start, RandomSource random, EntityType<?> type, int diameter, int attempts) {
        for (int i = 0; i < attempts; ++i) {
            int z;
            int y;
            int x = start.m_123341_() + random.m_188503_(diameter) - diameter / 2;
            BlockPos pos = new BlockPos(x, y = level.m_5452_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, new BlockPos(x, 0, z = start.m_123343_() + random.m_188503_(diameter) - diameter / 2)).m_123342_(), z);
            if (!NaturalSpawner.m_47051_((SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (LevelReader)level, (BlockPos)pos, type) || !(pos.m_123331_((Vec3i)start) > 6.0)) continue;
            return pos;
        }
        return null;
    }

    private static boolean hasEnoughSpace(Level level, EntityDimensions dimensions, BlockPos spawnPos) {
        for (BlockPos pos : BlockPos.m_121940_((BlockPos)spawnPos, (BlockPos)spawnPos.m_121955_((Vec3i)BlockPos.m_274561_((double)dimensions.f_20377_, (double)dimensions.f_20378_, (double)dimensions.f_20377_)))) {
            if (level.m_8055_(pos).m_60812_((BlockGetter)level, pos).m_83281_()) continue;
            return false;
        }
        return true;
    }
}

