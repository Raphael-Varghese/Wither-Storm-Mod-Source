/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.screens.MenuScreens
 *  net.minecraft.world.inventory.MenuType
 */
package nonamecrackers2.witherstormmod.client.init;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import nonamecrackers2.witherstormmod.client.gui.menu.SuperBeaconScreen;
import nonamecrackers2.witherstormmod.client.gui.menu.WitheredPhlegmScreen;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMenuTypes;

public class WitherStormModMenuScreens {
    public static void register() {
        MenuScreens.m_96206_((MenuType)((MenuType)WitherStormModMenuTypes.SUPER_BEACON.get()), SuperBeaconScreen::new);
        MenuScreens.m_96206_((MenuType)((MenuType)WitherStormModMenuTypes.SUPER_SUPPORT_BEACON.get()), SuperBeaconScreen::new);
        MenuScreens.m_96206_((MenuType)((MenuType)WitherStormModMenuTypes.WITHERED_PHLEGM.get()), WitheredPhlegmScreen::new);
    }
}

