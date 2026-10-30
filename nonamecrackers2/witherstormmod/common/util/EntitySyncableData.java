/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 */
package nonamecrackers2.witherstormmod.common.util;

import net.minecraft.network.FriendlyByteBuf;

public interface EntitySyncableData {
    public void writeData(FriendlyByteBuf var1);

    public void readData(FriendlyByteBuf var1);
}

