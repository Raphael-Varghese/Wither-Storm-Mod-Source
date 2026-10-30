/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraftforge.network.NetworkEvent$Context
 *  nonamecrackers2.crackerslib.common.packet.Packet
 */
package nonamecrackers2.witherstormmod.common.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import nonamecrackers2.crackerslib.common.packet.Packet;
import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;

public class OnHeadAttackedMessage
extends Packet {
    private int entityId;
    private int headIndex;

    public OnHeadAttackedMessage(int entityId, int headIndex) {
        super(true);
        this.entityId = entityId;
        this.headIndex = headIndex;
    }

    public OnHeadAttackedMessage() {
        super(false);
    }

    protected void decode(FriendlyByteBuf buffer) {
        this.entityId = buffer.m_130242_();
        this.headIndex = buffer.m_130242_();
    }

    protected void encode(FriendlyByteBuf buffer) {
        buffer.m_130130_(this.entityId);
        buffer.m_130130_(this.headIndex);
    }

    public int getEntityId() {
        return this.entityId;
    }

    public int getHeadIndex() {
        return this.headIndex;
    }

    public Runnable getProcessor(NetworkEvent.Context context) {
        return OnHeadAttackedMessage.client(() -> WitherStormModMessageHandlerClient.processOnHeadAttackedMessage(this));
    }
}

