/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.fml.DistExecutor
 *  net.minecraftforge.network.NetworkEvent$Context
 *  nonamecrackers2.crackerslib.common.packet.Packet
 */
package nonamecrackers2.witherstormmod.common.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import nonamecrackers2.crackerslib.common.packet.Packet;
import nonamecrackers2.witherstormmod.client.packet.WitherStormModMessageHandlerClient;

public class UpdateEffectInstanceMessage
extends Packet {
    private int entityId;
    private byte effectId;
    private byte amplifier;
    private int duration;
    private boolean showDuration;

    public UpdateEffectInstanceMessage(int entityId, MobEffectInstance effect, boolean showDuration) {
        super(true);
        this.entityId = entityId;
        this.effectId = (byte)(MobEffect.m_19459_((MobEffect)effect.m_19544_()) & 0xFF);
        this.amplifier = (byte)(effect.m_19564_() & 0xFF);
        this.duration = effect.m_19557_() > Short.MAX_VALUE ? Short.MAX_VALUE : effect.m_19557_();
        this.showDuration = showDuration;
    }

    public UpdateEffectInstanceMessage() {
        super(false);
    }

    public int getEntityID() {
        return this.entityId;
    }

    public byte getEffectID() {
        return this.effectId;
    }

    public byte getAmplifier() {
        return this.amplifier;
    }

    public int getDuration() {
        return this.duration;
    }

    public boolean shouldShowDuration() {
        return this.showDuration;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.m_130130_(this.entityId);
        buffer.writeByte((int)this.effectId);
        buffer.writeByte((int)this.amplifier);
        buffer.m_130130_(this.duration);
    }

    public void decode(FriendlyByteBuf buffer) {
        this.entityId = buffer.m_130242_();
        this.effectId = buffer.readByte();
        this.amplifier = buffer.readByte();
        this.duration = buffer.m_130242_();
    }

    public Runnable getProcessor(NetworkEvent.Context context) {
        return () -> DistExecutor.unsafeRunWhenOn((Dist)Dist.CLIENT, () -> () -> WitherStormModMessageHandlerClient.processUpdateEffectInstanceMessage(this));
    }

    public String toString() {
        return "UpdateEffectInstanceMessage[id=" + this.entityId + ", effectId=" + this.effectId + ", amplifer=" + this.amplifier + ", duration=" + this.duration + ", showDuration=" + this.showDuration + "]";
    }
}

