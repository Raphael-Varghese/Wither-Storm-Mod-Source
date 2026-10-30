/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.common.event;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;

public class EntityConversionEvents {
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity living;
        DamageSource source;
        Level world = event.getEntity().m_9236_();
        if (!world.f_46443_ && ((Boolean)WitherStormModConfig.SERVER.sickenedMobConversions.get()).booleanValue() && (source = event.getSource()).m_276093_(WitherStormModDamageTypes.WITHER_SICKNESS) && (living = event.getEntity()) instanceof Mob) {
            Mob mob = (Mob)living;
            if (WorldTainting.getInstance().convertMob(mob, true)) {
                event.setCanceled(true);
            }
        }
    }
}

