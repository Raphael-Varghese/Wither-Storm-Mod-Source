/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.phys.AABB
 */
package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import nonamecrackers2.witherstormmod.common.blockentity.FormidibombBlockEntity;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.item.FormidibombItem;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class IgnoredTargetsManager {
    public static final int DEFAULT_TIME = 80;
    private final WitherStormEntity storm;
    private final List<Ignored> entities = Lists.newArrayList();
    private final List<AABB> restrictedTargetingRegions = Lists.newArrayList();

    public IgnoredTargetsManager(WitherStormEntity storm) {
        this.storm = storm;
    }

    public static double getTargetRestrictionSize(Entity entity) {
        if (entity instanceof FormidibombEntity) {
            FormidibombEntity formidibomb = (FormidibombEntity)entity;
            if (formidibomb.m_6084_() && formidibomb.getStartFuse() > 0) {
                return 20.0;
            }
        } else if (entity instanceof Player) {
            Player player = (Player)entity;
            for (ItemStack stack : player.m_150109_().f_35974_) {
                FormidibombItem item;
                Item item2 = stack.m_41720_();
                if (!(item2 instanceof FormidibombItem) || (item = (FormidibombItem)item2).getStartFuse(stack) <= 0) continue;
                return 20.0;
            }
        } else if (entity instanceof WitheredSymbiontEntity) {
            return 50.0;
        }
        return -1.0;
    }

    public void tick() {
        Iterator<Ignored> iterator = this.entities.iterator();
        while (iterator.hasNext()) {
            Ignored ignored = iterator.next();
            if (ignored.ticks <= 0) continue;
            --ignored.ticks;
            if (ignored.ticks != 0) continue;
            iterator.remove();
        }
        this.restrictedTargetingRegions.clear();
        AABB searchBox = this.storm.getSearchBox();
        for (BlockEntity tile : WorldUtil.getBlockEntitiesInAABB(this.storm.m_9236_(), searchBox)) {
            FormidibombBlockEntity formidibomb;
            if (!(tile instanceof FormidibombBlockEntity) || (formidibomb = (FormidibombBlockEntity)tile).getStartFuse() <= 0) continue;
            this.restrictedTargetingRegions.add(new AABB(formidibomb.m_58899_()).m_82400_(20.0));
        }
        for (Entity entity : this.storm.m_9236_().m_45976_(Entity.class, searchBox)) {
            double size = IgnoredTargetsManager.getTargetRestrictionSize(entity);
            if (size == -1.0) continue;
            this.restrictedTargetingRegions.add(new AABB(entity.m_20183_()).m_82400_(size));
        }
    }

    public void addEntityToIgnore(Entity entity) {
        this.addEntityToIgnore(entity, 80);
    }

    public void addEntityToIgnore(Entity entity, int time) {
        if (!this.shouldIgnoreEntity(entity)) {
            this.entities.add(new Ignored(entity.m_20148_(), time));
        }
    }

    public boolean shouldIgnoreEntity(Entity entity) {
        return this.entities.stream().anyMatch(e -> e.entity.equals(entity.m_20148_())) || this.restrictedTargetingRegions.stream().anyMatch(aabb -> aabb.m_82390_(entity.m_146892_()));
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        ListTag list = new ListTag();
        for (Ignored ignored : this.entities) {
            CompoundTag entry = new CompoundTag();
            entry.m_128362_("UUID", ignored.entity);
            entry.m_128405_("Ticks", ignored.ticks);
            list.add((Object)entry);
        }
        tag.m_128365_("Entities", (Tag)list);
        return tag;
    }

    public void read(CompoundTag tag) {
        this.entities.clear();
        ListTag list = tag.m_128437_("Entities", 10);
        for (int i = 0; i < list.size(); ++i) {
            CompoundTag entry = list.m_128728_(i);
            UUID id = entry.m_128342_("UUID");
            int ticks = entry.m_128451_("Ticks");
            this.entities.add(new Ignored(id, ticks));
        }
    }

    private class Ignored {
        private final UUID entity;
        private int ticks;

        public Ignored(UUID entity, int time) {
            this.entity = entity;
            this.ticks = time;
        }
    }
}

