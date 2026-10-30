/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.DoubleArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
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

public class SetPhaseCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder setPhaseCommand = (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(Commands.m_82127_((String)"phase").then(Commands.m_82127_((String)"set").then(Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91449_()).then(Commands.m_82129_((String)"phase", (ArgumentType)DoubleArgumentType.doubleArg((double)0.0)).executes(SetPhaseCommand::setPhase)))));
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
                if (phase == 4.5) {
                    setPhase = storm.setPhase(4);
                    if (setPhase) {
                        storm.setConsumedEntities(storm.getSubPhaseRequirement(4) + 1);
                    }
                } else if (phase == 5.25) {
                    setPhase = storm.setPhase(5);
                    if (setPhase) {
                        storm.setConsumedEntities(storm.getSubPhaseRequirement(5) + 1);
                    }
                } else if (phase == 5.5) {
                    setPhase = storm.setPhase(5);
                    if (setPhase) {
                        storm.setConsumedEntities(storm.getConsumptionAmountForPhase(5) + 1);
                    }
                } else if (phase == 6.5) {
                    setPhase = storm.setPhase(6);
                    if (setPhase) {
                        storm.setConsumedEntities(storm.getSubPhaseRequirement(6) + 1);
                    }
                } else if (phase == 7.5) {
                    setPhase = storm.setPhase(7);
                    if (setPhase) {
                        storm.setConsumedEntities(storm.getConsumptionAmountForPhase(7) + 1);
                    }
                } else {
                    int intPhase = Mth.m_14107_((double)phase);
                    setPhase = storm.setPhase(intPhase);
                }
                if (!setPhase) {
                    MutableComponent component = Component.m_237110_((String)"commands.witherstormmod.setphase.invalid", (Object[])new Object[]{phase, storm.m_5446_()});
                    source.m_81352_((Component)component);
                } else {
                    MutableComponent component = Component.m_237110_((String)"commands.witherstormmod.setphase.success", (Object[])new Object[]{phase, storm.m_5446_()});
                    source.m_288197_(() -> component, true);
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
}

