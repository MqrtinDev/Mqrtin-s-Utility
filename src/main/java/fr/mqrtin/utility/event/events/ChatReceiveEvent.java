package fr.mqrtin.utility.event.events;

import fr.mqrtin.utility.event.CancellableEvent;
import net.minecraft.util.IChatComponent;

public class ChatReceiveEvent extends CancellableEvent {

    private final byte type;
    private final IChatComponent chatComponent;

    public ChatReceiveEvent(byte type, IChatComponent chatComponent) {
        this.type = type;
        this.chatComponent = chatComponent;
    }

    public byte getType() {
        return type;
    }

    public IChatComponent getChatComponent() {
        return chatComponent;
    }
}

