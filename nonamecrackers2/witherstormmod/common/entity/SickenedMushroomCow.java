/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.ai.attributes.AttributeSupplier$Builder
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.ItemUtils
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.SuspiciousStewItem
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.common.IForgeShearable
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package nonamecrackers2.witherstormmod.common.entity;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SuspiciousStewItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.IForgeShearable;
import nonamecrackers2.witherstormmod.common.entity.SickenedCow;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SickenedMushroomCow
extends SickenedCow
implements IForgeShearable {
    public SickenedMushroomCow(EntityType<? extends SickenedMushroomCow> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder m_28307_() {
        return Monster.m_33035_().m_22268_(Attributes.f_22279_, 0.3).m_22268_(Attributes.f_22276_, 26.0);
    }

    @Override
    public InteractionResult m_6071_(Player player, InteractionHand hand) {
        ItemStack stack = player.m_21120_(hand);
        if (stack.m_150930_(Items.f_42399_) && !this.m_6162_()) {
            ItemStack stew = new ItemStack((ItemLike)Items.f_42718_);
            SuspiciousStewItem.m_43258_((ItemStack)stew, (MobEffect)MobEffects.f_19615_, (int)(160 + this.f_19796_.m_188503_(60)));
            ItemStack filled = ItemUtils.m_41817_((ItemStack)stack, (Player)player, (ItemStack)stew, (boolean)false);
            player.m_21008_(hand, filled);
            this.m_5496_(SoundEvents.f_12074_, 1.0f, 1.0f);
            return InteractionResult.m_19078_((boolean)this.m_9236_().f_46443_);
        }
        return super.m_6071_(player, hand);
    }

    @NotNull
    public List<ItemStack> onSheared(@Nullable Player player, @NotNull ItemStack item, Level level, BlockPos pos, int fortune) {
        SickenedCow cow;
        this.m_9236_().m_6269_((Player)null, (Entity)this, SoundEvents.f_12075_, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0f, 1.0f);
        if (!this.m_9236_().f_46443_ && (cow = (SickenedCow)((EntityType)WitherStormModEntityTypes.SICKENED_COW.get()).m_20615_(this.m_9236_())) != null) {
            ((ServerLevel)this.m_9236_()).m_8767_((ParticleOptions)ParticleTypes.f_123813_, this.m_20185_(), this.m_20227_(0.5), this.m_20189_(), 1, 0.0, 0.0, 0.0, 0.0);
            this.m_146870_();
            cow.m_7678_(this.m_20185_(), this.m_20186_(), this.m_20189_(), this.m_146908_(), this.m_146909_());
            cow.m_21153_(this.m_21223_());
            cow.f_20883_ = this.f_20883_;
            if (this.m_8077_()) {
                cow.m_6593_(this.m_7770_());
                cow.m_20340_(this.m_20151_());
            }
            if (this.m_21532_()) {
                cow.m_21530_();
            }
            cow.m_20331_(this.m_20147_());
            this.m_9236_().m_7967_((Entity)cow);
            ArrayList items = Lists.newArrayList();
            for (int i = 0; i < 3; ++i) {
                ItemStack drop = new ItemStack((ItemLike)WitherStormModItems.TAINTED_MUSHROOM.get());
                items.add(drop);
            }
            return items;
        }
        return Collections.emptyList();
    }
}

