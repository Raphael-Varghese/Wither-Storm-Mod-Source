/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.item.PrimedTnt
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  net.minecraft.world.phys.HitResult
 *  net.minecraftforge.network.NetworkHooks
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity;

import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import org.jetbrains.annotations.NotNull;

public class SuperTNTEntity
extends PrimedTnt {
    private static final EntityDataAccessor<Integer> START_FUSE = SynchedEntityData.m_135353_(SuperTNTEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    @Nullable
    private LivingEntity owner;
    private int startLife;

    public SuperTNTEntity(EntityType<? extends SuperTNTEntity> type, Level world) {
        super(type, world);
        this.m_32085_(320);
    }

    public SuperTNTEntity(Level world, double x, double y, double z, @Nullable LivingEntity owner) {
        this((EntityType<? extends SuperTNTEntity>)((EntityType)WitherStormModEntityTypes.SUPER_TNT.get()), world);
        this.m_6034_(x, y, z);
        double d0 = world.f_46441_.m_188500_() * 6.2831854820251465;
        this.m_20334_(-Math.sin(d0) * 0.02, 0.2, -Math.cos(d0) * 0.02);
        this.m_32085_(320);
        this.f_19854_ = x;
        this.f_19855_ = y;
        this.f_19856_ = z;
        this.owner = owner;
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(START_FUSE, (Object)0);
    }

    protected void m_7378_(@NotNull CompoundTag compound) {
        super.m_7378_(compound);
        if (compound.m_128441_("StartFuse")) {
            this.setStartFuse(compound.m_128451_("StartFuse"));
        }
    }

    protected void m_7380_(@NotNull CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128405_("StartFuse", this.getStartLife());
    }

    @Nullable
    public LivingEntity m_19749_() {
        return this.owner;
    }

    protected void m_32103_() {
        this.m_9236_().m_254877_((Entity)this, WitherStormModDamageTypes.superTntExplosion(this.m_9236_()), null, this.m_20185_(), this.m_20227_(0.0625), this.m_20189_(), 32.0f, false, Level.ExplosionInteraction.TNT);
    }

    public void m_32085_(int fuse) {
        super.m_32085_(fuse);
        this.setStartFuse(fuse);
    }

    public void setStartFuse(int fuse) {
        this.f_19804_.m_135381_(START_FUSE, (Object)fuse);
        this.startLife = fuse;
    }

    public int getStartLife() {
        return this.startLife;
    }

    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack((ItemLike)WitherStormModItems.SUPER_TNT.get());
    }

    @NotNull
    public Packet<ClientGamePacketListener> m_5654_() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }
}

