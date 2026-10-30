/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.DefaultVertexFormat
 *  net.minecraft.client.renderer.ShaderInstance
 *  net.minecraftforge.client.event.RegisterShadersEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package nonamecrackers2.witherstormmod.client.init;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import java.io.IOException;
import java.util.Objects;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import nonamecrackers2.witherstormmod.WitherStormMod;

public class WitherStormModShaders {
    private static ShaderInstance witherStormShader;
    private static ShaderInstance witherStormDissolveShader;

    @SubscribeEvent
    public static void registerShaders(RegisterShadersEvent event) throws IOException {
        event.registerShader(new ShaderInstance(event.getResourceProvider(), WitherStormMod.id("wither_storm"), DefaultVertexFormat.f_85812_), s -> {
            witherStormShader = s;
        });
        event.registerShader(new ShaderInstance(event.getResourceProvider(), WitherStormMod.id("wither_storm_dissolve"), DefaultVertexFormat.f_85812_), s -> {
            witherStormDissolveShader = s;
        });
    }

    public static ShaderInstance getWitherStormShader() {
        return Objects.requireNonNull(witherStormShader, "The wither storm shader has not been created yet");
    }

    public static ShaderInstance getWitherStormDissolveShader() {
        return Objects.requireNonNull(witherStormDissolveShader, "The wither storm dissolve shader has not been created yet");
    }
}

