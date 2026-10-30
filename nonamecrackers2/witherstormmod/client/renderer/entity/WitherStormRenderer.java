/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRendererProvider$Context
 *  org.joml.Matrix4f
 */
package nonamecrackers2.witherstormmod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import java.util.Random;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import nonamecrackers2.witherstormmod.client.init.WitherStormModRenderers;
import nonamecrackers2.witherstormmod.client.renderer.entity.AbstractWitherStormRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.AbstractWitherStormModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormCommandBlockModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormDestroyerModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormDevourerModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormDismantledModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormEvolvedDestroyerModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormEvolvedDevourerModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormGrowingHunchbackModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback1_1Model;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback1_2Model;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback2_1Model;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback3_1Model;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback3_2Model;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchbackModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormIntermediateDevourerModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormIntermediateEvolvedDestroyerModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormIntermediateEvolvedDevourerModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormPregnantHunchbackModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormTornEvolvedDevourerModel;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import org.joml.Matrix4f;

public class WitherStormRenderer
extends AbstractWitherStormRenderer<WitherStormEntity, AbstractWitherStormModel<WitherStormEntity>> {
    private final WitherStormCommandBlockModel<WitherStormEntity> commandBlockModel;
    private final WitherStormDestroyerModel<WitherStormEntity> destroyerModel;
    private final WitherStormIntermediateEvolvedDestroyerModel<WitherStormEntity> intermediateEvolvedDestroyer;
    private final WitherStormEvolvedDestroyerModel<WitherStormEntity> evolvedDestroyerModel;
    private final WitherStormIntermediateDevourerModel<WitherStormEntity> intermediateDevourerModel;
    private final WitherStormDevourerModel<WitherStormEntity> devourerModel;
    private final WitherStormIntermediateEvolvedDevourerModel<WitherStormEntity> intermediateEvolvedDevourerModel;
    private final WitherStormEvolvedDevourerModel<WitherStormEntity> evolvedDevourerModel;
    private final WitherStormDismantledModel<WitherStormEntity> dismantledModel;
    private final WitherStormTornEvolvedDevourerModel<WitherStormEntity> tornModel;
    private final WitherStormHunchbackModel<WitherStormEntity> hunchbackP1;
    private final WitherStormHunchback1_1Model<WitherStormEntity> hunchbackP125;
    private final WitherStormHunchback1_2Model<WitherStormEntity> hunchbackP15;
    private final WitherStormGrowingHunchbackModel<WitherStormEntity> hunchbackP2;
    private final WitherStormHunchback2_1Model<WitherStormEntity> hunchbackP25;
    private final WitherStormPregnantHunchbackModel<WitherStormEntity> hunchbackP3;
    private final WitherStormHunchback3_1Model<WitherStormEntity> hunchbackP31;
    private final WitherStormHunchback3_2Model<WitherStormEntity> hunchbackP32;

    public WitherStormRenderer(EntityRendererProvider.Context context) {
        this(context, new WitherStormCommandBlockModel<WitherStormEntity>(context.m_174023_(WitherStormModRenderers.WITHER_STORM_0)));
    }

    private WitherStormRenderer(EntityRendererProvider.Context context, WitherStormCommandBlockModel<WitherStormEntity> base) {
        super(context, base);
        this.commandBlockModel = base;
        this.hunchbackP1 = new WitherStormHunchbackModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_1));
        this.hunchbackP125 = new WitherStormHunchback1_1Model(context.m_174023_(WitherStormModRenderers.WITHER_STORM_1_1));
        this.hunchbackP15 = new WitherStormHunchback1_2Model(context.m_174023_(WitherStormModRenderers.WITHER_STORM_1_2));
        this.hunchbackP2 = new WitherStormGrowingHunchbackModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_2));
        this.hunchbackP25 = new WitherStormHunchback2_1Model(context.m_174023_(WitherStormModRenderers.WITHER_STORM_2_1));
        this.hunchbackP3 = new WitherStormPregnantHunchbackModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_3));
        this.hunchbackP31 = new WitherStormHunchback3_1Model(context.m_174023_(WitherStormModRenderers.WITHER_STORM_3_1));
        this.hunchbackP32 = new WitherStormHunchback3_2Model(context.m_174023_(WitherStormModRenderers.WITHER_STORM_3_2));
        this.destroyerModel = new WitherStormDestroyerModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_4));
        this.intermediateEvolvedDestroyer = new WitherStormIntermediateEvolvedDestroyerModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_4_5));
        this.evolvedDestroyerModel = new WitherStormEvolvedDestroyerModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_5));
        this.intermediateDevourerModel = new WitherStormIntermediateDevourerModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_5_5));
        this.devourerModel = new WitherStormDevourerModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_6));
        this.intermediateEvolvedDevourerModel = new WitherStormIntermediateEvolvedDevourerModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_6_5));
        this.evolvedDevourerModel = new WitherStormEvolvedDevourerModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_7));
        this.dismantledModel = new WitherStormDismantledModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_DISMANTLED));
        this.tornModel = new WitherStormTornEvolvedDevourerModel(context.m_174023_(WitherStormModRenderers.WITHER_STORM_TORN));
    }

    @Override
    public void render(WitherStormEntity entity, float yRot, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, yRot, partialTicks, stack, buffer, packedLight);
        if (entity.getDeathTime() > 0) {
            float f1 = ((float)entity.getDeathTime() + partialTicks) / 200.0f;
            float f2 = Math.min(f1 > 1.6f ? (f1 - 1.6f) / 0.2f : 0.0f, 1.0f);
            Random random = new Random(382L);
            VertexConsumer builder = buffer.m_6299_(RenderType.m_110502_());
            stack.m_85836_();
            stack.m_85837_(0.0, (double)entity.getUnmodifiedHeight() / 2.0, 0.0);
            int i = 0;
            while ((float)i < (f1 + f1 * f1) / 2.0f * 60.0f) {
                stack.m_252781_(Axis.f_252529_.m_252977_(random.nextFloat() * 360.0f));
                stack.m_252781_(Axis.f_252436_.m_252977_(random.nextFloat() * 360.0f));
                stack.m_252781_(Axis.f_252403_.m_252977_(random.nextFloat() * 360.0f));
                stack.m_252781_(Axis.f_252529_.m_252977_(random.nextFloat() * 360.0f));
                stack.m_252781_(Axis.f_252436_.m_252977_(random.nextFloat() * 360.0f));
                stack.m_252781_(Axis.f_252403_.m_252977_(random.nextFloat() * 360.0f + f1 * 90.0f));
                float f3 = random.nextFloat() * (entity.getUnmodifiedSize() / 1.5f) * 2.5f + 5.0f + f2 * 10.0f;
                float f4 = random.nextFloat() * 10.0f + 1.0f + f2 * 2.0f;
                Matrix4f matrix4f = stack.m_85850_().m_252922_();
                int k = (int)(255.0f * (1.0f - f2));
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_6122_(255, 255, 255, k).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_6122_(255, 255, 255, k).m_5752_();
                builder.m_252986_(matrix4f, -((float)(Math.sqrt(3.0) / 2.0)) * f4, f3, -0.5f * f4).m_6122_(255, 0, 255, 0).m_5752_();
                builder.m_252986_(matrix4f, (float)(Math.sqrt(3.0) / 2.0) * f4, f3, -0.5f * f4).m_6122_(255, 0, 255, 0).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_6122_(255, 255, 255, k).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_6122_(255, 255, 255, k).m_5752_();
                builder.m_252986_(matrix4f, (float)(Math.sqrt(3.0) / 2.0) * f4, f3, -0.5f * f4).m_6122_(255, 0, 255, 0).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, f3, f4).m_6122_(255, 0, 255, 0).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_6122_(255, 255, 255, k).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, 0.0f, 0.0f).m_6122_(255, 255, 255, k).m_5752_();
                builder.m_252986_(matrix4f, 0.0f, f3, f4).m_6122_(255, 0, 255, 0).m_5752_();
                builder.m_252986_(matrix4f, -((float)(Math.sqrt(3.0) / 2.0)) * f4, f3, -0.5f * f4).m_6122_(255, 0, 255, 0).m_5752_();
                ++i;
            }
            stack.m_85849_();
        }
    }

    @Override
    public AbstractWitherStormModel<WitherStormEntity> fetchModel(WitherStormEntity entity) {
        int phase = entity.getPhase();
        int consumptionAmountForPhase = entity.getConsumptionAmountForPhase(phase);
        int consumedEntities = entity.getConsumedEntities();
        if (phase == 1) {
            if (consumedEntities >= entity.adjustAmountForEvolutionSpeed(250)) {
                return this.hunchbackP15;
            }
            if (consumedEntities >= entity.adjustAmountForEvolutionSpeed(150)) {
                return this.hunchbackP125;
            }
            if (consumptionAmountForPhase >= consumedEntities) {
                return this.hunchbackP1;
            }
        } else {
            if (phase == 2) {
                if (consumedEntities >= entity.adjustAmountForEvolutionSpeed(800)) {
                    return this.hunchbackP25;
                }
                return this.hunchbackP2;
            }
            if (phase == 3) {
                if (consumedEntities >= entity.adjustAmountForEvolutionSpeed(3500)) {
                    return this.hunchbackP32;
                }
                if (consumedEntities >= entity.adjustAmountForEvolutionSpeed(2350)) {
                    return this.hunchbackP31;
                }
                return this.hunchbackP3;
            }
            if (phase == 4) {
                if (consumedEntities <= entity.getSubPhaseRequirement(phase)) {
                    return this.destroyerModel;
                }
                return this.intermediateEvolvedDestroyer;
            }
            if (phase == 5) {
                if (consumptionAmountForPhase < consumedEntities) {
                    return this.devourerModel;
                }
                if (consumedEntities <= entity.getSubPhaseRequirement(phase)) {
                    return this.evolvedDestroyerModel;
                }
                return this.intermediateDevourerModel;
            }
            if (phase == 6) {
                if (consumedEntities <= entity.getSubPhaseRequirement(phase)) {
                    return this.dismantledModel;
                }
                return this.intermediateEvolvedDevourerModel;
            }
            if (phase == 7) {
                if (!entity.isBeingTornApart()) {
                    return this.evolvedDevourerModel;
                }
                return this.tornModel;
            }
            return this.commandBlockModel;
        }
        return null;
    }
}

