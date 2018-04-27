package me.cadox8.deud.utils;

import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DeudColor {

    public static final DeudColor WHITE = new DeudColor(255, 255, 255);
    public static final DeudColor BLACK = new DeudColor(0, 0, 0);
    public static final DeudColor RED = new DeudColor(255, 51, 51);
    public static final DeudColor YELLOW = new DeudColor(255, 255, 0);
    public static final DeudColor DARK_RED = new DeudColor(182, 0, 0);

    public static final DeudColor TRANSPARENT = new DeudColor(224, 224, 224, 50);

    //Class Utils
    @Getter private int red;
    @Getter private int green;
    @Getter private int blue;
    @Getter private int alpha;

    private DeudColor(int red, int green, int blue) {
        this(red, green, blue, 255);
    }

    private DeudColor(int red, int green, int blue, int alpha) {
        if (red > 255 || red < 0) return;
        if (blue > 255 || blue < 0) return;
        if (green > 255 || green < 0) return;
        if (alpha > 255 || alpha < 0) return;

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public BufferedImage colorImage(BufferedImage image, Color color) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, color.getRGB());
            }
        }
        return image;
    }

    public Color toColor() {
        return new Color(red, green, blue, alpha);
    }

    public static DeudColor fromRGB(int r, int g, int b) {
        return new DeudColor(r, g, b);
    }

    //Class
    public Color setRed(int red) {
        return new Color(red, getGreen(), getBlue(), getAlpha());
    }

    public Color setGreen(int green) {
        return new Color(getRed(), green, getBlue(), getAlpha());
    }

    public Color setBlue(int blue) {
        return new Color(getRed(), getGreen(), blue, getAlpha());
    }

    public Color setAlpha(int alpha) {
        return new Color(getRed(), getGreen(), getBlue(), alpha);
    }

    @Override
    public String toString() {
        return "DeudColor:[RGBA:" + getRed() + "," + getGreen() + "," + getBlue() + "," + getAlpha() + "]";
    }
}
