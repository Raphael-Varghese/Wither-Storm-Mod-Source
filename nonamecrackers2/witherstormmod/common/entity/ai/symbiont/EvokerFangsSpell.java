/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.util.random.SimpleWeightedRandomList
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.projectile.EvokerFangs
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;

import java.util.Objects;
import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class EvokerFangsSpell
extends SymbiontSpell {
    private static final SimpleWeightedRandomList<EntityType<? extends Mob>> PILLAGERS = SimpleWeightedRandomList.m_146263_().m_146271_((Object)((EntityType)WitherStormModEntityTypes.SICKENED_PILLAGER.get()), 3).m_146271_((Object)((EntityType)WitherStormModEntityTypes.SICKENED_VINDICATOR.get()), 3).m_146270_();

    public EvokerFangsSpell(WitheredSymbiontEntity symbiont, SpellType type) {
        super(symbiont, type);
    }

    @Override
    public void cast(@Nonnull LivingEntity target) {
        int chance;
        int n = chance = this.entity.shouldIncreaseDifficulty() ? 6 : 3;
        if (this.entity.m_217043_().m_188503_(chance) == 1) {
            for (int i = 0; i < 3; ++i) {
                Mob mob = WorldUtil.summonRandomMob((ServerLevel)this.entity.m_9236_(), this.entity.m_20183_(), this.entity.m_217043_(), 6, PILLAGERS, this.entity.shouldIncreaseDifficulty());
                if (mob == null) continue;
                EvokerFangsSpell.addAttributes(mob);
            }
        }
        LivingEntity entity = this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE);
        double minHeight = Math.min(entity.m_20186_(), this.entity.m_20186_()) - 2.0;
        double maxHeight = Math.max(entity.m_20186_(), this.entity.m_20186_()) + 2.0;
        int ringCount = 16;
        int fangsPerRing = 8;
        double initialRadius = 1.5;
        double expansionRate = 3.0 + this.entity.m_217043_().m_188500_();
        for (int i = 0; i < ringCount; ++i) {
            double radius = initialRadius + (double)i * expansionRate;
            double angleIncrement = Math.PI * 2 / (double)(fangsPerRing += 4);
            for (int i1 = 0; i1 < fangsPerRing; ++i1) {
                double angle = (double)i1 * angleIncrement;
                double x = this.entity.m_20185_() + Math.cos(angle) * radius;
                double z = this.entity.m_20189_() + Math.sin(angle) * radius;
                this.createFang(x, z, minHeight, maxHeight, (float)angle, i1 + 2);
            }
        }
    }

    private void createFang(double x, double z, double minHeight, double maxHeight, float yRot, int delay) {
        BlockPos blockPos = BlockPos.m_274561_((double)x, (double)maxHeight, (double)z);
        boolean flag = false;
        double d0 = 0.0;
        do {
            BlockState state1;
            VoxelShape shape;
            BlockPos below = blockPos.m_7495_();
            BlockState state = this.entity.m_9236_().m_8055_(below);
            if (!state.m_60783_((BlockGetter)this.entity.m_9236_(), below, Direction.UP)) continue;
            if (!this.entity.m_9236_().m_46859_(blockPos) && !(shape = (state1 = this.entity.m_9236_().m_8055_(blockPos)).m_60812_((BlockGetter)this.entity.m_9236_(), blockPos)).m_83281_()) {
                d0 = shape.m_83297_(Direction.Axis.Y);
            }
            flag = true;
            break;
        } while ((blockPos = blockPos.m_7495_()).m_123342_() >= Mth.m_14107_((double)minHeight) - 1);
        if (flag) {
            this.entity.m_9236_().m_7967_((Entity)new EvokerFangs(this.entity.m_9236_(), x, (double)blockPos.m_123342_() + d0, z, yRot, delay, (LivingEntity)this.entity));
        }
    }

    @Override
    public int getDelay(RandomSource random, float modifier) {
        return Math.max(60, random.m_188503_(100)) - Mth.m_14143_((float)modifier) * 10;
    }

    private static void addAttributes(Mob mob) {
        Objects.requireNonNull(mob.m_21051_(Attributes.f_22276_)).m_22125_(new AttributeModifier("194fec31-b36e-41fc-ad72-02a5cb891def", -((mob.m_217043_().m_188500_() + 0.5) * 2.0), AttributeModifier.Operation.ADDITION));
        Objects.requireNonNull(mob.m_21051_(Attributes.f_22279_)).m_22125_(new AttributeModifier("5965c24d-8ac1-4f04-92ee-3d2724f976e8", -0.08, AttributeModifier.Operation.ADDITION));
    }
}

