/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraftforge.registries.ForgeRegistries
 */
package nonamecrackers2.witherstormmod.common.resources.taint;

import com.google.gson.JsonObject;
import java.util.List;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.common.resources.taint.TagBasedTaintRecipe;
import nonamecrackers2.witherstormmod.common.resources.taint.TaintRecipe;

public class SingleBlockTaintRecipe
extends TaintRecipe {
    private final Block block;

    public SingleBlockTaintRecipe(Block block, MobEffect effect, BlockState replacement, List<Property<?>> propertiesToCopy) {
        super(effect, replacement, propertiesToCopy);
        this.block = block;
    }

    @Override
    public boolean canConvertBlock(BlockState state) {
        return state.m_60713_(this.block);
    }

    @Override
    public String getName() {
        return ForgeRegistries.BLOCKS.getKey((Object)this.block).m_135815_();
    }

    @Override
    public void serializeFrom(JsonObject object) {
        object.addProperty("block", ForgeRegistries.BLOCKS.getKey((Object)this.block).toString());
    }

    public Block getBlock() {
        return this.block;
    }

    @Override
    public int compareTo(TaintRecipe o) {
        return o instanceof TagBasedTaintRecipe ? 1 : 0;
    }
}

