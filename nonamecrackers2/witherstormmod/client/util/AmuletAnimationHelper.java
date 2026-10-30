/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.math.Axis
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.player.AbstractClientPlayer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtUtils
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.event.TickEvent$ClientTickEvent
 *  org.joml.Matrix4f
 */
package nonamecrackers2.witherstormmod.client.util;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.common.item.AmuletItem;
import org.joml.Matrix4f;

public class AmuletAnimationHelper {
    public static final ResourceLocation GLARE = new ResourceLocation("witherstormmod", "textures/misc/glare.png");
    private static final Map<InteractionHand, AnimationHolder> ANIMATIONS = ImmutableMap.of((Object)InteractionHand.MAIN_HAND, (Object)new AnimationHolder(), (Object)InteractionHand.OFF_HAND, (Object)new AnimationHolder());

    public static void onRenderItemInHand(ItemStack item, PoseStack stack, InteractionHand hand, float partialTicks, MultiBufferSource buffer) {
        if (item.m_41720_() instanceof AmuletItem) {
            AnimationHolder holder = ANIMATIONS.get(hand);
            VertexConsumer consumer = buffer.m_6299_(RenderType.m_110473_((ResourceLocation)GLARE));
            stack.m_85837_(0.0, 0.2, 0.07);
            stack.m_252781_(Axis.f_252529_.m_252977_(Mth.m_14179_((float)partialTicks, (float)holder.animO, (float)holder.anim)));
            AmuletAnimationHelper.drawGlare(stack, hand, 1.0f, -0.53, 0.089, -0.53, 0.0f, 0.24705882f, 1.0f, 0.0f, holder.tickCount + 360, partialTicks, consumer, Mth.m_14179_((float)partialTicks, (float)holder.pulseIntensityO[0], (float)holder.pulseIntensity[0]));
            AmuletAnimationHelper.drawGlare(stack, hand, 0.4f, -0.2, 0.09, -0.02, 0.007843138f, 0.8980392f, 0.7019608f, 0.0f, holder.tickCount, partialTicks, consumer, Mth.m_14179_((float)partialTicks, (float)holder.pulseIntensityO[1], (float)holder.pulseIntensity[1]));
            AmuletAnimationHelper.drawGlare(stack, hand, 0.4f, -0.2, 0.09, -0.02, 0.9411765f, 0.15294118f, 0.02745098f, hand == InteractionHand.MAIN_HAND ? 270.0f : 90.0f, holder.tickCount, partialTicks, consumer, Mth.m_14179_((float)partialTicks, (float)holder.pulseIntensityO[4], (float)holder.pulseIntensity[4]));
            AmuletAnimationHelper.drawGlare(stack, hand, 0.4f, -0.2, 0.09, -0.02, 0.85882354f, 0.85882354f, 0.85882354f, 180.0f, holder.tickCount, partialTicks, consumer, Mth.m_14179_((float)partialTicks, (float)holder.pulseIntensityO[3], (float)holder.pulseIntensity[3]));
            AmuletAnimationHelper.drawGlare(stack, hand, 0.4f, -0.2, 0.09, -0.02, 0.25882354f, 0.8666667f, 0.023529412f, hand == InteractionHand.MAIN_HAND ? 90.0f : 270.0f, holder.tickCount, partialTicks, consumer, Mth.m_14179_((float)partialTicks, (float)holder.pulseIntensityO[2], (float)holder.pulseIntensity[2]));
            stack.m_85837_(0.0, -0.2, -0.07);
        }
    }

