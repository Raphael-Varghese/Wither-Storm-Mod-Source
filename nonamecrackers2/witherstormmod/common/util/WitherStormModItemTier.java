/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tags.ItemTags
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.Tier
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 */
package nonamecrackers2.witherstormmod.common.util;

import java.util.function.Supplier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;

public enum WitherStormModItemTier implements Tier
{
    WOOD_CMD(5, 0, 3.5f, 3.75f, 32, () -> Ingredient.m_204132_((TagKey)ItemTags.f_13168_)),
    STONE_CMD(3, 0, 16.0f, 5.25f, 5, () -> Ingredient.m_204132_((TagKey)ItemTags.f_13165_)),
    IRON_CMD(5, 0, 8.0f, 6.5f, 10, () -> Ingredient.m_43929_((ItemLike[])new ItemLike[]{Items.f_42416_})),
    GOLD_CMD(1, 0, 32.0f, 2.5f, 64, () -> Ingredient.m_43929_((ItemLike[])new ItemLike[]{Items.f_42417_})),
    COMMAND_BLOCK(5, 0, 14.0f, 6.0f, 15, () -> Ingredient.m_43929_((ItemLike[])new ItemLike[]{Items.f_42415_})),
    EYE_OF_THE_STORM(5, 0, 12.0f, 7.5f, 25, () -> Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)WitherStormModItems.TAINTED_DUST.get()})),
    FORMIDI_BLADE(5, 0, 12.0f, 6.0f, 15, () -> Ingredient.m_43929_((ItemLike[])new ItemLike[]{(ItemLike)WitherStormModItems.FORMIDIBOMB.get()}));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    private WitherStormModItemTier(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> ingredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = ingredient;
    }

    public int m_6609_() {
        return this.uses;
    }

    public float m_6624_() {
        return this.speed;
    }

    public float m_6631_() {
        return this.damage;
    }

    public int m_6604_() {
        return this.level;
    }

    public int m_6601_() {
        return this.enchantmentValue;
    }

    public Ingredient m_6282_() {
        return this.repairIngredient.get();
    }
}

