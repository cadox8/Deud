package es.cadox8.deud.ui.helpers;

import lombok.Data;
import lombok.ToString;

import java.awt.*;

@Data
@ToString
public class UiDimension {

    private int x = 0, y = 0, width = 0, height = 0;

    public UiDimension() {}

    public UiDimension(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public int getMaxWidth() {
        return this.x + this.width;
    }

    public int getMaxHeight() {
        return this.y + this.height;
    }

    public UiDimension add(int x, int y, int width, int height) {
        this.x += x;
        this.y += y;
        this.width += width;
        this.height += height;
        return this;
    }

    public UiDimension set(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        return this;
    }

    public UiDimension addX(int x) {
        this.setX(this.getX() + x);
        return this;
    }
    public UiDimension addY(int y) {
        this.setY(this.getY() + y);
        return this;
    }

    public static UiDimension fromUIDimension(UiDimension uiDimension) {
        return new UiDimension(uiDimension.getX(), uiDimension.getY(), uiDimension.getWidth(), uiDimension.getHeight());
    }
}
