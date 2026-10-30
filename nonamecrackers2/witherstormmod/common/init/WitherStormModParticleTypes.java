/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleOptions$Deserializer
 *  net.minecraft.core.particles.ParticleType
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package nonamecrackers2.witherstormmod.common.init;

import com.mojang.serialization.Codec;
import java.util.function.Function;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions;

public class WitherStormModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create((IForgeRegistry)ForgeRegistries.PARTICLE_TYPES, (String)"witherstormmod");
    public static final RegistryObject<SimpleParticleType> COMMAND_BLOCK = PARTICLE_TYPES.register("command_block", () -> new SimpleParticleType(true));
    public static final RegistryObject<ParticleType<TractorBeamParticleOptions>> TRACTOR_BEAM = WitherStormModParticleTypes.register("tractor_beam", false, TractorBeamParticleOptions.DESERIALIZIER, type -> TractorBeamParticleOptions.CODEC);
    public static final RegistryObject<SimpleParticleType> PHLEGM = PARTICLE_TYPES.register("phlegm", () -> new SimpleParticleType(true));

    public static <T extends ParticleOptions> RegistryObject<ParticleType<T>> register(String name, boolean overrideLimiter, ParticleOptions.Deserializer<T> deserializer, final Function<ParticleType<T>, Codec<T>> codecGetter) {
        return PARTICLE_TYPES.register(name, () -> new ParticleType<T>(overrideLimiter, deserializer){

            public Codec<T> m_7652_() {
                return (Codec)codecGetter.apply(this);
            }
        });
    }
}

