/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.advancements.critereon.EntityFlagsPredicate$Builder
 *  net.minecraft.advancements.critereon.EntityPredicate
 *  net.minecraft.advancements.critereon.EntityPredicate$Builder
 *  net.minecraft.data.loot.EntityLootSubProvider
 *  net.minecraft.tags.EntityTypeTags
 *  net.minecraft.tags.ItemTags
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.flag.FeatureFlags
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.storage.loot.LootContext$EntityTarget
 *  net.minecraft.world.level.storage.loot.LootPool
 *  net.minecraft.world.level.storage.loot.LootTable
 *  net.minecraft.world.level.storage.loot.entries.LootItem
 *  net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer$Builder
 *  net.minecraft.world.level.storage.loot.entries.TagEntry
 *  net.minecraft.world.level.storage.loot.functions.LootItemFunction$Builder
 *  net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction
 *  net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
 *  net.minecraft.world.level.storage.loot.functions.SmeltItemFunction
 *  net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition
 *  net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition
 *  net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition
 *  net.minecraft.world.level.storage.loot.providers.number.NumberProvider
 *  net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
 */
package nonamecrackers2.witherstormmod.common.data.loot;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.stream.Stream;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;

public class WitherStormModEntityLootProvider
extends EntityLootSubProvider {
    public WitherStormModEntityLootProvider() {
        super(FeatureFlags.f_244280_.m_247355_());
    }

    public void m_246942_() {
        this.m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42402_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)2.0f))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)((ItemLike)WitherStormModItems.WITHERED_FLESH.get())).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))))));
        this.m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_COW.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42454_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)2.0f))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)((ItemLike)WitherStormModItems.WITHERED_FLESH.get())).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)1.0f, (float)3.0f))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))))));
        this.m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_PIG.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)((ItemLike)WitherStormModItems.WITHERED_FLESH.get())).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)1.0f, (float)3.0f))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))))));
        this.m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)((ItemLike)WitherStormModItems.WITHERED_FLESH.get())).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42403_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)2.0f))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)TagEntry.m_205095_((TagKey)ItemTags.f_13159_)).m_79080_(LootItemEntityPropertyCondition.m_81864_((LootContext.EntityTarget)LootContext.EntityTarget.KILLER, (EntityPredicate.Builder)EntityPredicate.Builder.m_36633_().m_204077_(EntityTypeTags.f_13120_)))));
        this.m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42454_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)2.0f))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)((ItemLike)WitherStormModItems.WITHERED_FLESH.get())).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)1.0f, (float)3.0f))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))))));
        this.m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_PHANTOM.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42714_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f)))).m_79080_(LootItemKilledByPlayerCondition.m_81901_())));
        this.m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42412_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)2.0f))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)((ItemLike)WitherStormModItems.WITHERED_BONE.get())).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)2.0f))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))))));
        this.m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)((ItemLike)WitherStormModItems.WITHERED_FLESH.get())).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)2.0f))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42401_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)2.0f))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)((ItemLike)WitherStormModItems.WITHERED_SPIDER_EYE.get())).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)-1.0f, (float)1.0f))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f)))).m_79080_(LootItemKilledByPlayerCondition.m_81901_())));
        this.m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)((ItemLike)WitherStormModItems.WITHERED_FLESH.get())).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)2.0f))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42416_)).m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42619_)).m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42620_)).m_79080_(LootItemKilledByPlayerCondition.m_81901_()).m_79080_(LootItemRandomChanceWithLootingCondition.m_81963_((float)0.025f, (float)0.01f))));
        this.m_245309_((EntityType)WitherStormModEntityTypes.WITHERED_SYMBIONT.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)((ItemLike)WitherStormModItems.COMMAND_BLOCK_BOOK.get())))));
        this.m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)((ItemLike)WitherStormModItems.WITHERED_FLESH.get())).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)2.0f))).m_79078_((LootItemFunction.Builder)LootingEnchantFunction.m_165229_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)1.0f))))).m_79161_(LootPool.m_79043_().m_79080_(LootItemKilledByPlayerCondition.m_81901_()).m_79080_(LootItemRandomChanceWithLootingCondition.m_81963_((float)0.025f, (float)0.01f)).m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42416_)).m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42619_)).m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42620_).m_79078_((LootItemFunction.Builder)SmeltItemFunction.m_81271_().m_79080_(LootItemEntityPropertyCondition.m_81867_((LootContext.EntityTarget)LootContext.EntityTarget.THIS, (EntityPredicate)EntityPredicate.Builder.m_36633_().m_36642_(EntityFlagsPredicate.Builder.m_33713_().m_33714_(Boolean.valueOf(true)).m_33716_()).m_36662_()))))));
        this.m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_IRON_GOLEM.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_41951_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)0.0f, (float)2.0f))))).m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42416_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)3.0f, (float)5.0f))))));
        this.m_245309_((EntityType)WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get(), LootTable.m_79147_().m_79161_(LootPool.m_79043_().m_79076_((LootPoolEntryContainer.Builder)LootItem.m_79579_((ItemLike)Items.f_42452_).m_79078_((LootItemFunction.Builder)SetItemCountFunction.m_165412_((NumberProvider)UniformGenerator.m_165780_((float)3.0f, (float)5.0f))))));
    }

    protected Stream<EntityType<?>> getKnownEntityTypes() {
        ArrayList list = Lists.newArrayList();
        list.add((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get());
        list.add((EntityType)WitherStormModEntityTypes.SICKENED_COW.get());
        list.add((EntityType)WitherStormModEntityTypes.SICKENED_PIG.get());
        list.add((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get());
        list.add((EntityType)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get());
        list.add((EntityType)WitherStormModEntityTypes.SICKENED_PHANTOM.get());
        list.add((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get());
        list.add((EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get());
        list.add((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get());
        list.add((EntityType)WitherStormModEntityTypes.WITHERED_SYMBIONT.get());
        list.add((EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get());
        list.add((EntityType)WitherStormModEntityTypes.SICKENED_IRON_GOLEM.get());
        list.add((EntityType)WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get());
        return list.stream();
    }
}

