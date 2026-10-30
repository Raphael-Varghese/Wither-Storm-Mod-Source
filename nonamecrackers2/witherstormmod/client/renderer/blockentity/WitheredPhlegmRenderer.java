/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.blockentity.BlockEntityRenderer
 *  net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.ItemRenderer
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.item.ItemDisplayContext
 *  net.minecraft.world.item.ItemStack
 */
package nonamecrackers2.witherstormmod.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import nonamecrackers2.witherstormmod.common.blockentity.WitheredPhlegmBlockEntity;

public class WitheredPhlegmRenderer
implements BlockEntityRenderer<WitheredPhlegmBlockEntity> {
    private final ItemRenderer itemRenderer;

    public WitheredPhlegmRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.m_234447_();
    }

    public void render(WitheredPhlegmBlockEntity entity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight, int overlayTexture) {
        stack.m_85837_(0.5, 0.5, 0.5);
        long seed = entity.m_58899_().m_121878_();
        RandomSource random = RandomSource.m_216335_((long)seed);
        int totalItems = 0;
        for (int i = 0; i < entity.m_6643_(); ++i) {
            if (entity.m_8020_(i).m_41619_()) continue;
            ++totalItems;
        }
        float ratio = (float)totalItems / (float)entity.m_6643_();
        for (int i = 0; i < entity.m_6643_(); ++i) {
            ItemStack item = entity.m_8020_(i);
            if (item.m_41619_()) continue;
            stack.m_85836_();
            float scale = 0.5f + (1.0f - ratio) / 2.0f;
            float dist = 1.0f;
            stack.m_85841_(scale, scale, scale);
            stack.m_85837_(random.m_188500_() * (double)dist - (double)dist / 2.0, random.m_188500_() * (double)dist - (double)dist / 2.0, random.m_188500_() * (double)dist - (double)dist / 2.0);
            stack.m_252781_(Axis.f_252529_.m_252977_(random.m_188501_() * 360.0f));
            stack.m_252781_(Axis.f_252436_.m_252977_(random.m_188501_() * 360.0f));
            this.itemRenderer.m_269128_(item, ItemDisplayContext.FIXED, packedLight, overlayTexture, stack, buffer, entity.m_58904_(), (int)seed);
            stack.m_85849_();
        }
    }

    public int m_142163_() {
        return 32;
    }
}

