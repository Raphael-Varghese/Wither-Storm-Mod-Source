/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.network.syncher.SynchedEntityData$DataItem
 *  net.minecraft.network.syncher.SynchedEntityData$DataValue
 *  nonamecrackers2.crackerslib.common.packet.Packet
 */
package nonamecrackers2.witherstormmod.common.packet;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.SynchedEntityData;
import nonamecrackers2.crackerslib.common.packet.Packet;
import nonamecrackers2.witherstormmod.mixin.IMixinSynchedEntityData;

public abstract class DistantRendererMessage
extends Packet {
    private List<Integer> applicable;

    protected DistantRendererMessage(boolean valid, List<Integer> applicable) {
        super(valid);
        this.applicable = applicable;
    }

    public void decode(FriendlyByteBuf buffer) throws IllegalArgumentException, IndexOutOfBoundsException {
        int length = buffer.m_130242_();
        ArrayList applicable = Lists.newArrayList();
        for (int i = 0; i < length; ++i) {
            applicable.add(buffer.m_130242_());
        }
        this.applicable = applicable;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.m_130130_(this.applicable.size());
        this.applicable.forEach(arg_0 -> ((FriendlyByteBuf)buffer).m_130130_(arg_0));
    }

    public List<Integer> getApplicable() {
        return this.applicable;
    }

    protected static List<SynchedEntityData.DataValue<?>> getPackedData(SynchedEntityData data) {
        IMixinSynchedEntityData mixinData = (IMixinSynchedEntityData)data;
        ArrayList values = Lists.newArrayList();
        mixinData.getLock().readLock().lock();
        for (SynchedEntityData.DataItem item : mixinData.getItemsById().values()) {
            values.add(item.m_253123_());
        }
        mixinData.getLock().readLock().unlock();
        return values;
    }
}

