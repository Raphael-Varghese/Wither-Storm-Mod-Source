/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Font
 *  net.minecraft.client.gui.GuiGraphics
 *  net.minecraft.client.gui.components.AbstractSelectionList$Entry
 *  net.minecraft.client.gui.components.Button
 *  net.minecraft.client.gui.components.Button$Builder
 *  net.minecraft.client.gui.components.ObjectSelectionList
 *  net.minecraft.client.gui.components.ObjectSelectionList$Entry
 *  net.minecraft.client.gui.components.Tooltip
 *  net.minecraft.client.gui.components.events.GuiEventListener
 *  net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.resources.MobEffectTextureManager
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.FormattedText
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.FormattedCharSequence
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ContainerListener
 *  net.minecraft.world.item.ItemStack
 */
package nonamecrackers2.witherstormmod.client.gui.menu;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSelectionList;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.MobEffectTextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.item.ItemStack;
import nonamecrackers2.witherstormmod.common.blockentity.inventory.AbstractSuperBeaconMenu;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.packet.SuperBeaconSetEffectMessage;
import nonamecrackers2.witherstormmod.common.packet.SuperBeaconToggleAreaMessage;

public class SuperBeaconScreen
extends AbstractContainerScreen<AbstractSuperBeaconMenu> {
    private static final ResourceLocation BORDER = new ResourceLocation("witherstormmod", "textures/gui/container/super_beacon.png");
    private static final ResourceLocation WINDOW = new ResourceLocation("witherstormmod", "textures/gui/container/super_beacon_window.png");
    private static final ResourceLocation BUTTONS = new ResourceLocation("witherstormmod", "textures/gui/container/super_beacon_buttons.png");
    private static final Component SELECTED_EFFECT = Component.m_237115_((String)"container.witherstormmod.withered_beacon.selected");
    private static final Component AVAILABLE_EFFECTS = Component.m_237115_((String)"container.witherstormmod.withered_beacon.available_effects");
    private static final Component INFO = Component.m_237115_((String)"withered_beacon.info");
    private static final int WINDOW_X = 10;
    private static final int WINDOW_Y = 9;
    private static final int WINDOW_WIDTH = 210;
    private static final int WINDOW_HEIGHT = 116;
    private static final int DIVIDED_WINDOW_WIDTH = 105;
    private static final int BUTTON_WIDTH = 22;
    private static final int BOTTOM_BAR_HEIGHT = 32;
    @Nullable
    private MobEffect primary;
    private int level;
    private final EffectList effectList;
    @Nullable
    private BeaconButton select;
    @Nullable
    private BeaconButton unselect;
    @Nullable
    private Button info;
    @Nullable
    private Button exitInfo;
    @Nullable
    private BeaconButton showArea;
    private boolean shouldRenderInfo;
    private boolean shouldShowArea;
    private int setEffectCooldown;

    public SuperBeaconScreen(final AbstractSuperBeaconMenu menu, Inventory inventory, Component name) {
        super((AbstractContainerMenu)menu, inventory, name);
        this.f_97726_ = 230;
        this.f_97727_ = 157;
        this.effectList = new EffectList(Minecraft.m_91087_(), 105, 116, 9, 125);
        this.effectList.m_93507_(115);
        menu.m_38893_(new ContainerListener(){

            public void m_7934_(AbstractContainerMenu container, int slot, ItemStack stack) {
            }

            public void m_142153_(AbstractContainerMenu container, int slot, int value) {
                SuperBeaconScreen.this.primary = menu.getPrimaryEffect();
                SuperBeaconScreen.this.level = menu.getLevel();
                SuperBeaconScreen.this.shouldShowArea = menu.shouldShowArea();
                SuperBeaconScreen.this.setEffectCooldown = menu.getCooldown();
            }
        });
    }

    protected void m_7856_() {
        super.m_7856_();
        int width = (this.f_96543_ - this.f_97726_) / 2;
        int height = (this.f_96544_ - this.f_97727_) / 2;
        this.effectList.m_93437_(105, 116, height + 9 + 24, height + 9 + 116 - 4);
        this.effectList.m_93507_(width + 10 + 105 + 1);
        this.m_142416_((GuiEventListener)this.effectList);
        int buttonY = 125 + height + 16 - 11;
        int buttonMiddle = 115 + width;
        this.select = (BeaconButton)Button.m_253074_((Component)Component.m_237119_(), button -> {
            MobEffect effect = this.getSelectedEffect();
            if (effect != null) {
                WitherStormModPacketHandlers.MAIN.sendToServer((Object)new SuperBeaconSetEffectMessage(MobEffect.m_19459_((MobEffect)effect)));
                this.f_96541_.f_91074_.m_6915_();
            }
        }).m_252794_(buttonMiddle - 2 - 22, buttonY).m_253046_(22, 22).build(builder -> new BeaconButton((Button.Builder)builder, 88));
        this.unselect = (BeaconButton)Button.m_253074_((Component)Component.m_237119_(), button -> {
            if (!this.shouldRenderInfo) {
                WitherStormModPacketHandlers.MAIN.sendToServer((Object)new SuperBeaconSetEffectMessage(MobEffect.m_19459_(null)));
            }
        }).m_252794_(buttonMiddle + 2, buttonY).m_253046_(22, 22).build(builder -> new BeaconButton((Button.Builder)builder, 110));
        this.info = Button.m_253074_((Component)Component.m_237113_((String)"i"), button -> {
            this.shouldRenderInfo = true;
            this.m_7787_((GuiEventListener)this.exitInfo);
        }).m_252794_(width + 10, buttonY).m_253046_(20, 20).m_253136_();
        this.exitInfo = Button.m_253074_((Component)Component.m_237113_((String)"X").m_130940_(ChatFormatting.RED), button -> {
            this.shouldRenderInfo = false;
            this.m_169411_((GuiEventListener)button);
        }).m_252794_(width - 20, height - 30).m_253046_(20, 20).m_253136_();
        this.showArea = (BeaconButton)Button.m_253074_((Component)Component.m_237119_(), button -> {
            WitherStormModPacketHandlers.MAIN.sendToServer((Object)new SuperBeaconToggleAreaMessage(!this.shouldShowArea));
            this.f_96541_.f_91074_.m_6915_();
        }).m_252794_(width + this.f_97726_ - 10 - 22, buttonY).m_253046_(22, 22).m_257505_(Tooltip.m_257550_((Component)Component.m_237115_((String)"gui.witherstormmod.button.showArea.description"))).build(builder -> new BeaconButton((Button.Builder)builder, 132));
        if (this.shouldRenderInfo) {
            this.m_7787_((GuiEventListener)this.exitInfo);
        }
        this.exitInfo.setFGColor(0xFF0000);
        this.m_7787_((GuiEventListener)this.select);
        this.m_7787_((GuiEventListener)this.unselect);
        this.m_7787_((GuiEventListener)this.info);
        this.m_7787_((GuiEventListener)this.showArea);
    }

    public void m_88315_(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
        this.m_280273_(stack);
        super.m_88315_(stack, mouseX, mouseY, partialTicks);
        MobEffect effect = this.getSelectedEffect();
        if (this.setEffectCooldown > 0) {
            this.select.m_257544_(Tooltip.m_257550_((Component)Component.m_237115_((String)"gui.witherstormmod.button.select.cooldown.description")));
        } else {
            this.select.m_257544_(null);
        }
        this.select.f_93623_ = !this.shouldRenderInfo && effect != null && effect != this.primary && this.setEffectCooldown == 0;
        this.unselect.f_93623_ = !this.shouldRenderInfo && this.primary != null;
        this.info.f_93623_ = !this.shouldRenderInfo;
        this.showArea.f_93623_ = !this.shouldRenderInfo;
        this.showArea.overlayTexY = 22 * (this.shouldShowArea ? 6 : 7);
        this.showArea.m_88315_(stack, mouseX, mouseY, partialTicks);
        this.select.m_88315_(stack, mouseX, mouseY, partialTicks);
        this.unselect.m_88315_(stack, mouseX, mouseY, partialTicks);
        this.info.m_88315_(stack, mouseX, mouseY, partialTicks);
        if (this.shouldRenderInfo) {
            stack.m_280168_().m_85836_();
            stack.m_280168_().m_85837_(0.0, 0.0, 1.0);
            this.m_280273_(stack);
            this.exitInfo.m_88315_(stack, mouseX, mouseY, partialTicks);
            int textX = (this.f_96543_ - this.f_97726_) / 2 - 20;
            int textWidth = this.f_97726_ + 40;
            int textHeight = this.f_96547_.m_92920_(INFO.getString(), textWidth);
            int textY = (this.f_96544_ - this.f_97727_) / 2 + this.f_97727_ / 2 - textHeight / 2;
            stack.m_280168_().m_85837_(0.0, 0.0, 1.0);
            List text = this.f_96547_.m_92923_((FormattedText)INFO, textWidth);
            for (int i = 0; i < text.size(); ++i) {
                FormattedCharSequence formattedCharSequence = (FormattedCharSequence)text.get(i);
                Objects.requireNonNull(this.f_96547_);
                stack.m_280648_(this.f_96547_, formattedCharSequence, textX, i * 9 + textY, -1);
            }
            stack.m_280168_().m_85849_();
        }
    }

    protected void m_7286_(GuiGraphics stack, float partialTicks, int mouseX, int mouseY) {
        this.drawMenu(stack, WINDOW);
    }

    protected void m_280003_(GuiGraphics stack, int mouseX, int mouseY) {
        stack.m_280218_(BORDER, 0, 0, 0, 0, this.f_97726_, this.f_97727_);
        stack.m_280218_(BORDER, 116, 13, 0, this.f_97727_, 105, 20);
        stack.m_280653_(this.f_96547_, SELECTED_EFFECT, 62, 19, -1);
        stack.m_280653_(this.f_96547_, AVAILABLE_EFFECTS, 167, 19, -1);
        MutableComponent mutableComponent = Component.m_237110_((String)"container.witherstormmod.withered_beacon.level", (Object[])new Object[]{this.level > 0 ? Component.m_237115_((String)("enchantment.level." + this.level)) : ""});
        Objects.requireNonNull(this.f_96547_);
        stack.m_280430_(this.f_96547_, (Component)mutableComponent, 20, 115 - 9, -1);
        if (this.primary != null) {
            MobEffectTextureManager textureManager = this.f_96541_.m_91306_();
            TextureAtlasSprite sprite = textureManager.m_118732_(this.primary);
            int x = 62 - (int)((float)sprite.m_245424_().m_246492_() * 1.5f);
            int y = 67 - (int)((float)sprite.m_245424_().m_245330_() * 1.5f) - 8;
            stack.m_280159_(x, y, 0, 54, 54, sprite);
            stack.m_280653_(this.f_96547_, this.primary.m_19482_(), 62, y + sprite.m_245424_().m_246492_() * 3, -1);
        }
    }

    private void drawMenu(GuiGraphics stack, ResourceLocation tex) {
        stack.m_280246_(1.0f, 1.0f, 1.0f, 1.0f);
        int width = (this.f_96543_ - this.f_97726_) / 2;
        int height = (this.f_96544_ - this.f_97727_) / 2;
        stack.m_280218_(tex, width, height, 0, 0, this.f_97726_, this.f_97727_);
    }

    @Nullable
    private MobEffect getSelectedEffect() {
        EffectList.Entry entry = (EffectList.Entry)this.effectList.m_93511_();
        if (entry != null) {
            return entry.getEffect();
        }
        return null;
    }

    public void setValidEffects(Set<MobEffect> effects) {
        this.effectList.clear();
        for (MobEffect effect : effects) {
            this.effectList.addEffect(effect);
        }
    }

    public static class EffectList
    extends ObjectSelectionList<Entry> {
        public EffectList(Minecraft mc, int width, int height, int top, int bottom) {
            super(mc, width, height, top, bottom, 20);
            this.m_93488_(false);
            this.m_93496_(false);
        }

        public void addEffect(MobEffect effect) {
            this.m_7085_((AbstractSelectionList.Entry)new Entry(effect));
        }

        public int m_5759_() {
            return this.getWidth();
        }

        protected int m_5756_() {
            return this.f_93393_ + this.getWidth() - 7;
        }

        public void clear() {
            this.m_93516_();
        }

        public class Entry
        extends ObjectSelectionList.Entry<Entry> {
            private final MobEffect effect;
            private final Component name;

            private Entry(MobEffect effect) {
                this.effect = effect;
                this.name = effect.m_19482_().m_6881_();
            }

            public Component m_142172_() {
                return this.name;
            }

            public void m_6311_(GuiGraphics stack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean selected, float partialTicks) {
                MobEffectTextureManager textureManager = EffectList.this.f_93386_.m_91306_();
                TextureAtlasSprite sprite = textureManager.m_118732_(this.effect);
                stack.m_280246_(1.0f, 1.0f, 1.0f, 1.0f);
                stack.m_280159_(left, top + height / 2 - 9, 0, 18, 18, sprite);
                Font font = ((EffectList)EffectList.this).f_93386_.f_91062_;
                int n = top + height / 2;
                Objects.requireNonNull(((EffectList)EffectList.this).f_93386_.f_91062_);
                stack.m_280430_(font, this.name, left + 28, n - 9 / 2, -1);
            }

            public MobEffect getEffect() {
                return this.effect;
            }

            public boolean m_6375_(double x, double y, int clickType) {
                if (clickType == 0) {
                    EffectList.this.m_6987_((AbstractSelectionList.Entry)this);
                    return true;
                }
                return false;
            }
        }
    }

    private static class BeaconButton
    extends Button {
        public int overlayTexY;

        public BeaconButton(Button.Builder builder, int overlayTexY) {
            super(builder);
            this.overlayTexY = overlayTexY;
        }

        public void m_87963_(GuiGraphics stack, int mouseX, int mouseY, float partialTikcs) {
            int i = 0;
            if (this.m_198029_()) {
                i += 22;
            }
            if (!this.m_142518_()) {
                i = 44;
            }
            RenderSystem.enableDepthTest();
            stack.m_280163_(BUTTONS, this.m_252754_(), this.m_252907_(), 0.0f, (float)i, this.f_93618_, this.f_93619_, 255, 255);
            stack.m_280163_(BUTTONS, this.m_252754_(), this.m_252907_(), 0.0f, (float)this.overlayTexY, this.f_93618_, this.f_93619_, 255, 255);
        }
    }
}

