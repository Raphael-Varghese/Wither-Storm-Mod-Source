/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.EyesLayer
 *  net.minecraft.resources.ResourceLocation
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.layer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import nonamecrackers2.witherstormmod.client.renderer.entity.model.WitheredSymbiontModel;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;

public class WitheredSymbiontEyesLayer
extends EyesLayer<WitheredSymbiontEntity, WitheredSymbiontModel<WitheredSymbiontEntity>> {
    private static final RenderType EYES = RenderType.m_110488_((ResourceLocation)new ResourceLocation("witherstormmod", "textures/entity/withered_symbiont/withered_symbiont_emissive.png"));

    public WitheredSymbiontEyesLayer(RenderLayerParent<WitheredSymbiontEntity, WitheredSymbiontModel<WitheredSymbiontEntity>> renderer) {
        super(renderer);
    }

    public RenderType m_5708_() {
        return EYES;
    }
}

