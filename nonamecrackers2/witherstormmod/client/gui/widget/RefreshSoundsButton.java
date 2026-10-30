/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.components.Button
 *  net.minecraft.client.gui.components.Button$Builder
 *  net.minecraft.resources.ResourceLocation
 */
package nonamecrackers2.witherstormmod.client.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.resources.ResourceLocation;

public class RefreshSoundsButton
extends Button {
    private static final ResourceLocation SOUND_REFRESHER = new ResourceLocation("witherstormmod", "textures/gui/sound_refresher.png");

    public RefreshSoundsButton(Button.Builder builder) {
        super(builder);
    }

    public void m_87963_(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
        int x = 0;
        int y = 0;
        if (!this.f_93623_) {
            x = 20;
        }
        if (this.m_198029_()) {
            y = 20;
        }
        RenderSystem.enableDepthTest();
        stack.m_280163_(SOUND_REFRESHER, this.m_252754_(), this.m_252907_(), (float)x, (float)y, this.f_93618_, this.f_93619_, 256, 256);
    }
}

