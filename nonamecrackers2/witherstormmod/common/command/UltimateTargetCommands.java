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
 *  net.minecraft.ChatFormatting
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.commands.arguments.coordinates.BlockPosArgument
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.chat.ClickEvent
 *  net.minecraft.network.chat.ClickEvent$Action
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.Style
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager;

public class UltimateTargetCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder setPhaseCommand = (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"ultimateTarget").then(Commands.m_82127_((String)"set").then(((RequiredArgumentBuilder)Commands.m_82129_((String)"storm", (ArgumentType)EntityArgument.m_91449_()).then(Commands.m_82129_((String)"entity", (ArgumentType)EntityArgument.m_91449_()).executes(UltimateTargetCommands::setUltimateTarget))).then(Commands.m_82129_((String)"pos", (ArgumentType)BlockPosArgument.m_118239_()).executes(UltimateTargetCommands::setPos))))).then(((LiteralArgumentBuilder)Commands.m_82127_((String)"get").then(Commands.m_82127_((String)"pos").then(Commands.m_82129_((String)"storm", (ArgumentType)EntityArgument.m_91449_()).executes(UltimateTargetCommands::getPos)))).then(Commands.m_82127_((String)"entity").then(Commands.m_82129_((String)"storm", (ArgumentType)EntityArgument.m_91449_()).executes(UltimateTargetCommands::getUltimateTarget))))).then(Commands.m_82127_((String)"clear").then(((RequiredArgumentBuilder)Commands.m_82129_((String)"storm", (ArgumentType)EntityArgument.m_91449_()).then(Commands.m_82127_((String)"entity").executes(UltimateTargetCommands::clearUltimateTarget))).then(Commands.m_82127_((String)"pos").executes(UltimateTargetCommands::clearPos))))).then(((LiteralArgumentBuilder)Commands.m_82127_((String)"distractions").then(Commands.m_82127_((String)"makeDistracted").then(Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91449_()).executes(UltimateTargetCommands::makeUltimateTargetDistracted)))).then(Commands.m_82127_((String)"makeFocused").then(Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91449_()).executes(UltimateTargetCommands::makeUltimateTargetFocused))))).then(((LiteralArgumentBuilder)Commands.m_82127_((String)"chase").then(Commands.m_82127_((String)"begin").then(Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91449_()).executes(UltimateTargetCommands::beginChase)))).then(Commands.m_82127_((String)"stop").then(Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91449_()).executes(UltimateTargetCommands::stopChase)))));
        dispatcher.register(setPhaseCommand);
    }

    private static int setUltimateTarget(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity target = EntityArgument.m_91452_(context, (String)"entity");
        Entity entity = EntityArgument.m_91452_(context, (String)"storm");
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            if (target instanceof LivingEntity && !target.equals((Object)storm) && !(target instanceof WitherStormSegmentEntity)) {
                UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
                if (manager != null) {
                    if (target.m_20148_().equals(manager.getTargetOverride())) {
                        source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.set.duplicate"));
                    } else {
                        manager.setTargetOverride(target.m_20148_());
                        source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.ultimateTarget.set.success", (Object[])new Object[]{target.m_5446_()}), true);
                    }
                } else {
                    source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.invalid"));
                }
            } else {
                source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.set.entity.invalid"));
            }
        } else {
            source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
        }
        return target.m_19879_();
    }

    private static int setPos(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        BlockPos pos = BlockPosArgument.m_118242_(context, (String)"pos");
        Entity entity = EntityArgument.m_91452_(context, (String)"storm");
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
            if (manager != null) {
                if (pos.equals((Object)manager.getBlockTargetOverride())) {
                    source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.set.duplicate"));
                } else {
                    manager.setBlockTargetOverride(pos);
                    source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.ultimateTarget.set.success", (Object[])new Object[]{pos}), true);
                }
            } else {
                source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.invalid"));
            }
        } else {
            source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
        }
        return 0;
    }

    private static int getPos(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"storm");
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            Vec3 pos = storm.getUltimateTargetPos();
            if (pos != null) {
                source.m_288197_(() -> {
                    double x = (double)Math.round(pos.f_82479_ * 10.0) / 10.0;
                    double y = (double)Math.round(pos.f_82480_ * 10.0) / 10.0;
                    double z = (double)Math.round(pos.f_82481_ * 10.0) / 10.0;
                    String tpCommand = "/tp " + x + " " + y + " " + z;
                    ClickEvent event = new ClickEvent(ClickEvent.Action.RUN_COMMAND, tpCommand);
                    Style style = Style.f_131099_.m_131142_(event).m_131140_(ChatFormatting.BLUE);
                    return Component.m_237110_((String)"commands.witherstormmod.ultimateTarget.get.pos", (Object[])new Object[]{storm.m_5446_(), x, y, z, Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.get.pos.click").m_130948_(style)});
                }, false);
            } else {
                source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.ultimateTarget.get.pos.none", (Object[])new Object[]{storm.m_5446_()}), false);
            }
        } else {
            source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
        }
        return 0;
    }

    private static int getUltimateTarget(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"storm");
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            LivingEntity target = storm.getUltimateTarget();
            if (target != null) {
                source.m_288197_(() -> {
                    String tpCommand = "/tp " + target.m_20149_();
                    ClickEvent event = new ClickEvent(ClickEvent.Action.RUN_COMMAND, tpCommand);
                    Style style = Style.f_131099_.m_131142_(event).m_131140_(ChatFormatting.BLUE);
                    return Component.m_237110_((String)"commands.witherstormmod.ultimateTarget.get.player", (Object[])new Object[]{storm.m_5446_(), target.m_5446_(), Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.get.player.click").m_130948_(style)});
                }, false);
            } else {
                source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.ultimateTarget.get.player.none", (Object[])new Object[]{storm.m_5446_()}), false);
            }
        } else {
            source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
        }
        return 0;
    }

    private static int clearUltimateTarget(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"storm");
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
            if (manager != null) {
                manager.setTargetOverride(null);
                source.m_288197_(() -> Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.clear.success"), true);
            } else {
                source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.invalid"));
            }
        } else {
            source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
        }
        return 0;
    }

    private static int clearPos(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"storm");
        if (entity instanceof WitherStormEntity) {
            WitherStormEntity storm = (WitherStormEntity)entity;
            UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
            if (manager != null) {
                manager.setBlockTargetOverride(null);
                source.m_288197_(() -> Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.clear.success"), true);
            } else {
                source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.invalid"));
            }
        } else {
            source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
        }
        return 0;
    }

    private static int makeUltimateTargetDistracted(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        int phase = 0;
        if (entity != null) {
            if (entity instanceof WitherStormEntity) {
                WitherStormEntity storm = (WitherStormEntity)entity;
                UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
                if (manager != null) {
                    if (!manager.isDistracted()) {
                        manager.makeDistracted(UltimateTargetManager.DistractionReason.FORCED);
                        source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.distractions.ultimateTargetDistractions.makeDistracted.success", (Object[])new Object[]{storm.m_5446_()}), true);
                    } else {
                        source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.distractions.ultimateTargetDistractions.makeDistracted.fail"));
                    }
                } else {
                    source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.invalid"));
                }
                phase = storm.getPhase();
            } else {
                source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
            }
        }
        return phase;
    }

    private static int makeUltimateTargetFocused(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        int phase = 0;
        if (entity != null) {
            if (entity instanceof WitherStormEntity) {
                WitherStormEntity storm = (WitherStormEntity)entity;
                UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
                if (manager != null) {
                    if (manager.isDistracted()) {
                        manager.makeFocused();
                        source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.distractions.ultimateTargetDistractions.makeFocused.success", (Object[])new Object[]{storm.m_5446_()}), true);
                    } else {
                        source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.distractions.ultimateTargetDistractions.makeFocused.fail"));
                    }
                } else {
                    source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.invalid"));
                }
                phase = storm.getPhase();
            } else {
                source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
            }
        }
        return phase;
    }

    private static int beginChase(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        if (entity != null) {
            if (entity instanceof WitherStormEntity) {
                WitherStormEntity storm = (WitherStormEntity)entity;
                UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
                if (manager != null) {
                    if (!manager.isTargetStationary()) {
                        manager.accelerate();
                        source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.ultimateTarget.beginChase", (Object[])new Object[]{storm.m_5446_()}), true);
                    } else {
                        source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.cannotBeginChase"));
                    }
                } else {
                    source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.invalid"));
                }
            } else {
                source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
            }
        }
        return 0;
    }

    private static int stopChase(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        if (entity != null) {
            if (entity instanceof WitherStormEntity) {
                WitherStormEntity storm = (WitherStormEntity)entity;
                UltimateTargetManager manager = storm.getUltimateTargetManager().orElse(null);
                if (manager != null) {
                    if (manager.isTargetStationary()) {
                        manager.deaccelerate();
                        source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.ultimateTarget.stopChase", (Object[])new Object[]{storm.m_5446_()}), true);
                    } else {
                        source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.cannotStopChase"));
                    }
                } else {
                    source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.ultimateTarget.invalid"));
                }
            } else {
                source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
            }
        }
        return 0;
    }
}

