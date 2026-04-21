package fr.mqrtin.utility.module.modules.QOL;

import fr.mqrtin.utility.event.EventTarget;
import fr.mqrtin.utility.event.events.Render3DEvent;
import fr.mqrtin.utility.module.ModuleCategory;
import fr.mqrtin.utility.module.impl.Module;
import fr.mqrtin.utility.module.property.properties.IntProperty;
import fr.mqrtin.utility.module.property.properties.PercentProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class Waypoint extends Module {

    private final List<NametagData> waypoints;

    private final IntProperty waypointPaddingSize = new IntProperty("Padding Size", 5, 0, 50);
    private final IntProperty waypointMaxDistance = new IntProperty("Max Distance", 500, -1, 5000);
    private final PercentProperty waypointScale = new PercentProperty("Distance Scale", 100, 0, 500, () -> true);

    public Waypoint() {
        super("Waypoint", ModuleCategory.QOL, false);
        this.waypoints = new ArrayList<>();
        waypoints.add(new NametagData("0", Color.GREEN, 0, 75, 0));
    }

    @EventTarget
    public void onRender3D(Render3DEvent event){
        if(!isEnabled())
            return;
        if(Minecraft.getMinecraft().theWorld == null || Minecraft.getMinecraft().thePlayer == null) return;


        float partialTicks = event.getPartialTicks();
        if (partialTicks < 0.0F) {
            return;
        }

        waypoints.forEach( nametagData -> {
            renderNameTag(nametagData);
        });
    }

    private void renderNameTag(NametagData data) {
        Minecraft mc = Minecraft.getMinecraft();

        double x = data.getX() - mc.getRenderManager().viewerPosX;
        double y = data.getY() - mc.getRenderManager().viewerPosY;
        double z = data.getZ() - mc.getRenderManager().viewerPosZ;

        double distance = mc.thePlayer.getDistance(data.getX(), data.getY(), data.getZ());

        if (waypointMaxDistance.getValue() != -1 && distance > waypointMaxDistance.getValue()) {
            return;
        }

        GlStateManager.pushMatrix();

        GlStateManager.translate(x, y + 0.5, z);
        GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0F, 1F, 0F);
        GlStateManager.rotate(mc.getRenderManager().playerViewX, 1F, 0F, 0F);

        float scale = (float) (distance * 0.01f * waypointScale.getValue() / 100);
        scale = Math.max(scale, 0.02f);

        GlStateManager.scale(-scale, -scale, 1);

        String text = "§f" + data.getText() + " §7(" + (int) distance + "m)";
        int width = mc.fontRendererObj.getStringWidth(text) / 2;

        int padding = waypointPaddingSize.getValue();

        int left = -width - padding;
        int right = width + padding;
        int top = -mc.fontRendererObj.FONT_HEIGHT - padding;
        int bottom = padding;

        int bgColor = 0x55000000;
        int outlineColor = data.getColor() != null ? data.getColor().getRGB() : 0xFFFFFFFF;

        GlStateManager.disableDepth();

        // Background
        Gui.drawRect(left, top, right, bottom, bgColor);

        int thickness = 1;

        // Outline
        Gui.drawRect(left, top, right, top + thickness, outlineColor);           // Top
        Gui.drawRect(left, bottom - thickness, right, bottom, outlineColor);     // Bottom
        Gui.drawRect(left, top, left + thickness, bottom, outlineColor);         // Left
        Gui.drawRect(right - thickness, top, right, bottom, outlineColor);       // Right

        // Texte
        mc.fontRendererObj.drawString(
                text,
                -width,
                -mc.fontRendererObj.FONT_HEIGHT,
                0xFFFFFF,
                true
        );

        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }


    public static final class NametagData {
        /** Le texte affiché par le nametag */
        private final String text;

        /** La couleur du texte (peut être null pour la couleur par défaut) */
        private final Color color;

        /** Coordonnée X de la position du nametag dans le monde */
        private final double x;

        /** Coordonnée Y de la position du nametag dans le monde */
        private final double y;

        /** Coordonnée Z de la position du nametag dans le monde */
        private final double z;

        /**
         * Crée une nouvelle instance de NametagData avec un texte, une couleur et une position.
         *
         * @param text Le texte à afficher (ne doit pas être null)
         * @param color La couleur du texte en RGB (peut être null pour la couleur par défaut blanche)
         * @param x La coordonnée X dans le monde
         * @param y La coordonnée Y dans le monde
         * @param z La coordonnée Z dans le monde
         */
        public NametagData(String text, Color color, double x, double y, double z) {
            this.text = text;
            this.color = color;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        /**
         * Retourne le texte du nametag.
         *
         * @return le texte à afficher
         */
        public String getText() {
            return text;
        }

        /**
         * Retourne la couleur du nametag.
         *
         * @return la couleur RGB du texte, ou null pour la couleur par défaut
         */
        public Color getColor() {
            return color;
        }

        /**
         * Retourne la coordonnée X du nametag.
         *
         * @return la coordonnée X dans le monde
         */
        public double getX() {
            return x;
        }

        /**
         * Retourne la coordonnée Y du nametag.
         *
         * @return la coordonnée Y dans le monde
         */
        public double getY() {
            return y;
        }

        /**
         * Retourne la coordonnée Z du nametag.
         *
         * @return la coordonnée Z dans le monde
         */
        public double getZ() {
            return z;
        }
    }
}
