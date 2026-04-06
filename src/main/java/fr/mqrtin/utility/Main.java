package fr.mqrtin.utility;

import fr.mqrtin.utility.event.handlers.RenderEventHandler;
import fr.mqrtin.utility.module.ModuleManager;
import fr.mqrtin.utility.module.modules.hidden.CPSCounter;
import fr.mqrtin.utility.module.modules.misc.HeadObfuscationOverlay;
import fr.mqrtin.utility.module.modules.misc.LabelModule;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = "mqrtinsutility", name = "Mqrtin's Utility", version = "1.0.0")
public class Main {
    public static KeyBinding openGuiKeybind = new KeyBinding("Open GUI", Keyboard.KEY_RCONTROL, "Mqrtin's Utility");

    public static Main instance;
    public static ModuleManager moduleManager;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("Mqrtin's Utility init");
        moduleManager = new ModuleManager();
        moduleManager.register(new LabelModule(), new CPSCounter(), new HeadObfuscationOverlay());
        instance = this;
        ClientRegistry.registerKeyBinding(openGuiKeybind);

        // Enregistrer le handler d'événements Forge pour le rendu
        MinecraftForge.EVENT_BUS.register(new RenderEventHandler());
    }
}
