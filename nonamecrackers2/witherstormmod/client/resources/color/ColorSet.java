/*
 * Decompiled with CFR 0.152.
 */
package nonamecrackers2.witherstormmod.client.resources.color;

import java.awt.Color;
import nonamecrackers2.witherstormmod.client.resources.color.SkyColorSet;

public record ColorSet(Color tractorBeamColor, Color tractorBeamNightColor, Color nightShineColor, SkyColorSet skyColors) {
    public static final ColorSet DEFAULT = new ColorSet(new Color(128, 77, 204), new Color(128, 77, 204), new Color(150, 59, 255, 75), SkyColorSet.DEFAULT_SKY_COLORS);

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Color tractorBeamColor;
        private Color tractorBeamNightColor;
        private Color nightShineColor;
        private SkyColorSet skyColors;

        private Builder() {
            this.tractorBeamColor = ColorSet.DEFAULT.tractorBeamColor;
            this.tractorBeamNightColor = ColorSet.DEFAULT.tractorBeamNightColor;
            this.nightShineColor = ColorSet.DEFAULT.nightShineColor;
            this.skyColors = ColorSet.DEFAULT.skyColors;
        }

        public Color getTractorBeamColor() {
            return this.tractorBeamColor;
        }

        public Color getTractorBeamNightColor() {
            return this.tractorBeamNightColor;
        }

        public Color getNightShineColor() {
            return this.nightShineColor;
        }

        public SkyColorSet getSkyColors() {
            return this.skyColors;
        }

        public Builder setTractorBeamColor(Color color) {
            this.tractorBeamColor = color;
            this.tractorBeamNightColor = color;
            return this;
        }

        public Builder setTractorBeamNightColor(Color color) {
            this.tractorBeamNightColor = color;
            return this;
        }

        public Builder setNightShineColor(Color color) {
            this.nightShineColor = color;
            return this;
        }

        public Builder setSkyColors(SkyColorSet colors) {
            this.skyColors = colors;
            return this;
        }

        public Builder copy() {
            return new Builder().setTractorBeamColor(this.tractorBeamColor).setNightShineColor(this.nightShineColor).setTractorBeamNightColor(this.tractorBeamNightColor).setSkyColors(this.skyColors);
        }

        public ColorSet build() {
            return new ColorSet(this.tractorBeamColor, this.tractorBeamNightColor, this.nightShineColor, this.skyColors);
        }
    }
}

