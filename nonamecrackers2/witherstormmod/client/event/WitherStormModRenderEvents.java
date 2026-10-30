/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.Camera
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.renderer.FogRenderer
 *  net.minecraft.client.renderer.FogRenderer$FogMode
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.MultiBufferSource$BufferSource
 *  net.minecraft.client.renderer.entity.EntityRenderDispatcher
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.client.event.RenderLevelStageEvent
 *  net.minecraftforge.client.event.RenderLevelStageEvent$Stage
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.client.event;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.function.BiConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.client.renderer.entity.AbstractWitherStormRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.AbstractWitherStormModel;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class WitherStormModRenderEvents {
    @SubscribeEvent
    public static void renderWitherStormPost(RenderLevelStageEvent event) {
        if (event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_PARTICLES)) {
            Minecraft mc = Minecraft.m_91087_();
            ClientLevel world = mc.f_91073_;
            PoseStack stack = event.getPoseStack();
            MultiBufferSource.BufferSource buffer = mc.m_91269_().m_110104_();
            Vec3 pos = mc.f_91063_.m_109153_().m_90583_();
            Vec3 negPos = new Vec3(-pos.f_82479_, -pos.f_82480_, -pos.f_82481_);
            float partialTicks = event.getPartialTick();
            EntityRenderDispatcher manager = mc.m_91290_();
            boolean flag = mc.f_91073_.m_104583_().m_5781_(Mth.m_14107_((double)pos.f_82479_), Mth.m_14107_((double)pos.f_82480_)) || mc.f_91065_.m_93090_().m_93715_();
            FogRenderer.m_109018_((Camera)mc.f_91063_.m_109153_(), (float)partialTicks, (ClientLevel)world, (int)((Integer)mc.f_91066_.m_231984_().m_231551_()), (float)mc.f_91063_.m_109131_(partialTicks));
            FogRenderer.m_109036_();
            if ((Integer)mc.f_91066_.m_231984_().m_231551_() >= 4) {
                FogRenderer.m_234172_((Camera)mc.f_91063_.m_109153_(), (FogRenderer.FogMode)FogRenderer.FogMode.FOG_TERRAIN, (float)Math.max(mc.f_91063_.m_109152_() - 16.0f, 32.0f), (boolean)flag, (float)partialTicks);
            }
            WitherStormModRenderEvents.renderStorms(manager, world, stack, partialTicks, negPos, (MultiBufferSource)buffer, (s, r) -> {
                if (((Boolean)WitherStormModConfig.CLIENT.renderDebrisRings.get()).booleanValue() && (!((Boolean)WitherStormModConfig.CLIENT.hideDebrisRingsUntilSplit.get()).booleanValue() || s.getPhase() > 5)) {
                    r.renderDebrisRings((WitherStormEntity)s, stack, (MultiBufferSource)buffer, partialTicks, manager.m_114394_((Entity)s, partialTicks));
                }
            });
            buffer.m_109911_();
            FogRenderer.m_109017_();
            WitherStormModRenderEvents.renderStorms(manager, world, stack, partialTicks, negPos, (MultiBufferSource)buffer, (s, r) -> {
                if (!(!((Boolean)WitherStormModConfig.CLIENT.renderTractorBeams.get()).booleanValue() || s.m_20096_() && s.isPlayingDead())) {
                    int packedLight = manager.m_114394_((Entity)s, partialTicks);
                    r.prepareHeadAnimsForTractorBeams(s, partialTicks);
                    r.renderTractorBeams(s, stack, (MultiBufferSource)buffer, partialTicks, packedLight);
                }
            });
            buffer.m_109911_();
            WitherStormModRenderEvents.renderStorms(manager, world, stack, partialTicks, negPos, (MultiBufferSource)buffer, (s, r) -> {
                if (((Boolean)WitherStormModConfig.CLIENT.renderShine.get()).booleanValue() && s.shouldShine()) {
                    AbstractWitherStormRenderer.renderShine(s, stack, partialTicks, mc.f_91063_.m_109153_(), (MultiBufferSource)buffer);
                }
            });
            buffer.m_109911_();
        }
    }

    private static <T extends WitherStormEntity, M extends AbstractWitherStormModel<T>> void renderStorms(EntityRenderDispatcher manager, ClientLevel world, PoseStack stack, float partialTicks, Vec3 negPos, MultiBufferSource buffer, BiConsumer<T, AbstractWitherStormRenderer<T, M>> action) {
        for (Entity entity : world.m_104735_()) {
            WitherStormEntity storm;
            AbstractWitherStormRenderer renderer;
            if (!(entity instanceof WitherStormEntity) || (renderer = AbstractWitherStormRenderer.getRenderer(storm = (WitherStormEntity)entity, manager)) == null) continue;
            stack.m_85836_();
            double x = Mth.m_14139_((double)partialTicks, (double)storm.f_19790_, (double)storm.m_20185_());
            double y = Mth.m_14139_((double)partialTicks, (double)storm.f_19791_, (double)storm.m_20186_());
            double z = Mth.m_14139_((double)partialTicks, (double)storm.f_19792_, (double)storm.m_20189_());
            stack.m_85837_(negPos.f_82479_, negPos.f_82480_, negPos.f_82481_);
            stack.m_85837_(x, y, z);
            renderer.updateModel(storm);
            action.accept(storm, renderer);
            stack.m_85849_();
        }
    }
}

