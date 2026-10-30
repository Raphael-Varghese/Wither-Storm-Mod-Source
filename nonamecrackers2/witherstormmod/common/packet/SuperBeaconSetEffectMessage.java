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
import nonamecrackers2.witherstormmod.common.packet.WitherStormModMessageHandlerServer;

public class SuperBeaconSetEffectMessage
extends Packet {
    private int effect;

    public SuperBeaconSetEffectMessage(int effect) {
        super(true);
        this.effect = effect;
    }

    public SuperBeaconSetEffectMessage() {
        super(false);
    }

    public int getEffectId() {
        return this.effect;
    }

    public void decode(FriendlyByteBuf buffer) {
        this.effect = buffer.m_130242_();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.m_130130_(this.effect);
    }

    public Runnable getProcessor(NetworkEvent.Context context) {
        return () -> WitherStormModMessageHandlerServer.processSuperBeaconSetEffectMessage(this, context.getSender());
    }
}

