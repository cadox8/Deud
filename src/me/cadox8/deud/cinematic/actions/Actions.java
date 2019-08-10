package me.cadox8.deud.cinematic.actions;

public abstract class Actions {

    private final ActionType type;

    public Actions(ActionType type) {
        this.type = type;
    }

    public abstract void perform();

    public enum ActionType {
        GO, WAIT, TALK
    }
}
