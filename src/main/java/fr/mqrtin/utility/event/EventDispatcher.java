package fr.mqrtin.utility.event;

import fr.mqrtin.utility.Main;

import java.lang.reflect.Method;

/**
 * Dispatche les événements personnalisés aux modules
 */
public class EventDispatcher {

    /**
     * Dispatche un événement à tous les modules
     */
    public static void dispatchEvent(Event event) {
        Main.moduleManager.modules.forEach((moduleClass, module) -> {
            try {
                // Trouver les méthodes annotées avec @EventTarget
                Method[] methods = moduleClass.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(EventTarget.class)) {
                        // Vérifier que la méthode accepte un seul paramètre compatible avec l'événement
                        Class<?>[] paramTypes = method.getParameterTypes();
                        if (paramTypes.length == 1 && paramTypes[0].isAssignableFrom(event.getClass())) {
                            method.setAccessible(true);
                            method.invoke(module, event);
                        }
                    }
                }
            } catch (Exception e) {
                // Silencieusement ignorer les erreurs de dispatch
            }
        });
    }
}

