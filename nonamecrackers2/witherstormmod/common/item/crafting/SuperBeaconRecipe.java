/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 *  net.minecraft.core.NonNullList
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.GsonHelper
 *  net.minecraft.util.StringRepresentable
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.player.StackedContents
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 */
package nonamecrackers2.witherstormmod.common.item.crafting;

import com.google.gson.JsonObject;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import nonamecrackers2.witherstormmod.common.blockentity.SuperBeaconBlockEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;

public abstract class SuperBeaconRecipe
implements Recipe<SuperBeaconBlockEntity> {
    protected final ResourceLocation id;
    protected final NonNullList<Ingredient> ingredients;
    protected final Condition condition;

    public SuperBeaconRecipe(ResourceLocation id, NonNullList<Ingredient> ingredients, Condition condition) {
        this.id = id;
        this.ingredients = ingredients;
        this.condition = condition;
    }

    public boolean matches(SuperBeaconBlockEntity block, Level level) {
        StackedContents contents = new StackedContents();
        int j = 0;
        for (int i = 0; i < block.m_6643_(); ++i) {
            ItemStack item = block.m_8020_(i);
            if (item.m_41619_()) continue;
            ++j;
            contents.m_36468_(item, 1);
        }
        return j == this.ingredients.size() && contents.m_36475_((Recipe)this, null);
    }

    public ItemStack assemble(SuperBeaconBlockEntity entity, RegistryAccess access) {
        if (!this.isResummonEntity()) {
            return this.m_8043_(access).m_41777_();
        }
        return ItemStack.f_41583_;
    }

    public boolean m_8004_(int width, int height) {
        return true;
    }

    public ItemStack m_8042_() {
        return new ItemStack((ItemLike)WitherStormModBlocks.SUPER_BEACON.get());
    }

    public abstract boolean isResummonEntity();

    @Nullable
    public EntityType<?> getResummonEntity() {
        return null;
    }

    @Nullable
    public CompoundTag getResummonEntityNBT() {
        return null;
    }

    public NonNullList<Ingredient> m_7527_() {
        return this.ingredients;
    }

    public ResourceLocation m_6423_() {
        return this.id;
    }

    public Condition getCondition() {
        return this.condition;
    }

    public static enum Condition implements StringRepresentable
    {
        NONE("none", null, e -> true),
        MAIN_ACTIVATED("main_activated", "witherstormmod.jei.super_beacon.requiresMainActivated", e -> e.isActive()),
        FULL_SUPPORTS("all_supports", "witherstormmod.jei.super_beacon.requiresAllSupports", e -> e.isActive() && e.getConnected().size() == 4),
        FULLY_COMLETED("fully_completed", "witherstormmod.jei.super_beacon.requiresFullBeacon", e -> e.isActive() && e.getConnected().size() == 4 && e.beaconLevel == 4);

        private final String name;
        @Nullable
        private final String description;
        private final Predicate<SuperBeaconBlockEntity> predicate;

        private Condition(String name, String description, Predicate<SuperBeaconBlockEntity> predicate) {
            this.name = name;
            this.description = description;
            this.predicate = predicate;
        }

        public String m_7912_() {
            return this.name;
        }

        public boolean canCraft(SuperBeaconBlockEntity entity) {
            return this.predicate.test(entity);
        }

        public String getDescription() {
            return this.description;
        }

        public static Condition fromJson(JsonObject object, String name) {
            if (object.has(name)) {
                String id = GsonHelper.m_13906_((JsonObject)object, (String)name);
                for (Condition value : Condition.values()) {
                    if (!value.name.equals(id)) continue;
                    return value;
                }
            }
            return NONE;
        }
    }
}

