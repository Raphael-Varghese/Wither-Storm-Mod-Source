/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.CatModel
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.CatRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.animal.Cat
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import net.minecraft.client.model.CatModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cat;
import nonamecrackers2.witherstormmod.common.entity.WitherSickened;

public class SickenedCatRenderer
extends CatRenderer {
    public static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_cat.png");
    public static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_cat_emissive.png");

    public SickenedCatRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.m_115326_((RenderLayer)new EyesLayer<Cat, CatModel<Cat>>((RenderLayerParent)this){

            public RenderType m_5708_() {
                return RenderType.m_110488_((ResourceLocation)EMISSIVE);
            }
        });
    }

    public ResourceLocation m_5478_(Cat cat) {
        return TEXTURE;
    }

    protected boolean isShaking(Cat entity) {
        return super.m_5936_((LivingEntity)entity) || ((WitherSickened)entity).isConverting();
    }
}

