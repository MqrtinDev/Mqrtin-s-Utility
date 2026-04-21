package fr.mqrtin.utility.utils;

import java.awt.Color;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

/**
 * Utilitaire de rendu pour afficher des éléments visuels personnalisés dans le monde Minecraft.
 * 
 * Fournit des méthodes pour rendre:
 * <ul>
 *     <li>Des nametags (étiquettes de nom) avec couleurs et positions 3D personnalisées</li>
 *     <li>Des waypoints (points de repère) pour marquer des emplacements importants</li>
 * </ul>
 * 
 * <p>Les nametags support deux modes d'échelle:
 * <ul>
 *     <li>Mode relatif: l'échelle varie selon la distance du joueur</li>
 *     <li>Mode fixe: l'échelle reste constante</li>
 * </ul>
 * </p>
 * 
 * @author Mqrtin
 * @version 1.0
 * @since 1.8.9
 */
public class RenderUtil {

    /**
     * Classe contenant les données nécessaires pour rendre un nametag.
     * 
     * <p>Un nametag est une étiquette de texte affichée dans le monde 3D avec:
     * <ul>
     *     <li>Un texte personnalisé</li>
     *     <li>Une couleur</li>
     *     <li>Une position dans l'espace 3D (x, y, z)</li>
     * </ul>
     * </p>
     */
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

    /**
     * Affiche un waypoint (point de repère) avec une taille fixe à l'écran.
     *
     * <p>Un waypoint est un marqueur visuel utilisé pour indiquer des emplacements importants
     * dans le monde Minecraft, comme des bases, des trésors ou des points d'intérêt.
     * Le waypoint est projeté en 2D à l'écran et reste à la même taille peu importe la distance.
     * </p>
     *
     * <p><b>Dimensions:</b>
     * <ul>
     *     <li>Hauteur du rectangle: hauteur du texte + paddingSize</li>
     *     <li>Largeur du rectangle: largeur du texte + paddingSize</li>
     *     <li>Bordure (outline): couleur du waypoint</li>
     *     <li>Affichage: centré à la position projetée en 2D à l'écran</li>
     * </ul>
     * </p>
     *
     * @param nametagData Les données du waypoint (texte, couleur, position en 3D à projeter)
     * @param textSize La taille fixe du texte en pixels (ex: 10, 12, 16)
     * @param paddingSize L'espacement intérieur du rectangle en pixels
     * @param maxDistance La distance maximale en blocs pour afficher le waypoint.
     *                    Si la distance est supérieure, le waypoint n'est pas affiché.
     *
     * @example <code>
     * NametagData waypoint = new NametagData("Ma base", new Color(0, 255, 0), 100, 64, 200);
     * RenderUtil.renderWaypoint(waypoint, 12, 10, 500);
     * // Affiche "Ma base" en vert avec un rectangle de bordure verte, taille fixe peu importe la distance
     * </code>
     */
    public static void renderWaypoint(NametagData nametagData, int textSize, int paddingSize, int maxDistance) {
        if (nametagData == null || nametagData.getText() == null) return;

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.theWorld == null || mc.thePlayer == null) return;

        FontRenderer fontrenderer = mc.fontRendererObj;
        RenderManager renderManager = mc.getRenderManager();

        double dx = nametagData.getX() - renderManager.viewerPosX;
        double dy = nametagData.getY() - renderManager.viewerPosY;
        double dz = nametagData.getZ() - renderManager.viewerPosZ;
        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (maxDistance != -1 && distance > maxDistance) return;

        // Buffers directs correctement alloués
        IntBuffer viewport = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
        FloatBuffer modelview = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        FloatBuffer projection = ByteBuffer.allocateDirect(16 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        FloatBuffer screenCoords = ByteBuffer.allocateDirect(4 * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();

        GL11.glGetInteger(GL11.GL_VIEWPORT, viewport);
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, modelview);
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, projection);

        viewport.rewind();
        modelview.rewind();
        projection.rewind();

        boolean success = GLU.gluProject(
                (float) dx, (float) dy, (float) dz,
                modelview, projection, viewport, screenCoords
        );

        if (!success) return;

        screenCoords.rewind();
        float projX = screenCoords.get();
        float projY = screenCoords.get();
        float sz = screenCoords.get();
        if (sz < 0.0F) return;

        // Conversion en coordonnées ScaledResolution
        ScaledResolution sr = new ScaledResolution(mc);
        int scaleFactor = sr.getScaleFactor();

        viewport.rewind();
        int vpHeight = viewport.get(3);

        float screenX = projX / scaleFactor;
        float screenY = (vpHeight - projY) / scaleFactor;

        String displayText = nametagData.getText();
        int textWidth = fontrenderer.getStringWidth(displayText);
        int fontHeight = 8;
        int rectWidth  = textWidth + paddingSize;
        int rectHeight = fontHeight + paddingSize;

        int borderColor = nametagData.getColor() == null ? 0xFFFFFFFF : nametagData.getColor().getRGB();
        float r = ((borderColor >> 16) & 0xFF) / 255.0F;
        float g = ((borderColor >> 8)  & 0xFF) / 255.0F;
        float b = ((borderColor)       & 0xFF) / 255.0F;
        float a = ((borderColor >> 24) & 0xFF) / 255.0F;
        if (a == 0) a = 1.0F;

        int left   = (int)(screenX - rectWidth  / 2f);
        int right  = (int)(screenX + rectWidth  / 2f);
        int top    = (int)(screenY - rectHeight / 2f);
        int bottom = (int)(screenY + rectHeight / 2f);

        // Rendu en coordonnées GUI (ScaledResolution)
        GlStateManager.pushMatrix();
        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        GL11.glOrtho(0, sr.getScaledWidth(), sr.getScaledHeight(), 0, -1, 1);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();

        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer wr = tessellator.getWorldRenderer();

        GlStateManager.disableTexture2D();

        // Fond noir semi-transparent
        wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
        wr.pos(left,  bottom, 0).color(0f, 0f, 0f, 0.5f).endVertex();
        wr.pos(right, bottom, 0).color(0f, 0f, 0f, 0.5f).endVertex();
        wr.pos(right, top,    0).color(0f, 0f, 0f, 0.5f).endVertex();
        wr.pos(left,  top,    0).color(0f, 0f, 0f, 0.5f).endVertex();
        tessellator.draw();

        // Bordure colorée
        wr.begin(2, DefaultVertexFormats.POSITION_COLOR);
        wr.pos(left,  top,    0).color(r, g, b, a).endVertex();
        wr.pos(right, top,    0).color(r, g, b, a).endVertex();
        wr.pos(right, bottom, 0).color(r, g, b, a).endVertex();
        wr.pos(left,  bottom, 0).color(r, g, b, a).endVertex();
        tessellator.draw();

        // Texte
        GlStateManager.enableTexture2D();
        fontrenderer.drawStringWithShadow(
                displayText,
                screenX - textWidth / 2f,
                screenY - fontHeight / 2f,
                0xFFFFFFFF
        );

        // Restauration
        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.popMatrix();

        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.color(1f, 1f, 1f, 1f);
        GlStateManager.popMatrix();
    }

}

