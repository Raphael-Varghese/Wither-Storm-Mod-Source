/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleOptions$Deserializer
 *  net.minecraft.core.particles.ParticleType
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraftforge.registries.ForgeRegistries
 */
package nonamecrackers2.witherstormmod.common.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;

public record TractorBeamParticleOptions(int storm, int head) implements ParticleOptions
{
    public static final Codec<TractorBeamParticleOptions> CODEC = RecordCodecBuilder.create(instance -> instance.group((App)Codec.INT.fieldOf("storm").forGetter(TractorBeamParticleOptions::storm), (App)Codec.INT.fieldOf("head").forGetter(TractorBeamParticleOptions::head)).apply((Applicative)instance, TractorBeamParticleOptions::new));
    public static final ParticleOptions.Deserializer<TractorBeamParticleOptions> DESERIALIZIER = new ParticleOptions.Deserializer<TractorBeamParticleOptions>(){

        public TractorBeamParticleOptions fromCommand(ParticleType<TractorBeamParticleOptions> type, StringReader reader) throws CommandSyntaxException {
            return new TractorBeamParticleOptions(reader.readInt(), reader.readInt());
        }

        public TractorBeamParticleOptions fromNetwork(ParticleType<TractorBeamParticleOptions> type, FriendlyByteBuf buffer) {
            return new TractorBeamParticleOptions(buffer.m_130242_(), buffer.m_130242_());
        }
    };

    public ParticleType<?> m_6012_() {
        return (ParticleType)WitherStormModParticleTypes.TRACTOR_BEAM.get();
    }

    public void m_7711_(FriendlyByteBuf buffer) {
        buffer.m_130130_(this.head);
        buffer.m_130130_(this.head);
    }

    public String m_5942_() {
        return ForgeRegistries.PARTICLE_TYPES.getKey(this.m_6012_()) + " " + this.storm + " " + this.head;
    }
}

