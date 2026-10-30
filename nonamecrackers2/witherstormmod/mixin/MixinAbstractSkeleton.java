/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.monster.AbstractSkeleton
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package nonamecrackers2.witherstormmod.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={AbstractSkeleton.class})
public abstract class MixinAbstractSkeleton
extends Monster {
    private MixinAbstractSkeleton(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Inject(method={"performRangedAttack"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;getX()D", ordinal=0)}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD)
    public void performRangedAttackInvoke(LivingEntity entity, float f, CallbackInfo ci, ItemStack stack, AbstractArrow arrow) {
        WitherStormEntity storm;
        Pair<Boolean, Integer> pair;
        if (entity instanceof WitherStormEntity && ((Boolean)(pair = TractorBeamHelper.isInsideTractorBeam((Entity)this, storm = (WitherStormEntity)entity, 4.0)).getFirst()).booleanValue()) {
            Vec3 pos = storm.getHeadPos((Integer)pair.getSecond());
            double x = pos.m_7096_() - this.m_20185_();
            double y = pos.m_7098_() - arrow.m_20186_();
            double z = pos.m_7094_() - this.m_20189_();
            double dist = Math.sqrt(x * x + z * z);
            arrow.m_6686_(x, y + dist * (double)0.2f, z, 1.6f, (float)(14 - this.m_9236_().m_46791_().m_19028_() * 4));
            this.m_5496_(SoundEvents.f_12382_, 1.0f, 1.0f / (this.m_217043_().m_188501_() * 0.4f + 0.8f));
            this.m_9236_().m_7967_((Entity)arrow);
            ci.cancel();
        }
    }
}

