/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.NonNullList
 *  net.minecraft.core.Vec3i
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
 *  net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket
 *  net.minecraft.server.level.ServerChunkCache
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.Container
 *  net.minecraft.world.ContainerHelper
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ContainerData
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.entity.ContainerOpenersCounter
 *  net.minecraft.world.level.block.entity.HopperBlockEntity
 *  net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.common.blockentity;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.block.WitheredPhlegmBlock;
import nonamecrackers2.witherstormmod.common.blockentity.inventory.WitheredPhlegmMenu;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlockEntityTypes;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;

public class WitheredPhlegmBlockEntity
extends RandomizableContainerBlockEntity {
    public static final int CONTAINER_SIZE = 25;
    private NonNullList<ItemStack> items = NonNullList.m_122780_((int)25, (Object)ItemStack.f_41583_);
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter(){

        protected void m_142148_(Level level, BlockPos pos, BlockState state, int i, int i2) {
        }

        protected void m_142292_(Level level, BlockPos pos, BlockState state) {
            WitheredPhlegmBlockEntity.this.playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_PHLEGM_BLOCK_OPEN.get());
        }

        protected void m_142289_(Level level, BlockPos pos, BlockState state) {
            WitheredPhlegmBlockEntity.this.playSound((SoundEvent)WitherStormModSoundEvents.WITHERED_PHLEGM_BLOCK_CLOSE.get());
        }

        protected boolean m_142718_(Player player) {
            AbstractContainerMenu abstractContainerMenu = player.f_36096_;
            if (abstractContainerMenu instanceof WitheredPhlegmMenu) {
                WitheredPhlegmMenu menu = (WitheredPhlegmMenu)abstractContainerMenu;
                return menu.getContainer() == WitheredPhlegmBlockEntity.this;
            }
            return false;
        }
    };
    private final ContainerData dataAccess = new ContainerData(){

        public void m_8050_(int slot, int data) {
            if (slot == 0) {
                WitheredPhlegmBlockEntity.this.storedExperience = data;
            }
        }

        public int m_6499_() {
            return 1;
        }

        public int m_6413_(int slot) {
            return slot == 0 ? WitheredPhlegmBlockEntity.this.storedExperience : 0;
        }
    };
    private int storedExperience;

    public WitheredPhlegmBlockEntity(BlockPos pos, BlockState state) {
        super((BlockEntityType)WitherStormModBlockEntityTypes.WITHERED_PHLEGM.get(), pos, state);
    }

    public int m_6643_() {
        return 25;
    }

    protected NonNullList<ItemStack> m_7086_() {
        return this.items;
    }

    protected void m_6520_(NonNullList<ItemStack> items) {
        this.items = items;
    }

    protected Component m_6820_() {
        return Component.m_237115_((String)"container.witherstormmod.phlegm_block");
    }

    protected AbstractContainerMenu m_6555_(int id, Inventory inventory) {
        return new WitheredPhlegmMenu(id, inventory, (Container)this, this.dataAccess);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, WitheredPhlegmBlockEntity entity) {
        if (!((Boolean)state.m_61143_((Property)WitheredPhlegmBlock.POWERED)).booleanValue() && !entity.m_7086_().stream().allMatch(Predicate.not(ItemStack::m_41619_))) {
            AABB box = new AABB(pos, pos.m_7918_(1, 1, 1));
            List items = level.m_45976_(ItemEntity.class, box.m_82400_(8.0));
            for (ItemEntity item : items) {
                Vec3 vec3 = Vec3.m_82512_((Vec3i)pos);
                double d = item.m_20182_().m_82554_(vec3);
                Vec3 delta = vec3.m_82546_(item.m_20182_()).m_82541_().m_82490_(Math.max(1.0 - d / 8.0, 0.1));
                delta = item.m_20184_().m_82542_(0.8, 1.0, 0.8).m_82549_(delta.m_82542_(1.0, 0.2, 1.0));
                item.m_20256_(delta);
                ((ServerChunkCache)item.m_20193_().m_7726_()).m_8445_((Entity)item, (Packet)new ClientboundSetEntityMotionPacket((Entity)item));
                if (!item.m_20191_().m_82381_(box.m_82400_(0.5)) || !HopperBlockEntity.m_59331_((Container)entity, (ItemEntity)item)) continue;
                level.m_5594_(null, pos, SoundEvents.f_12019_, SoundSource.BLOCKS, 1.0f, 1.0f);
            }
        }
    }

    public void m_142466_(CompoundTag tag) {
        super.m_142466_(tag);
        this.items = NonNullList.m_122780_((int)this.m_6643_(), (Object)ItemStack.f_41583_);
        if (!this.m_59631_(tag)) {
            ContainerHelper.m_18980_((CompoundTag)tag, this.items);
        }
        this.storedExperience = tag.m_128451_("StoredXp");
    }

    protected void m_183515_(CompoundTag tag) {
        super.m_183515_(tag);
        if (!this.m_59634_(tag)) {
            ContainerHelper.m_18973_((CompoundTag)tag, this.items);
        }
        tag.m_128405_("StoredXp", this.storedExperience);
    }

    private void playSound(SoundEvent event) {
        this.f_58857_.m_5594_(null, this.m_58899_(), event, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    public void m_5856_(Player player) {
        if (!this.f_58859_ && !player.m_5833_()) {
            this.openersCounter.m_155452_(player, this.m_58904_(), this.m_58899_(), this.m_58900_());
        }
    }

    public void m_5785_(Player player) {
        if (!this.f_58859_ && !player.m_5833_()) {
            this.openersCounter.m_155468_(player, this.m_58904_(), this.m_58899_(), this.m_58900_());
        }
    }

    public void recheckOpen() {
        if (!this.f_58859_) {
            this.openersCounter.m_155476_(this.m_58904_(), this.m_58899_(), this.m_58900_());
        }
    }

    public Packet<ClientGamePacketListener> m_58483_() {
        return ClientboundBlockEntityDataPacket.m_195640_((BlockEntity)this);
    }

    public CompoundTag m_5995_() {
        CompoundTag tag = new CompoundTag();
        ContainerHelper.m_18973_((CompoundTag)tag, this.items);
        return tag;
    }

    public void handleUpdateTag(CompoundTag tag) {
        ContainerHelper.m_18980_((CompoundTag)tag, this.items);
    }

    public void m_6596_() {
        super.m_6596_();
        if (this.f_58857_ != null) {
            this.f_58857_.m_7260_(this.m_58899_(), this.m_58900_(), this.m_58900_(), 3);
        }
    }

    public int getStoredXp() {
        return this.storedExperience;
    }
}

