/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Supplier
 *  com.google.common.collect.Lists
 *  net.minecraft.Util
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.level.ItemLike
 */
package nonamecrackers2.witherstormmod.common.util;

import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import net.minecraft.Util;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ItemLike;

public class EquipmentHelper {
    private static final List<EquipmentType> HELMETS = (List)Util.m_137469_((Object)Lists.newArrayList(), helmetTypes -> {
        helmetTypes.add(new EquipmentType(Items.f_41852_, 15));
        helmetTypes.add(new EquipmentType(Items.f_42407_, 25));
        helmetTypes.add(new EquipmentType(Items.f_42464_, 20));
        helmetTypes.add(new EquipmentType(Items.f_42476_, 15));
        helmetTypes.add(new EquipmentType(Items.f_42468_, 10));
        helmetTypes.add(new EquipmentType(Items.f_42472_, 5));
        helmetTypes.add(new EquipmentType(Items.f_42354_, 1));
    });
    private static final List<EquipmentType> ADVANCED_HELMETS = (List)Util.m_137469_((Object)Lists.newArrayList(), helmetTypes -> {
        helmetTypes.addAll(HELMETS);
        helmetTypes.add(new EquipmentType(Items.f_42407_, 5));
        helmetTypes.add(new EquipmentType(Items.f_42464_, 10));
        helmetTypes.add(new EquipmentType(Items.f_42476_, 15));
        helmetTypes.add(new EquipmentType(Items.f_42468_, 20));
        helmetTypes.add(new EquipmentType(Items.f_42472_, 10));
    });
    private static final List<EquipmentType> CHESTPLATES = (List)Util.m_137469_((Object)Lists.newArrayList(), chestplateTypes -> {
        chestplateTypes.add(new EquipmentType(Items.f_41852_, 15));
        chestplateTypes.add(new EquipmentType(Items.f_42408_, 25));
        chestplateTypes.add(new EquipmentType(Items.f_42465_, 20));
        chestplateTypes.add(new EquipmentType(Items.f_42477_, 15));
        chestplateTypes.add(new EquipmentType(Items.f_42469_, 10));
        chestplateTypes.add(new EquipmentType(Items.f_42473_, 5));
    });
    private static final List<EquipmentType> ADVANCED_CHESTPLATES = (List)Util.m_137469_((Object)Lists.newArrayList(), chestplateTypes -> {
        chestplateTypes.addAll(CHESTPLATES);
        chestplateTypes.add(new EquipmentType(Items.f_42408_, 5));
        chestplateTypes.add(new EquipmentType(Items.f_42465_, 10));
        chestplateTypes.add(new EquipmentType(Items.f_42477_, 15));
        chestplateTypes.add(new EquipmentType(Items.f_42469_, 20));
        chestplateTypes.add(new EquipmentType(Items.f_42473_, 10));
    });
    private static final List<EquipmentType> LEGGINGS = (List)Util.m_137469_((Object)Lists.newArrayList(), leggingsTypes -> {
        leggingsTypes.add(new EquipmentType(Items.f_41852_, 15));
        leggingsTypes.add(new EquipmentType(Items.f_42462_, 25));
        leggingsTypes.add(new EquipmentType(Items.f_42466_, 20));
        leggingsTypes.add(new EquipmentType(Items.f_42478_, 15));
        leggingsTypes.add(new EquipmentType(Items.f_42470_, 10));
        leggingsTypes.add(new EquipmentType(Items.f_42474_, 5));
    });
    private static final List<EquipmentType> ADVANCED_LEGGINGS = (List)Util.m_137469_((Object)Lists.newArrayList(), leggingsTypes -> {
        leggingsTypes.addAll(LEGGINGS);
        leggingsTypes.add(new EquipmentType(Items.f_42462_, 5));
        leggingsTypes.add(new EquipmentType(Items.f_42466_, 10));
        leggingsTypes.add(new EquipmentType(Items.f_42478_, 15));
        leggingsTypes.add(new EquipmentType(Items.f_42470_, 20));
        leggingsTypes.add(new EquipmentType(Items.f_42474_, 10));
    });
    private static final List<EquipmentType> BOOTS = (List)Util.m_137469_((Object)Lists.newArrayList(), bootsTypes -> {
        bootsTypes.add(new EquipmentType(Items.f_41852_, 15));
        bootsTypes.add(new EquipmentType(Items.f_42463_, 25));
        bootsTypes.add(new EquipmentType(Items.f_42467_, 20));
        bootsTypes.add(new EquipmentType(Items.f_42479_, 15));
        bootsTypes.add(new EquipmentType(Items.f_42471_, 10));
        bootsTypes.add(new EquipmentType(Items.f_42475_, 5));
    });
    private static final List<EquipmentType> ADVANCED_BOOTS = (List)Util.m_137469_((Object)Lists.newArrayList(), bootsTypes -> {
        bootsTypes.addAll(BOOTS);
        bootsTypes.add(new EquipmentType(Items.f_42463_, 5));
        bootsTypes.add(new EquipmentType(Items.f_42467_, 15));
        bootsTypes.add(new EquipmentType(Items.f_42479_, 10));
        bootsTypes.add(new EquipmentType(Items.f_42471_, 20));
        bootsTypes.add(new EquipmentType(Items.f_42475_, 10));
    });

