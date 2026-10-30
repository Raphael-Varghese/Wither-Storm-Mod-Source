/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.AvoidEntityGoal
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.entity.goal;

import java.util.Comparator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class AvoidWitherStormGoal
extends AvoidEntityGoal<WitherStormEntity> {
    private final float smallPhaseMaxDist;

    public AvoidWitherStormGoal(PathfinderMob mob, float maxDist, double walkSpeedModifier, double sprintSpeedModifier) {
        super(mob, WitherStormEntity.class, maxDist, walkSpeedModifier, sprintSpeedModifier);
        this.smallPhaseMaxDist = maxDist / 8.0f;
    }

    public boolean m_8036_() {
        AABB box = this.f_25015_.m_20191_().m_82400_((double)this.f_25017_);
        List storms = this.f_25015_.m_9236_().m_6443_(WitherStormEntity.class, box, storm -> storm != this.f_25015_ && storm.m_6084_());
        storms.sort(Comparator.comparingDouble(arg_0 -> ((PathfinderMob)this.f_25015_).m_20280_(arg_0)));
        this.f_25016_ = storms.stream().findFirst().orElse(null);
        if (this.f_25016_ != null && (!((Boolean)TractorBeamHelper.isInsideTractorBeam((Entity)this.f_25015_, (WitherStormEntity)this.f_25016_, 4.0).getFirst()).booleanValue() || this.f_25015_.m_20096_()) && WorldUtil.canSeeOrIsNotInASmallArea((Entity)this.f_25016_, (Entity)this.f_25015_)) {
            BlockPos portalPos = null;
            if (((Boolean)WitherStormModConfig.SERVER.mobsRunIntoPortals.get()).booleanValue()) {
                portalPos = this.getNearestLoadedBlockPos(this.f_25015_.m_20182_(), 16, Blocks.f_50142_);
            }
            if ((((WitherStormEntity)this.f_25016_).getPhase() >= 4 || (((WitherStormEntity)this.f_25016_).getPhase() == 2 || ((WitherStormEntity)this.f_25016_).getPhase() == 3) && this.f_25015_.m_20191_().m_82400_((double)this.smallPhaseMaxDist).m_82390_(((WitherStormEntity)this.f_25016_).m_20182_())) && !((WitherStormEntity)this.f_25016_).isDeadOrPlayingDead()) {
                Vec3 vec3;
                Vec3 vec32 = vec3 = portalPos != null ? new Vec3((double)portalPos.m_123341_() + 0.5, (double)(portalPos.m_123342_() + 1), (double)portalPos.m_123343_() + 0.5) : this.f_25015_.m_20182_().m_82546_(((WitherStormEntity)this.f_25016_).m_20182_()).m_82541_().m_82490_(16.0).m_82549_(this.f_25015_.m_20182_());
                if (this.f_25018_ == null || this.f_25018_.m_77392_()) {
                    this.f_25018_ = this.f_25019_.m_26524_(vec3.f_82479_, vec3.f_82480_, vec3.f_82481_, 0);
                    this.f_25015_.m_6710_(null);
                }
                return this.f_25018_ != null;
            }
        }
        return false;
    }

    public boolean m_8045_() {
        boolean flag = this.f_25016_ == null || (Boolean)TractorBeamHelper.isInsideTractorBeam((Entity)this.f_25015_, (WitherStormEntity)this.f_25016_, 4.0).getFirst() == false || this.f_25015_.m_20096_();
        return super.m_8045_() && flag;
    }

    public BlockPos getNearestLoadedBlockPos(Vec3 pos, int radius, Block block) {
        int minX = (int)pos.f_82479_ - radius;
        int minY = (int)pos.f_82480_ - 4;
        int minZ = (int)pos.f_82481_ - radius;
        int maxX = (int)pos.f_82479_ + radius;
        int maxY = (int)pos.f_82480_ + 4;
        int maxZ = (int)pos.f_82481_ + radius;
        for (BlockPos blockPos : BlockPos.m_121940_((BlockPos)new BlockPos(minX, minY, minZ), (BlockPos)new BlockPos(maxX, maxY, maxZ))) {
            BlockState blockState = this.f_25015_.m_9236_().m_8055_(blockPos);
            if (blockState.m_60734_() != block) continue;
            return blockPos;
        }
        return null;
    }
}

