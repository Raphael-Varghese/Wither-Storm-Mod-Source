/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package nonamecrackers2.witherstormmod.client.resources.color;

import java.awt.Color;
import javax.annotation.Nullable;

public record SkyColorSet(Color skyColor, Color cloudColor, Color fogColor, @Nullable Color nightSkyColor, @Nullable Color nightCloudColor, @Nullable Color nightFogColor) {
    public static final SkyColorSet DEFAULT_SKY_COLORS = new SkyColorSet(new Color(20, 0, 19), new Color(28, 10, 27), new Color(133, 69, 62));

    public SkyColorSet(Color skyColor, Color cloudColor, Color fogColor) {
        this(skyColor, cloudColor, fogColor, null, null, null);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Color skyDarkenColor = DEFAULT_SKY_COLORS.skyColor();
        private Color skyDarkenCloudColor = DEFAULT_SKY_COLORS.cloudColor();
        private Color skyDarkenFogColor = DEFAULT_SKY_COLORS.fogColor();
        @Nullable
        private Color nightSkyDarkenColor;
        @Nullable
        private Color nightSkyDarkenCloudColor;
        @Nullable
        private Color nightSkyDarkenFogColor;

        private Builder() {
        }

        public Builder setSkyColor(Color color) {
            this.skyDarkenColor = color;
            return this;
        }

        public Builder setCloudColor(Color color) {
            this.skyDarkenCloudColor = color;
            return this;
        }

        public Builder setFogColor(Color color) {
            this.skyDarkenFogColor = color;
            return this;
        }

        public Builder setNightSkyColor(Color color) {
            this.nightSkyDarkenColor = color;
            return this;
        }

        public Builder setNightCloudColor(Color color) {
            this.nightSkyDarkenCloudColor = color;
            return this;
        }

        public Builder setNightFogColor(Color color) {
            this.nightSkyDarkenFogColor = color;
            return this;
        }

        public SkyColorSet build() {
            return new SkyColorSet(this.skyDarkenColor, this.skyDarkenCloudColor, this.skyDarkenFogColor, this.nightSkyDarkenColor, this.nightSkyDarkenCloudColor, this.nightSkyDarkenFogColor);
        }
    }
}

