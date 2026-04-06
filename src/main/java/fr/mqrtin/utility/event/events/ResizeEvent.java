package fr.mqrtin.utility.event.events;

import fr.mqrtin.utility.event.Event;

public class ResizeEvent extends Event {

    private final int width;
    private final int height;

    public ResizeEvent(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