    public static void drawGlare(PoseStack stack, InteractionHand hand, float scale, double xOffset, double zOffset, double radius, float r, float g, float b, float degreeOffset, int ticks, float partialTicks, VertexConsumer consumer, float pulseIntensity) {
        stack.m_85836_();
        stack.m_252781_(Axis.f_252529_.m_252977_(degreeOffset - 30.0f));
        stack.m_252781_(Axis.f_252392_.m_252977_(90.0f));
        Minecraft mc = Minecraft.m_91087_();
        HumanoidArm arm = (HumanoidArm)mc.f_91066_.m_232107_().m_231551_();
        boolean swap = arm == HumanoidArm.RIGHT ? hand == InteractionHand.MAIN_HAND : hand == InteractionHand.OFF_HAND;
        stack.m_85837_(xOffset, radius, swap ? -zOffset : zOffset);
        stack.m_85841_(scale, scale, scale);
        Matrix4f matrix = stack.m_85850_().m_252922_();
        float alpha = Mth.m_14036_((float)(Mth.m_14089_((float)(((float)ticks + degreeOffset + partialTicks) * 0.2f)) * pulseIntensity), (float)0.0f, (float)1.0f);
        if (alpha > 0.0f) {
            consumer.m_252986_(matrix, 0.0f, 0.0f, 0.0f).m_85950_(r, g, b, alpha).m_7421_(0.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix, 0.0f, 1.0f, 0.0f).m_85950_(r, g, b, alpha).m_7421_(0.0f, 1.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix, 1.0f, 1.0f, 0.0f).m_85950_(r, g, b, alpha).m_7421_(1.0f, 1.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
            consumer.m_252986_(matrix, 1.0f, 0.0f, 0.0f).m_85950_(r, g, b, alpha).m_7421_(1.0f, 0.0f).m_86008_(OverlayTexture.f_118083_).m_85969_(0xF000F0).m_5601_(0.0f, 1.0f, 0.0f).m_5752_();
        }
        stack.m_85849_();
    }

    public static float getPulseIntensity(AbstractClientPlayer player, ClientLevel level, ItemStack stack, String id, int distance) {
        CompoundTag tag = stack.m_41784_();
        if (tag.m_128441_(id + "Pos")) {
            BlockPos pos = NbtUtils.m_129239_((CompoundTag)tag.m_128469_(id + "Pos"));
            float angle = (float)(Mth.m_14136_((double)((double)pos.m_123341_() - player.m_20185_()), (double)((double)pos.m_123343_() - player.m_20189_())) * 57.29577951308232);
            float angleDiff = (Mth.m_14177_((float)(-player.f_20885_)) - angle + 180.0f + 360.0f) % 360.0f - 180.0f;
            float value = 1.0f - Mth.m_14036_((float)Mth.m_14154_((float)(angleDiff * 0.03f)), (float)0.0f, (float)0.8f);
            int dist = tag.m_128451_(id + "Dist");
            if (dist >= 0) {
                return value * Mth.m_14036_((float)(((float)distance - (float)dist) * 0.05f), (float)0.0f, (float)1.0f);
            }
            return 0.0f;
        }
        return 0.0f;
    }

    public static void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.m_91087_();
        if (!mc.m_91104_() && mc.f_91074_ != null) {
            for (InteractionHand hand : InteractionHand.values()) {
                AnimationHolder holder;
                ItemStack item = mc.f_91074_.m_21120_(hand);
                if (!item.m_150930_((Item)WitherStormModItems.AMULET.get()) || (holder = ANIMATIONS.get(hand)) == null) continue;
                ++holder.tickCount;
                CompoundTag tag = item.m_41784_();
                int index = tag.m_128451_("SelectedIndex");
                float target = AmuletAnimationHelper.getSwapDegrees(hand, index);
                if (target != holder.targetO) {
                    holder.steps = 16;
                }
                holder.animO = holder.anim;
                if (holder.steps > 0) {
                    holder.anim += Mth.m_14177_((float)(target - holder.anim)) / (float)holder.steps;
                    --holder.steps;
                }
                holder.targetO = target;
                for (int i = 0; i < holder.pulseIntensity.length; ++i) {
                    holder.pulseIntensityO[i] = holder.pulseIntensity[i];
                    holder.pulseIntensity[i] = AmuletAnimationHelper.getPulseIntensity((AbstractClientPlayer)mc.f_91074_, mc.f_91073_, item, AmuletItem.TRACKING[i], 1000);
                }
            }
        }
    }

    public static float getSwapDegrees(InteractionHand hand, int index) {
        if (hand == InteractionHand.OFF_HAND) {
            switch (index) {
                case 1: {
                    return 0.0f;
                }
                case 2: {
                    return 90.0f;
                }
                case 3: {
                    return 180.0f;
                }
                case 4: {
                    return 270.0f;
                }
            }
            return 0.0f;
        }
        if (hand == InteractionHand.MAIN_HAND) {
            switch (index) {
                case 1: {
                    return 0.0f;
                }
                case 2: {
                    return 270.0f;
                }
                case 3: {
                    return 180.0f;
                }
                case 4: {
                    return 90.0f;
                }
            }
            return 0.0f;
        }
        return 0.0f;
    }

    private static class AnimationHolder {
        private float anim;
        private float animO;
        private float targetO;
        private int steps;
        private int tickCount;
        private float[] pulseIntensity = new float[5];
        private float[] pulseIntensityO = new float[5];

        private AnimationHolder() {
        }
    }
}

