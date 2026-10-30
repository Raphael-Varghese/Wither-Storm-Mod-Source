/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.SpriteLoader$Preparations
 *  net.minecraft.client.renderer.texture.TextureAtlas
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.client.renderer.texture.SpriteLoader;
import net.minecraft.client.renderer.texture.TextureAtlas;
import nonamecrackers2.witherstormmod.client.util.TextureAtlasAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={TextureAtlas.class})
public abstract class MixinTextureAtlas
implements TextureAtlasAccessor {
    @Unique
    private int totalWidth;
    @Unique
    private int totalHeight;

    @Inject(method={"upload"}, at={@At(value="TAIL")})
    public void uploadTail(SpriteLoader.Preparations preperations, CallbackInfo ci) {
        this.totalWidth = preperations.f_243669_();
        this.totalHeight = preperations.f_244632_();
    }

    @Override
    public int getHeight() {
        return this.totalHeight;
    }

    @Override
    public int getWidth() {
        return this.totalWidth;
    }
}

