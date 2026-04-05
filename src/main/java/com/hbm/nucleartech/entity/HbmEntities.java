package com.hbm.nucleartech.entity;
import com.hbm.nucleartech.entity.custom.MissileEntity;

import com.hbm.nucleartech.HBM;
import com.hbm.nucleartech.entity.custom.NuclearCreeperEntity;
import com.hbm.nucleartech.explosion.NuclearBombType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HbmEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HBM.MOD_ID);

    public static final RegistryObject<EntityType<NuclearCreeperEntity>> NUCLEAR_CREEPER =
            ENTITY_TYPES.register("nuclear_creeper", () -> EntityType.Builder.of(NuclearCreeperEntity::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("nuclear_creeper"));

    public static final RegistryObject<EntityType<MissileEntity>> MISSILE =
            ENTITY_TYPES.register("missile", () -> EntityType.Builder
                    .<MissileEntity>of(MissileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 2.0f)
                    .build("missile"));

    public static final RegistryObject<EntityType<MissileEntity>> MISSILE_TACTICAL =
            ENTITY_TYPES.register("missile_tactical", () -> EntityType.Builder
                    .<MissileEntity>of((type, level) -> new MissileEntity(type, level, NuclearBombType.TACTICAL), MobCategory.MISC)
                    .sized(0.5f, 2.0f).build("missile_tactical"));

    public static final RegistryObject<EntityType<MissileEntity>> MISSILE_STANDARD =
            ENTITY_TYPES.register("missile_standard", () -> EntityType.Builder
                    .<MissileEntity>of((type, level) -> new MissileEntity(type, level, NuclearBombType.STANDARD), MobCategory.MISC)
                    .sized(0.5f, 2.0f).build("missile_standard"));

    public static final RegistryObject<EntityType<MissileEntity>> MISSILE_THERMONUCLEAR =
            ENTITY_TYPES.register("missile_thermonuclear", () -> EntityType.Builder
                    .<MissileEntity>of((type, level) -> new MissileEntity(type, level, NuclearBombType.THERMONUCLEAR), MobCategory.MISC)
                    .sized(0.5f, 2.0f).build("missile_thermonuclear"));

    public static final RegistryObject<EntityType<MissileEntity>> MISSILE_BUNKER_BUSTER =
            ENTITY_TYPES.register("missile_bunker_buster", () -> EntityType.Builder
                    .<MissileEntity>of((type, level) -> new MissileEntity(type, level, NuclearBombType.BUNKER_BUSTER), MobCategory.MISC)
                    .sized(0.5f, 2.0f).build("missile_bunker_buster"));

    public static final RegistryObject<EntityType<MissileEntity>> MISSILE_COBALT =
            ENTITY_TYPES.register("missile_cobalt", () -> EntityType.Builder
                    .<MissileEntity>of((type, level) -> new MissileEntity(type, level, NuclearBombType.COBALT), MobCategory.MISC)
                    .sized(0.5f, 2.0f).build("missile_cobalt"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
