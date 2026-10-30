/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.Container
 *  net.minecraft.world.SimpleContainer
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ContainerData
 *  net.minecraft.world.inventory.MenuType
 *  net.minecraft.world.inventory.SimpleContainerData
 *  net.minecraft.world.inventory.Slot
 *  net.minecraft.world.item.ItemStack
 */
package nonamecrackers2.witherstormmod.common.blockentity.inventory;

import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import nonamecrackers2.witherstormmod.common.init.WitherStormModMenuTypes;

public class WitheredPhlegmMenu
extends AbstractContainerMenu {
    private final Container container;
    private final ContainerData data;

    public WitheredPhlegmMenu(int id, Inventory inventory) {
        this(id, inventory, (Container)new SimpleContainer(25), (ContainerData)new SimpleContainerData(1));
    }

    public Container getContainer() {
        return this.container;
    }

    public WitheredPhlegmMenu(int id, Inventory playerInventory, Container container, ContainerData data) {
        super((MenuType)WitherStormModMenuTypes.WITHERED_PHLEGM.get(), id);
        this.container = container;
        this.data = data;
        container.m_5856_(playerInventory.f_35978_);
        int rowsColumns = Mth.m_14165_((double)Math.sqrt(container.m_6643_()));
        int totalSlots = 0;
        block0: for (int y = 0; y < rowsColumns; ++y) {
            for (int x = 0; x < rowsColumns; ++x) {
                if (totalSlots >= container.m_6643_()) break block0;
                this.m_38897_(new Slot(container, totalSlots, 44 + x * 18, 18 + y * 18));
                ++totalSlots;
            }
        }
        int yOffset = 22;
        for (int l = 0; l < 3; ++l) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.m_38897_(new Slot((Container)playerInventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + yOffset));
            }
        }
        for (int i1 = 0; i1 < 9; ++i1) {
            this.m_38897_(new Slot((Container)playerInventory, i1, 8 + i1 * 18, 161 + yOffset));
        }
        this.m_38884_(data);
    }

    public boolean m_6875_(Player player) {
        return this.container.m_6542_(player);
    }

    public void m_6877_(Player player) {
        super.m_6877_(player);
        this.container.m_5785_(player);
    }

    public ItemStack m_7648_(Player player, int slotId) {
        ItemStack stack = ItemStack.f_41583_;
        Slot slot = (Slot)this.f_38839_.get(slotId);
        if (slot != null && slot.m_6657_()) {
            ItemStack inSlot = slot.m_7993_();
            stack = inSlot.m_41777_();
            if (slotId < this.container.m_6643_() ? !this.m_38903_(inSlot, this.container.m_6643_(), this.f_38839_.size(), true) : !this.m_38903_(inSlot, 0, this.container.m_6643_(), false)) {
                return ItemStack.f_41583_;
            }
            if (inSlot.m_41619_()) {
                slot.m_269060_(ItemStack.f_41583_);
            } else {
                slot.m_6654_();
            }
        }
        return stack;
    }

    public int getXp() {
        return this.data.m_6413_(0);
    }
}

