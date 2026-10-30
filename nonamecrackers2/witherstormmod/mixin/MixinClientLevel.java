/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.client.event.WitherStormAmbienceEffects;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={ClientLevel.class})
public abstract class MixinClientLevel {
    @Redirect(method={"getSkyColor"}, at=@At(value="NEW", target="(DDD)Lnet/minecraft/world/phys/Vec3;"))
    public Vec3 redirectGetSkyColor(double r, double g, double b, Vec3 cameraPos, float partialTicks) {
        Vec3 color = new Vec3(r, g, b);
        if (((Boolean)WitherStormModConfig.CLIENT.renderSkyAmbienceEffects.get()).booleanValue()) {
            return WitherStormAmbienceEffects.modifySkyColor(this.getMinecraft(), color, cameraPos, partialTicks);
        }
        return color;
    }

    @Inject(method={"getSkyDarken"}, at={@At(value="RETURN")}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD)
    public void redirectGetSkyDarken(float partialTicks, CallbackInfoReturnable<Float> ci, float f, float f1) {
        float toReturn = f1 * 0.8f + 0.2f;
        if (((Boolean)WitherStormModConfig.CLIENT.renderSkyAmbienceEffects.get()).booleanValue()) {
            Minecraft mc = this.getMinecraft();
            ci.setReturnValue((Object)Float.valueOf(WitherStormAmbienceEffects.modifySkyDarken(mc, mc.f_91063_.m_109153_().m_90583_(), toReturn, 0.0f)));
        }
    }

    @Redirect(method={"getCloudColor"}, at=@At(value="NEW", target="(DDD)Lnet/minecraft/world/phys/Vec3;"))
    public Vec3 redirectGetCloudColor(double r, double g, double b, float partialTicks) {
        Vec3 color = new Vec3(r, g, b);
        if (((Boolean)WitherStormModConfig.CLIENT.renderSkyAmbienceEffects.get()).booleanValue()) {
            Minecraft mc = Minecraft.m_91087_();
            return WitherStormAmbienceEffects.modifyCloudColors(mc, mc.f_91063_.m_109153_().m_90583_(), color, partialTicks);
        }
        return color;
    }

    @Inject(method={"shouldTickDeath"}, at={@At(value="HEAD")}, cancellable=true)
    public void shouldTickDeathHead(Entity entity, CallbackInfoReturnable<Boolean> ci) {
        WitherStormEntity storm;
        if (entity instanceof WitherStormEntity && (storm = (WitherStormEntity)entity).isOnDistantRenderer()) {
            ci.setReturnValue((Object)true);
        }
    }

    @Accessor
    public abstract Minecraft getMinecraft();
}

