/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.projectile.ShulkerBullet
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package nonamecrackers2.witherstormmod.mixin;

import javax.annotation.Nullable;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={ShulkerBullet.class})
public interface IMixinShulkerBullet {
    @Accessor
    public void setFinalTarget(Entity var1);

    @Accessor
    public void setCurrentMoveDirection(Direction var1);

    @Invoker
    public void callSelectNextMoveDirection(@Nullable Direction.Axis var1);
}

