/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.entity.PartEntity
 */
package nonamecrackers2.witherstormmod.common.entity.part;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;

public class LinkedPartEntity<T extends Entity, P extends LinkedPartEntity<T, P>>
extends PartEntity<T> {
    @Nullable
    protected final P linkedChild;
    public final int segment;
    protected boolean isBase = true;
    protected final EntityDimensions size;
    protected boolean pushEntities;

    public LinkedPartEntity(T parent, @Nullable P linkedChild, float width, float height, int segment) {
        super(parent);
        this.linkedChild = linkedChild;
        if (this.linkedChild != null) {
            ((LinkedPartEntity)((Object)this.linkedChild)).isBase = false;
        }
        this.size = EntityDimensions.m_20395_((float)width, (float)height);
        this.m_6210_();
        this.segment = segment;
    }

    public LinkedPartEntity(T parent, float width, float height, int segment) {
        this(parent, null, width, height, segment);
    }

    public void m_8119_() {
        List list;
        super.m_8119_();
        if (this.pushEntities && !(list = this.m_9236_().m_6249_(this.getParent(), this.m_20191_(), EntitySelector.m_20421_((Entity)this.getParent()))).isEmpty()) {
            for (Entity entity : list) {
                if (entity instanceof PartEntity) continue;
                entity.m_7334_((Entity)this);
            }
        }
        if (this.linkedChild != null) {
            this.tickChild();
        }
    }

    protected void tickChild() {
        Vec3 look = this.getDeltaLinkPos();
        ((LinkedPartEntity)((Object)this.linkedChild)).tickAndO();
        this.linkedChild.m_6034_(look.f_82479_ + this.m_20185_(), look.f_82480_ + this.m_20186_(), look.f_82481_ + this.m_20189_());
    }

    protected Vec3 getDeltaLinkPos() {
        return this.m_20154_().m_82542_((double)this.m_20205_(), (double)this.m_20206_(), (double)this.m_20205_());
    }

    public void tickAndO() {
        this.m_146867_();
        ++this.f_19797_;
        this.m_8119_();
    }

    protected void m_8097_() {
    }

    protected void m_7378_(CompoundTag compound) {
    }

    protected void m_7380_(CompoundTag compound) {
    }

    @Nullable
    public P getLinkedChild() {
        return this.linkedChild;
    }

    public List<P> getChained() {
        ArrayList chained = Lists.newArrayList();
        for (P current = this.linkedChild; current != null; current = ((LinkedPartEntity)((Object)current)).getLinkedChild()) {
            chained.add(current);
        }
        return chained;
    }

    @Nullable
    public P getSegment(int segment) {
        for (LinkedPartEntity p : this.getChained()) {
            if (p.segment != segment) continue;
            return (P)((Object)p);
        }
        return null;
    }

    public P getLast() {
        List<P> chained = this.getChained();
        return (P)((Object)((LinkedPartEntity)((Object)chained.get(chained.size() - 1))));
    }

    public boolean m_7306_(Entity entity) {
        return this == entity || this.getParent() == entity;
    }

    public EntityDimensions m_6972_(Pose pose) {
        return this.size;
    }
}

