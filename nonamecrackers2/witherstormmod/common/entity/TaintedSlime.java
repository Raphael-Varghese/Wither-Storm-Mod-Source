/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.AnimationState
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.Slime
 *  net.minecraft.world.level.Level
 */
package nonamecrackers2.witherstormmod.common.entity;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;

public class TaintedSlime
extends Slime {
    public AnimationState idle = new AnimationState();

    public TaintedSlime(EntityType<? extends TaintedSlime> type, Level level) {
        super(type, level);
        this.idle.m_216977_(this.f_19797_);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.m_33035_();
    }
}

