/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.FishingHook
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.entity.projectile.ThrownPotion
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraft.world.item.alchemy.PotionUtils
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  net.minecraft.world.level.levelgen.structure.BoundingBox
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraftforge.event.ItemAttributeModifierEvent
 *  net.minecraftforge.event.TickEvent$LevelTickEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.event.entity.ProjectileImpactEvent
 *  net.minecraftforge.event.entity.ProjectileImpactEvent$ImpactResult
 *  net.minecraftforge.event.entity.player.AttackEntityEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.network.PacketDistributor
 */
package nonamecrackers2.witherstormmod.common.event;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;
import nonamecrackers2.witherstormmod.common.capability.WitherStormAutoSpawner;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.entity.WitherStormEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModCapabilities;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.common.init.WitherStormModPacketHandlers;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import nonamecrackers2.witherstormmod.common.item.EyeOfTheStormItem;
import nonamecrackers2.witherstormmod.common.packet.ShakeScreenMessage;
import nonamecrackers2.witherstormmod.common.world.tainting.WorldTainting;

public class WitherStormModUtilEvents {
    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        Projectile hit2;
        if (event.getProjectile() instanceof FishingHook) {
            EntityHitResult hit2;
            HitResult hitResult = event.getRayTraceResult();
            if (hitResult instanceof EntityHitResult && ((hit2 = (EntityHitResult)hitResult).m_82443_() instanceof CommandBlockEntity || hit2.m_82443_() instanceof WitherStormEntity)) {
                event.setImpactResult(ProjectileImpactEvent.ImpactResult.SKIP_ENTITY);
            }
        } else if (!event.getProjectile().m_9236_().m_5776_() && (hit2 = event.getProjectile()) instanceof ThrownPotion) {
            ThrownPotion projectile = (ThrownPotion)hit2;
            hit2 = event.getRayTraceResult();
            if (hit2 instanceof BlockHitResult) {
                BlockHitResult hit3 = (BlockHitResult)hit2;
                Potion potion = PotionUtils.m_43579_((ItemStack)projectile.m_7846_());
                BoundingBox box = new BoundingBox(hit3.m_82425_()).m_191961_(1);
                WorldTainting.getInstance().convertBlocks(box, projectile.m_9236_(), potion);
            }
        }
    }

    @SubscribeEvent
    public static void modifyItemAttributes(ItemAttributeModifierEvent event) {
        CompoundTag tag;
        float ratio;
        ItemStack stack;
        if (event.getSlotType() == EquipmentSlot.MAINHAND && (stack = event.getItemStack()).m_150930_((Item)WitherStormModItems.EYE_OF_THE_STORM.get()) && stack.m_41782_() && (ratio = (tag = stack.m_41783_()).m_128457_("EntityHealthRatio")) > 0.0f) {
            event.addModifier(Attributes.f_22281_, new AttributeModifier(EyeOfTheStormItem.DAMAGE_MODIFIER_ID, "Health damage modifier", (double)(-ratio * 5.0f), AttributeModifier.Operation.ADDITION));
        }
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            event.level.getCapability(WitherStormModCapabilities.WITHER_STORM_AUTO_SPAWNER).ifPresent(WitherStormAutoSpawner::tick);
        }
    }

    @SubscribeEvent
    public static void onPlayerAttack(AttackEntityEvent event) {
        ItemStack stack;
        Player player = event.getEntity();
        if (!player.m_9236_().m_5776_() && (stack = player.m_21205_()).m_150930_((Item)WitherStormModItems.FORMIDI_BLADE.get()) && !player.m_36335_().m_41519_(stack.m_41720_())) {
            Entity hit = event.getTarget();
            CompoundTag tag = stack.m_41784_();
            float power = Math.min(1.0f, tag.m_128457_("Power"));
            if (power > 0.0f) {
                tag.m_128379_("IsCharged", false);
                tag.m_128350_("Power", 2.0f);
                player.m_9236_().m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), (SoundEvent)WitherStormModSoundEvents.FORMIDI_BLADE_DECHARGE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
                player.m_36335_().m_41524_(stack.m_41720_(), 100);
                player.m_9236_().m_254849_((Entity)player, hit.m_20185_(), hit.m_20186_(), hit.m_20189_(), 4.0f * power, Level.ExplosionInteraction.NONE);
                for (ServerPlayer nearby : player.m_9236_().m_45976_(ServerPlayer.class, player.m_20191_().m_82400_(32.0))) {
                    WitherStormModPacketHandlers.MAIN.send(PacketDistributor.PLAYER.with(() -> nearby), (Object)new ShakeScreenMessage(40.0f, 2.5f));
                }
            }
        }
    }
}

