package com.hbm.nucleartech.block.menu;

import com.hbm.nucleartech.HBM;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegisterMenus {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, HBM.MOD_ID);

    public static final RegistryObject<MenuType<MissileLauncherMenu>> MISSILE_LAUNCHER =
            MENUS.register("missile_launcher",
                    () -> IForgeMenuType.create(MissileLauncherMenu::new));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
