/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.renderer.PanoramaRenderer
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.Constant
 *  org.spongepowered.asm.mixin.injection.ModifyConstant
 */
package nonamecrackers2.witherstormmod.mixin;

import javax.annotation.Nullable;
import net.minecraft.client.renderer.PanoramaRenderer;
import nonamecrackers2.witherstormmod.client.util.PanoramaExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value={PanoramaRenderer.class})
public abstract class MixinPanoramaRenderer
implements PanoramaExtensions {
    @Shadow
    private float f_244569_;
    @Nullable
    private Float xOffset;

    @Override
    public void setXOffset(float offset) {
        this.xOffset = Float.valueOf(offset);
    }

    @Override
    public void setStartingSpin(float spin) {
        this.f_244569_ = spin;
    }

    @ModifyConstant(method={"render"}, constant={@Constant(floatValue=10.0f)})
    private float modifyXOffset(float offset) {
        return this.xOffset != null ? this.xOffset.floatValue() : offset;
    }
}

