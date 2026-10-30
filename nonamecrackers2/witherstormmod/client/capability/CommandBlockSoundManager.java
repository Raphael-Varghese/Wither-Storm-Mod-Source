/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package nonamecrackers2.witherstormmod.client.capability;

import net.minecraft.client.Minecraft;
import nonamecrackers2.witherstormmod.client.audio.CommandBlockEntityLoop;
import nonamecrackers2.witherstormmod.client.audio.EntitySoundManager;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;

public class CommandBlockSoundManager
extends EntitySoundManager<CommandBlockEntity, CommandBlockEntityLoop> {
    public CommandBlockSoundManager(Minecraft minecraft) {
        super(minecraft, CommandBlockEntity.class);
    }

    @Override
    protected boolean alreadyHasLoop(CommandBlockEntity entity) {
        boolean flag = false;
        for (CommandBlockEntityLoop loop : this.loops) {
            if (loop.entity != entity) continue;
            flag = true;
            break;
        }
        return flag;
    }

    @Override
    protected CommandBlockEntityLoop create(CommandBlockEntity entity) {
        return new CommandBlockEntityLoop(entity);
    }

    @Override
    protected CommandBlockEntityLoop copyFrom(CommandBlockEntityLoop loop) {
        return new CommandBlockEntityLoop(loop.entity);
    }
}

