/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.RecipeType
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.event.AnvilUpdateEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.common.event;

import java.util.List;
import java.util.Map;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeTypes;
import nonamecrackers2.witherstormmod.common.item.crafting.AnvilRecipe;

public class AnvilRecipeEvents {
    @SubscribeEvent
    public static void doModdedRecipes(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();
        Level level = event.getPlayer().m_9236_();
        AnvilRecipe.AnvilContents contents = new AnvilRecipe.AnvilContents(left, right);
        List recipes = level.m_7465_().m_44056_((RecipeType)WitherStormModRecipeTypes.ANVIL.get(), (Container)contents, level);
        if (!recipes.isEmpty()) {
            AnvilRecipe recipe = (AnvilRecipe)recipes.get(0);
            Map enchantments = EnchantmentHelper.m_44831_((ItemStack)left);
            enchantments.putAll(EnchantmentHelper.m_44831_((ItemStack)right));
            ItemStack output = recipe.assemble(contents, level.m_9598_());
            EnchantmentHelper.m_44865_((Map)enchantments, (ItemStack)output);
            event.setOutput(output);
            event.setCost(recipe.getCost());
        }
    }
}

