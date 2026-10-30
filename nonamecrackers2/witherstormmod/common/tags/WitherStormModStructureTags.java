/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.level.levelgen.structure.Structure
 */
package nonamecrackers2.witherstormmod.common.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import nonamecrackers2.witherstormmod.WitherStormMod;

public class WitherStormModStructureTags {
    public static final TagKey<Structure> WITHER_STORM_DISTRACTABLE = TagKey.m_203882_((ResourceKey)Registries.f_256944_, (ResourceLocation)new ResourceLocation("witherstormmod", "wither_storm_distractable"));
    public static final TagKey<Structure> STORM_SPAWN_PLATFORMS = TagKey.m_203882_((ResourceKey)Registries.f_256944_, (ResourceLocation)WitherStormMod.id("storm_spawn_platforms"));
}

