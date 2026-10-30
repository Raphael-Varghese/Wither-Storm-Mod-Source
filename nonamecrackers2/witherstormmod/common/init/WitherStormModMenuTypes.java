/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.flag.FeatureFlags
 *  net.minecraft.world.inventory.MenuType
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package nonamecrackers2.witherstormmod.common.init;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import nonamecrackers2.witherstormmod.common.blockentity.inventory.SuperBeaconMenu;
import nonamecrackers2.witherstormmod.common.blockentity.inventory.SuperSupportBeaconMenu;
import nonamecrackers2.witherstormmod.common.blockentity.inventory.WitheredPhlegmMenu;

public class WitherStormModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create((IForgeRegistry)ForgeRegistries.MENU_TYPES, (String)"witherstormmod");
    public static final RegistryObject<MenuType<SuperBeaconMenu>> SUPER_BEACON = MENU_TYPES.register("super_beacon", () -> new MenuType(SuperBeaconMenu::new, FeatureFlags.f_244332_));
    public static final RegistryObject<MenuType<SuperSupportBeaconMenu>> SUPER_SUPPORT_BEACON = MENU_TYPES.register("super_support_beacon", () -> new MenuType(SuperSupportBeaconMenu::new, FeatureFlags.f_244332_));
    public static final RegistryObject<MenuType<WitheredPhlegmMenu>> WITHERED_PHLEGM = MENU_TYPES.register("withered_phlegm", () -> new MenuType(WitheredPhlegmMenu::new, FeatureFlags.f_244332_));
}

