/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.level.TicketType
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.levelgen.structure.StructurePiece
 *  net.minecraft.world.level.levelgen.structure.StructureStart
 */
package nonamecrackers2.witherstormmod.common.capability;

import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModStructureTags;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;
import nonamecrackers2.witherstormmod.common.world.gen.feature.structure.StormSpawnPlatformStructure;

public class WitherStormAutoSpawner {
    private static final TicketType<BlockPos> INITIAL_SPAWN = TicketType.m_9465_((String)"initial_spawn", Vec3i::compareTo, (int)100);
    private final ServerLevel level;
    private int tickCount;
    private boolean hasSpawnedWitherStorm;

    public WitherStormAutoSpawner(ServerLevel level) {
        this.level = level;
    }

    public void tick() {
        if (((Boolean)WitherStormModConfig.COMMON.autoSpawnWitherStorm.get()).booleanValue()) {
            ++this.tickCount;
            int maxTime = Math.max((Integer)WitherStormModConfig.COMMON.autoSpawnTime.get() * 60 * 20, 100);
            if (!this.hasSpawnedWitherStorm && this.tickCount > maxTime) {
                this.hasSpawnedWitherStorm = true;
                Pair<BlockPos, StructureStart> structure = WorldUtil.findNearestMapStructure(this.level, WitherStormModStructureTags.STORM_SPAWN_PLATFORMS, BlockPos.f_121853_, 100, false);
                if (structure != null) {
                    BlockPos pos = (BlockPos)structure.getFirst();
                    this.level.m_46865_(pos);
                    StructureStart start = (StructureStart)structure.getSecond();
                    for (StructurePiece piece : start.m_73602_()) {
                        StormSpawnPlatformStructure.Piece spawnPiece;
                        if (!(piece instanceof StormSpawnPlatformStructure.Piece) || (spawnPiece = (StormSpawnPlatformStructure.Piece)piece).getSpawnPos() == null) continue;
                        pos = spawnPiece.getSpawnPos();
                        break;
                    }
                    this.level.m_7726_().addRegionTicket(INITIAL_SPAWN, new ChunkPos(pos), 2, (Object)pos, true);
                    WitherStormEntity storm = (WitherStormEntity)((EntityType)WitherStormModEntityTypes.WITHER_STORM.get()).m_20615_((Level)this.level);
                    storm.m_6027_((double)pos.m_123341_() + 0.5, pos.m_123342_(), (double)pos.m_123343_() + 0.5);
                    storm.makeInvulnerable();
                    storm.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), 4.0f, 1.0f);
                    for (ServerPlayer player : this.level.m_8795_(EntitySelector.f_20408_)) {
                        CriteriaTriggers.f_10580_.m_68256_(player, (Entity)storm);
                    }
                    this.level.m_7967_((Entity)storm);
                }
            }
        } else {
            this.hasSpawnedWitherStorm = true;
        }
    }

    public void read(CompoundTag tag) {
        this.tickCount = tag.m_128451_("TickCount");
        this.hasSpawnedWitherStorm = tag.m_128471_("HasSpawnedWitherStorm");
    }

    public CompoundTag write() {
        CompoundTag tag = new CompoundTag();
        tag.m_128405_("TickCount", this.tickCount);
        tag.m_128379_("HasSpawnedWitherStorm", this.hasSpawnedWitherStorm);
        return tag;
    }
}

