/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  net.minecraft.commands.arguments.selector.EntitySelector
 *  net.minecraft.commands.arguments.selector.EntitySelectorParser
 *  net.minecraft.commands.arguments.selector.options.EntitySelectorOptions
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraftforge.common.command.IEntitySelectorType
 */
package nonamecrackers2.witherstormmod.common.command.argument.entityselector;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.concurrent.CompletableFuture;
import net.minecraft.commands.arguments.selector.EntitySelector;
import net.minecraft.commands.arguments.selector.EntitySelectorParser;
import net.minecraft.commands.arguments.selector.options.EntitySelectorOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.command.IEntitySelectorType;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;

public class WitherStormSelector
implements IEntitySelectorType {
    public EntitySelector build(EntitySelectorParser parser) throws CommandSyntaxException {
        parser.m_121237_(1);
        parser.m_121279_(true);
        parser.m_121268_(EntitySelectorParser.f_121197_);
        parser.m_121241_((EntityType)WitherStormModEntityTypes.WITHER_STORM.get());
        parser.m_121272_(Entity::m_6084_);
        StringReader reader = parser.m_121346_();
        parser.m_121270_((b, c) -> WitherStormSelector.suggestOpenOptions(parser, b));
        if (reader.canRead() && reader.peek() == '[') {
            reader.skip();
            parser.m_121270_((b, c) -> WitherStormSelector.suggestOptionsKeyOrClose(parser, b));
            parser.m_121317_();
        }
        parser.m_121229_();
        return parser.m_121230_();
    }

    private static CompletableFuture<Suggestions> suggestOpenOptions(EntitySelectorParser parser, SuggestionsBuilder builder) {
        builder.suggest(String.valueOf('['));
        return builder.buildFuture();
    }

    private static CompletableFuture<Suggestions> suggestOptionsKeyOrClose(EntitySelectorParser parser, SuggestionsBuilder builder) {
        builder.suggest(String.valueOf(']'));
        EntitySelectorOptions.m_121440_((EntitySelectorParser)parser, (SuggestionsBuilder)builder);
        return builder.buildFuture();
    }

    public Component getSuggestionTooltip() {
        return Component.m_237115_((String)"argument.witherstormmod.entity.selector.wither_storm");
    }
}

