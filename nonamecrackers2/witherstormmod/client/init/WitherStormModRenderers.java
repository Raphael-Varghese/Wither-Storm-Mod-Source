/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.BeeModel
 *  net.minecraft.client.model.ChickenModel
 *  net.minecraft.client.model.CowModel
 *  net.minecraft.client.model.CreeperModel
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.model.IllagerModel
 *  net.minecraft.client.model.ParrotModel
 *  net.minecraft.client.model.PhantomModel
 *  net.minecraft.client.model.PigModel
 *  net.minecraft.client.model.SkeletonModel
 *  net.minecraft.client.model.SnowGolemModel
 *  net.minecraft.client.model.SpiderModel
 *  net.minecraft.client.model.ZombieVillagerModel
 *  net.minecraft.client.model.geom.LayerDefinitions
 *  net.minecraft.client.model.geom.ModelLayerLocation
 *  net.minecraft.client.model.geom.ModelLayers
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.renderer.entity.EntityRenderDispatcher
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.LivingEntityRenderer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.properties.WoodType
 *  net.minecraftforge.client.event.EntityRenderersEvent$AddLayers
 *  net.minecraftforge.client.event.EntityRenderersEvent$RegisterLayerDefinitions
 *  net.minecraftforge.client.event.EntityRenderersEvent$RegisterRenderers
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.client.init;

import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.BeeModel;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.model.PhantomModel;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.ZombieVillagerModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.client.renderer.blockentity.SuperBeaconRenderer;
import nonamecrackers2.witherstormmod.client.renderer.blockentity.SuperSupportBeaconRenderer;
import nonamecrackers2.witherstormmod.client.renderer.blockentity.WitheredPhlegmRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.BlockClusterRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.BlueFlamingWitherSkullRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.CommandBlockRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.FlamingWitherSkullRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.FormidibombRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedBeeRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedCatRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedChickenRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedCowRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedCreeperRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedIronGolemRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedMushroomCowRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedParrotRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedPhantomRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedPigRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedPillagerRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedSkeletonRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedSnowGolemRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedSpiderRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedVillagerRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedVindicatorRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedWolfRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SickenedZombieRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.SuperTNTRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.TaintedSlimeRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.TentacleRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.TentacleSpikeRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.WitherStormHeadRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.WitherStormRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.WitherStormSegmentRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.WitheredSymbiontRenderer;
import nonamecrackers2.witherstormmod.client.renderer.entity.layer.AbsorbtionLayer;
import nonamecrackers2.witherstormmod.client.renderer.entity.layer.WitherSicknessLayer;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.FlamingWitherSkullModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.SantaHatModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.TaintedSlimeModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.TentacleModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.TentacleSpikeModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.WitherStormHeadModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.WitheredSymbiontModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.commandblock.RibcageModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.sickenedentity.SickenedIronGolemModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.sickenedentity.SickenedVillagerModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormCommandBlockModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormDestroyerModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormDevourerModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormDismantledModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormEvolvedDestroyerModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormEvolvedDevourerModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormGrowingHunchbackModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback1_1Model;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback1_2Model;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback2_1Model;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback3_1Model;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchback3_2Model;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormHunchbackModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormIntermediateDevourerModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormIntermediateEvolvedDestroyerModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormIntermediateEvolvedDevourerModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormPregnantHunchbackModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormSegmentModel;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.witherstorm.impl.WitherStormTornEvolvedDevourerModel;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;

