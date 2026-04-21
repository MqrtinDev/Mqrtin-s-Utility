package fr.mqrtin.utility.event.handlers;

import fr.mqrtin.utility.Main;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEventHandler {

    /**
     * Intercepte le rendu du bloc qui recouvre la vue quand la tête est dans un bloc
     */
    @SubscribeEvent
    public void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
        if (Main.moduleManager == null) {
            return;
        }

        /*HeadObfuscationOverlay module = (HeadObfuscationOverlay) Main.moduleManager.modules.get(HeadObfuscationOverlay.class);
        if (event.overlayType != RenderBlockOverlayEvent.OverlayType.BLOCK) {
            return;
        }

        if (module != null && module.isEnabled() && HeadObfuscationOverlay.HideOverlay.isOverlayTransparent()) {
            // Annuler le rendu du bloc
            event.setCanceled(true);
        }*/
    }
}


