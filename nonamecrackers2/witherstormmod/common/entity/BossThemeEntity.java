/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.network.chat.Component
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity;

import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;

public interface BossThemeEntity {
    public SoundEvent getBossTheme();

    default public SoundSource getCategory() {
        return SoundSource.MUSIC;
    }

    default public boolean matches(BossThemeEntity entity) {
        return this.getBossTheme() == entity.getBossTheme() && this.getCategory() == entity.getCategory() && this.priority() == entity.priority();
    }

    default public int getFadeTime() {
        return 240;
    }

    public boolean isStillAlive();

    default public boolean shouldPlayBossTheme() {
        return this.isStillAlive() && this.getBossTheme() != null && this.checkConfig();
    }

    default public boolean checkConfig() {
        return true;
    }

    default public double distanceToPlay() {
        return 0.0;
    }

    public int priority();

    default public boolean hasPriority(BossThemeEntity entity) {
        return this.priority() > entity.priority();
    }

    public Vec3 getPosition();

    @Nullable
    default public Component getWatermark() {
        return null;
    }

    default public boolean smartBossMusic() {
        return false;
    }
}

