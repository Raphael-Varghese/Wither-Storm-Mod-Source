/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.Util
 *  net.minecraft.client.gui.screens.TitleScreen
 *  net.minecraft.client.renderer.CubeMap
 *  net.minecraft.client.renderer.PanoramaRenderer
 *  net.minecraft.resources.ResourceLocation
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.Util;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.resources.ResourceLocation;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.mixin.MixinCubeMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={TitleScreen.class})
public class MixinTitleScreen {
    private static final CubeMap WITHERSTORMMOD_CUBE_MAP = (CubeMap)Util.m_137469_((Object)new CubeMap(new ResourceLocation("textures/gui/title/background/panorama")), map -> {
        ((MixinCubeMap)map).getImages()[0] = new ResourceLocation("witherstormmod", "textures/gui/title/background/panorama_0.png");
    });
    private final PanoramaRenderer witherstormmodPanorama = new PanoramaRenderer(WITHERSTORMMOD_CUBE_MAP);

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/renderer/PanoramaRenderer;render(FF)V"))
    public void render_overridePanorama(PanoramaRenderer panorama, float partialTicks, float alpha) {
        if (((Boolean)WitherStormModConfig.CLIENT.customPanorama.get()).booleanValue()) {
            this.witherstormmodPanorama.m_110003_(partialTicks, alpha);
        } else {
            panorama.m_110003_(partialTicks, alpha);
        }
    }
}

