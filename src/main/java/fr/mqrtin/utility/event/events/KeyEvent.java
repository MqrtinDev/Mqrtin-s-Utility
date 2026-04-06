package fr.mqrtin.utility.event.events;

import fr.mqrtin.utility.event.Event;

public class KeyEvent extends Event {

    private final int keyCode;

    public KeyEvent(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}

