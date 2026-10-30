/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.data.PackOutput
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.Mob
 */
package nonamecrackers2.witherstormmod.common.data;

import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import nonamecrackers2.witherstormmod.api.common.data.MobConversionProvider;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;

public class WitherStormModMobConversionsProvider
extends MobConversionProvider {
    public WitherStormModMobConversionsProvider(PackOutput output) {
        super(output, "witherstormmod");
    }

    @Override
    protected void addConversions() {
        this.add((EntityType<? extends Mob>)EntityType.f_20501_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20524_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20479_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_SPIDER.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20558_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_CREEPER.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20492_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20530_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_VILLAGER.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20509_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_PHANTOM.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20555_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_CHICKEN.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20508_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_PARROT.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20557_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_COW.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20510_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_PIG.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20504_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_MUSHROOM_COW.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20550_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_BEE.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20513_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_PILLAGER.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20493_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_VINDICATOR.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20481_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_SKELETON.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20458_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20562_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_ZOMBIE.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20460_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_IRON_GOLEM.get()), false);
        this.add((EntityType<? extends Mob>)EntityType.f_20528_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_SNOW_GOLEM.get()), false);
        this.add((EntityType<? extends Mob>)EntityType.f_20499_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_WOLF.get()));
        this.add((EntityType<? extends Mob>)EntityType.f_20553_, (EntityType<? extends Mob>)((EntityType)WitherStormModEntityTypes.SICKENED_CAT.get()));
    }
}

