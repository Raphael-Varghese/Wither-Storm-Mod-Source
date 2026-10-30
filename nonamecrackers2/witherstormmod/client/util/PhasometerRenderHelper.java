/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Font
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.item.ItemStack
 */
package nonamecrackers2.witherstormmod.client.util;

import java.util.List;
import java.util.Objects;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import nonamecrackers2.witherstormmod.common.item.PhasometerItem;

public class PhasometerRenderHelper {
    public static void renderPhasometerOverlay(ItemStack item, GuiGraphics stack, float partialTicks, int width, int height, String dotDotDot) {
        CompoundTag tag = item.m_41784_();
        Minecraft mc = Minecraft.m_91087_();
        if (tag.m_128441_(PhasometerItem.DataEntry.PHASE.tagName)) {
            List<PhasometerItem.DataEntry> entries = PhasometerItem.getEntries(tag);
            for (int i = 0; i < entries.size(); ++i) {
                Component text = entries.get(i).getDisplayText(tag);
                Font font = mc.f_91062_;
                int n = width / 2;
                Objects.requireNonNull(mc.f_91062_);
                stack.m_280653_(font, text, n, 30 + i * (9 + 2), -1);
            }
        } else {
            Object text = tag.m_128441_(PhasometerItem.DataEntry.OBSTRUCTED.tagName) ? PhasometerItem.DataEntry.OBSTRUCTED.getDisplayText(tag) : Component.m_237110_((String)"description.phasometer.searching", (Object[])new Object[]{dotDotDot}).m_130940_(ChatFormatting.GRAY);
            stack.m_280653_(mc.f_91062_, text, width / 2, 30, -1);
        }
    }
}

