package me.cadox8.deud.graphics;

import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;

@RequiredArgsConstructor
public class Sprites {

    private final BufferedImage sprites;

    public BufferedImage crop(int x, int y, int width, int height) {
        try {
            return sprites.getSubimage(x, y, width, height);
        } catch (RasterFormatException e) {
            e.printStackTrace();
            System.exit(6);
            return null;
        }
    }

    public static BufferedImage coloredSprite(int width, int height, Color color) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int a = color.getAlpha();
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                int p = (a << 24) | (r << 16) | (g << 8) | b; //pixel

                img.setRGB(x, y, p);
            }
        }
        return img;
    }

    public static BufferedImage randomImage(int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int a = (int) (Math.random() * 256);
                int r = (int) (Math.random() * 256);
                int g = (int) (Math.random() * 256);
                int b = (int) (Math.random() * 256);

                int p = (a << 24) | (r << 16) | (g << 8) | b;

                img.setRGB(x, y, p);
            }
        }
        return img;
    }
}
