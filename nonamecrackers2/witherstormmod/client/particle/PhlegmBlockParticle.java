/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.particle.ParticleProvider
 *  net.minecraft.client.particle.ParticleRenderType
 *  net.minecraft.client.particle.SimpleAnimatedParticle
 *  net.minecraft.client.particle.SpriteSet
 *  net.minecraft.core.particles.SimpleParticleType
 */
package nonamecrackers2.witherstormmod.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

public class PhlegmBlockParticle
extends SimpleAnimatedParticle {
    public PhlegmBlockParticle(ClientLevel world, double x, double y, double z, double dX, double dY, double dZ, SpriteSet sprite) {
        super(world, x, y, z, sprite, 0.0f);
        this.f_107215_ = dX;
        this.f_107216_ = dY;
        this.f_107217_ = dZ;
        this.f_107225_ = 45;
        this.f_107663_ = 0.1f * this.f_107223_.m_188501_();
        this.f_107219_ = false;
    }

    public ParticleRenderType m_7556_() {
        return ParticleRenderType.f_107430_;
    }

    public static class Factory
    implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Factory(SpriteSet sprites) {
            this.sprites = sprites;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double dX, double dY, double dZ) {
            PhlegmBlockParticle particle = new PhlegmBlockParticle(world, x, y, z, dX, dY, dZ, this.sprites);
            particle.m_108335_(this.sprites);
            return particle;
        }
    }
}

