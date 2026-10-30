/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.HolderGetter
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtUtils
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$MovementEmission
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.network.NetworkHooks
 */
package nonamecrackers2.witherstormmod.common.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.serializer.WitherStormModDataSerializers;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
import nonamecrackers2.witherstormmod.common.util.WitherStormModNBTUtil;

public class BlockClusterEntity
extends Entity {
    protected static final EntityDataAccessor<Map<BlockPos, BlockState>> BLOCKS = SynchedEntityData.m_135353_(BlockClusterEntity.class, WitherStormModDataSerializers.BLOCK_STATE_POS_MAP);
    private static final EntityDataAccessor<List<CompoundTag>> TILE_DATA = SynchedEntityData.m_135353_(BlockClusterEntity.class, WitherStormModDataSerializers.COMPOUND_LIST);
    private static final EntityDataAccessor<BlockPos> START_POS = SynchedEntityData.m_135353_(BlockClusterEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135038_);
    private static final EntityDataAccessor<Vec2> ROTATION_DELTA = SynchedEntityData.m_135353_(BlockClusterEntity.class, WitherStormModDataSerializers.VECTOR_2F);
    private static final EntityDataAccessor<Boolean> PHYSICS = SynchedEntityData.m_135353_(BlockClusterEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Boolean> FORCE_RENDER = SynchedEntityData.m_135353_(BlockClusterEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135035_);
    private static final EntityDataAccessor<Float> X_SIZE = SynchedEntityData.m_135353_(BlockClusterEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Float> Y_SIZE = SynchedEntityData.m_135353_(BlockClusterEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Float> Z_SIZE = SynchedEntityData.m_135353_(BlockClusterEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Integer> SHAKE_TIME = SynchedEntityData.m_135353_(BlockClusterEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    protected static final EntityDataAccessor<Optional<BlockPos>> FADE_POINT = SynchedEntityData.m_135353_(BlockClusterEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135039_);
    private static final EntityDataAccessor<Float> FADE_STRENGTH = SynchedEntityData.m_135353_(BlockClusterEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135029_);
    private static final EntityDataAccessor<Integer> FADE_DISTANCE_OFFSET = SynchedEntityData.m_135353_(BlockClusterEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    public int time;
    public boolean dropItems;
    public boolean resetGravityOnLoad;
    private int shakeTime;
    @Nonnull
    public Vec2 shakeO;
    @Nonnull
    public Vec2 shake;
    private int sink;
    private boolean antiStacking;
    private boolean shouldCrumble;
    private boolean shouldntCountToConsumedEntities;
    private float xClusterRot;
    private float xClusterRotO;
    private float yClusterRot;
    private float yClusterRotO;
    private boolean createdFromBeam;
    private boolean createdFromFallingBlock;
    private int headCreatedFrom;
    private double tractorBeamDistanceThreshold;

    public BlockClusterEntity(EntityType<? extends BlockClusterEntity> entityType, Level world) {
        super(entityType, world);
        this.dropItems = (Boolean)WitherStormModConfig.COMMON.blockClustersDropItems.get();
        this.resetGravityOnLoad = true;
        this.shakeO = Vec2.f_82462_;
        this.shake = Vec2.f_82462_;
        this.headCreatedFrom = -1;
    }

    public void populate(Map<BlockPos, BlockState> states) {
        if (states.size() > 0) {
            int minX = 0;
            int minY = 0;
            int minZ = 0;
            int maxX = 0;
            int maxY = 0;
            int maxZ = 0;
            for (Map.Entry<BlockPos, BlockState> entry : states.entrySet()) {
                BlockPos pos = entry.getKey();
                if (pos.m_123341_() < minX) {
                    minX = pos.m_123341_();
                }
                if (pos.m_123342_() < minY) {
                    minY = pos.m_123342_();
                }
                if (pos.m_123343_() < minZ) {
                    minZ = pos.m_123343_();
                }
                if (pos.m_123341_() > maxX) {
                    maxX = pos.m_123341_();
                }
                if (pos.m_123342_() > maxY) {
                    maxY = pos.m_123342_();
                }
                if (pos.m_123343_() <= maxZ) continue;
                maxZ = pos.m_123343_();
            }
            float x = maxX - minX;
            float y = maxY - minY;
            float z = maxZ - minZ;
            this.setSize(Math.abs(x) + 1.0f, Math.abs(y) + 1.0f, Math.abs(z) + 1.0f);
            this.setStartPos(BlockPos.m_274561_((double)((double)minX + (double)x / 2.0), (double)((double)minY + (double)y / 2.0), (double)((double)minZ + (double)z / 2.0)));
            this.setBlocks(states);
        }
    }

    public void populate(BlockPos start, BlockPos end, Predicate<BlockState> filter) {
        float x = Mth.m_14143_((float)(end.m_123341_() - start.m_123341_()));
        float y = Mth.m_14143_((float)(end.m_123342_() - start.m_123342_()));
        float z = Mth.m_14143_((float)(end.m_123343_() - start.m_123343_()));
        Vec3 clusterPos = Vec3.m_82528_((Vec3i)start).m_82520_((double)x / 2.0 + 0.5, Math.min((double)y, 0.0), (double)z / 2.0 + 0.5);
        this.m_6034_(clusterPos.f_82479_, clusterPos.f_82480_, clusterPos.f_82481_);
        this.setSize(Math.abs(x) + 1.0f, Math.abs(y) + 1.0f, Math.abs(z) + 1.0f);
        this.setStartPos(start.m_7918_((int)((double)x / 2.0), (int)((double)y / 2.0), (int)((double)z / 2.0)));
        Iterable blocks = BlockPos.m_121940_((BlockPos)start, (BlockPos)end);
        for (BlockPos blockPos : blocks) {
            BlockEntity tile;
            BlockState state = this.m_9236_().m_8055_(blockPos);
            if (state.m_60795_() || !filter.test(state)) continue;
            if (state.m_155947_() && (tile = this.m_9236_().m_7702_(blockPos)) != null) {
                this.addTileData(tile.serializeNBT());
                this.m_9236_().m_46747_(blockPos);
            }
            BlockPos relative = blockPos.m_121996_((Vec3i)this.getStartPos());
            this.addBlock(state, relative);
        }
        for (Map.Entry entry : this.getBlocks().entrySet()) {
            BlockPos pos = this.getStartPos().m_121955_((Vec3i)entry.getKey());
            this.m_9236_().m_7731_(pos, Blocks.f_50016_.m_49966_(), 3);
        }
    }

    public void populateWithRadius(BlockPos start, float radius, Predicate<BlockState> filter) {
        this.setSize(Mth.m_14167_((float)radius) * 2 - 1, Mth.m_14167_((float)radius) * 2 - 1, Mth.m_14167_((float)radius) * 2 - 1);
        this.setStartPos(start);
        this.m_6034_((float)start.m_123341_() + 0.5f, (double)start.m_123342_() - this.m_20191_().m_82399_().f_82480_ + 0.5, (float)start.m_123343_() + 0.5f);
        for (int x = -Mth.m_14143_((float)radius); x < Mth.m_14167_((float)radius); ++x) {
            for (int y = -Mth.m_14143_((float)radius); y < Mth.m_14167_((float)radius); ++y) {
                for (int z = -Mth.m_14143_((float)radius); z < Mth.m_14167_((float)radius); ++z) {
                    BlockEntity tile;
                    if (!(Mth.m_14116_((float)(x * x + y * y + z * z)) < radius)) continue;
                    BlockPos currentPos = new BlockPos(x + start.m_123341_(), y + start.m_123342_(), z + start.m_123343_());
                    BlockPos relativePos = new BlockPos(x, y, z);
                    BlockState state = this.m_9236_().m_8055_(currentPos);
                    if (state.m_60795_() || !filter.test(state)) continue;
                    if (state.m_155947_() && (tile = this.m_9236_().m_7702_(currentPos)) != null) {
                        this.addTileData(tile.serializeNBT());
                        this.m_9236_().m_46747_(currentPos);
                    }
                    this.addBlock(state, relativePos);
                }
            }
        }
        for (Map.Entry<BlockPos, BlockState> entry : this.getBlocks().entrySet()) {
            BlockPos pos = start.m_121955_((Vec3i)entry.getKey());
            this.m_9236_().m_7731_(pos, Blocks.f_50016_.m_49966_(), 3);
        }
    }

    public void setTime(int time) {
        this.time = time;
    }

    protected void m_8097_() {
        this.f_19804_.m_135372_(START_POS, (Object)BlockPos.f_121853_);
        this.f_19804_.m_135372_(BLOCKS, new HashMap());
        this.f_19804_.m_135372_(TILE_DATA, new ArrayList());
        this.f_19804_.m_135372_(ROTATION_DELTA, (Object)new Vec2(0.0f, 0.0f));
        this.f_19804_.m_135372_(PHYSICS, (Object)true);
        this.f_19804_.m_135372_(FORCE_RENDER, (Object)false);
        this.f_19804_.m_135372_(X_SIZE, (Object)Float.valueOf(1.0f));
        this.f_19804_.m_135372_(Y_SIZE, (Object)Float.valueOf(1.0f));
        this.f_19804_.m_135372_(Z_SIZE, (Object)Float.valueOf(1.0f));
        this.f_19804_.m_135372_(SHAKE_TIME, (Object)0);
        this.f_19804_.m_135372_(FADE_POINT, Optional.empty());
        this.f_19804_.m_135372_(FADE_STRENGTH, (Object)Float.valueOf(10.0f));
        this.f_19804_.m_135372_(FADE_DISTANCE_OFFSET, (Object)0);
    }

    protected void m_7378_(CompoundTag compound) {
        if (compound.m_128441_("StartPos")) {
            this.setStartPos(NbtUtils.m_129239_((CompoundTag)compound.m_128469_("StartPos")));
        }
        if (compound.m_128441_("Blocks")) {
            this.setBlocks(WitherStormModNBTUtil.readBlockStatePosMap((HolderGetter<Block>)this.m_9236_().m_246945_(Registries.f_256747_), compound.m_128437_("Blocks", 10)));
        }
        if (compound.m_128441_("TileData")) {
            this.setTileData(WitherStormModNBTUtil.readCompoundList(compound.m_128437_("TileData", 10)));
        }
        if (compound.m_128441_("RotationDelta")) {
            CompoundTag deltaCompound = compound.m_128469_("RotationDelta");
            this.setRotationDelta(WitherStormModNBTUtil.readVector2f(deltaCompound));
        }
        if (compound.m_128441_("Width")) {
            this.f_19804_.m_135381_(X_SIZE, (Object)Float.valueOf(compound.m_128457_("Width")));
            this.f_19804_.m_135381_(Z_SIZE, (Object)Float.valueOf(compound.m_128457_("Width")));
        } else {
            this.f_19804_.m_135381_(X_SIZE, (Object)Float.valueOf(compound.m_128457_("XSize")));
            this.f_19804_.m_135381_(Z_SIZE, (Object)Float.valueOf(compound.m_128457_("ZSize")));
        }
        if (compound.m_128441_("Height")) {
            this.f_19804_.m_135381_(Y_SIZE, (Object)Float.valueOf(compound.m_128457_("Height")));
        } else {
            this.f_19804_.m_135381_(Y_SIZE, (Object)Float.valueOf(compound.m_128457_("YSize")));
        }
        this.m_6210_();
        this.time = compound.m_128451_("Time");
        this.dropItems = compound.m_128471_("DropItems");
        this.resetGravityOnLoad = compound.m_128471_("ResetGravity");
        if (this.resetGravityOnLoad) {
            this.m_20242_(false);
        }
        this.setForceRender(compound.m_128471_("ForceRender"));
        this.setShakeTime(compound.m_128451_("ShakeTime"));
        this.setSink(compound.m_128451_("GroundSink"));
        this.setAntiStacking(compound.m_128471_("AntiStacking"));
        if (compound.m_128441_("StaticFadePos")) {
            this.f_19804_.m_135381_(FADE_POINT, Optional.of(NbtUtils.m_129239_((CompoundTag)compound.m_128469_("StaticFadePos"))));
        }
        this.shouldCrumble = compound.m_128471_("ShouldCrumble");
        this.shouldntCountToConsumedEntities = compound.m_128471_("ShouldntCountToConsumedEntities");
        this.createdFromBeam = compound.m_128471_("CreatedFromBeam");
        this.createdFromFallingBlock = compound.m_128471_("CreatedFromFallingBlock");
        this.headCreatedFrom = compound.m_128451_("HeadCreatedFrom");
        this.tractorBeamDistanceThreshold = compound.m_128459_("TractorBeamDistanceThreshold");
        if (compound.m_128441_("HasPhysics")) {
            this.setPhysics(compound.m_128471_("HasPhysics"));
        }
    }

    protected void m_7380_(CompoundTag compound) {
        compound.m_128365_("StartPos", (Tag)NbtUtils.m_129224_((BlockPos)this.getStartPos()));
        compound.m_128365_("Blocks", (Tag)WitherStormModNBTUtil.writeBlockStatePosMap(this.getBlocks()));
        compound.m_128365_("TileData", (Tag)WitherStormModNBTUtil.writeCompoundList(this.getTileData()));
        compound.m_128350_("XSize", ((Float)this.f_19804_.m_135370_(X_SIZE)).floatValue());
        compound.m_128350_("YSize", ((Float)this.f_19804_.m_135370_(Y_SIZE)).floatValue());
        compound.m_128350_("ZSize", ((Float)this.f_19804_.m_135370_(Z_SIZE)).floatValue());
        compound.m_128405_("Time", this.time);
        compound.m_128379_("DropItems", this.dropItems);
        compound.m_128365_("RotationDelta", (Tag)WitherStormModNBTUtil.writeVector2f(this.getRotationDelta()));
        compound.m_128379_("ResetGravity", this.resetGravityOnLoad);
        compound.m_128379_("ForceRender", this.forceRender());
        compound.m_128405_("ShakeTime", this.shakeTime);
        compound.m_128405_("GroundSink", this.getSink());
        compound.m_128379_("AntiStacking", this.antiStacking());
        ((Optional)this.f_19804_.m_135370_(FADE_POINT)).ifPresent(pos -> compound.m_128365_("StaticFadePos", (Tag)NbtUtils.m_129224_((BlockPos)pos)));
        compound.m_128379_("ShouldCrumble", this.shouldCrumble);
        compound.m_128379_("ShouldntCountToConsumedEntities", this.shouldntCountToConsumedEntities);
        compound.m_128379_("CreatedFromBeam", this.createdFromBeam);
        compound.m_128379_("CreatedFromFallingBlock", this.createdFromFallingBlock);
        compound.m_128405_("HeadCreatedFrom", this.headCreatedFrom);
        compound.m_128347_("TractorBeamDistanceThreshold", this.tractorBeamDistanceThreshold);
        compound.m_128379_("HasPhysics", this.physicsEnabled());
    }

    public BlockClusterEntity splitAt(Direction.Axis axis) {
        HashMap split = Maps.newHashMap();
        HashMap current = Maps.newHashMap(this.getBlocks());
        Iterator iterator = current.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            BlockPos pos = (BlockPos)entry.getKey();
            switch (axis) {
                case Y: {
                    if (pos.m_123342_() >= 0) break;
                    split.put(pos, (BlockState)entry.getValue());
                    iterator.remove();
                    break;
                }
                case X: {
                    if (pos.m_123341_() >= 0) break;
                    split.put(pos, (BlockState)entry.getValue());
                    iterator.remove();
                    break;
                }
                case Z: {
                    if (pos.m_123343_() >= 0) break;
                    split.put(pos, (BlockState)entry.getValue());
                    iterator.remove();
                }
            }
        }
        if (!split.isEmpty() && !current.isEmpty()) {
            if (this.getSize() < 10) {
                this.setShouldCrumble(false);
            }
            this.setBlocks(current);
            BlockClusterEntity splitCluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(this.m_9236_());
            splitCluster.setSize(((Float)this.f_19804_.m_135370_(X_SIZE)).floatValue(), ((Float)this.f_19804_.m_135370_(Y_SIZE)).floatValue(), ((Float)this.f_19804_.m_135370_(Z_SIZE)).floatValue());
            splitCluster.setStartPos(this.getStartPos());
            splitCluster.setBlocks(split);
            splitCluster.m_20219_(this.m_20182_());
            splitCluster.m_20242_(this.m_20068_());
            splitCluster.setPhysics(this.physicsEnabled());
            splitCluster.setRotationDelta(this.getRotationDelta());
            splitCluster.m_20256_(this.m_20184_().m_82490_(0.8));
            splitCluster.setShouldCrumble(this.shouldCrumble());
            splitCluster.setFadePos(this.getFadePos());
            splitCluster.setFadeDistanceOffset(this.getFadeDistanceOffset());
            splitCluster.setFadeStrength(this.getFadeStrength());
            return splitCluster;
        }
        return null;
    }

    public void m_8119_() {
        this.shakeO = new Vec2(this.shake.f_82470_, this.shake.f_82471_);
        if (this.shakeTime > 0) {
            float shakeTime = this.getShakeTime();
            float x = Mth.m_14089_((float)(shakeTime * 4.5f)) * 0.05f + (this.f_19796_.m_188501_() - 0.5f) * 0.05f;
            float z = Mth.m_14031_((float)(shakeTime * 3.5f)) * 0.15f + (this.f_19796_.m_188501_() - 0.5f) * 0.2f;
            this.shake = new Vec2(x, z);
            --this.shakeTime;
            if (this.shakeTime == 0) {
                this.setShakeTime(0);
            }
        } else {
            this.shake = new Vec2(0.0f, 0.0f);
        }
        if (!this.m_20068_()) {
            this.m_20256_(this.m_20184_().m_82520_(0.0, -0.04, 0.0));
        }
        this.m_6478_(MoverType.SELF, this.m_20184_());
        ++this.time;
        this.f_19794_ = !this.physicsEnabled();
        super.m_8119_();
        this.xClusterRotO = this.xClusterRot;
        this.yClusterRotO = this.yClusterRot;
        if (this.getShakeTime() <= 0) {
            this.xClusterRot += this.getRotationDelta().f_82470_;
            this.yClusterRot += this.getRotationDelta().f_82471_;
        }
        if (!this.m_9236_().f_46443_) {
            BlockState state;
            BlockPos pos = this.m_20183_();
            if (this.getBlocks().isEmpty()) {
                this.m_146870_();
            }
            Map<BlockPos, BlockState> blocks = this.getBlocks();
            boolean isAir = true;
            for (Map.Entry<BlockPos, BlockState> entry : blocks.entrySet()) {
                state = entry.getValue();
                if (!isAir) continue;
                isAir = state.m_60795_();
            }
            if (isAir) {
                this.m_146870_();
            }
            if (!this.m_20096_()) {
                if ((float)pos.m_123342_() + this.m_20206_() <= (float)this.m_9236_().m_141937_() || this.time > 600) {
                    if (this.dropItems && this.m_9236_().m_46469_().m_46207_(GameRules.f_46137_)) {
                        for (Map.Entry<BlockPos, BlockState> entry : blocks.entrySet()) {
                            state = entry.getValue();
                            BlockPos position = pos.m_121955_((Vec3i)entry.getKey());
                            this.spawnAtSpecificLocation((ItemLike)state.m_60734_().m_5456_(), position);
                        }
                    }
                    this.m_146870_();
                }
            } else {
                this.place();
            }
            if (((Boolean)WitherStormModConfig.SERVER.clustersRemoveItems.get()).booleanValue() && this.getSize() != 1 && !this.createdFromTractorBeam()) {
                List items = this.m_9236_().m_45976_(ItemEntity.class, this.m_20191_());
                for (ItemEntity entity : items) {
                    if (!entity.m_6084_() || entity.m_19749_() != null) continue;
                    entity.m_146870_();
                }
            }
        } else {
            this.m_6210_();
            this.m_20090_();
        }
    }

    public void place() {
        this.m_146870_();
        BlockPos pos = this.m_20183_();
        if (this.antiStacking()) {
            BlockPos currentPos = this.m_20183_();
            BlockState current = this.m_9236_().m_8055_(currentPos);
            for (int i = 0; i < 50 && current.m_60795_(); ++i) {
                currentPos = currentPos.m_7495_();
                current = this.m_9236_().m_8055_(currentPos);
            }
            pos = pos.m_175288_(currentPos.m_123342_());
        }
        for (Map.Entry<BlockPos, BlockState> entry : this.getBlocks().entrySet()) {
            BlockState state = entry.getValue();
            BlockPos relativePos = entry.getKey();
            BlockPos basePos = pos.m_7918_(relativePos.m_123341_(), relativePos.m_123342_() - this.getSink(), relativePos.m_123343_());
            BlockPos placementPos = basePos.m_6630_(Mth.m_14107_((double)(this.m_20191_().m_82376_() / 2.0 - 0.5)));
            if (this.m_9236_().m_7702_(placementPos) == null && !this.m_9236_().m_8055_(placementPos).m_204336_(BlockTags.f_13069_) && !state.m_204336_(WitherStormModBlockTags.BLOCK_CLUSTERS_CANNOT_PLACE) && this.m_9236_().m_7731_(placementPos, state, 3)) {
                BlockEntity tile;
                CompoundTag tileData;
                if (state.m_155947_() && (tileData = this.getTileDataFromOffsetPos(relativePos)) != null && (tile = this.m_9236_().m_7702_(placementPos)) != null) {
                    tileData.m_128405_("x", placementPos.m_123341_());
                    tileData.m_128405_("y", placementPos.m_123342_());
                    tileData.m_128405_("z", placementPos.m_123343_());
                    tile.m_142466_(tileData);
                    tile.m_6596_();
                }
                this.m_9236_().m_46672_(placementPos, state.m_60734_());
                continue;
            }
            if (!this.dropItems || !this.m_9236_().m_46469_().m_46207_(GameRules.f_46137_)) continue;
            this.spawnAtSpecificLocation((ItemLike)state.m_60734_().m_5456_(), placementPos);
        }
    }

    public void spawnAtSpecificLocation(ItemLike item, BlockPos position) {
        ItemStack stack = new ItemStack(item);
        ItemEntity itemEntity = new ItemEntity(this.m_9236_(), (double)position.m_123341_(), (double)position.m_123342_(), (double)position.m_123343_(), stack);
        itemEntity.m_32060_();
        if (this.captureDrops() != null) {
            this.captureDrops().add(itemEntity);
        } else {
            this.m_9236_().m_7967_((Entity)itemEntity);
        }
    }

    public void setRotationDelta(Vec2 rotation) {
        this.f_19804_.m_135381_(ROTATION_DELTA, (Object)rotation);
    }

    public Vec2 getRotationDelta() {
        return (Vec2)this.f_19804_.m_135370_(ROTATION_DELTA);
    }

    public Map<BlockPos, BlockState> getBlocks() {
        return (Map)this.f_19804_.m_135370_(BLOCKS);
    }

    public void addBlock(BlockState state, BlockPos relativePosition) {
        this.put(BLOCKS, state, relativePosition);
    }

    private void put(EntityDataAccessor<Map<BlockPos, BlockState>> param, BlockState state, BlockPos pos) {
        HashMap map = Maps.newHashMap(this.getBlocks());
        map.put(pos, state);
        this.f_19804_.m_135381_(param, (Object)map);
    }

    public void setBlocks(Map<BlockPos, BlockState> blocks) {
        this.f_19804_.m_135381_(BLOCKS, blocks);
    }

    public void setStartPos(BlockPos pos) {
        this.f_19804_.m_135381_(START_POS, (Object)pos);
    }

    public BlockPos getStartPos() {
        return (BlockPos)this.f_19804_.m_135370_(START_POS);
    }

    public List<CompoundTag> getTileData() {
        return (List)this.f_19804_.m_135370_(TILE_DATA);
    }

    @Nullable
    public CompoundTag getTileDataFromOffsetPos(BlockPos pos) {
        BlockPos actualPos = this.getStartPos().m_121955_((Vec3i)pos);
        for (CompoundTag data : this.getTileData()) {
            if (data.m_128451_("x") != actualPos.m_123341_() || data.m_128451_("y") != actualPos.m_123342_() || data.m_128451_("z") != actualPos.m_123343_()) continue;
            return data;
        }
        return null;
    }

    public void addTileData(CompoundTag compound) {
        ArrayList list = Lists.newArrayList(this.getTileData());
        list.add(compound);
        this.f_19804_.m_276349_(TILE_DATA, (Object)list, true);
    }

    public void setTileData(List<CompoundTag> list) {
        this.f_19804_.m_135381_(TILE_DATA, list);
    }

    public EntityDimensions m_6972_(Pose pose) {
        return EntityDimensions.m_20395_((float)Math.max(((Float)this.f_19804_.m_135370_(X_SIZE)).floatValue(), ((Float)this.f_19804_.m_135370_(Z_SIZE)).floatValue()), (float)((Float)this.f_19804_.m_135370_(Y_SIZE)).floatValue());
    }

    public Packet<ClientGamePacketListener> m_5654_() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    public int getSize() {
        return this.getBlocks().size();
    }

    public boolean physicsEnabled() {
        return (Boolean)this.f_19804_.m_135370_(PHYSICS);
    }

    public void setPhysics(boolean physics) {
        this.f_19804_.m_135381_(PHYSICS, (Object)physics);
        this.f_19794_ = !physics;
    }

    public boolean containsBlock(Block block) {
        for (Map.Entry<BlockPos, BlockState> entry : this.getBlocks().entrySet()) {
            if (!entry.getValue().m_60713_(block)) continue;
            return true;
        }
        return false;
    }

    public void setResetGravityOnLoad(boolean shouldReset) {
        this.resetGravityOnLoad = shouldReset;
    }

    public void m_7350_(EntityDataAccessor<?> parameter) {
        super.m_7350_(parameter);
        if (parameter.equals(X_SIZE) || parameter.equals(Y_SIZE) || parameter.equals(Z_SIZE)) {
            this.m_6210_();
        } else if (parameter.equals(SHAKE_TIME)) {
            this.shakeTime = (Integer)this.f_19804_.m_135370_(SHAKE_TIME);
        }
    }

    public boolean forceRender() {
        return (Boolean)this.f_19804_.m_135370_(FORCE_RENDER);
    }

    public void setForceRender(boolean flag) {
        this.f_19804_.m_135381_(FORCE_RENDER, (Object)flag);
    }

    public void setSize(float x, float y, float z) {
        this.f_19804_.m_135381_(X_SIZE, (Object)Float.valueOf(x));
        this.f_19804_.m_135381_(Y_SIZE, (Object)Float.valueOf(y));
        this.f_19804_.m_135381_(Z_SIZE, (Object)Float.valueOf(z));
        this.m_6210_();
    }

    protected AABB m_142242_() {
        float x = ((Float)this.f_19804_.m_135370_(X_SIZE)).floatValue();
        float y = ((Float)this.f_19804_.m_135370_(Y_SIZE)).floatValue();
        float z = ((Float)this.f_19804_.m_135370_(Z_SIZE)).floatValue();
        return new AABB(this.m_20185_() - (double)(x / 2.0f), this.m_20186_(), this.m_20189_() - (double)z / 2.0, this.m_20185_() + (double)x / 2.0, this.m_20186_() + (double)y, this.m_20189_() + (double)z / 2.0);
    }

    public void setShakeTime(int time) {
        this.shakeTime = time;
        this.f_19804_.m_135381_(SHAKE_TIME, (Object)time);
    }

    public int getShakeTime() {
        return this.shakeTime;
    }

    public void setSink(int sink) {
        this.sink = sink;
    }

    public int getSink() {
        return this.sink;
    }

    public void setAntiStacking(boolean flag) {
        this.antiStacking = flag;
    }

    public boolean antiStacking() {
        return this.antiStacking;
    }

    @Nullable
    public BlockPos getFadePos() {
        return ((Optional)this.f_19804_.m_135370_(FADE_POINT)).orElse(null);
    }

    public void setFadePos(@Nullable BlockPos pos) {
        this.f_19804_.m_135381_(FADE_POINT, Optional.ofNullable(pos));
    }

    public void setFadeStrength(float strength) {
        this.f_19804_.m_135381_(FADE_STRENGTH, (Object)Float.valueOf(strength));
    }

    public float getFadeStrength() {
        return ((Float)this.f_19804_.m_135370_(FADE_STRENGTH)).floatValue();
    }

    public int getFadeDistanceOffset() {
        return (Integer)this.f_19804_.m_135370_(FADE_DISTANCE_OFFSET);
    }

    public void setFadeDistanceOffset(int offset) {
        this.f_19804_.m_135381_(FADE_DISTANCE_OFFSET, (Object)offset);
    }

    public void setShouldCrumble(boolean flag) {
        this.shouldCrumble = flag;
    }

    public boolean shouldCrumble() {
        return this.shouldCrumble;
    }

    public void setShouldntCountToConsumedEntities(boolean flag) {
        this.shouldntCountToConsumedEntities = flag;
    }

    public boolean shouldntCountToConsumedEntities() {
        return this.shouldntCountToConsumedEntities;
    }

    public boolean createdFromTractorBeam() {
        return this.createdFromBeam;
    }

    public void setCreatedFromTractorBeam(boolean flag) {
        this.createdFromBeam = flag;
    }

    public boolean createdFromFallingBlock() {
        return this.createdFromFallingBlock;
    }

    public void setCreatedFromFallingBlock(boolean flag) {
        this.createdFromFallingBlock = flag;
    }

    public int getHeadCreatedFrom() {
        return this.headCreatedFrom;
    }

    public void setHeadCreatedFrom(int head) {
        this.headCreatedFrom = head;
    }

    public float getClusterXRot(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.xClusterRotO, (float)this.xClusterRot);
    }

    public float getClusterYRot(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.yClusterRotO, (float)this.yClusterRot);
    }

    public void setTractorBeamDistanceThreshold(double distance) {
        this.tractorBeamDistanceThreshold = distance;
    }

    public double getTractorBeamDistanceThreshold() {
        return this.tractorBeamDistanceThreshold;
    }

    public boolean m_6072_() {
        return false;
    }

    protected boolean m_20073_() {
        this.f_19799_.clear();
        this.forgeFluidTypeHeight.clear();
        return this.isInFluidType();
    }

    protected Entity.MovementEmission m_142319_() {
        return Entity.MovementEmission.NONE;
    }

    public void m_5844_() {
    }

    public boolean m_5843_() {
        return false;
    }

    public void m_6075_() {
        this.m_9236_().m_46473_().m_6180_("entityBaseTick");
        this.f_19867_ = this.f_19787_;
        this.f_19860_ = this.m_146909_();
        this.f_19859_ = this.m_146908_();
        this.m_146871_();
        this.f_19803_ = false;
        this.m_9236_().m_46473_().m_7238_();
    }
}

