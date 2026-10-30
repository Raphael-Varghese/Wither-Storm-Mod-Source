/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={ItemEntity.class}, priority=999)
public abstract class MixinItemEntity {
    @Redirect(method={"tick"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/item/ItemEntity;setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V", ordinal=0))
    public void tick_makeItemFloatInVoid(ItemEntity entity, Vec3 delta) {
        if (entity.m_20186_() < (double)entity.m_9236_().m_141937_() && this.m_32055_().m_204117_(WitherStormModItemTags.CANNOT_FALL_IN_VOID)) {
            entity.m_20256_(entity.m_20184_().m_82520_(0.0, 0.04, 0.0));
        } else {
            entity.m_20256_(delta);
        }
    }

    @Shadow
    public abstract ItemStack m_32055_();
}

