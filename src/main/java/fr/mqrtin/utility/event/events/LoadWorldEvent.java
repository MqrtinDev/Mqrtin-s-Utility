package fr.mqrtin.utility.event.events;

import fr.mqrtin.utility.event.Event;
import net.minecraft.world.World;

public class LoadWorldEvent extends Event {

    private final World world;

    public LoadWorldEvent(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }
}

