/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.util.Mth
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.client.event.ViewportEvent$ComputeFogColor
 *  net.minecraftforge.event.TickEvent$ClientTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.client.event;

import java.awt.Color;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.client.capability.WitherStormDistantRenderer;
import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
import nonamecrackers2.witherstormmod.client.renderer.entity.AbstractWitherStormRenderer;
import nonamecrackers2.witherstormmod.client.resources.WitherStormResourceConfigManager;
import nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;

public class WitherStormAmbienceEffects {
    private static final int COLOR_TRANSITION = 40;
    private final Minecraft mc;
    private float alpha = 1.0f;
    private float alphaO = 1.0f;
    private int colorTransitionTime;
    private SkyColorSet current = SkyColorSet.DEFAULT_SKY_COLORS;
    private SkyColorSet previous = SkyColorSet.DEFAULT_SKY_COLORS;

    public WitherStormAmbienceEffects(Minecraft mc) {
        this.mc = mc;
    }

    public void tick() {
        if (((Boolean)WitherStormModConfig.CLIENT.renderSkyAmbienceEffects.get()).booleanValue()) {
            List<WitherStormEntity> storms = WitherStormAmbienceEffects.getApplicableEntities(this.mc.f_91073_);
            Vec3 cameraPos = this.mc.f_91063_.m_109153_().m_90583_();
            WitherStormEntity storm = storms.stream().sorted(Comparator.comparingInt(WitherStormEntity::getConsumedEntities).reversed()).findFirst().orElse(null);
            this.alphaO = this.alpha;
            if (storm != null && !storm.isDeadOrPlayingDead()) {
                float alpha = WitherStormAmbienceEffects.modifyEffectMagnitude(storm, Mth.m_14036_((float)((float)(cameraPos.m_82554_(storm.m_20182_()) - 200.0) * 0.005f), (float)0.0f, (float)1.0f));
                this.alpha += (alpha - this.alpha) / 25.0f;
                SkyColorSet set = WitherStormResourceConfigManager.INSTANCE.getColorSetByPhase(storm.getPhase()).skyColors();
                if (set != null && !set.equals(this.current)) {
                    this.previous = this.current;
                    this.current = set;
                    this.colorTransitionTime = 40;
                }
            } else {
                this.alpha += (1.0f - this.alpha) / 100.0f;
            }
            if (this.colorTransitionTime > 0) {
                --this.colorTransitionTime;
            }
        }
    }

