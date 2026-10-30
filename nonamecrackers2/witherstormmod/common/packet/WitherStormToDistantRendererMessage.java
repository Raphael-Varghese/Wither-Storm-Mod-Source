/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.core.Vec3i
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket$AttributeSnapshot
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.network.syncher.SynchedEntityData$DataValue
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeInstance
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.entity.IEntityAdditionalSpawnData
 *  net.minecraftforge.fml.DistExecutor
 *  net.minecraftforge.network.NetworkEvent$Context
 *  net.minecraftforge.registries.ForgeRegistries
 */
package nonamecrackers2.witherstormmod.common.packet;

import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import net.minecraft.core.Vec3i;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.HeadManager;
import nonamecrackers2.witherstormmod.common.packet.DistantRendererMessage;

public class WitherStormToDistantRendererMessage
extends DistantRendererMessage {
    private final WitherStormEntity entity;
    private int id;
    private UUID uuid;
    private ResourceLocation type;
    private Vec3 pos;
    private Vec3i delta;
    private byte yRot;
    private byte xRot;
    private byte yHeadRot;
    private HeadManager.PackedHeadRots rots;
    private List<SynchedEntityData.DataValue<?>> packedItems;
    private final List<ClientboundUpdateAttributesPacket.AttributeSnapshot> attributes = Lists.newArrayList();
    private FriendlyByteBuf buffer;

    public WitherStormToDistantRendererMessage(List<Integer> applicable, WitherStormEntity entity) {
        super(true, applicable);
        Vec3i delta;
        this.entity = entity;
        this.id = entity.m_19879_();
        this.uuid = entity.m_20148_();
        this.type = ForgeRegistries.ENTITY_TYPES.getKey((Object)entity.m_6095_());
        this.pos = new Vec3(entity.m_20185_(), entity.m_20186_(), entity.m_20189_());
        this.yRot = (byte)(entity.m_146908_() * 256.0f / 360.0f);
        this.xRot = (byte)(entity.m_146909_() * 256.0f / 360.0f);
        this.yHeadRot = (byte)(entity.f_20885_ * 256.0f / 360.0f);
        this.rots = entity.getHeadManager().packHeadRotations();
        Vec3 vector3d = entity.m_20184_();
        this.delta = delta = new Vec3i(Mth.m_14107_((double)Mth.m_14008_((double)vector3d.f_82479_, (double)-3.9, (double)3.9)), Mth.m_14107_((double)Mth.m_14008_((double)vector3d.f_82480_, (double)-3.9, (double)3.9)), Mth.m_14107_((double)Mth.m_14008_((double)vector3d.f_82481_, (double)-3.9, (double)3.9)));
        SynchedEntityData data = entity.m_20088_();
        this.packedItems = WitherStormToDistantRendererMessage.getPackedData(data);
        for (AttributeInstance attribute : entity.m_21204_().m_22170_()) {
            this.attributes.add(new ClientboundUpdateAttributesPacket.AttributeSnapshot(attribute.m_22099_(), attribute.m_22115_(), (Collection)attribute.m_22122_()));
        }
        this.buffer = null;
    }

    public WitherStormToDistantRendererMessage() {
        super(false, Lists.newArrayList());
        this.entity = null;
    }

    public int getId() {
        return this.id;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public ResourceLocation getType() {
        return this.type;
    }

    public Vec3 getPos() {
        return this.pos;
    }

    public Vec3i getDeltaMovement() {
        return this.delta;
    }

    public byte getYRot() {
        return this.yRot;
    }

    public byte getXRot() {
        return this.xRot;
    }

    public byte getHeadYRot() {
        return this.yHeadRot;
    }

    public HeadManager.PackedHeadRots getRots() {
        return this.rots;
    }

    public List<SynchedEntityData.DataValue<?>> getUnpackedData() {
        return this.packedItems;
    }

    public FriendlyByteBuf getBuffer() {
        return this.buffer;
    }

    @Override
    public void decode(FriendlyByteBuf buffer) {
        short j;
        super.decode(buffer);
        this.id = buffer.m_130242_();
        this.uuid = buffer.m_130259_();
        this.type = buffer.m_130281_();
        double x = buffer.readDouble();
        double y = buffer.readDouble();
        double z = buffer.readDouble();
        this.pos = new Vec3(x, y, z);
        this.yRot = buffer.readByte();
        this.xRot = buffer.readByte();
        this.yHeadRot = buffer.readByte();
        this.rots = HeadManager.PackedHeadRots.fromPacket(buffer);
        short xd = buffer.readShort();
        short yd = buffer.readShort();
        short zd = buffer.readShort();
        this.delta = new Vec3i((int)xd, (int)yd, (int)zd);
        ArrayList packedData = Lists.newArrayList();
        while ((j = buffer.readUnsignedByte()) != 255) {
            packedData.add(SynchedEntityData.DataValue.m_252860_((FriendlyByteBuf)buffer, (int)j));
        }
        this.packedItems = packedData;
        int attributeSize = buffer.readInt();
        for (int i = 0; i < attributeSize; ++i) {
            ResourceLocation location = buffer.m_130281_();
            Attribute attribute = (Attribute)ForgeRegistries.ATTRIBUTES.getValue(location);
            double base = buffer.readDouble();
            ArrayList list = Lists.newArrayList();
            int modifierSize = buffer.m_130242_();
            for (int l = 0; l < modifierSize; ++l) {
                UUID uuid = buffer.m_130259_();
                list.add(new AttributeModifier(uuid, "Unknown synced attribute modifier", buffer.readDouble(), AttributeModifier.Operation.m_22236_((int)buffer.readByte())));
            }
            this.attributes.add(new ClientboundUpdateAttributesPacket.AttributeSnapshot(attribute, base, (Collection)list));
        }
        int count = buffer.m_130242_();
        if (count > 0) {
            FriendlyByteBuf extra = new FriendlyByteBuf(Unpooled.buffer());
            extra.writeBytes((ByteBuf)buffer, count);
            this.buffer = extra;
        } else {
            this.buffer = new FriendlyByteBuf(Unpooled.buffer());
        }
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        super.encode(buffer);
        buffer.m_130130_(this.id);
        buffer.m_130077_(this.uuid);
        buffer.m_130085_(this.type);
        buffer.writeDouble(this.pos.f_82479_);
        buffer.writeDouble(this.pos.f_82480_);
        buffer.writeDouble(this.pos.f_82481_);
        buffer.writeByte((int)this.yRot);
        buffer.writeByte((int)this.xRot);
        buffer.writeByte((int)this.yHeadRot);
        this.rots.toPacket(buffer);
        buffer.writeShort(this.delta.m_123341_());
        buffer.writeShort(this.delta.m_123342_());
        buffer.writeShort(this.delta.m_123343_());
        for (SynchedEntityData.DataValue<?> value : this.packedItems) {
            value.m_252897_(buffer);
        }
        buffer.writeByte(255);
        buffer.writeInt(this.attributes.size());
        for (ClientboundUpdateAttributesPacket.AttributeSnapshot snapshot : this.attributes) {
            buffer.m_130085_(ForgeRegistries.ATTRIBUTES.getKey((Object)snapshot.m_133601_()));
            buffer.writeDouble(snapshot.m_133602_());
            buffer.m_130130_(snapshot.m_133603_().size());
            for (AttributeModifier modifier : snapshot.m_133603_()) {
                buffer.m_130077_(modifier.m_22209_());
                buffer.writeDouble(modifier.m_22218_());
                buffer.writeByte(modifier.m_22217_().m_22235_());
            }
        }
        FriendlyByteBuf extra = new FriendlyByteBuf(Unpooled.buffer());
        WitherStormEntity witherStormEntity = this.entity;
        if (witherStormEntity instanceof IEntityAdditionalSpawnData) {
            IEntityAdditionalSpawnData e = (IEntityAdditionalSpawnData)witherStormEntity;
            e.writeSpawnData(extra);
        }
        this.entity.writeData(extra);
        buffer.m_130130_(extra.readableBytes());
        buffer.writeBytes((ByteBuf)extra);
        extra.release();
    }

    public Runnable getProcessor(NetworkEvent.Context context) {
        return () -> DistExecutor.unsafeRunWhenOn((Dist)Dist.CLIENT, () -> () -> WitherStormModMessageHandlerClient.processWitherStormToDistantRendererMessage(this));
    }

    public String toString() {
        return "WitherStormToDistantRendererMessage[id=" + this.id + ", uuid=" + String.valueOf(this.uuid) + ", type=" + String.valueOf(this.type) + ", x=" + String.valueOf(this.pos.f_82479_) + ", y=" + String.valueOf(this.pos.f_82480_) + ", z=" + String.valueOf(this.pos.f_82481_) + ", yRot= " + String.valueOf(this.yRot) + ", xRot= " + String.valueOf(this.xRot) + ", yHeadRot= " + String.valueOf(this.yHeadRot) + ", headRots= " + this.rots.toString() + ", xd=" + String.valueOf(this.delta.m_123341_()) + ", yd=" + String.valueOf(this.delta.m_123342_()) + ", zd=" + String.valueOf(this.delta.m_123343_()) + "]";
    }
}

