/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.HolderGetter
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.NbtUtils
 *  net.minecraft.nbt.Tag
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class WitherStormModNBTUtil {
    public static ListTag writeBlockStatePosMap(Map<BlockPos, BlockState> map) {
        ListTag list = new ListTag();
        for (Map.Entry<BlockPos, BlockState> entry : map.entrySet()) {
            CompoundTag stateCompound = NbtUtils.m_129202_((BlockState)entry.getValue());
            stateCompound.m_128365_("RelativePos", (Tag)NbtUtils.m_129224_((BlockPos)entry.getKey()));
            list.add((Object)stateCompound);
        }
        return list;
    }

    public static ListTag writeCompoundList(List<CompoundTag> compounds) {
        ListTag list = new ListTag();
        for (int i = 0; i < compounds.size(); ++i) {
            list.add((Object)((Tag)compounds.get(i)));
        }
        return list;
    }

    public static CompoundTag writeChunkPosList(List<ChunkPos> chunks) {
        CompoundTag compound = new CompoundTag();
        for (int i = 0; i < chunks.size(); ++i) {
            CompoundTag chunkCompound = new CompoundTag();
            ChunkPos pos = chunks.get(i);
            chunkCompound.m_128405_("x", pos.f_45578_);
            chunkCompound.m_128405_("z", pos.f_45579_);
            compound.m_128365_("Chunk" + i, (Tag)chunkCompound);
        }
        return compound;
    }

    public static Map<BlockPos, BlockState> readBlockStatePosMap(HolderGetter<Block> getter, ListTag list) {
        HashMap<BlockPos, BlockState> blocks = new HashMap<BlockPos, BlockState>();
        for (int i = 0; i < list.size(); ++i) {
            CompoundTag stateCompound = list.m_128728_(i);
            BlockState state = NbtUtils.m_247651_(getter, (CompoundTag)stateCompound);
            BlockPos pos = NbtUtils.m_129239_((CompoundTag)stateCompound.m_128469_("RelativePos"));
            blocks.put(pos, state);
        }
        return blocks;
    }

    public static List<CompoundTag> readCompoundList(ListTag list) {
        ArrayList<CompoundTag> compounds = new ArrayList<CompoundTag>();
        for (int i = 0; i < list.size(); ++i) {
            compounds.add(list.m_128728_(i));
        }
        return compounds;
    }

    public static List<ChunkPos> readChunkPosList(CompoundTag compound) {
        ArrayList<ChunkPos> list = new ArrayList<ChunkPos>();
        for (int i = 0; i < compound.m_128431_().size(); ++i) {
            if (!compound.m_128441_("Chunk" + i)) continue;
            CompoundTag chunkCompound = compound.m_128469_("Chunk" + i);
            ChunkPos pos = new ChunkPos(chunkCompound.m_128451_("x"), chunkCompound.m_128451_("z"));
            list.add(pos);
        }
        return list;
    }

    public static CompoundTag writeVector2f(Vec2 vector) {
        CompoundTag compound = new CompoundTag();
        compound.m_128350_("x", vector.f_82470_);
        compound.m_128350_("y", vector.f_82471_);
        return compound;
    }

    public static Vec2 readVector2f(CompoundTag compound) {
        return new Vec2(compound.m_128457_("x"), compound.m_128457_("y"));
    }

    public static CompoundTag writeChunkPos(ChunkPos pos) {
        CompoundTag compound = new CompoundTag();
        compound.m_128405_("x", pos.f_45578_);
        compound.m_128405_("z", pos.f_45579_);
        return compound;
    }

    public static ChunkPos readChunkPos(CompoundTag compound) {
        return new ChunkPos(compound.m_128451_("x"), compound.m_128451_("z"));
    }

    public static CompoundTag writeVector3d(Vec3 vector) {
        CompoundTag compound = new CompoundTag();
        compound.m_128347_("X", vector.m_7096_());
        compound.m_128347_("Y", vector.m_7098_());
        compound.m_128347_("Z", vector.m_7094_());
        return compound;
    }

    public static Vec3 readVector3d(CompoundTag compound) {
        return new Vec3(compound.m_128459_("X"), compound.m_128459_("Y"), compound.m_128459_("Z"));
    }
}

