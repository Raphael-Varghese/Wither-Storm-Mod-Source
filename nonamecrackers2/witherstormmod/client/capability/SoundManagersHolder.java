/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraftforge.event.TickEvent$ClientTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.client.capability;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.client.audio.ISoundManager;
import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;

public class SoundManagersHolder {
    private final List<ISoundManager> managers = new ArrayList<ISoundManager>();

    public List<ISoundManager> getManagers() {
        return ImmutableList.copyOf(this.managers);
    }

    public void putManager(ISoundManager manager) {
        this.managers.add(manager);
    }

    public static class Events {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            ClientLevel world;
            Minecraft mc = Minecraft.m_91087_();
            if (event.phase == TickEvent.Phase.START && (world = mc.f_91073_) != null && !mc.m_91104_()) {
                world.getCapability(WitherStormModClientCapabilities.SOUND_MANAGERS).ifPresent(holder -> {
                    for (ISoundManager manager : holder.getManagers()) {
                        manager.tick();
                    }
                });
            }
        }
    }
}

