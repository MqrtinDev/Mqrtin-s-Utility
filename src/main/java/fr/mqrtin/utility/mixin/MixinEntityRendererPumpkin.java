package fr.mqrtin.utility.mixin;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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



