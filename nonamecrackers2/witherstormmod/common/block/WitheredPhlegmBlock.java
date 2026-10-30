/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.Container
 *  net.minecraft.world.Containers
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.BaseEntityBlock
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.RenderShape
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityTicker
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.BlockStateProperties
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.block;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.blockentity.WitheredPhlegmBlockEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;

public class WitheredPhlegmBlock
extends BaseEntityBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.f_61448_;

    public WitheredPhlegmBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.m_49959_((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)POWERED, (Comparable)Boolean.valueOf(false)));
    }

    protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
        builder.m_61104_(new Property[]{POWERED});
    }

    public void m_6861_(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos1, boolean flag) {
        boolean neighborSignal = level.m_276867_(pos);
        if (neighborSignal != (Boolean)state.m_61143_((Property)POWERED)) {
            level.m_7731_(pos, (BlockState)state.m_61124_((Property)POWERED, (Comparable)Boolean.valueOf(neighborSignal)), 3);
        }
    }

    public InteractionResult m_6227_(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.f_46443_) {
            return InteractionResult.SUCCESS;
        }
        BlockEntity blockEntity = level.m_7702_(pos);
        if (blockEntity instanceof WitheredPhlegmBlockEntity) {
            WitheredPhlegmBlockEntity phlegmBlock = (WitheredPhlegmBlockEntity)blockEntity;
            player.m_5893_((MenuProvider)phlegmBlock);
        }
        return InteractionResult.CONSUME;
    }

    public void m_6810_(BlockState state, Level level, BlockPos pos, BlockState state1, boolean flag) {
        BlockEntity blockEntity;
        if (!state.m_60713_(state1.m_60734_()) && (blockEntity = level.m_7702_(pos)) instanceof Container) {
            Container container = (Container)blockEntity;
            Containers.m_19002_((Level)level, (BlockPos)pos, (Container)container);
            level.m_46717_(pos, (Block)this);
        }
        super.m_6810_(state, level, pos, state1, flag);
    }

    public RenderShape m_7514_(BlockState state) {
        return RenderShape.MODEL;
    }

    public BlockEntity m_142194_(BlockPos pos, BlockState state) {
        return new WitheredPhlegmBlockEntity(pos, state);
    }

    public <T extends BlockEntity> BlockEntityTicker<T> m_142354_(Level level, BlockState state, BlockEntityType<T> type) {
        return level.f_46443_ ? null : WitheredPhlegmBlock.m_152132_(type, (BlockEntityType)((BlockEntityType)WitherStormModBlockEntityTypes.WITHERED_PHLEGM.get()), WitheredPhlegmBlockEntity::serverTick);
    }

    public boolean m_7278_(BlockState state) {
        return true;
    }

    public int m_6782_(BlockState state, Level level, BlockPos pos) {
        return AbstractContainerMenu.m_38918_((BlockEntity)level.m_7702_(pos));
    }

    public void m_6402_(Level level, BlockPos pos, BlockState state, LivingEntity entity, ItemStack item) {
        BlockEntity blockEntity;
        if (item.m_41788_() && (blockEntity = level.m_7702_(pos)) instanceof WitheredPhlegmBlockEntity) {
            WitheredPhlegmBlockEntity phlegmBlock = (WitheredPhlegmBlockEntity)blockEntity;
            phlegmBlock.m_58638_(item.m_41786_());
        }
    }

    public void m_214162_(BlockState state, Level level, BlockPos pos, RandomSource source) {
        if (!((Boolean)state.m_61143_((Property)BlockStateProperties.f_61448_)).booleanValue()) {
            WitheredPhlegmBlock.doParticles(source, pos, level, 6, 3.0f, 0.1f);
        } else {
            WitheredPhlegmBlock.doParticles(source, pos, level, 2, 1.0f, -0.01f);
        }
    }

    private static void doParticles(RandomSource random, BlockPos pos, Level level, int amount, float radius, float speed) {
        for (int i = 0; i < amount; ++i) {
            double x = ((double)random.m_188501_() * 2.0 - 1.0) * (double)radius;
            double y = ((double)random.m_188501_() * 2.0 - 1.0) * (double)radius;
            double z = ((double)random.m_188501_() * 2.0 - 1.0) * (double)radius;
            Vec3 start = Vec3.m_82512_((Vec3i)pos).m_82520_(x, y, z);
            Vec3 delta = new Vec3(x, y, z).m_82490_((double)speed);
            level.m_7106_((ParticleOptions)WitherStormModParticleTypes.PHLEGM.get(), start.f_82479_, start.f_82480_, start.f_82481_, -delta.f_82479_, -delta.f_82480_, -delta.f_82481_);
        }
    }

    public void m_5871_(ItemStack stack, BlockGetter level, List<Component> text, TooltipFlag flag) {
        text.add((Component)Component.m_237115_((String)"description.withered_phlegm.use").m_130940_(ChatFormatting.DARK_GRAY));
    }

    public void m_213897_(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockEntity blockEntity = level.m_7702_(pos);
        if (blockEntity instanceof WitheredPhlegmBlockEntity) {
            WitheredPhlegmBlockEntity entity = (WitheredPhlegmBlockEntity)blockEntity;
            entity.recheckOpen();
        }
    }

    public int getExpDrop(BlockState state, LevelReader level, RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
        int n;
        BlockEntity blockEntity = level.m_7702_(pos);
        if (blockEntity instanceof WitheredPhlegmBlockEntity) {
            WitheredPhlegmBlockEntity phlegm = (WitheredPhlegmBlockEntity)blockEntity;
            n = phlegm.getStoredXp();
        } else {
            n = 0;
        }
        return n;
    }
}

