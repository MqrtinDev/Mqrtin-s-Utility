package fr.mqrtin.utility.event.events;

import fr.mqrtin.utility.event.Event;
import net.minecraft.client.entity.EntityPlayerSP;

public class PlayerUpdateEvent extends Event {

    public enum Stage {
        PRE,
        POST
    }

    private final Stage stage;
    private final EntityPlayerSP player;

    public PlayerUpdateEvent(Stage stage, EntityPlayerSP player) {
        this.stage = stage;
        this.player = player;
    }

    public Stage getStage() {
        return stage;
    }

    public EntityPlayerSP getPlayer() {
        return player;
    }
}

