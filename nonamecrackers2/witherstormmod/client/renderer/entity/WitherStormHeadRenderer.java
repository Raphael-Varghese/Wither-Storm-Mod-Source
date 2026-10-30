/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.MobRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Mob
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import java.time.temporal.ChronoField;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.client.renderer.entity.layer.WitherStormHeadEyesLayer;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.WitherStormHeadModel;
import nonamecrackers2.witherstormmod.client.resources.WitherStormResourceConfigManager;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity;

public class WitherStormHeadRenderer
extends MobRenderer<WitherStormHeadEntity, WitherStormHeadModel> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/wither_storm_head/wither_storm_head.png");
    public static final ResourceLocation TEXTURE_HURT = new ResourceLocation("witherstormmod", "textures/entity/wither_storm_head/wither_storm_head_hurt.png");
    public static final ResourceLocation EMISSIVE = new ResourceLocation("witherstormmod", "textures/entity/wither_storm_head/wither_storm_head_emissive.png");
    public static final ResourceLocation EMISSIVE_HURT = new ResourceLocation("witherstormmod", "textures/entity/wither_storm_head/wither_storm_head_emissive_hurt.png");

    public WitherStormHeadRenderer(EntityRendererProvider.Context context) {
        super(context, (EntityModel)new WitherStormHeadModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_HEAD)), 3.5f);
        this.m_115326_((RenderLayer)new WitherStormHeadEyesLayer((RenderLayerParent<WitherStormHeadEntity, WitherStormHeadModel>)this));
    }

    protected int getBlockLightLevel(WitherStormHeadEntity entity, BlockPos pos) {
        return Math.max(0, (int)((100.0f - entity.getFadeAnimation()) / 4.0f - 10.0f));
    }

    public void render(WitherStormHeadEntity entity, float p_225623_2_, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
        super.m_7392_((Mob)entity, p_225623_2_, partialTicks, stack, buffer, packedLight);
        if (((Boolean)WitherStormModConfig.CLIENT.renderTractorBeams.get()).booleanValue() && !entity.isPlayingDead() && !entity.isHurt() && ((WitherStormHeadModel)this.m_7200_()).getHead().shouldRenderTractorBeam(entity, 0)) {
            int month = WitherStormMod.DATE.get(ChronoField.MONTH_OF_YEAR);
            int day = WitherStormMod.DATE.get(ChronoField.DAY_OF_MONTH);
            boolean flag = month == 10 && day == 31;
            Color color = WitherStormResourceConfigManager.INSTANCE.getColorSetByPhase(4).tractorBeamColor();
            float r = flag ? 0.5294118f : (float)color.getRed() / 255.0f;
            float g = flag ? 0.32156864f : (float)color.getGreen() / 255.0f;
            float b = flag ? 0.10980392f : (float)color.getBlue() / 255.0f;
            ((WitherStormHeadModel)this.m_7200_()).getHead().renderTractorBeam(entity, stack, buffer, packedLight, r, g, b, 0.5f, partialTicks, -1.0, 1.0f);
        }
    }

    public ResourceLocation getTextureLocation(WitherStormHeadEntity entity) {
        return entity.isHurt() ? TEXTURE_HURT : TEXTURE;
    }

    protected void scale(WitherStormHeadEntity entity, PoseStack stack, float p_225620_3_) {
        stack.m_85841_(2.0f, 2.0f, 2.0f);
    }
}

