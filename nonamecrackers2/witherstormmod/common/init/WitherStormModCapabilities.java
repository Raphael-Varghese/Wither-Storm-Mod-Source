/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Direction
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.common.capabilities.Capability
 *  net.minecraftforge.common.capabilities.CapabilityManager
 *  net.minecraftforge.common.capabilities.CapabilityToken
 *  net.minecraftforge.common.capabilities.ICapabilityProvider
 *  net.minecraftforge.common.capabilities.ICapabilitySerializable
 *  net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
 *  net.minecraftforge.common.util.LazyOptional
 *  net.minecraftforge.event.AttachCapabilitiesEvent
 */
package nonamecrackers2.witherstormmod.common.init;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.capability.ChunkLoadingBlockEntities;
import nonamecrackers2.witherstormmod.common.capability.EntityCapability;
import nonamecrackers2.witherstormmod.common.capability.PlayerWitherStormData;
import nonamecrackers2.witherstormmod.common.capability.WitherSicknessTracker;
import nonamecrackers2.witherstormmod.common.capability.WitherStormAutoSpawner;
import nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager;
import nonamecrackers2.witherstormmod.common.capability.WitherStormModChunkLoader;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherSickened;

public class WitherStormModCapabilities {
    public static final Capability<WitherStormModChunkLoader> CHUNK_LOADER = CapabilityManager.get((CapabilityToken)new CapabilityToken<WitherStormModChunkLoader>(){});
    public static final Capability<WitherSicknessTracker> WITHER_SICKNESS_TRACKER = CapabilityManager.get((CapabilityToken)new CapabilityToken<WitherSicknessTracker>(){});
    public static final Capability<PlayerWitherStormData> PLAYER_WITHER_STORM_DATA = CapabilityManager.get((CapabilityToken)new CapabilityToken<PlayerWitherStormData>(){});
    public static final Capability<WitherStormBowelsManager> BOWELS_MANAGER = CapabilityManager.get((CapabilityToken)new CapabilityToken<WitherStormBowelsManager>(){});
    public static final Capability<ChunkLoadingBlockEntities> CHUNK_LOADING_BLOCK_ENTITIES = CapabilityManager.get((CapabilityToken)new CapabilityToken<ChunkLoadingBlockEntities>(){});
    public static final Capability<WitherStormAutoSpawner> WITHER_STORM_AUTO_SPAWNER = CapabilityManager.get((CapabilityToken)new CapabilityToken<WitherStormAutoSpawner>(){});

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(WitherSicknessTracker.class);
        event.register(PlayerWitherStormData.class);
        event.register(WitherStormBowelsManager.class);
        event.register(ChunkLoadingBlockEntities.class);
        event.register(WitherStormModChunkLoader.class);
        event.register(WitherStormAutoSpawner.class);
    }

    public static void attachWorldCapabilities(AttachCapabilitiesEvent<Level> event) {
        Level world = (Level)event.getObject();
        if (!world.f_46443_) {
            final LazyOptional chunkLoadingBlockEntities = LazyOptional.of(() -> new ChunkLoadingBlockEntities((ServerLevel)world));
            event.addCapability(new ResourceLocation("witherstormmod", "chunk_loading_block_entities"), (ICapabilityProvider)new ICapabilitySerializable<CompoundTag>(){

                public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
                    return cap == CHUNK_LOADING_BLOCK_ENTITIES ? chunkLoadingBlockEntities.cast() : LazyOptional.empty();
                }

                public CompoundTag serializeNBT() {
                    return ((ChunkLoadingBlockEntities)chunkLoadingBlockEntities.orElse(null)).write();
                }

                public void deserializeNBT(CompoundTag nbt) {
                    ((ChunkLoadingBlockEntities)chunkLoadingBlockEntities.orElse(null)).read(nbt);
                }
            });
            event.addListener(() -> ((LazyOptional)chunkLoadingBlockEntities).invalidate());
            final LazyOptional chunkLoader = LazyOptional.of(() -> new WitherStormModChunkLoader((ServerLevel)world));
            event.addCapability(new ResourceLocation("witherstormmod", "chunk_loader"), (ICapabilityProvider)new ICapabilitySerializable<Tag>(){

                public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
                    return capability == CHUNK_LOADER ? chunkLoader.cast() : LazyOptional.empty();
                }

                public Tag serializeNBT() {
                    return ((WitherStormModChunkLoader)chunkLoader.orElse(null)).write();
                }

                public void deserializeNBT(Tag nbt) {
                    ((WitherStormModChunkLoader)chunkLoader.orElse(null)).read((CompoundTag)nbt);
                }
            });
            event.addListener(() -> ((LazyOptional)chunkLoader).invalidate());
            if (world.m_46472_().m_135782_().equals((Object)WitherStormMod.bowelsLocation())) {
                final LazyOptional bowelsManager = LazyOptional.of(() -> new WitherStormBowelsManager((ServerLevel)world));
                event.addCapability(new ResourceLocation("witherstormmod", "bowels_manager"), (ICapabilityProvider)new ICapabilitySerializable<Tag>(){

                    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
                        return capability == BOWELS_MANAGER ? bowelsManager.cast() : LazyOptional.empty();
                    }

                    public Tag serializeNBT() {
                        return ((WitherStormBowelsManager)bowelsManager.orElse(null)).write();
                    }

                    public void deserializeNBT(Tag nbt) {
                        ((WitherStormBowelsManager)bowelsManager.orElse(null)).read((CompoundTag)nbt);
                    }
                });
                event.addListener(() -> ((LazyOptional)bowelsManager).invalidate());
            } else if (world.m_46472_().equals((Object)Level.f_46428_)) {
                final LazyOptional autoSpawner = LazyOptional.of(() -> new WitherStormAutoSpawner((ServerLevel)world));
                event.addCapability(new ResourceLocation("witherstormmod", "auto_spawner"), (ICapabilityProvider)new ICapabilitySerializable<CompoundTag>(){

                    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
                        return cap == WITHER_STORM_AUTO_SPAWNER ? autoSpawner.cast() : LazyOptional.empty();
                    }

                    public CompoundTag serializeNBT() {
                        return ((WitherStormAutoSpawner)autoSpawner.orElse(null)).write();
                    }

                    public void deserializeNBT(CompoundTag nbt) {
                        ((WitherStormAutoSpawner)autoSpawner.orElse(null)).read(nbt);
                    }
                });
                event.addListener(() -> ((LazyOptional)autoSpawner).invalidate());
            }
        }
    }

    public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = (Entity)event.getObject();
        if (!(entity instanceof WitherSickened) && entity instanceof LivingEntity && ((Boolean)WitherStormModConfig.SERVER.witherSicknessEnabled.get()).booleanValue()) {
            LivingEntity living = (LivingEntity)entity;
            LazyOptional tracker = LazyOptional.of(() -> new WitherSicknessTracker(living));
            event.addCapability(new ResourceLocation("witherstormmod", "wither_sickness_tracker"), new EntityCapability.Serializable(WITHER_SICKNESS_TRACKER, tracker));
        }
        if (entity instanceof Player) {
            Player player = (Player)entity;
            LazyOptional data = LazyOptional.of(() -> new PlayerWitherStormData(player));
            event.addCapability(new ResourceLocation("witherstormmod", "wither_storm_data"), new EntityCapability.Serializable(PLAYER_WITHER_STORM_DATA, data));
        }
    }
}

