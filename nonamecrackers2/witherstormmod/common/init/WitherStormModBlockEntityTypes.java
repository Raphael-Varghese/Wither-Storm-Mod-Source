/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.entity.BlockEntityType$Builder
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package nonamecrackers2.witherstormmod.common.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import nonamecrackers2.witherstormmod.common.blockentity.FireworkBundleBlockEntity;
import nonamecrackers2.witherstormmod.common.blockentity.FormidibombBlockEntity;
import nonamecrackers2.witherstormmod.common.blockentity.SuperBeaconBlockEntity;
import nonamecrackers2.witherstormmod.common.blockentity.SuperSupportBeaconBlockEntity;
import nonamecrackers2.witherstormmod.common.blockentity.WitheredPhlegmBlockEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;

public class WitherStormModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create((IForgeRegistry)ForgeRegistries.BLOCK_ENTITY_TYPES, (String)"witherstormmod");
    public static final RegistryObject<BlockEntityType<FormidibombBlockEntity>> FORMIDIBOMB = BLOCK_ENTITIES.register("formidibomb", () -> BlockEntityType.Builder.m_155273_(FormidibombBlockEntity::new, (Block[])new Block[]{(Block)WitherStormModBlocks.FORMIDIBOMB.get()}).m_58966_(null));
    public static final RegistryObject<BlockEntityType<SuperBeaconBlockEntity>> SUPER_BEACON = BLOCK_ENTITIES.register("super_beacon", () -> BlockEntityType.Builder.m_155273_(SuperBeaconBlockEntity::new, (Block[])new Block[]{(Block)WitherStormModBlocks.SUPER_BEACON.get()}).m_58966_(null));
    public static final RegistryObject<BlockEntityType<SuperSupportBeaconBlockEntity>> SUPER_SUPPORT_BEACON = BLOCK_ENTITIES.register("super_support_beacon", () -> BlockEntityType.Builder.m_155273_(SuperSupportBeaconBlockEntity::new, (Block[])new Block[]{(Block)WitherStormModBlocks.SUPER_SUPPORT_BEACON.get()}).m_58966_(null));
    public static final RegistryObject<BlockEntityType<FireworkBundleBlockEntity>> FIREWORK_BUNDLE = BLOCK_ENTITIES.register("firework_bundle", () -> BlockEntityType.Builder.m_155273_(FireworkBundleBlockEntity::new, (Block[])new Block[]{(Block)WitherStormModBlocks.FIREWORK_BUNDLE.get()}).m_58966_(null));
    public static final RegistryObject<BlockEntityType<WitheredPhlegmBlockEntity>> WITHERED_PHLEGM = BLOCK_ENTITIES.register("withered_phlegm", () -> BlockEntityType.Builder.m_155273_(WitheredPhlegmBlockEntity::new, (Block[])new Block[]{(Block)WitherStormModBlocks.WITHERED_PHLEGM_BLOCK.get()}).m_58966_(null));
}

