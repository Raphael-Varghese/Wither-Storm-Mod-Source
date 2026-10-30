/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.DefaultedVertexConsumer
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  org.joml.Matrix3f
 *  org.joml.Matrix3fc
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Quaternionfc
 *  org.joml.Vector3f
 *  org.joml.Vector4f
 */
package nonamecrackers2.witherstormmod.client.util;

import com.mojang.blaze3d.vertex.DefaultedVertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.core.Direction;
import org.joml.Matrix3f;
import org.joml.Matrix3fc;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class TiledTextureGenerator
extends DefaultedVertexConsumer {
    private final VertexConsumer delegate;
    private final PoseStack stack;
    private float x;
    private float y;
    private float z;
    private int r;
    private int g;
    private int b;
    private int a;
    private int overlayU;
    private int overlayV;
    private int lightCoords;
    private float nx;
    private float ny;
    private float nz;
    private float texScale;

    public TiledTextureGenerator(VertexConsumer consumer, PoseStack stack, float texScale) {
        this.delegate = consumer;
        this.stack = stack;
        this.resetState();
        this.texScale = texScale;
    }

    private void resetState() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.r = 255;
        this.g = 255;
        this.b = 255;
        this.a = 255;
        this.overlayU = 0;
        this.overlayV = 10;
        this.lightCoords = 0xF000F0;
        this.nx = 0.0f;
        this.ny = 1.0f;
        this.nz = 0.0f;
    }

    public VertexConsumer m_5483_(double x, double y, double z) {
        this.x = (float)x;
        this.y = (float)y;
        this.z = (float)z;
        return this;
    }

    public VertexConsumer m_6122_(int red, int green, int blue, int alpha) {
        this.r = red;
        this.g = green;
        this.b = blue;
        this.a = alpha;
        return this;
    }

    public VertexConsumer m_7421_(float u, float v) {
        return this;
    }

    public VertexConsumer m_7122_(int overlayU, int overlayV) {
        this.overlayU = overlayU;
        this.overlayV = overlayV;
        return this;
    }

    public VertexConsumer m_7120_(int coord1, int coord2) {
        this.lightCoords = coord1 | coord2 << 16;
        return this;
    }

    public VertexConsumer m_5601_(float x, float y, float z) {
        this.nx = x;
        this.ny = y;
        this.nz = z;
        return this;
    }

    public void m_5752_() {
        Vector3f vector3f = new Matrix3f((Matrix3fc)this.stack.m_85850_().m_252943_()).invert().transform(new Vector3f(this.nx, this.ny, this.nz));
        Direction direction = Direction.m_122372_((float)vector3f.x(), (float)vector3f.y(), (float)vector3f.z());
        Vector4f vector4f = new Matrix4f((Matrix4fc)this.stack.m_85850_().m_252922_()).invert().transform(new Vector4f(this.x, this.y, this.z, 1.0f));
        vector4f.rotateY((float)Math.PI);
        vector4f.rotateX(-1.5707964f);
        vector4f.rotate((Quaternionfc)direction.m_253075_());
        float f = ((direction.m_122434_() == Direction.Axis.X ? -1.0f : 1.0f) * vector4f.x() + 0.5f) * this.texScale + this.getUOffset(direction);
        float f1 = (vector4f.y() + 0.5f) * this.texScale + this.getVOffset(direction);
        this.delegate.m_5483_((double)this.x, (double)this.y, (double)this.z).m_6122_(this.r, this.g, this.b, this.a).m_7421_(f, f1).m_7122_(this.overlayU, this.overlayV).m_85969_(this.lightCoords).m_5601_(this.nx, this.ny, this.nz).m_5752_();
        this.resetState();
    }

    private float getUOffset(Direction direction) {
        Vector3f step = direction.m_253071_();
        if (step.x > 0.0f || step.x < 0.0f) {
            return 0.0f;
        }
        if (step.z > 0.0f || step.z < 0.0f) {
            return this.texScale;
        }
        if (step.y > 0.0f || step.y < 0.0f) {
            return this.texScale * 2.0f;
        }
        return 0.0f;
    }

    private float getVOffset(Direction direction) {
        Vector3f step = direction.m_253071_();
        if (step.x < 0.0f || step.z < 0.0f || step.y < 0.0f) {
            return 0.0f;
        }
        if (step.x > 0.0f || step.z > 0.0f || step.y > 0.0f) {
            return this.texScale * 2.0f;
        }
        return 0.0f;
    }
}

