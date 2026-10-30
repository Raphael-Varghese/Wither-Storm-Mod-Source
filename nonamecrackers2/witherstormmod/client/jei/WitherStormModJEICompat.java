/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  mezz.jei.api.IModPlugin
 *  mezz.jei.api.JeiPlugin
 *  mezz.jei.api.constants.RecipeTypes
 *  mezz.jei.api.helpers.IJeiHelpers
 *  mezz.jei.api.recipe.RecipeType
 *  mezz.jei.api.recipe.category.IRecipeCategory
 *  mezz.jei.api.registration.IRecipeCatalystRegistration
 *  mezz.jei.api.registration.IRecipeCategoryRegistration
 *  mezz.jei.api.registration.IRecipeRegistration
 *  net.minecraft.client.Minecraft
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.RecipeManager
 *  net.minecraft.world.item.crafting.RecipeType
 *  net.minecraft.world.level.ItemLike
 */
package nonamecrackers2.witherstormmod.client.jei;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.ItemLike;
import nonamecrackers2.witherstormmod.client.jei.category.SuperBeaconItemCrafting;
import nonamecrackers2.witherstormmod.client.jei.category.SuperBeaconSummoning;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.common.init.WitherStormModRecipeTypes;
import nonamecrackers2.witherstormmod.common.item.crafting.AnvilRecipe;
import nonamecrackers2.witherstormmod.common.item.crafting.ItemCraftSuperBeaconRecipe;
import nonamecrackers2.witherstormmod.common.item.crafting.ResummonSuperBeaconRecipe;

@JeiPlugin
public class WitherStormModJEICompat
implements IModPlugin {
    private static final ResourceLocation ID = new ResourceLocation("witherstormmod", "jei_compat");
    public static final RecipeType<ItemCraftSuperBeaconRecipe> SUPER_BEACON_ITEM_CRAFTING = RecipeType.create((String)"witherstormmod", (String)"item_craft_super_beacon", ItemCraftSuperBeaconRecipe.class);
    public static final RecipeType<ResummonSuperBeaconRecipe> SUPER_BEACON_SUMMONING = RecipeType.create((String)"witherstormmod", (String)"resummoning_super_beacon", ResummonSuperBeaconRecipe.class);

    public ResourceLocation getPluginUid() {
        return ID;
    }

    public void registerRecipes(IRecipeRegistration registration) {
        Minecraft mc = Minecraft.m_91087_();
        RecipeManager manager = mc.f_91073_.m_7465_();
        registration.addRecipes(SUPER_BEACON_ITEM_CRAFTING, manager.m_44013_((net.minecraft.world.item.crafting.RecipeType)WitherStormModRecipeTypes.SUPER_BEACON_ITEM.get()));
        registration.addRecipes(SUPER_BEACON_SUMMONING, manager.m_44013_((net.minecraft.world.item.crafting.RecipeType)WitherStormModRecipeTypes.SUPER_BEACON_RESUMMON.get()));
        MutableComponent info = Component.m_237115_((String)"withered_beacon.info");
        registration.addItemStackInfo(new ItemStack((ItemLike)WitherStormModBlocks.SUPER_BEACON.get()), new Component[]{info});
        registration.addItemStackInfo(new ItemStack((ItemLike)WitherStormModBlocks.SUPER_SUPPORT_BEACON.get()), new Component[]{info});
        MutableComponent pumpkinInfo = Component.m_237115_((String)"witherstormmod.jei.tainted_pumpkin_info");
        registration.addItemStackInfo(new ItemStack((ItemLike)WitherStormModBlocks.TAINTED_PUMPKIN.get()), new Component[]{pumpkinInfo});
        registration.addItemStackInfo(new ItemStack((ItemLike)WitherStormModBlocks.TAINTED_CARVED_PUMPKIN.get()), new Component[]{pumpkinInfo});
        registration.addItemStackInfo(new ItemStack((ItemLike)WitherStormModBlocks.TAINTED_JACK_O_LANTERN.get()), new Component[]{pumpkinInfo});
        ArrayList recipes = Lists.newArrayList();
        for (AnvilRecipe recipe : manager.m_44013_((net.minecraft.world.item.crafting.RecipeType)WitherStormModRecipeTypes.ANVIL.get())) {
            recipes.add(registration.getVanillaRecipeFactory().createAnvilRecipe(Arrays.asList(recipe.getLeft().m_43908_()), Arrays.asList(recipe.getRight().m_43908_()), (List)Lists.newArrayList((Object[])new ItemStack[]{recipe.getOutputRaw()})));
        }
        registration.addRecipes(RecipeTypes.ANVIL, (List)recipes);
    }

    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers helpers = registration.getJeiHelpers();
        registration.addRecipeCategories(new IRecipeCategory[]{new SuperBeaconItemCrafting(helpers.getGuiHelper())});
        registration.addRecipeCategories(new IRecipeCategory[]{new SuperBeaconSummoning(helpers.getGuiHelper())});
    }

    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack((ItemLike)WitherStormModItems.SUPER_BEACON.get()), new RecipeType[]{SUPER_BEACON_ITEM_CRAFTING});
        registration.addRecipeCatalyst(new ItemStack((ItemLike)WitherStormModItems.SUPER_SUPPORT_BEACON.get()), new RecipeType[]{SUPER_BEACON_ITEM_CRAFTING});
        registration.addRecipeCatalyst(new ItemStack((ItemLike)WitherStormModItems.SUPER_BEACON.get()), new RecipeType[]{SUPER_BEACON_SUMMONING});
        registration.addRecipeCatalyst(new ItemStack((ItemLike)WitherStormModItems.SUPER_SUPPORT_BEACON.get()), new RecipeType[]{SUPER_BEACON_SUMMONING});
    }
}

