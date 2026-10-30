/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.Holder
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageType
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 */
package nonamecrackers2.witherstormmod.common.init;

import javax.annotation.Nullable;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class WitherStormModDamageTypes {
    public static final ResourceKey<DamageType> FLAMING_WITHER_SKULL = WitherStormModDamageTypes.create("flaming_wither_skull");
    public static final ResourceKey<DamageType> WITHER_SICKNESS = WitherStormModDamageTypes.create("wither_sickness");
    public static final ResourceKey<DamageType> PLAYER_FORMIDIBOMB = WitherStormModDamageTypes.create("player_formidibomb");
    public static final ResourceKey<DamageType> FORMIDIBOMB = WitherStormModDamageTypes.create("formidibomb");
    public static final ResourceKey<DamageType> WITHER_STORM_ATTACK_MOB = WitherStormModDamageTypes.create("wither_storm_attack_mob");
    public static final ResourceKey<DamageType> WITHER_STORM_ATTACK = WitherStormModDamageTypes.create("wither_storm_attack");
    public static final ResourceKey<DamageType> PLAYER_ATTACK_WITHER_STORM = WitherStormModDamageTypes.create("player_attack_wither_storm");
    public static final ResourceKey<DamageType> MOB_ATTACK_WITHER_STORM = WitherStormModDamageTypes.create("mob_attack_wither_storm");
    public static final ResourceKey<DamageType> SUPER_TNT_EXPLOSION = WitherStormModDamageTypes.create("super_tnt_explosion");
    public static final ResourceKey<DamageType> IRON_PIERCING = WitherStormModDamageTypes.create("iron_pierce");

    private static ResourceKey<DamageType> create(String id) {
        return ResourceKey.m_135785_((ResourceKey)Registries.f_268580_, (ResourceLocation)new ResourceLocation("witherstormmod", id));
    }

    public static DamageSource source(RegistryAccess access, ResourceKey<DamageType> key) {
        return new DamageSource((Holder)access.m_175515_(Registries.f_268580_).m_246971_(key));
    }

    public static DamageSource source(RegistryAccess access, ResourceKey<DamageType> key, @Nullable Entity entity) {
        return new DamageSource((Holder)access.m_175515_(Registries.f_268580_).m_246971_(key), entity);
    }

    public static DamageSource source(RegistryAccess access, ResourceKey<DamageType> key, @Nullable Entity entity, @Nullable Entity entity2) {
        return new DamageSource((Holder)access.m_175515_(Registries.f_268580_).m_246971_(key), entity, entity2);
    }

    public static DamageSource witherStormAttack(LivingEntity entity) {
        return WitherStormModDamageTypes.source(entity.m_9236_().m_9598_(), WITHER_STORM_ATTACK, (Entity)entity);
    }

    public static DamageSource witherStormAttackMob(LivingEntity entity) {
        return WitherStormModDamageTypes.source(entity.m_9236_().m_9598_(), WITHER_STORM_ATTACK_MOB, (Entity)entity);
    }

    public static DamageSource playerAttackWitherStorm(Player entity) {
        return WitherStormModDamageTypes.source(entity.m_9236_().m_9598_(), PLAYER_ATTACK_WITHER_STORM, (Entity)entity);
    }

    public static DamageSource mobAttackWitherStorm(LivingEntity entity) {
        return WitherStormModDamageTypes.source(entity.m_9236_().m_9598_(), MOB_ATTACK_WITHER_STORM, (Entity)entity);
    }

    public static DamageSource superTntExplosion(Level level) {
        return WitherStormModDamageTypes.source(level.m_9598_(), SUPER_TNT_EXPLOSION);
    }

    public static DamageSource ironPierced(LivingEntity entity) {
        return WitherStormModDamageTypes.source(entity.m_9236_().m_9598_(), IRON_PIERCING, (Entity)entity);
    }
}