    public static void applyEquipment(Monster monster, DifficultyInstance difficulty, boolean useAdvanced) {
        HashMap<EquipmentSlot, Supplier> equipmentMap = new HashMap<EquipmentSlot, Supplier>();
        equipmentMap.put(EquipmentSlot.HEAD, () -> EquipmentHelper.getRandomItemBasedOnWeight(monster.m_217043_(), useAdvanced ? ADVANCED_HELMETS : HELMETS));
        equipmentMap.put(EquipmentSlot.CHEST, () -> EquipmentHelper.getRandomItemBasedOnWeight(monster.m_217043_(), useAdvanced ? ADVANCED_CHESTPLATES : HELMETS));
        equipmentMap.put(EquipmentSlot.LEGS, () -> EquipmentHelper.getRandomItemBasedOnWeight(monster.m_217043_(), useAdvanced ? ADVANCED_LEGGINGS : HELMETS));
        equipmentMap.put(EquipmentSlot.FEET, () -> EquipmentHelper.getRandomItemBasedOnWeight(monster.m_217043_(), useAdvanced ? ADVANCED_BOOTS : BOOTS));
        equipmentMap.forEach((equipmentSlot, itemSupplier) -> {
            if (!monster.m_21033_(equipmentSlot)) {
                ItemStack equipmentStack = new ItemStack((ItemLike)itemSupplier.get());
                int enchantmentLevel = (int)(5.0f + difficulty.m_19057_() * (float)monster.m_217043_().m_188503_(40));
                EnchantmentHelper.m_220292_((RandomSource)monster.m_217043_(), (ItemStack)equipmentStack, (int)enchantmentLevel, (boolean)false);
                monster.m_8061_(equipmentSlot, equipmentStack);
            }
        });
    }

    private static Item getRandomItemBasedOnWeight(RandomSource random, List<EquipmentType> equipment) {
        int rarityWeight = equipment.stream().mapToInt(EquipmentType::getRarity).sum();
        int randomWeight = random.m_188503_(rarityWeight);
        Item selectedItem = null;
        int cumWeight = 0;
        for (EquipmentType type : equipment) {
            if (randomWeight >= (cumWeight += type.getRarity())) continue;
            selectedItem = type.getItem();
            break;
        }
        return Objects.requireNonNull(selectedItem, "Could not get an item! Is the equipment list empty?");
    }

    private static class EquipmentType {
        private final Item item;
        private final int rarity;

        public EquipmentType(Item item, int rarity) {
            this.item = item;
            this.rarity = rarity;
        }

        public Item getItem() {
            return this.item;
        }

        public int getRarity() {
            return this.rarity;
        }
    }
}

