/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.ItemRenderer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.client.renderer.texture.TextureAtlas
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.inventory.InventoryMenu
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.client.model.data.ModelData
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 */
package nonamecrackers2.witherstormmod.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.data.ModelData;
import nonamecrackers2.witherstormmod.client.renderer.blockentity.AbstractSuperBeaconRenderer;
import nonamecrackers2.witherstormmod.client.util.TextureAtlasAccessor;
import nonamecrackers2.witherstormmod.common.blockentity.AbstractSuperBeaconBlockEntity;
import nonamecrackers2.witherstormmod.common.blockentity.SuperBeaconBlockEntity;
import nonamecrackers2.witherstormmod.common.blockentity.SuperSupportBeaconBlockEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class SuperBeaconRenderer
extends AbstractSuperBeaconRenderer<SuperBeaconBlockEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("witherstormmod", "block/tainted_dust_block");
    private final TextureAtlas atlas;
    private final TextureAtlasSprite texture;
    private final ItemRenderer itemRenderer;

    public SuperBeaconRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
        Minecraft mc = Minecraft.m_91087_();
        this.atlas = mc.m_91304_().m_119428_(InventoryMenu.f_39692_);
        this.texture = this.atlas.m_118316_(TEXTURE);
        this.itemRenderer = mc.m_91291_();
    }

    @Override
    public void render(SuperBeaconBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight, int overlayTexture) {
        float baseRotation;
        super.render(entity, partialTicks, stack, buffer, packedLight, overlayTexture);
        Minecraft mc = Minecraft.m_91087_();
        float tickCount = (float)entity.getTicks() + partialTicks;
        float resummonTicks = (float)entity.getResummonTicks() + partialTicks;
        if (entity.getResummonEntity() == WitherStormModEntityTypes.WITHER_STORM.get() && entity.getResummonTicks() > 60) {
            float speed = 200.0f / (resummonTicks - 400.0f);
            stack.m_85836_();
            Vec2 shake = entity.getShake(partialTicks);
            stack.m_85837_((double)(shake.f_82470_ * speed), 3.0, (double)(shake.f_82471_ * speed));
            stack.m_85837_(0.5, 0.5, 0.5);
            stack.m_252781_(Axis.f_252403_.m_252977_(tickCount * speed));
            stack.m_252781_(Axis.f_252495_.m_252977_(tickCount * speed));
            stack.m_85837_(-0.5, -0.5, -0.5);
            mc.m_91289_().renderSingleBlock(Blocks.f_50272_.m_49966_(), stack, buffer, 0xF000F0, OverlayTexture.f_118083_, ModelData.EMPTY, null);
            stack.m_85849_();
        }
        stack.m_85837_(0.5, 0.5, 0.5);
        if (entity.getResummonEntity() == WitherStormModEntityTypes.WITHER_STORM.get() && entity.getResummonTicks() > 60) {
            stack.m_85836_();
            VertexConsumer consumer = buffer.m_6299_(RenderType.m_110473_((ResourceLocation)BEAM));
            int[] color = entity.getBeamColor();
            float anim = entity.getActivateAnimation(partialTicks);
            float r = Mth.m_14179_((float)anim, (float)0.5f, (float)((float)color[0] / 255.0f));
            float g = Mth.m_14179_((float)anim, (float)0.5f, (float)((float)color[1] / 255.0f));
            float b = Mth.m_14179_((float)anim, (float)0.5f, (float)((float)color[2] / 255.0f));
            float pulse = this.getCrsytalScale(entity, partialTicks);
            Vec3 pos = Vec3.m_82512_((Vec3i)entity.m_58899_());
            if (anim > 0.01f) {
                SuperBeaconRenderer.renderConnectBeam(consumer, stack, mc.f_91063_.m_109153_(), pulse, pos, pos.m_82520_(0.0, 3.0, 0.0), r, g, b, anim);
            }
            stack.m_85849_();
        }
        stack.m_85836_();
        float scale = 1.0f;
        if (entity.getResummonTicks() > 0) {
            baseRotation = (tickCount + (float)Math.pow((float)entity.getResummonTicks() + partialTicks, 2.0)) * 0.02f;
            scale -= ((float)entity.getResummonTicks() + partialTicks) / 60.0f;
        } else {
            baseRotation = tickCount * 0.02f;
        }
        stack.m_85841_(scale, scale, scale);
        stack.m_85837_(0.0, 1.0, 0.0);
        int total = entity.m_6643_();
        float angleInterval = 360.0f / (float)total * ((float)Math.PI / 180);
        for (int i = 0; i < total; ++i) {
            stack.m_85836_();
            float angle = angleInterval * (float)i;
            float x = Mth.m_14031_((float)(angle + baseRotation));
            float z = Mth.m_14089_((float)(angle + baseRotation));
            stack.m_85837_((double)x, 0.0, (double)z);
            Vec2 shake = entity.getShake(partialTicks);
            if (entity.isDoingResummonAnimation()) {
                stack.m_252880_(shake.f_82470_, 0.0f, shake.f_82471_);
            }
            stack.m_85837_(0.0, (double)(Mth.m_14031_((float)((tickCount + (float)(i * total)) * 0.2f)) * 0.05f), 0.0);
            stack.m_252781_(Axis.f_252392_.m_252977_(tickCount + (float)(i * 100)));
            this.itemRenderer.m_269128_(entity.m_8020_(i), ItemDisplayContext.FIXED, packedLight, overlayTexture, stack, buffer, entity.m_58904_(), (int)entity.m_58899_().m_121878_());
            stack.m_85849_();
        }
        stack.m_85849_();
        if (entity.showWorkingArea()) {
            for (Map.Entry<AbstractSuperBeaconBlockEntity.Color, BlockPos> entry : entity.getConnected().entrySet()) {
                BlockPos pos = entry.getValue();
                AbstractSuperBeaconBlockEntity.Color color = entry.getKey();
                float angle = SuperSupportBeaconBlockEntity.getAngleBetween(pos, entity.m_58899_());
                float arcHalf = 45.0f;
                VertexConsumer consumer = buffer.m_6299_(RenderType.m_110504_());
                SuperBeaconRenderer.drawLineSegment(stack, consumer, 0.0f, 10.0f, color.getRed(), color.getGreen(), color.getBlue(), angle + arcHalf, -0.05f);
                SuperBeaconRenderer.drawLineSegment(stack, consumer, 11.0f, 1.0f, color.getRed(), color.getGreen(), color.getBlue(), angle + arcHalf, -0.05f);
                SuperBeaconRenderer.drawLineSegment(stack, consumer, 13.0f, 1.0f, color.getRed(), color.getGreen(), color.getBlue(), angle + arcHalf, -0.05f);
                SuperBeaconRenderer.drawLineSegment(stack, consumer, 15.0f, 0.5f, color.getRed(), color.getGreen(), color.getBlue(), angle + arcHalf, -0.05f);
                SuperBeaconRenderer.drawLineSegment(stack, consumer, 0.0f, 10.0f, color.getRed(), color.getGreen(), color.getBlue(), angle - arcHalf, 0.05f);
                SuperBeaconRenderer.drawLineSegment(stack, consumer, 11.0f, 1.0f, color.getRed(), color.getGreen(), color.getBlue(), angle - arcHalf, 0.05f);
                SuperBeaconRenderer.drawLineSegment(stack, consumer, 13.0f, 1.0f, color.getRed(), color.getGreen(), color.getBlue(), angle - arcHalf, 0.05f);
                SuperBeaconRenderer.drawLineSegment(stack, consumer, 15.0f, 0.5f, color.getRed(), color.getGreen(), color.getBlue(), angle - arcHalf, 0.05f);
            }
        }
    }

    private static void drawLineSegment(PoseStack stack, VertexConsumer consumer, float zOffset, float distance, int r, int g, int b, float angle, float xOffset) {
        stack.m_85836_();
        stack.m_252781_(Axis.f_252436_.m_252977_(angle));
        stack.m_85837_((double)xOffset, 0.0, 0.0);
        Matrix4f pose = stack.m_85850_().m_252922_();
        Matrix3f normal = stack.m_85850_().m_252943_();
        consumer.m_252986_(pose, 0.0f, 0.0f, zOffset).m_6122_(r, g, b, 255).m_252939_(normal, 0.0f, 0.0f, 1.0f).m_5752_();
        consumer.m_252986_(pose, 0.0f, 0.0f, zOffset + distance).m_6122_(r, g, b, 255).m_252939_(normal, 0.0f, 0.0f, 1.0f).m_5752_();
        stack.m_85849_();
    }

    @Override
    protected float[] getUVS() {
        TextureAtlasAccessor accessor = (TextureAtlasAccessor)this.atlas;
        float uvSquish = 3.0f;
        return new float[]{this.texture.m_118409_() + uvSquish / (float)accessor.getWidth(), this.texture.m_118411_() + uvSquish / (float)accessor.getHeight(), this.texture.m_118410_() - uvSquish / (float)accessor.getWidth(), this.texture.m_118412_() - uvSquish / (float)accessor.getHeight()};
    }

    @Override
    protected RenderType getRenderType(SuperBeaconBlockEntity entity) {
        return RenderType.m_110473_((ResourceLocation)InventoryMenu.f_39692_);
    }

    @Override
    protected float getCrsytalScale(SuperBeaconBlockEntity entity, float partialTicks) {
        return Mth.m_14179_((float)entity.getActivateAnimation(partialTicks), (float)0.4f, (float)((Mth.m_14031_((float)(((float)entity.getTicks() + partialTicks) * 0.1f)) + 10.0f) * 0.05f));
    }

    @Override
    protected void transformCrystal(SuperBeaconBlockEntity entity, float partialTicks, PoseStack stack) {
        float speed = Mth.m_14179_((float)entity.getActivateAnimation(partialTicks), (float)0.1f, (float)1.0f);
        stack.m_252781_(Axis.f_252529_.m_252977_(Mth.m_14177_((float)((float)entity.getTicks() + partialTicks)) * 5.0f * speed));
        stack.m_252781_(Axis.f_252393_.m_252977_(Mth.m_14177_((float)((float)entity.getTicks() + partialTicks)) * 12.0f * speed));
    }
}

