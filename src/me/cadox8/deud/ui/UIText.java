package me.cadox8.deud.ui;

import java.awt.*;

public class UIText extends UIObject {

    private String text;
    private Color color;
    private ClickListener clicker;

    private int fixedX, fixedY;

    public UIText(float x, float y, Color color, String text, ClickListener clicker) {
        super(x, y - 12, 250, 15);
        this.text = text;
        this.color = color;
        this.clicker = clicker;

        fixedX = (int)x;
        fixedY = (int)y;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.drawString(text, fixedX, fixedY);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
