/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.SplashManager
 *  net.minecraft.server.packs.resources.ResourceManager
 *  net.minecraft.util.profiling.ProfilerFiller
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package nonamecrackers2.witherstormmod.mixin;

import java.util.List;
import net.minecraft.client.resources.SplashManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SplashManager.class})
public class MixinSplashManager {
    @Shadow
    private List<String> f_118862_;

    @Inject(method={"apply"}, at={@At(value="TAIL")})
    public void witherstormmod$addCustomSplashes$apply(List<String> splashes, ResourceManager manager, ProfilerFiller filler, CallbackInfo ci) {
        this.f_118862_.add("Waga Baga Bobo!");
        this.f_118862_.add("Also try Explorer's Eve!");
        this.f_118862_.add("Also try CWSM Plus!");
        this.f_118862_.add("Also try Command Tool Expansion!");
        this.f_118862_.add("Also try Bloxxify's Lightbringer!");
        this.f_118862_.add("It's symbiont, not 'symbiote'!");
        this.f_118862_.add("Useless tentacles included!");
        this.f_118862_.add("Elixir not included!");
        this.f_118862_.add("Slightly optimized Wither Storm included!");
        this.f_118862_.add("Shout out to Nazaru!");
        this.f_118862_.add("World destruction included!");
        this.f_118862_.add("Also try Story Mod!");
        this.f_118862_.add("Includes zombies doing flips!");
        this.f_118862_.add("Rest in peace Sickened Frog and Rabbit!");
        this.f_118862_.add("IT SHOULD HAVE BEEN ME!!");
    }
}

