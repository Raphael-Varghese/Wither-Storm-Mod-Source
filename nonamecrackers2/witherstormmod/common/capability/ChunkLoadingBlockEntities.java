/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.NbtUtils
 *  net.minecraft.nbt.Tag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.block.entity.BlockEntity
 */
package nonamecrackers2.witherstormmod.common.capability;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ChunkLoadingBlockEntities {
    private final ServerLevel level;
    private List<BlockPos> loadingEntities = Lists.newArrayList();

    public ChunkLoadingBlockEntities(ServerLevel level) {
        this.level = level;
    }

    public void tick() {
        Iterator<BlockPos> loadingEntities = this.loadingEntities.iterator();
        while (loadingEntities.hasNext()) {
            BlockPos pos = loadingEntities.next();
            if (!this.level.m_46749_(pos)) continue;
            BlockEntity entity = this.level.m_7702_(pos);
            ChunkPos chunk = new ChunkPos(pos);
            if (entity != null && !entity.m_58901_()) {
                if (this.level.m_8902_().contains(chunk.m_45588_())) continue;
                this.level.m_8602_(chunk.f_45578_, chunk.f_45579_, true);
                continue;
            }
            this.level.m_8602_(chunk.f_45578_, chunk.f_45579_, false);
            loadingEntities.remove();
        }
    }

    public CompoundTag write() {
        CompoundTag tag = new CompoundTag();
        ListTag list = new ListTag();
        for (BlockPos pos : this.loadingEntities) {
            list.add((Object)NbtUtils.m_129224_((BlockPos)pos));
        }
        tag.m_128365_("LoadedEntities", (Tag)list);
        return tag;
    }

    public void read(CompoundTag tag) {
        ListTag list = tag.m_128437_("LoadedEntities", 10);
        ArrayList loadingEntities = Lists.newArrayList();
        for (int i = 0; i < list.size(); ++i) {
            loadingEntities.add(NbtUtils.m_129239_((CompoundTag)list.m_128728_(i)));
        }
        this.loadingEntities = loadingEntities;
    }

    public void add(BlockPos pos) {
        this.loadingEntities.add(pos);
    }

    public void remove(BlockPos pos) {
        this.loadingEntities.remove(pos);
    }
}

