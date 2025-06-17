package es.cadox8.deud.entities.components;

import java.awt.*;

public abstract class AbstractComponent {

    public AbstractComponent() {

    }

    // --- ---
    public abstract void tick();
    public abstract void render(Graphics g);
    // --- ---
}
