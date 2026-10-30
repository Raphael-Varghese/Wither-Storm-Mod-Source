/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  net.minecraft.core.Holder
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.levelgen.feature.ConfiguredFeature
 *  net.minecraft.world.level.levelgen.feature.Feature
 *  net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package nonamecrackers2.witherstormmod.common.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import nonamecrackers2.witherstormmod.common.world.gen.feature.BowelsPodiumFeature;
import nonamecrackers2.witherstormmod.common.world.gen.feature.CommandBlockPodiumFeature;

public class WitherStormModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create((IForgeRegistry)ForgeRegistries.FEATURES, (String)"witherstormmod");
    public static final RegistryObject<CommandBlockPodiumFeature> COMMAND_BLOCK_PODIUM_FEATURE = FEATURES.register("command_block_podium", () -> new CommandBlockPodiumFeature((Codec<NoneFeatureConfiguration>)NoneFeatureConfiguration.f_67815_, new ResourceLocation("witherstormmod", "command_block_podium")));
    public static final RegistryObject<BowelsPodiumFeature> BOWELS_PODIUM_FEATURE = FEATURES.register("bowels_podium", () -> new BowelsPodiumFeature((Codec<NoneFeatureConfiguration>)NoneFeatureConfiguration.f_67815_, new ResourceLocation("witherstormmod", "bowels_podium")));

    public static Holder<ConfiguredFeature<?, ?>> getConfiguredFeature(ServerLevel level, ResourceLocation id) {
        return level.m_9598_().m_175515_(Registries.f_256911_).m_246971_(ResourceKey.m_135785_((ResourceKey)Registries.f_256911_, (ResourceLocation)id));
    }
}

