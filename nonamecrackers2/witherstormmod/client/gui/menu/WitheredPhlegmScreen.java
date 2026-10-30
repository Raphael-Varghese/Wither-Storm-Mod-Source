/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.inventory.AbstractContainerMenu
 */
package nonamecrackers2.witherstormmod.client.gui.menu;

import java.util.Objects;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.blockentity.inventory.WitheredPhlegmMenu;

public class WitheredPhlegmScreen
extends AbstractContainerScreen<WitheredPhlegmMenu> {
    public static final ResourceLocation CONTAINER_BACKGROUND = WitherStormMod.id("textures/gui/container/withered_phlegm.png");

    public WitheredPhlegmScreen(WitheredPhlegmMenu menu, Inventory inventory, Component title) {
        super((AbstractContainerMenu)menu, inventory, title);
        this.f_97727_ = 207;
        this.f_97731_ = 114;
    }

    public void m_88315_(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
        this.m_280273_(stack);
        super.m_88315_(stack, mouseX, mouseY, partialTicks);
        int xp = ((WitheredPhlegmMenu)this.f_97732_).getXp();
        if (xp > 0) {
            int x = this.f_96543_ / 2 - this.f_97726_ / 2 + this.f_97728_;
            int y = this.f_96544_ / 2 - this.f_97727_ / 2 + this.f_97731_ - 20;
            MutableComponent mutableComponent = Component.m_237113_((String)"XP:");
            Objects.requireNonNull(this.f_96547_);
            stack.m_280614_(this.f_96547_, (Component)mutableComponent, x, y - 9 - 2, 0x404040, false);
            String level = String.valueOf(xp);
            stack.drawString(this.f_96547_, level, (float)(x + 1), (float)y, 0, false);
            stack.drawString(this.f_96547_, level, (float)(x - 1), (float)y, 0, false);
            stack.drawString(this.f_96547_, level, (float)x, (float)(y + 1), 0, false);
            stack.drawString(this.f_96547_, level, (float)x, (float)(y - 1), 0, false);
            stack.drawString(this.f_96547_, level, (float)x, (float)y, 8453920, false);
        }
        this.m_280072_(stack, mouseX, mouseY);
    }

    protected void m_7286_(GuiGraphics stack, float partialTick, int mouseX, int mouseY) {
        int x = (this.f_96543_ - this.f_97726_) / 2;
        int y = (this.f_96544_ - this.f_97727_) / 2;
        stack.m_280218_(CONTAINER_BACKGROUND, x, y, 0, 0, this.f_97726_, this.f_97727_);
    }
}

