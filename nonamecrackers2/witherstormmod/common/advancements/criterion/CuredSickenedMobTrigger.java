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
 *  net.minecraft.world.entity.Mob
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
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.storage.loot.LootContext;

public class CuredSickenedMobTrigger
extends SimpleCriterionTrigger<Instance> {
    private static final ResourceLocation ID = new ResourceLocation("witherstormmod", "cured_sickened_mob");

    public ResourceLocation m_7295_() {
        return ID;
    }

    public void trigger(ServerPlayer player, Mob entity, Mob conversion) {
        LootContext entityContext = EntityPredicate.m_36616_((ServerPlayer)player, (Entity)entity);
        LootContext conversionContext = EntityPredicate.m_36616_((ServerPlayer)player, (Entity)conversion);
        this.m_66234_(player, instance -> instance.matches(entityContext, conversionContext));
    }

    protected Instance createInstance(JsonObject object, ContextAwarePredicate player, DeserializationContext parser) {
        ContextAwarePredicate sickened = EntityPredicate.m_285855_((JsonObject)object, (String)"sickened", (DeserializationContext)parser);
        ContextAwarePredicate conversion = EntityPredicate.m_285855_((JsonObject)object, (String)"converison", (DeserializationContext)parser);
        return new Instance(ID, player, sickened, conversion);
    }

    public static class Instance
    extends AbstractCriterionTriggerInstance {
        private final ContextAwarePredicate sickened;
        private final ContextAwarePredicate conversion;

        public Instance(ResourceLocation id, ContextAwarePredicate player, ContextAwarePredicate sickened, ContextAwarePredicate conversion) {
            super(id, player);
            this.sickened = sickened;
            this.conversion = conversion;
        }

        public boolean matches(LootContext sickened, LootContext conversion) {
            if (!this.sickened.m_285831_(sickened)) {
                return false;
            }
            return this.conversion.m_285831_(conversion);
        }

        public JsonObject m_7683_(SerializationContext serializer) {
            JsonObject object = super.m_7683_(serializer);
            object.add("sickened", this.sickened.m_286026_(serializer));
            object.add("conversion", this.sickened.m_286026_(serializer));
            return object;
        }
    }
}

