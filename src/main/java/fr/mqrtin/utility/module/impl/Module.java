package fr.mqrtin.utility.module.impl;

import fr.mqrtin.utility.Main;
import fr.mqrtin.utility.module.ModuleCategory;
import fr.mqrtin.utility.module.ModuleManager;
import fr.mqrtin.utility.module.property.Property;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public abstract class Module {

    private boolean enabled;

    private KeyBinding keybind;

    private final String moduleName;
    private final ModuleCategory category;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled) {
            return;
        }
        this.enabled = enabled;

        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public final String getModuleName() {
        return moduleName;
    }

    public ModuleCategory getCategory() {
        return category;
    }

    public final KeyBinding getKeybind() {
        return keybind;
    }

    public final void setKeybind(KeyBinding keybind) {
        this.keybind = keybind;
    }

    public Module(String moduleName, ModuleCategory category, boolean hasKeybind){
        this.moduleName = moduleName;
        this.category = category;
        if (hasKeybind) {
            this.keybind = new KeyBinding("Module : " + getModuleName(), 0, "Mqrtin's Utility");
            ClientRegistry.registerKeyBinding(getKeybind());
        }
    }

    public Module(String moduleName, boolean hasKeybind){
        this(moduleName, ModuleCategory.OTHER, hasKeybind);
    }


    public boolean isHidden(){
        return false;
    }

    public boolean toggle(){
        setEnabled(!enabled);
        return enabled;
    }

    public void onEnable(){

    }

    public void onDisable(){

    }

    public void onPropertyChange(Property property){}

    public void verifyValue(String name) {
    }

    public static <T extends Module> T getInstance(Class<T> clazz) {
        return (T) Main.moduleManager.modules.get(clazz);
    }
}
