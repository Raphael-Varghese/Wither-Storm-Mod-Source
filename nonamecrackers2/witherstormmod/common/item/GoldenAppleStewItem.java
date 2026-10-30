/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.BowlFoodItem
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 */
package nonamecrackers2.witherstormmod.common.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;

public class GoldenAppleStewItem
extends BowlFoodItem {
    public GoldenAppleStewItem(Item.Properties properties) {
        super(properties);
    }

    public InteractionResult m_6880_(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        boolean[] flag = new boolean[]{false};
        entity.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> {
            if (tracker.isInfected() && !tracker.isBeingCured() && !tracker.isActuallyImmune()) {
                tracker.beginCure();
                entity.m_9236_().m_6263_(null, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), SoundEvents.f_12644_, SoundSource.NEUTRAL, 1.0f, 1.0f);
                flag[0] = true;
            }
        });
        if (flag[0]) {
            if (!player.m_150110_().f_35937_) {
                stack.m_41774_(1);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public ItemStack m_5922_(ItemStack stack, Level world, LivingEntity entity) {
        entity.getCapability(WitherStormModCapabilities.WITHER_SICKNESS_TRACKER).ifPresent(tracker -> {
            if (tracker.isInfected() && !tracker.isBeingCured() && !tracker.isActuallyImmune()) {
                tracker.beginCure();
                entity.m_9236_().m_6263_(null, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), SoundEvents.f_12644_, SoundSource.NEUTRAL, 1.0f, 1.0f);
            }
        });
        return super.m_5922_(stack, world, entity);
    }
}

