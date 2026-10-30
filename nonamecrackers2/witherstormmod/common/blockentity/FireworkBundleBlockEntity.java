/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.Util
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.projectile.FireworkRocketEntity
 *  net.minecraft.world.item.DyeColor
 *  net.minecraft.world.item.FireworkRocketItem$Shape
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.blockentity;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;

public class FireworkBundleBlockEntity
extends BlockEntity {
    private static final int FUSE_TIME = 100;
    private static final int LAUNCH_TIME = 500;
    private final RandomSource random = RandomSource.m_216327_();
    private int fuse;
    private int launchDuration;

    public FireworkBundleBlockEntity(BlockPos pos, BlockState state) {
        super((BlockEntityType)WitherStormModBlockEntityTypes.FIREWORK_BUNDLE.get(), pos, state);
    }

    protected void m_183515_(CompoundTag tag) {
        super.m_183515_(tag);
        tag.m_128405_("Fuse", this.fuse);
        tag.m_128405_("LaunchDuration", this.launchDuration);
    }

    public void m_142466_(CompoundTag tag) {
        super.m_142466_(tag);
        this.fuse = tag.m_128451_("Fuse");
        this.launchDuration = tag.m_128451_("LaunchDuration");
    }

    public void beginFuse() {
        if (this.fuse == 0 && this.launchDuration == 0) {
            this.fuse = 100;
            this.f_58857_.m_247517_(null, this.m_58899_(), SoundEvents.f_12512_, SoundSource.BLOCKS);
        }
    }

    public boolean isActivated() {
        return this.fuse > 0 || this.launchDuration > 0;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, FireworkBundleBlockEntity entity) {
        if (entity.fuse > 0) {
            if (level instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel)level;
                Vec3 vec = Vec3.m_82512_((Vec3i)pos);
                serverLevel.m_8767_((ParticleOptions)ParticleTypes.f_123762_, vec.f_82479_, vec.f_82480_, vec.f_82481_, 1, 0.0, 0.0, 0.0, 0.0);
            }
            --entity.fuse;
            if (entity.fuse == 0) {
                entity.launchDuration = 500;
            }
        }
        if (entity.launchDuration > 0) {
            --entity.launchDuration;
            if (entity.launchDuration == 0) {
                level.m_7471_(pos, false);
            } else if (entity.random.m_188503_(3) == 0) {
                Vec3 vec = Vec3.m_82512_((Vec3i)pos).m_82520_(entity.random.m_188500_() - 0.5, 0.6, entity.random.m_188500_() - 0.5);
                ItemStack stack = FireworkBundleBlockEntity.createRandomFireworkItem(entity.random);
                FireworkRocketEntity rocket = new FireworkRocketEntity(level, vec.f_82479_, vec.f_82480_, vec.f_82481_, stack);
                Vec3 delta = rocket.m_20184_().m_82520_((entity.random.m_188500_() - 0.5) * 0.05, 0.0, (entity.random.m_188500_() - 0.5) * 0.05);
                rocket.m_20256_(delta);
                level.m_7967_((Entity)rocket);
            }
        }
    }

    private static ItemStack createRandomFireworkItem(RandomSource random) {
        ItemStack stack = new ItemStack((ItemLike)Items.f_42688_);
        ListTag list = new ListTag();
        CompoundTag tag = new CompoundTag();
        tag.m_128379_("Flicker", random.m_188499_());
        tag.m_128379_("Trail", random.m_188499_());
        ArrayList colors = Lists.newArrayList();
        int size = random.m_188503_(5) + 1;
        for (int i = 0; i < size; ++i) {
            DyeColor color = (DyeColor)Util.m_214670_((Object[])DyeColor.values(), (RandomSource)random);
            colors.add(color.m_41070_());
        }
        tag.m_128408_("Colors", (List)colors);
        tag.m_128344_("Type", (byte)((FireworkRocketItem.Shape)Util.m_214670_((Object[])FireworkRocketItem.Shape.values(), (RandomSource)random)).m_41236_());
        list.add((Object)tag);
        CompoundTag fireworks = stack.m_41698_("Fireworks");
        fireworks.m_128344_("Flight", (byte)(random.m_188503_(1) + 2));
        fireworks.m_128365_("Explosions", (Tag)list);
        return stack;
    }
}

