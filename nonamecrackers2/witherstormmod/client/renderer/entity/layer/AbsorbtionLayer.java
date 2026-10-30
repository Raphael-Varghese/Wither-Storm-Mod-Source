/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.targeting.TargetingConditions
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import nonamecrackers2.witherstormmod.client.rendertype.UtilRenderTypes;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class AbsorbtionLayer<T extends LivingEntity, M extends EntityModel<T>>
extends RenderLayer<T, M> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/misc/absorbtion.png");
    private static final RenderType RENDER_TYPE = UtilRenderTypes.entityDecalTranslucent(TEXTURE);

    public AbsorbtionLayer(RenderLayerParent<T, M> parent) {
        super(parent);
    }

    public void render(PoseStack stack, MultiBufferSource buffer, int p_117351_, T entity, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
        float alpha;
        WitherStormEntity storm;
        if (!entity.m_20145_() && (storm = (WitherStormEntity)entity.m_9236_().m_45963_(WitherStormEntity.class, TargetingConditions.m_148353_(), null, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), entity.m_20191_().m_82400_(50.0))) != null && storm.getPhase() > 3 && !storm.isDeadOrPlayingDead() && (alpha = Mth.m_14036_((float)((30.0f - (float)storm.m_146892_().m_82554_(entity.m_146892_())) * 0.1f), (float)0.0f, (float)0.9f)) > 0.0f) {
            this.renderOverlay(stack, buffer, p_117351_, entity, alpha);
        }
    }

    private void renderOverlay(PoseStack stack, MultiBufferSource buffer, int packedLight, T entity, float alpha) {
        this.m_117386_().m_7695_(stack, buffer.m_6299_(RENDER_TYPE), packedLight, LivingEntityRenderer.m_115338_(entity, (float)0.0f), 1.0f, 1.0f, 1.0f, alpha);
    }
}

