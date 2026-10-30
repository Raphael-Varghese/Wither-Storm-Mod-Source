/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeInstance
 *  net.minecraft.world.entity.ai.attributes.AttributeMap
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.common.util.LazyOptional
 */
package nonamecrackers2.witherstormmod.common.potion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModEntityTags;

public class WitherSicknessEffect
extends MobEffect {
    public WitherSicknessEffect(MobEffectCategory type, int color) {
        super(type, color);
    }

    public void m_6742_(LivingEntity entity, int amplifier) {
        LazyOptional trackerOptional = entity.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER);
        trackerOptional.ifPresent(tracker -> {
            if (!tracker.isBeingCured()) {
                float damage = entity.m_6095_().m_204039_(WitherStormModEntityTags.HIGH_IMMUNITY) ? 1.0f : 2.0f;
                entity.m_6469_(WitherStormModDamageTypes.source(entity.m_9236_().m_9598_(), WitherStormModDamageTypes.WITHER_SICKNESS), damage);
                this.addToMaxHealthModifier(entity, -0.5, amplifier);
            }
        });
        if (!trackerOptional.isPresent()) {
            float damage = entity.m_6095_().m_204039_(WitherStormModEntityTags.HIGH_IMMUNITY) ? 1.0f : 2.0f;
            entity.m_6469_(WitherStormModDamageTypes.source(entity.m_9236_().m_9598_(), WitherStormModDamageTypes.WITHER_SICKNESS), damage);
            this.addToMaxHealthModifier(entity, -0.5, amplifier);
        }
    }

    public boolean m_6584_(int p_76397_1_, int p_76397_2_) {
        int i = 7200 >> p_76397_2_;
        if (i > 0) {
            return p_76397_1_ % i == 0;
        }
        return true;
    }

    public void addToMaxHealthModifier(LivingEntity entity, double amount, int amplifier) {
        Attribute attribute = Attributes.f_22276_;
        if (this.m_19485_().containsKey(attribute)) {
            UUID id = ((AttributeModifier)this.m_19485_().get(attribute)).m_22209_();
            AttributeModifier modifier = entity.m_21051_(attribute).m_22111_(id);
            if (modifier != null) {
                double value = modifier.m_22218_();
                value = Math.max(entity.m_21172_(attribute) * -1.0 + 1.0, value + amount);
                this.updateAttributeModifier(entity, Attributes.f_22276_, value + amount, amplifier);
            }
        }
    }

    public void updateAttributeModifier(LivingEntity entity, Attribute attribute, double amount, int amplifier) {
        UUID id = ((AttributeModifier)this.m_19485_().get(attribute)).m_22209_();
        AttributeModifier modifier = entity.m_21051_(attribute).m_22111_(id);
        AttributeInstance instance = entity.m_21204_().m_22146_(attribute);
        if (instance != null) {
            instance.m_22130_(modifier);
            AttributeModifier newModifier = new AttributeModifier(modifier.m_22209_(), this.m_19481_() + " " + amplifier, amount, modifier.m_22217_());
            instance.m_22125_(newModifier);
        }
    }

    public void m_6385_(LivingEntity entity, AttributeMap manager, int amplifier) {
        for (Map.Entry entry : this.m_19485_().entrySet()) {
            AttributeInstance instance = manager.m_22146_((Attribute)entry.getKey());
            if (instance == null) continue;
            AttributeModifier modifier = (AttributeModifier)entry.getValue();
            if (!instance.m_22109_(modifier)) {
                instance.m_22125_(new AttributeModifier(modifier.m_22209_(), this.m_19481_() + " " + amplifier, this.m_7048_(amplifier, modifier), modifier.m_22217_()));
                continue;
            }
            AttributeModifier original = instance.m_22111_(modifier.m_22209_());
            instance.m_22130_(modifier);
            instance.m_22125_(new AttributeModifier(original.m_22209_(), this.m_19481_() + " " + amplifier, this.m_7048_(amplifier, original), original.m_22217_()));
        }
    }

    public List<ItemStack> getCurativeItems() {
        return new ArrayList<ItemStack>();
    }
}

