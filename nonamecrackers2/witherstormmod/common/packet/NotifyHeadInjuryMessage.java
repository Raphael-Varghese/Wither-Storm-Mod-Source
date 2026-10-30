/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.fml.DistExecutor
 *  net.minecraftforge.network.NetworkEvent$Context
 *  nonamecrackers2.crackerslib.common.packet.Packet
 */
package nonamecrackers2.witherstormmod.common.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import nonamecrackers2.crackerslib.common.packet.Packet;
import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class NotifyHeadInjuryMessage
extends Packet {
    private int entityId;
    private byte head;

    public NotifyHeadInjuryMessage(WitherStormEntity entity, int head) {
        super(true);
        this.entityId = entity.m_19879_();
        this.head = (byte)head;
    }

    public NotifyHeadInjuryMessage() {
        super(false);
    }

    public int getEntityID() {
        return this.entityId;
    }

    public byte getHead() {
        return this.head;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.entityId);
        buffer.writeByte((int)this.head);
    }

    public void decode(FriendlyByteBuf buffer) {
        this.entityId = buffer.readInt();
        this.head = buffer.readByte();
    }

    public Runnable getProcessor(NetworkEvent.Context context) {
        return () -> DistExecutor.unsafeRunWhenOn((Dist)Dist.CLIENT, () -> () -> WitherStormModMessageHandlerClient.processNotifyHeadInjuryMessage(this));
    }

    public String toString() {
        return "NotifyHeadInjuryMessage[entityId=" + this.entityId + ", head=" + this.head + "]";
    }
}

