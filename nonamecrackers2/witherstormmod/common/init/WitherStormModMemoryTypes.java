/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.ai.memory.MemoryModuleType
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package nonamecrackers2.witherstormmod.common.init;

import java.util.Optional;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;

public class WitherStormModMemoryTypes {
    public static final DeferredRegister<MemoryModuleType<?>> MEMORY_MODULE_TYPES = DeferredRegister.create((IForgeRegistry)ForgeRegistries.MEMORY_MODULE_TYPES, (String)"witherstormmod");
    public static final RegistryObject<MemoryModuleType<WitherStormEntity>> NEAREST_WITHER_STORM = MEMORY_MODULE_TYPES.register("nearest_wither_storm", () -> new MemoryModuleType(Optional.empty()));
}

