/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.world.item.context.UseOnContext
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.RotatedPillarBlock
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraftforge.common.ToolAction
 *  net.minecraftforge.common.ToolActions
 *  org.jetbrains.annotations.Nullable
 */
package nonamecrackers2.witherstormmod.common.block;

import java.util.function.Supplier;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

public class StrippableLogBlock
extends RotatedPillarBlock {
    private final Supplier<Block> supplier;

    public StrippableLogBlock(BlockBehaviour.Properties properties, Supplier<Block> supplier) {
        super(properties);
        this.supplier = supplier;
    }

    @Nullable
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        BlockState transformed = super.getToolModifiedState(state, context, toolAction, simulate);
        if (transformed == null && ToolActions.AXE_STRIP == toolAction) {
            return (BlockState)this.supplier.get().m_49966_().m_61124_((Property)RotatedPillarBlock.f_55923_, (Comparable)((Direction.Axis)state.m_61143_((Property)RotatedPillarBlock.f_55923_)));
        }
        return transformed;
    }
}

