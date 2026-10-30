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
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
import nonamecrackers2.witherstormmod.common.particle.TractorBeamParticleOptions;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;

public class TractorBeamParticle
extends SimpleAnimatedParticle {
    private final int storm;
    private final int head;
    private Vec3 pos;

    public TractorBeamParticle(int storm, int head, ClientLevel world, double x, double y, double z, double dX, double dY, double dZ, SpriteSet sprite) {
        super(world, x, y, z, sprite, 0.0f);
        this.storm = storm;
        this.head = head;
        this.f_107215_ = dX;
        this.f_107216_ = dY;
        this.f_107217_ = dZ;
        this.f_172258_ = 1.0f;
        this.f_107225_ = 20 + this.f_107223_.m_188503_(30);
        this.f_107663_ = 0.1f;
        this.f_107219_ = false;
        this.pos = new Vec3(this.f_107212_, this.f_107213_, this.f_107214_);
    }

    public ParticleRenderType m_7556_() {
        return ParticleRenderType.f_107432_;
    }

    public void m_5989_() {
        super.m_5989_();
        this.pos = new Vec3(this.f_107212_, this.f_107213_, this.f_107214_);
        if (!this.canExist()) {
            this.m_107274_();
        }
    }

    public <T extends LivingEntity> boolean canExist() {
        LivingEntity storm;
        Entity entity = this.f_107208_.m_6815_(this.storm);
        return entity instanceof LivingEntity && entity instanceof WitherStormBase && TractorBeamHelper.isInsideTractorBeam(this.pos, storm = (LivingEntity)entity, 4.0, this.head);
    }

    public static class Factory
    implements ParticleProvider<TractorBeamParticleOptions> {
        private final SpriteSet sprites;

        public Factory(SpriteSet sprites) {
            this.sprites = sprites;
        }

        public Particle createParticle(TractorBeamParticleOptions options, ClientLevel world, double x, double y, double z, double dX, double dY, double dZ) {
            TractorBeamParticle particle = new TractorBeamParticle(options.storm(), options.head(), world, x, y, z, dX, dY, dZ, this.sprites);
            particle.m_108335_(this.sprites);
            return particle;
        }
    }
}

