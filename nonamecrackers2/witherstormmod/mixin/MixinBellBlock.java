/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.BellBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package nonamecrackers2.witherstormmod.mixin;

import java.util.Comparator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCriteriaTriggers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={BellBlock.class})
public class MixinBellBlock {
    @Inject(method={"onHit"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/player/Player;awardStat(Lnet/minecraft/resources/ResourceLocation;)V")})
    public void onHitInvoke(Level level, BlockState state, BlockHitResult hitResult, @Nullable Player player, boolean flag, CallbackInfoReturnable<Boolean> ci) {
        AABB box;
        List storms;
        if (!level.f_46443_ && !(storms = level.m_45976_(WitherStormEntity.class, box = player.m_20191_().m_82400_(300.0))).isEmpty()) {
            storms.sort(Comparator.comparingDouble(arg_0 -> ((Player)player).m_20280_(arg_0)));
            WitherStormEntity storm = (WitherStormEntity)storms.get(0);
            WitherStormModCriteriaTriggers.RING_BELL_NEAR_STORM.trigger((ServerPlayer)player, storm);
        }
    }
}

