/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.gui.components.Tooltip
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.packs.PackType
 *  net.minecraft.server.packs.PathPackResources
 *  net.minecraft.server.packs.repository.Pack
 *  net.minecraft.server.packs.repository.Pack$Position
 *  net.minecraft.server.packs.repository.PackSource
 *  net.minecraftforge.event.AddPackFindersEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.ModList
 *  net.minecraftforge.fml.config.ModConfig$Type
 *  net.minecraftforge.forgespi.locating.IModFile
 *  nonamecrackers2.crackerslib.client.event.impl.AddConfigEntryToMenuEvent
 *  nonamecrackers2.crackerslib.client.event.impl.ConfigMenuButtonEvent
 *  nonamecrackers2.crackerslib.client.event.impl.OnConfigScreenOpened
 *  nonamecrackers2.crackerslib.client.event.impl.RegisterConfigScreensEvent
 *  nonamecrackers2.crackerslib.client.gui.ConfigHomeScreen
 *  nonamecrackers2.crackerslib.client.gui.title.ImageTitle
 *  nonamecrackers2.crackerslib.client.gui.title.TitleLogo
 *  nonamecrackers2.crackerslib.common.compat.CompatHelper
 */
package nonamecrackers2.witherstormmod.client.event;

import java.nio.file.Path;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.forgespi.locating.IModFile;
import nonamecrackers2.crackerslib.client.event.impl.AddConfigEntryToMenuEvent;
import nonamecrackers2.crackerslib.client.event.impl.ConfigMenuButtonEvent;
import nonamecrackers2.crackerslib.client.event.impl.OnConfigScreenOpened;
import nonamecrackers2.crackerslib.client.event.impl.RegisterConfigScreensEvent;
import nonamecrackers2.crackerslib.client.gui.ConfigHomeScreen;
import nonamecrackers2.crackerslib.client.gui.title.ImageTitle;
import nonamecrackers2.crackerslib.client.gui.title.TitleLogo;
import nonamecrackers2.crackerslib.common.compat.CompatHelper;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.client.gui.WitherStormModConfigHomeScreen;
import nonamecrackers2.witherstormmod.client.util.Contributors;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;

public class WitherStormModClientConfigEvents {
    public static void registerConfigScreen(RegisterConfigScreensEvent event) {
        event.builder(ConfigHomeScreen.builder((TitleLogo)ImageTitle.ofMod((String)"witherstormmod", (int)256, (int)128, (float)1.0f)).crackersDefault("https://github.com/nonamecrackers2/crackers-wither-storm-mod/issues").addLinkButton((Component)Component.m_237115_((String)"gui.witherstormmod.screen.wsmoptions.nazaKofi").m_130940_(ChatFormatting.GREEN), "https://ko-fi.com/nazaru", Tooltip.m_257550_((Component)Component.m_237115_((String)"gui.witherstormmod.screen.wsmoptions.nazaKofi.info"))).build(WitherStormModConfigHomeScreen::new)).addSpec(ModConfig.Type.CLIENT, WitherStormModConfig.CLIENT_SPEC).addSpec(ModConfig.Type.COMMON, WitherStormModConfig.COMMON_SPEC).addSpec(ModConfig.Type.SERVER, WitherStormModConfig.SERVER_SPEC).register();
    }

    public static void registerConfigMenuButton(ConfigMenuButtonEvent event) {
        event.defaultButtonWithSingleCharacter('W', 7143633);
    }

    @SubscribeEvent
    public static void onConfigMenuOpened(OnConfigScreenOpened event) {
        if (event.getModId().equals("witherstormmod")) {
            event.setInitialPath(event.getType().extension());
        }
    }

    @SubscribeEvent
    public static void onOptionAddedToMenu(AddConfigEntryToMenuEvent event) {
        if (event.getModId().equals("witherstormmod")) {
            if (event.isValue(WitherStormModConfig.SERVER.flyingDisabledWarning)) {
                event.setCanceled(true);
            } else if (event.isValue(WitherStormModConfig.SERVER.shouldChunkLoadWhenNoPlayers)) {
                event.setCanceled(true);
            } else if (event.isValue(WitherStormModConfig.CLIENT.optifineWarning)) {
                event.setCanceled(!CompatHelper.isOptifineLoaded());
            } else if (event.isValue(WitherStormModConfig.CLIENT.aprilFools)) {
                event.setCanceled(!WitherStormMod.isAprilFools());
            } else if (event.isValue(WitherStormModConfig.CLIENT.patronCosmetic)) {
                event.setCanceled(!Contributors.currentPlayerHasCosmetic());
            }
        }
    }

    public static void addPackFindersEvent(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            event.addRepositorySource(consumer -> consumer.accept(Pack.m_245429_((String)"witherstormmod:programmer_art", (Component)Component.m_237115_((String)"witherstormmod.resourcepacks.programmer_art"), (boolean)false, id -> {
                IModFile modFile = ModList.get().getModFileById("witherstormmod").getFile();
                Path resourcePath = modFile.findResource(new String[]{"resourcepacks/programmer_art"});
                return new PathPackResources(modFile.getFileName() + ":" + resourcePath, resourcePath, true);
            }, (PackType)event.getPackType(), (Pack.Position)Pack.Position.TOP, (PackSource)PackSource.f_10528_)));
        }
    }
}

