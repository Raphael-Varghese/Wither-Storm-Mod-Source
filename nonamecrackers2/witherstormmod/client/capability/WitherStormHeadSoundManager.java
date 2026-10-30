/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 */
package nonamecrackers2.witherstormmod.client.capability;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import nonamecrackers2.witherstormmod.client.audio.EntitySoundManager;
import nonamecrackers2.witherstormmod.client.audio.TractorBeamLoop;
import nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;

public class WitherStormHeadSoundManager
extends EntitySoundManager<WitherStormHeadEntity, TractorBeamLoop<WitherStormHeadEntity>> {
    public WitherStormHeadSoundManager(Minecraft minecraft) {
        super(minecraft, WitherStormHeadEntity.class);
    }

    @Override
    protected boolean canPlay(WitherStormHeadEntity entity) {
        double distance = Math.sqrt(this.minecraft.f_91074_.m_20238_(TractorBeamHelper.calculateClosestPoint((Entity)this.minecraft.f_91074_, (LivingEntity)entity)));
        return super.canPlay(entity) && !entity.isDeadOrPlayingDead() && distance <= TractorBeamLoop.DISTANCE_REQUIRED && entity.tractorBeamActive(0);
    }

    @Override
    protected boolean alreadyHasLoop(WitherStormHeadEntity entity) {
        for (TractorBeamLoop loop : this.loops) {
            if (loop.getEntity() != entity) continue;
            return true;
        }
        return false;
    }

    @Override
    protected TractorBeamLoop<WitherStormHeadEntity> create(WitherStormHeadEntity entity) {
        return new TractorBeamLoop<WitherStormHeadEntity>(entity, loopEntity -> !loopEntity.tractorBeamActive(0));
    }

    @Override
    protected TractorBeamLoop<WitherStormHeadEntity> copyFrom(TractorBeamLoop<WitherStormHeadEntity> loop) {
        return new TractorBeamLoop<WitherStormHeadEntity>(loop.getEntity(), loopEntity -> !loopEntity.tractorBeamActive(0));
    }
}

