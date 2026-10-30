/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.player.Player
 */
package nonamecrackers2.witherstormmod.common.capability;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import nonamecrackers2.witherstormmod.common.capability.EntityCapability;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class PlayerWitherStormData
extends EntityCapability<PlayerWitherStormData, Player> {
    private static final int AREA_CHECK_INTERVAL = 80;
    private List<Snapshot> data = new ArrayList<Snapshot>();
    private int invulnerableTime;
    private boolean hasActivatedSuperBeacon;
    private UUID killedByStorm;
    private int nextAreaCheck;
    private boolean isInAnOpenArea;

    public PlayerWitherStormData(Player player) {
        super(player);
    }

    public PlayerWitherStormData() {
    }

    @Override
    public void tick() {
        for (int i = 0; i < this.data.size(); ++i) {
            Snapshot snapshot = this.data.get(i);
            snapshot.tick();
            if (!snapshot.shouldRemove) continue;
            this.data.remove(i);
        }
        if (this.invulnerableTime > 0) {
            --this.invulnerableTime;
        }
        if (this.nextAreaCheck > 0) {
            --this.nextAreaCheck;
        }
        if (this.nextAreaCheck == 0) {
            this.isInAnOpenArea = WorldUtil.isInAnOpenArea(this.entity);
            this.nextAreaCheck = 80;
        }
    }

    @Override
    public CompoundTag write() {
        CompoundTag compound = new CompoundTag();
        ListTag list = new ListTag();
        for (Snapshot snapshot : this.data) {
            list.add((Object)snapshot.write());
        }
        compound.m_128365_("Data", (Tag)list);
        compound.m_128405_("InvulnerableTime", this.invulnerableTime);
        compound.m_128379_("HasActivatedSuperBeacon", this.hasActivatedSuperBeacon);
        if (this.killedByStorm != null) {
            compound.m_128362_("KilledByStorm", this.killedByStorm);
        }
        return compound;
    }

    @Override
    public void read(CompoundTag compound) {
        ListTag list = compound.m_128437_("Data", 10);
        for (int i = 0; i < list.size(); ++i) {
            CompoundTag snapshot = list.m_128728_(i);
            this.data.add(Snapshot.read(snapshot));
        }
        this.invulnerableTime = compound.m_128451_("InvulnerableTime");
        this.hasActivatedSuperBeacon = compound.m_128471_("HasActivatedSuperBeacon");
        if (compound.m_128403_("KilledByStorm")) {
            this.killedByStorm = compound.m_128342_("KilledByStorm");
        }
    }

    @Override
    public void copyFrom(PlayerWitherStormData instance) {
        this.data = instance.data;
        this.invulnerableTime = instance.invulnerableTime;
        this.killedByStorm = instance.killedByStorm;
    }

    public boolean hasRecentlySummonedSymbiont(WitherStormEntity entity) {
        Snapshot snapshot = this.getFor(entity);
        return snapshot != null ? snapshot.getSymbiontSummonCooldown() > 0 : false;
    }

    public boolean hasKilledSymbiontRecently() {
        return this.invulnerableTime > 0;
    }

    public boolean hasChangedPhase(WitherStormEntity entity) {
        Snapshot snapshot = this.getFor(entity);
        return snapshot != null ? snapshot.getLastPhase() != entity.getPhase() : true;
    }

    public void markSummonedSymbiont(WitherStormEntity entity) {
        Snapshot snapshot = this.getFor(entity);
        int symbiontSummonCooldown = Mth.m_14045_((int)((Integer)WitherStormModConfig.SERVER.playerSummoningDelay.get()), (int)1, (int)60) * 1200 + ((Player)this.entity).m_217043_().m_188503_(2400);
        if (snapshot != null) {
            snapshot.symbiontSummonCooldown = symbiontSummonCooldown;
            snapshot.lastPhase = entity.getPhase();
        } else {
            this.data.add(new Snapshot(entity.m_20148_(), entity.getPhase(), symbiontSummonCooldown));
        }
    }

    public void markKilledSymbiont(WitherStormEntity entity) {
        int symbiontSummonCooldown = Mth.m_14045_((int)((Integer)WitherStormModConfig.SERVER.playerSummoningDelayOnKill.get()), (int)1, (int)60) * 1200 + ((Player)this.entity).m_217043_().m_188503_(24000);
        Snapshot snapshot = this.getFor(entity);
        if (snapshot != null) {
            snapshot.symbiontSummonCooldown = symbiontSummonCooldown;
        } else {
            this.data.add(new Snapshot(entity.m_20148_(), entity.getPhase(), symbiontSummonCooldown));
        }
    }

    public void makeInvulnerable(int time) {
        this.invulnerableTime = time;
    }

    @Nullable
    public Snapshot getFor(WitherStormEntity entity) {
        for (Snapshot snapshot : this.data) {
            if (snapshot.lastStorm == null || !snapshot.lastStorm.equals(entity.m_20148_())) continue;
            return snapshot;
        }
        return null;
    }

    public boolean hasActivatedSuperBeacon() {
        return this.hasActivatedSuperBeacon;
    }

    public void setActivatedSuperBeacon(boolean flag) {
        this.hasActivatedSuperBeacon = flag;
    }

    public UUID getKilledByStorm() {
        return this.killedByStorm;
    }

    public void setKilledByStorm(@Nullable UUID id) {
        this.killedByStorm = id;
    }

    public boolean isInAnOpenArea() {
        return this.isInAnOpenArea;
    }

    public static class Snapshot {
        @Nullable
        private UUID lastStorm;
        private int lastPhase;
        private int symbiontSummonCooldown;
        public boolean shouldRemove;

        public Snapshot(@Nullable UUID lastStorm, int lastPhase, int cooldown) {
            this.lastStorm = lastStorm;
            this.lastPhase = lastPhase;
            this.symbiontSummonCooldown = cooldown;
        }

        @Nullable
        public UUID getLastStorm() {
            return this.lastStorm;
        }

        public int getLastPhase() {
            return this.lastPhase;
        }

        public int getSymbiontSummonCooldown() {
            return this.symbiontSummonCooldown;
        }

        public void tick() {
            if (this.symbiontSummonCooldown > 0) {
                --this.symbiontSummonCooldown;
            } else {
                this.shouldRemove = true;
            }
        }

        public CompoundTag write() {
            CompoundTag data = new CompoundTag();
            if (this.lastStorm != null) {
                data.m_128362_("Summoner", this.lastStorm);
            }
            data.m_128405_("SummonerPhase", this.lastPhase);
            data.m_128405_("SummoningCooldown", this.symbiontSummonCooldown);
            return data;
        }

        public static Snapshot read(CompoundTag compound) {
            UUID lastStorm = null;
            if (compound.m_128441_("Summoner")) {
                lastStorm = compound.m_128342_("Summoner");
            }
            int lastPhase = compound.m_128451_("SummonerPhase");
            int summoningCooldown = compound.m_128451_("SummoningCooldown");
            return new Snapshot(lastStorm, lastPhase, summoningCooldown);
        }

        public String toString() {
            return "Snapshot[UUID=" + this.lastStorm + ", lastPhase" + this.lastPhase + ", summoningCooldown" + this.symbiontSummonCooldown + "]";
        }
    }
}

