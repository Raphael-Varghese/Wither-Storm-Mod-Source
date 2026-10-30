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

public class SummonMobSuperBeaconTrigger
extends SimpleCriterionTrigger<Instance> {
    private static final ResourceLocation ID = new ResourceLocation("witherstormmod", "summon_mob_withered_beacon");

    public ResourceLocation m_7295_() {
        return ID;
    }

    protected Instance createInstance(JsonObject object, ContextAwarePredicate player, DeserializationContext context) {
        ContextAwarePredicate summoned = EntityPredicate.m_285855_((JsonObject)object, (String)"resummoned", (DeserializationContext)context);
        return new Instance(player, summoned);
    }

    public void trigger(ServerPlayer player, Entity summoned) {
        LootContext context = EntityPredicate.m_36616_((ServerPlayer)player, (Entity)summoned);
        this.m_66234_(player, instance -> instance.matches(context));
    }

    public static class Instance
    extends AbstractCriterionTriggerInstance {
        private final ContextAwarePredicate summoned;

        public Instance(ContextAwarePredicate player, ContextAwarePredicate summoned) {
            super(ID, player);
            this.summoned = summoned;
        }

        public boolean matches(LootContext context) {
            return this.summoned.m_285831_(context);
        }

        public JsonObject m_7683_(SerializationContext context) {
            JsonObject obj = super.m_7683_(context);
            obj.add("summoned", this.summoned.m_286026_(context));
            return obj;
        }
    }
}

