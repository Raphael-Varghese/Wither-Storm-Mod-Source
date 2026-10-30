/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance
 *  net.minecraft.advancements.critereon.ContextAwarePredicate
 *  net.minecraft.advancements.critereon.DeserializationContext
 *  net.minecraft.advancements.critereon.EntityPredicate
 *  net.minecraft.advancements.critereon.MinMaxBounds$Ints
 *  net.minecraft.advancements.critereon.SerializationContext
 *  net.minecraft.advancements.critereon.SimpleCriterionTrigger
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.storage.loot.LootContext
 */
package nonamecrackers2.witherstormmod.common.advancements.criterion;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;

public class LinkAmuletTrigger
extends SimpleCriterionTrigger<Instance> {
    private static final ResourceLocation ID = new ResourceLocation("witherstormmod", "link_amulet");

    public ResourceLocation m_7295_() {
        return ID;
    }

    protected Instance createInstance(JsonObject object, ContextAwarePredicate player, DeserializationContext context) {
        ContextAwarePredicate linked = EntityPredicate.m_285855_((JsonObject)object, (String)"linked", (DeserializationContext)context);
        MinMaxBounds.Ints totalLinked = MinMaxBounds.Ints.m_55373_((JsonElement)object.get("total_linked"));
        return new Instance(player, linked, totalLinked);
    }

    public void trigger(ServerPlayer player, Entity linked, int totalLinked) {
        LootContext context = EntityPredicate.m_36616_((ServerPlayer)player, (Entity)linked);
        this.m_66234_(player, instance -> instance.matches(context, totalLinked));
    }

    public static class Instance
    extends AbstractCriterionTriggerInstance {
        private final ContextAwarePredicate linked;
        private final MinMaxBounds.Ints totalLinked;

        public Instance(ContextAwarePredicate player, ContextAwarePredicate linked, MinMaxBounds.Ints totalLinked) {
            super(ID, player);
            this.linked = linked;
            this.totalLinked = totalLinked;
        }

        public boolean matches(LootContext context, int totalLinked) {
            return this.linked.m_285831_(context) && this.totalLinked.m_55390_(totalLinked);
        }

        public JsonObject m_7683_(SerializationContext context) {
            JsonObject obj = super.m_7683_(context);
            obj.add("linked", this.linked.m_286026_(context));
            obj.add("total_linked", this.totalLinked.m_55328_());
            return obj;
        }
    }
}

