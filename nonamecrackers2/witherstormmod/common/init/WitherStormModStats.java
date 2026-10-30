/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Registry
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.stats.StatFormatter
 *  net.minecraft.stats.Stats
 */
package nonamecrackers2.witherstormmod.common.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

public class WitherStormModStats {
    public static ResourceLocation INTERACT_WITH_SUPER_BEACON;

    public static void register() {
        INTERACT_WITH_SUPER_BEACON = WitherStormModStats.makeCustomStat("interact_with_super_beacon", StatFormatter.f_12873_);
    }

    private static ResourceLocation makeCustomStat(String id, StatFormatter formatter) {
        ResourceLocation rl = new ResourceLocation("witherstormmod", id);
        Registry.m_122965_((Registry)BuiltInRegistries.f_256771_, (ResourceLocation)rl, (Object)rl);
        Stats.f_12988_.m_12899_((Object)rl, formatter);
        return rl;
    }
}

