/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.fml.DistExecutor
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package nonamecrackers2.witherstormmod.common.packet;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.packet.DistantRendererMessage;

public class UpdateStormHeadLookMessage
extends DistantRendererMessage {
    private int entityId;
    private byte yHeadRot;

    @Deprecated
    public UpdateStormHeadLookMessage(List<Integer> applicable, WitherStormEntity entity, byte yHeadRot) {
        super(true, applicable);
        this.entityId = entity.m_19879_();
        this.yHeadRot = yHeadRot;
    }

    public UpdateStormHeadLookMessage(List<Integer> applicable, int id, byte yHeadRot) {
        super(true, applicable);
        this.entityId = id;
        this.yHeadRot = yHeadRot;
    }

    public UpdateStormHeadLookMessage() {
        super(false, Lists.newArrayList());
    }

    public int getEntityID() {
        return this.entityId;
    }

    public byte getYRot() {
        return this.yHeadRot;
    }

    @Override
    public void decode(FriendlyByteBuf buffer) {
        super.decode(buffer);
        this.entityId = buffer.m_130242_();
        this.yHeadRot = buffer.readByte();
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        super.encode(buffer);
        buffer.m_130130_(this.entityId);
        buffer.writeByte((int)this.yHeadRot);
    }

    public Runnable getProcessor(NetworkEvent.Context context) {
        return () -> DistExecutor.unsafeRunWhenOn((Dist)Dist.CLIENT, () -> () -> WitherStormModMessageHandlerClient.processUpdateStormHeadLookMessage(this));
    }

    public String toString() {
        return "UpdateStormHeadLookMessage[id=" + this.entityId + ", yHeadRot=" + this.yHeadRot + "]";
    }
}

