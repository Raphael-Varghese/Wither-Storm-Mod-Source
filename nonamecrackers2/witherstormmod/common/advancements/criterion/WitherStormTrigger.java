/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance
 *  net.minecraft.advancements.critereon.ContextAwarePredicate
 *  net.minecraft.advancements.critereon.DeserializationContext
 *  net.minecraft.advancements.critereon.EntityPredicate
 *  net.minecraft.advancements.critereon.SerializationContext
 *  net.minecraft.advancements.critereon.SimpleCriterionTrigger
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.storage.loot.LootContext
 */
package nonamecrackers2.witherstormmod.common.advancements.criterion;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class WitherStormTrigger
extends SimpleCriterionTrigger<Instance> {
    private final ResourceLocation id;

    public WitherStormTrigger(ResourceLocation id) {
        this.id = id;
    }

    public ResourceLocation m_7295_() {
        return this.id;
    }

    public void trigger(ServerPlayer player, WitherStormEntity entity) {
        LootContext context = EntityPredicate.m_36616_((ServerPlayer)player, (Entity)entity);
        this.m_66234_(player, instance -> instance.matches(context));
    }

    protected Instance createInstance(JsonObject object, ContextAwarePredicate player, DeserializationContext parser) {
        ContextAwarePredicate entity = EntityPredicate.m_285855_((JsonObject)object, (String)"entity", (DeserializationContext)parser);
        return new Instance(this.id, player, entity);
    }

    public static class Instance
    extends AbstractCriterionTriggerInstance {
        private final ContextAwarePredicate entity;

        public Instance(ResourceLocation id, ContextAwarePredicate player, ContextAwarePredicate entity) {
            super(id, player);
            this.entity = entity;
        }

        public boolean matches(LootContext context) {
            return this.entity.m_285831_(context);
        }

        public JsonObject m_7683_(SerializationContext serializer) {
            JsonObject object = super.m_7683_(serializer);
            object.add("entity", this.entity.m_286026_(serializer));
            return object;
        }
    }
}

