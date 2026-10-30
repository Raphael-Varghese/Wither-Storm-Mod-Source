/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.network.chat.ClickEvent
 *  net.minecraft.network.chat.ClickEvent$Action
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.Style
 *  net.minecraftforge.client.event.ClientPlayerNetworkEvent$LoggingIn
 *  net.minecraftforge.client.event.CustomizeGuiOverlayEvent$DebugText
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  nonamecrackers2.crackerslib.common.compat.CompatHelper
 */
package nonamecrackers2.witherstormmod.client.event;

import java.util.ArrayList;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.crackerslib.common.compat.CompatHelper;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.client.instancing.RenderBufferer;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;

public class WitherStormModGuiEvents {
    @SubscribeEvent
    public static void onRenderOverlay(CustomizeGuiOverlayEvent.DebugText event) {
        Minecraft mc = Minecraft.m_91087_();
        if (mc.f_91066_.f_92063_) {
            ArrayList text = event.getRight();
            text.add("");
            text.add("witherstormmod: " + WitherStormMod.getVersion());
            text.add("Buffered Instances: " + RenderBufferer.INSTANCE.getTotalInstances());
            boolean flag = RenderBufferer.shouldUse();
            Object s = String.valueOf(flag);
            s = flag ? ChatFormatting.GREEN + (String)s : ChatFormatting.RED + (String)s;
            text.add("Render Bufferer Active: " + (String)s);
        }
    }

    @SubscribeEvent
    public static void onPlayerJoinLevel(ClientPlayerNetworkEvent.LoggingIn event) {
        if (CompatHelper.isOptifineLoaded() && ((Boolean)WitherStormModConfig.CLIENT.optifineWarning.get()).booleanValue()) {
            Minecraft mc = Minecraft.m_91087_();
            ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/nonamecrackers2/crackers-wither-storm-mod/wiki/Known-Compatibility-Issues");
            mc.f_91065_.m_93076_().m_93785_((Component)Component.m_237115_((String)"chat.witherstormmod.optifine.notice").m_130948_(Style.f_131099_.m_131157_(ChatFormatting.RED).m_131142_(clickEvent)));
            WitherStormModConfig.CLIENT.optifineWarning.set((Object)false);
        }
    }
}

