package fr.mqrtin.utility.event.handlers;

import fr.mqrtin.utility.Main;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ConfigSaveHandler {
    private static int tickCounter = 0;

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        // Sauvegarder toutes les 30 secondes (600 ticks)
        if (++tickCounter >= 600) {
            tickCounter = 0;
            if (Main.configManager != null) {
                Main.configManager.saveConfig();
            }
        }
    }
}

