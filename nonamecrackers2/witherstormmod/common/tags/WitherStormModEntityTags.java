/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.entity.EntityType
 */
package nonamecrackers2.witherstormmod.common.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class WitherStormModEntityTags {
    public static final TagKey<EntityType<?>> HIGH_IMMUNITY = TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("witherstormmod", "high_immunity"));
    public static final TagKey<EntityType<?>> WITHER_SICKNESS_IMMUNE = TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("witherstormmod", "wither_sickness_immune"));
    public static final TagKey<EntityType<?>> SICKENED_MOBS = TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("witherstormmod:sickened_mobs"));
    public static final TagKey<EntityType<?>> WITHER_STORM_TARGETING_BLACKLIST = TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("witherstormmod:wither_storm_targeting_blacklist"));
    public static final TagKey<EntityType<?>> FAVOURABLE_MOBS = TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)new ResourceLocation("witherstormmod:favourable_mobs"));
}

