/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.player.AbstractClientPlayer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.item.ItemStack
 */
package nonamecrackers2.witherstormmod.client.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import nonamecrackers2.witherstormmod.client.util.AmuletAnimationHelper;
import nonamecrackers2.witherstormmod.common.item.FormidiBladeItem;

public class FormidiBladeAnimationHelper {
    public static void onRenderItemInHand(AbstractClientPlayer player, ItemStack item, PoseStack stack, InteractionHand hand, float partialTicks, MultiBufferSource buffer) {
        if (item.m_41720_() instanceof FormidiBladeItem) {
            VertexConsumer consumer = buffer.m_6299_(RenderType.m_110473_((ResourceLocation)AmuletAnimationHelper.GLARE));
            CompoundTag tag = item.m_41783_();
            float pulseIntensity = 0.0f;
            if (tag != null) {
                pulseIntensity = Math.min(1.0f, tag.m_128457_("Power"));
            }
            AmuletAnimationHelper.drawGlare(stack, hand, 1.0f, -0.33, 0.089, -0.43, 0.5058824f, 0.0f, 0.7764706f, 0.0f, player.f_19797_, partialTicks, consumer, pulseIntensity);
        }
    }
}

