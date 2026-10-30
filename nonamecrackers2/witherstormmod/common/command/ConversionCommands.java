/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.commands.arguments.coordinates.BlockPosArgument
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.levelgen.structure.BoundingBox
 */
package nonamecrackers2.witherstormmod.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;

public class ConversionCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder setPhaseCommand = (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"convert").then(Commands.m_82127_((String)"canConvert").then(((RequiredArgumentBuilder)Commands.m_82129_((String)"mob", (ArgumentType)EntityArgument.m_91449_()).executes(ctx -> ConversionCommands.canConvert((CommandContext<CommandSourceStack>)ctx, false))).then(Commands.m_82127_((String)"fromWitherSickness").executes(ctx -> ConversionCommands.canConvert((CommandContext<CommandSourceStack>)ctx, true)))))).then(((RequiredArgumentBuilder)Commands.m_82129_((String)"mob", (ArgumentType)EntityArgument.m_91449_()).then(Commands.m_82127_((String)"toSickened").executes(ConversionCommands::convertMob))).then(Commands.m_82127_((String)"toCured").executes(ConversionCommands::convertMobBack)))).then(((RequiredArgumentBuilder)Commands.m_82129_((String)"pos", (ArgumentType)BlockPosArgument.m_118239_()).then(Commands.m_82129_((String)"to", (ArgumentType)BlockPosArgument.m_118239_()).executes(ConversionCommands::convertArea))).executes(ConversionCommands::convertBlock)));
        dispatcher.register(setPhaseCommand);
    }

    private static int canConvert(CommandContext<CommandSourceStack> context, boolean fromWitherSickness) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"mob");
        if (entity instanceof Mob) {
            if (WorldTainting.getInstance().canConvertMob(entity, fromWitherSickness)) {
                source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.conversion.convert.possible", (Object[])new Object[]{entity.m_5446_()}), false);
                return 1;
            }
            source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.conversion.convert.impossible", (Object[])new Object[]{entity.m_5446_()}), false);
            return 0;
        }
        source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.conversion.invalid"));
        return -1;
    }

    private static int convertMob(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"mob");
        if (entity instanceof Mob) {
            Mob mob = (Mob)entity;
            if (WorldTainting.getInstance().convertMob(mob, false)) {
                source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.conversion.success", (Object[])new Object[]{entity.m_5446_()}), true);
                return 1;
            }
            source.m_288197_(() -> Component.m_237115_((String)"commands.witherstormmod.conversion.fail"), false);
            return 0;
        }
        source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.conversion.invalid"));
        return -1;
    }

    private static int convertMobBack(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"mob");
        if (entity instanceof Mob && entity instanceof WitherSickened) {
            WitherSickened witherSickened = (WitherSickened)entity;
            if (witherSickened.cure(source.m_81372_())) {
                source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.conversion.success", (Object[])new Object[]{entity.m_5446_()}), true);
                return 1;
            }
            source.m_288197_(() -> Component.m_237115_((String)"commands.witherstormmod.conversion.fail"), false);
            return 0;
        }
        source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.conversion.invalid"));
        return -1;
    }

    private static int convertBlock(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        BlockPos pos = BlockPosArgument.m_118242_(context, (String)"pos");
        if (WorldTainting.getInstance().convertBlock(pos, (Level)source.m_81372_())) {
            source.m_288197_(() -> Component.m_237115_((String)"commands.witherstormmod.conversion.block.success"), true);
            return 1;
        }
        source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.conversion.block.fail"));
        return 0;
    }

    private static int convertArea(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        int maxAllowed;
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        BlockPos start = BlockPosArgument.m_118242_(context, (String)"pos");
        BlockPos end = BlockPosArgument.m_118242_(context, (String)"to");
        BoundingBox box = new BoundingBox(start.m_123341_(), start.m_123342_(), start.m_123343_(), end.m_123341_(), end.m_123342_(), end.m_123343_());
        int area = box.m_71056_() * box.m_71057_() * box.m_71058_();
        if (area > (maxAllowed = source.m_81372_().m_46469_().m_46215_(GameRules.f_263760_))) {
            source.m_81352_((Component)Component.m_237110_((String)"commands.witherstormmod.conversion.block.area.excessive", (Object[])new Object[]{maxAllowed}));
            return -1;
        }
        int count = WorldTainting.getInstance().convertBlocks(box, (Level)source.m_81372_());
        source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.conversion.block.area.success", (Object[])new Object[]{count}), true);
        return count;
    }
}

