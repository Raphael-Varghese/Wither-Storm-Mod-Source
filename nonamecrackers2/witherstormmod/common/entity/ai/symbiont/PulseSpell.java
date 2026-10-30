/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.tags.TagKey
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.network.PacketDistributor
 */
package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.packet.PlayerMotionMessage;
import nonamecrackers2.witherstormmod.common.predicate.BlockPredicateBuilder;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;

public class PulseSpell
extends SymbiontSpell {
    public static final Predicate<BlockState> CAN_BE_THROWN = BlockPredicateBuilder.and().isNotAir().isNotAFluid().isNotTag(WitherStormModBlockTags.TAINTED_BLOCKS).isNotTag(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST).isNotTag(WitherStormModBlockTags.SMALL_CLUSTER_BLACKLIST).isNotTag((TagKey<Block>)BlockTags.f_278394_).isNotTag((TagKey<Block>)BlockTags.f_13079_).isNotTag(WitherStormModBlockTags.RED_SUPPORT_BASE).isNotTag(WitherStormModBlockTags.GREEN_SUPPORT_BASE).isNotTag(WitherStormModBlockTags.AQUA_SUPPORT_BASE).isNotTag(WitherStormModBlockTags.GRAY_SUPPORT_BASE).isNotTag(WitherStormModBlockTags.WITHERED_BEACON_BASE).build();

    public PulseSpell(WitheredSymbiontEntity symbiont, SpellType type) {
        super(symbiont, type);
    }

    @Override
    public void start(LivingEntity target) {
    }

    @Override
    public void cast(LivingEntity target) {
        List nearbyEntities = this.entity.getNearbyTargets(WitheredSymbiontEntity.PULSE_PREDICATE).toList();
        List<BlockPos> nearbyBlocks = this.getNearbyBlocks();
        this.entity.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), 4.0f, 2.0f);
        for (BlockPos pos : nearbyBlocks) {
            BlockClusterEntity cluster = (BlockClusterEntity)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()).m_20615_(this.entity.m_9236_());
            if (cluster == null) continue;
            cluster.populateWithRadius(pos, 1.0f, blockState -> !blockState.m_204336_(WitherStormModBlockTags.WITHER_STORM_BLOCK_BLACKLIST) && !blockState.m_204336_(WitherStormModBlockTags.SMALL_CLUSTER_BLACKLIST) && !blockState.m_204336_(WitherStormModBlockTags.TAINTED_BLOCKS));
            cluster.setTime(100);
            cluster.setShouldCrumble(false);
            int rotationDelta = this.entity.m_217043_().m_188503_(129) - 64;
            cluster.setRotationDelta(new Vec2((float)rotationDelta * 0.0625f, (float)rotationDelta * 0.0625f));
            cluster.m_20242_(false);
            cluster.setPhysics(true);
            double deltaX = (double)(pos.m_123341_() + this.entity.m_217043_().m_188503_(4)) - this.entity.m_20185_();
            double deltaY = (double)(pos.m_123342_() + this.entity.m_217043_().m_188503_(4)) - this.entity.m_20186_();
            double deltaZ = (double)(pos.m_123343_() + this.entity.m_217043_().m_188503_(4)) - this.entity.m_20189_();
            Vec3 deltaMovement = new Vec3(deltaX, deltaY, deltaZ).m_82541_().m_82490_(2.0);
            cluster.m_20256_(deltaMovement);
            this.entity.m_9236_().m_7967_((Entity)cluster);
        }
        for (Entity entity : nearbyEntities) {
            if (!entity.m_6084_() || entity == this.entity) continue;
            double deltaX = entity.m_20185_() - this.entity.m_20185_();
            double deltaY = entity.m_20186_() + 1.0 - this.entity.m_20186_();
            double deltaZ = entity.m_20189_() - this.entity.m_20189_();
            Vec3 deltaMovement = new Vec3(deltaX, deltaY, deltaZ).m_82541_().m_82490_(3.0);
            entity.m_20256_(deltaMovement);
            if (!(entity instanceof ServerPlayer)) continue;
            PlayerMotionMessage message = new PlayerMotionMessage(deltaMovement);
            WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)entity), (Object)message);
        }
    }

    @Override
    public void doCasting(LivingEntity target) {
        List nearbyEntities = this.entity.getNearbyTargets(WitheredSymbiontEntity.PULSE_PREDICATE).toList();
        Vec3 entityDelta = this.entity.m_20299_(1.0f).m_82520_(this.entity.m_20185_(), this.entity.m_20188_(), this.entity.m_20189_()).m_82541_().m_82542_(0.1, 0.1, 0.1);
        ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)((SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get()), this.entity.m_20185_(), this.entity.m_20188_(), this.entity.m_20189_(), 3, entityDelta.m_7096_(), entityDelta.m_7098_(), entityDelta.m_7094_(), 1.0);
        for (Entity entity : nearbyEntities) {
            double x = entity.m_20185_() + this.entity.m_217043_().m_188583_() * 1.0;
            double y = entity.m_20188_() + this.entity.m_217043_().m_188583_() * 1.0;
            double z = entity.m_20189_() + this.entity.m_217043_().m_188583_() * 1.0;
            Vec3 delta = entity.m_20299_(1.0f).m_82492_(x, y, z).m_82541_().m_82542_(0.1, 0.1, 0.1);
            ((ServerLevel)this.entity.m_9236_()).m_8767_((ParticleOptions)((SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get()), x, y, z, 0, delta.m_7096_(), delta.m_7098_(), delta.m_7094_(), 1.0);
        }
        if (this.entity.f_19797_ % Math.max(2, 16) == 0) {
            this.entity.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_ACTIVATES.get(), 4.0f, 0.75f);
        }
    }

    private List<BlockPos> getNearbyBlocks() {
        ArrayList<BlockPos> nearbyBlocks = new ArrayList<BlockPos>();
        BlockPos entityPos = this.entity.m_20183_();
        int horizontalRange = 16;
        int verticalRange = 8;
        int maxCount = 1024;
        int blockCount = 0;
        for (int x = -horizontalRange; x <= horizontalRange; ++x) {
            block1: for (int y = -verticalRange; y <= verticalRange; ++y) {
                for (int z = -horizontalRange; z <= horizontalRange; ++z) {
                    BlockPos searchPos = entityPos.m_7918_(x, y, z);
                    if (blockCount >= maxCount) continue block1;
                    if (!CAN_BE_THROWN.test(this.entity.m_9236_().m_8055_(searchPos))) continue;
                    nearbyBlocks.add(searchPos);
                    ++blockCount;
                }
            }
        }
        return nearbyBlocks;
    }

    @Override
    public int getDelay(RandomSource random, float modifier) {
        return Math.max(480, random.m_188503_(600)) - Mth.m_14143_((float)modifier) * 10;
    }
}

