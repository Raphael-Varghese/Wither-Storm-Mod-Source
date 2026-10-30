/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.core.Direction
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.common.capabilities.Capability
 *  net.minecraftforge.common.capabilities.CapabilityManager
 *  net.minecraftforge.common.capabilities.CapabilityToken
 *  net.minecraftforge.common.capabilities.ICapabilityProvider
 *  net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
 *  net.minecraftforge.common.util.LazyOptional
 *  net.minecraftforge.event.AttachCapabilitiesEvent
 */
package nonamecrackers2.witherstormmod.client.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import nonamecrackers2.witherstormmod.WitherStormMod;
import nonamecrackers2.witherstormmod.client.audio.ISoundManager;
import nonamecrackers2.witherstormmod.client.audio.bosstheme.BossThemeManager;
import nonamecrackers2.witherstormmod.client.capability.BowelsEffectsManager;
import nonamecrackers2.witherstormmod.client.capability.CommandBlockSoundManager;
import nonamecrackers2.witherstormmod.client.capability.FormidiBladeLoopManager;
import nonamecrackers2.witherstormmod.client.capability.FormidibombEffectsManager;
import nonamecrackers2.witherstormmod.client.capability.PlayerCameraShaker;
import nonamecrackers2.witherstormmod.client.capability.PlayerScreenBlinder;
import nonamecrackers2.witherstormmod.client.capability.PlayerTractorBeamEffects;
import nonamecrackers2.witherstormmod.client.capability.SoundManagersHolder;
import nonamecrackers2.witherstormmod.client.capability.WitherStormDistantRenderer;
import nonamecrackers2.witherstormmod.client.capability.WitherStormHeadSoundManager;
import nonamecrackers2.witherstormmod.client.capability.WitherStormLoopingSoundManager;
import nonamecrackers2.witherstormmod.client.capability.WitheredSymbiontSoundManager;
import nonamecrackers2.witherstormmod.client.capability.WitheredSymbiontSpellLoopManager;
import nonamecrackers2.witherstormmod.client.event.WitherStormAmbienceEffects;

