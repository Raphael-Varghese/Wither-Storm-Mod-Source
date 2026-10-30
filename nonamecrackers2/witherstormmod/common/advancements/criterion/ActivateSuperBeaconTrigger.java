/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance
 *  net.minecraft.advancements.critereon.ContextAwarePredicate
 *  net.minecraft.advancements.critereon.DeserializationContext
 *  net.minecraft.advancements.critereon.MinMaxBounds$Ints
 *  net.minecraft.advancements.critereon.SerializationContext
 *  net.minecraft.advancements.critereon.SimpleCriterionTrigger
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerPlayer
 */
package nonamecrackers2.witherstormmod.common.advancements.criterion;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class ActivateSuperBeaconTrigger
extends SimpleCriterionTrigger<TriggerInstance> {
    private static final ResourceLocation ID = new ResourceLocation("witherstormmod", "activate_super_beacon");

    public ResourceLocation m_7295_() {
        return ID;
    }

    protected TriggerInstance createInstance(JsonObject obj, ContextAwarePredicate player, DeserializationContext contet) {
        MinMaxBounds.Ints totalActivated = MinMaxBounds.Ints.m_55373_((JsonElement)obj.get("total_activated"));
        return new TriggerInstance(player, totalActivated);
    }

    public void trigger(ServerPlayer player, int totalActivated) {
        this.m_66234_(player, instance -> instance.matches(totalActivated));
    }

    public static class TriggerInstance
    extends AbstractCriterionTriggerInstance {
        private final MinMaxBounds.Ints totalActivated;

        public TriggerInstance(ContextAwarePredicate player, MinMaxBounds.Ints totalActivated) {
            super(ID, player);
            this.totalActivated = totalActivated;
        }

        public boolean matches(int activated) {
            return this.totalActivated.m_55390_(activated);
        }

        public JsonObject m_7683_(SerializationContext context) {
            JsonObject obj = super.m_7683_(context);
            obj.add("level", this.totalActivated.m_55328_());
            return obj;
        }
    }
}

