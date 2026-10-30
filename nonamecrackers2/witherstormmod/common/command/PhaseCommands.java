/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.DoubleArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 */
package nonamecrackers2.witherstormmod.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.util.EvolutionProfiler;

public class PhaseCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder setPhaseCommand = (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"phase").then(Commands.m_82127_((String)"set").then(Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91449_()).then(Commands.m_82129_((String)"phase", (ArgumentType)DoubleArgumentType.doubleArg((double)0.0)).executes(PhaseCommands::setPhase))))).then(Commands.m_82127_((String)"get").then(Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91449_()).executes(PhaseCommands::getPhase)))).then(Commands.m_82127_((String)"evolve").then(((RequiredArgumentBuilder)Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91449_()).executes(ctx -> PhaseCommands.evolve((CommandContext<CommandSourceStack>)ctx, false))).then(Commands.m_82127_((String)"force").executes(ctx -> PhaseCommands.evolve((CommandContext<CommandSourceStack>)ctx, true))))));
        dispatcher.register(setPhaseCommand);
    }

    private static int setPhase(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        double phase = DoubleArgumentType.getDouble(context, (String)"phase");
        if (entity != null) {
            if (entity instanceof WitherStormEntity) {
                WitherStormEntity storm = (WitherStormEntity)entity;
                boolean setPhase = false;
                setPhase = phase >= 1.1 && phase < 1.2 ? storm.setPhase(1, storm.adjustAmountForEvolutionSpeed(150)) : (phase >= 1.2 && phase < 2.0 ? storm.setPhase(1, storm.adjustAmountForEvolutionSpeed(250)) : (phase >= 2.1 && phase < 3.0 ? storm.setPhase(2, storm.adjustAmountForEvolutionSpeed(800)) : (phase >= 3.1 && phase < 3.2 ? storm.setPhase(3, storm.adjustAmountForEvolutionSpeed(2350)) : (phase >= 3.2 && phase < 4.0 ? storm.setPhase(3, storm.adjustAmountForEvolutionSpeed(3500)) : (phase >= 4.5 && phase < 5.0 ? storm.setPhase(4, storm.getSubPhaseRequirement(4) + 1) : (phase >= 5.25 && phase < 5.5 ? storm.setPhase(5, storm.getSubPhaseRequirement(5) + 1) : (phase >= 5.5 && phase < 6.0 ? storm.setPhase(5, storm.getConsumptionAmountForPhase(5) + 1) : (phase >= 6.5 && phase < 7.0 ? storm.setPhase(6, storm.getSubPhaseRequirement(6) + 1) : (phase >= 7.5 ? storm.setPhase(7, storm.getConsumptionAmountForPhase(7) + 1) : storm.setPhase(Mth.m_14107_((double)phase)))))))))));
                if (!setPhase) {
                    MutableComponent component = Component.m_237110_((String)"commands.witherstormmod.setphase.invalid", (Object[])new Object[]{phase, storm.m_5446_()});
                    source.m_81352_((Component)component);
                } else {
                    source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.setphase.success", (Object[])new Object[]{phase, storm.m_5446_()}), true);
                    EvolutionProfiler profiler = storm.getEvolutionProfiler();
                    if (profiler.isProfiling()) {
                        profiler.begin();
                    }
                }
            } else {
                MutableComponent component = Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid");
                source.m_81352_((Component)component);
            }
        }
        return 0;
    }

    private static int getPhase(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        int phase = -1;
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            phase = storm.getPhase();
            source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.getphase.result", (Object[])new Object[]{storm.m_5446_(), storm.getPhase()}), false);
        } else {
            source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
        }
        return phase;
    }

    private static int evolve(CommandContext<CommandSourceStack> context, boolean force) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        int phase = 0;
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            if (storm.evolve(force)) {
                source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.evolve.success", (Object[])new Object[]{storm.m_5446_(), storm.getPhase()}), true);
            } else {
                source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.evolve.fail"));
            }
            phase = storm.getPhase();
        } else {
            source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
        }
        return phase;
    }
}

