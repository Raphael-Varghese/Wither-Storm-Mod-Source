/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.client.extensions.common.IClientItemExtensions
 */
package nonamecrackers2.witherstormmod.client.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class TaintedCarvedPumpkinExtensions
implements IClientItemExtensions {
    public static final TaintedCarvedPumpkinExtensions INSTANCE = new TaintedCarvedPumpkinExtensions();
    private static final ResourceLocation TAINTED_PUMPKIN_BLUR = new ResourceLocation("witherstormmod", "textures/misc/tainted_pumpkin_blur.png");

    private TaintedCarvedPumpkinExtensions() {
    }

    public void renderHelmetOverlay(ItemStack stack, Player player, int width, int height, float partialTick) {
        GuiGraphics graphics = new GuiGraphics(Minecraft.m_91087_(), Minecraft.m_91087_().m_91269_().m_110104_());
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        graphics.m_280246_(1.0f, 1.0f, 1.0f, 1.0f);
        graphics.m_280398_(TAINTED_PUMPKIN_BLUR, 0, 0, -90, 0.0f, 0.0f, width, height, width, height);
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        graphics.m_280246_(1.0f, 1.0f, 1.0f, 1.0f);
    }
}

