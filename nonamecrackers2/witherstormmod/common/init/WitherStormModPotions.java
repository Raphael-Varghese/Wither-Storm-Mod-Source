/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package nonamecrackers2.witherstormmod.common.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class WitherStormModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create((IForgeRegistry)ForgeRegistries.POTIONS, (String)"witherstormmod");
    public static final RegistryObject<Potion> WITHER = POTIONS.register("wither", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance(MobEffects.f_19615_, 900)}));
    public static final RegistryObject<Potion> LONG_WITHER = POTIONS.register("long_wither", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance(MobEffects.f_19615_, 1800)}));
    public static final RegistryObject<Potion> STRONG_WITHER = POTIONS.register("strong_wither", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance(MobEffects.f_19615_, 432, 1)}));
}

