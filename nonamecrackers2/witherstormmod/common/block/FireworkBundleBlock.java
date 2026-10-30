/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.stats.Stats
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Explosion
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.BaseEntityBlock
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.RenderShape
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityTicker
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.BlockHitResult
 *  org.jetbrains.annotations.Nullable
 */
package nonamecrackers2.witherstormmod.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import nonamecrackers2.witherstormmod.common.blockentity.FireworkBundleBlockEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
import org.jetbrains.annotations.Nullable;

public class FireworkBundleBlock
extends BaseEntityBlock {
    public FireworkBundleBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public RenderShape m_7514_(BlockState state) {
        return RenderShape.MODEL;
    }

    public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction direction, @Nullable LivingEntity igniter) {
        this.activateFuse(level, pos);
    }

    public void m_6807_(BlockState state, Level level, BlockPos pos, BlockState state2, boolean flag) {
        if (state2.m_60713_(state.m_60734_()) && level.m_276867_(pos)) {
            this.activateFuse(level, pos);
        }
    }

    public void m_6861_(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos2, boolean flag) {
        if (level.m_276867_(pos)) {
            this.activateFuse(level, pos);
        }
    }

    public InteractionResult m_6227_(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack stack = player.m_21120_(hand);
        if (!stack.m_150930_(Items.f_42409_) && !stack.m_150930_(Items.f_42613_)) {
            return super.m_6227_(state, level, pos, player, hand, hitResult);
        }
        this.onCaughtFire(state, level, pos, hitResult.m_82434_(), (LivingEntity)player);
        Item item = stack.m_41720_();
        if (!player.m_7500_()) {
            if (stack.m_150930_(Items.f_42409_)) {
                stack.m_41622_(1, (LivingEntity)player, p -> p.m_21190_(hand));
            } else {
                stack.m_41774_(1);
            }
        }
        player.m_36246_(Stats.f_12982_.m_12902_((Object)item));
        return InteractionResult.m_19078_((boolean)level.f_46443_);
    }

    public void m_5581_(Level p_60453_, BlockState p_60454_, BlockHitResult p_60455_, Projectile p_60456_) {
    }

    public boolean m_6903_(Explosion explosion) {
        return false;
    }

    private void activateFuse(Level level, BlockPos pos) {
        BlockEntity blockEntity = level.m_7702_(pos);
        if (blockEntity instanceof FireworkBundleBlockEntity) {
            FireworkBundleBlockEntity entity = (FireworkBundleBlockEntity)blockEntity;
            entity.beginFuse();
        }
    }

    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        FireworkBundleBlockEntity entity;
        BlockEntity blockEntity = level.m_7702_(pos);
        if (blockEntity instanceof FireworkBundleBlockEntity && (entity = (FireworkBundleBlockEntity)blockEntity).isActivated()) {
            return 4;
        }
        return super.getLightEmission(state, level, pos);
    }

    public BlockEntity m_142194_(BlockPos pos, BlockState state) {
        return new FireworkBundleBlockEntity(pos, state);
    }

    public <T extends BlockEntity> BlockEntityTicker<T> m_142354_(Level level, BlockState state, BlockEntityType<T> type) {
        return level.f_46443_ ? null : FireworkBundleBlock.m_152132_(type, (BlockEntityType)((BlockEntityType)WitherStormModBlockEntityTypes.FIREWORK_BUNDLE.get()), FireworkBundleBlockEntity::serverTick);
    }
}

