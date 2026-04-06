package fr.mqrtin.utility.event.events;

import fr.mqrtin.utility.event.CancellableEvent;

public class MoveInputEvent extends CancellableEvent {

    private float strafe;
    private float forward;
    private boolean jump;
    private boolean sneak;

    public MoveInputEvent(float strafe, float forward, boolean jump, boolean sneak) {
        this.strafe = strafe;
        this.forward = forward;
        this.jump = jump;
        this.sneak = sneak;
    }

    public float getStrafe() {
        return strafe;
    }

    public void setStrafe(float strafe) {
        this.strafe = strafe;
    }

    public float getForward() {
        return forward;
    }

    public void setForward(float forward) {
        this.forward = forward;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public boolean isSneak() {
        return sneak;
    }

    public void setSneak(boolean sneak) {
        this.sneak = sneak;
    }
}

