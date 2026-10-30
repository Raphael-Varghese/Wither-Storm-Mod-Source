/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.SkeletonModel
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.SkeletonRenderer
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.monster.AbstractSkeleton
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.common.entity.SickenedSkeleton;

public class SickenedSkeletonRenderer
extends SkeletonRenderer {
    private static final ResourceLocation SICKENED_SKELETON_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_skeleton.png");
    private static final ResourceLocation SICKENED_SKELETON_EMISSIVE_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_skeleton_emissive.png");

    public SickenedSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context, WitherStormModRenderers.SICKENED_SKELETON, WitherStormModRenderers.SICKENED_SKELETON_INNER_ARMOR, WitherStormModRenderers.SICKENED_SKELETON_OUTER_ARMOR);
        this.m_115326_((RenderLayer)new EyesLayer<AbstractSkeleton, SkeletonModel<AbstractSkeleton>>((RenderLayerParent)this){

            public RenderType m_5708_() {
                return RenderType.m_110488_((ResourceLocation)SICKENED_SKELETON_EMISSIVE_LOCATION);
            }
        });
    }

    public ResourceLocation m_5478_(AbstractSkeleton entity) {
        return SICKENED_SKELETON_LOCATION;
    }

    protected boolean m_5936_(AbstractSkeleton entity) {
        return super.m_5936_(entity) || ((SickenedSkeleton)entity).isConverting();
    }
}

