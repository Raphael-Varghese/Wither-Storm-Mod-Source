/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.Component$Serializer
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.Mth
 *  net.minecraft.world.LockCode
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.inventory.ContainerData
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.AABB
 *  net.minecraftforge.common.extensions.IForgeBlockEntity
 *  net.minecraftforge.network.PacketDistributor
 */
package nonamecrackers2.witherstormmod.common.blockentity;

import com.google.common.collect.ImmutableSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.LockCode;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.extensions.IForgeBlockEntity;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.common.blockentity.SuperSupportBeaconBlockEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.packet.RemoveDistantSuperBeaconMessage;
import nonamecrackers2.witherstormmod.common.packet.UpdateDistantSuperBeaconMessage;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;

public abstract class AbstractSuperBeaconBlockEntity
extends BlockEntity
implements MenuProvider {
    public static final Set<MobEffect> VALID_EFFECTS = ImmutableSet.of((Object)MobEffects.f_19600_, (Object)MobEffects.f_19606_, (Object)MobEffects.f_19598_, (Object)MobEffects.f_19603_, (Object)MobEffects.f_19596_, (Object)MobEffects.f_19611_, (Object[])new MobEffect[]{MobEffects.f_19605_, MobEffects.f_19618_});
    public static final int POWER_UP_ANIM_TIME = 80;
    public static final int POWER_UP_CLIMAX = 40;
    public static final int CONTAINER_DATA_SIZE = 4;
    public static final int SCAN_DIST = 5;
    public static final int COOLDOWN = 200;
    protected final Predicate<BlockEntity> isValidSupportBeacon = entity -> {
        if (!this.isActive()) return false;
        if (entity == null) return false;
        if (!(entity instanceof SuperSupportBeaconBlockEntity)) return false;
        SuperSupportBeaconBlockEntity support = (SuperSupportBeaconBlockEntity)((Object)entity);
        if (entity.m_58901_()) return false;
        if (support.getColor() == null) return false;
        return true;
    };
    protected final Random random = new Random();
    protected int ticks;
    protected int activationTime;
    protected int beamHeight;
    @Nullable
    protected MobEffect effect;
    protected boolean isActive;
    protected float activateAnim;
    protected float activateAnimO;
    protected LockCode lockKey = LockCode.f_19102_;
    @Nullable
    protected Component name;
    public int beaconLevel;
    protected int randomOffset;
    protected final ContainerData data = new ContainerData(){

        public void m_8050_(int id, int value) {
            switch (id) {
                case 0: {
                    AbstractSuperBeaconBlockEntity.this.beaconLevel = value;
                    break;
                }
                case 1: {
                    MobEffect effect = MobEffect.m_19453_((int)value);
                    if (effect != null && !AbstractSuperBeaconBlockEntity.this.getValidEffects().contains(effect)) break;
                    AbstractSuperBeaconBlockEntity.this.effect = effect;
                    break;
                }
                case 2: {
                    AbstractSuperBeaconBlockEntity.this.setShowWorkingArea(value == 1);
                    break;
                }
                case 3: {
                    AbstractSuperBeaconBlockEntity.this.effectSetCooldown = value;
                }
            }
        }

        public int m_6499_() {
            return 4;
        }

        public int m_6413_(int id) {
            switch (id) {
                case 0: {
                    return AbstractSuperBeaconBlockEntity.this.beaconLevel;
                }
                case 1: {
                    return MobEffect.m_19459_((MobEffect)AbstractSuperBeaconBlockEntity.this.effect);
                }
                case 2: {
                    return AbstractSuperBeaconBlockEntity.this.showWorkingArea() ? 1 : 0;
                }
                case 3: {
                    return AbstractSuperBeaconBlockEntity.this.effectSetCooldown;
                }
            }
            return 0;
        }
    };
    protected int poweringUpAnimation;
    protected boolean showWorkingArea;
    protected int effectSetCooldown;

    public AbstractSuperBeaconBlockEntity(BlockEntityType<? extends AbstractSuperBeaconBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.randomOffset = this.random.nextInt(100);
    }

    public void tick() {
        ++this.ticks;
        if (!this.f_58857_.f_46443_) {
            WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> ((Level)this.f_58857_).m_46472_()), (Object)new UpdateDistantSuperBeaconMessage(this.m_58899_(), this.getBeamColor(), this.isActive(), this.getBeamHeight(), this.getThickness(), this.getOuterThickness()));
        }
        this.tickActivationAnimation();
        this.activateAnimO = this.activateAnim;
        this.activateAnim = this.shouldDoActivatedAnim() ? (this.activateAnim += (1.0f - this.activateAnim) / 8.0f) : (this.activateAnim += (0.0f - this.activateAnim) / 8.0f);
        if (this.isActive() && !this.isPoweringUp() && (this.ticks + this.randomOffset) % 80 == 0) {
            this.f_58857_.m_5594_(null, this.m_58899_(), (SoundEvent)WitherStormModSoundEvents.WITHERED_BEACON_AMBIENT.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
        }
        if (this.poweringUpAnimation > 0) {
            --this.poweringUpAnimation;
            this.doPoweringUpAnimation();
        }
        if (!this.f_58857_.f_46443_ && this.effect != null && this.isActive()) {
            this.applyEffect((ServerLevel)this.f_58857_);
        }
        if (!this.f_58857_.f_46443_ && this.effectSetCooldown > 0) {
            --this.effectSetCooldown;
        }
    }

    protected abstract void applyEffect(ServerLevel var1);

    public void doActivationSequence() {
        this.beamHeight = 0;
        this.activationTime = 0;
        this.markUpdated();
    }

    protected void tickActivationAnimation() {
        if (this.hasReachedPowerUpClimax()) {
            ++this.activationTime;
            if (this.beamHeight < 1024) {
                this.beamHeight += this.activationTime / 2;
            }
        }
    }

    protected void doPoweringUpAnimation() {
    }

    public int getTicks() {
        return this.ticks;
    }

    public AABB getRenderBoundingBox() {
        return IForgeBlockEntity.INFINITE_EXTENT_AABB;
    }

    public int getBeamHeight() {
        return this.beamHeight;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public float getActivateAnimation(float partialTicks) {
        return Mth.m_14179_((float)partialTicks, (float)this.activateAnimO, (float)this.activateAnim);
    }

    protected void activate() {
        this.playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_BEACON_ACTIVATE.get(), 1.0f, 1.0f);
        this.doActivationSequence();
    }

    protected void deactivate() {
        this.playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_BEACON_DEACTIVATE.get(), 1.0f, 1.0f);
    }

    protected void playSound(SoundEvent event, float volume, float pitch) {
        this.f_58857_.m_5594_(null, this.m_58899_(), event, SoundSource.BLOCKS, volume, pitch + (this.random.nextFloat() - 0.5f) * 0.35f);
    }

    public void m_7651_() {
        super.m_7651_();
        if (this.isActive()) {
            this.deactivate();
        }
        if (!this.f_58857_.f_46443_) {
            WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> ((Level)this.f_58857_).m_46472_()), (Object)new RemoveDistantSuperBeaconMessage(this.m_58899_()));
        }
    }

    public abstract float getThickness();

    public abstract float getOuterThickness();

    public Packet<ClientGamePacketListener> m_58483_() {
        return ClientboundBlockEntityDataPacket.m_195640_((BlockEntity)this);
    }

    public CompoundTag m_5995_() {
        return this.m_187482_();
    }

    public void onLoad() {
        super.onLoad();
        this.f_58857_.getCapability(WitherStormModCapabilities.CHUNK_LOADING_BLOCK_ENTITIES).ifPresent(cap -> cap.add(this.m_58899_()));
    }

    public void m_142466_(CompoundTag tag) {
        super.m_142466_(tag);
        this.activationTime = tag.m_128451_("ActivationTime");
        this.beamHeight = tag.m_128451_("BeamHeight");
        this.isActive = tag.m_128471_("IsActive");
        if (tag.m_128425_("CustomName", 8)) {
            this.name = Component.Serializer.m_130701_((String)tag.m_128461_("CustomName"));
        }
        this.poweringUpAnimation = tag.m_128451_("PowerUpTime");
        this.activateAnim = tag.m_128457_("ActivationAnim");
        this.effect = MobEffect.m_19453_((int)tag.m_128451_("Primary"));
        this.showWorkingArea = tag.m_128471_("ShowWorkingArea");
        this.effectSetCooldown = tag.m_128451_("Cooldown");
        this.lockKey = LockCode.m_19111_((CompoundTag)tag);
    }

    protected void m_183515_(CompoundTag tag) {
        super.m_183515_(tag);
        tag.m_128405_("ActivationTime", this.activationTime);
        tag.m_128405_("BeamHeight", this.beamHeight);
        tag.m_128379_("IsActive", this.isActive);
        if (this.name != null) {
            tag.m_128359_("CustomName", Component.Serializer.m_130703_((Component)this.name));
        }
        tag.m_128405_("PowerUpTime", this.poweringUpAnimation);
        tag.m_128350_("ActivationAnim", this.activateAnim);
        tag.m_128405_("Primary", MobEffect.m_19459_((MobEffect)this.effect));
        tag.m_128379_("ShowWorkingArea", this.showWorkingArea);
        tag.m_128405_("Cooldown", this.effectSetCooldown);
        this.lockKey.m_19109_(tag);
    }

    protected void markUpdated() {
        this.m_6596_();
        this.f_58857_.m_7260_(this.m_58899_(), this.m_58900_(), this.m_58900_(), 3);
    }

    public void setCustomName(@Nullable Component component) {
        this.name = component;
    }

    public Component m_5446_() {
        return this.name != null ? this.name : Component.m_237115_((String)"container.witherstormmod.withered_beacon");
    }

    public boolean isPoweringUp() {
        return this.poweringUpAnimation > 0;
    }

    public boolean hasReachedPowerUpClimax() {
        return this.poweringUpAnimation <= 40;
    }

    public abstract Set<MobEffect> getValidEffects();

    public void doPowerUp(ServerPlayer player) {
        this.playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_BEACON_ACTIVATE.get(), 1.0f, 1.0f);
    }

    public void setShowWorkingArea(boolean flag) {
        this.showWorkingArea = flag;
        this.markUpdated();
    }

    public boolean showWorkingArea() {
        return this.showWorkingArea;
    }

    public abstract int[] getBeamColor();

    public int getCooldown() {
        return this.effectSetCooldown;
    }

    public void setCooldown(int cooldown) {
        this.effectSetCooldown = cooldown;
    }

    protected boolean shouldDoActivatedAnim() {
        return this.isActive() && this.hasReachedPowerUpClimax();
    }

    protected int getResummonThreshold() {
        return 60 + Color.values().length * 40;
    }

    public static enum Color {
        AQUA((Set<MobEffect>)ImmutableSet.of((Object)MobEffects.f_19611_, (Object)MobEffects.f_19608_, (Object)MobEffects.f_19598_), block -> block.m_204336_(WitherStormModBlockTags.AQUA_SUPPORT_BASE), 5, 255, 255),
        GREEN((Set<MobEffect>)ImmutableSet.of((Object)MobEffects.f_19596_, (Object)MobEffects.f_19593_, (Object)MobEffects.f_19603_), block -> block.m_204336_(WitherStormModBlockTags.GREEN_SUPPORT_BASE), 26, 255, 0),
        GRAY((Set<MobEffect>)ImmutableSet.of((Object)MobEffects.f_19600_, (Object)MobEffects.f_19609_, (Object)MobEffects.f_19607_), block -> block.m_204336_(WitherStormModBlockTags.GRAY_SUPPORT_BASE), 255, 255, 255),
        RED((Set<MobEffect>)ImmutableSet.of((Object)MobEffects.f_19606_, (Object)MobEffects.f_19605_, (Object)MobEffects.f_19618_), block -> block.m_204336_(WitherStormModBlockTags.RED_SUPPORT_BASE), 240, 39, 7);

        private final Set<MobEffect> validEffects;
        private final Predicate<BlockState> block;
        private final int r;
        private final int g;
        private final int b;

        private Color(Set<MobEffect> effects, Predicate<BlockState> block, int r, int g, int b) {
            this.validEffects = effects;
            this.block = block;
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public boolean isValidBaseBlock(BlockState block) {
            return this.block.test(block);
        }

        public int getRed() {
            return this.r;
        }

        public int getGreen() {
            return this.g;
        }

        public int getBlue() {
            return this.b;
        }

        public Set<MobEffect> getValidEffects() {
            return this.validEffects;
        }
    }
}

