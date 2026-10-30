/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 */
package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm;

import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.common.capability.WitherStormBowelsManager;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;

public class BowelsInstanceManager {
    private final WitherStormEntity storm;
    @Nullable
    private WitherStormBowelsManager.BowelsInstance instance;
    @Nullable
    private CommandBlockEntity commandBlock;

    public BowelsInstanceManager(WitherStormEntity storm) {
        this.storm = storm;
    }

    public void loadInstance() {
        ServerLevel bowels = WitherStormMod.bowels((ServerLevel)this.storm.m_9236_());
        bowels.getCapability(WitherStormModCapabilities.BOWELS_MANAGER).ifPresent(manager -> {
            WitherStormBowelsManager.BowelsInstance instance = manager.getOrCreateInstanceFor(this.storm);
            if (instance != null) {
                manager.add(instance);
                instance.setup(bowels);
                instance.doChunkLoading(bowels);
                this.instance = instance;
            }
        });
    }

    public void tick() {
        if (this.instance != null) {
            if (this.instance.isCompleted()) {
                this.commandBlock = null;
                this.instance = null;
                this.loadInstance();
            }
            if (this.commandBlock == null && this.instance.hasPreparedArena()) {
                Entity entity;
                ServerLevel bowels = WitherStormMod.bowels((ServerLevel)this.storm.m_9236_());
                UUID uuid = this.instance.getCommandBlockUUID();
                if (uuid != null && (entity = bowels.m_8791_(this.instance.getCommandBlockUUID())) instanceof CommandBlockEntity) {
                    this.commandBlock = (CommandBlockEntity)entity;
                }
            }
        }
    }

    @Nullable
    public CommandBlockEntity getCommandBlock() {
        return this.commandBlock;
    }

    @Nullable
    public WitherStormBowelsManager.BowelsInstance getBowelsInstance() {
        return this.instance;
    }
}

