package fr.mqrtin.utility;

import fr.mqrtin.utility.event.handlers.ConfigSaveHandler;
import fr.mqrtin.utility.event.handlers.RenderEventHandler;
import fr.mqrtin.utility.handler.ApolloPacketHandler;
import fr.mqrtin.utility.handler.KeybindHandler;
import fr.mqrtin.utility.manager.config.ConfigManager;
import fr.mqrtin.utility.module.ModuleManager;
import fr.mqrtin.utility.module.modules.hidden.CPSCounter;
import fr.mqrtin.utility.module.modules.HUD.LabelModule;
import fr.mqrtin.utility.module.modules.QOL.NoClickDelay;
import fr.mqrtin.utility.module.modules.QOL.FullBright;
import fr.mqrtin.utility.module.modules.QOL.Waypoint;
import fr.mqrtin.utility.module.modules.other.DebugModule;
import fr.mqrtin.utility.module.modules.settings.TabOverlay;
import fr.mqrtin.utility.module.property.PropertyManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "mqrtinsutility", name = "Mqrtin's Utility", version = "1.0.0")
public class Main {
    public static Main instance;
    public static ModuleManager moduleManager;
    public static PropertyManager propertyManager;
    public static ConfigManager configManager;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("Mqrtin's Utility init");
        moduleManager = new ModuleManager();

        // Enregistrer les modules
        moduleManager.register(LabelModule.class, CPSCounter.class, NoClickDelay.class, FullBright.class, TabOverlay.class, Waypoint.class, DebugModule.class);

        // Créer le PropertyManager et enregistrer les propriétés
        propertyManager = new PropertyManager();
        moduleManager.modules.values().forEach(propertyManager::registerModule);

        // Créer le ConfigManager et charger la config
        configManager = new ConfigManager(propertyManager);
        configManager.loadConfig();

        instance = this;

        // Enregistrer le handler d'événements Forge pour le rendu
        MinecraftForge.EVENT_BUS.register(new RenderEventHandler());
        MinecraftForge.EVENT_BUS.register(new KeybindHandler());
        MinecraftForge.EVENT_BUS.register(new ApolloPacketHandler());
        MinecraftForge.EVENT_BUS.register(new ConfigSaveHandler());
    }
}
