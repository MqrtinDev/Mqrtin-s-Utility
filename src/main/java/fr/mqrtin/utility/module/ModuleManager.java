package fr.mqrtin.utility.module;

import fr.mqrtin.utility.module.impl.Module;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

public class ModuleManager {

    public final Map<Class<?>, Module> modules;
    public final Map<Class<?>, KeyBinding> keybinds;


    public ModuleManager() {
        modules = new HashMap<>();
        keybinds = new HashMap<>();
    }

    public void register(Module... modules){
        for (Module module : modules) {
            this.modules.put(module.getClass(), module);
            this.keybinds.put(module.getClass(), module.getKeybind());
        }
    }
    public void register(Class<? extends Module>... classes){
        for (Class<? extends Module> aClass : classes) {
            try {
                Module module = aClass.newInstance();
                this.modules.put(aClass, module);
                this.keybinds.put(aClass, module.getKeybind());
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

