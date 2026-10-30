/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Mth
 *  net.minecraft.world.level.block.Block
 *  net.minecraftforge.client.event.RegisterColorHandlersEvent$Block
 *  org.joml.Vector3f
 */
package nonamecrackers2.witherstormmod.client.event;

import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import nonamecrackers2.witherstormmod.common.block.WireBlock;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
import org.joml.Vector3f;

public class WitherStormModRegisterBlockColors {
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, world, pos, i) -> {
            Vector3f color = ((WireBlock)((Object)((Object)WitherStormModBlocks.TAINTED_DUST.get()))).getColor();
            return Mth.m_14159_((float)color.x(), (float)color.y(), (float)color.z());
        }, new Block[]{(Block)WitherStormModBlocks.TAINTED_DUST.get()});
    }
}

