package fr.mqrtin.utility.event.events;

import fr.mqrtin.utility.event.CancellableEvent;
import net.minecraft.network.Packet;

public class PacketEvent extends CancellableEvent {

    public enum State {
        INCOMING,
        OUTGOING
    }

    private final State state;
    private final Packet<?> packet;

    public PacketEvent(State state, Packet<?> packet) {
        this.state = state;
        this.packet = packet;
    }

    public State getState() {
        return state;
    }

    public Packet<?> getPacket() {
        return packet;
    }
}

