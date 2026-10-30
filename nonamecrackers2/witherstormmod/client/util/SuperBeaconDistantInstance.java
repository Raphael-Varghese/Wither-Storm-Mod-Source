/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 */
package nonamecrackers2.witherstormmod.client.util;

import net.minecraft.core.BlockPos;

public class SuperBeaconDistantInstance {
    public final BlockPos pos;
    public int[] color;
    public boolean active;
    public int beaconHeight;
    public float beamWidth = 0.25f;
    public float outerBeamWidth = 0.45f;

    public SuperBeaconDistantInstance(BlockPos pos, int[] color) {
        this.pos = pos;
        this.color = color;
    }
}

