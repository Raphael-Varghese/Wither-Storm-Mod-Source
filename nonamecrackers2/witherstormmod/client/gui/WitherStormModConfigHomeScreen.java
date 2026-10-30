/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.components.AbstractButton
 *  net.minecraft.client.gui.components.Button
 *  net.minecraft.client.gui.components.Button$Builder
 *  net.minecraft.client.gui.components.Button$OnPress
 *  net.minecraft.client.gui.components.Tooltip
 *  net.minecraft.client.gui.components.events.GuiEventListener
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.network.chat.Component
 *  net.minecraftforge.common.ForgeConfigSpec
 *  net.minecraftforge.fml.config.ModConfig$Type
 *  nonamecrackers2.crackerslib.client.gui.ConfigHomeScreen
 *  nonamecrackers2.crackerslib.client.gui.title.TitleLogo
 */
package nonamecrackers2.witherstormmod.client.gui;

import java.lang.invoke.LambdaMetafactory;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import nonamecrackers2.crackerslib.client.gui.ConfigHomeScreen;
import nonamecrackers2.crackerslib.client.gui.title.TitleLogo;
import nonamecrackers2.witherstormmod.client.audio.SoundManagersRefresher;

public class WitherStormModConfigHomeScreen
extends ConfigHomeScreen {
    public WitherStormModConfigHomeScreen(String modid, Map<ModConfig.Type, ForgeConfigSpec> specs, TitleLogo title, boolean isWorldLoaded, boolean hasSinglePlayerServer, Screen previous, List<Supplier<AbstractButton>> extraButtons, int totalColumns) {
        super(modid, specs, title, isWorldLoaded, hasSinglePlayerServer, previous, extraButtons, totalColumns);
    }

    protected void m_7856_() {
        super.m_7856_();
        ((Button)this.m_142416_((GuiEventListener)Button.m_253074_((Component)Component.m_237115_((String)"gui.witherstormmod.button.refreshSounds.title"), (Button.OnPress)(Button.OnPress)LambdaMetafactory.metafactory(null, null, null, (Lnet/minecraft/client/gui/components/Button;)V, lambda$init$0(net.minecraft.client.gui.components.Button ), (Lnet/minecraft/client/gui/components/Button;)V)()).m_252794_((int)5, (int)5).m_253046_((int)20, (int)20).m_257505_((Tooltip)Tooltip.m_257550_((Component)Component.m_237115_((String)"gui.witherstormmod.button.refreshSounds.title"))).build((Function<Button.Builder, Button>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, <init>(net.minecraft.client.gui.components.Button$Builder ), (Lnet/minecraft/client/gui/components/Button$Builder;)Lnet/minecraft/client/gui/components/Button;)()))).f_93623_ = this.f_96541_.f_91073_ != null;
    }

    private static /* synthetic */ void lambda$init$0(Button button) {
        SoundManagersRefresher.INSTANCE.refresh();
    }
}

