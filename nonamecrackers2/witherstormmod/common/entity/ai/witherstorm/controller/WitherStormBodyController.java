/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.control.BodyRotationControl
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.fml.DistExecutor
 *  net.minecraftforge.network.NetworkEvent$Context
 *  net.minecraftforge.network.PacketDistributor
 *  nonamecrackers2.crackerslib.common.packet.Packet
 */
package nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.controller;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.crackerslib.common.packet.Packet;
import nonamecrackers2.witherstormmod.client.init.WitherStormModClientCapabilities;
import nonamecrackers2.witherstormmod.common.config.WitherStormModConfig;
import nonamecrackers2.witherstormmod.common.entity.FormidibombEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.PlayDeadManager;
import nonamecrackers2.witherstormmod.common.entity.ai.witherstorm.head.AdditionalHead;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;

public class WitherStormBodyController
extends BodyRotationControl {
    protected final WitherStormEntity storm;
    protected float maxRotSpeed;
    protected boolean hasWanted;
    protected double wantedX;
    protected double wantedZ;
    protected int headStableTime;
    protected float lastStableYHeadRot;

    public WitherStormBodyController(WitherStormEntity storm) {
        super((Mob)storm);
        this.storm = storm;
    }

    public void m_8121_() {
        if (!this.storm.m_9236_().f_46443_) {
            if (!this.storm.m_21525_() && !this.storm.isDeadOrPlayingDead()) {
                boolean ultimateTargetInUseBySegment = this.storm.getUltimateTarget() == null ? false : this.storm.targetInUseBySegment((Entity)this.storm.getUltimateTarget());
                boolean ultimateTargetInUseByHeads = this.storm.getUltimateTarget() == null ? false : this.storm.alreadyATarget((Entity)this.storm.getUltimateTarget(), false);
                FormidibombEntity formidibomb = this.storm.getFormidibomb();
                boolean formidibombNearby = this.storm.isNearbyTickingFormidibomb();
                if (this.storm.getPhase() > 3) {
                    if (!ultimateTargetInUseBySegment && !ultimateTargetInUseByHeads && !formidibombNearby && this.storm.shouldRotateTowardsUltimateTarget()) {
                        Vec3 pos = this.storm.getUltimateTargetPos();
                        if (pos != null) {
                            this.setBodyAt(pos.m_7096_(), pos.m_7094_(), ((Double)WitherStormModConfig.SERVER.rotationSpeed.get()).floatValue());
                        }
                        if (this.hasWanted) {
                            this.storm.f_20883_ = this.rotateTowards(this.storm.f_20883_, this.getYRotD(), this.maxRotSpeed);
                            this.m_24881_();
                        }
                    } else if (formidibombNearby) {
                        this.setBodyAt(formidibomb.m_20185_(), formidibomb.m_20189_(), 0.1f);
                        if (this.hasWanted) {
                            this.storm.f_20883_ = this.rotateTowards(this.storm.f_20883_, this.getYRotD(), this.maxRotSpeed);
                            this.m_24881_();
                        }
                    }
                } else {
                    boolean flag = true;
                    for (AdditionalHead head : this.storm.getHeadManager().getOtherHeads()) {
                        if (head.getTarget() == null) continue;
                        flag = false;
                        break;
                    }
                    if (flag && this.storm.m_5448_() == null && this.m_24884_()) {
                        Vec3 pos = this.storm.getUltimateTargetPos();
                        if (pos != null) {
                            this.setBodyAt(pos.m_7096_(), pos.m_7094_(), 5.0f);
                        }
                        if (this.hasWanted) {
                            this.storm.f_20883_ = this.rotateTowards(this.storm.f_20883_, this.getYRotD(), this.maxRotSpeed);
                            this.m_24881_();
                        }
                    } else {
                        super.m_8121_();
                    }
                }
            }
            if (this.storm.canFallOnBack()) {
                if (this.storm.getPlayDeadManager().getState() == PlayDeadManager.State.PLAYING_DEAD) {
                    if (this.storm.xBodyRot < 90.0f) {
                        this.storm.xBodyRot += this.storm.xBodyRot * 0.04f + 0.05f;
                    }
                    if (this.storm.xBodyRot > 90.0f) {
                        this.storm.xBodyRot = 90.0f;
                        this.storm.onFallOnBack();
                    }
                } else if (!this.storm.isPlayingDead()) {
                    WitherStormEntity ultimateTargetInUseBySegment = this.storm;
                    ultimateTargetInUseBySegment.xBodyRot = ultimateTargetInUseBySegment.xBodyRot + (-this.storm.xBodyRot * 0.015f - 0.02f);
                    if (this.storm.xBodyRot < 0.0f) {
                        this.storm.xBodyRot = 0.0f;
                    }
                }
            }
            UpdateBodyRotMessage message = new UpdateBodyRotMessage(this.storm);
            ResourceKey dimension = this.storm.m_9236_().m_46472_();
            WitherStormModPacketHandlers.MAIN.send(PacketDistributor.DIMENSION.with(() -> dimension), (Object)message);
        }
    }

    private void setBodyAt(double x, double z, float speed) {
        this.wantedX = x;
        this.wantedZ = z;
        this.maxRotSpeed = speed;
        this.hasWanted = true;
    }

    protected void m_24881_() {
        this.storm.f_20885_ = Mth.m_14094_((float)this.storm.f_20885_, (float)this.storm.f_20883_, (float)this.storm.m_8085_());
    }

    protected float rotateTowards(float yRot, float newYRot, float speed) {
        float f = Mth.m_14118_((float)yRot, (float)newYRot);
        float f1 = Mth.m_14036_((float)f, (float)(-speed), (float)speed);
        return yRot + f1;
    }

    protected float getYRotD() {
        double d0 = this.wantedX - this.storm.m_20185_();
        double d1 = this.wantedZ - this.storm.m_20189_();
        return (float)(Mth.m_14136_((double)d1, (double)d0) * 57.2957763671875) - 90.0f;
    }

    protected boolean m_24884_() {
        double d1;
        double d0 = this.storm.m_20185_() - this.storm.f_19854_;
        return d0 * d0 + (d1 = this.storm.m_20189_() - this.storm.f_19856_) * d1 > 2.500000277905201E-7;
    }

    public static class UpdateBodyRotMessage
    extends Packet {
        private float yBodyRot;
        private float xBodyRot;
        private int entityId;

        private UpdateBodyRotMessage(WitherStormEntity entity) {
            super(true);
            this.yBodyRot = entity.f_20883_;
            this.xBodyRot = entity.xBodyRot;
            this.entityId = entity.m_19879_();
        }

        public UpdateBodyRotMessage() {
            super(false);
        }

        public void decode(FriendlyByteBuf buffer) {
            this.yBodyRot = buffer.readFloat();
            this.xBodyRot = buffer.readFloat();
            this.entityId = buffer.m_130242_();
        }

        public void encode(FriendlyByteBuf buffer) {
            buffer.writeFloat(this.yBodyRot);
            buffer.writeFloat(this.xBodyRot);
            buffer.m_130130_(this.entityId);
        }

        public Runnable getProcessor(NetworkEvent.Context context) {
            return () -> DistExecutor.unsafeRunWhenOn((Dist)Dist.CLIENT, () -> () -> UpdateBodyRotMessage.processMessage(this));
        }

        public String toString() {
            return "UpdateBodyRotMessage[yBodyRot=" + this.yBodyRot + ", xBodyRot=" + this.xBodyRot + "]";
        }

        public static void processMessage(UpdateBodyRotMessage message) {
            Minecraft mc = Minecraft.m_91087_();
            ClientLevel world = mc.f_91073_;
            WitherStormEntity entity = (WitherStormEntity)mc.f_91073_.m_6815_(message.entityId);
            if (entity != null) {
                entity.lerpBodyRotationTo(message.xBodyRot, message.yBodyRot, 3);
            }
            world.getCapability(WitherStormModClientCapabilities.DISTANT_RENDERER).ifPresent(distantRenderer -> {
                WitherStormEntity distantEntity = distantRenderer.get(message.entityId);
                if (distantEntity != null) {
                    distantEntity.lerpBodyRotationTo(message.xBodyRot, message.yBodyRot, 3);
                }
            });
        }
    }
}

