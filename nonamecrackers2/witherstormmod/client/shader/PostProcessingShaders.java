/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonSyntaxException
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.renderer.PostChain
 *  net.minecraft.client.renderer.PostPass
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.packs.resources.ResourceManager
 *  net.minecraft.server.packs.resources.ResourceManagerReloadListener
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package nonamecrackers2.witherstormmod.client.shader;

import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.mixin.IMixinPostChain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PostProcessingShaders
implements ResourceManagerReloadListener {
    public static final PostProcessingShaders INSTANCE = new PostProcessingShaders(Minecraft.m_91087_());
    private static final ResourceLocation ABERRATION = new ResourceLocation("witherstormmod", "shaders/post/aberration.json");
    private static final Logger LOGGER = LogManager.getLogger();
    private final Minecraft minecraft;
    private PostChain aberrationEffect;
    private float prevWidth;
    private float prevHeight;

    public PostProcessingShaders(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public void renderShaders(float partialTicks) {
        this.minecraft.m_91307_().m_6180_("chromatic_aberration_effect");
        if (this.shouldRenderChromaticAberration()) {
            if (this.prevWidth != (float)this.minecraft.m_91268_().m_85441_() || this.prevHeight != (float)this.minecraft.m_91268_().m_85442_()) {
                this.aberrationEffect.m_110025_(this.minecraft.m_91268_().m_85441_(), this.minecraft.m_91268_().m_85442_());
            }
            ClientLevel world = this.minecraft.f_91073_;
            world.getCapability(WitherStormModClientCapabilities.FORMIDIBOMB_EFFECTS).ifPresent(effects -> {
                if (effects.getStartFuse() > 0) {
                    float multiplier = ((float)effects.getStartFuse() - (float)effects.getLife()) / (float)effects.getStartFuse();
                    for (PostPass shader : this.getShaders()) {
                        shader.m_110074_().m_108960_("Multiplier").m_5985_(multiplier * 0.1f);
                    }
                    this.aberrationEffect.m_110023_(partialTicks);
                    this.minecraft.m_91385_().m_83947_(false);
                }
            });
        }
        this.minecraft.m_91307_().m_7238_();
        this.prevWidth = this.minecraft.m_91268_().m_85441_();
        this.prevHeight = this.minecraft.m_91268_().m_85442_();
    }

    public void initShader(ResourceManager manager) {
        if (this.aberrationEffect != null) {
            this.aberrationEffect.close();
        }
        this.aberrationEffect = null;
        try {
            this.aberrationEffect = new PostChain(this.minecraft.m_91097_(), manager, this.minecraft.m_91385_(), ABERRATION);
            this.aberrationEffect.m_110025_(this.minecraft.m_91268_().m_85441_(), this.minecraft.m_91268_().m_85442_());
        }
        catch (JsonSyntaxException e) {
            LOGGER.warn("Failed to parse shader: {}", (Object)ABERRATION, (Object)e);
        }
        catch (IOException e) {
            LOGGER.warn("Failed to load shader: {}", (Object)ABERRATION, (Object)e);
        }
    }

    public boolean shouldRenderChromaticAberration() {
        return (Boolean)WitherStormModConfig.CLIENT.chromaticAberration.get() != false && this.aberrationEffect != null;
    }

    public void m_6213_(ResourceManager resourceManager) {
        this.initShader(resourceManager);
    }

    public List<PostPass> getShaders() {
        return ((IMixinPostChain)this.aberrationEffect).getPasses();
    }
}

