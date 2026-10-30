package nonamecrackers2.witherstormmod.client.audio;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.TickableSoundInstance;
import net.minecraft.world.entity.Entity;
import nonamecrackers2.witherstormmod.client.audio.FadingSoundLoop;
import nonamecrackers2.witherstormmod.client.audio.IForceStoppableSound;
import nonamecrackers2.witherstormmod.client.audio.ISoundManager;

public abstract class EntitySoundManager<T extends Entity, L extends AbstractTickableSoundInstance>
implements ISoundManager {
    protected final Minecraft minecraft;
    protected final Class<T> entityClass;
    protected final List<L> loops = new ArrayList<L>();

    public EntitySoundManager(Minecraft minecraft, Class<T> entityClass) {
        this.minecraft = minecraft;
        this.entityClass = entityClass;
    }

    @Override
    public void tick() {
        ClientLevel world = this.minecraft.f_91073_;
        for (Entity entity : world.m_104735_()) {
            Entity t;
            if (!this.entityClass.isInstance(entity) || !this.canPlay(t = entity)) continue;
            this.putLoop(this.create(t));
        }
        for (int i = 0; i < this.loops.size(); ++i) {
            AbstractTickableSoundInstance loop = (AbstractTickableSoundInstance)this.loops.get(i);
            if (!loop.m_7801_() && (!(loop instanceof FadingSoundLoop) || !((FadingSoundLoop)loop).isStopping())) continue;
            this.loops.remove(i);
        }
    }

    protected abstract boolean alreadyHasLoop(T var1);

    protected void putLoop(L loop) {
        if (!this.loops.contains(loop)) {
            this.loops.add(loop);
            this.minecraft.m_91106_().m_120372_(loop);
        }
    }

    protected boolean canPlay(T entity) {
        return !this.alreadyHasLoop(entity) && entity.m_6084_();
    }

    @Override
    public void refresh() {
        ArrayList<AbstractTickableSoundInstance> soundsToAdd = new ArrayList<AbstractTickableSoundInstance>();
        for (int i = 0; i < this.loops.size(); ++i) {
            AbstractTickableSoundInstance loop = (AbstractTickableSoundInstance)this.loops.get(i);
            AbstractTickableSoundInstance newLoop = this.copyFrom(loop);
            ((IForceStoppableSound)loop).forceStop();
            soundsToAdd.add(newLoop);
            this.minecraft.m_91106_().m_120372_((TickableSoundInstance)newLoop);
        }
        this.loops.clear();
        this.loops.addAll(soundsToAdd);
    }

    protected abstract L create(T var1);

    protected abstract L copyFrom(L var1);
}

