/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.EntityType
 */
package nonamecrackers2.witherstormmod.common.resources.taint;

import net.minecraft.world.entity.EntityType;

public record MobConversion(EntityType<?> from, EntityType<?> to, boolean canBeConvertedFromWitherSickness) {
}

