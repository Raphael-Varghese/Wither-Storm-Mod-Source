/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.Util
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.ai.attributes.AttributeInstance
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraftforge.client.event.RenderGuiOverlayEvent$Pre
 *  net.minecraftforge.client.gui.overlay.ForgeGui
 *  net.minecraftforge.client.gui.overlay.VanillaGuiOverlay
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.client.event;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEffects;

public class RenderWitherSicknessOverlay {
    private static final ResourceLocation WITHER_SICKNESS_ICONS = new ResourceLocation("witherstormmod", "textures/gui/wither_sickness.png");
    private final RandomSource random = RandomSource.m_216327_();
    private int lastHealth;
    private int displayHealth;
    private long lastHealthTime;
    private long healthBlinkTime;

    @SubscribeEvent
    public void renderOverlay(RenderGuiOverlayEvent.Pre event) {
        LocalPlayer player;
        Minecraft mc = Minecraft.m_91087_();
        ForgeGui gui = (ForgeGui)mc.f_91065_;
        if (event.getOverlay() == VanillaGuiOverlay.PLAYER_HEALTH.type() && !mc.f_91066_.f_92062_ && gui.shouldDrawSurvivalElements() && (player = mc.f_91074_).m_21023_((MobEffect)WitherStormModEffects.WITHER_SICKNESS.get())) {
            boolean highlight;
            gui.setupOverlayRenderState(true, false);
            int width = event.getWindow().m_85445_();
            int height = event.getWindow().m_85446_();
            event.setCanceled(true);
            RenderSystem.enableBlend();
            int health = Mth.m_14167_((float)player.m_21223_());
            int tickCount = gui.m_93079_();
            boolean bl = highlight = this.healthBlinkTime > (long)tickCount && (this.healthBlinkTime - (long)tickCount) / 3L % 2L == 1L;
            if (health < this.lastHealth && player.f_19802_ > 0) {
                this.lastHealthTime = Util.m_137550_();
                this.healthBlinkTime = tickCount + 20;
            } else if (health > this.lastHealth && player.f_19802_ > 0) {
                this.lastHealthTime = Util.m_137550_();
                this.healthBlinkTime = tickCount + 10;
            }
            if (Util.m_137550_() - this.lastHealthTime > 1000L) {
                this.lastHealth = health;
                this.displayHealth = health;
                this.lastHealthTime = Util.m_137550_();
            }
            this.lastHealth = health;
            int healthLast = this.displayHealth;
            AttributeInstance maxHealth = player.m_21051_(Attributes.f_22276_);
            float healthMax = (float)maxHealth.m_22135_();
            int absorbtion = Mth.m_14167_((float)player.m_6103_());
            int healthRows = Mth.m_14167_((float)((healthMax + (float)absorbtion) / 2.0f / 10.0f));
            int rowHeight = Math.max(10 - (healthRows - 2), 3);
            this.random.m_188584_((long)(tickCount * 312871));
            int left = width / 2 - 91;
            int top = height - gui.leftHeight;
            gui.leftHeight += healthRows * rowHeight;
            if (rowHeight != 10) {
                gui.leftHeight += 10 - rowHeight;
            }
            int regen = -1;
            if (player.m_21023_(MobEffects.f_19605_)) {
                regen = tickCount % Mth.m_14167_((float)(healthMax + 5.0f));
            }
            int TOP = player.m_9236_().m_6106_().m_5466_() ? 9 : 0;
            int BACKGROUND = highlight ? 25 : 16;
            int margin = 34;
            float absorbtionRemaining = absorbtion;
            for (int i = Mth.m_14167_((float)((healthMax + (float)absorbtion) / 2.0f)) - 1; i >= 0; --i) {
                int row = Mth.m_14167_((float)((float)(i + 1) / 10.0f)) - 1;
                int x = left + i % 10 * 8;
                int y = top - row * rowHeight;
                if (health <= 4) {
                    y += this.random.m_188503_(2);
                }
                if (i == regen) {
                    y -= 2;
                }
                event.getGuiGraphics().m_280218_(WITHER_SICKNESS_ICONS, x, y, BACKGROUND, TOP, 9, 9);
                if (highlight) {
                    if (i * 2 + 1 < healthLast) {
                        event.getGuiGraphics().m_280218_(WITHER_SICKNESS_ICONS, x, y, margin, TOP, 9, 9);
                    } else if (i * 2 + 1 == healthLast) {
                        event.getGuiGraphics().m_280218_(WITHER_SICKNESS_ICONS, x, y, margin + 9, TOP, 9, 9);
                    }
                }
                if (absorbtionRemaining > 0.0f) {
                    if (absorbtionRemaining == (float)absorbtion && (float)absorbtion % 2.0f == 1.0f) {
                        event.getGuiGraphics().m_280218_(WITHER_SICKNESS_ICONS, x, y, margin + 9, TOP, 9, 9);
                        absorbtionRemaining -= 1.0f;
                        continue;
                    }
                    event.getGuiGraphics().m_280218_(WITHER_SICKNESS_ICONS, x, y, margin, TOP, 9, 9);
                    absorbtionRemaining -= 2.0f;
                    continue;
                }
                if (i * 2 + 1 < health) {
                    event.getGuiGraphics().m_280218_(WITHER_SICKNESS_ICONS, x, y, margin, TOP, 9, 9);
                    continue;
                }
                if (i * 2 + 1 != health) continue;
                event.getGuiGraphics().m_280218_(WITHER_SICKNESS_ICONS, x, y, margin + 9, TOP, 9, 9);
            }
            RenderSystem.disableBlend();
        }
    }
}

