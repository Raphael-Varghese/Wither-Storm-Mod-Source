/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.Bee
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import nonamecrackers2.witherstormmod.common.entity.SickenedBee;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Bee.class})
public abstract class MixinBee
extends Animal {
    private MixinBee() {
        super(null, null);
        throw new UnsupportedOperationException();
    }

    @Inject(method={"isFlowerValid"}, at={@At(value="HEAD")}, cancellable=true)
    public void witherstormmod$checkIfFlowerValidForSickenedBee_isFlowerValid(BlockPos pos, CallbackInfoReturnable<Boolean> ci) {
        if ((Bee)this instanceof SickenedBee) {
            ci.setReturnValue((Object)SickenedBee.TAINTABLE.test(this.m_9236_().m_8055_(pos)));
        }
    }
}

