package com.hbm.nucleartech.handler;

import com.hbm.nucleartech.HBM;
import com.hbm.nucleartech.capability.HbmCapabilities;
import com.hbm.nucleartech.damagesource.RegisterDamageSources;
import com.hbm.nucleartech.interfaces.IEntityCapabilityBase.Type;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HBM.MOD_ID)
public class AsbestosSystemNT {

    private static final float DAMAGE_THRESHOLD = 100F;
    private static final float DAMAGE_MULTIPLIER = 0.001F;

    private static final double MAX_ASBESTOS = 1000F;

    private static final float BLINDING_THRESHOLD = 50F;
    private static final float NOUSE_THRESHOLD = 75F;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.player.level().isClientSide()) return;

        ServerPlayer player = (ServerPlayer) event.player;

        double asb = HbmCapabilities.getData(player).getValue(Type.ASBESTOS);
        System.out.println("[Debug] Player " + player.getName().getString() + " has asbestos level: " + asb);

        // лимитируем максимум
        if(asb > MAX_ASBESTOS) asb = MAX_ASBESTOS;

        if(asb > 0 && !player.isCreative() && !player.isSpectator()) {

            // эффекты
            if(player.tickCount % 20 == 0){
                if(asb > BLINDING_THRESHOLD)
                    player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0));
                if(asb > NOUSE_THRESHOLD)
                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0));
                if(asb > DAMAGE_THRESHOLD){
                    double damage = (asb - DAMAGE_THRESHOLD) * DAMAGE_MULTIPLIER;
                    player.hurt(RegisterDamageSources.ASBESTOS, (float)damage);
                }
            }


        }
    }

    // удобный метод для добавления асбеста игроку
    public static void addAsbestosToPlayer(ServerPlayer player, float amount) {
        float current = HbmCapabilities.getData(player).getValue(Type.ASBESTOS);
        HbmCapabilities.getData(player).setValue(Type.ASBESTOS, current + amount);
    }
}