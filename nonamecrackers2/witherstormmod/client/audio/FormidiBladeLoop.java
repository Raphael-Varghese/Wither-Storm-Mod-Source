package nonamecrackers2.witherstormmod.client.audio;

import java.util.Optional;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import nonamecrackers2.witherstormmod.client.audio.FadingSoundLoop;
import nonamecrackers2.witherstormmod.client.audio.IForceStoppableSound;
import nonamecrackers2.witherstormmod.common.item.FormidiBladeItem;

public class FormidiBladeLoop
extends FadingSoundLoop
implements IForceStoppableSound {
    public static final float MIN_POWER = 0.05f;
    public final AbstractClientPlayer player;

    public FormidiBladeLoop(AbstractClientPlayer player, SoundEvent event) {
        super(event, SoundSource.AMBIENT);
        this.player = player;
    }

    @Override
    public void m_7788_() {
        super.m_7788_();
        Vec3 view = this.player.m_20252_(1.0f).m_82490_(0.2);
        this.f_119575_ = this.player.m_20185_() + view.f_82479_;
        this.f_119576_ = this.player.m_20188_() + view.f_82480_;
        this.f_119577_ = this.player.m_20189_() + view.f_82481_;
        boolean flag = false;
        Optional<Float> power = FormidiBladeLoop.getPower(this.player);
        if (FormidiBladeLoop.canPlay(power)) {
            flag = true;
            float powerf = power.get().floatValue();
            this.dampen = (1.0f - powerf) * 50.0f;
            this.f_119574_ = 0.5f + powerf / 2.0f;
        }
        if (!this.player.m_6084_() || !flag) {
            this.stopSound();
        }
    }

    public static Optional<Float> getPower(AbstractClientPlayer player) {
        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = player.m_21120_(hand);
            if (!(stack.m_41720_() instanceof FormidiBladeItem)) continue;
            float power = FormidiBladeItem.getPower((LivingEntity)player, stack, false);
            return Optional.of(Float.valueOf(power));
        }
        return Optional.empty();
    }

    public static boolean canPlay(Optional<Float> power) {
        return power.map(p -> p.floatValue() > 0.05f).orElse(false);
    }

    public static boolean canPlay(AbstractClientPlayer player) {
        return FormidiBladeLoop.canPlay(FormidiBladeLoop.getPower(player));
    }

    @Override
    public void forceStop() {
        this.m_119609_();
    }

    @Override
    protected int getFadeTime() {
        return 20;
    }
}

