/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.item.AmuletItem;
import nonamecrackers2.witherstormmod.common.item.GoldenAppleStewItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Mob.class})
public abstract class MixinMob
extends LivingEntity {
    private MixinMob(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }

    @Inject(method={"checkAndHandleImportantInteractions"}, at={@At(value="HEAD")}, cancellable=true)
    private void checkAndHandleImportantInteractionsHead(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> ci) {
        InteractionResult result;
        ItemStack stack = player.m_21120_(hand);
        if ((stack.m_41720_() instanceof AmuletItem || stack.m_41720_() instanceof GoldenAppleStewItem) && (result = stack.m_41647_(player, (LivingEntity)this, hand)).m_19077_()) {
            ci.setReturnValue((Object)result);
        }
    }

    @Inject(method={"isSunBurnTick"}, at={@At(value="HEAD")}, cancellable=true)
    private void isSunBurnTickHead(CallbackInfoReturnable<Boolean> ci) {
        if (!this.m_9236_().f_46443_) {
            double distance = 200.0;
            for (WitherStormEntity storm : this.m_9236_().m_45976_(WitherStormEntity.class, this.m_20191_().m_82377_(distance, (double)this.m_9236_().m_141928_(), distance))) {
                if (!(storm.m_20182_().m_82546_(this.m_20182_()).m_165924_() <= distance) || storm.isDeadOrPlayingDead() || storm.getPhase() <= 5) continue;
                ci.setReturnValue((Object)false);
            }
        }
    }
}

