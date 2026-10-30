/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.event.TickEvent$PlayerTickEvent
 */
package nonamecrackers2.witherstormmod.client.capability;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.TickEvent;
import nonamecrackers2.witherstormmod.api.common.entity.WitherStormBase;
import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
import nonamecrackers2.witherstormmod.common.util.TractorBeamHelper;

public class PlayerTractorBeamEffects {
    private final LocalPlayer player;
    private int ticksInTractorBeam;

    public PlayerTractorBeamEffects(LocalPlayer player) {
        this.player = player;
    }

    public PlayerTractorBeamEffects() {
        this.player = null;
    }

    public <T extends LivingEntity> void tick() {
        boolean flag = false;
        if (EntitySelector.f_20408_.test(this.player)) {
            for (Entity entity : this.player.f_108545_.m_104735_()) {
                WitherStormBase storm;
                LivingEntity t;
                LivingEntity living;
                if (!(entity instanceof LivingEntity) || !((living = (LivingEntity)entity) instanceof WitherStormBase) || !((Boolean)TractorBeamHelper.isInsideTractorBeam((Entity)this.player, t = (LivingEntity)(storm = (WitherStormBase)living), 4.0).getFirst()).booleanValue()) continue;
                flag = true;
                break;
            }
        }
        if (flag) {
            if (this.ticksInTractorBeam < 240) {
                ++this.ticksInTractorBeam;
            }
        } else if (this.ticksInTractorBeam > 0) {
            --this.ticksInTractorBeam;
        }
    }

    public int getTicksInTractorBeam() {
        return this.ticksInTractorBeam;
    }

    public float getPercent() {
        return (float)Math.min(this.getTicksInTractorBeam(), 120) / 240.0f;
    }

    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            event.player.getCapability(WitherStormModClientCapabilities.TRACTOR_BEAM_EFFECTS).ifPresent(PlayerTractorBeamEffects::tick);
        }
    }
}

