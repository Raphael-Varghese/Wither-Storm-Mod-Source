/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity.section;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class Section {
    protected AABB boundingBox;
    protected EntityDimensions size;
    protected Vec3 position;
    protected Vec3 offset;
    protected final WitherStormEntity owner;
    protected Predicate<WitherStormEntity> isActive;
    protected final float[] color = new float[]{0.0f, 1.0f, 0.0f};

    public Section(WitherStormEntity entity, float height, float width, double x, double y, double z, Predicate<WitherStormEntity> isActive) {
        this.owner = entity;
        this.size = EntityDimensions.m_20395_((float)width, (float)height);
        this.position = entity.m_20182_();
        this.offset = new Vec3(x, y, z);
        this.boundingBox = this.size.m_20393_(entity.m_20182_());
        this.isActive = isActive;
    }

    public Section(WitherStormEntity entity, float height, float width, double x, double y, double z, int phase) {
        this(entity, height, width, x, y, z, storm -> storm.getPhase() > phase);
    }

    public void setSize(float height, float width) {
        this.size = EntityDimensions.m_20395_((float)width, (float)height);
    }

    public void setOffset(double x, double y, double z) {
        this.offset = new Vec3(x, y, z);
    }

    public void tick() {
        float yBodyRot = this.owner.f_20883_ * ((float)Math.PI / 180);
        float yBodyRot90 = (this.owner.f_20883_ + 90.0f) * ((float)Math.PI / 180);
        float xBodyRot = (this.owner.xBodyRot + 90.0f) * ((float)Math.PI / 180);
        double xOffset = (double)Mth.m_14089_((float)yBodyRot) * this.getOffset().m_7096_();
        double zOffset = (double)Mth.m_14031_((float)yBodyRot) * this.getOffset().m_7096_();
        float offset = (float)Math.atan2(this.getOffset().m_7094_(), this.getOffset().m_7098_());
        double rawX = Mth.m_14089_((float)(xBodyRot + offset)) * Mth.m_14089_((float)yBodyRot90);
        double rawY = Mth.m_14031_((float)(xBodyRot + offset));
        double rawZ = Mth.m_14089_((float)(xBodyRot + offset)) * Mth.m_14031_((float)yBodyRot90);
        double x = xOffset + this.owner.m_20185_() + rawX * Math.sqrt(this.getOffset().m_7094_() * this.getOffset().m_7094_() + this.getOffset().m_7098_() * this.getOffset().m_7098_());
        double y = this.owner.m_20186_() + rawY * Math.sqrt(this.getOffset().m_7094_() * this.getOffset().m_7094_() + this.getOffset().m_7098_() * this.getOffset().m_7098_());
        double z = zOffset + this.owner.m_20189_() + rawZ * Math.sqrt(this.getOffset().m_7094_() * this.getOffset().m_7094_() + this.getOffset().m_7098_() * this.getOffset().m_7098_());
        this.position = new Vec3(x, y, z);
        this.boundingBox = this.size.m_20393_(this.position);
        this.collideWithNearbyEntities();
    }

    protected void collideWithNearbyEntities() {
        List list = this.owner.m_9236_().m_6249_((Entity)this.owner, this.getBoundingBox(), EntitySelector.m_20421_((Entity)this.owner));
        if (!list.isEmpty()) {
            for (Entity entity : list) {
                this.applyEntityCollision(entity);
            }
        }
    }

    public void applyEntityCollision(Entity entity) {
        double d1;
        double d0;
        double d2;
        if (!this.owner.m_20365_(entity) && !entity.f_19794_ && !this.owner.f_19794_ && (d2 = Mth.m_14005_((double)(d0 = entity.m_20185_() - this.position.f_82479_), (double)(d1 = entity.m_20189_() - this.position.f_82481_))) >= (double)0.01f) {
            d2 = Math.sqrt(d2);
            d0 /= d2;
            d1 /= d2;
            double d3 = 1.0 / d2;
            if (d3 > 1.0) {
                d3 = 1.0;
            }
            d0 *= d3;
            d1 *= d3;
            d0 *= (double)0.05f;
            d1 *= (double)0.05f;
            if (!entity.m_20160_()) {
                entity.m_5997_(d0, 0.0, d1);
            }
        }
    }

    public Vec3 getPosition() {
        return this.position;
    }

    public Vec3 getOffset() {
        return this.offset;
    }

    public void setOffset(Vec3 vector) {
        this.offset = vector;
    }

    public AABB getBoundingBox() {
        return this.boundingBox;
    }

    public Level getLevel() {
        return this.owner.m_9236_();
    }

    public boolean isActive() {
        return this.isActive.test(this.owner);
    }

    public Section setColor(float r, float g, float b) {
        this.color[0] = r;
        this.color[1] = g;
        this.color[2] = b;
        return this;
    }

    public float[] getColor() {
        return this.color;
    }
}

