/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.Camera
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.blockentity.BeaconRenderer
 *  net.minecraft.client.renderer.blockentity.BlockEntityRenderer
 *  net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider$Context
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.core.Position
 *  net.minecraft.core.Vec3i
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.phys.Vec3
 *  org.joml.Matrix4f
 */
package nonamecrackers2.witherstormmod.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Position;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.common.blockentity.AbstractSuperBeaconBlockEntity;
import org.joml.Matrix4f;

public abstract class AbstractSuperBeaconRenderer<T extends AbstractSuperBeaconBlockEntity>
implements BlockEntityRenderer<T> {
    protected static final ResourceLocation BEAM = new ResourceLocation("witherstormmod", "textures/misc/beam.png");

    public AbstractSuperBeaconRenderer(BlockEntityRendererProvider.Context context) {
    }

    public void render(T entity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight, int overlayTexture) {
        stack.m_85836_();
        VertexConsumer consumer = buffer.m_6299_(this.getRenderType(entity));
        float scale = this.getCrsytalScale(entity, partialTicks);
        stack.m_85837_(0.5, 0.5, 0.5);
        this.transformCrystal(entity, partialTicks, stack);
        float[] uvs = this.getUVS();
        int light = this.getLightTexture(entity, packedLight);
        AbstractSuperBeaconRenderer.renderOrb(stack, consumer, scale, uvs, light);
        stack.m_85849_();
    }

    public static void renderOrb(PoseStack stack, VertexConsumer consumer, float scale, int packedLight) {
        AbstractSuperBeaconRenderer.renderOrb(stack, consumer, scale, new float[]{0.0f, 0.0f, 1.0f, 1.0f}, packedLight);
    }

    public static void renderOrb(PoseStack stack, VertexConsumer consumer, float scale, float[] uvs, int packedLight) {
        stack.m_85836_();
        stack.m_252781_(Axis.f_252529_.m_252977_(45.0f));
        AbstractSuperBeaconRenderer.drawBox(consumer, stack, OverlayTexture.f_118083_, packedLight, 1.0f, uvs[0], uvs[1], uvs[2], uvs[3], scale / 2.0f);
        stack.m_85849_();
        stack.m_85836_();
        stack.m_252781_(Axis.f_252436_.m_252977_(45.0f));
        AbstractSuperBeaconRenderer.drawBox(consumer, stack, OverlayTexture.f_118083_, packedLight, 1.0f, uvs[0], uvs[1], uvs[2], uvs[3], scale / 2.0f);
        stack.m_85849_();
        stack.m_85836_();
        stack.m_252781_(Axis.f_252403_.m_252977_(45.0f));
        AbstractSuperBeaconRenderer.drawBox(consumer, stack, OverlayTexture.f_118083_, packedLight, 1.0f, uvs[0], uvs[1], uvs[2], uvs[3], scale / 2.0f);
        stack.m_85849_();
    }

    public static void renderBeam(boolean active, PoseStack stack, int[] color, MultiBufferSource buffer, float partialTicks, long gameTime, int beamHeight, float beamThickness, float outerThickness) {
        if (active) {
            stack.m_85836_();
            stack.m_85837_(0.0, 0.5, 0.0);
            float r = (float)color[0] / 255.0f;
            float g = (float)color[1] / 255.0f;
            float b = (float)color[2] / 255.0f;
            BeaconRenderer.m_112184_((PoseStack)stack, (MultiBufferSource)buffer, (ResourceLocation)BeaconRenderer.f_112102_, (float)partialTicks, (float)1.0f, (long)gameTime, (int)0, (int)beamHeight, (float[])new float[]{r, g, b, 1.0f}, (float)beamThickness, (float)outerThickness);
            stack.m_85849_();
        }
    }

    public static void renderConnectBeam(VertexConsumer consumer, PoseStack stack, Camera camera, float pulse, Vec3 start, Vec3 end, float r, float g, float b, float a) {
        stack.m_85836_();
        double dX = start.f_82479_ - end.f_82479_;
        double dY = start.f_82480_ - end.f_82480_;
        double dZ = start.f_82481_ - end.f_82481_;
        float yaw = (float)Math.atan2(dZ, dX) - 1.5707964f;
        float pitch = (float)Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + (float)Math.PI;
        stack.m_252781_(Axis.f_252392_.m_252961_(yaw));
        stack.m_252781_(Axis.f_252529_.m_252961_(pitch));
        Matrix4f pose = stack.m_85850_().m_252922_();
        consumer.m_252986_(pose, -pulse, 0.0f, 0.0f).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, pulse, 0.0f, 0.0f).m_85950_(r, g, b, a).m_7421_(1.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, pulse, (float)start.m_82554_(end), 0.0f).m_85950_(r, g, b, a).m_7421_(1.0f, 1.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, -pulse, (float)start.m_82554_(end), 0.0f).m_85950_(r, g, b, a).m_7421_(0.0f, 1.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, 0.0f, 0.0f, -pulse).m_85950_(r, g, b, a).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, 0.0f, 0.0f, pulse).m_85950_(r, g, b, a).m_7421_(1.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, 0.0f, (float)start.m_82554_(end), pulse).m_85950_(r, g, b, a).m_7421_(1.0f, 1.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, 0.0f, (float)start.m_82554_(end), -pulse).m_85950_(r, g, b, a).m_7421_(0.0f, 1.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        stack.m_85849_();
    }

    protected abstract float[] getUVS();

    protected abstract RenderType getRenderType(T var1);

    protected abstract float getCrsytalScale(T var1, float var2);

    protected abstract void transformCrystal(T var1, float var2, PoseStack var3);

    protected int getLightTexture(T entity, int packedLight) {
        return ((AbstractSuperBeaconBlockEntity)((Object)entity)).isActive() ? packedLight : 0xF000F0;
    }

    public int m_142163_() {
        return 256;
    }

    public boolean shouldRender(T entity, Vec3 pos) {
        return Vec3.m_82512_((Vec3i)entity.m_58899_()).m_82542_(1.0, 0.0, 1.0).m_82509_((Position)pos.m_82542_(1.0, 0.0, 1.0), (double)this.m_142163_());
    }

    public boolean shouldRenderOffScreen(T entity) {
        return true;
    }

    protected static void drawBox(VertexConsumer consumer, PoseStack stack, int overlayTexture, int packedLight, float alpha, float uMin, float vMin, float uMax, float vMax, float scale) {
        Matrix4f pose = stack.m_85850_().m_252922_();
        consumer.m_252986_(pose, -scale, -scale, -scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMin, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, -scale, scale, -scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMin, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, scale, scale, -scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMax, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, scale, -scale, -scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMax, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, -scale, -scale, scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMin, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, -scale, scale, scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMin, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, scale, scale, scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMax, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, scale, -scale, scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMax, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, -scale, -scale, -scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMin, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, -scale, -scale, scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMin, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, scale, -scale, scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMax, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, scale, -scale, -scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMax, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, -scale, scale, -scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMin, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, -scale, scale, scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMin, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, scale, scale, scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMax, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, scale, scale, -scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMax, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, -scale, -scale, -scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMin, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, -scale, -scale, scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMin, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, -scale, scale, scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMax, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, -scale, scale, -scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMax, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, scale, -scale, -scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMin, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, scale, -scale, scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMin, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, scale, scale, scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMax, vMax).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        consumer.m_252986_(pose, scale, scale, -scale).m_85950_(1.0f, 1.0f, 1.0f, alpha).m_7421_(uMax, vMin).m_86008_(overlayTexture).m_85969_(packedLight).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
    }
}

