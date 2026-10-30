/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.world.entity.Entity
 */
package nonamecrackers2.witherstormmod.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class ConsumedEntitiesCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder setPhaseCommand = (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"consumedEntities").then(Commands.m_82127_((String)"set").then(((RequiredArgumentBuilder)Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91449_()).then(Commands.m_82129_((String)"amount", (ArgumentType)IntegerArgumentType.integer((int)0)).executes(ctx -> ConsumedEntitiesCommands.setConsumedEntities((CommandContext<CommandSourceStack>)ctx, IntegerArgumentType.getInteger((CommandContext)ctx, (String)"amount"))))).then(Commands.m_82127_((String)"blackhole").executes(ctx -> ConsumedEntitiesCommands.setConsumedEntities((CommandContext<CommandSourceStack>)ctx, 16000)))))).then(Commands.m_82127_((String)"get").then(Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91449_()).executes(ConsumedEntitiesCommands::getConsumedEntities)))).then(Commands.m_82127_((String)"lock").then(Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91460_()).executes(ConsumedEntitiesCommands::lockWitherStorm)))).then(Commands.m_82127_((String)"unlock").then(Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91460_()).executes(ConsumedEntitiesCommands::unlockWitherStorm))));
        dispatcher.register(setPhaseCommand);
    }

    private static int setConsumedEntities(CommandContext<CommandSourceStack> context, int consumedAmount) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            storm.setConsumedEntities(consumedAmount);
            storm.checkConsumptionAmount();
            source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.setconsumed.success", (Object[])new Object[]{consumedAmount, storm.m_5446_()}), true);
        } else {
            MutableComponent component = Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid");
            source.m_81352_((Component)component);
        }
        return consumedAmount;
    }

    private static int getConsumedEntities(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        int consumedAmount = 0;
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            consumedAmount = storm.getConsumedEntities();
            source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.getconsumed.result", (Object[])new Object[]{storm.m_5446_(), storm.getConsumedEntities(), storm.getConsumptionAmountForPhase(storm.getPhase())}), false);
        } else {
            source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
        }
        return consumedAmount;
    }

    private static int lockWitherStorm(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            if (!storm.isConsumptionLocked()) {
                storm.makeConsumptionLocked(true);
                source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.lock.success", (Object[])new Object[]{storm.m_5446_()}), true);
            } else {
                source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.lock.fail"));
            }
        } else {
            source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
        }
        return 0;
    }

    private static int unlockWitherStorm(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            if (storm.isConsumptionLocked()) {
                storm.makeConsumptionLocked(false);
                source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.unlock.success", (Object[])new Object[]{storm.m_5446_()}), true);
            } else {
                source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.unlock.fail"));
            }
        } else {
            source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
        }
        return 0;
    }
}

