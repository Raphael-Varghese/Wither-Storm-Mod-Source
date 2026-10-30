/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.ShulkerBullet
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  org.jetbrains.annotations.NotNull
 */
package nonamecrackers2.witherstormmod.common.entity.ai.symbiont;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SymbiontSpell;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.mixin.IMixinShulkerBullet;
import org.jetbrains.annotations.NotNull;

public class ShulkerBulletsSpell
extends SymbiontSpell {
    public ShulkerBulletsSpell(WitheredSymbiontEntity symbiont, SpellType type) {
        super(symbiont, type);
    }

    @Override
    public void start(@NotNull LivingEntity target) {
        this.projectiles.clear();
        double y = this.entity.m_20188_();
        int amount = this.entity.shouldIncreaseDifficulty() ? 6 : 5;
        for (int i = 0; i < amount; ++i) {
            float theta = (float)(Math.PI * 2 / (double)amount);
            float angle = theta * (float)i;
            double x = 5.0 * (double)Mth.m_14089_((float)angle) + this.entity.m_20185_();
            double z = 5.0 * (double)Mth.m_14031_((float)angle) + this.entity.m_20189_();
            ShulkerBullet projectile = new ShulkerBullet(EntityType.f_20522_, this.entity.m_9236_());
            projectile.m_6027_(x, y, z);
            projectile.m_20242_(true);
            projectile.m_5602_((Entity)this.entity);
            this.entity.m_9236_().m_7967_((Entity)projectile);
            this.projectiles.add(projectile);
        }
    }

    @Override
    public void cast(LivingEntity target) {
        int size = this.projectiles.size();
        for (int i = 0; i < size; ++i) {
            Entity projectile = (Entity)this.projectiles.get(i);
            if (!(projectile instanceof ShulkerBullet)) continue;
            IMixinShulkerBullet bullet = (IMixinShulkerBullet)projectile;
            bullet.setFinalTarget((Entity)this.entity.getRandomNearbyTargetOrFallback(target, WitheredSymbiontEntity.TARGET_PREDICATE));
            bullet.setCurrentMoveDirection(Direction.UP);
            bullet.callSelectNextMoveDirection(Direction.Axis.Y);
            projectile.m_20242_(false);
        }
        this.entity.m_5496_(SoundEvents.f_12417_, 4.0f, 1.0f);
        this.projectiles.clear();
    }

    @Override
    public void doCasting(LivingEntity target) {
        int size = this.projectiles.size();
        int spellCastingTime = this.type.spellTime() - this.entity.getSpellCastingTime();
        for (int i = 0; i < size; ++i) {
            Entity entity = (Entity)this.projectiles.get(i);
            if (!entity.m_6084_()) continue;
            float theta = (float)(Math.PI * 2 / (double)size);
            float angle = theta * (float)i + (float)spellCastingTime * 0.1f;
            double radius = 5.0;
            double x = radius * (double)Mth.m_14089_((float)angle) + this.entity.m_20185_();
            double y = this.entity.m_20188_();
            double z = radius * (double)Mth.m_14031_((float)angle) + this.entity.m_20189_();
            Vec3 wanted = new Vec3(x, y, z);
            double distance = entity.m_20182_().m_82554_(wanted);
            Vec3 delta = wanted.m_82546_(entity.m_20182_()).m_82541_().m_82542_(distance, distance, distance);
            entity.m_20256_(delta);
            BlockPos.m_121921_((AABB)entity.m_20191_().m_82400_(1.0)).forEach(pos -> entity.m_9236_().m_46961_(pos, true));
        }
    }

    @Override
    public int getDelay(RandomSource random, float modifier) {
        return Math.max(500, random.m_188503_(620)) - Mth.m_14143_((float)modifier) * 10;
    }
}

