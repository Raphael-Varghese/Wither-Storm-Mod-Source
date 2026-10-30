/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.world.InteractionHand
 *  net.minecraftforge.network.NetworkEvent$Context
 *  nonamecrackers2.crackerslib.common.packet.Packet
 */
package nonamecrackers2.witherstormmod.common.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.network.NetworkEvent;
import nonamecrackers2.crackerslib.common.packet.Packet;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.packet.WitherStormModMessageHandlerServer;

public class InjureHeadMessage
extends Packet {
    private int entityId;
    private byte head;
    private InteractionHand hand;

    public InjureHeadMessage(WitherStormEntity entity, int head, InteractionHand hand) {
        super(true);
        this.entityId = entity.m_19879_();
        this.head = (byte)head;
        this.hand = hand;
    }

    public InjureHeadMessage() {
        super(false);
    }

    public int getEntityID() {
        return this.entityId;
    }

    public byte getHead() {
        return this.head;
    }

    public InteractionHand getHand() {
        return this.hand;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.entityId);
        buffer.writeByte((int)this.head);
        buffer.m_130068_((Enum)this.hand);
    }

    public void decode(FriendlyByteBuf buffer) throws IllegalArgumentException, IndexOutOfBoundsException {
        this.entityId = buffer.readInt();
        this.head = buffer.readByte();
        this.hand = (InteractionHand)buffer.m_130066_(InteractionHand.class);
    }

    public Runnable getProcessor(NetworkEvent.Context context) {
        return () -> WitherStormModMessageHandlerServer.processInjureHeadMessage(this, context.getSender());
    }

    public String toString() {
        return "InjureHeadMessage[entityId=" + this.entityId + ", head=" + this.head + ", hand=" + this.hand + "]";
    }
}

