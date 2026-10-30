/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity.section;

import java.util.function.Predicate;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.section.Section;

public class FallingSection
extends Section {
    public FallingSection(WitherStormEntity entity, float height, float width, double x, double y, double z, Predicate<WitherStormEntity> isActive) {
        super(entity, height, width, x, y, z, isActive);
    }

    public FallingSection(WitherStormEntity entity, float height, float width, double x, double y, double z, int phase) {
        super(entity, height, width, x, y, z, phase);
    }

    @Override
    public Vec3 getOffset() {
        double sin = Math.sin(Math.toRadians(this.owner.xBodyRot));
        double x = this.offset.f_82479_ * sin;
        double y = this.offset.f_82480_;
        double z = this.offset.f_82481_ * sin;
        return new Vec3(x, y, z);
    }
}

