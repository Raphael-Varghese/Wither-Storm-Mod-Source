/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 */
package nonamecrackers2.witherstormmod.common.item;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import nonamecrackers2.witherstormmod.common.blockentity.FormidibombBlockEntity;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;

public class FormidibombItem
extends BlockItem {
    public FormidibombItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    public void m_6883_(ItemStack stack, Level world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        if (!world.f_46443_) {
            this.tickFuse(stack, world, entity, entity.m_20183_());
        }
    }

    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        this.tickFuse(stack, entity.m_9236_(), null, entity.m_20183_());
        return super.onEntityItemUpdate(stack, entity);
    }

    public void tickFuse(ItemStack stack, Level world, @Nullable Entity entity, BlockPos pos) {
        if (!world.f_46443_) {
            if (((Boolean)WitherStormModConfig.SERVER.formidibombFuseEnabled.get()).booleanValue() && this.getStartFuse(stack) > 0) {
                this.countFuse(stack, -1);
            }
            if (this.getStartFuse(stack) > 0) {
                if (((Boolean)WitherStormModConfig.SERVER.shouldDropFromInventory.get()).booleanValue() && this.getFuse(stack) <= this.getStartFuse(stack) / (Integer)WitherStormModConfig.SERVER.dropInterval.get() && !world.f_46443_) {
                    LivingEntity living = null;
                    if (entity instanceof LivingEntity) {
                        living = (LivingEntity)entity;
                    }
                    FormidibombEntity formidibomb = new FormidibombEntity(world, (double)pos.m_123341_() + 0.5, pos.m_123342_(), (double)pos.m_123343_() + 0.5, living, null, ((Block)WitherStormModBlocks.FORMIDIBOMB.get()).m_49966_());
                    formidibomb.m_32085_(this.getFuse(stack));
                    formidibomb.setStartFuse(this.getStartFuse(stack));
                    if (world.m_7967_((Entity)formidibomb)) {
                        stack.m_41774_(1);
                        world.m_6263_(null, formidibomb.m_20185_(), formidibomb.m_20186_(), formidibomb.m_20189_(), SoundEvents.f_12512_, SoundSource.BLOCKS, 1.0f, 1.0f);
                    }
                }
                if (this.getFuse(stack) <= 0) {
                    stack.m_41774_(1);
                    if (!world.f_46443_) {
                        FormidibombEntity.explode(world, entity, 48 + world.f_46441_.m_188503_(9), 3, pos.m_123341_(), pos.m_123342_(), pos.m_123343_());
                    }
                }
            }
        }
    }

    public void m_7836_(ItemStack stack, Level world, Player entity) {
        this.setFuse(stack, (Integer)WitherStormModConfig.SERVER.craftFuseTicks.get());
    }

    public int m_142158_(ItemStack stack) {
        return Math.round(13.0f - (float)(this.getStartFuse(stack) - this.getFuse(stack)) * 13.0f / (float)this.getStartFuse(stack));
    }

    public int m_142159_(ItemStack stack) {
        int fuse = this.getStartFuse(stack) / this.getFuse(stack);
        int color = fuse % 2 == 0 ? 12718080 : 0x990099;
        return color;
    }

    public boolean m_142522_(ItemStack stack) {
        return this.getFuse(stack) > 0 && this.getFuse(stack) < this.getStartFuse(stack);
    }

    protected boolean m_7274_(BlockPos pos, Level world, Player player, ItemStack stack, BlockState state) {
        int fuse = this.getFuse(stack);
        int startFuse = this.getStartFuse(stack);
        BlockEntity tile = world.m_7702_(pos);
        if (tile instanceof FormidibombBlockEntity) {
            FormidibombBlockEntity formidibomb = (FormidibombBlockEntity)tile;
            formidibomb.setLifeFuse(fuse);
            formidibomb.setStartFuse(startFuse);
            formidibomb.setFormidibombOwner((LivingEntity)player);
            formidibomb.m_6596_();
        }
        return super.m_7274_(pos, world, player, stack, state);
    }

    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged ? super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged) : false;
    }

    public int getFuse(ItemStack stack) {
        return stack.m_41784_().m_128451_("Fuse");
    }

    public int getStartFuse(ItemStack stack) {
        return stack.m_41784_().m_128451_("StartFuse");
    }

    protected void countFuse(ItemStack stack, int amount) {
        CompoundTag compound = stack.m_41784_();
        compound.m_128405_("Fuse", compound.m_128451_("Fuse") + amount);
    }

    public void setFuse(ItemStack stack, int fuse) {
        CompoundTag compound = stack.m_41784_();
        compound.m_128405_("Fuse", fuse);
        compound.m_128405_("StartFuse", fuse);
    }
}

