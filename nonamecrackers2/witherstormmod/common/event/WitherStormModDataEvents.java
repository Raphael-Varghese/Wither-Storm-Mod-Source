/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.data.DataGenerator
 *  net.minecraft.data.DataProvider
 *  net.minecraft.data.PackOutput
 *  net.minecraft.data.loot.LootTableProvider
 *  net.minecraft.data.loot.LootTableProvider$SubProviderEntry
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
 *  net.minecraftforge.common.data.ExistingFileHelper
 *  net.minecraftforge.data.event.GatherDataEvent
 *  net.minecraftforge.event.AddReloadListenerEvent
 */
package nonamecrackers2.witherstormmod.common.event;

import java.util.List;
import java.util.Set;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import nonamecrackers2.witherstormmod.common.data.WitherStormModBlockStatesProvider;
import nonamecrackers2.witherstormmod.common.data.WitherStormModBlockTaintingRecipeProvider;
import nonamecrackers2.witherstormmod.common.data.WitherStormModItemModelProvider;
import nonamecrackers2.witherstormmod.common.data.WitherStormModLangProvider;
import nonamecrackers2.witherstormmod.common.data.WitherStormModMobConversionsProvider;
import nonamecrackers2.witherstormmod.common.data.WitherStormModRecipeProvider;
import nonamecrackers2.witherstormmod.common.data.loot.WitherStormModBlockLootProvider;
import nonamecrackers2.witherstormmod.common.data.loot.WitherStormModEntityLootProvider;
import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;

public class WitherStormModDataEvents {
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper exFileHelper = event.getExistingFileHelper();
        generator.addProvider(event.includeClient(), (DataProvider)new WitherStormModLangProvider(output));
        generator.addProvider(event.includeServer(), (DataProvider)new WitherStormModRecipeProvider(output));
        generator.addProvider(event.includeClient(), (DataProvider)new WitherStormModBlockStatesProvider(output, exFileHelper));
        generator.addProvider(event.includeClient(), (DataProvider)new WitherStormModItemModelProvider(output, exFileHelper));
        generator.addProvider(event.includeServer(), (DataProvider)new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(WitherStormModBlockLootProvider::new, LootContextParamSets.f_81421_), new LootTableProvider.SubProviderEntry(WitherStormModEntityLootProvider::new, LootContextParamSets.f_81415_))));
        generator.addProvider(event.includeServer(), (DataProvider)new WitherStormModBlockTaintingRecipeProvider(output));
        generator.addProvider(event.includeServer(), (DataProvider)new WitherStormModMobConversionsProvider(output));
    }

    public static void addResourceListeners(AddReloadListenerEvent event) {
        WorldTainting.initializeOrAddListeners(arg_0 -> ((AddReloadListenerEvent)event).addListener(arg_0));
    }
}

