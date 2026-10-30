/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.util;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;

public class TentacleOffsets {
    private double x;
    private double y;
    private double z;
    private float xCurl;
    private float yCurl;
    private float baseXRot;
    private float baseYRot;

    public TentacleOffsets(double x, double y, double z, float xCurl, float yCurl, float baseXRot, float baseYRot) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.xCurl = xCurl;
        this.yCurl = yCurl;
        this.baseXRot = baseXRot;
        this.baseYRot = baseYRot;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public float getXCurl() {
        return this.xCurl;
    }

    public float getYCurl() {
        return this.yCurl;
    }

    public float getBaseXRot() {
        return this.baseXRot;
    }

    public float getBaseYRot() {
        return this.baseYRot;
    }

    public void apply(LivingEntity living, TentacleEntity entity) {
        this.setPos(living, entity);
        entity.setSavedXCurl(this.getXCurl());
        entity.setSavedYCurl(this.getYCurl());
        entity.setSavedXOffset(this.getBaseXRot());
        entity.setSavedYOffset(this.getBaseYRot() - living.f_20883_ - 90.0f);
    }

    public void setPos(LivingEntity living, TentacleEntity entity) {
        float angle = (float)Mth.m_14136_((double)this.getX(), (double)this.getZ());
        double p = Math.sqrt(this.getX() * this.getX() + this.getZ() * this.getZ());
        float rot = -living.f_20883_ * ((float)Math.PI / 180);
        double newXOffset = (double)Mth.m_14089_((float)(rot + angle)) * p;
        double newZOffset = (double)Mth.m_14031_((float)(rot + angle)) * p;
        Vec3 pos = new Vec3(living.m_20185_() + newXOffset, living.m_20186_() + this.getY(), living.m_20189_() + newZOffset);
        entity.m_6034_(pos.f_82479_, pos.f_82480_, pos.f_82481_);
    }
}

