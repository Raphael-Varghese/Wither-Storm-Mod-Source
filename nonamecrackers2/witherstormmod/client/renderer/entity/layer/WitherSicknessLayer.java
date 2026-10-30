/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.model.VillagerHeadModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraftforge.fml.ModList
 *  nonamecrackers2.crackerslib.common.compat.CompatHelper
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.VillagerHeadModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.ModList;
import nonamecrackers2.crackerslib.common.compat.CompatHelper;
import nonamecrackers2.witherstormmod.client.rendertype.UtilRenderTypes;
import nonamecrackers2.witherstormmod.client.util.TiledTextureGenerator;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;

public class WitherSicknessLayer<T extends LivingEntity, M extends EntityModel<T>>
extends RenderLayer<T, M> {
    private static final ResourceLocation WITHER_SICKNESS_LAYER_64 = new ResourceLocation("witherstormmod", "textures/entity/wither_sickness_layer/wither_sickness_layer.png");
    private final M model;

    public WitherSicknessLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
        this.model = renderer.m_7200_();
    }

    public void render(PoseStack stack, MultiBufferSource buffer, int packedLight, T entity, float p_225628_5_, float p_225628_6_, float partialTicks, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        ResourceLocation location = WITHER_SICKNESS_LAYER_64;
        entity.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> {
            if (!tracker.isActuallyImmune() && (tracker.isInfected() || tracker.isBeingCured()) && !entity.m_20145_()) {
                M model = this.getModel();
                if (model instanceof VillagerHeadModel) {
                    VillagerHeadModel m = (VillagerHeadModel)model;
                    m.m_7491_(false);
                }
                Object consumer = CompatHelper.isSodiumLoaded() && !ModList.get().isLoaded("embeddium") ? buffer.m_6299_(UtilRenderTypes.entityDecalTranslucent(location)) : new TiledTextureGenerator(buffer.m_6299_(UtilRenderTypes.entityDecalTranslucent(location)), stack, 0.25f);
                float healthRatio = 0.8f - entity.m_21223_() / entity.m_21233_();
                float alpha = (float)tracker.getDelayTicks() / (float)tracker.getApplicationDelay() * 0.5f * ((Mth.m_14089_((float)(((float)entity.f_19797_ + partialTicks) * healthRatio)) + 2.0f) * 0.25f) + 0.2f;
                if (tracker.isBeingCured()) {
                    alpha = ((float)tracker.getCureDelay() - (float)tracker.getCureDelayTicks()) / (float)tracker.getCureDelay() * 0.5f * (alpha * 2.0f);
                }
                this.getModel().m_7695_(stack, consumer, packedLight, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, alpha);
                if (model instanceof VillagerHeadModel) {
                    VillagerHeadModel m = (VillagerHeadModel)model;
                    m.m_7491_(true);
                }
            }
        });
    }

    public M getModel() {
        return this.model;
    }
}

