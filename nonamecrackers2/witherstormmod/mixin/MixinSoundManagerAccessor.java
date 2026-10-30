/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.sounds.SoundEngine
 *  net.minecraft.client.sounds.SoundManager
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.sounds.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={SoundManager.class})
public interface MixinSoundManagerAccessor {
    @Accessor(value="soundEngine")
    public SoundEngine witherstormmod$getSoundEngine();
}

