/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.geom.builders.CubeDefinition
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.UVPair
 *  org.joml.Vector3f
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package nonamecrackers2.witherstormmod.mixin;

import net.minecraft.client.model.geom.builders.CubeDefinition;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.UVPair;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={CubeDefinition.class})
public interface IMixinCubeDefinition {
    @Accessor
    public Vector3f getOrigin();

    @Accessor
    public Vector3f getDimensions();

    @Accessor
    public CubeDeformation getGrow();

    @Accessor
    public boolean getMirror();

    @Accessor
    public UVPair getTexCoord();

    @Accessor
    public UVPair getTexScale();
}

