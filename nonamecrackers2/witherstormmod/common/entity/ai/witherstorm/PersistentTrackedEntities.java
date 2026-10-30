/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.NbtUtils
 *  net.minecraft.nbt.Tag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 */
package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;

public class PersistentTrackedEntities {
    private static final int TICKS_TILL_REMOVE_SAVED = 80;
    private List<Entity> currentTrackedEntities = Lists.newArrayList();
    private List<UUID> savedTrackedEntities = Lists.newArrayList();
    private int tickCount;
    private boolean hasLoadedSaved;

    public void tick(ServerLevel level) {
        ++this.tickCount;
        Iterator<UUID> iterator = this.savedTrackedEntities.iterator();
        while (iterator.hasNext()) {
            UUID id = iterator.next();
            Entity entity = level.m_8791_(id);
            if (entity != null && !entity.m_213877_()) {
                this.currentTrackedEntities.add(entity);
                iterator.remove();
                continue;
            }
            if (this.tickCount <= 80) continue;
            iterator.remove();
        }
    }

    public List<Entity> getCurrentTrackedEntities() {
        return ImmutableList.copyOf(this.currentTrackedEntities);
    }

    public void trackEntityToConsume(Entity entity) {
        this.currentTrackedEntities.add(entity);
    }

    public void stopTrackingEntity(Entity entity) {
        this.currentTrackedEntities.remove(entity);
    }

    public boolean contains(Entity entity) {
        return this.currentTrackedEntities.contains(entity);
    }

    public void clearAll() {
        this.currentTrackedEntities.clear();
    }

    public void clearAndMakeAllFall() {
        int size = this.currentTrackedEntities.size();
        for (int i = 0; i < size; ++i) {
            Entity tracked = this.currentTrackedEntities.get(i);
            tracked.m_20242_(false);
            if (!(tracked instanceof BlockClusterEntity)) continue;
            ((BlockClusterEntity)tracked).setPhysics(true);
        }
        this.currentTrackedEntities.clear();
    }

    public void destroyAllClusters() {
        Iterator<Entity> iterator = this.currentTrackedEntities.iterator();
        while (iterator.hasNext()) {
            Entity tracked = iterator.next();
            if (!(tracked instanceof BlockClusterEntity)) continue;
            iterator.remove();
            tracked.m_146870_();
        }
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        ListTag list = new ListTag();
        ArrayList toSave = Lists.newArrayList(this.savedTrackedEntities);
        toSave.addAll(this.currentTrackedEntities.stream().map(Entity::m_20148_).toList());
        for (UUID id : toSave) {
            list.add((Object)NbtUtils.m_129226_((UUID)id));
        }
        tag.m_128365_("Entities", (Tag)list);
        return tag;
    }

    public void read(CompoundTag tag) {
        if (!this.hasLoadedSaved) {
            this.savedTrackedEntities.clear();
            ListTag list = tag.m_128437_("Entities", 11);
            for (int i = 0; i < list.size(); ++i) {
                UUID id = NbtUtils.m_129233_((Tag)list.get(i));
                this.savedTrackedEntities.add(id);
            }
            this.hasLoadedSaved = true;
        }
    }
}

