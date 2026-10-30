/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider$Context
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.phys.Vec3
 */
package nonamecrackers2.witherstormmod.client.renderer.blockentity;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.client.renderer.blockentity.AbstractSuperBeaconRenderer;
import nonamecrackers2.witherstormmod.common.blockentity.AbstractSuperBeaconBlockEntity;
import nonamecrackers2.witherstormmod.common.blockentity.SuperSupportBeaconBlockEntity;

public class SuperSupportBeaconRenderer
extends AbstractSuperBeaconRenderer<SuperSupportBeaconBlockEntity> {
    private static final float[] UVS = new float[]{0.375f, 0.375f, 0.625f, 0.625f};
    private static final ResourceLocation DIAMOND = new ResourceLocation("witherstormmod", "textures/block/support_beacon_diamond.png");
    private static final ResourceLocation EMERALD = new ResourceLocation("witherstormmod", "textures/block/support_beacon_emerald.png");
    private static final ResourceLocation IRON = new ResourceLocation("witherstormmod", "textures/block/support_beacon_iron.png");
    private static final ResourceLocation REDSTONE = new ResourceLocation("witherstormmod", "textures/block/support_beacon_redstone.png");
    private static final Map<AbstractSuperBeaconBlockEntity.Color, ResourceLocation> TEX_BY_COLOR = ImmutableMap.of((Object)((Object)AbstractSuperBeaconBlockEntity.Color.AQUA), (Object)DIAMOND, (Object)((Object)AbstractSuperBeaconBlockEntity.Color.GREEN), (Object)EMERALD, (Object)((Object)AbstractSuperBeaconBlockEntity.Color.GRAY), (Object)IRON, (Object)((Object)AbstractSuperBeaconBlockEntity.Color.RED), (Object)REDSTONE);

    public SuperSupportBeaconRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(SuperSupportBeaconBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight, int overlayTexture) {
        super.render(entity, partialTicks, stack, buffer, packedLight, overlayTexture);
        BlockPos pos = entity.getBeamPos();
        if (pos != null) {
            stack.m_85836_();
            stack.m_85837_(0.5, 0.6, 0.5);
            Minecraft mc = Minecraft.m_91087_();
            VertexConsumer consumer = buffer.m_6299_(RenderType.m_110473_((ResourceLocation)BEAM));
            int[] color = entity.getBeamColor();
            float anim = entity.getActivateAnimation(partialTicks);
            float r = Mth.m_14179_((float)anim, (float)0.5f, (float)((float)color[0] / 255.0f));
            float g = Mth.m_14179_((float)anim, (float)0.5f, (float)((float)color[1] / 255.0f));
            float b = Mth.m_14179_((float)anim, (float)0.5f, (float)((float)color[2] / 255.0f));
            float pulse = this.getCrsytalScale(entity, partialTicks);
            if (anim > 0.01f) {
                SuperSupportBeaconRenderer.renderConnectBeam(consumer, stack, mc.f_91063_.m_109153_(), pulse, Vec3.m_82512_((Vec3i)entity.m_58899_()), Vec3.m_82512_((Vec3i)pos).m_82520_(0.0, -0.1, 0.0), r, g, b, anim);
            }
            stack.m_85849_();
        }
    }

    @Override
    protected float[] getUVS() {
        return UVS;
    }

    @Override
    protected RenderType getRenderType(SuperSupportBeaconBlockEntity entity) {
        return RenderType.m_110458_((ResourceLocation)(entity.getColor() == null ? IRON : TEX_BY_COLOR.get((Object)entity.getColor())));
    }

    @Override
    protected float getCrsytalScale(SuperSupportBeaconBlockEntity entity, float partialTicks) {
        return Mth.m_14179_((float)entity.getActivateAnimation(partialTicks), (float)0.2f, (float)((Mth.m_14031_((float)(((float)entity.getTicks() + partialTicks) * 0.1f)) + 10.0f) * 0.035f));
    }

    @Override
    protected void transformCrystal(SuperSupportBeaconBlockEntity entity, float partialTicks, PoseStack stack) {
        stack.m_85837_(0.0, 0.1, 0.0);
        float speed = Mth.m_14179_((float)entity.getActivateAnimation(partialTicks), (float)0.2f, (float)1.0f);
        stack.m_252781_(Axis.f_252529_.m_252977_(Mth.m_14177_((float)((float)entity.getTicks() + partialTicks)) * 4.0f * speed));
        stack.m_252781_(Axis.f_252392_.m_252977_(Mth.m_14177_((float)((float)entity.getTicks() + partialTicks)) * 8.0f * speed));
    }
}

