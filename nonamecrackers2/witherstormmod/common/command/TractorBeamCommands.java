/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.datafixers.util.Pair
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.entity.Entity
 */
package nonamecrackers2.witherstormmod.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.datafixers.util.Pair;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;

public class TractorBeamCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder setPhaseCommand = (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(Commands.m_82127_((String)"tractorBeam").then(Commands.m_82127_((String)"isInBeam").then(Commands.m_82129_((String)"entity", (ArgumentType)EntityArgument.m_91449_()).then(Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91449_()).executes(TractorBeamCommands::isInBeam)))));
        dispatcher.register(setPhaseCommand);
    }

    private static int isInBeam(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity target = EntityArgument.m_91452_(context, (String)"entity");
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        int head = -1;
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            Pair<Boolean, Integer> result = TractorBeamHelper.isInsideTractorBeam(target, storm, 4.0);
            if (((Boolean)result.getFirst()).booleanValue()) {
                head = (Integer)result.getSecond();
                source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.isInTractorBeam.success", (Object[])new Object[]{target.m_5446_(), result.getSecond()}), false);
            } else {
                source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.isInTractorBeam.fail", (Object[])new Object[]{target.m_5446_()}), false);
            }
        } else {
            source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
        }
        return head;
    }
}

