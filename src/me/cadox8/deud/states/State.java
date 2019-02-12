package me.cadox8.deud.states;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;

import java.awt.*;

public abstract class State {

    protected GameAPI gameAPI;

    @Getter @Setter private static State state = null;

    public State(GameAPI gameAPI) {
        this.gameAPI = gameAPI;
    }


    public abstract void tick();
    public abstract void render(Graphics g);
}
