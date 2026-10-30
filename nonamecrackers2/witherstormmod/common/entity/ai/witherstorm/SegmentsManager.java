/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Position
 *  net.minecraft.server.level.FullChunkStatus
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.chunk.ChunkAccess
 *  net.minecraft.world.level.chunk.ChunkStatus
 *  net.minecraft.world.level.chunk.LevelChunk
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm;

import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.server.level.FullChunkStatus;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;

public class SegmentsManager {
    protected final WitherStormEntity owner;
    protected final WitherStormSegmentEntity[] segments;

    public SegmentsManager(WitherStormEntity owner) {
        this.owner = owner;
        this.segments = new WitherStormSegmentEntity[2];
    }

    public void createSegments() {
        for (int i = 0; i < this.segments.length; ++i) {
            this.createSegment(i);
        }
    }

    public void createSegment(int index) {
        BlockPos lastKnown;
        WitherStormSegmentEntity existing;
        if (this.owner.m_6084_() && ((existing = this.segments[index]) == null || existing.m_146911_() != null && existing.m_146911_().m_146965_()) && this.posLoaded(lastKnown = BlockPos.m_274561_((double)this.owner.getDesiredSegmentX(index + 1), (double)this.owner.getDesiredSegmentY(index + 1), (double)this.owner.getDesiredSegmentZ(index + 1)))) {
            WitherStormSegmentEntity segment = new WitherStormSegmentEntity(this.owner);
            segment.m_6863_(this.owner.m_6162_());
            segment.m_21557_(this.owner.m_21525_());
            if (this.owner.m_21532_()) {
                segment.m_21530_();
            }
            segment.m_20331_(this.owner.m_20147_());
            segment.m_20225_(this.owner.m_20067_());
            segment.m_20242_(this.owner.m_20068_());
            segment.setOtherHeadsDisabled(this.owner.areOtherHeadsDisabled());
            segment.setMirrored(index % 2 != 0);
            segment.setPhase(this.owner.getPhase());
            segment.m_5618_(this.owner.f_20883_);
            this.setSegment(segment, index);
        }
    }

    public void removeSegments() {
        for (int i = 0; i < this.segments.length; ++i) {
            WitherStormSegmentEntity segment = this.segments[i];
            if (segment == null || segment.m_213877_()) continue;
            this.segments[i].getTrackedEntities().clearAndMakeAllFall();
            this.segments[i].m_146870_();
        }
    }

    public void readdSegments() {
        for (int i = 0; i < this.segments.length; ++i) {
            WitherStormSegmentEntity segment = this.segments[i];
            if (segment != null && !segment.isAddedToWorld()) {
                Vec3 desiredPos;
                if (segment.m_146911_() == null || !this.posLoaded(desiredPos = new Vec3(this.owner.getDesiredSegmentX(segment.isMirrored() ? 1 : 2), this.owner.getDesiredSegmentY(segment.isMirrored() ? 1 : 2), this.owner.getDesiredSegmentZ(segment.isMirrored() ? 1 : 2)))) continue;
                segment.revive();
                segment.regatherCapabilities();
                segment.m_146884_(desiredPos);
                segment.m_6863_(this.owner.m_6162_());
                segment.m_21557_(this.owner.m_21525_());
                if (this.owner.m_21532_()) {
                    segment.m_21530_();
                }
                segment.m_20331_(this.owner.m_20147_());
                segment.m_20225_(this.owner.m_20067_());
                segment.m_20242_(this.owner.m_20068_());
                segment.setOtherHeadsDisabled(this.owner.areOtherHeadsDisabled());
                segment.setMirrored(i % 2 != 0);
                segment.setPhase(this.owner.getPhase());
                this.addSegment(i);
                continue;
            }
            if (segment != null && segment.m_6084_()) continue;
            this.createSegment(i);
            this.addSegment(i);
        }
    }

    public void addSegments() {
        for (int i = 0; i < this.segments.length; ++i) {
            this.addSegment(i);
        }
    }

