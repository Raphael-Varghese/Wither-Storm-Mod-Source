/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.model.ZombieVillagerModel
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.HumanoidMobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.entity.layers.VillagerProfessionLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ZombieVillagerModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.VillagerProfessionLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.common.entity.SickenedVillager;

public class SickenedVillagerRenderer
extends HumanoidMobRenderer<SickenedVillager, ZombieVillagerModel<SickenedVillager>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_villager.png");
    private static final ResourceLocation TEXTURE_EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/sickened/sickened_villager_emissive.png");

    public SickenedVillagerRenderer(EntityRendererProvider.Context context) {
        super(context, (HumanoidModel)new ZombieVillagerModel(context.m_174023_(WitherStormModRenderers.SICKENED_VILLAGER)), 0.5f);
        this.m_115326_((RenderLayer)new HumanoidArmorLayer((RenderLayerParent)this, (HumanoidModel)new ZombieVillagerModel(context.m_174023_(WitherStormModRenderers.SICKENED_VILLAGER_INNER_ARMOR)), (HumanoidModel)new ZombieVillagerModel(context.m_174023_(WitherStormModRenderers.SICKENED_VILLAGER_OUTER_ARMOR)), context.m_266367_()));
        this.m_115326_((RenderLayer)new VillagerProfessionLayer((RenderLayerParent)this, context.m_174026_(), "zombie_villager"));
        this.m_115326_((RenderLayer)new EyesLayer<SickenedVillager, ZombieVillagerModel<SickenedVillager>>((RenderLayerParent)this){

            public RenderType m_5708_() {
                return RenderType.m_110488_((ResourceLocation)TEXTURE_EMISSIVE);
            }
        });
    }

    public ResourceLocation getTextureLocation(SickenedVillager entity) {
        return TEXTURE;
    }

    protected boolean isShaking(SickenedVillager entity) {
        return super.m_5936_((LivingEntity)entity) || entity.isConverting();
    }
}

