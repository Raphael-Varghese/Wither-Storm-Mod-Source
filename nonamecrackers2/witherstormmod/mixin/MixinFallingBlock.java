/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.item.FallingBlockEntity
 *  net.minecraft.world.level.block.FallingBlock
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.block.FallingBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={FallingBlock.class})
public interface MixinFallingBlock {
    @Invoker
    public void callFalling(FallingBlockEntity var1);
}

