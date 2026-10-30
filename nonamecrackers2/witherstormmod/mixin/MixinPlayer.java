/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Player.class})
public class MixinPlayer {
    @Inject(method={"wantsToStopRiding"}, at={@At(value="HEAD")}, cancellable=true)
    protected void wantsToStopRiding(CallbackInfoReturnable<Boolean> callback) {
        if (((Player)this).m_20202_() instanceof TentacleEntity && ((Boolean)WitherStormModConfig.COMMON.playerCannotDismountTentacles.get()).booleanValue()) {
            callback.setReturnValue((Object)false);
        }
    }

    @Redirect(method={"isScoping"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    public boolean redirectIsIsScoping(ItemStack stack, Item item) {
        return stack.m_150930_(item) || stack.m_150930_((Item)WitherStormModItems.PHASOMETER.get());
    }
}

