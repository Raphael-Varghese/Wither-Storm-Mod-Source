/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraftforge.event.RegisterCommandsEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.config.ModConfig$Type
 *  net.minecraftforge.fml.loading.FMLEnvironment
 *  nonamecrackers2.crackerslib.common.command.ConfigCommandBuilder
 */
package nonamecrackers2.witherstormmod.common.event;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLEnvironment;
import nonamecrackers2.crackerslib.common.command.ConfigCommandBuilder;
import nonamecrackers2.witherstormmod.common.command.BowelsCommands;
import nonamecrackers2.witherstormmod.common.command.ChunkLoaderCommands;
import nonamecrackers2.witherstormmod.common.command.ConsumedEntitiesCommands;
import nonamecrackers2.witherstormmod.common.command.ConversionCommands;
import nonamecrackers2.witherstormmod.common.command.CreateClusterCommand;
import nonamecrackers2.witherstormmod.common.command.DebugCommands;
import nonamecrackers2.witherstormmod.common.command.DoDeathSequenceCommand;
import nonamecrackers2.witherstormmod.common.command.ExplodeStormCommand;
import nonamecrackers2.witherstormmod.common.command.PhaseCommands;
import nonamecrackers2.witherstormmod.common.command.RandomizeSicknessModifiersCommand;
import nonamecrackers2.witherstormmod.common.command.ReviveStormCommand;
import nonamecrackers2.witherstormmod.common.command.SetEvolutionAttributeCommand;
import nonamecrackers2.witherstormmod.common.command.ShakeScreenCommand;
import nonamecrackers2.witherstormmod.common.command.StartCureCommand;
import nonamecrackers2.witherstormmod.common.command.StartInfectionCommand;
import nonamecrackers2.witherstormmod.common.command.TractorBeamCommands;
import nonamecrackers2.witherstormmod.common.command.UltimateTargetCommands;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;

public class WitherStormModRegisterCommands {
    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
        CommandDispatcher dispatcher = event.getDispatcher();
        PhaseCommands.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        ConsumedEntitiesCommands.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        CreateClusterCommand.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        SetEvolutionAttributeCommand.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        StartInfectionCommand.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        StartCureCommand.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        ExplodeStormCommand.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        ReviveStormCommand.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        BowelsCommands.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        UltimateTargetCommands.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        RandomizeSicknessModifiersCommand.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        DoDeathSequenceCommand.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        TractorBeamCommands.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        ChunkLoaderCommands.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        ConversionCommands.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        ShakeScreenCommand.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        if (!FMLEnvironment.production) {
            DebugCommands.register((CommandDispatcher<CommandSourceStack>)dispatcher);
        }
        ConfigCommandBuilder.builder((CommandDispatcher)event.getDispatcher(), (String)"witherstormmod").addSpec(ModConfig.Type.SERVER, WitherStormModConfig.SERVER_SPEC).register();
    }
}

