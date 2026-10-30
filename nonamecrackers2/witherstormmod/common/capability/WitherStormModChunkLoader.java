/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.ibm.icu.impl.locale.XCldrStub$ImmutableMap
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.TicketType
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraftforge.event.TickEvent$LevelTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package nonamecrackers2.witherstormmod.common.capability;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ibm.icu.impl.locale.XCldrStub;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.ChunkLoader;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.util.WitherStormModNBTUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WitherStormModChunkLoader {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final TicketType<ChunkPos> WITHER_STORM = TicketType.m_9462_((String)"witherstormmod:wither_storm", Comparator.comparingLong(ChunkPos::m_45588_));
    public static final TicketType<ChunkPos> LOAD = TicketType.m_9465_((String)"witherstormmod:load", Comparator.comparingLong(ChunkPos::m_45588_), (int)100);
    private final ServerLevel level;
    private final Map<UUID, Instance> instances = Maps.newHashMap();
    private List<ChunkPos> lastKnownPositions = Lists.newArrayList();
    private boolean loadedLastKnown;

    public WitherStormModChunkLoader(ServerLevel level) {
        this.level = level;
    }

    @Nullable
    public Instance getInstance(UUID id) {
        return this.instances.get(id);
    }

    public Map<UUID, Instance> getInstances() {
        return XCldrStub.ImmutableMap.copyOf(this.instances);
    }

    public boolean hasChunkLoaders() {
        return !this.instances.isEmpty();
    }

    public void refreshAllLoaders() {
        for (Instance instance : this.instances.values()) {
            instance.unload();
            instance.load();
            LOGGER.debug("Refreshed chunk loaders");
        }
    }

    public void tick() {
        if (this.hasChunkLoaders()) {
            this.level.m_8886_();
        }
        if (!this.loadedLastKnown) {
            int count = 0;
            for (ChunkPos pos : this.lastKnownPositions) {
                ++count;
                this.level.m_7726_().m_8387_(LOAD, pos, 2, (Object)pos);
            }
            LOGGER.debug("Loaded {} chunks for initial loading sequence in {}", (Object)count, (Object)this.level.m_46472_());
            this.loadedLastKnown = true;
        }
        for (Entity entity : this.level.m_8583_()) {
            ChunkLoader loader;
            if (!(entity instanceof ChunkLoader) || !(loader = (ChunkLoader)entity).shouldLoad()) continue;
            Instance instance = this.instances.computeIfAbsent(entity.m_20148_(), id -> {
                LOGGER.debug("A new chunk loading entity has entered the world: {}", (Object)entity);
                ChunkPos pos = entity.m_146902_();
                return new Instance(loader, pos, loader.loadRadius());
            });
            instance.updateCurrentPosition(entity.m_146902_());
        }
        Iterator<Map.Entry<UUID, Instance>> iterator = this.instances.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, Instance> entry = iterator.next();
            Instance instance = entry.getValue();
            if (instance.loader.isStillValidForChunkLoading()) {
                if (((Boolean)WitherStormModConfig.SERVER.shouldChunkLoadWhenNoPlayers.get()).booleanValue() || this.level.m_7654_().m_7416_() > 0) {
                    if (!instance.checkAndClearNeedsInit()) continue;
                    instance.load();
                    continue;
                }
                if (instance.needsInit) continue;
                instance.unload();
                instance.needsInit = true;
                continue;
            }
            instance.unload();
            iterator.remove();
        }
    }

    public CompoundTag write() {
        CompoundTag tag = new CompoundTag();
        if (!this.instances.isEmpty()) {
            tag.m_128365_("LastKnownPositions", (Tag)WitherStormModNBTUtil.writeChunkPosList(this.instances.values().stream().map(Instance::getPos).toList()));
        }
        return tag;
    }

    public void read(CompoundTag tag) {
        if (tag.m_128441_("LastKnownPositions")) {
            this.lastKnownPositions = WitherStormModNBTUtil.readChunkPosList(tag.m_128469_("LastKnownPositions"));
        }
    }

    public class Instance {
        private ChunkPos current;
        private int radius;
        private boolean needsInit = true;
        private final ChunkLoader loader;

        public Instance(ChunkLoader loader, ChunkPos current, int radius) {
            this.loader = loader;
            this.current = current;
            this.radius = radius;
        }

        public int getRadius() {
            return this.radius;
        }

        public ChunkPos getPos() {
            return this.current;
        }

        public void updateCurrentPosition(ChunkPos pos) {
            if (!this.current.equals((Object)pos)) {
                this.unload();
                this.current = pos;
                this.load();
            }
        }

        public void unload() {
            LOGGER.debug("Unloading chunk {}", (Object)this.current);
            WitherStormModChunkLoader.this.level.m_7726_().removeRegionTicket(WITHER_STORM, this.current, this.radius, (Object)this.current, true);
        }

        public void load() {
            LOGGER.debug("Loading chunk {} with radius {}", (Object)this.current, (Object)this.radius);
            WitherStormModChunkLoader.this.level.m_7726_().addRegionTicket(WITHER_STORM, this.current, this.radius, (Object)this.current, true);
        }

        public boolean checkAndClearNeedsInit() {
            boolean flag = this.needsInit;
            if (flag) {
                this.needsInit = false;
            }
            return flag;
        }
    }

    public static class Events {
        private Events() {
        }

        @SubscribeEvent
        public static void onLevelTick(TickEvent.LevelTickEvent event) {
            if (event.phase == TickEvent.Phase.START) {
                event.level.getCapability(WitherStormModCapabilities.CHUNK_LOADER).ifPresent(WitherStormModChunkLoader::tick);
            }
        }
    }
}

