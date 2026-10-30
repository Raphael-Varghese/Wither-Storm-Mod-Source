/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.RecipeType
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package nonamecrackers2.witherstormmod.common.init;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import nonamecrackers2.witherstormmod.common.item.crafting.AnvilRecipe;
import nonamecrackers2.witherstormmod.common.item.crafting.ItemCraftSuperBeaconRecipe;
import nonamecrackers2.witherstormmod.common.item.crafting.ResummonSuperBeaconRecipe;

public class WitherStormModRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create((IForgeRegistry)ForgeRegistries.RECIPE_TYPES, (String)"witherstormmod");
    public static final RegistryObject<RecipeType<ResummonSuperBeaconRecipe>> SUPER_BEACON_RESUMMON = WitherStormModRecipeTypes.register("super_beacon_resummon");
    public static final RegistryObject<RecipeType<ItemCraftSuperBeaconRecipe>> SUPER_BEACON_ITEM = WitherStormModRecipeTypes.register("super_beacon_item");
    public static final RegistryObject<RecipeType<AnvilRecipe>> ANVIL = WitherStormModRecipeTypes.register("anvil");

    private static <T extends Recipe<?>> RegistryObject<RecipeType<T>> register(final String id) {
        return RECIPE_TYPES.register(id, () -> new RecipeType<T>(){

            public String toString() {
                return id;
            }
        });
    }
}

