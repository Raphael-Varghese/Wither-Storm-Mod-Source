/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ZombieModel
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.AbstractZombieRenderer
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.monster.Zombie
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.common.entity.SickenedZombie;

public class SickenedZombieRenderer
extends AbstractZombieRenderer<SickenedZombie, ZombieModel<SickenedZombie>> {
    private static final ResourceLocation SICKENED_ZOMBIE_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_zombie.png");
    private static final ResourceLocation SICKENED_ZOMBIE_EMISSIVE_LOCATION = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_zombie_emissive.png");

    public SickenedZombieRenderer(EntityRendererProvider.Context context) {
        super(context, new ZombieModel(context.m_174023_(WitherStormModRenderers.SICKENED_ZOMBIE)), new ZombieModel(context.m_174023_(WitherStormModRenderers.SICKENED_ZOMBIE_INNER_ARMOR)), new ZombieModel(context.m_174023_(WitherStormModRenderers.SICKENED_ZOMBIE_OUTER_ARMOR)));
        this.m_115326_((RenderLayer)new EyesLayer<SickenedZombie, ZombieModel<SickenedZombie>>((RenderLayerParent)this){

            public RenderType m_5708_() {
                return RenderType.m_110488_((ResourceLocation)SICKENED_ZOMBIE_EMISSIVE_LOCATION);
            }
        });
    }

    public ResourceLocation m_5478_(Zombie zombie) {
        return SICKENED_ZOMBIE_LOCATION;
    }

    protected boolean isShaking(SickenedZombie entity) {
        return super.m_5936_((Zombie)entity) || entity.isConverting();
    }
}

