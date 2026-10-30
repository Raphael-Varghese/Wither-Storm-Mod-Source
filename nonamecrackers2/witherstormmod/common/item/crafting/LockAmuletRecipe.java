/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.inventory.CraftingContainer
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.CraftingBookCategory
 *  net.minecraft.world.item.crafting.CustomRecipe
 *  net.minecraft.world.item.crafting.RecipeSerializer
 *  net.minecraft.world.level.Level
 */
package nonamecrackers2.witherstormmod.common.item.crafting;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeSerializers;
import nonamecrackers2.witherstormmod.common.item.AmuletItem;

public class LockAmuletRecipe
extends CustomRecipe {
    public LockAmuletRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    public boolean matches(CraftingContainer container, Level level) {
        boolean flag = true;
        int totalAmulets = 0;
        for (int i = 0; i < container.m_6643_(); ++i) {
            ItemStack stack = container.m_8020_(i);
            if (stack.m_41720_() instanceof AmuletItem) {
                ++totalAmulets;
            }
            if ((stack.m_41619_() || stack.m_41720_() instanceof AmuletItem) && !stack.m_41784_().m_128471_("Locked") && totalAmulets <= 1) continue;
            flag = false;
        }
        return flag;
    }

    public ItemStack assemble(CraftingContainer container, RegistryAccess access) {
        for (int i = 0; i < container.m_6643_(); ++i) {
            ItemStack slotItem = container.m_8020_(i);
            if (!(slotItem.m_41720_() instanceof AmuletItem)) continue;
            ItemStack stack = slotItem.m_41777_();
            stack.m_41784_().m_128379_("Locked", true);
            return stack;
        }
        return ItemStack.f_41583_;
    }

    public boolean m_8004_(int width, int height) {
        return width * height >= 1;
    }

    public RecipeSerializer<?> m_7707_() {
        return (RecipeSerializer)WitherStormModRecipeSerializers.LOCK_AMULET.get();
    }
}

