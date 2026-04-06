package fr.mqrtin.utility.event.events;

import fr.mqrtin.utility.event.CancellableEvent;

public class WindowClickEvent extends CancellableEvent {

    private final int windowId;
    private final int slotId;
    private final int mouseButton;
    private final int mode;

    public WindowClickEvent(int windowId, int slotId, int mouseButton, int mode) {
        this.windowId = windowId;
        this.slotId = slotId;
        this.mouseButton = mouseButton;
        this.mode = mode;
    }

    public int getWindowId() {
        return windowId;
    }

    public int getSlotId() {
        return slotId;
    }

    public int getMouseButton() {
        return mouseButton;
    }

    public int getMode() {
        return mode;
    }
}

