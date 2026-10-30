/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.SimpleFoiledItem
 */
package nonamecrackers2.witherstormmod.common.item;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SimpleFoiledItem;

public class WitheredNetherStarItem
extends SimpleFoiledItem {
    public WitheredNetherStarItem(Item.Properties properties) {
        super(properties);
    }

    public boolean m_41386_(DamageSource source) {
        return source.m_269533_(DamageTypeTags.f_268738_);
    }
}

