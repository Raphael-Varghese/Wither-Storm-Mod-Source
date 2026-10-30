/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.arguments.EntityAnchorArgument$Anchor
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Position
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.targeting.TargetingConditions
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.item.PrimedTnt
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.event.entity.EntityMountEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingEntityUseItemEvent$Start
 *  net.minecraftforge.event.entity.player.PlayerEvent$PlayerRespawnEvent
 *  net.minecraftforge.event.level.ExplosionEvent$Detonate
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.common.event;

import java.util.UUID;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.WitherStormHead;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;

public class WitherStormEvents {
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        LivingEntity attacker = entity.m_21232_();
        if (!entity.m_9236_().f_46443_) {
            boolean flag = false;
            if (attacker instanceof WitherStormEntity) {
                WitherStormEntity storm = (WitherStormEntity)attacker;
                if (ForgeEventFactory.getMobGriefingEvent((Level)entity.m_9236_(), (Entity)entity)) {
                    BlockPos pos = BlockPos.m_274446_((Position)entity.m_20182_());
                    BlockState state = Blocks.f_50070_.m_49966_();
                    if (entity.m_9236_().m_46859_(pos) && state.m_60710_((LevelReader)entity.m_9236_(), pos)) {
                        entity.m_9236_().m_7731_(pos, state, 3);
                        flag = true;
                    }
                }
                if (!flag) {
                    ItemEntity item = new ItemEntity(entity.m_9236_(), entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), new ItemStack((ItemLike)Items.f_41951_));
                    entity.m_9236_().m_7967_((Entity)item);
                }
                if (entity instanceof ServerPlayer) {
                    ServerPlayer player = (ServerPlayer)entity;
                    player.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> data.setKilledByStorm(storm.m_20148_()));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        if (player instanceof ServerPlayer) {
            ServerPlayer player2 = (ServerPlayer)player;
            if (!event.isEndConquered()) {
                player2.getCapability(WitherStormModCapabilities.PLAYER_WITHER_STORM_DATA).ifPresent(data -> {
                    WitherStormEntity storm;
                    Entity patt3648$temp;
                    UUID id = data.getKilledByStorm();
                    if (((Boolean)WitherStormModConfig.SERVER.preventWitherStormCamping.get()).booleanValue() && id != null && (patt3648$temp = player2.m_284548_().m_8791_(id)) instanceof WitherStormEntity && (storm = (WitherStormEntity)patt3648$temp).getPhase() > 3 && (double)storm.m_20270_((Entity)player2) < 300.0) {
                        ServerLevel level = player2.m_284548_();
                        for (int i = 0; i < 10; ++i) {
                            Vec3 vec;
                            float angle = storm.m_217043_().m_188501_() * ((float)Math.PI * 2);
                            int x = (int)(Mth.m_14089_((float)angle) * ((float)storm.m_217043_().m_188503_(200) + 300.0f)) + storm.m_146903_();
                            int z = (int)(Mth.m_14031_((float)angle) * ((float)storm.m_217043_().m_188503_(200) + 300.0f)) + storm.m_146907_();
                            level.m_46745_(new BlockPos(x, 0, z));
                            int y = level.m_6924_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
                            BlockPos pos = new BlockPos(x, y, z);
                            if (!Level.m_46741_((BlockPos)pos) || (vec = (Vec3)Player.m_36130_((ServerLevel)level, (BlockPos)pos, (float)0.0f, (boolean)true, (boolean)true).orElse(null)) == null) continue;
                            player2.m_7618_(EntityAnchorArgument.Anchor.EYES, vec);
                            player2.m_6027_(vec.f_82479_, vec.f_82480_, vec.f_82481_);
                            break;
                        }
                    }
                    data.setKilledByStorm(null);
                });
            }
        }
    }

    @SubscribeEvent
    public static void onExplosionDetonate(ExplosionEvent.Detonate event) {
        Entity source;
        Level world = event.getLevel();
        if (!world.f_46443_ && (source = event.getExplosion().getExploder()) != null && !(source instanceof WitherStormEntity)) {
            for (Entity entity : world.m_45971_(WitherStormEntity.class, TargetingConditions.f_26872_, null, source.m_20191_().m_82400_(100.0))) {
                if (!(entity instanceof WitherStormEntity)) continue;
                WitherStormEntity storm = (WitherStormEntity)entity;
                for (WitherStormHead head : storm.getHeadManager().getHeads()) {
                    Projectile projectile;
                    Entity exploder;
                    if (!storm.tractorBeamActive(head.getIndex())) continue;
                    if (source instanceof PrimedTnt && !(source instanceof FormidibombEntity) && storm.canBeDistracted(head.getIndex(), WitherStormBase.DistractionType.ENTITY_BASED)) {
                        int chance = Math.max(1, Mth.m_14143_((float)(storm.m_20270_(source) / 30.0f)));
                        if (head.canSee(source) && storm.m_217043_().m_188503_(chance) == 0) {
                            head.makeDistracted(source.m_20182_(), storm.m_217043_().m_188503_(60) + 120);
                        }
                    }
                    if ((exploder = event.getExplosion().getExploder()) != null && exploder instanceof Projectile && (projectile = (Projectile)exploder).m_19749_() == storm) continue;
                    Vec3 headPos = head.getHeadPos();
                    double d = event.getExplosion().getPosition().m_82554_(headPos);
                    double d2 = storm.getPhase() < 4 ? 5.0 : 12.0;
                    if (!(d < d2) || storm.isDeadOrPlayingDead() || head.isHeadInjured() || !head.checkAndCountAttack()) continue;
                    head.hurt(source, storm.getHeadManager().getHeadInjuryTime());
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityMount(EntityMountEvent event) {
        if (event.getEntityMounting() instanceof WitherStormEntity) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onEntityUseItem(LivingEntityUseItemEvent.Start event) {
        ItemStack item = event.getItem();
        if (item.m_150930_(Items.f_220219_)) {
            for (WitherStormEntity storm : event.getEntity().m_9236_().m_45976_(WitherStormEntity.class, event.getEntity().m_20191_().m_82377_(128.0, 256.0, 128.0))) {
                if (storm.isDeadOrPlayingDead()) continue;
                for (WitherStormHead head : storm.getHeadManager().getHeads()) {
                    if (!storm.tractorBeamActive(head.getIndex()) || !storm.canBeDistracted(head.getIndex(), WitherStormBase.DistractionType.ENTITY_BASED) || storm.m_217043_().m_188503_(3) != 0) continue;
                    head.makeDistracted(event.getEntity().m_146892_(), 80);
                }
            }
        }
    }
}

