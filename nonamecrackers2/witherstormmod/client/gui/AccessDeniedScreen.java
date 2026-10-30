/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.Util
 *  net.minecraft.client.gui.Font
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.components.Button
 *  net.minecraft.client.gui.components.events.GuiEventListener
 *  net.minecraft.client.gui.screens.ConfirmLinkScreen
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.FormattedText
 *  net.minecraft.network.chat.Style
 *  net.minecraft.util.FormattedCharSequence
 */
package nonamecrackers2.witherstormmod.client.gui;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import nonamecrackers2.witherstormmod.client.util.Contributors;

@Deprecated
public class AccessDeniedScreen
extends Screen {
    private static final Component TITLE = Component.m_237113_((String)"Whoops!").m_130948_(Style.f_131099_.m_131136_(Boolean.valueOf(true)));
    private static final Component DESCRIPTION = Component.m_237113_((String)"Looks like you do not have access to this build of Cracker's Wither Storm Mod. This is an in-development build exclusive for BETA testers, patrons, etc. The content in this in-development build will be made available freely to the public when it is deemed to be finished.");
    private static final Component PATREON = Component.m_237113_((String)"If you're a nonamecrackers2 patron, open the link below and sign in with your Patreon account to gain access.");
    private static final Component NOTE = Component.m_237113_((String)"NOTE: The Minecraft account used to launch this game will be associated with your Patreon account.").m_130940_(ChatFormatting.DARK_GRAY);
    private static final Component ERROR = Component.m_237113_((String)"Something went wrong while authenticating! Please check your internet connection. If the problem persists, please notify nonamecrackers2 and provide the latest.log file.");
    private Button openLink;
    private Button refresh;
    private Contributors.Result access;
    @Nullable
    private CompletableFuture<Contributors.Result> resultGetter;

    public AccessDeniedScreen(Contributors.Result access) {
        super((Component)Component.m_237119_());
        this.access = access;
    }

    protected void m_7856_() {
        this.openLink = Button.m_253074_((Component)Component.m_237113_((String)"Open Link"), button -> {
            String url = "https://patronauthenticator-sp4uwbgqwa-uc.a.run.app/login?mc_uuid=" + this.f_96541_.m_91094_().m_92545_();
            this.f_96541_.m_91152_((Screen)new ConfirmLinkScreen(b -> {
                if (b) {
                    Util.m_137581_().m_137646_(url);
                }
                this.f_96541_.m_91152_((Screen)this);
            }, url, true));
        }).m_252780_(100).m_252794_(this.f_96543_ / 2 - 120, 210).m_253136_();
        this.refresh = Button.m_253074_((Component)Component.m_237113_((String)"Refresh"), button -> this.refresh()).m_252780_(100).m_252794_(this.f_96543_ / 2 + 20, 210).m_253136_();
        if (this.access != Contributors.Result.ERROR) {
            this.m_142416_((GuiEventListener)this.openLink);
        } else {
            this.refresh.m_252865_(this.f_96543_ / 2 - this.refresh.m_5711_() / 2);
        }
        this.m_142416_((GuiEventListener)this.refresh);
    }

    public void m_88315_(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
        this.m_280273_(stack);
        int y = this.f_96544_ / 8;
        stack.m_280653_(this.f_96547_, TITLE, this.f_96543_ / 2, y, -1);
        if (this.access != Contributors.Result.ERROR) {
            y = AccessDeniedScreen.drawSplitText(stack, this.f_96547_, DESCRIPTION, this.f_96543_ - 50, this.f_96543_ / 2, y + 30, -1);
            y = AccessDeniedScreen.drawSplitText(stack, this.f_96547_, PATREON, this.f_96543_ - 50, this.f_96543_ / 2, y + 20, -1);
            y = AccessDeniedScreen.drawSplitText(stack, this.f_96547_, NOTE, this.f_96543_ - 50, this.f_96543_ / 2, y + 20, -1);
            this.openLink.m_253211_(y + this.openLink.m_93694_() + 10);
        } else {
            y = AccessDeniedScreen.drawSplitText(stack, this.f_96547_, ERROR, this.f_96543_ - 50, this.f_96543_ / 2, y + 30, -1);
        }
        this.refresh.m_253211_(y + this.openLink.m_93694_() + 10);
        super.m_88315_(stack, mouseX, mouseY, partialTicks);
    }

    private static int drawSplitText(GuiGraphics stack, Font font, Component text, int width, int x, int y, int color) {
        List desc = font.m_92923_((FormattedText)text, width);
        int lastY = y;
        for (int i = 0; i < desc.size(); ++i) {
            FormattedCharSequence line = (FormattedCharSequence)desc.get(i);
            Objects.requireNonNull(font);
            lastY = y + i * (9 + 2);
            stack.m_280364_(font, line, x, lastY, color);
        }
        return lastY;
    }

    public void m_86600_() {
        this.f_96541_.m_91397_().m_120183_();
        this.f_96541_.m_91106_().m_120389_(false);
        if (this.resultGetter != null && this.resultGetter.isDone()) {
            try {
                this.access = this.resultGetter.get();
                if (!this.access.canLaunchGame()) {
                    this.m_267719_();
                } else {
                    this.m_7379_();
                }
            }
            catch (InterruptedException | ExecutionException exception) {
                // empty catch block
            }
            this.resultGetter = null;
        }
    }

    public boolean m_6913_() {
        return false;
    }

    public void refresh() {
        this.refresh.f_93623_ = false;
        this.openLink.f_93623_ = false;
        if (this.resultGetter == null) {
            this.resultGetter = CompletableFuture.supplyAsync(() -> Contributors.getAccess(this.f_96541_.m_91094_().m_92545_()));
        }
    }
}

