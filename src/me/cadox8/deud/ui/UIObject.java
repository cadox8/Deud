package me.cadox8.deud.ui;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIObject {

    @Getter @Setter protected float x, y;
    @Getter @Setter protected int width, height;
    @Getter @Setter protected Rectangle bounds;
    @Getter @Setter protected boolean hovering = false;

    public UIObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void onClick();

    public void onMouseMove(MouseEvent e) {
        if (bounds.contains(e.getX(), e.getY())) {
            hovering = true;
        } else {
            hovering = false;
        }
    }

    public void onMouseRelease(MouseEvent e) {
        if (hovering) onClick();
    }
}
