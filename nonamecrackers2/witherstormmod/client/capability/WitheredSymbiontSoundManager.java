/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 */
package nonamecrackers2.witherstormmod.client.capability;

import java.util.function.Predicate;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import nonamecrackers2.witherstormmod.client.audio.EntitySoundLoop;
import nonamecrackers2.witherstormmod.client.audio.EntitySoundManager;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;

public class WitheredSymbiontSoundManager
extends EntitySoundManager<WitheredSymbiontEntity, EntitySoundLoop<WitheredSymbiontEntity>> {
    private static final Predicate<WitheredSymbiontEntity> SHOULD_STOP = entity -> !entity.isVulnerable();

    public WitheredSymbiontSoundManager(Minecraft minecraft) {
        super(minecraft, WitheredSymbiontEntity.class);
    }

    @Override
    protected boolean canPlay(WitheredSymbiontEntity entity) {
        return super.canPlay(entity) && !SHOULD_STOP.test(entity);
    }

    @Override
    protected boolean alreadyHasLoop(WitheredSymbiontEntity entity) {
        boolean flag = false;
        for (EntitySoundLoop loop : this.loops) {
            if (loop.entity != entity) continue;
            flag = true;
            break;
        }
        return flag;
    }

    @Override
    protected EntitySoundLoop<WitheredSymbiontEntity> create(WitheredSymbiontEntity entity) {
        return new EntitySoundLoop<WitheredSymbiontEntity>(entity, (SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_HEART_BEAT.get(), SoundSource.AMBIENT, 20, 7.0f, SHOULD_STOP);
    }

    @Override
    protected EntitySoundLoop<WitheredSymbiontEntity> copyFrom(EntitySoundLoop<WitheredSymbiontEntity> loop) {
        return new EntitySoundLoop<WitheredSymbiontEntity>((WitheredSymbiontEntity)loop.entity, (SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_HEART_BEAT.get(), SoundSource.AMBIENT, 20, 7.0f, SHOULD_STOP);
    }
}

