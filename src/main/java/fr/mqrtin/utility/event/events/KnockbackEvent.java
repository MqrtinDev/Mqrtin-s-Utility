package fr.mqrtin.utility.event.events;

import fr.mqrtin.utility.event.CancellableEvent;

public class KnockbackEvent extends CancellableEvent {

    private float strength;
    private float ratioX;
    private float ratioZ;

    public KnockbackEvent(float strength, float ratioX, float ratioZ) {
        this.strength = strength;
        this.ratioX = ratioX;
        this.ratioZ = ratioZ;
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public float getRatioX() {
        return ratioX;
    }

    public void setRatioX(float ratioX) {
        this.ratioX = ratioX;
    }

    public float getRatioZ() {
        return ratioZ;
    }

    public void setRatioZ(float ratioZ) {
        this.ratioZ = ratioZ;
    }
}

