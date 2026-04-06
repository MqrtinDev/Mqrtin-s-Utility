package fr.mqrtin.utility.event.events;

import fr.mqrtin.utility.event.CancellableEvent;

public class RaytraceEvent extends CancellableEvent {

    private double reachDistance;

    public RaytraceEvent(double reachDistance) {
        this.reachDistance = reachDistance;
    }

    public double getReachDistance() {
        return reachDistance;
    }

    public void setReachDistance(double reachDistance) {
        this.reachDistance = reachDistance;
    }
}

