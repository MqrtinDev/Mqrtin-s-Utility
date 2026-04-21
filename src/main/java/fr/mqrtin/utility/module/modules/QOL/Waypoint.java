package fr.mqrtin.utility.module.modules.QOL;

import fr.mqrtin.utility.event.EventTarget;
import fr.mqrtin.utility.event.events.Render3DEvent;
import fr.mqrtin.utility.module.ModuleCategory;
import fr.mqrtin.utility.module.impl.Module;
import fr.mqrtin.utility.module.property.properties.BooleanProperty;
import fr.mqrtin.utility.module.property.properties.FloatProperty;
import fr.mqrtin.utility.module.property.properties.IntProperty;
import fr.mqrtin.utility.utils.RenderUtil;
import net.minecraft.client.Minecraft;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Waypoint extends Module {

    private final List<RenderUtil.NametagData> waypoints;

    private final IntProperty waypointTextSize = new IntProperty("Text Size", 50, 0, 50);
    private final IntProperty waypointPaddingSize = new IntProperty("Padding Size", 5, 0, 50);
    private final IntProperty waypointMaxDistance = new IntProperty("Max Distance", 500, -1, 5000);

    public Waypoint() {
        super("Waypoint", ModuleCategory.QOL, false);
        this.waypoints = new ArrayList<>();
        waypoints.add(new RenderUtil.NametagData("0", Color.GREEN, 0, 75, 0));
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

        waypoints.forEach( nametagData -> RenderUtil.renderWaypoint(nametagData, waypointTextSize.getValue(), waypointPaddingSize.getValue(), waypointMaxDistance.getValue()));
    }
}
