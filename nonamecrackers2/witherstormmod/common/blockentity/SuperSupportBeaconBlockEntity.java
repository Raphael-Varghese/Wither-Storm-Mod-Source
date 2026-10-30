/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtUtils
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.world.Container
 *  net.minecraft.world.LockCode
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ContainerLevelAccess
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BaseContainerBlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.network.PacketDistributor
 *  net.minecraftforge.network.PacketDistributor$TargetPoint
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package nonamecrackers2.witherstormmod.common.blockentity;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.LockCode;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.common.blockentity.AbstractSuperBeaconBlockEntity;
import nonamecrackers2.witherstormmod.common.blockentity.SuperBeaconBlockEntity;
import nonamecrackers2.witherstormmod.common.blockentity.inventory.SuperSupportBeaconMenu;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SuperSupportBeaconBlockEntity
extends AbstractSuperBeaconBlockEntity {
    public static final int EFFECT_AREA_ARC = 90;
    private static final int[] FALLBACK_COLOR = new int[]{255, 255, 255};
    private static final Logger LOGGER = LogManager.getLogger();
    @Nullable
    private AbstractSuperBeaconBlockEntity.Color color;
    @Nullable
    private BlockPos connectedBeacon;

    public SuperSupportBeaconBlockEntity(BlockPos pos, BlockState state) {
        super((BlockEntityType<? extends AbstractSuperBeaconBlockEntity>)((BlockEntityType)WitherStormModBlockEntityTypes.SUPER_SUPPORT_BEACON.get()), pos, state);
    }

    @Override
    public void tick() {
        SuperBeaconBlockEntity beacon;
        super.tick();
        if (!this.f_58857_.f_46443_) {
            boolean flag;
            SuperBeaconBlockEntity beacon2;
            BlockPos searchStart = this.f_58858_.m_7495_();
            int radius = 1;
            ArrayList blocks = Lists.newArrayList();
            for (int x = -radius; x <= radius; ++x) {
                for (int z = -radius; z <= radius; ++z) {
                    blocks.add(this.f_58857_.m_8055_(searchStart.m_7918_(x, 0, z)));
                }
            }
            AbstractSuperBeaconBlockEntity.Color matching = null;
            for (AbstractSuperBeaconBlockEntity.Color color : AbstractSuperBeaconBlockEntity.Color.values()) {
                if (!blocks.stream().allMatch(block -> color.isValidBaseBlock((BlockState)block))) continue;
                matching = color;
                break;
            }
            if (matching != this.color) {
                this.color = matching;
                this.markUpdated();
            }
            if ((beacon2 = this.getNearbyValidBeacon()) != null) {
                if (this.connectedBeacon != beacon2.m_58899_()) {
                    this.markUpdated();
                }
                this.connectedBeacon = beacon2.m_58899_();
            }
            boolean bl = flag = this.color != null && beacon2 != null;
            if (flag != this.isActive) {
                if (flag) {
                    this.isActive = true;
                    this.activate();
                } else {
                    this.isActive = false;
                    this.deactivate();
                }
                this.markUpdated();
            }
        }
        if ((beacon = this.getConnectedBeaconEntity()) != null) {
            this.beaconLevel = beacon.beaconLevel;
            this.showWorkingArea = beacon.showWorkingArea();
            if (!this.f_58857_.f_46443_ && this.color != null && beacon.getResummonTicks() == this.getResummonThreshold()) {
                this.playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_BEACON_ACTIVATE.get(), 1.0f, 1.0f);
                this.playSound((SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), 10.0f, 1.0f);
                Vec3 pos = Vec3.m_82512_((Vec3i)this.getConnectedBeacon());
                WitherStormModPacketHandlers.MAIN.send(PacketDistributor.NEAR.with(PacketDistributor.TargetPoint.p((double)pos.f_82479_, (double)pos.f_82480_, (double)pos.f_82481_, (double)20.0, (ResourceKey)this.f_58857_.m_46472_())), (Object)new ShakeScreenMessage(80.0f, 10.0f));
            }
        }
    }

    @Override
    protected void applyEffect(ServerLevel level) {
        if (this.getConnectedBeacon() != null) {
            BlockPos target = this.getConnectedBeacon();
            Vec3 targetVec = Vec3.m_82512_((Vec3i)target);
            float angleWithMainBeacon = SuperSupportBeaconBlockEntity.getAngleBetween(this.m_58899_(), this.getConnectedBeacon());
            for (ServerPlayer player : level.m_6907_()) {
                float angle = (float)(Mth.m_14136_((double)(player.m_20185_() - targetVec.f_82479_), (double)(player.m_20189_() - targetVec.f_82481_)) * 57.29577951308232);
                float diff = (angleWithMainBeacon - angle + 180.0f + 360.0f) % 360.0f - 180.0f;
                if (!(diff <= 45.0f) || !(diff >= -45.0f)) continue;
                player.m_7292_(new MobEffectInstance(this.effect, 205, this.beaconLevel - 1, true, true));
            }
        }
    }

    public static float getAngleBetween(BlockPos blockPos, BlockPos blockTarget) {
        Vec3 pos = Vec3.m_82512_((Vec3i)blockPos);
        Vec3 target = Vec3.m_82512_((Vec3i)blockTarget);
        return (float)(Mth.m_14136_((double)(pos.f_82479_ - target.f_82479_), (double)(pos.f_82481_ - target.f_82481_)) * 57.29577951308232);
    }

    @Override
    public float getThickness() {
        return 0.15f;
    }

    @Override
    public float getOuterThickness() {
        return 0.2f;
    }

    public AbstractSuperBeaconBlockEntity.Color getColor() {
        return this.color;
    }

    @Override
    public int[] getBeamColor() {
        AbstractSuperBeaconBlockEntity.Color color = this.getColor();
        if (color != null) {
            return new int[]{color.getRed(), color.getGreen(), color.getBlue()};
        }
        return FALLBACK_COLOR;
    }

    @Nullable
    private SuperBeaconBlockEntity getNearbyValidBeacon() {
        AABB box = new AABB(this.m_58899_()).m_82400_(5.0);
        List<BlockEntity> entities = WorldUtil.getBlockEntitiesInAABB(this.f_58857_, box);
        for (BlockEntity entity : entities) {
            SuperBeaconBlockEntity beacon;
            if (!(entity instanceof SuperBeaconBlockEntity) || !(beacon = (SuperBeaconBlockEntity)entity).isConnected(this.m_58899_())) continue;
            return beacon;
        }
        return null;
    }

    @Nullable
    public BlockPos getConnectedBeacon() {
        return this.connectedBeacon;
    }

    public SuperBeaconBlockEntity getConnectedBeaconEntity() {
        BlockEntity entity;
        if (this.getConnectedBeacon() != null && (entity = this.f_58857_.m_7702_(this.getConnectedBeacon())) instanceof SuperBeaconBlockEntity) {
            SuperBeaconBlockEntity superBeacon = (SuperBeaconBlockEntity)entity;
            return superBeacon;
        }
        return null;
    }

    @Override
    public Component m_5446_() {
        return this.name != null ? this.name : Component.m_237115_((String)"container.witherstormmod.withered_support_beacon");
    }

    @Override
    public void m_142466_(CompoundTag tag) {
        super.m_142466_(tag);
        int colorIndex = tag.m_128451_("Color");
        if (colorIndex >= 0 && colorIndex < AbstractSuperBeaconBlockEntity.Color.values().length) {
            this.color = AbstractSuperBeaconBlockEntity.Color.values()[colorIndex];
        } else if (colorIndex == -1) {
            this.color = null;
        } else {
            LOGGER.warn("Read incorrect color index value {}", (Object)colorIndex);
        }
        this.connectedBeacon = tag.m_128441_("Connected") ? NbtUtils.m_129239_((CompoundTag)tag.m_128469_("Connected")) : null;
    }

    @Override
    protected void m_183515_(CompoundTag tag) {
        super.m_183515_(tag);
        if (this.color != null) {
            tag.m_128405_("Color", this.color.ordinal());
        } else {
            tag.m_128405_("Color", -1);
        }
        if (this.connectedBeacon != null) {
            tag.m_128365_("Connected", (Tag)NbtUtils.m_129224_((BlockPos)this.connectedBeacon));
        } else {
            tag.m_128473_("Connected");
        }
    }

    @Override
    public Set<MobEffect> getValidEffects() {
        if (this.color != null) {
            return this.color.getValidEffects();
        }
        return ImmutableSet.of();
    }

    @Override
    public void setShowWorkingArea(boolean flag) {
        SuperBeaconBlockEntity entity = this.getConnectedBeaconEntity();
        if (entity != null) {
            entity.setShowWorkingArea(flag);
        }
        super.setShowWorkingArea(flag);
    }

    public AbstractContainerMenu m_7208_(int id, Inventory inventory, Player player) {
        return BaseContainerBlockEntity.m_58629_((Player)player, (LockCode)this.lockKey, (Component)this.m_5446_()) ? new SuperSupportBeaconMenu(id, (Container)inventory, this.data, ContainerLevelAccess.m_39289_((Level)this.f_58857_, (BlockPos)this.m_58899_()), this::doPowerUp, this.getValidEffects()) : null;
    }

    @Nullable
    public BlockPos getBeamPos() {
        BlockPos pos;
        SuperBeaconBlockEntity connected = this.getConnectedBeaconEntity();
        boolean flag = false;
        if (connected != null && this.color != null && connected.getResummonTicks() > this.getResummonThreshold()) {
            flag = true;
        }
        if ((pos = this.getConnectedBeacon()) != null && flag) {
            return pos.m_6630_(3);
        }
        return pos;
    }

    @Override
    protected boolean shouldDoActivatedAnim() {
        SuperBeaconBlockEntity connected = this.getConnectedBeaconEntity();
        boolean flag = false;
        if (connected != null && this.color != null && connected.getResummonTicks() > this.getResummonThreshold()) {
            flag = true;
        }
        return super.shouldDoActivatedAnim() || flag;
    }

    @Override
    protected int getResummonThreshold() {
        return 60 + this.color.ordinal() * 40;
    }
}

