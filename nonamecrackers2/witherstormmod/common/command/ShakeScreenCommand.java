/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.FloatArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.commands.arguments.TimeArgument
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraftforge.network.PacketDistributor
 */
package nonamecrackers2.witherstormmod.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Collection;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.TimeArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;

public class ShakeScreenCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder setPhaseCommand = (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(Commands.m_82127_((String)"screenShake").then(Commands.m_82129_((String)"players", (ArgumentType)EntityArgument.m_91470_()).then(Commands.m_82129_((String)"time", (ArgumentType)TimeArgument.m_113037_()).then(Commands.m_82129_((String)"strength", (ArgumentType)FloatArgumentType.floatArg((float)0.0f)).executes(ShakeScreenCommand::shakeScreen)))));
        dispatcher.register(setPhaseCommand);
    }

    private static int shakeScreen(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack stack = (CommandSourceStack)context.getSource();
        Collection players = EntityArgument.m_91477_(context, (String)"players");
        int time = IntegerArgumentType.getInteger(context, (String)"time");
        if (time > 1200) {
            stack.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.screenShake.fail"));
            return -1;
        }
        float strength = FloatArgumentType.getFloat(context, (String)"strength");
        for (ServerPlayer player : players) {
            WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> player), (Object)new ShakeScreenMessage(time, strength));
        }
        stack.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.screenShake.success", (Object[])new Object[]{players.size()}), true);
        return players.size();
    }
}

