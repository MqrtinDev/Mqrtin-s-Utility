package fr.mqrtin.utility.event.events;

import fr.mqrtin.utility.event.Event;

public class TickEvent extends Event {

    public enum Stage {
        START,
        END
    }

    private final Stage stage;

    public TickEvent(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}

