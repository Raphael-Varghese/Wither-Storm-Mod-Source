/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundMoveEntityPacket$Pos
 *  net.minecraft.network.protocol.game.ClientboundMoveEntityPacket$PosRot
 *  net.minecraft.network.protocol.game.ClientboundMoveEntityPacket$Rot
 *  net.minecraft.network.protocol.game.ClientboundRotateHeadPacket
 *  net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket
 *  net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket
 *  net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket
 *  net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.network.PacketDistributor
 *  net.minecraftforge.network.PacketDistributor$PacketTarget
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package nonamecrackers2.witherstormmod.mixin;

import java.util.List;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.packet.StormAttributesMessage;
import nonamecrackers2.witherstormmod.common.packet.StormMetadataMessage;
import nonamecrackers2.witherstormmod.common.packet.StormTeleportMessage;
import nonamecrackers2.witherstormmod.common.packet.UpdateStormHeadLookMessage;
import nonamecrackers2.witherstormmod.common.packet.UpdateStormPositionMessage;
import nonamecrackers2.witherstormmod.common.packet.UpdateStormVelocityMessage;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets={"net.minecraft.server.level.ChunkMap$TrackedEntity"})
public class MixinTrackedEntity {
    @Shadow
    @Final
    private Entity f_140472_;

    @Inject(method={"broadcast"}, at={@At(value="TAIL")})
    public void witherstormmod$copyVanillaAndSendToDistantRenderer_broadcast(Packet<?> packet, CallbackInfo ci) {
        Entity entity = this.f_140472_;
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            PacketDistributor.PacketTarget target = PacketDistributor.DIMENSION.with(() -> ((Level)storm.m_9236_()).m_46472_());
            List<Integer> applicable = WorldUtil.getStormIds(storm);
            if (packet instanceof ClientboundMoveEntityPacket.Rot) {
                ClientboundMoveEntityPacket.Rot rotPacket = (ClientboundMoveEntityPacket.Rot)packet;
                WitherStormModPacketHandlers.MAIN.send(target, (Object)new UpdateStormPositionMessage(applicable, this.f_140472_.m_19879_(), rotPacket.m_132531_(), rotPacket.m_132532_(), rotPacket.m_132535_()));
            } else if (packet instanceof ClientboundMoveEntityPacket.Pos) {
                ClientboundMoveEntityPacket.Pos posPacket = (ClientboundMoveEntityPacket.Pos)packet;
                WitherStormModPacketHandlers.MAIN.send(target, (Object)new UpdateStormPositionMessage(applicable, this.f_140472_.m_19879_(), posPacket.m_178997_(), posPacket.m_178998_(), posPacket.m_178999_(), posPacket.m_132535_()));
            } else if (packet instanceof ClientboundMoveEntityPacket.PosRot) {
                ClientboundMoveEntityPacket.PosRot posRotPacket = (ClientboundMoveEntityPacket.PosRot)packet;
                WitherStormModPacketHandlers.MAIN.send(target, (Object)new UpdateStormPositionMessage(applicable, this.f_140472_.m_19879_(), posRotPacket.m_178997_(), posRotPacket.m_178998_(), posRotPacket.m_178999_(), posRotPacket.m_132531_(), posRotPacket.m_132532_(), posRotPacket.m_132535_()));
            } else if (packet instanceof ClientboundTeleportEntityPacket) {
                ClientboundTeleportEntityPacket teleportPacket = (ClientboundTeleportEntityPacket)packet;
                WitherStormModPacketHandlers.MAIN.send(target, (Object)new StormTeleportMessage(applicable, teleportPacket.m_133545_(), teleportPacket.m_133548_(), teleportPacket.m_133549_(), teleportPacket.m_133550_(), teleportPacket.m_133551_(), teleportPacket.m_133552_(), teleportPacket.m_133553_()));
            } else if (packet instanceof ClientboundSetEntityMotionPacket) {
                ClientboundSetEntityMotionPacket motionPacket = (ClientboundSetEntityMotionPacket)packet;
                WitherStormModPacketHandlers.MAIN.send(target, (Object)new UpdateStormVelocityMessage(applicable, motionPacket.m_133192_(), motionPacket.m_133195_(), motionPacket.m_133196_(), motionPacket.m_133197_()));
            } else if (packet instanceof ClientboundRotateHeadPacket) {
                ClientboundRotateHeadPacket headPacket = (ClientboundRotateHeadPacket)packet;
                WitherStormModPacketHandlers.MAIN.send(target, (Object)new UpdateStormHeadLookMessage(applicable, storm.m_19879_(), headPacket.m_132977_()));
            } else if (packet instanceof ClientboundSetEntityDataPacket) {
                ClientboundSetEntityDataPacket dataPacket = (ClientboundSetEntityDataPacket)packet;
                WitherStormModPacketHandlers.MAIN.send(target, (Object)new StormMetadataMessage(applicable, dataPacket.f_133143_(), dataPacket.f_133144_()));
            } else if (packet instanceof ClientboundUpdateAttributesPacket) {
                ClientboundUpdateAttributesPacket attributesPacket = (ClientboundUpdateAttributesPacket)packet;
                WitherStormModPacketHandlers.MAIN.send(target, (Object)new StormAttributesMessage(applicable, attributesPacket.m_133588_(), attributesPacket.m_133591_()));
            }
        }
    }
}

