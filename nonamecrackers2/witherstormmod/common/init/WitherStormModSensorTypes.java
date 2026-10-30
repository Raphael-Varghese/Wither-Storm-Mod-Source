/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.ai.sensing.SensorType
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package nonamecrackers2.witherstormmod.common.init;

import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import nonamecrackers2.witherstormmod.common.entity.ai.sensing.WitherStormSensor;

public class WitherStormModSensorTypes {
    public static final DeferredRegister<SensorType<?>> SENSOR_TYPES = DeferredRegister.create((IForgeRegistry)ForgeRegistries.SENSOR_TYPES, (String)"witherstormmod");
    public static final RegistryObject<SensorType<WitherStormSensor>> WITHER_STORM_SENSOR = SENSOR_TYPES.register("wither_storm", () -> new SensorType(WitherStormSensor::new));
}

