/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.resources.ResourceLocation
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import nonamecrackers2.witherstormmod.client.renderer.entity.FlamingWitherSkullRenderer;
import nonamecrackers2.witherstormmod.common.entity.BlueFlamingWitherSkullEntity;

public class BlueFlamingWitherSkullRenderer
extends FlamingWitherSkullRenderer<BlueFlamingWitherSkullEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/flaming_wither_skull/blue_flaming_wither_skull.png");
    private static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/flaming_wither_skull/blue_flaming_wither_skull_emissive.png");

    public BlueFlamingWitherSkullRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(BlueFlamingWitherSkullEntity entity) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getEmissiveTextureLocation(BlueFlamingWitherSkullEntity entity) {
        return EMISSIVE;
    }
}

