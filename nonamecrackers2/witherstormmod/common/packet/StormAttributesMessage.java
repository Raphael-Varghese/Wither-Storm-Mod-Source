/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket$AttributeSnapshot
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeInstance
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.fml.DistExecutor
 *  net.minecraftforge.network.NetworkEvent$Context
 *  net.minecraftforge.registries.ForgeRegistries
 */
package nonamecrackers2.witherstormmod.common.packet;

import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
import nonamecrackers2.witherstormmod.common.packet.DistantRendererMessage;

public class StormAttributesMessage
extends DistantRendererMessage {
    private int entityId;
    private List<ClientboundUpdateAttributesPacket.AttributeSnapshot> attributes = Lists.newArrayList();

    public StormAttributesMessage(List<Integer> applicable, int id, Collection<AttributeInstance> attributes) {
        super(true, applicable);
        this.entityId = id;
        for (AttributeInstance attribute : attributes) {
            this.attributes.add(new ClientboundUpdateAttributesPacket.AttributeSnapshot(attribute.m_22099_(), attribute.m_22115_(), (Collection)attribute.m_22122_()));
        }
    }

    public StormAttributesMessage(List<Integer> applicable, int id, List<ClientboundUpdateAttributesPacket.AttributeSnapshot> attributes) {
        super(true, applicable);
        this.entityId = id;
        this.attributes = attributes;
    }

    public StormAttributesMessage() {
        super(false, Lists.newArrayList());
    }

    public int getEntityID() {
        return this.entityId;
    }

    public List<ClientboundUpdateAttributesPacket.AttributeSnapshot> getAttributes() {
        return this.attributes;
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        super.encode(buffer);
        buffer.m_130130_(this.entityId);
        buffer.m_236828_(this.attributes, (buffer1, snapshot) -> {
            buffer1.m_130085_(ForgeRegistries.ATTRIBUTES.getKey((Object)snapshot.m_133601_()));
            buffer1.writeDouble(snapshot.m_133602_());
            buffer1.m_236828_(snapshot.m_133603_(), (buffer2, modifier) -> {
                buffer2.m_130077_(modifier.m_22209_());
                buffer2.writeDouble(modifier.m_22218_());
                buffer2.writeByte(modifier.m_22217_().m_22235_());
            });
        });
    }

    @Override
    public void decode(FriendlyByteBuf buffer) {
        super.decode(buffer);
        this.entityId = buffer.m_130242_();
        this.attributes = buffer.m_236845_(buffer1 -> {
            ResourceLocation location = buffer1.m_130281_();
            Attribute attribute = (Attribute)ForgeRegistries.ATTRIBUTES.getValue(location);
            double base = buffer1.readDouble();
            List list = buffer1.m_236845_(buffer2 -> new AttributeModifier(buffer2.m_130259_(), "Unknown synced attribute modifier", buffer.readDouble(), AttributeModifier.Operation.m_22236_((int)buffer.readByte())));
            return new ClientboundUpdateAttributesPacket.AttributeSnapshot(attribute, base, (Collection)list);
        });
    }

    public Runnable getProcessor(NetworkEvent.Context context) {
        return () -> DistExecutor.unsafeRunWhenOn((Dist)Dist.CLIENT, () -> () -> WitherStormModMessageHandlerClient.processStormAttributesMessage(this));
    }

    public String toString() {
        return "StormAttributesMessage[id=" + this.entityId + ", properties=" + this.attributes.toString() + "]";
    }
}

