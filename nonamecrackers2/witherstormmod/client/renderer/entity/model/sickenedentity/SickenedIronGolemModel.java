/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.HierarchicalModel
 *  net.minecraft.client.model.IronGolemModel
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.util.Mth
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model.sickenedentity;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.util.Mth;
import nonamecrackers2.witherstormmod.common.entity.SickenedIronGolem;

public class SickenedIronGolemModel<T extends SickenedIronGolem>
extends HierarchicalModel<T> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public SickenedIronGolemModel(ModelPart root) {
        this.root = root;
        this.head = root.m_171324_("head");
        this.rightArm = root.m_171324_("right_arm");
        this.leftArm = root.m_171324_("left_arm");
        this.rightLeg = root.m_171324_("right_leg");
        this.leftLeg = root.m_171324_("left_leg");
    }

    public ModelPart m_142109_() {
        return this.root;
    }

    public static LayerDefinition createBodyLayer() {
        return IronGolemModel.m_170698_();
    }

    public void setupAnim(T entity, float walkAnimation, float animationSpeed, float bob, float yRot, float xRot) {
        this.head.f_104204_ = yRot * ((float)Math.PI / 180);
        this.head.f_104203_ = xRot * ((float)Math.PI / 180);
        this.rightLeg.f_104203_ = -1.5f * Mth.m_14156_((float)walkAnimation, (float)13.0f) * animationSpeed;
        this.leftLeg.f_104203_ = 1.5f * Mth.m_14156_((float)walkAnimation, (float)13.0f) * animationSpeed;
        this.rightLeg.f_104204_ = 0.0f;
        this.leftLeg.f_104204_ = 0.0f;
    }

    public void prepareMobModel(T entity, float walkAnimation, float animationSpeed, float partialTicks) {
        int i = ((SickenedIronGolem)entity).getAttackAnimationTick();
        if (i > 0) {
            this.rightArm.f_104203_ = -2.0f + 1.5f * Mth.m_14156_((float)((float)i - partialTicks), (float)10.0f);
            this.leftArm.f_104203_ = -2.0f + 1.5f * Mth.m_14156_((float)((float)i - partialTicks), (float)10.0f);
        } else {
            this.rightArm.f_104203_ = (-0.2f + 1.5f * Mth.m_14156_((float)walkAnimation, (float)13.0f)) * animationSpeed;
            this.leftArm.f_104203_ = (-0.2f - 1.5f * Mth.m_14156_((float)walkAnimation, (float)13.0f)) * animationSpeed;
        }
    }
}

