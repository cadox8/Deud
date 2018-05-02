package me.cadox8.deud.states;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.API;

import java.awt.*;

public abstract class State {

    protected API API;

    @Getter @Setter private static State state = null;

    public State(API API) {
        this.API = API;
    }


    public abstract void tick();
    public abstract void render(Graphics g);
}
