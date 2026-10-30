/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.ai.goal.Goal$Flag
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 */
package nonamecrackers2.witherstormmod.common.entity.goal;

import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import nonamecrackers2.witherstormmod.common.entity.SickenedBee;
import nonamecrackers2.witherstormmod.common.tags.WitherStormModBlockTags;
import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;

public class SickenedBeeConversionGoal
extends Goal {
    private static final int COOLDOWN = 200;
    private final SickenedBee bee;
    private BlockPos targetPos;
    private int conversionTimer;
    private int conversionCooldown;

    public SickenedBeeConversionGoal(SickenedBee bee) {
        this.bee = bee;
        this.m_7021_(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean m_8036_() {
        return this.findConvertibleBlock() && this.conversionCooldown <= 0;
    }

    public boolean m_8045_() {
        return this.targetPos != null && this.bee.m_20275_(this.targetPos.m_123341_(), this.targetPos.m_123342_(), this.targetPos.m_123343_()) < 6.0 && this.conversionTimer > 0;
    }

    public void m_8056_() {
        this.bee.m_21573_().m_26519_((double)this.targetPos.m_123341_(), (double)this.targetPos.m_123342_(), (double)this.targetPos.m_123343_(), 0.75);
        this.conversionTimer = 20;
    }

    public void m_8041_() {
        this.targetPos = null;
        this.conversionTimer = 0;
    }

    public void m_8037_() {
        if (this.conversionCooldown > 0) {
            --this.conversionCooldown;
        }
        if (this.targetPos != null) {
            BlockState targetBlockState = this.bee.m_9236_().m_8055_(this.targetPos);
            if (targetBlockState.m_204336_(WitherStormModBlockTags.SICKENED_BEE_CAN_CONVERT) && this.bee.m_20275_(this.targetPos.m_123341_(), this.targetPos.m_123342_(), this.targetPos.m_123343_()) <= 6.0) {
                --this.conversionTimer;
                if (this.conversionTimer <= 0) {
                    this.taintBlock();
                    this.m_8041_();
                    this.conversionCooldown = 200;
                }
            } else {
                this.m_8041_();
            }
        }
    }

    private boolean findConvertibleBlock() {
        Level level = this.bee.m_9236_();
        BlockPos blockPos = this.bee.m_20183_();
        for (int x = -6; x <= 6; ++x) {
            for (int y = -6; y <= 6; ++y) {
                for (int z = -6; z <= 6; ++z) {
                    BlockPos pos = blockPos.m_7918_(x, y, z);
                    BlockState state = level.m_8055_(pos);
                    if (!state.m_204336_(WitherStormModBlockTags.SICKENED_BEE_CAN_CONVERT)) continue;
                    this.targetPos = pos;
                    return true;
                }
            }
        }
        return false;
    }

    private void taintBlock() {
        if (this.targetPos != null) {
            WorldTainting.getInstance().convertBlock(this.targetPos, this.bee.m_9236_());
        }
    }
}

