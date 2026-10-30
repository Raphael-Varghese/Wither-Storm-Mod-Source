/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  net.minecraft.ChatFormatting
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerPlayer
 */
package nonamecrackers2.witherstormmod.common.util;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class EvolutionProfiler {
    private final Int2ObjectMap<Integer> ticksToEvolve = new Int2ObjectOpenHashMap();
    private final List<Integer> consumedEntitiesPerSeconds = Lists.newArrayList();
    private double consumedEntitiesPerSecond;
    private int ticksSinceLastPhase;
    private int lastConsumedEntities;
    private boolean isProfiling;

    public void tick(WitherStormEntity storm) {
        ++this.ticksSinceLastPhase;
        MinecraftServer server = storm.m_9236_().m_7654_();
        if (this.ticksToEvolve.containsKey(7)) {
            for (Int2ObjectMap.Entry entry : this.ticksToEvolve.int2ObjectEntrySet()) {
                int phase = entry.getIntKey();
                int ticks = (Integer)entry.getValue();
                MutableComponent message = Component.m_237113_((String)(ticks + " ticks to evolve from " + (phase - 1) + " to " + phase)).m_130940_(ChatFormatting.GOLD);
                for (ServerPlayer player : server.m_6846_().m_11314_()) {
                    player.m_213846_((Component)message);
                }
            }
            this.isProfiling = false;
        }
        if (this.ticksSinceLastPhase % 20 == 0) {
            this.consumedEntitiesPerSeconds.add(storm.getConsumedEntities() - this.lastConsumedEntities);
            this.lastConsumedEntities = storm.getConsumedEntities();
            int size = this.consumedEntitiesPerSeconds.size();
            int sum = 0;
            for (int i : this.consumedEntitiesPerSeconds) {
                sum += i;
            }
            this.consumedEntitiesPerSecond = (double)sum / (double)size;
            if (this.consumedEntitiesPerSeconds.size() > 60) {
                this.consumedEntitiesPerSeconds.clear();
                for (ServerPlayer player : server.m_6846_().m_11314_()) {
                    player.m_213846_((Component)Component.m_237113_((String)("Consumed entities per second for phase " + storm.getPhase() + ": " + this.consumedEntitiesPerSecond)).m_130940_(ChatFormatting.YELLOW));
                }
            }
        }
    }

    public void onEvolve(WitherStormEntity storm) {
        int phase = storm.getPhase();
        this.ticksToEvolve.put(phase, (Object)this.ticksSinceLastPhase);
        MutableComponent message = Component.m_237113_((String)("Phase " + (phase - 1) + " to " + phase + " took " + this.ticksSinceLastPhase + " ticks")).m_130940_(ChatFormatting.GOLD);
        for (ServerPlayer player : storm.m_9236_().m_7654_().m_6846_().m_11314_()) {
            player.m_213846_((Component)message);
        }
        this.ticksSinceLastPhase = 0;
    }

    public void begin() {
        this.isProfiling = true;
        this.ticksToEvolve.clear();
        this.ticksSinceLastPhase = 0;
        this.consumedEntitiesPerSeconds.clear();
        this.consumedEntitiesPerSecond = 0.0;
    }

    public boolean isProfiling() {
        return this.isProfiling;
    }

    public double getConsumedEntitiesPerSecond() {
        return this.consumedEntitiesPerSecond;
    }

    public void save(CompoundTag tag) {
        tag.m_128379_("IsProfiling", this.isProfiling);
        tag.m_128405_("TicksSinceLastPhase", this.ticksSinceLastPhase);
        ListTag list = new ListTag();
        for (Int2ObjectMap.Entry entry : this.ticksToEvolve.int2ObjectEntrySet()) {
            CompoundTag entryTag = new CompoundTag();
            entryTag.m_128405_("Phase", entry.getIntKey());
            entryTag.m_128405_("Ticks", ((Integer)entry.getValue()).intValue());
            list.add((Object)entryTag);
        }
        tag.m_128365_("TicksToEvolve", (Tag)list);
    }

    public void read(CompoundTag tag) {
        this.isProfiling = tag.m_128471_("IsProfiling");
        this.ticksSinceLastPhase = tag.m_128451_("TicksSinceLastPhase");
        ListTag list = tag.m_128437_("TicksToEvolve", 10);
        this.ticksToEvolve.clear();
        for (int i = 0; i < list.size(); ++i) {
            CompoundTag entryTag = list.m_128728_(i);
            this.ticksToEvolve.put(entryTag.m_128451_("Phase"), (Object)entryTag.m_128451_("Ticks"));
        }
    }
}

