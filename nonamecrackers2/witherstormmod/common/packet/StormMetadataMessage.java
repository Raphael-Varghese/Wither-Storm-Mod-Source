/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.network.syncher.SynchedEntityData$DataValue
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.fml.DistExecutor
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package nonamecrackers2.witherstormmod.common.packet;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
import nonamecrackers2.witherstormmod.common.packet.DistantRendererMessage;

public class StormMetadataMessage
extends DistantRendererMessage {
    private int entityId;
    private List<SynchedEntityData.DataValue<?>> packedItems;

    @Deprecated
    public StormMetadataMessage(List<Integer> applicable, int id, SynchedEntityData data) {
        super(true, applicable);
        this.entityId = id;
        this.packedItems = StormMetadataMessage.getPackedData(data);
    }

    public StormMetadataMessage(List<Integer> applicable, int id, List<SynchedEntityData.DataValue<?>> packedItems) {
        super(true, applicable);
        this.entityId = id;
        this.packedItems = packedItems;
    }

    public StormMetadataMessage() {
        super(false, Lists.newArrayList());
    }

    public int getEntityID() {
        return this.entityId;
    }

    public List<SynchedEntityData.DataValue<?>> getUnpackedItems() {
        return this.packedItems;
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        super.encode(buffer);
        buffer.m_130130_(this.entityId);
        for (SynchedEntityData.DataValue<?> value : this.packedItems) {
            value.m_252897_(buffer);
        }
        buffer.writeByte(255);
    }

    @Override
    public void decode(FriendlyByteBuf buffer) {
        short j;
        super.decode(buffer);
        this.entityId = buffer.m_130242_();
        ArrayList packedData = Lists.newArrayList();
        while ((j = buffer.readUnsignedByte()) != 255) {
            packedData.add(SynchedEntityData.DataValue.m_252860_((FriendlyByteBuf)buffer, (int)j));
        }
        this.packedItems = packedData;
    }

    public Runnable getProcessor(NetworkEvent.Context context) {
        return () -> DistExecutor.unsafeRunWhenOn((Dist)Dist.CLIENT, () -> () -> WitherStormModMessageHandlerClient.processStormMetadataMessage(this));
    }

    public String toString() {
        return "StormMetadataMessage[id=" + this.entityId + ", data=" + this.packedItems.toString() + "]";
    }
}

