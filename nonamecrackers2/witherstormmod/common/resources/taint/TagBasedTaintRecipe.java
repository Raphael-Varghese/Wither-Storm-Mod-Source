/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 */
package nonamecrackers2.witherstormmod.common.resources.taint;

import com.google.gson.JsonObject;
import java.util.List;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import nonamecrackers2.witherstormmod.common.resources.taint.SingleBlockTaintRecipe;
import nonamecrackers2.witherstormmod.common.resources.taint.TaintRecipe;

public class TagBasedTaintRecipe
extends TaintRecipe {
    private final TagKey<Block> tag;

    public TagBasedTaintRecipe(TagKey<Block> tag, MobEffect effect, BlockState replacement, List<Property<?>> propertiesToCopy) {
        super(effect, replacement, propertiesToCopy);
        this.tag = tag;
    }

    @Override
    public boolean canConvertBlock(BlockState state) {
        return state.m_204336_(this.tag);
    }

    @Override
    public String getName() {
        return this.tag.f_203868_().m_135815_();
    }

    @Override
    public void serializeFrom(JsonObject object) {
        object.addProperty("block", "#" + this.tag.f_203868_().toString());
    }

    public TagKey<Block> getTag() {
        return this.tag;
    }

    @Override
    public int compareTo(TaintRecipe o) {
        return o instanceof SingleBlockTaintRecipe ? -1 : 0;
    }
}

