/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.blockentity;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
import nonamecrackers2.witherstormmod.common.util.IFormidibomb;

public class FormidibombBlockEntity
extends BlockEntity
implements IFormidibomb {
    @Nullable
    private LivingEntity owner;
    private int fuse;
    private int startFuse;

    public FormidibombBlockEntity(BlockPos pos, BlockState state) {
        super((BlockEntityType)WitherStormModBlockEntityTypes.FORMIDIBOMB.get(), pos, state);
        this.startFuse = this.fuse = 1200;
    }

    protected void m_183515_(CompoundTag tag) {
        super.m_183515_(tag);
        tag.m_128405_("Fuse", this.getFuseLife());
        tag.m_128405_("StartFuse", this.getStartFuse());
    }

    public void m_142466_(CompoundTag compound) {
        super.m_142466_(compound);
        if (compound.m_128441_("Fuse")) {
            this.setLifeFuse(compound.m_128451_("Fuse"));
        }
        if (compound.m_128441_("StartFuse")) {
            this.setStartFuse(compound.m_128451_("StartFuse"));
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, FormidibombBlockEntity blockEntity) {
        if (blockEntity.getStartFuse() > 0) {
            FormidibombEntity entity;
            if (((Boolean)WitherStormModConfig.SERVER.formidibombFuseEnabled.get()).booleanValue()) {
                --blockEntity.fuse;
            }
            if (blockEntity.getFuseLife() <= 0) {
                level.m_7731_(blockEntity.m_58899_(), Blocks.f_50016_.m_49966_(), 3);
                level.m_46747_(blockEntity.m_58899_());
            } else if (blockEntity.getFuseLife() <= blockEntity.getStartFuse() / 4 && level.m_7967_((Entity)(entity = new FormidibombEntity(level, (double)blockEntity.m_58899_().m_123341_() + 0.5, blockEntity.m_58899_().m_123342_(), (double)blockEntity.m_58899_().m_123343_() + 0.5, blockEntity.getFormidibombOwner(), blockEntity, blockEntity.m_58900_())))) {
                level.m_7731_(blockEntity.m_58899_(), Blocks.f_50016_.m_49966_(), 3);
                level.m_46747_(blockEntity.m_58899_());
                level.m_6263_(null, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), SoundEvents.f_12512_, SoundSource.BLOCKS, 1.0f, 1.0f);
            }
        }
    }

    @Override
    public boolean isStillAlive() {
        return !this.m_58901_();
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.m_195640_((BlockEntity)this);
    }

    public CompoundTag m_5995_() {
        return this.m_187482_();
    }

    @Override
    public int getFuseLife() {
        return this.fuse;
    }

    @Override
    public void setLifeFuse(int fuse) {
        this.fuse = fuse;
        this.setStartFuse(fuse);
    }

    @Override
    public int getStartFuse() {
        return this.startFuse;
    }

    @Override
    public void setStartFuse(int fuse) {
        this.startFuse = fuse;
    }

    @Override
    @Nullable
    public LivingEntity getFormidibombOwner() {
        return this.owner;
    }

    @Override
    public void setFormidibombOwner(@Nullable LivingEntity entity) {
        this.owner = entity;
    }

    @Override
    public Vec3 getPosition() {
        return new Vec3((double)this.m_58899_().m_123341_(), (double)this.m_58899_().m_123342_(), (double)this.m_58899_().m_123343_());
    }
}

