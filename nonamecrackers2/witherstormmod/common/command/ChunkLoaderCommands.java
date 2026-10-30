/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  net.minecraft.ChatFormatting
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.Ticket
 *  net.minecraft.util.SortedArraySet
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.ChunkPos
 */
package nonamecrackers2.witherstormmod.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.Ticket;
import net.minecraft.util.SortedArraySet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import nonamecrackers2.witherstormmod.common.capability.WitherStormModChunkLoader;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.mixin.MixinDistanceManager;
import nonamecrackers2.witherstormmod.mixin.MixinServerChunkCache;

public class ChunkLoaderCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder setPhaseCommand = (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(((LiteralArgumentBuilder)Commands.m_82127_((String)"chunkLoader").then(((LiteralArgumentBuilder)Commands.m_82127_((String)"get").then(Commands.m_82129_((String)"storm", (ArgumentType)EntityArgument.m_91449_()).executes(ctx -> ChunkLoaderCommands.get((CommandContext<CommandSourceStack>)ctx, true)))).executes(ctx -> ChunkLoaderCommands.get((CommandContext<CommandSourceStack>)ctx, false)))).then(Commands.m_82127_((String)"refresh").executes(ChunkLoaderCommands::refresh)));
        dispatcher.register(setPhaseCommand);
    }

    private static int get(CommandContext<CommandSourceStack> context, boolean hasStorm) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        WitherStormModChunkLoader loader = (WitherStormModChunkLoader)source.m_81372_().getCapability(WitherStormModCapabilities.CHUNK_LOADER).orElse(null);
        if (loader != null) {
            if (hasStorm) {
                Entity entity = EntityArgument.m_91452_(context, (String)"storm");
                if (entity instanceof WitherStormEntity) {
                    WitherStormEntity storm = (WitherStormEntity)entity;
                    WitherStormModChunkLoader.Instance instance = loader.getInstance(storm.m_20148_());
                    if (instance != null) {
                        source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.chunkloader.get.specific", (Object[])new Object[]{storm.m_5446_(), storm.m_20183_().m_123341_(), storm.m_20183_().m_123343_(), instance.getRadius()}), false);
                        List tickets = ((SortedArraySet)ChunkLoaderCommands.getTickets(source.m_81372_()).get(storm.m_146902_().m_45588_())).stream().filter(t -> t.m_9428_() == WitherStormModChunkLoader.WITHER_STORM).collect(Collectors.toList());
                        source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.chunkloader.get.specific.ticket", (Object[])new Object[]{tickets.size(), entity.m_146902_()}).m_130940_(ChatFormatting.DARK_GRAY), hasStorm);
                        return instance.getRadius();
                    }
                    source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.chunkloader.none"));
                } else {
                    source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
                }
            } else {
                Map<UUID, WitherStormModChunkLoader.Instance> instances = loader.getInstances();
                int totalChunkLoaders = 0;
                for (Map.Entry<UUID, WitherStormModChunkLoader.Instance> entry : instances.entrySet()) {
                    WitherStormModChunkLoader.Instance instance = entry.getValue();
                    ++totalChunkLoaders;
                    ChunkPos pos = instance.getPos();
                    source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.chunkloader.get.specific", (Object[])new Object[]{entry.getKey(), pos.m_151390_(), pos.m_151393_(), instance.getRadius()}).m_130940_(ChatFormatting.DARK_GRAY), false);
                }
                MutableComponent text = Component.m_237110_((String)"commands.witherstormmod.chunkloader.get", (Object[])new Object[]{totalChunkLoaders}).m_130940_(ChatFormatting.YELLOW);
                source.m_288197_(() -> ChunkLoaderCommands.lambda$get$7((Component)text), false);
                List tickets = ChunkLoaderCommands.getTickets(source.m_81372_()).values().stream().flatMap(Collection::stream).filter(t -> t.m_9428_() == WitherStormModChunkLoader.WITHER_STORM).collect(Collectors.toList());
                source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.chunkloader.get.tickets", (Object[])new Object[]{tickets.size(), source.m_81372_().m_46472_().m_135782_()}), hasStorm);
                return totalChunkLoaders;
            }
        }
        return 0;
    }

    private static int refresh(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        WitherStormModChunkLoader loader = (WitherStormModChunkLoader)source.m_81372_().getCapability(WitherStormModCapabilities.CHUNK_LOADER).orElse(null);
        if (loader != null) {
            loader.refreshAllLoaders();
            source.m_288197_(() -> Component.m_237115_((String)"commands.witherstormmod.chunkloader.refresh"), true);
        }
        return 0;
    }

    private static Long2ObjectOpenHashMap<SortedArraySet<Ticket<?>>> getTickets(ServerLevel level) {
        MixinServerChunkCache cache = (MixinServerChunkCache)level.m_7726_();
        MixinDistanceManager manager = (MixinDistanceManager)cache.getDistanceManager();
        return manager.getTickets();
    }

    private static /* synthetic */ Component lambda$get$7(Component text) {
        return text;
    }
}

