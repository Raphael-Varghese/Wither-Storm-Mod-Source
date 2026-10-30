/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mezz.jei.api.gui.builder.IRecipeLayoutBuilder
 *  mezz.jei.api.gui.drawable.IDrawable
 *  mezz.jei.api.gui.ingredient.IRecipeSlotsView
 *  mezz.jei.api.helpers.IGuiHelper
 *  mezz.jei.api.recipe.IFocusGroup
 *  mezz.jei.api.recipe.RecipeIngredientRole
 *  mezz.jei.api.recipe.RecipeType
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.screens.inventory.InventoryScreen
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.SpawnEggItem
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.common.ForgeSpawnEggItem
 */
package nonamecrackers2.witherstormmod.client.jei.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import nonamecrackers2.witherstormmod.client.instancing.RenderBufferer;
import nonamecrackers2.witherstormmod.client.jei.WitherStormModJEICompat;
import nonamecrackers2.witherstormmod.client.jei.category.SuperBeaconCategory;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.AdditionalHead;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.HeadManager;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
import nonamecrackers2.witherstormmod.common.item.crafting.ResummonSuperBeaconRecipe;
import nonamecrackers2.witherstormmod.common.item.crafting.SuperBeaconRecipe;

public class SuperBeaconSummoning
extends SuperBeaconCategory<ResummonSuperBeaconRecipe> {
    private static final ResourceLocation ICON_TEXTURE = new ResourceLocation("witherstormmod", "textures/gui/jei/summoning_icon.png");
    private final IDrawable icon;

    public SuperBeaconSummoning(IGuiHelper helper) {
        super(helper);
        this.icon = new SuperBeaconCategory.Icon(helper.createDrawableItemStack(new ItemStack((ItemLike)WitherStormModBlocks.SUPER_BEACON.get())), ICON_TEXTURE);
    }

    public RecipeType<ResummonSuperBeaconRecipe> getRecipeType() {
        return WitherStormModJEICompat.SUPER_BEACON_SUMMONING;
    }

    public Component getTitle() {
        return Component.m_237115_((String)"witherstormmod.jei.resummoning_super_beacon.title");
    }

    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    protected void addResult(IRecipeLayoutBuilder builder, ResummonSuperBeaconRecipe recipe, IFocusGroup focuses, int x, int y, RegistryAccess access) {
        EntityType<?> type = recipe.getResummonEntity();
        if (type != null) {
            SpawnEggItem spawnEgg = ForgeSpawnEggItem.fromEntityType(type);
            if (spawnEgg != null) {
                builder.addInvisibleIngredients(RecipeIngredientRole.OUTPUT).addItemStack(new ItemStack((ItemLike)spawnEgg));
            } else {
                builder.addInvisibleIngredients(RecipeIngredientRole.OUTPUT).addItemStack(ItemStack.f_41583_);
            }
            Minecraft mc = Minecraft.m_91087_();
            Entity entity = type.m_20615_((Level)mc.f_91073_);
            if (entity instanceof LivingEntity) {
                LivingEntity living;
                recipe.toRender = living = (LivingEntity)entity;
                if (living instanceof WitherStormEntity) {
                    WitherStormEntity storm = (WitherStormEntity)living;
                    storm.setPhase(4);
                }
            }
        } else {
            builder.addInvisibleIngredients(RecipeIngredientRole.OUTPUT).addItemStack(ItemStack.f_41583_);
        }
    }

    @Override
    public void draw(ResummonSuperBeaconRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics stack, double mouseX, double mouseY) {
        if (recipe.toRender != null) {
            int x = this.getWidth() / 2;
            int y = this.getHeight() / 2;
            if (recipe.getCondition() != SuperBeaconRecipe.Condition.NONE) {
                y -= 5;
            }
            y = (int)((float)y + recipe.toRender.m_20206_() / 2.0f * 20.0f);
            float angleX = (float)(-mouseX) + (float)x;
            float angleY = (float)(-mouseY) + (float)y - recipe.toRender.m_20192_() * 20.0f;
            stack.m_280168_().m_85836_();
            stack.m_280168_().m_85837_(0.0, 0.0, 50.0);
            LivingEntity livingEntity = recipe.toRender;
            if (livingEntity instanceof WitherStormEntity) {
                WitherStormEntity storm = (WitherStormEntity)livingEntity;
                HeadManager manager = storm.getHeadManager();
                for (WitherStormHead head : manager.getHeads()) {
                    if (!(head instanceof AdditionalHead)) continue;
                    AdditionalHead additionalHead = (AdditionalHead)head;
                    additionalHead.yRot = angleX * 20.0f * ((float)Math.PI / 180) + 180.0f;
                    additionalHead.xRot = -angleY * 20.0f * ((float)Math.PI / 180);
                    additionalHead.yRotO = additionalHead.yRot;
                    additionalHead.xRotO = additionalHead.xRot;
                }
                stack.m_280168_().m_85837_(80.0, 60.0, 0.0);
                stack.m_280168_().m_85841_(0.1f, 0.1f, 0.1f);
            }
            RenderBufferer.pushTempDisabled();
            InventoryScreen.m_274545_((GuiGraphics)stack, (int)x, (int)y, (int)20, (float)angleX, (float)angleY, (LivingEntity)recipe.toRender);
            stack.m_280168_().m_85849_();
        }
        super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);
    }

    public boolean isHandled(ResummonSuperBeaconRecipe recipe) {
        return !recipe.m_6423_().toString().equals("witherstormmod:summon_pig");
    }
}

