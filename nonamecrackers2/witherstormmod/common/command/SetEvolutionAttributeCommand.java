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
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.attributes.Attribute
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModAttributes;

public class SetEvolutionAttributeCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder setPhaseCommand = (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(Commands.m_82127_((String)"evolutionSpeed").then(Commands.m_82127_((String)"set").then(Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91449_()).then(Commands.m_82129_((String)"value", (ArgumentType)DoubleArgumentType.doubleArg((double)0.1, (double)32.0)).executes(SetEvolutionAttributeCommand::setEvolutionSpeed)))));
        dispatcher.register(setPhaseCommand);
    }

    private static int setEvolutionSpeed(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        double value = DoubleArgumentType.getDouble(context, (String)"value");
        int phase = 0;
        if (entity != null) {
            if (entity instanceof WitherStormEntity) {
                WitherStormEntity storm = (WitherStormEntity)entity;
                phase = storm.getPhase();
                storm.m_21051_((Attribute)WitherStormModAttributes.EVOLUTION_SPEED.get()).m_22100_(value);
                storm.setPhase(storm.getPhase());
                MutableComponent component = Component.m_237110_((String)"commands.witherstormmod.setevolution.success", (Object[])new Object[]{value, storm.m_5446_()});
                source.m_288197_(() -> component, true);
            } else {
                MutableComponent component = Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid");
                source.m_81352_((Component)component);
            }
        }
        return phase;
    }
}

