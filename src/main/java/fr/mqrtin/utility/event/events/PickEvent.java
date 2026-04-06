package fr.mqrtin.utility.event.events;

import fr.mqrtin.utility.event.CancellableEvent;
import net.minecraft.util.MovingObjectPosition;

public class PickEvent extends CancellableEvent {

    private MovingObjectPosition target;

    public PickEvent(MovingObjectPosition target) {
        this.target = target;
    }

    public MovingObjectPosition getTarget() {
        return target;
    }

    public void setTarget(MovingObjectPosition target) {
        this.target = target;
    }
}

