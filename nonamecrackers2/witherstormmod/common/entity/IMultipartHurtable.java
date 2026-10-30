/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraftforge.entity.PartEntity
 */
package nonamecrackers2.witherstormmod.common.entity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.entity.PartEntity;

public interface IMultipartHurtable<T extends PartEntity<?>> {
    public boolean hurt(T var1, DamageSource var2, float var3);
}

