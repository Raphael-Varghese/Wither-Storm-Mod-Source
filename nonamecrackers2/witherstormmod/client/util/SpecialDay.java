/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.util.Mth
 */
package nonamecrackers2.witherstormmod.client.util;

import java.awt.Color;
import java.time.temporal.ChronoField;
import java.util.function.BiFunction;
import javax.annotation.Nullable;
import net.minecraft.util.Mth;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public enum SpecialDay {
    HALLOWEEN(new Color(135, 82, 28), SpecialDay.isDate(10, 31)),
    CHRISTMAS((w, p, h) -> {
        float tickCount = ((float)(w.f_19797_ + h * 1000 + w.m_19879_() * 1000) + p) / 100.0f;
        float r = Mth.m_14116_((float)Math.max(Mth.m_14031_((float)tickCount), 0.0f));
        float g = Mth.m_14116_((float)Math.max(Mth.m_14031_((float)(tickCount + (float)Math.PI)), 0.0f));
        return new Color(r * 0.5f, g * 0.5f, 0.0f);
    }, SpecialDay.isDayInMonth(12, 24, 25));

    private final ColorGetter color;
    private final BiFunction<Integer, Integer, Boolean> shouldShow;

    private SpecialDay(ColorGetter color, BiFunction<Integer, Integer, Boolean> shouldShow) {
        this.color = color;
        this.shouldShow = shouldShow;
    }

    private SpecialDay(Color color, BiFunction<Integer, Integer, Boolean> shouldShow) {
        this((WitherStormEntity e, float p, int h) -> color, shouldShow);
    }

    public Color getColor(WitherStormEntity storm, float partialTicks, int head) {
        return this.color.getColor(storm, partialTicks, head);
    }

    private static BiFunction<Integer, Integer, Boolean> isDate(int month, int day) {
        return (m, d) -> m == month && d == day;
    }

    private static BiFunction<Integer, Integer, Boolean> isDayInMonth(int month, int ... days) {
        return (m, d) -> {
            if (m == month) {
                for (int day : days) {
                    if (d != day) continue;
                    return true;
                }
            }
            return false;
        };
    }

    @Nullable
    public static SpecialDay getForCurrentDate() {
        int month = WitherStormMod.DATE.get(ChronoField.MONTH_OF_YEAR);
        int day = WitherStormMod.DATE.get(ChronoField.DAY_OF_MONTH);
        for (SpecialDay colors : SpecialDay.values()) {
            if (!colors.shouldShow.apply(month, day).booleanValue()) continue;
            return colors;
        }
        return null;
    }

    @FunctionalInterface
    public static interface ColorGetter {
        public Color getColor(WitherStormEntity var1, float var2, int var3);
    }
}

