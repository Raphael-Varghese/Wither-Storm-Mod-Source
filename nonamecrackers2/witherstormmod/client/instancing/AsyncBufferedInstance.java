/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.BufferBuilder
 *  com.mojang.blaze3d.vertex.BufferBuilder$RenderedBuffer
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexBuffer
 *  com.mojang.blaze3d.vertex.VertexBuffer$Usage
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  javax.annotation.Nullable
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.system.MemoryUtil
 */
package nonamecrackers2.witherstormmod.client.instancing;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.nio.Buffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import nonamecrackers2.witherstormmod.client.instancing.BufferedInstance;
import nonamecrackers2.witherstormmod.mixin.MixinBufferBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.system.MemoryUtil;

public class AsyncBufferedInstance
extends BufferedInstance {
    private static final Logger LOGGER = LogManager.getLogger();
    @Nullable
    private CompletableFuture<BufferBuilder> bufferBuilder;

    public AsyncBufferedInstance(RenderType type, BufferedInstance.Bufferable bufferer, Supplier<Boolean> shouldRemove) {
        super(type, bufferer, shouldRemove);
    }

    public void checkBufferBuilderStatus() {
        if (this.bufferBuilder != null && this.bufferBuilder.isDone()) {
            try {
                BufferBuilder builder = this.bufferBuilder.get();
                if (this.buffer != null) {
                    this.buffer.close();
                }
                this.buffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
                BufferBuilder.RenderedBuffer rendered = builder.m_231175_();
                this.buffer.m_85921_();
                this.buffer.m_231221_(rendered);
                VertexBuffer.m_85931_();
                builder.m_85729_();
                MixinBufferBuilder mixin = (MixinBufferBuilder)builder;
                MemoryUtil.memFree((Buffer)mixin.witherstormmod$getBuffer());
                mixin.witherstormmod$setBuffer(null);
            }
            catch (InterruptedException | ExecutionException e) {
                LOGGER.error("Failed to get BufferBuilder", (Throwable)e);
            }
            this.bufferBuilder = null;
        }
    }

    @Override
    public void buildBuffer(PoseStack stack, ExecutorService pool) {
        this.bufferBuilder = CompletableFuture.supplyAsync(() -> {
            RenderType type = this.getRenderType();
            BufferBuilder builder = new BufferBuilder(512);
            builder.m_166779_(type.m_173186_(), type.m_110508_());
            this.bufferInto(stack, (VertexConsumer)builder, 0xF000F0, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
            return builder;
        }, pool).handle((b, e) -> {
            if (e != null) {
                LOGGER.error("Failed to build buffer asynchronously", e);
            }
            b.m_85729_();
            return b;
        });
        this.dirty = false;
    }
}

