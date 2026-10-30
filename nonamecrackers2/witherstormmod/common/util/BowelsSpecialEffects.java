/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.DimensionSpecialEffects$NetherEffects
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.util;

import java.awt.Color;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.client.resources.WitherStormResourceConfigManager;

public class BowelsSpecialEffects
extends DimensionSpecialEffects.NetherEffects {
    public Vec3 m_5927_(Vec3 col, float dayCycle) {
        Color color = WitherStormResourceConfigManager.INSTANCE.getBowelsFogColor().orElse(null);
        if (color != null) {
            return new Vec3((double)((float)color.getRed() / 255.0f), (double)((float)color.getGreen() / 255.0f), (double)((float)color.getBlue() / 255.0f));
        }
        return super.m_5927_(col, dayCycle);
    }
}

