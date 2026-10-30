/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.monster.Phantom
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={Phantom.class})
public interface IMixinPhantom {
    @Accessor
    public Vec3 getMoveTargetPoint();

    @Accessor
    public void setMoveTargetPoint(Vec3 var1);

    @Accessor
    public BlockPos getAnchorPoint();

    @Accessor
    public void setAnchorPoint(BlockPos var1);
}

