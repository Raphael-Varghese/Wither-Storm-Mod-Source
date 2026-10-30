/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.util;

import javax.annotation.Nullable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public interface IFormidibomb {
    public int getFuseLife();

    public void setLifeFuse(int var1);

    public int getStartFuse();

    public void setStartFuse(int var1);

    @Nullable
    public LivingEntity getFormidibombOwner();

    public void setFormidibombOwner(@Nullable LivingEntity var1);

    public Vec3 getPosition();

    public boolean isStillAlive();

    default public void copyFrom(IFormidibomb formidibomb) {
        this.setLifeFuse(formidibomb.getFuseLife());
        this.setStartFuse(formidibomb.getStartFuse());
        this.setFormidibombOwner(formidibomb.getFormidibombOwner());
    }
}

