/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.player.AbstractClientPlayer
 *  net.minecraft.sounds.SoundEvent
 */
package nonamecrackers2.witherstormmod.client.capability;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.sounds.SoundEvent;
import nonamecrackers2.witherstormmod.client.audio.EntitySoundManager;
import nonamecrackers2.witherstormmod.client.audio.FormidiBladeLoop;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;

public class FormidiBladeLoopManager
extends EntitySoundManager<AbstractClientPlayer, FormidiBladeLoop> {
    public FormidiBladeLoopManager(Minecraft minecraft) {
        super(minecraft, AbstractClientPlayer.class);
    }

    @Override
    protected boolean canPlay(AbstractClientPlayer entity) {
        return super.canPlay(entity) && FormidiBladeLoop.canPlay(entity);
    }

    @Override
    protected boolean alreadyHasLoop(AbstractClientPlayer entity) {
        return this.loops.stream().anyMatch(l -> l.player == entity);
    }

    @Override
    protected FormidiBladeLoop create(AbstractClientPlayer entity) {
        return new FormidiBladeLoop(entity, (SoundEvent)WitherStormModSoundEvents.FORMIDIBOMB_PULSE_LOOP.get());
    }

    @Override
    protected FormidiBladeLoop copyFrom(FormidiBladeLoop loop) {
        return this.create(loop.player);
    }
}

