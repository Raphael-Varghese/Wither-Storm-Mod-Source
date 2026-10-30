/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.BufferBuilder
 *  com.mojang.blaze3d.vertex.BufferBuilder$RenderedBuffer
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.Tesselator
 *  com.mojang.blaze3d.vertex.VertexBuffer
 *  com.mojang.blaze3d.vertex.VertexBuffer$Usage
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  javax.annotation.Nullable
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.texture.OverlayTexture
 */
package nonamecrackers2.witherstormmod.client.instancing;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class BufferedInstance {
    @Nullable
    protected VertexBuffer buffer;
    private final RenderType type;
    private Bufferable bufferer;
    protected boolean dirty = true;
    @Nullable
    private final Supplier<Boolean> shouldRemove;
    private boolean wasRenderedLastFrame = true;

    public BufferedInstance(RenderType type, Bufferable bufferer, @Nullable Supplier<Boolean> shouldRemove) {
        this.type = type;
        this.bufferer = bufferer;
        this.shouldRemove = shouldRemove;
    }

    public void bufferInto(PoseStack stack, VertexConsumer consumer, int packedLight, int overlayTexture, float r, float g, float b, float a) {
        this.bufferer.bufferInto(stack, consumer, packedLight, overlayTexture, r, g, b, a);
    }

    public RenderType getRenderType() {
        return this.type;
    }

    @Nullable
    public VertexBuffer getBuffer() {
        return this.buffer;
    }

    public void close() {
        if (this.buffer != null) {
            this.buffer.close();
        }
    }

    public boolean requiresComputing() {
        return this.dirty;
    }

    public void markDirty() {
        this.dirty = true;
    }

    public void buildBuffer(PoseStack stack, ExecutorService pool) {
        if (this.buffer != null) {
            this.buffer.close();
        }
        this.buffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
        RenderType type = this.getRenderType();
        BufferBuilder buffer = Tesselator.m_85913_().m_85915_();
        buffer.m_166779_(type.m_173186_(), type.m_110508_());
        this.bufferInto(stack, (VertexConsumer)buffer, 0xF000F0, OverlayTexture.f_118083_, 1.0f, 1.0f, 1.0f, 1.0f);
        BufferBuilder.RenderedBuffer rendered = buffer.m_231175_();
        this.buffer.m_85921_();
        this.buffer.m_231221_(rendered);
        VertexBuffer.m_85931_();
        this.dirty = false;
    }

    public void setBufferer(Bufferable bufferer) {
        this.bufferer = bufferer;
    }

    public boolean shouldRemove() {
        return this.shouldRemove != null ? this.shouldRemove.get() : false;
    }

    public void setRenderedLastFrame(boolean flag) {
        this.wasRenderedLastFrame = flag;
    }

    public boolean wasRenderedLastFrame() {
        return this.wasRenderedLastFrame;
    }

    public boolean hasRemoveSupplier() {
        return this.shouldRemove != null;
    }

    public static interface Bufferable {
        public void bufferInto(PoseStack var1, VertexConsumer var2, int var3, int var4, float var5, float var6, float var7, float var8);
    }
}

