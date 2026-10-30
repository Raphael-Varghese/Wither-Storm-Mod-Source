/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.Vec3i
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundSoundPacket
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.portal.PortalInfo
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.util.ITeleporter
 *  net.minecraftforge.registries.ForgeRegistries
 */
package nonamecrackers2.witherstormmod.common.util;

import java.util.function.Function;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;

public class BowelsTeleporter
implements ITeleporter {
    private final BlockPos pos;

    public BowelsTeleporter(BlockPos pos) {
        this.pos = pos;
    }

    public PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
        return new PortalInfo(Vec3.m_82539_((Vec3i)this.pos), Vec3.f_82478_, entity.m_146908_(), entity.m_146909_());
    }

    public boolean playTeleportSound(ServerPlayer player, ServerLevel sourceWorld, ServerLevel destWorld) {
        player.f_8906_.m_9829_((Packet)new ClientboundSoundPacket((Holder)ForgeRegistries.SOUND_EVENTS.getHolder((Object)((SoundEvent)WitherStormModSoundEvents.BOWELS_TRANSPORT.get())).get(), SoundSource.AMBIENT, player.m_20185_(), player.m_20186_(), player.m_20189_(), 1.0f, 1.0f, player.m_217043_().m_188505_()));
        return false;
    }
}

