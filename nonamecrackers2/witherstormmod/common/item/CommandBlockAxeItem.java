/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.item.AxeItem
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Tier
 */
package nonamecrackers2.witherstormmod.common.item;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class CommandBlockAxeItem
extends AxeItem {
    public CommandBlockAxeItem(Tier tier, int damage, float attackSpeed, Item.Properties properties) {
        super(tier, (float)damage, attackSpeed, properties);
    }

    public boolean m_8120_(ItemStack stack) {
        return this.getMaxStackSize(stack) == 1;
    }

    public boolean m_41386_(DamageSource source) {
        return source.m_269533_(DamageTypeTags.f_268738_);
    }
}

