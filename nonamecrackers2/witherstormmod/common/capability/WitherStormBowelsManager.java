/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.Holder
 *  net.minecraft.core.SectionPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.NbtUtils
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.level.TicketType
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.SpawnPlacements$Type
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.NaturalSpawner
 *  net.minecraft.world.level.StructureManager
 *  net.minecraft.world.level.WorldGenLevel
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.chunk.ChunkAccess
 *  net.minecraft.world.level.chunk.ChunkStatus
 *  net.minecraft.world.level.chunk.StructureAccess
 *  net.minecraft.world.level.levelgen.feature.ConfiguredFeature
 *  net.minecraft.world.level.levelgen.structure.Structure
 *  net.minecraft.world.level.levelgen.structure.StructureCheckResult
 *  net.minecraft.world.level.levelgen.structure.StructurePiece
 *  net.minecraft.world.level.levelgen.structure.StructureStart
 *  net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement
 *  net.minecraft.world.level.levelgen.structure.placement.StructurePlacement
 *  net.minecraft.world.phys.AABB
 *  net.minecraftforge.common.util.ITeleporter
 *  net.minecraftforge.event.TickEvent$LevelTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.event.level.LevelEvent$Load
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package nonamecrackers2.witherstormmod.common.capability;

import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.SectionPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.StructureAccess;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureCheckResult;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModFeatures;
import nonamecrackers2.witherstormmod.common.init.WitherStormModStructures;
import nonamecrackers2.witherstormmod.common.util.BowelsTeleporter;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WitherStormBowelsManager {
    private static final Logger LOGGER = LogManager.getLogger((String)"witherstormmod/WitherStormBowelsManager");
    private static final int ENTRANCE_SEARCH_RADIUS = 96;
    private static final int ENTRANCE_SEARCH_RADIUS_MIN = 35;
    private static BlockPos NORTH_HEAD_POS = new BlockPos(-2, 128, 27);
    private static BlockPos SOUTH_HEAD_POS = new BlockPos(-3, 128, -23);
    private final Map<UUID, Entity> queuedEnter = new HashMap<UUID, Entity>();
    private final Map<Entity, Runnable> queuedLeave = new HashMap<Entity, Runnable>();
    private final List<BowelsInstance> instances = new ArrayList<BowelsInstance>();
    private final ServerLevel world;
    private final Random random = new Random();
    @Nullable
    private Pair<BlockPos, StructureStart> nextAvailableStructure;

    public WitherStormBowelsManager(ServerLevel world) {
        this.world = world;
    }

    public WitherStormBowelsManager() {
        this.world = null;
    }

    public void read(CompoundTag compound) {
        ListTag list = compound.m_128437_("Instances", 10);
        for (int i = 0; i < list.size(); ++i) {
            CompoundTag instanceCompound = list.m_128728_(i);
            this.instances.add(BowelsInstance.read(instanceCompound));
        }
    }

    public CompoundTag write() {
        CompoundTag compound = new CompoundTag();
        ListTag list = new ListTag();
        for (BowelsInstance instance : this.instances) {
            list.add((Object)instance.write());
        }
        compound.m_128365_("Instances", (Tag)list);
        return compound;
    }

    @Nullable
    public BowelsInstance getOrCreateInstanceFor(WitherStormEntity storm) {
        BowelsInstance instance = this.get(storm.m_20148_());
        if (instance == null) {
            Pair<BlockPos, StructureStart> start;
            LOGGER.debug("Searching for available bowels arena for entity {}", (Object)storm);
            if (this.nextAvailableStructure != null) {
                LOGGER.debug("Using pre-located structure...");
                start = this.nextAvailableStructure;
                this.nextAvailableStructure = null;
            } else {
                start = this.getAvailableStructure();
            }
            if (start != null) {
                instance = new BowelsInstance(start, storm.m_20148_());
            } else {
                LOGGER.error("Could not find an available bowels structure for {}. This shouldn't happen!", (Object)storm);
            }
        }
        return instance;
    }

    @Nullable
    public Pair<BlockPos, StructureStart> getAvailableStructure() {
        if (!this.world.m_7654_().m_129910_().m_246337_().m_247749_()) {
            this.world.m_7654_().m_6846_().m_11314_().forEach(player -> player.m_213846_((Component)Component.m_237115_((String)"chat.witherstormmod.bowels.structuresDisabled").m_130940_(ChatFormatting.RED)));
            LOGGER.warn("Structures are disabled, meaning the bowels cannot be accessed. Please enable structures to have access to the final boss battle.");
            return null;
        }
        Holder<Structure> holder = WitherStormBowelsManager.getBowels(this.world);
        StructureManager manager = this.world.m_215010_();
        BlockPos startPos = BlockPos.f_121853_;
        if (!this.instances.isEmpty()) {
            startPos = this.instances.get(this.instances.size() - 1).getPos();
        }
        RandomSpreadStructurePlacement placement = null;
        for (StructurePlacement structurePlacement : this.world.m_7726_().m_255415_().m_255260_(holder)) {
            RandomSpreadStructurePlacement p;
            if (!(structurePlacement instanceof RandomSpreadStructurePlacement)) continue;
            placement = p = (RandomSpreadStructurePlacement)structurePlacement;
            break;
        }
        if (placement == null) {
            LOGGER.error("The Bowels structure must be the random spread placement type!");
            return null;
        }
        int sectionX = SectionPos.m_123171_((int)startPos.m_123341_());
        int sectionZ = SectionPos.m_123171_((int)startPos.m_123343_());
        for (int area = 0; area <= 100; ++area) {
            int i = placement.m_205003_();
            for (int x = -area; x <= area; ++x) {
                boolean xFlag = x == -area || x == area;
                for (int z = -area; z <= area; ++z) {
                    ChunkAccess chunk;
                    StructureStart start;
                    boolean zFlag;
                    boolean bl = zFlag = z == -area || z == area;
                    if (!xFlag && !zFlag) continue;
                    int finalX = sectionX + i * x;
                    int finalZ = sectionZ + i * z;
                    ChunkPos potential = placement.m_227008_(this.world.m_7328_(), finalX, finalZ);
                    StructureCheckResult result = manager.m_220473_(potential, (Structure)holder.m_203334_(), false);
                    if (result == StructureCheckResult.START_NOT_PRESENT || (start = manager.m_220512_(SectionPos.m_175562_((ChunkAccess)(chunk = this.world.m_46819_(potential.f_45578_, potential.f_45579_, ChunkStatus.f_62315_))), (Structure)holder.m_203334_(), (StructureAccess)chunk)) == null || !start.m_73603_()) continue;
                    BlockPos locatePos = placement.m_227039_(start.m_163625_());
                    if (!this.instances.stream().filter(instance -> instance.getPos().equals((Object)locatePos)).collect(Collectors.toList()).isEmpty()) continue;
                    return Pair.of((Object)locatePos, (Object)start);
                }
            }
        }
        return null;
    }

    @Nullable
    public BowelsInstance get(UUID uuid) {
        for (BowelsInstance instance : this.instances) {
            if (!instance.witherStorm.equals(uuid) || instance.isCompleted()) continue;
            return instance;
        }
        return null;
    }

    public void add(BowelsInstance instance) {
        if (!this.instances.contains(instance)) {
            this.instances.add(instance);
        }
    }

    public void onLoad() {
        for (BowelsInstance instance : this.instances) {
            instance.setup(this.world);
        }
        this.nextAvailableStructure = this.getAvailableStructure();
        LOGGER.debug("Pre-locating a bowels arena: {}", this.nextAvailableStructure);
    }

    @Nullable
    private WitherStormEntity findStorm(UUID uuid) {
        MinecraftServer server = this.world.m_7654_();
        for (Level world : server.m_129785_()) {
            ServerLevel serverWorld = (ServerLevel)world;
            Entity entity = serverWorld.m_8791_(uuid);
            if (entity == null || !(entity instanceof WitherStormEntity)) continue;
            return (WitherStormEntity)entity;
        }
        return null;
    }

    public BlockPos calculateEntrancePos(BowelsInstance instance) {
        BlockPos pos = instance.getPos();
        if (instance.getStart() != null) {
            boolean flag = true;
            if (((Boolean)WitherStormModConfig.SERVER.randomBowelsEntrace.get()).booleanValue()) {
                block0: for (int startRadius = 96; startRadius > 35; startRadius -= 10) {
                    int randomStartAngle = this.random.nextInt(360);
                    int angleOffset = 20;
                    for (int i = 0; i < 360 / angleOffset; ++i) {
                        float angle = (float)randomStartAngle + (float)(angleOffset * i);
                        int x = (int)(Mth.m_14031_((float)(angle * ((float)Math.PI / 180))) * (float)startRadius) + pos.m_123341_();
                        int z = (int)(Mth.m_14089_((float)(angle * ((float)Math.PI / 180))) * (float)startRadius) + pos.m_123343_();
                        BlockPos currentPos = new BlockPos(x, 112, z);
                        for (int j = 0; j < 30 && this.world.m_8055_(currentPos.m_7495_()).m_60713_(Blocks.f_50016_); ++j) {
                            currentPos = currentPos.m_7495_();
                        }
                        if (!NaturalSpawner.m_47051_((SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (LevelReader)this.world, (BlockPos)currentPos, (EntityType)EntityType.f_20532_)) continue;
                        int scanRadius = 10;
                        int totalAir = 0;
                        for (BlockState state : WorldUtil.getBlockStatesBetweenClosed((Level)this.world, currentPos.m_7918_(-scanRadius, -scanRadius, -scanRadius), currentPos.m_7918_(scanRadius, scanRadius, scanRadius))) {
                            if (!state.m_60795_()) continue;
                            ++totalAir;
                        }
                        float bias = (float)totalAir / 8000.0f;
                        if (!(bias <= 0.2f)) continue;
                        pos = currentPos;
                        flag = false;
                        break block0;
                    }
                }
            }
            if (flag) {
                Rotation rotation = ((StructurePiece)instance.getStart().m_73602_().get(0)).m_6830_();
                BlockPos offset = BlockPos.f_121853_.m_5487_(Direction.Axis.X, -112).m_7954_(rotation);
                pos = pos.m_6630_(120);
                pos = pos.m_121955_((Vec3i)offset);
                BlockState state = this.world.m_8055_(pos);
                while (state.m_60713_(Blocks.f_50016_) && pos.m_123342_() > 0) {
                    BlockPos below = pos.m_7495_();
                    state = this.world.m_8055_(below);
                    pos = below;
                }
                pos = pos.m_7494_();
            }
        } else {
            LOGGER.error("Structure start is null: {}", (Object)instance);
        }
        return pos;
    }

    public void setCompleted(WitherStormEntity entity, boolean completed) {
        BowelsInstance instance = this.get(entity.m_20148_());
        if (instance != null) {
            instance.setCompleted(completed);
        }
    }

    private void clean() {
        LOGGER.debug("Cleaning instances...");
        int successfullyCleaned = 0;
        for (BowelsInstance instance : this.instances) {
            WitherStormEntity storm = this.findStorm(instance.witherStorm);
            if (instance.isCompleted()) continue;
            instance.setCompleted(storm == null || !storm.m_6084_());
            if (!instance.isCompleted()) continue;
            if (instance.getStart() != null) {
                instance.forceChunks(this.world, instance.getCenter(), false);
                ++successfullyCleaned;
                continue;
            }
            LOGGER.error("Bowels instance start is null! Cannot properly clean instance: {}", (Object)instance);
        }
        LOGGER.debug("Finished cleaning; successfully cleaned {} instances", (Object)successfullyCleaned);
    }

    @Nullable
    public BowelsInstance getInstanceFromEntity(Entity entity) {
        double distance = -1.0;
        BowelsInstance finalInstance = null;
        for (BowelsInstance instance : this.instances) {
            double d0 = entity.m_20183_().m_123331_((Vec3i)instance.getPos());
            if (distance != -1.0 && !(d0 < distance)) continue;
            distance = d0;
            finalInstance = instance;
        }
        return finalInstance;
    }

    public void prepareArena(BowelsInstance instance, WitherStormEntity storm) {
        if (instance.getStart() != null) {
            Rotation rotation = ((StructurePiece)instance.getStart().m_73602_().get(0)).m_6830_();
            BlockPos pos = instance.getCenter().m_6630_(110);
            BlockPos offset = BlockPos.f_121853_.m_5487_(Direction.Axis.X, -3).m_7954_(rotation);
            pos = pos.m_121955_((Vec3i)offset);
            if (!instance.hasPreparedArena()) {
                ((ConfiguredFeature)WitherStormModFeatures.getConfiguredFeature(this.world, WitherStormModFeatures.BOWELS_PODIUM_FEATURE.getId()).m_203334_()).m_224953_((WorldGenLevel)this.world, this.world.m_7726_().m_8481_(), RandomSource.m_216327_(), pos);
                int amount = 6 + this.random.nextInt(6);
                for (int i = 0; i < amount; ++i) {
                    this.trySpawnTentacle(50, (Vec3i)pos);
                }
                this.spawnHeads(rotation, instance.getCenter());
                instance.setPreparedArena();
                LOGGER.debug("Prepared bowels arena for entity {}", (Object)storm);
            }
            if (instance.commandBlock == null) {
                CommandBlockEntity entity = this.createCommandBlock(pos, rotation, storm);
                instance.commandBlock = entity.m_20148_();
                this.world.m_7967_((Entity)entity);
            }
        } else {
            LOGGER.error("Structure start is null: {}", (Object)instance);
        }
    }

    private void trySpawnTentacle(int diameter, Vec3i center) {
        TentacleEntity tentacle;
        BlockPos pos = null;
        for (int i = 0; i < 10; ++i) {
            int x = center.m_123341_() + this.random.nextInt(diameter) - diameter / 2;
            int z = center.m_123343_() + this.random.nextInt(diameter) - diameter / 2;
            int y = center.m_123342_();
            BlockPos currentPos = new BlockPos(x, y, z);
            for (int j = 0; j < 10 && this.world.m_8055_(currentPos.m_7495_()).m_60713_(Blocks.f_50016_); ++j) {
                currentPos = currentPos.m_7495_();
            }
            List nearbyTentacles = this.world.m_45976_(TentacleEntity.class, new AABB(currentPos).m_82400_(10.0));
            if (!NaturalSpawner.m_47051_((SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (LevelReader)this.world, (BlockPos)currentPos, (EntityType)((EntityType)WitherStormModEntityTypes.TENTACLE.get())) || !nearbyTentacles.isEmpty() || !(Math.sqrt(currentPos.m_123331_(center)) > 10.0)) continue;
            pos = currentPos;
            break;
        }
        if (pos != null && (tentacle = (TentacleEntity)((EntityType)WitherStormModEntityTypes.TENTACLE.get()).m_262451_(this.world, null, null, pos, MobSpawnType.EVENT, false, false)) != null && this.hasEnoughSpace((Entity)tentacle, pos)) {
            tentacle.setDormant(true);
            tentacle.lerpCurlTo(0.1f, 0.05f * (float)tentacle.m_217043_().m_188583_(), 8);
            this.world.m_47205_((Entity)tentacle);
        }
    }

    private boolean hasEnoughSpace(Entity entity, BlockPos spawnPos) {
        for (BlockPos pos : BlockPos.m_121940_((BlockPos)spawnPos, (BlockPos)spawnPos.m_7918_(1, 8, 1))) {
            if (this.world.m_8055_(pos).m_60812_((BlockGetter)this.world, pos).m_83281_()) continue;
            return false;
        }
        return true;
    }

    private void spawnHeads(Rotation rotation, BlockPos structurePos) {
        BlockPos[] offsets = new BlockPos[]{NORTH_HEAD_POS, SOUTH_HEAD_POS};
        for (int i = 0; i < 2; ++i) {
            BlockPos offset = offsets[i];
            offset = offset.m_7954_(rotation);
            BlockPos pos = structurePos.m_121955_((Vec3i)offset);
            WitherStormHeadEntity head = (WitherStormHeadEntity)((EntityType)WitherStormModEntityTypes.WITHER_STORM_HEAD.get()).m_262451_(this.world, null, null, pos, MobSpawnType.EVENT, false, false);
            float rot = WitherStormBowelsManager.rotate(rotation) + (i == 0 ? 180.0f : 0.0f);
            head.m_146922_(rot);
            head.m_146926_(60.0f);
            head.m_5618_(head.m_146908_());
            head.m_5616_(head.m_146908_());
            head.setActive(false);
            this.world.m_7967_((Entity)head);
        }
    }

    private CommandBlockEntity createCommandBlock(BlockPos pos, Rotation rotation, WitherStormEntity storm) {
        CommandBlockEntity entity = (CommandBlockEntity)((EntityType)WitherStormModEntityTypes.COMMAND_BLOCK.get()).m_20615_((Level)this.world);
        entity.m_6034_((double)pos.m_123341_() + 0.5, pos.m_123342_(), (double)pos.m_123343_() + 0.5);
        entity.setState(CommandBlockEntity.State.BOSSFIGHT);
        entity.setMode(CommandBlockEntity.Mode.TENTACLES);
        entity.setOwner(storm);
        entity.setOwnerUUID(storm.m_20148_());
        entity.m_146922_(WitherStormBowelsManager.rotate(rotation) + 90.0f);
        entity.m_5618_(entity.m_146908_());
        entity.m_5616_(entity.m_146908_());
        return entity;
    }

    private static float rotate(Rotation rotation) {
        switch (rotation) {
            case CLOCKWISE_180: {
                return 180.0f;
            }
            case COUNTERCLOCKWISE_90: {
                return 270.0f;
            }
            case CLOCKWISE_90: {
                return 90.0f;
            }
        }
        return 0.0f;
    }

    private void resetEmptyTimeIfNeeded() {
        for (BowelsInstance instance : this.instances) {
            if (instance.isCompleted()) continue;
            this.world.m_8886_();
            break;
        }
    }

    public static BowelsEnterStatus enter(ServerLevel world, WitherStormEntity storm, Entity entity) {
        ServerLevel bowels;
        WitherStormBowelsManager manager;
        BowelsEnterStatus flag = BowelsEnterStatus.ENTITY_CANNOT_CHANGE;
        if (entity.isAddedToWorld() && entity.m_6072_() && !entity.m_20159_() && !entity.m_20160_() && (manager = (WitherStormBowelsManager)(bowels = WitherStormMod.bowels(world)).getCapability(WitherStormModCapabilities.BOWELS_MANAGER).orElse(null)) != null) {
            if (storm.m_6084_()) {
                BowelsInstance instance = manager.getOrCreateInstanceFor(storm);
                if (instance != null) {
                    manager.add(instance);
                    instance.setup(bowels);
                    manager.prepareArena(instance, storm);
                    BlockPos pos = manager.calculateEntrancePos(instance);
                    entity.changeDimension(bowels, (ITeleporter)new BowelsTeleporter(pos));
                    LOGGER.info("{} is entering the bowels of {}", (Object)entity, (Object)storm);
                    flag = BowelsEnterStatus.SUCCESS;
                } else {
                    flag = BowelsEnterStatus.CANT_SETUP_BOWELS;
                    LOGGER.error("Failed to setup bowels! It's likely the mod could not locate an available bowels arena or failed to do so.");
                }
            }
            manager.clean();
        }
        return flag;
    }

    public static void leave(ServerLevel world, Entity entity, @Nullable BowelsInstance instance) {
        if (entity.isAddedToWorld() && entity.m_6072_() && !entity.m_20159_() && !entity.m_20160_()) {
            world.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> {
                BowelsInstance newInstance = instance;
                if (newInstance == null) {
                    newInstance = manager.getInstanceFromEntity(entity);
                }
                if (newInstance != null) {
                    WitherStormEntity storm = manager.findStorm(newInstance.witherStorm);
                    if (storm != null) {
                        BlockPos pos = storm.m_20183_().m_6625_(5);
                        entity.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> data.makeInvulnerable(2400));
                        Entity teleported = entity.changeDimension((ServerLevel)storm.m_9236_(), (ITeleporter)new BowelsTeleporter(pos));
                        if (teleported instanceof LivingEntity) {
                            LivingEntity living = (LivingEntity)teleported;
                            if (((Boolean)WitherStormModConfig.SERVER.bowelsFallResistance.get()).booleanValue()) {
                                living.m_7292_(new MobEffectInstance(MobEffects.f_19606_, 120, 255, false, false, false));
                            }
                        }
                    } else {
                        ServerLevel overworld = world.m_7654_().m_129880_(Level.f_46428_);
                        entity.changeDimension(overworld, (ITeleporter)new BowelsTeleporter(overworld.m_220360_()));
                    }
                } else {
                    ServerLevel overworld = world.m_7654_().m_129880_(Level.f_46428_);
                    entity.changeDimension(overworld, (ITeleporter)new BowelsTeleporter(overworld.m_220360_()));
                }
            });
        }
    }

    public static void queueEnter(ServerPlayer player, WitherStormEntity storm) {
        if (!player.m_9236_().m_46472_().m_135782_().equals((Object)WitherStormMod.bowelsLocation()) && player.m_6072_() && !player.m_20159_() && !player.m_20160_()) {
            ServerLevel bowels = WitherStormMod.bowels(player.m_284548_());
            bowels.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> manager.queuedEnter.putIfAbsent(storm.m_20148_(), (Entity)player));
        }
    }

    public static void queueLeave(Entity entity) {
        WitherStormBowelsManager.queueLeave(entity, () -> {});
    }

    public static void queueLeave(Entity entity, Runnable action) {
        if (entity.m_9236_().m_46472_().m_135782_().equals((Object)WitherStormMod.bowelsLocation()) && entity.m_6072_() && !entity.m_20159_() && !entity.m_20160_()) {
            ServerLevel bowels = WitherStormMod.bowels((ServerLevel)entity.m_9236_());
            bowels.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> manager.queuedLeave.putIfAbsent(entity, action));
        }
    }

    public static Holder<Structure> getBowels(ServerLevel level) {
        return level.m_9598_().m_175515_(Registries.f_256944_).m_246971_(ResourceKey.m_135785_((ResourceKey)Registries.f_256944_, (ResourceLocation)WitherStormModStructures.BOWELS.getId()));
    }

    @Nullable
    private static StructureStart getAvailableStartAt(ServerLevel world, BlockPos pos) {
        ChunkAccess chunk = world.m_46819_(pos.m_123341_() >> 4, pos.m_123343_() >> 4, ChunkStatus.f_62315_);
        StructureStart start = world.m_215010_().m_220512_(SectionPos.m_175562_((ChunkAccess)chunk), (Structure)WitherStormBowelsManager.getBowels(world).m_203334_(), (StructureAccess)chunk);
        if (start != null && start.m_73603_()) {
            return start;
        }
        return null;
    }

    @SubscribeEvent
    public static void onDimensionLoad(LevelEvent.Load event) {
        Level level = (Level)event.getLevel();
        if (!level.m_5776_() && level.m_46472_().m_135782_().equals((Object)WitherStormMod.bowelsLocation())) {
            level.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> manager.onLoad());
        }
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.LevelTickEvent event) {
        Level level = event.level;
        if (level instanceof ServerLevel) {
            ServerLevel world = (ServerLevel)level;
            if (event.phase == TickEvent.Phase.START && world.m_46472_().m_135782_().equals((Object)WitherStormMod.bowelsLocation())) {
                world.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> {
                    manager.queuedEnter.forEach((uuid, player) -> {
                        WitherStormEntity entity = manager.findStorm((UUID)uuid);
                        if (entity != null) {
                            WitherStormBowelsManager.enter(world, entity, player);
                        }
                    });
                    manager.queuedEnter.clear();
                    manager.queuedLeave.forEach((player, action) -> {
                        WitherStormBowelsManager.leave(world, player, null);
                        action.run();
                    });
                    manager.queuedLeave.clear();
                    manager.resetEmptyTimeIfNeeded();
                });
                for (Entity entity : world.m_8583_()) {
                    if (entity == null || !(entity.m_20186_() < 50.0)) continue;
                    WitherStormBowelsManager.queueLeave(entity);
                }
            }
        }
    }

    public static class BowelsInstance {
        private static final TicketType<ChunkPos> BOWELS = TicketType.m_9462_((String)"witherstormmod:bowels", Comparator.comparingLong(ChunkPos::m_45588_));
        @Nullable
        private Pair<BlockPos, StructureStart> start;
        private final UUID witherStorm;
        @Nullable
        private UUID commandBlock;
        private boolean hasPreparedArena;
        private boolean isCompleted;

        public BowelsInstance(Pair<BlockPos, StructureStart> start, UUID storm) {
            this.start = start;
            this.witherStorm = storm;
        }

        public BowelsInstance(BlockPos pos, UUID storm) {
            this.start = Pair.of((Object)pos, null);
            this.witherStorm = storm;
        }

        public void setup(ServerLevel world) {
            if (!world.m_46472_().m_135782_().equals((Object)WitherStormMod.bowelsLocation())) {
                LOGGER.error("Cannot setup {} in {}", (Object)this, (Object)world.m_46472_());
                return;
            }
            if (this.getStart() == null) {
                StructureStart start = WitherStormBowelsManager.getAvailableStartAt(world, this.getPos());
                if (start != null) {
                    this.start = Pair.of((Object)this.getPos(), (Object)start);
                } else {
                    LOGGER.error("Could not find saved structure start or start is not valid from {}. It is important that these values aren't modified!", (Object)this.getPos());
                    return;
                }
            }
            LOGGER.debug("Successfully setup this bowels instance {}", (Object)this);
        }

        public void doChunkLoading(ServerLevel level) {
            if (!this.isCompleted()) {
                this.forceChunks(level, this.getCenter(), true);
            } else {
                this.forceChunks(level, this.getCenter(), false);
            }
        }

        private void forceChunks(ServerLevel world, BlockPos pos, boolean force) {
            ChunkPos chunkPos = new ChunkPos(pos);
            if (force) {
                LOGGER.debug("Loaded chunks in arena");
                world.m_7726_().addRegionTicket(BOWELS, chunkPos, 3, (Object)chunkPos, true);
            } else {
                LOGGER.debug("Unloaded chunks in arena");
                world.m_7726_().m_8438_(BOWELS, chunkPos, 3, (Object)chunkPos);
            }
        }

        public CompoundTag write() {
            CompoundTag compound = new CompoundTag();
            compound.m_128365_("Pos", (Tag)NbtUtils.m_129224_((BlockPos)this.getPos()));
            compound.m_128362_("Storm", this.witherStorm);
            if (this.commandBlock != null) {
                compound.m_128362_("CommandBlock", this.commandBlock);
            }
            compound.m_128379_("HasPreparedArena", this.hasPreparedArena);
            compound.m_128379_("Completed", this.isCompleted);
            return compound;
        }

        public static BowelsInstance read(CompoundTag compound) {
            BlockPos pos = NbtUtils.m_129239_((CompoundTag)compound.m_128469_("Pos"));
            UUID storm = compound.m_128342_("Storm");
            UUID commandBlock = null;
            if (compound.m_128441_("CommandBlock")) {
                commandBlock = compound.m_128342_("CommandBlock");
            }
            BowelsInstance instance = new BowelsInstance(pos, storm);
            instance.commandBlock = commandBlock;
            instance.hasPreparedArena = compound.m_128471_("HasPreparedArena");
            instance.isCompleted = compound.m_128471_("Completed");
            return instance;
        }

        public void setCompleted(boolean completed) {
            this.isCompleted = completed;
        }

        public boolean isCompleted() {
            return this.isCompleted;
        }

        public void setPreparedArena() {
            this.hasPreparedArena = true;
        }

        public boolean hasPreparedArena() {
            return this.hasPreparedArena;
        }

        public UUID getCommandBlockUUID() {
            return this.commandBlock;
        }

        public BlockPos getPos() {
            return (BlockPos)this.start.getFirst();
        }

        @Nullable
        public BlockPos getCenter() {
            StructureStart start = this.getStart();
            if (start != null) {
                BlockPos center = ((StructurePiece)start.m_73602_().get(0)).m_73547_().m_162394_();
                return new BlockPos(center.m_123341_(), 0, center.m_123343_());
            }
            return null;
        }

        @Nullable
        public StructureStart getStart() {
            return (StructureStart)this.start.getSecond();
        }

        public String toString() {
            return "BowelsInstance[start = " + this.start + ", pos = " + this.getPos() + ", storm = " + this.witherStorm + ", commandBlock = " + this.commandBlock + ", hasPreparedArena = " + this.hasPreparedArena + ", completed = " + this.isCompleted + "]";
        }
    }

    public static enum BowelsEnterStatus {
        SUCCESS,
        CANT_SETUP_BOWELS,
        ENTITY_CANNOT_CHANGE;

    }
}

