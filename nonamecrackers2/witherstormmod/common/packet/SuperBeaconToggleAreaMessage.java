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

public class SuperBeaconToggleAreaMessage
extends Packet {
    private boolean shouldShow;

    public SuperBeaconToggleAreaMessage(boolean shouldShow) {
        super(true);
        this.shouldShow = shouldShow;
    }

    public SuperBeaconToggleAreaMessage() {
        super(false);
    }

    public boolean shouldShowArea() {
        return this.shouldShow;
    }

    public void decode(FriendlyByteBuf buffer) {
        this.shouldShow = buffer.readBoolean();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBoolean(this.shouldShow);
    }

    public Runnable getProcessor(NetworkEvent.Context context) {
        return () -> WitherStormModMessageHandlerServer.processSuperBeaconToggleAreaMessage(this, context.getSender());
    }
}

