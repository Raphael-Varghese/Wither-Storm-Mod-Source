/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.resources.ResourceLocation
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.TaintedSlimeModel;
import nonamecrackers2.witherstormmod.common.entity.TaintedSlime;

public class TaintedSlimeRenderer
extends MobRenderer<TaintedSlime, TaintedSlimeModel<TaintedSlime>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/tainted_slime/tainted_slime.png");

    public TaintedSlimeRenderer(EntityRendererProvider.Context context) {
        super(context, new TaintedSlimeModel(context.m_174023_(WitherStormModRenderers.TAINTED_SLIME)), 0.25f);
    }

    public ResourceLocation getTextureLocation(TaintedSlime slime) {
        return TEXTURE;
    }
}