public class WitherStormModRenderers {
    public static final ModelLayerLocation WITHER_STORM_0 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase0");
    public static final ModelLayerLocation WITHER_STORM_1 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase1");
    public static final ModelLayerLocation WITHER_STORM_1_1 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase1_1");
    public static final ModelLayerLocation WITHER_STORM_1_2 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase1_2");
    public static final ModelLayerLocation WITHER_STORM_2 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase2");
    public static final ModelLayerLocation WITHER_STORM_2_1 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase2_1");
    public static final ModelLayerLocation WITHER_STORM_3 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase3");
    public static final ModelLayerLocation WITHER_STORM_3_1 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase3_1");
    public static final ModelLayerLocation WITHER_STORM_3_2 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase3_2");
    public static final ModelLayerLocation WITHER_STORM_4 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase4");
    public static final ModelLayerLocation WITHER_STORM_4_5 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase4_5");
    public static final ModelLayerLocation WITHER_STORM_5 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase5");
    public static final ModelLayerLocation WITHER_STORM_5_5 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase5_5");
    public static final ModelLayerLocation WITHER_STORM_6 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase6");
    public static final ModelLayerLocation WITHER_STORM_6_5 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase6_5");
    public static final ModelLayerLocation WITHER_STORM_7 = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "phase7");
    public static final ModelLayerLocation WITHER_STORM_DISMANTLED = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "dismantled");
    public static final ModelLayerLocation WITHER_STORM_TORN = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "torn");
    public static final ModelLayerLocation WITHER_STORM_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm"), "armor");
    public static final ModelLayerLocation WITHER_STORM_SEGMENT = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm_segment"), "main");
    public static final ModelLayerLocation FLAMING_WITHER_SKULL = new ModelLayerLocation(new ResourceLocation("witherstormmod", "flaming_wither_skull"), "main");
    public static final ModelLayerLocation SICKENED_ZOMBIE = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_zombie"), "main");
    public static final ModelLayerLocation SICKENED_ZOMBIE_INNER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_zombie"), "inner_armor");
    public static final ModelLayerLocation SICKENED_ZOMBIE_OUTER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_zombie"), "outer_armor");
    public static final ModelLayerLocation SICKENED_SKELETON = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_skeleton"), "main");
    public static final ModelLayerLocation SICKENED_SKELETON_INNER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_skeleton"), "inner_armor");
    public static final ModelLayerLocation SICKENED_SKELETON_OUTER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_skeleton"), "outer_armor");
    public static final ModelLayerLocation SICKENED_SPIDER = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_spider"), "main");
    public static final ModelLayerLocation SICKENED_CREEPER = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_creeper"), "main");
    public static final ModelLayerLocation SICKENED_CREEPER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_creeper"), "armor");
    public static final ModelLayerLocation SICKENED_VILLAGER = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_villager"), "main");
    public static final ModelLayerLocation SICKENED_VILLAGER_INNER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_villager"), "inner_armor");
    public static final ModelLayerLocation SICKENED_VILLAGER_OUTER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_villager"), "outer_armor");
    public static final ModelLayerLocation SICKENED_PHANTOM = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_phantom"), "main");
    public static final ModelLayerLocation SICKENED_CHICKEN = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_chicken"), "main");
    public static final ModelLayerLocation SICKENED_PARROT = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_parrot"), "main");
    public static final ModelLayerLocation SICKENED_COW = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_cow"), "main");
    public static final ModelLayerLocation SICKENED_PIG = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_pig"), "main");
    public static final ModelLayerLocation SICKENED_BEE = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_bee"), "main");
    public static final ModelLayerLocation SICKENED_MUSHROOM_COW = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_mushroom_cow"), "main");
    public static final ModelLayerLocation SICKENED_PILLAGER = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_pillager"), "main");
    public static final ModelLayerLocation SICKENED_VINDICATOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_vindicator"), "main");
    public static final ModelLayerLocation SICKENED_IRON_GOLEM = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_iron_golem"), "main");
    public static final ModelLayerLocation SICKENED_SNOW_GOLEM = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_snow_golem"), "main");
    public static final ModelLayerLocation SICKENED_GOAT = new ModelLayerLocation(new ResourceLocation("witherstormmod", "sickened_goat"), "main");
    public static final ModelLayerLocation WITHERED_SYMBIONT = new ModelLayerLocation(new ResourceLocation("witherstormmod", "withered_symbiont"), "main");
    public static final ModelLayerLocation SYMBIONT_INNER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "withered_symbiont"), "inner_armor");
    public static final ModelLayerLocation SYMBIONT_OUTER_ARMOR = new ModelLayerLocation(new ResourceLocation("witherstormmod", "withered_symbiont"), "outer_armor");
    public static final ModelLayerLocation WITHER_STORM_HEAD = new ModelLayerLocation(new ResourceLocation("witherstormmod", "wither_storm_head"), "main");
    public static final ModelLayerLocation TENTACLE = new ModelLayerLocation(new ResourceLocation("witherstormmod", "tentacle"), "main");
    public static final ModelLayerLocation RIBCAGE = new ModelLayerLocation(new ResourceLocation("witherstormmod", "ribcage"), "main");
    public static final ModelLayerLocation TAINTED_SLIME = new ModelLayerLocation(new ResourceLocation("witherstormmod", "tainted_slime"), "main");
    public static final ModelLayerLocation TENTACLE_SPIKE = new ModelLayerLocation(new ResourceLocation("witherstormmod", "tentacle_spike"), "main");
    public static final ModelLayerLocation SANTA_HAT = new ModelLayerLocation(WitherStormMod.id("santa_hat"), "main");
    public static final ModelLayerLocation TAINTED_SIGN = ModelLayers.m_171291_((WoodType)WitherStormModBlocks.TAINTED);
    public static final ModelLayerLocation TAINTED_HANGING_SIGN = ModelLayers.m_247439_((WoodType)WitherStormModBlocks.TAINTED);

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.WITHER_STORM.get(), WitherStormRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get(), BlockClusterRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.WITHER_STORM_SEGMENT.get(), WitherStormSegmentRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.FLAMING_WITHER_SKULL.get(), FlamingWitherSkullRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.BLUE_FLAMING_WITHER_SKULL.get(), BlueFlamingWitherSkullRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get(), SickenedZombieRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get(), SickenedSkeletonRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get(), SickenedSpiderRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get(), SickenedCreeperRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SUPER_TNT.get(), SuperTNTRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.FORMIDIBOMB.get(), FormidibombRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.COMMAND_BLOCK.get(), CommandBlockRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.WITHERED_SYMBIONT.get(), WitheredSymbiontRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.WITHER_STORM_HEAD.get(), WitherStormHeadRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.TENTACLE.get(), TentacleRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get(), SickenedVillagerRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_PHANTOM.get(), SickenedPhantomRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get(), SickenedChickenRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_PARROT.get(), SickenedParrotRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_WOLF.get(), SickenedWolfRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_CAT.get(), SickenedCatRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_COW.get(), SickenedCowRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_PIG.get(), SickenedPigRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get(), SickenedMushroomCowRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_BEE.get(), SickenedBeeRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_PILLAGER.get(), SickenedPillagerRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_VINDICATOR.get(), SickenedVindicatorRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_IRON_GOLEM.get(), SickenedIronGolemRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get(), SickenedSnowGolemRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.TAINTED_SLIME.get(), TaintedSlimeRenderer::new);
        event.registerEntityRenderer((EntityType)WitherStormModEntityTypes.TENTACLE_SPIKE.get(), TentacleSpikeRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType)WitherStormModBlockEntityTypes.SUPER_BEACON.get(), SuperBeaconRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType)WitherStormModBlockEntityTypes.SUPER_SUPPORT_BEACON.get(), SuperSupportBeaconRenderer::new);
        event.registerBlockEntityRenderer((BlockEntityType)WitherStormModBlockEntityTypes.WITHERED_PHLEGM.get(), WitheredPhlegmRenderer::new);
    }

    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        LayerDefinition humanoid = LayerDefinition.m_171565_((MeshDefinition)HumanoidModel.m_170681_((CubeDeformation)CubeDeformation.f_171458_, (float)0.0f), (int)64, (int)64);
        LayerDefinition humanoidInner = LayerDefinition.m_171565_((MeshDefinition)HumanoidModel.m_170681_((CubeDeformation)LayerDefinitions.f_171107_, (float)0.0f), (int)64, (int)32);
        LayerDefinition humanoidOuter = LayerDefinition.m_171565_((MeshDefinition)HumanoidModel.m_170681_((CubeDeformation)LayerDefinitions.f_171106_, (float)0.0f), (int)64, (int)32);
        event.registerLayerDefinition(WITHER_STORM_0, () -> WitherStormCommandBlockModel.createLayerDefinition(CubeDeformation.f_171458_));
        event.registerLayerDefinition(WITHER_STORM_1, () -> WitherStormHunchbackModel.createLayerDefinition(CubeDeformation.f_171458_));
        event.registerLayerDefinition(WITHER_STORM_1_1, () -> WitherStormHunchback1_1Model.createLayerDefinition(CubeDeformation.f_171458_));
        event.registerLayerDefinition(WITHER_STORM_1_2, () -> WitherStormHunchback1_2Model.createLayerDefinition(CubeDeformation.f_171458_));
        event.registerLayerDefinition(WITHER_STORM_2, () -> WitherStormGrowingHunchbackModel.createLayerDefinition(CubeDeformation.f_171458_));
        event.registerLayerDefinition(WITHER_STORM_2_1, () -> WitherStormHunchback2_1Model.createLayerDefinition(CubeDeformation.f_171458_));
        event.registerLayerDefinition(WITHER_STORM_3, () -> WitherStormPregnantHunchbackModel.createLayerDefinition(CubeDeformation.f_171458_));
        event.registerLayerDefinition(WITHER_STORM_3_1, () -> WitherStormHunchback3_1Model.createLayerDefinition(CubeDeformation.f_171458_));
        event.registerLayerDefinition(WITHER_STORM_3_2, () -> WitherStormHunchback3_2Model.createLayerDefinition(CubeDeformation.f_171458_));
        event.registerLayerDefinition(WITHER_STORM_4, WitherStormDestroyerModel::createLayerDefinition);
        event.registerLayerDefinition(WITHER_STORM_4_5, WitherStormIntermediateEvolvedDestroyerModel::createLayerDefinition);
        event.registerLayerDefinition(WITHER_STORM_5, WitherStormEvolvedDestroyerModel::createLayerDefinition);
        event.registerLayerDefinition(WITHER_STORM_5_5, WitherStormIntermediateDevourerModel::createLayerDefinition);
        event.registerLayerDefinition(WITHER_STORM_6, WitherStormDevourerModel::createLayerDefinition);
        event.registerLayerDefinition(WITHER_STORM_6_5, WitherStormIntermediateEvolvedDevourerModel::createLayerDefinition);
        event.registerLayerDefinition(WITHER_STORM_7, WitherStormEvolvedDevourerModel::createLayerDefinition);
        event.registerLayerDefinition(WITHER_STORM_DISMANTLED, WitherStormDismantledModel::createLayerDefinition);
        event.registerLayerDefinition(WITHER_STORM_TORN, WitherStormTornEvolvedDevourerModel::createLayerDefinition);
        event.registerLayerDefinition(WITHER_STORM_ARMOR, () -> WitherStormCommandBlockModel.createLayerDefinition(LayerDefinitions.f_171107_));
        event.registerLayerDefinition(WITHER_STORM_SEGMENT, WitherStormSegmentModel::createLayerDefinition);
        event.registerLayerDefinition(FLAMING_WITHER_SKULL, FlamingWitherSkullModel::createLayerDefinition);
        event.registerLayerDefinition(SICKENED_ZOMBIE, () -> humanoid);
        event.registerLayerDefinition(SICKENED_ZOMBIE_INNER_ARMOR, () -> humanoidInner);
        event.registerLayerDefinition(SICKENED_ZOMBIE_OUTER_ARMOR, () -> humanoidOuter);
        event.registerLayerDefinition(SICKENED_SKELETON, SkeletonModel::m_170942_);
        event.registerLayerDefinition(SICKENED_SKELETON_INNER_ARMOR, () -> humanoidInner);
        event.registerLayerDefinition(SICKENED_SKELETON_OUTER_ARMOR, () -> humanoidOuter);
        event.registerLayerDefinition(SICKENED_CREEPER, () -> CreeperModel.m_170525_((CubeDeformation)CubeDeformation.f_171458_));
        event.registerLayerDefinition(SICKENED_CREEPER_ARMOR, () -> CreeperModel.m_170525_((CubeDeformation)new CubeDeformation(2.0f)));
        event.registerLayerDefinition(SICKENED_SPIDER, SpiderModel::m_170985_);
        event.registerLayerDefinition(SICKENED_VILLAGER, SickenedVillagerModel::createBodyLayer);
        event.registerLayerDefinition(SICKENED_VILLAGER_INNER_ARMOR, () -> ZombieVillagerModel.m_171093_((CubeDeformation)new CubeDeformation(0.5f)));
        event.registerLayerDefinition(SICKENED_VILLAGER_OUTER_ARMOR, () -> ZombieVillagerModel.m_171093_((CubeDeformation)new CubeDeformation(1.0f)));
        event.registerLayerDefinition(SICKENED_PHANTOM, PhantomModel::m_170789_);
        event.registerLayerDefinition(SICKENED_CHICKEN, ChickenModel::m_170491_);
        event.registerLayerDefinition(SICKENED_PARROT, ParrotModel::m_170781_);
        event.registerLayerDefinition(SICKENED_COW, CowModel::m_170516_);
        event.registerLayerDefinition(SICKENED_PIG, () -> PigModel.m_170800_((CubeDeformation)CubeDeformation.f_171458_));
        event.registerLayerDefinition(SICKENED_MUSHROOM_COW, CowModel::m_170516_);
        event.registerLayerDefinition(SICKENED_BEE, BeeModel::m_170440_);
        event.registerLayerDefinition(SICKENED_PILLAGER, IllagerModel::m_170689_);
        event.registerLayerDefinition(SICKENED_VINDICATOR, IllagerModel::m_170689_);
        event.registerLayerDefinition(SICKENED_IRON_GOLEM, SickenedIronGolemModel::createBodyLayer);
        event.registerLayerDefinition(SICKENED_SNOW_GOLEM, SnowGolemModel::m_170966_);
        event.registerLayerDefinition(WITHERED_SYMBIONT, WitheredSymbiontModel::createLayerDefinition);
        event.registerLayerDefinition(SYMBIONT_INNER_ARMOR, () -> humanoidInner);
        event.registerLayerDefinition(SYMBIONT_OUTER_ARMOR, () -> humanoidOuter);
        event.registerLayerDefinition(WITHER_STORM_HEAD, WitherStormHeadModel::createLayerDefinition);
        event.registerLayerDefinition(TENTACLE, TentacleModel::createLayerDefinition);
        event.registerLayerDefinition(RIBCAGE, RibcageModel::createLayerDefinition);
        event.registerLayerDefinition(TAINTED_SLIME, TaintedSlimeModel::createLayerDefinition);
        event.registerLayerDefinition(TENTACLE_SPIKE, TentacleSpikeModel::createLayerDefinition);
        event.registerLayerDefinition(SANTA_HAT, SantaHatModel::createLayerDefinition);
    }

    @SubscribeEvent
    public static void addRendererLayers(EntityRenderersEvent.AddLayers event) {
        Minecraft mc = Minecraft.m_91087_();
        EntityRenderDispatcher manager = mc.m_91290_();
        manager.getSkinMap().forEach((type, renderer) -> {
            if (renderer instanceof LivingEntityRenderer) {
                LivingEntityRenderer livingRenderer = (LivingEntityRenderer)renderer;
                if (((Boolean)WitherStormModConfig.CLIENT.witherSicknessLayer.get()).booleanValue()) {
                    livingRenderer.m_115326_(new WitherSicknessLayer(livingRenderer));
                }
                livingRenderer.m_115326_(new AbsorbtionLayer(livingRenderer));
            }
        });
        for (Map.Entry entry : manager.f_114362_.entrySet()) {
            EntityRenderer renderer2 = (EntityRenderer)entry.getValue();
            if (!(renderer2 instanceof LivingEntityRenderer)) continue;
            LivingEntityRenderer livingRenderer = (LivingEntityRenderer)renderer2;
            if (((Boolean)WitherStormModConfig.CLIENT.witherSicknessLayer.get()).booleanValue()) {
                livingRenderer.m_115326_(new WitherSicknessLayer(livingRenderer));
            }
            livingRenderer.m_115326_(new AbsorbtionLayer(livingRenderer));
        }
    }
}

