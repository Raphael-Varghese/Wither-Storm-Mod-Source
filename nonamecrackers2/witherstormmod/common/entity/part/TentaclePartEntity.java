/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.util.Mth
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 */
package nonamecrackers2.witherstormmod.common.entity.part;

import javax.annotation.Nullable;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import nonamecrackers2.witherstormmod.common.entity.IMultipartHurtable;
import nonamecrackers2.witherstormmod.common.entity.part.LinkedPartEntity;

public class TentaclePartEntity<T extends LivingEntity>
extends LinkedPartEntity<T, TentaclePartEntity<T>> {
    public float xCurl;
    public float yCurl;
    public double xOffset;
    public double yOffset;
    public double zOffset;
    public float xRotOffset;
    public float yRotOffset;
    private float newWidth;
    private float newHeight;

    public TentaclePartEntity(T parent, float width, float height, int segment, float xCurl, float yCurl) {
        super(parent, width, height, segment);
        this.xCurl = xCurl;
        this.yCurl = yCurl;
        this.pushEntities = true;
        this.newWidth = width;
        this.newHeight = height;
    }

    public TentaclePartEntity(T parent, @Nullable TentaclePartEntity<T> linkedChild, float width, float height, int segment, float xCurl, float yCurl) {
        super(parent, linkedChild, width, height, segment);
        this.xCurl = xCurl;
        this.yCurl = yCurl;
        this.pushEntities = true;
        this.newWidth = width;
        this.newHeight = height;
    }

    public TentaclePartEntity<T> setOffset(double x, double y, double z, float xRot, float yRot) {
        this.xOffset = x;
        this.yOffset = y;
        this.zOffset = z;
        this.xRotOffset = xRot;
        this.yRotOffset = yRot;
        return this;
    }

    @Override
    public void m_8119_() {
        super.m_8119_();
        if (!this.isBase) {
            float rot = this.m_146909_() * this.xCurl;
            this.newWidth = Math.abs(Mth.m_14031_((float)(rot * ((float)Math.PI / 180)))) * (this.size.f_20377_ - this.size.f_20378_) + this.size.f_20378_;
            this.newHeight = Math.abs(Mth.m_14031_((float)(rot * ((float)Math.PI / 180)))) * (this.size.f_20378_ - this.size.f_20377_) + this.size.f_20377_;
        }
        this.m_6210_();
    }

    @Override
    protected void tickChild() {
        super.tickChild();
        ((TentaclePartEntity)this.linkedChild).m_146926_(this.m_146909_() * this.xCurl);
        ((TentaclePartEntity)this.linkedChild).m_146922_((this.m_146908_() - this.yRotOffset) * this.yCurl + this.yRotOffset);
    }

    @Override
    public EntityDimensions m_6972_(Pose pose) {
        return EntityDimensions.m_20395_((float)this.newWidth, (float)this.newHeight);
    }

    public boolean m_6469_(DamageSource source, float damage) {
        if (!this.m_6673_(source)) {
            return ((IMultipartHurtable)((LivingEntity)this.getParent())).hurt(this, source, damage);
        }
        return false;
    }

    public boolean m_6087_() {
        return true;
    }

    public boolean m_142391_() {
        return false;
    }
}

