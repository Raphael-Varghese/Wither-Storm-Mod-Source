/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.GsonHelper
 */
package nonamecrackers2.witherstormmod.client.resources.texture;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.function.Consumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import nonamecrackers2.witherstormmod.client.renderer.entity.AbstractWitherStormRenderer;

public record TextureSet(ResourceLocation invulnerable, ResourceLocation main, ResourceLocation emissiveDecal, ResourceLocation debrisRing) {
    public static final TextureSet DEFAULT = TextureSet.builder().build();

    public static Builder builder() {
        return new Builder();
    }

    public static TextureSet fromJson(JsonObject object) throws JsonSyntaxException {
        Builder builder = TextureSet.builder();
        TextureSet.applyTexture(builder::setInvulnerable, object, "invulnerable");
        TextureSet.applyTexture(builder::setMain, object, "main");
        TextureSet.applyTexture(builder::setEmissiveDecal, object, "emissive_decal");
        TextureSet.applyTexture(builder::setDebrisRing, object, "debris_ring");
        return builder.build();
    }

    private static void applyTexture(Consumer<ResourceLocation> consumer, JsonObject object, String path) throws JsonSyntaxException {
        if (object.has(path)) {
            String rawId = GsonHelper.m_13906_((JsonObject)object, (String)path);
            ResourceLocation id = ResourceLocation.m_135820_((String)rawId);
            if (id != null) {
                consumer.accept(id);
            } else {
                throw new JsonSyntaxException("Not a valid id: '" + rawId + "'");
            }
        }
    }

    public static class Builder {
        private ResourceLocation invulnerable = AbstractWitherStormRenderer.WITHER_STORM_INVULNERABLE_LOCATION;
        private ResourceLocation main = AbstractWitherStormRenderer.WITHER_STORM_LOCATION;
        private ResourceLocation emissiveDecal = AbstractWitherStormRenderer.EMISSIVE_DECAL;
        private ResourceLocation debrisRing = AbstractWitherStormRenderer.DEBRIS_RING;

        private Builder() {
        }

        public Builder setInvulnerable(ResourceLocation invulnerable) {
            this.invulnerable = invulnerable;
            return this;
        }

        public Builder setMain(ResourceLocation main) {
            this.main = main;
            return this;
        }

        public Builder setEmissiveDecal(ResourceLocation emissiveDecal) {
            this.emissiveDecal = emissiveDecal;
            return this;
        }

        public Builder setDebrisRing(ResourceLocation debrisRing) {
            this.debrisRing = debrisRing;
            return this;
        }

        public TextureSet build() {
            return new TextureSet(this.invulnerable, this.main, this.emissiveDecal, this.debrisRing);
        }
    }
}

