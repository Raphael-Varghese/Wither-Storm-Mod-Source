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

public class ShakeScreenMessage
extends Packet {
    private float duration;
    private float power;

    public ShakeScreenMessage(float duration, float power) {
        super(true);
        this.duration = duration;
        this.power = power;
    }

    public ShakeScreenMessage() {
        super(false);
    }

    public float getDuration() {
        return this.duration;
    }

    public float getPower() {
        return this.power;
    }

    public void decode(FriendlyByteBuf buffer) {
        this.duration = buffer.readFloat();
        this.power = buffer.readFloat();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeFloat(this.duration);
        buffer.writeFloat(this.power);
    }

    public Runnable getProcessor(NetworkEvent.Context context) {
        return () -> DistExecutor.unsafeRunWhenOn((Dist)Dist.CLIENT, () -> () -> WitherStormModMessageHandlerClient.processShakeScreenMessage(this));
    }

    public String toString() {
        return "ShakeScreenMessage[duration=" + this.duration + ", power=" + this.power + "]";
    }
}

