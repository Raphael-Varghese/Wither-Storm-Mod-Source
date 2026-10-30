/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import nonamecrackers2.witherstormmod.client.util.PhasometerRenderHelper;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Gui.class})
public class MixinGui {
    @Unique
    private static final ResourceLocation PHASOMETER_SCOPE_TEXTURE = new ResourceLocation("witherstormmod", "textures/misc/phasometer_scope.png");
    @Final
    @Shadow
    protected Minecraft f_92986_;
    @Shadow
    protected int f_92977_;
    @Shadow
    protected int f_92978_;
    @Shadow
    protected int f_92989_;
    @Unique
    private String dotDotDot = "";

    @ModifyArg(method={"renderSpyglassOverlay"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIFFIIII)V"))
    public ResourceLocation renderSpyglassOverlayInvoke(ResourceLocation original) {
        if (this.f_92986_.f_91074_.m_21211_().m_150930_((Item)WitherStormModItems.PHASOMETER.get())) {
            return PHASOMETER_SCOPE_TEXTURE;
        }
        return original;
    }

    @Inject(method={"renderSpyglassOverlay"}, at={@At(value="TAIL")})
    public void renderSpyglassOverlayTail(GuiGraphics stack, float partialTicks, CallbackInfo ci) {
        ItemStack item = this.f_92986_.f_91074_.m_21211_();
        if (item.m_150930_((Item)WitherStormModItems.PHASOMETER.get())) {
            PhasometerRenderHelper.renderPhasometerOverlay(item, stack, partialTicks, this.f_92977_, this.f_92978_, this.dotDotDot);
        }
    }

    @Inject(method={"tick()V"}, at={@At(value="TAIL")})
    public void tickTail(CallbackInfo ci) {
        if (this.f_92989_ % 5 == 0) {
            this.dotDotDot = this.dotDotDot.length() >= 3 ? "" : this.dotDotDot + ".";
        }
    }
}

