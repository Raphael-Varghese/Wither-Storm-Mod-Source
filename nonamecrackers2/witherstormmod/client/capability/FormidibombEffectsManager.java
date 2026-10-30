/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.resources.sounds.TickableSoundInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 */
package nonamecrackers2.witherstormmod.client.capability;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.TickableSoundInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import nonamecrackers2.witherstormmod.client.audio.FormidibombFuseLoop;
import nonamecrackers2.witherstormmod.client.audio.ISoundManager;
import nonamecrackers2.witherstormmod.common.util.IFormidibomb;
import nonamecrackers2.witherstormmod.common.util.WorldUtil;

public class FormidibombEffectsManager
implements ISoundManager {
    private final Minecraft minecraft;
    @Nullable
    private IFormidibomb formidibomb;
    private final List<FormidibombFuseLoop> loops = Lists.newArrayList();

    public FormidibombEffectsManager(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public FormidibombEffectsManager() {
        this.minecraft = null;
    }

    @Override
    public void tick() {
        IFormidibomb current;
        ClientLevel world = this.minecraft.f_91073_;
        IFormidibomb formidibomb = null;
        for (Entity entity : world.m_104735_()) {
            if (!(entity instanceof IFormidibomb)) continue;
            current = (IFormidibomb)entity;
            formidibomb = this.compare(formidibomb, current);
            if (this.alreadyHasLoop(current)) continue;
            this.putLoop(new FormidibombFuseLoop(current));
        }
        for (BlockEntity tile : WorldUtil.getBlockEntitiesInAABB((Level)world, this.minecraft.f_91074_.m_20191_().m_82400_(50.0))) {
            if (!(tile instanceof IFormidibomb)) continue;
            current = (IFormidibomb)tile;
            formidibomb = this.compare(formidibomb, current);
            if (this.alreadyHasLoop(current)) continue;
            this.putLoop(new FormidibombFuseLoop(current));
        }
        this.formidibomb = formidibomb;
        for (int i = 0; i < this.loops.size(); ++i) {
            FormidibombFuseLoop loop = this.loops.get(i);
            if (!loop.m_7801_() && !loop.shouldStop()) continue;
            this.loops.remove(i);
        }
    }

    public IFormidibomb compare(@Nullable IFormidibomb previous, IFormidibomb current) {
        if (previous != null) {
            if (current.getStartFuse() > 0) {
                int percentage = (current.getStartFuse() - current.getFuseLife()) / current.getStartFuse();
                if (previous.getStartFuse() > 0) {
                    if (percentage > (previous.getStartFuse() - previous.getFuseLife()) / previous.getStartFuse()) {
                        return current;
                    }
                    return previous;
                }
                return current;
            }
            return previous;
        }
        return current;
    }

    private boolean alreadyHasLoop(IFormidibomb formidibomb) {
        boolean flag = false;
        for (FormidibombFuseLoop loop : this.loops) {
            if (loop.formidibomb != formidibomb) continue;
            flag = true;
            break;
        }
        return flag;
    }

    private void putLoop(FormidibombFuseLoop loop) {
        if (!this.loops.contains(loop)) {
            this.loops.add(loop);
            this.minecraft.m_91106_().m_120372_((TickableSoundInstance)loop);
        }
    }

    public int getLife() {
        return this.formidibomb != null ? this.formidibomb.getFuseLife() : 0;
    }

    public int getStartFuse() {
        return this.formidibomb != null ? this.formidibomb.getStartFuse() : 0;
    }

    @Override
    public void refresh() {
        ArrayList<FormidibombFuseLoop> soundsToAdd = new ArrayList<FormidibombFuseLoop>();
        for (int i = 0; i < this.loops.size(); ++i) {
            FormidibombFuseLoop loop = this.loops.get(i);
            FormidibombFuseLoop newLoop = new FormidibombFuseLoop(loop.formidibomb);
            loop.forceStop();
            soundsToAdd.add(newLoop);
            this.minecraft.m_91106_().m_120372_((TickableSoundInstance)newLoop);
        }
        this.loops.clear();
        this.loops.addAll(soundsToAdd);
    }
}

