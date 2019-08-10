package me.cadox8.deud.cinematic;

import me.cadox8.deud.cinematic.actions.Actions;

import java.util.ArrayList;
import java.util.Arrays;

public class Cinematic {

    private ArrayList<Actions> actions;

    public Cinematic() {
        actions = new ArrayList<>();
    }

    public void execute() {
        actions.forEach(Actions::perform);
    }

    public void addActions(Actions... actions) {
        this.actions.addAll(Arrays.asList(actions));
    }
}
