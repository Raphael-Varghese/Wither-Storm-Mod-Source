/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleType
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.client.event.RegisterParticleProvidersEvent
 *  net.minecraftforge.event.TickEvent$ClientTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 */
package nonamecrackers2.witherstormmod.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.TickEvent;
import nonamecrackers2.witherstormmod.client.particle.CommandBlockParticle;
import nonamecrackers2.witherstormmod.client.particle.PhlegmBlockParticle;
import nonamecrackers2.witherstormmod.client.particle.TractorBeamParticle;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModItemTags;

public class ParticleEvents {
    public static void registerFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet((ParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get(), CommandBlockParticle.Factory::new);
        event.registerSpriteSet((ParticleType)WitherStormModParticleTypes.TRACTOR_BEAM.get(), TractorBeamParticle.Factory::new);
        event.registerSpriteSet((ParticleType)WitherStormModParticleTypes.PHLEGM.get(), PhlegmBlockParticle.Factory::new);
    }

    public static void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.m_91087_();
        if (event.phase == TickEvent.Phase.START && !mc.m_91104_() && mc.f_91073_ != null) {
            ClientLevel world = mc.f_91073_;
            for (Entity entity : world.m_104735_()) {
                ItemEntity item;
                if (!(entity instanceof ItemEntity) || !(item = (ItemEntity)entity).m_32055_().m_150930_((Item)WitherStormModItems.COMMAND_BLOCK_BOOK.get()) && !item.m_32055_().m_204117_(WitherStormModItemTags.COMMAND_BLOCK_TOOLS)) continue;
                for (int i = 0; i < 2; ++i) {
                    double x = item.m_20185_() + world.m_213780_().m_188583_() * 0.4;
                    double y = item.m_20188_() + world.m_213780_().m_188583_() * 0.4;
                    double z = item.m_20189_() + world.m_213780_().m_188583_() * 0.4;
                    Vec3 delta = item.m_20299_(1.0f).m_82492_(x, y, z).m_82541_().m_82542_(0.05, 0.05, 0.05);
                    world.m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), x, y, z, delta.m_7096_(), delta.m_7098_(), delta.m_7094_());
                }
            }
        }
    }
}

