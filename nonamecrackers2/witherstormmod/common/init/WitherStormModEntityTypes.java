/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EntityType$Builder
 *  net.minecraft.world.entity.MobCategory
 *  net.minecraft.world.entity.SpawnPlacements$Type
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraftforge.event.entity.EntityAttributeCreationEvent
 *  net.minecraftforge.event.entity.SpawnPlacementRegisterEvent
 *  net.minecraftforge.event.entity.SpawnPlacementRegisterEvent$Operation
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package nonamecrackers2.witherstormmod.common.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import nonamecrackers2.witherstormmod.client.util.ClientBlockClusterFactory;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
import nonamecrackers2.witherstormmod.common.entity.BlueFlamingWitherSkullEntity;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.entity.FlamingWitherSkullEntity;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.entity.SickenedBee;
import nonamecrackers2.witherstormmod.common.entity.SickenedCat;
import nonamecrackers2.witherstormmod.common.entity.SickenedChicken;
import nonamecrackers2.witherstormmod.common.entity.SickenedCow;
import nonamecrackers2.witherstormmod.common.entity.SickenedCreeper;
import nonamecrackers2.witherstormmod.common.entity.SickenedIronGolem;
import nonamecrackers2.witherstormmod.common.entity.SickenedMushroomCow;
import nonamecrackers2.witherstormmod.common.entity.SickenedParrot;
import nonamecrackers2.witherstormmod.common.entity.SickenedPhantom;
import nonamecrackers2.witherstormmod.common.entity.SickenedPig;
import nonamecrackers2.witherstormmod.common.entity.SickenedPillager;
import nonamecrackers2.witherstormmod.common.entity.SickenedSkeleton;
import nonamecrackers2.witherstormmod.common.entity.SickenedSnowGolem;
import nonamecrackers2.witherstormmod.common.entity.SickenedSpider;
import nonamecrackers2.witherstormmod.common.entity.SickenedVillager;
import nonamecrackers2.witherstormmod.common.entity.SickenedVindicator;
import nonamecrackers2.witherstormmod.common.entity.SickenedWolf;
import nonamecrackers2.witherstormmod.common.entity.SickenedZombie;
import nonamecrackers2.witherstormmod.common.entity.SuperTNTEntity;
import nonamecrackers2.witherstormmod.common.entity.TaintedSlime;
import nonamecrackers2.witherstormmod.common.entity.TentacleEntity;
import nonamecrackers2.witherstormmod.common.entity.TentacleSpike;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormHeadEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormSegmentEntity;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;

