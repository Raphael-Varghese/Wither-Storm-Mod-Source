/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.client.renderer.GameRenderer
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package nonamecrackers2.witherstormmod.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
import nonamecrackers2.witherstormmod.client.instancing.RenderBufferer;
import nonamecrackers2.witherstormmod.client.shader.PostProcessingShaders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={GameRenderer.class})
public class MixinGameRenderer {
    @Inject(method={"renderLevel"}, at={@At(value="TAIL")})
    public void renderLevelTail(float partialTicks, long l, PoseStack stack, CallbackInfo ci) {
        PostProcessingShaders.INSTANCE.renderShaders(partialTicks);
    }

    @Inject(method={"bobView"}, at={@At(value="TAIL")})
    public void bobViewTail(PoseStack stack, float partialTicks, CallbackInfo ci) {
        Minecraft mc = Minecraft.m_91087_();
        Entity entity = mc.m_91288_();
        if (entity instanceof LocalPlayer) {
            LocalPlayer player = (LocalPlayer)entity;
            player.getCapability(WitherStormModClientCapabilities.CAMERA_SHAKER).ifPresent(shaker -> {
                float x = shaker.getXShake(partialTicks);
                float y = shaker.getYShake(partialTicks);
                stack.m_85837_((double)Mth.m_14031_((float)((float)Math.toRadians(x))), (double)Mth.m_14031_((float)((float)Math.toRadians(y))), 0.0);
            });
        }
    }

    @Inject(method={"close"}, at={@At(value="TAIL")})
    public void witherstormmod$shutdownRenderBufferer_close(CallbackInfo ci) {
        RenderBufferer.INSTANCE.shutdown();
    }
}

