/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.network.PacketDistributor
 */
package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;

import java.util.List;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import nonamecrackers2.witherstormmod.common.packet.PlayerMotionMessage;

public class PullSpell
extends SymbiontSpell {
    public PullSpell(WitheredSymbiontEntity symbiont, SpellType type) {
        super(symbiont, type);
    }

    @Override
    public void cast(LivingEntity target) {
    }

    @Override
    public void doCasting(LivingEntity target) {
        List nearbyTargets = this.entity.getNearbyTargets(WitheredSymbiontEntity.MOB_TARGET_PREDICATE).toList();
        for (LivingEntity entity : nearbyTargets) {
            if (!entity.m_6084_() || entity == this.entity) continue;
            Vec3 delta = this.entity.m_20182_().m_82546_(entity.m_20182_()).m_82541_().m_82490_(0.1);
            if ((double)entity.m_20270_((Entity)this.entity) < 3.0) {
                this.entity.breakSpell();
                return;
            }
            delta = new Vec3(delta.m_7096_(), entity.m_20184_().m_7098_(), delta.m_7094_());
            entity.m_20256_(delta);
            if (entity instanceof ServerPlayer) {
                PlayerMotionMessage message = new PlayerMotionMessage(delta);
                WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)entity), (Object)message);
            }
            double x = entity.m_20185_() + entity.m_217043_().m_188583_() * entity.m_20191_().m_82362_() * 0.4;
            double y = entity.m_20191_().m_82399_().f_82480_ + entity.m_217043_().m_188583_() * entity.m_20191_().m_82376_() * 0.4;
            double z = entity.m_20189_() + entity.m_217043_().m_188583_() * entity.m_20191_().m_82385_() * 0.4;
            Vec3 particleDelta = this.entity.m_20299_(1.0f).m_82492_(x, y, z).m_82541_().m_82490_(0.1);
            ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)((SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get()), x, y, z, 0, particleDelta.m_7096_(), particleDelta.m_7098_(), particleDelta.m_7094_(), 1.0);
        }
    }

    @Override
    public int getDelay(RandomSource random, float modifier) {
        return Math.max(340, random.m_188503_(420)) - Mth.m_14143_((float)modifier) * 10;
    }
}

