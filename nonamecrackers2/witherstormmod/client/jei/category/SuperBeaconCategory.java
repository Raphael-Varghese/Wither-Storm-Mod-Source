/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mezz.jei.api.gui.builder.IRecipeLayoutBuilder
 *  mezz.jei.api.gui.drawable.IDrawable
 *  mezz.jei.api.gui.ingredient.IRecipeSlotView
 *  mezz.jei.api.gui.ingredient.IRecipeSlotsView
 *  mezz.jei.api.helpers.IGuiHelper
 *  mezz.jei.api.recipe.IFocusGroup
 *  mezz.jei.api.recipe.RecipeIngredientRole
 *  mezz.jei.api.recipe.category.IRecipeCategory
 *  mezz.jei.library.gui.ingredients.RecipeSlot
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Font
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.core.NonNullList
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.item.crafting.Ingredient
 */
package nonamecrackers2.witherstormmod.client.jei.category;

import java.util.Objects;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.library.gui.ingredients.RecipeSlot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.crafting.Ingredient;
import nonamecrackers2.witherstormmod.common.item.crafting.SuperBeaconRecipe;

public abstract class SuperBeaconCategory<T extends SuperBeaconRecipe>
implements IRecipeCategory<T> {
    protected static final ResourceLocation SLOT = new ResourceLocation("witherstormmod", "textures/gui/jei/slot.png");
    private final IDrawable background;

    protected SuperBeaconCategory(IGuiHelper helper) {
        this.background = helper.createBlankDrawable(180, 120);
    }

    public IDrawable getBackground() {
        return this.background;
    }

    public void setRecipe(IRecipeLayoutBuilder builder, T recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> ingredients = ((SuperBeaconRecipe)recipe).m_7527_();
        int totalSize = ingredients.size();
        float angleOffset = 360.0f / (float)totalSize;
        int halfWidth = this.getWidth() / 2;
        int halfHeight = this.getHeight() / 2;
        if (((SuperBeaconRecipe)recipe).getCondition() != SuperBeaconRecipe.Condition.NONE) {
            halfHeight -= 5;
        }
        for (int i = 0; i < totalSize; ++i) {
            float angle = angleOffset * (float)i;
            float x = Mth.m_14089_((float)(angle * ((float)Math.PI / 180))) * 40.0f + (float)halfWidth - 8.0f;
            float y = Mth.m_14031_((float)(angle * ((float)Math.PI / 180))) * 40.0f + (float)halfHeight - 8.0f;
            Ingredient ingredient = (Ingredient)ingredients.get(i);
            builder.addSlot(RecipeIngredientRole.INPUT, (int)x, (int)y).addIngredients(ingredient);
        }
        Minecraft mc = Minecraft.m_91087_();
        this.addResult(builder, recipe, focuses, halfWidth, halfHeight, mc.f_91073_.m_9598_());
    }

    protected abstract void addResult(IRecipeLayoutBuilder var1, T var2, IFocusGroup var3, int var4, int var5, RegistryAccess var6);

    public void draw(T recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics stack, double mouseX, double mouseY) {
        String desc = ((SuperBeaconRecipe)recipe).getCondition().getDescription();
        if (desc != null) {
            Minecraft mc = Minecraft.m_91087_();
            Font font = mc.f_91062_;
            MutableComponent mutableComponent = Component.m_237115_((String)desc);
            int n = this.getWidth() / 2;
            int n2 = this.getHeight();
            Objects.requireNonNull(mc.f_91062_);
            stack.m_280653_(font, (Component)mutableComponent, n, n2 - 9 - 1, -1);
        }
        for (IRecipeSlotView slotView : recipeSlotsView.getSlotViews()) {
            if (!(slotView instanceof RecipeSlot)) continue;
            RecipeSlot slot = (RecipeSlot)slotView;
            int x = slot.getRect().m_110085_();
            int y = slot.getRect().m_110086_();
            stack.m_280398_(SLOT, x - 1, y - 1, 0, 0.0f, 0.0f, 18, 18, 256, 256);
        }
    }

    protected static class Icon
    implements IDrawable {
        private final IDrawable wrapped;
        private final ResourceLocation iconTexture;

        public Icon(IDrawable wrapped, ResourceLocation iconTexture) {
            this.wrapped = wrapped;
            this.iconTexture = iconTexture;
        }

        public int getWidth() {
            return this.wrapped.getWidth();
        }

        public int getHeight() {
            return this.wrapped.getHeight();
        }

        public void draw(GuiGraphics stack, int xOffset, int yOffset) {
            this.wrapped.draw(stack, xOffset, yOffset);
            stack.m_280398_(this.iconTexture, xOffset + 8, yOffset + 8, 0, 0.0f, 0.0f, 8, 8, 8, 8);
        }
    }
}

