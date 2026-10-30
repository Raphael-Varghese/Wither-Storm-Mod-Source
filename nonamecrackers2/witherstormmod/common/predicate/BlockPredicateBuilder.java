/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 */
package nonamecrackers2.witherstormmod.common.predicate;

import java.util.Objects;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import nonamecrackers2.witherstormmod.common.predicate.PredicateBuilder;

public class BlockPredicateBuilder
extends PredicateBuilder<BlockState, BlockPredicateBuilder> {
    private BlockPredicateBuilder(PredicateBuilder.Comparison comparison) {
        super(comparison);
    }

    public static BlockPredicateBuilder or() {
        return new BlockPredicateBuilder(PredicateBuilder.Comparison.OR);
    }

    public static BlockPredicateBuilder and() {
        return new BlockPredicateBuilder(PredicateBuilder.Comparison.AND);
    }

    public BlockPredicateBuilder isExactly(Block block) {
        return (BlockPredicateBuilder)this.addTest(b -> b.m_60713_(block));
    }

    public BlockPredicateBuilder isNotExactly(Block block) {
        return (BlockPredicateBuilder)this.addTest(b -> !b.m_60713_(block));
    }

    public BlockPredicateBuilder isExactly(BlockState state) {
        return (BlockPredicateBuilder)this.addTest(b -> Objects.equals(state, b));
    }

    public BlockPredicateBuilder isNotExactly(BlockState state) {
        return (BlockPredicateBuilder)this.addTest(b -> !Objects.equals(state, b));
    }

    public BlockPredicateBuilder isTag(TagKey<Block> tag) {
        return (BlockPredicateBuilder)this.addTest(b -> b.m_204336_(tag));
    }

    public BlockPredicateBuilder isNotTag(TagKey<Block> tag) {
        return (BlockPredicateBuilder)this.addTest(b -> !b.m_204336_(tag));
    }

    public BlockPredicateBuilder isAir() {
        return (BlockPredicateBuilder)this.addTest(b -> b.m_60795_());
    }

    public BlockPredicateBuilder isNotAir() {
        return (BlockPredicateBuilder)this.addTest(b -> !b.m_60795_());
    }

    public BlockPredicateBuilder isNotAFluid() {
        return (BlockPredicateBuilder)this.addTest(b -> b.m_60819_().m_76178_());
    }

    public BlockPredicateBuilder isAFluid() {
        return (BlockPredicateBuilder)this.addTest(b -> !b.m_60819_().m_76178_());
    }
}

