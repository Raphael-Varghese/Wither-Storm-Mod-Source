/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.DoubleArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.arguments.DimensionArgument
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.commands.arguments.ResourceLocationArgument
 *  net.minecraft.commands.arguments.coordinates.BlockPosArgument
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.ThrownPotion
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraft.world.item.alchemy.PotionUtils
 *  net.minecraft.world.item.alchemy.Potions
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.WorldGenLevel
 *  net.minecraft.world.level.levelgen.feature.ConfiguredFeature
 *  net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
 *  net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
 *  net.minecraftforge.network.PacketDistributor
 *  net.minecraftforge.server.command.EnumArgument
 */
package nonamecrackers2.witherstormmod.common.command;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Collection;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.DimensionArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.server.command.EnumArgument;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
import nonamecrackers2.witherstormmod.api.common.registry.WitherStormModRegistries;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.ultimatetarget.UltimateTargetManager;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModFeatures;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.packet.CreateDebrisMessage;
import nonamecrackers2.witherstormmod.common.util.EvolutionProfiler;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;
import nonamecrackers2.witherstormmod.common.world.gen.feature.CommandBlockPodiumFeature;

public class DebugCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder setPhaseCommand = (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"witherstormmod").requires(commandSource -> commandSource.m_6761_(2))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_((String)"debug").then(((LiteralArgumentBuilder)Commands.m_82127_((String)"podium").then(Commands.m_82127_((String)"place").then(Commands.m_82129_((String)"dimension", (ArgumentType)DimensionArgument.m_88805_()).then(Commands.m_82129_((String)"pos", (ArgumentType)BlockPosArgument.m_118239_()).executes(DebugCommands::placePodium))))).then(Commands.m_82127_((String)"remove").then(Commands.m_82129_((String)"dimension", (ArgumentType)DimensionArgument.m_88805_()).then(Commands.m_82129_((String)"pos", (ArgumentType)BlockPosArgument.m_118239_()).executes(DebugCommands::removePodium)))))).then(Commands.m_82127_((String)"debris").then(Commands.m_82127_((String)"create").then(Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91449_()).executes(DebugCommands::createDebris))))).then(Commands.m_82127_((String)"deathClusters").then(Commands.m_82127_((String)"drop").then(Commands.m_82129_((String)"storm", (ArgumentType)EntityArgument.m_91449_()).executes(DebugCommands::dropDeathClusters))))).then(Commands.m_82127_((String)"beacon").then(Commands.m_82127_((String)"reset").then(Commands.m_82129_((String)"player", (ArgumentType)EntityArgument.m_91466_()).executes(DebugCommands::resetPlayerBeaconData))))).then(((LiteralArgumentBuilder)Commands.m_82127_((String)"evolutionProfiler").then(Commands.m_82127_((String)"begin").executes(DebugCommands::beginEvolutionProfiler))).then(Commands.m_82127_((String)"query").then(Commands.m_82129_((String)"witherstorm", (ArgumentType)EntityArgument.m_91449_()).executes(DebugCommands::queryEvolutionProfiler))))).then(Commands.m_82127_((String)"splitCluster").then(Commands.m_82129_((String)"blockcluster", (ArgumentType)EntityArgument.m_91449_()).then(Commands.m_82129_((String)"axis", (ArgumentType)EnumArgument.enumArgument(Direction.Axis.class)).executes(DebugCommands::splitBlockCluster))))).then(Commands.m_82127_((String)"symbiont").then(Commands.m_82127_((String)"doSpell").then(Commands.m_82129_((String)"symbiont", (ArgumentType)EntityArgument.m_91449_()).then(Commands.m_82129_((String)"spell", (ArgumentType)ResourceLocationArgument.m_106984_()).executes(DebugCommands::doSymbiontSpell)))))).then(Commands.m_82127_((String)"potionTest").executes(DebugCommands::potionTest))).then(Commands.m_82127_((String)"caveRumble").then(Commands.m_82129_((String)"players", (ArgumentType)EntityArgument.m_91470_()).then(Commands.m_82129_((String)"intensity", (ArgumentType)DoubleArgumentType.doubleArg((double)0.0, (double)1.0)).executes(DebugCommands::doCaveRumble)))));
        dispatcher.register(setPhaseCommand);
    }

    private static int placePodium(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerLevel world = DimensionArgument.m_88808_(context, (String)"dimension");
        BlockPos pos = BlockPosArgument.m_118242_(context, (String)"pos");
        int result = 0;
        try {
            if (((ConfiguredFeature)WitherStormModFeatures.getConfiguredFeature(world, WitherStormModFeatures.COMMAND_BLOCK_PODIUM_FEATURE.getId()).m_203334_()).m_224953_((WorldGenLevel)world, world.m_7726_().m_8481_(), RandomSource.m_216327_(), pos)) {
                result = 1;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static int removePodium(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerLevel world = DimensionArgument.m_88808_(context, (String)"dimension");
        BlockPos pos = BlockPosArgument.m_118242_(context, (String)"pos");
        int result = 0;
        try {
            ConfiguredFeature configured = (ConfiguredFeature)WitherStormModFeatures.getConfiguredFeature(world, WitherStormModFeatures.COMMAND_BLOCK_PODIUM_FEATURE.getId()).m_203334_();
            if (((CommandBlockPodiumFeature)configured.f_65377_()).remove((WorldGenLevel)world, world.m_7726_().m_8481_(), RandomSource.m_216327_(), pos, (FeatureConfiguration)((NoneFeatureConfiguration)configured.f_65378_()))) {
                result = 1;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static int createDebris(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        int phase = 0;
        if (entity != null) {
            if (entity instanceof WitherStormEntity) {
                WitherStormEntity storm = (WitherStormEntity)entity;
                phase = storm.getPhase();
                WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> storm.m_9236_().m_46472_()), (Object)new CreateDebrisMessage(storm, storm.isDeadOrPlayingDead()));
                source.m_288197_(() -> Component.m_237110_((String)"commands.witherstormmod.createDebris.success", (Object[])new Object[]{storm.m_5446_()}), true);
            } else {
                source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
            }
        }
        return phase;
    }

    private static int dropDeathClusters(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"storm");
        int phase = 0;
        if (entity != null) {
            if (entity instanceof WitherStormEntity) {
                WitherStormEntity storm = (WitherStormEntity)entity;
                phase = storm.getPhase();
                storm.dropMassCluster(phase);
            } else {
                source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
            }
        }
        return phase;
    }

    private static int resetPlayerBeaconData(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.m_91474_(context, (String)"player");
        player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> data.setActivatedSuperBeacon(false));
        return 0;
    }

    private static int beginEvolutionProfiler(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = ((CommandSourceStack)context.getSource()).m_230896_();
        WitherStormEntity storm = (WitherStormEntity)((EntityType)WitherStormModEntityTypes.WITHER_STORM.get()).m_20615_(player.m_9236_());
        storm.m_20219_(player.m_20182_().m_82520_(0.0, 10.0, 0.0));
        storm.getEvolutionProfiler().begin();
        player.m_9236_().m_7967_((Entity)storm);
        WitherStormModConfig.SERVER.ultimateTargetingType.set((Object)UltimateTargetManager.TargetingType.NONE);
        WitherStormModConfig.SERVER.evolutionAttributeModifier.set((Object)((Double)WitherStormModConfig.SERVER.evolutionAttributeModifier.getDefault()));
        return 0;
    }

    private static int queryEvolutionProfiler(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CommandSourceStack source = (CommandSourceStack)context.getSource();
        Entity entity = EntityArgument.m_91452_(context, (String)"witherstorm");
        if (entity != null) {
            if (entity instanceof WitherStormEntity) {
                WitherStormEntity storm = (WitherStormEntity)entity;
                EvolutionProfiler profiler = storm.getEvolutionProfiler();
                if (profiler.isProfiling()) {
                    source.m_288197_(() -> Component.m_237113_((String)("Consumed entities per second: " + (double)Math.round(storm.getEvolutionProfiler().getConsumedEntitiesPerSecond() * 10.0) / 10.0)), false);
                }
            } else {
                source.m_81352_((Component)Component.m_237115_((String)"commands.witherstormmod.entity.arg.invalid"));
            }
        }
        return 0;
    }

    private static int splitBlockCluster(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        BlockClusterEntity cluster;
        BlockClusterEntity split;
        Entity entity = EntityArgument.m_91452_(context, (String)"blockcluster");
        Direction.Axis axis = (Direction.Axis)context.getArgument("axis", Direction.Axis.class);
        if (entity instanceof BlockClusterEntity && (split = (cluster = (BlockClusterEntity)entity).splitAt(axis)) != null) {
            entity.m_9236_().m_7967_((Entity)split);
        }
        return 0;
    }

    private static int doSymbiontSpell(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Entity entity = EntityArgument.m_91452_(context, (String)"symbiont");
        ResourceLocation spell = (ResourceLocation)context.getArgument("spell", ResourceLocation.class);
        if (entity instanceof WitheredSymbiontEntity) {
            WitheredSymbiontEntity symbiont = (WitheredSymbiontEntity)entity;
            SpellType type = (SpellType)WitherStormModRegistries.SPELL_TYPES.get().getValue(spell);
            if (type != null) {
                symbiont.m_6710_((LivingEntity)((CommandSourceStack)context.getSource()).m_230896_());
                symbiont.setAndCastSpell(type);
            }
            return 1;
        }
        return 0;
    }

    private static int potionTest(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = ((CommandSourceStack)context.getSource()).m_230896_();
        ThrownPotion potion = new ThrownPotion(player.m_9236_(), (LivingEntity)player);
        ItemStack item = new ItemStack((ItemLike)Items.f_42736_);
        PotionUtils.m_43549_((ItemStack)item, (Potion)Potions.f_43599_);
        PotionUtils.m_43552_((ItemStack)item, (Collection)Lists.newArrayList((Object[])new MobEffectInstance[]{new MobEffectInstance(MobEffects.f_19615_, 60, 2)}));
        potion.m_37446_(item);
        player.m_9236_().m_7967_((Entity)potion);
        return 0;
    }

    private static int doCaveRumble(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Collection players = EntityArgument.m_91477_(context, (String)"players");
        double intensity = DoubleArgumentType.getDouble(context, (String)"intensity");
        RandomSource random = RandomSource.m_216327_();
        for (ServerPlayer player : players) {
            WorldUtil.doCaveRumble(((CommandSourceStack)context.getSource()).m_81372_(), player, intensity, random);
        }
        return 0;
    }
}

