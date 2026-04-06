package fr.mqrtin.utility.mixin;

import fr.mqrtin.utility.module.modules.misc.HeadObfuscationOverlay;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;

@SideOnly(Side.CLIENT)
@Mixin(value = {GuiIngameForge.class}, priority = 9998)
public abstract class MixinGuiIngame {
    /**
     * Ce mixin est un placeholder pour la détection de HEAD obfusquée.
     * La logique réelle est gérée par HeadObfuscationOverlay et RenderEventHandler.
     */
}

