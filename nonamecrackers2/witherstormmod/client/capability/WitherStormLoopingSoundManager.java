/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.sounds.TickableSoundInstance
 *  net.minecraft.sounds.SoundEvent
 */
package nonamecrackers2.witherstormmod.client.capability;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.TickableSoundInstance;
import net.minecraft.sounds.SoundEvent;
import nonamecrackers2.witherstormmod.client.audio.ISoundManager;
import nonamecrackers2.witherstormmod.client.audio.WitherStormSoundLoop;
import nonamecrackers2.witherstormmod.client.audio.WitherStormTractorBeamLoop;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;

public class WitherStormLoopingSoundManager
implements ISoundManager {
    private final Minecraft minecraft;
    private final Int2ObjectMap<WitherStormSoundLoop> sounds = new Int2ObjectOpenHashMap();
    private final Int2ObjectMap<WitherStormSoundLoop> pendingReplacements = new Int2ObjectOpenHashMap();
    private final Int2ObjectMap<WitherStormSoundLoop> additional = new Int2ObjectOpenHashMap();
    private final Int2ObjectMap<WitherStormTractorBeamLoop[]> beamLoops = new Int2ObjectOpenHashMap();

    public WitherStormLoopingSoundManager(Minecraft mc) {
        this.minecraft = mc;
    }

    public WitherStormLoopingSoundManager() {
        this.minecraft = null;
    }

    @Override
    public void tick() {
        this.applyPendingReplacements();
        this.removeAllPendingRemovals();
    }

    private void applyPendingReplacements() {
        for (Int2ObjectMap.Entry entry : this.pendingReplacements.int2ObjectEntrySet()) {
            ((WitherStormSoundLoop)this.sounds.get(entry.getIntKey())).stopSound();
            WitherStormSoundLoop replacement = (WitherStormSoundLoop)entry.getValue();
            this.sounds.replace(entry.getIntKey(), (Object)replacement);
            this.pendingReplacements.remove(entry.getIntKey());
            this.minecraft.m_91106_().m_120372_((TickableSoundInstance)replacement);
        }
    }

    private void removeAllPendingRemovals() {
        ObjectIterator iterator = this.sounds.int2ObjectEntrySet().iterator();
        while (iterator.hasNext()) {
            Int2ObjectMap.Entry entry = (Int2ObjectMap.Entry)iterator.next();
            WitherStormSoundLoop sound = (WitherStormSoundLoop)entry.getValue();
            if (!sound.m_7801_()) continue;
            iterator.remove();
        }
        for (Int2ObjectMap.Entry entry : this.beamLoops.int2ObjectEntrySet()) {
            WitherStormTractorBeamLoop[] loops = (WitherStormTractorBeamLoop[])entry.getValue();
            for (int i = 0; i < loops.length; ++i) {
                if (loops[i] == null || !loops[i].m_7801_()) continue;
                loops[i] = null;
            }
        }
        ObjectIterator iterator2 = this.additional.int2ObjectEntrySet().iterator();
        while (iterator2.hasNext()) {
            Int2ObjectMap.Entry entry = (Int2ObjectMap.Entry)iterator2.next();
            WitherStormSoundLoop sound = (WitherStormSoundLoop)entry.getValue();
            if (!sound.m_7801_()) continue;
            iterator2.remove();
        }
    }

    public void putSound(int id, WitherStormSoundLoop sound) {
        if (!this.sounds.containsKey(id)) {
            this.minecraft.m_91106_().m_120372_((TickableSoundInstance)sound);
            this.sounds.put(id, (Object)sound);
        }
    }

    public void putAdditionalSound(int id, WitherStormSoundLoop sound) {
        if (!this.additional.containsKey(id)) {
            this.minecraft.m_91106_().m_120372_((TickableSoundInstance)sound);
            this.additional.put(id, (Object)sound);
        }
    }

    @Override
    public void refresh() {
        for (Int2ObjectMap.Entry entry : this.sounds.int2ObjectEntrySet()) {
            WitherStormSoundLoop sound = (WitherStormSoundLoop)entry.getValue();
            sound.forceStop();
            int key = entry.getIntKey();
            this.sounds.remove(entry.getIntKey());
            if (key == 0) continue;
            this.putSound(key, new WitherStormSoundLoop(sound.getPos(), sound.getSoundEvent()));
        }
        for (Int2ObjectMap.Entry entry : this.beamLoops.int2ObjectEntrySet()) {
            WitherStormTractorBeamLoop[] loops = (WitherStormTractorBeamLoop[])entry.getValue();
            for (int i = 0; i < loops.length; ++i) {
                if (loops[i] == null) continue;
                loops[i].forceStop();
                WitherStormTractorBeamLoop newLoop = new WitherStormTractorBeamLoop((WitherStormEntity)loops[i].getEntity(), loops[i].getHead());
                loops[i] = null;
                this.putBeamSound(((WitherStormEntity)newLoop.getEntity()).m_19879_(), newLoop.getHead(), newLoop);
            }
        }
        for (Int2ObjectMap.Entry entry : this.additional.int2ObjectEntrySet()) {
            WitherStormSoundLoop sound = (WitherStormSoundLoop)entry.getValue();
            sound.forceStop();
            int key = entry.getIntKey();
            this.additional.remove(entry.getIntKey());
            if (key == 0) continue;
            this.putSound(key, new WitherStormSoundLoop(sound.getPos(), sound.getSoundEvent()));
        }
    }

    public WitherStormSoundLoop getSound(int id) {
        return (WitherStormSoundLoop)this.sounds.get(id);
    }

    public WitherStormSoundLoop getAdditional(int id) {
        return (WitherStormSoundLoop)this.additional.get(id);
    }

    public WitherStormTractorBeamLoop[] getBeamSound(int id) {
        return (WitherStormTractorBeamLoop[])this.beamLoops.get(id);
    }

    public void putBeamSound(int id, int head, WitherStormTractorBeamLoop loop) {
        WitherStormTractorBeamLoop[] loops = new WitherStormTractorBeamLoop[3];
        if (this.beamLoops.containsKey(id)) {
            loops = (WitherStormTractorBeamLoop[])this.beamLoops.get(id);
        }
        loops[head] = loop;
        this.beamLoops.put(id, (Object)loops);
        this.minecraft.m_91106_().m_120372_((TickableSoundInstance)loop);
    }

    public boolean alreadyHasLoop(int id, int head) {
        if (this.beamLoops.containsKey(id)) {
            WitherStormTractorBeamLoop[] loop = (WitherStormTractorBeamLoop[])this.beamLoops.get(id);
            return loop[head] != null && !loop[head].m_7801_();
        }
        return false;
    }

    public void stopSound(int id) {
        if (this.sounds.containsKey(id)) {
            ((WitherStormSoundLoop)this.sounds.get(id)).stopSound();
        }
    }

    public void stopAdditional(int id) {
        if (this.additional.containsKey(id)) {
            ((WitherStormSoundLoop)this.additional.get(id)).stopSound();
        }
    }

    public void replace(int id, WitherStormSoundLoop sound) {
        this.pendingReplacements.putIfAbsent(id, (Object)sound);
    }

    public WitherStormSoundLoop getReplacement(int id) {
        return (WitherStormSoundLoop)this.pendingReplacements.get(id);
    }

    public boolean alreadyHasReplacement(int id) {
        return this.pendingReplacements.containsKey(id);
    }

    public static SoundEvent getSoundBasedOnDistance(float distance) {
        SoundEvent event = (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_CLOSE_LOOP.get();
        if (distance > 3.0f && distance < 6.0f) {
            event = (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_DISTANT_LOOP.get();
        } else if (distance > 6.0f) {
            event = (SoundEvent)WitherStormModSoundEvents.WITHER_STORM_FAR_LOOP.get();
        }
        return event;
    }
}

