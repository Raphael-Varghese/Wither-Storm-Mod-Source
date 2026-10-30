/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.monster.CrossbowAttackMob
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.entity.projectile.ThrownEnderpearl
 *  net.minecraft.world.item.CrossbowItem
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.ProjectileWeaponItem
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 *  org.joml.Vector3f
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package nonamecrackers2.witherstormmod.mixin;

import java.util.function.Predicate;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={CrossbowItem.class})
public class MixinCrossbowItem {
    @Unique
    private static final Predicate<ItemStack> HELD = stack -> stack.m_41720_() == Items.f_42584_;

    @Inject(method={"getSupportedHeldProjectiles"}, at={@At(value="HEAD")}, cancellable=true)
    public void getSupportedHeldProjectilesHead(CallbackInfoReturnable<Predicate<ItemStack>> callback) {
        if (((Boolean)WitherStormModConfig.SERVER.crossbowsSupportEnderPearls.get()).booleanValue()) {
            callback.setReturnValue(ProjectileWeaponItem.f_43006_.or(HELD));
        }
    }

    @Inject(method={"shootProjectile"}, at={@At(value="HEAD")}, cancellable=true)
    private static void shootProjectile(Level world, LivingEntity entity, InteractionHand hand, ItemStack stack, ItemStack projectile, float shotPitch, boolean instabuild, float power, float f, float offset, CallbackInfo callback) {
        if (!world.f_46443_ && ((Boolean)WitherStormModConfig.SERVER.crossbowsSupportEnderPearls.get()).booleanValue() && projectile.m_41720_() == Items.f_42584_) {
            ThrownEnderpearl projectileEntity = new ThrownEnderpearl(world, entity);
            if (entity instanceof CrossbowAttackMob) {
                CrossbowAttackMob crossbowUser = (CrossbowAttackMob)entity;
                crossbowUser.m_5811_(crossbowUser.m_5448_(), stack, (Projectile)projectileEntity, offset);
            } else {
                Vec3 upVector = entity.m_20289_(1.0f);
                Quaternionf quaternion = new Quaternionf().setAngleAxis((double)(offset * ((float)Math.PI / 180)), upVector.f_82479_, upVector.f_82480_, upVector.f_82481_);
                Vec3 viewVector = entity.m_20252_(1.0f);
                Vector3f vector3f = viewVector.m_252839_().rotate((Quaternionfc)quaternion);
                projectileEntity.m_6686_((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), power, f);
            }
            stack.m_41622_(3, entity, livingEntity -> livingEntity.m_21190_(hand));
            world.m_7967_((Entity)projectileEntity);
            world.m_6263_(null, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), SoundEvents.f_11847_, SoundSource.PLAYERS, 1.0f, shotPitch);
            callback.cancel();
        }
    }
}

