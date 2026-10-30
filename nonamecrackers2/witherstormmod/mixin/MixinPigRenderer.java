/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.PigRenderer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.animal.Pig
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Pig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={PigRenderer.class})
public class MixinPigRenderer {
    @Unique
    private static final ResourceLocation REUBEN_TEXTURE = new ResourceLocation("witherstormmod", "textures/entity/misc/reuben.png");

    @Inject(method={"getTextureLocation(Lnet/minecraft/world/entity/animal/Pig;)Lnet/minecraft/resources/ResourceLocation;"}, at={@At(value="HEAD")}, cancellable=true)
    public void getTextureLocationHead(Pig pig, CallbackInfoReturnable<ResourceLocation> ci) {
        if (pig.m_8077_() && pig.m_7755_().getString().equals("reuben")) {
            ci.setReturnValue((Object)REUBEN_TEXTURE);
        }
    }
}

