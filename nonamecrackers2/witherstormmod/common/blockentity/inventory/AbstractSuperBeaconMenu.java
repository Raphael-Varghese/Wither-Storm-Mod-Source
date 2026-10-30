/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  javax.annotation.Nullable
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.Container
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ContainerData
 *  net.minecraft.world.inventory.ContainerLevelAccess
 *  net.minecraft.world.inventory.MenuType
 *  net.minecraft.world.inventory.SimpleContainerData
 *  net.minecraft.world.item.ItemStack
 */
package nonamecrackers2.witherstormmod.common.blockentity.inventory;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractSuperBeaconMenu
extends AbstractContainerMenu {
    protected final ContainerLevelAccess access;
    private final ContainerData data;
    @Nullable
    private final Consumer<ServerPlayer> powerUp;
    private final Set<MobEffect> validEffects;

    public AbstractSuperBeaconMenu(MenuType<? extends AbstractSuperBeaconMenu> type, int id, Container container) {
        this(type, id, container, (ContainerData)new SimpleContainerData(4), ContainerLevelAccess.f_39287_, null, (Set<MobEffect>)ImmutableSet.of());
    }

    public AbstractSuperBeaconMenu(MenuType<? extends AbstractSuperBeaconMenu> type, int id, Container container, ContainerData data, ContainerLevelAccess access, @Nullable Consumer<ServerPlayer> powerUp, Set<MobEffect> validEffects) {
        super(type, id);
        this.access = access;
        this.data = data;
        this.m_38884_(data);
        this.powerUp = powerUp;
        this.validEffects = validEffects;
    }

    public void m_7511_(int slot, int value) {
        super.m_7511_(slot, value);
        this.m_38946_();
    }

    public int getLevel() {
        return this.data.m_6413_(0);
    }

    @Nullable
    public MobEffect getPrimaryEffect() {
        return MobEffect.m_19453_((int)this.data.m_6413_(1));
    }

    public void updateEffects(int effectId) {
        this.data.m_8050_(1, effectId);
    }

    public void activateCooldown(int time) {
        this.data.m_8050_(3, time);
    }

    public int getCooldown() {
        return this.data.m_6413_(3);
    }

    public void setShowArea(boolean flag) {
        this.data.m_8050_(2, flag ? 1 : 0);
    }

    public boolean shouldShowArea() {
        return this.data.m_6413_(2) == 1;
    }

    public void doPowerUp(ServerPlayer player) {
        if (this.powerUp != null) {
            this.powerUp.accept(player);
        }
    }

    public Set<MobEffect> getValidEffects() {
        return this.validEffects;
    }

    public ItemStack m_7648_(Player player, int slot) {
        return ItemStack.f_41583_;
    }
}

