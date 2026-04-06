package fr.mqrtin.utility.mixin;

import fr.mqrtin.utility.Main;
import fr.mqrtin.utility.module.modules.misc.HeadObfuscationOverlay;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;

@SideOnly(Side.CLIENT)
@Mixin(EntityRenderer.class)
public abstract class MixinEntityRendererPumpkin {

    /**
     * Ce mixin ne contient pas d'injections directes.
     * Le module HeadObfuscationOverlay gère la détection de la tête obfusquée
     * et la rend transparente via le système d'événements Forge.
     */
}



