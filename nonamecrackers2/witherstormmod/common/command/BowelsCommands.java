/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 */
package nonamecrackers2.witherstormmod.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;

public class BowelsCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder setPhaseCommand = (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(((LiteralArgumentBuilder)Commands.m_82127_((String)"bowels").then(Commands.m_82129_((String)"entity", (ArgumentType)EntityArgument.m_91449_()).then(Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91449_()).executes(BowelsCommands::enterStorm)))).then(Commands.m_82127_((String)"new").then(Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91449_()).executes(BowelsCommands::newBowels))));
        dispatcher.register(setPhaseCommand);
    }

    private static int enterStorm(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity enteringEntity = EntityArgument.m_91452_(context, (String)"entity");
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        int phase = 0;
        if (entity != null) {
            if (entity instanceof WitherStormEntity) {
                WitherStormEntity storm = (WitherStormEntity)entity;
                phase = storm.getPhase();
                if (!enteringEntity.m_9236_().m_46472_().m_135782_().equals((Object)WitherStormMod.bowelsLocation())) {
                    WitherStormBowelsManager.BowelsEnterStatus flag = WitherStormBowelsManager.enter((ServerLevel)storm.m_9236_(), storm, enteringEntity);
                    switch (flag) {
                        case ENTITY_CANNOT_CHANGE: {
                            source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.enterBowels.failure.cannotChangeDim"));
                        }
                        case CANT_SETUP_BOWELS: {
                            source.m_81352_((Component)Component.m_237110_((String)"commands.witherstormmod.enterBowels.failure", (Object[])new Object[]{storm.m_5446_()}));
                        }
                    }
                } else {
                    source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.enterBowels.dim.invalid"));
                }
            } else {
                source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
            }
        }
        return phase;
    }

    private static int newBowels(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        ServerLevel world = source.m_81372_();
        int phase = 0;
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            phase = storm.getPhase();
            ServerLevel bowels = WitherStormMod.bowels(world);
            bowels.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> {
                WitherStormBowelsManager.BowelsInstance instance = manager.get(storm.m_20148_());
                if (instance != null && !instance.isCompleted()) {
                    instance.setCompleted(true);
                    source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.newBowels.success", (Object[])new Object[]{storm.m_5446_()}), true);
                } else {
                    source.m_81352_((Component)Component.m_237110_((String)"commands.witherstormmod.newBowels.failure", (Object[])new Object[]{storm.m_5446_()}));
                }
            });
        } else {
            source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
        }
        return phase;
    }
}

