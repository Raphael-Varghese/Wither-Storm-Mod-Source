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

public class RemoveStormFromDistantRendererMessage
extends DistantRendererMessage {
    private int id;

    public RemoveStormFromDistantRendererMessage(List<Integer> applicable, WitherStormEntity entity) {
        super(true, applicable);
        this.id = entity.m_19879_();
    }

    public RemoveStormFromDistantRendererMessage() {
        super(false, Lists.newArrayList());
    }

    public int getId() {
        return this.id;
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        super.encode(buffer);
        buffer.m_130130_(this.id);
    }

    @Override
    public void decode(FriendlyByteBuf buffer) {
        super.decode(buffer);
        this.id = buffer.m_130242_();
    }

    public Runnable getProcessor(NetworkEvent.Context context) {
        return () -> DistExecutor.unsafeRunWhenOn((Dist)Dist.CLIENT, () -> () -> WitherStormModMessageHandlerClient.processRemoveStormFromDistantRendererMessage(this));
    }

    public String toString() {
        return "RemoveStormFromDistantRendererMessage[id=" + this.id + "]";
    }
}

