package fr.mqrtin.utility.event.handlers;

import fr.mqrtin.utility.Main;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ConfigSaveHandler {
    private static int clientTickCounter = 0;
    private static int serverTickCounter = 0;
    private static final int SAVE_INTERVAL = 600; // 30 secondes (20 ticks/sec * 30 = 600 ticks)

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        // Sauvegarder toutes les 30 secondes (600 ticks)
        if (event.phase == TickEvent.Phase.START) {
            if (++clientTickCounter >= SAVE_INTERVAL) {
                clientTickCounter = 0;
                if (Main.configManager != null) {
                    Main.configManager.saveConfig();
                }
            }
        }
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        // Sauvegarder toutes les 30 secondes sur le serveur aussi
        if (event.phase == TickEvent.Phase.START) {
            if (++serverTickCounter >= SAVE_INTERVAL) {
                serverTickCounter = 0;
                if (Main.configManager != null) {
                    Main.configManager.saveConfig();
                }
            }
        }
    }
}

