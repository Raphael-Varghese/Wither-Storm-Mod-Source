/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.SimpleParticleType
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.packs.resources.PreparableReloadListener
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.levelgen.structure.BoundingBox
 *  net.minecraft.world.phys.AABB
 *  org.apache.commons.lang3.mutable.MutableBoolean
 *  org.apache.commons.lang3.mutable.MutableInt
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package nonamecrackers2.witherstormmod.common.world.tainting;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import nonamecrackers2.witherstormmod.common.entity.WitherSickened;
import nonamecrackers2.witherstormmod.common.init.WitherStormModParticleTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.resources.BlockTainting;
import nonamecrackers2.witherstormmod.common.resources.MobConversions;
import nonamecrackers2.witherstormmod.common.resources.taint.MobConversion;
import nonamecrackers2.witherstormmod.common.resources.taint.TaintRecipe;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldTainting {
    private static final Logger LOGGER = LogManager.getLogger((String)"witherstormmod/WorldTainting");
    @Nullable
    public static WorldTainting instance;
    private final BlockTainting blockTainting = new BlockTainting();
    private final MobConversions mobConversions = new MobConversions();

    private void addReloadListeners(Consumer<PreparableReloadListener> addListener) {
        addListener.accept((PreparableReloadListener)this.blockTainting);
        addListener.accept((PreparableReloadListener)this.mobConversions);
    }

    public Map<ResourceLocation, TaintRecipe> getBlockTaintingRecipes() {
        return this.blockTainting.getRecipes();
    }

    public Map<ResourceLocation, MobConversion> getMobConversions() {
        return this.mobConversions.getConversions();
    }

    public Optional<MobConversion> getMobConversionFor(EntityType<?> type) {
        return this.getMobConversions().values().stream().filter(c -> c.from().equals((Object)type)).findFirst();
    }

    @Nullable
    public EntityType<?> getOriginalTypeFromConvertedType(EntityType<?> converted) {
        return this.getMobConversions().values().stream().filter(c -> c.to().equals((Object)converted)).findFirst().orElseThrow().from();
    }

    public boolean canConvertBlock(BlockPos pos, Level level) {
        for (TaintRecipe recipe : this.getBlockTaintingRecipes().values()) {
            if (!recipe.canConvertBlock(level.m_8055_(pos))) continue;
            return true;
        }
        return false;
    }

    public int convertBlocks(BoundingBox box, Level level) {
        return this.convertBlocks(box, level, (TaintRecipe r) -> true);
    }

    public int convertBlocks(BoundingBox box, Level level, MobEffect effect) {
        return this.convertBlocks(box, level, (TaintRecipe r) -> r.effect() == effect);
    }

    public int convertBlocks(BoundingBox box, Level level, Potion potion) {
        return this.convertBlocks(box, level, (TaintRecipe r) -> r.canConvertWithPotion(potion));
    }

    public int convertBlocks(BoundingBox box, Level level, Predicate<TaintRecipe> canConvert) {
        MutableInt count = new MutableInt();
        BlockPos.m_121919_((BoundingBox)box).forEach(pos -> {
            for (TaintRecipe recipe : WorldTainting.getInstance().getBlockTaintingRecipes().values()) {
                BlockState state = level.m_8055_(pos);
                if (!canConvert.test(recipe) || !recipe.canConvertBlock(state)) continue;
                BlockState replacement = recipe.replacement();
                for (Property<?> property : recipe.propertiesToCopy()) {
                    replacement = WorldTainting.copyProperty(property, state, replacement);
                }
                if (!level.m_6933_(pos, replacement, 3, 0)) continue;
                count.increment();
                break;
            }
        });
        return count.getValue();
    }

    public boolean convertBlock(BlockPos pos, Level level) {
        for (TaintRecipe recipe : WorldTainting.getInstance().getBlockTaintingRecipes().values()) {
            BlockState state;
            if (!recipe.canConvertBlock(state = level.m_8055_(pos))) continue;
            BlockState replacement = recipe.replacement();
            for (Property<?> property : recipe.propertiesToCopy()) {
                replacement = WorldTainting.copyProperty(property, state, replacement);
            }
            return level.m_7731_(pos, replacement, 3);
        }
        return false;
    }

    private static <T extends Comparable<T>> BlockState copyProperty(Property<T> property, BlockState from, BlockState to) {
        if (from.m_61138_(property)) {
            return (BlockState)to.m_61124_(property, from.m_61143_(property));
        }
        return to;
    }

    public boolean canConvertMob(Entity entity, boolean fromWitherSickness) {
        if (entity instanceof Mob) {
            MutableBoolean result = new MutableBoolean();
            this.getMobConversionFor(entity.m_6095_()).ifPresent(conversion -> result.setValue(conversion.canBeConvertedFromWitherSickness() || !fromWitherSickness));
            return result.getValue();
        }
        return false;
    }

    public boolean convertMob(Mob mob, boolean fromWitherSickness) {
        EntityType type = mob.m_6095_();
        MutableBoolean result = new MutableBoolean();
        this.getMobConversionFor(type).ifPresent(conversion -> {
            if (conversion.canBeConvertedFromWitherSickness() || !fromWitherSickness) {
                CompoundTag data = mob.serializeNBT();
                try {
                    Mob converted = mob.m_21406_(conversion.to(), true);
                    if (converted != null) {
                        WorldTainting.copyExtraData(mob, converted);
                        if (converted instanceof WitherSickened) {
                            WitherSickened sickened = (WitherSickened)converted;
                            sickened.getData().setOriginal(type, data);
                            sickened.convertFrom(mob);
                        }
                        converted.m_216990_((SoundEvent)WitherStormModSoundEvents.MOB_INFECTED.get());
                        ServerLevel level = (ServerLevel)converted.m_9236_();
                        AABB box = converted.m_20191_();
                        level.m_8767_((ParticleOptions)((SimpleParticleType)WitherStormModParticleTypes.PHLEGM.get()), box.m_82399_().f_82479_, box.m_82399_().f_82480_, box.m_82399_().f_82481_, 10, box.m_82362_() / 2.0, box.m_82376_() / 2.0, box.m_82385_() / 2.0, 0.05);
                        result.setTrue();
                    }
                }
                catch (ClassCastException e) {
                    LOGGER.warn("Invalid mob conversion! Cannot convert to {} since it is not a valid mob. Please fix your mob conversion: {}", conversion.to(), conversion);
                }
            }
        });
        return result.getValue();
    }

    public static void copyExtraData(Mob from, Mob to) {
        to.f_20883_ = from.f_20883_;
        to.f_20885_ = from.f_20885_;
    }

    public static void initializeOrAddListeners(Consumer<PreparableReloadListener> addListener) {
        if (instance == null) {
            instance = new WorldTainting();
        }
        instance.addReloadListeners(addListener);
    }

    public static WorldTainting getInstance() {
        return Objects.requireNonNull(instance, "WorldTainting has not been initialized yet");
    }
}

