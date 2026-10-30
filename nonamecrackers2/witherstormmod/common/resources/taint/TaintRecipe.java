/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 */
package nonamecrackers2.witherstormmod.common.resources.taint;

import com.google.gson.JsonObject;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public abstract class TaintRecipe
implements Comparable<TaintRecipe> {
    @Nullable
    protected final MobEffect effect;
    protected final BlockState replacement;
    protected final List<Property<?>> propertiesToCopy;

    public TaintRecipe(@Nullable MobEffect effect, BlockState replacement, List<Property<?>> propertiesToCopy) {
        this.effect = effect;
        this.replacement = replacement;
        this.propertiesToCopy = propertiesToCopy;
    }

    @Nullable
    public MobEffect effect() {
        return this.effect;
    }

    public BlockState replacement() {
        return this.replacement;
    }

    public List<Property<?>> propertiesToCopy() {
        return this.propertiesToCopy;
    }

    public abstract boolean canConvertBlock(BlockState var1);

    public abstract void serializeFrom(JsonObject var1);

    public abstract String getName();

    public boolean canConvertWithPotion(Potion potion) {
        if (this.effect == null) {
            return false;
        }
        return potion.m_43488_().stream().anyMatch(e -> e.m_19544_() == this.effect);
    }
}