    public float lerpAlpha(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.alphaO, (float)this.alpha);
    }

    public Color lerpColorsByTransition(Function<SkyColorSet, Color> dayColorGetter, Function<SkyColorSet, Color> nightColorGetter, float partialTicks) {
        float lerp = Mth.m_14036_((float)(((float)this.colorTransitionTime - partialTicks) / 40.0f), (float)0.0f, (float)1.0f);
        Color previous = WitherStormAmbienceEffects.lerpNightColors(dayColorGetter.apply(this.previous), nightColorGetter.apply(this.previous), partialTicks);
        Color current = WitherStormAmbienceEffects.lerpNightColors(dayColorGetter.apply(this.current), nightColorGetter.apply(this.current), partialTicks);
        int r = Mth.m_14143_((float)Mth.m_14179_((float)lerp, (float)current.getRed(), (float)previous.getRed()));
        int g = Mth.m_14143_((float)Mth.m_14179_((float)lerp, (float)current.getGreen(), (float)previous.getGreen()));
        int b = Mth.m_14143_((float)Mth.m_14179_((float)lerp, (float)current.getBlue(), (float)previous.getBlue()));
        int a = Mth.m_14143_((float)Mth.m_14179_((float)lerp, (float)current.getAlpha(), (float)previous.getAlpha()));
        return new Color(r, g, b, a);
    }

    private static Color lerpNightColors(Color day, @Nullable Color night, float partialTicks) {
        if (night != null) {
            float nightLerp = AbstractWitherStormRenderer.getNightTimeLerp((Level)Minecraft.m_91087_().f_91073_, partialTicks);
            int r = Mth.m_14143_((float)Mth.m_14179_((float)nightLerp, (float)day.getRed(), (float)night.getRed()));
            int g = Mth.m_14143_((float)Mth.m_14179_((float)nightLerp, (float)day.getGreen(), (float)night.getGreen()));
            int b = Mth.m_14143_((float)Mth.m_14179_((float)nightLerp, (float)day.getBlue(), (float)night.getBlue()));
            int a = Mth.m_14143_((float)Mth.m_14179_((float)nightLerp, (float)day.getAlpha(), (float)night.getAlpha()));
            return new Color(r, g, b, a);
        }
        return day;
    }

    private static List<WitherStormEntity> getApplicableEntities(ClientLevel level) {
        return WitherStormDistantRenderer.getAllStorms(level).stream().filter(entity -> !(entity instanceof WitherStormSegmentEntity)).collect(Collectors.toList());
    }

    public static float modifyEffectMagnitude(WitherStormEntity storm, float alpha) {
        if (storm.getPhase() < 5) {
            return 1.0f;
        }
        if (storm.getPhase() == 5) {
            return Math.min(alpha + Mth.m_14036_((float)(1.0f - storm.getPhaseProgress()), (float)0.15f, (float)1.0f), 1.0f);
        }
        return Math.min(alpha + 0.15f, 1.0f);
    }

    public static Vec3 modifySkyColor(Minecraft mc, Vec3 original, Vec3 cameraPos, float partialTicks) {
        return WitherStormAmbienceEffects.transitionColor(mc, cameraPos, original, SkyColorSet::skyColor, SkyColorSet::nightSkyColor, 200.0, partialTicks);
    }

    public static float modifySkyDarken(Minecraft mc, Vec3 cameraPos, float original, float partialTicks) {
        WitherStormAmbienceEffects effects = (WitherStormAmbienceEffects)mc.f_91073_.getCapability(WitherStormModClientCapabilities.AMBIENT_EFFECTS).orElse(null);
        if (effects != null) {
            return original * Math.min(effects.lerpAlpha(partialTicks) + 0.4f, 1.0f);
        }
        return original;
    }

    public static Vec3 modifyCloudColors(Minecraft mc, Vec3 cameraPos, Vec3 original, float partialTicks) {
        return WitherStormAmbienceEffects.transitionColor(mc, cameraPos, original, SkyColorSet::cloudColor, SkyColorSet::nightCloudColor, 200.0, partialTicks);
    }

    private static Vec3 transitionColor(Minecraft mc, Vec3 cameraPos, Vec3 original, Function<SkyColorSet, Color> dayColorGetter, Function<SkyColorSet, Color> nightColorGetter, double distance, float partialTicks) {
        WitherStormAmbienceEffects effects = (WitherStormAmbienceEffects)mc.f_91073_.getCapability(WitherStormModClientCapabilities.AMBIENT_EFFECTS).orElse(null);
        if (effects != null) {
            float alpha = effects.lerpAlpha(partialTicks);
            Color col = effects.lerpColorsByTransition(dayColorGetter, nightColorGetter, partialTicks);
            int[] color = new int[]{col.getRed(), col.getGreen(), col.getBlue()};
            double rDelta = original.f_82479_ * 255.0 - (double)color[0];
            double gDelta = original.f_82480_ * 255.0 - (double)color[1];
            double bDelta = original.f_82481_ * 255.0 - (double)color[2];
            color[0] = (int)((double)color[0] + rDelta * (double)alpha);
            color[1] = (int)((double)color[1] + gDelta * (double)alpha);
            color[2] = (int)((double)color[2] + bDelta * (double)alpha);
            return new Vec3((double)color[0] / 255.0, (double)color[1] / 255.0, (double)color[2] / 255.0);
        }
        return original;
    }

    public static class Events {
        @SubscribeEvent
        public static void onWorldTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.START) {
                Minecraft mc = Minecraft.m_91087_();
                ClientLevel level = mc.f_91073_;
                if (level != null && !mc.m_91104_()) {
                    level.getCapability(WitherStormModClientCapabilities.AMBIENT_EFFECTS).ifPresent(WitherStormAmbienceEffects::tick);
                }
            }
        }

        @SubscribeEvent
        public static void fogColor(ViewportEvent.ComputeFogColor event) {
            if (((Boolean)WitherStormModConfig.CLIENT.renderSkyAmbienceEffects.get()).booleanValue()) {
                Minecraft mc = Minecraft.m_91087_();
                mc.f_91073_.getCapability(WitherStormModClientCapabilities.AMBIENT_EFFECTS).ifPresent(effects -> {
                    float alpha = effects.lerpAlpha((float)event.getPartialTick());
                    Color fog = effects.lerpColorsByTransition(SkyColorSet::fogColor, SkyColorSet::nightFogColor, (float)event.getPartialTick());
                    int[] color = new int[]{fog.getRed(), fog.getGreen(), fog.getBlue()};
                    float rDelta = event.getRed() * 255.0f - (float)color[0];
                    float gDelta = event.getGreen() * 255.0f - (float)color[1];
                    float bDelta = event.getBlue() * 255.0f - (float)color[2];
                    color[0] = (int)((float)color[0] + rDelta * alpha);
                    color[1] = (int)((float)color[1] + gDelta * alpha);
                    color[2] = (int)((float)color[2] + bDelta * alpha);
                    event.setRed((float)color[0] / 255.0f);
                    event.setGreen((float)color[1] / 255.0f);
                    event.setBlue((float)color[2] / 255.0f);
                });
            }
        }
    }
}

