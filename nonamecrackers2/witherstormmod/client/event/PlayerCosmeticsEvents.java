/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraftforge.client.event.RenderPlayerEvent$Post
 *  net.minecraftforge.client.model.data.ModelData
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.client.event;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.client.util.Contributors;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;

public class PlayerCosmeticsEvents {
    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent.Post event) {
        Player player = event.getEntity();
        if ((((Boolean)WitherStormModConfig.CLIENT.patronCosmetic.get()).booleanValue() || player != Minecraft.m_91087_().f_91074_) && !player.m_20145_()) {
            int overlay;
            float scale;
            float tickCount;
            String name = player.m_36316_().getName();
            BlockState state = null;
            BlockState devState = null;
            BlockState kofiState = null;
            if (Contributors.isDeveloper(name)) {
                devState = Blocks.f_50272_.m_49966_();
            }
            if (Contributors.isPatron(name)) {
                state = ((Block)WitherStormModBlocks.FORMIDIBOMB.get()).m_49966_();
            }
            if (Contributors.isKofi(name)) {
                kofiState = ((Block)WitherStormModBlocks.TAINTED_ZOMBIE_LYING.get()).m_49966_();
            }
            Minecraft mc = Minecraft.m_91087_();
            if (state != null) {
                PoseStack stack = event.getPoseStack();
                stack.m_85836_();
                tickCount = (float)player.f_19797_ + event.getPartialTick();
                stack.m_252781_(Axis.f_252436_.m_252977_(tickCount));
                stack.m_85837_(0.6, (double)(player.m_20206_() + 0.2f), 0.0);
                stack.m_85836_();
                scale = (Mth.m_14031_((float)(tickCount * 0.1f)) + 10.0f) * 0.025f;
                stack.m_252781_(Axis.f_252529_.m_252977_(tickCount));
                stack.m_252781_(Axis.f_252393_.m_252977_(tickCount * 4.0f));
                stack.m_85841_(scale, scale, scale);
                stack.m_85837_(-0.5, -0.5, -0.5);
                overlay = OverlayTexture.f_118083_;
                if (player.f_19797_ / 20 % 5 == 0 && Contributors.isPatron(name)) {
                    overlay = OverlayTexture.m_118093_((int)OverlayTexture.m_118088_((float)1.0f), (int)10);
                }
                mc.m_91289_().renderSingleBlock(state, stack, event.getMultiBufferSource(), 0xF000F0, overlay, ModelData.EMPTY, null);
                stack.m_85849_();
                stack.m_85849_();
            }
            if (devState != null) {
                PoseStack devStack = event.getPoseStack();
                devStack.m_85836_();
                tickCount = (float)player.f_19797_ + event.getPartialTick();
                devStack.m_252781_(Axis.f_252436_.m_252977_(tickCount));
                devStack.m_85837_(-0.6, (double)(player.m_20206_() + 0.2f), 0.0);
                devStack.m_85836_();
                scale = (Mth.m_14031_((float)(tickCount * 0.1f)) + 10.0f) * 0.025f;
                devStack.m_252781_(Axis.f_252529_.m_252977_(tickCount));
                devStack.m_252781_(Axis.f_252393_.m_252977_(tickCount * 4.0f));
                devStack.m_85841_(scale, scale, scale);
                devStack.m_85837_(-0.5, -0.5, -0.5);
                overlay = OverlayTexture.f_118083_;
                if (player.f_19797_ / 20 % 5 == 0 && Contributors.isPatron(name)) {
                    overlay = OverlayTexture.m_118093_((int)OverlayTexture.m_118088_((float)1.0f), (int)10);
                }
                mc.m_91289_().renderSingleBlock(devState, devStack, event.getMultiBufferSource(), 0xF000F0, overlay, ModelData.EMPTY, null);
                devStack.m_85849_();
                devStack.m_85849_();
            }
            if (kofiState != null) {
                PoseStack kofiStack = event.getPoseStack();
                kofiStack.m_85836_();
                tickCount = (float)player.f_19797_ + event.getPartialTick();
                kofiStack.m_252781_(Axis.f_252436_.m_252977_(-tickCount));
                kofiStack.m_85837_(-0.6, (double)(player.m_20206_() + 0.2f), 1.0);
                kofiStack.m_85836_();
                scale = 0.2727f;
                kofiStack.m_252781_(Axis.f_252529_.m_252977_(-tickCount * 6.0f));
                kofiStack.m_252781_(Axis.f_252393_.m_252977_(-tickCount * 6.0f));
                kofiStack.m_85841_(scale, scale, scale);
                kofiStack.m_85837_(-0.5, -0.5, -0.5);
                overlay = OverlayTexture.f_118083_;
                mc.m_91289_().renderSingleBlock(kofiState, kofiStack, event.getMultiBufferSource(), 0xF000F0, overlay, ModelData.EMPTY, null);
                kofiStack.m_85849_();
                kofiStack.m_85849_();
            }
        }
    }
}

