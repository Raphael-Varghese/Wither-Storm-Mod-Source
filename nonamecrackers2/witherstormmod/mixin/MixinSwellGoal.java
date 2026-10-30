/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.goal.SwellGoal
 *  net.minecraft.world.entity.monster.Creeper
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package nonamecrackers2.witherstormmod.mixin;

import com.mojang.datafixers.util.Pair;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={SwellGoal.class})
public abstract class MixinSwellGoal {
    @Final
    @Shadow
    private Creeper f_25916_;
    @Shadow
    @Nullable
    private LivingEntity f_25917_;

    @Inject(method={"canUse"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/monster/Creeper;getTarget()Lnet/minecraft/world/entity/LivingEntity;", shift=At.Shift.BY, by=2)}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD)
    public void canUseInvoke(CallbackInfoReturnable<Boolean> ci, LivingEntity livingentity) {
        Vec3 headPos = this.getHeadPosOfTractorBeamCreeperIsIn(livingentity);
        if (headPos != null && Math.sqrt(this.f_25916_.m_20238_(headPos)) < 12.0 && this.f_25916_.m_217043_().m_188503_(3) == 0) {
            ci.setReturnValue((Object)true);
        }
    }

    @Inject(method={"tick"}, at={@At(value="HEAD")}, cancellable=true)
    public void tickInvoke(CallbackInfo ci) {
        Vec3 headPos = this.getHeadPosOfTractorBeamCreeperIsIn(this.f_25917_);
        if (headPos != null) {
            if (this.f_25916_.m_146892_().m_82554_(headPos) > 16.0) {
                this.f_25916_.m_32283_(-1);
            } else {
                this.f_25916_.m_32283_(1);
            }
            ci.cancel();
        }
    }

    @Unique
    @Nullable
    private Vec3 getHeadPosOfTractorBeamCreeperIsIn(@Nullable LivingEntity target) {
        WitherStormEntity storm;
        Pair<Boolean, Integer> pair;
        if (target instanceof WitherStormEntity && ((Boolean)(pair = TractorBeamHelper.isInsideTractorBeam((Entity)this.f_25916_, storm = (WitherStormEntity)target, 4.0)).getFirst()).booleanValue()) {
            return storm.getHeadPos((Integer)pair.getSecond());
        }
        return null;
    }
}

