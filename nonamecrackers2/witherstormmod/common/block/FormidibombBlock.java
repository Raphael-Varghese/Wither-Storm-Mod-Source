/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Explosion
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.EntityBlock
 *  net.minecraft.world.level.block.HorizontalDirectionalBlock
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.block.TntBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityTicker
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.DirectionProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.material.PushReaction
 *  net.minecraft.world.level.storage.loot.BuiltInLootTables
 *  net.minecraft.world.level.storage.loot.LootParams
 *  net.minecraft.world.level.storage.loot.LootParams$Builder
 *  net.minecraft.world.level.storage.loot.LootTable
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParams
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.block;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.blockentity.FormidibombBlockEntity;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import nonamecrackers2.witherstormmod.common.item.FormidibombItem;
import nonamecrackers2.witherstormmod.common.util.IFormidibomb;

public class FormidibombBlock
extends TntBlock
implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.f_54117_;

    public FormidibombBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.m_49959_((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)FACING, (Comparable)Direction.NORTH)).m_61124_((Property)TntBlock.f_57419_, (Comparable)Boolean.valueOf(false)));
    }

    public void m_7592_(Level world, BlockPos pos, Explosion explosion) {
        if (!world.f_46443_) {
            BlockEntity tile = world.m_7702_(pos);
            IFormidibomb formidibomb = null;
            if (tile instanceof IFormidibomb) {
                formidibomb = (IFormidibomb)tile;
            }
            FormidibombEntity entity = new FormidibombEntity(world, (double)pos.m_123341_() + 0.5, pos.m_123342_(), (double)pos.m_123343_() + 0.5, explosion.m_252906_(), formidibomb, world.m_8055_(pos));
            entity.initiateFuse(20);
            world.m_7967_((Entity)entity);
        }
    }

    public void onCaughtFire(BlockState state, Level world, BlockPos pos, Direction face, LivingEntity igniter) {
        if (!world.f_46443_) {
            BlockEntity tile = world.m_7702_(pos);
            IFormidibomb formidibomb = null;
            if (tile instanceof IFormidibomb) {
                formidibomb = (IFormidibomb)tile;
            }
            FormidibombEntity entity = new FormidibombEntity(world, (double)pos.m_123341_() + 0.5, pos.m_123342_(), (double)pos.m_123343_() + 0.5, igniter, formidibomb, state);
            int newFuse = (Integer)WitherStormModConfig.SERVER.catchFireFuseTicks.get();
            if (entity.getFuseLife() > newFuse) {
                entity.initiateFuse(newFuse);
            }
            world.m_7967_((Entity)entity);
            world.m_6263_(null, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), SoundEvents.f_12512_, SoundSource.BLOCKS, 1.0f, 1.0f);
        }
    }

    public void m_214162_(BlockState state, Level level, BlockPos pos, RandomSource source) {
        FormidibombBlock.doParticles(source, pos, level, 6, 3.0f, 0.1f);
    }

    private static void doParticles(RandomSource random, BlockPos pos, Level level, int amount, float radius, float speed) {
        for (int i = 0; i < amount; ++i) {
            double x = ((double)random.m_188501_() * 2.0 - 1.0) * (double)radius;
            double y = ((double)random.m_188501_() * 2.0 - 1.0) * (double)radius;
            double z = ((double)random.m_188501_() * 2.0 - 1.0) * (double)radius;
            Vec3 start = Vec3.m_82512_((Vec3i)pos).m_82520_(x, y, z);
            Vec3 delta = new Vec3(x, y, z).m_82490_((double)speed);
            level.m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), start.f_82479_, start.f_82480_, start.f_82481_, -delta.f_82479_, -delta.f_82480_, -delta.f_82481_);
        }
    }

    public BlockState m_5573_(BlockPlaceContext context) {
        return (BlockState)this.m_49966_().m_61124_((Property)FACING, (Comparable)context.m_8125_());
    }

    public BlockState m_6843_(BlockState state, Rotation rotation) {
        return (BlockState)state.m_61124_((Property)FACING, (Comparable)rotation.m_55954_((Direction)state.m_61143_((Property)FACING)));
    }

    public BlockState m_6943_(BlockState state, Mirror mirror) {
        return state.m_60717_(mirror.m_54846_((Direction)state.m_61143_((Property)FACING)));
    }

    protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
        super.m_7926_(builder);
        builder.m_61104_(new Property[]{FACING});
    }

    public BlockEntity m_142194_(BlockPos pos, BlockState state) {
        return new FormidibombBlockEntity(pos, state);
    }

    public <T extends BlockEntity> BlockEntityTicker<T> m_142354_(Level level, BlockState state, BlockEntityType<T> type) {
        return FormidibombBlock.castTicker((BlockEntityTicker<FormidibombBlockEntity>)((BlockEntityTicker)FormidibombBlockEntity::tick));
    }

    private static <T extends BlockEntity> BlockEntityTicker<T> castTicker(BlockEntityTicker<FormidibombBlockEntity> ticker) {
        return ticker;
    }

    public List<ItemStack> m_49635_(BlockState state, LootParams.Builder loot) {
        ResourceLocation location = this.m_60589_();
        if (location == BuiltInLootTables.f_78712_) {
            return Collections.emptyList();
        }
        LootParams context = loot.m_287286_(LootContextParams.f_81461_, (Object)state).m_287235_(LootContextParamSets.f_81421_);
        ServerLevel world = context.m_287182_();
        LootTable table = world.m_7654_().m_278653_().m_278676_(location);
        ObjectArrayList stacks = table.m_287195_(context);
        for (ItemStack stack : stacks) {
            BlockEntity tile;
            if (!(stack.m_41720_() instanceof FormidibombItem) || !((tile = (BlockEntity)loot.m_287159_(LootContextParams.f_81462_)) instanceof IFormidibomb)) continue;
            IFormidibomb formidibomb = (IFormidibomb)tile;
            CompoundTag compound = stack.m_41784_();
            compound.m_128405_("Fuse", formidibomb.getFuseLife());
            compound.m_128405_("StartFuse", formidibomb.getStartFuse());
        }
        return stacks;
    }

    public void m_5871_(ItemStack stack, BlockGetter world, List<Component> components, TooltipFlag flag) {
        FormidibombItem item;
        super.m_5871_(stack, world, components, flag);
        if (stack.m_41720_() instanceof FormidibombItem && (item = (FormidibombItem)stack.m_41720_()).getStartFuse(stack) > 0 && item.getFuse(stack) < item.getStartFuse(stack)) {
            ChatFormatting style = item.getFuse(stack) / 10 % 2 == 0 ? ChatFormatting.RED : ChatFormatting.DARK_PURPLE;
            components.add((Component)Component.m_237110_((String)"description.formidibomb.fuse", (Object[])new Object[]{item.getFuse(stack) / 20}).m_130940_(style));
        }
    }

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.BLOCK;
    }
}

