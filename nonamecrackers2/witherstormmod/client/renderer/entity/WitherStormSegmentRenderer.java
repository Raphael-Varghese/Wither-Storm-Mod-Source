/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.client.renderer.entity.AbstractWitherStormRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.AbstractWitherStormModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormSegmentModel;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;

public class WitherStormSegmentRenderer
extends AbstractWitherStormRenderer<WitherStormSegmentEntity, AbstractWitherStormModel<WitherStormSegmentEntity>> {
    private final WitherStormSegmentModel<WitherStormSegmentEntity> segmentModel;

    public WitherStormSegmentRenderer(EntityRendererProvider.Context context) {
        this(context, new WitherStormSegmentModel<WitherStormSegmentEntity>(context.m_174023_(WitherStormModRenderers.WITHER_STORM_SEGMENT)));
    }

    private WitherStormSegmentRenderer(EntityRendererProvider.Context context, WitherStormSegmentModel<WitherStormSegmentEntity> base) {
        super(context, base);
        this.segmentModel = base;
    }

    @Override
    public AbstractWitherStormModel<WitherStormSegmentEntity> fetchModel(WitherStormSegmentEntity entity) {
        return this.segmentModel;
    }

    @Override
    public int getPulseAmount(WitherStormSegmentEntity entity) {
        return (int)((float)entity.getPhase() * 5.0f / 2.0f * ((Boolean)WitherStormModConfig.CLIENT.lowResModels.get() != false ? 3.0f : 1.0f));
    }
}

