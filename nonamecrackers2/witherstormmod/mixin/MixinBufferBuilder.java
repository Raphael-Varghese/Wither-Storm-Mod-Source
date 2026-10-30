/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.BufferBuilder
 *  javax.annotation.Nullable
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package nonamecrackers2.witherstormmod.mixin;

import com.mojang.blaze3d.vertex.BufferBuilder;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={BufferBuilder.class})
public interface MixinBufferBuilder {
    @Accessor(value="buffer")
    public ByteBuffer witherstormmod$getBuffer();

    @Accessor(value="buffer")
    public void witherstormmod$setBuffer(@Nullable ByteBuffer var1);
}

