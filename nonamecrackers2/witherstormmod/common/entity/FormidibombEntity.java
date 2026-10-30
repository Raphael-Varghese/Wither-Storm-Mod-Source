/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.HolderGetter
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtUtils
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.network.syncher.EntityDataSerializers
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.Mth
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.item.PrimedTnt
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.enchantment.ProtectionEnchantment
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.EntityBasedExplosionDamageCalculator
 *  net.minecraft.world.level.Explosion
 *  net.minecraft.world.level.Explosion$BlockInteraction
 *  net.minecraft.world.level.ExplosionDamageCalculator
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.BaseFireBlock
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.level.storage.loot.LootParams$Builder
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParams
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.network.NetworkHooks
 *  net.minecraftforge.network.PacketDistributor
 *  net.minecraftforge.network.PacketDistributor$TargetPoint
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.EntityBasedExplosionDamageCalculator;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
import nonamecrackers2.witherstormmod.common.init.WitherStormModDamageTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.packet.FormidibombExplosionMessage;
import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
import nonamecrackers2.witherstormmod.common.util.IFormidibomb;
import org.jetbrains.annotations.NotNull;

public class FormidibombEntity
extends PrimedTnt
implements IFormidibomb {
    private static final EntityDataAccessor<Optional<BlockState>> BLOCK_STATE = SynchedEntityData.m_135353_(FormidibombEntity.class, (EntityDataSerializer)EntityDataSerializers.f_268618_);
    private static final EntityDataAccessor<Integer> START_FUSE = SynchedEntityData.m_135353_(FormidibombEntity.class, (EntityDataSerializer)EntityDataSerializers.f_135028_);
    @Nullable
    private LivingEntity owner;
    private int airTime;

    public FormidibombEntity(EntityType<? extends FormidibombEntity> type, Level world) {
        super(type, world);
        this.initiateFuse(1200);
    }

    public FormidibombEntity(Level world, double x, double y, double z, @Nullable LivingEntity owner, @Nullable IFormidibomb previous, @Nullable BlockState state) {
        this((EntityType<? extends FormidibombEntity>)((EntityType)WitherStormModEntityTypes.FORMIDIBOMB.get()), world);
        this.m_6034_(x, y, z);
        double d0 = world.f_46441_.m_188500_() * 6.2831854820251465;
        this.m_20334_(-Math.sin(d0) * 0.02, 0.2, -Math.cos(d0) * 0.02);
        this.initiateFuse(1200);
        this.f_19854_ = x;
        this.f_19855_ = y;
        this.f_19856_ = z;
        this.owner = owner;
        if (previous != null && previous.getStartFuse() > 0) {
            this.copyFrom(previous);
        }
        this.setBlockState(state);
    }

    protected void m_8097_() {
        super.m_8097_();
        this.f_19804_.m_135372_(BLOCK_STATE, Optional.empty());
        this.f_19804_.m_135372_(START_FUSE, (Object)0);
    }

    protected void m_7378_(@NotNull CompoundTag compound) {
        super.m_7378_(compound);
        if (compound.m_128441_("StartFuse")) {
            this.setStartFuse(compound.m_128451_("StartFuse"));
        }
        if (compound.m_128441_("State")) {
            this.setBlockState(NbtUtils.m_247651_((HolderGetter)this.m_9236_().m_246945_(Registries.f_256747_), (CompoundTag)compound.m_128469_("State")));
        }
    }

    protected void m_7380_(@NotNull CompoundTag compound) {
        super.m_7380_(compound);
        compound.m_128405_("StartFuse", this.getStartFuse());
        compound.m_128365_("State", (Tag)NbtUtils.m_129202_((BlockState)this.getBlockState()));
    }

    public void m_8119_() {
        float radius;
        float speed;
        super.m_8119_();
        int startFuse = this.getStartFuse();
        int currentFuse = this.m_32100_();
        float fuseProgress = 1.0f - (float)currentFuse / (float)startFuse;
        float initalRadius = 3.0f;
        float initalSpeed = 0.1f;
        if (fuseProgress < 0.5f) {
            speed = initalSpeed * (1.0f - 2.0f * fuseProgress);
            radius = initalRadius * (1.0f - fuseProgress);
        } else {
            speed = initalSpeed * (8.0f * (fuseProgress - 0.5f));
            radius = 1.5f;
        }
        for (int i = 0; i < 6; ++i) {
            double x = ((double)this.f_19796_.m_188501_() * 2.0 - 1.0) * (double)radius;
            double y = ((double)this.f_19796_.m_188501_() * 2.0 - 1.0) * (double)radius;
            double z = ((double)this.f_19796_.m_188501_() * 2.0 - 1.0) * (double)radius;
            Vec3 start = this.m_20182_().m_82520_(x, y, z);
            Vec3 delta = new Vec3(x, y, z).m_82490_((double)speed);
            if (fuseProgress > 0.5f) {
                delta = delta.m_82548_();
            }
            this.m_9236_().m_7106_((ParticleOptions)WitherStormModParticleTypes.COMMAND_BLOCK.get(), start.f_82479_, start.f_82480_ + 0.5, start.f_82481_, -delta.f_82479_, -delta.f_82480_, -delta.f_82481_);
        }
        this.airTime = this.m_20096_() ? 0 : ++this.airTime;
    }

    @Override
    public int getFuseLife() {
        return this.m_32100_();
    }

    @Override
    public void setLifeFuse(int fuse) {
        this.m_32085_(fuse);
    }

    public void initiateFuse(int fuse) {
        this.setLifeFuse(fuse);
        this.setStartFuse(fuse);
    }

    @Override
    public int getStartFuse() {
        return (Integer)this.f_19804_.m_135370_(START_FUSE);
    }

    @Override
    public void setStartFuse(int fuse) {
        this.f_19804_.m_135381_(START_FUSE, (Object)fuse);
    }

    @Override
    public LivingEntity getFormidibombOwner() {
        return this.owner;
    }

    @Override
    public void setFormidibombOwner(LivingEntity entity) {
        this.owner = entity;
    }

    public BlockState getBlockState() {
        return ((Optional)this.f_19804_.m_135370_(BLOCK_STATE)).orElse(((Block)WitherStormModBlocks.FORMIDIBOMB.get()).m_49966_());
    }

    public void setBlockState(@Nullable BlockState state) {
        this.f_19804_.m_135381_(BLOCK_STATE, Optional.ofNullable(state));
    }

    public ItemStack getPickedResult(HitResult target) {
        ItemStack stack = new ItemStack((ItemLike)WitherStormModItems.FORMIDIBOMB.get());
        CompoundTag compound = stack.m_41784_();
        compound.m_128405_("Fuse", this.m_32100_());
        compound.m_128405_("StartFuse", this.getStartFuse());
        return stack;
    }

    protected void m_32103_() {
        FormidibombEntity.explode(this.m_9236_(), (Entity)this.m_19749_(), 48 + this.m_9236_().f_46441_.m_188503_(9), 3, this.m_20185_(), this.m_20186_(), this.m_20189_());
        WitherStormModPacketHandlers.MAIN.send(PacketDistributor.NEAR.with(PacketDistributor.TargetPoint.p((double)this.m_20185_(), (double)this.m_20186_(), (double)this.m_20189_(), (double)100.0, (ResourceKey)this.m_9236_().m_46472_())), (Object)new ShakeScreenMessage(480.0f, 24.0f));
    }

    @Override
    public Vec3 getPosition() {
        return this.m_20182_();
    }

    @Override
    public boolean isStillAlive() {
        return this.m_6084_();
    }

    public int getAirTime() {
        return this.airTime;
    }

    @NotNull
    public Packet<ClientGamePacketListener> m_5654_() {
        return NetworkHooks.getEntitySpawningPacket((Entity)this);
    }

    public static void explode(Level world, @Nullable Entity entity, int radius, int squish, double x, double y, double z) {
        world.m_46473_().m_6180_("formidibomb_explosion");
        ExplosionDamageCalculator explosionContext = entity == null ? new ExplosionDamageCalculator() : new EntityBasedExplosionDamageCalculator(entity);
        Explosion explosion = new Explosion(world, entity, FormidibombEntity.formidibomb(world.m_9598_(), entity), explosionContext, x, y, z, (float)radius, true, Explosion.BlockInteraction.DESTROY);
        if (world.f_46443_) {
            int poofCount = Math.max(4500, world.f_46441_.m_188503_(5001));
            for (int i = 0; i < poofCount; ++i) {
                double speedX = world.f_46441_.m_188583_() * 0.5;
                double speedY = world.f_46441_.m_188583_() * 0.5;
                double speedZ = world.f_46441_.m_188583_() * 0.5;
                double deltaX = world.f_46441_.m_188583_() * 4.0;
                double deltaY = world.f_46441_.m_188583_() * 4.0;
                double deltaZ = world.f_46441_.m_188583_() * 4.0;
                world.m_7106_((ParticleOptions)ParticleTypes.f_123759_, x + deltaX, y + deltaY, z + deltaZ, speedX, speedY, speedZ);
            }
            int explosionCount = Math.max(50, world.f_46441_.m_188503_(76));
            for (int i = 0; i < explosionCount; ++i) {
                double deltaX = world.f_46441_.m_188583_() * 12.0;
                double deltaY = world.f_46441_.m_188583_() * 12.0;
                double deltaZ = world.f_46441_.m_188583_() * 12.0;
                world.m_7106_((ParticleOptions)ParticleTypes.f_123812_, x + deltaX, y + deltaY, z + deltaZ, 0.0, 0.0, 0.0);
            }
        }
        if (world.f_46443_) {
            SoundEvent event = (SoundEvent)WitherStormModSoundEvents.FORMIDIBOMB_EXPLOSION.get();
            if (!((Boolean)WitherStormModConfig.CLIENT.earRingingEffects.get()).booleanValue()) {
                event = (SoundEvent)WitherStormModSoundEvents.FORMIDIBOMB_EXPLOSION_QUIET.get();
            }
            world.m_7785_(x, y, z, event, SoundSource.BLOCKS, 16.0f, 1.0f, false);
        }
        world.m_6263_(null, x, y, z, (SoundEvent)WitherStormModSoundEvents.TREMBLE.get(), SoundSource.BLOCKS, 32.0f, 1.0f);
        if (!world.f_46443_) {
            FormidibombExplosionMessage message = new FormidibombExplosionMessage(entity, x, y, z, radius, squish);
            WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> ((Level)world).m_46472_()), (Object)message);
            float diameter = (float)radius * 2.0f;
            int minX = Mth.m_14107_((double)(x - (double)diameter - 1.0));
            int maxX = Mth.m_14107_((double)(x + (double)diameter + 1.0));
            int minY = Mth.m_14107_((double)(y - (double)diameter - 1.0));
            int maxY = Mth.m_14107_((double)(y + (double)diameter + 1.0));
            int minZ = Mth.m_14107_((double)(z - (double)diameter - 1.0));
            int maxZ = Mth.m_14107_((double)(z + (double)diameter + 1.0));
            AABB explosionArea = new AABB((double)minX, (double)minY, (double)minZ, (double)maxX, (double)maxY, (double)maxZ);
            List entities = world.m_45976_(WitherStormEntity.class, explosionArea.m_82400_(200.0));
            for (WitherStormEntity storm : entities) {
                if (!storm.canBeFormidibombed(true)) continue;
                storm.explode();
            }
            ObjectArrayList items = new ObjectArrayList();
            for (int i = -radius; i < radius; ++i) {
                for (int j = -radius; j < radius; ++j) {
                    for (int k = -radius; k < radius; ++k) {
                        if (!(Mth.m_14116_((float)(i * i + j * j * squish + k * k)) < (float)radius)) continue;
                        int random = world.f_46441_.m_188503_(2);
                        for (int l = -random; l <= random; ++l) {
                            BlockPos pos = BlockPos.m_274561_((double)((double)i + x), (double)((double)j + y - (double)l), (double)((double)k + z));
                            BlockState state = world.m_8055_(pos);
                            FluidState fluid = world.m_6425_(pos);
                            if (state.m_60713_(Blocks.f_50016_)) continue;
                            BlockPos imutable = pos.m_7949_();
                            if (state.canDropFromExplosion((BlockGetter)world, pos, explosion) && world instanceof ServerLevel) {
                                BlockEntity tile = state.m_155947_() ? world.m_7702_(pos) : null;
                                LootParams.Builder context = new LootParams.Builder((ServerLevel)world).m_287286_(LootContextParams.f_81460_, (Object)Vec3.m_82512_((Vec3i)pos)).m_287286_(LootContextParams.f_81463_, (Object)ItemStack.f_41583_).m_287289_(LootContextParams.f_81462_, (Object)tile).m_287289_(LootContextParams.f_81455_, (Object)entity).m_287286_(LootContextParams.f_81464_, (Object)Float.valueOf(radius));
                                state.m_287290_(context).forEach(item -> FormidibombEntity.addBlockDrops((ObjectArrayList<Pair<ItemStack, BlockPos>>)items, item, imutable));
                            }
                            float resistance = (float)radius * (0.7f + world.f_46441_.m_188501_() * 0.6f);
                            Optional blockResistance = explosionContext.m_6617_(explosion, (BlockGetter)world, pos, state, fluid);
                            if (blockResistance.isPresent()) {
                                resistance -= (((Float)blockResistance.get()).floatValue() + 0.3f) * ((Boolean)WitherStormModConfig.SERVER.lowerBlockResistance.get() != false ? 0.01f : 0.3f);
                            }
                            if (!(resistance > 0.0f) || !explosionContext.m_6714_(explosion, (BlockGetter)world, pos, state, resistance)) continue;
                            state.onBlockExploded(world, pos, explosion);
                            if (world.f_46441_.m_188503_(3) != 0 || !world.m_8055_(pos).m_60713_(Blocks.f_50016_) || !world.m_8055_(pos.m_7495_()).m_60804_((BlockGetter)world, pos.m_7495_())) continue;
                            world.m_46597_(pos, BaseFireBlock.m_49245_((BlockGetter)world, (BlockPos)pos));
                        }
                    }
                }
            }
            List entitiesToExplode = world.m_45933_(null, explosionArea);
            ForgeEventFactory.onExplosionDetonate((Level)world, (Explosion)explosion, (List)entitiesToExplode, (double)diameter);
            Vec3 vector = new Vec3(x, y, z);
            for (Entity toExplode : entitiesToExplode) {
                double relativeZ;
                double relativeY;
                double relativeX;
                double sqrtPos;
                double distance;
                if (toExplode.m_6128_() || !((distance = Math.sqrt(toExplode.m_20238_(vector)) / (double)diameter) <= 1.0) || (sqrtPos = Math.sqrt((relativeX = toExplode.m_20185_() - x) * relativeX + (relativeY = (toExplode instanceof PrimedTnt ? toExplode.m_20186_() : toExplode.m_20188_()) - y) * relativeY + (relativeZ = toExplode.m_20189_() - z) * relativeZ)) == 0.0) continue;
                relativeX /= sqrtPos;
                relativeY /= sqrtPos;
                relativeZ /= sqrtPos;
                double seenPercent = Explosion.m_46064_((Vec3)vector, (Entity)toExplode);
                double explosionPower = (1.0 - distance) * seenPercent;
                toExplode.m_6469_(FormidibombEntity.formidibomb(world.m_9598_(), entity), (float)((int)((explosionPower * explosionPower + explosionPower) / 2.0 * 7.0 * (double)diameter + 1.0)));
                double explosionPowerModifiable = explosionPower;
                if (toExplode instanceof LivingEntity) {
                    explosionPowerModifiable = ProtectionEnchantment.m_45135_((LivingEntity)((LivingEntity)toExplode), (double)explosionPower);
                }
                toExplode.m_20256_(toExplode.m_20184_().m_82520_(relativeX * (explosionPowerModifiable + (double)radius), relativeY * (explosionPowerModifiable + (double)radius), relativeZ * (explosionPowerModifiable + (double)radius)));
            }
            for (Pair pair : items) {
                Block.m_49840_((Level)world, (BlockPos)((BlockPos)pair.getSecond()), (ItemStack)((ItemStack)pair.getFirst()));
            }
        }
        world.m_46473_().m_7238_();
    }

    private static void addBlockDrops(ObjectArrayList<Pair<ItemStack, BlockPos>> items, ItemStack stack, BlockPos pos) {
        int i = items.size();
        for (int j = 0; j < i; ++j) {
            Pair pair = (Pair)items.get(j);
            ItemStack pairStack = (ItemStack)pair.getFirst();
            if (!ItemEntity.m_32026_((ItemStack)pairStack, (ItemStack)stack)) continue;
            ItemStack merged = ItemEntity.m_32029_((ItemStack)pairStack, (ItemStack)stack, (int)16);
            items.set(j, (Object)Pair.of((Object)merged, (Object)((BlockPos)pair.getSecond())));
            if (!stack.m_41619_()) continue;
            return;
        }
        items.add((Object)Pair.of((Object)stack, (Object)pos));
    }

    public static DamageSource formidibomb(RegistryAccess access, @Nullable Entity entity) {
        return entity != null ? WitherStormModDamageTypes.source(access, WitherStormModDamageTypes.PLAYER_FORMIDIBOMB, entity) : WitherStormModDamageTypes.source(access, WitherStormModDamageTypes.FORMIDIBOMB);
    }
}

