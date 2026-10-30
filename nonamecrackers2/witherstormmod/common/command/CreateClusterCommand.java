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
 *  javax.annotation.Nullable
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.arguments.CompoundTagArgument
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.commands.arguments.coordinates.BlockPosArgument
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.level.Level
 */
package nonamecrackers2.witherstormmod.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import javax.annotation.Nullable;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.CompoundTagArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;

public class CreateClusterCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder createClusterCommand = (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(Commands.m_82127_((String)"cluster").then(((LiteralArgumentBuilder)Commands.m_82127_((String)"create").then(Commands.m_82129_((String)"start", (ArgumentType)BlockPosArgument.m_118239_()).then(Commands.m_82129_((String)"end", (ArgumentType)BlockPosArgument.m_118239_()).then(((RequiredArgumentBuilder)Commands.m_82129_((String)"time", (ArgumentType)IntegerArgumentType.integer((int)0)).then(((RequiredArgumentBuilder)Commands.m_82129_((String)"data", (ArgumentType)CompoundTagArgument.m_87657_()).then(Commands.m_82129_((String)"storm", (ArgumentType)EntityArgument.m_91449_()).executes(context -> CreateClusterCommand.createClusterFill((CommandContext<CommandSourceStack>)context, EntityArgument.m_91452_((CommandContext)context, (String)"storm"), CompoundTagArgument.m_87660_((CommandContext)context, (String)"data"))))).executes(context -> CreateClusterCommand.createClusterFill((CommandContext<CommandSourceStack>)context, null, CompoundTagArgument.m_87660_((CommandContext)context, (String)"data"))))).executes(context -> CreateClusterCommand.createClusterFill((CommandContext<CommandSourceStack>)context, null, null)))))).then(Commands.m_82129_((String)"pos", (ArgumentType)BlockPosArgument.m_118239_()).then(Commands.m_82129_((String)"radius", (ArgumentType)IntegerArgumentType.integer((int)0)).then(((RequiredArgumentBuilder)Commands.m_82129_((String)"time", (ArgumentType)IntegerArgumentType.integer((int)0)).then(((RequiredArgumentBuilder)Commands.m_82129_((String)"data", (ArgumentType)CompoundTagArgument.m_87657_()).then(Commands.m_82129_((String)"storm", (ArgumentType)EntityArgument.m_91449_()).executes(context -> CreateClusterCommand.createCluster((CommandContext<CommandSourceStack>)context, EntityArgument.m_91452_((CommandContext)context, (String)"storm"), CompoundTagArgument.m_87660_((CommandContext)context, (String)"data"))))).executes(context -> CreateClusterCommand.createCluster((CommandContext<CommandSourceStack>)context, null, CompoundTagArgument.m_87660_((CommandContext)context, (String)"data"))))).executes(context -> CreateClusterCommand.createCluster((CommandContext<CommandSourceStack>)context, null, null)))))));
        dispatcher.register(createClusterCommand);
    }

    private static int createCluster(CommandContext<CommandSourceStack> context, @Nullable Entity entity, @Nullable CompoundTag tag) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        ServerLevel world = source.m_81372_();
        BlockPos pos = BlockPosArgument.m_118242_(context, (String)"pos");
        int radius = IntegerArgumentType.getInteger(context, (String)"radius");
        int time = IntegerArgumentType.getInteger(context, (String)"time");
        BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_((Level)world);
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            cluster.m_20242_(true);
            cluster.setPhysics(false);
            storm.getTrackedEntities().trackEntityToConsume(cluster);
        } else if (entity != null) {
            source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
            return 0;
        }
        if (tag != null) {
            cluster.m_20258_(tag);
        }
        cluster.populateWithRadius(pos, radius, blockstate -> true);
        cluster.setTime(time);
        world.m_7967_((Entity)cluster);
        return cluster.getSize();
    }

    private static int createClusterFill(CommandContext<CommandSourceStack> context, @Nullable Entity entity, @Nullable CompoundTag tag) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        ServerLevel world = source.m_81372_();
        BlockPos start = BlockPosArgument.m_118242_(context, (String)"start");
        BlockPos end = BlockPosArgument.m_118242_(context, (String)"end");
        int time = IntegerArgumentType.getInteger(context, (String)"time");
        BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_((Level)world);
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            cluster.m_20242_(true);
            cluster.setPhysics(false);
            storm.getTrackedEntities().trackEntityToConsume(cluster);
        } else if (entity != null) {
            source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
            return 0;
        }
        if (tag != null) {
            cluster.m_20258_(tag);
        }
        cluster.populate(start, end, blockstate -> true);
        cluster.setTime(time);
        world.m_7967_((Entity)cluster);
        return cluster.getSize();
    }
}

