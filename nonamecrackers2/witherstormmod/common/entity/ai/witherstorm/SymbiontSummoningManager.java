/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.SpawnPlacements$Type
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.ai.targeting.TargetingConditions
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.NaturalSpawner
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.shapes.VoxelShape
 *  net.minecraftforge.common.capabilities.ForgeCapabilities
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.items.IItemHandler
 */
package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm;

import java.util.Collections;
import java.util.List;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.items.IItemHandler;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.capability.PlayerWitherStormData;
import nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.item.FormidibombItem;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModItemTags;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class SymbiontSummoningManager {
    protected final WitherStormEntity entity;
    protected int timeTillCanSummonSymbiont;

    public SymbiontSummoningManager(WitherStormEntity entity) {
        this.entity = entity;
    }

    public void tick() {
        int delay;
        if (this.timeTillCanSummonSymbiont > 0) {
            --this.timeTillCanSummonSymbiont;
        }
        if (((Boolean)WitherStormModConfig.SERVER.canSummonSymbiont.get()).booleanValue() && this.entity.f_19797_ % (delay = Mth.m_14045_((int)((Integer)WitherStormModConfig.SERVER.minimumSpawnCheckInterval.get()), (int)1, (int)240) * 20) * (this.entity.m_217043_().m_188503_(3) + 1) == 0) {
            List players = this.entity.m_9236_().m_45955_(TargetingConditions.f_26872_, null, this.entity.getSearchBox());
            Collections.sort(players, (entity, entity1) -> Mth.m_14143_((float)(entity.m_20270_((Entity)this.entity) - entity1.m_20270_((Entity)this.entity))));
            Player player = null;
            for (Player playerToCheck : players) {
                if (!this.playerApplicable(playerToCheck)) continue;
                player = playerToCheck;
                break;
            }
            if (this.canSummonSymbiont() && player != null) {
                this.summonSymbiont(player);
            }
        }
    }

    protected boolean canSummonSymbiont() {
        WitherStormBowelsManager.BowelsInstance instance;
        ServerLevel bowels;
        WitherStormBowelsManager manager;
        CommandBlockEntity commandBlock;
        boolean flag = true;
        if (this.entity.isDeadOrPlayingDead() || !this.entity.m_6084_()) {
            flag = false;
        }
        if (this.entity.getPhase() < 5 || this.entity.getConsumedEntities() < this.entity.getConsumptionAmountForPhase(5)) {
            flag = false;
        }
        AABB searchBox = this.entity.getSearchBox().m_82400_(50.0);
        List<Entity> entities = WorldUtil.getPerformantEntitiesOfClass((ServerLevel)this.entity.m_9236_(), Entity.class, searchBox);
        for (Entity entity : entities) {
            if (!entity.m_6084_()) continue;
            if (entity instanceof FormidibombEntity) {
                FormidibombEntity formidibomb = (FormidibombEntity)entity;
                if (formidibomb.getStartFuse() <= 0) continue;
                flag = false;
                break;
            }
            if (!(entity instanceof WitheredSymbiontEntity)) continue;
            flag = false;
            break;
        }
        if (this.entity.isAttractingFormidibomb()) {
            flag = false;
        }
        if (this.timeTillCanSummonSymbiont > 0) {
            flag = false;
        }
        if (this.entity.hasRecentlyBeenRevived()) {
            flag = false;
        }
        if ((commandBlock = this.entity.getBowelsCommandBlock()) != null && commandBlock.m_21223_() < commandBlock.m_21233_()) {
            flag = false;
        }
        if ((manager = (WitherStormBowelsManager)(bowels = WitherStormMod.bowels((ServerLevel)this.entity.m_9236_())).getCapability(WitherStormModCapabilities.BOWELS_MANAGER).orElse(null)) != null && (instance = this.entity.getBowelsInstance()) != null) {
            AABB box = new AABB(instance.getPos()).m_82400_(50.0);
            for (Player entity : bowels.m_45976_(Player.class, box)) {
                if (manager.getInstanceFromEntity((Entity)entity) != instance) continue;
                flag = false;
                break;
            }
        }
        return flag;
    }

    protected boolean playerApplicable(Player player) {
        PlayerWitherStormData data;
        if (!EntitySelector.f_20408_.test(player) || !EntitySelector.f_20403_.test(player)) {
            return false;
        }
        if (player.m_20182_().m_82546_(this.entity.m_20182_()).m_165924_() > this.entity.m_21133_(Attributes.f_22277_)) {
            return false;
        }
        IItemHandler handler = (IItemHandler)player.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(null);
        if (handler != null) {
            for (int i = 0; i < handler.getSlots(); ++i) {
                FormidibombItem item;
                ItemStack stack = handler.getStackInSlot(i);
                if (stack.m_150930_((Item)WitherStormModItems.COMMAND_BLOCK_BOOK.get())) {
                    return false;
                }
                if (!(stack.m_41720_() instanceof FormidibombItem ? (item = (FormidibombItem)stack.m_41720_()).getStartFuse(stack) > 0 : this.entity.getPhase() > 5 && stack.m_204117_(WitherStormModItemTags.COMMAND_BLOCK_TOOLS))) continue;
                return false;
            }
        }
        if ((data = (PlayerWitherStormData)player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).orElse(null)) != null) {
            if (data.hasKilledSymbiontRecently()) {
                return false;
            }
            if (!data.hasChangedPhase(this.entity) && data.hasRecentlySummonedSymbiont(this.entity)) {
                return false;
            }
        }
        return true;
    }

    public void summonSymbiont(Player player) {
        float angle = -((float)Math.atan2(player.m_20185_() - this.entity.m_20185_(), player.m_20189_() - this.entity.m_20189_()));
        float spawnX = Mth.m_14089_((float)angle) * 30.0f + (float)this.entity.m_20185_();
        float spawnZ = Mth.m_14031_((float)angle) * 30.0f + (float)this.entity.m_20189_();
        double y = this.entity.m_20188_();
        for (int i = 0; i < 10; ++i) {
            int randomX = Mth.m_14143_((float)spawnX) + (int)(10.0 * this.entity.m_217043_().m_188583_()) + 5;
            int randomZ = Mth.m_14143_((float)spawnZ) + (int)(10.0 * this.entity.m_217043_().m_188583_()) + 5;
            int radius = 5;
            Integer highest = null;
            BlockPos pos = null;
            for (int ax = -radius; ax <= radius; ++ax) {
                for (int az = -radius; az <= radius; ++az) {
                    int x = ax + randomX;
                    int z = az + randomZ;
                    int height = player.m_9236_().m_6924_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z) - 1;
                    if (highest != null && height <= highest) continue;
                    highest = height;
                    pos = new BlockPos(x, highest.intValue(), z);
                }
            }
            BlockState state = this.entity.m_9236_().m_8055_(pos);
            if (!state.m_60783_((BlockGetter)this.entity.m_9236_(), pos, Direction.UP) || !state.m_60643_((BlockGetter)this.entity.m_9236_(), pos, this.entity.m_6095_())) continue;
            VoxelShape shape = state.m_60812_((BlockGetter)this.entity.m_9236_(), pos);
            if (!shape.m_83281_()) {
                y = shape.m_83297_(Direction.Axis.Y) + (double)pos.m_123342_();
            }
            if (!NaturalSpawner.m_47051_((SpawnPlacements.Type)SpawnPlacements.Type.ON_GROUND, (LevelReader)this.entity.m_9236_(), (BlockPos)BlockPos.m_274561_((double)((double)pos.m_123341_() + 0.5), (double)y, (double)((double)pos.m_123343_() + 0.5)), (EntityType)((EntityType)WitherStormModEntityTypes.WITHERED_SYMBIONT.get()))) continue;
            WitheredSymbiontEntity entity = (WitheredSymbiontEntity)((EntityType)WitherStormModEntityTypes.WITHERED_SYMBIONT.get()).m_20615_(this.entity.m_9236_());
            entity.m_6034_((double)pos.m_123341_() + 0.5, y, (double)pos.m_123343_() + 0.5);
            double deltaX = player.m_20185_() - entity.m_20185_();
            double deltaY = player.m_20188_() - entity.m_20188_();
            double deltaZ = player.m_20189_() - entity.m_20189_();
            double sqrt = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
            float yRot = (float)(Mth.m_14136_((double)deltaZ, (double)deltaX) * 57.2957763671875) - 90.0f;
            float xRot = (float)(-(Mth.m_14136_((double)deltaY, (double)sqrt) * 57.2957763671875));
            entity.f_20883_ = yRot;
            entity.m_146922_(yRot);
            entity.m_146926_(xRot);
            entity.setOwner(this.entity);
            this.entity.m_9236_().m_7967_((Entity)entity);
            ServerLevel world = (ServerLevel)this.entity.m_9236_();
            for (Player nearbyPlayers : world.m_45955_(TargetingConditions.f_26872_, null, this.entity.getSearchBox())) {
                CriteriaTriggers.f_10580_.m_68256_((ServerPlayer)nearbyPlayers, (Entity)entity);
            }
            ForgeEventFactory.onFinalizeSpawn((Mob)entity, (ServerLevelAccessor)world, (DifficultyInstance)world.m_6436_(entity.m_20183_()), (MobSpawnType)MobSpawnType.TRIGGERED, null, null);
            entity.m_21373_();
            world.m_8767_((ParticleOptions)((SimpleParticleType)WitherStormModParticleTypes.COMMAND_BLOCK.get()), entity.m_20185_(), entity.m_20188_(), entity.m_20189_(), 20, this.entity.m_217043_().m_188583_(), this.entity.m_217043_().m_188583_(), this.entity.m_217043_().m_188583_(), 0.2);
            world.m_8767_((ParticleOptions)ParticleTypes.f_123755_, entity.m_20185_(), entity.m_20188_(), entity.m_20189_(), 20, this.entity.m_217043_().m_188583_(), this.entity.m_217043_().m_188583_(), this.entity.m_217043_().m_188583_(), 0.01);
            this.timeTillCanSummonSymbiont = Mth.m_14045_((int)((Integer)WitherStormModConfig.SERVER.witherStormSummoningDelay.get()), (int)1, (int)20) * 1200 + this.entity.m_217043_().m_188503_(12000);
            player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> data.markSummonedSymbiont(this.entity));
            this.entity.m_5496_((SoundEvent)WitherStormModSoundEvents.COMMAND_BLOCK_SUMMON.get(), 15.0f, 1.0f);
            entity.m_5496_((SoundEvent)WitherStormModSoundEvents.WITHERED_SYMBIONT_SPAWN.get(), 12.0f, 1.0f);
            break;
        }
    }

    public int getSummoningDelay() {
        return this.timeTillCanSummonSymbiont;
    }

    public void setSummoningDelay(int delay) {
        this.timeTillCanSummonSymbiont = delay;
    }
}

