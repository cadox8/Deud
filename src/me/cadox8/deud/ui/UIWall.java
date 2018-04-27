package me.cadox8.deud.ui;

import java.awt.*;

public class UIWall extends UIObject {

    private Color color;

    public UIWall(float x, float y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.fillRect((int) x, (int) y, width, height);
        g.setColor(color);
    }

    @Override
    public void onClick() {
    }
}
