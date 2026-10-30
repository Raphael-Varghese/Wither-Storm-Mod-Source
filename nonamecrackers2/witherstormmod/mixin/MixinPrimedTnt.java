/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.item.PrimedTnt
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 */
package nonamecrackers2.witherstormmod.mixin;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value={PrimedTnt.class})
public abstract class MixinPrimedTnt
extends Entity {
    private MixinPrimedTnt(EntityType<?> type, Level level) {
        super(type, level);
    }

    @ModifyVariable(method={"tick"}, at=@At(value="STORE"), ordinal=0)
    public int tickModifyFuse(int i) {
        Pair<Boolean, Integer> pair;
        List storms;
        WitherStormEntity storm;
        if (!((PrimedTnt)this instanceof FormidibombEntity) && (storm = WorldUtil.getNearest(storms = this.m_9236_().m_45976_(WitherStormEntity.class, this.m_20191_().m_82377_(100.0, 200.0, 100.0)), this.m_20182_(), Entity::m_20182_)) != null && ((Boolean)(pair = TractorBeamHelper.isInsideTractorBeam(this, storm, 4.0)).getFirst()).booleanValue()) {
            Vec3 pos = storm.getHeadPos((Integer)pair.getSecond());
            if (pos.m_82554_(this.m_20182_()) > 12.0) {
                if (i == 20) {
                    return 80;
                }
            } else {
                return 0;
            }
        }
        return i;
    }

    @Shadow
    public abstract void m_32085_(int var1);
}

