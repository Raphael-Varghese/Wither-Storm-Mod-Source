/*
 * Decompiled with CFR 0.152.
 */
package nonamecrackers2.witherstormmod.common.entity;

public interface ChunkLoader {
    public int loadRadius();

    default public boolean shouldLoad() {
        return true;
    }

    default public boolean shouldContinueLoading() {
        return this.shouldLoad() && this.isStillValidForChunkLoading();
    }

    public boolean isStillValidForChunkLoading();
}

