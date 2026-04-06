package fr.mqrtin.utility.event.events;

import fr.mqrtin.utility.event.Event;

public class UpdateEvent extends Event {

    public enum Stage {
        PRE,
        POST
    }

    private final Stage stage;

    public UpdateEvent(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}

