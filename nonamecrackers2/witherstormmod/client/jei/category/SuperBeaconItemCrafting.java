/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mezz.jei.api.gui.builder.IRecipeLayoutBuilder
 *  mezz.jei.api.gui.drawable.IDrawable
 *  mezz.jei.api.helpers.IGuiHelper
 *  mezz.jei.api.recipe.IFocusGroup
 *  mezz.jei.api.recipe.RecipeIngredientRole
 *  mezz.jei.api.recipe.RecipeType
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 */
package nonamecrackers2.witherstormmod.client.jei.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import nonamecrackers2.witherstormmod.client.jei.WitherStormModJEICompat;
import nonamecrackers2.witherstormmod.client.jei.category.SuperBeaconCategory;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
import nonamecrackers2.witherstormmod.common.item.crafting.ItemCraftSuperBeaconRecipe;

public class SuperBeaconItemCrafting
extends SuperBeaconCategory<ItemCraftSuperBeaconRecipe> {
    private static final ResourceLocation ICON_TEXTURE = new ResourceLocation("witherstormmod", "textures/gui/jei/crafting_icon.png");
    private final IDrawable icon;

    public SuperBeaconItemCrafting(IGuiHelper helper) {
        super(helper);
        this.icon = new SuperBeaconCategory.Icon(helper.createDrawableItemStack(new ItemStack((ItemLike)WitherStormModBlocks.SUPER_BEACON.get())), ICON_TEXTURE);
    }

    public RecipeType<ItemCraftSuperBeaconRecipe> getRecipeType() {
        return WitherStormModJEICompat.SUPER_BEACON_ITEM_CRAFTING;
    }

    public Component getTitle() {
        return Component.m_237115_((String)"witherstormmod.jei.item_craft_super_beacon.title");
    }

    @Override
    protected void addResult(IRecipeLayoutBuilder builder, ItemCraftSuperBeaconRecipe recipe, IFocusGroup focuses, int x, int y, RegistryAccess access) {
        builder.addSlot(RecipeIngredientRole.OUTPUT, x - 8, y - 8).addItemStack(recipe.m_8043_(access));
    }

    public IDrawable getIcon() {
        return this.icon;
    }
}

