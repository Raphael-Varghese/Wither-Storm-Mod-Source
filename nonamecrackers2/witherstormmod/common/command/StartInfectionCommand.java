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
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 */
package nonamecrackers2.witherstormmod.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Optional;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModEntityTags;

public class StartInfectionCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder setPhaseCommand = (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(Commands.m_82127_((String)"sickness").then(Commands.m_82127_((String)"infect").then(Commands.m_82129_((String)"entity", (ArgumentType)EntityArgument.m_91449_()).executes(StartInfectionCommand::startInfection))));
        dispatcher.register(setPhaseCommand);
    }

    private static int startInfection(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"entity");
        int timeTillInfected = 0;
        if (entity != null) {
            if (entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity)entity;
                if (!entity.m_6095_().m_204039_(WitherStormModEntityTags.WITHER_SICKNESS_IMMUNE)) {
                    Optional optional = living.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).resolve();
                    if (optional.isPresent()) {
                        WitherSicknessTracker tracker = (WitherSicknessTracker)optional.get();
                        timeTillInfected = tracker.getDelayTicks();
                        if (!tracker.isInfected()) {
                            MutableComponent component = Component.m_237110_((String)"commands.witherstormmod.sickness.startInfection", (Object[])new Object[]{living.m_5446_()});
                            source.m_288197_(() -> component, true);
                            tracker.beginInfection();
                        } else {
                            MutableComponent component = Component.m_237110_((String)"commands.witherstormmod.sickness.alreadyInfected", (Object[])new Object[]{living.m_5446_()});
                            source.m_81352_((Component)component);
                        }
                    }
                } else {
                    MutableComponent component = Component.m_237110_((String)"commands.witherstormmod.sickness.immune", (Object[])new Object[]{entity.m_5446_()});
                    source.m_81352_((Component)component);
                }
            } else {
                MutableComponent component = Component.m_237115_((String)"commands.witherstormmod.entity.arg.notLiving");
                source.m_81352_((Component)component);
            }
        }
        return timeTillInfected;
    }
}