    public void addSegment(int index) {
        Vec3 desiredPos;
        WitherStormSegmentEntity segment;
        if (this.owner.isAddedToWorld() && this.owner.m_6084_() && (segment = this.segments[index]) != null && !segment.isAddedToWorld() && (segment.m_146911_() != null && !segment.m_146911_().m_146965_() || segment.m_146911_() == null) && this.posLoaded(desiredPos = new Vec3(this.owner.getDesiredSegmentX(segment.isMirrored() ? 1 : 2), this.owner.getDesiredSegmentY(segment.isMirrored() ? 1 : 2), this.owner.getDesiredSegmentZ(segment.isMirrored() ? 1 : 2)))) {
            segment.m_146884_(desiredPos);
            segment.m_5618_(this.owner.f_20883_);
            this.owner.m_9236_().m_7967_((Entity)segment);
            segment.setPhase(this.owner.getPhase());
            segment.getPlayDeadManager().setStateRaw(this.owner.getPlayDeadManager().getState());
            segment.getPlayDeadManager().setTickAmount(this.owner.getPlayDeadManager().getTicks());
            for (WitherStormHead head : segment.getHeadManager().getHeads()) {
                if (segment.isPlayingDead()) {
                    head.setRoar(true);
                    continue;
                }
                head.doRoar(false);
            }
        }
    }

    public void killSegments() {
        for (int i = 0; i < this.segments.length; ++i) {
            WitherStormSegmentEntity segment = this.segments[i];
            if (segment == null || !segment.m_6084_()) continue;
            segment.m_6469_(segment.m_269291_().m_269341_(), Float.MAX_VALUE);
        }
    }

    public WitherStormSegmentEntity[] getSegments() {
        return this.segments;
    }

    public void setSegment(@Nullable WitherStormSegmentEntity entity, int index) {
        this.segments[index] = entity;
    }

    public void findSegments(ServerLevel world) {
        block0: for (int i = 0; i < this.segments.length; ++i) {
            WitherStormSegmentEntity old = this.segments[i];
            if (old == null || old.m_146911_() == null || old.m_146911_().m_146965_()) continue;
            for (Entity entity : world.m_8583_()) {
                if (!old.m_20148_().equals(entity.m_20148_()) || !(entity instanceof WitherStormSegmentEntity)) continue;
                WitherStormSegmentEntity segment = (WitherStormSegmentEntity)entity;
                this.setSegment(segment, i);
                continue block0;
            }
        }
        for (Entity entity : world.m_8583_()) {
            if (!(entity instanceof WitherStormSegmentEntity)) continue;
            WitherStormSegmentEntity segment = (WitherStormSegmentEntity)entity;
            UUID parent = segment.getParentUUID();
            if (!this.owner.m_20148_().equals(parent)) continue;
            int index = segment.isMirrored() ? 1 : 0;
            WitherStormSegmentEntity existing = this.segments[index];
            if (existing != null) {
                if (segment.m_20148_().equals(existing.m_20148_())) continue;
                if (segment.getTimeWithParent() > existing.getTimeWithParent()) {
                    existing.m_146870_();
                    this.setSegment(segment, index);
                    continue;
                }
                if (existing.getTimeWithParent() <= segment.getTimeWithParent()) continue;
                segment.m_146870_();
                this.setSegment(existing, index);
                continue;
            }
            this.setSegment(segment, index);
        }
    }

    private boolean posLoaded(Vec3 pos) {
        return this.posLoaded(BlockPos.m_274446_((Position)pos));
    }

    private boolean posLoaded(BlockPos pos) {
        ChunkPos chunkPos = new ChunkPos(pos);
        ChunkAccess chunk = this.owner.m_9236_().m_6522_(chunkPos.f_45578_, chunkPos.f_45579_, ChunkStatus.f_62326_, false);
        if (!(chunk instanceof LevelChunk)) {
            return false;
        }
        FullChunkStatus type = ((LevelChunk)chunk).m_287138_();
        return type.m_287205_(FullChunkStatus.ENTITY_TICKING);
    }
}

