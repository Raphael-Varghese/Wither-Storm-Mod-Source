/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.block.PointedDripstoneBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={PointedDripstoneBlock.class})
public interface MixinPointedDripstoneBlock {
    @Invoker
    public static boolean callIsStalagmite(BlockState state) {
        throw new AssertionError();
    }

    @Invoker
    public static void callSpawnFallingStalactite(BlockState state, ServerLevel level, BlockPos pos) {
        throw new AssertionError();
    }
}