public class WitherStormModClientCapabilities {
    public static final Capability<WitherStormDistantRenderer> DISTANT_RENDERER = CapabilityManager.get((CapabilityToken)new CapabilityToken<WitherStormDistantRenderer>(){});
    public static final Capability<SoundManagersHolder> SOUND_MANAGERS = CapabilityManager.get((CapabilityToken)new CapabilityToken<SoundManagersHolder>(){});
    public static final Capability<WitherStormLoopingSoundManager> LOOPING_MANAGER = CapabilityManager.get((CapabilityToken)new CapabilityToken<WitherStormLoopingSoundManager>(){});
    public static final Capability<PlayerCameraShaker> CAMERA_SHAKER = CapabilityManager.get((CapabilityToken)new CapabilityToken<PlayerCameraShaker>(){});
    public static final Capability<FormidibombEffectsManager> FORMIDIBOMB_EFFECTS = CapabilityManager.get((CapabilityToken)new CapabilityToken<FormidibombEffectsManager>(){});
    public static final Capability<PlayerScreenBlinder> SCREEN_BLINDER = CapabilityManager.get((CapabilityToken)new CapabilityToken<PlayerScreenBlinder>(){});
    public static final Capability<CommandBlockSoundManager> COMMAND_BLOCK_SOUND_MANAGER = CapabilityManager.get((CapabilityToken)new CapabilityToken<CommandBlockSoundManager>(){});
    public static final Capability<WitheredSymbiontSpellLoopManager> WITHERED_SYMBIONT_SPELL_LOOP_MANAGER = CapabilityManager.get((CapabilityToken)new CapabilityToken<WitheredSymbiontSpellLoopManager>(){});
    public static final Capability<WitheredSymbiontSoundManager> WITHERED_SYMBIONT_SOUND_MANAGER = CapabilityManager.get((CapabilityToken)new CapabilityToken<WitheredSymbiontSoundManager>(){});
    public static final Capability<BossThemeManager> BOSS_THEME_MANAGER = CapabilityManager.get((CapabilityToken)new CapabilityToken<BossThemeManager>(){});
    public static final Capability<BowelsEffectsManager> BOWELS_EFFECTS_MANAGER = CapabilityManager.get((CapabilityToken)new CapabilityToken<BowelsEffectsManager>(){});
    public static final Capability<WitherStormHeadSoundManager> WITHER_STORM_HEAD_SOUND_MANAGER = CapabilityManager.get((CapabilityToken)new CapabilityToken<WitherStormHeadSoundManager>(){});
    public static final Capability<PlayerTractorBeamEffects> TRACTOR_BEAM_EFFECTS = CapabilityManager.get((CapabilityToken)new CapabilityToken<PlayerTractorBeamEffects>(){});
    public static final Capability<WitherStormAmbienceEffects> AMBIENT_EFFECTS = CapabilityManager.get((CapabilityToken)new CapabilityToken<WitherStormAmbienceEffects>(){});
    public static final Capability<FormidiBladeLoopManager> FORMIDI_BLADE_LOOP_MANAGER = CapabilityManager.get((CapabilityToken)new CapabilityToken<FormidiBladeLoopManager>(){});

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(WitherStormDistantRenderer.class);
        event.register(SoundManagersHolder.class);
        event.register(WitherStormLoopingSoundManager.class);
        event.register(PlayerCameraShaker.class);
        event.register(FormidibombEffectsManager.class);
        event.register(PlayerScreenBlinder.class);
        event.register(CommandBlockSoundManager.class);
        event.register(WitheredSymbiontSpellLoopManager.class);
        event.register(WitheredSymbiontSoundManager.class);
        event.register(BossThemeManager.class);
        event.register(BowelsEffectsManager.class);
        event.register(WitherStormHeadSoundManager.class);
        event.register(PlayerTractorBeamEffects.class);
        event.register(WitherStormAmbienceEffects.class);
        event.register(FormidiBladeLoopManager.class);
    }

    public static void attachWorldCapabilities(AttachCapabilitiesEvent<Level> event) {
        Level world = (Level)event.getObject();
        if (world.f_46443_) {
            Minecraft mc = Minecraft.m_91087_();
            final LazyOptional distantRenderer = LazyOptional.of(() -> new WitherStormDistantRenderer(mc));
            event.addCapability(new ResourceLocation("witherstormmod", "distant_renderer"), new ICapabilityProvider(){

                public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
                    return capability == DISTANT_RENDERER ? distantRenderer.cast() : LazyOptional.empty();
                }
            });
            event.addListener(() -> ((LazyOptional)distantRenderer).invalidate());
            if (world.m_46472_().m_135782_().equals((Object)WitherStormMod.bowelsLocation())) {
                final LazyOptional bowelsEffects = LazyOptional.of(() -> new BowelsEffectsManager(mc));
                event.addCapability(new ResourceLocation("witherstormmod", "bowels_effects_manager"), new ICapabilityProvider(){

                    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
                        return capability == BOWELS_EFFECTS_MANAGER ? bowelsEffects.cast() : LazyOptional.empty();
                    }
                });
                event.addListener(() -> ((LazyOptional)bowelsEffects).invalidate());
            }
            final LazyOptional ambientEffects = LazyOptional.of(() -> new WitherStormAmbienceEffects(mc));
            event.addCapability(new ResourceLocation("witherstormmod", "wither_storm_ambience_effects"), new ICapabilityProvider(){

                public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
                    return cap == AMBIENT_EFFECTS ? ambientEffects.cast() : LazyOptional.empty();
                }
            });
            event.addListener(() -> ((LazyOptional)ambientEffects).invalidate());
            SoundManagersHolder holder = new SoundManagersHolder();
            final LazyOptional holderOptional = LazyOptional.of(() -> holder);
            event.addCapability(new ResourceLocation("witherstormmod", "sound_managers_holder"), new ICapabilityProvider(){

                public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
                    return capability == SOUND_MANAGERS ? holderOptional.cast() : LazyOptional.empty();
                }
            });
            event.addListener(() -> ((LazyOptional)holderOptional).invalidate());
            WitherStormModClientCapabilities.registerSoundManager(event, holder, "looping_sound_manager", new WitherStormLoopingSoundManager(mc), LOOPING_MANAGER);
            WitherStormModClientCapabilities.registerSoundManager(event, holder, "formidibomb_effects_manager", new FormidibombEffectsManager(mc), FORMIDIBOMB_EFFECTS);
            WitherStormModClientCapabilities.registerSoundManager(event, holder, "command_block_sound_manager", new CommandBlockSoundManager(mc), COMMAND_BLOCK_SOUND_MANAGER);
            WitherStormModClientCapabilities.registerSoundManager(event, holder, "withered_symbiont_spell_loop_manager", new WitheredSymbiontSpellLoopManager(mc), WITHERED_SYMBIONT_SPELL_LOOP_MANAGER);
            WitherStormModClientCapabilities.registerSoundManager(event, holder, "withered_symbiont_sound_manager", new WitheredSymbiontSoundManager(mc), WITHERED_SYMBIONT_SOUND_MANAGER);
            WitherStormModClientCapabilities.registerSoundManager(event, holder, "boss_theme_manager", new BossThemeManager(mc), BOSS_THEME_MANAGER);
            WitherStormModClientCapabilities.registerSoundManager(event, holder, "wither_storm_head_sound_manager", new WitherStormHeadSoundManager(mc), WITHER_STORM_HEAD_SOUND_MANAGER);
            WitherStormModClientCapabilities.registerSoundManager(event, holder, "formidi_blade_loop_manager", new FormidiBladeLoopManager(mc), FORMIDI_BLADE_LOOP_MANAGER);
        }
    }

    private static <H> void registerSoundManager(AttachCapabilitiesEvent<Level> event, SoundManagersHolder holder, String id, ISoundManager manager, final Capability<H> capability) {
        final LazyOptional optional = LazyOptional.of(() -> manager);
        event.addCapability(new ResourceLocation("witherstormmod", id), new ICapabilityProvider(){

            public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
                return cap == capability ? optional.cast() : LazyOptional.empty();
            }
        });
        event.addListener(() -> ((LazyOptional)optional).invalidate());
        holder.putManager(manager);
    }

    public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = (Entity)event.getObject();
        if (entity instanceof LocalPlayer) {
            LocalPlayer player = (LocalPlayer)entity;
            final LazyOptional cameraShaker = LazyOptional.of(() -> new PlayerCameraShaker(player));
            event.addCapability(new ResourceLocation("witherstormmod", "camera_shaker"), new ICapabilityProvider(){

                public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
                    return capability == CAMERA_SHAKER ? cameraShaker.cast() : LazyOptional.empty();
                }
            });
            event.addListener(() -> ((LazyOptional)cameraShaker).invalidate());
            final LazyOptional screenBlinder = LazyOptional.of(PlayerScreenBlinder::new);
            event.addCapability(new ResourceLocation("witherstormmod", "blinder"), new ICapabilityProvider(){

                public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
                    return capability == SCREEN_BLINDER ? screenBlinder.cast() : LazyOptional.empty();
                }
            });
            event.addListener(() -> ((LazyOptional)screenBlinder).invalidate());
            final LazyOptional tractorBeamEffects = LazyOptional.of(() -> new PlayerTractorBeamEffects(player));
            event.addCapability(new ResourceLocation("witherstormmod", "tractor_beam_effects"), new ICapabilityProvider(){

                public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
                    return capability == TRACTOR_BEAM_EFFECTS ? tractorBeamEffects.cast() : LazyOptional.empty();
                }
            });
            event.addListener(() -> ((LazyOptional)tractorBeamEffects).invalidate());
        }
    }
}

