/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.fml.DistExecutor
 *  net.minecraftforge.network.NetworkEvent$Context
 *  org.apache.commons.compress.utils.Lists
 */
package nonamecrackers2.witherstormmod.common.packet;

import java.util.List;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;
import nonamecrackers2.witherstormmod.common.packet.DistantRendererMessage;
import org.apache.commons.compress.utils.Lists;

public class UpdateStormPositionMessage
extends DistantRendererMessage {
    private int entityId;
    private short xa;
    private short ya;
    private short za;
    private byte yRot;
    private byte xRot;
    private boolean onGround;
    private boolean hasRot;
    private boolean hasPos;

    public UpdateStormPositionMessage(List<Integer> applicable, int id) {
        super(true, applicable);
        this.entityId = id;
    }

    public UpdateStormPositionMessage(List<Integer> applicable, int id, byte yRot, byte xRot, boolean onGround) {
        this(applicable, id);
        this.yRot = yRot;
        this.xRot = xRot;
        this.onGround = onGround;
        this.hasRot = true;
    }

    public UpdateStormPositionMessage(List<Integer> applicable, int id, short x, short y, short z, byte yRot, byte xRot, boolean onGround) {
        this(applicable, id);
        this.xa = x;
        this.ya = y;
        this.za = z;
        this.yRot = yRot;
        this.xRot = xRot;
        this.onGround = onGround;
        this.hasRot = true;
        this.hasPos = true;
    }

    public UpdateStormPositionMessage(List<Integer> applicable, int id, short x, short y, short z, boolean onGround) {
        this(applicable, id);
        this.xa = x;
        this.ya = y;
        this.za = z;
        this.onGround = onGround;
        this.hasPos = true;
    }

    public UpdateStormPositionMessage() {
        super(false, Lists.newArrayList());
    }

    public int getEntityID() {
        return this.entityId;
    }

    public boolean hasRotation() {
        return this.hasRot;
    }

    public boolean hasPosition() {
        return this.hasPos;
    }

    public boolean onGround() {
        return this.onGround;
    }

    public short getX() {
        return this.xa;
    }

    public short getY() {
        return this.ya;
    }

    public short getZ() {
        return this.za;
    }

    public byte getYRot() {
        return this.yRot;
    }

    public byte getXRot() {
        return this.xRot;
    }

    @Override
    public void decode(FriendlyByteBuf buffer) {
        super.decode(buffer);
        this.entityId = buffer.m_130242_();
        this.hasPos = buffer.readBoolean();
        if (this.hasPos) {
            this.xa = buffer.readShort();
            this.ya = buffer.readShort();
            this.za = buffer.readShort();
        }
        this.hasRot = buffer.readBoolean();
        if (this.hasRot) {
            this.yRot = buffer.readByte();
            this.xRot = buffer.readByte();
        }
        this.onGround = buffer.readBoolean();
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        super.encode(buffer);
        buffer.m_130130_(this.entityId);
        buffer.writeBoolean(this.hasPos);
        if (this.hasPos) {
            buffer.writeShort((int)this.xa);
            buffer.writeShort((int)this.ya);
            buffer.writeShort((int)this.za);
        }
        buffer.writeBoolean(this.hasRot);
        if (this.hasRot) {
            buffer.writeByte((int)this.yRot);
            buffer.writeByte((int)this.xRot);
        }
        buffer.writeBoolean(this.onGround);
    }

    public Runnable getProcessor(NetworkEvent.Context context) {
        return () -> DistExecutor.unsafeRunWhenOn((Dist)Dist.CLIENT, () -> () -> WitherStormModMessageHandlerClient.processUpdateStormPositionMessage(this));
    }

    public String toString() {
        return "UpdateWitherstormPositionMessage[id=" + this.entityId + ", hasPos=" + this.hasPos + ", hasRot=" + this.hasRot + ", x=" + this.xa + ", y=" + this.ya + ", z=" + this.za + ", yRot=" + this.yRot + ", xRot=" + this.xRot + "]";
    }
}

