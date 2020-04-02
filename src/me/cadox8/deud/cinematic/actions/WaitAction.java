package me.cadox8.deud.cinematic.actions;

public class WaitAction extends Actions {

    private double time;

    public WaitAction(double time) {
        super(ActionType.WAIT);

        this.time = time;
    }

    @Override
    public void perform() {

    }
}
