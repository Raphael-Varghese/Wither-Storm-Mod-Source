/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.renderer.item.ItemProperties
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Tier
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.item.UseAnim
 *  net.minecraft.world.level.Level
 */
package nonamecrackers2.witherstormmod.common.item;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.common.item.CommandBlockSwordItem;

public class FormidiBladeItem
extends CommandBlockSwordItem {
    public static final int DEFAULT_RELEASE_TIME = 40;
    public static final String POWER = "Power";
    public static final String IS_CHARGED = "IsCharged";
    private static final float POWER_DECREASE_PER_TICK = 0.2f;

    public FormidiBladeItem(Tier tier, int damage, float attackSpeed, Item.Properties properties) {
        super(tier, damage, attackSpeed, properties);
    }

    public void m_6883_(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        CompoundTag tag = stack.m_41783_();
        if (tag != null && tag.m_128425_(IS_CHARGED, 1) && !tag.m_128471_(IS_CHARGED)) {
            float power = tag.m_128457_(POWER);
            if (power > 0.2f) {
                tag.m_128350_(POWER, power -= 0.2f);
            } else {
                tag.m_128473_(POWER);
            }
        }
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, InteractionHand hand) {
        CompoundTag tag;
        ItemStack stack = player.m_21120_(hand);
        if (!(player.m_36335_().m_41519_((Item)this) || (tag = stack.m_41783_()) != null && tag.m_128425_(POWER, 10) && !(tag.m_128457_(POWER) < 1.0f))) {
            player.m_6672_(hand);
            return InteractionResultHolder.m_19090_((Object)stack);
        }
        return InteractionResultHolder.m_19100_((Object)stack);
    }

    public int m_8105_(ItemStack stack) {
        return 72000;
    }

    public UseAnim m_6164_(ItemStack stack) {
        return UseAnim.NONE;
    }

    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        float power = (float)entity.m_21252_() / 40.0f;
        CompoundTag tag = stack.m_41784_();
        tag.m_128350_(POWER, Math.min(1.0f, power));
        tag.m_128379_(IS_CHARGED, true);
    }

    public void m_7373_(ItemStack stack, Level level, List<Component> text, TooltipFlag flag) {
        text.add((Component)Component.m_237115_((String)"item.witherstormmod.formidi_blade.author").m_130940_(ChatFormatting.DARK_GRAY));
        text.add((Component)Component.m_237115_((String)"item.witherstormmod.formidi_blade.use").m_130940_(ChatFormatting.DARK_GRAY));
    }

    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged ? super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) : false;
    }

    public static float getPower(@Nullable LivingEntity entity, ItemStack stack, boolean useItemTime) {
        CompoundTag tag = stack.m_41783_();
        float chargingPower = useItemTime && entity != null ? Math.min((float)entity.m_21252_() / 40.0f, 1.0f) : 0.0f;
        if (tag != null && tag.m_128425_(POWER, 5)) {
            return Math.max(tag.m_128457_(POWER), chargingPower);
        }
        return chargingPower;
    }

    public static void registerItemProperty() {
        ItemProperties.register((Item)((Item)WitherStormModItems.FORMIDI_BLADE.get()), (ResourceLocation)new ResourceLocation("witherstormmod", "anim"), (stack, world, entity, i) -> {
            if (entity != null) {
                return FormidiBladeItem.getPower(entity, stack, entity.m_21120_(InteractionHand.OFF_HAND) == stack || entity.m_21120_(InteractionHand.MAIN_HAND) == stack);
            }
            return 0.0f;
        });
    }
}

