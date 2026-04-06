package fr.mqrtin.utility.module.modules.misc;

import fr.mqrtin.utility.event.EventTarget;
import fr.mqrtin.utility.event.events.Render2DEvent;
import fr.mqrtin.utility.module.impl.Module;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class HeadObfuscationOverlay extends Module {

    public HeadObfuscationOverlay() {
        super("Head Obfuscation Overlay");
        setEnabled(true);
    }

    @Override
    public void onDisable() {
        HideOverlay.setOverlayTransparent(false);
    }

    @EventTarget
    public void onRender2D(Render2DEvent event) {
        float partialTicks = event.getPartialTicks();

        if (partialTicks < 0.0F) {
            return;
        }

        if (!isEnabled()) {
            HideOverlay.setOverlayTransparent(false);
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;

        if (player == null || mc.theWorld == null) {
            HideOverlay.setOverlayTransparent(false);
            return;
        }

        // Vérifier si la tête du joueur est obfusquée (dans un bloc)
        boolean transparent = isHeadObfuscated(player);
        HideOverlay.setOverlayTransparent(transparent);

        if (transparent) {
            ScaledResolution resolution = new ScaledResolution(mc);
            Gui.drawRect(0, 0, resolution.getScaledWidth(), resolution.getScaledHeight(), 0x44000000);
        }
    }

    /**
     * Vérifie si la tête du joueur est obfusquée (dans un bloc solide ou du liquide)
     */
    private boolean isHeadObfuscated(EntityPlayerSP player) {
        Minecraft mc = Minecraft.getMinecraft();

        // Obtenir la position de la tête du joueur
        Vec3 eyePos = new Vec3(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        BlockPos blockPos = new BlockPos(eyePos.xCoord, eyePos.yCoord, eyePos.zCoord);

        // Vérifier le bloc où se trouve la tête
        Block block = mc.theWorld.getBlockState(blockPos).getBlock();

        // Vérifier si c'est un bloc solide ou du liquide
        return block != null && (block.getMaterial().blocksMovement() || block instanceof BlockLiquid);
    }

    /**
     * Classe interne pour gérer l'affichage/masquage de l'overlay
     */
    public static class HideOverlay {
        private static boolean overlayTransparent = false;

        public static void setOverlayTransparent(boolean transparent) {
            overlayTransparent = transparent;
        }

        public static boolean isOverlayTransparent() {
            return overlayTransparent;
        }
    }
}