public class WitherStormModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create((IForgeRegistry)ForgeRegistries.ENTITY_TYPES, (String)"witherstormmod");
    public static final RegistryObject<EntityType<WitherStormEntity>> WITHER_STORM = WitherStormModEntityTypes.register("wither_storm", EntityType.Builder.m_20704_(WitherStormEntity::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.9f, 3.5f).setTrackingRange(512).m_20702_(512).m_20719_());
    public static final RegistryObject<EntityType<BlockClusterEntity>> BLOCK_CLUSTER = WitherStormModEntityTypes.register("block_cluster", EntityType.Builder.m_20704_(BlockClusterEntity::new, (MobCategory)MobCategory.MISC).m_20699_(1.0f, 1.0f).setTrackingRange(512).m_20702_(512).m_20719_().m_20717_(10).setCustomClientFactory(ClientBlockClusterFactory::make));
    public static final RegistryObject<EntityType<WitherStormSegmentEntity>> WITHER_STORM_SEGMENT = WitherStormModEntityTypes.register("wither_storm_segment", EntityType.Builder.m_20704_(WitherStormSegmentEntity::new, (MobCategory)MobCategory.MONSTER).m_20699_(30.0f, 25.0f).setTrackingRange(512).m_20702_(512).m_20719_());
    public static final RegistryObject<EntityType<FlamingWitherSkullEntity>> FLAMING_WITHER_SKULL = WitherStormModEntityTypes.register("flaming_wither_skull", EntityType.Builder.m_20704_(FlamingWitherSkullEntity::new, (MobCategory)MobCategory.MISC).m_20699_(0.8f, 0.8f).m_20702_(4).m_20717_(10));
    public static final RegistryObject<EntityType<BlueFlamingWitherSkullEntity>> BLUE_FLAMING_WITHER_SKULL = WitherStormModEntityTypes.register("blue_flaming_wither_skull", EntityType.Builder.m_20704_(BlueFlamingWitherSkullEntity::new, (MobCategory)MobCategory.MISC).m_20699_(0.8f, 0.8f).m_20702_(4).m_20717_(10));
    public static final RegistryObject<EntityType<SickenedZombie>> SICKENED_ZOMBIE = WitherStormModEntityTypes.register("sickened_zombie", EntityType.Builder.m_20704_(SickenedZombie::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.6f, 1.95f).m_20702_(8));
    public static final RegistryObject<EntityType<SickenedSkeleton>> SICKENED_SKELETON = WitherStormModEntityTypes.register("sickened_skeleton", EntityType.Builder.m_20704_(SickenedSkeleton::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.6f, 1.99f).m_20702_(8));
    public static final RegistryObject<EntityType<SickenedSpider>> SICKENED_SPIDER = WitherStormModEntityTypes.register("sickened_spider", EntityType.Builder.m_20704_(SickenedSpider::new, (MobCategory)MobCategory.MONSTER).m_20699_(1.6f, 1.1f).m_20702_(8));
    public static final RegistryObject<EntityType<SickenedCreeper>> SICKENED_CREEPER = WitherStormModEntityTypes.register("sickened_creeper", EntityType.Builder.m_20704_(SickenedCreeper::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.6f, 1.7f).m_20702_(8));
    public static final RegistryObject<EntityType<SuperTNTEntity>> SUPER_TNT = WitherStormModEntityTypes.register("super_tnt", EntityType.Builder.m_20704_(SuperTNTEntity::new, (MobCategory)MobCategory.MISC).m_20719_().m_20699_(0.98f, 0.98f).m_20702_(10).m_20717_(10));
    public static final RegistryObject<EntityType<FormidibombEntity>> FORMIDIBOMB = WitherStormModEntityTypes.register("formidibomb", EntityType.Builder.m_20704_(FormidibombEntity::new, (MobCategory)MobCategory.MISC).m_20719_().m_20699_(0.98f, 0.98f).m_20702_(10));
    public static final RegistryObject<EntityType<CommandBlockEntity>> COMMAND_BLOCK = WitherStormModEntityTypes.register("command_block", EntityType.Builder.m_20704_(CommandBlockEntity::new, (MobCategory)MobCategory.MISC).m_20719_().m_20699_(1.0f, 1.0f).m_20702_(10));
    public static final RegistryObject<EntityType<WitheredSymbiontEntity>> WITHERED_SYMBIONT = WitherStormModEntityTypes.register("withered_symbiont", EntityType.Builder.m_20704_(WitheredSymbiontEntity::new, (MobCategory)MobCategory.MONSTER).m_20719_().m_20699_(1.2f, 3.8f).m_20702_(10));
    public static final RegistryObject<EntityType<WitherStormHeadEntity>> WITHER_STORM_HEAD = WitherStormModEntityTypes.register("wither_storm_head", EntityType.Builder.m_20704_(WitherStormHeadEntity::new, (MobCategory)MobCategory.MONSTER).m_20719_().m_20699_(5.0f, 5.0f).m_20702_(10));
    public static final RegistryObject<EntityType<TentacleEntity>> TENTACLE = WitherStormModEntityTypes.register("tentacle", EntityType.Builder.m_20704_(TentacleEntity::new, (MobCategory)MobCategory.MONSTER).m_20719_().m_20699_(7.5f, 9.5f).m_20702_(10));
    public static final RegistryObject<EntityType<SickenedVillager>> SICKENED_VILLAGER = WitherStormModEntityTypes.register("sickened_villager", EntityType.Builder.m_20704_(SickenedVillager::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.6f, 1.95f).m_20702_(8));
    public static final RegistryObject<EntityType<SickenedPhantom>> SICKENED_PHANTOM = WitherStormModEntityTypes.register("sickened_phantom", EntityType.Builder.m_20704_(SickenedPhantom::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.9f, 0.5f).m_20702_(8));
    public static final RegistryObject<EntityType<SickenedChicken>> SICKENED_CHICKEN = WitherStormModEntityTypes.register("sickened_chicken", EntityType.Builder.m_20704_(SickenedChicken::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.4f, 0.7f).m_20702_(10));
    public static final RegistryObject<EntityType<SickenedParrot>> SICKENED_PARROT = WitherStormModEntityTypes.register("sickened_parrot", EntityType.Builder.m_20704_(SickenedParrot::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.5f, 0.9f).m_20702_(8));
    public static final RegistryObject<EntityType<SickenedWolf>> SICKENED_WOLF = WitherStormModEntityTypes.register("sickened_wolf", EntityType.Builder.m_20704_(SickenedWolf::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.6f, 0.85f).m_20702_(10));
    public static final RegistryObject<EntityType<SickenedCat>> SICKENED_CAT = WitherStormModEntityTypes.register("sickened_cat", EntityType.Builder.m_20704_(SickenedCat::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.6f, 0.7f).m_20702_(8));
    public static final RegistryObject<EntityType<SickenedCow>> SICKENED_COW = WitherStormModEntityTypes.register("sickened_cow", EntityType.Builder.m_20704_(SickenedCow::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.9f, 1.4f).m_20702_(10));
    public static final RegistryObject<EntityType<SickenedPig>> SICKENED_PIG = WitherStormModEntityTypes.register("sickened_pig", EntityType.Builder.m_20704_(SickenedPig::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.9f, 0.9f).m_20702_(10));
    public static final RegistryObject<EntityType<SickenedMushroomCow>> SICKENED_MUSHROOM_COW = WitherStormModEntityTypes.register("sickened_mushroom_cow", EntityType.Builder.m_20704_(SickenedMushroomCow::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.9f, 1.4f).m_20702_(10));
    public static final RegistryObject<EntityType<SickenedBee>> SICKENED_BEE = WitherStormModEntityTypes.register("sickened_bee", EntityType.Builder.m_20704_(SickenedBee::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.7f, 0.6f).m_20702_(8));
    public static final RegistryObject<EntityType<SickenedPillager>> SICKENED_PILLAGER = WitherStormModEntityTypes.register("sickened_pillager", EntityType.Builder.m_20704_(SickenedPillager::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.6f, 1.95f).m_20702_(8));
    public static final RegistryObject<EntityType<SickenedVindicator>> SICKENED_VINDICATOR = WitherStormModEntityTypes.register("sickened_vindicator", EntityType.Builder.m_20704_(SickenedVindicator::new, (MobCategory)MobCategory.MONSTER).m_20699_(0.6f, 1.95f).m_20702_(8));
    public static final RegistryObject<EntityType<SickenedIronGolem>> SICKENED_IRON_GOLEM = WitherStormModEntityTypes.register("sickened_iron_golem", EntityType.Builder.m_20704_(SickenedIronGolem::new, (MobCategory)MobCategory.MONSTER).m_20699_(1.4f, 2.7f).m_20702_(10).m_20719_().m_20714_(new Block[]{Blocks.f_152499_}));
    public static final RegistryObject<EntityType<SickenedSnowGolem>> SICKENED_SNOW_GOLEM = WitherStormModEntityTypes.register("sickened_snow_golem", EntityType.Builder.m_20704_(SickenedSnowGolem::new, (MobCategory)MobCategory.MONSTER).m_20714_(new Block[]{Blocks.f_152499_}).m_20699_(0.7f, 1.9f).m_20702_(8));
    public static final RegistryObject<EntityType<TaintedSlime>> TAINTED_SLIME = WitherStormModEntityTypes.register("tainted_slime", EntityType.Builder.m_20704_(TaintedSlime::new, (MobCategory)MobCategory.MONSTER).m_20698_().m_20699_(2.04f, 2.04f).m_20702_(10));
    public static final RegistryObject<EntityType<TentacleSpike>> TENTACLE_SPIKE = WitherStormModEntityTypes.register("tentacle_spike", EntityType.Builder.m_20704_(TentacleSpike::new, (MobCategory)MobCategory.MISC).m_20699_(0.5f, 1.4f).m_20702_(6).m_20717_(2));

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String id, EntityType.Builder<T> builder) {
        return ENTITIES.register(id, () -> builder.m_20712_(new ResourceLocation("witherstormmod", id).toString()));
    }

    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put((EntityType)WITHER_STORM.get(), WitherStormEntity.createAttributes().m_22265_());
        event.put((EntityType)WITHER_STORM_SEGMENT.get(), WitherStormSegmentEntity.createAttributes().m_22265_());
        event.put((EntityType)SICKENED_ZOMBIE.get(), SickenedZombie.m_34328_().m_22265_());
        event.put((EntityType)SICKENED_SKELETON.get(), SickenedSkeleton.m_32166_().m_22265_());
        event.put((EntityType)SICKENED_SPIDER.get(), SickenedSpider.m_33815_().m_22265_());
        event.put((EntityType)SICKENED_CREEPER.get(), SickenedCreeper.m_32318_().m_22265_());
        event.put((EntityType)COMMAND_BLOCK.get(), CommandBlockEntity.createAttributes().m_22265_());
        event.put((EntityType)WITHERED_SYMBIONT.get(), WitheredSymbiontEntity.createAttributes().m_22265_());
        event.put((EntityType)WITHER_STORM_HEAD.get(), WitherStormHeadEntity.createAttributes().m_22265_());
        event.put((EntityType)TENTACLE.get(), TentacleEntity.createAttributes().m_22265_());
        event.put((EntityType)SICKENED_VILLAGER.get(), SickenedVillager.m_34328_().m_22265_());
        event.put((EntityType)SICKENED_PHANTOM.get(), SickenedPhantom.createAttributes().m_22265_());
        event.put((EntityType)SICKENED_CHICKEN.get(), SickenedChicken.m_28263_().m_22265_());
        event.put((EntityType)SICKENED_PARROT.get(), SickenedParrot.m_29438_().m_22265_());
        event.put((EntityType)SICKENED_WOLF.get(), SickenedWolf.m_30425_().m_22265_());
        event.put((EntityType)SICKENED_CAT.get(), SickenedCat.m_28168_().m_22265_());
        event.put((EntityType)SICKENED_COW.get(), SickenedCow.m_28307_().m_22265_());
        event.put((EntityType)SICKENED_PIG.get(), SickenedPig.m_29503_().m_22265_());
        event.put((EntityType)SICKENED_MUSHROOM_COW.get(), SickenedMushroomCow.m_28307_().m_22265_());
        event.put((EntityType)SICKENED_BEE.get(), SickenedBee.m_27858_().m_22265_());
        event.put((EntityType)SICKENED_PILLAGER.get(), SickenedPillager.m_33307_().m_22265_());
        event.put((EntityType)SICKENED_VINDICATOR.get(), SickenedVindicator.m_34104_().m_22265_());
        event.put((EntityType)SICKENED_IRON_GOLEM.get(), SickenedIronGolem.createAttributes().m_22265_());
        event.put((EntityType)SICKENED_SNOW_GOLEM.get(), SickenedSnowGolem.m_29934_().m_22265_());
        event.put((EntityType)TAINTED_SLIME.get(), TaintedSlime.createAttributes().m_22265_());
    }

    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register((EntityType)SICKENED_ZOMBIE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::m_219013_, SpawnPlacementRegisterEvent.Operation.OR);
        event.register((EntityType)SICKENED_SKELETON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::m_219013_, SpawnPlacementRegisterEvent.Operation.OR);
        event.register((EntityType)SICKENED_SPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::m_219013_, SpawnPlacementRegisterEvent.Operation.OR);
        event.register((EntityType)SICKENED_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::m_219013_, SpawnPlacementRegisterEvent.Operation.OR);
    }
}

