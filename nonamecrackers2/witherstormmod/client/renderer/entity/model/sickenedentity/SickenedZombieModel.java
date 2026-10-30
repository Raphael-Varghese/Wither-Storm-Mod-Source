/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.AbstractZombieModel
 *  net.minecraft.client.model.geom.ModelPart
 */
package nonamecrackers2.witherstormmod.client.renderer.entity.model.sickenedentity;

import net.minecraft.client.model.AbstractZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import nonamecrackers2.witherstormmod.common.entity.SickenedZombie;

@Deprecated
public class SickenedZombieModel<T extends SickenedZombie>
extends AbstractZombieModel<T> {
    public SickenedZombieModel(ModelPart part) {
        super(part);
    }

    public boolean isAggressive(T entity) {
        return entity.m_5912_();
    }
}

